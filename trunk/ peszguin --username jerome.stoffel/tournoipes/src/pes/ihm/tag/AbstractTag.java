package pes.ihm.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

/**
 * Classe squelette pour l'implémentation des tags JSP.
 * Cette classe propose un squelette permettant de construire aisément des tags JSP.
 * La méthode {@link #doStartTag()} soit être définie dans le tag implémentant. Toutes
 * les autres méthodes peuvent être redéfinie, mais dans le cas général, il conviendra simplement
 * de redéfinir {@link #doEndTag()} si on veut une action à la fin du tag, et {@link #release()}
 * pour liberer les ressources spécifiques au tag.
 * @author Denis Cabasson
 * @author Entrepôt de données locales
 * @author Insee 2004
 * @version 1.2 14/02/2005
 * @see javax.servlet.jsp.tagext.Tag
 * @since 1.2
 */
public abstract class AbstractTag implements Tag {

	/* Attributs */

	/**Le contexte de la page dans laquelle ce tag a été appellé.
	 * Contient en particulier la <code>request</code> qui a appellé la page courante */
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

	/* Méthodes */

	/**
	 * La méthode appellée lors de l'ouverture de ce tag.
	 * L'implémentation de cette méthode est laissée au soin de la classe étendant le squelette
	 * de tag.
	 * @return {@link javax.servlet.jsp.tagext.Tag#SKIP_BODY} or {@link javax.servlet.jsp.tagext.Tag#EVAL_BODY_INCLUDE}
	 * @throws JspException En cas d'erreur d'ecriture dans la page
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public abstract int doStartTag() throws JspException;

	/**
	 * La méthode appellée lors de la fermeture de ce tag.
	 * Cette méthode ne fait rien et retourne {@link javax.servlet.jsp.tagext.Tag#EVAL_PAGE}
	 * pour continuer l'évaluation de la page courante.
	 * Cette méthode peut être redéfinie par la classe etendant cette classe pour effectuer une
	 * autre action.
	 * @return {@link javax.servlet.jsp.tagext.Tag#EVAL_PAGE}
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	/**
	 * La méthode appellée lors de la desctruction de ce tag.
	 * Cette méthode libère les ressources associées à ce tag.
	 * La classe étendant cette classe doit à son tour liberer les ressources mobilisées.
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		this.parent = null;
		this.pc = null;
	}

}
