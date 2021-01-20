package fr.eni.eniEncheres.test;

import java.time.LocalDate;

import fr.eni.eniEncheres.BO.ArticleVendu;
import fr.eni.eniEncheres.BO.Categorie;
import fr.eni.eniEncheres.BO.Utilisateur;
import fr.eni.eniEncheres.DAL.DAOFact;
import fr.eni.eniEncheres.DAL.EnchereDALException;
import fr.eni.eniEncheres.DAL.IArticleVenduDAO;
import fr.eni.eniEncheres.DAL.IUtilisateurDao;


public class TestGilles {

	public static void main(String[] args) {


		IUtilisateurDao daoUtilisateur = DAOFact.getUtilisateurDAO();
		
		
		
		Utilisateur utilisateur1 = new Utilisateur("titi64", "Loiseau", "Pierre", "p.loiseau@campus-eni.fr", "0855246525", "rue du nid", "29000", "Quimper", "fsx5dsq448", 100, false);
//		Utilisateur utilisateur2 = new Utilisateur("titi64", "Bellepaire", "Lucie", "bellepaire2L@campus-eni.fr", "0955246528", "rue de la mer", "29200", "Brest", "5qsfd", 100, false);
//		try {
//			daoUtilisateur.create(utilisateur1);
//			daoUtilisateur.create(utilisateur2);
//		} catch (UtilisateurDaoException e) {
//			System.err.println("problème dans la création de(s) utilisateur(s)");
//			e.printStackTrace();
//		}
		
		/* Creation d'une Catégorie*/
		
//		ICategorieDAO daoCategorie = DAOFact.getCategorieDAO();
//		try {
//			Categorie tmp = new Categorie("Véhicule"); //Attention majuscule sur Vehicule
//			daoCategorie.insertCategorie(tmp);// Enchere enchere =
//		} catch (CategorieDALException e) {
//			// throw new FraisManagerException("Problème dans l'ajout de le Enchere");
//			// afaire
//			System.out.println("error insert enchere");
//		}
		
//		utilisateur1.setNoUtilisateur(1);
//		Categorie categorieChoisie = new Categorie(2, "Véhicule");
//		Retrait lieuRetrait1 = new Retrait("rue des chiens", "29000", "quimper");
//		//lieuRetrait1.setNoArticle(5);
//		ArticleVendu firstArticle = new ArticleVendu("voiture99", "Voiture avec quatre roues bon état", LocalDate.now(), LocalDate.now(), 10, 12, utilisateur1, categorieChoisie, lieuRetrait1);
//		
//		DAOFact.getArticleVenduDAO().insert(firstArticle);
//		
//		System.out.println(firstArticle);
		
//		IRetraitDAO daoRetrait = DAOFact.getRetraitDAO();
//		try {
//			firstArticle.setLieuRetrait(daoRetrait.insertRetrait(firstArticle.getLieuRetrait()));
//		} catch (RetraitDALException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		IArticleVenduDAO daoArticleVendu = DAOFact.getArticleVenduDAO();
		try {
//			System.out.println(daoArticleVendu.getAll());
			String mot = "table";
			for(ArticleVendu a : daoArticleVendu.getAll(mot)) {
				System.out.println(a);
			}
		} catch (EnchereDALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}