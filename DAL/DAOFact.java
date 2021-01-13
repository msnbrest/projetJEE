package fr.eni.eniEncheres.DAL;

public class DAOFact {

	public static IArticleVenduDAO getArticleVenduDAO () {
		return new ArticleVenduImpl();
	}
}
