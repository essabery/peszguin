package controllers;

import java.util.List;

import models.Joueur;
import models.Participant;
import play.mvc.*;

public class Participants extends Controller {

	public static void index() {
		List<Participant> participants = Participant.findAll();
		render(participants);
	}

	public static void nouveau() {
		render();
	}

	public static void add(String nom, String prenom, String pseudo) {
		new Participant(nom, prenom, pseudo).save();
		redirect("Participants.index");
	}

	public static void delete(Long id) {
		Participant.findById(id)._delete();
		redirect("Participants.index");
	}

}
