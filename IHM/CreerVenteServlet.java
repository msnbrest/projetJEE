package fr.eni.eniEncheres.IHM;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eniEncheres.BLL.ArticleVenduManagerException;
import fr.eni.eniEncheres.BLL.ArticleVenduManagerSing;
import fr.eni.eniEncheres.BLL.IArticleVenduManager;
import fr.eni.eniEncheres.BLL.IUtilisateurManager;
import fr.eni.eniEncheres.BLL.UtilisateurBLLException;
import fr.eni.eniEncheres.BLL.UtilisateurSingleton;
import fr.eni.eniEncheres.BO.ArticleVendu;
import fr.eni.eniEncheres.BO.Categorie;
import fr.eni.eniEncheres.BO.Retrait;
import fr.eni.eniEncheres.BO.Utilisateur;

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
		IArticleVenduManager managerArticleVendu = ArticleVenduManagerSing.getInstance();
		CreerVenteModel model = new CreerVenteModel();

		if (request.getParameter("form_nom_article") != null) {
			ArticleVendu article = new ArticleVendu();

			article.setNomArticle(request.getParameter("form_nom_article"));
			article.setDescription(request.getParameter("form_description_article"));

			Categorie categorieArticle = new Categorie();
			categorieArticle.setNoCategorie(Integer.parseInt(request.getParameter("form_no_categorie_article")));

			article.setCategorieArticleVendu(categorieArticle);
			article.setMiseAPrix(Integer.parseInt(request.getParameter("form_montant_enchere")));
			article.setDateDebutEncheres(LocalDate.parse(request.getParameter("form_date_debut_article")));
			article.setDateFinEncheres(LocalDate.parse(request.getParameter("form_date_fin_article")));

			Retrait lieuRetrait = new Retrait();
			lieuRetrait.setRue(request.getParameter("form_rue_retrait"));
			lieuRetrait.setCodePostal(request.getParameter("form_code_postal_retrait"));
			lieuRetrait.setVille(request.getParameter("form_ville_retrait"));

			article.setLieuRetrait(lieuRetrait);

			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setNoUtilisateur(Integer.parseInt(request.getSession().getAttribute("numUtilisateur").toString()));

			article.setUtilisateur(utilisateur);

			try {
				managerArticleVendu.insertArticle(article);
			} catch (ArticleVenduManagerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("/index").forward(request, response);
			
		} else {
			// Récupere l'adresse par défaut de l'utilisateur pour la transmettre à la jsp
			IUtilisateurManager managerUtilisateur = UtilisateurSingleton.getInstance();
			Utilisateur utilisateur = new Utilisateur();
			if (request.getSession().getAttribute("login") != null) {
				try {
					utilisateur = managerUtilisateur
							.getUserByIdentifiant(request.getSession().getAttribute("login").toString());
					// TODO: pas très top de donner toutes les infos sur l'utilisaeteur
					model.setUtilisateur(utilisateur);
				} catch (UtilisateurBLLException e) {
					// TODO Créer une classe exception pour gérer les messages
					e.printStackTrace();
				}
			}
			request.setAttribute("model", model);
			request.getRequestDispatcher("creer_vente.jsp").forward(request, response);
		}

		// si formulaire contient adresse autre que adresse client, alors ajouter un
		// retrait, sinon osef car adresse déjà client : dans le métier

		// request.getRequestDispatcher("creer_vente.jsp").forward(request, response);
		// TODO : creer_vente.jsp coder model.article.no pour annuler la vente.

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
