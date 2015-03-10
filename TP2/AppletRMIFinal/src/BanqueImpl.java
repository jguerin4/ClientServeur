import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import BaseDeDonnee.ConnectionManager;

public class BanqueImpl extends java.rmi.server.UnicastRemoteObject implements
		Banque {
	/**
	 * Author: Johnny Gu�rin, Marcel Lalibert� et Eric Dionne Based on code
	 * from: Hamid Mcheik
	 * 
	 */

	// Static car on veut que le serveur ne g�re qu'un connection � la fois.
	private static final long serialVersionUID = 1L;
	private static CallableStatement callableStatement;
	private static String requeteSql;
	public static Connection connectionBD = null;
	private Compte compteAAfficher = new Compte();

	public BanqueImpl() throws java.rmi.RemoteException {
		// ne pas oublier d'appeler le constructeur de la classe anc�tre
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

			System.out.println("Rmi enregistr�");
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

			// requeteSql = "{call tp2afficherCompte(?,?,?,?,?,?)}";
			//
			// callableStatement = connectionBD.prepareCall(requeteSql);
			//
			// callableStatement.setInt(1, id);
			// callableStatement.callableStatement.registerOutParameter(2,java.sql.Types.VARCHAR);
			// callableStatement.callableStatement.registerOutParameter(3,java.sql.Types.VARCHAR);
			// callableStatement.callableStatement.registerOutParameter(4,java.sql.Types.DOUBLE);
			// callableStatement.registerOutParameter(5,
			// java.sql.Types.INTEGER);
			// callableStatement.registerOutParameter(6,
			// java.sql.Types.VARCHAR);
			//
			// callableStatement.executeUpdate();
			//
			// compteAAfficher.setId(id);
			// compteAAfficher.setNom(callableStatement.getNString(2));
			// compteAAfficher.setPrenom(callableStatement.getNString(3));
			// compteAAfficher.setSolde(callableStatement.getNString(4));
			//
			//
			// int resultUpdate = callableStatement.getInt(5);
			// String errMessage = callableStatement.getNString(6);
			//
			//
			// if (resultUpdate != 0) {
			// System.out.println(errMessage);
			// }
			//
			// else {
			// System.out.println("Requ�te effectu� avec succ�s!");
			// }
			//
			// } catch (SQLException e) {
			// System.out.println("Erreur de connexion avec la bd Oracle:");
			// System.out.println(e.toString());
			//

			return compteAAfficher;
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

	public void ajoutSomme(int id, double somme)
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
			System.out.println("toute est beau");

		} catch (SQLException e) {
			System.out.println(e.toString());
			if (connectionBD != null) {
				try {
					System.err.print("La transaction est annul�e");
					connectionBD.rollback();
				} catch (SQLException excep) {
					System.out.println(excep.toString());
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

	}

	public void retirerSomme(int id, double somme)
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
			System.out.println("toute est beau");

		} catch (SQLException e) {
			System.out.println(e.toString());
			if (connectionBD != null) {
				try {
					System.err.print("La transaction est annul�e");
					connectionBD.rollback();
				} catch (SQLException excep) {
					System.out.println(excep.toString());
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
	}
}
