package fr.eni.eniEncheres.IHM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eniEncheres.BLL.ArticleVenduManagerException;
import fr.eni.eniEncheres.BLL.ArticleVenduManagerSing;
import fr.eni.eniEncheres.BLL.EnchereSingleton;
import fr.eni.eniEncheres.BLL.IArticleVenduManager;
import fr.eni.eniEncheres.BLL.IEnchereManager;
import fr.eni.eniEncheres.BO.ArticleVendu;

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
		ArticleVendu articleVendu = null;
		VoirObjModel model = new VoirObjModel();
		// TODO : si connécté
		// alors charger objet selon id reçu de requette html
		// sinon rediriger vers login
		

		if(request.getParameter("proposition") != null) {
			
		}
		//Recup de l'article vendu : par no_articleVendu
		try {
			articleVendu = managerArticleVendu.getArticleVenduByNoArticle(1);
			//managerEnchere.getEnchereByArticleId(noArticle);
		} catch (ArticleVenduManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.setArticleVendu(articleVendu);
		
		request.setAttribute("model", model);
		request.getRequestDispatcher("voir_obj.jsp").forward(request, response);
		// TODO : dans voir_obj.jsp coder offre minimale
		
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
