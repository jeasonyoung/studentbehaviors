<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	//roles
	$("#settings_usersedit_form input[name='role']").combobox({
		required:true,
		valueField:"value",
		textField:"value",
		data:[{
			value:"admin"
		},{
			value:"headmaster"
		},{
			value:"view"
		}]
	});
	//teachers
	$("#settings_usersedit_form input[name='teacherId']").combobox({
		url:"${pageContext.request.contextPath}/settings/teachers!all.action",
		required:true,
		valueField:"id",
		textField:"name"
	});
});
//-->
</script>
<form id="settings_usersedit_form" method="POST" style="padding:10px;">
	<div style="float:left;margin-top:2px;width:250px;">
		<span style="float:left;width:65px;text-align:right;padding-top:2px;">教师用户：</span>
		<input type="text" name="teacherId" style="width:168px;"/>
	</div>
	<div style="float:left;margin-top:2px;width:250px;">
		<span style="float:left;width:65px;text-align:right;padding-top:2px;">用户角色：</span>
		<input type="text" name="role" style="width:128px;"/>
	</div>
</form>