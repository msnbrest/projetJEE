package fr.eni.eniEncheres.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.eniEncheres.BO.ArticleVendu;
import fr.eni.eniEncheres.BO.Categorie;
import fr.eni.eniEncheres.BO.Utilisateur;


public class ArticleVenduImpl implements IArticleVenduDAO {

	// Requete SQL Insert ne possï¿½de pas de no_article
	private String INSERT = "INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie, article_accessible) VALUES (?,?,?,?,?,?,?,?, ?)";
	private String SELECT = "Select * From ARTICLES_VENDUS";
	private String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE no_article like ?";
	private String UPDATE = "";// TODO Delete then Insert or update?

	@Override
	public ArticleVendu insert(ArticleVendu article) {
		try (Connection cnx = ConnectionProvider.getConnection();) {
			PreparedStatement stmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, article.getNomArticle());
			stmt.setString(2, article.getDescription());
			stmt.setDate(3, java.sql.Date.valueOf(article.getDateDebutEncheres()));
			stmt.setDate(4, java.sql.Date.valueOf(article.getDateFinEncheres()));
			stmt.setInt(5, article.getMiseAPrix());
			stmt.setInt(6, article.getPrixVente());
			// stmt.setInt(7, article.getUtilisateur().getNoUtilisateur());
			stmt.setInt(7, 20);
			stmt.setInt(8, 3);
			stmt.setBoolean(9, article.getEtatVente());
			Integer nbRows = stmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					article.setNoArticle(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return article;
	}
	
	public void delete(ArticleVendu article) throws EnchereDALException{
		try {
			Connection conx = ConnectionProvider.getConnection();
			PreparedStatement req = conx.prepareStatement(DELETE);
			req.setInt(1, article.getNoArticle());
			req.executeUpdate();

			} catch (Exception e) {
			// e.printStackTrace();
			throw new EnchereDALException("Probleme au niveau de DAL pour suprimer un article");

			 }
	}

	@Override
	public ArticleVendu update(Integer a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleVendu> getAll() throws EnchereDALException{
		List<ArticleVendu> result = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArticleVendu article = new ArticleVendu();
				article.setNoArticle(rs.getInt("no_article"));
				article.setNomArticle(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				//TODO choisir date et heure
				article.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				article.setMiseAPrix(rs.getInt("prix_initial"));
				article.setPrixVente(rs.getInt("prix_vente"));
				//TODO Enlever l'objet utilisateur/Categorie dans la bo ou réaliser une requete sql plus longue? Constructeur sup?
				article.setUtilisateur(new Utilisateur());
				//libelle fixé à ""
				article.setCategorieArticleVendu(new Categorie(rs.getInt("no_categorie"), ""));
				article.setArticleAccessible(rs.getBoolean("article_accessible"));
				result.add(article);
			}
		} catch (Exception e) {
			throw new EnchereDALException("problème dans la selection des articles");
		}
		return result;
	}
	

	@Override
	public List<ArticleVendu> getListeArticleVenduUser(String researchNameItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleVendu> getListeArticleVenduUser(String researchNameItem, Categorie noCat) {
		// TODO Auto-generated method stub
		return null;
	}

}
