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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUtilisateurManager manager = UtilisateurSingleton.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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
		Boolean logged = false;

		if (request.getSession().getAttribute("login") == null) {

			if (request.getParameter("pseudo") != null && request.getParameter("sender").equals("Creer")) {
				// besoin des infos, sinon les demander

				// TODO : exist deja dans BDD pseudo???
				String pseudo = request.getParameter("pseudo");
				String prenom = request.getParameter("prenom");
				String telephone = request.getParameter("telephone");
				String codepostal = request.getParameter("codepostal");
				String motdepasse = request.getParameter("motdepasse");
				// TODO : CRYPTAGE!!!!!
				// TODO : erreur mdp different

				String nom = request.getParameter("nom");
				String email = request.getParameter("email");
				String rue = request.getParameter("rue");
				String ville = request.getParameter("ville");

				utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codepostal, ville, motdepasse,
						0, false);

				// TODO : filtrer si cases remplies
				if (request.getParameter("motdepasse").equals(request.getParameter("confirmation"))) {

					try {
						manager.CreateUser(utilisateur);
						request.getSession().setAttribute("login", request.getParameter("pseudo"));
						request.getSession().setAttribute("id", utilisateur.getNoUtilisateur());
						logged = true;
					} catch (UtilisateurBLLException e) {
						// TODO : dire à visiteur erreur inscription, verifier champs
					}
				} else {

					utilisateur.setMotDePasse("");
					model.setMessage("Erreur, mots de passe différent");
				}

				model.setUtilisateur(utilisateur);
				request.setAttribute("model", model);
			}

		} else {
			// dsl, déjà co
			logged = true;
		}

		if (logged) {
			request.getRequestDispatcher("/index").forward(request, response);
		} else {
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
