<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="displaytag" uri="http://displaytag.sf.net"%>

<s:if test="info">
	<p>le participant <s:property value="participant.pseudo" /> a �t�
	supprim� avec succ�s</p>
</s:if>