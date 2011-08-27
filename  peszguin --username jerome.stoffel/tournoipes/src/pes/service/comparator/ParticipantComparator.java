package pes.service.comparator;

import java.util.Comparator;

import pes.domaine.Participant;

public class ParticipantComparator implements Comparator<Participant> {

	@Override
	public int compare(Participant o1, Participant o2) {
		if (o1.getPrenom().compareTo(o2.getPrenom()) != 0) {
			return o1.getPrenom().compareTo(o2.getPrenom());
		} else {
			return o1.getNom().compareTo(o2.getNom());
		}
	}
}
