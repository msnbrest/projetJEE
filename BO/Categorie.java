package fr.eni.eniEncheres.BO;

import java.util.ArrayList;

public class Categorie {
	private int noCategorie;
	private String libelle;
	private ArrayList<ArticleVendu> ListeArticleVendu = new ArrayList<ArticleVendu>();

	public Categorie() {
	}

	public Categorie(String libelle) {
		super();
		this.libelle = libelle;
	}

	public Categorie(int noCategorie, String libelle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + ", ListeArticleVendu="
				+ ListeArticleVendu + "]";
	}

	public ArrayList<ArticleVendu> getListeArticleVendu() {
		return ListeArticleVendu;
	}

	public void setListeArticleVendu(ArrayList<ArticleVendu> listeArticleVendu1) {
		ListeArticleVendu = listeArticleVendu1;
	}

}
