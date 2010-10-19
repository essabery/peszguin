package pes.ihm.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

/**
 * Classe squelette pour l'impl�mentation des tags JSP.
 * Cette classe propose un squelette permettant de construire ais�ment des tags JSP.
 * La m�thode {@link #doStartTag()} soit �tre d�finie dans le tag impl�mentant. Toutes
 * les autres m�thodes peuvent �tre red�finie, mais dans le cas g�n�ral, il conviendra simplement
 * de red�finir {@link #doEndTag()} si on veut une action � la fin du tag, et {@link #release()}
 * pour liberer les ressources sp�cifiques au tag.
 * @author Denis Cabasson
 * @author Entrep�t de donn�es locales
 * @author Insee 2004
 * @version 1.2 14/02/2005
 * @see javax.servlet.jsp.tagext.Tag
 * @since 1.2
 */
public abstract class AbstractTag implements Tag {

	/* Attributs */

	/**Le contexte de la page dans laquelle ce tag a �t� appell�.
	 * Contient en particulier la <code>request</code> qui a appell� la page courante */
	protected PageContext pc=null;
	/** Le tag parent de ce tag, pour pouvoir continuer le traitement */
	protected Tag parent = null;

	/* Setters / Getters */

	/**
	 * Setter pour l'attribut <code>pc</code> de ce tag.
	 * @param arg0 le contexte de la page courante
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(javax.servlet.jsp.PageContext)
	 */
	public void setPageContext(PageContext arg0) {
		this.pc = arg0;
	}

	/**
	 * Setter pour l'attribut <code>parent</code> de ce tag.
	 * @param arg0 le tag parent de ce tag
	 * @see javax.servlet.jsp.tagext.Tag#setParent(javax.servlet.jsp.tagext.Tag)
	 */
	public void setParent(Tag arg0) {
		this.parent = arg0;
	}

	/**
	 * Accesseur pour l'attribut <code>pc</code> de ce tag.
	 * @return le contexte de la page courante
	 * @see javax.servlet.jsp.tagext.Tag#getParent()
	 */
	public Tag getParent() {
		return this.parent;
	}

	/* M�thodes */

	/**
	 * La m�thode appell�e lors de l'ouverture de ce tag.
	 * L'impl�mentation de cette m�thode est laiss�e au soin de la classe �tendant le squelette
	 * de tag.
	 * @return {@link javax.servlet.jsp.tagext.Tag#SKIP_BODY} or {@link javax.servlet.jsp.tagext.Tag#EVAL_BODY_INCLUDE}
	 * @throws JspException En cas d'erreur d'ecriture dans la page
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public abstract int doStartTag() throws JspException;

	/**
	 * La m�thode appell�e lors de la fermeture de ce tag.
	 * Cette m�thode ne fait rien et retourne {@link javax.servlet.jsp.tagext.Tag#EVAL_PAGE}
	 * pour continuer l'�valuation de la page courante.
	 * Cette m�thode peut �tre red�finie par la classe etendant cette classe pour effectuer une
	 * autre action.
	 * @return {@link javax.servlet.jsp.tagext.Tag#EVAL_PAGE}
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	/**
	 * La m�thode appell�e lors de la desctruction de ce tag.
	 * Cette m�thode lib�re les ressources associ�es � ce tag.
	 * La classe �tendant cette classe doit � son tour liberer les ressources mobilis�es.
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		this.parent = null;
		this.pc = null;
	}

}
