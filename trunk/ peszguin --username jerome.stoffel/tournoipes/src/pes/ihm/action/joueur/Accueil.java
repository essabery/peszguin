package pes.ihm.action.joueur;

import java.util.List;

import pes.dao.IJoueurDAO;
import pes.domaine.Joueur;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Accueil extends ActionSupport {

	private int nbJoueurs;
	
	private IJoueurDAO joueurDAO;

	public String execute() {
		List<Joueur> joueurs = joueurDAO.findAll();
		if (joueurs == null) {
			nbJoueurs = 0;
		} else {
			nbJoueurs = joueurs.size();
		}
		return SUCCESS;
	}

	public int getNbJoueurs() {
		return nbJoueurs;
	}

	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}

}
