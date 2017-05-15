package AdvancedProgramming.Lab8_Networking.ClientApplication;

import java.io.*;
import java.net.*;

/**
 * Created by apiriu on 5/8/2017.
 */
public class ClientThread extends Thread {
    private Socket socket = null;
    private final GameServer server;

    public ClientThread(GameServer server, Socket client) {
        this.server = server;
        this.socket = client;
    }

    //    ... // Create the constructor that receives a reference to the server and to the client socket
    public void run() {
        BufferedReader in = null; //client -> server stream
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String request = null;
        try {
            assert in != null;
            request = in.readLine();
            String response = execute(request);
            PrintWriter out = new PrintWriter(socket.getOutputStream()); //server -> client stream
            System.out.println(response);
            out.println(response);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close(); //... usse try-catch-finally to handle the exceptions!
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String execute(String request) {
        String message;

        if(request.equalsIgnoreCase("stop")) {
            message = "Server stopped";
        }
        else {
            message = "Server received the request " + request;
        }
        return message;
    }
}
