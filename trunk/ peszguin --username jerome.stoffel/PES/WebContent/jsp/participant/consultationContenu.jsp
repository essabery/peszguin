<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="displaytag" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="ce" uri="/cewolf"%>

<%@page import="org.jfree.chart.JFreeChart"%>

<%@page import="pes.domaine.Evenement"%>
<%@page import="java.util.List"%><jsp:useBean
	id="butsParMinutesProducer"
	class="pes.service.charts.ButsParMinutesProducer"></jsp:useBean>

<s:if test="init">
	<s:if test="participants!=null">
		<displaytag:table name="participants" defaultsort="1" pagesize="20"
			requestURI="<%= request.getContextPath()+"/participant/consultation.action" %>"
			sort="list" class="displayCenter"
			decorator="pes.ihm.wrapper.ParticipantWrapper">
			<displaytag:column property="pseudo" title="Pseudo" sortable="true"></displaytag:column>
			<displaytag:column property="nom" title="Nom" sortable="true"></displaytag:column>
			<displaytag:column property="prenom" title="Prénom" sortable="true"></displaytag:column>
			<displaytag:column property="afficher" title=""></displaytag:column>
		</displaytag:table>
	</s:if>
	<s:else>
		<p class="messagePasDeTableau">Pas de participants à Afficher</p>
	</s:else>
