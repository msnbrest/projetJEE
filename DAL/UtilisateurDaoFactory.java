package fr.eni.eniEncheres.DAL;

public class UtilisateurDaoFactory {
	
	public static IUtilisateurDao getDao() {
		return new UtilisateurDaoImpl();
	}
}
