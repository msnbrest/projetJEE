package fr.eni.eniEncheres.DAL;

import java.util.List;

import fr.eni.eniEncheres.BO.ArticleVendu;
import fr.eni.eniEncheres.BO.Categorie;
import fr.eni.eniEncheres.BO.Enchere;
import fr.eni.eniEncheres.BO.Utilisateur;

public interface IArticleVenduDAO {

	public ArticleVendu insert(ArticleVendu article);
	
	public void delete(ArticleVendu article) throws EnchereDALException;
	
	public void update(Enchere enchere) throws EnchereDALException;
	
	public ArticleVendu updateArticleVendu(ArticleVendu article) throws EnchereDALException;
	
	public List<ArticleVendu> getAll() throws EnchereDALException;
	
	public List<ArticleVendu> getAllByNomArticle(String nomArticle) throws EnchereDALException;
	
	public List<ArticleVendu> getAllByNomArticleAndNoCategorie(String nomArticle, Integer noCategorie) throws EnchereDALException;
	
	public List<ArticleVendu> getAllByNoCategorie(Integer noCategorie) throws EnchereDALException;
	
	public ArticleVendu getByID(Integer noArticle) throws EnchereDALException;
	
	public List<ArticleVendu> getAllByNoUtilisateurBeforeSale(Integer noUtilisateur) throws EnchereDALException;
	
	public List<ArticleVendu> getAllByNoUtilisateurInSale(Integer noUtilisateur) throws EnchereDALException;
	
	public List<ArticleVendu> getAllByNoUtilisateurAfterSale(Integer noUtilisateur) throws EnchereDALException;
	
}