</s:if>
<s:else>
	<div align="center">
	<h3>Statistiques de <s:property
		value="participant.prenomPseudoNom" /></h3>
	<BR></BR>
	</div>
	<div align="center">
	<table border="1">
		<tr>
			<td>
			<table>
				<tr>
					<td style="font-weight: bold;">
					<div align="center">Général</div>
					</td>
				</tr>
				<tr>
					<td>
					<div align="center">
					<table class="displayCenter">
						<tr>
							<td>Nombre de tournois</td>
							<td><s:property value="nbTournois" /></td>
						</tr>
						<tr>
							<td>Nombre de victoires</td>
							<td><s:property value="nbVictoires" /></td>
						</tr>
						<tr>
							<td>Nombre de nuls</td>
							<td><s:property value="nbNuls" /></td>
						</tr>
						<tr>
							<td>Nombre de défaites</td>
							<td><s:property value="nbDefaites" /></td>
						</tr>
						<tr>
							<td>Nombre de buts marqués</td>
							<td><s:property value="nbButsMarques" /></td>
						</tr>
						<tr>
							<td>Nombre de buts encaissés</td>
							<td><s:property value="nbButsEncaisses" /></td>
						</tr>
						<tr>
							<td>Nombre de cartons jaunes</td>
							<td><s:property value="nbCartonsJaunes" /></td>
						</tr>
						<tr>
							<td>Nombre de cartons rouges</td>
							<td><s:property value="nbCartonsRouges" /></td>
						</tr>
						<tr>
							<td>Nombre de blessures</td>
							<td><s:property value="nbBlessures" /></td>
						</tr>
						<tr>
							<td>Nombre de blessures infligées</td>
							<td><s:property value="nbBlessuresInfligees" /></td>
						</tr>
					</table>
					</div>
					</td>
				</tr>
			</table>
			</td>
			<td>
			<table>
				<tr>
					<td style="font-weight: bold;">
					<div align="center">Tournois</div>
					</td>
				</tr>
				<tr>
					<td>
					<div align="center"><displaytag:table name="tournois"
						class="displayCenter" decorator="pes.ihm.wrapper.TournoiWrapper"
						defaultsort="2" defaultorder="descending" pagesize="5" sort="list"
						requestURI="/PES/participant/afficherParticipant.action"
						id="tournois">
						<displaytag:column property="libelle" title="Libellé"></displaytag:column>
						<displaytag:column property="dateLien" title="Date"
							sortProperty="date"></displaytag:column>
						<displaytag:column property="classementParticipant" title="Class."></displaytag:column>
					</displaytag:table></div>
					</td>
				</tr>
			</table>
			</td>
			<td>
			<div align="center">
			<table>
				<tr>
					<td style="font-weight: bold;">
					<div align="center">Buteurs</div>
					</td>
				</tr>
				<tr>
					<td>
					<div align="center"><displaytag:table name="buteurs"
						class="displayCenter" decorator="pes.ihm.wrapper.JoueurWrapper"
						defaultsort="2" defaultorder="descending" pagesize="5" sort="list"
						requestURI="/PES/participant/afficherParticipant.action"
						id="buteurs">
						<displaytag:column property="nom" value="Nom"></displaytag:column>
						<displaytag:column property="butsParticipant" title="Buts"
							sortable="true" defaultorder="descending"></displaytag:column>
						<displaytag:column property="nbTournoisParticipant"
							title="Nb Tournois"></displaytag:column>
						<displaytag:column property="butsParTournoi" title="Buts/Tournoi"
							sortable="true" defaultorder="descending"></displaytag:column>
					</displaytag:table></div>
					</td>
			</table>
			</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<div align="center">
			<table>
				<tr>
					<td>
					<table>
						<tr>
							<td style="font-weight: bold;">
							<div align="center">Adversaires</div>
							</td>
						</tr>
						<tr>
							<td>
							<div align="center"><displaytag:table name="adversaires"
								class="displayCenter"
								decorator="pes.ihm.wrapper.ParticipantWrapper" defaultsort="1"
								pagesize="5" sort="list"
								requestURI="/PES/participant/afficherParticipant.action"
								id="adversaires">
								<displaytag:column property="prenomLien" title="Prénom"
									sortable="true" sortProperty="prenom"></displaytag:column>
								<displaytag:column property="pseudo" title="Pseudo"></displaytag:column>
								<displaytag:column property="nbMatchsVsParticipant"
									title="Nb Matchs" sortable="true"></displaytag:column>
								<displaytag:column property="victoireVsParticipant" title="V"
									sortable="true"></displaytag:column>
								<displaytag:column property="nulVsParticipant" title="N"
									sortable="true"></displaytag:column>
								<displaytag:column property="defaiteVsParticipant" title="D"
									sortable="true"></displaytag:column>
							</displaytag:table></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</div>
			</td>
			<td colspan="2">
			<div align="center">
			<table>
				<tr>
					<td style="font-weight: bold;">
					<div align="center">Extrêmes</div>
					</td>
				</tr>
				<tr>
					<td>
					<div align="center">
					<table class="displayCenter">
						<tr>
							<td>Meilleur classement</td>
							<td><s:property value="meilleurClassement" /> (<a
								href="/PES/tournoi/afficherTournoi.action?idTournoi=<s:property value='tournoiMeilleurClassement.id' /> "><s:property
								value="tournoiMeilleurClassement.date" /></a>)</td>
						</tr>
						<tr>
							<td>Moins bon classement</td>
							<td><s:property value="pireClassement" /> (<a
								href="/PES/tournoi/afficherTournoi.action?idTournoi=<s:property value='tournoiPireClassement.id' /> "><s:property
								value="tournoiPireClassement.date" /></a>)</td>
						</tr>
						<tr>
							<th colspan="2" style="background-color: orange;">Victoire
							la plus zguin</th>
						</tr>
						<tr>
							<td><s:property
								value="plusGrosseVictoire.equipeDomicile.participant.prenom" />
							<s:property value="plusGrosseVictoire.score" /> <s:property
								value="plusGrosseVictoire.equipeExterieur.participant.prenom" /></td>
							<td><s:property value="plusGrosseVictoire.niveau.libelle" />
							(<a
								href="/PES/tournoi/afficherTournoi.action?idTournoi=<s:property value='tournoiPlusGrosseVictoire.id' />"><s:property
								value="tournoiPlusGrosseVictoire.date" /></a>)</td>
						</tr>
						<tr>
							<th colspan="2" style="background-color: orange;">Plus
							grosse branlée</th>
						</tr>
						<tr>
							<td><s:property
								value="plusGrosseDefaite.equipeDomicile.participant.prenom" />
							<s:property value="plusGrosseDefaite.score" /> <s:property
								value="plusGrosseDefaite.equipeExterieur.participant.prenom" /></td>
							<td><s:property value="plusGrosseDefaite.niveau.libelle" />
							(<a
								href="/PES/tournoi/afficherTournoi.action?idTournoi=<s:property value='tournoiPlusGrosseDefaite.id' /> "><s:property
								value="tournoiPlusGrosseDefaite.date" /></a>)</td>
						</tr>
					</table>
					</div>
					</td>
				</tr>
			</table>
			</div>
			</td>
		</tr>
		<tr>

			<td colspan="2">
			<div align="center">
			<table>
				<tr>
					<td style="font-weight: bold;">
					<div align="center">Buts marqués par minutes</div>
					</td>
				</tr>
				<tr>
					<td><ce:chart id="butsMarquesParMinutes" type="pie3D"
						showlegend="false">
						<ce:data>
							<ce:producer id="butsParMinutesProducer">
								<ce:param name="evenements"
									value="<%= (Evenement[])session.getAttribute("butsMarques")%>" />
							</ce:producer>
						</ce:data>
					</ce:chart>
					<div align="center"><ce:img chartid="butsMarquesParMinutes"
						height="250" width="350" renderer="butsMarques.cewolf"></ce:img></div>
					</td>
				</tr>
			</table>
			</div>
			</td>
			<td colspan="2">
			<div align="center">
			<table>
				<tr>
					<td style="font-weight: bold;">
					<div align="center">Buts encaissés par minutes</div>
					</td>
				</tr>
				<tr>
					<td><ce:chart id="butsEncaissesParMinutes" type="pie3D"
						showlegend="false">
						<ce:data>
							<ce:producer id="butsParMinutesProducer">
								<ce:param name="evenements"
									value="<%= (Evenement[])session.getAttribute("butsEncaisses")%>" />
							</ce:producer>
						</ce:data>
					</ce:chart>
					<div align="center"><ce:img chartid="butsEncaissesParMinutes"
						height="250" width="350" renderer="butsEncaisses.cewolf"></ce:img></div>
					</td>
				</tr>
			</table>
			</div>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<div align="center">
			<table>
				<tr>
					<td style="font-weight: bold;">
					<div align="center">Stats détaillées</div>
					</td>
				</tr>
				<tr>
					<td>
					<div align="center">
					<table class="displayCenter">
						<thead>
							<tr>
								<th colspan="3" class="sortable"></th>
								<th class="sortable" colspan="2">Général</th>
								<th class="sortable" colspan="2">Dom.</th>
								<th class="sortable" colspan="2">Ext.</th>
								<th class="sortable" colspan="2">Finale</th>
							</tr>
						</thead>
						<tr class="odd">
							<th colspan="3" class="sortable">Matchs joués</th>
							<td colspan="2">
							<div align="center"><s:property value="nbMatchs" /></div>
							</td>
							<td colspan="2">
							<div align="center"><s:property value="nbMatchsDom" /></div>
							</td>
							<td colspan="2">
							<div align="center"><s:property value="nbMatchsExt" /></div>
							</td>
							<td colspan="2">
							<div align="center"><s:property value="nbMatchsFinale" /></div>
							</td>
						</tr>
						<thead>
							<tr>
								<th colspan="3" class="sortable"></th>
								<th class="sortable">Nb</th>
								<th class="sortable">%</th>
								<th class="sortable">Nb</th>
								<th class="sortable">%</th>
								<th class="sortable">Nb</th>
								<th class="sortable">%</th>
								<th class="sortable">Nb</th>
								<th class="sortable">%</th>
							</tr>
						</thead>
						<tr class="odd">
							<th colspan="3" class="sortable">Victoires</th>
							<td>
							<div align="center"><s:property value="nbVictoires" /></div>
							</td>
							<td>
							<div align="center"><s:property value="pourcentageVictoire" /></div>
							</td>
							<td>
							<div align="center"><s:property value="nbVictoiresDom" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="pourcentageVictoireDom" /></div>
							</td>
							<td>
							<div align="center"><s:property value="nbVictoiresExt" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="pourcentageVictoireExt" /></div>
							</td>
							<td>
							<div align="center"><s:property value="nbVictoiresFinale" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="pourcentageVictoireFinale" /></div>
							</td>
						</tr>
						<tr class="even">
							<th colspan="3" class="sortable">Nuls</th>
							<td>
							<div align="center"><s:property value="nbNuls" /></div>
							</td>
							<td>
							<div align="center"><s:property value="pourcentageNul" /></div>
							</td>
							<td>
							<div align="center"><s:property value="nbNulsDom" /></div>
							</td>
							<td>
							<div align="center"><s:property value="pourcentageNulDom" /></div>
							</td>
							<td>
							<div align="center"><s:property value="nbNulsExt" /></div>
							</td>
							<td>
							<div align="center"><s:property value="pourcentageNulExt" /></div>
							</td>
							<td colspan="2"></td>
						</tr>
						<tr class="odd">
							<th colspan="3" class="sortable">Défaites</th>
							<td>
							<div align="center"><s:property value="nbDefaites" /></div>
							</td>
							<td>
							<div align="center"><s:property value="pourcentageDefaite" /></div>
							</td>
							<td>
							<div align="center"><s:property value="nbDefaitesDom" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="pourcentageDefaiteDom" /></div>
							</td>
							<td>
							<div align="center"><s:property value="nbDefaitesExt" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="pourcentageDefaiteExt" /></div>
							</td>
							<td>
							<div align="center"><s:property value="nbDefaitesFinale" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="pourcentageDefaiteFinale" /></div>
							</td>
						</tr>
						<thead>
							<tr>
								<th colspan="3" class="sortable"></th>
								<th class="sortable">Nb</th>
								<th class="sortable">Nb/Match</th>
								<th class="sortable">Nb</th>
								<th class="sortable">Nb/Match</th>
								<th class="sortable">Nb</th>
								<th class="sortable">Nb/Match</th>
								<th class="sortable">Nb</th>
								<th class="sortable">Nb/Match</th>
							</tr>
						</thead>
						<tr class="even">
							<th colspan="3" class="sortable">Buts marqués</th>
							<td>
							<div align="center"><s:property value="nbButsMarques" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbButsMarquesParMatch" /></div>
							</td>
							<td>
							<div align="center"><s:property value="nbButsMarquesDom" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbButsMarquesParMatchDom" /></div>
							</td>
							<td>
							<div align="center"><s:property value="nbButsMarquesExt" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbButsMarquesParMatchExt" /></div>
							</td>
							<td>
							<div align="center"><s:property value="nbButsMarquesFinale" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbButsMarquesParMatchFinale" /></div>
							</td>
						</tr>
						<tr class="odd">
							<th colspan="3" class="sortable">Buts encaissés</th>
							<td>
							<div align="center"><s:property value="nbButsEncaisses" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbButsEncaissesParMatch" /></div>
							</td>
							<td>
							<div align="center"><s:property value="nbButsEncaissesDom" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbButsEncaissesParMatchDom" /></div>
							</td>
							<td>
							<div align="center"><s:property value="nbButsEncaissesExt" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbButsEncaissesParMatchExt" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbButsEncaissesFinale" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbButsEncaissesParMatchFinale" /></div>
							</td>
						</tr>
						<tr class="even">
							<th colspan="3" class="sortable">Cartons jaunes</th>
							<td>
							<div align="center"><s:property value="nbCartonsJaunes" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbCartonsJaunesParMatch" /></div>
							</td>
							<td>
							<div align="center"><s:property value="nbCartonsJaunesDom" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbCartonsJaunesParMatchDom" /></div>
							</td>
							<td>
							<div align="center"><s:property value="nbCartonsJaunesExt" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbCartonsJaunesParMatchExt" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbCartonsJaunesFinale" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbCartonsJaunesParMatchFinale" /></div>
							</td>
						</tr>
						<tr class="odd">
							<th colspan="3" class="sortable">Cartons rouges</th>
							<td>
							<div align="center"><s:property value="nbCartonsRouges" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbCartonsRougesParMatch" /></div>
							</td>
							<td>
							<div align="center"><s:property value="nbCartonsRougesDom" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbCartonsRougesParMatchDom" /></div>
							</td>
							<td>
							<div align="center"><s:property value="nbCartonsRougesExt" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbCartonsRougesParMatchExt" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbCartonsRougesFinale" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbCartonsRougesParMatchFinale" /></div>
							</td>
						</tr>
						<tr class="even">
							<th colspan="3" class="sortable">Blessures</th>
							<td>
							<div align="center"><s:property value="nbBlessures" /></div>
							</td>
							<td>
							<div align="center"><s:property value="nbBlessuresParMatch" /></div>
							</td>
							<td>
							<div align="center"><s:property value="nbBlessuresDom" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbBlessuresParMatchDom" /></div>
							</td>
							<td>
							<div align="center"><s:property value="nbBlessuresExt" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbBlessuresParMatchExt" /></div>
							</td>
							<td>
							<div align="center"><s:property value="nbBlessuresFinale" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbBlessuresParMatchFinale" /></div>
							</td>
						</tr>
						<tr class="odd">
							<th colspan="3" class="sortable">Blessures infligées</th>
							<td>
							<div align="center"><s:property
								value="nbBlessuresInfligees" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbBlessuresInfligeesParMatch" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbBlessuresInfligeesDom" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbBlessuresInfligeesParMatchDom" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbBlessuresInfligeesExt" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbBlessuresInfligeesParMatchExt" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbBlessuresInfligeesFinale" /></div>
							</td>
							<td>
							<div align="center"><s:property
								value="nbBlessuresInfligeesParMatchFinale" /></div>
							</td>
						</tr>
					</table>
					</div>
					</td>
				</tr>
			</table>
			</div>
			</td>
		</tr>
	</table>
	</div>

	<div align="center"><s:form action="consultation">
		<s:submit value="Retourner au choix du participant"></s:submit>
	</s:form></div>
</s:else>