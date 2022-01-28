package models.Usine;

import java.util.ArrayList;
import java.util.Observer;

import models.Composant.ComposantE;
import models.Composant.EntryComponent;

public abstract class AUsineProduction extends AUsine implements Observer{

	private int interval;
	private int maxInterval;
	private ComposantE sortie;
	public AUsineProduction(ArrayList<String> p, int interval, ArrayList<EntryComponent> ec, ComposantE product, int id, int x,
			int y) throws Exception {
		super(p, ec, id, x, y);
		sortie = product;
		this.interval = interval;
		maxInterval = interval;
	}
	public int getInterval() {
		return interval;

	}
	public ComposantE getSortie() {
		return sortie;
	}
	@Override
	public void lap() {
		interval--;
		if(interval == 0) {
			interval = maxInterval;
			System.out.println("interval");
		}
	}
	public boolean tryToProduce() {
		return false;
	}
}
