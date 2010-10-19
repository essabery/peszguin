<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="displaytag" uri="http://displaytag.sf.net"%>

<h3>Choix du tournoi à alimenter</h3>
<s:form action="choixTournoi">
	<p><span><s:select list="tournoisDispos" name="idTournoi"
		headerKey="-1" headerValue="Choisir un tournoi" listKey="id"
		listValue="dateEtLibelle"></s:select><s:fielderror
		fieldName="tournoi" theme="monSimple"></s:fielderror> </span></p>

	<p><s:submit value="C'est parti !"></s:submit></p>
</s:form>