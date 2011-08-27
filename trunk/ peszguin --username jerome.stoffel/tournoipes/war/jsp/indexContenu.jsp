<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div align="center">
<table>
	<tr>
		<td><s:a action="participant/accueil">
			<p><img src="/PES/images/participant.png"
				onmouseover="zoomParticipant()" onmouseout="dezoomParticipant()"
				id="imgParticipant"></img></p>
			<p align="center">Participants</p>
		</s:a></td>
		<td><s:a action="tournoi/accueil">
			<p><img src="/PES/images/tournoi.png" onmouseover="zoomTournoi()"
				onmouseout="dezoomTournoi()" id="imgTournoi"></img></p>
			<p align="center">Tournois</p>
		</s:a></td>
	</tr>
	<tr>
		<td><s:a action="joueur/accueil">
			<p><img src="/PES/images/joueur.png" onmouseover="zoomJoueur()"
				onmouseout="dezoomJoueur()" id="imgJoueur"></img></p>
			<p align="center">Joueurs</p>
		</s:a></td>
		<td><s:a action="equipe/accueil">
			<p><img src="/PES/images/equipe.png" onmouseover="zoomEquipe()"
				onmouseout="dezoomEquipe()" id="imgEquipe"></img></p>
			<p align="center">Equipes</p>
		</s:a></td>
	</tr>
</table>
</div>
<script type="text/javascript">
	function zoomParticipant() {
		document.getElementById("imgParticipant").setAttribute("src", "/PES/images/participantZoom.png");
	}
	function dezoomParticipant() {
		document.getElementById("imgParticipant").setAttribute("src", "/PES/images/participant.png");
	}
	function zoomTournoi() {
		document.getElementById("imgTournoi").setAttribute("src", "/PES/images/tournoiZoom.png");
	}
	function dezoomTournoi() {
		document.getElementById("imgTournoi").setAttribute("src", "/PES/images/tournoi.png");
	}
	function zoomJoueur() {
		document.getElementById("imgJoueur").setAttribute("src", "/PES/images/joueurZoom.png");
	}
	function dezoomJoueur() {
		document.getElementById("imgJoueur").setAttribute("src", "/PES/images/joueur.png");
	}
	function zoomEquipe() {
		document.getElementById("imgEquipe").setAttribute("src", "/PES/images/equipeZoom.png");
	}
	function dezoomEquipe() {
		document.getElementById("imgEquipe").setAttribute("src", "/PES/images/equipe.png");
	}
</script>