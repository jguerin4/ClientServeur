
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JApplet;
import javax.swing.JLabel;

 
public class BanqueClient extends JApplet
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() {
        try {
			int port = 5077;
            Registry registry = LocateRegistry.getRegistry(getCodeBase().getHost(), port);
            System.out.println("In applet!");
            Banque remoteReference = (Banque) registry.lookup("AppletRMIBanque");
            getContentPane().add(new JLabel(remoteReference.getMessage()));
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}


