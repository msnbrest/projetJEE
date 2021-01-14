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
			return dao.getAll();
		} catch (EnchereDALException e) {
			throw new ArticleVenduManagerException("Problème dans la récupération des articles");
			//e.printStackTrace();
		}
		
	}

	@Override
	public List<ArticleVendu> getArticleVendu(Integer noArticle, String categorie) throws ArticleVenduManagerException {
		
		
		return null;
	}

	@Override
	public List<ArticleVendu> getListeArticleVenduUser(Integer noUtilisateur) throws ArticleVenduManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArticleVendu getArticleVendu(Integer noArticle) throws ArticleVenduManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArticleVendu insertArticle(ArticleVendu article) throws ArticleVenduManagerException {
		return dao.insert(article);
	}

	@Override
	public Integer updateArticle(ArticleVendu article) throws ArticleVenduManagerException {
		// TODO Auto-generated method stub
		return null;
	}
}
