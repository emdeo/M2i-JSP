package m2i.geom;

public class Cercle2 extends Forme {
	
	private float _Rayon;
	
	public Cercle2(int ID_Forme, Point coord, float _Rayon) {
		super(ID_Forme, coord);
		this._Rayon = _Rayon;
	}

	@Override
	public float Surface() {
		return (float) (Math.PI * (this._Rayon * this._Rayon));
	}

	@Override
	public float Perimetre() {
		return 2 * (float) Math.PI * this._Rayon;
	}

	public float get_Rayon() {
		return _Rayon;
	}

	public void set_Rayon(float _Rayon) {
		this._Rayon = _Rayon;
	}

	@Override
	public int getID_Forme() {
		// TODO Auto-generated method stub
		return super.getID_Forme();
	}

	@Override
	public void setID_Forme(int iD_Forme) {
		// TODO Auto-generated method stub
		super.setID_Forme(iD_Forme);
	}

	@Override
	public Point get_coord() {
		// TODO Auto-generated method stub
		return super.get_coord();
	}

	@Override
	public void set_coord(Point _coord) {
		// TODO Auto-generated method stub
		super.set_coord(_coord);
	}
	
}
