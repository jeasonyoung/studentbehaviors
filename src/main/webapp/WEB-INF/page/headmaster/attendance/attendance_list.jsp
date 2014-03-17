<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	//list
	var dg = $("#headmaster_attendancelist_dg").datagrid({
		url:"${pageContext.request.contextPath}/headmaster/abnattendance!datagrid.action",
		fit:true,
		fitColumns:true,
		rownumbers:true,
		pagination:true,
		pagePosition:"bottom",
		pageSize:20,
		pageList:[20,30,40],
		border:true,
		striped:true,
		idField:"id",
		sortName:"createTime",
		sortOrder:"desc",
		columns:[[{
			title:"所属班级",
			field:"className",
			width:20,
			sortable:true
		},{
			title:"学生姓名",
			field:"studentName",
			width:20,
			sortable:true
		},{
			title:"异常日期",
			field:"date",
			width:20,
			align:"center",
			sortable:true
		},{
			title:"考勤段",
			field:"segment",
			width:10,
			align:"center",
			formatter:function(value,row,index){
				if(value == 0) return "";
				if(value == 1) return "晨检";
				if(value == 2) return "午检";
				return value;
			},
			sortable:true
		},{
			title:"考勤原因",
			field:"status",
			width:15,
			align:"center",
			formatter:function(value,row,index){
				if(value == 0) return "";
				if(value == 1) return "迟到";
				if(value == 2) return "病假";
				if(value == 3) return "事假";
				if(value == 4) return "其他";
				return value;
			},
			styler:function(value,row,index){
				if(value == 0) return "background-color:#e7f7f7;";
				if(value == 1) return "background-color:#31b573;color:#7e6338;";
				if(value == 2) return "background-color:#52e2cc;color:#7e6338;";
				if(value == 3) return "background-color:#edf8a1;color:#7e6338;";
				if(value == 4) return "background-color:#f0ac41;color:#7e6338;";
				return "background-color:#ffee00;color:red;";
			},
			sortable:true
		},{
			title:"备注",
			field:"remarks",
			width:128,
			sortable:true
		},{
			title:"登记者",
			field:"createUserName",
			width:20,
			sortable:true
		},{
			title:"登记时间",
			field:"createTime",
			width:20,
			align:"center",
			formatter:function(value,row,index){
				if(value.length >= 10){
					return value.substring(0,10);
				}
				return value;
			},
			sortable:true
		}]],
		toolbar:"#headmaster_attendancelist_dg_toolbar"
	});
	//class
	var ddl_class = $("#headmaster_attendancelist_dg_toolbar input[name=classId]").combobox({
		url:"${pageContext.request.contextPath}/settings/headmaster!classes.action",
		valueField:"id",
		textField:"name"
	}); 
	//search
	headmaster_attendancelist_dg_search = function(){
		dg.datagrid("load",{
			classId:ddl_class.combobox("getValue"),
			date:$("#headmaster_attendancelist_dg_toolbar input[name=date]").val(),
			segment:$("#headmaster_attendancelist_dg_toolbar input[name=segment]:checked").val(),
			studentName:$("#headmaster_attendancelist_dg_toolbar input[name=studentName]").val()
		});
	};
});
//-->
</script>
<table id="headmaster_attendancelist_dg"></table>
<div id="headmaster_attendancelist_dg_toolbar" style="height:auto;">
	<span>日期：</span>
	<input name="date" type="text" class="easyui-datebox" style="width:128px;"/>
	
	<span>班级：</span>
	<input name="classId" type="text" style="width:168px;"/>
	
	<span>学生姓名：</span>
	<input name="studentName" type="text" style="width:168px;"/>
	
	<input name="segment" type="radio" value="1"/><span>晨检</span>
	<input name="segment" type="radio" value="2"/><span>午检</span>
	
	<a href="#" class="easyui-linkbutton" onclick="headmaster_attendancelist_dg_search()" data-options="iconCls:'icon-search',plain:true">查询</a>
</div>