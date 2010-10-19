package pes.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import pes.dao.ITournoiDAO;
import pes.domaine.EStatutTournoi;
import pes.domaine.Participant;
import pes.domaine.Tournoi;

public class TournoiDAO extends GenericDAO<Tournoi, Integer> implements
		ITournoiDAO {

	@Override
	public void updateStatut(int idTournoi, EStatutTournoi statut) {
		Session session = getSession();
		try {
			String update = "UPDATE tournoi SET STATUT='" + statut.getCode()
					+ "' WHERE  ID=" + idTournoi;
			session.createSQLQuery(update).executeUpdate();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(Tournoi tournoi) {
		Session session = getSession();
		int id = getIdMax() + 1;
		try {
			String insert = "INSERT INTO tournoi (ID,DATE,LIBELLE,STRUCTURE,STATUT) VALUES ('"
					+ id
					+ "',  TIMESTAMP("
					+ new SimpleDateFormat("yyyyMMdd")
							.format(tournoi.getDate())
					+ "),'"
					+ tournoi.getLibelle()
					+ "','"
					+ tournoi.getStructure().getCode()
					+ "','"
					+ tournoi.getStatut().getCode() + "')";
			session.createSQLQuery(insert).executeUpdate();
			for (Participant participant : tournoi.getParticipants()) {
				insert = "INSERT INTO participant_tournoi (ID_TOURNOI,ID_PARTICIPANT) VALUES ('"
						+ id + "','" + participant.getId() + "')";
				session.createSQLQuery(insert).executeUpdate();
			}

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
			String select = "select id from tournoi";
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
	public List<Tournoi> findByStatut(EStatutTournoi statut) {
		List<Tournoi> all = findAll();
		List<Tournoi> retour = new ArrayList<Tournoi>();
		for (Tournoi tournoi : all) {
			if (tournoi.getStatut() == statut) {
				retour.add(tournoi);
			}
		}
		return retour;
	}

	@Override
	public List<Tournoi> findByStatut(EStatutTournoi statut,
			Participant participant) {
		List<Tournoi> tournoisTermines = findByStatut(EStatutTournoi.TERMINE);
		List<Tournoi> retour = new ArrayList<Tournoi>();
		for (Tournoi tournoi : tournoisTermines) {
			for (Participant p : tournoi.getParticipants()) {
				if (p.getId().equals(participant.getId())) {
					retour.add(tournoi);
				}
			}
		}
		return retour;
	}

}
