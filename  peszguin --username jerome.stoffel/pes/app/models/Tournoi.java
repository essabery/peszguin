package models;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import play.db.jpa.Model;

@Entity
public class Tournoi extends Model {

	public Date date;
	public String libelle;

	@ManyToOne
	public StatutTournoi statut;

	@OneToMany(mappedBy = "tournoi")
	public Set<Equipe> equipes;
	@ManyToMany
	@JoinTable(name = "participant_tournoi")
	public List<Participant> participants;

	@Transient
	public List<Match> matchs;

	public Tournoi(Date date, String libelle) {
		this.date = date;
		this.libelle = libelle;
	}

	public Tournoi(String libelle) {
		new Tournoi(new Date(), libelle);
	}

}
