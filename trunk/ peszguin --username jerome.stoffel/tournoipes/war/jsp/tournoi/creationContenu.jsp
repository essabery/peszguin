<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sd" uri="/struts-dojo-tags"%>
<sd:head />

<h3>Création d'un nouveau tournoi</h3>
<s:form action="validerCreation">
	<p><span class="titreElementCourt"><s:label value="Nom"
		for="nom" /></span> <s:textfield name="libelle" id="nom"
		title="Entrez le nom ici (facultatif)" /></p>

	<p><span class="titreElementCourt"><s:label value="Date"
		for="date" /></span><sd:datetimepicker name="date"
		displayFormat="dd/MM/yyyy" /></p>
		
	<table>
		<tr>
			<td>Equipes disponibles</td>
			<td></td>
			<td>Equipes choisies</td>
		</tr>
		<tr>
			<td><s:select list="equipesDispos" size="10" listKey="id"
				listValue="libelle" multiple="true" name="idEquipesDispos"
				cssClass="transfert"></s:select></td>
			<td valign="middle" align="center"><BR />
			<p><s:submit action="ajoutEquipes" value=""
				cssClass="flecheDroite"></s:submit></p>
			<p><s:submit action="suppressionEquipes" value=""
				cssClass="flecheGauche"></s:submit></p>
			</td>
			<td><s:select list="equipesChoisies" size="10" listKey="id"
				listValue="libelle" multiple="true" name="idEquipesChoisies"
				cssClass="transfert"></s:select></td>
		</tr>
		<tr>
			<td colspan="3"><s:fielderror fieldName="nbEquipes"
				theme="monSimple"></s:fielderror></td>
		</tr>
		<tr></tr>
		<tr>
			<td>Participants disponibles</td>
			<td></td>
			<td>Participants choisis</td>
		</tr>
		<tr>
			<td><s:select list="participantsDispos" size="10" listKey="id"
				listValue="prenomPseudoNom" multiple="true"
				name="idParticipantsDispos" cssClass="transfert"></s:select></td>
			<td valign="middle" align="center"><BR />
			<p><s:submit action="ajoutParticipants" value=""
				cssClass="flecheDroite"></s:submit></p>
			<p><s:submit action="suppressionParticipants" value=""
				cssClass="flecheGauche"></s:submit></p>
			</td>
			<td><s:select list="participantsChoisis" size="10" listKey="id"
				listValue="prenomPseudoNom" multiple="true"
				name="idParticipantsChoisis" cssClass="transfert"></s:select></td>
		</tr>
		<tr>
			<td colspan="3"><s:fielderror fieldName="nbParticipants"
				theme="monSimple"></s:fielderror></td>
		</tr>
	</table>
	<p><s:submit value="Valider" /></p>
</s:form>