package pes.service.comparator;

import java.util.Comparator;

import pes.domaine.Joueur;


public class JoueurComparator implements Comparator<Joueur> {

	@Override
	public int compare(Joueur o1, Joueur o2) {
		if (o1.getNom().compareTo(o2.getNom()) != 0) {
			return o1.getNom().compareTo(o2.getNom());
		} else {
			return o1.getPrenom().compareTo(o2.getPrenom());
		}
	}

}
