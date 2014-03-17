<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	//list
	var dg = $("#settings_teachers_dg").datagrid({
		url:"${pageContext.request.contextPath}/settings/teachers!datagrid.action",
		fit:true,
		fitColumns:true,
		pagination:true,
		pagePosition:"bottom",
		pageSize:20,
		pageList:[20,30,40],
		border:true,
		striped:true,
		idField:"id",
		sortName:"account",
		sortOrder:"asc",
		columns:[[{
			field:"id",
			checkbox:true
		},{
			title:"城域网帐号",
			field:"account",
			width:60,
			align:"left",
			sortable:true
		},{
			title:"教师姓名",
			field:"name",
			width:50,
			sortable:true
		},{
			title:"性别",
			field:"sex",
			width:30,
			align:"center",
			sortable:true
		},{
			title:"职称",
			field:"titles",
			width:70,
			sortable:true
		},{
			title:"职务类别",
			field:"jobCategory",
			width:100,
			sortable:true
		},{
			title:"电话",
			field:"phone",
			width:120,
			sortable:true
		}]],
		toolbar:"#settings_teachers_dg_toolbar",
		onDblClickRow:function(rowIndex,rowData){
			edit_window("编辑教师",rowIndex,rowData);
		}
	});
	//edit
	function edit_window(title,index,row){
		var d = $("<div/>").dialog({
			title:title,
			width:520,
			height:170,
			href:"${pageContext.request.contextPath}/settings/teachers!edit.action",
			modal:true,
			buttons:[{
				text:"保存",
				iconCls:"icon-save",
				handler:function(){
					$.messager.progress();
					$("#settings_teachersedit_form").form("submit",{
						url:"${pageContext.request.contextPath}/settings/teachers!update.action",
						onSubmit: function(){
							var isValid = $(this).form("validate");
							if (!isValid)$.messager.progress("close");
							return isValid;
						},
						success:function(data){
							$.messager.progress("close");
							var data = jQuery.parseJSON(data);
							if(data.success){
								dg.datagrid(row ? "updateRow" : "insertRow",{
									index:index,
									row:data.data
								});
								d.dialog("close");
							}else{
								$.messager.show({
									title:"保存异常",
									msg:data.msg
								});
							}
						}
					});
				}
			},{
				text:"关闭",
				iconCls:"icon-cancel",
				handler:function(){
					d.dialog("close");
				}
			}],
			onClose:function(){
				$(this).dialog("destroy");
			},
			onLoad:function(){
				if(row) $("#settings_teachersedit_form").form("load",row);
			}
		});
	};
	//search
	settings_teachers_dg_search = function(){
		dg.datagrid("load",{
			account:$("#settings_teachers_dg_toolbar input[type=text]").val()
		});
	};
	//import
	settings_teachers_dg_import = function(){
		$.messager.confirm("确认","您是否确认从城域网中导入教师数据?",function(r){
			if(!r)return;
			$.messager.progress();
			$.ajax({
				url:"${pageContext.request.contextPath}/settings/sync!teachers.action",
				type:"GET",
				dataType:"json",
				success:function(data,textStatus){
					$.messager.progress("close");
					$.messager.alert("提示","导入数据完成！");
					if(data.success){
						dg.datagrid("load");
						dg.datagrid("unselectAll");
					}else{
						$.messager.show({
							title:"提示",
							msg:data.msg
						});
					}
				}
			});
		});
	};
	//add
	settings_teachers_dg_add = function(){
		edit_window("新增教师",0,null);
	};
	//delete
	settings_teachers_dg_delete = function(){
		var rows = dg.datagrid("getChecked");
		if(rows && rows.length > 0){
			$.messager.confirm("确认","您是否确认删除选中的数据?",function(r){
				if(!r)return;
				var ids = [];
				for(var i = 0; i < rows.length; i++){
					ids.push(rows[i].id + "#" + rows[i].role);
				}
				$.ajax({
					url:"${pageContext.request.contextPath}/settings/teachers!delete.action",
					type:"POST",
					data:{
						id:ids.join("|")
					},
					dataType:"json",
					success:function(data,textStatus){
						if(data.success){
							dg.datagrid("load");
							dg.datagrid("unselectAll");
						}else{
							$.messager.show({
								title:"提示",
								msg:data.msg
							});
						}
					}
				});
			});
		}else{
			$.messager.alert("提示","未选中须删除的数据！");
		}
	};
});
//-->
</script>
<table id="settings_teachers_dg"></table>
<div id="settings_teachers_dg_toolbar" style="padding:2px;height:auto;">
	<a href="#" class="easyui-linkbutton" onclick="settings_teachers_dg_import()" data-options="iconCls:'icon-add',plain:true" style="float:left;">导入教师</a>
	<a href="#" class="easyui-linkbutton" onclick="settings_teachers_dg_add()" data-options="iconCls:'icon-add',plain:true" style="float:left;">新增</a>
	<span>|</span>
	<a href="#" class="easyui-linkbutton" onclick="settings_teachers_dg_delete()" data-options="iconCls:'icon-remove',plain:true">删除</a>
	<span style="margin-left:20px;">教师姓名:</span>
	<input type="text" style="width:268px;"/>
	<a href="#" class="easyui-linkbutton" onclick="settings_teachers_dg_search()" data-options="iconCls:'icon-search',plain:true">查询</a>
</div>