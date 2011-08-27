package pes.ihm.action.participant;

import pes.dao.IParticipantDAO;
import pes.domaine.Participant;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Creation extends ActionSupport {

	// champs du formulaire
	private String nom;
	private String prenom;
	private String pseudo;

	private IParticipantDAO participantDAO;

	// Participant créé
	private Participant participant;

	private boolean info;
	private boolean error;

	public String validerCreation() {
		if (champsValides()) {
			participant = new Participant();
			participant.setNom(nom);
			participant.setPrenom(prenom);
			participant.setPseudo(pseudo);
			participantDAO.insert(participant);
			nom = "";
			prenom = "";
			pseudo = "";
			info = true;
			return SUCCESS;
		} else {
			return INPUT;
		}

	}

	private boolean champsValides() {
		boolean resultat = true;
		if (pseudo.equals("")) {
			addFieldError("pseudoVide", "Veuillez remplir le pseudo");
			resultat = false;
		}
		return resultat;
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

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
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

}
