package fr.eni.eniEncheres.BLL;

import fr.eni.eniEncheres.BO.Retrait;
import fr.eni.eniEncheres.DAL.DAOFact;
import fr.eni.eniEncheres.DAL.IRetraitDAO;
import fr.eni.eniEncheres.DAL.RetraitDALException;

public class RetraitManagerImpl implements IRetraitManager {

	private IRetraitDAO userDao = DAOFact.getRetraitDAO();

	@Override
	public Retrait insertRetrait(Retrait retrait) throws RetraitBLLException {
		
		try {
			userDao.insertRetrait(retrait);
		} catch (RetraitDALException e) {
			throw new RetraitBLLException(e.getMessage());
		}
		return retrait;
		
	}

	@Override
	public Retrait getRetraitByArticleID(int id) throws RetraitBLLException {
		
		Retrait retrait = new Retrait();
		try {
			retrait = userDao.getRetraitByArticleID(id);
		} catch (RetraitDALException e) {
			throw new RetraitBLLException(e.getMessage());
		}
		return retrait;
		
	}

	@Override
	public void deleteRetrait(Integer id) throws RetraitBLLException {
		
		try {
			userDao.deleteRetrait(id);
		} catch (RetraitDALException e) {
			throw new RetraitBLLException(e.getMessage());
		}

	}

	@Override
	public Retrait updateRetrait(Retrait retrait) throws RetraitBLLException {

		try {
			retrait = userDao.updateRetrait(retrait);
		} catch (RetraitDALException e) {
			throw new RetraitBLLException(e.getMessage());
		}
		return retrait;
		
	}

}
