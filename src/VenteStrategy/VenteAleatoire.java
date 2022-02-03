package VenteStrategy;

import java.util.Random;

public class VenteAleatoire implements IVenteStrategy {

	private Random r = new Random();
	private int bound;
	public VenteAleatoire() {
		bound = 1000;
	}
	public VenteAleatoire(int bound) {
		this.bound = bound;
	}
	
	@Override
	public boolean VenteStrategy() {
		// TODO Auto-generated method stub
		int rand = r.nextInt(bound);
		return rand == 1;
	}

}
