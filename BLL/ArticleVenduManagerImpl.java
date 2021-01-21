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

	@Override
	public ArticleVendu insertArticle(ArticleVendu article) throws ArticleVenduManagerException {
		article.setPrixVente(article.getMiseAPrix());
		ArticleVendu articleInsertion = article;
		try {
			articleInsertion = dao.insert(articleInsertion);
		} catch (ArticleVenduDALException e) {
		}
		return articleInsertion;
	}

	@Override
	public List<ArticleVendu> getArticlesVendu() throws ArticleVenduManagerException {
		try {
			listArticles = dao.getAll();
		} catch (ArticleVenduDALException e) {
			throw new ArticleVenduManagerException(e.getMessage());
		}
		return listArticles;
	}

	@Override
	public List<ArticleVendu> getArticlesVendu(String nomArticle, Integer noCategorie)
			throws ArticleVenduManagerException {

		try {
			return dao.getAllByNomArticleAndNoCategorie(nomArticle, noCategorie);
		} catch (ArticleVenduDALException e) {
			throw new ArticleVenduManagerException(e.getMessage());
		}

	}

	@Override
	public List<ArticleVendu> getArticlesVendu(String nomArticle) throws ArticleVenduManagerException {

		try {
			return dao.getAllByNomArticle(nomArticle);
		} catch (ArticleVenduDALException e) {
			throw new ArticleVenduManagerException("Erreur dans la récupération des articles aux niveau de la BLL");
		}

	}

	@Override
	public ArticleVendu getArticleVenduByNoArticle(Integer noArticle) throws ArticleVenduManagerException {

		try {
			return dao.getByID(noArticle);
		} catch (ArticleVenduDALException e) {
			throw new ArticleVenduManagerException("Erreur dans la récupération des articles aux niveau de la BLL");
		}

	}

	@Override
	public List<ArticleVendu> getArticlesVenduByNoCategorie(Integer noCategorie) throws ArticleVenduManagerException {

		try {
			return dao.getAllByNoCategorie(noCategorie);
		} catch (ArticleVenduDALException e) {
			throw new ArticleVenduManagerException("Erreur dans la récupération des articles aux niveau de la BLL");
		}

	}

	@Override
	public Integer updateArticle(Integer noCategorie) throws ArticleVenduManagerException {
		// TODO Auto-generated method stub
		return null;
	}

}
