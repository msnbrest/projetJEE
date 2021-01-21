package fr.eni.eniEncheres.BLL;

import java.util.ArrayList;
import java.util.List;

import fr.eni.eniEncheres.BO.Utilisateur;
import fr.eni.eniEncheres.DAL.DAOFact;
import fr.eni.eniEncheres.DAL.IUtilisateurDAO;
import fr.eni.eniEncheres.DAL.UtilisateurDAOException;

public class UtilisateurManagerImpl implements IUtilisateurManager {

	private IUtilisateurDAO userDao = DAOFact.getUserDAO();

	@Override
	public Utilisateur CreateUser(Utilisateur utilisateur) throws UtilisateurBLLException {
		
		try {
			userDao.createUser(utilisateur);
		} catch (UtilisateurDAOException e) {
			throw new UtilisateurBLLException(e.getMessage());
		}
		
		return utilisateur;
	}

	@Override
	public List<Utilisateur> getAllUsers() throws UtilisateurBLLException {
		
		List<Utilisateur> users = new ArrayList<Utilisateur>();
		try {
			users = userDao.getAllUsers();
		} catch (UtilisateurDAOException e) {
			throw new UtilisateurBLLException(e.getMessage());
		}
		
		return users;
	}

	@Override
	public List<Utilisateur> getUsersByName(String name) throws UtilisateurBLLException {
		
		List<Utilisateur> users = new ArrayList<Utilisateur>();
		try {
			users = userDao.getUsersByName(name);
		} catch (UtilisateurDAOException e) {
			throw new UtilisateurBLLException(e.getMessage());
		}
		
		return users;
	}

	@Override
	public Utilisateur getUserByIdentifiant(String identifiant) throws UtilisateurBLLException {
		Utilisateur user = new Utilisateur();
		try {
			user = userDao.getUserByIdentifiant(identifiant);
		} catch (UtilisateurDAOException e) {
			throw new UtilisateurBLLException(e.getMessage());
		}
		return user;
	}

	@Override
	public Utilisateur updateUser(Utilisateur utilisateur) throws UtilisateurBLLException {
		
		try {
			userDao.updateUser(utilisateur);
		} catch (UtilisateurDAOException e) {
			throw new UtilisateurBLLException(e.getMessage());
		}
		
		return utilisateur;
	}

	@Override
	public boolean deleteUser(Integer id) throws UtilisateurBLLException {

		try {
			userDao.deleteUser(id);
		} catch (UtilisateurDAOException e) {
			throw new UtilisateurBLLException(e.getMessage());
		}
		
		return true;

	}

}
