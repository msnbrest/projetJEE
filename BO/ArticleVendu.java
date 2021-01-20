package fr.eni.eniEncheres.BO;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ArticleVendu {

	private Integer noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private Integer miseAPrix;
	private Integer prixVente;
	private Boolean etatVente;
	private Categorie categorieArticleVendu;
	private Utilisateur utilisateur;
	private Retrait lieuRetrait;
	private List<Enchere> concerne;
//	private Boolean articleAccessible;//TODO accessible 
	
	public ArticleVendu() {
		
	}
	
	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, Integer miseAPrix, Integer prixVente, Boolean etatVente,
			Categorie categorieArticleVendu, Utilisateur utilisateur/*, Boolean articleAccessible*/) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.categorieArticleVendu = categorieArticleVendu;
		this.utilisateur = utilisateur;
//		this.articleAccessible = articleAccessible;
	}



	public Integer getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(Integer noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public Integer getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(Integer miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public Integer getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(Integer prixVente) {
		this.prixVente = prixVente;
	}

	public Boolean getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(Boolean etatVente) {
		this.etatVente = etatVente;
	}

	public Categorie getCategorieArticleVendu() {
		return categorieArticleVendu;
	}

	public void setCategorieArticleVendu(Categorie categorieArticleVendu) {
		this.categorieArticleVendu = categorieArticleVendu;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Retrait getLieuRetrait() {
		return lieuRetrait;
	}

	public void setLieuRetrait(Retrait lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}

	public List<Enchere> getConcerne() {
		return concerne;
	}

	public void setConcerne(List<Enchere> concerne) {
		this.concerne = concerne;
	}

//	public Boolean getArticleAccessible() {
//		return articleAccessible;
//	}
//
//	public void setArticleAccessible(Boolean articleAccessible) {
//		this.articleAccessible = articleAccessible;
//	}

	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", categorieArticleVendu="
				+ categorieArticleVendu + ", utilisateur=" + utilisateur + ", lieuRetrait=" + lieuRetrait
				+ ", concerne=" + concerne 
//				+ ", articleAccessible=" + articleAccessible 
				+ "]";
	}
}
