package fr.eni.eniEncheres.BLL;

import java.util.List;

import fr.eni.eniEncheres.BO.Utilisateur;

public interface IUtilisateurManager {
	
	public Utilisateur CreateUtilisteur(Utilisateur utilisateur) throws UtilisateurBLLException;
	public List<Utilisateur> getAll() throws UtilisateurBLLException;
	public void deleteUtilisateur(Integer id);
	public Utilisateur updateUtilisateur (Integer id);
	

}
