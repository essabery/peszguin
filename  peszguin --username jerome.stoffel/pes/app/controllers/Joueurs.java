package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Joueur;
import models.Poste;
import play.mvc.*;

public class Joueurs extends Controller {

	public static void index(Long filtre) {
		List<Poste> postes = Poste.findAll();
		List<Joueur> joueurs = new ArrayList<Joueur>();
		if (filtre != null && filtre != 0l) {
			joueurs = Joueur.find("poste.id", filtre).fetch();
		} else {
			joueurs = Joueur.findAll();
		}
		render(joueurs, postes, filtre);
	}

	public static void nouveau() {
		List<Poste> postes = Poste.findAll();
		render(postes);
	}

	public static void add(String prenom, String nom, long idPoste) {
		if (idPoste == -1) {
			error("Il faut sélectionner un poste");
		} else {
			Poste poste = Poste.findById(idPoste);
			new Joueur(prenom, nom, poste).save();
			redirect("Joueurs.index");
		}
	}

	public static void delete(Long id) {
		Joueur.findById(id)._delete();
		redirect("Joueurs.index");
	}
}
