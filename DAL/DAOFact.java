package fr.eni.eniEncheres.DAL;

public class DAOFact {

	public static IArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduImpl();
	}

	public static IEnchereDAO getEnchereDAO() {
		return new EnchereDAOImpl();
	}

	public static ICategorieDAO getCategorieDAO() {
		return new CategorieDAOImpl();
	}

	public static IRetraitDAO getRetraitDAO() {
		return new RetraitDAOImpl();
	}

	public static IUtilisateurDao getUtilisateurDAO() {
		return new UtilisateurDaoImpl();
	}
}
