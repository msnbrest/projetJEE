package fr.eni.eniEncheres.IHM;

import java.io.IOException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

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

		if (request.getSession().getAttribute("login") == null) {

			// dsl, pas co
			request.getRequestDispatcher("/index").forward(request, response);

		} else {

			// init
			CreerVenteModel model = new CreerVenteModel();
			IUtilisateurManager managerUtilisateur = UtilisateurSingleton.getInstance();
			IArticleVenduManager managerArticleVendu = ArticleVenduManagerSing.getInstance();
			Boolean ok_govente = true;
			ArticleVendu article = new ArticleVendu();
			article.setCategorie(new Categorie());
			article.setLieuRetrait(new Retrait());
			article.setUtilisateur(new Utilisateur());

			if (request.getParameter("sender") == null) {

				// Afficher adresse postale par défaut
				try {
					article.setUtilisateur(managerUtilisateur
							.getUserByIdentifiant(request.getSession().getAttribute("login").toString()));

					article.getLieuRetrait().setRue(article.getUtilisateur().getRue());
					article.getLieuRetrait().setCodePostal(article.getUtilisateur().getCodePostal());
					article.getLieuRetrait().setVille(article.getUtilisateur().getVille());

					// info: toutes les infos?
				} catch (UtilisateurBLLException e) {
					model.setMessage(e.getMessage());
				}

			} else if (request.getParameter("sender").equals("Enregistrer")) {
				// save to article

				article.setNomArticle(request.getParameter("form_nom_article"));
				article.setDescription(request.getParameter("form_description_article"));
				article.setMiseAPrix(0 + (request.getParameter("form_montant_enchere").equals("") ? 0
						: Integer.parseInt(request.getParameter("form_montant_enchere"))));
				article.setDateDebutEncheres(
						request.getParameter("form_date_debut_article").equals("") ? LocalDate.now()
								: LocalDate.parse(request.getParameter("form_date_debut_article")));
				article.setDateFinEncheres(request.getParameter("form_date_fin_article").equals("") ? LocalDate.now()
						: LocalDate.parse(request.getParameter("form_date_fin_article")));

				article.getCategorie().setNoCategorie(request.getParameter("form_no_categorie_article").equals("") ? 0
						: Integer.parseInt(request.getParameter("form_no_categorie_article")));

				// TODO : SI formulaire contient adresse autre que adresse client, alors ajouter
				// un retrait, sinon osef car adresse déjà client : dans le métier

				try {
					article.setUtilisateur(managerUtilisateur
							.getUserByIdentifiant(request.getSession().getAttribute("login").toString()));
					// info: toutes les infos?

					// soit infos via formulaire ou par défaut
					article.getLieuRetrait()
							.setRue(request.getParameter("form_rue_retrait").equals("")
									? article.getUtilisateur().getRue()
									: request.getParameter("form_rue_retrait"));
					article.getLieuRetrait()
							.setCodePostal(request.getParameter("form_code_postal_retrait").equals("")
									? article.getUtilisateur().getCodePostal()
									: request.getParameter("form_code_postal_retrait"));
					article.getLieuRetrait()
							.setVille(request.getParameter("form_ville_retrait").equals("")
									? article.getUtilisateur().getVille()
									: request.getParameter("form_ville_retrait"));

					if (article.getNomArticle().equals("")) {
						model.setMessage("Titre manquant");
					} else if (article.getDescription().equals("")) {
						model.setMessage("Déscription manquante");
					} else if (article.getCategorie().getNoCategorie() < 1) {
						model.setMessage("Catégorie manquante");
					} else if (article.getDateDebutEncheres().isBefore(LocalDate.now())) {
						model.setMessage("Erreur Date de fin trop proche");
					} else if (!article.getDateFinEncheres().isAfter(article.getDateDebutEncheres())) {
						model.setMessage("Erreur Date de début proche de Date de fin");
					} else if (article.getMiseAPrix() < 0) {
						model.setMessage("Mise à prix manquante");
					} else if (article.getLieuRetrait().getRue().equals("")) {
						model.setMessage("Rue de retrait manquante");
					} else if (article.getLieuRetrait().getCodePostal().equals("")) {
						model.setMessage("Code postal de retrait manquant");
					} else if (article.getLieuRetrait().getVille().equals("")) {
						model.setMessage("Ville de retrait manquante");
					} else {

						try {
							article.getUtilisateur().setNoUtilisateur(
									Integer.parseInt(request.getSession().getAttribute("id").toString()));

							managerArticleVendu.insertArticle(article);
						} catch (ArticleVenduManagerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						ok_govente = false;
						request.getRequestDispatcher("/index").forward(request, response);

					}
				} catch (UtilisateurBLLException e) {
					model.setMessage(e.getMessage());
				}

			} else if (request.getParameter("sender").equals("Annuler")) {

				ok_govente = false;
				request.getRequestDispatcher("/index").forward(request, response);

			} else if (request.getParameter("sender").equals("Annuler la vente")) {
				// delete article
				// TODO : creer_vente.jsp coder model.article.no pour annuler la vente.

			}

			if (ok_govente) {
				// si formulaire contient adresse autre que adresse client, alors ajouter un
				// retrait, sinon osef car adresse déjà client

				// xxxx

				model.setArticle(article);
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
