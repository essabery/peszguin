package models;

import java.io.Serializable;

import play.db.jpa.Model;

public class TypeEvenement extends Model {

	public String libelle;

	public TypeEvenement(String libelle) {
		this.libelle = libelle;
	}
}
