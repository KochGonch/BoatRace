package client; /**
 * Created by Даша on 16.03.2017.
 */
import java.io.*;
import java.net.*;

public class RBClient {
    public static void main(String[] args){
        Coord coord = new Coord();
        Coord newcoord;
        Coord finishcoord = new Coord(5,5);

        int port;
        InetAddress localHost = null;
        try {
            localHost = Inet4Address.getLocalHost();
            //BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            Socket clientSocket = new Socket(localHost, 56789);
            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
            outToServer.writeObject(coord);
            newcoord = new Coord((Coord)inFromServer.readObject());
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