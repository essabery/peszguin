package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Joueur extends Model {

	public String prenom;
	public String nom;

	@ManyToOne
	public Poste poste;

	public Joueur(String prenom, String nom, Poste poste) {
		this.prenom = prenom;
		this.nom = nom;
		this.poste = poste;
	}

}
