package fr.eni.eniEncheres.BLL;

import java.util.ArrayList;
import java.util.List;

import fr.eni.eniEncheres.BO.Utilisateur;
import fr.eni.eniEncheres.DAL.IUtilisateurDao;
import fr.eni.eniEncheres.DAL.UtilisateurDaoException;
import fr.eni.eniEncheres.DAL.UtilisateurDaoFactory;

public class UtilisateurManagerImpl implements IUtilisateurManager {
	
	private IUtilisateurDao userDao = UtilisateurDaoFactory.getDao();

	@Override
	public Utilisateur CreateUtilisteur(Utilisateur utilisateur) throws UtilisateurBLLException {
		try {
			userDao.create(utilisateur);
		} catch (UtilisateurDaoException e) {
			e.printStackTrace();
			throw new UtilisateurBLLException("probleme Bll exception lors de la creation");
			
		}
		return utilisateur;
	}

	@Override
	public List<Utilisateur> getAll() throws UtilisateurBLLException {
		List<Utilisateur> users = new ArrayList<Utilisateur>();
		try {
			users = userDao.getAllUsers();
		} catch (UtilisateurDaoException e) {
			throw new UtilisateurBLLException(e.getMessage());
//			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void deleteUtilisateur(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Utilisateur updateUtilisateur(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
