package m2i.geom;

public class Carre implements IGeometrie{
	
	private int _ID_Forme;
	private int _X;
	private int _Y;
	private float _Cote;
	private float _Surface;
	private float _Perimetre;
	
	public Carre(int _ID_Forme, int _X, int _Y, float _Cote) {
		super();
		this._ID_Forme = _ID_Forme;
		this._X = _X;
		this._Y = _Y;
		this._Cote = _Cote;
		this._Surface = Surface();
		this._Perimetre = Perimetre();
	}

	@Override
	public float Surface() {
		return this._Cote * this._Cote;
	}

	@Override
	public float Perimetre() {
		return this._Cote * 4;
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

	public float get_Cote() {
		return _Cote;
	}

	public void set_Cote(float _Cote) {
		this._Cote = _Cote;
	}

	public int get_ID_Forme() {
		return _ID_Forme;
	}

	public void set_ID_Forme(int _ID_Forme) {
		this._ID_Forme = _ID_Forme;
	}

	public float get_Surface() {
		return _Surface;
	}

	public float get_Perimetre() {
		return _Perimetre;
	}

}
