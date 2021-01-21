package fr.eni.eniEncheres.BLL;

import java.util.List;

import fr.eni.eniEncheres.BO.ArticleVendu;

public interface IArticleVenduManager {

	// return Article vendu
	public ArticleVendu insertArticle(ArticleVendu article) throws ArticleVenduManagerException;

	public List<ArticleVendu> getArticlesVendu() throws ArticleVenduManagerException;

	// return article vendu
	public List<ArticleVendu> getArticlesVenduByNoCategorie(Integer noCategorie) throws ArticleVenduManagerException;

	// recherche d'articles en fonction de son noCategorie et de son nom (Article)
	public List<ArticleVendu> getArticlesVendu(String nomArticle, Integer noCategorie)
			throws ArticleVenduManagerException;

	public List<ArticleVendu> getArticlesVendu(String nomArticle) throws ArticleVenduManagerException;

	// return article vendu
	public ArticleVendu getArticleVenduByNoArticle(Integer noArticle) throws ArticleVenduManagerException;

	// return noArticle
	public Integer updateArticle(Integer noCategorie) throws ArticleVenduManagerException;
}
