package AdvancedProgramming.Lab8_Networking.ClientApplication;

import com.sun.deploy.util.SessionState;

import java.io.*;
import java.net.*;

/**
 * Created by apiriu on 5/8/2017.
 */
public class GameServer {
    private static final int PORT = 8100;
    private ServerSocket serverSocket;
    private boolean running = false;

    public static void main(String[] args)  {
        GameServer server = new GameServer();
        server.init();
        server.waitForClients(); //... handle the exceptions!
    }

    private void init() {
        try {
            this.serverSocket = new ServerSocket();
            this.serverSocket.setReuseAddress(true);
            this.serverSocket.bind(new InetSocketAddress(PORT));
            this.running = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void waitForClients() {
        while (this.running) {
            try {
                Socket client = this.serverSocket.accept();
//                System.out.println("New client connected.");
                new ClientThread(this, client).run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    ... // Implement the init() method: create the serverSocket and set running to true
//            ... // Implement the waitForClients() method: while running is true, create a new socket for every incoming client and start a ClientThread to execute its request.

    public void stop() throws IOException {
        this.running = false;
        serverSocket.close();
    }
}
