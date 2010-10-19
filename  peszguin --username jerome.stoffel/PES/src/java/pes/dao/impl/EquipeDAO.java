package pes.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import pes.dao.IEquipeDAO;
import pes.domaine.Equipe;
import pes.domaine.Joueur;

public class EquipeDAO extends GenericDAO<Equipe, Integer> implements
		IEquipeDAO {

	@Override
	public void updateTirage(int idEquipe, int idParticipant, String poule,
			int position) {
		Session session = getSession();
		try {
			String update = "UPDATE equipe SET ID_PARTICIPANT='"
					+ idParticipant + "', POULE='" + poule + "', POSITION='"
					+ 3 + "' WHERE  ID=" + idEquipe;
			session.createSQLQuery(update).executeUpdate();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(Equipe equipe) {
		Session session = getSession();
		try {
			int id = getIdMax() + 1;
			String insert = "INSERT INTO equipe (ID,LIBELLE) VALUES ('" + id
					+ "',  '" + equipe.getLibelle() + "')";
			session.createSQLQuery(insert).executeUpdate();
			for (Joueur joueur : equipe.getJoueurs()) {
				insert = "INSERT INTO joueur_equipe (ID_EQUIPE,ID_JOUEUR) VALUES ('"
						+ id + "','" + joueur.getId() + "')";
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
			String select = "select id from equipe";
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
	public void delete(Equipe equipe) {
		Session session = getSession();
		try {
			String delete = "delete from joueur_equipe where id_equipe="
					+ equipe.getId();
			session.createSQLQuery(delete).executeUpdate();
			delete = "delete from equipe where id=" + equipe.getId();
			session.createSQLQuery(delete).executeUpdate();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateTournoi(int idEquipe, int idTournoi) {
		Session session = getSession();
		try {
			String update = "UPDATE equipe SET ID_TOURNOI='" + idTournoi
					+ "' WHERE  ID=" + idEquipe;
			session.createSQLQuery(update).executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Equipe> findDispo() {
		List<Equipe> all = findAll();
		List<Equipe> dispos = new ArrayList<Equipe>();
		for (Equipe equipe : all) {
			if (equipe.getTournoi() == null) {
				dispos.add(equipe);
			}
		}
		return dispos;

	}

}
