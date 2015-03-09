import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.File;
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
	
	public static Connection connectionBD = null;
	
	
	private static final long serialVersionUID = 1L;

	
	public BanqueImpl() throws java.rmi.RemoteException {
		  // ne pas oublier d'appeler le constructeur de la classe ancï¿½tre (UnicastRemoteObject)
		  super(); 
		  // clients = new Hashtable();
		}
  	public String getMessage() throws java.rmi.RemoteException  {
  		return "Exemple correct!";
 	}
  	// Static car on veut que le serveur ne gère qu'un connection à la fois.
  	private static Statement banqueStmt;
	private static String requeteSql;
    private static ResultSet sqlResult;
  	//Les fonctions vont ici
   public static void main(String args[]) throws MalformedURLException, RemoteException
   {
        try
        {
//        	ConnectionManager.ajouterConnection("Banque");
//        	connectionBD = ConnectionManager.getInstance("Banque");
//        	
//        	banqueStmt = connectionBD.createStatement();
        	
        	/**
			 File f1= new File ("./bin");
			 String codeBase=f1.getAbsoluteFile().toURI().toURL().toString();
			 System.setProperty("java.rmi.server.codebase", codeBase);
			
			 int port = 8989;
			 System.out.println("In BanqueImpl----!");
			 Registry registry = LocateRegistry.getRegistry(port);
			 // Banque remoteReference = (Banque)
			 UnicastRemoteObject.exportObject(new BanqueImpl());
			 BanqueImpl remoteReference = new BanqueImpl();
			 System.out.println("In BanqueImpl!");
			 registry.rebind("rmi://localhost:8989/AppletRMIBanque",
			 remoteReference);
			 System.out.println("In BanqueImpl===============!");

        	/**/
        	
        	File f1= new File ("./bin");
        	String codeBase=f1.getAbsoluteFile().toURI().toURL().toString();
        	System.setProperty("java.rmi.server.codebase", codeBase);
        	
        	
        	LocateRegistry.createRegistry(8989);
        	Registry registry = LocateRegistry.getRegistry(8989);
        	BanqueImpl serverReference = new BanqueImpl();
        	
        	registry.rebind("rmi://localhost:8989/AppletRMIBanque",serverReference);
            
//            requeteSql = "ajouterCompte(1,'Laliberte','Bob')";
//            sqlResult = banqueStmt.executeQuery(requeteSql);
//            
//            if (sqlResult.next()) {
//            	System.out.println("Contenu de la réponse à la requête:");
//		        System.out.println(sqlResult.toString());
//	        }
//
//            sqlResult.close();
//            
        }
//        catch (SQLException e) {
//			System.out.println("Erreur de connexion avec la bd Oracle");
//	
//		}
        catch (MalformedURLException e) {
			System.out.println("Erreur de formation de l'url");
	
		}
        catch (RemoteException e) {
			System.out.println("Erreur de connexion rmi a distance");
	
		}
        
        finally {
			if (banqueStmt != null) 
				try { banqueStmt.close(); } catch (Exception e) { } 
			
				     
		}
    }
}
              
              
              
              
   

                                                                                                                                                                                                                                                                                                            