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
    public boolean [][] pos = new boolean [20][20]; // это типо битовое поле как у Бори

    public ClientConnecton(Socket socket) {
        this.socket = socket;  // инициализация сокета
        for (int i=0; i<20; i++)  // создание поля
            for (int j=0; j<20; j++) pos[i][j]=false;
        for (int i=1; i<20; i+=4) // созание препятствий
            for (int j=1; j<20; j+=4) {
                pos[i][j]=true;
            }
    }

    synchronized void sendHelm(ClientConnecton connection)  // это то что я при тебе перепечатывала у Бори
    {                                                      //провека свободна ли клетка и перемещение если свободна
        if ((clientCoord.getTX()-clientCoord.getX()>0)&&(!pos[clientCoord.getX()+1][clientCoord.getY()])) {
            clientCoord.Setdir(Direction.RIGHT);
            pos[clientCoord.getX()+1][clientCoord.getY()]=true;
            pos[clientCoord.getX()][clientCoord.getY()]=false;
        }
        else if ((clientCoord.getTX()-clientCoord.getX()<0)&&(!pos[clientCoord.getX()-1][clientCoord.getY()])) {
            clientCoord.Setdir(Direction.LEFT);
            pos[clientCoord.getX()-1][clientCoord.getY()]=true;
            pos[clientCoord.getX()][clientCoord.getY()]=false;
        }
        else if ((clientCoord.getTY()-clientCoord.getY()>0)&&(!pos[clientCoord.getX()][clientCoord.getY()+1])) {
            clientCoord.Setdir(Direction.GO);
            pos[clientCoord.getX()][clientCoord.getY()+1]=true;
            pos[clientCoord.getX()][clientCoord.getY()]=false;
        }
        else if ((clientCoord.getTY()-clientCoord.getY()<0)&&(!pos[clientCoord.getX()][clientCoord.getY()-1])) {
            clientCoord.Setdir(Direction.BACK);
            pos[clientCoord.getX()][clientCoord.getY()-1]=true;
            pos[clientCoord.getX()][clientCoord.getY()]=false;
        }
    }

    public boolean ChangeDir (Coord coord1)  // это тоже проверка нет ли рядом других лодок но которую я писала до битового поля
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

    public void removeClient(ClientConnecton connecton) {  //это вот как раз удаление клиента, хз где это лучше делать
        pos[clientCoord.getX()][clientCoord.getY()]=false;
        clients.remove(connecton);
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inFromClient = new ObjectInputStream(socket.getInputStream()); //переменная отвечающая за прием объекта
            ObjectOutputStream outToClient = new ObjectOutputStream(socket.getOutputStream()); //за передачу объекта
            while(true) {
                clientCoord = new Coord((Coord) inFromClient.readObject()); //присваиваем клиентКоорд полученный от клиента объект
                clientX = clientCoord.getX();  // это на всякий случай, чисто для удобства
                clientY = clientCoord.getY();
                sendHelm(this);
                //newCoord = new Coord(clientX + 2, clientY + 2, Direction.LEFT); // тестовое изменение координа
                                              // вообще тут должны быть проверки все и высчитывание новых координат и направления
                outToClient.writeObject(newCoord); // отправляем новый объект клиенту
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
