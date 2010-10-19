package pes.domaine;

import java.io.Serializable;

public enum ENiveauMatch implements Serializable {
	POULE1("POULE1", "Match de poule - Journée 1"), //
	POULE2("POULE2", "Match de poule - Journée 2"), //
	POULE3("POULE3", "Match de poule - Journée 3"), //
	POULE4("POULE4", "Match de poule - Journée 4"), //
	POULE5("POULE5", "Match de poule - Journée 5"), //
	POULE6("POULE6", "Match de poule - Journée 6"), //
	SEIZIEME("SEIZIEME", "16ème de finale"), //
	HUITIEME("HUITIEME", "8ème de finale"), //
	QUART("QUART", "Quart de finale"), //
	DEMI_ALLER("DEMI_ALLER", "Demi-finale aller"), //
	DEMI_RETOUR("DEMI_RETOUR", "Demi-finale retour"), //
	FINALE7("FINALE7", "Finale 7ème place"), //
	FINALE5("FINALE5", "Finale 5ème place"), //
	FINALE3("FINALE3", "Finale 3ème place"), //
	FINALE1("FINALE1", "Grande Finale");

	private String code;
	private String libelle;

	private ENiveauMatch(String code, String libelle) {
		this.code = code;
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public String getCode() {
		return code;
	}

	public static ENiveauMatch get(String code) {
		for (ENiveauMatch niveau : values()) {
			if (niveau.getCode().equals(code)) {
				return niveau;
			}
		}
		return null;
	}
}
