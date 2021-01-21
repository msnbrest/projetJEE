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
import fr.eni.eniEncheres.BO.Enchere;
import fr.eni.eniEncheres.BO.Utilisateur;

public class ArticleVenduImpl implements IArticleVenduDAO {

	// Requete SQL Insert ne posséde pas de no_article
	private String INSERT = "INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?,?)";
	// private String SELECT_BY_NOM_ARTICLE_NO_CATEGORIE = "Select * From
	// ARTICLES_VENDUS Where date_fin_encheres >= nom_article like ? AND
	// no_categorie = ?";
	// private String SELECT_BY_NOM_ARTICLE = "Select * From ARTICLES_VENDUS Where
	// date_fin_encheres >= nom_article like ?";
	// private String SELECT_BY_NO_CATEGORIE = "Select * From ARTICLES_VENDUS Where
	// date_fin_encheres >= no_categorie like ?";
	private String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE no_article like ?";
	private String UPDATE_PRIX_VENTE = "UPDATE ARTICLES_VENDUS SET prix_vente = ? where no_article like ?";
	private String UPDATE_ARTICLE_VENDU = "UPDATE ARTICLES_VENDUS SET nom_article  = ?,description  = ?,date_debut_encheres  = ?,date_fin_encheres  = ?,"
			+ "prix_initial  = ?,prix_vente  = ?, no_utilisateur  = ?,no_categorie  = ? where no_article like ?";
	private String SELECT_WHEN_DISCONNECTED = "select DISTINCT a.no_article, nom_article, date_fin_encheres, a.no_categorie, a.no_utilisateur, pseudo, prix_vente from ARTICLES_VENDUS as a "
			+ "INNER join CATEGORIES c on c.no_categorie = a.no_categorie "
			+ "INNER join UTILISATEURS u on u.no_utilisateur = a.no_utilisateur "
			+ "INNER join ENCHERES e on e.no_article = a.no_article "
			+ "where date_debut_encheres <= getdate() AND date_fin_encheres >= GETDATE() ORDER by date_fin_encheres";
	private String SELECT_BY_NOM_ARTICLE = "select DISTINCT a.no_article, nom_article, date_fin_encheres, a.no_categorie, a.no_utilisateur, pseudo, prix_vente from ARTICLES_VENDUS as a "
			+ "INNER join CATEGORIES c on c.no_categorie = a.no_categorie "
			+ "INNER join UTILISATEURS u on u.no_utilisateur = a.no_utilisateur "
			+ "INNER join ENCHERES e on e.no_article = a.no_article "
			+ "where date_debut_encheres <= getdate() AND date_fin_encheres >= GETDATE() AND nom_article like ? ORDER by date_fin_encheres";
	private String SELECT_BY_NO_CATEGORIE = "select DISTINCT a.no_article, nom_article, date_fin_encheres, a.no_categorie, a.no_utilisateur, pseudo, prix_vente from ARTICLES_VENDUS as a "
			+ "INNER join CATEGORIES c on c.no_categorie = a.no_categorie "
			+ "INNER join UTILISATEURS u on u.no_utilisateur = a.no_utilisateur "
			+ "INNER join ENCHERES e on e.no_article = a.no_article "
			+ "where date_debut_encheres <= getdate() AND date_fin_encheres >= GETDATE() AND a.no_categorie like ? ORDER by date_fin_encheres";
	private String SELECT_BY_NOM_ARTICLE_NO_CATEGORIE = "select DISTINCT a.no_article, nom_article, date_fin_encheres, a.no_categorie, a.no_utilisateur, pseudo, prix_vente from ARTICLES_VENDUS as a "
			+ "INNER join CATEGORIES c on c.no_categorie = a.no_categorie "
			+ "INNER join UTILISATEURS u on u.no_utilisateur = a.no_utilisateur "
			+ "INNER join ENCHERES e on e.no_article = a.no_article "
			+ "where date_debut_encheres <= getdate() AND date_fin_encheres >= GETDATE() AND a.no_categorie like ? AND nom_article like ? ORDER by date_fin_encheres";

