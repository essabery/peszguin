package models;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Equipe extends Model {

	public String libelle;

	@ManyToMany
	@JoinTable(name = "joueur_equipe")
	public List<Joueur> joueurs;
	@ManyToOne
	public Participant participant;
	@ManyToOne
	public Tournoi tournoi;

	public String poule;
	public Integer position;
}
