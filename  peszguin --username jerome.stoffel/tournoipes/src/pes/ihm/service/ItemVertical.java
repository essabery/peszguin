package pes.ihm.service;

import pes.ihm.tag.MenuOngletsTag;
import pes.ihm.tag.MenuVerticalTag;

/**
 * Un item vertical est un item de menu qui sera affich� dans le bandeau
 * vertical gauche des pages de l'application. Ils correpsond � l'un des trois
 * premiers niveaux hi�rarchiques du menu. Si un quatri�me niveau est
 * n�cessaire, il est pris en charge par une liste d'onglets attach�s � l'item
 * de menu vertical.
 *
 * L'ensemble des items de menu sont d�finis, avec leurs propri�t�s, dans le
 * fichier de properties menuResources.properties.
 *
 * Les classes ListeMenuVertical et ListeOngles permet de les cr�er � partir de
 * ce fichier et de les utiliser dans un tag pour effectuer leur affichage sous
 * forme d'arbre ou de liste d'onglets dans la page.
 *
 * @author Aude Mulliez
 * @since 27/02/2009
 * @version 1.0
 * @see ItemDeMenu
 * @see ItemOnglet
 * @see ListeMenuVertical
 * @see ListeOnglets
 * @see MapMenuVertical
 * @see MenuVerticalTag
 * @see MenuOngletsTag
 */
public class ItemVertical extends ItemDeMenu {
	/**
	 * Code de l'item de menu parent de cet item. Vaut null s'il s'agit d'un
	 * item de premier niveau.
	 */
	private String parentCode;

	/**
	 * Code du domaine m�tier d'appartenance de cet item de menu
	 */
	private String domaineCode;

	/**
	 * Niveau de l'item de menu dans la hi�rarchie (1,2 ou 3)
	 */
	private int niveau;

	/**
	 * Titre de niveau 1 � afficher dans le bandeau de titre quand on se trouve
	 * sur cet item de menu
	 */
	private String titre1;

	/**
	 * Titre de niveau 2 � afficher dans le bandeau de titre quand on se trouve
	 * sur cet item de menu
	 */
	private String titre2;

	public ItemVertical(String code, String label, String lien) {
		super();
		this.code = code;
		this.label = label;
		this.lien = lien;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getTitre1() {
		return titre1;
	}

	public void setTitre1(String titre1) {
		this.titre1 = titre1;
	}

	public String getTitre2() {
		return titre2;
	}

	public void setTitre2(String titre2) {
		this.titre2 = titre2;
	}

	public boolean hasParent() {
		if (this.parentCode == null)
			return false;
		else
			return true;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public String getDomaineCode() {
		return domaineCode;
	}

	public void setDomaineCode(String domaineCode) {
		this.domaineCode = domaineCode;
	}

}
