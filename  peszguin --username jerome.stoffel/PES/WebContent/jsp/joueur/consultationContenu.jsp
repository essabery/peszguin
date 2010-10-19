<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="displaytag" uri="http://displaytag.sf.net"%>

<s:if test="joueurs!=null">
	<displaytag:table name="joueurs" defaultsort="1" pagesize="20"
		requestURI="<%= request.getContextPath()+"/joueur/consultation.action" %>"
		sort="list" class="displayCenter"
		decorator="pes.ihm.wrapper.JoueurWrapper">
		<displaytag:column property="nom" title="Nom" sortable="true"></displaytag:column>
		<displaytag:column property="prenom" title="Prénom" sortable="true"></displaytag:column>
		<displaytag:column property="poste.libelle" title="Poste"
			sortable="true"></displaytag:column>
		<displaytag:column property="afficher" title=""></displaytag:column></displaytag:table>
</s:if>
<s:else>
	<p class="messagePasDeTableau">Pas de joueurs à Afficher</p>
</s:else>

<s:if test="afficherStats">Ici on affiche les stats de <s:property
		value="joueur.nom" />
</s:if>
