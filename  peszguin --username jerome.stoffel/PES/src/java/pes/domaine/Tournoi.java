package pes.domaine;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@SuppressWarnings("serial")
public class Tournoi implements Serializable {
	private Integer id;
	private Date date;
	private String libelle;
	private EStructureTournoi structure;
	private EStatutTournoi statut;
	private Set<Equipe> equipes;
	private Set<Participant> participants;
	private Set<Match> matchs;

	public Tournoi() {

	}

	public Tournoi(Date date, String libelle) {
		this.date = date;
		this.libelle = libelle;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EStructureTournoi getStructure() {
		return structure;
	}

	public void setStructure(EStructureTournoi structure) {
		this.structure = structure;
	}

	public EStatutTournoi getStatut() {
		return statut;
	}

	public void setStatut(EStatutTournoi statut) {
		this.statut = statut;
	}

	public Set<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(Set<Equipe> equipes) {
		this.equipes = equipes;
	}

	public Set<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}

	public Equipe findEquipe(int idEquipe) {
		for (Equipe equipe : equipes) {
			if (equipe.getId() == idEquipe) {
				return equipe;
			}
		}
		return null;
	}

	public List<Match> findMatchs(Equipe e) {
		if (matchs == null) {
			return null;
		}
		List<Match> result = new ArrayList<Match>();
		for (Match match : matchs) {
			if (match.getEquipeDomicile().getId().equals(e.getId())) {
				result.add(match);
			} else if (match.getEquipeExterieur().getId().equals(e.getId())) {
				result.add(match);
			}
		}
		return result.size() > 0 ? result : null;
	}

	public List<Match> findMatchsPoule(Equipe e) {
		if (matchs == null) {
			return null;
		}
		List<Match> result = new ArrayList<Match>();
		for (Match match : matchs) {
			if (match.isPoule()) {
				if (match.getEquipeDomicile().getId().equals(e.getId())) {
					result.add(match);
				} else if (match.getEquipeExterieur().getId().equals(e.getId())) {
					result.add(match);
				}
			}
		}
		return result.size() > 0 ? result : null;
	}

	public Set<Match> getMatchs() {
		return matchs;
	}

	public void setMatchs(Set<Match> matchs) {
		this.matchs = matchs;
	}

	public String getDateEtLibelle() {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String lib = "";
		if (libelle != null) {
			lib = " - " + libelle;
		}
		return format.format(date) + lib;
	}
	
	public String getDateFormatee() {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(date);
	}

}
