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
		entries = ec;
		setImages(p);
		this.id = id;
	}

	public abstract FullnessE getFullness();

	public BufferedImage getImage() {
		var f = getFullness();
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

	public void receiveComponent(AComposant comp) {
		
		ComposantE c;
		
		if (comp instanceof Aile)
			c = ComposantE.AILE;
		else if (comp instanceof Avion)
			c = ComposantE.AVION;
		else if (comp instanceof Metal)
			c = ComposantE.METAL;
		else
			c =  ComposantE.MOTEUR;
		for(int i = 0; i < entries.size(); ++i) {
			var e = entries.get(i);
			e.tryAddComp(c);
		}
		
	}

}
