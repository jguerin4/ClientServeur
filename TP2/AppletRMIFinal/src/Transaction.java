public class Transaction {
	private int idTransaction;
	private int noCompte;
	private double montant;
	private double solde;
	private String date;
	public int getIdTransaction() {
		return idTransaction;
	}
	public void setIdTransaction(int idTransaction) {
		this.idTransaction = idTransaction;
	}
	public int getNoCompte() {
		return noCompte;
	}
	public void setNoCompte(int noCompte) {
		this.noCompte = noCompte;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
