package pes.ihm.wrapper;

import org.displaytag.decorator.TableDecorator;

import pes.dao.IParticipantDAO;
import pes.dao.ITournoiDAO;
import pes.dao.impl.ParticipantDAO;
import pes.dao.impl.TournoiDAO;
import pes.domaine.Participant;
import pes.domaine.Tournoi;

import com.opensymphony.xwork2.ActionContext;

public class TournoiWrapper extends TableDecorator {

	public String getConsulter() {
		Tournoi tournoi = (Tournoi) getCurrentRowObject();
		return "<a href='/PES/tournoi/afficherTournoi.action?idTournoi="
				+ tournoi.getId()
				+ "'><img src='/PES/images/consulter.png' style='border: none;'"
				+ " title='Afficher le tournoi'></img></a>";
	}

	public int getClassementParticipant() {
		IParticipantDAO participantDAO = new ParticipantDAO();
		Participant participant = participantDAO
				.findById((Integer) ActionContext.getContext().getSession()
						.get("idParticipant"));
		Tournoi tournoi = (Tournoi) getCurrentRowObject();

		return participant.getClassement(tournoi);
	}

	public String getLibelleLien() {
		Tournoi tournoi = (Tournoi) getCurrentRowObject();
		return "<a href='/PES/tournoi/afficherTournoi.action?idTournoi="
				+ tournoi.getId() + "'>" + tournoi.getLibelle() + "</a>";
	}

	public String getDateLien() {
		Tournoi tournoi = (Tournoi) getCurrentRowObject();
		return "<a href='/PES/tournoi/afficherTournoi.action?idTournoi="
				+ tournoi.getId() + "'>" + tournoi.getDate() + "</a>";
	}

}
