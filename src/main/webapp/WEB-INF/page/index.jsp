<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><s:property value="systemName"/></title>
	</head>
	<body class="easyui-layout">
		<!--banner-->
		<div data-options="region:'north',href:'index!top.action'"  style="height:86px;overflow:hidden;">
		</div>
		<!-- left menu -->
		<div title=" " data-options="region:'west',split:true,href:'index!leftmenu.action'" style="width:200px;overflow:hidden;">
		</div>
		<!-- workspace -->
		<div data-options="region:'center',href:'index!workspace.action'" style="overflow:hidden;">
		</div>  
		<!-- footer -->
		<div data-options="region:'south',href:'index!footer.action'" style="height:27px;overflow:hidden;">
		</div> 
	</body>
</html>