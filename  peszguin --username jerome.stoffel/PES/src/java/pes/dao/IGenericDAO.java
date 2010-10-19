package pes.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDAO<E, PK extends Serializable> {
	public E findById(PK id);

	public List<E> findAll();

}