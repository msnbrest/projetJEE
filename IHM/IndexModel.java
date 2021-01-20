package fr.eni.eniEncheres.IHM;

import java.util.ArrayList;
import java.util.List;

import fr.eni.eniEncheres.BO.ArticleVendu;

public class IndexModel {
	private List<ArticleVendu> lstArticleVendu = new ArrayList<ArticleVendu>();
	
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

	@Override
	public String toString() {
		return "IndexModel [lstArticleVendu=" + lstArticleVendu + "]";
	}
}
