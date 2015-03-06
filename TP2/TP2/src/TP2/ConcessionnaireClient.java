

package TP2;

import java.util.Vector;

import TP2.IParcAuto;
import TP2.Voiture;

public class ConcessionnaireClient
{
	public static IParcAuto b;
	public static IFacturation f;
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) 
	{
		
		int nouveauNoFacture;
		String nouveauNomAcheteur;
		double nouveauMontant;
		
		int nouveauNoSerie;
		String nouveauMarque;
		String nouveauModele;
		double nouveauPoids;
		double nouveauPrix;
		String nouveauCouleur;
		String nouveauAnnee;
		
		int rechercheNoSerie;
		
		String rechercheMarque;
		
		int rechercheNoFacture;
		
		String rechercheNomAcheteur;
		
		try 
		{

			java.io.DataInputStream in = new java.io.DataInputStream(System.in);
			System.out.println("Client start :");

			b = (IParcAuto)java.rmi.Naming.lookup("rmi://localhost:8989/MonConcessionnaire");
			f = (IFacturation)java.rmi.Naming.lookup("rmi://localhost:8989/MonConcessionnaire");
			// On appelle les méthodes de l'interface

			b.chargerDonnes();
			
			int choice;
			do
			{
				System.out.println("1 - Nouvelle voiture");
				System.out.println("2 - Nouvelle facture");
				System.out.println("3 - Afficher voiture par no de serie");
				System.out.println("4 - Afficher voiture par marque");
				System.out.println("5 - Afficher facture par no de ID");
				System.out.println("6 - Afficher facture par nom de l'acheteur");
				System.out.println("0 - Quitter");
				
				choice = Integer.parseInt(in.readLine());
				switch (choice) 
				{
					case 1: 
						
						/**
						 * Saisi des données
						 * 	
						 */
						
						System.out.println("Entrez le numéro de série de la voiture:");
						nouveauNoSerie = Integer.parseInt(in.readLine());
						System.out.println("Entrez la marque de la voiture:");
						nouveauMarque = in.readLine();
						System.out.println("Entrez le modèle de la voiture:");
						nouveauModele = in.readLine();
						System.out.println("Entrez le poids de la voiture (en kg):");
						nouveauPoids = Double.parseDouble(in.readLine());
						System.out.println("Entrez le prix de la voiture (ex:41250.57):");
						nouveauPrix = Double.parseDouble(in.readLine());
						System.out.println("Entrez la couleur de la voiture:");
						nouveauCouleur = in.readLine();
						System.out.println("Entrez l'année de la voiture (ex:2014):");
						nouveauAnnee = in.readLine();
						
						//La fonction ajouterVoiture(...) retourne vrai si l'ajout d'une voiture est possible et faux sinon.
						//Si la fonction retourne vrai, la voiture à été ajoutée dans la base de donnée du concessionnaire.
						
						if(b.ajouterVoiture(nouveauNoSerie,nouveauMarque,
						nouveauModele,nouveauPoids,nouveauPrix,nouveauCouleur,nouveauAnnee))
						{
							System.out.println("Voiture ajouté avec succès au concessionnaire!");
						}
						
						else
							System.out.println("Impossible d'ajouter la voiture: le numéro de série existe déjà et doit être unique.");
						
						break;
				
	
				
					case 2:
						
						/**
						 * Saisi des données
						 * 	
						 */
						
						System.out.println("Entrez le numéro de la facture:");
						nouveauNoFacture = Integer.parseInt(in.readLine());
						System.out.println("Entrez la nom de l'acheteur sur la facture:");
						nouveauNomAcheteur = in.readLine();
						System.out.println("Entrez le montant total de la facture (ex:41250.57):");
						nouveauMontant = Double.parseDouble(in.readLine());
						
						
						if(f.ajouterFacture(nouveauNoFacture, nouveauNomAcheteur, nouveauMontant))
						{
							System.out.println("Facture ajouté avec succes");
						}
						else
						{
							System.out.println("La facture ne peut pas être ajouté car il existe déjà une facture ayant le même numéro de facture.");
						}

					
						break;
						
					case 3:
						Voiture voitureID;
						
						System.out.println("Entrez le numéro de série de la voiture que vous voulez afficher:");
						rechercheNoSerie = Integer.parseInt(in.readLine());
						
						voitureID = b.rechercherVoiture(rechercheNoSerie);

						
						if(voitureID != null)
						{
						
							System.out.println("Voici les caractéristiques de la voiture correspondante:");
							
							System.out.println("No de serie: ");
							System.out.println(voitureID.getM_noSerie());
							
							System.out.println("Marque: ");
							System.out.println(voitureID.getM_marque());
							
							System.out.println("Modele: ");
							System.out.println(voitureID.getM_modele());
							
							System.out.println("Couleur: ");
							System.out.println(voitureID.getM_couleur());
							
							System.out.println("Année: ");
							System.out.println(voitureID.getM_annee());
							
							System.out.println("Poids: ");
							System.out.println(voitureID.getM_poids());
							
							System.out.println("Prix: ");
							System.out.println(voitureID.getM_prix());
						}

						else
							System.out.println("Aucune voiture correspondant au numéro de série que vous avez entré: ");
						
						break;
					case 4:
						
						
						System.out.println("Entrez la marque des voitures que vous voulez afficher:");
						rechercheMarque = in.readLine();
						
						Vector<Voiture> voitureAAfficher = b.rechercherVoiture(rechercheMarque);

						
						if(voitureAAfficher.size() >= 0)
						{
							for(int i = 0; i < voitureAAfficher.size(); i++)
							{
							
								Voiture voitureFor = voitureAAfficher.get(i);
								
								//(if(la voiture existe))
								
								System.out.println("Voiture #:" + String.valueOf(i +1));
								
								System.out.println("No de serie: ");
								System.out.println(voitureFor.getM_noSerie());
								
								System.out.println("Marque: ");
								System.out.println(voitureFor.getM_marque());
								
								System.out.println("Modele: ");
								System.out.println(voitureFor.getM_modele());
								
								System.out.println("Couleur: ");
								System.out.println(voitureFor.getM_couleur());
								
								System.out.println("Année: ");
								System.out.println(voitureFor.getM_annee());
								
								System.out.println("Poids: ");
								System.out.println(voitureFor.getM_poids());
								
								System.out.println("Prix: ");
								System.out.println(voitureFor.getM_prix());
							}
						}
						
						else
						{
							System.out.println("Aucune voiture correspondant à la marque que vous avez entré: ");
						}
						break;
						
						
					case 5:
						Facture factureID;
						
						System.out.println("Entrez le numéro de série de la facture que vous voulez afficher:");
						rechercheNoFacture = Integer.parseInt(in.readLine());
						
						factureID = f.rechercherFacture(rechercheNoFacture);

						if(factureID != null)
						{
						
							System.out.println("Voici les caractéristiques de la facture correspondante:");
							
							System.out.println("No de facture: ");
							System.out.println(factureID.getM_noFacture());
							
							System.out.println("Nom de l'acheteur: ");
							System.out.println(factureID.getM_nomAcheteur());
							
							System.out.println("Montant de la facture: ");
							System.out.println(factureID.getM_montant());
						}
						
						else
						{
							System.out.println("Aucune facture correspondante au numéro de série entré: ");
						}
						
						break;
						
					case 6:

						System.out.println("Entrez le nom de l'acheteur des factures que vous voulez afficher:");
						rechercheNomAcheteur = in.readLine();

						Vector<Facture>	factureAAfficher = f.rechercherFacture(rechercheNomAcheteur);

						if(factureAAfficher.size() >= 0)
							
						{
							
							System.out.println("Voici les caractéristiques de/des la/les facture(s) correspondante(s):");
							for(int i = 0; i < factureAAfficher.size(); i++)
							{
							
								System.out.println("Facture #:" + String.valueOf(i+1));
								
								Facture factureFor = factureAAfficher.get(i);
							
								System.out.println("No de facture: ");
								System.out.println(factureFor.getM_noFacture());
								
								System.out.println("Nom de l'acheteur: ");
								System.out.println(factureFor.getM_nomAcheteur());
								
								System.out.println("Montant de la facture: ");
								System.out.println(factureFor.getM_montant());
							}
							
						}
						
						else
						{
							System.out.println("Aucune facture correspondante au numéro de série entré: ");
						}
						
						
						break;
						
						
					case 0:
						b.sauverDonnees();
						break;
					default:
						System.out.println("ATTENTION: Veuillez entrer un numéro valide (0-6):");
						break;
						
				}
			}while(choice != 0);
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
	
}
