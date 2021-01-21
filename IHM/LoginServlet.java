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

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUtilisateurManager managerBLL = UtilisateurSingleton.getInstance();
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
		
		String identifiant = request.getParameter("identifiant");
		String mdp = request.getParameter("mdp");
	

		try {
			
			if ((managerBLL.sauthentifier(identifiant, mdp))) {
				
				request.getSession().setAttribute("login", request.getParameter("identifiant"));
				
				request.getSession().setAttribute("numUtilisateur",
						managerBLL.getUserByIdentifiant(identifiant).getNoUtilisateur());
				
				request.getRequestDispatcher("/Encheres").forward(request, response);
				
			}

			if (!(managerBLL.sauthentifier(identifiant, mdp))) {
				
				modelLogin.setMessage("Veuillez vous identifier");
				request.setAttribute("modelLogin", modelLogin);
			}
			request.getRequestDispatcher("login.jsp").forward(request, response);

		} catch (UtilisateurBLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
