package pes.domaine;

import java.io.Serializable;

public enum ENiveauMatch implements Serializable {
	POULE1("POULE1", "Match de poule - Journ�e 1"), //
	POULE2("POULE2", "Match de poule - Journ�e 2"), //
	POULE3("POULE3", "Match de poule - Journ�e 3"), //
	POULE4("POULE4", "Match de poule - Journ�e 4"), //
	POULE5("POULE5", "Match de poule - Journ�e 5"), //
	POULE6("POULE6", "Match de poule - Journ�e 6"), //
	SEIZIEME("SEIZIEME", "16�me de finale"), //
	HUITIEME("HUITIEME", "8�me de finale"), //
	QUART("QUART", "Quart de finale"), //
	DEMI_ALLER("DEMI_ALLER", "Demi-finale aller"), //
	DEMI_RETOUR("DEMI_RETOUR", "Demi-finale retour"), //
	FINALE7("FINALE7", "Finale 7�me place"), //
	FINALE5("FINALE5", "Finale 5�me place"), //
	FINALE3("FINALE3", "Finale 3�me place"), //
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
