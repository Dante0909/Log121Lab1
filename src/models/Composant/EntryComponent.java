package models.Composant;

public class EntryComponent {

	private ComposantE type;
	private int amountNeeded;
	private int currentAmount;

	
	public EntryComponent(ComposantE t, int a) {
		// TODO Auto-generated constructor stub
		type = t;
		amountNeeded = a;
	}

	public ComposantE getType() {
		return type;
	}

	public int getAmount() {
		return amountNeeded;
	}

	public static ComposantE getEntry(String c) {
		switch (c) {
		case "avion":
			return ComposantE.AVION;
		case "aile":
			return ComposantE.AILE;
		case "moteur":
			return ComposantE.MOTEUR;
		case "metal":
			return ComposantE.METAL;
		default:
			return null;
		}

	}
}
