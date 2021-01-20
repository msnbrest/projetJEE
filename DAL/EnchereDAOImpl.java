package fr.eni.eniEncheres.DAL;

import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.eniEncheres.BO.Enchere;

public class EnchereDAOImpl implements IEnchereDAO {
	private final String INSERT = "INSERT INTO ENCHERES (date_enchere,montant_enchere,no_article,no_utilisateur ) VALUES (?,?,?,?)";
	private final String SELECT_BY_NO_ARTICLE = "SELECT * FROM ENCHERES WHERE NO_ARTICLE = ?";
	private final String SELECT_BY_NO_UTILISATEUR_INSALE = "SELECT * FROM ENCHERES WHERE NO_UTILISATEUR = ? AND date_debut_encheres <= getdate() AND date_fin_encheres >= GETDATE() ORDER by date_fin_encheres";
	private final String SELECT_BY_NO_UTILISATEUR_AFTERSALE = "SELECT * FROM ENCHERES WHERE NO_UTILISATEUR = ? AND date_fin_encheres < GETDATE() ORDER by date_fin_encheres";
	private final String UPDATE = "UPDATE ENCHERES SET date_enchere=?, montant_enchere=?, no_article=?, no_utilisateur=? WHERE NO_ENCHERE = ?";
	private final String DELETE = "DELETE FROM ENCHERES WHERE NO_ENCHERE = ?";

	@Override
	public Enchere insertEnchere(Enchere enchere) throws EnchereDALException {

		try (Connection conx = ConnectionProvider.getConnection()) {
			PreparedStatement req = conx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			req.setTimestamp(1, Timestamp.valueOf(enchere.getDateEnchere()));
			req.setInt(2, enchere.getMontantEnchere());
			req.setInt(3, enchere.getNoArticle());
			req.setInt(4, enchere.getNoUtilisateur());
			int nbRows = req.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = req.getGeneratedKeys();
				if (rs.next()) {
					enchere.setIdEnchere(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new EnchereDALException("problème dans l'insertion d'une Enchere");
		}

		return enchere;

	}

	@Override
	public Enchere getEnchereByArticleId(int id) throws EnchereDALException {

		Enchere enchere = null;

		try (Connection conx = ConnectionProvider.getConnection()) {
			PreparedStatement req = conx.prepareStatement(SELECT_BY_NO_ARTICLE);
			req.setInt(1, id);
			ResultSet res = req.executeQuery();
			if (res.next()) {
				enchere = new Enchere();
				enchere.setNoArticle(res.getInt("no_enchere"));
				enchere.setDateEnchere(res.getTimestamp("date_enchere").toLocalDateTime());
				enchere.setMontantEnchere(res.getInt("montant_enchere"));
				enchere.setNoArticle(res.getInt("no_article"));
				enchere.setNoUtilisateur(res.getInt("no_utilisateur"));
			}
		} catch (SQLException e) {
			throw new EnchereDALException("Problème de lecture d'une enchère");
		}

		return enchere;
	}

	@Override
	public Enchere getEnchereByUtilisateurIdInSale(int noArticle) throws EnchereDALException {

		Enchere enchere = null;

		try (Connection conx = ConnectionProvider.getConnection()) {
			PreparedStatement req = conx.prepareStatement(SELECT_BY_NO_UTILISATEUR_INSALE);
			req.setInt(1, noArticle);
			ResultSet res = req.executeQuery();
			if (res.next()) {
				enchere = new Enchere();
				enchere.setNoArticle(res.getInt("no_enchere"));
				enchere.setDateEnchere(res.getTimestamp("date_enchere").toLocalDateTime());
				enchere.setMontantEnchere(res.getInt("montant_enchere"));
				enchere.setNoArticle(res.getInt("no_article"));
				enchere.setNoUtilisateur(res.getInt("no_utilisateur"));
			}
		} catch (SQLException e) {
			throw new EnchereDALException("Problème de lecture d'une enchère");
		}

		return enchere;
	}

	@Override
	public Enchere getEnchereByUtilisateurIdAfterSale(int noArticle) throws EnchereDALException {

		Enchere enchere = null;

		try (Connection conx = ConnectionProvider.getConnection()) {
			PreparedStatement req = conx.prepareStatement(SELECT_BY_NO_UTILISATEUR_AFTERSALE);
			req.setInt(1, noArticle);
			ResultSet res = req.executeQuery();
			if (res.next()) {
				enchere = new Enchere();
				enchere.setNoArticle(res.getInt("no_enchere"));
				enchere.setDateEnchere(res.getTimestamp("date_enchere").toLocalDateTime());
				enchere.setMontantEnchere(res.getInt("montant_enchere"));
				enchere.setNoArticle(res.getInt("no_article"));
				enchere.setNoUtilisateur(res.getInt("no_utilisateur"));
			}
		} catch (SQLException e) {
			throw new EnchereDALException("Problème de lecture d'une enchère");
		}

		return enchere;
	}

	@Override
	public void deleteEnchere(Integer id) throws EnchereDALException {

		try {
			Connection conx = ConnectionProvider.getConnection();
			PreparedStatement req = conx.prepareStatement(DELETE);
			req.setInt(1, id);
			req.executeUpdate();
		} catch (SQLException e) {
			throw new EnchereDALException("Problème de suppression d'une enchère");
		}

	}

	@Override
	public Enchere updateEnchere(Enchere enchere) throws EnchereDALException {

		try {
			Connection conx = ConnectionProvider.getConnection();
			PreparedStatement req = conx.prepareStatement(UPDATE);
			req.setInt(1, enchere.getIdEnchere());
			req.setTimestamp(2, Timestamp.valueOf(enchere.getDateEnchere()));
			req.setInt(3, enchere.getMontantEnchere());
			req.setInt(4, enchere.getNoArticle());
			req.setInt(5, enchere.getNoUtilisateur());
			req.executeUpdate();
		} catch (SQLException e) {
			throw new EnchereDALException("Problème de mise à jour d'une enchère");
		}

		return enchere;
	}

}
