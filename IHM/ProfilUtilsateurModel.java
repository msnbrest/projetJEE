package fr.eni.eniEncheres.IHM;

import fr.eni.eniEncheres.BO.Utilisateur;

public class ProfilUtilsateurModel {

	private Utilisateur utilisateur;
	private String message = "";

	public ProfilUtilsateurModel() {
	}

	public ProfilUtilsateurModel(Utilisateur utilisateur) {
		super();
		this.utilisateur = utilisateur;
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

	public void setMessage(String message1) {
		this.message += message1+"<br>";
	}


	
	
}
