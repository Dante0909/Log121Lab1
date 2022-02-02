package simulation;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import models.Chemin;
import models.Usine.AUsineProduction;
import models.Usine.FullnessE;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;

	// Variables temporaires de la demonstration:
	private Point position = new Point(0, 0);
	private Point vitesse = new Point(1, 1);
	private int taille = 32;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// On ajoute à la position le delta x et y de la vitesse
		var usines = Simulation.Usines;

		Graphics2D d2 = (Graphics2D) g;

		for (int i = 0; i < usines.size(); ++i) {
			var u = usines.get(i);
			if (u instanceof AUsineProduction) {
				var c = ((AUsineProduction) u).getProductPath();
				if (c != null)
					g.drawLine(c.getSX(), c.getSY(), c.getEX(), c.getEY());
			}

			d2.drawImage(u.getImage(u.getFullness()), u.getX(), u.getY(), null);

		}
		var composants = Simulation.Composants;
		
		for (int j = 0; j < composants.size(); ++j) {
			var c = composants.get(j);
			var p = c.getPoint();
			d2.drawImage(c.getIcon(),p.x,p.y,null);
			
		}
		position.translate(vitesse.x, vitesse.y);
		// g.fillRect(position.x, position.y, taille, taille);

	}

}