package socket;

import java.net.*;
import java.io.*;

public class SocketServidor {
    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(12345);
            System.out.println("Servidor Socket aguardando conexões...");

            while (true) {
                Socket cliente = servidor.accept();
                new Thread(new HandleCliente(cliente)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}