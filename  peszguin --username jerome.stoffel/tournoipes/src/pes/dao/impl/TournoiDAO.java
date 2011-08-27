package pes.dao.impl;

import java.util.List;

import pes.dao.ITournoiDAO;
import pes.domaine.EStatutTournoi;
import pes.domaine.Participant;
import pes.domaine.Tournoi;

public class TournoiDAO implements ITournoiDAO {

	@Override
	public void updateStatut(int idTournoi, EStatutTournoi statut) {
		// TODO Auto-generated method stub

	}

	@Override
	public int insert(Tournoi tournoi) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Tournoi> findByStatut(EStatutTournoi statut) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tournoi> findByStatut(EStatutTournoi statut,
			Participant participant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tournoi> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tournoi findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
