package pes.ihm.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pes.ihm.util.ParametresMenu;


/**
 * Cette classe ne contient qu'un attribut et une méthode statics, qui permettent
 */

public class ListeMenuVertical {
	/* L'ordre de cette liste a une importance. En effet, elle va servir à construire
	 * le menu du bandeau de gauche à l'aide de js de la YUI library. Il faut pour cela
	 * construire chque noeud en donnant le label, le lien éventuel, et le noeud parent.
	 *
	 * Il faut donc que les noeuds parents soient créés avant les noeuds enfants.
	 */
	private static List<ItemVertical> listeItems = new ArrayList<ItemVertical>();

	static		{

		/*des listes d'items de menu*/
		List<String> listeMenus1 = Arrays.asList(ParametresMenu.getString("menu.niveau1.liste").split(","));
		List<String> listeMenus2 = Arrays.asList(ParametresMenu.getString("menu.niveau2.liste").split(","));
		//List<String> listeMenus3 = Arrays.asList(ParametresMenu.getString("menu.niveau3.liste").split(","));

		for (String item1:listeMenus1){
			ItemVertical itemVertical1 = new ItemVertical(item1, ParametresMenu.getString("menu." + item1 + ".label"),
					ParametresMenu.getString("menu." + item1 + ".lien"));
			itemVertical1.setNiveau(1);
			listeItems.add(itemVertical1);
		}

		for (String item2:listeMenus2){
			ItemVertical itemVertical2 = new ItemVertical(item2, ParametresMenu.getString("menu." + item2 + ".label"),
					ParametresMenu.getString("menu." + item2 + ".lien"));
			itemVertical2.setNiveau(2);
			itemVertical2.setParentCode(ParametresMenu.getString("menu." + item2 + ".parent"));
			listeItems.add(itemVertical2);
		}

//		for (String item3:listeMenus3){
//			ItemVertical itemVertical3 = new ItemVertical(item3, ParametresMenu.getString("menu." + item3 + ".label"),
//					ParametresMenu.getString("menu." + item3 + ".lien"));
//			itemVertical3.setNiveau(3);
//			itemVertical3.setParentCode(ParametresMenu.getString("menu." + item3 + ".parent"));
//			listeItems.add(itemVertical3);
//		}
	}

	public static List<ItemVertical> getListeItems() {
		return listeItems;
	}

	public static void setListeItems(List<ItemVertical> listeItems) {
		ListeMenuVertical.listeItems = listeItems;
	}
}
