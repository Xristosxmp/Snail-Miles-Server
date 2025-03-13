package com.snailmiles.app;

import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyWebSocketHandler extends TextWebSocketHandler {
    private static final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private static final Map<String, Map<String, Object>> sessionsData = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("New connection: " + session.getId());
    }

    @Override protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Parse the incoming JSON message
        String payload = message.getPayload();

        // Parse the JSON string to a Map
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> data = objectMapper.readValue(payload, Map.class);

        // Extract the values from the Map and convert to Long
        String userId = ((String) data.get("user_id"));
        String offerId = ((String) data.get("offer_id"));
        String chainId = ((String) data.get("chain_id"));
        Long offerPoints = ((Number) data.get("offer_points")).longValue();

        // Store session and data
        sessions.put(userId, session);
        sessionsData.put(userId, data);  // Store the data associated with the user

        System.out.println("User ID: " + userId + ", Offer ID: " + offerId + ", Chain ID: " + chainId + ", Offer Points: " + offerPoints);
    }

    @Override public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Remove session and associated data when the connection is closed
        sessions.remove(session.getId());
        sessionsData.remove(session.getId());
        System.out.println("Session closed: " + session.getId());
    }

    // Function to send a message to a specific user
    public static void sendMessageToUser(String userId, String message) throws Exception {
        WebSocketSession session = sessions.get(userId);
        if (session != null && session.isOpen()) {
            session.sendMessage(new TextMessage(message));
        }
    }

    // Function to retrieve data for a specific user
    public static Map<String, Object> getUserData(String userId) {
        return sessionsData.get(userId);
    }

    public static boolean userExistFromSession(String userId) {
        if(sessions.get(userId) == null) return false;
        if(sessions.get(userId).isOpen()) return true;
        return false;
    }
}
