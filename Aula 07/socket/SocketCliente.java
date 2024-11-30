package socket;

import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

public class SocketCliente {
    public static void main(String[] args) {
        try {
            String input = JOptionPane.showInputDialog(
                "Digite as coordenadas (x;y):"
            );
            
            Socket socket = new Socket("localhost", 12345);
            
            PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true
            );
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            out.println(input);
            
            String resposta = in.readLine();
            String[] resultado = resposta.split(";");
            
            JOptionPane.showMessageDialog(null, 
                "Coordenadas Polares:\n" +
                "r = " + resultado[0] + "\n" +
                "θ = " + resultado[1] + "°"
            );

            socket.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "Erro: " + e.getMessage()
            );
        }
    }
}