<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	//grade
	var dd_grade = $("#reports_class_daily_list_dg_toolbar input[name='grade']").combobox({
		required:true,
		valueField:"value",
		textField:"value",
		data:[{value:"一年级",selected:true},{value:"二年级"},{value:"三年级"},{value:"四年级"},{value:"五年级"},{value:"六年级"}]
	});
	//date
	var dd_date = $("#reports_class_daily_list_dg_toolbar input[name='date']").datebox({
		required:true
	});
	dd_date.datebox("setValue","<%out.print(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));%>");
	//query
	var query = function(){
		return {
			grade:dd_grade.combobox("getValue"),
			date:dd_date.datebox("getValue")	
		}
	};
	//list
	var dg = $("#reports_class_daily_list_dg").datagrid({
		url:"${pageContext.request.contextPath}/reports/report!classDailyReport.action",
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
		idField:"classId",
		sortName:"className",
		sortOrder:"desc",
		columns:[[{
			title:"班级",
			field:"className",
			width:20
		},{
			title:"出勤率",
			field:"attendance",
			width:10,
			align:"right"
		},{
			title:"应到人数",
			field:"total",
			width:10,
			align:"right"
		},{
			title:"缺勤人数",
			field:"statistics",
			width:10,
			align:"right",
			formatter: function(value,row,index){
				if(row.statistics)
					return row.statistics.total;
				return value;
			}
		},{
			title:"迟到",
			field:"statistics.abns",
			width:10,
			align:"right",
			formatter: function(value,row,index){
				var abns = row.statistics.abns;
				if($.isArray(abns) && abns.length > 0){
					var result = null;
					$.each(abns,function(i,n){
						if(n.status == 1){
							result = n.count;
							return result;
						}
					});
					if(result)return result;
				}
				return 0;
			}
		},{
			title:"病假",
			field:"statistics.abns.status",
			width:10,
			align:"right",
			formatter: function(value,row,index){
				var abns = row.statistics.abns;
				if($.isArray(abns) && abns.length > 0){
					var result = null;
					$.each(abns,function(i,n){
						if(n.status == 2){
							result = n.count;
							return result;
						}
					});
					if(result)return result;
				}
				return 0;
			}
		},{
			title:"事假",
			field:"statistics.abns.count",
			width:10,
			align:"right",
			formatter: function(value,row,index){
				var abns = row.statistics.abns;
				if($.isArray(abns) && abns.length > 0){
					var result = null;
					$.each(abns,function(i,n){
						if(n.status == 3){
							result = n.count;
							return result;
						}
					});
					if(result)return result;
				}
				return 0;
			}
		},{
			title:"其他",
			field:"classId",
			width:10,
			align:"right",
			formatter: function(value,row,index){
				var abns = row.statistics.abns;
				if($.isArray(abns) && abns.length > 0){
					var result = null;
					$.each(abns,function(i,n){
						if(n.status == 4){
							result = n.count;
							return result;
						}
					});
					if(result)return result;
				}
				return 0;
			}
		}]],
		toolbar:"#reports_class_daily_list_dg_toolbar"
	});
	//search
	reports_class_daily_list_dg_search = function(){
		var q = query();
		dg.datagrid("load",q);
	};
});
//-->
</script>
<table id="reports_class_daily_list_dg"></table>
<div id="reports_class_daily_list_dg_toolbar" style="height:auto;">
	<span>年级：</span>
	<input name="grade" type="text" style="width:80px;"/>
	<span>日期：</span>
	<input name="date" type="text" style="width:90px;"/>
	<a href="#" class="easyui-linkbutton" onclick="reports_class_daily_list_dg_search()" data-options="iconCls:'icon-search',plain:true">查询</a>
</div>