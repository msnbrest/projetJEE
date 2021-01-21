package fr.eni.eniEncheres.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.eniEncheres.BO.Utilisateur;

public class UtilisateurDAOImpl implements IUtilisateurDAO {

	@Override
	public Utilisateur create(Utilisateur utilisateur) throws UtilisateurDAOException {

		try {
			Connection conx = ConnectionProvider.getConnection();
			PreparedStatement req = conx.prepareStatement("INSERT INTO UTILISATEURS VALUES (?,?,?,?,?,?,?,?,?,?,?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);
			req.setString(1, utilisateur.getPseudo());
			req.setString(2, utilisateur.getNom());
			req.setString(3, utilisateur.getPrenom());
			req.setString(4, utilisateur.getEmail());
			req.setString(5, utilisateur.getTelephone());
			req.setString(6, utilisateur.getRue());
			req.setString(7, utilisateur.getCodePostal());
			req.setString(8, utilisateur.getVille());
			req.setString(9, utilisateur.getMotDePasse());
			req.setInt(10, utilisateur.getCredit());
			req.setBoolean(11, utilisateur.getAdministrateur());
			int nbRows = req.executeUpdate();

			if (nbRows == 1) {
				ResultSet rs = req.getGeneratedKeys();
				if (rs.next()) {
					utilisateur.setNoUtilisateur(rs.getInt(1));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new UtilisateurDAOException("probleme lors de l insertion");
		}
		// TODO: Retour de produit

		return utilisateur;
	}

	@Override
	public List<Utilisateur> getAllUsers() throws UtilisateurDAOException {

		List<Utilisateur> users = new ArrayList<Utilisateur>();

		try {
			Connection conx = ConnectionProvider.getConnection();
			PreparedStatement req = conx.prepareStatement("SELECT * FROM UTILISATEURS ");
			ResultSet res = req.executeQuery();
			while (res.next()) {

				Utilisateur u = new Utilisateur();
				u.setNoUtilisateur(res.getInt("no_utilisateur"));
				u.setPseudo(res.getString("pseudo"));
				u.setNom(res.getString("nom"));
				u.setPrenom(res.getString("prenom"));
				u.setEmail(res.getString("email"));
				u.setTelephone(res.getString("telephone"));
				u.setRue(res.getString("rue"));
				u.setCodePostal(res.getString("code_postal"));
				u.setVille(res.getString("ville"));
				u.setMotDePasse(res.getString("mot_de_passe"));
				u.setCredit(res.getInt("credit"));
				users.add(u);

			}

		} catch (Exception e) {
//			e.printStackTrace();
			throw new UtilisateurDAOException("probleme lors de la selection de la liste des utilisateurs");

		}

		return users;
	}

	@Override
	public void deleteUser(Integer id) throws UtilisateurDAOException {

		try {
			Connection conx = ConnectionProvider.getConnection();
			PreparedStatement req = conx.prepareStatement("DELETE  FROM UTILISATEURS WHERE no_utilisateur like ?");
			req.setInt(1, id);
			req.executeUpdate();

		} catch (Exception e) {
//			e.printStackTrace();
			throw new UtilisateurDAOException(e.getMessage());

		}

	}

	@Override
	public List<Utilisateur> getUserByMC(String mc) throws UtilisateurDAOException {
		List<Utilisateur> users = new ArrayList<Utilisateur>();

		try {
			Connection conx = ConnectionProvider.getConnection();
			PreparedStatement req = conx.prepareStatement("SELECT * FROM UTILISATEURS WHERE NOM LIKE ?");
			req.setString(1, "%" + mc + "%");
			ResultSet res = req.executeQuery();

			while (res.next()) {

				Utilisateur u = new Utilisateur();
				u.setNoUtilisateur(res.getInt("no_utilisateur"));
				u.setPseudo(res.getString("pseudo"));
				u.setNom(res.getString("nom"));
				u.setPrenom(res.getString("prenom"));
				u.setEmail(res.getString("email"));
				u.setTelephone(res.getString("telephone"));
				u.setRue(res.getString("rue"));
				u.setCodePostal(res.getString("code_postal"));
				u.setVille(res.getString("ville"));
				u.setMotDePasse(res.getString("mot_de_passe"));
				u.setCredit(res.getInt("credit"));
				users.add(u);

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new UtilisateurDAOException(e.getMessage());

		}
		return users;
	}

	@Override
	public Utilisateur updateUser(Utilisateur utilisateur) throws UtilisateurDAOException {
		try {
			Connection conx = ConnectionProvider.getConnection();
			PreparedStatement req = conx.prepareStatement(
					"UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=?, credit=?, administrateur=? WHERE no_utilisateur=? ");
			req.setString(1, utilisateur.getPseudo());
			req.setString(2, utilisateur.getNom());
			req.setString(3, utilisateur.getPrenom());
			req.setString(4, utilisateur.getEmail());
			req.setString(5, utilisateur.getTelephone());
			req.setString(6, utilisateur.getRue());
			req.setString(7, utilisateur.getCodePostal());
			req.setString(8, utilisateur.getVille());
			req.setString(9, utilisateur.getMotDePasse());
			req.setInt(10, utilisateur.getCredit());
			req.setBoolean(11, utilisateur.getAdministrateur());
			req.setInt(12, utilisateur.getNoUtilisateur());

			if (utilisateur.getNoUtilisateur() != null) {
				req.executeUpdate();
			} else {
				throw new UtilisateurDAOException("probleme lors de l insertion");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new UtilisateurDAOException("probleme lors de l insertion");
		}
		// TODO: Retour de produit

		return utilisateur;
	}

	@Override
	public Utilisateur getUserById(Integer id) throws UtilisateurDAOException {
		Utilisateur u = null;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement req = cnx.prepareStatement("SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?");
			req.setInt(1, id);
			ResultSet res = req.executeQuery();

			if (res.next()) {
				u = new Utilisateur();
				u.setNoUtilisateur(res.getInt("no_utilisateur"));
				u.setPseudo(res.getString("pseudo"));
				u.setNom(res.getString("nom"));
				u.setPrenom(res.getString("prenom"));
				u.setEmail(res.getString("email"));
				u.setTelephone(res.getString("telephone"));
				u.setRue(res.getString("rue"));
				u.setCodePostal(res.getString("code_postal"));
				u.setVille(res.getString("ville"));
				u.setMotDePasse(res.getString("mot_de_passe"));
				u.setCredit(res.getInt("credit"));
			}
		} catch (SQLException e) {
//	e.printStackTrace();
			throw new UtilisateurDAOException("probleme lors de l appel de l utisateur");
		}
		return u;
	}

	@Override
	public Utilisateur getUserByIdentifiant(String identifiant) throws UtilisateurDAOException {

		Utilisateur user = new Utilisateur();

		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement req = cnx
					.prepareStatement("SELECT * FROM UTILISATEURS WHERE pseudo like ? OR email like ?");
			req.setString(1, identifiant);
			req.setString(2, identifiant);
			ResultSet res = req.executeQuery();

			if (res.next()) {
				user.setNoUtilisateur(res.getInt("no_utilisateur"));
				user.setPseudo(res.getString("pseudo"));
				user.setNom(res.getString("nom"));
				user.setPrenom(res.getString("prenom"));
				user.setEmail(res.getString("email"));
				user.setTelephone(res.getString("telephone"));
				user.setRue(res.getString("rue"));
				user.setCodePostal(res.getString("code_postal"));
				user.setVille(res.getString("ville"));
				user.setMotDePasse(res.getString("mot_de_passe"));
				user.setCredit(res.getInt("credit"));
				user.setAdministrateur(res.getBoolean("administrateur"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new UtilisateurDAOException("probleme lors de l appel de l utisateur");
		}
		return user;

	}

	@Override
	public  Boolean  sauthentifier(String identifiant, String mdp) throws UtilisateurDAOException {
		boolean status = false; 
//		Utilisateur user = new Utilisateur();

		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement req = cnx
					.prepareStatement("SELECT * FROM UTILISATEURS WHERE (pseudo=? AND mot_de_passe= ?) OR (email = ? AND mot_de_passe= ?) ");
			req.setString(1, identifiant);
			req.setString(2, mdp);
			req.setString(3, identifiant);
			req.setString(4, mdp);
			ResultSet res = req.executeQuery();
			
			status = res.next();

//			if (res.next()) {
//				user.setNoUtilisateur(res.getInt("no_utilisateur"));
//				user.setPseudo(res.getString("pseudo"));
//				user.setNom(res.getString("nom"));
//				user.setPrenom(res.getString("prenom"));
//				user.setEmail(res.getString("email"));
//				user.setTelephone(res.getString("telephone"));
//				user.setRue(res.getString("rue"));
//				user.setCodePostal(res.getString("code_postal"));
//				user.setVille(res.getString("ville"));
//				user.setMotDePasse(res.getString("mot_de_passe"));
//				user.setCredit(res.getInt("credit"));
//				user.setAdministrateur(res.getBoolean("administrateur"));
//			}
//			else
//			{
//				System.out.println("erreur Login");
//			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new UtilisateurDAOException("probleme lors de l appel de l utisateur");
		}
		return status;
	}

}
