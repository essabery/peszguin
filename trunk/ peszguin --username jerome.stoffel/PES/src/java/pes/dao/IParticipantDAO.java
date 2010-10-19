package pes.dao;

import pes.domaine.Participant;

public interface IParticipantDAO extends IGenericDAO<Participant, Integer> {
	public Participant findByPseudo(String pseudo);
	public int insert(Participant participant);
	public void delete(Participant participant);
}
