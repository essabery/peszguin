package pes.ihm.wrapper;

import org.displaytag.decorator.TableDecorator;

import pes.domaine.Equipe;


public class EquipeWrapper extends TableDecorator {

	public String getAfficher() {
		Equipe equipe = (Equipe) getCurrentRowObject();
		return "<a href='/PES/equipe/afficherEquipe.action?idEquipe="
				+ equipe.getId()
				+ "'><img src='/PES/images/consulter.png' style='border: none;'"
				+ " title='Afficher équipe'></img></a>";
	}

	public String getSupprimer() {
		Equipe equipe = (Equipe) getCurrentRowObject();
		return "<a href='/PES/equipe/suppression.action?idEquipe="
				+ equipe.getId()
				+ "'><img src='/PES/images/supprimer.png' style='border: none;'"
				+ " title='Supprimer équipe'></img></a>";
	}

}
