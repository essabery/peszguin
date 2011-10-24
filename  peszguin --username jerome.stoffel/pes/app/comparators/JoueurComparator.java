package comparators;

import java.util.Comparator;

import models.Joueur;

import org.apache.commons.collections.comparators.ComparatorChain;

public class JoueurComparator implements Comparator<Joueur> {

	@Override
	public int compare(Joueur joueur1, Joueur joueur2) {
		if (joueur1.nom == null && joueur2.nom != null) {
			return -1;
		} else if (joueur2.nom == null && joueur1.nom != null) {
			return 1;
		} else {
			int compareNom = joueur1.nom.compareTo(joueur2.nom);
			if (compareNom != 0) {
				return compareNom;
			} else {
				if (joueur1.prenom == null && joueur2.prenom != null) {
					return -1;
				} else if (joueur2.prenom == null && joueur1.prenom != null) {
					return 1;
				} else {
					return joueur1.prenom.compareTo(joueur2.prenom);
				}
			}
		}
	}

}
