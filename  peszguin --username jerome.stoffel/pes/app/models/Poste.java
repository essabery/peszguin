package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Poste extends Model {

	public String libelle;

	public Poste(String libelle) {
		this.libelle = libelle;
	}

}
