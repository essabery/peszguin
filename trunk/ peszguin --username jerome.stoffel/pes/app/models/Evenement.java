package models;

import java.io.Serializable;

import play.db.jpa.Model;

@SuppressWarnings("serial")
public class Evenement extends Model {

	public TypeEvenement type;
	public Match match;
	public Equipe equipe;
	public Joueur joueur;
	public Integer minute;

	public Evenement(TypeEvenement type, Match match, Equipe equipe,
			Joueur joueur, Integer minute) {
		this.type = type;
		this.match = match;
		this.equipe = equipe;
		this.joueur = joueur;
		this.minute = minute;
	}

}
