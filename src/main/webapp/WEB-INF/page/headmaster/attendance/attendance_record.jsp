<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	//list
	var dg = $("#headmaster_attendance_dg").datagrid({
		url:"",
		fit:true,
		fitColumns:true,
		pagination:true,
		pagePosition:"bottom",
		pageSize:20,
		pageList:[20,30,40],
		border:true,
		striped:true,
		idField:"id",
		//sortName:"joinYear",
		//sortOrder:"desc",
		columns:[[{
			title:"姓名",
			field:"name",
			width:30,
			sortable:true
		},{
			title:"性别",
			field:"sex",
			width:20,
			align:"left",
			sortable:true
		},{
			title:"考勤原因",
			field:"status",
			width:168,
			sortable:true
		},{
			title:"备注",
			field:"remarks",
			width:30,
			align:"left",
			sortable:true
		}]],
		//toolbar:"#headmaster_attendance_dg_toolbar",
		onDblClickRow:function(rowIndex,rowData){
			//edit_window("编辑班级",rowIndex,rowData);
		}
	});
	
	var record_date = $("#headmaster_attendance_dg_toolbar input[name=date]").datebox({
		required:true,
		onChange:function(newValue,oldValue){
			//alert(newValue);
		}
	});
	record_date.datebox("setValue","<%out.print(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));%>");
	//日历
	$("#headmaster_attendance_record_calendar").calendar({
		onSelect:function(date){
			var m = date.getMonth()+1,d = date.getDate();
			var data = date.getFullYear() + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d);
			record_date.datebox("setValue",data);
		}
	});
});
//-->
</script>
<div class="easyui-layout" data-options="fit:true">
	<div class="easyui-layout" data-options="region:'center',border:false">
		<div id="headmaster_attendance_dg_toolbar" data-options="region:'north',border:false,height:70" style="padding:6px;">
			<div style="float:left;">
				<span>日期：</span>
				<input name="date" type="text" style="width:98px;"/>
			</div>
			<div style="float:left;">
				<input name="segment" type="radio" value="1"/><span>晨检</span>
				<input name="segment" type="radio" value="2"/><span>午检</span>
			</div>
		</div>
		<div data-options="region:'center',border:false">
			<table id="headmaster_attendance_dg"></table>
		</div>
	</div>
	<div data-options="region:'east',width:198" style="padding:10px;">
		<div id="headmaster_attendance_record_calendar" class="easyui-calendar" style="width:180px;height:180px;"></div>
	</div>
</div>