package com.snailmiles.app.Controller.transactions;

import com.snailmiles.app.DTO.transactions.CreateTransactionResponse;
import com.snailmiles.app.DTO.transactions.TransactionRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/socket")
@AllArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping("/transact")
    public ResponseEntity<CreateTransactionResponse> transaction(@RequestBody TransactionRequest request) {
        CreateTransactionResponse response = new CreateTransactionResponse();
        try {
            String privateUserTopic = "/user/" + request.getUser_id() + "/topic/onTransact";
            messagingTemplate.convertAndSend(privateUserTopic, response);
        }catch (Exception e){
            response.setStatus(400);
        }
        // Return response to HTTP client
        return ResponseEntity.ok(response);
    }
}
