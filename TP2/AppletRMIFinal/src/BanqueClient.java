import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JApplet;
import javax.swing.JLabel;

public class BanqueClient extends JApplet implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Button CreerCompteButton;

	// Button wrongButton;
	 TextField idFieldTitle;
	 TextField nomFieldTitle;
	 TextField prenomFieldTitle;
	 TextField soldeFieldTitle;
	 
	 TextField idField;
	 TextField nomField;
	 TextField prenomField;
	 TextField soldeField;
	// CheckboxGroup radioGroup;
	// Checkbox radio1;
	// Checkbox radio2;
	// Checkbox radio3;

	public void init() {
		try {
			// Création des assets de l'applet
			setLayout(new FlowLayout());
			CreerCompteButton = new Button("Créer un compte");
			// wrongButton = new Button("Don't click!");
			idFieldTitle = new TextField("ID:");
			nomFieldTitle = new TextField("Nom:");
			prenomFieldTitle = new TextField("Prenom:");
			soldeFieldTitle = new TextField("Solde:");
			
			idField = new TextField("",10);
			nomField = new TextField("",10);
			prenomField = new TextField("",10);
			soldeField = new TextField("",10);
			
			
			
			idFieldTitle.setEditable(false);
			nomFieldTitle.setEditable(false);
			prenomFieldTitle.setEditable(false);
			soldeFieldTitle.setEditable(false);
			
			idField.setEditable(true);
			nomField.setEditable(true);
			prenomField.setEditable(true);
			soldeField.setEditable(true);
			// radioGroup = new CheckboxGroup();
			// radio1 = new Checkbox("Red", radioGroup,false);
			// radio2 = new Checkbox("Blue", radioGroup,true);
			// radio3 = new Checkbox("Green", radioGroup,false);
			add(CreerCompteButton);
			
			add(idFieldTitle);
			add(idField);
			
			add(nomFieldTitle);
			add(nomField);
			
			add(prenomFieldTitle);
			add(prenomField);
			
			add(soldeFieldTitle);
			add(soldeField);
			// add(wrongButton);
			// add(nameField);
			// add(radio1);
			// add(radio2);
			// add(radio3);
			
			CreerCompteButton.addActionListener(this);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == CreerCompteButton) {
			try {
				// établissement de la connexion RMI
				int port = 8989;
				Registry registry = LocateRegistry.getRegistry(getCodeBase()
						.getHost(), port);
				System.out.println("Création d'un compte ....");
				Banque remoteReference = (Banque) registry.lookup("rmi://localhost:8989/AppletRMIBanque");
				remoteReference.creerCompte(53,"Bissonette","Bob",50000.57);
				System.out.println("Compte créer!!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
