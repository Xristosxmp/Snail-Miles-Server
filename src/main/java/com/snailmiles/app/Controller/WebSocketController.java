package com.snailmiles.app.Controller;

import com.snailmiles.app.Models.SocketObject;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/socket")
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/transact")
    public Map<String, String> transaction(@RequestBody SocketObject message) {
        Map<String, String> response = new HashMap<>();
        try {
            String privateUserTopic = "/user/" + message.getUser_id() + "/topic/onTransact";
            response.put("message", "200");
            messagingTemplate.convertAndSend(privateUserTopic, response);
        }catch (Exception e){e.printStackTrace();}
        // Return response to HTTP client
        return response;
    }
}
