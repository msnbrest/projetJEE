package fr.eni.eniEncheres.BLL;

import java.util.ArrayList;
import java.util.List;

import fr.eni.eniEncheres.BO.ArticleVendu;
import fr.eni.eniEncheres.DAL.ArticleVenduDALException;
import fr.eni.eniEncheres.DAL.DAOFact;
import fr.eni.eniEncheres.DAL.EnchereDALException;
import fr.eni.eniEncheres.DAL.IArticleVenduDAO;

public class ArticleVenduManagerImpl implements IArticleVenduManager {
	private IArticleVenduDAO dao = DAOFact.getArticleVenduDAO();
	
	private List<ArticleVendu> listArticles = new ArrayList<ArticleVendu>();
	
	public List<ArticleVendu> getArticleVendu() throws ArticleVenduManagerException {
		try {
			listArticles = dao.getAll();
		} catch (ArticleVenduDALException e) {
			throw new ArticleVenduManagerException(e.getMessage());
		}
		return listArticles;
	}
	
	@Override
	public ArticleVendu insertArticle(ArticleVendu article) throws ArticleVenduManagerException {
		try {
			return dao.insert(article);
		} catch (ArticleVenduDALException e) {
			throw new ArticleVenduManagerException(e.getMessage());
		}
	}

	@Override
	public List<ArticleVendu> getArticleVendu(String nomArticle, Integer noCategorie)
			throws ArticleVenduManagerException {
		
		try {
			return dao.getAllByNomArticleAndNoCategorie(nomArticle, noCategorie);
		} catch (ArticleVenduDALException e) {
			throw new ArticleVenduManagerException(e.getMessage());
		}
		
	}
	
	@Override
	public ArticleVendu getArticleVendu(Integer noArticle) throws ArticleVenduManagerException {
		try {
			return dao.getByID(noArticle);

		} catch (ArticleVenduDALException e) {
			throw new ArticleVenduManagerException(e.getMessage());
		}
	}

	@Override
	public Integer updateArticle(Integer noCategorie) throws ArticleVenduManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
