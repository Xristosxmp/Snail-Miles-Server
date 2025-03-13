package com.snailmiles.app;

import com.snailmiles.app.Models.Transaction;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repo.TransactionRepository;
import com.snailmiles.app.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class WebSocketMessageController {

    @Autowired private UserRepository userRepository; // Inject UserRepository to access DB
    @Autowired private TransactionRepository transactionRepository;

    @PostMapping("/transanct")
    public ResponseEntity<String> sendMessageToUser(@RequestBody Map<String, Object> request) throws Exception {
        // Convert user_id from the request to Long (assuming user_id is passed as part of the request body)
        String userId = (String) request.get("user_id");
        if(MyWebSocketHandler.userExistFromSession(userId) == false) return ResponseEntity.status(400).body("User with id " + userId + " not found with open Socket");


        // Retrieve the stored data for the user
        Map<String, Object> userData = MyWebSocketHandler.getUserData(userId);

        if (userData != null) {
            String offerId = ((String) userData.get("offer_id"));
            String chainId = ((String) userData.get("chain_id"));
            Long offerPoints = ((Number) userData.get("offer_points")).longValue();

            // Create a custom message
            String message = "200";
            try {


                // Retrieve the user from the database
                User user = userRepository.findById(userId).orElse(null);

                if (user != null) {
                    // Update user's points (subtract offer points)
                    int newPoints = user.getPoints() - offerPoints.intValue(); // Convert Long to int for point subtraction
                    if (newPoints < 0) {
                        // Send a message to the user via WebSocket
                        MyWebSocketHandler.sendMessageToUser(userId, "400");
                        return ResponseEntity.status(400).body("Insufficient Points for User");
                    }
                    user.setPoints(newPoints);
                    userRepository.save(user);
                    // Send a message to the user via WebSocket
                    MyWebSocketHandler.sendMessageToUser(userId, message);

                    Transaction transaction = new Transaction(userId, chainId, offerId, offerPoints, new Date());
                    transactionRepository.save(transaction);
                    return ResponseEntity.ok("User points updated to " + newPoints);
                } else {
                    MyWebSocketHandler.sendMessageToUser(userId, "404");
                    return ResponseEntity.status(400).body("User not found.");
                }
            } catch (Exception e) {
                MyWebSocketHandler.sendMessageToUser(userId, "404");
                return ResponseEntity.status(400).body("Error: " + e.getMessage());
            }
        } else {
            MyWebSocketHandler.sendMessageToUser(userId, "404");
            return ResponseEntity.status(400).body("User not connected or data not available.");
        }
    }
}