	// Réalise l'insertion del'article vendu puis celle du lieu de retrait en
	// utilisant l'IRetraitDAO
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
			stmt.setInt(7, article.getUtilisateur().getNoUtilisateur());
			stmt.setInt(8, article.getCategorieArticleVendu().getNoCategorie());
			Integer nbRows = stmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					article.setNoArticle(rs.getInt(1));
					article.getLieuRetrait().setNoArticle(article.getNoArticle());
					;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		// Insertion du lieu de retrait qui intervient après l'insertion de
		// l'articleVendu
		IRetraitDAO daoRetrait = DAOFact.getRetraitDAO();
		try {
			article.setLieuRetrait(daoRetrait.insertRetrait(article.getLieuRetrait()));
		} catch (RetraitDALException e) {

		}
		return article;
	}

	@Override
	public boolean delete(ArticleVendu article) throws EnchereDALException {
		
		try (Connection conx = ConnectionProvider.getConnection()){
			PreparedStatement req = conx.prepareStatement(DELETE);
			req.setInt(1, article.getNoArticle());
			req.executeUpdate();

			IRetraitDAO daoRetrait = DAOFact.getRetraitDAO();
			try {
				daoRetrait.deleteRetrait(article.getNoArticle());
			} catch (RetraitDALException e) {
				throw new EnchereDALException(
						"Erreur dans le delete du lieu de retrait, arrivé au niveau de la classe articleVendu ("
								+ e.getMessage() + ")");
			}
		} catch (Exception e) {
			throw new EnchereDALException("Problème pour supprimer un article (" + e.getMessage() + ")");
		}
		
		return true;
	}

	@Override
	public void update(Enchere enchere) throws EnchereDALException {

		try {
			Connection conx = ConnectionProvider.getConnection();
			PreparedStatement req = conx.prepareStatement(UPDATE_PRIX_VENTE);
			req.setInt(1, enchere.getMontantEnchere());
			req.setInt(2, enchere.getIdEnchere());
			req.executeUpdate();
		} catch (SQLException e) {
			throw new EnchereDALException(
					"Problème de mise à jour du prix de vente d'un article (" + e.getMessage() + ")");
		}
	}

	@Override
	public ArticleVendu updateArticleVendu(ArticleVendu article) throws EnchereDALException {

		try (Connection cnx = ConnectionProvider.getConnection();) {
			PreparedStatement stmt = cnx.prepareStatement(UPDATE_ARTICLE_VENDU, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, article.getNomArticle());
			stmt.setString(2, article.getDescription());
			stmt.setDate(3, java.sql.Date.valueOf(article.getDateDebutEncheres()));
			stmt.setDate(4, java.sql.Date.valueOf(article.getDateFinEncheres()));
			stmt.setInt(5, article.getMiseAPrix());
			stmt.setInt(6, article.getPrixVente());
			stmt.setInt(7, article.getUtilisateur().getNoUtilisateur());
			stmt.setInt(8, article.getCategorieArticleVendu().getNoCategorie());
			stmt.setInt(9, article.getNoArticle());
			Integer nbRows = stmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					article.setNoArticle(rs.getInt(1));
					article.getLieuRetrait().setNoArticle(article.getNoArticle());
				}
			}
		} catch (SQLException e) {
			throw new EnchereDALException(
					"Problème de mise à jour du prix de vente d'un article (" + e.getMessage() + ")");
		}

		IRetraitDAO daoRetrait = DAOFact.getRetraitDAO();
		try {
			article.setLieuRetrait(daoRetrait.insertRetrait(article.getLieuRetrait()));
		} catch (RetraitDALException e) {

		}
		return article;
	}

	@Override
	public List<ArticleVendu> getAll() throws EnchereDALException {
		List<ArticleVendu> result = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_WHEN_DISCONNECTED);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArticleVendu article = new ArticleVendu();
				article.setNoArticle(rs.getInt("no_article"));
				article.setNomArticle(rs.getString("nom_article"));
//				article.setDescription(rs.getString("description"));
//				article.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
//				article.setMiseAPrix(rs.getInt("prix_initial"));
				article.setPrixVente(rs.getInt("prix_vente"));
				// TODO Enlever l'objet utilisateur/Categorie dans la bo ou rï¿½aliser une
				// requete sql plus longue? Constructeur sup?
				Utilisateur util = new Utilisateur();
				util.setNoUtilisateur(rs.getInt("no_Utilisateur"));
				util.setPseudo(rs.getString("pseudo"));
				article.setUtilisateur(util);
				article.setCategorieArticleVendu(new Categorie(rs.getInt("no_categorie"), ""));
				result.add(article);
			}
		} catch (Exception e) {
			throw new EnchereDALException("Problème dans la selection des articles (" + e.getMessage() + ")");
		}
		return result;
	}

	@Override
	public List<ArticleVendu> getAllByNomArticle(String nomArticle) throws EnchereDALException {
		List<ArticleVendu> result = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_NOM_ARTICLE);
			String motRecherche = "%" + nomArticle + "%";
			stmt.setString(1, motRecherche);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArticleVendu article = new ArticleVendu();
				article.setNoArticle(rs.getInt("no_article"));
				article.setNomArticle(rs.getString("nom_article"));
