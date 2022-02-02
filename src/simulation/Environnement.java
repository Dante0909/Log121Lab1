package simulation;

import javax.swing.SwingWorker;

public class Environnement extends SwingWorker<Object, String> {
	private boolean actif = true;
	private static final int DELAI = 100;

	@Override
	protected Object doInBackground() throws Exception {
		while (actif) {
			Thread.sleep(DELAI);
			/**
			 * C'est ici que vous aurez à faire la gestion de la notion de tour.
			 */
			var usines = Simulation.Usines;
			for (int i = 0; i < usines.size(); ++i) {
				usines.get(i).lap();
			}
			var comp = Simulation.Composants;
			for (int i = 0; i < comp.size(); ++i) {
				comp.get(i).lap();
			}

			firePropertyChange("TEST", null, "test");
		}
		return null;
	}

}