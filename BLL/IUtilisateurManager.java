package fr.eni.eniEncheres.BLL;

import java.util.List;

import fr.eni.eniEncheres.BO.Utilisateur;

public interface IUtilisateurManager {

	public Utilisateur CreateUser(Utilisateur utilisateur) throws UtilisateurBLLException;

	public List<Utilisateur> getAllUsers() throws UtilisateurBLLException;

	public List<Utilisateur> getUsersByName(String name) throws UtilisateurBLLException;

	public Utilisateur getUserByIdentifiant(String identifiant) throws UtilisateurBLLException;
	public void deleteUtilisateur(Integer id) throws UtilisateurBLLException;
	public Boolean sauthentifier(String identifiant, String mdp) throws UtilisateurBLLException;
	
	
	public Utilisateur updateUser(Utilisateur utilisateur) throws UtilisateurBLLException;
}
