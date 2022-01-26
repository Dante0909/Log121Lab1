package simulation;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import models.Chemin;
import models.Usine.FullnessE;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	
	// Variables temporaires de la demonstration:
	private Point position = new Point(0,0);
	private Point vitesse = new Point(1,1);
	private int taille = 32;
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// On ajoute à la position le delta x et y de la vitesse
		var usines = MenuFenetre.Usines;
		var chemins = MenuFenetre.Chemins;
		
		for(int i = 0; i < chemins.size();++i) {
			Chemin c = chemins.get(i);
			g.drawLine(c.getSX(),c.getSY(),c.getEX(),c.getEY());
		}
		
		Graphics2D d2 = (Graphics2D) g;
		
		for(int i = 0; i < usines.size();++i) {
			var u = usines.get(i);
			d2.drawImage(u.getImage(u.getFullness()),u.getX(),u.getY(),null);
		}
		
		
		//position.translate(vitesse.x, vitesse.y);
		//g.fillRect(position.x, position.y, taille, taille);
		
	}

}