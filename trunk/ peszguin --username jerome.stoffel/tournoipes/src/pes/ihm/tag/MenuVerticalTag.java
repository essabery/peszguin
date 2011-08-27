package pes.ihm.tag;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;

import pes.ihm.service.ItemVertical;
import pes.ihm.service.ListeMenuVertical;

/**
 * Tag permettant de créer le menu vertical de droite. Il permet de générer le
 * script javascript nécessaire, en s'appuyant sur la liste des items de menu
 * java statique. Il permet également de développer et mettre en forme la partie
 * du menu correspondant à la page courante.
 * 
 * 
 * 
 * @author Aude Mulliez
 * @author Octopusse
 * @author Insee 2007
 * @version 1.0
 * @since 1.0
 */
public class MenuVerticalTag extends AbstractTag {

	/**
	 * La méthode appellée lors de l'ouverture de ce tag. <br>
	 **/

	@Override
	public int doStartTag() throws JspTagException {
		HttpServletRequest requete = (HttpServletRequest) pc.getRequest();

		/*
		 * Remarque : contextMenu ne peut pas être null (sinon erreur générée
		 * par les tiles)
		 */
		String contextMenu = (String) requete.getAttribute("contextMenu");
		// si on n'est pas passé par la première page, ou si la session a été
		// perdue, on n'affiche pas l'idep
		List<ItemVertical> listeItems = ListeMenuVertical.getListeItems();

		try {
			JspWriter out = pc.getOut();

			out.println("<div id=\"accueil\">");
			out.println("<a class=\"niveau1\" href=\""
					+ requete.getContextPath()
					+ "/accueil.action\">Accueil</a>");
			out.println("</div>");
			out.println("<div id=\"menuOcto\"></div>");
			out.println("<script type=\"text/javascript\">");
			out.println("(function() {");
			out.println("var menu;");
			out.println("function buildRandomTextNodeTree() {");
			out.println("var param;");
			out.println("menu = new YAHOO.widget.TreeView(\"menuOcto\");");

			for (ItemVertical item : listeItems) {
				// Dans le cas où on traite l'item du contexte, il faut
				// développer le menu et
				// ne pas mettre de lien. Si pas de parent, on met à la racine.
				if (item.getCode().equals(contextMenu)) {
					out
							.println("param = { label:\"" + item.getLabel()
									+ "\"};");
					if (item.getParentCode() == null) {
						out
								.println("var "
										+ item.getCode()
										+ "= new YAHOO.widget.MenuNode(param, menu.getRoot(), false);");
					} else {
						out.println("var " + item.getCode()
								+ "= new YAHOO.widget.MenuNode(param, "
								+ item.getParentCode() + ", false);");
					}
					out.println(item.getCode()
							+ ".labelStyle = \"menuCourant\";");
					out.println(item.getCode() + ".expand();");
					out.println("for (i=0 ; i<" + item.getCode()
							+ ".depth ; i++) {");
					out.println(item.getCode() + ".getAncestor(i).expand();");
					out.println(item.getCode()
							+ ".getAncestor(i).labelStyle = \"menuCourant\";");
					out.println("}");
					out.println("");
				}
				// Sinon, construction classique. Si pas de parent, on met à la
				// racine.
				else {
					out.println("param = { label:\"" + item.getLabel()
							+ "\", href:\"" + requete.getContextPath()
							+ item.getLien() + ".action\" };");
					if (item.getParentCode() == null) {
						out
								.println("var "
										+ item.getCode()
										+ "= new YAHOO.widget.MenuNode(param, menu.getRoot(), false);");
					} else {
						out.println("var " + item.getCode()
								+ "= new YAHOO.widget.MenuNode(param, "
								+ item.getParentCode() + ", false);");
					}
					out.println(item.getCode() + ".labelStyle = \"niveau"
							+ item.getNiveau() + "\";");
					out.println("");
				}
			}
			out.println("menu.draw();");
			out.println("}");
			out
					.println(" YAHOO.util.Event.addListener(window, \"load\", buildRandomTextNodeTree());");
			out.println("})();");
			out.println("</script>");

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
