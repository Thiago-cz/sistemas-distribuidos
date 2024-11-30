package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class HandleCliente implements Runnable {
    private Socket cliente;

    public HandleCliente(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(
                new InputStreamReader(cliente.getInputStream())
            );
            PrintWriter saida = new PrintWriter(
                cliente.getOutputStream(), true
            );

            String coordenadas = entrada.readLine();
            String[] coord = coordenadas.split(";");
            double x = Double.parseDouble(coord[0]);
            double y = Double.parseDouble(coord[1]);

            // Cálculo Coordenadas Polares
            double r = Math.sqrt(x*x + y*y);
            double theta = Math.atan2(y, x);

            // Convertendo para graus
            theta = Math.toDegrees(theta);

            saida.println(r + ";" + theta);

            cliente.close();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
