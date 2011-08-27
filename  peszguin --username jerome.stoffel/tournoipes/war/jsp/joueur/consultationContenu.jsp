<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="displaytag" uri="http://displaytag.sf.net"%>

<s:if test="init">
	<s:if test="joueurs!=null">
		<displaytag:table name="joueurs" defaultsort="1" pagesize="20"
			requestURI="/PES/joueur/consultation.action" sort="list"
			class="displayCenter" decorator="pes.ihm.wrapper.JoueurWrapper">
			<displaytag:column property="nom" title="Nom" sortable="true"></displaytag:column>
			<displaytag:column property="prenom" title="Prénom" sortable="true"></displaytag:column>
			<displaytag:column property="poste.libelle" title="Poste"
				sortable="true"></displaytag:column>
			<displaytag:column property="afficher" title=""></displaytag:column>
		</displaytag:table>
	</s:if>
	<s:else>
		<p class="messagePasDeTableau">Pas de joueurs à Afficher</p>
	</s:else>
</s:if>
<s:else>
	<h3>Statistiques de <s:property value="joueur.prenom" /> <s:property
		value="joueur.nom" /></h3>
	<div align="center"><s:form action="consultation">
		<s:submit value="Retourner au choix du joueur"></s:submit>
	</s:form></div>
</s:else>