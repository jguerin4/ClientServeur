package TP2;

import java.util.Vector;

public interface IFacturation extends java.rmi.Remote {
	public Facture rechercherFacture(int noSerie)throws java.rmi.RemoteException;
	public Vector<Facture> rechercherFacture(String nomAcheteur)throws java.rmi.RemoteException;
	public boolean ajouterFacture(int noFacture, String nomAcheteur, double montant)throws java.rmi.RemoteException;
}
