package fr.eni.eniEncheres.BLL;

import fr.eni.eniEncheres.BO.Retrait;

public interface IRetraitManager {

	public Retrait insertRetrait(Retrait retrait) throws RetraitBLLException;

	public Retrait getRetraitByArticleID(int id) throws RetraitBLLException;

	public void deleteRetrait(Integer id) throws RetraitBLLException;

	public Retrait updateRetrait(Retrait retrait) throws RetraitBLLException;

}
