<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="mainoffer.inc"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>


<%

String path = request.getContextPath();

	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
	<head>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>
	
		<title>查询结果显示页面</title>
		<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
		<link rel="STYLESHEET" type="text/css"
			href="${ctx}/styles/dhtmlxchart.css" />
		<script type='text/javascript'
			src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
		<script src="${ctx}/scripts/reportForm/codebase/dhtmlxchart.js"
			type="text/javascript"></script>
		<script type="text/javascript"
			src="${ctx}/scripts/reportForm/prototype.js"></script>
		<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
	 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
			type="text/css"> 
			<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
		<link href="${ctx}/styles/validationEngine.jquery.css"
			rel="stylesheet" type="text/css">
		<link rel="STYLESHEET" type="text/css"
			href="${ctx}/styles/dhtmlxchart.css" />
		<script src="${ctx}/scripts/reportForm/codebase/dhtmlxchart.js"
			type="text/javascript"></script>
		<script type='text/javascript'
			src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
		<script type='text/javascript'
			src="${ctx}/scripts/jquery.validationEngine.js"></script>
		<script type='text/javascript'
			src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
		<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
		<script type="text/javascript">
		window.onload=function() {
			var pageoffset = "${pageoffset}";
			var a = "${tempHaving}";
			var arrays = "${arrays}";
			while(arrays.indexOf("%")>-1){
				arrays = arrays.replace("%","％");
			}
			getHTML("tcontent","queryformcount.do?method=selectTable",
				"reportFormId="+encodeURIComponent(${reportFormId})+"&arrays="+encodeURIComponent(arrays)+
				"&tempHaving=${tempHaving}&pageoffset="+encodeURIComponent(${pageoffset}),"post");
		}
		</script>
		<script type="text/javascript">
		function disp(strarr){
		arr=strarr.split(",");
		if(${isshow}=="1"){
			for(i=1;i<arr.length;i++){
			document.getElementById(arr[i]+"_color").color="#07519a";
			document.getElementById(arr[i]+"_display").style.display="none";
			document.getElementById(arr[i]+"_image").background="../images/tiao.gif";
			}
		}
		
		if(arr[0] == "email"){
			document.getElementById("ExportDiv").style.display = "";
		}else{
			document.getElementById("ExportDiv").style.display = "none";
		}

		obj_color=document.getElementById(arr[0]+"_color").color="#cc6633";
		obj_display=document.getElementById(arr[0]+"_display").style.display="inline";
		document.getElementById(arr[0]+"_image").background="../images/tiao1.gif";
		if(arr[0]=='oa'){
			pie();
		}
		if(arr[0]=='column'){
			bar();
		}
		if(arr[0]=='poly'){
			line();
		}
		
	  }
	  function bar(){
	  	var barChart2 =  new dhtmlXChart({
			view:"bar",
			container:"chart2",
			value:"#y#",
			label:"#y#",
			color:"#45abf5",
			gradient:true,
			border:false,
			width:25,
			tooltip: {
	            template: "#y#"
	        },
	        xAxis: {
	            title: "${coordx}",
	            template: "#x#"
	        },
	        yAxis: {
	            start: 0,
	            end: ${max},
	            step: ${step},
	            title: "${coordy}"
	        }
		});
		barChart2.parse(${jsonStr},"json");
	  }
		function pie(){
		   var chart = new dhtmlXChart({
		        view: "pie3D",
		        container: "chart",
		        value: "#y#",
		        label: function(obj) {
		            var sum = chart.sum("#y#")
		            return obj.x + " (" + Math.round(parseFloat(obj.y) / sum * 100) + "%)"
		        },
		        color: "#color#",
		        radius: 110
		    });
		    chart.parse(${jsonPie}, "json");
		}
		function line(){
			var barChart2 =  new dhtmlXChart({
			view:"line",
			container:"chartline",
			value:"#y#",
			
			tooltip: {
	            template: "#y#"
	        },
	        item: {
            borderColor: "#3399ff",
            color: "#ffffff"
	        },
	        line: {
	            color: "#3399ff",
	            width: 3
	        },
	        xAxis: {
	            title: "${coordx}",
	            template: "#x#"
	        },
	        yAxis: {
	            start: 0,
	            end: ${max},
	            step: ${step},
	            title: "${coordy}"
	        }
		});
		barChart2.parse(${jsonline},"json");
		}
		/*function Export(obj){
			$('#geshi_d').val('');
			$('#reportFormId_d').val('');
			$('#picpath_d').val('');
			
			//设置报表格式
			var geshi = "";
	  		var reportFormId = "${reportFormId}";
	  		var picpath = "";
	  		var picpaths = document.getElementsByName("picName");
	  		if(picpaths != null){
	  			for(var i=0;i<picpaths.length;i++){
	  				if(picpaths[i].value != ""){
	  					picpath += picpaths[i].value+",";
	  				}
	  			}
	  		}
	  		//图片路径
	  		if(picpath != ""){
				picpath = picpath.substring(0,picpath.length-1);
	  		}
	  		
	  		if(obj.value.indexOf("TXT")!= -1){
	  			$('#geshi_d').val("txt");
	  			$('#reportFormId_d').val(reportFormId);
	  			$('#picpath_d').val(picpath);
	  			$('#dialog-reportExport').dialog("open");
	  		}else if(obj.value.indexOf("WORD")!= -1){
	  			$('#geshi_d').val("doc");
	  			$('#reportFormId_d').val(reportFormId);
	  			$('#picpath_d').val(picpath);
	  			$('#dialog-reportExport').dialog("open");
	  		}else if(obj.value.indexOf("HTML")!= -1){
	  			$('#geshi_d').val("html");
	  			$('#reportFormId_d').val(reportFormId);
	  			$('#picpath_d').val(picpath);
	  			$('#dialog-reportExport').dialog("open");
	  		}else if(obj.value.indexOf("PDF")!= -1){
	  			$('#geshi_d').val("pdf");
	  			$('#reportFormId_d').val(reportFormId);
	  			$('#picpath_d').val(picpath);
	  			$('#dialog-reportExport').dialog("open");
	  		}else if(obj.value.indexOf("EXCEL")!= -1){
	  			document.getElementById("geshi").value="xls";
	  		}else if(obj.value.indexOf("CSV")!= -1){
	  			document.getElementById("geshi").value="csv";
	  		}
			
			
			
  		}
  		function exportReport() {
  			location.href="/fort/queryformcount.do?method=export&geshi="+$('#geshi_d').val()+"&reportFormId="+$('#reportFormId_d').val()+"&picpath="+$('#picpath').val()+"&createUser="+$('#createUser').val()+"&exportUser="+$('#exportUser').val()+"&company="+$('#company').val();
  			$('#dialog-reportExport').dialog("close");
  		}*/
  		
  		function Export(obj){
			//设置报表格式
			var geshi = "";
	  		var reportFormId = "${reportFormId}";
	  		var picpath = "";
	  		var picpaths = document.getElementsByName("picName");
	  		if(picpaths != null){
	  			for(var i=0;i<picpaths.length;i++){
	  				if(picpaths[i].value != ""){
	  					picpath += picpaths[i].value+",";
	  				}
	  			}
	  		}
	  		//图片路径
	  		if(picpath != ""){
				picpath = picpath.substring(0,picpath.length-1);
	  		}
	  		
	  		if(obj.value.indexOf("TXT")!= -1){
	  			geshi = "txt";
	  			window.open("${ctx}/pages/reportForm/reportinfo.jsp?geshi="+geshi+"&reportFormId="+reportFormId+"&picpath="+picpath,"填写报表信息","height=350, width=400,toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no");
	  			return false;
	  		}else if(obj.value.indexOf("EXCEL")!= -1){
	  			document.getElementById("geshi").value="xls";
	  		}else if(obj.value.indexOf("CSV")!= -1){
	  			document.getElementById("geshi").value="csv";
	  		}else if(obj.value.indexOf("WORD")!= -1){
	  			geshi = "doc";
	  			window.open("${ctx}/pages/reportForm/reportinfo.jsp?geshi="+geshi+"&reportFormId="+reportFormId+"&picpath="+picpath,"填写报表信息","height=350, width=400,toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no");
	  			return false;
	  		}else if(obj.value.indexOf("HTML")!= -1){
	  			geshi = "html";
	  			window.open("${ctx}/pages/reportForm/reportinfo.jsp?geshi="+geshi+"&reportFormId="+reportFormId+"&picpath="+picpath,"填写报表信息","height=350, width=400,toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no");
	  			return false;
	  		}else if(obj.value.indexOf("PDF")!= -1){
	  			geshi = "pdf";
	  			window.open("${ctx}/pages/reportForm/reportinfo.jsp?geshi="+geshi+"&reportFormId="+reportFormId+"&picpath="+picpath,"填写报表信息","height=350, width=400,toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no");
	  			return false;
	  		}
	  		document.ExportForm.submit();
  		}
  		
		function getHTML(id,url,pars,meth)
		{ 
			new Ajax.Updater({success: id},url,{method: meth, parameters: pars,evalScripts: true ,asynchronous: false});
		}
		//以下是分页操作	
	function selectAll(obj, action){
		//var arrays = obj.arrays.value;
		//var tempHaving = obj.tempHaving.value;
		//while(arrays.indexOf("%")>-1){
			//arrays = arrays.replace("%","％");
		//}
		getHTML("tcontent","queryformcount.do?method=selectTable",
				"reportFormId="+encodeURIComponent(${reportFormId})+"&pageoffset="+encodeURIComponent(${pageoffset})+
				"&action="+action,"post");
	}
	
	function pagego(){
	   var obj = document.SelectCom;
	   var action = obj.action.value+"&page="+obj.page.value;
	   var pp = this.chackpage(obj);
	   if(pp==true){
	   	  this.selectAll(obj,action);
	   }else{
	 	  return false;
	   }
	}
	
	function chackpage(obj){
	
		var page = obj.page.value;
		if (page==""){
			alert("要查找的页面不能为空");
	   		obj.page.focus();
	   		return false;
		}
		if (!checkInteger(obj.page)){
			return false;
		}
		var totalpages = 0;
		var totalpages = obj.totalPages.value;
		page = Number(page);
		totalpages = Number(totalpages);
		if (page>totalpages){
			alert("要查找的页面不能大于总页数！");
			return false;
		}
		if (page<=0){
			alert("要查找的页面不能小于零！");
			return false;
		}
		return true;
	}
	function onkeyPageGo(){
		if(event.keyCode==13){
			return pagego();
		}
	}
	function checkInteger(Object){
		var strInteger=Object.value;
	    if(strInteger.length==0){
	  	   return true;
		}else{
		   var pattern = /^-?\d+$/;
		   if(strInteger.match(pattern)==null){
		   	  alert("只能输入数字");
		 	  Object.focus();
		 	  return false;
		   }else{
		  	  return true;
		   }
		}
	} 		
		</script>
	</head>
	<body>
		<table border="0" cellspacing="1" cellpadding="0"
			style="width: 99%; margin-left: 4px; margin-top: 2px;">
			<tr>
				<td valign="top">
					<div class="framDiv">

						<!--  左侧table-->
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td class="r2titler" colspan="2">
									<b>报表统计结果显示</b>
								</td>
							</tr>
							<tr>
								<td align="center"
									style="padding-top: 10px; padding-bottom: 10px;">
									<table cellSpacing="0" cellPadding="0" width="820" border="0"
										style="font-size: 13px;">
										<TBODY>
											<TR>
												<logic:notEqual name="isshow" value="1">
													<TD width="100" height="19" align="center"
														background="../images/tiao1.gif" style="LINE-HEIGHT: 14pt"
														id="email_image"
														onclick="disp('email,oa,column,poly,radar');"
														style="cursor:hand;">
														<font id="email_color" color="#cc6633">列表</font>
													</TD>
													<TD width="720" style="BORDER-BOTTOM: #778a98 1px solid">
														&nbsp;
													</TD>
												</logic:notEqual>
												<logic:equal name="isshow" value="1">
													<TD width="100" height="19" align="center"
														background="../images/tiao1.gif" style="LINE-HEIGHT: 14pt"
														id="email_image"
														onclick="disp('email,oa,column,poly,radar');"
														style="cursor:pointer;">
														<font id="email_color" color="#cc6633"
															style="cursor: pointer;">列表</font>
													</TD>
													<TD width="100" align="center"
														background="../images/tiao.gif" style="LINE-HEIGHT: 14pt"
														id="oa_image"
														onclick="disp('oa,email,column,poly,radar');"
														style="cursor:pointer;">
														<font id="oa_color" style="cursor: pointer;">饼图</font>
													</TD>
													<TD width="100" align="center"
														background="../images/tiao.gif" style="LINE-HEIGHT: 14pt"
														id="column_image"
														onclick="disp('column,poly,radar,oa,email');"
														style="cursor:pointer;">
														<font id="column_color" style="cursor: pointer;">柱形图</font>
													</TD>
													<TD width="100" align="center"
														background="../images/tiao.gif" style="LINE-HEIGHT: 14pt"
														id="poly_image"
														onclick="disp('poly,oa,email,column,radar');"
														style="cursor:pointer;">
														<font id="poly_color" style="cursor: pointer;">折线图</font>
													</TD>
													<TD width="100" align="center"
														background="../images/tiao.gif" style="LINE-HEIGHT: 14pt"
														id="radar_image"
														onclick="disp('radar,column,poly,oa,email');"
														style="cursor:pointer;">
														<font id="radar_color" style="cursor: pointer;">雷达图</font>
													</TD>
													<TD width="320" style="">
														&nbsp;
													</TD>
												</logic:equal>
											</TR>
										</TBODY>
									</table>
									<!-- 列表数据显示 -->
									<table width="820" height="380" border="0" cellpadding="0"
										cellspacing="0" id="email_display" style="display: inline"
										bgcolor="#FFFFFF">
										<tr>
											<td width="800" height="340" align="left" valign="top"
												style="BORDER-RIGHT: #778a98 1px solid; BORDER-LEFT: #778a98 1px solid; BORDER-BOTTOM: #778a98 1px solid">
												<div id="tcontent" name="tcontent" style="cursor: pointer;">
												</div>
											</td>
										</tr>
									</table>
									<!-- 饼图 -->
									<table width="820" height="380" border="0" cellpadding="0"
										cellspacing="0" id="oa_display" bgcolor="#FFFFFF"
										style="display: none">
										<tr>
											<td width="700" height="340">
												<table style="margin-left: 35px;">
													<tr>
														<td>
															<div id="chart"
																style="width: 700px; height: 400px; border: 1px solid #A4BED4;"></div>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									<!-- 柱状图 -->
									<table width="820" height="380" align="center"
										id="column_display" style="display: none" bgcolor="#FFFFFF"
										border="0" cellpadding="0" id="column_display">
										<tr>
											<td width="700" height="340">
												<table style="margin-left: 35px;">
													<tr>
														<td>
															<div id="chart2"
																style="width: 700px; height: 400px; border: 1px solid #A4BED4;"></div>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>


									<!-- 折线图 -->
									<table width="820" height="380" border="0" cellpadding="0"
										cellspacing="0" id="poly_display" style="display: none"
										bgcolor="#FFFFFF">
										<tr>
											<td width="700" height="340">
												<table style="margin-left: 35px;">
													<tr>
														<td>
															<div id="chartline"
																style="width: 700px; height: 400px; border: 1px solid #A4BED4;"></div>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									<!-- 雷达图 -->
									<table width="500" height="300" border="0" cellpadding="0"
										cellspacing="0" id="radar_display" style="display: none"
										bgcolor="#FFFFFF">
										<tr>
											<td width="500" height="300">
												<table style="margin-left: 35px;">
													<logic:notEmpty name="spiderChart">
														<tr>
															<td align="center" colspan="6">
																<img src="${spiderChart }" width="700" height="400" />
															</td>
														</tr>
													</logic:notEmpty>
												</table>
											</td>
										</tr>
									</table>

								</td>
							</tr>

						</table>
				</td>
			</tr>
		</table>
		<div id="ExportDiv" style="margin-left: 4px; margin-top: 10px;" align="center">
			<form name="ExportForm" action="queryformcount.do?method=export"
				method="post">
				<input type="button" class="btnyh" value="TXT报表"
					onclick="Export(this);" />
				<input type="button" class="btnyh" value="EXCEL报表"
					onclick="Export(this);" />
				<input type="button" class="btnyh" value="CSV报表 "
					onclick="Export(this);" />

				<input type="button" class="btnyh" value="HTML报表"
					onclick="Export(this);" />
				<input type="button" class="btnyh" value="WORD报表 "
					onclick="Export(this);" />

			<!--  	<input type="button" class="btnyh" value="PDF报表"
					onclick="Export(this);" /> -->

				<input type="button" class="btnyh" value=" 返回 "		
					onClick="window.location.href='${ctx}/queryStat.do?method=initPage'"/>
				<input type="hidden" class="btnyh" name="reportFormId"
					value="${reportFormId }" />
				<input type="hidden" class="btnyh" name="arrays" value="${arrays}" />
				<input type="hidden" class="btnyh" name="geshi" value="" id="geshi" />
				<input type="hidden" class="btnyh" name="picName" value="${pie }" />
				<input type="hidden" class="btnyh" name="picName" value="${bar }" />
				<input type="hidden" class="btnyh" name="picName"
					value="${lineChart }" />
				<input type="hidden" class="btnyh" name="picName"
					value="${spiderChart }" />
			</form>
		</div>
		
	</body>
</html>
