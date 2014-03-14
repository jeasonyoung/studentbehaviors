<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	//list
	var dg = $("#settings_classes_dg").datagrid({
		url:"${pageContext.request.contextPath}/settings/classes!datagrid.action",
		fit:true,
		fitColumns:true,
		pagination:true,
		pagePosition:"bottom",
		pageSize:20,
		pageList:[20,30,40],
		border:true,
		striped:true,
		idField:"id",
		sortName:"joinYear",
		sortOrder:"desc",
		columns:[[{
			field:"id",
			checkbox:true
		},{
			title:"所属年级",
			field:"grade",
			width:30,
			sortable:true
		},{
			title:"班级代码",
			field:"code",
			width:50,
			align:"left",
			sortable:true
		},{
			title:"班级名称",
			field:"name",
			width:80,
			sortable:true
		},{
			title:"培养层次",
			field:"level",
			width:30,
			align:"center",
			sortable:true
		},{
			title:"入学年度",
			field:"joinYear",
			width:20,
			align:"center",
			sortable:true
		},{
			title:"状态",
			field:"status",
			width:20,
			align:"center",
			formatter:function(value,row,index){
				if(value == 0) return "不在校";
				if(value == 1) return "在校";
				return value;
			},
			sortable:true
		}]],
		toolbar:"#settings_classes_dg_toolbar",
		onDblClickRow:function(rowIndex,rowData){
			edit_window("编辑班级",rowIndex,rowData);
		}
	});
	//edit
	function edit_window(title,index,row){
		var d = $("<div/>").dialog({
			title:title,
			width:520,
			height:170,
			href:"${pageContext.request.contextPath}/settings/classes!edit.action",
			modal:true,
			buttons:[{
				text:"保存",
				iconCls:"icon-save",
				handler:function(){
					$.messager.progress();
					$("#settings_classesedit_form").form("submit",{
						url:"${pageContext.request.contextPath}/settings/classes!update.action",
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
				if(row) $("#settings_classesedit_form").form("load",row);
			}
		});
	};
	//import
	settings_classes_dg_import = function(){
		$.messager.confirm("确认","您是否确认从城域网中导入教师数据?",function(r){
			if(!r)return;
			$.messager.progress();
			$.ajax({
				url:"${pageContext.request.contextPath}/settings/sync!classes.action",
				type:"GET",
				dataType:"json",
				success:function(data,textStatus){
					$.messager.progress("close");
					$.messager.alert("提示","导入班级数据完成！");
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
	//import students
	settings_classes_dg_importstudents = function(){
		var rows = dg.datagrid("getChecked");
		if(rows && rows.length > 0){
			$.messager.confirm("确认","您是否确认导入选中班级的学生数据?",function(r){
				if(!r)return;
				var ids = [];
				for(var i = 0; i < rows.length; i++){
					ids.push(rows[i].id);
				}
				$.messager.progress();
				$.ajax({
					url:"${pageContext.request.contextPath}/settings/sync!students.action",
					type:"POST",
					data:{
						classId:ids.join("|")
					},
					dataType:"json",
					success:function(data,textStatus){
						$.messager.progress("close");
						$.messager.alert("提示","导入班级下学生数据完成！");
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
			$.messager.alert("提示","未选中须导入学生的班级！");
		}
	};
	//search
	settings_classes_dg_search = function(){
		dg.datagrid("load",{
			name:$("#settings_classes_dg_toolbar input[name=name]").val(),
			grade:$("#settings_classes_dg_toolbar input[name=grade]").val()
		});
	};
	//add
	settings_classes_dg_add = function(){
		edit_window("新增班级",0,null);
	};
	//delete
	settings_classes_dg_delete = function(){
		var rows = dg.datagrid("getChecked");
		if(rows && rows.length > 0){
			$.messager.confirm("确认","您是否确认删除选中的数据?",function(r){
				if(!r)return;
				var ids = [];
				for(var i = 0; i < rows.length; i++){
					ids.push(rows[i].id);
				}
				$.ajax({
					url:"${pageContext.request.contextPath}/settings/classes!delete.action",
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
<table id="settings_classes_dg"></table>
<div id="settings_classes_dg_toolbar" style="padding:2px;height:auto;">
	<a href="#" class="easyui-linkbutton" onclick="settings_classes_dg_import()" data-options="iconCls:'icon-add',plain:true" style="float:left;">导入班级</a>
	<a href="#" class="easyui-linkbutton" onclick="settings_classes_dg_importstudents()" data-options="iconCls:'icon-add',plain:true" style="float:left;">导入学生</a>
	<a href="#" class="easyui-linkbutton" onclick="settings_classes_dg_add()" data-options="iconCls:'icon-add',plain:true" style="float:left;">新增</a>
	<span>|</span>
	<a href="#" class="easyui-linkbutton" onclick="settings_classes_dg_delete()" data-options="iconCls:'icon-remove',plain:true">删除</a>
	<span style="margin-left:20px;">所属年级:</span>
	<input type="text" name="grade"  style="width:128px;"/>
	<span style="margin-left:20px;">班级名称:</span>
	<input type="text" name="name"  style="width:268px;"/>
	<a href="#" class="easyui-linkbutton" onclick="settings_classes_dg_search()" data-options="iconCls:'icon-search',plain:true">查询</a>
</div>