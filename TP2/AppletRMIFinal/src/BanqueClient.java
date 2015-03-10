import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class BanqueClient extends JApplet implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Créer un compte
	Button CreerCompteButton;

	TextField idFieldTitle;
	TextField nomFieldTitle;
	TextField prenomFieldTitle;
	TextField soldeFieldTitle;

	TextField idField;
	TextField nomField;
	TextField prenomField;
	TextField soldeField;
	//Voir les détails d'un compte
	Button detailCompteButton;
	
	TextField idDetailCompteTitle;
	TextField idDetailCompte;
	
	//Ajouter une somme sur un compte
	Button ajouterSomme;
	
	TextField idAjouterSommeTitle;
	TextField idAjouterSommeCompte;
	
	TextField montantAjoutTitle;
	TextField montantAjout;
	
	//Retirer une somme d'un compte
	Button retirerSomme;
	
	TextField idRetirerSommeTitle;
	TextField idRetirerSommeCompte;
	
	TextField montantRetraitTitle;
	TextField montantRetrait;

	JTextArea etatApplet;

	
	public void init() {
		
		try {
			// Création des assets de l'applet
			setLayout(new FlowLayout(5,10,5));
			getContentPane().setBackground( Color.orange );
			
			// Initialisation Créer compte
			CreerCompteButton = new Button("Créer un compte");
			CreerCompteButton.setBackground(java.awt.Color.red);
			CreerCompteButton.addActionListener(this);
			
			
			idFieldTitle = new TextField("ID:");
			idFieldTitle.setBackground(Color.yellow);
			nomFieldTitle = new TextField("Nom:");
			nomFieldTitle.setBackground(Color.yellow);
			prenomFieldTitle = new TextField("Prenom:");
			prenomFieldTitle.setBackground(Color.yellow);
			soldeFieldTitle = new TextField("Solde:");
			soldeFieldTitle.setBackground(Color.yellow);

			idField = new TextField("", 10);
			nomField = new TextField("", 10);
			prenomField = new TextField("", 10);
			soldeField = new TextField("", 10);

			idFieldTitle.setEditable(false);
			nomFieldTitle.setEditable(false);
			prenomFieldTitle.setEditable(false);
			soldeFieldTitle.setEditable(false);

			idField.setEditable(true);
			nomField.setEditable(true);
			prenomField.setEditable(true);
			soldeField.setEditable(true);

			add(CreerCompteButton);

			add(idFieldTitle);
			add(idField);

			add(nomFieldTitle);
			add(nomField);

			add(prenomFieldTitle);
			add(prenomField);

			add(soldeFieldTitle);
			add(soldeField);
			
			//Initialisation Voir les détails d'un compte
			detailCompteButton = new Button("         Voir les détails d'un compte         ");
			detailCompteButton.addActionListener(this);
			detailCompteButton.setBackground(Color.red);
			
			
			idDetailCompteTitle = new TextField("ID du compte à consulter:", 20);
			idDetailCompte = new TextField("",48);
			
			
			idDetailCompteTitle.setEnabled(false);
			idDetailCompte.setEnabled(true);
			
			add(detailCompteButton);
			add(idDetailCompteTitle);
			add(idDetailCompte);
			
			//Initialisation Ajouter une somme sur un compte
			ajouterSomme = new Button("      Ajouter de l'argent à un compte      ");
			ajouterSomme.setBackground(java.awt.Color.red);
			
			idAjouterSommeTitle = new TextField("ID du compte à mettre à jour:",20);
			idAjouterSommeCompte = new TextField("",5);
			
			montantAjoutTitle = new TextField("Montant à ajouter:");
			montantAjout = new TextField("",15);
			
			idAjouterSommeTitle.setEnabled(false);
			idAjouterSommeCompte.setEnabled(true);
			montantAjoutTitle.setEnabled(false);
			montantAjout.setEnabled(true);
					
			add(ajouterSomme);
			add(idAjouterSommeTitle);
			add(idAjouterSommeCompte);
			add(montantAjoutTitle);
			add(montantAjout);
						
			
			//Initialisation Retirer une somme d'un compte

			retirerSomme = new Button("      Retirer de l'argent à un compte      ");
			retirerSomme.setBackground(java.awt.Color.red);
			
			idRetirerSommeTitle = new TextField("ID du compte à mettre à jour:",20);
			idRetirerSommeCompte = new TextField("",5);
			montantRetraitTitle = new TextField("Montant à retirer:");
			montantRetrait = new TextField("",15);
			
			idRetirerSommeTitle.setEnabled(false);
			idRetirerSommeCompte.setEnabled(true);
			montantRetraitTitle.setEnabled(false);
			montantRetrait.setEnabled(true);
					
			add(retirerSomme);
			add(idRetirerSommeTitle);
			add(idRetirerSommeCompte);
			add(montantRetraitTitle);
			add(montantRetrait);
			
			
			// Rétroaction sur l'état de l'applet
			etatApplet = new JTextArea("Applet démarré!", 5,50);
			etatApplet.setEditable(false);
			etatApplet.setBackground(Color.LIGHT_GRAY);
			add(etatApplet);

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
				Banque remoteReference = (Banque) registry
						.lookup("rmi://localhost:8989/AppletRMIBanque");

				int nouveauId;
				String nouveauNom;
				String nouveauPrenom;
				double nouveauSolde;

				nouveauId = Integer.parseInt(idField.getText());
				nouveauNom = nomField.getText();
				nouveauPrenom = prenomField.getText();
				nouveauSolde = Double.parseDouble(soldeField.getText());

				remoteReference.creerCompte(nouveauId, nouveauNom,
						nouveauPrenom, nouveauSolde);

				etatApplet.setText("Compte créer avec succès avec les valeurs suivantes: " + "\n"
									+ "ID: " + nouveauId + "\n"
									+ "Nom: " + nouveauNom + "\n"
									+ "Prenom: " + nouveauPrenom + "\n"
									+ "Solde: " + nouveauSolde + "\n");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(evt.getSource() == detailCompteButton){
			try {
				// établissement de la connexion RMI
				int port = 8989;
				Registry registry = LocateRegistry.getRegistry(getCodeBase()
						.getHost(), port);
				System.out.println("Obtention des détails du compte ....");
				Banque remoteReference = (Banque) registry
						.lookup("rmi://localhost:8989/AppletRMIBanque");

				int detailId;
				

				detailId = Integer.parseInt(idField.getText());

				remoteReference.afficherCompte(detailId);

				etatApplet.setText("Voici les détails du compte: " + "\n");

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else if(evt.getSource() == ajouterSomme){
			try {
				// établissement de la connexion RMI
				int port = 8989;
				Registry registry = LocateRegistry.getRegistry(getCodeBase()
						.getHost(), port);
				System.out.println("Ajouter d'une somme ....");
				Banque remoteReference = (Banque) registry
						.lookup("rmi://localhost:8989/AppletRMIBanque");

				int idAjoutArgent;
				double ajoutNouveauSolde;

				idAjoutArgent = Integer.parseInt(idField.getText());
				ajoutNouveauSolde = Double.parseDouble(soldeField.getText());

				remoteReference.ajoutSomme(idAjoutArgent, ajoutNouveauSolde);

				etatApplet.setText("Compte créer avec succès avec les valeurs suivantes: " + "\n"
									+ "ID: " + idAjoutArgent + "\n"
									+ "Solde: " + ajoutNouveauSolde + "\n");

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else if(evt.getSource() == retirerSomme){
			try {
				// établissement de la connexion RMI
				int port = 8989;
				Registry registry = LocateRegistry.getRegistry(getCodeBase()
						.getHost(), port);
				System.out.println("Retirer une somme ....");
				Banque remoteReference = (Banque) registry
						.lookup("rmi://localhost:8989/AppletRMIBanque");

				int idRetirerArgent;
				double retirerNouveauSolde;

				idRetirerArgent = Integer.parseInt(idField.getText());
				retirerNouveauSolde = Double.parseDouble(soldeField.getText());

				remoteReference.ajoutSomme(idRetirerArgent, retirerNouveauSolde);

				etatApplet.setText("Compte créer avec succès avec les valeurs suivantes: " + "\n"
									+ "ID: " + idRetirerArgent + "\n"
									+ "Solde: " + retirerNouveauSolde + "\n");

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
