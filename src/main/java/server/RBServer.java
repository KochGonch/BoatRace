package server;

import java.io.*;
import java.net.*;

public class RBServer {
    public static void main(String[] args){
        try {
            ServerSocket welcomeSocket = new ServerSocket(12345);
            while(true) {
                Socket connectionSocket = welcomeSocket.accept();
                new ClientConnecton(connectionSocket).start();
                System.out.println("Client connected");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
