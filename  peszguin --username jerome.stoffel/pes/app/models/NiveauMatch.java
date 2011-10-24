package models;

import java.io.Serializable;

import play.db.jpa.Model;

public class NiveauMatch extends Model {

	public String libelle;

	public NiveauMatch(String libelle) {
		this.libelle = libelle;
	}
}
