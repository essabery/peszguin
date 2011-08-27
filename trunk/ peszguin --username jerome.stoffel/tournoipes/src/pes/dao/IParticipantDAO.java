package pes.dao;

import java.util.List;

import pes.domaine.Participant;

public interface IParticipantDAO {
	public Participant findByPseudo(String pseudo);
	public int insert(Participant participant);
	public void delete(Participant participant);
	public List<Participant> findAll();
	public Participant findById(int id);
}
