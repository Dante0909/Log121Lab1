package VenteStrategy;

public class VenteFixe implements IVenteStrategy {

	private int timer;
	private int maxTimer;
	public VenteFixe(int timer) {
		maxTimer = timer;
		this.timer = maxTimer;
	}
	public VenteFixe() {
		maxTimer = 1500;
		this.timer = maxTimer;
	}
	@Override
	public boolean VenteStrategy() {
		// TODO Auto-generated method stub
		--timer;
		if(timer == 0) {
			timer = maxTimer;
			return true;
		}
		return false;
	}

}
