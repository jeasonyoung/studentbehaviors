<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	//grade
	var dd_grade = $("#reports_student_abn_list_dg_toolbar input[name='grade']").combobox({
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
	var dd_class = $("#reports_student_abn_list_dg_toolbar input[name=classId]").combobox({
		url:"${pageContext.request.contextPath}/settings/classes!all.action",
		valueField:"id",
		textField:"name",
		onBeforeLoad:function(param){
			param.grade = dd_grade.combobox("getValue");
		}
	});
	//list
	var dg = $("#reports_student_abn_list_dg").datagrid({
		url:"${pageContext.request.contextPath}/reports/stureport!students.action",
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
		sortName:"date",
		sortOrder:"desc",
		columns:[[{
			title:"所属年级",
			field:"grade",
			sortable:true,
			width:20
		},{
			title:"所属班级",
			field:"className",
			sortable:true,
			width:20
		},{
			title:"学生姓名",
			field:"studentName",
			sortable:true,
			width:20
		},{
			title:"考勤段",
			field:"segment",
			align:"center",
			sortable:true,
			formatter:function(value,row,index){
				if(value == 0) return "";
				if(value == 1) return "晨检";
				if(value == 2) return "午检";
				return value;
			},
			width:10
		},{
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
			sortable:true,
			width:15
		},{
			title:"考勤备注",
			field:"remarks",
			width:35
		},{
			title:"登记者",
			field:"createUserName",
			width:10
		}]],
		toolbar:"#reports_student_abn_list_dg_toolbar"
	});
	//search
	reports_student_abn_list_dg_search = function(){
		dg.datagrid("load",{
			grade:dd_grade.combobox("getValue"),
			classId:dd_class.combobox("getValue"),
			studentName:$("#reports_student_abn_list_dg_toolbar input[name='studentName']").val(),
			date:$("#reports_student_abn_list_dg_toolbar input[name='date']").val()
		});
	};
});
//-->
</script>
<table id="reports_student_abn_list_dg"></table>
<div id="reports_student_abn_list_dg_toolbar" style="height:auto;">
	<span>日期：</span>
	<input name="date" type="text" class="easyui-datebox" style="width:128px;"/>

	<span>年级：</span>
	<input name="grade" type="text" style="width:80px;"/>
	
	<span>班级：</span>
	<input name="classId" type="text" style="width:168px;"/>
	
	<span>学生姓名：</span>
	<input name="studentName" type="text" style="width:168px;"/>
	
	<a href="#" class="easyui-linkbutton" onclick="reports_student_abn_list_dg_search()" data-options="iconCls:'icon-search',plain:true">查询</a>
</div>