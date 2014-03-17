<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	$("#settings_classesedit_form input[name=status]").combobox({
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
<form id="settings_classesedit_form" method="POST" style="padding:7px;">
	<div style="float:left;margin-top:2px;width:450px;">
		<div style="float:left;">
			<span>班级名称：</span>
			<input type="text" name="name" class="easyui-validatebox" data-options="required:true" style="width:128px;"/>
			<input type="hidden" name="id"/>
		</div>
		<div style="float:left;margin-left:35px;">
			<span>班级代码：</span>
			<input type="text" name="code" class="easyui-validatebox" data-options="required:true" style="width:146px;"/>
		</div>
	</div>
	<div style="float:left;margin-top:2px;width:450px;">
		<div style="float:left;">
			<span>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级：</span>
			<input type="text" name="grade" class="easyui-validatebox" data-options="required:true" style="width:128px;"/>
		</div>
		<div style="float:left;margin-left:35px;">
			<span>培养层次：</span>
			<input type="text" name="level" style="width:128px;"/>
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