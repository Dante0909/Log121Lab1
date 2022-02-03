package models.Usine;

import java.util.ArrayList;
import java.util.Observable;

import models.Composant.*;

public class UsineAile extends AUsineProduction {

	public UsineAile(ArrayList<String> paths, int interval, ArrayList<EntryComponent> ec, ComposantE sortie, int id,
			int x, int y) throws Exception {
		// TODO Auto-generated constructor stubs
		super(paths, interval, ec, sortie, id, x, y);
	}


	@Override
	public void lap() {
		super.lap();
		// TODO Auto-generated method stub

	}

	@Override
	public void produce(AComposant c) {
		Aile a = new Aile(super.getX(), super.getY(), this.getProductPath());
		super.produce(a);
		
	}
}
