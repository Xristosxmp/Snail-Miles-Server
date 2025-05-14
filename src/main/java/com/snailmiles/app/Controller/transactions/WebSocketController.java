package com.snailmiles.app.Controller.transactions;

import com.snailmiles.app.DTO.transactions.CreateTransactionResponse;
import com.snailmiles.app.DTO.transactions.TransactionRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/socket")
@AllArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final Map<String, String> socketSessions;

    @PostMapping("/transact")
    public ResponseEntity<CreateTransactionResponse> transaction(@RequestBody TransactionRequest request) {
        CreateTransactionResponse response = new CreateTransactionResponse();

        try {
            String topic = request.getUser_id();
            System.out.println(topic);
            if (topic != null) {
                String privateUserTopic = "/user/" + topic + "/topic/onTransact";
                messagingTemplate.convertAndSend(privateUserTopic, response);
            } else {
                System.out.println("User not connected: " + topic);
                response.setStatus(404);
                response.setMessage("Not connected");
            }
        } catch (Exception e) {
            response.setStatus(400);
        }

        return ResponseEntity.ok(response);
    }
}

