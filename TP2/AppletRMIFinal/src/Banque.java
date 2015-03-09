
import java.rmi.Remote;

import java.rmi.RemoteException;

public interface Banque extends Remote
{
    void creerCompte () throws RemoteException;
}

