package pes.domaine;

import java.io.Serializable;

public enum ETypeEvenement implements Serializable {
	BUT("BUT", "But"), //
	CARTON_JAUNE("CARTON_JAUNE", "Carton jaune"), //
	CARTON_ROUGE("CARTON_ROUGE", "Carton rouge"), //
	XBLESSURE("XBLESSURE", "Blessure");

	private String code;
	private String libelle;

	private ETypeEvenement(String code, String libelle) {
		this.code = code;
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public String getCode() {
		return code;
	}

	public static ETypeEvenement get(String code) {
		for (ETypeEvenement type : values()) {
			if (type.getCode().equals(code)) {
				return type;
			}
		}
		return null;
	}
}
