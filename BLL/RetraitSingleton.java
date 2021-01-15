package fr.eni.eniEncheres.BLL;

public class RetraitSingleton {

	private static IRetraitManager instance;

	public static IRetraitManager getInstance() {
		if (instance == null) {
			instance = new RetraitManagerImpl();
		}
		return instance;
	}

}
