package pes.ihm.action.joueur;

import static pes.dao.util.HibernateUtil.daoFactory;
import java.util.List;
import pes.domaine.Joueur;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Accueil extends ActionSupport {

	private int nbJoueurs;

	public String execute() {
		List<Joueur> joueurs = daoFactory.getJoueurDAO().findAll();
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
