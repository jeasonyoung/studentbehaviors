<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	//list
	var dg = $("#settings_headmaster_dg").datagrid({
		url:"${pageContext.request.contextPath}/settings/headmaster!datagrid.action",
		fit:true,
		fitColumns:true,
		pagination:true,
		pagePosition:"bottom",
		pageSize:20,
		pageList:[20,30,40],
		border:true,
		striped:true,
		idField:"id",
		sortName:"teacherName",
		sortOrder:"asc",
		columns:[[{
			field:"id",
			checkbox:true
		},{
			title:"班主任老师姓名",
			field:"teacherName",
			width:70,
			align:"left",
			sortable:true
		},{
			title:"所任班级",
			field:"className",
			width:80,
			sortable:true
		},{
			title:"类型",
			field:"type",
			width:40,
			align:"center",
			formatter: function(value,row,index){
				if(value == 1) return "班主任";
				if(value == 2) return "副班主任";
				return value;
			},
			sortable:true
		}]],
		toolbar:"#settings_headmaster_dg_toolbar",
		onDblClickRow:function(rowIndex,rowData){
			edit_window("编辑班主任",rowIndex,rowData);
		}
	});
	//edit
	function edit_window(title,index,row){
		var d = $("<div/>").dialog({
			title:title,
			width:300,
			height:170,
			href:"${pageContext.request.contextPath}/settings/headmaster!edit.action",
			modal:true,
			buttons:[{
				text:"保存",
				iconCls:"icon-save",
				handler:function(){
					$.messager.progress();
					$("#settings_headmasteredit_form").form("submit",{
						url:"${pageContext.request.contextPath}/settings/headmaster!update.action",
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
				if(row) $("#settings_headmasteredit_form").form("load",row);
			}
		});
	};
	//search
	settings_headmaster_dg_search = function(){
		dg.datagrid("load",{
			teacherName:$("#settings_headmaster_dg_toolbar input[name=teacherName]").val(),
			className:$("#settings_headmaster_dg_toolbar input[name=className]").val()
		});
	};
	//add
	settings_headmaster_dg_add = function(){
		edit_window("新增班主任",0,null);
	};
	//delete
	settings_headmaster_dg_delete = function(){
		var rows = dg.datagrid("getChecked");
		if(rows && rows.length > 0){
			$.messager.confirm("确认","您是否确认删除选中的数据?",function(r){
				if(!r)return;
				var ids = [];
				for(var i = 0; i < rows.length; i++){
					ids.push(rows[i].id);
				}
				$.ajax({
					url:"${pageContext.request.contextPath}/settings/headmaster!delete.action",
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
<table id="settings_headmaster_dg"></table>
<div id="settings_headmaster_dg_toolbar" style="padding:2px;height:auto;">
	<a href="#" class="easyui-linkbutton" onclick="settings_headmaster_dg_add()" data-options="iconCls:'icon-add',plain:true" style="float:left;">新增</a>
	<span>|</span>
	<a href="#" class="easyui-linkbutton" onclick="settings_headmaster_dg_delete()" data-options="iconCls:'icon-remove',plain:true">删除</a>
	<span style="margin-left:20px;">教师姓名:</span>
	<input type="text" name="teacherName" style="width:128px;"/>
	<span style="margin-left:20px;">班级名称:</span>
	<input type="text" name="className" style="width:128px;"/>
	<a href="#" class="easyui-linkbutton" onclick="settings_headmaster_dg_search()" data-options="iconCls:'icon-search',plain:true">查询</a>
</div>