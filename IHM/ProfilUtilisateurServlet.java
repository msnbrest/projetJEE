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
 * Servlet implementation class ProfilUtilisateurServlet
 */
@WebServlet("/profil")
public class ProfilUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUtilisateurManager managerBLL = UtilisateurSingleton.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfilUtilisateurServlet() {
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

		String data = "";
		Utilisateur utilisateur = new Utilisateur();

		if (request.getSession().getAttribute("login") != null) {
			if (request.getParameter("name") != null && !request.getParameter("name").equals("")) {
				// if ((request.getSession().getAttribute("login") != null) &&
				// (!(request.getSession().getAttribute("login").toString().equals("")))) {
				// data = request.getSession().getAttribute("login").toString();
				// TODO : nettoyer le code commenté? c'est caca
				data = request.getParameter("name");

				model.setMessage("Profil de " + data);
			} else {
				data = request.getSession().getAttribute("login").toString();
				model.setMessage("Mon profil");
				model.setIsme(true);
			}
		}

		if (!data.equals("")) {
			try {
				utilisateur = managerBLL.getUserByIdentifiant(data);
			} catch (UtilisateurBLLException e) {
				e.printStackTrace();
				// TODO : afficher erreur html
			}
		}

		model.setUtilisateur(utilisateur);
		request.setAttribute("model", model);
		request.getRequestDispatcher("afficheProfil.jsp").forward(request, response);

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
