<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	$("#settings_studentsedit_form input[name=classId]").combobox({
		url:"${pageContext.request.contextPath}/settings/classes!all.action",
		valueField:"id",
		textField:"name"
	});
	$("#settings_studentsedit_form input[name=status]").combobox({
		valueField:"value",
		textField:"name",
		data:[{
			name:"不在校",
			value:0
		},{
			name:"在校",
			value:1
		}]
	});
});
//-->
</script>
<form id="settings_studentsedit_form" method="POST" style="padding:7px;">
	<div style="float:left;margin-top:2px;width:450px;">
		<span>所属班级：</span>
		<input type="text" name="classId" class="easyui-validatebox" data-options="required:true" style="width:368px;"/>
	</div>
	<div style="float:left;margin-top:2px;width:450px;">
		<div style="float:left;">
			<span>学生代码：</span>
			<input type="text" name="code" class="easyui-validatebox" data-options="required:true" style="width:128px;"/>
			<input type="hidden" name="id"/>
		</div>
		<div style="float:left;margin-left:35px;">
			<span>学生姓名：</span>
			<input type="text" name="name" class="easyui-validatebox" data-options="required:true" style="width:128px;"/>
		</div>
	</div>
	<div style="float:left;margin-top:2px;width:450px;">
		<div style="float:left;">
			<span>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</span>
			<input type="text" name="gender" class="easyui-validatebox" data-options="required:true" style="width:128px;"/>
		</div>
		<div style="float:left;margin-left:35px;">
			<span>身份证号：</span>
			<input type="text" name="idCard" style="width:128px;"/>
		</div>
	</div>
	<div style="float:left;margin-top:2px;width:450px;">
		<div style="float:left;">
			<span>入学年度：</span>
			<input type="text" name="joinYear" style="width:128px;"/>
		</div>
		<div style="float:left;margin-left:35px;">
			<span>状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：</span>
			<input type="text" name="status" style="width:128px;"/>
		</div>
	</div>
</form>