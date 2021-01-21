package fr.eni.eniEncheres.BLL;

import fr.eni.eniEncheres.BO.Enchere;
import fr.eni.eniEncheres.DAL.DAOFact;
import fr.eni.eniEncheres.DAL.EnchereDALException;
import fr.eni.eniEncheres.DAL.IEnchereDAO;

public class EnchereManagerImpl implements IEnchereManager {

	private IEnchereDAO userDao = DAOFact.getEnchereDAO();

	@Override
	public Enchere insertEnchere(Enchere enchere) throws EnchereBLLException {

		try {
			userDao.insertEnchere(enchere);
		} catch (EnchereDALException e) {
			throw new EnchereBLLException("probleme Bll exception lors de la creation");
		}
		return enchere;

	}

	@Override
	public Enchere getEnchereByArticleId(int noArticle) throws EnchereBLLException {

		Enchere enchere = new Enchere();
		try {
			enchere = userDao.getEnchereByArticleId(noArticle);
		} catch (EnchereDALException e) {
			throw new EnchereBLLException(e.getMessage());
		}
		return enchere;
	}

	@Override
	public Enchere getEnchereByUtilisateurIdInSale(int noArticle) throws EnchereBLLException {

		Enchere enchere = new Enchere();
		try {
			enchere = userDao.getEnchereByUtilisateurIdInSale(noArticle);//TODO : by in sale
		} catch (EnchereDALException e) {
			throw new EnchereBLLException(e.getMessage());
		}
		return enchere;

	}

	@Override
	public Enchere getEnchereByUtilisateurIdAfterSale(int noArticle) throws EnchereBLLException {

		Enchere enchere = new Enchere();
		try {
			enchere = userDao.getEnchereByUtilisateurIdAfterSale(noArticle);//TODO : by after sale
		} catch (EnchereDALException e) {
			throw new EnchereBLLException(e.getMessage());
		}
		return enchere;

	}

	@Override
	public void deleteEnchere(Integer id) throws EnchereBLLException {

		try {
			userDao.deleteEnchere(id);
		} catch (EnchereDALException e) {
			throw new EnchereBLLException(e.getMessage());
		}

	}

	@Override
	public Enchere updateEnchere(Enchere enchere) throws EnchereBLLException {

		try {
			enchere = userDao.updateEnchere(enchere);
		} catch (EnchereDALException e) {
			throw new EnchereBLLException(e.getMessage());
		}
		return enchere;

	}

}
