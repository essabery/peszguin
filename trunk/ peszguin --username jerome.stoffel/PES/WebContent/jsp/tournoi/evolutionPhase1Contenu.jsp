<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="displaytag" uri="http://displaytag.sf.net"%>

<h3>Association des participants avec leurs équipes</h3>

<s:form action="phase1Valider">
	<table>
		<tr>
			<th>Equipe</th>
			<th>Participant</th>
			<th>Poule</th>
			<th>Position</th>
		</tr>
		<tr>
			<td>Equipe <b><s:property value="e1.libelle" /></b></td>
			<td><s:select list="participants" listKey="id"
				listValue="prenomPseudoNom" headerKey="-1"
				headerValue="Choisir un participant" name="p1"></s:select></td>
			<td><s:select list="poules" headerKey="-1"
				headerValue="Choisir une poule" name="poule1"></s:select></td>
			<td><s:select list="positions" headerKey="-1"
				headerValue="Choisir une position" name="pos1"></s:select></td>
			<td>
			<p><s:fielderror fieldName="p1" theme="monSimple">
			</s:fielderror></p>
			<p><s:fielderror fieldName="pos1" theme="monSimple"></s:fielderror></p>
			<p><s:fielderror fieldName="poule1" theme="monSimple"></s:fielderror></p>
			</td>
		</tr>
		<tr>
			<td>Equipe <b><s:property value="e2.libelle" /></b></td>
			<td><s:select list="participants" listKey="id"
				listValue="prenomPseudoNom" headerKey="-1"
				headerValue="Choisir un participant" name="p2"></s:select></td>
			<td><s:select list="poules" headerKey="-1"
				headerValue="Choisir une poule" name="poule2"></s:select></td>
			<td><s:select list="positions" headerKey="-1"
				headerValue="Choisir une position" name="pos2"></s:select></td>
			<td>
			<p><s:fielderror fieldName="p2" theme="monSimple"></s:fielderror></p>
			<p><s:fielderror fieldName="pos2" theme="monSimple"></s:fielderror></p>
			<p><s:fielderror fieldName="poule2" theme="monSimple"></s:fielderror></p>
			</td>
		</tr>
		<tr>
			<td>Equipe <b><s:property value="e3.libelle" /></b></td>
			<td><s:select list="participants" listKey="id"
				listValue="prenomPseudoNom" headerKey="-1"
				headerValue="Choisir un participant" name="p3"></s:select></td>
			<td><s:select list="poules" headerKey="-1"
				headerValue="Choisir une poule" name="poule3"></s:select></td>
			<td><s:select list="positions" headerKey="-1"
				headerValue="Choisir une position" name="pos3"></s:select></td>
			<td>
			<p><s:fielderror fieldName="p3" theme="monSimple"></s:fielderror></p>
			<p><s:fielderror fieldName="pos3" theme="monSimple"></s:fielderror></p>
			<p><s:fielderror fieldName="poule3" theme="monSimple"></s:fielderror></p>
			</td>
		</tr>
		<tr>
			<td>Equipe <b><s:property value="e4.libelle" /></b></td>
			<td><s:select list="participants" listKey="id"
				listValue="prenomPseudoNom" headerKey="-1"
				headerValue="Choisir un participant" name="p4"></s:select></td>
			<td><s:select list="poules" headerKey="-1"
				headerValue="Choisir une poule" name="poule4"></s:select></td>
			<td><s:select list="positions" headerKey="-1"
				headerValue="Choisir une position" name="pos4"></s:select></td>
			<td>
			<p><s:fielderror fieldName="p4" theme="monSimple"></s:fielderror></p>
			<p><s:fielderror fieldName="pos4" theme="monSimple"></s:fielderror></p>
			<p><s:fielderror fieldName="poule4" theme="monSimple"></s:fielderror></p>
			</td>
		</tr>
		<tr>
			<td>Equipe <b><s:property value="e5.libelle" /></b></td>
			<td><s:select list="participants" listKey="id"
				listValue="prenomPseudoNom" headerKey="-1"
				headerValue="Choisir un participant" name="p5"></s:select></td>
			<td><s:select list="poules" headerKey="-1"
				headerValue="Choisir une poule" name="poule5"></s:select></td>
			<td><s:select list="positions" headerKey="-1"
				headerValue="Choisir une position" name="pos5"></s:select></td>
			<td>
			<p><s:fielderror fieldName="p5" theme="monSimple"></s:fielderror></p>
			<p><s:fielderror fieldName="pos5" theme="monSimple"></s:fielderror></p>
			<p><s:fielderror fieldName="poule6" theme="monSimple"></s:fielderror></p>
			</td>
		</tr>
		<tr>
			<td>Equipe <b><s:property value="e6.libelle" /></b></td>
			<td><s:select list="participants" listKey="id"
				listValue="prenomPseudoNom" headerKey="-1"
				headerValue="Choisir un participant" name="p6"></s:select></td>
			<td><s:select list="poules" headerKey="-1"
				headerValue="Choisir une poule" name="poule6"></s:select></td>
			<td><s:select list="positions" headerKey="-1"
				headerValue="Choisir une position" name="pos6"></s:select></td>
			<td>
			<p><s:fielderror fieldName="p6" theme="monSimple"></s:fielderror></p>
			<p><s:fielderror fieldName="pos6" theme="monSimple"></s:fielderror></p>
			<p><s:fielderror fieldName="poule6" theme="monSimple"></s:fielderror></p>
			</td>
		</tr>
		<tr>
			<td>Equipe <b><s:property value="e7.libelle" /></b></td>
			<td><s:select list="participants" listKey="id"
				listValue="prenomPseudoNom" headerKey="-1"
				headerValue="Choisir un participant" name="p7"></s:select></td>
			<td><s:select list="poules" headerKey="-1"
				headerValue="Choisir une poule" name="poule7"></s:select></td>
			<td><s:select list="positions" headerKey="-1"
				headerValue="Choisir une position" name="pos7"></s:select></td>
			<td>
			<p><s:fielderror fieldName="p7" theme="monSimple"></s:fielderror></p>
			<p><s:fielderror fieldName="pos7" theme="monSimple"></s:fielderror></p>
			<p><s:fielderror fieldName="poule7" theme="monSimple"></s:fielderror></p>
			</td>
		</tr>
		<tr>
			<td>Equipe <b><s:property value="e8.libelle" /></b></td>
			<td><s:select list="participants" listKey="id"
				listValue="prenomPseudoNom" headerKey="-1"
				headerValue="Choisir un participant" name="p8"></s:select></td>
			<td><s:select list="poules" headerKey="-1"
				headerValue="Choisir une poule" name="poule8"></s:select></td>
			<td><s:select list="positions" headerKey="-1"
				headerValue="Choisir une position" name="pos8"></s:select></td>
			<td>
			<p><s:fielderror fieldName="p8" theme="monSimple"></s:fielderror></p>
			<p><s:fielderror fieldName="pos8" theme="monSimple"></s:fielderror></p>
			<p><s:fielderror fieldName="poule8" theme="monSimple"></s:fielderror></p>
			</td>
		</tr>

		<tr>
			<td colspan="5"><s:fielderror fieldName="distincts"
				theme="monSimple"></s:fielderror></td>
		</tr>
		<tr>
			<td colspan="5"><s:fielderror fieldName="coherencePoules"
				theme="monSimple"></s:fielderror></td>
		</tr>
		<tr>
			<td colspan="5"><s:fielderror fieldName="coherencePositions"
				theme="monSimple"></s:fielderror></td>
		</tr>
	</table>
	<s:submit value="Etape suivante"></s:submit>
</s:form>