package pes.domaine;

import java.io.Serializable;

public enum EStructureTournoi implements Serializable {
	STANDARD("STANDARD", "Standard (8 équipes)"), //
	COUPEDUMONDE("COUPEDUMONDE", "Coupe du monde (32 équipes)");

	private String code;
	private String libelle;

	private EStructureTournoi(String code, String libelle) {
		this.code = code;
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public String getCode() {
		return code;
	}

	public static EStructureTournoi get(String code) {
		for (EStructureTournoi structure : values()) {
			if (structure.getCode().equals(code)) {
				return structure;
			}
		}
		return null;
	}
}
