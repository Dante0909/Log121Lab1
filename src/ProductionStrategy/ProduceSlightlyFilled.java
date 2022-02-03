package ProductionStrategy;

import java.util.ArrayList;
import java.util.Random;  
import models.Composant.EntryComponent;

public class ProduceSlightlyFilled extends AProduceBehaviour {

	Random r = new Random();
	@Override
	public boolean tryProduce(ArrayList<EntryComponent> entries) {
		var b = super.tryProduce(entries);
		int rand = r.nextInt(101);
		return b && rand > 33;
	}

}
