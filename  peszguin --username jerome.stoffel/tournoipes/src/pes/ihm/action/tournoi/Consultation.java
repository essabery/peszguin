package pes.ihm.action.tournoi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.struts2.interceptor.SessionAware;

import pes.dao.IEvenementDAO;
import pes.dao.IMatchDAO;
import pes.dao.ITournoiDAO;
import pes.domaine.ENiveauMatch;
import pes.domaine.EStatutTournoi;
import pes.domaine.ETypeEvenement;
import pes.domaine.Equipe;
import pes.domaine.Evenement;
import pes.domaine.Joueur;
import pes.domaine.Match;
import pes.domaine.Tournoi;
import pes.service.comparator.BoucherComparator;
import pes.service.comparator.EquipeComparatorPoule;
import pes.service.comparator.NiveauMatchComparator;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Consultation extends ActionSupport implements SessionAware {

	private List<Tournoi> tournois;
	private int idTournoi;
	private Tournoi tournoi;

	private Integer idMatchEnCours;

	private boolean info;
	private boolean error;
	private boolean afficherStats;
	private boolean afficherMatch;
	private boolean init;

	private List<Equipe> equipesPouleA;
	private List<Equipe> equipesPouleB;

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

	private List<Joueur> buteurs;
	private List<Joueur> bouchers;
	private List<Joueur> blesses;

	private IMatchDAO matchDAO;
	private ITournoiDAO tournoiDAO;
	private IEvenementDAO evenementDAO;

	@SuppressWarnings("unchecked")
	private Map session;

	public String initialiser() {
		init = true;
		afficherTournois();
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String afficher() {
		afficherStats = true;
		session.put("idTournoi", idTournoi);
		afficherTout();
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String afficherMatch() {
		afficherStats = true;
		if (idMatchEnCours == null) {
			afficherTout();
			return SUCCESS;
		}
		Match matchEnCours = matchDAO.findById(idMatchEnCours);

		session.put("matchEnCours", matchEnCours);
		afficherMatch = true;

		afficherTout();
		return SUCCESS;
	}

	private void afficherTout() {
		afficherMatchsPoule();
		afficherMatchsArbreFinal();
		afficherButeurs();
		afficherBouchers();
		afficherBlesses();

	}

	private void afficherTournois() {
		tournois = tournoiDAO.findByStatut(EStatutTournoi.TERMINE);
	}

	@SuppressWarnings("unchecked")
	private void afficherMatchsPoule() {
		tournoi = tournoiDAO.findById((Integer) session.get("idTournoi"));

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

	private void afficherMatchsArbreFinal() {
		tournoi = tournoiDAO.findById((Integer) session.get("idTournoi"));

		List<Match> matchsDemiAller = matchDAO.find(tournoi,
				ENiveauMatch.DEMI_ALLER);
		List<Match> matchsDemiRetour = matchDAO.find(tournoi,
				ENiveauMatch.DEMI_RETOUR);

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

		finale1 = matchDAO.find(tournoi, ENiveauMatch.FINALE1).get(0);
		finale2 = matchDAO.find(tournoi, ENiveauMatch.FINALE3).get(0);
		finale3 = matchDAO.find(tournoi, ENiveauMatch.FINALE5).get(0);
		finale4 = matchDAO.find(tournoi, ENiveauMatch.FINALE7).get(0);
	}

	private void afficherButeurs() {
		tournoi = tournoiDAO.findById((Integer) session.get("idTournoi"));

		List<Evenement> buts = evenementDAO.find(tournoi, ETypeEvenement.BUT);
		Set<Joueur> temp = new HashSet<Joueur>();
		for (Evenement but : buts) {
			temp.add(but.getJoueur());
		}
		buteurs = new ArrayList<Joueur>(temp);
	}

	private void afficherBlesses() {
		tournoi = tournoiDAO.findById((Integer) session.get("idTournoi"));

		List<Evenement> blessures = evenementDAO.find(tournoi,
				ETypeEvenement.XBLESSURE);
		Set<Joueur> temp = new HashSet<Joueur>();
		for (Evenement blessure : blessures) {
			temp.add(blessure.getJoueur());
		}
		blesses = new ArrayList<Joueur>(temp);
	}

	private void afficherBouchers() {
		tournoi = tournoiDAO.findById((Integer) session.get("idTournoi"));

		Set<Joueur> temp = new HashSet<Joueur>();
		List<Evenement> jaunes = evenementDAO.find(tournoi,
				ETypeEvenement.CARTON_JAUNE);
		for (Evenement jaune : jaunes) {
			temp.add(jaune.getJoueur());
		}

		List<Evenement> rouges = evenementDAO.find(tournoi,
				ETypeEvenement.CARTON_ROUGE);
		for (Evenement rouge : rouges) {
			temp.add(rouge.getJoueur());
		}
		bouchers = new ArrayList<Joueur>(temp);

		Collections.sort(bouchers, new BoucherComparator(tournoi));
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

	// public String supprimer() {
	// tournoi = daoFactory.getTournoiDAO().findById(idTournoi);
	// daoFactory.getTournoiDAO().delete(tournoi);
	// tournois = tService.findAll();
	// info = true;
	// return SUCCESS;
	//
	// }

	public List<Tournoi> getTournois() {
		return tournois;
	}

	public void setTournois(List<Tournoi> tournois) {
		this.tournois = tournois;
	}

	public int getIdTournoi() {
		return idTournoi;
	}

	public void setIdTournoi(int idTournoi) {
		this.idTournoi = idTournoi;
	}

	public Tournoi getTournoi() {
		return tournoi;
	}

	public void setTournoi(Tournoi tournoi) {
		this.tournoi = tournoi;
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

	public boolean isAfficherStats() {
		return afficherStats;
	}

	public void setAfficherStats(boolean afficherStats) {
		this.afficherStats = afficherStats;
	}

	@SuppressWarnings("unchecked")
	public void setSession(Map session) {
		this.session = session;
	}

	public boolean isAfficherMatch() {
		return afficherMatch;
	}

	public void setAfficherMatch(boolean afficherMatch) {
		this.afficherMatch = afficherMatch;
	}

	public Integer getIdMatchEnCours() {
		return idMatchEnCours;
	}

	public void setIdMatchEnCours(Integer idMatchEnCours) {
		this.idMatchEnCours = idMatchEnCours;
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

	public boolean isInit() {
		return init;
	}

	public void setInit(boolean init) {
		this.init = init;
	}

	public List<Joueur> getButeurs() {
		return buteurs;
	}

	public void setButeurs(List<Joueur> buteurs) {
		this.buteurs = buteurs;
	}

	public List<Joueur> getBlesses() {
		return blesses;
	}

	public void setBlesses(List<Joueur> blesses) {
		this.blesses = blesses;
	}

	public List<Joueur> getBouchers() {
		return bouchers;
	}

	public void setBouchers(List<Joueur> bouchers) {
		this.bouchers = bouchers;
	}

}
