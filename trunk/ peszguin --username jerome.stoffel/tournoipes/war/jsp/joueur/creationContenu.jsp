<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3>Création d'un nouveau joueur</h3>
<s:form action="validerCreation">
	<p><span class="titreElementCourt"><s:label value="Nom"
		for="nom" /></span> <s:textfield name="nom" id="nom"
		title="Entrez le nom ici (obligatoire)" /><span><s:fielderror
		fieldName="nomVide" theme="monSimple"></s:fielderror></span></p>
	<p><span class="titreElementCourt"><s:label value="Prénom"
		for="prenom" /></span><s:textfield name="prenom" id="prenom"
		title="Entrez le prénom ici (facultatif)" /></p>
	<p><span class="titreElementCourt"><s:label value="Poste"
		for="poste" /></span><s:select list="postes" name="codePoste" listKey="code"
		listValue="libelle" headerKey="0" headerValue="Choissez un poste"
		title="Choisissez le poste ici (obligatoire)"></s:select> <span><s:fielderror
		fieldName="posteVide" theme="monSimple"></s:fielderror></span></p>
	<p><s:submit value="Valider" /></p>
</s:form>