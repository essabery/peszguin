<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div align="center">
<table>
	<tr>
		<td><s:a action="creation">
			<p><img src="/PES/images/nouveau.png"></img></p>
			<p align="center">Créer un nouveau participant</p>
		</s:a></td>
		<td><s:a action="consultation">
			<p><img src="/PES/images/loupe.png"></img></p>
			<p align="center">Consulter les participants</p>
		</s:a></td>
		<td><s:a action="hall">
			<p><img src="/PES/images/hall.png"></img></p>
			<p align="center">Hall of Fame</p>
		</s:a></td>
	</tr>
</table>
</div>