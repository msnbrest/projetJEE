package fr.eni.eniEncheres.BLL;

import java.util.List;

import fr.eni.eniEncheres.BO.Categorie;

public interface ICategorieManager {
	
	public Categorie insertCategorie(Categorie categorie) throws CategorieBLLException;

	public List<Categorie> getAllCategorie() throws CategorieBLLException;

	public Categorie getCategorieById(int id) throws CategorieBLLException;

	public void deleteCategorie(Integer id) throws CategorieBLLException;

	public Categorie updateCategorie(Categorie categorie) throws CategorieBLLException;

}
