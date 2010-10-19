<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="displaytag" uri="http://displaytag.sf.net"%>


<%@page import="java.util.Map"%>
<%@page import="pes.domaine.ENiveauMatch"%>
<%@page import="java.util.List"%>
<%@page import="pes.domaine.Match"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.SortedMap"%>
<%@page import="pes.domaine.Evenement"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@page import="pes.service.comparator.EvenementComparator"%>

<style>
<!--
-->
td.niveau {
	border-top: 1px solid black;
	font-weight: bold;
}

td.extGauche {
	border-right: 1px solid black;
}

td.CARTON_JAUNE {
	background: url("../images/jaune.png") no-repeat;
	width: 16px;
	height: 16px;
}

td.CARTON_ROUGE {
	background: url("../images/rouge.png") no-repeat;
	width: 16px;
	height: 16px;
}

td.BUT {
	background: url("../images/but.png") no-repeat;
	width: 16px;
	height: 16px;
}

td.XBLESSURE {
	background: url("../images/blessure.png") no-repeat;
	width: 16px;
	height: 16px;
}

td.evenement {
	font-size: 80%;
}

td.formEvenement {
	border-top: 1px solid black;
}
</style>

<h3 style="margin-bottom: 20px;">Phase finale</h3>

<table>
	<tr>
		<td>
		<table>
			<tr>
				<td colspan="3" class="niveau">Demi-Finales - Matchs Aller</td>
			</tr>
			<tr>
				<td><s:property
					value="demi1Aller.equipeDomicile.participant.prenom" /></td>
				<td><a
					href="modifMatchFinal.action?idMatchEnCours=<s:property
			value="demi1Aller.id" ></s:property>"><s:property
					value="demi1Aller.score" /></a></td>
				<td><s:property
					value="demi1Aller.equipeExterieur.participant.prenom" /></td>
			</tr>
			<tr>
				<td><s:property
					value="demi2Aller.equipeDomicile.participant.prenom" /></td>
				<td><a
					href="modifMatchFinal.action?idMatchEnCours=<s:property
			value="demi2Aller.id" />"><s:property
					value="demi2Aller.score" /></a></td>
				<td><s:property
					value="demi2Aller.equipeExterieur.participant.prenom" /></td>
			</tr>
			<tr>
				<td><s:property
					value="demi3Aller.equipeDomicile.participant.prenom" /></td>
				<td><a
					href="modifMatchFinal.action?idMatchEnCours=<s:property
			value="demi3Aller.id" />"><s:property
					value="demi3Aller.score" /></a></td>
				<td><s:property
					value="demi3Aller.equipeExterieur.participant.prenom" /></td>
			</tr>
			<tr>
				<td><s:property
					value="demi4Aller.equipeDomicile.participant.prenom" /></td>
				<td><a
					href="modifMatchFinal.action?idMatchEnCours=<s:property
			value="demi4Aller.id" />"><s:property
					value="demi4Aller.score" /></a></td>
				<td><s:property
					value="demi4Aller.equipeExterieur.participant.prenom" /></td>
			</tr>

			<tr>
				<td colspan="3" class="niveau">Demi-Finales - Matchs Retour</td>
			</tr>
			<tr>
				<td><s:property
					value="demi1Retour.equipeDomicile.participant.prenom" /></td>
				<td><a
					href="modifMatchFinal.action?idMatchEnCours=<s:property
			value="demi1Retour.id" />"><s:property
					value="demi1Retour.score" /></a></td>
				<td><s:property
					value="demi1Retour.equipeExterieur.participant.prenom" /></td>
			</tr>
			<tr>
				<td><s:property
					value="demi2Retour.equipeDomicile.participant.prenom" /></td>
				<td><a
					href="modifMatchFinal.action?idMatchEnCours=<s:property
			value="demi2Retour.id" />"><s:property
					value="demi2Retour.score" /></a></td>
				<td><s:property
					value="demi2Retour.equipeExterieur.participant.prenom" /></td>
			</tr>
			<tr>
				<td><s:property
					value="demi3Retour.equipeDomicile.participant.prenom" /></td>
				<td><a
					href="modifMatchFinal.action?idMatchEnCours=<s:property
			value="demi3Retour.id" />"><s:property
					value="demi3Retour.score" /></a></td>
				<td><s:property
					value="demi3Retour.equipeExterieur.participant.prenom" /></td>
			</tr>
			<tr>
				<td><s:property
					value="demi4Retour.equipeDomicile.participant.prenom" /></td>
				<td><a
					href="modifMatchFinal.action?idMatchEnCours=<s:property
			value="demi4Retour.id" />"><s:property
					value="demi4Retour.score" /></a></td>
				<td><s:property
					value="demi4Retour.equipeExterieur.participant.prenom" /></td>
			</tr>

			<s:if test="finales">
				<tr>
					<td colspan="3" class="niveau">Finales</td>
				</tr>
				<tr>
					<td><s:property
						value="finale1.equipeDomicile.participant.prenom" /></td>
					<td><a
						href="modifMatchFinal.action?idMatchEnCours=<s:property
			value="finale1.id" />"><s:property
						value="finale1.score" /></a></td>
					<td><s:property
						value="finale1.equipeExterieur.participant.prenom" /></td>
				</tr>
				<tr>
					<td><s:property
						value="finale2.equipeDomicile.participant.prenom" /></td>
					<td><a
						href="modifMatchFinal.action?idMatchEnCours=<s:property
			value="finale2.id" />"><s:property
						value="finale2.score" /></a></td>
					<td><s:property
						value="finale2.equipeExterieur.participant.prenom" /></td>
				</tr>
				<tr>
					<td><s:property
						value="finale3.equipeDomicile.participant.prenom" /></td>
					<td><a
						href="modifMatchFinal.action?idMatchEnCours=<s:property
			value="finale3.id" />"><s:property
						value="finale3.score" /></a></td>
					<td><s:property
						value="finale3.equipeExterieur.participant.prenom" /></td>
				</tr>
				<tr>
					<td><s:property
						value="finale4.equipeDomicile.participant.prenom" /></td>
					<td><a
						href="modifMatchFinal.action?idMatchEnCours=<s:property
			value="finale4.id" />"><s:property
						value="finale4.score" /></a></td>
					<td><s:property
						value="finale4.equipeExterieur.participant.prenom" /></td>
				</tr>
			</s:if>
		</table>
		</td>
		<td><s:if test="modif">
			<%
				Match matchEnCours = (Match) session
							.getAttribute("matchEnCours");
			%>
			<s:form action="validerMatchFinal">
				<table>
					<tr>
						<td colspan="6">
						<h3>Match en cours</h3>
						</td>
					</tr>
					<tr>
						<td colspan="2">
						<h3><%=matchEnCours.getEquipeDomicile().getParticipant()
									.getPrenom()%></h3>
						</td>
						<td colspan="2">
						<h3><%=matchEnCours.getButsDomicile()%>-<%=matchEnCours.getButsExterieur()%></h3>
						</td>
						<td colspan="2">
						<h3><%=matchEnCours.getEquipeExterieur()
									.getParticipant().getPrenom()%></h3>
						</td>
						<td></td>
					</tr>

					<%
						if (matchEnCours.getEvenements() != null) {
									List<Evenement> evenements = new ArrayList<Evenement>(
											matchEnCours.getEvenements());
									Collections.sort(evenements, new EvenementComparator());
									for (Evenement evenement : evenements) {
										if (evenement.getEquipe().getId().equals(
												matchEnCours.getEquipeDomicile().getId())) {
					%>
					<tr>
						<td class="<%=evenement.getType().getCode()%>"></td>
						<td class="evenement"><%=evenement.getJoueur().getNom()%></td>
						<td class="evenement"><%=evenement.getMinuteFormate()%></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<%
						} else {
					%>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td class="<%=evenement.getType().getCode()%>"></td>
						<td class="evenement"><%=evenement.getJoueur().getNom()%></td>
						<td class="evenement"><%=evenement.getMinuteFormate()%></td>
					</tr>
					<%
						}
									}
								}
					%>
					<tr>
						<td colspan="6" class="formEvenement"></td>
					</tr>
					<%
						if (matchEnCours.isTermine() == false) {
					%>
					<tr>
						<td colspan="2"><s:select list="typesEvenements"
							name="codeTypeEvenement" listKey="code" listValue="libelle"></s:select></td>
						<td colspan="2"><s:select list="joueurs" name="idJoueur"
							listKey="id" listValue="nom"></s:select></td>
						<td colspan="2"><s:select list="minutes" name="minute"
							headerKey="-1" headerValue="N/A"></s:select></td>

					</tr>
					<%
						}
					%>
				</table>

				<%
					if (matchEnCours.isTermine() == false) {
				%>
				<s:submit value="Ajouter" action="addEvenementFinal"></s:submit>

				<s:submit value="Valider le match"></s:submit>
				<%
					}
				%>
				<s:if test="finales==false">
					<s:submit value="Reset le match" action="resetMatchFinal"></s:submit>
				</s:if>
				<%
					if (matchEnCours.getNiveau() == ENiveauMatch.FINALE1
									|| matchEnCours.getNiveau() == ENiveauMatch.FINALE3
									|| matchEnCours.getNiveau() == ENiveauMatch.FINALE5
									|| matchEnCours.getNiveau() == ENiveauMatch.FINALE7) {
				%>
				<s:submit value="Reset le match" action="resetMatchFinal"></s:submit>
				<%
					}
				%>


			</s:form>
		</s:if></td>
	</tr>
</table>

<s:if test="validationFinale">
	<s:form action="validationFinale">
		<s:submit value="Tournoi terminé : enregistrer"></s:submit>
	</s:form>
</s:if>
