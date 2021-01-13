package fr.eni.eniEncheres.BLL;

import java.util.List;

import fr.eni.eniEncheres.BO.ArticleVendu;
import fr.eni.eniEncheres.DAL.DAOFact;
import fr.eni.eniEncheres.DAL.IArticleVenduDAO;

public class ArticleVenduManagerImpl implements IArticleVenduManager {
	private IArticleVenduDAO dao = DAOFact.getArticleVenduDAO();

	@Override
	public List<ArticleVendu> getArticleVendu(Integer noArticle, String catégorie) throws ArticleVenduManagerException {
		// TODO Auto-generated method stub
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
	public Integer insertArticle(ArticleVendu article) throws ArticleVenduManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateArticle(ArticleVendu article) throws ArticleVenduManagerException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
