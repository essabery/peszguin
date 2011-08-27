package pes.dao.impl;

import java.util.List;

import pes.dao.IMatchDAO;
import pes.domaine.ENiveauMatch;
import pes.domaine.Match;
import pes.domaine.Tournoi;

public class MatchDAO implements IMatchDAO {

	@Override
	public List<Match> find(Tournoi tournoi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Match match) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Match match) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Match> find(Tournoi tournoi, ENiveauMatch niveau) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateMatchTermine(Match match) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateMatchReset(Match match) {
		// TODO Auto-generated method stub

	}

	@Override
	public Match findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
