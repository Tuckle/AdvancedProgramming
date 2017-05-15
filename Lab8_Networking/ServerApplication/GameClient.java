package AdvancedProgramming.Lab8_Networking.ServerApplication;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.SocketHandler;

/**
 * Created by apiriu on 5/8/2017.
 */
public class GameClient {
    private final static String SERVER_ADDRESS = "127.0.0.1";
    private final static int PORT = 8100;
//    private Socket socket;
//
//    public GameClient() throws IOException {
//        this.socket = new Socket(SERVER_ADDRESS, PORT);
//    }

    public static void main(String[] args) throws IOException {
        GameClient client = new GameClient();
        while (true) {
            String request = client.readFromKeyboard();
            if (request.equalsIgnoreCase("exit")) {
                break;
            }
            else if(request.equalsIgnoreCase("create_game") || request.equalsIgnoreCase("create game")) {
                break;
            }
            else if(request.equalsIgnoreCase("join_game")) {
                break;
            }
            else if(request.equalsIgnoreCase("extract_letters")){
                break;
            }
            else if(request.equalsIgnoreCase("submit_word")){
                break;
            }
            else {
                client.sendRequestToServer(request);
            }
        }
    }

    private void sendRequestToServer(String request) throws IOException {
        Socket socket = new Socket(SERVER_ADDRESS, PORT);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(request);
    }
//    ... //Implement the sendRequestToServer method

    private String readFromKeyboard() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
