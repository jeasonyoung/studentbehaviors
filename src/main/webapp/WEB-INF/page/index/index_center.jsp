<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	//date
	var dd_date = $("#index_center_dg_toolbar input[name='date']").datebox({
		required:true
	});
	dd_date.datebox("setValue","<%out.print(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));%>");
	//query
	var query = function(){
		return {
			date:dd_date.combobox("getValue"),
			segment:$("#index_center_dg_toolbar input[name='segment']:checked").val()	
		}
	};
	//dg
	var dg = $("#index_center_dg").datagrid({
		url:"${pageContext.request.contextPath}/index/report!attendanceRecords.action",
		queryParams:query(),
		fit:true,
		fitColumns:true,
		rownumbers:true,
		pagination:false,
		pagePosition:"bottom",
		pageSize:20,
		pageList:[20,30,40],
		border:true,
		striped:true,
		idField:"className",
		sortName:"className",
		sortOrder:"asc",
		columns:[[{
			title:"星期",
			field:"week",
			width:10,
			align:"center"
		},{
			title:"所属年级",
			field:"grade",
			width:10
		},{
			title:"班级",
			field:"className",
			width:20
		},{
			title:"应到人数",
			field:"total",
			width:10,
			align:"right"
		},{
			title:"考勤段",
			field:"segment",
			width:10,
			formatter:function(value,row,index){
				if(value == 0) return "";
				if(value == 1) return "晨检";
				if(value == 2) return "午检";
				return value;
			},
			align:"center"
		},{
			title:"考勤情况",
			field:"status",
			width:10,
			align:"center"
		},{
			title:"迟到",
			field:"late",
			width:10,
			align:"right"
		},{
			title:"病假",
			field:"disease",
			width:10,
			align:"right"
		},{
			title:"事假",
			field:"leave",
			width:10,
			align:"right"
		},{
			title:"其他",
			field:"other",
			width:10,
			align:"right"
		}]],
		toolbar:"#index_center_dg_toolbar"
	});
	//search
	index_center_dg_search = function(){
		var q = query();
		dg.datagrid("load",q);
	};
});
//-->
</script>
<table id="index_center_dg"></table>
<div id="index_center_dg_toolbar" style="height:auto;">
	<span>日期：</span>
	<input name="date" type="text" style="width:90px;"/>
	
	<input name="segment" type="radio" value="1" checked="checked"/><span>晨检</span>
	<input name="segment" type="radio" value="2"/><span>午检</span>
	
	<a href="#" class="easyui-linkbutton" onclick="index_center_dg_search()" data-options="iconCls:'icon-search',plain:true">查询</a>
</div>