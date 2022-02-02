package simulation;

import java.util.ArrayList;

import models.Chemin;
import models.Composant.AComposant;
import models.Usine.AUsine;

public class Simulation {

	/**
	 * Cette classe repr�sente l'application dans son ensemble.
	 */
	public static ArrayList<AUsine> Usines = new ArrayList<AUsine>();
	public static ArrayList<AComposant> Composants = new ArrayList<AComposant>();
	
	public static void main(String[] args) {
		Environnement environnement = new Environnement();
		FenetrePrincipale fenetre = new FenetrePrincipale();

		environnement.addPropertyChangeListener(fenetre);
		environnement.execute();
	}

}
