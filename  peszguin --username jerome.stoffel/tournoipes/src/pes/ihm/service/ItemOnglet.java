package pes.ihm.service;

import pes.ihm.tag.MenuOngletsTag;
import pes.ihm.tag.MenuVerticalTag;

/**
 * Un item onglet est un item de menu qui sera affiché sous forme d'onglets en
 * haut de la page de l'application.Un item onglet est lié à un item vertical :
 * il correspond à un quatrième niveau dans la hiérarchie des menus, qu'il n'est
 * pas ergonomique d'afficher dans l'arbre de ce menu vertical.
 *
 * L'ensemble des items de menu sont définis, avec leurs propriétés, dans le
 * fichier de properties menuResources.properties.
 *
 * Les classes ListeMenuVertical et ListeOngles permet de les créer à partir de
 * ce fichier et de les utiliser dans un tag pour effectuer leur affichage sous
 * forme d'arbre ou de liste d'onglets dans la page.
 *
 * @author Aude Mulliez
 * @since 27/02/2009
 * @version 1.0
 * @see ItemDeMenu
 * @see ItemVertical
 * @see ListeMenuVertical
 * @see ListeOnglets
 * @see MapMenuVertical
 * @see MenuVerticalTag
 * @see MenuOngletsTag
 */
public class ItemOnglet extends ItemDeMenu {

	public ItemOnglet(String code, String label, String lien) {
		this.code = code;
		this.label = label;
		this.lien = lien;
	}
}
