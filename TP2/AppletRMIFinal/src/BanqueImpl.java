import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BaseDeDonnee.ConnectionManager;

public class BanqueImpl extends java.rmi.server.UnicastRemoteObject implements
		Banque {
	/**
	 * Author: Johnny Guérin, Marcel Laliberté et Eric Dionne Based on code
	 * from: Hamid Mcheik
	 * 
	 */

	// Static car on veut que le serveur ne gère qu'un connection à la fois.
	private static final long serialVersionUID = 1L;
	private static CallableStatement callableStatement;
	private static String requeteSql;
	public static Connection connectionBD = null;
	private Compte compteAAfficher = new Compte();

	public BanqueImpl() throws java.rmi.RemoteException {
		// ne pas oublier d'appeler le constructeur de la classe ancï¿½tre
		// (UnicastRemoteObject)
		super();
		// clients = new Hashtable();
	}

	public static void main(String args[]) throws MalformedURLException,
			RemoteException {
		try {
			ConnectionManager.ajouterConnection("Banque");
			File f1 = new File("./bin");
			String codeBase = f1.getAbsoluteFile().toURI().toURL().toString();
			System.setProperty("java.rmi.server.codebase", codeBase);

			LocateRegistry.createRegistry(8989);
			Registry registry = LocateRegistry.getRegistry(8989);
			BanqueImpl serverReference = new BanqueImpl();
			registry.rebind("rmi://localhost:8989/AppletRMIBanque",
					serverReference);

			System.out.println("Rmi enregistré");
		} catch (MalformedURLException e) {
			System.out.println("Erreur de formation de l'url");
			System.out.println(e.toString());
		} catch (RemoteException e) {
			System.out.println("Erreur de connexion rmi a distance");
			System.out.println(e.toString());
		}

	}

	public void creerCompte(int id, String nom, String prenom,
			double soldeDepart) throws java.rmi.RemoteException {
		try {
			connectionBD = ConnectionManager.getInstance("Banque");

			requeteSql = "{call tp2creerCompte(?,?,?,?)}";

			callableStatement = connectionBD.prepareCall(requeteSql);

			callableStatement.setInt(1, id);
			callableStatement.setString(2, nom);
			callableStatement.setString(3, prenom);
			callableStatement.setDouble(4, soldeDepart);

			callableStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Erreur de connexion avec la bd Oracle:");
			System.out.println(e.toString());

		}

		finally {
			if (callableStatement != null)
				try {
					callableStatement.close();
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			if (connectionBD != null)
				try {
					connectionBD.close();
				} catch (Exception e) {
					System.out.println(e.toString());
				}
		}
	}

	public Compte afficherCompte(int id) throws java.rmi.RemoteException {
		try {
			connectionBD = ConnectionManager.getInstance("Banque");

			Statement stmt = null;
			ResultSet rs = null;

			
			String sql = "SELECT NOCOMPTE, NOM, PRENOM, SOLDE FROM TP2COMPTE WHERE NOCOMPTE=" + id;

			stmt = connectionBD.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				compteAAfficher.setId(rs.getInt("NOCOMPTE"));
				compteAAfficher.setNom(rs.getString("NOM"));
				compteAAfficher.setPrenom(rs.getString("PRENOM"));
				compteAAfficher.setSolde(rs.getDouble("SOLDE"));

			} else {
				System.out.println("Pas de compte 10");
			}

			rs.close();

		} catch (SQLException e) {
			System.out.println("Erreur de connexion avec la bd Oracle:");
			System.out.println(e.toString());

		}

		finally {
			if (callableStatement != null)
				try {
					callableStatement.close();
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			if (connectionBD != null)
				try {
					connectionBD.close();
				} catch (Exception e) {
					System.out.println(e.toString());
				}

		}
		return compteAAfficher;

	}

	public Boolean ajoutSomme(int id, double somme)
			throws java.rmi.RemoteException {
		try {
			connectionBD = ConnectionManager.getInstance("Banque");
			connectionBD.setAutoCommit(false);
			requeteSql = "{call tp2ajouterSomme(?,?)}";
			callableStatement = connectionBD.prepareCall(requeteSql);
			callableStatement.setInt(1, id);
			callableStatement.setDouble(2, somme);
			callableStatement.executeUpdate();
			connectionBD.commit();

		} catch (SQLException e) {
			System.out.println(e.toString());
			if (connectionBD != null) {
				try {
					System.err.print("La transaction est annulée");
					connectionBD.rollback();
					return false;
				} catch (SQLException excep) {
					System.out.println(excep.toString());
					return false;
				}
			}
		}

		finally {
			if (callableStatement != null)
				try {
					callableStatement.close();
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			if (connectionBD != null)
				try {
					connectionBD.close();
				} catch (Exception e) {
					System.out.println(e.toString());
				}
		}
		return true;

	}

	public Boolean retirerSomme(int id, double somme)
			throws java.rmi.RemoteException {
		try {
			connectionBD = ConnectionManager.getInstance("Banque");
			connectionBD.setAutoCommit(false);
			requeteSql = "{call tp2ajouterSomme(?,?)}";
			callableStatement = connectionBD.prepareCall(requeteSql);
			somme *= -1;
			callableStatement.setInt(1, id);
			callableStatement.setDouble(2, somme);
			callableStatement.executeUpdate();
			connectionBD.commit();

		} catch (SQLException e) {
			System.out.println(e.toString());
			if (connectionBD != null) {
				try {
					System.err.print("La transaction est annulée");
					connectionBD.rollback();
					return false;
				} catch (SQLException excep) {
					System.out.println(excep.toString());
					return false;
				}
			}
		}

		finally {
			if (callableStatement != null)
				try {
					callableStatement.close();
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			if (connectionBD != null)
				try {
					connectionBD.close();
				} catch (Exception e) {
					System.out.println(e.toString());
				}
		}
		return true;
	}
}
