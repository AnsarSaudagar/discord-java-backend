package com.example.discord.runner;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SocketIOServerRunner implements CommandLineRunner {

    private final SocketIOServer socketIOServer;
    private final ConcurrentHashMap<String, String> userSessionMap = new ConcurrentHashMap<>();

    public SocketIOServerRunner(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;

        // Handle user connections
        socketIOServer.addConnectListener(onConnected());
        socketIOServer.addDisconnectListener(onDisconnected());
    }

    private ConnectListener onConnected() {
        return client -> {
            String userId = client.getHandshakeData().getSingleUrlParam("userId"); // Pass userId during connection
            if (userId != null) {
                userSessionMap.put(userId, client.getSessionId().toString());
                System.out.println("User connected: " + userId + " (Session: " + client.getSessionId() + ")");
            }
        };
    }

    private DisconnectListener onDisconnected() {
        return client -> {
            String sessionId = client.getSessionId().toString();
            userSessionMap.values().removeIf(value -> value.equals(sessionId));
            System.out.println("Client disconnected: " + sessionId);
        };
    }

    public void sendMessageToUser(String friend_id, String message) {
        System.out.println("friend id = " + friend_id);
        String sessionId = userSessionMap.get(friend_id);
        System.out.println("outside socket = " + sessionId);
        if (sessionId != null) {
            try {
                UUID uuid = UUID.fromString(sessionId);
                System.out.println("inside socket");
                socketIOServer.getClient(uuid).sendEvent("private_message", message);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid session ID: " + sessionId);
            }
        } else {
            System.out.println("User not connected: " + friend_id);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        // Start the Socket.IO server
        socketIOServer.start();
        System.out.println("Socket.IO server started on port " + socketIOServer.getConfiguration().getPort());

        // Add a shutdown hook to stop the server
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            socketIOServer.stop();
            System.out.println("Socket.IO server stopped.");
        }));
    }
}
