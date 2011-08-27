<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="displaytag" uri="http://displaytag.sf.net"%>

<div align="center"><h3>Hall of Fame</h3></div>

<BR/>

<div align="center">
<table class="displayCenter">
	<thead>
		<tr>
			<th>Record</th>
			<th>Valeur</th>
			<th>Participant(s)</th>
		</tr>
		<tr>
			<th colspan="3">
			<div align="center">Général</div>
			</th>
		</tr>
	</thead>
	<tr class="odd">
		<th>Plus grand nombre de tournois joués</th>
		<td ><s:property value="hall.tournoisJouesValeur" /></td>
		<td><s:property value="hall.tournoisJouesParticipant" escape="false"/></td>
	</tr>
	<tr class="even">
		<th>Plus grand nombre de tournois gagnés</th>
		<td><s:property value="hall.tournoisGagnesValeur" /></td>
		<td><s:property value="hall.tournoisGagnesParticipant" escape="false" /></td>
	</tr>
	<tr class="odd">
		<th>Meilleur résultat en poules</th>
		<td><s:property value="hall.resultatPoulesValeur"/></td>
		<td><s:property value="hall.resultatPoulesParticipant" escape="false" /> </td>
	</tr>
	<tr class="even">
		<th>Finale la plus prolifique</th>
		<td><s:property value="hall.finaleProlifiqueValeur"/></td>
		<td><s:property value="hall.finaleProlifiqueParticipant" escape="false" /> </td>
	</tr>
	<tr class="odd">
		<th>Profiteur de CSC</th>
		<td><s:property value="hall.profiteurCSCValeur"/></td>
		<td><s:property value="hall.profiteurCSCParticipant" escape="false"/> </td>
	</tr>
	<tr class="even">
		<th>Victime de CSC</th>
		<td><s:property value="hall.victimeCSCValeur"/></td>
		<td><s:property value="hall.victimeCSCParticipant" escape="false" /> </td>
	</tr>
	<thead>
		<tr>
			<th colspan="3">
			<div align="center">Par tournoi</div>
			</th>
		</tr>
	</thead>
	<tr class="even">
		<th>Meilleure attaque</th>
		<td><s:property value="hall.attaqueValeur"/></td>
		<td><s:property value="hall.attaqueParticipant" escape="false" /> </td>
	</tr>
	<tr class="odd">
		<th>Meilleure défense</th>
		<td><s:property value="hall.defenseValeur"/></td>
		<td><s:property value="hall.defenseParticipant" escape="false" /> </td>
	</tr>
	<tr class="even">
		<th>Meilleure moisson de cartons jaunes</th>
		<td><s:property value="hall.jaunesValeur"/></td>
		<td><s:property value="hall.jaunesParticipant" escape="false" /> </td>
	</tr>
	<tr class="odd">
		<th>Meilleure moisson de cartons rouges</th>
		<td><s:property value="hall.rougesValeur"/></td>
		<td><s:property value="hall.rougesParticipant" escape="false" /> </td>
	</tr>
	<tr class="even">
		<th>Plus grand nombre de blessures</th>
		<td><s:property value="hall.blessuresValeur"/></td>
		<td><s:property value="hall.blessuresParticipant" escape="false" /> </td>
	</tr>
	<tr class="odd">
		<th>Plus grand nombre de blessures infligées</th>
		<td><s:property value="hall.blessuresInfligeesValeur"/></td>
		<td><s:property value="hall.blessuresInfligeesParticipant" escape="false" /> </td>
	</tr>

</table>
</div>