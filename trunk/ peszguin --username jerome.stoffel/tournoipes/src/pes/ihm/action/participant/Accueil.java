package pes.ihm.action.participant;

import java.util.List;

import pes.dao.IParticipantDAO;
import pes.domaine.Participant;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Accueil extends ActionSupport {

	private int nbParticipants;
	private IParticipantDAO participantDAO;

	public String execute() {
		List<Participant> participants = participantDAO.findAll();
		if (participants == null) {
			nbParticipants = 0;
		} else {
			nbParticipants = participants.size();
		}
		return SUCCESS;
	}

	public int getNbParticipants() {
		return nbParticipants;
	}

	public void setNbParticipants(int nbParticipants) {
		this.nbParticipants = nbParticipants;
	}
}
