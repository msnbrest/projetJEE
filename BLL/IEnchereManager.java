package fr.eni.eniEncheres.BLL;

import fr.eni.eniEncheres.BO.Enchere;

public interface IEnchereManager {

	public Enchere insertEnchere(Enchere enchere) throws EnchereBLLException;

	public Enchere getEnchereByArticleId(int noArticle) throws EnchereBLLException;

	public Enchere getEnchereByUtilisateurIdInSale(int noArticle) throws EnchereBLLException;

	public Enchere getEnchereByUtilisateurIdAfterSale(int noArticle) throws EnchereBLLException;

	public void deleteEnchere(Integer id) throws EnchereBLLException;

	public Enchere updateEnchere(Enchere enchere) throws EnchereBLLException;

}
