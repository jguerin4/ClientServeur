import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Banque extends Remote {
	void creerCompte(int id, String nom, String prenom, double soldeDepart) throws RemoteException;
	void afficherCompte(int id)  throws RemoteException;
	void ajoutSomme(int id, double somme) throws RemoteException;
	void retirerSomme(int id, double somme) throws RemoteException;
}
