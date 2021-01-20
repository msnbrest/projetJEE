package fr.eni.eniEncheres.IHM;

import fr.eni.eniEncheres.BO.ArticleVendu;

public class CreerVenteModel {
	private ArticleVendu article;
	private String message;
	
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
// envoyer adresse rue+codepostal+ville à jsp :D
}
