package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServidorImplementacao extends UnicastRemoteObject 
    implements ServidorInterface {

    protected ServidorImplementacao() throws RemoteException {
        super();
    }

    @Override
    public String converterCoordenadas(double x, double y) 
        throws RemoteException {
        double r = Math.sqrt(x*x + y*y);
        double theta = Math.toDegrees(Math.atan2(y, x));
        
        return r + ";" + theta;
    }
}