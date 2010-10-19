package pes.service.comparator;

import java.util.Comparator;

import pes.domaine.Equipe;

public class EquipeComparator implements Comparator<Equipe> {

	@Override
	public int compare(Equipe o1, Equipe o2) {
		return o1.getLibelle().compareTo(o2.getLibelle());
	}

}
