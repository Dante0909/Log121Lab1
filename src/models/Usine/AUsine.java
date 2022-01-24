package models.Usine;

import java.util.ArrayList;

import models.AObjet;

public abstract class AUsine extends AObjet {
	private ArrayList<String> paths;
	private int interval;
	private FullnessUsine fullness;
	public AUsine(ArrayList<String> p, int i) throws Exception {
		super(p);
		setPaths(p);
		setInterval(i);
		setFullness(FullnessUsine.VIDE);
		
	}
	
	public ArrayList<String> getPaths(){
		return paths;
	}
	private void setPaths(ArrayList<String> p) {
		if(p != null && p.size() > 0) {
			paths = p;
		}
		
	}
	public int getInterval() {
		return interval;
		
	}
	private void setInterval(int i) {
		interval = i;
	}
	public FullnessUsine getFullness() {
		return fullness;
	}
	private void setFullness(FullnessUsine f) {
		fullness = f;
	}
}
