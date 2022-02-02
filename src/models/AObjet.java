package models;
import java.awt.Point;

public abstract class AObjet {
	private Point point;
	
	protected void setPos(int x, int y) {
		point = new Point(x,y);
	}
	public int getX() {
		return point.x;
	}
	public int getY() {
		return point.y;
	}
	public Point getPoint() {
		return point;
	}
	public abstract void lap();
}
