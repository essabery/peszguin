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
<%@page import="pes.service.comparator.EvenementComparator"%><h3>Phase
de poules</h3>



<table style="width: 100%;">
	<tr>
		<td>
		<table align="center">
			<tr>
				<td colspan="3">
				<h3>Poule A</h3>
				</td>
				<td colspan="3">
				<h3>Poule B</h3>
				</td>
			</tr>
			<tr>
				<td colspan="3"><displaytag:table class="displayCenter"
					name="equipesPouleA" sort="external">
					<displaytag:column property="libelle" title="Equipe"></displaytag:column>
					<displaytag:column property="participant.prenom" title="Prénom"></displaytag:column>
					<displaytag:column property="points" title="Pts"></displaytag:column>
					<displaytag:column property="victoiresPoule" title="V"></displaytag:column>
					<displaytag:column property="nulsPoule" title="N"></displaytag:column>
					<displaytag:column property="defaitesPoule" title="D"></displaytag:column>
					<displaytag:column property="butsPoule" title="BP"></displaytag:column>
					<displaytag:column property="butsContrePoule" title="BC"></displaytag:column>
					<displaytag:column property="differencePouleFormate" title="Diff"></displaytag:column>
				</displaytag:table></td>
				<td colspan="3"><displaytag:table class="displayCenter"
					name="equipesPouleB" sort="external">
					<displaytag:column property="libelle" title="Equipe"></displaytag:column>
					<displaytag:column property="participant.prenom" title="Prénom"></displaytag:column>
					<displaytag:column property="points" title="Pts"></displaytag:column>
					<displaytag:column property="victoiresPoule" title="V"></displaytag:column>
					<displaytag:column property="nulsPoule" title="N"></displaytag:column>
					<displaytag:column property="defaitesPoule" title="D"></displaytag:column>
					<displaytag:column property="butsPoule" title="BP"></displaytag:column>
					<displaytag:column property="butsContrePoule" title="BC"></displaytag:column>
					<displaytag:column property="differencePouleFormate" title="Diff"></displaytag:column>
				</displaytag:table></td>
			</tr>
			<%
				@SuppressWarnings("unchecked")
				SortedMap<ENiveauMatch, Map<String, List<Match>>> matchs = (SortedMap<ENiveauMatch, Map<String, List<Match>>>) session
						.getAttribute("matchs");
				for (ENiveauMatch niveau : matchs.keySet()) {
			%>
			<tr align="center">
				<td colspan="6" class="niveau" align="center"><%=niveau.getLibelle()%></td>
			</tr>
			<tr>
				<td align="right">
				<%
					Match match = matchs.get(niveau).get("A").get(0);
				%> <%=match.getEquipeDomicile().getParticipant()
								.getPrenom()%></td>
				<td><a
					href="modifMatchPoule.action?idMatchEnCours=<%=match.getId()%>">
				<%=match.getScore()%></a></td>
				<td class="extGauche" align="left"><%=match.getEquipeExterieur().getParticipant()
								.getPrenom()%></td>
				<td class="domDroite">
				<%
					match = matchs.get(niveau).get("B").get(0);
				%> <%=match.getEquipeDomicile().getParticipant()
								.getPrenom()%></td>
				<td><a
					href="modifMatchPoule.action?idMatchEnCours=<%=match.getId()%>">
				<%=match.getScore()%></a></td>
				<td><%=match.getEquipeExterieur().getParticipant()
								.getPrenom()%></td>
			</tr>
			<tr>
				<td>
				<%
					match = matchs.get(niveau).get("A").get(1);
				%> <%=match.getEquipeDomicile().getParticipant()
								.getPrenom()%></td>
				<td><a
					href="modifMatchPoule.action?idMatchEnCours=<%=match.getId()%>">
				<%=match.getScore()%></a></td>
				<td class="extGauche"><%=match.getEquipeExterieur().getParticipant()
								.getPrenom()%></td>
				<td class="domDroite">
				<%
					match = matchs.get(niveau).get("B").get(1);
				%> <%=match.getEquipeDomicile().getParticipant()
								.getPrenom()%></td>
				<td><a
					href="modifMatchPoule.action?idMatchEnCours=<%=match.getId()%>">
				<%=match.getScore()%></a></td>
				<td><%=match.getEquipeExterieur().getParticipant()
								.getPrenom()%></td>
			</tr>
			<%
				}
			%>


		</table>
		</td>
		<td><s:if test="modif">
			<%
				Match matchEnCours = (Match) session
							.getAttribute("matchEnCours");
			%>
			<s:form action="validerMatch">
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
				<s:submit value="Ajouter" action="addEvenement"></s:submit>

				<s:submit value="Valider le match"></s:submit>
				<%
					}
				%>
				<s:submit value="Reset le match" action="resetMatch"></s:submit>
			</s:form>
		</s:if></td>
	</tr>
	<%
		boolean finPoules = true;
		for (ENiveauMatch niveau : matchs.keySet()) {
			for (String poule : matchs.get(niveau).keySet()) {
				for (Match m : matchs.get(niveau).get(poule)) {
					if (!m.isTermine()) {
						finPoules = false;
					}
				}
			}
		}

		if (finPoules) {
	%>
	<tr>
		<td align="center"><s:form action="phase2Valider">
			<s:submit value="Valider les résultats des poules"></s:submit>
		</s:form></td>
		<td></td>
	</tr>
	<%
		}
	%>

</table>

