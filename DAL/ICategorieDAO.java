package fr.eni.eniEncheres.DAL;

import java.util.List;

import fr.eni.eniEncheres.BO.Categorie;

public interface ICategorieDAO {
	public Categorie insert(Categorie categorie) throws CategorieDALException;
	public List<Categorie> getAllCategorie() throws CategorieDALException;
	public void deleteCategorie(Integer id) throws CategorieDALException;
}
