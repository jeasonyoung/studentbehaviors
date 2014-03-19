<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	login_list_dialog_submit = function(){
		$.messager.progress();
		var f = $("#login_list_form").form("submit",{
			url:"${pageContext.request.contextPath}/login/login!login.action",
			onSubmit: function(){
				var isValid = $(this).form("validate");
				if (!isValid)$.messager.progress("close");
				return isValid;
			},
			success:function(data){
				$.messager.progress("close");
				var data = jQuery.parseJSON(data); 
				if(!data.success){
					f.form("reset");
					$.messager.alert("用户登录失败",data.msg);
				}else{
					window.location.href = "index.action"
				}
			}
		});
	};
});
//-->
</script>
<div data-options="region:'center'">
	 <div class="easyui-dialog" title="用户登录" style="width:300px;height:150px;" 
	 		data-options="resizable:false,closable:false,modal:true,buttons:[{text:'登录',iconCls:'icon-save',handler:function(){login_list_dialog_submit();}}]">
	 	<form id="login_list_form" method="POST" style="padding:5px;">
	 		<div style="float:left;margin-top:7px;">
	 			<span style="float:left;width:65px;text-align:right;padding-top:2px;">用户账号：</span>
	 			<input type="text" name="account" class="easyui-validatebox" data-options="required:true" style="width:168px;"/>
	 		</div>
	 		<div style="float:left;margin-top:5px;">
	 			<span style="float:left;width:65px;text-align:right;padding-top:2px;">用户密码：</span>
	 			<input type="password" name="password" class="easyui-validatebox" data-options="required:true" style="width:168px;"/>
	 		</div>
	 	</form>
	 </div>
</div>