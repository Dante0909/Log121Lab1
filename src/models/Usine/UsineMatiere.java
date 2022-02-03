package models.Usine;

import java.util.ArrayList;
import java.util.Observable;

import models.Composant.AComposant;
import models.Composant.ComposantE;
import models.Composant.EntryComponent;
import models.Composant.Metal;
import simulation.Simulation;

public class UsineMatiere extends AUsineProduction {

	public UsineMatiere(ArrayList<String> paths, int interval, ArrayList<EntryComponent> ec, ComposantE sortie, int id, int x, int y) throws Exception {
		// TODO Auto-generated constructor stubs
		super(paths, interval, ec,sortie,id,x,y);
	}

	
	@Override
	public void lap() {
		super.lap();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void produce(AComposant c) {
		Metal m = new Metal(super.getX(),super.getY(),super.getProductPath());
		super.produce(m);
	}

}
