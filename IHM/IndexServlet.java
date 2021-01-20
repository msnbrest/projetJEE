package fr.eni.eniEncheres.IHM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eniEncheres.BLL.ArticleVenduManagerException;
import fr.eni.eniEncheres.BLL.ArticleVenduManagerSing;
import fr.eni.eniEncheres.BLL.IArticleVenduManager;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
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
		IndexModel model = new IndexModel();

		if (request.getParameter("rechercheNom") != null) {
			// has formulaire ? go !
			
			if (!request.getParameter("noCategorie").equals("") && request.getParameter("rechercheNom").equals("")) {
//que no et nom
			} else if (!request.getParameter("noCategorie").equals("") && !request.getParameter("rechercheNom").equals("")) {
				
				try {
					model.setLstArticleVendu(managerArticleVendu.getArticleVendu(request.getParameter("rechercheNom"),
							Integer.parseInt(request.getParameter("noCategorie"))));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ArticleVenduManagerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
		} else {
			try {
				model.setLstArticleVendu(managerArticleVendu.getArticleVendu());
				System.out.println(model.getLstArticleVendu());
			} catch (ArticleVenduManagerException e) {
				e.printStackTrace();
			}
		}

		request.setAttribute("model", model);
		request.getRequestDispatcher("index.jsp").forward(request, response);
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
