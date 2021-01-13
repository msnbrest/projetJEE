package fr.eni.projetJEE.BLL;

import java.time.LocalDate;

import fr.eni.projetJEE.BO.Enchere;
import fr.eni.projetJEE.DAL.DAOFact;
import fr.eni.projetJEE.DAL.EnchereDALException;
import fr.eni.projetJEE.DAL.EnchereDAO;

public class letest {

	public static void main(String[] args) {
		EnchereDAO dao = DAOFact.getEnchereDAO();

		try {
			Enchere tmp = new Enchere();
			tmp.setDateEnchere(LocalDate.now());
			tmp.setMontantEnchere(10);
			tmp.setNoArticle(6);
			tmp.setNoUtilisateur(8);

			dao.insert(tmp);// Enchere enchere =
		} catch (EnchereDALException e) {
			// throw new FraisManagerException("Problème dans l'ajout de le Enchere");
			// afaire
			System.out.println("error insert enchere");
		}

	}

}
