package server;

import java.net.Socket;

/**
 * Created by Даша on 16.03.2017.
 */
public class ClientConnecton extends Thread {
    private Socket socket;

    public ClientConnecton(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("ekhke");
    }
}
