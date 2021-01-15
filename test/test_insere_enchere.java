package fr.eni.eniEncheres.test;

import java.time.LocalDate;

import fr.eni.eniEncheres.BO.Enchere;
import fr.eni.eniEncheres.DAL.DAOFact;
import fr.eni.eniEncheres.DAL.EnchereDALException;
import fr.eni.eniEncheres.DAL.IEnchereDAO;

public class test_insere_enchere {

	public static void main(String[] args) {
		IEnchereDAO dao = DAOFact.getEnchereDAO();

		try {
			Enchere tmp = new Enchere(LocalDate.now(), 10, 6, 1);
			dao.insert(tmp);// Enchere enchere =
		} catch (EnchereDALException e) {
			// throw new FraisManagerException("Problème dans l'ajout de le Enchere");
			// afaire
			System.out.println("error insert enchere");
		}

	}

}
