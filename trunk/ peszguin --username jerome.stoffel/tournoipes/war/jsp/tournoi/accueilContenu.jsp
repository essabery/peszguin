<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div align="center">
<table>
	<tr>
		<td><s:a action="creation">
			<p><img src="/PES/images/nouveau.png"></img></p>
			<p align="center">Créer un nouveau tournoi</p>
		</s:a></td>
		<td><s:a action="consultation">
			<p><img src="/PES/images/loupe.png"></img></p>
			<p align="center">Consulter les tournois terminés</p>
		</s:a></td>
		<td><s:a action="evolution">
			<p><img src="/PES/images/evolution.png"></img></p>
			<p align="center">Entrer des résultats</p>
		</s:a></td>
	</tr>
</table>
</div>