package pes.ihm.action.tournoi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.struts2.interceptor.SessionAware;

import pes.dao.IEquipeDAO;
import pes.dao.IEvenementDAO;
import pes.dao.IJoueurDAO;
import pes.dao.IMatchDAO;
import pes.dao.IParticipantDAO;
import pes.dao.ITournoiDAO;
import pes.domaine.ENiveauMatch;
import pes.domaine.EStatutTournoi;
import pes.domaine.ETypeEvenement;
import pes.domaine.Equipe;
import pes.domaine.Evenement;
import pes.domaine.Joueur;
import pes.domaine.Match;
import pes.domaine.Participant;
import pes.domaine.Tournoi;
import pes.service.comparator.EquipeComparatorPoule;
import pes.service.comparator.JoueurComparator;
import pes.service.comparator.NiveauMatchComparator;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Evolution extends ActionSupport implements SessionAware {

	// Attributs choix tournoi
	private List<Tournoi> tournoisDispos;
	private int idTournoi;

	private IMatchDAO matchDAO;
	private IEquipeDAO equipeDAO;
	private IJoueurDAO joueurDAO;
	private ITournoiDAO tournoiDAO;
	private IParticipantDAO participantDAO;
	private IEvenementDAO evenementDAO;

	// Attributs phase 1 (tirage)
	private Equipe e1;
	private Equipe e2;
	private Equipe e3;
	private Equipe e4;
	private Equipe e5;
	private Equipe e6;
	private Equipe e7;
	private Equipe e8;
	private int p1;
	private int p2;
	private int p3;
	private int p4;
	private int p5;
	private int p6;
	private int p7;
	private int p8;
	private List<Participant> participants;
	private String poule1;
	private String poule2;
	private String poule3;
	private String poule4;
	private String poule5;
	private String poule6;
	private String poule7;
	private String poule8;
	private List<String> poules;
	private Integer pos1;
	private Integer pos2;
	private Integer pos3;
	private Integer pos4;
	private Integer pos5;
	private Integer pos6;
	private Integer pos7;
	private Integer pos8;
	private List<Integer> positions;

	// Attributs phase 2 (phase de poules)
	private List<Equipe> equipesPouleA;
	private List<Equipe> equipesPouleB;

	private Equipe equipeA1;
	private Equipe equipeA2;
	private Equipe equipeA3;
	private Equipe equipeA4;
	private Equipe equipeB1;
	private Equipe equipeB2;
	private Equipe equipeB3;
	private Equipe equipeB4;

	private boolean modif;
	private int idMatchEnCours;
	private List<ETypeEvenement> typesEvenements;
	private String codeTypeEvenement;
	private List<Joueur> joueurs;
	private int idJoueur;
	private List<Integer> minutes;
	private Integer minute;

	// Attributs phase 3
	private Match demi1Aller;
	private Match demi1Retour;
	private Match demi2Aller;
	private Match demi2Retour;
	private Match demi3Aller;
	private Match demi3Retour;
	private Match demi4Aller;
	private Match demi4Retour;

	private Match finale1;
	private Match finale2;
	private Match finale3;
	private Match finale4;

	private boolean finales;
	private boolean validationFinale;

	// Attributs phase 4

	private boolean info;
	private boolean error;

	// Attributs session
	@SuppressWarnings("unchecked")
	private Map session;
	private final String cleTournoi = "cleTournoi";

	public String initialiser() {
		session.remove(cleTournoi);
		tournoisDispos = new ArrayList<Tournoi>();
		List<Tournoi> all = tournoiDAO.findAll();
		for (Tournoi t : all) {
			if (t.getStatut() != EStatutTournoi.TERMINE) {
				tournoisDispos.add(t);
			}
		}
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String choixTournoi() {
		if (idTournoi != -1) {
			Tournoi tournoi = tournoiDAO.findById(idTournoi);
			session.put(cleTournoi, idTournoi);
			System.out.println("idTournoi=" + idTournoi);

			switch (tournoi.getStatut()) {
			case TIRAGE:
				return "phase1";
			case POULES:
				return "phase2";
			case ARBRE_FINAL:
				return "phase3";
			default:
				return "error";
			}

		} else {
			addFieldError("tournoi", "Il faut choisir un tournoi");
			tournoisDispos = new ArrayList<Tournoi>();
			List<Tournoi> all = tournoiDAO.findAll();
			for (Tournoi t : all) {
				if (t.getStatut() != EStatutTournoi.TERMINE) {
					tournoisDispos.add(t);
				}
			}
			return INPUT;
		}
	}

	@SuppressWarnings("unchecked")
	public String phase1Init() {
		Tournoi tournoi = tournoiDAO
				.findById((Integer) session.get(cleTournoi));

		List<Equipe> equipes = new ArrayList<Equipe>(tournoi.getEquipes());
		participants = new ArrayList<Participant>(tournoi.getParticipants());

		poules = new ArrayList<String>();
		poules.add("A");
		poules.add("B");

		positions = new ArrayList<Integer>();
		positions.add(1);
		positions.add(2);
		positions.add(3);
		positions.add(4);

		e1 = equipeDAO.findById(equipes.get(0).getId());
		e2 = equipeDAO.findById(equipes.get(1).getId());
		e3 = equipeDAO.findById(equipes.get(2).getId());
		e4 = equipeDAO.findById(equipes.get(3).getId());
		e5 = equipeDAO.findById(equipes.get(4).getId());
		e6 = equipeDAO.findById(equipes.get(5).getId());
		e7 = equipeDAO.findById(equipes.get(6).getId());
		e8 = equipeDAO.findById(equipes.get(7).getId());

		session.put("idEquipe1", e1.getId());
		session.put("idEquipe2", e2.getId());
		session.put("idEquipe3", e3.getId());
		session.put("idEquipe4", e4.getId());
		session.put("idEquipe5", e5.getId());
		session.put("idEquipe6", e6.getId());
		session.put("idEquipe7", e7.getId());
		session.put("idEquipe8", e8.getId());

		return SUCCESS;
	}

	public String phase1Valider() {
		Tournoi tournoi = tournoiDAO
				.findById((Integer) session.get(cleTournoi));

		e1 = equipeDAO.findById((Integer) session.get("idEquipe1"));
		e2 = equipeDAO.findById((Integer) session.get("idEquipe2"));
		e3 = equipeDAO.findById((Integer) session.get("idEquipe3"));
		e4 = equipeDAO.findById((Integer) session.get("idEquipe4"));
		e5 = equipeDAO.findById((Integer) session.get("idEquipe5"));
		e6 = equipeDAO.findById((Integer) session.get("idEquipe6"));
		e7 = equipeDAO.findById((Integer) session.get("idEquipe7"));
		e8 = equipeDAO.findById((Integer) session.get("idEquipe8"));

		if (!testParamPhase1()) {
			participants = new ArrayList<Participant>(tournoi.getParticipants());
			poules = new ArrayList<String>();
			poules.add("A");
			poules.add("B");
			positions = new ArrayList<Integer>();
			positions.add(1);
			positions.add(2);
			positions.add(3);
			positions.add(4);
			return INPUT;
		}

		Participant participant1 = participantDAO.findById(p1);
		Participant participant2 = participantDAO.findById(p2);
		Participant participant3 = participantDAO.findById(p3);
		Participant participant4 = participantDAO.findById(p4);
		Participant participant5 = participantDAO.findById(p5);
		Participant participant6 = participantDAO.findById(p6);
		Participant participant7 = participantDAO.findById(p7);
		Participant participant8 = participantDAO.findById(p8);

		e1.setParticipant(participant1);
		e1.setPoule(poule1);
		e1.setPosition(pos1);
		e2.setParticipant(participant2);
		e2.setPoule(poule2);
		e2.setPosition(pos2);
		e3.setParticipant(participant3);
		e3.setPoule(poule3);
		e3.setPosition(pos3);
		e4.setParticipant(participant4);
		e4.setPoule(poule4);
		e4.setPosition(pos4);
		e5.setParticipant(participant5);
		e5.setPoule(poule5);
		e5.setPosition(pos5);
		e6.setParticipant(participant6);
		e6.setPoule(poule6);
		e6.setPosition(pos6);
		e7.setParticipant(participant7);
		e7.setPoule(poule7);
		e7.setPosition(pos7);
		e8.setParticipant(participant8);
		e8.setPoule(poule8);
		e8.setPosition(pos8);

		// création des matchs de poule
		equipesPouleA = new ArrayList<Equipe>();
		equipesPouleB = new ArrayList<Equipe>();
		for (Equipe equipe : tournoi.getEquipes()) {
			if (equipe.getPoule().equals("A")) {
				equipesPouleA.add(equipe);
				switch (equipe.getPosition()) {
				case 1:
					equipeA1 = equipe;
					break;
				case 2:
					equipeA2 = equipe;
					break;
				case 3:
					equipeA3 = equipe;
					break;
				case 4:
					equipeA4 = equipe;
					break;
				default:
					break;
				}
			} else {
				equipesPouleB.add(equipe);
				switch (equipe.getPosition()) {
				case 1:
					equipeB1 = equipe;
					break;
				case 2:
					equipeB2 = equipe;
					break;
				case 3:
					equipeB3 = equipe;
					break;
				case 4:
					equipeB4 = equipe;
					break;
				default:
					break;
				}
			}
		}
		Match m11 = new Match(ENiveauMatch.POULE1, equipeA1, equipeA2, tournoi);
		Match m12 = new Match(ENiveauMatch.POULE1, equipeA3, equipeA4, tournoi);
		Match m13 = new Match(ENiveauMatch.POULE1, equipeB1, equipeB2, tournoi);
		Match m14 = new Match(ENiveauMatch.POULE1, equipeB3, equipeB4, tournoi);

		Match m21 = new Match(ENiveauMatch.POULE2, equipeA4, equipeA1, tournoi);
		Match m22 = new Match(ENiveauMatch.POULE2, equipeA2, equipeA3, tournoi);
		Match m23 = new Match(ENiveauMatch.POULE2, equipeB4, equipeB1, tournoi);
		Match m24 = new Match(ENiveauMatch.POULE2, equipeB2, equipeB3, tournoi);

		Match m31 = new Match(ENiveauMatch.POULE3, equipeA1, equipeA3, tournoi);
		Match m32 = new Match(ENiveauMatch.POULE3, equipeA4, equipeA2, tournoi);
		Match m33 = new Match(ENiveauMatch.POULE3, equipeB1, equipeB3, tournoi);
		Match m34 = new Match(ENiveauMatch.POULE3, equipeB4, equipeB2, tournoi);

		Match m41 = new Match(ENiveauMatch.POULE4, equipeA4, equipeA3, tournoi);
		Match m42 = new Match(ENiveauMatch.POULE4, equipeA2, equipeA1, tournoi);
		Match m43 = new Match(ENiveauMatch.POULE4, equipeB4, equipeB3, tournoi);
		Match m44 = new Match(ENiveauMatch.POULE4, equipeB2, equipeB1, tournoi);

		Match m51 = new Match(ENiveauMatch.POULE5, equipeA3, equipeA2, tournoi);
		Match m52 = new Match(ENiveauMatch.POULE5, equipeA1, equipeA4, tournoi);
		Match m53 = new Match(ENiveauMatch.POULE5, equipeB3, equipeB2, tournoi);
		Match m54 = new Match(ENiveauMatch.POULE5, equipeB1, equipeB4, tournoi);

		Match m61 = new Match(ENiveauMatch.POULE6, equipeA2, equipeA4, tournoi);
		Match m62 = new Match(ENiveauMatch.POULE6, equipeA3, equipeA1, tournoi);
		Match m63 = new Match(ENiveauMatch.POULE6, equipeB2, equipeB4, tournoi);
		Match m64 = new Match(ENiveauMatch.POULE6, equipeB3, equipeB1, tournoi);

		matchDAO.insert(m11);
		matchDAO.insert(m12);
		matchDAO.insert(m13);
		matchDAO.insert(m14);
		matchDAO.insert(m21);
		matchDAO.insert(m22);
		matchDAO.insert(m23);
		matchDAO.insert(m24);
		matchDAO.insert(m31);
		matchDAO.insert(m32);
		matchDAO.insert(m33);
		matchDAO.insert(m34);
		matchDAO.insert(m41);
		matchDAO.insert(m42);
		matchDAO.insert(m43);
		matchDAO.insert(m44);
		matchDAO.insert(m51);
		matchDAO.insert(m52);
		matchDAO.insert(m53);
		matchDAO.insert(m54);
		matchDAO.insert(m61);
		matchDAO.insert(m62);
		matchDAO.insert(m63);
		matchDAO.insert(m64);

		equipeDAO.updateTirage(e1.getId(), p1, poule1, pos1);
		equipeDAO.updateTirage(e2.getId(), p2, poule2, pos2);
		equipeDAO.updateTirage(e3.getId(), p3, poule3, pos3);
		equipeDAO.updateTirage(e4.getId(), p4, poule4, pos4);
		equipeDAO.updateTirage(e5.getId(), p5, poule5, pos5);
		equipeDAO.updateTirage(e6.getId(), p6, poule6, pos6);
		equipeDAO.updateTirage(e7.getId(), p7, poule7, pos7);
		equipeDAO.updateTirage(e8.getId(), p8, poule8, pos8);

		tournoiDAO.updateStatut(tournoi.getId(), EStatutTournoi.POULES);

		return SUCCESS;
	}

	private boolean testParamPhase1() {
		boolean formOk = true;

		if (p1 == -1) {
			formOk = false;
			addFieldError("p1", "Il faut choisir un participant");
		}
		if (poule1.equals("-1")) {
			formOk = false;
			addFieldError("poule1", "Il faut choisir une poule");
		}
		if (pos1 == -1) {
			formOk = false;
			addFieldError("pos1", "Il faut choisir une position");
		}
		if (p2 == -1) {
			formOk = false;
			addFieldError("p2", "Il faut choisir un participant");
		}
		if (poule2.equals("-1")) {
			formOk = false;
			addFieldError("poule2", "Il faut choisir une poule");
		}
		if (pos2 == -1) {
			formOk = false;
			addFieldError("pos2", "Il faut choisir une position");
		}
		if (p3 == -1) {
			formOk = false;
			addFieldError("p3", "Il faut choisir un participant");
		}
		if (poule3.equals("-1")) {
			formOk = false;
			addFieldError("poule3", "Il faut choisir une poule");
		}
		if (pos3 == -1) {
			formOk = false;
			addFieldError("pos3", "Il faut choisir une position");
		}
		if (p4 == -1) {
			formOk = false;
			addFieldError("p4", "Il faut choisir un participant");
		}
		if (poule4.equals("-1")) {
			formOk = false;
			addFieldError("poule4", "Il faut choisir une poule");
		}
		if (pos4 == -1) {
			formOk = false;
			addFieldError("pos4", "Il faut choisir une position");
		}
		if (p5 == -1) {
			formOk = false;
			addFieldError("p5", "Il faut choisir un participant");
		}
		if (poule5.equals("-1")) {
			formOk = false;
			addFieldError("poule5", "Il faut choisir une poule");
		}
		if (pos5 == -1) {
			formOk = false;
			addFieldError("pos5", "Il faut choisir une position");
		}
		if (p6 == -1) {
			formOk = false;
			addFieldError("p6", "Il faut choisir un participant");
		}
		if (poule6.equals("-1")) {
			formOk = false;
			addFieldError("poule6", "Il faut choisir une poule");
		}
		if (pos6 == -1) {
			formOk = false;
			addFieldError("pos6", "Il faut choisir une position");
		}
		if (p7 == -1) {
			formOk = false;
			addFieldError("p7", "Il faut choisir un participant");
		}
		if (poule7.equals("-1")) {
			formOk = false;
			addFieldError("poule7", "Il faut choisir une poule");
		}
		if (pos7 == -1) {
			formOk = false;
			addFieldError("pos7", "Il faut choisir une position");
		}
		if (p8 == -1) {
			formOk = false;
			addFieldError("p8", "Il faut choisir un participant");
		}
		if (poule8.equals("-1")) {
			formOk = false;
			addFieldError("poule8", "Il faut choisir une poule");
		}
		if (pos8 == -1) {
			formOk = false;
			addFieldError("pos8", "Il faut choisir une position");
		}
		if (formOk) {
			Set<Integer> verif = new HashSet<Integer>();
			verif.add(p1);
			verif.add(p2);
			verif.add(p3);
			verif.add(p4);
			verif.add(p5);
			verif.add(p6);
			verif.add(p7);
			verif.add(p8);

			if (verif.size() < 8) {
				formOk = false;
				addFieldError("distincts",
						"Il faut choisir des participants distincts");
			}

			int nbA = 0;
			int nbB = 0;
			if (poule1.equals("A")) {
				nbA++;
			} else {
				nbB++;
			}
			if (poule2.equals("A")) {
				nbA++;
			} else {
				nbB++;
			}
			if (poule3.equals("A")) {
				nbA++;
			} else {
				nbB++;
			}
			if (poule4.equals("A")) {
				nbA++;
			} else {
				nbB++;
			}
			if (poule5.equals("A")) {
				nbA++;
			} else {
				nbB++;
			}
			if (poule6.equals("A")) {
				nbA++;
			} else {
				nbB++;
			}
			if (poule7.equals("A")) {
				nbA++;
			} else {
				nbB++;
			}
			if (poule8.equals("A")) {
				nbA++;
			} else {
				nbB++;
			}

			if (nbA != 4 || nbB != 4) {
				formOk = false;
				addFieldError("coherencePoules",
						"Il faut 4 équipes dans chaque poule : actuellement il y a "
								+ nbA + " équipes dans la poule A et " + nbB
								+ " équipes dans la poule B");
			}

			List<Integer> positionsDispo = new ArrayList<Integer>();
			positionsDispo.add(1);
			positionsDispo.add(1);
			positionsDispo.add(2);
			positionsDispo.add(2);
			positionsDispo.add(3);
			positionsDispo.add(3);
			positionsDispo.add(4);
			positionsDispo.add(4);
			positionsDispo.remove(pos1);
			positionsDispo.remove(pos2);
			positionsDispo.remove(pos3);
			positionsDispo.remove(pos4);
			positionsDispo.remove(pos5);
			positionsDispo.remove(pos6);
			positionsDispo.remove(pos7);
			positionsDispo.remove(pos8);

			if (positionsDispo.size() > 0) {
				formOk = false;
				addFieldError("coherencePositions",
						"Erreur de cohérence dans les positions");
			}
		}
		return formOk;
	}

	public String phase2Init() {
		phase2Affichage();

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String modifMatch() {
		phase2Affichage();
		modif = true;
		Match matchEnCours = matchDAO.findById(idMatchEnCours);
		initListesEvenement(matchEnCours);
		session.put("matchEnCours", matchEnCours);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String addEvenement() {
		phase2Affichage();
		modif = true;
		Match matchEnCours = (Match) session.get("matchEnCours");
		initListesEvenement(matchEnCours);

		Evenement evenement = new Evenement();
		evenement.setMatch(matchEnCours);
		evenement.setType(ETypeEvenement.get(codeTypeEvenement));
		if (minute != -1) {
			evenement.setMinute(minute);
		}

		if (idJoueur == -1) {
			evenement.setJoueur(joueurDAO.findById(-1));
			evenement.setEquipe(matchEnCours.getEquipeDomicile());
		} else if (idJoueur == -2) {
			evenement.setJoueur(joueurDAO.findById(-1));
			evenement.setEquipe(matchEnCours.getEquipeExterieur());
		} else {
			Joueur joueur = joueurDAO.findById(idJoueur);
			evenement.setJoueur(joueur);
			for (Joueur j : matchEnCours.getEquipeDomicile().getJoueurs()) {
				if (j.getId().equals(joueur.getId())) {
					evenement.setEquipe(matchEnCours.getEquipeDomicile());
				}
			}
			if (evenement.getEquipe() == null) {
				evenement.setEquipe(matchEnCours.getEquipeExterieur());
			}
		}

		evenementDAO.insert(evenement);
		matchEnCours = matchDAO.findById(matchEnCours.getId());
		session.put("matchEnCours", matchEnCours);

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String resetMatch() {
		modif = true;
		phase2Affichage();
		Match matchEnCours = (Match) session.get("matchEnCours");
		matchEnCours = matchDAO.findById(matchEnCours.getId());
		evenementDAO.deleteByMatch(matchEnCours);
		matchEnCours.setTermine(false);
		matchDAO.updateMatchReset(matchEnCours);
		matchEnCours = matchDAO.findById(matchEnCours.getId());
		initListesEvenement(matchEnCours);
		matchEnCours.setEvenements(null);
		session.put("matchEnCours", matchEnCours);
		Collections.sort(equipesPouleA, new EquipeComparatorPoule());
		Collections.sort(equipesPouleB, new EquipeComparatorPoule());
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String validerMatch() {
		phase2Affichage();
		modif = false;
		Match matchEnCours = (Match) session.get("matchEnCours");
		matchEnCours = matchDAO.findById(matchEnCours.getId());
		matchEnCours.setTermine(true);
		SortedMap<ENiveauMatch, Map<String, List<Match>>> matchs = (SortedMap<ENiveauMatch, Map<String, List<Match>>>) session
				.get("matchs");

		List<Match> temp = matchs.get(matchEnCours.getNiveau()).get(
				matchEnCours.getEquipeDomicile().getPoule());
		if (temp.get(0).getId() == matchEnCours.getId()) {
			temp.remove(0);
			temp.add(0, matchEnCours);
		} else {
			temp.remove(1);
			temp.add(matchEnCours);
		}

		session.put("matchs", matchs);
		matchDAO.updateMatchTermine(matchEnCours);
		Collections.sort(equipesPouleA, new EquipeComparatorPoule());
		Collections.sort(equipesPouleB, new EquipeComparatorPoule());
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	private void phase2Affichage() {
		Tournoi tournoi = tournoiDAO
				.findById((Integer) session.get(cleTournoi));
		equipesPouleA = new ArrayList<Equipe>();
		equipesPouleB = new ArrayList<Equipe>();

		for (Equipe equipe : tournoi.getEquipes()) {
			if (equipe.getPoule().equals("A")) {
				equipesPouleA.add(equipe);
			} else {
				equipesPouleB.add(equipe);
			}
		}

		Collections.sort(equipesPouleA, new EquipeComparatorPoule());
		Collections.sort(equipesPouleB, new EquipeComparatorPoule());

		List<Match> matchs = matchDAO.find(tournoi);

		SortedMap<ENiveauMatch, Map<String, List<Match>>> matchsParNiveauEtPoule = new TreeMap<ENiveauMatch, Map<String, List<Match>>>(
				new NiveauMatchComparator());

		for (Match match : matchs) {
			if (match.isPoule()) {
				if (!matchsParNiveauEtPoule.containsKey(match.getNiveau())) {
					matchsParNiveauEtPoule.put(match.getNiveau(),
							new HashMap<String, List<Match>>());
				}
				if (!matchsParNiveauEtPoule.get(match.getNiveau()).containsKey(
						match.getEquipeDomicile().getPoule())) {
					matchsParNiveauEtPoule.get(match.getNiveau()).put(
							match.getEquipeDomicile().getPoule(),
							new ArrayList<Match>());
				}
				matchsParNiveauEtPoule.get(match.getNiveau())
						.get(match.getEquipeDomicile().getPoule()).add(match);
			}
		}

		session.put("matchs", matchsParNiveauEtPoule);
	}

	private void initListesEvenement(Match matchEnCours) {
		typesEvenements = new ArrayList<ETypeEvenement>(
				Arrays.asList(ETypeEvenement.values()));
		joueurs = new ArrayList<Joueur>();
		Joueur cscDom = new Joueur();
		cscDom.setId(-1);
		cscDom.setNom("CSC Dom");
		Joueur cscExt = new Joueur();
		cscExt.setId(-2);
		cscExt.setNom("CSC Ext");
		joueurs.add(cscDom);
		joueurs.add(cscExt);
		joueurs.addAll(matchEnCours.getEquipeDomicile().getJoueurs());
		joueurs.addAll(matchEnCours.getEquipeExterieur().getJoueurs());
		Collections.sort(joueurs, new JoueurComparator());

		minutes = new ArrayList<Integer>();
		for (int i = 1; i < 91; i++) {
			minutes.add(i);
		}
		if (matchEnCours.getNiveau() == ENiveauMatch.DEMI_RETOUR
				|| matchEnCours.getNiveau() == ENiveauMatch.FINALE7
				|| matchEnCours.getNiveau() == ENiveauMatch.FINALE5
				|| matchEnCours.getNiveau() == ENiveauMatch.FINALE3
				|| matchEnCours.getNiveau() == ENiveauMatch.FINALE1) {
			for (int i = 91; i < 121; i++) {
				minutes.add(i);
			}
		}

	}

	public String phase2Valider() {
		Tournoi tournoi = tournoiDAO
				.findById((Integer) session.get(cleTournoi));

		equipesPouleA = new ArrayList<Equipe>();
		equipesPouleB = new ArrayList<Equipe>();
		for (Equipe equipe : tournoi.getEquipes()) {
			if (equipe.getPoule().equals("A")) {
				equipesPouleA.add(equipe);
			} else {
				equipesPouleB.add(equipe);
			}
		}

		Collections.sort(equipesPouleA, new EquipeComparatorPoule());
		Collections.sort(equipesPouleB, new EquipeComparatorPoule());

		Match m1 = new Match(ENiveauMatch.DEMI_ALLER, equipesPouleA.get(1),
				equipesPouleB.get(0), tournoi);
		Match m2 = new Match(ENiveauMatch.DEMI_RETOUR, equipesPouleB.get(0),
				equipesPouleA.get(1), tournoi);

		Match m3 = new Match(ENiveauMatch.DEMI_ALLER, equipesPouleB.get(1),
				equipesPouleA.get(0), tournoi);
		Match m4 = new Match(ENiveauMatch.DEMI_RETOUR, equipesPouleA.get(0),
				equipesPouleB.get(1), tournoi);

		Match m5 = new Match(ENiveauMatch.DEMI_ALLER, equipesPouleA.get(3),
				equipesPouleB.get(2), tournoi);
		Match m6 = new Match(ENiveauMatch.DEMI_RETOUR, equipesPouleB.get(2),
				equipesPouleA.get(3), tournoi);

		Match m7 = new Match(ENiveauMatch.DEMI_ALLER, equipesPouleB.get(3),
				equipesPouleA.get(2), tournoi);
		Match m8 = new Match(ENiveauMatch.DEMI_RETOUR, equipesPouleA.get(2),
				equipesPouleB.get(3), tournoi);

		matchDAO.insert(m1);
		matchDAO.insert(m2);
		matchDAO.insert(m3);
		matchDAO.insert(m4);
		matchDAO.insert(m5);
		matchDAO.insert(m6);
		matchDAO.insert(m7);
		matchDAO.insert(m8);

		tournoiDAO.updateStatut(tournoi.getId(), EStatutTournoi.ARBRE_FINAL);
		return SUCCESS;
	}

	public String phase3Init() {
		phase3Affichage();
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String modifMatchFinal() {
		phase3Affichage();
		modif = true;
		Match matchEnCours = matchDAO.findById(idMatchEnCours);
		initListesEvenement(matchEnCours);
		session.put("matchEnCours", matchEnCours);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String addEvenementFinal() {
		phase3Affichage();
		modif = true;
		Match matchEnCours = (Match) session.get("matchEnCours");
		initListesEvenement(matchEnCours);

		Evenement evenement = new Evenement();
		evenement.setMatch(matchEnCours);
		evenement.setType(ETypeEvenement.get(codeTypeEvenement));
		if (minute != -1) {
			evenement.setMinute(minute);
		}

		if (idJoueur == -1) {
			evenement.setJoueur(joueurDAO.findById(-1));
			evenement.setEquipe(matchEnCours.getEquipeDomicile());
		} else if (idJoueur == -2) {
			evenement.setJoueur(joueurDAO.findById(-1));
			evenement.setEquipe(matchEnCours.getEquipeExterieur());
		} else {
			Joueur joueur = joueurDAO.findById(idJoueur);
			evenement.setJoueur(joueur);
			for (Joueur j : matchEnCours.getEquipeDomicile().getJoueurs()) {
				if (j.getId().equals(joueur.getId())) {
					evenement.setEquipe(matchEnCours.getEquipeDomicile());
				}
			}
			if (evenement.getEquipe() == null) {
				evenement.setEquipe(matchEnCours.getEquipeExterieur());
			}
		}

		evenementDAO.insert(evenement);
		matchEnCours = matchDAO.findById(matchEnCours.getId());
		session.put("matchEnCours", matchEnCours);

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String resetMatchFinal() {
		modif = true;
		Match matchEnCours = (Match) session.get("matchEnCours");
		matchEnCours = matchDAO.findById(matchEnCours.getId());
		evenementDAO.deleteByMatch(matchEnCours);
		matchDAO.updateMatchReset(matchEnCours);
		matchEnCours = matchDAO.findById(matchEnCours.getId());
		initListesEvenement(matchEnCours);
		matchEnCours.setEvenements(null);
		session.put("matchEnCours", matchEnCours);
		phase3Affichage();
		return SUCCESS;
	}

	public String validerMatchFinal() {
		modif = false;
		Match matchEnCours = (Match) session.get("matchEnCours");
		matchEnCours = matchDAO.findById(matchEnCours.getId());
		matchDAO.updateMatchTermine(matchEnCours);
		phase3Affichage();
		return SUCCESS;
	}

	public String validationFinale() {
		Tournoi tournoi = tournoiDAO
				.findById((Integer) session.get(cleTournoi));
		tournoiDAO.updateStatut(tournoi.getId(), EStatutTournoi.TERMINE);
		return SUCCESS;
	}

	private void phase3Affichage() {
		Tournoi tournoi = tournoiDAO
				.findById((Integer) session.get(cleTournoi));

		List<Match> matchsDemiAller = matchDAO.find(tournoi,
				ENiveauMatch.DEMI_ALLER);
		List<Match> matchsDemiRetour = matchDAO.find(tournoi,
				ENiveauMatch.DEMI_RETOUR);
		List<Match> matchsFinale = new ArrayList<Match>();

		matchsFinale.addAll(matchDAO.find(tournoi, ENiveauMatch.FINALE1));
		matchsFinale.addAll(matchDAO.find(tournoi, ENiveauMatch.FINALE3));
		matchsFinale.addAll(matchDAO.find(tournoi, ENiveauMatch.FINALE5));
		matchsFinale.addAll(matchDAO.find(tournoi, ENiveauMatch.FINALE7));

		equipesPouleA = new ArrayList<Equipe>();
		equipesPouleB = new ArrayList<Equipe>();
		for (Equipe equipe : tournoi.getEquipes()) {
			if (equipe.getPoule().equals("A")) {
				equipesPouleA.add(equipe);
			} else {
				equipesPouleB.add(equipe);
			}
		}

		Collections.sort(equipesPouleA, new EquipeComparatorPoule());
		Collections.sort(equipesPouleB, new EquipeComparatorPoule());

		demi1Aller = findMatchByEquipe(matchsDemiAller, equipesPouleA.get(0));
		demi2Aller = findMatchByEquipe(matchsDemiAller, equipesPouleA.get(1));
		demi3Aller = findMatchByEquipe(matchsDemiAller, equipesPouleA.get(2));
		demi4Aller = findMatchByEquipe(matchsDemiAller, equipesPouleA.get(3));

		demi1Retour = findMatchByEquipe(matchsDemiRetour, equipesPouleA.get(0));
		demi2Retour = findMatchByEquipe(matchsDemiRetour, equipesPouleA.get(1));
		demi3Retour = findMatchByEquipe(matchsDemiRetour, equipesPouleA.get(2));
		demi4Retour = findMatchByEquipe(matchsDemiRetour, equipesPouleA.get(3));

		if (demi1Aller.isTermine() && demi2Aller.isTermine()
				&& demi3Aller.isTermine() && demi4Aller.isTermine()
				&& demi1Retour.isTermine() && demi2Retour.isTermine()
				&& demi3Retour.isTermine() && demi4Retour.isTermine()) {
			finales = true;
			if (matchsFinale.isEmpty()) {
				finale1 = new Match(ENiveauMatch.FINALE1, getVainqueur(
						demi1Aller, demi1Retour), getVainqueur(demi2Aller,
						demi2Retour), tournoi);
				finale2 = new Match(ENiveauMatch.FINALE3, getPerdant(
						demi1Aller, demi1Retour), getPerdant(demi2Aller,
						demi2Retour), tournoi);
				finale3 = new Match(ENiveauMatch.FINALE5, getVainqueur(
						demi3Aller, demi3Retour), getVainqueur(demi4Aller,
						demi4Retour), tournoi);
				finale4 = new Match(ENiveauMatch.FINALE7, getPerdant(
						demi3Aller, demi3Retour), getPerdant(demi4Aller,
						demi4Retour), tournoi);

				matchDAO.insert(finale1);
				matchDAO.insert(finale2);
				matchDAO.insert(finale3);
				matchDAO.insert(finale4);
			} else {
				finale1 = findMatchByEquipe(matchsFinale,
						getVainqueur(demi1Aller, demi1Retour));
				finale2 = findMatchByEquipe(matchsFinale,
						getPerdant(demi1Aller, demi1Retour));
				finale3 = findMatchByEquipe(matchsFinale,
						getVainqueur(demi3Aller, demi3Retour));
				finale4 = findMatchByEquipe(matchsFinale,
						getPerdant(demi3Aller, demi3Retour));
			}
			if (finale1.isTermine() && finale2.isTermine()
					&& finale3.isTermine() && finale4.isTermine()) {
				validationFinale = true;
			}
		}

	}

	private Equipe getVainqueur(Match demiAller, Match demiRetour) {
		Equipe e1 = demiAller.getEquipeDomicile();
		Equipe e2 = demiAller.getEquipeExterieur();

		int nbButsE1 = demiAller.getButs(e1) + demiRetour.getButs(e1);
		int nbButsE2 = demiAller.getButs(e2) + demiRetour.getButs(e2);

		if (nbButsE1 > nbButsE2) {
			return e1;
		} else if (nbButsE1 < nbButsE2) {
			return e2;
		} else {
			int nbButsExtE1 = demiRetour.getButs(e1);
			int nbButsExtE2 = demiAller.getButs(e2);
			if (nbButsExtE1 > nbButsExtE2) {
				return e1;
			} else if (nbButsExtE1 < nbButsExtE2) {
				return e2;
			} else {
				if (demiRetour.getTabDom() != null) {
					if (demiRetour.getTabDom() > demiRetour.getTabExt()) {
						return e2;
					} else {
						return e1;
					}
				}
				// TODO Tirs aux buts
				return e1;
			}
		}
	}

	private Equipe getPerdant(Match demiAller, Match demiRetour) {
		Equipe e1 = demiAller.getEquipeDomicile();
		Equipe e2 = demiAller.getEquipeExterieur();

		int nbButsE1 = demiAller.getButs(e1) + demiRetour.getButs(e1);
		int nbButsE2 = demiAller.getButs(e2) + demiRetour.getButs(e2);

		if (nbButsE1 > nbButsE2) {
			return e2;
		} else if (nbButsE1 < nbButsE2) {
			return e1;
		} else {
			int nbButsExtE1 = demiRetour.getButs(e1);
			int nbButsExtE2 = demiAller.getButs(e2);
			if (nbButsExtE1 > nbButsExtE2) {
				return e2;
			} else if (nbButsExtE1 < nbButsExtE2) {
				return e1;
			} else {
				if (demiRetour.getTabDom() != null) {
					if (demiRetour.getTabDom() > demiRetour.getTabExt()) {
						return e1;
					} else {
						return e2;
					}
				}// TODO Tirs aux buts
				return e2;
			}
		}
	}

	private Match findMatchByEquipe(List<Match> matchs, Equipe e) {
		for (Match match : matchs) {
			if (match.getEquipeDomicile().getId().equals(e.getId())) {
				return match;
			} else if (match.getEquipeExterieur().getId().equals(e.getId())) {
				return match;
			}
		}
		return null;
	}

	public String phase4Init() {
		return SUCCESS;
	}

	public int getIdTournoi() {
		return idTournoi;
	}

	public void setIdTournoi(int idTournoi) {
		this.idTournoi = idTournoi;
	}

	public boolean isInfo() {
		return info;
	}

	public void setInfo(boolean info) {
		this.info = info;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	@SuppressWarnings("unchecked")
	public void setSession(Map session) {
		this.session = session;
	}

	public List<Tournoi> getTournoisDispos() {
		return tournoisDispos;
	}

	public void setTournoisDispos(List<Tournoi> tournoisDispos) {
		this.tournoisDispos = tournoisDispos;
	}

	public Equipe getE1() {
		return e1;
	}

	public void setE1(Equipe e1) {
		this.e1 = e1;
	}

	public Equipe getE2() {
		return e2;
	}

	public void setE2(Equipe e2) {
		this.e2 = e2;
	}

	public Equipe getE3() {
		return e3;
	}

	public void setE3(Equipe e3) {
		this.e3 = e3;
	}

	public Equipe getE4() {
		return e4;
	}

	public void setE4(Equipe e4) {
		this.e4 = e4;
	}

	public Equipe getE5() {
		return e5;
	}

	public void setE5(Equipe e5) {
		this.e5 = e5;
	}

	public Equipe getE6() {
		return e6;
	}

	public void setE6(Equipe e6) {
		this.e6 = e6;
	}

	public Equipe getE7() {
		return e7;
	}

	public void setE7(Equipe e7) {
		this.e7 = e7;
	}

	public Equipe getE8() {
		return e8;
	}

	public void setE8(Equipe e8) {
		this.e8 = e8;
	}

	public int getP1() {
		return p1;
	}

	public void setP1(int p1) {
		this.p1 = p1;
	}

	public int getP2() {
		return p2;
	}

	public void setP2(int p2) {
		this.p2 = p2;
	}

	public int getP3() {
		return p3;
	}

	public void setP3(int p3) {
		this.p3 = p3;
	}

	public int getP4() {
		return p4;
	}

	public void setP4(int p4) {
		this.p4 = p4;
	}

	public int getP5() {
		return p5;
	}

	public void setP5(int p5) {
		this.p5 = p5;
	}

	public int getP6() {
		return p6;
	}

	public void setP6(int p6) {
		this.p6 = p6;
	}

	public int getP7() {
		return p7;
	}

	public void setP7(int p7) {
		this.p7 = p7;
	}

	public int getP8() {
		return p8;
	}

	public void setP8(int p8) {
		this.p8 = p8;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public List<String> getPoules() {
		return poules;
	}

	public void setPoules(List<String> poules) {
		this.poules = poules;
	}

	public List<Integer> getPositions() {
		return positions;
	}

	public void setPositions(List<Integer> positions) {
		this.positions = positions;
	}

	public String getPoule1() {
		return poule1;
	}

	public void setPoule1(String poule1) {
		this.poule1 = poule1;
	}

	public String getPoule2() {
		return poule2;
	}

	public void setPoule2(String poule2) {
		this.poule2 = poule2;
	}

	public String getPoule3() {
		return poule3;
	}

	public void setPoule3(String poule3) {
		this.poule3 = poule3;
	}

	public String getPoule4() {
		return poule4;
	}

	public void setPoule4(String poule4) {
		this.poule4 = poule4;
	}

	public String getPoule5() {
		return poule5;
	}

	public void setPoule5(String poule5) {
		this.poule5 = poule5;
	}

	public String getPoule6() {
		return poule6;
	}

	public void setPoule6(String poule6) {
		this.poule6 = poule6;
	}

	public String getPoule7() {
		return poule7;
	}

	public void setPoule7(String poule7) {
		this.poule7 = poule7;
	}

	public String getPoule8() {
		return poule8;
	}

	public void setPoule8(String poule8) {
		this.poule8 = poule8;
	}

	public Integer getPos1() {
		return pos1;
	}

	public void setPos1(Integer pos1) {
		this.pos1 = pos1;
	}

	public Integer getPos2() {
		return pos2;
	}

	public void setPos2(Integer pos2) {
		this.pos2 = pos2;
	}

	public Integer getPos3() {
		return pos3;
	}

	public void setPos3(Integer pos3) {
		this.pos3 = pos3;
	}

	public Integer getPos4() {
		return pos4;
	}

	public void setPos4(Integer pos4) {
		this.pos4 = pos4;
	}

	public Integer getPos5() {
		return pos5;
	}

	public void setPos5(Integer pos5) {
		this.pos5 = pos5;
	}

	public Integer getPos6() {
		return pos6;
	}

	public void setPos6(Integer pos6) {
		this.pos6 = pos6;
	}

	public Integer getPos7() {
		return pos7;
	}

	public void setPos7(Integer pos7) {
		this.pos7 = pos7;
	}

	public Integer getPos8() {
		return pos8;
	}

	public void setPos8(Integer pos8) {
		this.pos8 = pos8;
	}

	public List<Equipe> getEquipesPouleA() {
		return equipesPouleA;
	}

	public void setEquipesPouleA(List<Equipe> equipesPouleA) {
		this.equipesPouleA = equipesPouleA;
	}

	public List<Equipe> getEquipesPouleB() {
		return equipesPouleB;
	}

	public void setEquipesPouleB(List<Equipe> equipesPouleB) {
		this.equipesPouleB = equipesPouleB;
	}

	public Equipe getEquipeA1() {
		return equipeA1;
	}

	public void setEquipeA1(Equipe equipeA1) {
		this.equipeA1 = equipeA1;
	}

	public Equipe getEquipeA2() {
		return equipeA2;
	}

	public void setEquipeA2(Equipe equipeA2) {
		this.equipeA2 = equipeA2;
	}

	public Equipe getEquipeA3() {
		return equipeA3;
	}

	public void setEquipeA3(Equipe equipeA3) {
		this.equipeA3 = equipeA3;
	}

	public Equipe getEquipeA4() {
		return equipeA4;
	}

	public void setEquipeA4(Equipe equipeA4) {
		this.equipeA4 = equipeA4;
	}

	public Equipe getEquipeB1() {
		return equipeB1;
	}

	public void setEquipeB1(Equipe equipeB1) {
		this.equipeB1 = equipeB1;
	}

	public Equipe getEquipeB2() {
		return equipeB2;
	}

	public void setEquipeB2(Equipe equipeB2) {
		this.equipeB2 = equipeB2;
	}

	public Equipe getEquipeB3() {
		return equipeB3;
	}

	public void setEquipeB3(Equipe equipeB3) {
		this.equipeB3 = equipeB3;
	}

	public Equipe getEquipeB4() {
		return equipeB4;
	}

	public void setEquipeB4(Equipe equipeB4) {
		this.equipeB4 = equipeB4;
	}

	public boolean isModif() {
		return modif;
	}

	public void setModif(boolean modif) {
		this.modif = modif;
	}

	public int getIdMatchEnCours() {
		return idMatchEnCours;
	}

	public void setIdMatchEnCours(int idMatchEnCours) {
		this.idMatchEnCours = idMatchEnCours;
	}

	public List<ETypeEvenement> getTypesEvenements() {
		return typesEvenements;
	}

	public void setTypesEvenements(List<ETypeEvenement> typesEvenements) {
		this.typesEvenements = typesEvenements;
	}

	public String getCodeTypeEvenement() {
		return codeTypeEvenement;
	}

	public void setCodeTypeEvenement(String codeTypeEvenement) {
		this.codeTypeEvenement = codeTypeEvenement;
	}

	public List<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(List<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	public int getIdJoueur() {
		return idJoueur;
	}

	public void setIdJoueur(int idJoueur) {
		this.idJoueur = idJoueur;
	}

	public List<Integer> getMinutes() {
		return minutes;
	}

	public void setMinutes(List<Integer> minutes) {
		this.minutes = minutes;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	public Match getDemi1Aller() {
		return demi1Aller;
	}

	public void setDemi1Aller(Match demi1Aller) {
		this.demi1Aller = demi1Aller;
	}

	public Match getDemi1Retour() {
		return demi1Retour;
	}

	public void setDemi1Retour(Match demi1Retour) {
		this.demi1Retour = demi1Retour;
	}

	public Match getDemi2Aller() {
		return demi2Aller;
	}

	public void setDemi2Aller(Match demi2Aller) {
		this.demi2Aller = demi2Aller;
	}

	public Match getDemi2Retour() {
		return demi2Retour;
	}

	public void setDemi2Retour(Match demi2Retour) {
		this.demi2Retour = demi2Retour;
	}

	public Match getDemi3Aller() {
		return demi3Aller;
	}

	public void setDemi3Aller(Match demi3Aller) {
		this.demi3Aller = demi3Aller;
	}

	public Match getDemi3Retour() {
		return demi3Retour;
	}

	public void setDemi3Retour(Match demi3Retour) {
		this.demi3Retour = demi3Retour;
	}

	public Match getDemi4Aller() {
		return demi4Aller;
	}

	public void setDemi4Aller(Match demi4Aller) {
		this.demi4Aller = demi4Aller;
	}

	public Match getDemi4Retour() {
		return demi4Retour;
	}

	public void setDemi4Retour(Match demi4Retour) {
		this.demi4Retour = demi4Retour;
	}

	public Match getFinale1() {
		return finale1;
	}

	public void setFinale1(Match finale1) {
		this.finale1 = finale1;
	}

	public Match getFinale2() {
		return finale2;
	}

	public void setFinale2(Match finale2) {
		this.finale2 = finale2;
	}

	public Match getFinale3() {
		return finale3;
	}

	public void setFinale3(Match finale3) {
		this.finale3 = finale3;
	}

	public Match getFinale4() {
		return finale4;
	}

	public void setFinale4(Match finale4) {
		this.finale4 = finale4;
	}

	public boolean isFinales() {
		return finales;
	}

	public void setFinales(boolean finales) {
		this.finales = finales;
	}

	public boolean isValidationFinale() {
		return validationFinale;
	}

	public void setValidationFinale(boolean validationFinale) {
		this.validationFinale = validationFinale;
	}

}
