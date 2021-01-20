package fr.eni.eniEncheres.BLL;

import java.util.ArrayList;
import java.util.List;

import fr.eni.eniEncheres.BO.Utilisateur;
import fr.eni.eniEncheres.DAL.IUtilisateurDAO;
import fr.eni.eniEncheres.DAL.UtilisateurDAOException;
import fr.eni.eniEncheres.DAL.UtilisateurDAOFactory;

public class UtilisateurManagerImpl implements IUtilisateurManager {
	
	private IUtilisateurDAO userDao = UtilisateurDAOFactory.getDao();

	@Override
	public Utilisateur CreateUtilisteur(Utilisateur utilisateur) throws UtilisateurBLLException {
		try {
			userDao.create(utilisateur);
		} catch (UtilisateurDAOException e) {
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
		} catch (UtilisateurDAOException e) {
			throw new UtilisateurBLLException(e.getMessage());
//			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void deleteUtilisateur(Integer id) throws UtilisateurBLLException {
		try {
			userDao.deleteUser(id);
		} catch (UtilisateurDAOException e) {
//			e.printStackTrace();
			throw new UtilisateurBLLException(e.getMessage());
		
		}

	}

	@Override
	public Utilisateur updateUtilisateur(Integer id) {
		// TODO Auto-generated method stub
		return null;
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
	public List<Utilisateur> getUserByMC(String mc) throws UtilisateurBLLException {
		List <Utilisateur> users = new ArrayList<Utilisateur>();
		try {
			users = userDao.getUserByMC(mc);
		} catch (UtilisateurDAOException e) {
//			e.printStackTrace();
		throw new UtilisateurBLLException(e.getMessage());
			
		}
		return users ;
	}

}
