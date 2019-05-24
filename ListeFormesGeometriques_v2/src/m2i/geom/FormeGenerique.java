package m2i.geom;

public class FormeGenerique {

	private int _ID_Point;
	private int _X;
	private int _Y;
	
	private float _Longueur;
	private float _Largeur;
	private float _Cote;
	private float _Rayon;
	
	private float _Perimetre;
	private float _Surface;
	
	/**
	 * Constructeur
	 * 
	 * @param _ID_Point
	 * @param _X
	 * @param _Y
	 * @param _Longueur
	 * @param _Largeur
	 * @param _Cote
	 * @param _Rayon
	 * @param _Perimetre
	 * @param _Surface
	 */
	public FormeGenerique(int _ID_Point, int _X, int _Y, float _Longueur, float _Largeur, float _Cote, float _Rayon,
			float _Perimetre, float _Surface) {
		super();
		this._ID_Point = _ID_Point;
		this._X = _X;
		this._Y = _Y;
		this._Longueur = _Longueur;
		this._Largeur = _Largeur;
		this._Cote = _Cote;
		this._Rayon = _Rayon;
		this._Perimetre = _Perimetre;
		this._Surface = _Surface;
	}

	public int get_ID_Point() {
		return _ID_Point;
	}

	public void set_ID_Point(int _ID_Point) {
		this._ID_Point = _ID_Point;
	}

	public int get_X() {
		return _X;
	}

	public void set_X(int _X) {
		this._X = _X;
	}

	public int get_Y() {
		return _Y;
	}

	public void set_Y(int _Y) {
		this._Y = _Y;
	}

	public float get_Longueur() {
		return _Longueur;
	}

	public void set_Longueur(float _Longueur) {
		this._Longueur = _Longueur;
	}

	public float get_Largeur() {
		return _Largeur;
	}

	public void set_Largeur(float _Largeur) {
		this._Largeur = _Largeur;
	}

	public float get_Cote() {
		return _Cote;
	}

	public void set_Cote(float _Cote) {
		this._Cote = _Cote;
	}

	public float get_Rayon() {
		return _Rayon;
	}

	public void set_Rayon(float _Rayon) {
		this._Rayon = _Rayon;
	}

	public float get_Perimetre() {
		return _Perimetre;
	}

	public void set_Perimetre(float _Perimetre) {
		this._Perimetre = _Perimetre;
	}

	public float get_Surface() {
		return _Surface;
	}

	public void set_Surface(float _Surface) {
		this._Surface = _Surface;
	}
	
}
