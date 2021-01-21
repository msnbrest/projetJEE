package fr.eni.eniEncheres.DAL;

import java.util.List;

import fr.eni.eniEncheres.BO.ArticleVendu;
import fr.eni.eniEncheres.BO.Categorie;
import fr.eni.eniEncheres.BO.Enchere;
import fr.eni.eniEncheres.BO.Utilisateur;

public interface IArticleVenduDAO {

	public ArticleVendu insert(ArticleVendu article) throws ArticleVenduDALException;
	
	public ArticleVendu updateArticleVendu(ArticleVendu article) throws ArticleVenduDALException;
	
	public List<ArticleVendu> getAll() throws ArticleVenduDALException;
	
	public List<ArticleVendu> getAllByNomArticle(String nomArticle) throws ArticleVenduDALException;
	
	public List<ArticleVendu> getAllByNomArticleAndNoCategorie(String nomArticle, Integer noCategorie) throws ArticleVenduDALException;
	
	public List<ArticleVendu> getAllByNoCategorie(Integer noCategorie) throws ArticleVenduDALException;
	
	public ArticleVendu getByID(Integer noArticle) throws ArticleVenduDALException;
	
	public List<ArticleVendu> getAllByNoUtilisateurBeforeSale(Integer noUtilisateur) throws ArticleVenduDALException;
	
	public List<ArticleVendu> getAllByNoUtilisateurInSale(Integer noUtilisateur) throws ArticleVenduDALException;
	
	public List<ArticleVendu> getAllByNoUtilisateurAfterSale(Integer noUtilisateur) throws ArticleVenduDALException;
	
	public void update(Enchere enchere) throws ArticleVenduDALException;
	
	public boolean delete(ArticleVendu article) throws ArticleVenduDALException;
	
	public List<ArticleVendu> getAllByByNoUtilisateurAndTime(Integer noUtilisateur, String moment)throws ArticleVenduDALException;
	
}
