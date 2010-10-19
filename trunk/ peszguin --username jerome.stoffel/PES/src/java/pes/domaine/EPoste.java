package pes.domaine;

import java.io.Serializable;

public enum EPoste implements Serializable {
	GARDIEN("GARDIEN", "Gardien"), //
	DEFENSEUR("DEFENSEUR", "Défenseur"), //
	MILIEU("MILIEU", "Milieu"), //
	ATTAQUANT("ATTAQUANT", "Attaquant");

	private String code;
	private String libelle;

	private EPoste(String code, String libelle) {
		this.code = code;
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public String getCode() {
		return code;
	}

	public static EPoste get(String code) {
		for (EPoste poste : values()) {
			if (poste.getCode().equals(code)) {
				return poste;
			}
		}
		return null;
	}
}
