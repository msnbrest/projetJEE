package fr.eni.eniEncheres.DAL;

public class UtilisateurDAOFactory {
	
	public static IUtilisateurDAO getDao() {
		return new UtilisateurDAOImpl();
	}
}
