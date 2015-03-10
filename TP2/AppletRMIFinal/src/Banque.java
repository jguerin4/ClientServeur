import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Banque extends Remote {
	void creerCompte(int id, String nom, String prenom, double soldeDepart) throws RemoteException;
	Compte afficherCompte(int id)  throws RemoteException;
	Boolean ajoutSomme(int id, double somme) throws RemoteException;
	Boolean retirerSomme(int id, double somme) throws RemoteException;
}
