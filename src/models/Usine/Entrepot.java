package models.Usine;

import java.util.ArrayList;

import models.Chemin;
import models.Composant.AComposant;
import models.Composant.ComposantE;
import models.Composant.EntryComponent;

public class Entrepot extends ASubject {

	public Entrepot(ArrayList<String> paths, ArrayList<EntryComponent> ec, int id,
			int x, int y) throws Exception {
		// TODO Auto-generated constructor stubs
		super(paths, ec, id, x, y);
	}

	@Override
	public void lap() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ComposantE receiveComponent(AComposant comp) {
		return null;
		// TODO Auto-generated method stub
		
	}

}
