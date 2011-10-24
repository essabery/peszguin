package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Participant extends Model {

	public String prenom;
	public String nom;
	public String pseudo;

	public Participant(String prenom, String nom, String pseudo) {
		this.prenom = prenom;
		this.nom = nom;
		this.pseudo = pseudo;
	}

}
