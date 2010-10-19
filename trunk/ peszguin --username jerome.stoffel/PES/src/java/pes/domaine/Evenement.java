package pes.domaine;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Evenement implements Serializable {

	private Integer id;
	private ETypeEvenement type;
	private Match match;
	private Equipe equipe;
	private Joueur joueur;
	private Integer minute;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ETypeEvenement getType() {
		return type;
	}

	public void setType(ETypeEvenement type) {
		this.type = type;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	public String getMinuteFormate() {
		if (minute == null) {
			return "";
		} else {
			return minute + "'";
		}
	}

}
