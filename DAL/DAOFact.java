package fr.eni.projetJEE.DAL;

public class DAOFact {
	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOImpl();
	}
}
