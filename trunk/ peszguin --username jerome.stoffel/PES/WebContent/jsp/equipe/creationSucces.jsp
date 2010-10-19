<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="info">
	<p>La création de l'équipe <s:property value="equipe.libelle" /> a
	été faite avec succès</p>
</s:if>