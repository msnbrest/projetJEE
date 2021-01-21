package fr.eni.eniEncheres.IHM;

import fr.eni.eniEncheres.BO.Utilisateur;

public class EncheresModel {
	
	private Utilisateur utilisateur;
	private String message;
	
	
	public EncheresModel() {
		
	}

	public EncheresModel(Utilisateur utilisateur, String message) {
		super();
		this.utilisateur = utilisateur;
		this.message = message;
	}


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
