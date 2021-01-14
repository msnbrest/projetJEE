package fr.eni.eniEncheres.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.eniEncheres.BO.Categorie;

public class CategorieDaoImpl implements ICategorieDAO {

	@Override
	public Categorie insert(Categorie categorie) throws CategorieDALException {

		try {
			Connection conx = ConnectionProvider.getConnection();
			PreparedStatement req = conx.prepareStatement("INSERT INTO CATEGORIES (libelle) VALUES (?) ");
			req.setString(1, categorie.getLibelle());
			req.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new CategorieDALException("probleme lors de l insertion");
		}
		// TODO: Retour de produit

		return categorie;
	}

	@Override
	public List<Categorie> getAllCategorie() throws CategorieDALException {

		List<Categorie> users = new ArrayList<Categorie>();

		try {
			Connection conx = ConnectionProvider.getConnection();
			PreparedStatement req = conx.prepareStatement("SELECT * FROM CATEGORIES ");
			ResultSet res = req.executeQuery();
			while (res.next()) {

				Categorie uu = new Categorie();
				uu.setNoCategorie(res.getInt("no_categorie"));
				uu.setLibelle(res.getString("libelle"));
				users.add(uu);

			}

		} catch (Exception e) {
//			e.printStackTrace();
			throw new CategorieDALException("probleme lors de la selection de la liste des categories");

		}

		return users;
	}

	@Override
	public void deleteCategorie(Integer id) throws CategorieDALException {

		try {
			//Connection conx = ConnectionProvider.getConnection();
			//PreparedStatement res = conx.prepareStatement("DELETE * FROM CATEGORIES WHERE noCategorie like ?");

		} catch (Exception e) {
//			e.printStackTrace();
			throw new CategorieDALException(e.getMessage());

		}

	}

}
