package models.Usine;

import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import models.AObjet;
import models.Composant.ComposantE;

public abstract class AUsine extends AObjet {
	private int id;
	private ArrayList<String> paths;
	private int interval;
	private FullnessE fullness;
	private ArrayList<EntryComponent> entries;
	private ComposantE sortie;
	private ArrayList<BufferedImage> images;
	public AUsine(ArrayList<String> p, int i, ArrayList<EntryComponent> ec, ComposantE product, int id, int x, int y) throws Exception {
		super(p);
		super.setPos(x, y);
		setPaths(p);
		setInterval(i);
		setFullness(FullnessE.VIDE);
		setEntries(ec);
		setSortie(product);
		setImages(paths);
		this.id = id;
	}

	public ArrayList<String> getPaths() {
		return paths;
	}

	private void setPaths(ArrayList<String> p) {
		if (p != null && p.size() > 0) {
			paths = p;
		}

	}

	public int getInterval() {
		return interval;

	}

	private void setInterval(int i) {
		interval = i;
	}

	public FullnessE getFullness() {
		return fullness;
	}

	private void setFullness(FullnessE f) {
		fullness = f;
	}

	public ArrayList<EntryComponent> getEntries() {
		return entries;
	}

	private void setEntries(ArrayList<EntryComponent> ec) {
		entries = ec;
	}
	public ComposantE getSortie() {
		return sortie;
	}
	private void setSortie(ComposantE s) {
		sortie = s;
	}
	public BufferedImage getImage (FullnessE f) {
		
		switch(f) {
		case VIDE:
			return images.get(0);
		case UN_TIERS:
			return images.get(0);
			
		case DEUX_TIERS:
			return images.get(0);
			
		case PLEIN:
			return images.get(0);
			default: return null;
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
