package pes.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

import pes.dao.IGenericDAO;

public abstract class GenericDAO<E, PK extends Serializable> implements
		IGenericDAO<E, PK> {

	private Class<E> persistentClass;
	private Session session;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.persistentClass = (Class<E>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void setSession(Session s) {
		this.session = s;
	}

	protected Session getSession() {
		if (session == null)
			throw new IllegalStateException(
					"Session has not been set on DAO before usage");
		return session;
	}

	public Class<E> getPersistentClass() {
		return persistentClass;
	}

	@SuppressWarnings("unchecked")
	public E findById(PK id, boolean lock) {
		E entity;
		if (lock)
			entity = (E) getSession().load(getPersistentClass(), id,
					LockMode.UPGRADE);
		else
			entity = (E) getSession().load(getPersistentClass(), id);

		return entity;
	}

	public List<E> findAll() {
		return findByCriteria();
	}

	@SuppressWarnings("unchecked")
	public List<E> findByExample(E exampleInstance, String[] excludeProperty) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		crit.add(example);
		return crit.list();
	}
	public void clear() {
		getSession().clear();
	}

	/**
	 * Use this inside subclasses as a convenience method.
	 */
	@SuppressWarnings("unchecked")
	protected List<E> findByCriteria(Criterion... criterion) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

	public E findById(PK id) {
		return findById(id, false);
	}

}
