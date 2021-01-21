package fr.eni.eniEncheres.IHM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eniEncheres.BLL.IUtilisateurManager;
import fr.eni.eniEncheres.BLL.UtilisateurBLLException;
import fr.eni.eniEncheres.BLL.UtilisateurSingleton;
import fr.eni.eniEncheres.BO.Utilisateur;

/**
 * Servlet implementation class ModifierProfil
 */
@WebServlet("/monProfil")
public class ModifierProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUtilisateurManager managerBLL = UtilisateurSingleton.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifierProfilServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProfilUtilisateurModel model = new ProfilUtilisateurModel();
		Utilisateur utilisateur = new Utilisateur();

		if (request.getSession().getAttribute("login") == null) {

			// dsl, pas co
			request.getRequestDispatcher("/index").forward(request, response);

		} else {

			if (request.getParameter("pseudo") == null) {

				// return infos à modif pour client
				try {
					utilisateur = managerBLL
							.getUserByIdentifiant(request.getSession().getAttribute("login").toString());
				} catch (UtilisateurBLLException e) {
					// TODO : afficher erreur html
				}

			} else {

				// maj ou suppr
				if (request.getParameter("sender") == null || request.getParameter("sender").equals("Enregistrer")) {

					// modification utilisateur, puis update sql
					utilisateur.setNoUtilisateur(Integer.parseInt(request.getSession().getAttribute("id").toString()));
					utilisateur.setPseudo(request.getParameter("pseudo"));
					utilisateur.setNom(request.getParameter("nom"));
					utilisateur.setPrenom(request.getParameter("prenom"));
					utilisateur.setEmail(request.getParameter("email"));
					utilisateur.setTelephone(request.getParameter("telephone"));
					utilisateur.setRue(request.getParameter("rue"));
					utilisateur.setCodePostal(request.getParameter("codepostal"));
					utilisateur.setVille(request.getParameter("ville"));
					utilisateur.setMotDePasse((request.getParameter("motdepasse")));
					utilisateur.setCredit(100);
					utilisateur.setAdministrateur(false);

					// TODO : plus tard, if mdpActuel equals sql mdp
					if (request.getParameter("mdpNouveau").equals(request.getParameter("mdpConfirmation"))) {
						try {
							managerBLL.updateUser(utilisateur);
						} catch (UtilisateurBLLException e) {
							model.setMessage(e.getMessage());
						}
					} else {
						utilisateur.setMotDePasse("");
						model.setMessage("Erreur, mots de passe différent");
					}

				} else if (request.getParameter("sender") != null
						&& request.getParameter("sender").equals("Supprimer mon compte")) {

					try {
						managerBLL.deleteUser(utilisateur.getNoUtilisateur());
					} catch (UtilisateurBLLException e) {
						model.setMessage(e.getMessage());
					}
				}
			}

		}

		model.setUtilisateur(utilisateur);
		request.getSession().setAttribute("model", model);
		request.getRequestDispatcher("monProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
