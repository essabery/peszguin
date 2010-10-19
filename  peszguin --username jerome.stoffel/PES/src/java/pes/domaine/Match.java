package pes.domaine;

import java.io.Serializable;
import java.util.Set;

@SuppressWarnings("serial")
public class Match implements Serializable {
	private Integer id;
	private ENiveauMatch niveau;
	private Equipe equipeDomicile;
	private Equipe equipeExterieur;
	private Tournoi tournoi;
	private Set<Evenement> evenements;
	private Integer tabDom;
	private Integer tabExt;
	private boolean termine;

	public Match() {

	}

	public Match(ENiveauMatch niveau, Equipe domicile, Equipe exterieur,
			Tournoi tournoi) {
		this.niveau = niveau;
		this.equipeDomicile = domicile;
		this.equipeExterieur = exterieur;
		this.tournoi = tournoi;
		this.termine = false;
	}

	public ENiveauMatch getNiveau() {
		return niveau;
	}

	public void setNiveau(ENiveauMatch niveau) {
		this.niveau = niveau;
	}

	public Equipe getEquipeDomicile() {
		return equipeDomicile;
	}

	public void setEquipeDomicile(Equipe equipeDomicile) {
		this.equipeDomicile = equipeDomicile;
	}

	public Equipe getEquipeExterieur() {
		return equipeExterieur;
	}

	public void setEquipeExterieur(Equipe equipeExterieur) {
		this.equipeExterieur = equipeExterieur;
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

	public Set<Evenement> getEvenements() {
		return evenements;
	}

	public void setEvenements(Set<Evenement> evenements) {
		this.evenements = evenements;
	}

	public boolean isTermine() {
		return termine;
	}

	public void setTermine(boolean termine) {
		this.termine = termine;
	}

	public String getScore() {
		if (!termine) {
			return "N/A";
		}
		String score = "";
		int butDom = 0;
		int butExt = 0;
		if (evenements == null) {
			score = "0-0";
		} else {
			for (Evenement evenement : evenements) {
				if (evenement.getType() == ETypeEvenement.BUT) {
					if (evenement.getEquipe().getId().equals(
							equipeDomicile.getId())) {
						butDom++;
					} else {
						butExt++;
					}
				}
			}
			score = butDom + "-" + butExt;
		}

		if (tabDom != null) {
			score = score + " (" + tabDom + " tab " + tabExt + ")";
		}
		return score;
	}

	public int getButsDomicile() {
		int result = 0;
		if (evenements == null) {
			return 0;
		}
		for (Evenement evenement : evenements) {
			if (evenement.getType() == ETypeEvenement.BUT) {
				if (evenement.getEquipe().getId()
						.equals(equipeDomicile.getId())) {
					result++;
				}
			}
		}
		return result;
	}

	public int getButsExterieur() {
		int result = 0;
		if (evenements == null) {
			return 0;
		}
		for (Evenement evenement : evenements) {
			if (evenement.getType() == ETypeEvenement.BUT) {
				if (evenement.getEquipe().getId().equals(
						equipeExterieur.getId())) {
					result++;
				}
			}
		}
		return result;
	}

	public int getButs(Equipe e) {
		if (e.getId().equals(equipeDomicile.getId())) {
			return getButsDomicile();
		} else if (e.getId().equals(equipeExterieur.getId())) {
			return getButsExterieur();
		} else {
			return 0;
		}
	}

	public int getButsContre(Equipe e) {
		if (e.getId().equals(equipeDomicile.getId())) {
			return getButsExterieur();
		} else if (e.getId().equals(equipeExterieur.getId())) {
			return getButsDomicile();
		} else {
			return 0;
		}
	}

	public int getPoints(Equipe e) {
		if (!termine) {
			return 0;
		}
		if (evenements == null) {
			return 0;
		}
		if (e.getId().equals(equipeDomicile.getId())) {
			if (getButs(e) > getButs(equipeExterieur)) {
				return 3;
			} else if (getButs(e) == getButs(equipeExterieur)) {
				return 1;
			} else {
				return 0;
			}
		} else if (e.getId().equals(equipeExterieur.getId())) {
			if (getButs(e) > getButs(equipeDomicile)) {
				return 3;
			} else if (getButs(e) == getButs(equipeDomicile)) {
				return 1;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}

	public boolean isPoule() {
		if (niveau == ENiveauMatch.POULE1 || niveau == ENiveauMatch.POULE2
				|| niveau == ENiveauMatch.POULE3
				|| niveau == ENiveauMatch.POULE4
				|| niveau == ENiveauMatch.POULE5
				|| niveau == ENiveauMatch.POULE6) {
			return true;
		} else {
			return false;
		}
	}

	public Equipe getGagnant() {
		if (getButs(equipeDomicile) > getButs(equipeExterieur)) {
			return equipeDomicile;
		} else if (getButs(equipeDomicile) < getButs(equipeExterieur)) {
			return equipeExterieur;
		} else {
			if (tabDom != null) {
				if (tabDom > tabExt) {
					return equipeDomicile;
				} else if (tabDom < tabExt) {
					return equipeExterieur;
				}
			}
			return null;
		}
	}

	public Equipe getPerdant() {
		if (getButs(equipeDomicile) > getButs(equipeExterieur)) {
			return equipeExterieur;
		} else if (getButs(equipeDomicile) < getButs(equipeExterieur)) {
			return equipeDomicile;
		} else {
			if (tabDom != null) {
				if (tabDom < tabExt) {
					return equipeDomicile;
				} else if (tabDom > tabExt) {
					return equipeExterieur;
				}
			}
			return null;
		}
	}

	public boolean isPlaying(Participant participant) {
		return (equipeDomicile.getParticipant().getId().equals(
				participant.getId()) || equipeExterieur.getParticipant()
				.getId().equals(participant.getId())) ? true : false;

	}

	public boolean isDomicile(Participant participant) {
		return equipeDomicile.getParticipant().getId().equals(
				participant.getId());
	}

	public boolean isExterieur(Participant participant) {
		return equipeExterieur.getParticipant().getId().equals(
				participant.getId());
	}

	public boolean isFinale() {
		return (niveau == ENiveauMatch.FINALE1
				|| niveau == ENiveauMatch.FINALE3
				|| niveau == ENiveauMatch.FINALE5 || niveau == ENiveauMatch.FINALE7);
	}

	public Integer getTabDom() {
		return tabDom;
	}

	public void setTabDom(Integer tabDom) {
		this.tabDom = tabDom;
	}

	public Integer getTabExt() {
		return tabExt;
	}

	public void setTabExt(Integer tabExt) {
		this.tabExt = tabExt;
	}

}
