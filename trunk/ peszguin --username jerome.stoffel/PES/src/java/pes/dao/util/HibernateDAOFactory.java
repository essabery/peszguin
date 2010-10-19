package pes.dao.util;

import org.hibernate.Session;

import pes.dao.IEquipeDAO;
import pes.dao.IEvenementDAO;
import pes.dao.IJoueurDAO;
import pes.dao.IMatchDAO;
import pes.dao.IParticipantDAO;
import pes.dao.ITournoiDAO;
import pes.dao.impl.EquipeDAO;
import pes.dao.impl.EvenementDAO;
import pes.dao.impl.GenericDAO;
import pes.dao.impl.JoueurDAO;
import pes.dao.impl.MatchDAO;
import pes.dao.impl.ParticipantDAO;
import pes.dao.impl.TournoiDAO;

public class HibernateDAOFactory extends DAOFactory {

	public IEquipeDAO getEquipeDAO() {
		return (IEquipeDAO) instantiateDAO(EquipeDAO.class);
	}

	public ITournoiDAO getTournoiDAO() {
		return (ITournoiDAO) instantiateDAO(TournoiDAO.class);
	}

	public IJoueurDAO getJoueurDAO() {
		return (IJoueurDAO) instantiateDAO(JoueurDAO.class);
	}

	public IParticipantDAO getParticipantDAO() {
		return (IParticipantDAO) instantiateDAO(ParticipantDAO.class);
	}

	@Override
	public IMatchDAO getMatchDAO() {
		return (IMatchDAO) instantiateDAO(MatchDAO.class);
	}
	
	@Override
	public IEvenementDAO getEvenementDAO() {
		return (IEvenementDAO) instantiateDAO(EvenementDAO.class);
	}
	
	@SuppressWarnings("unchecked")
	private GenericDAO instantiateDAO(Class daoClass) {
		try {
			GenericDAO dao = (GenericDAO) daoClass.newInstance();
			getCurrentSession().beginTransaction();
			dao.setSession(getCurrentSession());
			return dao;
		} catch (Exception ex) {
			throw new RuntimeException("Can not instantiate DAO: " + daoClass,
					ex);
		}
	}

	// You could override this if you don't want HibernateUtil for lookup
	protected Session getCurrentSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}



}
