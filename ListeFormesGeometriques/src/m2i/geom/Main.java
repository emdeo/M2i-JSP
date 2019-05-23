package m2i.geom;

public class Main {

	public static void main(String[] args) {
		DAO_Formes daof = new DAO_Formes();
		daof.Create(new Carre(99,9,9,15f));
//		daof.Create(new Cercle(2,1,3,15f));
//		daof.Create(new Rectangle(3,9,9,80f,50f));
//		daof.ReadAll();
//		daof.Delete(3);
	}

}
