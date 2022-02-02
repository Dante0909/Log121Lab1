package models.Usine;

import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import models.AObjet;
import models.Composant.*;

public abstract class AUsine extends AObjet {
	private int id;
	private FullnessE fullness;
	protected ArrayList<EntryComponent> entries;
	private ArrayList<BufferedImage> images;

	public AUsine(ArrayList<String> p, ArrayList<EntryComponent> ec, int id, int x, int y) throws Exception {
		super.setPos(x, y);
		setFullness(FullnessE.VIDE);
		entries = ec;
		setImages(p);
		this.id = id;
	}

	public FullnessE getFullness() {
		return fullness;
	}

	public void setFullness(FullnessE f) {
		fullness = f;
	}

	public BufferedImage getImage(FullnessE f) {
		if (f == null)
			return images.get(0);
		switch (f) {
		case VIDE:
			return images.get(0);
		case UN_TIER:
			return images.get(1);

		case DEUX_TIERS:
			return images.get(2);

		case PLEIN:
			return images.get(3);
		default:
			return images.get(0);
		}
	}

	private void setImages(ArrayList<String> p) {
		images = new ArrayList<BufferedImage>();
		for (int i = 0; i < p.size(); ++i) {

			try {
				BufferedImage im = ImageIO.read(new File(p.get(i)));
				images.add(im);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int getId() {
		return id;
	}

	public ComposantE receiveComponent(AComposant comp) {
		
		
		
		if (comp instanceof Aile)
			return ComposantE.AILE;
		else if (comp instanceof Avion)
			return ComposantE.AVION;
		else if (comp instanceof Metal)
			return ComposantE.METAL;
		else
			return ComposantE.MOTEUR;
	}

}
