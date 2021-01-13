package fr.eni.eniEncheres.TEST;

import fr.eni.eniEncheres.BO.Utilisateur;
import fr.eni.eniEncheres.DAL.IUtilisateurDao;
import fr.eni.eniEncheres.DAL.UtilisateurDaoException;
import fr.eni.eniEncheres.DAL.UtilisateurDaoFactory;



public class TestDAO {

	private static IUtilisateurDao userDao = UtilisateurDaoFactory.getDao();
			
	public static void main(String[] args) throws UtilisateurDaoException {
		userDao.create(new Utilisateur("Zizou", "Zidane", "Zineddine", "Zizoy@eni-campus.fr", "0606060606", "Av Champs Elysée", "75000", "Paris", "zzzz", 100, false));
		System.out.println(userDao.getAllUsers());
	}

}
