<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	//grade
	var dd_grade = $("#reports_status_week_list_dg_toolbar input[name='grade']").combobox({
		valueField:"value",
		textField:"value",
		data:[{value:"一年级"},{value:"二年级"},{value:"三年级"},{value:"四年级"},{value:"五年级"},{value:"六年级"}],
		onChange:function(newValue,oldValue){
			if(newValue == "") return;
			if(newValue != oldValue && dd_class) {
				dd_class.combobox("reload","${pageContext.request.contextPath}/settings/classes!all.action");
			}
		}
	});
	//class
	var dd_class = $("#reports_status_week_list_dg_toolbar input[name=classId]").combobox({
		url:"${pageContext.request.contextPath}/settings/classes!all.action",
		valueField:"id",
		textField:"name",
		onBeforeLoad:function(param){
			param.grade = dd_grade.combobox("getValue");
		}
	});
	//start_date
	var dd_start = $("#reports_status_week_list_dg_toolbar input[name='start']").datebox({
		required:true
	});
	//end_date
	var dd_end = $("#reports_status_week_list_dg_toolbar input[name='end']").datebox({
		required:true
	});
	//load week
	$.ajax({
		url:"${pageContext.request.contextPath}/reports/report!week.action",
		type:"POST",
		data:{date:"<%out.print(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));%>"},
		dataType:"json",
		success:function(data,textStatus){
			if(data && $.isArray(data) && data.length > 1){
				dd_start.datebox("setValue",data[0]);
				dd_end.datebox("setValue",data[1]);
			}
		}
	});
	//list
	var dg = $("#reports_status_week_list_dg").datagrid({
		url:"${pageContext.request.contextPath}/reports/report!statusReport.action",
		fit:true,
		fitColumns:true,
		rownumbers:true,
		pagination:false,
		pagePosition:"bottom",
		pageSize:20,
		pageList:[20,30,40],
		border:true,
		striped:true,
		idField:"status",
		sortName:"status",
		sortOrder:"desc",
		columns:[[{
			title:"考勤原因",
			field:"status",
			align:"center",
			formatter:function(value,row,index){
				if(value == 0) return "";
				if(value == 1) return "迟到";
				if(value == 2) return "病假";
				if(value == 3) return "事假";
				if(value == 4) return "其他";
				return value;
			},
			width:30
		},{
			title:"缺勤人数",
			field:"count",
			width:20,
			align:"right"
		},{
			title:"所占比例",
			field:"share",
			width:25,
			align:"right"
		}]],
		toolbar:"#reports_status_week_list_dg_toolbar"
	});
	//search
	reports_status_week_list_dg_search = function(){
		dg.datagrid("load",{
			grade:dd_grade.combobox("getValue"),
			classId:dd_class.combobox("getValue"),
			studentName:$("#reports_status_week_list_dg_toolbar input[name='studentName']").val(),
			start:dd_start.datebox("getValue"),
			end:dd_end.datebox("getValue")
		});
	};
});
//-->
</script>
<table id="reports_status_week_list_dg"></table>
<div id="reports_status_week_list_dg_toolbar" style="height:auto;">
	<span>年级：</span>
	<input name="grade" type="text" style="width:80px;"/>
	
	<span>班级：</span>
	<input name="classId" type="text" style="width:168px;"/>
	
	<span>学生姓名：</span>
	<input name="studentName" type="text" style="width:168px;"/>
	
	<span>开始日期：</span>
	<input name="start" type="text" style="width:90px;"/>
	
	<span>结束日期：</span>
	<input name="end" type="text" style="width:90px;"/>
	
	<a href="#" class="easyui-linkbutton" onclick="reports_status_week_list_dg_search()" data-options="iconCls:'icon-search',plain:true">查询</a>
</div>