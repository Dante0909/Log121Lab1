package models;


public abstract class AObjet {
	
	private int x;
	private int y;
	
	protected void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
}
