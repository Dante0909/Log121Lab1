package ProductionStrategy;

import java.util.ArrayList;
import java.util.Random;

import models.Composant.EntryComponent;

public class ProduceHighlyFilled extends AProduceBehaviour {
	
	Random r = new Random();
	
	@Override
	public boolean tryProduce(ArrayList<EntryComponent> entries) {
		var b = super.tryProduce(entries);
		var rand = r.nextInt(101);
		return b && rand > 66;
	}
}
