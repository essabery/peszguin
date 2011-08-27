package pes.dao;

import java.util.List;

import pes.domaine.Equipe;

public interface IEquipeDAO {
	public int insert(Equipe equipe);

	public void delete(Equipe equipe);

	public void updateTirage(int idEquipe, int idParticipant, String poule,
			int position);

	public void updateTournoi(int idEquipe, int idTournoi);

	public List<Equipe> findDispo();

	public List<Equipe> findAll();

	public Equipe findById(int id);
}
