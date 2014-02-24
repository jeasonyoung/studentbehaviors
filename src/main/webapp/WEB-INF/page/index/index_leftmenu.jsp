<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
<!--
$(function(){
	$("#index_leftMenu_tree").tree({
		url:"${pageContext.request.contextPath}/menu.action",
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
	
});
//-->
</script>
<ul id="index_leftMenu_tree"/>