package bank;


import java.util.*;

/*
* G�re la position d'un compte.
* Note : il faut implanter l'interface Serializable pour qu'une instance
* de la classe Position puisse �tre envoy�e par le serveur au client.
*/
public class Position implements java.io.Serializable {
	public double solde;
	public Date derniereOperation;

	// constructeur
	public Position(double solde) {
			this.solde = solde;
			this.derniereOperation = new Date();
	}
}
