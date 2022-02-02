package models.Usine;

import java.util.ArrayList;
import java.util.Observer;

import models.Chemin;
import models.Composant.*;
import simulation.Simulation;

public abstract class AUsineProduction extends AUsine implements Observer {

	private int interval;
	private int maxInterval;
	private ComposantE sortie;
	private Chemin productPath;

	public AUsineProduction(ArrayList<String> p, int interval, ArrayList<EntryComponent> ec, ComposantE product, int id,
			int x, int y) throws Exception {
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
		if (interval == 0) {
			if (tryToProduce()) {
				produce();
			}
			interval = maxInterval;
			if (this instanceof UsineMatiere) {
				System.out.println("interval");
			}

		}
	}

	public boolean tryToProduce() {
		return true;
	}

	public void produce() {
		int posX = super.getX();
		int posY = super.getY();
		AComposant a = null;
		System.out.println(sortie);
		switch (sortie) {
		case AILE:
			a = new Aile(posX, posY, this.getProductPath());
			break;
		case AVION:
			a = new Avion(posX, posY, this.getProductPath());
			break;
		case METAL:
			a = new Metal(posX, posY, this.getProductPath());
			break;
		case MOTEUR:
			a = new Moteur(posX, posY, this.getProductPath());
			break;
		default:
			break;

		}
		Simulation.Composants.add(a);
	}

	public void setProductPath(Chemin c) {
		productPath = c;
	}

	public Chemin getProductPath() {
		return productPath;
	}
	@Override
	public ComposantE receiveComponent(AComposant comp) {
		var c = super.receiveComponent(comp);
		
		return null;
	}
}
