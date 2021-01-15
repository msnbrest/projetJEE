package fr.eni.eniEncheres.BLL;

public class CategorieSingleton {

	private static ICategorieManager instance;

	public static ICategorieManager getInstance() {
		if (instance == null) {
			instance = new CategorieManagerImpl();
		}
		return instance;
	}

}
