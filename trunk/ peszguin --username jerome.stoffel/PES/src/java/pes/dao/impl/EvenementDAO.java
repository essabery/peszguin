package pes.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import pes.dao.IEvenementDAO;
import pes.domaine.ETypeEvenement;
import pes.domaine.Evenement;
import pes.domaine.Match;
import pes.domaine.Tournoi;

public class EvenementDAO extends GenericDAO<Evenement, Integer> implements
		IEvenementDAO {

	@Override
	public void deleteByMatch(Match match) {
		Session session = getSession();
		try {
			String delete = "delete from Evenement e where e.match=:match";
			session.createQuery(delete).setParameter("match", match)
					.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Evenement> find(Tournoi tournoi) {
		Session session = getSession();
		try {
			String select = "from Evenement e where e.equipe.tournoi=:tournoi";
			return (List<Evenement>) session.createQuery(select).setParameter(
					"tournoi", tournoi).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public int getIdMax() {
		Session session = getSession();
		try {
			String select = "select id from evenement";
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
	public void delete(Evenement evenement) {
		Session session = getSession();
		try {
			String delete = "delete from evenement where id="
					+ evenement.getId();
			session.createSQLQuery(delete).executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(Evenement evenement) {
		Session session = getSession();
		int id = getIdMax() + 1;
		try {
			String insert = "";
			if (evenement.getMinute() != null) {
				insert = "INSERT INTO evenement (ID,TYPE,ID_MATCH,ID_EQUIPE,ID_JOUEUR,MINUTE) VALUES ('"
						+ id
						+ "','"
						+ evenement.getType().getCode()
						+ "','"
						+ evenement.getMatch().getId()
						+ "','"
						+ evenement.getEquipe().getId()
						+ "','"
						+ evenement.getJoueur().getId()
						+ "','"
						+ evenement.getMinute() + "')";
			} else {
				insert = "INSERT INTO evenement (ID,TYPE,ID_MATCH,ID_EQUIPE,ID_JOUEUR) VALUES ('"
						+ id
						+ "','"
						+ evenement.getType().getCode()
						+ "','"
						+ evenement.getMatch().getId()
						+ "','"
						+ evenement.getEquipe().getId()
						+ "','"
						+ evenement.getJoueur().getId() + "')";
			}
			session.createSQLQuery(insert).executeUpdate();
			return id;
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Evenement> find(Tournoi tournoi, ETypeEvenement type) {
		List<Evenement> all = find(tournoi);
		List<Evenement> retour = new ArrayList<Evenement>();
		for (Evenement evenement : all) {
			if (evenement.getType() == type) {
				retour.add(evenement);
			}
		}
		return retour;
	}
}
