package pes.service.comparator;

import java.util.Comparator;

import pes.domaine.Evenement;

public class EvenementComparator implements Comparator<Evenement> {

	@Override
	public int compare(Evenement o1, Evenement o2) {
		if (o1.getMinute() == null && o2.getMinute() == null) {
			return o1.getType().getCode().compareTo(o2.getType().getCode());
		}
		if (o1.getMinute() == null && o2.getMinute() != null) {
			return 1;
		}
		if (o1.getMinute() != null && o2.getMinute() == null) {
			return -1;
		}
		return o1.getMinute().compareTo(o2.getMinute());
	}

}
