package bank;

import java.util.*;

public class CreerCompte {
	// La Hashtable qui contient tous les comptes des clients
	static Hashtable clients = new Hashtable();;
	
	public void creer_compte(String id, double somme_initiale) { //throws  java.rmi.RemoteException {
	  // on crèe un nouveau compte et on le rajoute dans la Hashtable
	  Compte nouveau = new Compte(somme_initiale);
	  clients.put(id, nouveau);
	}
}
