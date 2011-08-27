<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="displaytag" uri="http://displaytag.sf.net"%>


<%@page import="java.util.SortedMap"%>
<%@page import="pes.domaine.ENiveauMatch"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="pes.domaine.Match"%>
<%@page import="pes.domaine.Evenement"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@page import="pes.service.comparator.EvenementComparator"%>
<s:if test="init">
	<h3>Consultation tournois</h3>
	<displaytag:table class="displayCenter" name="tournois"
		decorator="pes.ihm.wrapper.TournoiWrapper" defaultsort="1" defaultorder="descending">
		<displaytag:column property="date" title="Date"></displaytag:column>
		<displaytag:column property="libelle" title="Libellé"></displaytag:column>
		<displaytag:column property="consulter" title=""></displaytag:column>
	</displaytag:table>
</s:if>
<s:else>
	<h3>Consultation tournoi <s:property value="tournoi.libelle" />
	du <s:property value="tournoi.date" /></h3>
	<s:if test="afficherStats">
		<table style="width: 100%;" class="test">
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
							href="afficherMatch.action?idMatchEnCours=<%=match.getId()%>">
						<%=match.getScore()%></a></td>
						<td class="extGauche" align="left"><%=match.getEquipeExterieur().getParticipant()
										.getPrenom()%></td>
						<td class="domDroite">
						<%
							match = matchs.get(niveau).get("B").get(0);
						%> <%=match.getEquipeDomicile().getParticipant()
										.getPrenom()%></td>
						<td><a
							href="afficherMatch.action?idMatchEnCours=<%=match.getId()%>">
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
							href="afficherMatch.action?idMatchEnCours=<%=match.getId()%>">
						<%=match.getScore()%></a></td>
						<td class="extGauche"><%=match.getEquipeExterieur().getParticipant()
										.getPrenom()%></td>
						<td class="domDroite">
						<%
							match = matchs.get(niveau).get("B").get(1);
						%> <%=match.getEquipeDomicile().getParticipant()
										.getPrenom()%></td>
						<td><a
							href="afficherMatch.action?idMatchEnCours=<%=match.getId()%>">
						<%=match.getScore()%></a></td>
						<td><%=match.getEquipeExterieur().getParticipant()
										.getPrenom()%></td>
					</tr>
					<%
						}
					%>
					<tr align="center">
						<td colspan="6" class="niveau" align="center">Demi-finales</td>
					</tr>
					<tr>
						<td></td>
						<td class="bold">Aller</td>
						<td></td>
						<td></td>
						<td class="bold">Retour</td>
						<td></td>
					</tr>
					<tr>
						<td><s:property
							value="demi1Aller.equipeDomicile.participant.prenom" /></td>
						<td><a
							href="afficherMatch.action?idMatchEnCours=<s:property
			value="demi1Aller.id" ></s:property>"><s:property
							value="demi1Aller.score" /></a></td>
						<td><s:property
							value="demi1Aller.equipeExterieur.participant.prenom" /></td>
						<td><s:property
							value="demi1Retour.equipeDomicile.participant.prenom" /></td>
						<td><a
							href="afficherMatch.action?idMatchEnCours=<s:property
			value="demi1Retour.id" ></s:property>"><s:property
							value="demi1Retour.score" /></a></td>
						<td><s:property
							value="demi1Retour.equipeExterieur.participant.prenom" /></td>
					</tr>
					<tr>
						<td><s:property
							value="demi2Aller.equipeDomicile.participant.prenom" /></td>
						<td><a
							href="afficherMatch.action?idMatchEnCours=<s:property
			value="demi2Aller.id" ></s:property>"><s:property
							value="demi2Aller.score" /></a></td>
						<td><s:property
							value="demi2Aller.equipeExterieur.participant.prenom" /></td>
						<td><s:property
							value="demi2Retour.equipeDomicile.participant.prenom" /></td>
						<td><a
							href="afficherMatch.action?idMatchEnCours=<s:property
			value="demi2Retour.id" ></s:property>"><s:property
							value="demi2Retour.score" /></a></td>
						<td><s:property
							value="demi2Retour.equipeExterieur.participant.prenom" /></td>
					</tr>

					<tr>
						<td><s:property
							value="demi3Aller.equipeDomicile.participant.prenom" /></td>
						<td><a
							href="afficherMatch.action?idMatchEnCours=<s:property
			value="demi3Aller.id" ></s:property>"><s:property
							value="demi3Aller.score" /></a></td>
						<td><s:property
							value="demi3Aller.equipeExterieur.participant.prenom" /></td>
						<td><s:property
							value="demi3Retour.equipeDomicile.participant.prenom" /></td>
						<td><a
							href="afficherMatch.action?idMatchEnCours=<s:property
			value="demi3Retour.id" ></s:property>"><s:property
							value="demi3Retour.score" /></a></td>
						<td><s:property
							value="demi3Retour.equipeExterieur.participant.prenom" /></td>
					</tr>
					<tr>
						<td><s:property
							value="demi4Aller.equipeDomicile.participant.prenom" /></td>
						<td><a
							href="afficherMatch.action?idMatchEnCours=<s:property
			value="demi4Aller.id" ></s:property>"><s:property
							value="demi4Aller.score" /></a></td>
						<td><s:property
							value="demi4Aller.equipeExterieur.participant.prenom" /></td>
						<td><s:property
							value="demi4Retour.equipeDomicile.participant.prenom" /></td>
						<td><a
							href="afficherMatch.action?idMatchEnCours=<s:property
			value="demi4Retour.id" ></s:property>"><s:property
							value="demi4Retour.score" /></a></td>
						<td><s:property
							value="demi4Retour.equipeExterieur.participant.prenom" /></td>
					</tr>

					<tr align="center">
						<td colspan="6" class="niveau" align="center">Finales</td>
					</tr>
					<tr>
						<td colspan="3" class="bold">Grande Finale</td>
						<td colspan="3" class="bold">Finale 3ème Place</td>
					</tr>

					<tr>
						<td><s:property
							value="finale1.equipeDomicile.participant.prenom" /></td>
						<td><a
							href="afficherMatch.action?idMatchEnCours=<s:property
			value="finale1.id" ></s:property>"><s:property
							value="finale1.score" /></a></td>
						<td><s:property
							value="finale1.equipeExterieur.participant.prenom" /></td>
						<td><s:property
							value="finale2.equipeDomicile.participant.prenom" /></td>
						<td><a
							href="afficherMatch.action?idMatchEnCours=<s:property
			value="finale2.id" ></s:property>"><s:property
							value="finale2.score" /></a></td>
						<td><s:property
							value="finale2.equipeExterieur.participant.prenom" /></td>
					</tr>
					<tr>
						<td colspan="3" class="bold">Finale 5ème Place</td>
						<td colspan="3" class="bold">Finale 7ème Place</td>
					</tr>

					<tr>
						<td><s:property
							value="finale3.equipeDomicile.participant.prenom" /></td>
						<td><a
							href="afficherMatch.action?idMatchEnCours=<s:property
			value="finale3.id" ></s:property>"><s:property
							value="finale3.score" /></a></td>
						<td><s:property
							value="finale3.equipeExterieur.participant.prenom" /></td>
						<td><s:property
							value="finale4.equipeDomicile.participant.prenom" /></td>
						<td><a
							href="afficherMatch.action?idMatchEnCours=<s:property
			value="finale4.id" ></s:property>"><s:property
							value="finale4.score" /></a></td>
						<td><s:property
							value="finale4.equipeExterieur.participant.prenom" /></td>
					</tr>
				</table>
				</td>
				<td>
				<table>
					<s:if test="afficherMatch">
						<%
							Match matchEnCours = (Match) session
												.getAttribute("matchEnCours");
						%>
						<tr>
							<td colspan="6">
							<h3>Match en cours</h3>
							</td>
						</tr>
						<tr>
							<td colspan="2">
							<h3><%=matchEnCours.getEquipeDomicile()
										.getParticipant().getPrenom()%></h3>
							</td>
							<td colspan="2">
							<h3><%=matchEnCours.getScore()%></h3>
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
											Collections.sort(evenements,
													new EvenementComparator());
											for (Evenement evenement : evenements) {
												if (evenement.getEquipe().getId().equals(
														matchEnCours.getEquipeDomicile()
																.getId())) {
						%>
						<tr>
							<td class="<%=evenement.getType().getCode()%>"></td>
							<td class="evenement"><%=evenement.getJoueur().getNom()%></td>
							<td class="evenement"><%=evenement
															.getMinuteFormate()%></td>
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
							<td class="evenement"><%=evenement
															.getMinuteFormate()%></td>
						</tr>
						<%
							}
											}
										}
						%>

					</s:if>
					<tr>
						<td class="formEvenement" colspan="6">Buteurs</td>
					</tr>
					<tr>
						<td colspan="6"><displaytag:table name="buteurs"
							class="displayCenter" decorator="pes.ihm.wrapper.JoueurWrapper"
							defaultsort="2" defaultorder="descending" pagesize="5"
							sort="list" requestURI="/PES/tournoi/afficherMatch.action">
							<displaytag:column property="nom" value="Nom"></displaytag:column>
							<displaytag:column property="butsTournoi" title="Buts"></displaytag:column>
							<displaytag:column property="participantTournoi"
								title="Participant"></displaytag:column>
						</displaytag:table></td>
					</tr>

					<tr>
						<td class="formEvenement" colspan="6">Bouchers</td>
					</tr>
					<tr>
						<td colspan="6"><displaytag:table name="bouchers"
							class="displayCenter" decorator="pes.ihm.wrapper.JoueurWrapper"
							defaultorder="descending" pagesize="5" sort="external"
							requestURI="/PES/tournoi/afficherMatch.action">
							<displaytag:column property="nom" value="Nom"></displaytag:column>
							<displaytag:column property="rougesTournoi" title="Rouges"></displaytag:column>
							<displaytag:column property="jaunesTournoi" title="Jaunes"
								defaultorder="descending"></displaytag:column>
							<displaytag:column property="participantTournoi"
								title="Participant"></displaytag:column>
						</displaytag:table></td>
					</tr>

					<tr>
						<td class="formEvenement" colspan="6">Blessures</td>
					</tr>
					<tr>
						<td colspan="6"><displaytag:table name="blesses"
							class="displayCenter" decorator="pes.ihm.wrapper.JoueurWrapper"
							defaultsort="2" defaultorder="descending" pagesize="5"
							sort="list" requestURI="/PES/tournoi/afficherMatch.action">
							<displaytag:column property="nom" value="Nom"></displaytag:column>
							<displaytag:column property="blessuresTournoi" title="Blessures"></displaytag:column>
							<displaytag:column property="participantTournoi"
								title="Participant"></displaytag:column>
						</displaytag:table></td>
					</tr>

				</table>
				</td>
			</tr>
		</table>
	</s:if>
	<div align="center"><s:form action="consultation">
		<s:submit value="Retourner au choix des tournois"></s:submit>
	</s:form></div>
</s:else>