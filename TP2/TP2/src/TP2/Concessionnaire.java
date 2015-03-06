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
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Concessionnaire extends java.rmi.server.UnicastRemoteObject implements IParcAuto, IFacturation {
	
	private static final boolean modeOracle = true;
	
	private static final String ConnStr = "jdbc:oracle:thin:@dim-oracle.uqac.ca:1521/dimdb.uqac.ca";
	private static final String ConnUser = "8ASY200-01";
	private static final String ConnPwd = "eJAASn4Phm";
	
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
		Voiture maVoiture = null;
		
		if(modeOracle)
		{
			Connection conn = null;
			Statement stmt = null;
			String sql = "SELECT no_serie, marque, modele, poids, prix, couleur, annee FROM Voitures WHERE no_serie = " + noSerie ;
			try {
				conn = DriverManager.getConnection(ConnStr, ConnUser,ConnPwd);
				if (conn != null) {
					stmt = conn.createStatement();
			        ResultSet rs = stmt.executeQuery(sql);
			        
			        while (rs.next()) {
			        	maVoiture = new Voiture();
			            maVoiture.setM_noSerie(rs.getInt("no_serie"));
			            maVoiture.setM_marque(rs.getString("marque"));
			            maVoiture.setM_modele(rs.getString("modele"));
			            maVoiture.setM_poids(rs.getDouble("poids"));
			            maVoiture.setM_prix(rs.getDouble("prix"));
			            maVoiture.setM_couleur(rs.getString("couleur"));
			            maVoiture.setM_annee(Integer.toString(rs.getInt("annee")));
			        }
			        rs.close();
				} else {
					System.out.println("Erreur de connexion avec la bd Oracle");
				}
	 
			} catch (SQLException e) {
				System.out.println("Erreur de connexion avec la bd Oracle");
		
			}finally {
				if (stmt != null) 
					try { stmt.close(); } catch (Exception e) { } 
				if (conn != null) 
					try { conn.close(); } catch (Exception e) { } 
					     
			}
			
			return maVoiture;
		
		}else{
			maVoiture = voitures.get(noSerie);
			
			if(maVoiture != null)
				return maVoiture;
			else
				return null;
		}
	}
	

	public Vector<Voiture> rechercherVoiture(String marqueVoiture) throws java.rmi.RemoteException
	{
		
		Vector<Voiture> voitureAAfficher = new Vector<Voiture>(10,5);
		Voiture maVoiture = null;
		
		if(modeOracle)
		{
			Connection conn = null;
			Statement stmt = null;
			String sql = "SELECT no_serie, marque, modele, poids, prix, couleur, annee FROM Voitures WHERE marque = '" + marqueVoiture + "'" ;
			try {
	 
				conn = DriverManager.getConnection(ConnStr, ConnUser,ConnPwd);
				if (conn != null) {
					stmt = conn.createStatement();
			        ResultSet rs = stmt.executeQuery(sql);
			        
			        while (rs.next()) {
			        	maVoiture = new Voiture();
			            maVoiture.setM_noSerie(rs.getInt("no_serie"));
			            maVoiture.setM_marque(rs.getString("marque"));
			            maVoiture.setM_modele(rs.getString("modele"));
			            maVoiture.setM_poids(rs.getDouble("poids"));
			            maVoiture.setM_prix(rs.getDouble("prix"));
			            maVoiture.setM_couleur(rs.getString("couleur"));
			            maVoiture.setM_annee(Integer.toString(rs.getInt("annee")));
			            voitureAAfficher.add(maVoiture);
			            System.out.println("Une voiture");
			        }
			        rs.close();
				} else {
					System.out.println("Erreur de connexion avec la bd Oracle");
				}
	 
			} catch (SQLException e) {
				System.out.println("Erreur de connexion avec la bd Oracle");
		
			}finally {
				if (stmt != null) 
					try { stmt.close(); } catch (Exception e) { } 
				if (conn != null) 
					try { conn.close(); } catch (Exception e) { } 
					     
			}
			
			return voitureAAfficher;
		
		}else{

			for (Enumeration e = voitures.keys(); e.hasMoreElements(); )
			{	
				Object obj = e.nextElement();
				maVoiture = voitures.get(obj);
				
				if(maVoiture.getM_marque().equals(marqueVoiture))
					voitureAAfficher.add(maVoiture);
			}
				return voitureAAfficher;
		}
	}
	
	public boolean ajouterVoiture(int noSerie, String marque,
			String modele, double poids, double prix, String couleur, String annee)throws java.rmi.RemoteException
	{

		boolean resultat = false;
		
		if(modeOracle)
		{
			Connection conn = null;
			Statement stmt = null;
			
			try {
	 
				conn = DriverManager.getConnection(ConnStr, ConnUser,ConnPwd);
				if (conn != null) {
					String sql =  "SELECT no_serie FROM Voitures WHERE no_serie = " + noSerie ;
					stmt = conn.createStatement();
			        ResultSet rs = stmt.executeQuery(sql);
			        
			        //Si la voiture existe deja
			        if (rs.next()) {
			        	resultat = false;
			        	rs.close();
			        }else{
						sql = "INSERT INTO Voitures (no_serie, marque, modele, poids, prix, couleur, annee) ";
						sql += "VALUES (" + noSerie + ",'" + marque + "','" + modele + "'," + poids + "," + prix + ",'" + couleur + "'," + annee +")";

				        stmt.executeUpdate(sql);
				        resultat = true;			        	
			        }
				}
	 
			} catch (SQLException e) {
				System.out.println("Erreur sql avec la bd Oracle");
		
			}finally {
				if (stmt != null) 
					try { stmt.close(); } catch (Exception e) { } 
				if (conn != null) 
					try { conn.close(); } catch (Exception e) { } 
					     
			}
			
			return resultat;
		}else{
		
			Voiture nouvelleVoiture = new Voiture(noSerie, marque,modele, poids, prix, couleur, annee);
			Voiture voitureTest;
			
			for (Enumeration e = voitures.keys(); e.hasMoreElements(); )
				{	
					Object obj = e.nextElement();
					voitureTest = voitures.get(obj);
					
					if( voitureTest.getM_noSerie() == noSerie )
						return false;
				}

			voitures.put(nouvelleVoiture.getM_noSerie(), nouvelleVoiture);
			return true;
		}
	}
	
	public Facture rechercherFacture(int noSerie) throws java.rmi.RemoteException
	{
		
		Facture maFacture = null;
		
		if(modeOracle)
		{
			Connection conn = null;
			Statement stmt = null;
			String sql = "SELECT no_factures, montant, nomAcheteur FROM Factures WHERE no_factures = " + noSerie ;
			try {
				conn = DriverManager.getConnection(ConnStr, ConnUser,ConnPwd);
				if (conn != null) {
					stmt = conn.createStatement();
			        ResultSet rs = stmt.executeQuery(sql);
			        
			        while (rs.next()) {
			        	maFacture = new Facture();
			        	maFacture.setM_noFacture(rs.getInt("no_factures"));
			        	maFacture.setM_montant(rs.getDouble("montant"));
			        	maFacture.setM_nomAcheteur(rs.getString("nomAcheteur"));
			        }
			        rs.close();
				} else {
					System.out.println("Erreur de connexion avec la bd Oracle");
				}
	 
			} catch (SQLException e) {
				System.out.println("Erreur de connexion avec la bd Oracle");
		
			}finally {
				if (stmt != null) 
					try { stmt.close(); } catch (Exception e) { } 
				if (conn != null) 
					try { conn.close(); } catch (Exception e) { } 
					     
			}
			
			return maFacture;
		
		}else{
			maFacture = factures.get(noSerie);
			
			if(maFacture != null)
				return maFacture;
			else
				return null;
		}

	}
	public Vector<Facture> rechercherFacture(String nomAcheteur) throws java.rmi.RemoteException
	{
		
		Vector<Facture> factureAAfficher = new Vector<Facture>(10,5);
		Facture maFacture;
		
		if(modeOracle)
		{
			Connection conn = null;
			Statement stmt = null;
			String sql = "SELECT no_factures, montant, nomAcheteur FROM Factures WHERE nomAcheteur = '" + nomAcheteur + "'" ;
			try {
	 
				conn = DriverManager.getConnection(ConnStr, ConnUser,ConnPwd);
				if (conn != null) {
					stmt = conn.createStatement();
			        ResultSet rs = stmt.executeQuery(sql);
			        
			        while (rs.next()) {
			        	maFacture = new Facture();
			        	maFacture.setM_noFacture(rs.getInt("no_factures"));
			        	maFacture.setM_montant(rs.getDouble("montant"));
			        	maFacture.setM_nomAcheteur(rs.getString("nomAcheteur"));
			            
			        	factureAAfficher.add(maFacture);
			            
			        }
			        rs.close();
				} else {
					System.out.println("Erreur de connexion avec la bd Oracle");
				}
	 
			} catch (SQLException e) {
				System.out.println("Erreur de connexion avec la bd Oracle");
		
			}finally {
				if (stmt != null) 
					try { stmt.close(); } catch (Exception e) { } 
				if (conn != null) 
					try { conn.close(); } catch (Exception e) { } 
					     
			}
			
			return factureAAfficher;
		
		}else{
		
			for (Enumeration e = factures.keys(); e.hasMoreElements(); )
			{	
				Object obj = e.nextElement();
				maFacture = factures.get(obj);
				
				if(maFacture.getM_nomAcheteur().equals(nomAcheteur) )
					factureAAfficher.add(maFacture);
			}
		
			return factureAAfficher;
		}
	}

	public boolean ajouterFacture(int noFacture, String nomAcheteur, double montant) throws java.rmi.RemoteException
	{
		
boolean resultat = false;
		
		if(modeOracle)
		{
			Connection conn = null;
			Statement stmt = null;
			
			try {
	 
				conn = DriverManager.getConnection(ConnStr, ConnUser,ConnPwd);
				if (conn != null) {
					String sql =  "SELECT no_factures FROM Factures WHERE no_factures = " + noFacture ;
					stmt = conn.createStatement();
			        ResultSet rs = stmt.executeQuery(sql);
			        
			        //Si la facture existe deja
			        if (rs.next()) {
			        	resultat = false;
			        	rs.close();
			        }else{
						sql = "INSERT INTO Factures (no_factures, montant, nomAcheteur) ";
						sql += "VALUES (" + noFacture + "," +  montant + ",'" + nomAcheteur + "')";

				        stmt.executeUpdate(sql);
				        resultat = true;			        	
			        }
				}
	 
			} catch (SQLException e) {
				System.out.println("Erreur sql avec la bd Oracle");
		
			}finally {
				if (stmt != null) 
					try { stmt.close(); } catch (Exception e) { } 
				if (conn != null) 
					try { conn.close(); } catch (Exception e) { } 
					     
			}
			
			return resultat;
		}else{

			Facture nouvelleFacture = new Facture(noFacture, nomAcheteur, montant);
			Facture factureTest;
			
			for (Enumeration e = factures.keys(); e.hasMoreElements(); )
			{	
				Object obj = e.nextElement();
				factureTest = factures.get(obj);
				
				if(factureTest.getM_noFacture() == noFacture )
					return false;	//On quitte la fonction et retourne faux
			}

			factures.put(nouvelleFacture.getM_noFacture(), nouvelleFacture);
			return true;
		}
		
	}
	
	public void sauverDonnees() throws java.rmi.RemoteException
	{ 
		if(!modeOracle){
			saveHashtable(voitures,"voitures.ser");
			saveHashtable(factures,"factures.ser");
		}
	}

	private void saveHashtable(Hashtable h,String file)
	{
		try 
		{
			FileOutputStream fileOut = new FileOutputStream(file); 
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(h);
			out.close(); 
			fileOut.close();
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
		if(!modeOracle){
			Hashtable s;
			s = loadHashtable("voitures.ser");
			if(s != null)
				voitures = s;
			else
				System.out.println("voitures est null...\n");
	
			s = loadHashtable("factures.ser");
			if(s != null)
				factures = s;
			else
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
				FileInputStream fileIn = new FileInputStream(file);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				h = (Hashtable)in.readObject();
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
