package models.Composant;

import models.AObjet;
import models.Chemin;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.Point;

public abstract class AComposant extends AObjet {

	protected BufferedImage icon;
	protected Chemin productPath;
	private int translateX;
	private int translateY;

	// the path for the composants are not in configuration.xml
	public AComposant(int x, int y, String path, Chemin c) {
		productPath = c;
		setPos(x, y);
		setIcon(path);
		
	}

	public void setPos(int x, int y) {
		super.setPos(x, y);
		setTranslates();
	}

	private void setIcon(String p) {
		try {
			BufferedImage im = ImageIO.read(new File(p));
			icon = im;
		} catch (IOException e) {
			e.printStackTrace();
		}
	};

	public BufferedImage getIcon() {
		return icon;
	}

	@Override
	public void lap() {
		Point p = super.getPoint();
		p.translate(translateX, translateY);
		var u = productPath.getDestination();
		if(p.x == u.getX() && p.y == u.getY()) {
			u.receiveComponent(null);
		}
	}

	private void setTranslates() {
		int temp = productPath.getEX() - productPath.getSX();
		if (temp > 0)
			translateX = 1;
		else if (temp < 0)
			translateX = -1;
		else
			translateX = 0;

		temp = productPath.getEY() - productPath.getSY();
		if (temp > 0)
			translateY = 1;
		else if (temp < 0)
			translateY = -1;
		else
			translateY = 0;

	}

}
