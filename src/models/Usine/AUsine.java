package models.Usine;

import java.util.ArrayList;

import models.AObjet;
import models.Composant.ComposantE;

public abstract class AUsine extends AObjet {
	private ArrayList<String> paths;
	private int interval;
	private FullnessE fullness;
	private ArrayList<EntryComponent> entries;
	private ComposantE sortie;

	public AUsine(ArrayList<String> p, int i, ArrayList<EntryComponent> ec, ComposantE product) throws Exception {
		super(p);
		setPaths(p);
		setInterval(i);
		setFullness(FullnessE.VIDE);
		setEntries(ec);
		setSortie(product);
	}

	public ArrayList<String> getPaths() {
		return paths;
	}

	private void setPaths(ArrayList<String> p) {
		if (p != null && p.size() > 0) {
			paths = p;
		}

	}

	public int getInterval() {
		return interval;

	}

	private void setInterval(int i) {
		interval = i;
	}

	public FullnessE getFullness() {
		return fullness;
	}

	private void setFullness(FullnessE f) {
		fullness = f;
	}

	public ArrayList<EntryComponent> getEntries() {
		return entries;
	}

	private void setEntries(ArrayList<EntryComponent> ec) {
		entries = ec;
	}
	public ComposantE getSortie() {
		return sortie;
	}
	private void setSortie(ComposantE s) {
		sortie = s;
	}
}