//				article.setDescription(rs.getString("description"));
//				article.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
//				article.setMiseAPrix(rs.getInt("prix_initial"));
				article.setPrixVente(rs.getInt("prix_vente"));
				// TODO Enlever l'objet utilisateur/Categorie dans la bo ou rï¿½aliser une
				// requete sql plus longue? Constructeur sup?
				Utilisateur util = new Utilisateur();
				util.setNoUtilisateur(rs.getInt("no_Utilisateur"));
				util.setPseudo(rs.getString("pseudo"));
				article.setUtilisateur(util);
				article.setCategorieArticleVendu(new Categorie(rs.getInt("no_categorie"), ""));
				result.add(article);
			}
		} catch (Exception e) {
			throw new EnchereDALException("Problème dans la selection des articles par nom (" + e.getMessage() + ")");
		}
		return result;
	}

	@Override
	public List<ArticleVendu> getAllByNomArticleAndNoCategorie(String nomArticle, Integer noCategorie)
			throws EnchereDALException {
		List<ArticleVendu> result = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_NOM_ARTICLE_NO_CATEGORIE);
			String motRecherche = "%" + nomArticle + "%";
			stmt.setInt(1, noCategorie);
			stmt.setString(2, motRecherche);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArticleVendu article = new ArticleVendu();
				article.setNoArticle(rs.getInt("no_article"));
				article.setNomArticle(rs.getString("nom_article"));
//				article.setDescription(rs.getString("description"));
//				article.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
//				article.setMiseAPrix(rs.getInt("prix_initial"));
				article.setPrixVente(rs.getInt("prix_vente"));
				// TODO Enlever l'objet utilisateur/Categorie dans la bo ou rï¿½aliser une
				// requete sql plus longue? Constructeur sup?
				Utilisateur util = new Utilisateur();
				util.setNoUtilisateur(rs.getInt("no_Utilisateur"));
				util.setPseudo(rs.getString("pseudo"));
				article.setUtilisateur(util);
				article.setCategorieArticleVendu(new Categorie(rs.getInt("no_categorie"), ""));
				result.add(article);
			}
		} catch (Exception e) {
			throw new EnchereDALException(
					"Problème dans la selection des articles par nom et catégorie (" + e.getMessage() + ")");
		}
		return result;
	}

	@Override
	public List<ArticleVendu> getAllByNoCategorie(Integer noCategorie) throws EnchereDALException {
		List<ArticleVendu> result = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_NO_CATEGORIE);
			stmt.setInt(1, noCategorie);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArticleVendu article = new ArticleVendu();
				article.setNoArticle(rs.getInt("no_article"));
				article.setNomArticle(rs.getString("nom_article"));
//				article.setDescription(rs.getString("description"));
//				article.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
//				article.setMiseAPrix(rs.getInt("prix_initial"));
				article.setPrixVente(rs.getInt("prix_vente"));
				// TODO Enlever l'objet utilisateur/Categorie dans la bo ou rï¿½aliser une
				// requete sql plus longue? Constructeur sup?
				Utilisateur util = new Utilisateur();
				util.setNoUtilisateur(rs.getInt("no_Utilisateur"));
				util.setPseudo(rs.getString("pseudo"));
				article.setUtilisateur(util);
				article.setCategorieArticleVendu(new Categorie(rs.getInt("no_categorie"), ""));
				result.add(article);
			}
		} catch (Exception e) {
			throw new EnchereDALException("Problème dans la selection des articles par nom (" + e.getMessage() + ")");
		}
		return result;
	}

	@Override
	public ArticleVendu getByID(Integer noArticle) throws EnchereDALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleVendu> getAllByNoUtilisateurBeforeSale(Integer noUtilisateur) throws EnchereDALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleVendu> getAllByNoUtilisateurInSale(Integer noUtilisateur) throws EnchereDALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleVendu> getAllByNoUtilisateurAfterSale(Integer noUtilisateur) throws EnchereDALException {
		// TODO Auto-generated method stub
		return null;
	}

}
