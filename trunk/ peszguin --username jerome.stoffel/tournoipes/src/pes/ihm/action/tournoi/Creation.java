package pes.ihm.action.tournoi;

import java.util.Date;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.struts2.interceptor.SessionAware;

import pes.dao.IEquipeDAO;
import pes.dao.IParticipantDAO;
import pes.dao.ITournoiDAO;
import pes.domaine.EStatutTournoi;
import pes.domaine.EStructureTournoi;
import pes.domaine.Equipe;
import pes.domaine.Participant;
import pes.domaine.Tournoi;
import pes.service.comparator.EquipeComparator;
import pes.service.comparator.ParticipantComparator;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Creation extends ActionSupport implements SessionAware {

	// champs du formulaire
	private String libelle;
	private Date date;

	private SortedSet<Equipe> equipesDispos;
	private SortedSet<Equipe> equipesChoisies;
	private SortedSet<Integer> idEquipesDispos;
	private SortedSet<Integer> idEquipesChoisies;

	private SortedSet<Participant> participantsDispos;
	private SortedSet<Participant> participantsChoisis;
	private SortedSet<Integer> idParticipantsDispos;
	private SortedSet<Integer> idParticipantsChoisis;

	private IEquipeDAO equipeDAO;
	private IParticipantDAO participantDAO;
	private ITournoiDAO tournoiDAO;

	// Tournoi créé
	private Tournoi tournoi;

	private boolean info;
	private boolean error;

	@SuppressWarnings("unchecked")
	private Map session;
	private final String cleEquipesChoisies = "equipesChoisies";
	private final String cleParticipantsChoisis = "participantsChoisis";

	public String initialiser() {
		nettoyerSession();
		remplirListes();
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String validerCreation() {
		if (champsValides()) {
			tournoi = new Tournoi();
			tournoi.setLibelle(libelle);
			tournoi.setDate(date);
			tournoi.setStructure(EStructureTournoi.STANDARD);
			tournoi.setStatut(EStatutTournoi.TIRAGE);

			participantsChoisis = (SortedSet<Participant>) session
					.get(cleParticipantsChoisis);
			tournoi.setParticipants(participantsChoisis);

			int id = tournoiDAO.insert(tournoi);

			equipesChoisies = (SortedSet<Equipe>) session
					.get(cleEquipesChoisies);
			for (Equipe equipe : equipesChoisies) {
				equipeDAO.updateTournoi(equipe.getId(), id);
			}
			info = true;
			nettoyerSession();
			remplirListes();
			return SUCCESS;
		} else {
			remplirListes();
			return INPUT;
		}
	}

	@SuppressWarnings("unchecked")
	public String ajouterEquipes() {
		equipesChoisies = (SortedSet<Equipe>) session.get(cleEquipesChoisies);
		if (equipesChoisies == null) {
			equipesChoisies = new TreeSet<Equipe>(new EquipeComparator());
		}
		for (Integer idEquipeDispo : idEquipesDispos) {
			equipesChoisies.add(equipeDAO.findById(idEquipeDispo));
		}
		session.put(cleEquipesChoisies, equipesChoisies);
		remplirListes();
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String supprimerEquipes() {
		equipesChoisies = (SortedSet<Equipe>) session.get(cleEquipesChoisies);
		if (equipesChoisies == null) {
			equipesChoisies = new TreeSet<Equipe>(new EquipeComparator());
		}
		for (Integer idEquipeChoisie : idEquipesChoisies) {
			equipesChoisies.remove(equipeDAO.findById(idEquipeChoisie));
		}
		session.put(cleEquipesChoisies, equipesChoisies);
		remplirListes();
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String ajouterParticipants() {
		participantsChoisis = (SortedSet<Participant>) session
				.get(cleParticipantsChoisis);
		if (participantsChoisis == null) {
			participantsChoisis = new TreeSet<Participant>(
					new ParticipantComparator());
		}
		for (Integer idParticipantDispo : idParticipantsDispos) {
			participantsChoisis
					.add(participantDAO.findById(idParticipantDispo));
		}
		session.put(cleParticipantsChoisis, participantsChoisis);
		remplirListes();
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String supprimerParticipants() {
		participantsChoisis = (SortedSet<Participant>) session
				.get(cleParticipantsChoisis);
		if (participantsChoisis == null) {
			participantsChoisis = new TreeSet<Participant>(
					new ParticipantComparator());
		}
		for (Integer idParticipantChoisi : idParticipantsDispos) {
			participantsChoisis.remove(participantDAO
					.findById(idParticipantChoisi));
		}
		session.put(cleParticipantsChoisis, participantsChoisis);
		remplirListes();
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	private void remplirListes() {
		equipesChoisies = (SortedSet<Equipe>) session.get(cleEquipesChoisies);
		if (equipesChoisies == null) {
			equipesChoisies = new TreeSet<Equipe>(new EquipeComparator());
		}

		equipesDispos = new TreeSet(new EquipeComparator());
		equipesDispos.addAll(equipeDAO.findDispo());
		equipesDispos.removeAll(equipesChoisies);

		participantsChoisis = (SortedSet<Participant>) session
				.get(cleParticipantsChoisis);
		if (participantsChoisis == null) {
			participantsChoisis = new TreeSet<Participant>(
					new ParticipantComparator());
		}
		participantsDispos = new TreeSet(new ParticipantComparator());
		participantsDispos.addAll(participantDAO.findAll());
		participantsDispos.removeAll(participantsChoisis);

	}

	private void nettoyerSession() {
		session.remove(cleEquipesChoisies);
		session.remove(cleParticipantsChoisis);
	}

	@SuppressWarnings("unchecked")
	private boolean champsValides() {
		boolean resultat = true;
		equipesChoisies = (SortedSet<Equipe>) session.get(cleEquipesChoisies);
		if (equipesChoisies == null || equipesChoisies.size() != 8) {
			resultat = false;
			addFieldError("nbEquipes", "Il faut exactement 8 équipes");
		}
		participantsChoisis = (SortedSet<Participant>) session
				.get(cleParticipantsChoisis);
		if (participantsChoisis == null || participantsChoisis.size() != 8) {
			resultat = false;
			addFieldError("nbParticipants", "Il faut exactement 8 participants");
		}
		return resultat;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Tournoi getTournoi() {
		return tournoi;
	}

	public void setTournoi(Tournoi tournoi) {
		this.tournoi = tournoi;
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

	public SortedSet<Equipe> getEquipesDispos() {
		return equipesDispos;
	}

	public void setEquipesDispos(SortedSet<Equipe> equipesDispos) {
		this.equipesDispos = equipesDispos;
	}

	public SortedSet<Equipe> getEquipesChoisies() {
		return equipesChoisies;
	}

	public void setEquipesChoisies(SortedSet<Equipe> equipesChoisies) {
		this.equipesChoisies = equipesChoisies;
	}

	public SortedSet<Integer> getIdEquipesDispos() {
		return idEquipesDispos;
	}

	public void setIdEquipesDispos(SortedSet<Integer> idEquipesDispos) {
		this.idEquipesDispos = idEquipesDispos;
	}

	public SortedSet<Integer> getIdEquipesChoisies() {
		return idEquipesChoisies;
	}

	public void setIdEquipesChoisies(SortedSet<Integer> idEquipesChoisies) {
		this.idEquipesChoisies = idEquipesChoisies;
	}

	public SortedSet<Participant> getParticipantsDispos() {
		return participantsDispos;
	}

	public void setParticipantsDispos(SortedSet<Participant> participantsDispos) {
		this.participantsDispos = participantsDispos;
	}

	public SortedSet<Participant> getParticipantsChoisis() {
		return participantsChoisis;
	}

	public void setParticipantsChoisis(
			SortedSet<Participant> participantsChoisis) {
		this.participantsChoisis = participantsChoisis;
	}

	public SortedSet<Integer> getIdParticipantsDispos() {
		return idParticipantsDispos;
	}

	public void setIdParticipantsDispos(SortedSet<Integer> idParticipantsDispos) {
		this.idParticipantsDispos = idParticipantsDispos;
	}

	public SortedSet<Integer> getIdParticipantsChoisis() {
		return idParticipantsChoisis;
	}

	public void setIdParticipantsChoisis(
			SortedSet<Integer> idParticipantsChoisis) {
		this.idParticipantsChoisis = idParticipantsChoisis;
	}

	@SuppressWarnings("unchecked")
	public void setSession(Map session) {
		this.session = session;
	}

}
