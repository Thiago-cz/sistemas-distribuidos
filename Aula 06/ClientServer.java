import java.io.*;
import java.net.*;

public class ClientServer {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
             ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {

            // Enviar operação
            String operacao = "deposito"; // ou "deposito"
            int numeroConta = 1;
            double valor = 50.0;

            output.writeUTF(operacao);
            output.writeInt(numeroConta);
            output.writeDouble(valor);
            
            output.flush();
            

            // Receber resposta
            String resposta = input.readUTF();
            System.out.println("Resposta do servidor: " + resposta);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
