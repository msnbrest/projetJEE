package fr.eni.eniEncheres.IHM;

public class VoirObjModel {

	private int meilleure_offre_montant = -1;
	private String meilleure_offre_pseudo = "";

	public String getMeilleure_offre() {
		return meilleure_offre_montant < 0 ? "Aucun offre"
				: meilleure_offre_montant + " pts par " + meilleure_offre_pseudo;
	}

	public int getOffre_minimale() {
		return meilleure_offre_montant < 0 ? 1 : meilleure_offre_montant + 1;
	}

}
