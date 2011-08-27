package pes.dao.impl;

import java.util.List;

import pes.dao.IEquipeDAO;
import pes.domaine.Equipe;

public class EquipeDAO implements IEquipeDAO {

	@Override
	public int insert(Equipe equipe) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Equipe equipe) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTirage(int idEquipe, int idParticipant, String poule,
			int position) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTournoi(int idEquipe, int idTournoi) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Equipe> findDispo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Equipe> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Equipe findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
