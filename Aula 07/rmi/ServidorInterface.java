package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServidorInterface extends Remote {
    String converterCoordenadas(double x, double y) 
        throws RemoteException;
}