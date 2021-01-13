package fr.eni.eniEncheres.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.eniEncheres.BO.Utilisateur;

public class UtilisateurDaoImpl implements IUtilisateurDao {

	@Override
	public Utilisateur create(Utilisateur utilisateur) throws UtilisateurDaoException {

		try {
			Connection conx = ConnectionProvider.getConnection();
			PreparedStatement req = conx.prepareStatement("INSERT INTO UTILISATEURS VALUES (?,?,?,?,?,?,?,?,?,?,?) ");
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
			req.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new UtilisateurDaoException("probleme lors de l insertion");
		}
		// TODO: Retour de produit

		return utilisateur;
	}

	@Override
	public List<Utilisateur> getAllUsers() throws UtilisateurDaoException {

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
			throw new UtilisateurDaoException("probleme lors de la selection de la liste des utilisateurs");

		}

		return users;
	}

	@Override
	public void deleteUser(Integer id) throws UtilisateurDaoException {

		try {
			Connection conx = ConnectionProvider.getConnection();
			PreparedStatement res = conx.prepareStatement("DELETE * FROM UTILISATEURS WHERE noUtilisateur like ?");

		} catch (Exception e) {
//			e.printStackTrace();
			throw new UtilisateurDaoException(e.getMessage());

		}

	}

}