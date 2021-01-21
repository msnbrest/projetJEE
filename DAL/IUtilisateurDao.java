package fr.eni.eniEncheres.DAL;

import java.util.List;

import fr.eni.eniEncheres.BO.Utilisateur;

public interface IUtilisateurDAO {

	public Utilisateur createUser(Utilisateur utilisateur) throws UtilisateurDAOException;

	public List<Utilisateur> getAllUsers() throws UtilisateurDAOException;

	public List<Utilisateur> getUsersByName(String name) throws UtilisateurDAOException;

	public Utilisateur getUserById(Integer id) throws UtilisateurDAOException;

	public Utilisateur getUserByIdentifiant(String identifiant) throws UtilisateurDAOException;

	public Utilisateur updateUser(Utilisateur utilisateur) throws UtilisateurDAOException;

	public boolean deleteUser(Integer Id) throws UtilisateurDAOException;

}