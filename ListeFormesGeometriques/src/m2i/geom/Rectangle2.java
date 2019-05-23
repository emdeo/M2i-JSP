package m2i.geom;

public class Rectangle2 extends Forme {
	
	private float _Largeur;
	private float _Longueur;
	
	public Rectangle2(int ID_Forme, Point _coord, float _Largeur, float _Longueur) {
		super(ID_Forme, _coord);
		this._Largeur = _Largeur;
		this._Longueur = _Longueur;
	}

	@Override
	public float Surface() {
		return this._Largeur * this._Longueur;
	}

	@Override
	public float Perimetre() {
		return this._Largeur * 2 + this._Longueur * 2;
	}

	public float get_Largeur() {
		return _Largeur;
	}

	public void set_Largeur(float _Largeur) {
		this._Largeur = _Largeur;
	}

	public float get_Longueur() {
		return _Longueur;
	}

	public void set_Longueur(float _Longueur) {
		this._Longueur = _Longueur;
	}
}
