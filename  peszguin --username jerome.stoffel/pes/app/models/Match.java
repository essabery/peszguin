package models;

import java.io.Serializable;
import java.util.Set;

import play.db.jpa.Model;

@SuppressWarnings("serial")
public class Match extends Model {

	public NiveauMatch niveau;

	public Equipe equipeDomicile;
	public Equipe equipeExterieur;
	public Tournoi tournoi;
	public Set<Evenement> evenements;

	public Integer tabDom;
	public Integer tabExt;

	public boolean termine;

	public Match(NiveauMatch niveau, Equipe domicile, Equipe exterieur,
			Tournoi tournoi) {
		this.niveau = niveau;
		this.equipeDomicile = domicile;
		this.equipeExterieur = exterieur;
		this.tournoi = tournoi;
		this.termine = false;
	}

}
