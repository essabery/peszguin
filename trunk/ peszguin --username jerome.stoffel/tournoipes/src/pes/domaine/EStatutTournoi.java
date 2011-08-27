package pes.domaine;

import java.io.Serializable;

public enum EStatutTournoi implements Serializable {
	TIRAGE("TIRAGE", "Tirage au sort"), //
	POULES("POULES", "Phase de poules"), //
	ARBRE_FINAL("ARBRE_FINAL", "Arbre final"), //
	TERMINE("TERMINE", "Terminé");

	private String code;
	private String libelle;

	private EStatutTournoi(String code, String libelle) {
		this.code = code;
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public String getCode() {
		return code;
	}

	public static EStatutTournoi get(String code) {
		for (EStatutTournoi statut : values()) {
			if (statut.getCode().equals(code)) {
				return statut;
			}
		}
		return null;
	}
}
