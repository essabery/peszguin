package pes.dao;

import java.util.List;

import pes.domaine.EStatutTournoi;
import pes.domaine.Participant;
import pes.domaine.Tournoi;

public interface ITournoiDAO  {

	public void updateStatut(int idTournoi, EStatutTournoi statut);
	public int insert(Tournoi tournoi);
	public List<Tournoi> findByStatut(EStatutTournoi statut);
	public List<Tournoi> findByStatut(EStatutTournoi statut, Participant participant);
	public List<Tournoi> findAll();
	public Tournoi findById(int id);
}
