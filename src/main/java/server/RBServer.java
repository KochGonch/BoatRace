package server;

import client.Coord;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class RBServer {
    public static List<ClientConnecton> clients = new ArrayList<ClientConnecton>(); // это список подключений клиента,
                                                                                    // когда подключается новый добавляяем сюда,
                                                                            //когда он проиграл или выйграл или отключился удаляем

    public static void main(String[] args){
        try {
            ServerSocket welcomeSocket = new ServerSocket(56789);   // это порт по которому соединяются с сервером
            while(true) {
                Socket connectionSocket = welcomeSocket.accept();    // асепт - это мы бесконечно ждем клиентов
                System.out.println("Hi, client");   // это просто вывод ради проверки
                ClientConnecton connecton = new ClientConnecton(connectionSocket); // когда подключился клиент создается класс
                                                                                    // который поток для его обработки
                clients.add(connecton); // это мы добавляем в список клиентов новое соединение
                connecton.start();  // запускаем поток обработки клиента
                System.out.println("Client connected");  // тоже ради прикола, можно удалить
                clients.remove(connecton); // когда проиграл, удаляем из списка, но там появилась функци удаления...
                                                // надо придумать как лучше это делать
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
