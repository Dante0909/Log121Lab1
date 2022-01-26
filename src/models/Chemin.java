package models;

import models.Usine.AUsine;

public class Chemin {

	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private static final int OFFSET = 15; 
	public Chemin(AUsine from, AUsine to) {
		startX = from.getX();
		startY = from.getY();
		endX = to.getX();
		endY = to.getY();
	}
	public int getSX() {
		return startX + OFFSET;
	}
	public int getSY() {
		return startY + OFFSET;
	}public int getEX() {
		return endX + OFFSET;
	}public int getEY() {
		return endY + OFFSET;
	}
	
}
