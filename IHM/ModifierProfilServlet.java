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
 * Servlet implementation class ModifierProfil
 */
@WebServlet("/monProfil")
public class ModifierProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUtilisateurManager managerBLL = UtilisateurSingleton.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifierProfilServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProfilUtilisateurModel model = new ProfilUtilisateurModel();
		Utilisateur utilisateur = new Utilisateur();

		if (request.getParameter("Pseudo") == null) {
			try {
				utilisateur = managerBLL.getUserByIdentifiant(request.getSession().getAttribute("login").toString());
			} catch (UtilisateurBLLException e) {
				e.printStackTrace();
				// TODO : afficher erreur html
			}
		} else {
			// modification utilisateur, puis update sql
			utilisateur.setNoUtilisateur(Integer.parseInt(request.getSession().getAttribute("id").toString()));
			utilisateur.setPseudo(request.getParameter("pseudo"));
			utilisateur.setNom(request.getParameter("nom"));
			utilisateur.setPrenom(request.getParameter("prenom"));
			utilisateur.setEmail(request.getParameter("email"));
			utilisateur.setTelephone(request.getParameter("telephone"));
			utilisateur.setRue(request.getParameter("rue"));
			utilisateur.setCodePostal(request.getParameter("codepostal"));
			utilisateur.setVille(request.getParameter("ville"));
			utilisateur.setMotDePasse((request.getParameter("motdepasse")));
			utilisateur.setCredit(0);
			utilisateur.setAdministrateur(Boolean.FALSE);
			managerBLL.updateUtilisateur(utilisateur.getNoUtilisateur());
		}
		
		model.setUtilisateur(utilisateur);
		request.getSession().setAttribute("model", model);
		request.getRequestDispatcher("monProfil.jsp").forward(request, response);
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
