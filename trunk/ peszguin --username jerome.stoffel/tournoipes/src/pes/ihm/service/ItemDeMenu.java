package pes.ihm.service;

import pes.ihm.tag.MenuOngletsTag;
import pes.ihm.tag.MenuVerticalTag;

/**
 * Un item de menu est un �l�ment du menu qui correspond � une fonctionnalit�
 * unitaire de l'application.
 *
 * On trouvera en fait deux types d'items de menus : les items plac�s de fa�on
 * verticale � gauche de la page, et les items sous forme d'onglets, et contenus
 * eux-m�me dans des items de menu verticaux.
 *
 * L'ensemble de ces items de menu sont d�finis, avec leurs propri�t�s, dans le
 * fichier de properties menuResources.properties.
 *
 * Les classes ListeMenuVertical et ListeOngles permet de les cr�er � partir de
 * ce fichier et de les utiliser dans un tag pour effectuer leur affichage sous
 * forme d'arbre ou de liste d'onglets dans la page.
 *
 * @author Aude Mulliez
 * @since 27/02/2009
 * @version 1.0
 * @see ItemOnglet
 * @see ItemVertical
 * @see ListeMenuVertical
 * @see ListeOnglets
 * @see MapMenuVertical
 * @see MenuVerticalTag
 * @see MenuOngletsTag
 */
public class ItemDeMenu {
	/**
	 * code de l'item de menu
	 */
	protected String code;

	/**
	 * libell� du code de menu (affich� dans l'IHM)
	 */
	protected String label;

	/**
	 * lien HTML vers lequel pointe l'item de menu
	 */
	protected String lien;

	public ItemDeMenu() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

}
