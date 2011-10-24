package models;

import java.io.Serializable;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class StatutTournoi extends Model {

	public String libelle;

	public StatutTournoi(String libelle) {
		this.libelle = libelle;
	}
}
