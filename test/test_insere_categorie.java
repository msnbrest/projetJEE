package fr.eni.eniEncheres.test;

import fr.eni.eniEncheres.BO.Categorie;
import fr.eni.eniEncheres.DAL.DAOFact;
import fr.eni.eniEncheres.DAL.CategorieDALException;
import fr.eni.eniEncheres.DAL.ICategorieDAO;

public class test_insere_categorie {

	public static void main(String[] args) {
		ICategorieDAO dao = DAOFact.getCategorieDAO();
		try {
			Categorie tmp = new Categorie("sport");
			dao.insert(tmp);// Enchere enchere =
		} catch (CategorieDALException e) {
			// throw new FraisManagerException("Problème dans l'ajout de le Enchere");
			// afaire
			System.out.println("error insert enchere");
		}
	}
}
