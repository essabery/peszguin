package pes.domaine;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Participant implements Serializable {
	private Integer id;
	private String pseudo;
	private String nom;
	private String prenom;

	public int getNbVictoires(Tournoi tournoi) {
		int nbVictoires = 0;
		for (Match match : tournoi.getMatchs()) {
			if (match.getGagnant() != null
					&& match.getGagnant().getParticipant().getId().equals(id)) {
				nbVictoires++;
			}
		}
		return nbVictoires;
	}

	public int getNbNuls(Tournoi tournoi) {
		int nbNuls = 0;
		for (Match match : tournoi.getMatchs()) {
			if (match.getGagnant() == null
					&& (match.getEquipeDomicile().getParticipant().getId()
							.equals(id) || match.getEquipeExterieur()
							.getParticipant().getId().equals(id))) {
				nbNuls++;
			}
		}
		return nbNuls;
	}

	public int getNbDefaites(Tournoi tournoi) {
		int nbDefaites = 0;
		for (Match match : tournoi.getMatchs()) {
			if (match.getPerdant() != null
					&& match.getPerdant().getParticipant().getId().equals(id)) {
				nbDefaites++;
			}
		}
		return nbDefaites;
	}

	public int getNbButsMarques(Tournoi tournoi) {
		int nbButsMarques = 0;
		for (Match match : tournoi.getMatchs()) {
			if (match.getEquipeDomicile().getParticipant().getId().equals(id)) {
				nbButsMarques = nbButsMarques + match.getButsDomicile();
			} else if (match.getEquipeExterieur().getParticipant().getId()
					.equals(id)) {
				nbButsMarques = nbButsMarques + match.getButsExterieur();
			}
		}
		return nbButsMarques;
	}

	public int getNbButsEncaisses(Tournoi tournoi) {
		int nbButsEncaisses = 0;
		for (Match match : tournoi.getMatchs()) {
			if (match.getEquipeDomicile().getParticipant().getId().equals(id)) {
				nbButsEncaisses = nbButsEncaisses + match.getButsExterieur();
			} else if (match.getEquipeExterieur().getParticipant().getId()
					.equals(id)) {
				nbButsEncaisses = nbButsEncaisses + match.getButsDomicile();
			}
		}
		return nbButsEncaisses;
	}

	public int getNbEvenements(Tournoi tournoi, ETypeEvenement type) {
		int nbEvenements = 0;
		for (Match match : tournoi.getMatchs()) {
			if (match.getEquipeDomicile().getParticipant().getId().equals(id)
					|| match.getEquipeExterieur().getParticipant().getId()
							.equals(id)) {
				for (Evenement e : match.getEvenements()) {
					if (e.getType() == type
							&& e.getEquipe().getParticipant().getId()
									.equals(id)) {
						nbEvenements++;
					}
				}
			}
		}
		return nbEvenements;
	}

	public Equipe getEquipe(Tournoi tournoi) {
		for (Equipe equipe : tournoi.getEquipes()) {
			if (equipe.getParticipant().getId().equals(id)) {
				return equipe;
			}
		}
		return null;
	}

	public int getClassement(Tournoi tournoi) {
		for (Match match : tournoi.getMatchs()) {
			if (match.getNiveau() == ENiveauMatch.FINALE1) {
				if (match.getGagnant() == null
						&& (match.getEquipeDomicile().getParticipant().getId()
								.equals(id) || match.getEquipeExterieur()
								.getParticipant().getId().equals(id))) {
					return 1;
				} else {
					if (match.getGagnant() != null
							&& match.getGagnant().getParticipant().getId()
									.equals(id)) {
						return 1;
					} else if (match.getPerdant() != null
							&& match.getPerdant().getParticipant().getId()
									.equals(id)) {
						return 2;
					}
				}
			} else if (match.getNiveau() == ENiveauMatch.FINALE3) {
				if (match.getGagnant() == null
						&& (match.getEquipeDomicile().getParticipant().getId()
								.equals(id) || match.getEquipeExterieur()
								.getParticipant().getId().equals(id))) {
					return 3;
				} else {
					if (match.getGagnant() != null
							&& match.getGagnant().getParticipant().getId()
									.equals(id)) {
						return 3;
					} else if (match.getPerdant() != null
							&& match.getPerdant().getParticipant().getId()
									.equals(id)) {
						return 4;
					}
				}
			} else if (match.getNiveau() == ENiveauMatch.FINALE5) {
				if (match.getGagnant() == null
						&& (match.getEquipeDomicile().getParticipant().getId()
								.equals(id) || match.getEquipeExterieur()
								.getParticipant().getId().equals(id))) {
					return 5;
				} else {
					if (match.getGagnant() != null
							&& match.getGagnant().getParticipant().getId()
									.equals(id)) {
						return 5;
					} else if (match.getPerdant() != null
							&& match.getPerdant().getParticipant().getId()
									.equals(id)) {
						return 6;
					}
				}
			} else if (match.getNiveau() == ENiveauMatch.FINALE7) {
				if (match.getGagnant() == null
						&& (match.getEquipeDomicile().getParticipant().getId()
								.equals(id) || match.getEquipeExterieur()
								.getParticipant().getId().equals(id))) {
					return 7;
				} else {
					if (match.getGagnant() != null
							&& match.getGagnant().getParticipant().getId()
									.equals(id)) {
						return 7;
					} else if (match.getPerdant() != null
							&& match.getPerdant().getParticipant().getId()
									.equals(id)) {
						return 8;
					}
				}
			}
		}
		return 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPrenomPseudoNom() {
		if (pseudo != null) {
			return prenom + " '" + pseudo + "' " + nom;
		} else {
			return prenom + " " + nom;
		}

	}
}
