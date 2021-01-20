package fr.eni.eniEncheres.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.eniEncheres.BO.Categorie;

public class CategorieDAOImpl implements ICategorieDAO {
	
	private final String INSERT = "INSERT INTO CATEGORIES (libelle) VALUES (?) ";
	private final String SELECT = "SELECT * FROM CATEGORIES";
	private final String SELECT_BY_NO_CATEGORIE = "SELECT * FROM CATEGORIES WHERE NO_CATEGORIE = ?";
	private final String UPDATE = "UPDATE CATEGORIES SET libelle=? WHERE NO_CATEGORIE = ?";
	private final String DELETE = "DELETE FROM CATEGORIES WHERE NO_CATEGORIE = ?";
	
	
	@Override
	public Categorie insertCategorie(Categorie categorie) throws CategorieDALException {

		try (Connection conx = ConnectionProvider.getConnection()){
			PreparedStatement req = conx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			req.setString(1, categorie.getLibelle());
			int nbRows = req.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = req.getGeneratedKeys();
				if (rs.next()) {
					categorie.setNoCategorie(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new CategorieDALException("problème dans l'insertion d'une Categorie");
		}

		return categorie;
	}

	@Override
	public List<Categorie> getAllCategorie() throws CategorieDALException {

		List<Categorie> categories = new ArrayList<Categorie>();

		try (Connection conx = ConnectionProvider.getConnection()) {
			PreparedStatement req = conx.prepareStatement(SELECT);
			ResultSet res = req.executeQuery();
			while (res.next()) {

				Categorie categorie = new Categorie();
				categorie.setNoCategorie(res.getInt("no_categorie"));
				categorie.setLibelle(res.getString("libelle"));
				categories.add(categorie);

			}
		} catch (SQLException e) {
			throw new CategorieDALException("Problème de lecture des catégories");
		}

		return categories;
	}

	@Override
	public Categorie getCategorieById(int id) throws CategorieDALException {

		Categorie categorie = null;

		try (Connection conx = ConnectionProvider.getConnection()) {
			PreparedStatement req = conx.prepareStatement(SELECT_BY_NO_CATEGORIE);
			req.setInt(1, id);
			ResultSet res = req.executeQuery();
			if (res.next()) {
				categorie = new Categorie();
				categorie.setNoCategorie(res.getInt("no_categorie"));
				categorie.setLibelle(res.getString("libelle"));
			}
		} catch (SQLException e) {
			throw new CategorieDALException("Problème de lecture d'une catégorie");
		}

		return categorie;
	}

	@Override
	public void deleteCategorie(Integer id) throws CategorieDALException {
		
		try {
			Connection conx = ConnectionProvider.getConnection();
			PreparedStatement req = conx.prepareStatement(DELETE);
			req.setInt(1, id);
			req.executeUpdate();
		} catch (SQLException e) {
			throw new CategorieDALException("Problème de suppression d'une catégorie");
		}

	}

	@Override
	public Categorie updateCategorie(Categorie categorie) throws CategorieDALException {
		
		try {
			Connection conx = ConnectionProvider.getConnection();
			PreparedStatement req = conx.prepareStatement(UPDATE);
			req.setString(1, categorie.getLibelle());
			req.setInt(2, categorie.getNoCategorie());
			req.executeUpdate();
		} catch (SQLException e) {
			throw new CategorieDALException("Problème de mise à jour d'une catégorie");
		}

		return categorie;
	}

}
