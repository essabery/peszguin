package pes.dao;

import java.util.List;

import pes.domaine.Joueur;

public interface IJoueurDAO {
	
	public Joueur findById(int id);
	public List<Joueur> findAll();
	public int insert(Joueur joueur);
	public void delete(Joueur joueur);
}
