package fr.eni.eniEncheres.DAL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.eniEncheres.BO.Enchere;

public class EnchereDAOImpl implements EnchereDAO {
	private String INSERT = "INSERT INTO ENCHERES (date_enchere,montant_enchere,no_article,no_utilisateur ) VALUES (?,?,?,?)";
	private String SELECT = "SELECT no_enchere,date_enchere,montant_enchere,no_article,no_utilisateur FROM ENCHERES";

	@Override
	public Enchere insert(Enchere enchere) throws EnchereDALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setDate(1, Date.valueOf(enchere.getDateEnchere()));
			stmt.setInt(2, enchere.getMontantEnchere());
			stmt.setInt(3, enchere.getNoArticle());
			stmt.setInt(4, enchere.getNoUtilisateur());
			int nbRows = stmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					enchere.setIdEnchere(rs.getInt(1));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			//throw new EnchereDALException("problème dans l'insertion d'une Enchere");
			//afaire
			System.out.println("problème dans l'insertion d'une Enchere");
		}
		return enchere;
	}

	@Override
	public List<Enchere> getAll() throws EnchereDALException {
		List<Enchere> result = new ArrayList<Enchere>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Enchere enchere = new Enchere();
				enchere.setIdEnchere(rs.getInt("no_enchere"));
				enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
				enchere.setMontantEnchere(rs.getInt("montant_enchere"));
				enchere.setNoArticle(rs.getInt("no_article"));
				enchere.setNoUtilisateur(rs.getInt("no_utilisateur"));
				result.add(enchere);
			}
		} catch (Exception e) {
			//throw new EnchereDALException("problème dans la selection des Encheres");
			//afaire
			System.out.println("problème dans la selection des Encheres");
		}
		return result;
	}
}
