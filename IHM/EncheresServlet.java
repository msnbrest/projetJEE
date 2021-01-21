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
 * Servlet implementation class EncheresServlet
 */
@WebServlet("/Encheres")
public class EncheresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EncheresModel model = new EncheresModel();
	Utilisateur utilisateur = null;
	IUtilisateurManager managerBll = UtilisateurSingleton.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EncheresServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String current = (String) request.getSession().getAttribute("login");
			utilisateur = managerBll.getUserByIdentifiant(current);
			model.setUtilisateur(utilisateur);
			model.setMessage("Bonjour");
		} catch (UtilisateurBLLException e) {

			e.printStackTrace();
		}
		
		request.setAttribute("modelEnchere", model);
		request.getRequestDispatcher("index.jsp").forward(request, response);
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
