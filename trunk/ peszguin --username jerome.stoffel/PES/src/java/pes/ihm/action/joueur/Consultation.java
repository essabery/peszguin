package pes.ihm.action.joueur;

import static pes.dao.util.HibernateUtil.daoFactory;

import java.util.List;

import pes.domaine.Joueur;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Consultation extends ActionSupport {

	private List<Joueur> joueurs;
	private int idJoueur;
	private Joueur joueur;

	private boolean info;
	private boolean error;
	private boolean afficherStats;

	public String initialiser() {
		joueurs = daoFactory.getJoueurDAO().findAll();
		return SUCCESS;
	}

	public String afficher() {
		afficherStats = true;
		joueurs = daoFactory.getJoueurDAO().findAll();
		joueur = daoFactory.getJoueurDAO().findById(idJoueur);
		return SUCCESS;
	}

	public String supprimer() {
		joueur = daoFactory.getJoueurDAO().findById(idJoueur);
		daoFactory.getJoueurDAO().delete(joueur);
		joueurs = daoFactory.getJoueurDAO().findAll();
		info = true;
		return SUCCESS;

	}

	public boolean isAfficherStats() {
		return afficherStats;
	}

	public void setAfficherStats(boolean afficherStats) {
		this.afficherStats = afficherStats;
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

}
