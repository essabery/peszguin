package pes.dao.impl;

import java.util.List;

import pes.dao.IEvenementDAO;
import pes.domaine.ETypeEvenement;
import pes.domaine.Evenement;
import pes.domaine.Match;
import pes.domaine.Tournoi;

public class EvenementDAO implements IEvenementDAO {

	@Override
	public void deleteByMatch(Match match) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Evenement> find(Tournoi tournoi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evenement> find(Tournoi tournoi, ETypeEvenement type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Evenement evenement) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Evenement evenement) {
		// TODO Auto-generated method stub

	}

}
