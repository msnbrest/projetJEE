package fr.eni.eniEncheres.DAL;

import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.eniEncheres.BO.Enchere;
import fr.eni.eniEncheres.BO.Utilisateur;

public class EnchereDAOImpl implements IEnchereDAO {
	private final String INSERT = "INSERT INTO ENCHERES (date_enchere,montant_enchere,no_article,no_utilisateur ) VALUES (?,?,?,?)";
	private final String SELECT_BY_NO_ARTICLE = "SELECT * FROM ENCHERES WHERE NO_ARTICLE like ?";
	private final String SELECT_BY_NO_UTILISATEUR_INSALE = "SELECT * FROM ENCHERES WHERE NO_UTILISATEUR like ? AND date_debut_encheres <= getdate() AND date_fin_encheres >= GETDATE() ORDER by date_fin_encheres";
	private final String SELECT_BY_NO_UTILISATEUR_AFTERSALE = "SELECT * FROM ENCHERES WHERE NO_UTILISATEUR like ? AND date_fin_encheres < GETDATE() ORDER by date_fin_encheres";
	private final String UPDATE = "UPDATE ENCHERES SET date_enchere=?, montant_enchere=?, no_article=?, no_utilisateur=? WHERE NO_ENCHERE like ?";
	private final String DELETE = "DELETE FROM ENCHERES WHERE NO_ENCHERE like ?";
	private final String SELECT_PSEUDO_BY_NO_ARTICLE = "select pseudo, montant_enchere From UTILISATEURS as u inner join ENCHERES e on u.no_utilisateur = e.no_utilisateur Where montant_enchere = (select MAX(montant_enchere) from ENCHERES where no_article = ?);";

	@Override
	public Enchere insertEnchere(Enchere enchere) throws EnchereDALException {

		try (Connection conx = ConnectionProvider.getConnection()) {
			PreparedStatement req = conx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			req.setTimestamp(1, Timestamp.valueOf(enchere.getDateEnchere()));
			req.setInt(2, enchere.getMontantEnchere());
			req.setInt(3, enchere.getNoArticle());
			req.setInt(4, enchere.getUtilisateur().getNoUtilisateur());
			int nbRows = req.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = req.getGeneratedKeys();
				if (rs.next()) {
					enchere.setIdEnchere(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new EnchereDALException("Problème d'insertion d'une enchère ("+e.getMessage()+")");
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
				
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(res.getInt("no_utilisateur"));
				utilisateur.setPseudo(res.getString("pseudo"));
				enchere.setUtilisateur(utilisateur);
			}
		} catch (SQLException e) {
			throw new EnchereDALException("Problème de lecture d'une enchère ("+e.getMessage()+")");
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
				
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(res.getInt("no_utilisateur"));
				utilisateur.setPseudo(res.getString("pseudo"));
				enchere.setUtilisateur(utilisateur);
			}
		} catch (SQLException e) {
			throw new EnchereDALException("Problème de lecture d'une enchère ("+e.getMessage()+")");
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

				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(res.getInt("no_utilisateur"));
				utilisateur.setPseudo(res.getString("pseudo"));
				enchere.setUtilisateur(utilisateur);
			}
		} catch (SQLException e) {
			throw new EnchereDALException("Problème de lecture d'une enchère ("+e.getMessage()+")");
		}

		return enchere;
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
			req.setInt(5, enchere.getUtilisateur().getNoUtilisateur());
			req.executeUpdate();
		} catch (SQLException e) {
			throw new EnchereDALException("Problème de mise à jour d'une enchère ("+e.getMessage()+")");
		}

		return enchere;
	}

	@Override
	public boolean deleteEnchere(Integer id) throws EnchereDALException {

		try {
			Connection conx = ConnectionProvider.getConnection();
			PreparedStatement req = conx.prepareStatement(DELETE);
			req.setInt(1, id);
			req.executeUpdate();
		} catch (SQLException e) {
			throw new EnchereDALException("Problème de suppression d'une enchère ("+e.getMessage()+")");
		}

		return true;
	}
	@Override
	public Enchere getEnchereAndPseudoByNoArticle(Integer noArticle) throws EnchereDALException {
		
		Enchere enchere = new Enchere();
		
		try (Connection conx = ConnectionProvider.getConnection()) {
			PreparedStatement req = conx.prepareStatement(SELECT_PSEUDO_BY_NO_ARTICLE);
			req.setInt(1, noArticle);
			ResultSet res = req.executeQuery();
			if (res.next()) {
				enchere.setMontantEnchere(res.getInt("montant_enchere"));

				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setPseudo(res.getString("pseudo"));
				enchere.setUtilisateur(utilisateur);
			}
		} catch (SQLException e) {
			throw new EnchereDALException("Problème de lecture d'une enchère");
		}

		return enchere;
	}

}
