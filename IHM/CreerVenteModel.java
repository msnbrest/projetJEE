package fr.eni.eniEncheres.IHM;

import fr.eni.eniEncheres.BO.ArticleVendu;
import fr.eni.eniEncheres.BO.Utilisateur;

public class CreerVenteModel {
	private Utilisateur utilisateur;
	private ArticleVendu article;
	
	public CreerVenteModel() {

	}

	public CreerVenteModel(Utilisateur utilisateur, ArticleVendu article) {
		super();
		this.utilisateur = utilisateur;
		this.article = article;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public ArticleVendu getArticle() {
		return article;
	}

	public void setArticle(ArticleVendu article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "CreerVenteModel [utilisateur=" + utilisateur + ", article=" + article + "]";
	}
	

	
	

}
