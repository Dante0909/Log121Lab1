package models.Usine;

import java.util.ArrayList;
import java.util.Observable;

import models.Composant.*;

public class UsineMoteur extends AUsineProduction {

	public UsineMoteur(ArrayList<String> paths, int interval, ArrayList<EntryComponent> ec, ComposantE sortie,int id, int x, int y) throws Exception {
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
		Moteur m = new Moteur(super.getX(), super.getY(), this.getProductPath());
		super.produce(m);
	}

}
