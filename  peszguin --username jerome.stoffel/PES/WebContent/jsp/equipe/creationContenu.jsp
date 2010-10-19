<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3>Création d'une nouvelle équipe</h3>
<s:form action="validerCreation">
	<p><span class="titreElementCourt"><s:label
		value="Nom (facultatif)" for="nom" /></span> <s:textfield name="nom"
		id="nom" title="Entrez le nom ici (facultatif)" /></p>

	<div align="center">
	<fieldset><legend>Choix des joueurs</legend>
	<table>
		<tr>
			<td><s:select list="joueursDispos" size="10" listKey="id"
				listValue="nomEtPrenom" multiple="true" name="idJoueursDispos"
				cssClass="transfert"></s:select></td>
			<td valign="middle" align="center"><BR />
			<p><s:submit action="ajoutJoueur" value=""
				cssClass="flecheDroite"></s:submit></p>
			<p><s:submit action="suppressionJoueur" value=""
				cssClass="flecheGauche"></s:submit></p>
			</td>
			<td><s:select list="joueursChoisis" size="10" listKey="id"
				listValue="nomEtPrenom" multiple="true" name="idJoueursChoisis"
				cssClass="transfert"></s:select></td>
		</tr>
		<tr>
			<td>Poste<BR />
			<s:select list="postes" name="codePoste" listKey="code"
				listValue="libelle" cssClass="transfert"
				headerValue="Tous les postes" headerKey="0" onchange="filtre()"></s:select></td>
			<td></td>
			<td></td>
		</tr>
	</table>
	</fieldset>
	</div>
	<p><s:submit value="Valider"></s:submit></p>
</s:form>

<script type="text/javascript">
	function filtre() {
		document.forms[0].action = "modificationFiltre.action";
		document.forms[0].submit();
	}
</script>