package fr.eni.eniEncheres.BLL;

public class UtilisateurSingleton {
	
	private static IUtilisateurManager instance;
	
	public static IUtilisateurManager getInstance() {
		if (instance == null) {
			instance = new UtilisateurManagerImpl();
		}
		return instance;
	}

}
