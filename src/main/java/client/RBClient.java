package client; /**
 * Created by Даша on 16.03.2017.
 */
import java.io.*;
import java.net.*;
import java.awt.*;
import java.util.Timer;

public class RBClient {
    //public static Coord coord;
    public static Coord coord = new Coord(3,4, Direction.GO); // здесь не надо инициализацию наверное, это ради тестирования
    public Coord finishcoord = new Coord(5,5, Direction.STOP);
    int port;
    public static boolean flag = true;

    public static void main(String[] args){
        // создание и отрисовка объекта лодка\ считывание координат с экрана
        coord = new Coord();
        InetAddress localHost = null;  // для связи с сервером
        try {
            localHost = Inet4Address.getLocalHost();
            //BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); // эта хрень вроде не нужна
            Socket clientSocket = new Socket(localHost, 56789);  //создание сокета
            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());  //для отправки
            ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream()); //для приема
            while(flag) {   // цикл обращения к серверу
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                outToServer.writeObject(coord); // отправляет объект
                coord = new Coord((Coord) inFromServer.readObject()); //получает исправленный
                coord.print();  //печатает результат
            }
            // refresh(); !!!! // дальше надо хрень которая будет на графике менять, но наверное ее не сюда

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