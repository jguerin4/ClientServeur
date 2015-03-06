
import java.rmi.Remote;

import java.rmi.RemoteException;

public interface Banque extends Remote
{
    String getMessage () throws RemoteException;
}

