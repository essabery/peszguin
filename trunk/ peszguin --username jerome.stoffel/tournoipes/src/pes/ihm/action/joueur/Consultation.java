package pes.ihm.action.joueur;

import java.util.List;

import pes.dao.IJoueurDAO;
import pes.domaine.Joueur;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Consultation extends ActionSupport {

	private List<Joueur> joueurs;
	private int idJoueur;
	private Joueur joueur;
	
	private IJoueurDAO joueurDAO;

	private boolean info;
	private boolean error;
	private boolean init;

	public String initialiser() {
		joueurs = joueurDAO.findAll();
		init=true;
		return SUCCESS;
	}

	public String afficher() {
		joueurs = joueurDAO.findAll();
		joueur = joueurDAO.findById(idJoueur);
		return SUCCESS;
	}

	public String supprimer() {
		joueur = joueurDAO.findById(idJoueur);
		joueurDAO.delete(joueur);
		joueurs = joueurDAO.findAll();
		info = true;
		return SUCCESS;

	}

	public List<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(List<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	public int getIdJoueur() {
		return idJoueur;
	}

	public void setIdJoueur(int idJoueur) {
		this.idJoueur = idJoueur;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public boolean isInfo() {
		return info;
	}

	public void setInfo(boolean info) {
		this.info = info;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public boolean isInit() {
		return init;
	}

	public void setInit(boolean init) {
		this.init = init;
	}

}
