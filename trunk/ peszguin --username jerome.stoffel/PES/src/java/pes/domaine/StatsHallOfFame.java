package pes.domaine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsHallOfFame {
	List<Tournoi> tournois;

	// hall of fame général
	private int tournoisJouesValeur = 0;
	private String tournoisJouesParticipant = "";
	private final String cleTournoisJoues = "tournoisJoues";

	private int tournoisGagnesValeur = 0;
	private String tournoisGagnesParticipant = "";
	private final String cleTournoisGagnes = "tournoisGagnes";

	private String resultatPoulesValeur = "";
	private String resultatPoulesParticipant = "";
	private final String cleResultatsPoules = "resultatsPoules";

	private String finaleProlifiqueValeur = "";
	private String finaleProlifiqueParticipant = "";
	private final String cleFinaleProlifique = "finaleProlifique";

	private int profiteurCSCValeur = 0;
	private String profiteurCSCParticipant = "";
	private final String cleProfiteurCSC = "profiteurCSC";

	private int victimeCSCValeur = 0;
	private String victimeCSCParticipant = "";
	private final String cleVictimeCSC = "victimeCSC";

	// hall of fame tournoi
	private int attaqueValeur = 0;
	private String attaqueParticipant = "";
	private final String cleAttaque = "attaque";
	private final String cleAttaqueTournoi = "attaqueTournoi";

	private int defenseValeur = -1;
	private String defenseParticipant = "";
	private final String cleDefense = "defense";
	private final String cleDefenseTournoi = "defenseTournoi";

	private int jaunesValeur = 0;
	private String jaunesParticipant = "";
	private final String cleJaunes = "jaunes";
	private final String cleJaunesTournoi = "jaunesTournoi";

	private int rougesValeur = 0;
	private String rougesParticipant = "";
	private final String cleRouges = "rouges";
	private final String cleRougesTournoi = "rougesTournoi";

	private int blessuresValeur = 0;
	private String blessuresParticipant = "";
	private final String cleBlessures = "blessures";
	private final String cleBlessuresTournoi = "blessuresTournoi";

	private int blessuresInfligeesValeur = 0;
	private String blessuresInfligeesParticipant = "";
	private final String cleBlessuresInfligees = "blessuresInfligees";
	private final String cleBlessuresInfligeesTournoi = "blessuresInfligeesTournoi";

	public StatsHallOfFame(List<Tournoi> tournois) {
		this.tournois = tournois;

		Integer idFinaleProlifique = -1;
		Map<Participant, Map<String, Object>> statsParticipant = new HashMap<Participant, Map<String, Object>>();

		for (Tournoi tournoi : tournois) {
			for (Participant p : tournoi.getParticipants()) {
				int attaqueTournoiCourant = 0;
				int defenseTournoiCourant = 0;
				int jaunesTournoiCourant = 0;
				int rougesTournoiCourant = 0;
				int blessuresTournoiCourant = 0;
				int blessuresInfligeesTournoiCourant = 0;

				Equipe equipe = p.getEquipe(tournoi);

				if (!statsParticipant.containsKey(p)) {
					statsParticipant.put(p, new HashMap<String, Object>());
					statsParticipant.get(p).put(cleTournoisJoues, 0);
					statsParticipant.get(p).put(cleTournoisGagnes, 0);
					statsParticipant.get(p).put(cleResultatsPoules, 0);
					statsParticipant.get(p).put(cleProfiteurCSC, 0);
					statsParticipant.get(p).put(cleVictimeCSC, 0);

					statsParticipant.get(p).put(cleAttaque, 0);
					statsParticipant.get(p).put(cleDefense, -1);
					statsParticipant.get(p).put(cleJaunes, 0);
					statsParticipant.get(p).put(cleRouges, 0);
					statsParticipant.get(p).put(cleBlessures, 0);
					statsParticipant.get(p).put(cleBlessuresInfligees, 0);
				}
				statsParticipant.get(p)
						.put(
								cleTournoisJoues,
								(Integer) statsParticipant.get(p).get(
										cleTournoisJoues) + 1);
				if (p.getClassement(tournoi) == 1) {
					statsParticipant.get(p).put(
							cleTournoisGagnes,
							(Integer) statsParticipant.get(p).get(
									cleTournoisGagnes) + 1);
				}

				for (Match match : tournoi.getMatchs()) {
					if (match.getEvenements() != null && match.isPlaying(p)) {
						for (Evenement event : match.getEvenements()) {
							switch (event.getType()) {
							case BUT:
								if (event.getJoueur().getId().equals(-1)) {
									// CSC pour
									if (event.getEquipe().getId().equals(
											equipe.getId())) {
										statsParticipant
												.get(p)
												.put(
														cleProfiteurCSC,
														(Integer) statsParticipant
																.get(p)
																.get(
																		cleProfiteurCSC) + 1);
									} else {
										// CSC contre
										statsParticipant
												.get(p)
												.put(
														cleVictimeCSC,
														(Integer) statsParticipant
																.get(p)
																.get(
																		cleVictimeCSC) + 1);
									}
								} // fin if CSC
								else {
									if (event.getEquipe().getId().equals(
											equipe.getId())) {
										attaqueTournoiCourant++;
									} else {
										defenseTournoiCourant++;
									}
								}
								break;
							case CARTON_JAUNE:
								if (event.getEquipe().getId().equals(
										equipe.getId())) {
									jaunesTournoiCourant++;
								}
								break;
							case CARTON_ROUGE:
								if (event.getEquipe().getId().equals(
										equipe.getId())) {
									rougesTournoiCourant++;
								}
								break;
							case XBLESSURE:
								if (event.getEquipe().getId().equals(
										equipe.getId())) {
									blessuresTournoiCourant++;
								} else {
									blessuresInfligeesTournoiCourant++;
								}
								break;
							default:
								break;
							}
						} // fin for evenements
					}
				} // fin for matchs

				if (attaqueTournoiCourant >= (Integer) statsParticipant.get(p)
						.get(cleAttaque)) {
					statsParticipant.get(p).put(cleAttaque,
							attaqueTournoiCourant);
					statsParticipant.get(p).put(cleAttaqueTournoi, tournoi);
				}
				if (defenseTournoiCourant <= (Integer) statsParticipant.get(p)
						.get(cleDefense)
						|| statsParticipant.get(p).get(cleDefense).equals(-1)) {
					statsParticipant.get(p).put(cleDefense,
							defenseTournoiCourant);
					statsParticipant.get(p).put(cleDefenseTournoi, tournoi);
				}
				if (jaunesTournoiCourant >= (Integer) statsParticipant.get(p)
						.get(cleJaunes)) {
					statsParticipant.get(p)
							.put(cleJaunes, jaunesTournoiCourant);
					statsParticipant.get(p).put(cleJaunesTournoi, tournoi);
				}
				if (rougesTournoiCourant >= (Integer) statsParticipant.get(p)
						.get(cleRouges)) {
					statsParticipant.get(p)
							.put(cleRouges, rougesTournoiCourant);
					statsParticipant.get(p).put(cleRougesTournoi, tournoi);
				}
				if (blessuresTournoiCourant >= (Integer) statsParticipant
						.get(p).get(cleBlessures)) {
					statsParticipant.get(p).put(cleBlessures,
							blessuresTournoiCourant);
					statsParticipant.get(p).put(cleBlessuresTournoi, tournoi);
				}
				if (blessuresInfligeesTournoiCourant >= (Integer) statsParticipant
						.get(p).get(cleBlessuresInfligees)) {
					statsParticipant.get(p).put(cleBlessuresInfligees,
							blessuresInfligeesTournoiCourant);
					statsParticipant.get(p).put(cleBlessuresInfligeesTournoi,
							tournoi);
				}

			} // fin for participants
		} // fin for tournois

		for (Participant p : statsParticipant.keySet()) {
			// tournois joues
			if ((Integer) statsParticipant.get(p).get(cleTournoisJoues) > tournoisJouesValeur) {
				tournoisJouesValeur = (Integer) statsParticipant.get(p).get(
						cleTournoisJoues);
				tournoisJouesParticipant = "<a href=\"/PES/participant/afficherParticipant.action?idParticipant="
						+ p.getId() + "\" title=\""+ p.getPrenomPseudoNom() +"\" >" + p.getPrenom() + "</a>";
			} else if (((Integer) statsParticipant.get(p).get(cleTournoisJoues))
					.equals(tournoisJouesValeur)) {
				tournoisJouesParticipant = tournoisJouesParticipant
						+ ", <a href=\"/PES/participant/afficherParticipant.action?idParticipant="
						+ p.getId() + "\" title=\""+ p.getPrenomPseudoNom() +"\" >" + p.getPrenom() + "</a>";
			}
			// tournois gagnes
			if ((Integer) statsParticipant.get(p).get(cleTournoisGagnes) > tournoisGagnesValeur) {
				tournoisGagnesValeur = (Integer) statsParticipant.get(p).get(
						cleTournoisGagnes);
				tournoisGagnesParticipant = "<a href=\"/PES/participant/afficherParticipant.action?idParticipant="
						+ p.getId() + "\" title=\""+ p.getPrenomPseudoNom() +"\" >" + p.getPrenom() + "</a>";
			} else if (((Integer) statsParticipant.get(p)
					.get(cleTournoisGagnes)).equals(tournoisGagnesValeur)) {
				tournoisGagnesParticipant = tournoisGagnesParticipant
						+ ", <a href=\"/PES/participant/afficherParticipant.action?idParticipant="
						+ p.getId() + "\" title=\""+ p.getPrenomPseudoNom() +"\" >" + p.getPrenom() + "</a> ";
			}
			// profiteur CSC
			if ((Integer) statsParticipant.get(p).get(cleProfiteurCSC) > profiteurCSCValeur) {
				profiteurCSCValeur = (Integer) statsParticipant.get(p).get(
						cleProfiteurCSC);
				profiteurCSCParticipant = "<a href=\"/PES/participant/afficherParticipant.action?idParticipant="
						+ p.getId() + "\" title=\""+ p.getPrenomPseudoNom() +"\" >" + p.getPrenom() + "</a>";
			} else if (((Integer) statsParticipant.get(p).get(cleProfiteurCSC))
					.equals(profiteurCSCValeur)) {
				profiteurCSCParticipant = profiteurCSCParticipant
						+ ", <a href=\"/PES/participant/afficherParticipant.action?idParticipant="
						+ p.getId() + "\" title=\""+ p.getPrenomPseudoNom() +"\" >" + p.getPrenom() + "</a> ";
			}
			// victime CSC
			if ((Integer) statsParticipant.get(p).get(cleVictimeCSC) > victimeCSCValeur) {
				victimeCSCValeur = (Integer) statsParticipant.get(p).get(
						cleVictimeCSC);
				victimeCSCParticipant = "<a href=\"/PES/participant/afficherParticipant.action?idParticipant="
						+ p.getId() + "\" title=\""+ p.getPrenomPseudoNom() +"\" >" + p.getPrenom() + "</a>";
			} else if (((Integer) statsParticipant.get(p).get(cleVictimeCSC))
					.equals(victimeCSCValeur)) {
				victimeCSCParticipant = victimeCSCParticipant
						+ ", <a href=\"/PES/participant/afficherParticipant.action?idParticipant="
						+ p.getId() + "\" title=\""+ p.getPrenomPseudoNom() +"\" >" + p.getPrenom() + "</a> ";
			}
			// attaque
			if ((Integer) statsParticipant.get(p).get(cleAttaque) > attaqueValeur) {
				attaqueValeur = (Integer) statsParticipant.get(p).get(
						cleAttaque);
				attaqueParticipant = "<a href=\"/PES/participant/afficherParticipant.action?idParticipant="
						+ p.getId()
						+ "\" title=\""+ p.getPrenomPseudoNom() +"\" >"
						+ p.getPrenom()
						+ "</a> (<a href=\"/PES/tournoi/afficherTournoi.action?idTournoi="
						+ ((Tournoi) statsParticipant.get(p).get(
								cleAttaqueTournoi)).getId()
						+ "\">"
						+ ((Tournoi) statsParticipant.get(p).get(
								cleAttaqueTournoi)).getDateFormatee() + "</a>)";
			} else if (((Integer) statsParticipant.get(p).get(cleAttaque))
					.equals(attaqueValeur)) {
				attaqueParticipant = attaqueParticipant
						+ "<BR/> <a href=\"/PES/participant/afficherParticipant.action?idParticipant="
						+ p.getId()
						+ "\" title=\""+ p.getPrenomPseudoNom() +"\" >"
						+ p.getPrenom()
						+ "</a> (<a href=\"/PES/tournoi/afficherTournoi.action?idTournoi="
						+ ((Tournoi) statsParticipant.get(p).get(
								cleAttaqueTournoi)).getId()
						+ "\">"
						+ ((Tournoi) statsParticipant.get(p).get(
								cleAttaqueTournoi)).getDateFormatee() + "</a>)";
			}
			// defense
			if ((Integer) statsParticipant.get(p).get(cleDefense) < defenseValeur
					|| defenseValeur == -1) {
				defenseValeur = (Integer) statsParticipant.get(p).get(
						cleDefense);
				defenseParticipant = "<a href=\"/PES/participant/afficherParticipant.action?idParticipant="
						+ p.getId()
						+ "\" title=\""+ p.getPrenomPseudoNom() +"\" >"
						+ p.getPrenom()
						+ "</a> (<a href=\"/PES/tournoi/afficherTournoi.action?idTournoi="
						+ ((Tournoi) statsParticipant.get(p).get(
								cleDefenseTournoi)).getId()
						+ "\">"
						+ ((Tournoi) statsParticipant.get(p).get(
								cleDefenseTournoi)).getDateFormatee() + "</a>)";
			} else if (((Integer) statsParticipant.get(p).get(cleDefense))
					.equals(defenseValeur)) {
				defenseParticipant = defenseParticipant
						+ "<BR/> <a href=\"/PES/participant/afficherParticipant.action?idParticipant="
						+ p.getId()
						+ "\" title=\""+ p.getPrenomPseudoNom() +"\" >"
						+ p.getPrenom()
						+ "</a> (<a href=\"/PES/tournoi/afficherTournoi.action?idTournoi="
						+ ((Tournoi) statsParticipant.get(p).get(
								cleDefenseTournoi)).getId()
						+ "\">"
						+ ((Tournoi) statsParticipant.get(p).get(
								cleDefenseTournoi)).getDateFormatee() + "</a>)";
			}
			// jaunes
			if ((Integer) statsParticipant.get(p).get(cleJaunes) > jaunesValeur) {
				jaunesValeur = (Integer) statsParticipant.get(p).get(cleJaunes);
				jaunesParticipant = "<a href=\"/PES/participant/afficherParticipant.action?idParticipant="
						+ p.getId()
						+ "\" title=\""+ p.getPrenomPseudoNom() +"\" >"
						+ p.getPrenom()
						+ "</a> (<a href=\"/PES/tournoi/afficherTournoi.action?idTournoi="
						+ ((Tournoi) statsParticipant.get(p).get(
								cleJaunesTournoi)).getId()
						+ "\">"
						+ ((Tournoi) statsParticipant.get(p).get(
								cleJaunesTournoi)).getDateFormatee() + "</a>)";
			} else if (((Integer) statsParticipant.get(p).get(cleJaunes))
					.equals(jaunesValeur)) {
				jaunesParticipant = jaunesParticipant
						+ "<BR/> <a href=\"/PES/participant/afficherParticipant.action?idParticipant="
						+ p.getId()
						+ "\" title=\""+ p.getPrenomPseudoNom() +"\" >"
						+ p.getPrenom()
						+ "</a> (<a href=\"/PES/tournoi/afficherTournoi.action?idTournoi="
						+ ((Tournoi) statsParticipant.get(p).get(
								cleJaunesTournoi)).getId()
						+ "\">"
						+ ((Tournoi) statsParticipant.get(p).get(
								cleJaunesTournoi)).getDateFormatee() + "</a>)";
			}
			// rouges
			if ((Integer) statsParticipant.get(p).get(cleRouges) > rougesValeur) {
				rougesValeur = (Integer) statsParticipant.get(p).get(cleRouges);
				rougesParticipant = "<a href=\"/PES/participant/afficherParticipant.action?idParticipant="
						+ p.getId()
						+ "\" title=\""+ p.getPrenomPseudoNom() +"\" >"
						+ p.getPrenom()
						+ "</a> (<a href=\"/PES/tournoi/afficherTournoi.action?idTournoi="
						+ ((Tournoi) statsParticipant.get(p).get(
								cleRougesTournoi)).getId()
						+ "\">"
						+ ((Tournoi) statsParticipant.get(p).get(
								cleRougesTournoi)).getDateFormatee() + "</a>)";
			} else if (((Integer) statsParticipant.get(p).get(cleRouges))
					.equals(rougesValeur)) {
				rougesParticipant = rougesParticipant
						+ "<BR/> <a href=\"/PES/participant/afficherParticipant.action?idParticipant="
						+ p.getId()
						+ "\" title=\""+ p.getPrenomPseudoNom() +"\" >"
						+ p.getPrenom()
						+ "</a> (<a href=\"/PES/tournoi/afficherTournoi.action?idTournoi="
						+ ((Tournoi) statsParticipant.get(p).get(
								cleRougesTournoi)).getId()
						+ "\">"
						+ ((Tournoi) statsParticipant.get(p).get(
								cleRougesTournoi)).getDateFormatee() + "</a>)";
			}
			// blessures
			if ((Integer) statsParticipant.get(p).get(cleBlessures) > blessuresValeur) {
				blessuresValeur = (Integer) statsParticipant.get(p).get(
						cleBlessures);
				blessuresParticipant = "<a href=\"/PES/participant/afficherParticipant.action?idParticipant="
						+ p.getId()
						+ "\" title=\""+ p.getPrenomPseudoNom() +"\" >"
						+ p.getPrenom()
						+ "</a> (<a href=\"/PES/tournoi/afficherTournoi.action?idTournoi="
						+ ((Tournoi) statsParticipant.get(p).get(
								cleBlessuresTournoi)).getId()
						+ "\">"
						+ ((Tournoi) statsParticipant.get(p).get(
								cleBlessuresTournoi)).getDateFormatee()
						+ "</a>)";
			} else if (((Integer) statsParticipant.get(p).get(cleBlessures))
					.equals(blessuresValeur)) {
				blessuresParticipant = blessuresParticipant
						+ "<BR/> <a href=\"/PES/participant/afficherParticipant.action?idParticipant="
						+ p.getId()
						+ "\" title=\""+ p.getPrenomPseudoNom() +"\" >"
						+ p.getPrenom()
						+ "</a> (<a href=\"/PES/tournoi/afficherTournoi.action?idTournoi="
						+ ((Tournoi) statsParticipant.get(p).get(
								cleBlessuresTournoi)).getId()
						+ "\">"
						+ ((Tournoi) statsParticipant.get(p).get(
								cleBlessuresTournoi)).getDateFormatee()
						+ "</a>)";
			}
			// blessures infligees
			if ((Integer) statsParticipant.get(p).get(cleBlessuresInfligees) > blessuresInfligeesValeur) {
				blessuresInfligeesValeur = (Integer) statsParticipant.get(p)
						.get(cleBlessuresInfligees);
				blessuresInfligeesParticipant = "<a href=\"/PES/participant/afficherParticipant.action?idParticipant="
						+ p.getId()
						+ "\" title=\""+ p.getPrenomPseudoNom() +"\" >"
						+ p.getPrenom()
						+ "</a> (<a href=\"/PES/tournoi/afficherTournoi.action?idTournoi="
						+ ((Tournoi) statsParticipant.get(p).get(
								cleBlessuresInfligeesTournoi)).getId()
						+ "\">"
						+ ((Tournoi) statsParticipant.get(p).get(
								cleBlessuresInfligeesTournoi))
								.getDateFormatee() + "</a>)";
			} else if (((Integer) statsParticipant.get(p).get(
					cleBlessuresInfligees)).equals(blessuresInfligeesValeur)) {
				blessuresInfligeesParticipant = blessuresInfligeesParticipant
						+ "<BR/> <a href=\"/PES/participant/afficherParticipant.action?idParticipant="
						+ p.getId()
						+ "\" title=\""+ p.getPrenomPseudoNom() +"\" >"
						+ p.getPrenom()
						+ "</a> (<a href=\"/PES/tournoi/afficherTournoi.action?idTournoi="
						+ ((Tournoi) statsParticipant.get(p).get(
								cleBlessuresInfligeesTournoi)).getId()
						+ "\">"
						+ ((Tournoi) statsParticipant.get(p).get(
								cleBlessuresInfligeesTournoi))
								.getDateFormatee() + "</a>)";
			}
		} // fin for Map

	}

	public List<Tournoi> getTournois() {
		return tournois;
	}

	public void setTournois(List<Tournoi> tournois) {
		this.tournois = tournois;
	}

	public int getTournoisJouesValeur() {
		return tournoisJouesValeur;
	}

	public void setTournoisJouesValeur(int tournoisJouesValeur) {
		this.tournoisJouesValeur = tournoisJouesValeur;
	}

	public String getTournoisJouesParticipant() {
		return tournoisJouesParticipant;
	}

	public void setTournoisJouesParticipant(String tournoisJouesParticipant) {
		this.tournoisJouesParticipant = tournoisJouesParticipant;
	}

	public int getTournoisGagnesValeur() {
		return tournoisGagnesValeur;
	}

	public void setTournoisGagnesValeur(int tournoisGagnesValeur) {
		this.tournoisGagnesValeur = tournoisGagnesValeur;
	}

	public String getTournoisGagnesParticipant() {
		return tournoisGagnesParticipant;
	}

	public void setTournoisGagnesParticipant(String tournoisGagnesParticipant) {
		this.tournoisGagnesParticipant = tournoisGagnesParticipant;
	}

	public String getResultatPoulesValeur() {
		return resultatPoulesValeur;
	}

	public void setResultatPoulesValeur(String resultatPoulesValeur) {
		this.resultatPoulesValeur = resultatPoulesValeur;
	}

	public String getResultatPoulesParticipant() {
		return resultatPoulesParticipant;
	}

	public void setResultatPoulesParticipant(String resultatPoulesParticipant) {
		this.resultatPoulesParticipant = resultatPoulesParticipant;
	}

	public String getFinaleProlifiqueValeur() {
		return finaleProlifiqueValeur;
	}

	public void setFinaleProlifiqueValeur(String finaleProlifiqueValeur) {
		this.finaleProlifiqueValeur = finaleProlifiqueValeur;
	}

	public String getFinaleProlifiqueParticipant() {
		return finaleProlifiqueParticipant;
	}

	public void setFinaleProlifiqueParticipant(
			String finaleProlifiqueParticipant) {
		this.finaleProlifiqueParticipant = finaleProlifiqueParticipant;
	}

	public int getProfiteurCSCValeur() {
		return profiteurCSCValeur;
	}

	public void setProfiteurCSCValeur(int profiteurCSCValeur) {
		this.profiteurCSCValeur = profiteurCSCValeur;
	}

	public String getProfiteurCSCParticipant() {
		return profiteurCSCParticipant;
	}

	public void setProfiteurCSCParticipant(String profiteurCSCParticipant) {
		this.profiteurCSCParticipant = profiteurCSCParticipant;
	}

	public int getVictimeCSCValeur() {
		return victimeCSCValeur;
	}

	public void setVictimeCSCValeur(int victimeCSCValeur) {
		this.victimeCSCValeur = victimeCSCValeur;
	}

	public String getVictimeCSCParticipant() {
		return victimeCSCParticipant;
	}

	public void setVictimeCSCParticipant(String victimeCSCParticipant) {
		this.victimeCSCParticipant = victimeCSCParticipant;
	}

	public int getAttaqueValeur() {
		return attaqueValeur;
	}

	public void setAttaqueValeur(int attaqueValeur) {
		this.attaqueValeur = attaqueValeur;
	}

	public String getAttaqueParticipant() {
		return attaqueParticipant;
	}

	public void setAttaqueParticipant(String attaqueParticipant) {
		this.attaqueParticipant = attaqueParticipant;
	}

	public int getDefenseValeur() {
		return defenseValeur;
	}

	public void setDefenseValeur(int defenseValeur) {
		this.defenseValeur = defenseValeur;
	}

	public String getDefenseParticipant() {
		return defenseParticipant;
	}

	public void setDefenseParticipant(String defenseParticipant) {
		this.defenseParticipant = defenseParticipant;
	}

	public int getJaunesValeur() {
		return jaunesValeur;
	}

	public void setJaunesValeur(int jaunesValeur) {
		this.jaunesValeur = jaunesValeur;
	}

	public String getJaunesParticipant() {
		return jaunesParticipant;
	}

	public void setJaunesParticipant(String jaunesParticipant) {
		this.jaunesParticipant = jaunesParticipant;
	}

	public int getRougesValeur() {
		return rougesValeur;
	}

	public void setRougesValeur(int rougesValeur) {
		this.rougesValeur = rougesValeur;
	}

	public String getRougesParticipant() {
		return rougesParticipant;
	}

	public void setRougesParticipant(String rougesParticipant) {
		this.rougesParticipant = rougesParticipant;
	}

	public int getBlessuresValeur() {
		return blessuresValeur;
	}

	public void setBlessuresValeur(int blessuresValeur) {
		this.blessuresValeur = blessuresValeur;
	}

	public String getBlessuresParticipant() {
		return blessuresParticipant;
	}

	public void setBlessuresParticipant(String blessuresParticipant) {
		this.blessuresParticipant = blessuresParticipant;
	}

	public int getBlessuresInfligeesValeur() {
		return blessuresInfligeesValeur;
	}

	public void setBlessuresInfligeesValeur(int blessuresInfligeesValeur) {
		this.blessuresInfligeesValeur = blessuresInfligeesValeur;
	}

	public String getBlessuresInfligeesParticipant() {
		return blessuresInfligeesParticipant;
	}

	public void setBlessuresInfligeesParticipant(
			String blessuresInfligeesParticipant) {
		this.blessuresInfligeesParticipant = blessuresInfligeesParticipant;
	}

	public String getCleTournoisJoues() {
		return cleTournoisJoues;
	}

	public String getCleTournoisGagnes() {
		return cleTournoisGagnes;
	}

	public String getCleResultatsPoules() {
		return cleResultatsPoules;
	}

	public String getCleFinaleProlifique() {
		return cleFinaleProlifique;
	}

	public String getCleProfiteurCSC() {
		return cleProfiteurCSC;
	}

	public String getCleVictimeCSC() {
		return cleVictimeCSC;
	}

	public String getCleAttaque() {
		return cleAttaque;
	}

	public String getCleAttaqueTournoi() {
		return cleAttaqueTournoi;
	}

	public String getCleDefense() {
		return cleDefense;
	}

	public String getCleDefenseTournoi() {
		return cleDefenseTournoi;
	}

	public String getCleJaunes() {
		return cleJaunes;
	}

	public String getCleJaunesTournoi() {
		return cleJaunesTournoi;
	}

	public String getCleRouges() {
		return cleRouges;
	}

	public String getCleRougesTournoi() {
		return cleRougesTournoi;
	}

	public String getCleBlessures() {
		return cleBlessures;
	}

	public String getCleBlessuresTournoi() {
		return cleBlessuresTournoi;
	}

	public String getCleBlessuresInfligees() {
		return cleBlessuresInfligees;
	}

	public String getCleBlessuresInfligeesTournoi() {
		return cleBlessuresInfligeesTournoi;
	}
}
