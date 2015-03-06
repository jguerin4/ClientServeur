import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.io.File;

public class BanqueImpl extends java.rmi.server.UnicastRemoteObject implements Banque
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BanqueImpl() throws java.rmi.RemoteException {
		  // ne pas oublier d'appeler le constructeur de la classe anc�tre (UnicastRemoteObject)
		  super(); 
		  // clients = new Hashtable();
		}
  	public String getMessage() throws java.rmi.RemoteException  {
  		return "Exemple correct!";
 	}
  	
  	//Les fonctions vont ici
   public static void main(String args[])
   {
        try
        {
        	File f1= new File ("./bin");
      	  	String codeBase=f1.getAbsoluteFile().toURI().toURL().toString();
      	  	System.setProperty("java.rmi.server.codebase", codeBase);
      	  
        	int port = 7878;
        	System.out.println("In BanqueImpl----!");
            Registry registry = LocateRegistry.getRegistry(port);
            // Banque remoteReference = (Banque) UnicastRemoteObject.exportObject(new BanqueImpl());
            BanqueImpl remoteReference = new BanqueImpl();
            System.out.println("In BanqueImpl!");
            registry.rebind("rmi://localhost:7878/AppletRMIBanque", remoteReference);
            System.out.println("In BanqueImpl===============!");
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
              
              
              
              
   

                                                                                                                                                                                                                                                                                                            