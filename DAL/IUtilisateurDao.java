package fr.eni.eniEncheres.DAL;

import java.util.List;

import fr.eni.eniEncheres.BO.Utilisateur;

public interface IUtilisateurDao {
	
	public Utilisateur create(Utilisateur utilisateur) throws UtilisateurDaoException; 
	public List<Utilisateur> getAllUsers() throws UtilisateurDaoException;
	public void deleteUser(Integer Id) throws UtilisateurDaoException;
}
