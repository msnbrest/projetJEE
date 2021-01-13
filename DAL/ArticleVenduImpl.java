package fr.eni.eniEncheres.DAL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.eniEncheres.BO.ArticleVendu;


public class ArticleVenduImpl implements IArticleVenduDAO {

	//Requete SQL Insert ne poss�de pas de no_article
	private String INSERT = "INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?,?)";
	
	@Override
	public ArticleVendu insert(ArticleVendu article) {
		try (Connection cnx = ConnectionProvider.getConnection();){
			PreparedStatement stmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, article.getNomArticle());
			stmt.setString(2, article.getDescription());
		
			stmt.setDate(3, Date.valueOf(article.getDateDebutEncheres()));
			stmt.setDate(4, Date.valueOf(article.getDateFinEncheres()));
			stmt.setInt(5, article.getMiseAPrix());
			stmt.setInt(6, article.getPrixVente());
			//TODO Fixer les todo dans la BO
			stmt.setInt(7, 7);
			//TODO Fixer les todo dans la BO
			stmt.setInt(8, 7);
			
			Integer nbRows = stmt.executeUpdate();
			if(nbRows == 1)
			{
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

}
