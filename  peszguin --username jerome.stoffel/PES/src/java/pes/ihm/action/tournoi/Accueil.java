package pes.ihm.action.tournoi;
import static pes.dao.util.HibernateUtil.daoFactory;

import java.util.List;

import pes.domaine.Tournoi;

import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
public class Accueil extends ActionSupport {

	private int nbTournois;

	public String execute() {
		List<Tournoi> tournois = daoFactory.getTournoiDAO().findAll();
		if (tournois == null) {
			nbTournois = 0;
		} else {
			nbTournois = tournois.size();
		}
		return SUCCESS;
	}

	public int getNbTournois() {
		return nbTournois;
	}

	public void setNbTournois(int nbTournois) {
		this.nbTournois = nbTournois;
	}
}
