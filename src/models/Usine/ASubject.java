package models.Usine;

import java.util.ArrayList;
import java.util.Observer;

import models.Composant.ComposantE;
import models.Composant.EntryComponent;

public abstract class ASubject extends AUsine {

	private ArrayList<Observer> observers = new ArrayList<Observer>();
	public ASubject(ArrayList<String> p, ArrayList<EntryComponent> ec, int id, int x, int y)
			throws Exception {
		super(p, ec, id, x, y);
		// TODO Auto-generated constructor stub
	}
	public void attach(Observer o) {
		observers.add(o);
	}
	public void dettach(Observer o) {
		observers.remove(o);
	}
	public void notifyObservers() {
		for(int i = 0; i < observers.size();++i) {
			var o = observers.get(i);
			o.update(null, observers);
		}
	}
}
