<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<script type="text/javascript">
<!--
$(function(){
	$("#settings_headmasteredit_form input[name=classId]").combobox({
		url:"${pageContext.request.contextPath}/settings/classes!all.action",
		required:true,
		valueField:"id",
		textField:"name"
	});
	$("#settings_headmasteredit_form input[name=teacherId]").combobox({
		url:"${pageContext.request.contextPath}/settings/teachers!all.action",
		required:true,
		valueField:"id",
		textField:"name"
	});
	$("#settings_headmasteredit_form input[name=type]").combobox({
		required:true,
		valueField:"value",
		textField:"name",
		data:[{
			name:"班主任",
			value:1
		},{
			name:"副班主任",
			value:2
		}]
	});
});
//-->
</script>
<form id="settings_headmasteredit_form" method="POST" style="padding:10px;">
	<div style="float:left;margin-top:2px;width:250px;">
		<span style="float:left;width:75px;text-align:right;padding-top:2px;">所属班级：</span>
		<input type="text" name="classId" style="width:168px;"/>
	</div>
	<div style="float:left;margin-top:2px;width:250px;">
		<span style="float:left;width:75px;text-align:right;padding-top:2px;">班主任老师：</span>
		<input type="text" name="teacherId" style="width:168px;"/>
	</div>
	<div style="float:left;margin-top:2px;width:250px;">
		<span style="float:left;width:75px;text-align:right;padding-top:2px;">班主任类型：</span>
		<input type="text" name="type" style="width:80px;"/>
	</div>
</form>