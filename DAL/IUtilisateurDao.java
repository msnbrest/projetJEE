package fr.eni.eniEncheres.DAL;

import java.util.List;

import fr.eni.eniEncheres.BO.Utilisateur;

public interface IUtilisateurDAO {
	
	public Utilisateur create(Utilisateur utilisateur) throws UtilisateurDAOException; 
	public List<Utilisateur> getAllUsers() throws UtilisateurDAOException;
	public void deleteUser(Integer Id) throws UtilisateurDAOException;
	public List<Utilisateur> getUserByMC(String mc) throws UtilisateurDAOException; 
	public Utilisateur getUserById(Integer id) throws UtilisateurDAOException; 
	public Utilisateur updateUser(Integer id) throws UtilisateurDAOException; 
	public Utilisateur getUserByIdentifiant(String identifiant) throws UtilisateurDAOException;
	public Boolean sauthentifier(String identifiant, String mdp) throws UtilisateurDAOException;
	
}