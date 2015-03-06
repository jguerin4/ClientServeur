/**
 * Classe Voiture: Simple classe qui contient les attributs  les voitures.
 * 
 */
package TP2;


public class Voiture implements java.io.Serializable {

	/**
	 * UId par défaut pour la sérialisation
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int m_noSerie;
	private String m_marque;
	private String m_modele;
	private double m_poids;
	private double m_prix;
	private String m_couleur;
	private String m_annee;
	
	
	public Voiture (){
	}
	
	public Voiture (int noSerie, String marque, String modele, double poids, double prix, String couleur, String annee){
		this.m_noSerie = noSerie;
		this.m_marque = marque;
		this.m_modele = modele;
		this.m_poids = poids;
		this.m_prix = prix;
		this.m_couleur = couleur;
		this.m_annee= annee;
	}
	

	public int getM_noSerie() {
		return m_noSerie;
	}

	public void setM_noSerie(int m_noSerie) {
		this.m_noSerie = m_noSerie;
	}

	public String getM_marque() {
		return m_marque;
	}

	public void setM_marque(String m_marque) {
		this.m_marque = m_marque;
	}

	public String getM_couleur() {
		return m_couleur;
	}

	public void setM_couleur(String m_couleur) {
		this.m_couleur = m_couleur;
	}

	public String getM_annee() {
		return m_annee;
	}

	public void setM_annee(String m_annee) {
		this.m_annee = m_annee;
	}


	public String getM_modele() {
		return m_modele;
	}


	public void setM_modele(String m_modele) {
		this.m_modele = m_modele;
	}


	public double getM_poids() {
		return m_poids;
	}


	public void setM_poids(double m_poids) {
		this.m_poids = m_poids;
	}


	public double getM_prix() {
		return m_prix;
	}


	public void setM_prix(double m_prix) {
		this.m_prix = m_prix;
	}
	
}
