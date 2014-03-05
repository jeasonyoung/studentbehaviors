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
<form id="settings_headmasteredit_form" class="edit" method="POST">
	<dd style="marign-top:2px;">
		<label style="width:88px;">所属班级：</label>
		<input type="text" name="classId" style="width:168px;"/>
	</dd>
	<dd style="marign-top:2px;">
		<label style="width:88px;">班主任老师：</label>
		<input type="text" name="teacherId" style="width:168px;"/>
	</dd>
	<dd style="marign-top:2px;">
		<label style="width:88px;">班主任类型：</label>
		<input type="text" name="type" style="width:80px;"/>
	</dd>
</form>