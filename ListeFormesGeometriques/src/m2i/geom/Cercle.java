package m2i.geom;

public class Cercle implements IGeometrie {

	private int _ID_Forme;
	private int _X;
	private int _Y;
	private float _Rayon;

	public Cercle(int _ID_Forme, int _X, int _Y, float _Rayon) {
		super();
		this._ID_Forme = _ID_Forme;
		this._X = _X;
		this._Y = _Y;
		this._Rayon = _Rayon;
	}

	@Override
	public float Surface() {
		return (float) Math.PI * (this._Rayon * this._Rayon);
	}

	@Override
	public float Perimetre() {
		return 2 * (float) Math.PI * this._Rayon;
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

	public float get_Rayon() {
		return _Rayon;
	}

	public void set_Rayon(float _Rayon) {
		this._Rayon = _Rayon;
	}

	public int get_ID_Forme() {
		return _ID_Forme;
	}

	public void set_ID_Forme(int _ID_Forme) {
		this._ID_Forme = _ID_Forme;
	}

}
