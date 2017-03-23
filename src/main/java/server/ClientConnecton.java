package server;

import client.Coord;

import java.net.Socket;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Даша on 16.03.2017.
 */
public class ClientConnecton extends Thread {
    private Socket socket;
    private Coord clientCoord;
    private Coord newCoord;
    int clientX;
    int clientY;

    public ClientConnecton(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inFromClient = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outToClient = new ObjectOutputStream(socket.getOutputStream());
            clientCoord = new Coord((Coord)inFromClient.readObject());
            clientX = clientCoord.getX();
            clientY = clientCoord.getY();
            newCoord = new Coord(clientX + 2, clientY + 2);
            outToClient.writeObject(newCoord);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
