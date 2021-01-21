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
	public Utilisateur createUser(Utilisateur utilisateur) throws UtilisateurDAOException {

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
			throw new UtilisateurDAOException("Erreur, insertion impossible (" + e.getMessage() + ")");
		}

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
			throw new UtilisateurDAOException(
					"Erreur, Selection impossible de la liste des utilisateurs (" + e.getMessage() + ")");
		}

		return users;
	}

	@Override
	public List<Utilisateur> getUsersByName(String name) throws UtilisateurDAOException {
		List<Utilisateur> users = new ArrayList<Utilisateur>();

		try {
			Connection conx = ConnectionProvider.getConnection();
			PreparedStatement req = conx.prepareStatement("SELECT * FROM UTILISATEURS WHERE NOM like ?");
			req.setString(1, "%" + name + "%");
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
			throw new UtilisateurDAOException("Erreur, Selection impossible d'un utilisateur (" + e.getMessage() + ")");
		}
		return users;
	}

	@Override
	public Utilisateur getUserById(Integer id) throws UtilisateurDAOException {

		Utilisateur user = new Utilisateur();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement req = cnx.prepareStatement("SELECT * FROM UTILISATEURS WHERE no_utilisateur like ?");
			req.setInt(1, id);
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
			}
		} catch (SQLException e) {
			throw new UtilisateurDAOException("Erreur, Selection impossible d'un utilisateur (" + e.getMessage() + ")");
		}

		return user;
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
			throw new UtilisateurDAOException("Erreur, Selection impossible d'un utilisateur (" + e.getMessage() + ")");
		}

		return user;
	}

	@Override
	public Utilisateur updateUser(Utilisateur utilisateur) throws UtilisateurDAOException {

		if (utilisateur.getNoUtilisateur() != null) {
			try {
				Connection conx = ConnectionProvider.getConnection();
				PreparedStatement req = conx.prepareStatement(
						"UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=?, credit=?, administrateur=? WHERE no_utilisateur like ? ");
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
				req.executeUpdate();

			} catch (Exception e) {
				throw new UtilisateurDAOException(
						"Erreur, Selection impossible d'un utilisateur (" + e.getMessage() + ")");
			}
		} else {
			throw new UtilisateurDAOException("Erreur, Mise à jour impossible d'un utilisateur (Id introuvable)");
		}

		return utilisateur;
	}

	@Override
	public boolean deleteUser(Integer id) throws UtilisateurDAOException {

		try {
			//TODO : MERDE
			id=999;
			Connection conx = ConnectionProvider.getConnection();
			PreparedStatement req = conx.prepareStatement("DELETE FROM UTILISATEURS WHERE no_utilisateur like ?");
			req.setInt(1, id);
			req.executeUpdate();
		} catch (Exception e) {
			throw new UtilisateurDAOException("Erreur, Suppression impossible d'un utilisateur (" + e.getMessage() + ")");
		}

		return true;

	}

}
