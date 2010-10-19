package pes.dao.util;

import pes.dao.IEquipeDAO;
import pes.dao.IEvenementDAO;
import pes.dao.IJoueurDAO;
import pes.dao.IMatchDAO;
import pes.dao.IParticipantDAO;
import pes.dao.ITournoiDAO;

public abstract class DAOFactory {

	/**
	 * Creates a standalone DAOFactory that returns unmanaged DAO beans for use
	 * in any environment Hibernate has been configured for. Uses
	 * HibernateUtil/SessionFactory and Hibernate context propagation
	 * (CurrentSessionContext), thread-bound or transaction-bound, and
	 * transaction scoped.
	 */
	@SuppressWarnings("unchecked")
	public static final Class HIBERNATE = pes.dao.util.HibernateDAOFactory.class;

	/**
	 * Factory method for instantiation of concrete factories.
	 */
	@SuppressWarnings("unchecked")
	public static DAOFactory instance(Class factory) {
		try {
			return (DAOFactory) factory.newInstance();
		} catch (Exception ex) {
			throw new RuntimeException("Couldn't create DAOFactory: " + factory);
		}
	}

	// Add your DAO interfaces here
	public abstract IEquipeDAO getEquipeDAO();

	public abstract IJoueurDAO getJoueurDAO();

	public abstract ITournoiDAO getTournoiDAO();

	public abstract IParticipantDAO getParticipantDAO();
	
	public abstract IMatchDAO getMatchDAO();
	
	public abstract IEvenementDAO getEvenementDAO();

}
