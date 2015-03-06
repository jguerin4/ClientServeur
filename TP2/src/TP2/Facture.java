/**
 * Package qui contient toutes les classes pour notre programme.
 * 
 */
package TP2;

public class Facture implements java.io.Serializable {

	/**
	 * UID par defaut pour la sérialisation.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Facture(){
	
	}

	public Facture(int noFacture, String nomAcheteur, double montant){
		this.m_noFacture = noFacture;
		this.m_nomAcheteur = nomAcheteur;
		this.m_montant = montant;
	}
	
	private int m_noFacture;
	private String m_nomAcheteur;
	private double m_montant;
	
	
	/**
	 * @return the m_noFacture
	 */
	public int getM_noFacture() {
		return m_noFacture;
	}
	/**
	 * @param m_noFacture the m_noFacture to set
	 */
	public void setM_noFacture(int m_noFacture) {
		this.m_noFacture = m_noFacture;
	}

	/**
	 * @return the m_nomAcheteur
	 */
	public String getM_nomAcheteur() {
		return m_nomAcheteur;
	}

	/**
	 * @param m_nomAcheteur the m_nomAcheteur to set
	 */
	public void setM_nomAcheteur(String m_nomAcheteur) {
		this.m_nomAcheteur = m_nomAcheteur;
	}

	/**
	 * @return the m_montant
	 */
	public double getM_montant() {
		return m_montant;
	}

	/**
	 * @param m_montant the m_montant to set
	 */
	public void setM_montant(double m_montant) {
		this.m_montant = m_montant;
	}


	
	
}
