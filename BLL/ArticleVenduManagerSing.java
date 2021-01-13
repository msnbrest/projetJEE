package fr.eni.eniEncheres.BLL;

public class ArticleVenduManagerSing {

	private static IArticleVenduManager instance;
	
	public static IArticleVenduManager getInstance() {
		if(instance == null) {
			instance = new ArticleVenduManagerImpl();
		}
		return instance;
	}
}
