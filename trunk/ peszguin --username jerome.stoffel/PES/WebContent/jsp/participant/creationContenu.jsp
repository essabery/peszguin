<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3>Cr�ation d'un nouveau participant</h3>
<s:form action="validerCreation">
	<p><span class="titreElementCourt"><s:label value="Nom"
		for="nom" /></span> <s:textfield name="nom" id="nom"
		title="Entrez le nom ici (facultatif)" /></p>
	<p><span class="titreElementCourt"><s:label value="Pr�nom"
		for="prenom" /></span><s:textfield name="prenom" id="prenom"
		title="Entrez le pr�nom ici (facultatif)" /></p>
	<p><span class="titreElementCourt"><s:label value="Pseudo"
		for="pseudo" /></span> <s:textfield name="pseudo" id="pseudo"
		title="Entrez le pseudo ici (obligatoire)" /><span><s:fielderror
		theme="monSimple">
		<param value="pseudoVide" />
		<param value="pseudoUtilise" />
	</s:fielderror></span></p>
	<p><s:submit value="Valider" /></p>
</s:form>