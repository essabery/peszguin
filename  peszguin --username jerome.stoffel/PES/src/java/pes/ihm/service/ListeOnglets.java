package pes.ihm.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pes.ihm.util.ParametresMenu;


public class ListeOnglets {
	private static Map<String,List<ItemOnglet>> mapOnglets = new HashMap<String,List<ItemOnglet>>();

	static		{

		/*initialisation de la liste des sous-menus pour lesquels on a des onglets*/
		List<String> listeSousMenus = Arrays.asList(ParametresMenu.getString("onglet.sousMenus.liste").split(","));

		/*Création pour chacun de ces sous-menus de la liste des onglets associée*/
		List<ItemOnglet> ongletsDuMenu = new ArrayList<ItemOnglet>();

		for(String sousMenu : listeSousMenus){
			List<String> ongletsDuMenuCode = Arrays.asList(ParametresMenu.getString("onglet." + sousMenu + ".liste").split(","));
			ongletsDuMenu = new ArrayList<ItemOnglet>();
			for (String ongletCode : ongletsDuMenuCode) {
				ongletsDuMenu.add(new ItemOnglet(ongletCode,
						ParametresMenu.getString("onglet." + sousMenu + "." + ongletCode + ".label"),
						ParametresMenu.getString("onglet." + sousMenu + "." + ongletCode + ".lien")));
			}
			mapOnglets.put(sousMenu,ongletsDuMenu);
		}

		//Ajout d'un item par défaut pour les cas où le contexte de la page
		//n'est pas renseigné.
		ongletsDuMenu = new ArrayList<ItemOnglet>();
		ongletsDuMenu.add(new ItemOnglet("default","Default","/"));
		mapOnglets.put("default",ongletsDuMenu);
	}

	public static Map<String,List<ItemOnglet>>  getListeOnglets() {
		return mapOnglets;
	}

	public static void setListeOnglets(Map<String,List<ItemOnglet>>  listeOnglets) {
		ListeOnglets.mapOnglets = listeOnglets;
	}
}
