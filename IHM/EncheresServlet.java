package fr.eni.eniEncheres.IHM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EncheresServlet
 */
@WebServlet("/EncheresServlet")
public class EncheresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EncheresServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO: a voir avec l equipe, afficher la liste des encheres 
		//model (info utilisateur : pseudo) pour affichage "Bonjour pseudo"
//		EncheresModel model = new EncheresModel();
//		model.setPseudo(request.getSession().getAttribute("login").toString());
//		request.setAttribute("model", model);
		request.getRequestDispatcher("index.jsp").forward(request, response);
}
	
	 


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
