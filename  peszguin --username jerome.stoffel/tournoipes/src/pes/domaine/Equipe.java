package pes.domaine;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@SuppressWarnings("serial")
public class Equipe implements Serializable {
	private Integer id;
	private String libelle;
	private Set<Joueur> joueurs;
	private Participant participant;
	private Tournoi tournoi;
	private String poule;
	private Integer position;

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Set<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(Set<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	public String getPoule() {
		return poule;
	}

	public void setPoule(String poule) {
		this.poule = poule;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Tournoi getTournoi() {
		return tournoi;
	}

	public void setTournoi(Tournoi tournoi) {
		this.tournoi = tournoi;
	}

	public int getPoints() {
		if (tournoi == null) {
			return 0;
		}
		List<Match> matchs = tournoi.findMatchsPoule(this);
		if (matchs == null) {
			return 0;
		}
		int points = 0;

		for (Match match : matchs) {
			if (match.isTermine()) {
				points = points + match.getPoints(this);
			}
		}
		return points;
	}

	public int getVictoiresPoule() {
		if (tournoi == null) {
			return 0;
		}
		List<Match> matchs = tournoi.findMatchsPoule(this);
		if (matchs == null) {
			return 0;
		}
		int nbVictoires = 0;

		for (Match match : matchs) {
			if (match.isTermine()) {
				if (match.getGagnant() != null
						&& match.getGagnant().equals(this)) {
					nbVictoires++;
				}
			}
		}
		return nbVictoires;
	}

	public int getNulsPoule() {
		if (tournoi == null) {
			return 0;
		}
		List<Match> matchs = tournoi.findMatchsPoule(this);
		if (matchs == null) {
			return 0;
		}
		int nbNuls = 0;

		for (Match match : matchs) {
			if (match.isTermine()) {
				if (match.getGagnant() == null) {
					nbNuls++;
				}
			}
		}
		return nbNuls;
	}

	public int getDefaitesPoule() {
		if (tournoi == null) {
			return 0;
		}
		List<Match> matchs = tournoi.findMatchsPoule(this);
		if (matchs == null) {
			return 0;
		}
		int nbDefaites = 0;

		for (Match match : matchs) {
			if (match.isTermine()) {
				if (match.getGagnant() != null
						&& !match.getGagnant().equals(this)) {
					nbDefaites++;
				}
			}
		}
		return nbDefaites;
	}

	public int getButsPoule() {
		if (tournoi == null) {
			return 0;
		}
		List<Match> matchs = tournoi.findMatchsPoule(this);
		if (matchs == null) {
			return 0;
		}
		int nbBP = 0;

		for (Match match : matchs) {
			if (match.isTermine()) {
				nbBP = nbBP + match.getButs(this);
			}
		}
		return nbBP;
	}

	public int getButsContrePoule() {
		if (tournoi == null) {
			return 0;
		}
		List<Match> matchs = tournoi.findMatchsPoule(this);
		if (matchs == null) {
			return 0;
		}
		int nbBC = 0;

		for (Match match : matchs) {
			if (match.isTermine()) {
				nbBC = nbBC + match.getButsContre(this);
			}
		}
		return nbBC;
	}

	public int getDifference() {
		return getButsPoule() - getButsContrePoule();
	}

	public String getDifferencePouleFormate() {
		int diff = getButsPoule() - getButsContrePoule();
		if (diff <= 0) {
			return "" + diff;
		} else {
			return "+" + diff;
		}
	}
}
