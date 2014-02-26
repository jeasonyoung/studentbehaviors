<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="settings_teachersdit_form" class="edit" method="POST">
	<dd style="marign-top:2px;">
		<div style="float:left;">
			<label style="width:88px;">教师名称：</label>
			<input type="hidden" name="id"/>
			<input type="text" name="name" class="easyui-validatebox" data-options="required:true" style="width:128px;"/>
		</div>
		<div style="float:left;margin-left:15px;">
			<label style="width:88px;">城域网帐号：</label>
			<input type="text" name="account" class="easyui-validatebox" data-options="required:true" style="width:128px;"/>
		</div>
	</dd>
	<dd style="marign-top:2px;">
		<div style="float:left;">
			<label style="width:88px;">性别：</label>
			<input type="text" name="sex" class="easyui-validatebox" data-options="required:true" style="width:128px;"/>
		</div>
		<div style="float:left;margin-left:15px;">
			<label style="width:88px;">电话：</label>
			<input type="text" name="phone" style="width:128px;"/>
		</div>
	</dd>
	<dd style="marign-top:2px;">
		<div style="float:left;">
			<label style="width:88px;">职称：</label>
			<input type="text" name="titles" style="width:128px;"/>
		</div>
		<div style="float:left;margin-left:15px;">
			<label style="width:88px;">职务类别：</label>
			<input type="text" name="jobCategory" style="width:128px;"/>
		</div>
	</dd>
</form>