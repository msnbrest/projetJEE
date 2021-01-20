package fr.eni.eniEncheres.IHM;

import fr.eni.eniEncheres.BO.Utilisateur;

public class ProfilUtilisateurModel {

	private Utilisateur utilisateur;
	private String message = "";
	private boolean isme = false;

	public ProfilUtilisateurModel() {
	}

	public ProfilUtilisateurModel(Utilisateur utilisateur) {
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

	public boolean isIsme() {
		return isme;
	}

	public void setIsme(boolean isme) {
		this.isme = isme;
	}

}
