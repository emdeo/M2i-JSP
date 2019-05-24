package m2i.geom;

public class Carre2 extends Rectangle2 {

	private float _Cote;

	/**
	 * Constructeur.
	 * 
	 * @param _coord
	 * @param _Cote
	 */
	public Carre2(int ID_Forme, Point _coord, float _Cote) {
		super(ID_Forme, _coord, _Cote, _Cote);
		this._Cote = _Cote;
	}

	@Override
	public float Surface() {
		return super.Surface();
	}

	@Override
	public float Perimetre() {
		return super.Perimetre();
	}

	@Override
	public float get_Largeur() {
		System.out.println("Carré n'a pas de largeur");
		return 0;
	}

	@Override
	public void set_Largeur(float _Largeur) {
		// Vide
	}

	@Override
	public float get_Longueur() {
		System.out.println("Carré n'a pas de longueur");
		return 0;
	}

	@Override
	public void set_Longueur(float _Longueur) {
		// Vide
	}

	public float get_Cote() {
		return _Cote;
	}

	public void set_Cote(float _Cote) {
		this._Cote = _Cote;
	}
}
