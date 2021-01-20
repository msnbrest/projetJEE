package fr.eni.eniEncheres.test;

import fr.eni.eniEncheres.BLL.IUtilisateurManager;
import fr.eni.eniEncheres.BLL.UtilisateurBLLException;
import fr.eni.eniEncheres.BLL.UtilisateurSingleton;
import fr.eni.eniEncheres.BO.Utilisateur;

public class TestBLL {
	
	private static IUtilisateurManager userManager = UtilisateurSingleton.getInstance();
	
	public static void main(String[] args) throws UtilisateurBLLException {
//		userManager.CreateUtilisteur(new Utilisateur("CR", "Ronaldo", "Cristiano", "CR@campus-eni.fr", "0707070707", "place etoile", "75000", "Paris", "123", 200, false));
//		userManager.CreateUtilisteur(new Utilisateur("JP", "pppp", "Jpxx", "JP@campus-eni.fr", "111111111", "place etoile", "75000", "Paris", "123", 200, false));
		
		System.out.println(userManager.getAll());

}
}
