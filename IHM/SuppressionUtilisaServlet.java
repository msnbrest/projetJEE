package fr.eni.eniEncheres.IHM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.SendResult;

import fr.eni.eniEncheres.BLL.IUtilisateurManager;
import fr.eni.eniEncheres.BLL.UtilisateurBLLException;
import fr.eni.eniEncheres.BLL.UtilisateurSingleton;

/**
 * Servlet implementation class SuppressionUtilisaServlet
 */
@WebServlet("/SupprimerCompte")
public class SuppressionUtilisaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUtilisateurManager managerBll= UtilisateurSingleton.getInstance();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuppressionUtilisaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelSuppression modelSup =  new ModelSuppression();
		if  (((HttpServletRequest)request).getSession().getAttribute("login") !=null ){
			
			Integer idCurrent = (Integer) request.getSession().getAttribute("numUtilisateur");
			try {
				
				managerBll.deleteUser(idCurrent);
				modelSup.setMessage("Votre compte a été bien supprimé, à bientôt");
				request.setAttribute("modelSup", modelSup);
				
			} catch (UtilisateurBLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		request.getSession().invalidate();
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
		
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
