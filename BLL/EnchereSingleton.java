package fr.eni.eniEncheres.BLL;

public class EnchereSingleton {
	
	private static IEnchereManager instance;
	
	public static IEnchereManager getInstance() {
		if (instance == null) {
			instance = new EnchereManagerImpl();
		}
		return instance;
	}

}
