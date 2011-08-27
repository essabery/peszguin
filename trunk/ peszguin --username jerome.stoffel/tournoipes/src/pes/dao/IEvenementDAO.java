package pes.dao;

import java.util.List;

import pes.domaine.ETypeEvenement;
import pes.domaine.Evenement;
import pes.domaine.Match;
import pes.domaine.Tournoi;

public interface IEvenementDAO  {
	public void deleteByMatch(Match match);
	public List<Evenement> find(Tournoi tournoi);
	public List<Evenement> find(Tournoi tournoi, ETypeEvenement type);
	public int insert(Evenement evenement);
	public void delete(Evenement evenement);
}
