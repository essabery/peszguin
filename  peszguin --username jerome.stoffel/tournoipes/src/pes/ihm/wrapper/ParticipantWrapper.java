package pes.ihm.wrapper;

import java.util.List;

import org.displaytag.decorator.TableDecorator;

import pes.dao.IParticipantDAO;
import pes.dao.ITournoiDAO;
import pes.dao.impl.ParticipantDAO;
import pes.dao.impl.TournoiDAO;
import pes.domaine.EStatutTournoi;
import pes.domaine.Equipe;
import pes.domaine.Match;
import pes.domaine.Participant;
import pes.domaine.Tournoi;

import com.opensymphony.xwork2.ActionContext;

public class ParticipantWrapper extends TableDecorator {

	public String getAfficher() {
		Participant participant = (Participant) getCurrentRowObject();
		return "<a href='/PES/participant/afficherParticipant.action?idParticipant="
				+ participant.getId()
				+ "'><img src='/PES/images/consulter.png' style='border: none;'"
				+ " title='Afficher le participant'></img></a>";
	}

	public String getSupprimer() {
		Participant participant = (Participant) getCurrentRowObject();
		return "<a href='/PES/participant/suppression.action?idParticipant="
				+ participant.getId()
				+ "'><img src='/PES/images/supprimer.png' style='border: none;'"
				+ " title='Supprimer le participant'></img></a>";
	}

	public String getPrenomLien() {
		Participant participant = (Participant) getCurrentRowObject();
		return "<a href='/PES/participant/afficherParticipant.action?idParticipant="
				+ participant.getId() + "'>" + participant.getPrenom() + "</a>";
	}

	public int getVictoireVsParticipant() {
		IParticipantDAO participantDAO = new ParticipantDAO();
		ITournoiDAO tournoiDAO = new TournoiDAO();
		Participant participant = participantDAO
				.findById((Integer) ActionContext.getContext().getSession()
						.get("idParticipant"));

		Participant participantRow = (Participant) getCurrentRowObject();

		List<Tournoi> tournois = tournoiDAO.findByStatut(
				EStatutTournoi.TERMINE, participant);
		int nbVictoires = 0;

		for (Tournoi tournoi : tournois) {
			Equipe e = participant.getEquipe(tournoi);
			List<Match> matchs = tournoi.findMatchs(e);
			for (Match match : matchs) {
				if (match.getEquipeDomicile().getParticipant().getId()
						.equals(participantRow.getId())
						|| match.getEquipeExterieur().getParticipant().getId()
								.equals(participantRow.getId())) {
					if (match.getGagnant() != null
							&& match.getGagnant().getId().equals(e.getId())) {
						nbVictoires++;
					}
				}
			}
		}
		return nbVictoires;
	}

	public int getNulVsParticipant() {
		IParticipantDAO participantDAO = new ParticipantDAO();
		ITournoiDAO tournoiDAO = new TournoiDAO();
		Participant participant = participantDAO
				.findById((Integer) ActionContext.getContext().getSession()
						.get("idParticipant"));
		Participant participantRow = (Participant) getCurrentRowObject();

		List<Tournoi> tournois = tournoiDAO.findByStatut(
				EStatutTournoi.TERMINE, participant);

		int nbNuls = 0;

		for (Tournoi tournoi : tournois) {
			Equipe e = participant.getEquipe(tournoi);
			List<Match> matchs = tournoi.findMatchs(e);
			for (Match match : matchs) {
				if (match.getEquipeDomicile().getParticipant().getId()
						.equals(participantRow.getId())
						|| match.getEquipeExterieur().getParticipant().getId()
								.equals(participantRow.getId())) {
					if (match.getGagnant() == null) {
						nbNuls++;
					}
				}
			}
		}
		return nbNuls;
	}

	public int getDefaiteVsParticipant() {
		IParticipantDAO participantDAO = new ParticipantDAO();
		ITournoiDAO tournoiDAO = new TournoiDAO();
		Participant participant = participantDAO
				.findById((Integer) ActionContext.getContext().getSession()
						.get("idParticipant"));
		Participant participantRow = (Participant) getCurrentRowObject();

		List<Tournoi> tournois = tournoiDAO.findByStatut(
				EStatutTournoi.TERMINE, participant);

		int nbDefaites = 0;

		for (Tournoi tournoi : tournois) {
			Equipe e = participant.getEquipe(tournoi);
			List<Match> matchs = tournoi.findMatchs(e);
			for (Match match : matchs) {
				if (match.getEquipeDomicile().getParticipant().getId()
						.equals(participantRow.getId())
						|| match.getEquipeExterieur().getParticipant().getId()
								.equals(participantRow.getId())) {
					if (match.getPerdant() != null
							&& match.getPerdant().getId().equals(e.getId())) {
						nbDefaites++;
					}
				}
			}
		}
		return nbDefaites;
	}

	public int getNbMatchsVsParticipant() {
		IParticipantDAO participantDAO = new ParticipantDAO();
		ITournoiDAO tournoiDAO = new TournoiDAO();
		Participant participant = participantDAO
				.findById((Integer) ActionContext.getContext().getSession()
						.get("idParticipant"));

		Participant participantRow = (Participant) getCurrentRowObject();

		List<Tournoi> tournois = tournoiDAO.findByStatut(
				EStatutTournoi.TERMINE, participant);
		int nbMatchs = 0;

		for (Tournoi tournoi : tournois) {
			Equipe e = participant.getEquipe(tournoi);
			List<Match> matchs = tournoi.findMatchs(e);
			for (Match match : matchs) {
				if (match.getEquipeDomicile().getParticipant().getId()
						.equals(participantRow.getId())
						|| match.getEquipeExterieur().getParticipant().getId()
								.equals(participantRow.getId())) {
					nbMatchs++;

				}
			}
		}
		return nbMatchs;
	}

}
