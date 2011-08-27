package pes.ihm.tag;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;

import pes.ihm.service.MapMenuVertical;

/**
 * Tag permettant de cr�er le texte du bandeau haut en fonction de la page �
 * laquelle on se situe. Cette page d�clare un attribut contextMenu, qui permet
 * de d�terminer les titres � afficher (titre1 et titre2).
 * 
 * Ce tag s'appuie sur la liste des items, rang�e dans une Map � laquelle on
 * acc�de par le code de l'item de menu (nom court utilis� dans les fichiers de
 * properties).
 * 
 * 
 * @author Aude Mulliez
 * @author Octopusse
 * @author Insee 2007
 * @version 1.0
 * @since 1.0
 */
public class BandeauTitreTag extends AbstractTag {

	/**
	 * La m�thode appell�e lors de l'ouverture de ce tag. <br>
	 **/

	@Override
	public int doStartTag() throws JspTagException {
		HttpServletRequest requete = (HttpServletRequest) pc.getRequest();

		/*
		 * Remarque : contextMenu ne peut pas �tre null (sinon erreur g�n�r�e
		 * par les tiles
		 */
		String contextMenu = (String) requete.getAttribute("contextMenu");

		try {
			JspWriter out = pc.getOut();

			out.println("<h1>" + MapMenuVertical.getTitre1(contextMenu)
					+ "</h1>");
			if (contextMenu.equals("accueil")) {
				out.println("<h2>Accueil</h2>");
			} else
				out.println("<h2>" + MapMenuVertical.getTitre2(contextMenu)
						+ "</h2>");

		} catch (IOException ex) {
			throw new JspTagException("An IOException occurred");
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() {
		return EVAL_PAGE;
	}
}
