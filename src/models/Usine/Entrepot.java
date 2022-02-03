package models.Usine;

import java.util.ArrayList;

import VenteStrategy.*;
import models.Chemin;
import models.Composant.AComposant;
import models.Composant.EntryComponent;

public class Entrepot extends ASubject {

	private IVenteStrategy venteS = new VenteFixe();
	public Entrepot(ArrayList<String> paths, ArrayList<EntryComponent> ec, int id, int x, int y) throws Exception {
		// TODO Auto-generated constructor stubs
		super(paths, ec, id, x, y);
	}
	public void setStrategy(String s) {
		if(s.equals("Aléatoire"))
			venteS = new VenteAleatoire();
		else if(s.equals("Temporel"))
			venteS = new VenteFixe();
			
	}
	@Override
	public void lap() {
		if(entries.get(0).getAmount() > 0) {
			if(venteS.VenteStrategy()) {
				sellPlane();
				
			}
		}

	}
	public int getCurrentAmount() {
		return entries.get(0).getAmount();
	}

	@Override
	public FullnessE getFullness() {
		
		switch (getCurrentAmount()) {
		
		case 0:
			return FullnessE.VIDE;
		case 1:
			return FullnessE.UN_TIER;
		case 2:
			return FullnessE.DEUX_TIERS;
		case 3:
			return FullnessE.PLEIN;
		default:
			return FullnessE.VIDE;
		}
	}
	@Override
	public void receiveComponent(AComposant comp) {
		super.receiveComponent(comp);		
		super.notifyObservers();
		
	}
	private void sellPlane() {
		super.entries.get(0).removeComp();
		super.notifyObservers();
		System.out.println("Sold plane");
	}

}
