package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;

public class ClienteRMI {
    public static void main(String[] args) {
        try {
            Registry registro = 
                LocateRegistry.getRegistry("localhost", 1099);
            
            ServidorInterface servidor = 
                (ServidorInterface) registro.lookup(
                    "ServidorCoordenadas"
                );
            
            String input = JOptionPane.showInputDialog(
                "Digite as coordenadas (x;y):"
            );
            
            String[] coord = input.split(";");
            double x = Double.parseDouble(coord[0]);
            double y = Double.parseDouble(coord[1]);
            
            String resultado = 
                servidor.converterCoordenadas(x, y);
            
            String[] polar = resultado.split(";");
            
            JOptionPane.showMessageDialog(null, 
                "Coordenadas Polares:\n" +
                "r = " + polar[0] + "\n" +
                "θ = " + polar[1] + "°"
            );
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "Erro: " + e.getMessage()
            );
        }
    }
}