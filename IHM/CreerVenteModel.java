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

	

	
	

	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
