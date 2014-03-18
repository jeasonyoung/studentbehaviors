<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	//list
	var dg = $("#reports_class_daily_list_dg").datagrid({
		url:"${pageContext.request.contextPath}/reports/report!classDailyReport.action",
		fit:true,
		fitColumns:true,
		rownumbers:true,
		pagination:false,
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
		}]],
		toolbar:"#reports_class_daily_list_dg_toolbar"
	});
});
//-->
</script>
<table id="reports_class_daily_list_dg"></table>
<div id="reports_class_daily_list_dg_toolbar" style="height:auto;">
	<span>年级：</span>
	<input name="grade" type="text" style="width:168px;"/>
	<span>日期：</span>
	<input name="date" type="text" class="easyui-datebox" style="width:128px;"/>
	<a href="#" class="easyui-linkbutton" onclick="alert('x')" data-options="iconCls:'icon-search',plain:true">查询</a>
</div>