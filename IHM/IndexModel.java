package fr.eni.eniEncheres.IHM;

import java.util.ArrayList;
import java.util.List;

import fr.eni.eniEncheres.BO.ArticleVendu;

public class IndexModel {
	private List<ArticleVendu> lstArticleVendu = new ArrayList<ArticleVendu>();
	private String message = "";

	public IndexModel() {

	}

	public IndexModel(List<ArticleVendu> lstArticleVendu) {

		this.lstArticleVendu = lstArticleVendu;
	}

	public List<ArticleVendu> getLstArticleVendu() {
		return lstArticleVendu;
	}

	public void setLstArticleVendu(List<ArticleVendu> lstArticleVendu) {
		this.lstArticleVendu = lstArticleVendu;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "IndexModel [lstArticleVendu=" + lstArticleVendu + "]";
	}
}
