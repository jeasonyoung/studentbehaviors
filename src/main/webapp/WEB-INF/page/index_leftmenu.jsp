<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
<!--
$(function(){
	$("#layout_leftMenu_swMainMenuTree").tree({
		url:"${pageContext.request.contextPath}/menu.action?systemId=PAS00000000000000000000000000000",
		lines:true,
		fit:true,
		animate:true,
		onClick:function(node){
			addWorkspaceTab(node);
		},
		onDbClick:function(node){
			if(node.state == "closed"){
				$(this).tree("expand",node.target);
			}else{
				$(this).tree("collapse",node.target);
			}
		}
	});
	/* 
	$("#layout_leftMenu_sysTree").tree({
		url:"${pageContext.request.contextPath}/system/menu!tree.action?systemId=S0000000000000000000000000000001",
		lines:true,
		fit:true,
		animate:true,
		onClick:function(node){
			addWorkspaceTab(node);
		},
		onDbClick:function(node){
			if(node.state == "closed"){
				$(this).tree("expand",node.target);
			}else{
				$(this).tree("collapse",node.target);
			}
		}
	}); */
});
//-->
</script>
<div class="easyui-accordion" data-options="fit:true,border:false">
	<div title="晨检/午检" data-options="iconCls:'icon-reload',selected:true" style="overflow:auto;padding:2px;">
		<ul id="layout_leftMenu_swMainMenuTree"/>
	</div>
	<!-- <div title="系统管理">
		<ul id="layout_leftMenu_sysTree"/>
	</div> -->
</div>