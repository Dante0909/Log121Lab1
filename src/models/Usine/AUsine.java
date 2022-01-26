package models.Usine;

import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import models.AObjet;
import models.Composant.ComposantE;
import models.Composant.EntryComponent;

public abstract class AUsine extends AObjet {
	private int id;
	private int interval;
	private FullnessE fullness;
	private ArrayList<EntryComponent> entries;
	private ComposantE sortie;
	private ArrayList<BufferedImage> images;
	public AUsine(ArrayList<String> p, int i, ArrayList<EntryComponent> ec, ComposantE product, int id, int x, int y) throws Exception {
		super.setPos(x, y);
		interval = i;
		setFullness(FullnessE.VIDE);
		entries = ec;
		sortie = product;
		setImages(p);
		this.id = id;
	}

	public int getInterval() {
		return interval;

	}

	public FullnessE getFullness() {
		return fullness;
	}

	public void setFullness(FullnessE f) {
		fullness = f;
	}

	public ArrayList<EntryComponent> getEntries() {
		return entries;
	}

	
	public ComposantE getSortie() {
		return sortie;
	}	
	public BufferedImage getImage (FullnessE f) {
		if(f == null) return images.get(0);
		switch(f) {
		case VIDE:
			return images.get(0);
		case UN_TIER:
			return images.get(1);
			
		case DEUX_TIERS:
			return images.get(2);
			
		case PLEIN:
			return images.get(3);
			default: return images.get(0);
		}
	}
	private void setImages(ArrayList<String> p) {
		images = new ArrayList<BufferedImage>();
		for(int i = 0; i < p.size();++i) {
			
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
}
