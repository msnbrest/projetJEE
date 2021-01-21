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
	ModelLogin modelLogin = new ModelLogin();

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
		
		if(request.getParameter("identifiant") != null && !request.getParameter("identifiant").toString().equals("")){
			// TODO : retenir l'id dans session attribute
			
			System.out.println(request.getParameter("identifiant"));
			
			try {
				Utilisateur utilisateur = new Utilisateur();
				utilisateur = managerBLL.getUserByIdentifiant(request.getParameter("identifiant"));
				request.getSession().setAttribute("login", request.getParameter("identifiant"));
				request.getSession().setAttribute("id", utilisateur.getNoUtilisateur());
				request.getRequestDispatcher("/index").forward(request, response);
			} catch (UtilisateurBLLException e) {
				request.getSession().setAttribute("message", "Problème de connexion : "+e.getMessage());
				request.getRequestDispatcher("login.jsp").forward(request, response);
				request.getSession().setAttribute("message", "");
			}
		}else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
