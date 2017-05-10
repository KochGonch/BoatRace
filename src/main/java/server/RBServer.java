package server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class RBServer {
    public static List<ClientConnecton> clients = new ArrayList<ClientConnecton>();

    public static void main(String[] args){
        try {
            ServerSocket welcomeSocket = new ServerSocket(56789);
            while(true) {
                Socket connectionSocket = welcomeSocket.accept();
                System.out.println("Hi, client");
                ClientConnecton connecton = new ClientConnecton(connectionSocket);
                clients.add(connecton);
                connecton.start();
                System.out.println("Client connected");
                clients.remove(connecton); // когда проиграл
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
