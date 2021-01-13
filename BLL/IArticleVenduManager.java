package fr.eni.eniEncheres.BLL;

import java.util.List;

import fr.eni.eniEncheres.BO.ArticleVendu;

public interface IArticleVenduManager {

	//TODO Regardé les methodes
	public List<ArticleVendu> getArticleVendu(Integer noArticle, String catégorie) throws ArticleVenduManagerException;
	
	// Obption de la liste des articles vendus par un utilisateur
	public List<ArticleVendu> getListeArticleVenduUser(Integer noUtilisateur) throws ArticleVenduManagerException;
	
	// return article vendu
	public ArticleVendu getArticleVendu(Integer noArticle) throws ArticleVenduManagerException;
	
	// return noArticle
	public Integer insertArticle(ArticleVendu article) throws ArticleVenduManagerException;
	
	//return noArticle
	public Integer updateArticle(ArticleVendu article) throws ArticleVenduManagerException;
	
}
