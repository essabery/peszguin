<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<tiles:useAttribute name="contextMenu" scope="request" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title><tiles:getAsString name="titre" /></title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/bandeauTitre.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/menuVertical.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/contenuPage.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/displaytag.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/pageSansOnglet.css">
<!--[if IE]><link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ie.css"><![endif]-->

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/yui/yahoo/yahoo-min.js?_yuiversion=2.4.1"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/yui/event/event-min.js?_yuiversion=2.4.1"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/yui/treeview/treeview-min.js?_yuiversion=2.4.1"></script>
<s:head />
</head>

<body>
<div id=bandeauTitre><tiles:insertAttribute name="bandeauTitre"></tiles:insertAttribute></div>
<div id=menuVertical><tiles:insertAttribute name="menuVertical"></tiles:insertAttribute></div>
<div id=contenuPage>
<div id="messageInfo"><tiles:insertAttribute name="messageInfo"></tiles:insertAttribute></div>
<div id="messageErreur"><tiles:insertAttribute
	name="messageErreur"></tiles:insertAttribute></div>
<div id="contenu"><tiles:insertAttribute name="contenu"></tiles:insertAttribute></div>
</div>
</body>
</html>