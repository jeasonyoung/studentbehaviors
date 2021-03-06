<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	//class
	var ddl_class = $("#headmaster_attendancerecord_dg_toolbar input[name=classId]").combobox({
		url:"${pageContext.request.contextPath}/settings/headmaster!classes.action",
		required:true,
		valueField:"id",
		textField:"name",
		onChange:function(newValue,oldValue){
			if(newValue == "") return;
			if(newValue != oldValue && query().date != "") {
				class_students(newValue);
				headmaster_attendancerecord_dg_search();
			}
		}
	});
	//date
	var record_date = $("#headmaster_attendancerecord_dg_toolbar input[name=date]").datebox({
		required:true,
		onChange:function(newValue,oldValue){
			if(oldValue == "") return;
			if(newValue != oldValue && query().classId != "") {
				 headmaster_attendancerecord_dg_search();
			}
		}
	});
	record_date.datebox("setValue","<%out.print(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));%>");
	//segment
	$("#headmaster_attendancerecord_dg_toolbar input[name=segment]").change(function(){
		if(query().classId != "") {
			 headmaster_attendancerecord_dg_search();
		}
	});
	//query
	function query(){
		return {
			classId:ddl_class.combobox("getValue"),
			date:record_date.datebox("getValue"),
			segment:$("#headmaster_attendancerecord_dg_toolbar input[name=segment]:checked").val()
		};
	};
	//list
	var dg = $("#headmaster_attendancerecord_dg").datagrid({
		url:"${pageContext.request.contextPath}/headmaster/attendance!datagrid.action",
		queryParams:query(),
		fit:true,
		fitColumns:true,
		rownumbers:true,
		singleSelect:true,
		border:true,
		striped:true,
		idField:"studentId",
		sortName:"studentName",
		sortOrder:"desc",
		columns:[[{
			title:"学生姓名",
			field:"studentName",
			width:20,
			sortable:true
		},{
			title:"性别",
			field:"gender",
			width:10,
			align:"center",
			sortable:true
		},{
			title:"考勤原因",
			field:"status",
			width:20,
			align:"center",
			sortable:true,
			formatter:function(value,row,index){
				if(value == 0) return "全勤";
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
			editor:{
				type:"combobox",
				options:{
					required:true,
					valueField:"value",
					textField:"name",
					data:[{
						value:0,
						name:"全勤"
					},{
						value:1,
						name:"迟到"
					},{
						value:2,
						name:"病假"
					},{
						value:3,
						name:"事假"
					},{
						value:4,
						name:"其他"
					}]
				}
			}
		},{
			title:"备注",
			field:"remarks",
			width:100,
			align:"left",
			sortable:true,
			editor:"textarea"
		}]],
		toolbar:"#headmaster_attendancerecord_dg_toolbar",
		onClickRow:function(index,row){
			if(editIndex != index){
				if(endEditting()){
					dg.datagrid("selectRow",index)
					  .datagrid("beginEdit",index);
					editIndex = index;
				}else{
					dg.datagrid("selectRow",editIndex);
				}
			}
		}
	});
	//edit_row
	var editIndex = undefined;
	function endEditting(){
		if(editIndex == undefined) return true;
		if(dg.datagrid("validateRow",editIndex)){
			var ed_status = dg.datagrid("getEditor", {index:editIndex,field:"status"}),
				ed_remarks = dg.datagrid("getEditor", {index:editIndex,field:"remarks"});
			if(ed_status && ed_remarks){
				var status_value = $(ed_status.target).combobox("getValue"),
					remarks_value = $(ed_remarks.target).val();
				var row = dg.datagrid("getRows")[editIndex];
				if(row && (row.status != status_value || row.remarks != remarks_value)){//ajax update
					row["status"] = status_value;
					row["remarks"] = remarks_value;
					if(row.id == undefined)row["id"] = " ";
					update_student_status(row,editIndex);
				}
			}
			dg.datagrid("endEdit",editIndex);
			editIndex = undefined;
			return true;
		}
		return false;
	}
	//update status
	function update_student_status(row, index){
		if(row.studentId){
			$.ajax({
				url:"${pageContext.request.contextPath}/headmaster/attendance!update.action",
				type:"POST",
				data:{
					id:row.id,
					classId:row.classId,
					studentId:row.studentId,
					date:record_date.datebox("getValue"),
					segment:$("#headmaster_attendancerecord_dg_toolbar input[name=segment]:checked").val(),
					status:row.status,
					remarks:row.remarks
				},
				dataType:"json",
				success:function(data){
					if(data.success){
						var q = query();
						class_students_abn(q.classId,q.date,q.segment);
						dg.datagrid("updateRow",{
							index:index,
							row:data.data
						});
					}
				}
			});
		}
	}
	//calendar
	$("#headmaster_attendancerecord_calendar").calendar({
		onSelect:function(date){
			var m = date.getMonth()+1,d = date.getDate();
			var data = date.getFullYear() + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d);
			record_date.datebox("setValue",data);
		}
	});
	//register
	var reg = $("#headmaster_attendancerecord_dg_register");
	if(reg){
		reg.hide();
		reg.click(function(){
			var q = query();
			if(q == null || q.classId == "" || q.date == "" || q.segment == ""){
				reg.hide();
				return;
			}
			$.messager.confirm("确认","您是否确认设置全班全勤?",function(r){
				if(!r)return;
				$.ajax({
					url:"${pageContext.request.contextPath}/headmaster/attendance!register.action",
					type:"POST",
					data:{
						classId:q.classId,
						date:q.date,
						segment:q.segment
					},
					dataType:"json",
					success:function(data){
						if(data){
							reg.hide();
						}else{
							reg.show();
						}
					}
				});
			});
		});
	}
	//load_register
	function load_register(q){
		if(q == null || q.classId == "" || q.date == "" || q.segment == ""){
			reg.hide();
			return;
		}
		$.ajax({
			url:"${pageContext.request.contextPath}/headmaster/attendance!loadregister.action",
			type:"POST",
			data:{
				classId:q.classId,
				date:q.date,
				segment:q.segment
			},
			dataType:"json",
			success:function(data){
				if((typeof data.id) == "undefined"){
					reg.show();
				}else{
					reg.hide();
				}
			}
		});
	};
	//search
	headmaster_attendancerecord_dg_search = function(){
		var q = query();
		dg.datagrid("load",q);
		class_students_abn(q.classId,q.date,q.segment);
		load_register(q);
	};
	//class_students
	var class_students_count = 0;
	function class_students(classId){
		$.ajax({
			url:"${pageContext.request.contextPath}/settings/students!number.action",
			type:"POST",
			data:{
				classId:classId
			},
			dataType:"json",
			success:function(data){
				class_students_count = data;
				$("#headmaster_attendancerecord_dg_toolbar_classes").html("应到：["+ class_students_count +"]人");
				$("#headmaster_attendancerecord_dg_toolbar_classes_truth").html("实到：["+ class_students_count +"]人");
				$("#headmaster_attendancerecord_dg_toolbar_classes_abn").html("缺勤：[0]人");
				//$("#headmaster_attendancerecord_dg_toolbar span:hidden").show();
			}
		});
	};
	//class_abn
	function class_students_abn(classId,date,segment){
		$.ajax({
			url:"${pageContext.request.contextPath}/headmaster/abnattendance!total.action",
			type:"POST",
			data:{
				classId:classId,
				date:date,
				segment:segment
			},
			dataType:"json",
			success:function(data){
				//console.info(data);
				if(!data)return;
				$("#headmaster_attendancerecord_dg_toolbar_classes_truth").html("实到：["+ (class_students_count - data.total) +"]人");
				var html = "缺勤：["+ data.total +"]人";
				if(data.abns && $.isArray(data.abns)){
					html += "("
					$.each(data.abns,function(i,n){
						if(i > 0) html += ",";
						if(n.status == 1){
							html += "迟到:" + n.count;
							return;
						}
						if(n.status == 2){
							html += "病假:" + n.count;
							return;
						}
						if(n.status == 3){
							html += "事假:" + n.count;
							return;
						}
						if(n.status == 4){
							html += "其他:" + n.count;
							return;
						}
					});
					html += ")";
				}
				$("#headmaster_attendancerecord_dg_toolbar_classes_abn").html(html);
			}
		});
	};
});
//-->
</script>
<div class="easyui-layout" data-options="fit:true">
	<div class="easyui-layout" data-options="region:'center',border:false">
		<table id="headmaster_attendancerecord_dg"></table>
		<div id="headmaster_attendancerecord_dg_toolbar" style="height:auto;">
			<span>日期：</span>
			<input name="date" type="text" style="width:98px;"/>
		 
			<span>班级：</span>
			<input name="classId" type="text" style="width:168px;"/>
		 
			<input name="segment" type="radio" value="1" checked="checked"/><span>晨检</span>
			<input name="segment" type="radio" value="2"/><span>午检</span>
				 
			<a href="#" class="easyui-linkbutton" style="margin-left:20px;" onclick="headmaster_attendancerecord_dg_search()" data-options="iconCls:'icon-search',plain:true">查询</a> 
			<a href="#"  id="headmaster_attendancerecord_dg_register"  class="easyui-linkbutton" style="margin-left:20px;" data-options="iconCls:'icon-tip',plain:true">设置全班全勤</a> 
			
			<span style="margin-left:2px;border:solid 1px #ccc;display:block;padding:5px;">
				<span id="headmaster_attendancerecord_dg_toolbar_classes"></span>
				<span id="headmaster_attendancerecord_dg_toolbar_classes_truth"></span>
				<span id="headmaster_attendancerecord_dg_toolbar_classes_abn"></span>
			</span>
		</div>
	</div>
	<div data-options="region:'east',width:198" style="padding:10px;">
		<div id="headmaster_attendancerecord_calendar" class="easyui-calendar" style="width:180px;height:180px;"></div>
	</div>
</div>