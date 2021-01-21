package fr.eni.eniEncheres.IHM;

import fr.eni.eniEncheres.BO.ArticleVendu;
import fr.eni.eniEncheres.BO.Enchere;

public class VoirObjModel {

	private String message = "";
	private ArticleVendu article;
	private Enchere enchere;

	public VoirObjModel() {

	}

	public VoirObjModel(String message, ArticleVendu article) {
		super();
		this.message = message;
		this.article = article;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ArticleVendu getArticle() {
		return article;
	}

	public void setArticle(ArticleVendu article1) {
		this.article = article1;
	}

	public Enchere getEnchere() {
		return enchere;
	}

	public void setEnchere(Enchere enchere) {
		this.enchere = enchere;
	}

}
