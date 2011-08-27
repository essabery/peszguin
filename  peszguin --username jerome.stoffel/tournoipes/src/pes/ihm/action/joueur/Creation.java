package pes.ihm.action.joueur;

import java.util.Arrays;
import java.util.List;

import pes.dao.IJoueurDAO;
import pes.domaine.EPoste;
import pes.domaine.Joueur;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Creation extends ActionSupport {

	// champs du formulaire
	private String nom;
	private String prenom;
	private List<EPoste> postes;
	private String codePoste;
	
	private IJoueurDAO joueurDAO;

	// Participant créé
	private Joueur joueur;

	boolean info;
	boolean error;

	public String initialiser() {
		postes = Arrays.asList(EPoste.values());
		return SUCCESS;
	}

	public String validerCreation() {
		postes = Arrays.asList(EPoste.values());
		if (champsValides()) {
			joueur = new Joueur();
			joueur.setNom(nom);
			joueur.setPrenom(prenom);
			joueur.setPoste(EPoste.get(codePoste));
			joueurDAO.insert(joueur);
			nom = "";
			prenom = "";
			codePoste = "0";
			info = true;
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	private boolean champsValides() {
		boolean resultat = true;
		if (nom.equals("")) {
			addFieldError("nomVide", "Veuillez remplir le nom");
			resultat = false;
		}
		if (codePoste.equals("0")) {
			addFieldError("posteVide", "Veuillez sélectionner un poste");
			resultat = false;
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

	public List<EPoste> getPostes() {
		return postes;
	}

	public void setPostes(List<EPoste> postes) {
		this.postes = postes;
	}

	public String getCodePoste() {
		return codePoste;
	}

	public void setCodePoste(String codePoste) {
		this.codePoste = codePoste;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public boolean isInfo() {
		return info;
	}

	public void setInfo(boolean info) {
		this.info = info;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

}
