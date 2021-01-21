package fr.eni.eniEncheres.IHM;

import fr.eni.eniEncheres.BO.ArticleVendu;

public class VoirObjModel {

	private int meilleure_offre_montant = -1;
	private String meilleure_offre_pseudo = "";
	private String message;
	private ArticleVendu articleVendu;
	
	public VoirObjModel() {
		
	}
	
	public VoirObjModel(int meilleure_offre_montant, String meilleure_offre_pseudo, String message,
			ArticleVendu articleVendu) {
		super();
		this.meilleure_offre_montant = meilleure_offre_montant;
		this.meilleure_offre_pseudo = meilleure_offre_pseudo;
		this.message = message;
		this.articleVendu = articleVendu;
	}

	public String getMeilleure_offre() {
		return meilleure_offre_montant < 0 ? "Aucun offre"
				: meilleure_offre_montant + " pts par " + meilleure_offre_pseudo;
	}

	public int getOffre_minimale() {
		return meilleure_offre_montant < 0 ? 1 : meilleure_offre_montant + 1;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}
	
	

}
