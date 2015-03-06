package TP2;



/**
 * Dans cette Classe, on utilise les versions de ParcAuto pour faire du traitement avec la BD.
 * 
 */

import java.util.*;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;

public class Concessionnaire extends java.rmi.server.UnicastRemoteObject implements IParcAuto, IFacturation {
	

	private Hashtable<Integer, Voiture> voitures;
	private Hashtable<Integer, Facture> factures;

	/**
	 * Valeur par défaut de la version pour la sérialisation.
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// constructeur qui crèe la Hashtable
	public Concessionnaire() throws java.rmi.RemoteException {
	// ne pas oublier d'appeler le constructeur de la classe ancêtre (UnicastRemoteObject)
	super();

	voitures= new Hashtable<Integer, Voiture>();
	factures = new Hashtable<Integer, Facture>();
	}
	
	
	public Voiture rechercherVoiture(int noSerie) throws java.rmi.RemoteException
	{
		Voiture maVoiture;
		maVoiture = voitures.get(noSerie);
		
		if(maVoiture != null)
			return maVoiture;
		
		else
			return null;
	}
	

	public Vector<Voiture> rechercherVoiture(String marqueVoiture) throws java.rmi.RemoteException
	{
		
		Vector<Voiture> voitureAAfficher = new Vector<Voiture>(10,5);
		Voiture voitureTest;
		
		for (Enumeration e = voitures.keys(); e.hasMoreElements(); )
			{	
				Object obj = e.nextElement();
				voitureTest = voitures.get(obj);
				
				if(voitureTest.getM_marque().equals(marqueVoiture) )
				{
					voitureAAfficher.add(voitureTest);
				}

			}

			return voitureAAfficher;
	}
	
	public boolean ajouterVoiture(int noSerie, String marque,
			String modele, double poids, double prix, String couleur, String annee)throws java.rmi.RemoteException
	{
		
		
		Voiture nouvelleVoiture = new Voiture(noSerie, marque,modele, poids, prix, couleur, annee);
		
		
		Voiture voitureTest;
		
		for (Enumeration e = voitures.keys(); e.hasMoreElements(); )
			{	
				Object obj = e.nextElement();
				voitureTest = voitures.get(obj);
				
				if( voitureTest.getM_noSerie() == noSerie )
				{
					return false;
				}

			}
		
		
		
		voitures.put(nouvelleVoiture.getM_noSerie(), nouvelleVoiture);
		return true;
		
	}
	
	public Facture rechercherFacture(int noSerie) throws java.rmi.RemoteException
	{
		Facture maFacture;
		maFacture = factures.get(noSerie);
		
		if(maFacture != null)
			return maFacture;
		else
			return null;

	}
	public Vector<Facture> rechercherFacture(String nomAcheteur) throws java.rmi.RemoteException
	{
		
		Vector<Facture> factureAAfficher = new Vector<Facture>(10,5);
		Facture factureTest;
		
		for (Enumeration e = factures.keys(); e.hasMoreElements(); )
		{	
			Object obj = e.nextElement();
			factureTest = factures.get(obj);
			
			if(factureTest.getM_nomAcheteur().equals(nomAcheteur) )
			{
				factureAAfficher.add(factureTest);
			}

		}
		
		
		return factureAAfficher;
	}

	public boolean ajouterFacture(int noFacture, String nomAcheteur, double montant) throws java.rmi.RemoteException
	{
		Facture nouvelleFacture = new Facture(noFacture, nomAcheteur, montant);
		
		
		Facture factureTest;
		
		for (Enumeration e = factures.keys(); e.hasMoreElements(); )
		{	
			Object obj = e.nextElement();
			factureTest = factures.get(obj);
			
			if(factureTest.getM_noFacture() == noFacture )
			{
				return false;	//On quitte la fonction et retourne faux
			}

		}
		

		factures.put(nouvelleFacture.getM_noFacture(), nouvelleFacture);
		return true;

		
	}
	
	public void sauverDonnees() throws java.rmi.RemoteException
	{ 
		saveHashtable(voitures,"voitures.ser");
		saveHashtable(factures,"factures.ser");
	}

	private void saveHashtable(Hashtable h,String file)
	{

		try 
		{
		
			System.out.println("Creating File/Object output stream...");
		
			FileOutputStream fileOut = new FileOutputStream(file); ObjectOutputStream out = new ObjectOutputStream(fileOut);
		
			System.out.println("Writing Hashtable Object..."); out.writeObject(h);
		
			System.out.println("Closing all output streams...\n"); out.close(); fileOut.close();

		} 
		
		catch(FileNotFoundException e)
		{ 
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void chargerDonnes() throws java.rmi.RemoteException
	{ 
		Hashtable s;
		s = loadHashtable("voitures.ser");
		if(s != null)
		{ 
			voitures = s;
		}
		else
		{
			System.out.println("voitures est null...\n");
			
		} 
		s = loadHashtable("factures.ser");
		if(s != null)
		{
			factures = s;
		}
		else
		{
			System.out.println("factures est null...\n");
			} 
	}

	private Hashtable loadHashtable(String file)
	{

		Hashtable h = null;
	
		try 
		{ 
			File f = new File(file);
			if(f.exists() && !f.isDirectory())
			{ 
				System.out.println("Creating File/Object input stream...");
				FileInputStream fileIn = new FileInputStream(file);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				System.out.println("Loading Hashtable Object...");
				h = (Hashtable)in.readObject();
				System.out.println("Closing all input streams...\n");
				in.close();
				fileIn.close();
			}
			else
			{
				h = null;
			}

	} 
		catch (ClassNotFoundException e)
		{ 
			e.printStackTrace();
		}
		catch(FileNotFoundException e)
		{ 
			e.printStackTrace();
		}
		catch (IOException e) 
		{ 
			e.printStackTrace();
		}

	return h; 
	
	
	}
	
	
	
	
	public static void main(String[] args) {
		// on positionne le gestionnaire de sécurité
		/// System.setSecurityManager(new RMISecurityManager());

		try {

		File f1= new File ("./bin");
		String codeBase=f1.getAbsoluteFile().toURI().toURL().toString();
		System.setProperty("java.rmi.server.codebase", codeBase);

		LocateRegistry.createRegistry(8989);

		// on crèe la banque et on l'enregistre grâce au Naming
		Concessionnaire con = new Concessionnaire();
		System.out.println("start server");
		java.rmi.Naming.rebind("rmi://localhost:8989/MonConcessionnaire", con);


		System.out.println("L'objet serveur RMI 'MonConcessionnaire' est enregistre");
		}
		catch(Exception e) {
		e.printStackTrace();
		}
		}
}
