package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import models.Equipe;
import models.Joueur;
import models.Poste;
import play.mvc.Controller;

import comparators.JoueurComparator;

public class Equipes extends Controller {

	public static void index() {
		List<Equipe> equipes = Equipe.findAll();
		render(equipes);
	}

	public static void info(Long id) {
		boolean info = true;
		List<Equipe> equipes = Equipe.findAll();
		Equipe equipe = Equipe.findById(id);
		List<Joueur> gardiens = new ArrayList<Joueur>();
		List<Joueur> defenseurs = new ArrayList<Joueur>();
		List<Joueur> milieux = new ArrayList<Joueur>();
		List<Joueur> attaquants = new ArrayList<Joueur>();

		Poste gardien = Poste.findById(1l);
		Poste defenseur = Poste.findById(2l);
		Poste milieu = Poste.findById(3l);
		Poste attaquant = Poste.findById(4l);

		List<Joueur> joueurs = equipe.joueurs;
		Collections.sort(joueurs, new JoueurComparator());
		for (Joueur joueur : joueurs) {
			if (joueur.poste.equals(gardien)) {
				gardiens.add(joueur);
			} else if (joueur.poste.equals(defenseur)) {
				defenseurs.add(joueur);
			} else if (joueur.poste.equals(milieu)) {
				milieux.add(joueur);
			} else if (joueur.poste.equals(attaquant)) {
				attaquants.add(joueur);
			}
		}
		render(info, equipes, gardiens, defenseurs, milieux, attaquants);
	}

	public static void nouveau(Long filtre, String selection) {
		List<Joueur> gardiens = new ArrayList<Joueur>();
		List<Joueur> defenseurs = new ArrayList<Joueur>();
		List<Joueur> milieux = new ArrayList<Joueur>();
		List<Joueur> attaquants = new ArrayList<Joueur>();
		if (selection != null && !selection.isEmpty()) {
			if (selection.startsWith(",")) {
				selection = selection.substring(1);
			}
			Poste gardien = Poste.findById(1l);
			Poste defenseur = Poste.findById(2l);
			Poste milieu = Poste.findById(3l);
			Poste attaquant = Poste.findById(4l);
			List<String> choixString = Arrays.asList(selection.split(","));
			Set<Long> choix = new HashSet<Long>();
			for (String id : choixString) {
				choix.add(Long.valueOf(id));
			}
			List<Joueur> joueurs = new ArrayList<Joueur>();
			for (Long id : choix) {
				Joueur joueur = Joueur.findById(id);
				joueurs.add(joueur);
			}
			Collections.sort(joueurs, new JoueurComparator());
			for (Joueur joueur : joueurs) {
				if (joueur.poste.equals(gardien)) {
					gardiens.add(joueur);
				} else if (joueur.poste.equals(defenseur)) {
					defenseurs.add(joueur);
				} else if (joueur.poste.equals(milieu)) {
					milieux.add(joueur);
				} else if (joueur.poste.equals(attaquant)) {
					attaquants.add(joueur);
				}
			}
		}
		List<Poste> postes = Poste.findAll();
		List<Joueur> joueurs = new ArrayList<Joueur>();
		if (filtre != null && filtre != 0l) {
			joueurs = Joueur.find("poste.id", filtre).fetch();
		} else {
			joueurs = Joueur.findAll();
		}
		render(joueurs, postes, filtre, selection, gardiens, defenseurs, milieux, attaquants);
	}

	public static void add() {
		render();
	}

}
