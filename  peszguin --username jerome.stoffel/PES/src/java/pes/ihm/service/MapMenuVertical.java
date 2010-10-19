package pes.ihm.service;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pes.ihm.util.ParametresMenu;

public class MapMenuVertical {
	/*
	 * Cette map sera "attaquée" par une clé, qui correspond au code de l'item
	 * de menu concerné. On peut récupérer facilement le titre1 et le titre2
	 * qu'il faudra ensuite afficher.
	 */
	private static Map<String, ItemVertical> MapItems = new HashMap<String, ItemVertical>();

	static {

		/*
		 * initialisation de la liste des sous-menus pour lesquels on a des
		 * onglets
		 */
		List<String> listeMenus1 = Arrays.asList(ParametresMenu.getString(
				"menu.niveau1.liste").split(","));
		List<String> listeMenus2 = Arrays.asList(ParametresMenu.getString(
				"menu.niveau2.liste").split(","));
//		List<String> listeMenus3 = Arrays.asList(ParametresMenu.getString(
//				"menu.niveau3.liste").split(","));

		for (String codeItem : listeMenus1) {
			ItemVertical itemVertical1 = new ItemVertical(codeItem,
					ParametresMenu.getString("menu." + codeItem + ".label"),
					ParametresMenu.getString("menu." + codeItem + ".lien"));
			itemVertical1.setTitre1(itemVertical1.getLabel());
			itemVertical1.setDomaineCode(itemVertical1.getCode());
			itemVertical1.setTitre2("");
			MapItems.put(codeItem, itemVertical1);
		}

		for (String codeItem : listeMenus2) {
			ItemVertical itemVertical2 = new ItemVertical(codeItem,
					ParametresMenu.getString("menu." + codeItem + ".label"),
					ParametresMenu.getString("menu." + codeItem + ".lien"));
			itemVertical2.setParentCode(ParametresMenu.getString("menu."
					+ codeItem + ".parent"));
			itemVertical2.setDomaineCode(ParametresMenu.getString("menu."
					+ codeItem + ".parent"));
			itemVertical2.setTitre1(MapItems.get(itemVertical2.getParentCode())
					.getLabel());
			itemVertical2.setTitre2(itemVertical2.getLabel());
			MapItems.put(codeItem, itemVertical2);
		}

//		for (String codeItem : listeMenus3) {
//			if (!codeItem.equals("")) {
//				ItemVertical itemVertical3 = new ItemVertical(
//						codeItem,
//						ParametresMenu.getString("menu." + codeItem + ".label"),
//						ParametresMenu.getString("menu." + codeItem + ".lien"));
//				itemVertical3.setParentCode(ParametresMenu.getString("menu."
//						+ codeItem + ".parent"));
//				itemVertical3.setDomaineCode(MapItems.get(
//						itemVertical3.getParentCode()).getDomaineCode());
//				itemVertical3.setTitre1(MapItems.get(
//						itemVertical3.getParentCode()).getTitre1());
//				itemVertical3.setTitre2(MapItems.get(
//						itemVertical3.getParentCode()).getLabel()
//						+ " - " + itemVertical3.getLabel());
//				MapItems.put(codeItem, itemVertical3);
//			}
//		}
		// Ajout des titres pour la page d'accueil
		ItemVertical itemAccueil = new ItemVertical("accueil", "Accueil", "/");
		itemAccueil.setTitre1(ParametresMenu.getString("menu.accueil.titre1"));
		MapItems.put("accueil", itemAccueil);

		// Ajout d'un item par défaut pour les cas où le context de la page
		// n'est pas renseigné.
		ItemVertical itemDefault = new ItemVertical("default", "Default", "/");
		itemDefault.setTitre1("Default");
		itemDefault.setTitre2("Default");
		MapItems.put("default", itemDefault);
	}

	public static Map<String, ItemVertical> getMapItems() {
		return MapItems;
	}

	public static void setMapItems(Map<String, ItemVertical> mapItems) {
		MapItems = mapItems;
	}

	public static String getTitre1(String codeItem) {
		String titre1 = MapItems.get(codeItem).getTitre1();
		if (titre1 != null)
			return titre1;
		else
			return "!Default!";
	}

	public static String getTitre2(String codeItem) {
		String titre2 = MapItems.get(codeItem).getTitre2();
		if (titre2 != null)
			return titre2;
		else
			return "!Default!";
	}

	public static String getDomaineCode(String codeItem) {
		return MapItems.get(codeItem).getDomaineCode();
	}

}
