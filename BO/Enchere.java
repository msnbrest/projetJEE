package fr.eni.projetJEE.BO;

import java.time.LocalDate;

public class Enchere {
	private int IdEnchere = -1;// -1 => pas encore enregistrée
	private LocalDate dateEnchere;
	private int montantEnchere;
	private int noArticle;
	private int noUtilisateur;

	public Enchere() {
	}
	
	public Enchere(LocalDate dateEnchere1, int montantEnchere1, int noArticle1, int noUtilisateur1) {
		super();
		this.dateEnchere = dateEnchere1;
		this.montantEnchere = montantEnchere1;
		this.noArticle = noArticle1;
		this.noUtilisateur = noUtilisateur1;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public int getIdEnchere() {
		return IdEnchere;
	}

	public void setIdEnchere(int IdEnchere) {
		this.IdEnchere = IdEnchere;
	}

	@Override
	public String toString() {
		return "Enchere [IdEnchere=" + IdEnchere + ", dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere
				+ ", noArticle=" + noArticle + ", noUtilisateur=" + noUtilisateur + "]";
	}

}
