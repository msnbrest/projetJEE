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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IUtilisateurManager managerBLL = UtilisateurSingleton.getInstance();
		
		if(request.getParameter("identifiant") != null){
			// TODO : retenir l'id dans session attribute
			request.getSession().setAttribute("login", request.getParameter("identifiant"));
			
			Utilisateur utilisateur = new Utilisateur();
			try {
				utilisateur = managerBLL.getUserByIdentifiant(request.getParameter("identifiant"));
			} catch (UtilisateurBLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getSession().setAttribute("numUtilisateur", utilisateur.getNoUtilisateur());
			request.getRequestDispatcher("/index").forward(request, response);
		}else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}
		/*
		 * TODO : TOUDOU recup infos, demander modele session, creer filtre verif mdp et
		 * pseudo exist et bon
		 */

//		request.getRequestDispatcher("login.jsp").forward(request, response);
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
