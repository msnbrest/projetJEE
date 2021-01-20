package fr.eni.eniEncheres.BLL;

import java.util.ArrayList;
import java.util.List;

import fr.eni.eniEncheres.BO.Categorie;
import fr.eni.eniEncheres.DAL.CategorieDALException;
import fr.eni.eniEncheres.DAL.DAOFact;
import fr.eni.eniEncheres.DAL.ICategorieDAO;

public class CategorieManagerImpl implements ICategorieManager {
	
	private ICategorieDAO userDao = DAOFact.getCategorieDAO();

	@Override
	public Categorie insertCategorie(Categorie categorie) throws CategorieBLLException {
		
		try {
			userDao.insertCategorie(categorie);
		} catch (CategorieDALException e) {
			throw new CategorieBLLException("probleme Bll exception lors de la creation");
		}
		return categorie;
		
	}

	@Override
	public List<Categorie> getAllCategorie() throws CategorieBLLException {

		List<Categorie> categories = new ArrayList<Categorie>();
		try {
			categories = userDao.getAllCategorie();
		} catch (CategorieDALException e) {
			throw new CategorieBLLException(e.getMessage());
		}
		return categories;
		
	}

	@Override
	public Categorie getCategorieById(int id) throws CategorieBLLException {
		Categorie categorie = new Categorie();
		try {
			categorie = userDao.getCategorieById(id);
		} catch (CategorieDALException e) {
			throw new CategorieBLLException(e.getMessage());
		}
		return categorie;
	}

	@Override
	public void deleteCategorie(Integer id) throws CategorieBLLException {
		
		try {
			userDao.deleteCategorie(id);;
		} catch (CategorieDALException e) {
			throw new CategorieBLLException(e.getMessage());
		}

	}

	@Override
	public Categorie updateCategorie(Categorie categorie) throws CategorieBLLException {
		
		try {
			categorie = userDao.updateCategorie(categorie);
		} catch (CategorieDALException e) {
			throw new CategorieBLLException(e.getMessage());
		}
		return categorie;
		
	}

}
