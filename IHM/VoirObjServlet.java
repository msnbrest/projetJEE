package fr.eni.eniEncheres.IHM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eniEncheres.BLL.ArticleVenduManagerException;
import fr.eni.eniEncheres.BLL.ArticleVenduManagerSing;
import fr.eni.eniEncheres.BLL.CategorieSingleton;
import fr.eni.eniEncheres.BLL.EnchereSingleton;
import fr.eni.eniEncheres.BLL.IArticleVenduManager;
import fr.eni.eniEncheres.BLL.ICategorieManager;
import fr.eni.eniEncheres.BLL.IEnchereManager;
import fr.eni.eniEncheres.BO.ArticleVendu;
import fr.eni.eniEncheres.BO.Categorie;

/**
 * Servlet implementation class name1Servlet
 */
@WebServlet("/objet")
public class VoirObjServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VoirObjServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		IArticleVenduManager managerArticleVendu = ArticleVenduManagerSing.getInstance();
		IEnchereManager managerEnchere = EnchereSingleton.getInstance();
		ArticleVendu article = new ArticleVendu();
		VoirObjModel model = new VoirObjModel();

		// alors charger objet selon id reçu de requette html
		// sinon rediriger vers login

		if (request.getSession().getAttribute("login") == null) {

			// dsl pas co
			request.getSession().setAttribute("message", "Connectez-vous pour voir cet article");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			request.getSession().setAttribute("message", "");

		} else {

			// Recup de l'article vendu par no_articleVendu
			try {
				article = managerArticleVendu.getArticleVenduByNoArticle(Integer.parseInt(request.getParameter("lot")));
				// TODO : ceci
				// managerEnchere.getEnchereByArticleId(noArticle);

				if (request.getParameter("proposition") != null) {
					// TODO : reçoit formulaire donc maj si prix>necessaire et créé enchere
				}

			} catch (NumberFormatException | ArticleVenduManagerException e) {
				model.setMessage("Erreur lors du chargement de l'article");
			}

			model.setArticle(article);
			request.setAttribute("model", model);
			request.getRequestDispatcher("voir_obj.jsp").forward(request, response);
			// TODO : dans voir_obj.jsp coder offre minimale

		}

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
