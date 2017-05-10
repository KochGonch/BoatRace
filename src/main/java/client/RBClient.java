package client; /**
 * Created by Даша on 16.03.2017.
 */
import java.io.*;
import java.net.*;
import java.awt.*;
import java.util.Timer;

public class RBClient {
    //public static Coord coord;
    public static Coord coord = new Coord(3,4, Direction.GO);
    public static Coord newcoord;
    public Coord finishcoord = new Coord(5,5, Direction.STOP);
    int port;
    public static boolean flag = true;

    public static void main(String[] args){
        // создание и отрисовка объекта лодка\ считывание координат с экрана

        coord = new Coord();
        InetAddress localHost = null;
        try {
            localHost = Inet4Address.getLocalHost();
            //BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            Socket clientSocket = new Socket(localHost, 56789);
            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
            while(flag) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                outToServer.writeObject(coord);
                newcoord = new Coord((Coord) inFromServer.readObject());
            }
            // refresh(); !!!!
            newcoord.print(newcoord);
            clientSocket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}