<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	$("#settings_usersedit_form input[name=teacherId]").combobox({
		url:"${pageContext.request.contextPath}/settings/teachers!all.action",
		required:true,
		valueField:"id",
		textField:"name"
	});
});
//-->
</script>
<form id="settings_usersedit_form" class="edit" method="POST">
	<dd style="marign-top:2px;">
		<label style="width:88px;">教师用户：</label>
		<input type="text" name="teacherId" style="width:168px;"/>
	</dd>
	<dd style="marign-top:2px;">
		<label style="width:88px;">用户角色：</label>
		<input type="text" name="role" style="width:80px;"/>
	</dd>
</form>