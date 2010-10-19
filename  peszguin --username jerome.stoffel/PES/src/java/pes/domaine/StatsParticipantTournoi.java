package pes.domaine;

import java.util.Set;

public class StatsParticipantTournoi {

	private Participant participant;
	private Tournoi tournoi;

	private int nbMatchs;
	private int nbMatchsDom;
	private int nbMatchsExt;
	private int nbMatchsFinale;

	private int nbVictoires;
	private int nbVictoiresDom;
	private int nbVictoiresExt;
	private int nbVictoiresFinale;

	private int nbDefaites;
	private int nbDefaitesDom;
	private int nbDefaitesExt;
	private int nbDefaitesFinale;

	private int nbNuls;
	private int nbNulsDom;
	private int nbNulsExt;

	private int nbButsMarques;
	private int nbButsMarquesDom;
	private int nbButsMarquesExt;
	private int nbButsMarquesFinale;

	private int nbButsEncaisses;
	private int nbButsEncaissesDom;
	private int nbButsEncaissesExt;
	private int nbButsEncaissesFinale;

	private int nbCartonsJaunes;
	private int nbCartonsJaunesDom;
	private int nbCartonsJaunesExt;
	private int nbCartonsJaunesFinale;

	private int nbCartonsRouges;
	private int nbCartonsRougesDom;
	private int nbCartonsRougesExt;
	private int nbCartonsRougesFinale;

	private int nbBlessures;
	private int nbBlessuresDom;
	private int nbBlessuresExt;
	private int nbBlessuresFinale;

	private int nbBlessuresInfligees;
	private int nbBlessuresInfligeesDom;
	private int nbBlessuresInfligeesExt;
	private int nbBlessuresInfligeesFinale;

