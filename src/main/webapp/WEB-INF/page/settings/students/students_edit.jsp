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
<form id="settings_studentsedit_form" class="edit" method="POST">
	<dd style="marign-top:2px;">
		<label style="width:88px;">所属班级：</label>
		<input type="text" name="classId" class="easyui-validatebox" data-options="required:true" style="width:368px;"/>
	</dd>
	<dd style="marign-top:2px;">
		<div style="float:left;">
			<label style="width:88px;">学生代码：</label>
			<input type="hidden" name="id"/>
			<input type="text" name="code" class="easyui-validatebox" data-options="required:true" style="width:128px;"/>
		</div>
		<div style="float:left;margin-left:15px;">
			<label style="width:88px;">学生姓名：</label>
			<input type="text" name="name" class="easyui-validatebox" data-options="required:true" style="width:128px;"/>
		</div>
	</dd>
	<dd style="marign-top:2px;">
		<div style="float:left;">
			<label style="width:88px;">性别：</label>
			<input type="text" name="gender" class="easyui-validatebox" data-options="required:true" style="width:128px;"/>
		</div>
		<div style="float:left;margin-left:15px;">
			<label style="width:88px;">身份证号：</label>
			<input type="text" name="idCard" style="width:128px;"/>
		</div>
	</dd>
	<dd style="marign-top:2px;">
		<div style="float:left;">
			<label style="width:88px;">入学年度：</label>
			<input type="text" name="joinYear" style="width:128px;"/>
		</div>
		<div style="float:left;margin-left:15px;">
			<label style="width:88px;">状态：</label>
			<input type="text" name="status" style="width:128px;"/>
		</div>
	</dd>
</form>