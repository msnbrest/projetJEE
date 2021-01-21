package fr.eni.eniEncheres.BLL;

import java.util.List;

import fr.eni.eniEncheres.BO.ArticleVendu;

public interface IArticleVenduManager {


	public List<ArticleVendu> getArticleVendu() throws ArticleVenduManagerException;
	
	// return Article vendu
	public ArticleVendu insertArticle(ArticleVendu article) throws ArticleVenduManagerException;
	
	// recherche d'articles en fonction de son noCategorie et de son nom (Article)
	public List<ArticleVendu> getArticleVendu(String nomArticle, Integer noCategorie) throws ArticleVenduManagerException;
	
	public List<ArticleVendu> getArticleVendu(String nomArticle) throws ArticleVenduManagerException;
	
	public List<ArticleVendu> getArticleVendu(Integer noCategorie) throws ArticleVenduManagerException;

	// return article vendu
	public ArticleVendu getArticleVenduByNoArticle(Integer noArticle) throws ArticleVenduManagerException;
	
	//return noArticle
	public Integer updateArticle(Integer noCategorie) throws ArticleVenduManagerException;
}