	public StatsParticipantTournoi(Participant participant, Tournoi tournoi) {
		this.participant = participant;
		this.tournoi = tournoi;
		
		nbMatchs = 0;
		nbMatchsDom = 0;
		nbMatchsExt = 0;
		nbMatchsFinale = 0;

		nbVictoires = 0;
		nbVictoiresDom = 0;
		nbVictoiresExt = 0;
		nbVictoiresFinale = 0;

		nbDefaites = 0;
		nbDefaitesDom = 0;
		nbDefaitesExt = 0;
		nbDefaitesFinale = 0;

		nbNuls = 0;
		nbNulsDom = 0;
		nbNulsExt = 0;

		nbButsMarques = 0;
		nbButsMarquesDom = 0;
		nbButsMarquesExt = 0;
		nbButsMarquesFinale = 0;

		nbButsEncaisses = 0;
		nbButsEncaissesDom = 0;
		nbButsEncaissesExt = 0;
		nbButsEncaissesFinale = 0;

		nbCartonsJaunes = 0;
		nbCartonsJaunesDom = 0;
		nbCartonsJaunesExt = 0;
		nbCartonsJaunesFinale = 0;

		nbCartonsRouges = 0;
		nbCartonsRougesDom = 0;
		nbCartonsRougesExt = 0;
		nbCartonsRougesFinale = 0;

		nbBlessures = 0;
		nbBlessuresDom = 0;
		nbBlessuresExt = 0;
		nbBlessuresFinale = 0;

		nbBlessuresInfligees = 0;
		nbBlessuresInfligeesDom = 0;
		nbBlessuresInfligeesExt = 0;
		nbBlessuresInfligeesFinale = 0;

		for (Match match : tournoi.getMatchs()) {
			if (match.isPlaying(participant)) {
				nbMatchs++;
				Set<Evenement> evenements = match.getEvenements();
				if (match.isFinale()) {
					nbMatchsFinale++;
					if (match.getGagnant().getParticipant().getId().equals(participant.getId())) {
						nbVictoires++;
						nbVictoiresFinale++;
					} else {
						nbDefaites++;
						nbDefaitesFinale++;
					}
					for (Evenement evenement : evenements) {
						switch (evenement.getType()) {
						case BUT:
							if (evenement.getEquipe().getParticipant().getId()
									.equals(participant.getId())) {
								nbButsMarques++;
								nbButsMarquesFinale++;
							} else {
								nbButsEncaisses++;
								nbButsEncaissesFinale++;
							}
							break;
						case CARTON_JAUNE:
							if (evenement.getEquipe().getParticipant().getId()
									.equals(participant.getId())) {
								nbCartonsJaunes++;
								nbCartonsJaunesFinale++;
							}
							break;
						case CARTON_ROUGE:
							if (evenement.getEquipe().getParticipant().getId()
									.equals(participant.getId())) {
								nbCartonsRouges++;
								nbCartonsRougesFinale++;
							}
							break;
						case XBLESSURE:
							if (evenement.getEquipe().getParticipant().getId()
									.equals(participant.getId())) {
								nbBlessures++;
								nbBlessuresFinale++;
							} else {
								nbBlessuresInfligees++;
								nbBlessuresInfligeesFinale++;
							}
							break;
						default:
							break;
						} // fin switch
					} // fin for evenements
				} // fin if finale
				else if (match.isDomicile(participant)) {
					nbMatchsDom++;
					if (match.getGagnant() == null) {
						nbNuls++;
						nbNulsDom++;
					} else if (match.getGagnant().getParticipant().getId()
							.equals(participant.getId())) {
						nbVictoires++;
						nbVictoiresDom++;
					} else {
						nbDefaites++;
						nbDefaitesDom++;
					}
					for (Evenement evenement : evenements) {
						switch (evenement.getType()) {
						case BUT:
							if (evenement.getEquipe().getParticipant().getId()
									.equals(participant.getId())) {
								nbButsMarques++;
								nbButsMarquesDom++;
							} else {
								nbButsEncaisses++;
								nbButsEncaissesDom++;
							}
							break;
						case CARTON_JAUNE:
							if (evenement.getEquipe().getParticipant().getId()
									.equals(participant.getId())) {
								nbCartonsJaunes++;
								nbCartonsJaunesDom++;
							}
							break;
						case CARTON_ROUGE:
							if (evenement.getEquipe().getParticipant().getId()
									.equals(participant.getId())) {
								nbCartonsRouges++;
								nbCartonsRougesDom++;
							}
							break;
						case XBLESSURE:
							if (evenement.getEquipe().getParticipant().getId()
									.equals(participant.getId())) {
								nbBlessures++;
								nbBlessuresDom++;
							} else {
								nbBlessuresInfligees++;
								nbBlessuresInfligeesDom++;
							}
							break;
						default:
							break;
						} // fin switch
					} // fin for evenements
				} // fin if domicile
				else if (match.isExterieur(participant)) {
					nbMatchsExt++;
					if (match.getGagnant() == null) {
						nbNuls++;
						nbNulsExt++;
					} else if (match.getGagnant().getParticipant().getId()
							.equals(participant.getId())) {
						nbVictoires++;
						nbVictoiresExt++;
					} else {
						nbDefaites++;
						nbDefaitesExt++;
					}
					for (Evenement evenement : evenements) {
						switch (evenement.getType()) {
						case BUT:
							if (evenement.getEquipe().getParticipant().getId()
									.equals(participant.getId())) {
								nbButsMarques++;
								nbButsMarquesExt++;
							} else {
								nbButsEncaisses++;
								nbButsEncaissesExt++;
							}
							break;
						case CARTON_JAUNE:
							if (evenement.getEquipe().getParticipant().getId()
									.equals(participant.getId())) {
								nbCartonsJaunes++;
								nbCartonsJaunesExt++;
							}
							break;
						case CARTON_ROUGE:
							if (evenement.getEquipe().getParticipant().getId()
									.equals(participant.getId())) {
								nbCartonsRouges++;
								nbCartonsRougesExt++;
							}
							break;
						case XBLESSURE:
							if (evenement.getEquipe().getParticipant().getId()
									.equals(participant.getId())) {
								nbBlessures++;
								nbBlessuresExt++;
							} else {
								nbBlessuresInfligees++;
								nbBlessuresInfligeesExt++;
							}
							break;
						default:
							break;
						} // fin switch
					} // fin for evenements
				} // fin if exterieur
			}
		} // fin for matchs
	}

	public int getNbButsMarques() {
		return nbButsMarques;
	}

	public void setNbButsMarques(int nbButsMarques) {
		this.nbButsMarques = nbButsMarques;
	}

	public int getNbButsMarquesDom() {
		return nbButsMarquesDom;
	}

