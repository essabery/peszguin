package pes.ihm.wrapper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import org.displaytag.decorator.TableDecorator;

import pes.dao.IParticipantDAO;
import pes.dao.ITournoiDAO;
import pes.dao.impl.ParticipantDAO;
import pes.dao.impl.TournoiDAO;
import pes.domaine.EStatutTournoi;
import pes.domaine.ETypeEvenement;
import pes.domaine.Equipe;
import pes.domaine.Joueur;
import pes.domaine.Participant;
import pes.domaine.Tournoi;

import com.opensymphony.xwork2.ActionContext;

public class JoueurWrapper extends TableDecorator {

	public String getAfficher() {
		Joueur joueur = (Joueur) getCurrentRowObject();
		return "<a href='/PES/joueur/afficherJoueur.action?idJoueur="
				+ joueur.getId()
				+ "'><img src='/PES/images/consulter.png' style='border: none;'"
				+ " title='Afficher le joueur'></img></a>";
	}

	public String getSupprimer() {
		Joueur joueur = (Joueur) getCurrentRowObject();
		return "<a href='/PES/joueur/suppression.action?idJoueur="
				+ joueur.getId()
				+ "'><img src='/PES/images/supprimer.png' style='border: none;'"
				+ " title='Supprimer le joueur'></img></a>";
	}

	public int getButsTournoi() {
		ITournoiDAO tournoiDAO = new TournoiDAO();
		Tournoi tournoi = tournoiDAO.findById((Integer) ActionContext
				.getContext().getSession().get("idTournoi"));
		Joueur joueur = (Joueur) getCurrentRowObject();

		int nbButs = joueur.getEvenements(tournoi, ETypeEvenement.BUT).size();

		return nbButs;
	}

	public int getRougesTournoi() {
		ITournoiDAO tournoiDAO = new TournoiDAO();
		Tournoi tournoi = tournoiDAO.findById((Integer) ActionContext
				.getContext().getSession().get("idTournoi"));
		Joueur joueur = (Joueur) getCurrentRowObject();

		int nbBlessures = joueur.getEvenements(tournoi,
				ETypeEvenement.CARTON_ROUGE).size();

		return nbBlessures;
	}

	public int getJaunesTournoi() {
		ITournoiDAO tournoiDAO = new TournoiDAO();
		Tournoi tournoi = tournoiDAO.findById((Integer) ActionContext
				.getContext().getSession().get("idTournoi"));
		Joueur joueur = (Joueur) getCurrentRowObject();

		int nbBlessures = joueur.getEvenements(tournoi,
				ETypeEvenement.CARTON_JAUNE).size();

		return nbBlessures;
	}

	public int getBlessuresTournoi() {
		ITournoiDAO tournoiDAO = new TournoiDAO();
		Tournoi tournoi = tournoiDAO.findById((Integer) ActionContext
				.getContext().getSession().get("idTournoi"));
		Joueur joueur = (Joueur) getCurrentRowObject();

		int nbBlessures = joueur.getEvenements(tournoi,
				ETypeEvenement.XBLESSURE).size();

		return nbBlessures;
	}

	public String getParticipantTournoi() {
		ITournoiDAO tournoiDAO = new TournoiDAO();
		Tournoi tournoi = tournoiDAO.findById((Integer) ActionContext
				.getContext().getSession().get("idTournoi"));
		Joueur joueur = (Joueur) getCurrentRowObject();
		if (joueur.getId().equals(-1)) {
			// Cas du CSC
			return "";
		}
		return joueur.getEquipe(tournoi).getParticipant().getPrenom();
	}

	public int getButsParticipant() {
		ITournoiDAO tournoiDAO = new TournoiDAO();
		IParticipantDAO participantDAO = new ParticipantDAO();
		Participant participant = participantDAO
				.findById((Integer) ActionContext.getContext().getSession()
						.get("idParticipant"));
		Joueur joueur = (Joueur) getCurrentRowObject();

		List<Tournoi> tournois = tournoiDAO.findByStatut(
				EStatutTournoi.TERMINE, participant);
		int nbButs = 0;
		for (Tournoi tournoi : tournois) {
			Equipe equipe = participant.getEquipe(tournoi);
			for (Joueur j : equipe.getJoueurs()) {
				if (j.getId().equals(joueur.getId())) {
					if (joueur.getEvenements(tournoi, ETypeEvenement.BUT)
							.size() > 0) {
						nbButs = nbButs
								+ joueur.getEvenements(tournoi,
										ETypeEvenement.BUT).size();
					}
				}
			}
		}
		return nbButs;
	}

	public String getButsParTournoi() {
		Double resultat = getButsParticipant() * 1.0
				/ getNbTournoisParticipant();
		NumberFormat formatter = new DecimalFormat("#0.00");
		return formatter.format(resultat);
	}

	public int getNbTournoisParticipant() {
		ITournoiDAO tournoiDAO = new TournoiDAO();
		IParticipantDAO participantDAO = new ParticipantDAO();
		Participant participant = participantDAO
				.findById((Integer) ActionContext.getContext().getSession()
						.get("idParticipant"));
		Joueur joueur = (Joueur) getCurrentRowObject();

		List<Tournoi> tournois = tournoiDAO.findByStatut(
				EStatutTournoi.TERMINE, participant);
		int nbTournois = 0;
		for (Tournoi tournoi : tournois) {
			Equipe equipe = participant.getEquipe(tournoi);
			for (Joueur j : equipe.getJoueurs()) {
				if (j.getId().equals(joueur.getId())) {
					nbTournois++;
				}
			}
		}
		return nbTournois;
	}

}
