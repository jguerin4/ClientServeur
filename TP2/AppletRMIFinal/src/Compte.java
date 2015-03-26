import java.util.ArrayList;


public class Compte implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nom;
	private String prenom;
	private double solde;
	private ArrayList<Transaction> listeTransaction = new ArrayList<Transaction>();
	private String errMessage;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public ArrayList<Transaction> getListeTransaction() {
		return listeTransaction;
	}
	public void setListeTransaction(ArrayList<Transaction> listeTransaction) {
		this.listeTransaction = listeTransaction;
	}
	public void ajoutTransaction(Transaction nouvelleTransaction){
		this.listeTransaction.add(nouvelleTransaction);
	}
	public String getErrMessage() {
		return errMessage;
	}
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	
}
