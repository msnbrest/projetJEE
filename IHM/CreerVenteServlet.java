package fr.eni.eniEncheres.IHM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class name1Servlet
 */
@WebServlet("/creer_vente")
public class CreerVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreerVenteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession().getAttribute("login") == null) {

			// dsl, pas co
			request.getRequestDispatcher("/index").forward(request, response);

		} else {

			// xxxx
			CreerVenteModel model = new CreerVenteModel();
			Boolean continuer = true;

			if (request.getParameter("sender") == null) {
				// ne rien faire
			} else if (request.getParameter("sender").equals("Enregistrer")) {
				// save to article
			} else if (request.getParameter("sender").equals("Annuler")) {

				request.getRequestDispatcher("/index").forward(request, response);
				continuer = false;
				
			} else if (request.getParameter("sender").equals("Annuler la vente")) {
				// delete article
			}

			if (continuer) {
				// si formulaire contient adresse autre que adresse client, alors ajouter un
				// retrait, sinon osef car adresse déjà client

				// xxxx
				request.setAttribute("model", model);
				request.getRequestDispatcher("creer_vente.jsp").forward(request, response);
				// TODO : creer_vente.jsp coder model.article.no pour annuler la vente.
			}

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