	public void setNbButsMarquesDom(int nbButsMarquesDom) {
		this.nbButsMarquesDom = nbButsMarquesDom;
	}

	public int getNbButsMarquesExt() {
		return nbButsMarquesExt;
	}

	public void setNbButsMarquesExt(int nbButsMarquesExt) {
		this.nbButsMarquesExt = nbButsMarquesExt;
	}

	public int getNbButsMarquesFinale() {
		return nbButsMarquesFinale;
	}

	public void setNbButsMarquesFinale(int nbButsMarquesFinale) {
		this.nbButsMarquesFinale = nbButsMarquesFinale;
	}

	public int getNbButsEncaisses() {
		return nbButsEncaisses;
	}

	public void setNbButsEncaisses(int nbButsEncaisses) {
		this.nbButsEncaisses = nbButsEncaisses;
	}

	public int getNbButsEncaissesDom() {
		return nbButsEncaissesDom;
	}

	public void setNbButsEncaissesDom(int nbButsEncaissesDom) {
		this.nbButsEncaissesDom = nbButsEncaissesDom;
	}

	public int getNbButsEncaissesExt() {
		return nbButsEncaissesExt;
	}

	public void setNbButsEncaissesExt(int nbButsEncaissesExt) {
		this.nbButsEncaissesExt = nbButsEncaissesExt;
	}

	public int getNbButsEncaissesFinale() {
		return nbButsEncaissesFinale;
	}

	public void setNbButsEncaissesFinale(int nbButsEncaissesFinale) {
		this.nbButsEncaissesFinale = nbButsEncaissesFinale;
	}

	public int getNbCartonsJaunes() {
		return nbCartonsJaunes;
	}

	public void setNbCartonsJaunes(int nbCartonsJaunes) {
		this.nbCartonsJaunes = nbCartonsJaunes;
	}

	public int getNbCartonsJaunesDom() {
		return nbCartonsJaunesDom;
	}

	public void setNbCartonsJaunesDom(int nbCartonsJaunesDom) {
		this.nbCartonsJaunesDom = nbCartonsJaunesDom;
	}

	public int getNbCartonsJaunesExt() {
		return nbCartonsJaunesExt;
	}

	public void setNbCartonsJaunesExt(int nbCartonsJaunesExt) {
		this.nbCartonsJaunesExt = nbCartonsJaunesExt;
	}

	public int getNbCartonsJaunesFinale() {
		return nbCartonsJaunesFinale;
	}

	public void setNbCartonsJaunesFinale(int nbCartonsJaunesFinale) {
		this.nbCartonsJaunesFinale = nbCartonsJaunesFinale;
	}

	public int getNbCartonsRouges() {
		return nbCartonsRouges;
	}

	public void setNbCartonsRouges(int nbCartonsRouges) {
		this.nbCartonsRouges = nbCartonsRouges;
	}

	public int getNbCartonsRougesDom() {
		return nbCartonsRougesDom;
	}

	public void setNbCartonsRougesDom(int nbCartonsRougesDom) {
		this.nbCartonsRougesDom = nbCartonsRougesDom;
	}

	public int getNbCartonsRougesExt() {
		return nbCartonsRougesExt;
	}

	public void setNbCartonsRougesExt(int nbCartonsRougesExt) {
		this.nbCartonsRougesExt = nbCartonsRougesExt;
	}

	public int getNbCartonsRougesFinale() {
		return nbCartonsRougesFinale;
	}

	public void setNbCartonsRougesFinale(int nbCartonsRougesFinale) {
		this.nbCartonsRougesFinale = nbCartonsRougesFinale;
	}

	public int getNbBlessures() {
		return nbBlessures;
	}

	public void setNbBlessures(int nbBlessures) {
		this.nbBlessures = nbBlessures;
	}

	public int getNbBlessuresDom() {
		return nbBlessuresDom;
	}

	public void setNbBlessuresDom(int nbBlessuresDom) {
		this.nbBlessuresDom = nbBlessuresDom;
	}

	public int getNbBlessuresExt() {
		return nbBlessuresExt;
	}

	public void setNbBlessuresExt(int nbBlessuresExt) {
		this.nbBlessuresExt = nbBlessuresExt;
	}

	public int getNbBlessuresFinale() {
		return nbBlessuresFinale;
	}

