package pes.service.comparator;

import java.util.Comparator;

import pes.domaine.Equipe;

public class EquipeComparatorPoule implements Comparator<Equipe> {

	@Override
	public int compare(Equipe o1, Equipe o2) {
		if (o1.getPoints() > o2.getPoints()) {
			return -1;
		} else if (o1.getPoints() < o2.getPoints()) {
			return 1;
		} else {
			if (o1.getDifference() > o2.getDifference()) {
				return -1;
			} else if (o1.getDifference() < o2.getDifference()) {
				return 1;
			} else {
				if (o1.getButsPoule() > o2.getButsPoule()) {
					return -1;
				} else if (o1.getButsPoule() < o2.getButsPoule()) {
					return 1;
				} else {
					return o1.getParticipant().getPrenom().compareTo(
							o2.getParticipant().getPrenom());
				}
			}
		}
	}
}
