<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="info">
	<p>La cr�ation de l'�quipe <s:property value="equipe.libelle" /> a
	�t� faite avec succ�s</p>
</s:if>