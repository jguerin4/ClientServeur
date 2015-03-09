import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Banque extends Remote {
	void creerCompte(int id, String nom, String prenom, double soldeDepart) throws RemoteException;
}
