package m2i.geom;

public class Rectangle implements IGeometrie {

	private int _ID_Forme;
	private int _X;
	private int _Y;
	private float _Longueur;
	private float _Largeur;

	public Rectangle(int _ID_Forme, int _X, int _Y, float _Longueur, float _Largeur) {
		super();
		this._ID_Forme = _ID_Forme;
		this._X = _X;
		this._Y = _Y;
		this._Longueur = _Longueur;
		this._Largeur = _Largeur;
	}

	@Override
	public float Surface() {
		return this._Largeur * this._Longueur;
	}

	@Override
	public float Perimetre() {
		return this._Largeur * 2 + this._Longueur * 2;
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

	public int get_ID_Forme() {
		return _ID_Forme;
	}

	public void set_ID_Forme(int _ID_Forme) {
		this._ID_Forme = _ID_Forme;
	}

}
