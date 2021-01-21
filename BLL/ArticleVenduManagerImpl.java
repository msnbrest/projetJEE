package fr.eni.eniEncheres.BLL;

import java.util.ArrayList;
import java.util.List;

import fr.eni.eniEncheres.BO.ArticleVendu;
import fr.eni.eniEncheres.DAL.DAOFact;
import fr.eni.eniEncheres.DAL.EnchereDALException;
import fr.eni.eniEncheres.DAL.IArticleVenduDAO;

public class ArticleVenduManagerImpl implements IArticleVenduManager {
	private IArticleVenduDAO dao = DAOFact.getArticleVenduDAO();
	
	private List<ArticleVendu> listArticles = new ArrayList<ArticleVendu>();
	
	public List<ArticleVendu> getArticleVendu() throws ArticleVenduManagerException {
		try {
			listArticles = dao.getAll();
			return listArticles;
		} catch (EnchereDALException e) {
			throw new ArticleVenduManagerException(e.getMessage());
		}
	}
	
	@Override
	public ArticleVendu insertArticle(ArticleVendu article) throws ArticleVenduManagerException {
		return dao.insert(article);
	}

	@Override
	public List<ArticleVendu> getArticleVendu(String nomArticle, Integer noCategorie)
			throws ArticleVenduManagerException {
		try {
			return dao.getAllByNomArticleAndNoCategorie(nomArticle, noCategorie);
		} catch (EnchereDALException e) {
			throw new ArticleVenduManagerException(e.getMessage());
		}
	}
	
	@Override
	public ArticleVendu getArticleVendu(Integer noArticle) throws ArticleVenduManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateArticle(Integer noCategorie) throws ArticleVenduManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
