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
@WebServlet("/RegisterServlet")
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

		if (request.getParameter("pseudo") != null) {

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

			Utilisateur user1 = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codepostal, ville,
					motdepasse, 0, false);

			try {
				manager.CreateUtilisteur(user1);
			} catch (UtilisateurBLLException e) {
				// throw new Exception
				// TODO : dire à visiteur erreur inscription, verifier champs
			}
			request.getSession().setAttribute("login", request.getParameter("pseudo"));
			request.getRequestDispatcher("/EncheresServlet").forward(request, response);
			

		} else {

			request.getRequestDispatcher("/LoginServlet").forward(request, response);

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
