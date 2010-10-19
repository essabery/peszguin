package pes.dao;

import pes.domaine.Joueur;

public interface IJoueurDAO extends IGenericDAO<Joueur, Integer> {
	
	public int insert(Joueur joueur);
	
	public void delete(Joueur joueur);
}
