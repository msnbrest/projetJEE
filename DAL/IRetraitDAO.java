package fr.eni.eniEncheres.DAL;

import fr.eni.eniEncheres.BO.Retrait;

public interface IRetraitDAO {
	
	public Retrait insertRetrait(Retrait retrait) throws RetraitDALException;

	public Retrait getRetraitByArticleID(int noArticle) throws RetraitDALException;

	public Retrait updateRetrait(Retrait retrait) throws RetraitDALException;

	public boolean deleteRetrait(Integer noArticle) throws RetraitDALException;
}
