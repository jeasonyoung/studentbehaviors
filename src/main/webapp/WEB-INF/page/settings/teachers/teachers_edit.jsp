<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="settings_teachersedit_form" method="POST" style="padding:10px;">
	<div style="float:left;margin-top:2px;width:450px;">
		<div style="float:left;">
			<span style="float:left;width:65px;text-align:right;padding-top:2px;">教师姓名：</span>
			<input type="text" name="name" class="easyui-validatebox" data-options="required:true" style="width:128px;"/>
			<input type="hidden" name="id"/>
		</div>
		<div style="float:left;margin-left:35px;">
			<span style="float:left;width:75px;text-align:right;padding-top:2px;">城域网帐号：</span>
			<input type="text" name="account" class="easyui-validatebox" data-options="required:true" style="width:128px;"/>
		</div>
	</div>
	<div style="float:left;margin-top:2px;width:450px;">
		<div style="float:left;">
			<span style="float:left;width:65px;text-align:right;padding-top:2px;">性别：</span>
			<input type="text" name="sex" style="width:128px;"/>
		</div>
		<div style="float:left;margin-left:35px;">
			<span style="float:left;width:75px;text-align:right;padding-top:2px;">电话：</span>
			<input type="text" name="phone" style="width:128px;"/>
		</div>
	</div>
	<div style="float:left;margin-top:2px;width:450px;">
		<div style="float:left;">
			<span style="float:left;width:65px;text-align:right;padding-top:2px;">职称：</span>
			<input type="text" name="titles" style="width:128px;"/>
		</div>
		<div style="float:left;margin-left:35px;">
			<span style="float:left;width:75px;text-align:right;padding-top:2px;">职务类别：</span>
			<input type="text" name="jobCategory" style="width:128px;"/>
		</div>
	</div>
</form>