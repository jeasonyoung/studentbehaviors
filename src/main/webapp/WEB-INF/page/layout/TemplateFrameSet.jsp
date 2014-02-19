<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
				
		<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui/themes/icon.css" type="text/css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui/themes/default/easyui.css" type="text/css"/>
		
		<!--[if lt IE 9]>
			<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jquery-easyui/jquery-1.9.1.min.js"></script>
		<![endif]-->
		<!--[if gte IE 9]><!-->
			<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jquery-easyui/jquery-2.0.min.js"></script>
		<!--<![endif]-->
		
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jquery-easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
		
		<decorator:head/>
		<base target="_self"/>
		<title>
			<decorator:title/>
		</title>
	</head>
	
	<body class="easyui-layout">
		<decorator:body/>
	</body>
</html>