package m2i.geom;

public class Point {
	
	private int ID_Point;
	private int X;
	private int Y;
	
	public Point(int iD_Point, int x, int y) {
		super();
		ID_Point = iD_Point;
		X = x;
		Y = y;
	}

	public int getID_Point() {
		return ID_Point;
	}

	public void setID_Point(int iD_Point) {
		ID_Point = iD_Point;
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}
}
