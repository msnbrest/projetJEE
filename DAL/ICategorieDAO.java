package fr.eni.eniEncheres.DAL;

import java.util.List;

import fr.eni.eniEncheres.BO.Categorie;

public interface ICategorieDAO {
	
	public Categorie insertCategorie(Categorie categorie) throws CategorieDALException;

	public List<Categorie> getAllCategorie() throws CategorieDALException;

	public Categorie getCategorieById(int id) throws CategorieDALException;

	public void deleteCategorie(Integer id) throws CategorieDALException;

	public Categorie updateCategorie(Categorie categorie) throws CategorieDALException;
}
