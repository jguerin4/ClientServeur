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

public class BanqueImpl extends java.rmi.server.UnicastRemoteObject implements Banque
{
	/** Author: Johnny Guérin, Marcel Laliberté et Eric Dionne
	 * 	Based code author: Hamid Mcheik
	 * 
	 */
	
	
	
	
	private static final long serialVersionUID = 1L;

	
	public BanqueImpl() throws java.rmi.RemoteException {
		  // ne pas oublier d'appeler le constructeur de la classe ancï¿½tre (UnicastRemoteObject)
		  super(); 
		  // clients = new Hashtable();
		}
  	public String getMessage() throws java.rmi.RemoteException  {
  		System.out.println("getMessage Appel!!");
  		return "Exemple correct!";
 	}
  	// Static car on veut que le serveur ne gère qu'un connection à la fois.
  	private static CallableStatement callableStatement;
	private static String requeteSql;
    private static ResultSet sqlResult;
    public static Connection connectionBD = null;
  	//Les fonctions vont ici
   public static void main(String args[]) throws MalformedURLException, RemoteException
   {
        try
        {
        	
        	File f1= new File ("./bin");
        	String codeBase=f1.getAbsoluteFile().toURI().toURL().toString();
        	System.setProperty("java.rmi.server.codebase", codeBase);
        	
        	
        	LocateRegistry.createRegistry(8989);
        	Registry registry = LocateRegistry.getRegistry(8989);
        	BanqueImpl serverReference = new BanqueImpl();
        	registry.rebind("rmi://localhost:8989/AppletRMIBanque",serverReference);
        	
        	
        	ConnectionManager.ajouterConnection("Banque");
        	connectionBD = ConnectionManager.getInstance("Banque");
        	
        	requeteSql = "{call tp2creerCompte(?,?,?,?)}";
        	
        	callableStatement = connectionBD.prepareCall(requeteSql);
        	
        	callableStatement.setInt(1, 420);
        	callableStatement.setString(2, "Dionne");
        	callableStatement.setString(3, "Éric");
        	callableStatement.setDouble(4, 25);

            callableStatement.executeUpdate();
            

        }
        catch (SQLException e) {
			System.out.println("Erreur de connexion avec la bd Oracle:");
			System.out.println(e.toString());
	
		}
        catch (MalformedURLException e) {
			System.out.println("Erreur de formation de l'url");
			System.out.println(e.toString());
		}
        catch (RemoteException e) {
			System.out.println("Erreur de connexion rmi a distance");
			System.out.println(e.toString());
		}
        
        finally {
			if (callableStatement != null) 
				try { callableStatement.close(); } catch (Exception e) { System.out.println(e.toString());} 
			if( connectionBD != null)
				try { connectionBD.close();		 } catch (Exception e) { System.out.println(e.toString());}
				     
		}
    }
}
              
              
              
              
   

                                                                                                                                                                                                                                                                                                            