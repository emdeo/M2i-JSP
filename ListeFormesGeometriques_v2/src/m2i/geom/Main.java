package m2i.geom;

public class Main {

	public static void main(String[] args) {
		DAO_Cercle daoce = new DAO_Cercle();
		DAO_Carre daoca = new DAO_Carre();
		DAO_Rectangle daor = new DAO_Rectangle();
		
		for(int i=1; i<2; i++) {
			daoce.Create(new Cercle2(i, new Point(i+1,1,5), i*11));
			daoca.Create(new Carre2(i+1, new Point(i+2,1,5), i*15));
			daor.Create(new Rectangle2(i+2, new Point(i,1,5), i*20, i*33));
		}
	}

}
