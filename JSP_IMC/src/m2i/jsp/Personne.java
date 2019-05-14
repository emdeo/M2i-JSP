package m2i.jsp;

public class Personne {

	private String Nom;
	private String Prenom;
	private float Taille;
	private float Poids;
	private Sexe Genre;

	/**
	 * Constructeur n°1. Préférable car il est indépendant du servlet (ie. on peut
	 * l'instancier sans recourir à un fichier jsp).
	 * 
	 * @param req
	 */
	public Personne(String nom, String prenom, float taille, float poids, Sexe genre) {
		super();
		Nom = nom;
		Prenom = prenom;
		Taille = taille;
		Poids = poids;
		Genre = genre;
	}

	/**
	 * Constructeur n°2.
	 * 
	 * @param req
	 */
	public Personne(javax.servlet.ServletRequest req) {
		super();
		Nom = req.getParameter("txtNom");
		Prenom = req.getParameter("txtPrenom");
		Taille = Float.parseFloat(req.getParameter("txtTaille"));
		Poids = Float.parseFloat(req.getParameter("txtPoids"));
		Genre = Sexe.valueOf(req.getParameter("lstSexe"));
	}

	/**
	 * Renvoyer une valeur arrondie au centième.
	 * 
	 * @param valeur
	 * @return
	 */
	public float arrondi(float valeur) {
		return Math.round(valeur * 100f) / 100f;
	}

	/**
	 * Calculer l'IMC.
	 * 
	 * @return
	 */
	public float calculIMC() {
		return arrondi(this.Poids / this.Taille * this.Taille);
	}

	/**
	 * Calculer le poids minimum.
	 * 
	 * @return
	 */
	public float poidsMin() {
		return arrondi(19 * (this.Taille * this.Taille));
	}

	/**
	 * Calculer le poids maximum.
	 * 
	 * @return
	 */
	public float poidsMax() {
		return arrondi(25 * (this.Taille * this.Taille));
	}

	/**
	 * Calculer le poids idéal.
	 * 
	 * @return
	 */
	public float poidsIdeal() {

		float taille = this.Taille * 100f;
		float output = taille - 100f;

		if (this.Genre == Sexe.feminin)
			output -= ((taille - 150f) / 2.5f);
		else
			output -= ((taille - 150f) / 4f);

		return arrondi(output);
	}

	/**
	 * Déterminer le diagnostic à partir de l'IMC.
	 * 
	 * @return
	 */
	public String diagnostic() {
		float imc = calculIMC();

		if (imc < 19)
			return "Sous-poids";
		else if (imc < 25)
			return "Poids sain";
		else if (imc < 30)
			return "Surpoids";
		else if (imc < 40)
			return "Obésité";
		return "Obésité morbide";
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public float getTaille() {
		return Taille;
	}

	public void setTaille(float taille) {
		Taille = taille;
	}

	public float getPoids() {
		return Poids;
	}

	public void setPoids(float poids) {
		Poids = poids;
	}

	public Sexe getGenre() {
		return Genre;
	}

	public void setGenre(Sexe genre) {
		Genre = genre;
	}
}
