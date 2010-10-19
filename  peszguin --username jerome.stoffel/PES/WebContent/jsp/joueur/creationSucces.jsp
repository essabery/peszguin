<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="info">
	<p>La création du joueur <b><s:property value="joueur.nom" /></b>
	a été faite avec succès</p>
</s:if>