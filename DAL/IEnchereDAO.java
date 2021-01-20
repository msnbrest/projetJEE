package fr.eni.eniEncheres.DAL;

import fr.eni.eniEncheres.BO.Enchere;

public interface IEnchereDAO {
	
	public Enchere insertEnchere(Enchere enchere) throws EnchereDALException;

	public Enchere getEnchereByArticleId(int id) throws EnchereDALException;

	public Enchere getEnchereByUtilisateurIdInSale(int noArticle) throws EnchereDALException;

	public Enchere getEnchereByUtilisateurIdAfterSale(int noArticle) throws EnchereDALException;

	public void deleteEnchere(Integer id) throws EnchereDALException;

	public Enchere updateEnchere(Enchere enchere) throws EnchereDALException;
}
