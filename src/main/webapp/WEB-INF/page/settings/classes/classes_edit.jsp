<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="settings_classesedit_form" class="edit" method="POST">
	<dd style="marign-top:2px;">
		<div style="float:left;">
			<label style="width:88px;">班级名称：</label>
			<input type="hidden" name="id"/>
			<input type="text" name="name" class="easyui-validatebox" data-options="required:true" style="width:128px;"/>
		</div>
		<div style="float:left;margin-left:15px;">
			<label style="width:88px;">班级代码：</label>
			<input type="text" name="code" class="easyui-validatebox" data-options="required:true" style="width:128px;"/>
		</div>
	</dd>
	<dd style="marign-top:2px;">
		<div style="float:left;">
			<label style="width:88px;">年级：</label>
			<input type="text" name="grade" class="easyui-validatebox" data-options="required:true" style="width:128px;"/>
		</div>
		<div style="float:left;margin-left:15px;">
			<label style="width:88px;">培养层次：</label>
			<input type="text" name="level" style="width:128px;"/>
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