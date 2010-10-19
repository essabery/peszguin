package pes.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import pes.dao.IParticipantDAO;
import pes.domaine.Participant;

public class ParticipantDAO extends GenericDAO<Participant, Integer> implements
		IParticipantDAO {

	@Override
	public Participant findByPseudo(String pseudo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Participant participant) {
		Session session = getSession();
		try {
			int id = getIdMax() + 1;

			String insert = "INSERT INTO participant (ID,NOM,PRENOM,PSEUDO) VALUES ('"
					+ id
					+ "','"
					+ participant.getNom()
					+ "','"
					+ participant.getPrenom()
					+ "','"
					+ participant.getPseudo() + "')";
			session.createSQLQuery(insert).executeUpdate();
			return id;
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	public int getIdMax() {
		Session session = getSession();
		try {
			String select = "select id from participant";
			List<Integer> listeId = (List<Integer>) session.createSQLQuery(
					select).list();
			if (listeId == null || listeId.isEmpty()) {
				return 0;
			} else if (listeId.size() == 1) {
				return listeId.get(0);
			} else {
				int max = listeId.get(0);
				for (Integer id : listeId) {
					if (id > max) {
						max = id;
					}
				}
				return max;
			}

		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public void delete(Participant participant) {
		Session session = getSession();
		try {
			String delete = "delete from participant where id="
					+ participant.getId();
			session.createSQLQuery(delete).executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
}
