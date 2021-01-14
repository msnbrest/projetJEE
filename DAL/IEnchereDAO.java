package fr.eni.eniEncheres.DAL;

import java.util.List;

import fr.eni.eniEncheres.BO.Enchere;

public interface IEnchereDAO {
	public Enchere insert(Enchere enchere) throws EnchereDALException;
	public List<Enchere> getAll() throws EnchereDALException;
}
