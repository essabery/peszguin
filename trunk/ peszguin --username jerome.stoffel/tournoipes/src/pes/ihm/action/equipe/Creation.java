package pes.ihm.action.equipe;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.struts2.interceptor.SessionAware;

import pes.dao.IEquipeDAO;
import pes.dao.IJoueurDAO;
import pes.domaine.EPoste;
import pes.domaine.Equipe;
import pes.domaine.Joueur;
import pes.service.comparator.JoueurComparator;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Creation extends ActionSupport implements SessionAware {

	private String nom;
	private SortedSet<Joueur> joueursDispos;
	private SortedSet<Joueur> joueursChoisis;
	private SortedSet<Integer> idJoueursDispos;
	private SortedSet<Integer> idJoueursChoisis;
	
	private IEquipeDAO equipeDAO;
	private IJoueurDAO joueurDAO;

	private List<EPoste> postes;
	private String codePoste;

	private Equipe equipe;

	private boolean info;
	private boolean error;

	@SuppressWarnings("unchecked")
	private Map session;
	private final String cleJoueursChoisis = "joueursChoisis";
	private final String cleCodePoste = "codePoste";

	public String initialiser() {
		nettoyerSession();
		remplirListes();
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String validerCreation() {
		equipe = new Equipe();
		if (nom != null) {
			equipe.setLibelle(nom);
		}
		equipe.setJoueurs((SortedSet<Joueur>) session.get(cleJoueursChoisis));
		nettoyerSession();
		remplirListes();
		
		equipeDAO.insert(equipe);
		
		info = true;
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String modifierFiltre() {
		session.put(cleCodePoste, codePoste);
		remplirListes();
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String ajouterJoueurs() {
		joueursChoisis = (SortedSet<Joueur>) session.get(cleJoueursChoisis);
		if (joueursChoisis == null) {
			joueursChoisis = new TreeSet<Joueur>(new JoueurComparator());
		}
		for (Integer idJoueurDispo : idJoueursDispos) {
			joueursChoisis.add(joueurDAO.findById(idJoueurDispo));
		}
		session.put(cleJoueursChoisis, joueursChoisis);
		remplirListes();
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String supprimerJoueurs() {
		joueursChoisis = (SortedSet<Joueur>) session.get(cleJoueursChoisis);
		if (joueursChoisis == null) {
			joueursChoisis = new TreeSet<Joueur>(new JoueurComparator());
		}
		for (Integer idJoueurChoisi : idJoueursChoisis) {
			joueursChoisis.remove(joueurDAO.findById(idJoueurChoisi));
		}
		session.put(cleJoueursChoisis, joueursChoisis);
		remplirListes();
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	private void remplirListes() {
		postes = Arrays.asList(EPoste.values());
		joueursChoisis = (SortedSet<Joueur>) session.get(cleJoueursChoisis);
		if (joueursChoisis == null) {
			joueursChoisis = new TreeSet<Joueur>(new JoueurComparator());
		}
		joueursDispos = new TreeSet(new JoueurComparator());
		joueursDispos.addAll(joueurDAO.findAll());
		joueursDispos.removeAll(joueursChoisis);
		filtrer();
	}

	private void filtrer() {
		codePoste = (String) session.get(cleCodePoste);
		if (codePoste != null) {
			if (!codePoste.equals("0")) {
				SortedSet<Joueur> joueursDisposFiltres = new TreeSet<Joueur>(
						new JoueurComparator());
				EPoste poste = EPoste.get(codePoste);
				for (Joueur joueur : joueursDispos) {
					if (joueur.getPoste() == poste) {
						joueursDisposFiltres.add(joueur);
					}
				}
				joueursDispos = joueursDisposFiltres;
			}
		}
	}

	private void nettoyerSession() {
		session.remove(cleCodePoste);
		session.remove(cleJoueursChoisis);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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

	@SuppressWarnings("unchecked")
	public void setSession(Map session) {
		this.session = session;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public SortedSet<Joueur> getJoueursDispos() {
		return joueursDispos;
	}

	public void setJoueursDispos(SortedSet<Joueur> joueursDispos) {
		this.joueursDispos = joueursDispos;
	}

	public SortedSet<Joueur> getJoueursChoisis() {
		return joueursChoisis;
	}

	public void setJoueursChoisis(SortedSet<Joueur> joueursChoisis) {
		this.joueursChoisis = joueursChoisis;
	}

	public SortedSet<Integer> getIdJoueursDispos() {
		return idJoueursDispos;
	}

	public void setIdJoueursDispos(SortedSet<Integer> idJoueursDispos) {
		this.idJoueursDispos = idJoueursDispos;
	}

	public SortedSet<Integer> getIdJoueursChoisis() {
		return idJoueursChoisis;
	}

	public void setIdJoueursChoisis(SortedSet<Integer> idJoueursChoisis) {
		this.idJoueursChoisis = idJoueursChoisis;
	}
}
