package fr.eni.eniEncheres.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.eniEncheres.BO.Retrait;

public class RetraitDAOImpl implements IRetraitDAO {
	private final String SELECT_BY_NO_ARTICLE = "SELECT * FROM RETRAITS WHERE NO_ARTICLE = ?";
	private final String INSERT = "INSERT INTO RETRAITS VALUES (?,?,?,?)";
	private final String UPDATE = "UPDATE RETRAITS SET rue=?, code_postal=?, ville=? WHERE no_article = ?";
	private final String DELETE = "DELETE FROM RETRAITS WHERE no_article = ?";

	@Override
	public Retrait insertRetrait(Retrait retrait) throws RetraitDALException {

		try (Connection conx = ConnectionProvider.getConnection()) {
			PreparedStatement req = conx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			req.setInt(1, retrait.getNoArticle());
			req.setString(2, retrait.getRue());
			req.setString(3, retrait.getCodePostal());
			req.setString(4, retrait.getVille());
			req.executeUpdate();
		} catch (SQLException e) {
			throw new RetraitDALException("Problème d'insertion d'un retrait");
		}

		return retrait;
	}

	@Override
	public Retrait getRetraitByArticleID(int noArticle) throws RetraitDALException {

		Retrait retrait = null;

		try (Connection conx = ConnectionProvider.getConnection()) {
			PreparedStatement req = conx.prepareStatement(SELECT_BY_NO_ARTICLE);
			req.setInt(1, noArticle);
			ResultSet res = req.executeQuery();
			if (res.next()) {
				retrait = new Retrait();
				retrait.setNoArticle(res.getInt("no_article"));
				retrait.setRue(res.getString("rue"));
				retrait.setCodePostal(res.getString("code_postal"));
				retrait.setVille(res.getString("ville"));
			}
		} catch (SQLException e) {
			throw new RetraitDALException("Problème de lecture d'un retrait");
		}

		return retrait;
	}

	@Override
	public void deleteRetrait(Integer noArticle) throws RetraitDALException {
		try {
			Connection conx = ConnectionProvider.getConnection();
			PreparedStatement req = conx.prepareStatement(DELETE);
			req.setInt(1, noArticle);
			req.executeUpdate();
		} catch (SQLException e) {
			throw new RetraitDALException("Problème de suppression d'un retrait");
		}

	}

	@Override
	public Retrait updateRetrait(Retrait retrait) throws RetraitDALException {

		try {
			Connection conx = ConnectionProvider.getConnection();
			PreparedStatement req = conx.prepareStatement(UPDATE);
			req.setInt(1, retrait.getNoArticle());
			req.setString(2, retrait.getRue());
			req.setString(3, retrait.getCodePostal());
			req.setString(4, retrait.getVille());
			req.executeUpdate();
		} catch (SQLException e) {
			throw new RetraitDALException("Problème de mise à jour d'un retrait");
		}

		return retrait;
	}

}
