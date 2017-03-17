package client; /**
 * Created by Даша on 16.03.2017.
 */
import java.io.*;
import java.net.*;

public class RBClient {
    public static void main(String[] args){
        int port;
        InetAddress localHost = null;
        try {
            localHost = Inet4Address.getLocalHost();
            Socket clientSocket = new Socket(localHost, 12345);
            clientSocket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
