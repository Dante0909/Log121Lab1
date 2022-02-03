package ProductionStrategy;

import java.util.ArrayList;

import models.Composant.EntryComponent;

public class ProduceFilled extends AProduceBehaviour {
	@Override
	public boolean tryProduce(ArrayList<EntryComponent> entries) {
		return false;
	}
}