	public void setNbBlessuresFinale(int nbBlessuresFinale) {
		this.nbBlessuresFinale = nbBlessuresFinale;
	}

	public int getNbBlessuresInfligees() {
		return nbBlessuresInfligees;
	}

	public void setNbBlessuresInfligees(int nbBlessuresInfligees) {
		this.nbBlessuresInfligees = nbBlessuresInfligees;
	}

	public int getNbBlessuresInfligeesDom() {
		return nbBlessuresInfligeesDom;
	}

	public void setNbBlessuresInfligeesDom(int nbBlessuresInfligeesDom) {
		this.nbBlessuresInfligeesDom = nbBlessuresInfligeesDom;
	}

	public int getNbBlessuresInfligeesExt() {
		return nbBlessuresInfligeesExt;
	}

	public void setNbBlessuresInfligeesExt(int nbBlessuresInfligeesExt) {
		this.nbBlessuresInfligeesExt = nbBlessuresInfligeesExt;
	}

	public int getNbBlessuresInfligeesFinale() {
		return nbBlessuresInfligeesFinale;
	}

	public void setNbBlessuresInfligeesFinale(int nbBlessuresInfligeesFinale) {
		this.nbBlessuresInfligeesFinale = nbBlessuresInfligeesFinale;
	}

	public int getNbMatchs() {
		return nbMatchs;
	}

	public void setNbMatchs(int nbMatchs) {
		this.nbMatchs = nbMatchs;
	}

	public int getNbMatchsDom() {
		return nbMatchsDom;
	}

	public void setNbMatchsDom(int nbMatchsDom) {
		this.nbMatchsDom = nbMatchsDom;
	}

	public int getNbMatchsExt() {
		return nbMatchsExt;
	}

	public void setNbMatchsExt(int nbMatchsExt) {
		this.nbMatchsExt = nbMatchsExt;
	}

	public int getNbMatchsFinale() {
		return nbMatchsFinale;
	}

	public void setNbMatchsFinale(int nbMatchsFinale) {
		this.nbMatchsFinale = nbMatchsFinale;
	}

	public int getNbVictoires() {
		return nbVictoires;
	}

	public void setNbVictoires(int nbVictoires) {
		this.nbVictoires = nbVictoires;
	}

	public int getNbVictoiresDom() {
		return nbVictoiresDom;
	}

	public void setNbVictoiresDom(int nbVictoiresDom) {
		this.nbVictoiresDom = nbVictoiresDom;
	}

	public int getNbVictoiresExt() {
		return nbVictoiresExt;
	}

	public void setNbVictoiresExt(int nbVictoiresExt) {
		this.nbVictoiresExt = nbVictoiresExt;
	}

	public int getNbVictoiresFinale() {
		return nbVictoiresFinale;
	}

	public void setNbVictoiresFinale(int nbVictoiresFinale) {
		this.nbVictoiresFinale = nbVictoiresFinale;
	}

	public int getNbDefaites() {
		return nbDefaites;
	}

	public void setNbDefaites(int nbDefaites) {
		this.nbDefaites = nbDefaites;
	}

	public int getNbDefaitesDom() {
		return nbDefaitesDom;
	}

	public void setNbDefaitesDom(int nbDefaitesDom) {
		this.nbDefaitesDom = nbDefaitesDom;
	}

	public int getNbDefaitesExt() {
		return nbDefaitesExt;
	}

	public void setNbDefaitesExt(int nbDefaitesExt) {
		this.nbDefaitesExt = nbDefaitesExt;
	}

	public int getNbDefaitesFinale() {
		return nbDefaitesFinale;
	}

	public void setNbDefaitesFinale(int nbDefaitesFinale) {
		this.nbDefaitesFinale = nbDefaitesFinale;
	}

	public int getNbNuls() {
		return nbNuls;
	}

	public void setNbNuls(int nbNuls) {
		this.nbNuls = nbNuls;
	}

	public int getNbNulsDom() {
		return nbNulsDom;
	}

	public void setNbNulsDom(int nbNulsDom) {
		this.nbNulsDom = nbNulsDom;
	}

	public int getNbNulsExt() {
		return nbNulsExt;
	}

	public void setNbNulsExt(int nbNulsExt) {
		this.nbNulsExt = nbNulsExt;
	}
}
