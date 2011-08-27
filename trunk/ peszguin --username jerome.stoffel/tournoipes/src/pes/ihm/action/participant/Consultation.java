package pes.ihm.action.participant;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.SessionAware;

import pes.dao.IParticipantDAO;
import pes.dao.ITournoiDAO;
import pes.domaine.EStatutTournoi;
import pes.domaine.ETypeEvenement;
import pes.domaine.Equipe;
import pes.domaine.Evenement;
import pes.domaine.Joueur;
import pes.domaine.Match;
import pes.domaine.Participant;
import pes.domaine.StatsParticipantTournoi;
import pes.domaine.Tournoi;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Consultation extends ActionSupport implements SessionAware {

	private List<Participant> participants;
	private int idParticipant;
	private Participant participant;

	private IParticipantDAO participantDAO;
	private ITournoiDAO tournoiDAO;

	private boolean info;
	private boolean error;
	private boolean init;
	private int nbTournois;
	private int nbVictoires;
	private int nbNuls;
	private int nbDefaites;
	private int nbButsMarques;
	private int nbButsEncaisses;
	private int nbCartonsJaunes;
	private int nbCartonsRouges;
	private int nbBlessures;
	private int nbBlessuresInfligees;
	private int meilleurClassement;
	private int pireClassement;

	private int nbButsMarquesDom;
	private int nbButsMarquesExt;
	private int nbButsMarquesFinale;
	private int nbButsEncaissesDom;
	private int nbButsEncaissesExt;
	private int nbButsEncaissesFinale;
	private int nbCartonsJaunesDom;
	private int nbCartonsJaunesExt;
	private int nbCartonsJaunesFinale;
	private int nbCartonsRougesDom;
	private int nbCartonsRougesExt;
	private int nbCartonsRougesFinale;
	private int nbBlessuresDom;
	private int nbBlessuresExt;
	private int nbBlessuresFinale;
	private int nbBlessuresInfligeesDom;
	private int nbBlessuresInfligeesExt;
	private int nbBlessuresInfligeesFinale;
	private int nbVictoiresDom;
	private int nbVictoiresExt;
	private int nbVictoiresFinale;
	private int nbNulsDom;
	private int nbNulsExt;
	private int nbDefaitesDom;
	private int nbDefaitesExt;
	private int nbDefaitesFinale;

	private int nbMatchs;
	private int nbMatchsDom;
	private int nbMatchsExt;
	private int nbMatchsFinale;

	private String nbButsMarquesParMatch;
	private String nbButsMarquesParMatchDom;
	private String nbButsMarquesParMatchExt;
	private String nbButsMarquesParMatchFinale;

	private String nbButsEncaissesParMatch;
	private String nbButsEncaissesParMatchDom;
	private String nbButsEncaissesParMatchExt;
	private String nbButsEncaissesParMatchFinale;

	private String nbCartonsJaunesParMatch;
	private String nbCartonsJaunesParMatchDom;
	private String nbCartonsJaunesParMatchExt;
	private String nbCartonsJaunesParMatchFinale;

	private String nbCartonsRougesParMatch;
	private String nbCartonsRougesParMatchDom;
	private String nbCartonsRougesParMatchExt;
	private String nbCartonsRougesParMatchFinale;

	private String nbBlessuresParMatch;
	private String nbBlessuresParMatchDom;
	private String nbBlessuresParMatchExt;
	private String nbBlessuresParMatchFinale;

	private String nbBlessuresInfligeesParMatch;
	private String nbBlessuresInfligeesParMatchDom;
	private String nbBlessuresInfligeesParMatchExt;
	private String nbBlessuresInfligeesParMatchFinale;

	private String pourcentageVictoire;
	private String pourcentageVictoireDom;
	private String pourcentageVictoireExt;
	private String pourcentageVictoireFinale;

	private String pourcentageDefaite;
	private String pourcentageDefaiteDom;
	private String pourcentageDefaiteExt;
	private String pourcentageDefaiteFinale;

	private String pourcentageNul;
	private String pourcentageNulDom;
	private String pourcentageNulExt;

	private Set<Joueur> buteurs;
	private List<Tournoi> tournois;
	private Set<Participant> adversaires;

	private Tournoi tournoiMeilleurClassement;
	private Tournoi tournoiPireClassement;
	private Tournoi tournoiPlusGrosseVictoire;
	private Tournoi tournoiPlusGrosseDefaite;

	private Match plusGrosseVictoire;
	private Match plusGrosseDefaite;

	@SuppressWarnings("unchecked")
	private Map session;

	public String initialiser() {
		init = true;
		participants = participantDAO.findAll();
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String afficher() {
		participant = participantDAO.findById(idParticipant);
		session.put("idParticipant", idParticipant);
		List<Tournoi> allTournois = tournoiDAO
				.findByStatut(EStatutTournoi.TERMINE);
		tournois = new ArrayList<Tournoi>();

		for (Tournoi tournoi : allTournois) {
			for (Participant p : tournoi.getParticipants()) {
				if (p.getId().equals(participant.getId())) {
					tournois.add(tournoi);
				}
			}
		}

		nbTournois = tournois.size();
		nbVictoires = 0;
		nbVictoiresDom = 0;
		nbVictoiresExt = 0;
		nbVictoiresFinale = 0;
		nbNuls = 0;
		nbNulsDom = 0;
		nbNulsExt = 0;
		nbDefaites = 0;
		nbDefaitesDom = 0;
		nbDefaitesExt = 0;
		nbDefaitesFinale = 0;
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

		meilleurClassement = 9;
		pireClassement = 0;
		if (tournois.size() == 0) {
			meilleurClassement = 0;
			pireClassement = 0;
		}

		int plusGrosseVictoireDiff = 0;
		int plusGrosseVictoireNbButs = 0;
		int plusGrosseDefaiteDiff = 0;
		int plusGrosseDefaiteNbButs = 0;

		buteurs = new HashSet<Joueur>();
		adversaires = new HashSet<Participant>();

		Evenement[] butsMarques = new Evenement[9999];
		Evenement[] butsEncaisses = new Evenement[9999];
		int iButsMarques = 0;
		int iButsEncaisses = 0;

		for (Tournoi tournoi : tournois) {
			// Calcul stats générales
			StatsParticipantTournoi stats = new StatsParticipantTournoi(
					participant, tournoi);

			nbMatchs = nbMatchs + stats.getNbMatchs();
			nbMatchsDom = nbMatchsDom + stats.getNbMatchsDom();
			nbMatchsExt = nbMatchsExt + stats.getNbMatchsExt();
			nbMatchsFinale = nbMatchsFinale + stats.getNbMatchsFinale();

			nbVictoires = nbVictoires + stats.getNbVictoires();
			nbVictoiresDom = nbVictoiresDom + stats.getNbVictoiresDom();
			nbVictoiresExt = nbVictoiresExt + stats.getNbVictoiresExt();
			nbVictoiresFinale = nbVictoiresFinale
					+ stats.getNbVictoiresFinale();

			nbDefaites = nbDefaites + stats.getNbDefaites();
			nbDefaitesDom = nbDefaitesDom + stats.getNbDefaitesDom();
			nbDefaitesExt = nbDefaitesExt + stats.getNbDefaitesExt();
			nbDefaitesFinale = nbDefaitesFinale + stats.getNbDefaitesFinale();

			nbNuls = nbNuls + stats.getNbNuls();
			nbNulsDom = nbNulsDom + stats.getNbNulsDom();
			nbNulsExt = nbNulsExt + stats.getNbNulsExt();

			nbButsMarques = nbButsMarques + stats.getNbButsMarques();
			nbButsMarquesDom = nbButsMarquesDom + stats.getNbButsMarquesDom();
			nbButsMarquesExt = nbButsMarquesExt + stats.getNbButsMarquesExt();
			nbButsMarquesFinale = nbButsMarquesFinale
					+ stats.getNbButsMarquesFinale();

			nbButsEncaisses = nbButsEncaisses + stats.getNbButsEncaisses();
			nbButsEncaissesDom = nbButsEncaissesDom
					+ stats.getNbButsEncaissesDom();
			nbButsEncaissesExt = nbButsEncaissesExt
					+ stats.getNbButsEncaissesExt();
			nbButsEncaissesFinale = nbButsEncaissesFinale
					+ stats.getNbButsEncaissesFinale();

			nbCartonsJaunes = nbCartonsJaunes + stats.getNbCartonsJaunes();
			nbCartonsJaunesDom = nbCartonsJaunesDom
					+ stats.getNbCartonsJaunesDom();
			nbCartonsJaunesExt = nbCartonsJaunesExt
					+ stats.getNbCartonsJaunesExt();
			nbCartonsJaunesFinale = nbCartonsJaunesFinale
					+ stats.getNbCartonsJaunesFinale();

			nbCartonsRouges = nbCartonsRouges + stats.getNbCartonsRouges();
			nbCartonsRougesDom = nbCartonsRougesDom
					+ stats.getNbCartonsRougesDom();
			nbCartonsRougesExt = nbCartonsRougesExt
					+ stats.getNbCartonsRougesExt();
			nbCartonsRougesFinale = nbCartonsRougesFinale
					+ stats.getNbCartonsRougesFinale();

			nbBlessures = nbBlessures + stats.getNbBlessures();
			nbBlessuresDom = nbBlessuresDom + stats.getNbBlessuresDom();
			nbBlessuresExt = nbBlessuresExt + stats.getNbBlessuresExt();
			nbBlessuresFinale = nbBlessuresFinale
					+ stats.getNbBlessuresFinale();

			nbBlessuresInfligees = nbBlessuresInfligees
					+ stats.getNbBlessuresInfligees();
			nbBlessuresInfligeesDom = nbBlessuresInfligeesDom
					+ stats.getNbBlessuresInfligeesDom();
			nbBlessuresInfligeesExt = nbBlessuresInfligeesExt
					+ stats.getNbBlessuresInfligeesExt();
			nbBlessuresInfligeesFinale = nbBlessuresInfligeesFinale
					+ stats.getNbBlessuresInfligeesFinale();

			// Calcul meilleur/pire classement
			int classement = participant.getClassement(tournoi);
			if (classement < meilleurClassement) {
				meilleurClassement = classement;
				tournoiMeilleurClassement = tournoi;
			}
			if (classement > pireClassement) {
				pireClassement = classement;
				tournoiPireClassement = tournoi;
			}

			// Recherche buteurs
			Equipe equipe = participant.getEquipe(tournoi);
			for (Joueur joueur : equipe.getJoueurs()) {
				if (joueur.getEvenements(tournoi, ETypeEvenement.BUT).size() > 0) {
					buteurs.add(joueur);
				}
			}

			for (Match match : tournoi.findMatchs(equipe)) {
				// Recherche de tous les buts (buts pour et buts contre)
				for (Evenement evenement : match.getEvenements()) {
					if (evenement.getType() == ETypeEvenement.BUT) {
						if (evenement.getEquipe().getParticipant().getId()
								.equals(participant.getId())) {
							butsMarques[iButsMarques] = evenement;
							iButsMarques++;
						} else {
							butsEncaisses[iButsEncaisses] = evenement;
							iButsEncaisses++;
						}
					}
				}

				// recherche des adversaires
				if (match.getEquipeDomicile().getId().equals(equipe.getId())) {
					adversaires
							.add(match.getEquipeExterieur().getParticipant());
				} else {
					adversaires.add(match.getEquipeDomicile().getParticipant());
				}

				// recherche plus grosse victoire et plus grosse défaite
				if (match.getGagnant() != null
						&& match.getGagnant().getId().equals(equipe.getId())) {
					int diff = match.getButs(equipe)
							- match.getButsContre(equipe);
					if (diff > plusGrosseVictoireDiff) {
						plusGrosseVictoireNbButs = match.getButs(equipe)
								+ match.getButsContre(equipe);
						plusGrosseVictoireDiff = diff;
						plusGrosseVictoire = match;
						tournoiPlusGrosseVictoire = tournoi;
					} else if (diff == plusGrosseVictoireDiff) {
						int nbButs = match.getButs(equipe)
								+ match.getButsContre(equipe);
						if (nbButs > plusGrosseVictoireNbButs) {
							plusGrosseVictoireNbButs = nbButs;
							plusGrosseVictoire = match;
							tournoiPlusGrosseVictoire = tournoi;
						}
					}
				} else if (match.getPerdant() != null
						&& match.getPerdant().getId().equals(equipe.getId())) {
					int diff = match.getButs(equipe)
							- match.getButsContre(equipe);
					if (diff < plusGrosseDefaiteDiff) {
						plusGrosseDefaiteNbButs = match.getButs(equipe)
								+ match.getButsContre(equipe);
						plusGrosseDefaiteDiff = diff;
						plusGrosseDefaite = match;
						tournoiPlusGrosseDefaite = tournoi;
					} else if (diff == plusGrosseDefaiteDiff) {
						int nbButs = match.getButs(equipe)
								+ match.getButsContre(equipe);
						if (nbButs > plusGrosseDefaiteNbButs) {
							plusGrosseDefaiteNbButs = nbButs;
							plusGrosseDefaite = match;
							tournoiPlusGrosseDefaite = tournoi;
						}
					}
				}
			}
		}// Fin for tournois

		NumberFormat formatter = new DecimalFormat("#0.00");

		nbButsMarquesParMatch = formatter
				.format(1.0 * nbButsMarques / nbMatchs);
		nbButsMarquesParMatchDom = formatter.format(1.0 * nbButsMarquesDom
				/ nbMatchsDom);
		nbButsMarquesParMatchExt = formatter.format(1.0 * nbButsMarquesExt
				/ nbMatchsExt);
		nbButsMarquesParMatchFinale = formatter.format(1.0
				* nbButsMarquesFinale / nbMatchsFinale);

		pourcentageVictoire = (int) (100.0 * nbVictoires / nbMatchs + 0.5)
				+ "%";
		pourcentageVictoireDom = (int) (100.0 * nbVictoiresDom / nbMatchsDom + 0.5)
				+ "%";
		pourcentageVictoireExt = (int) (100.0 * nbVictoiresExt / nbMatchsExt + 0.5)
				+ "%";
		pourcentageVictoireFinale = (int) (100.0 * nbVictoiresFinale
				/ nbMatchsFinale + 0.5)
				+ "%";

		pourcentageDefaite = (int) (100.0 * nbDefaites / nbMatchs + 0.5) + "%";
		pourcentageDefaiteDom = (int) (100.0 * nbDefaitesDom / nbMatchsDom + 0.5)
				+ "%";
		pourcentageDefaiteExt = (int) (100.0 * nbDefaitesExt / nbMatchsExt + 0.5)
				+ "%";
		pourcentageDefaiteFinale = (int) (100.0 * nbDefaitesFinale
				/ nbMatchsFinale + 0.5)
				+ "%";

		pourcentageNul = (int) (100.0 * nbNuls / nbMatchs + 0.5) + "%";
		pourcentageNulDom = (int) (100.0 * nbNulsDom / nbMatchsDom + 0.5) + "%";
		pourcentageNulExt = (int) (100.0 * nbNulsExt / nbMatchsExt + 0.5) + "%";

		nbButsEncaissesParMatch = formatter.format(1.0 * nbButsEncaisses
				/ nbMatchs);
		nbButsEncaissesParMatchDom = formatter.format(1.0 * nbButsEncaissesDom
				/ nbMatchsDom);
		nbButsEncaissesParMatchExt = formatter.format(1.0 * nbButsEncaissesExt
				/ nbMatchsExt);
		nbButsEncaissesParMatchFinale = formatter.format(1.0
				* nbButsEncaissesFinale / nbMatchsFinale);

		nbCartonsJaunesParMatch = formatter.format(1.0 * nbCartonsJaunes
				/ nbMatchs);
		nbCartonsJaunesParMatchDom = formatter.format(1.0 * nbCartonsJaunesDom
				/ nbMatchsDom);
		nbCartonsJaunesParMatchExt = formatter.format(1.0 * nbCartonsJaunesExt
				/ nbMatchsExt);
		nbCartonsJaunesParMatchFinale = formatter.format(1.0
				* nbCartonsJaunesFinale / nbMatchsFinale);

		nbCartonsRougesParMatch = formatter.format(1.0 * nbCartonsRouges
				/ nbMatchs);
		nbCartonsRougesParMatchDom = formatter.format(1.0 * nbCartonsRougesDom
				/ nbMatchsDom);
		nbCartonsRougesParMatchExt = formatter.format(1.0 * nbCartonsRougesExt
				/ nbMatchsExt);
		nbCartonsRougesParMatchFinale = formatter.format(1.0
				* nbCartonsRougesFinale / nbMatchsFinale);

		nbBlessuresParMatch = formatter.format(1.0 * nbBlessures / nbMatchs);
		nbBlessuresParMatchDom = formatter.format(1.0 * nbBlessuresDom
				/ nbMatchsDom);
		nbBlessuresParMatchExt = formatter.format(1.0 * nbBlessuresExt
				/ nbMatchsExt);
		nbBlessuresParMatchFinale = formatter.format(1.0 * nbBlessuresFinale
				/ nbMatchsFinale);

		nbBlessuresInfligeesParMatch = formatter.format(1.0
				* nbBlessuresInfligees / nbMatchs);
		nbBlessuresInfligeesParMatchDom = formatter.format(1.0
				* nbBlessuresInfligeesDom / nbMatchsDom);
		nbBlessuresInfligeesParMatchExt = formatter.format(1.0
				* nbBlessuresInfligeesExt / nbMatchsExt);
		nbBlessuresInfligeesParMatchFinale = formatter.format(1.0
				* nbBlessuresInfligeesFinale / nbMatchsFinale);

		session.put("butsMarques", butsMarques);
		session.put("butsEncaisses", butsEncaisses);

		return SUCCESS;
	}

	public String supprimer() {
		participant = participantDAO.findById(idParticipant);
		participantDAO.delete(participant);
		participants = participantDAO.findAll();
		info = true;
		return SUCCESS;

	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public int getIdParticipant() {
		return idParticipant;
	}

	public void setIdParticipant(int idParticipant) {
		this.idParticipant = idParticipant;
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
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

	public boolean isInit() {
		return init;
	}

	public void setInit(boolean init) {
		this.init = init;
	}

	public int getNbTournois() {
		return nbTournois;
	}

	public void setNbTournois(int nbTournois) {
		this.nbTournois = nbTournois;
	}

	public int getNbVictoires() {
		return nbVictoires;
	}

	public void setNbVictoires(int nbVictoires) {
		this.nbVictoires = nbVictoires;
	}

	public int getNbNuls() {
		return nbNuls;
	}

	public void setNbNuls(int nbNuls) {
		this.nbNuls = nbNuls;
	}

	public int getNbDefaites() {
		return nbDefaites;
	}

	public void setNbDefaites(int nbDefaites) {
		this.nbDefaites = nbDefaites;
	}

	public int getNbButsMarques() {
		return nbButsMarques;
	}

	public void setNbButsMarques(int nbButsMarques) {
		this.nbButsMarques = nbButsMarques;
	}

	public int getNbButsEncaisses() {
		return nbButsEncaisses;
	}

	public void setNbButsEncaisses(int nbButsEncaisses) {
		this.nbButsEncaisses = nbButsEncaisses;
	}

	public Set<Joueur> getButeurs() {
		return buteurs;
	}

	public void setButeurs(Set<Joueur> buteurs) {
		this.buteurs = buteurs;
	}

	@SuppressWarnings("unchecked")
	public void setSession(Map session) {
		this.session = session;
	}

	public List<Tournoi> getTournois() {
		return tournois;
	}

	public void setTournois(List<Tournoi> tournois) {
		this.tournois = tournois;
	}

	public int getMeilleurClassement() {
		return meilleurClassement;
	}

	public void setMeilleurClassement(int meilleurClassement) {
		this.meilleurClassement = meilleurClassement;
	}

	public Match getPlusGrosseVictoire() {
		return plusGrosseVictoire;
	}

	public void setPlusGrosseVictoire(Match plusGrosseVictoire) {
		this.plusGrosseVictoire = plusGrosseVictoire;
	}

	public Match getPlusGrosseDefaite() {
		return plusGrosseDefaite;
	}

	public void setPlusGrosseDefaite(Match plusGrosseDefaite) {
		this.plusGrosseDefaite = plusGrosseDefaite;
	}

	public Tournoi getTournoiMeilleurClassement() {
		return tournoiMeilleurClassement;
	}

	public void setTournoiMeilleurClassement(Tournoi tournoiMeilleurClassement) {
		this.tournoiMeilleurClassement = tournoiMeilleurClassement;
	}

	public Tournoi getTournoiPlusGrosseVictoire() {
		return tournoiPlusGrosseVictoire;
	}

	public void setTournoiPlusGrosseVictoire(Tournoi tournoiPlusGrosseVictoire) {
		this.tournoiPlusGrosseVictoire = tournoiPlusGrosseVictoire;
	}

	public Tournoi getTournoiPlusGrosseDefaite() {
		return tournoiPlusGrosseDefaite;
	}

	public void setTournoiPlusGrosseDefaite(Tournoi tournoiPlusGrosseDefaite) {
		this.tournoiPlusGrosseDefaite = tournoiPlusGrosseDefaite;
	}

	public Set<Participant> getAdversaires() {
		return adversaires;
	}

	public void setAdversaires(Set<Participant> adversaires) {
		this.adversaires = adversaires;
	}

	public int getPireClassement() {
		return pireClassement;
	}

	public void setPireClassement(int pireClassement) {
		this.pireClassement = pireClassement;
	}

	public Tournoi getTournoiPireClassement() {
		return tournoiPireClassement;
	}

	public void setTournoiPireClassement(Tournoi tournoiPireClassement) {
		this.tournoiPireClassement = tournoiPireClassement;
	}

	public int getNbCartonsJaunes() {
		return nbCartonsJaunes;
	}

	public void setNbCartonsJaunes(int nbCartonsJaunes) {
		this.nbCartonsJaunes = nbCartonsJaunes;
	}

	public int getNbCartonsRouges() {
		return nbCartonsRouges;
	}

	public void setNbCartonsRouges(int nbCartonsRouges) {
		this.nbCartonsRouges = nbCartonsRouges;
	}

	public int getNbBlessures() {
		return nbBlessures;
	}

	public void setNbBlessures(int nbBlessures) {
		this.nbBlessures = nbBlessures;
	}

	public String getNbButsEncaissesParMatch() {
		return nbButsEncaissesParMatch;
	}

	public void setNbButsEncaissesParMatch(String nbButsEncaissesParMatch) {
		this.nbButsEncaissesParMatch = nbButsEncaissesParMatch;
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

	public String getNbCartonsJaunesParMatch() {
		return nbCartonsJaunesParMatch;
	}

	public void setNbCartonsJaunesParMatch(String nbCartonsJaunesParMatch) {
		this.nbCartonsJaunesParMatch = nbCartonsJaunesParMatch;
	}

	public String getNbCartonsJaunesParMatchDom() {
		return nbCartonsJaunesParMatchDom;
	}

	public void setNbCartonsJaunesParMatchDom(String nbCartonsJaunesParMatchDom) {
		this.nbCartonsJaunesParMatchDom = nbCartonsJaunesParMatchDom;
	}

	public String getNbCartonsJaunesParMatchExt() {
		return nbCartonsJaunesParMatchExt;
	}

	public void setNbCartonsJaunesParMatchExt(String nbCartonsJaunesParMatchExt) {
		this.nbCartonsJaunesParMatchExt = nbCartonsJaunesParMatchExt;
	}

	public String getNbCartonsJaunesParMatchFinale() {
		return nbCartonsJaunesParMatchFinale;
	}

	public void setNbCartonsJaunesParMatchFinale(
			String nbCartonsJaunesParMatchFinale) {
		this.nbCartonsJaunesParMatchFinale = nbCartonsJaunesParMatchFinale;
	}

	public String getNbCartonsRougesParMatch() {
		return nbCartonsRougesParMatch;
	}

	public void setNbCartonsRougesParMatch(String nbCartonsRougesParMatch) {
		this.nbCartonsRougesParMatch = nbCartonsRougesParMatch;
	}

	public String getNbCartonsRougesParMatchDom() {
		return nbCartonsRougesParMatchDom;
	}

	public void setNbCartonsRougesParMatchDom(String nbCartonsRougesParMatchDom) {
		this.nbCartonsRougesParMatchDom = nbCartonsRougesParMatchDom;
	}

	public String getNbCartonsRougesParMatchExt() {
		return nbCartonsRougesParMatchExt;
	}

	public void setNbCartonsRougesParMatchExt(String nbCartonsRougesParMatchExt) {
		this.nbCartonsRougesParMatchExt = nbCartonsRougesParMatchExt;
	}

	public String getNbCartonsRougesParMatchFinale() {
		return nbCartonsRougesParMatchFinale;
	}

	public void setNbCartonsRougesParMatchFinale(
			String nbCartonsRougesParMatchFinale) {
		this.nbCartonsRougesParMatchFinale = nbCartonsRougesParMatchFinale;
	}

	public String getNbBlessuresParMatch() {
		return nbBlessuresParMatch;
	}

	public void setNbBlessuresParMatch(String nbBlessuresParMatch) {
		this.nbBlessuresParMatch = nbBlessuresParMatch;
	}

	public String getNbBlessuresParMatchDom() {
		return nbBlessuresParMatchDom;
	}

	public void setNbBlessuresParMatchDom(String nbBlessuresParMatchDom) {
		this.nbBlessuresParMatchDom = nbBlessuresParMatchDom;
	}

	public String getNbBlessuresParMatchExt() {
		return nbBlessuresParMatchExt;
	}

	public void setNbBlessuresParMatchExt(String nbBlessuresParMatchExt) {
		this.nbBlessuresParMatchExt = nbBlessuresParMatchExt;
	}

	public String getNbBlessuresParMatchFinale() {
		return nbBlessuresParMatchFinale;
	}

	public void setNbBlessuresParMatchFinale(String nbBlessuresParMatchFinale) {
		this.nbBlessuresParMatchFinale = nbBlessuresParMatchFinale;
	}

	public String getNbBlessuresInfligeesParMatch() {
		return nbBlessuresInfligeesParMatch;
	}

	public void setNbBlessuresInfligeesParMatch(
			String nbBlessuresInfligeesParMatch) {
		this.nbBlessuresInfligeesParMatch = nbBlessuresInfligeesParMatch;
	}

	public String getNbBlessuresInfligeesParMatchDom() {
		return nbBlessuresInfligeesParMatchDom;
	}

	public void setNbBlessuresInfligeesParMatchDom(
			String nbBlessuresInfligeesParMatchDom) {
		this.nbBlessuresInfligeesParMatchDom = nbBlessuresInfligeesParMatchDom;
	}

	public String getNbBlessuresInfligeesParMatchExt() {
		return nbBlessuresInfligeesParMatchExt;
	}

	public void setNbBlessuresInfligeesParMatchExt(
			String nbBlessuresInfligeesParMatchExt) {
		this.nbBlessuresInfligeesParMatchExt = nbBlessuresInfligeesParMatchExt;
	}

	public String getNbBlessuresInfligeesParMatchFinale() {
		return nbBlessuresInfligeesParMatchFinale;
	}

	public void setNbBlessuresInfligeesParMatchFinale(
			String nbBlessuresInfligeesParMatchFinale) {
		this.nbBlessuresInfligeesParMatchFinale = nbBlessuresInfligeesParMatchFinale;
	}

	public int getNbBlessuresInfligees() {
		return nbBlessuresInfligees;
	}

	public void setNbBlessuresInfligees(int nbBlessuresInfligees) {
		this.nbBlessuresInfligees = nbBlessuresInfligees;
	}

	public String getNbButsMarquesParMatch() {
		return nbButsMarquesParMatch;
	}

	public void setNbButsMarquesParMatch(String nbButsMarquesParMatch) {
		this.nbButsMarquesParMatch = nbButsMarquesParMatch;
	}

	public String getNbButsMarquesParMatchDom() {
		return nbButsMarquesParMatchDom;
	}

	public void setNbButsMarquesParMatchDom(String nbButsMarquesParMatchDom) {
		this.nbButsMarquesParMatchDom = nbButsMarquesParMatchDom;
	}

	public String getNbButsMarquesParMatchExt() {
		return nbButsMarquesParMatchExt;
	}

	public void setNbButsMarquesParMatchExt(String nbButsMarquesParMatchExt) {
		this.nbButsMarquesParMatchExt = nbButsMarquesParMatchExt;
	}

	public String getNbButsMarquesParMatchFinale() {
		return nbButsMarquesParMatchFinale;
	}

	public void setNbButsMarquesParMatchFinale(
			String nbButsMarquesParMatchFinale) {
		this.nbButsMarquesParMatchFinale = nbButsMarquesParMatchFinale;
	}

	public String getNbButsEncaissesParMatchDom() {
		return nbButsEncaissesParMatchDom;
	}

	public void setNbButsEncaissesParMatchDom(String nbButsEncaissesParMatchDom) {
		this.nbButsEncaissesParMatchDom = nbButsEncaissesParMatchDom;
	}

	public String getNbButsEncaissesParMatchExt() {
		return nbButsEncaissesParMatchExt;
	}

	public void setNbButsEncaissesParMatchExt(String nbButsEncaissesParMatchExt) {
		this.nbButsEncaissesParMatchExt = nbButsEncaissesParMatchExt;
	}

	public String getNbButsEncaissesParMatchFinale() {
		return nbButsEncaissesParMatchFinale;
	}

	public void setNbButsEncaissesParMatchFinale(
			String nbButsEncaissesParMatchFinale) {
		this.nbButsEncaissesParMatchFinale = nbButsEncaissesParMatchFinale;
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

	public String getPourcentageVictoire() {
		return pourcentageVictoire;
	}

	public void setPourcentageVictoire(String pourcentageVictoire) {
		this.pourcentageVictoire = pourcentageVictoire;
	}

	public String getPourcentageVictoireDom() {
		return pourcentageVictoireDom;
	}

	public void setPourcentageVictoireDom(String pourcentageVictoireDom) {
		this.pourcentageVictoireDom = pourcentageVictoireDom;
	}

	public String getPourcentageVictoireExt() {
		return pourcentageVictoireExt;
	}

	public void setPourcentageVictoireExt(String pourcentageVictoireExt) {
		this.pourcentageVictoireExt = pourcentageVictoireExt;
	}

	public String getPourcentageVictoireFinale() {
		return pourcentageVictoireFinale;
	}

	public void setPourcentageVictoireFinale(String pourcentageVictoireFinale) {
		this.pourcentageVictoireFinale = pourcentageVictoireFinale;
	}

	public String getPourcentageDefaite() {
		return pourcentageDefaite;
	}

	public void setPourcentageDefaite(String pourcentageDefaite) {
		this.pourcentageDefaite = pourcentageDefaite;
	}

	public String getPourcentageDefaiteDom() {
		return pourcentageDefaiteDom;
	}

	public void setPourcentageDefaiteDom(String pourcentageDefaiteDom) {
		this.pourcentageDefaiteDom = pourcentageDefaiteDom;
	}

	public String getPourcentageDefaiteExt() {
		return pourcentageDefaiteExt;
	}

	public void setPourcentageDefaiteExt(String pourcentageDefaiteExt) {
		this.pourcentageDefaiteExt = pourcentageDefaiteExt;
	}

	public String getPourcentageDefaiteFinale() {
		return pourcentageDefaiteFinale;
	}

	public void setPourcentageDefaiteFinale(String pourcentageDefaiteFinale) {
		this.pourcentageDefaiteFinale = pourcentageDefaiteFinale;
	}

	public String getPourcentageNul() {
		return pourcentageNul;
	}

	public void setPourcentageNul(String pourcentageNul) {
		this.pourcentageNul = pourcentageNul;
	}

	public String getPourcentageNulDom() {
		return pourcentageNulDom;
	}

	public void setPourcentageNulDom(String pourcentageNulDom) {
		this.pourcentageNulDom = pourcentageNulDom;
	}

	public String getPourcentageNulExt() {
		return pourcentageNulExt;
	}

	public void setPourcentageNulExt(String pourcentageNulExt) {
		this.pourcentageNulExt = pourcentageNulExt;
	}

}
