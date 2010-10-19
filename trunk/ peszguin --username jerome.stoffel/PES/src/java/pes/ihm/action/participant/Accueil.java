package pes.ihm.action.participant;

import static pes.dao.util.HibernateUtil.daoFactory;

import java.util.List;

import pes.domaine.Participant;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Accueil extends ActionSupport {

	private int nbParticipants;

	public String execute() {
		List<Participant> participants = daoFactory.getParticipantDAO()
				.findAll();
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
