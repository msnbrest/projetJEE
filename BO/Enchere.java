package fr.eni.eniEncheres.BO;

import java.time.LocalDateTime;

public class Enchere {
	private int IdEnchere;
	private LocalDateTime dateEnchere;
	private int montantEnchere;
	private int noArticle;
	private Utilisateur utilisateur;

	public Enchere() {
	}
	
	public Enchere(LocalDateTime dateEnchere1, int montantEnchere1, int noArticle1, Utilisateur utilisateur) {
		super();
		this.dateEnchere = dateEnchere1;
		this.montantEnchere = montantEnchere1;
		this.noArticle = noArticle1;
		this.utilisateur = utilisateur;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDateTime dateEnchere) {
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
				+ ", noArticle=" + noArticle + ", utilisateur=" + utilisateur + "]";
	}

}
