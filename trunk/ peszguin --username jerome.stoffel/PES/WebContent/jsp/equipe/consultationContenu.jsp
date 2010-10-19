<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="displaytag" uri="http://displaytag.sf.net"%>

<h3>Liste des équipes disponibles</h3>
<s:if test="equipes!=null">
	<displaytag:table name="equipes" defaultsort="1" pagesize="10"
		requestURI="<%= request.getContextPath()+"/equipe/consultation.action" %>"
		sort="list" class="displayCenter"
		decorator="pes.ihm.wrapper.EquipeWrapper">
		<displaytag:column property="libelle" title="Nom" sortable="true"></displaytag:column>
		<displaytag:column property="afficher" title=""></displaytag:column>
		<displaytag:column property="supprimer" title=""></displaytag:column>
	</displaytag:table>
</s:if>
<s:else>
	<p class="messagePasDeTableau">Pas d'équipes à Afficher</p>
</s:else>

<s:if test="afficherStats">

	<div align="center">
	<fieldset><legend> Liste des joueurs de l'équipe <b><s:property
		value="equipe.libelle" /></b> </legend>
	<table>
		<tr>
			<td>
			<h3>Gardiens</h3>
			<s:if test="gardiens!=null">
				<displaytag:table name="gardiens" class="displayCenter">
					<displaytag:column property="nom" title="Nom"></displaytag:column>
					<displaytag:column property="prenom" title="Prénom"></displaytag:column>
				</displaytag:table>
			</s:if> <s:else>Pas de gardiens</s:else></td>
			<td>
			<h3>Défenseurs</h3>
			<s:if test="defenseurs!=null">
				<displaytag:table name="defenseurs" class="displayCenter">
					<displaytag:column property="nom" title="Nom"></displaytag:column>
					<displaytag:column property="prenom" title="Prénom"></displaytag:column>
				</displaytag:table>
			</s:if> <s:else>Pas de défenseurs</s:else></td>
		</tr>
		<tr>
			<td>
			<h3>Milieux</h3>
			<s:if test="milieux!=null">
				<displaytag:table name="milieux" class="displayCenter">
					<displaytag:column property="nom" title="Nom"></displaytag:column>
					<displaytag:column property="prenom" title="Prénom"></displaytag:column>
				</displaytag:table>
			</s:if> <s:else>Pas de milieux</s:else></td>
			<td>
			<h3>Attaquants</h3>
			<s:if test="attaquants!=null">
				<displaytag:table name="attaquants" class="displayCenter">
					<displaytag:column property="nom" title="Nom"></displaytag:column>
					<displaytag:column property="prenom" title="Prénom"></displaytag:column>
				</displaytag:table>
			</s:if> <s:else>Pas d'attaquants</s:else></td>
		</tr>
	</table>
	</fieldset>
	</div>
</s:if>
