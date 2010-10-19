package pes.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import pes.dao.IJoueurDAO;
import pes.domaine.Joueur;

public class JoueurDAO extends GenericDAO<Joueur, Integer> implements
		IJoueurDAO {

	@Override
	public int insert(Joueur joueur) {
		Session session = getSession();
		int id = getIdMax() + 1;
		try {
			String insert = "INSERT INTO joueur (ID,NOM,PRENOM,POSTE) VALUES ('"
					+ id
					+ "','"
					+ joueur.getNom()
					+ "','"
					+ joueur.getPrenom()
					+ "','"
					+ joueur.getPoste().getCode() + "')";
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
			String select = "select id from joueur";
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
	public void delete(Joueur joueur) {
		Session session = getSession();
		try {
			String delete = "delete from joueur where id=" + joueur.getId();
			session.createSQLQuery(delete).executeUpdate();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

}
