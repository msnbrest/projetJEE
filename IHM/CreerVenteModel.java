package fr.eni.eniEncheres.IHM;

import fr.eni.eniEncheres.BO.ArticleVendu;
import fr.eni.eniEncheres.BO.Utilisateur;

public class CreerVenteModel {
	private ArticleVendu article;
	private Utilisateur utilisateur;
	private String message = "";

	public CreerVenteModel() {
	}

	public CreerVenteModel(ArticleVendu article) {
		super();
		this.article = article;
	}

	public ArticleVendu getArticle() {
		return article;
	}

	public void setArticle(ArticleVendu article) {
		this.article = article;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

// envoyer adresse rue+codepostal+ville à jsp :D
}
