package TP2;

import java.util.Hashtable;
import java.util.Vector;

public interface IParcAuto extends java.rmi.Remote {

	public Voiture rechercherVoiture(int noSerie)throws java.rmi.RemoteException;
	public Vector<Voiture> rechercherVoiture(String marque)throws java.rmi.RemoteException;
	public boolean ajouterVoiture(int noSerie, String marque,
			String modele, double poids, double prix, String couleur, String annee)throws java.rmi.RemoteException;
	public void sauverDonnees() throws java.rmi.RemoteException;
	public void chargerDonnes() throws java.rmi.RemoteException;
	
	
}
