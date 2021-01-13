package fr.eni.projetJEE.DAL;

import java.util.List;

import fr.eni.projetJEE.BO.Enchere;

public interface EnchereDAO {
	public Enchere insert(Enchere enchere) throws EnchereDALException;
	public List<Enchere> getAll() throws EnchereDALException;
}
