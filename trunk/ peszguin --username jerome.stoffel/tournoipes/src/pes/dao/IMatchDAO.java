package pes.dao;

import java.util.List;

import pes.domaine.ENiveauMatch;
import pes.domaine.Match;
import pes.domaine.Tournoi;

public interface IMatchDAO {
	public List<Match> find(Tournoi tournoi);

	public int insert(Match match);

	public void delete(Match match);

	public List<Match> find(Tournoi tournoi, ENiveauMatch niveau);

	public void updateMatchTermine(Match match);

	public void updateMatchReset(Match match);
	public Match findById(int id);
}
