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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class BanqueClient extends JApplet implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Cr�er un compte
	Button CreerCompteButton;

	TextField idFieldTitle;
	TextField nomFieldTitle;
	TextField prenomFieldTitle;
	TextField soldeFieldTitle;

	TextField idField;
	TextField nomField;
	TextField prenomField;
	TextField soldeField;
	// Voir les d�tails d'un compte
	Button detailCompteButton;

	TextField idDetailCompteTitle;
	TextField idDetailCompte;

	// Ajouter une somme sur un compte
	Button ajouterSomme;

	TextField idAjouterSommeTitle;
	TextField idAjouterSommeCompte;

	TextField montantAjoutTitle;
	TextField montantAjout;

	// Retirer une somme d'un compte
	Button retirerSomme;

	TextField idRetirerSommeTitle;
	TextField idRetirerSommeCompte;

	TextField montantRetraitTitle;
	TextField montantRetrait;

	JTextArea etatApplet;

	public void init() {

		try {
			// Cr�ation des assets de l'applet
			setLayout(new FlowLayout(5, 10, 10));
			getContentPane().setBackground(Color.orange);

			// Initialisation Cr�er compte
			CreerCompteButton = new Button("Cr�er un compte");
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

			// Initialisation Voir les d�tails d'un compte
			detailCompteButton = new Button(
					"         Voir les d�tails d'un compte         ");
			detailCompteButton.addActionListener(this);
			detailCompteButton.setBackground(Color.red);
			detailCompteButton.addActionListener(this);

			idDetailCompteTitle = new TextField("ID du compte � consulter:", 20);
			idDetailCompte = new TextField("", 48);

			idDetailCompteTitle.setEnabled(false);
			idDetailCompte.setEnabled(true);

			add(detailCompteButton);
			add(idDetailCompteTitle);
			add(idDetailCompte);

			// Initialisation Ajouter une somme sur un compte
			ajouterSomme = new Button(
					"      Ajouter de l'argent � un compte      ");
			ajouterSomme.setBackground(java.awt.Color.red);
			ajouterSomme.addActionListener(this);

			idAjouterSommeTitle = new TextField(
					"ID du compte � mettre � jour:", 20);
			idAjouterSommeCompte = new TextField("", 5);

			montantAjoutTitle = new TextField("Montant � ajouter:");
			montantAjout = new TextField("", 15);

			idAjouterSommeTitle.setEnabled(false);
			idAjouterSommeCompte.setEnabled(true);
			montantAjoutTitle.setEnabled(false);
			montantAjout.setEnabled(true);

			add(ajouterSomme);
			add(idAjouterSommeTitle);
			add(idAjouterSommeCompte);
			add(montantAjoutTitle);
			add(montantAjout);

			// Initialisation Retirer une somme d'un compte

			retirerSomme = new Button(
					"      Retirer de l'argent � un compte      ");
			retirerSomme.setBackground(java.awt.Color.red);
			retirerSomme.addActionListener(this);

			idRetirerSommeTitle = new TextField(
					"ID du compte � mettre � jour:", 20);
			idRetirerSommeCompte = new TextField("", 5);
			montantRetraitTitle = new TextField("Montant � retirer:");
			montantRetrait = new TextField("", 15);

			idRetirerSommeTitle.setEnabled(false);
			idRetirerSommeCompte.setEnabled(true);
			montantRetraitTitle.setEnabled(false);
			montantRetrait.setEnabled(true);

			add(retirerSomme);
			add(idRetirerSommeTitle);
			add(idRetirerSommeCompte);
			add(montantRetraitTitle);
			add(montantRetrait);

			// R�troaction sur l'�tat de l'applet
			etatApplet = new JTextArea("Applet d�marr�!", 25, 50);
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
				// �tablissement de la connexion RMI
				int port = 8989;
				Registry registry = LocateRegistry.getRegistry(getCodeBase()
						.getHost(), port);
				System.out.println("Cr�ation d'un compte ....");
				Banque remoteReference = (Banque) registry
						.lookup("rmi://localhost:8989/AppletRMIBanque");

				int nouveauId;
				String nouveauNom;
				String nouveauPrenom;
				double nouveauSolde;

				if (isInteger(idField.getText())
						&& isDouble(soldeField.getText())) {
					nouveauId = Integer.parseInt(idField.getText());
					nouveauNom = nomField.getText();
					nouveauPrenom = prenomField.getText();
					nouveauSolde = Double.parseDouble(soldeField.getText());
					remoteReference.creerCompte(nouveauId, nouveauNom,
							nouveauPrenom, nouveauSolde);
					etatApplet
							.setText("Compte cr�� avec succ�s avec les valeurs suivantes: "
									+ "\n"
									+ "ID: "
									+ nouveauId
									+ "\n"
									+ "Nom: "
									+ nouveauNom
									+ "\n"
									+ "Prenom: "
									+ nouveauPrenom
									+ "\n"
									+ "Solde: "
									+ nouveauSolde + "\n");
				}

				else {
					etatApplet
							.setText("Transaction annul�, veuillez recommencer avec des formats d'entr�es valides.");

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (evt.getSource() == detailCompteButton) {
			try {
				// �tablissement de la connexion RMI
				int port = 8989;
				Registry registry = LocateRegistry.getRegistry(getCodeBase()
						.getHost(), port);
				System.out.println("Obtention des d�tails du compte ....");
				Banque remoteReference = (Banque) registry
						.lookup("rmi://localhost:8989/AppletRMIBanque");

				int detailId;

				if (isInteger(idDetailCompte.getText())) {
					detailId = Integer.parseInt(idDetailCompte.getText());
					Compte detailDuCompte = new Compte();
					detailDuCompte = remoteReference.afficherCompte(detailId);
					etatApplet.setText("");
					etatApplet
							.setText("Voici les d�tails du compte: "
									+ "\n"
									+ "ID: "
									+ detailDuCompte.getId()
									+ "\n"
									+ "Nom: "
									+ detailDuCompte.getNom()
									+ "\n"
									+ "Prenom: "
									+ detailDuCompte.getPrenom()
									+ "\n"
									+ "Solde: "
									+ detailDuCompte.getSolde()
									+ "\n"
									+ "-----------------------  Transactions  ------------------------------- \n");
					ArrayList<Transaction> afficherListe = detailDuCompte
							.getListeTransaction();
					for (int i = 0; i < afficherListe.size(); i++) {
						Transaction afficherTransaction = afficherListe.get(i);
						etatApplet.append("Montant de la transaction #"
								+ (i + 1) + ": "
								+ afficherTransaction.getMontant() + "$"
								+ " solde: " + afficherTransaction.getSolde()
								+ " effectu� le: "
								+ afficherTransaction.getDate() + "\n");
					}
				}

				else {
					etatApplet
							.setText("Transaction annul�, veuillez recommencer avec des formats d'entr�es valides.");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (evt.getSource() == ajouterSomme) {
			try {
				// �tablissement de la connexion RMI
				int port = 8989;
				Registry registry = LocateRegistry.getRegistry(getCodeBase()
						.getHost(), port);
				System.out.println("Ajouter une somme ....");
				Banque remoteReference = (Banque) registry
						.lookup("rmi://localhost:8989/AppletRMIBanque");

				int idAjoutSomme;
				double ajouterNouvelleSomme;

				if (isInteger(idAjouterSommeCompte.getText()) // V�rifie le
																// format des
																// donn�es
						&& isDouble(montantAjout.getText())) {
					idAjoutSomme = Integer.parseInt(idAjouterSommeCompte
							.getText());
					ajouterNouvelleSomme = Double.parseDouble(montantAjout
							.getText());

					if (remoteReference.ajoutSomme(idAjoutSomme,
							ajouterNouvelleSomme)) {

						etatApplet.setText("Solde ajout� avec succ�s: " + "\n"
								+ "ID: " + idAjoutSomme + "\n"
								+ "Solde ajout�: " + ajouterNouvelleSomme
								+ "\n");
					} else {
						etatApplet
								.setText("La transaction � �t� annul�, un probl�me est survenu.");
					}
				}

				else {
					etatApplet
							.setText("Transaction annul�, veuillez recommencer avec des formats d'entr�es valides.");

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (evt.getSource() == retirerSomme) {
			try {
				// �tablissement de la connexion RMI
				int port = 8989;
				Registry registry = LocateRegistry.getRegistry(getCodeBase()
						.getHost(), port);
				System.out.println("Retirer une somme ....");
				Banque remoteReference = (Banque) registry
						.lookup("rmi://localhost:8989/AppletRMIBanque");

				int idRetirerSomme;
				double retirerNouvelleSomme;

				if (isInteger(idRetirerSommeCompte.getText()) // V�rifie le
																// format des
																// donn�es
						&& isDouble(montantRetrait.getText())) {
					idRetirerSomme = Integer.parseInt(idRetirerSommeCompte
							.getText());
					retirerNouvelleSomme = Double.parseDouble(montantRetrait
							.getText());

					etatApplet
							.setText("Veuillez recommencer avec des formats valides");

					if (remoteReference.retirerSomme(idRetirerSomme,
							retirerNouvelleSomme)) {
						etatApplet.setText("Solde retir� avec succ�s: " + "\n"
								+ "ID: " + idRetirerSomme + "\n"
								+ "Solde retir�: " + retirerNouvelleSomme
								+ "\n");
					} else {
						etatApplet
								.setText("La transaction � �t� annul�, un probl�me est survenu.");
					}

				}

				else {
					etatApplet
							.setText("Transaction annul�, veuillez recommencer avec des formats d'entr�es valides.");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public static boolean isInteger(String s) { // Code bas� sur ce lien
												// http://www.tutorialspoint.com/java/util/scanner_hasnextint.htm
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(s.trim());
		if (!sc.hasNextInt())
			return false;
		// we know it starts with a valid int, now make sure
		// there's nothing left!
		sc.nextInt();
		return !sc.hasNext();
	}

	public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
