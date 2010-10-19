package pes.service.comparator;

import java.util.Comparator;

import pes.domaine.ETypeEvenement;
import pes.domaine.Joueur;
import pes.domaine.Tournoi;

public class BoucherComparator implements Comparator<Joueur> {

	private Tournoi tournoi;

	public BoucherComparator(Tournoi tournoi) {
		this.tournoi = tournoi;
	}

	@Override
	public int compare(Joueur o1, Joueur o2) {
		if (o1.getEvenements(tournoi, ETypeEvenement.CARTON_ROUGE).size() > o2
				.getEvenements(tournoi, ETypeEvenement.CARTON_ROUGE).size()) {
			return -1;
		} else if (o1.getEvenements(tournoi, ETypeEvenement.CARTON_ROUGE)
				.size() < o2
				.getEvenements(tournoi, ETypeEvenement.CARTON_ROUGE).size()) {
			return 1;
		} else {
			if (o1.getEvenements(tournoi, ETypeEvenement.CARTON_JAUNE).size() > o2
					.getEvenements(tournoi, ETypeEvenement.CARTON_JAUNE).size()) {
				return -1;
			} else if (o1.getEvenements(tournoi, ETypeEvenement.CARTON_JAUNE)
					.size() < o2.getEvenements(tournoi,
					ETypeEvenement.CARTON_JAUNE).size()) {
				return 1;
			} else {
				return o1.getNom().compareTo(o2.getNom());
			}
		}
	}

}
