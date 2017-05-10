package server;

import client.Coord;
import client.Direction;

import java.net.Socket;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static server.RBServer.clients;

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

    public boolean ChangeDir (Coord coord1)
    {
        int x = 0;
        int y = 0;
        switch (coord1.getDIR()){
            case LEFT:
            {
                x = coord1.getX() - 1;
                y = coord1.getY();
                break;
            }
            case RIGHT:
            {
                x = coord1.getX() + 1;
                y = coord1.getY();
                break;
            }
            case GO:
            {
                x = coord1.getX();
                y = coord1.getY() + 1;
                break;
            }
            case STOP:
            {
                x = coord1.getX();
                y = coord1.getY() + 1;
                break;
            }
        }
        for (ClientConnecton client : clients){
            if ((x == client.clientX) || (y == client.clientY))
                return true;
        }
        return  false;
    }



    @Override
    public void run() {
        try {
            ObjectInputStream inFromClient = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outToClient = new ObjectOutputStream(socket.getOutputStream());
            while(true) {
                clientCoord = new Coord((Coord) inFromClient.readObject());
                clientX = clientCoord.getX();
                clientY = clientCoord.getY();
                newCoord = new Coord(clientX + 2, clientY + 2, Direction.LEFT);
                outToClient.writeObject(newCoord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
