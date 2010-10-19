package pes.ihm.action.equipe;

import static pes.dao.util.HibernateUtil.daoFactory;

import java.util.List;

import pes.domaine.Equipe;

import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
public class Accueil extends ActionSupport {

	private int nbEquipes;

	public String execute() {
		List<Equipe> equipes = daoFactory.getEquipeDAO().findAll();
		if (equipes == null) {
			nbEquipes = 0;
		} else {
			nbEquipes = equipes.size();
		}
		return SUCCESS;
	}

	public int getNbEquipes() {
		return nbEquipes;
	}

	public void setNbEquipes(int nbEquipes) {
		this.nbEquipes = nbEquipes;
	}


}
