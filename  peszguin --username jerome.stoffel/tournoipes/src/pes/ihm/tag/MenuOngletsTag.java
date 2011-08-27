package pes.ihm.tag;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;

import pes.ihm.service.ItemOnglet;
import pes.ihm.service.ListeOnglets;




/**
 * Tag permettant de créer un menu à onglets à partir des listes d'onglets existantes
 * en passant en paramètre la liste d'onglets souhaitée et l'onglet courant.
 *

 * @author Aude Mulliez
 * @author Octopusse
 * @author Insee 2007
 * @version 1.0
 * @since 1.0
 */
public class MenuOngletsTag extends AbstractTag {

	/**
	 * La méthode appellée lors de l'ouverture de ce tag. <br>
	 **/

	@Override
	public int doStartTag() throws JspTagException {
		HttpServletRequest requete = (HttpServletRequest) pc.getRequest();

		/*Remarque : contextMenu et contextOnglet ne peuvent pas être null (sinon erreur générée par les tiles*/
		String contextMenu = (String)requete.getAttribute("contextMenu");
		String contextOnglet = (String)requete.getAttribute("contextOnglet");
		List<ItemOnglet> listeOnglets = ListeOnglets.getListeOnglets().get(contextMenu);


		try {
			JspWriter out = pc.getOut();

			out.println("<ul id=\"listeOnglets\">");
			for (ItemOnglet onglet : listeOnglets){
				if (onglet.getCode().equals(contextOnglet)) {
					out.println("<li id=\"courant\"><span>" + onglet.getLabel() + "</span></li>");
				} else {
					out.println("<li><a href=\"" + requete.getContextPath() + onglet.getLien() + ".action\">");
					out.println("<span>" + onglet.getLabel() + "</span></a></li>");
				}
			}
			out.println("</ul>");

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
