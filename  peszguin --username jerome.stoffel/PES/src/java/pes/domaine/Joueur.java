package pes.domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Joueur implements Serializable {
	private Integer id;
	private String nom;
	private String prenom;
	private EPoste poste;

	public Joueur() {
	}

	public Joueur(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	public Equipe getEquipe(Tournoi tournoi) {
		for (Equipe equipe : tournoi.getEquipes()) {
			for (Joueur joueur : equipe.getJoueurs()) {
				if (joueur.getId().equals(id)) {
					return equipe;
				}
			}
		}
		return null;
	}

	public List<Evenement> getEvenements(Tournoi tournoi, ETypeEvenement type) {
		List<Evenement> resultat = new ArrayList<Evenement>();
		for (Match match : tournoi.getMatchs()) {
			if (match.getEvenements() != null) {
				for (Evenement evenement : match.getEvenements()) {
					if (evenement.getType() == type
							&& evenement.getJoueur().getId().equals(id)) {
						resultat.add(evenement);
					}
				}
			}
		}
		return resultat;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public EPoste getPoste() {
		return poste;
	}

	public void setPoste(EPoste poste) {
		this.poste = poste;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomEtPrenom() {
		if (nom == null) {
			return prenom;
		} else if (prenom == null) {
			return nom;
		} else {
			return nom + " " + prenom;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (id == ((Joueur) o).getId()) {
			return true;
		} else {
			return false;
		}
	}
}
