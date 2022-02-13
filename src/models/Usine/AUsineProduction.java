package models.Usine;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import ProductionStrategy.*;
import models.*;
import models.Composant.*;
import simulation.Simulation;

public abstract class AUsineProduction extends AUsine implements Observer {

	private int interval;
	private int maxInterval;
	private ComposantE sortie;
	private Chemin productPath;
	private AProduceBehaviour prodB = new ProduceRegular();

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
	public FullnessE getFullness() {
		var d = (float) interval / (float) maxInterval;

		if (d > 0.66f)
			return FullnessE.VIDE;
		else if (d > 0.33f)
			return FullnessE.UN_TIER;
		else if (d > 0.1f)
			return FullnessE.DEUX_TIERS;

		return FullnessE.PLEIN;
	}

	@Override
	public void lap() {
		if (prodB.tryProduce(entries)) {
			interval--;
			if (interval == 0) {
				produce(null);
				interval = maxInterval;
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {

		switch ((FullnessE)arg) {
		case UN_TIER:
			prodB = new ProduceSlightlyFilled();
			break;
		case DEUX_TIERS:
			prodB = new ProduceHighlyFilled();
			break;
		case PLEIN:
			prodB = new ProduceFilled();
			break;
		default:
			prodB = new ProduceRegular();
			break;

		}
	}

	public void produce(AComposant a) {
		if (entries != null) {
			for (int i = 0; i < entries.size(); ++i) {
				entries.get(i).resetProduction();
			}
		}

		Simulation.Composants.add(a);
	}

	public void setProductPath(Chemin c) {
		productPath = c;
	}

	public Chemin getProductPath() {
		return productPath;
	}

}
