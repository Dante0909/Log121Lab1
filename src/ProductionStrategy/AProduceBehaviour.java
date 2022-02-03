package ProductionStrategy;

import java.util.ArrayList;
import models.Composant.EntryComponent;

public abstract class AProduceBehaviour {
	
	public boolean tryProduce(ArrayList<EntryComponent> entries) {
		if(entries == null) return true;
		for(int i = 0; i < entries.size(); ++i) {
			if(!entries.get(i).isReady()) return false;
		}
		return true;
	}
}
