package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServidorRMI {
    public static void main(String[] args) {
        try {
            ServidorImplementacao servidor = 
                new ServidorImplementacao();
            
            Registry registro = 
                LocateRegistry.createRegistry(1099);
            
            registro.rebind("ServidorCoordenadas", servidor);
            
            System.out.println("Servidor RMI pronto...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}