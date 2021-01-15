package fr.eni.eniEncheres.DAL;

import java.util.List;

import fr.eni.eniEncheres.BO.ArticleVendu;
import fr.eni.eniEncheres.BO.Categorie;
import fr.eni.eniEncheres.BO.Utilisateur;

public interface IArticleVenduDAO {

	public ArticleVendu insert(ArticleVendu article);
	
	public List<ArticleVendu> getAll() throws EnchereDALException;
	
	public void delete(ArticleVendu article) throws EnchereDALException;
	
	
	
	// TODO voir la methode à mettre en place selon moi
	public ArticleVendu update(Integer a);

	//Give the list of itmes sold using the name without categorie field
	public List<ArticleVendu> getListeArticleVenduUser(String researchNameItem);
	
	//Give the same result using the close other methode but using another field
	public List<ArticleVendu> getListeArticleVenduUser(String researchNameItem, Categorie noCat);
}
