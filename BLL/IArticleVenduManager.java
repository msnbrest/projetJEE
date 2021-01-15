package fr.eni.eniEncheres.BLL;

import java.util.List;

import fr.eni.eniEncheres.BO.ArticleVendu;

public interface IArticleVenduManager {


	public List<ArticleVendu> getArticleVendu() throws ArticleVenduManagerException;
	
	// return noArticle
	public ArticleVendu insertArticle(ArticleVendu article) throws ArticleVenduManagerException;
	
	
	public List<ArticleVendu> getArticleVendu(Integer noArticle, String categorie) throws ArticleVenduManagerException;
	
	// Obtention de la liste des articles vendus par un utilisateur
	public List<ArticleVendu> getListeArticleVenduUser(Integer noUtilisateur) throws ArticleVenduManagerException;
	
	// return article vendu
	public ArticleVendu getArticleVendu(Integer noArticle) throws ArticleVenduManagerException;
	
	//return noArticle
	public Integer updateArticle(ArticleVendu article) throws ArticleVenduManagerException;
}
