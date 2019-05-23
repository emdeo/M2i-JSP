package m2i.geom;

public class Forme implements IGeometrie{
	
	private int ID_Forme;
	private Point _coord;

	public Forme(int iD_Forme, Point _coord) {
		super();
		ID_Forme = iD_Forme;
		this._coord = _coord;
	}

	@Override
	public float Surface() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float Perimetre() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getID_Forme() {
		return ID_Forme;
	}

	public void setID_Forme(int iD_Forme) {
		ID_Forme = iD_Forme;
	}

	public Point get_coord() {
		return _coord;
	}

	public void set_coord(Point _coord) {
		this._coord = _coord;
	}
	
}
