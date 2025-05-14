package com.snailmiles.app.Controller.transfer;

import com.snailmiles.app.DTO.transfer.*;
import com.snailmiles.app.Exceptions.transfer.InvalidTransferException;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/socket")
public class TransferPointsController {

    private final SimpMessagingTemplate messagingTemplate;
    private final Map<String, TransferSessionObject> tranferSessions;
    private final UserRepository userRepository;


    @PostMapping(value = "/onTransfer",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessTransferResponseOnAccept> transferPoints(@RequestBody TransferPointsRequest req){
        String topic = "/user/" + req.getSecret_key() + "/topic/onTransfer";
        String key = req.getSecret_key();

        User user_accepts = null;
        User user_sends;
        if (tranferSessions.containsKey(key)) {
            TransferSessionObject session = tranferSessions.get(key);

            user_accepts = userRepository.findById(req.getUser_accept_id()).get();
            user_sends = userRepository.findById(session.getUuid()).get();
            Integer points_to_tranfer = Integer.parseInt(session.getPoints_to_be_transfered());
            if(user_accepts.getPoints() + points_to_tranfer > 1000){
                String message = "Ο χρήστος δεν μπορεί να λάβει τόσους πόντους";
                int status = 400;
                messagingTemplate.convertAndSend(topic, InvalidTransferResponse.builder().withStatus(status).withMessage(message).build());
                throw new InvalidTransferException(message);
            }

            user_sends.setPoints(user_sends.getPoints() - points_to_tranfer);
            user_accepts.setPoints(user_accepts.getPoints() + points_to_tranfer);
            userRepository.save(user_accepts);
            userRepository.save(user_sends);
        }else{
            throw new InvalidTransferException("Δεν βρέθηκε η συναλλαγή για μεταφορά πόντων");
        }

        SuccessTransferResponseOnSend resOnSend = SuccessTransferResponseOnSend.builder().withStatus(200).withNew_points(user_sends.getPoints()).withMessage("Επιτυχής μεταφορά πόντων").build();
        messagingTemplate.convertAndSend(topic, resOnSend);

        SuccessTransferResponseOnAccept resOnAccept = SuccessTransferResponseOnAccept.builder().withStatus(200).withNew_points(user_accepts.getPoints()).withMessage("Επιτυχής μεταφορά πόντων").build();


        return ResponseEntity.ok().body(resOnAccept);
    }


}
