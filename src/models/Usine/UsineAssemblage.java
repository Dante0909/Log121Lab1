package models.Usine;

import java.util.ArrayList;
import java.util.Observable;

import models.Composant.ComposantE;
import models.Composant.EntryComponent;

public class UsineAssemblage extends AUsineProduction {

	public UsineAssemblage(ArrayList<String> paths, int interval, ArrayList<EntryComponent> ec, ComposantE sortie,int id, int x, int y) throws Exception {
		// TODO Auto-generated constructor stubs
		super(paths, interval, ec,sortie, id,x,y);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lap() {
		super.lap();
		// TODO Auto-generated method stub
		
	}

}
