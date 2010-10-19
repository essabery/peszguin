package pes.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import pes.dao.IMatchDAO;
import pes.domaine.ENiveauMatch;
import pes.domaine.Match;
import pes.domaine.Tournoi;

public class MatchDAO extends GenericDAO<Match, Integer> implements IMatchDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Match> find(Tournoi tournoi) {
		Session session = getSession();
		try {
			String select = "from Match where tournoi=:tournoi";
			List<Match> retour = (List<Match>) session.createQuery(select)
					.setEntity("tournoi", tournoi).list();
			return retour;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int insert(Match match) {
		Session session = getSession();
		int termine = 0;
		if (match.isTermine()) {
			termine = 1;
		}
		int id = getIdMax() + 1;
		try {
			String insert = "INSERT INTO rencontre (ID,NIVEAU,ID_TOURNOI,ID_EQUIPE_DOM,ID_EQUIPE_EXT,TERMINE) VALUES ('"
					+ id
					+ "','"
					+ match.getNiveau().getCode()
					+ "','"
					+ match.getTournoi().getId()
					+ "','"
					+ match.getEquipeDomicile().getId()
					+ "','"
					+ match.getEquipeExterieur().getId()
					+ "','"
					+ termine
					+ "')";
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
			String select = "select id from rencontre";
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
	public void delete(Match match) {
		Session session = getSession();
		try {
			String delete = "delete from rencontre where id=" + match.getId();
			session.createSQLQuery(delete).executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Match> find(Tournoi tournoi, ENiveauMatch niveau) {
		Session session = getSession();
		try {
			String select = "from Match where tournoi=:tournoi";
			List<Match> all = (List<Match>) session.createQuery(select)
					.setEntity("tournoi", tournoi).list();
			List<Match> retour = new ArrayList<Match>();
			for (Match match : all) {
				if (match.getNiveau() == niveau) {
					retour.add(match);
				}
			}
			return retour;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateMatchTermine(Match match) {
		Session session = getSession();
		try {
			String update = "UPDATE rencontre SET TERMINE='" + 1
					+ "' WHERE  ID=" + match.getId();
			session.createSQLQuery(update).executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateMatchReset(Match match) {
		Session session = getSession();
		try {
			String update = "UPDATE rencontre SET TERMINE='" + 0
					+ "' WHERE  ID=" + match.getId();
			session.createSQLQuery(update).executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
}
