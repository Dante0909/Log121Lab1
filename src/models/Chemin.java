package models;

import models.Usine.AUsine;

public class Chemin {

	private AUsine from;
	private AUsine to;
	private int offset;

	
	public Chemin(AUsine from, AUsine to) {
		var i = from.getImage(null);
		offset = i.getWidth() / 2;
		this.from = from;
		this.to = to;
	}

	public int getSX() {
		return from.getX() + offset;
	}

	public int getSY() {
		return from.getY() + offset;
	}

	public int getEX() {
		return to.getX() + offset;
	}

	public int getEY() {
		return to.getY() + offset;
	}
public AUsine getDestination() {
	return to;
}
}
