/**
 * 
 */
package TP2;


public class Facturation {
	
	private Facture facture;
	
	/**
	 * Constructeur
	 *
	 */
	public Facturation(int nouveauNoFacture, String nouveauNomAcheteur, double nouveauMontant){
		facture = new Facture(nouveauNoFacture, nouveauNomAcheteur, nouveauMontant);
	}
	
	void ajouterMontant(double somme) {
		facture.setM_montant(facture.getM_montant() + somme); 
		}

		void retirerMontant(double somme) {
		facture.setM_montant(facture.getM_montant() - somme); 
		}

		//Ainsi, facturation.facture() retourne la facture appartenant 
		Facture facture() { 
			return facture; 
		}
		
	
	
	
}
