<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="displaytag" uri="http://displaytag.sf.net"%>

<s:if test="info">
	<p>l'�quipe <s:property value="equipe.libelle" /> a �t� supprim�e
	avec succ�s</p>
</s:if>