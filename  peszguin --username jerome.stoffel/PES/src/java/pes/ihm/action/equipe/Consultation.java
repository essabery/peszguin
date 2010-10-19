package pes.ihm.action.equipe;

import static pes.dao.util.HibernateUtil.daoFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import pes.domaine.EPoste;
import pes.domaine.Equipe;
import pes.domaine.Joueur;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Consultation extends ActionSupport {

	private List<Equipe> equipes;
	private int idEquipe;
	private Equipe equipe;

	private boolean afficherStats;
	private List<Joueur> gardiens;
	private List<Joueur> defenseurs;
	private List<Joueur> milieux;
	private List<Joueur> attaquants;

	public String initialiser() {
		remplirListes();
		return SUCCESS;
	}

	public String afficher() {
		afficherStats = true;
		remplirListes();
		equipe = daoFactory.getEquipeDAO().findById(idEquipe);
		gardiens = filtrer(EPoste.GARDIEN, equipe.getJoueurs());
		defenseurs = filtrer(EPoste.DEFENSEUR, equipe.getJoueurs());
		milieux = filtrer(EPoste.MILIEU, equipe.getJoueurs());
		attaquants = filtrer(EPoste.ATTAQUANT, equipe.getJoueurs());
		return SUCCESS;
	}

	public String supprimer() {
		equipe = daoFactory.getEquipeDAO().findById(idEquipe);
		daoFactory.getEquipeDAO().delete(equipe);
		remplirListes();
		return SUCCESS;
	}

	private void remplirListes() {
		equipes = daoFactory.getEquipeDAO().findAll();
		List<Equipe> equipesDispos = new ArrayList<Equipe>();
		for (Equipe equipe : equipes) {
			if (equipe.getTournoi() == null) {
				equipesDispos.add(equipe);
			}
		}
		equipes = equipesDispos;
	}

	private List<Joueur> filtrer(EPoste poste, Set<Joueur> joueurs) {
		if (joueurs == null) {
			return null;
		}
		List<Joueur> resultat = new ArrayList<Joueur>();
		for (Joueur joueur : joueurs) {
			if (joueur.getPoste() == poste) {
				resultat.add(joueur);
			}
		}
		return resultat.isEmpty() ? null : resultat;
	}

	public boolean isAfficherStats() {
		return afficherStats;
	}

	public void setAfficherStats(boolean afficherStats) {
		this.afficherStats = afficherStats;
	}

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}

	public int getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(int idEquipe) {
		this.idEquipe = idEquipe;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public List<Joueur> getGardiens() {
		return gardiens;
	}

	public void setGardiens(List<Joueur> gardiens) {
		this.gardiens = gardiens;
	}

	public List<Joueur> getDefenseurs() {
		return defenseurs;
	}

	public void setDefenseurs(List<Joueur> defenseurs) {
		this.defenseurs = defenseurs;
	}

	public List<Joueur> getMilieux() {
		return milieux;
	}

	public void setMilieux(List<Joueur> milieux) {
		this.milieux = milieux;
	}

	public List<Joueur> getAttaquants() {
		return attaquants;
	}

	public void setAttaquants(List<Joueur> attaquants) {
		this.attaquants = attaquants;
	}

}
