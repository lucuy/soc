<%@ include file="mainoffer.inc"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%

String path = request.getContextPath();

	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>
	
		<title>table content</title>
		<link href="${ctx}/styles/currentlystyle.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
		<script language="javascript">
		function Export(obj){
			//设置报表格式
			var geshi = "";
	  		var rcid = "${rcid}";
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
	  			window.open("${ctx}/pages/reportForm/customreportinfo.jsp?geshi="+geshi+"&rcid="+rcid+"&picpath="+picpath,"填写报表信息","height=350, width=400,toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no");
	  			return false;
	  		}else if(obj.value.indexOf("EXCEL")!= -1){
	  			document.getElementById("geshi").value="xls";
	  		}else if(obj.value.indexOf("CSV")!= -1){
	  			document.getElementById("geshi").value="csv";
	  		}else if(obj.value.indexOf("WORD")!= -1){
	  			geshi = "doc";
	  			window.open("${ctx}/pages/reportForm/customreportinfo.jsp?geshi="+geshi+"&rcid="+rcid+"&picpath="+picpath,"填写报表信息","height=350, width=400,toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no");
	  			return false;
	  		}else if(obj.value.indexOf("HTML")!= -1){
	  			geshi = "html";
	  			window.open("${ctx}/pages/reportForm/customreportinfo.jsp?geshi="+geshi+"&rcid="+rcid+"&picpath="+picpath,"填写报表信息","height=350, width=400,toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no");
	  			return false;
	  		}else if(obj.value.indexOf("PDF")!= -1){
	  			geshi = "pdf";
	  			window.open("${ctx}/pages/reportForm/customreportinfo.jsp?geshi="+geshi+"&rcid="+rcid+"&picpath="+picpath,"填写报表信息","height=350, width=400,toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no");
	  			return false;
	  		}
	  		document.ExportForm.submit();
  		}
		</script>
	</head>
	<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 1px;">
	<tr>
		<td align="left"><a href="${ctx}/reportCustom.do?method=initPage" style="color: blue; text-decoration: none; size: 18px;">${requestScope.message }</a></td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2">
			<tr height="30">
				<logic:notEmpty name="ReportCustomForm" property="titles">
					<logic:iterate id="tlist" name="ReportCustomForm" property="titles">
						<td  class="biaoti">
								${tlist}
						</td>
					</logic:iterate>
				</logic:notEmpty>
			</tr>
			<logic:notEmpty name="ReportCustomForm" property="val">
				<logic:iterate id="s" name="ReportCustomForm" property="val">
					<tr>
						<logic:iterate id="l" name="ReportCustomForm" property="titles">
						<td style="font-size: 12px;" align="center">
								${s[l]}
						</td>
						</logic:iterate>
					</tr>
				</logic:iterate>
			</logic:notEmpty>
		</table>
		</td>
		
	</tr>
	
	<tr>
		<td align="center">
			<logic:notEmpty name="ReportCustomForm" property="titles">
			
			<div id="ExportDiv">
				<br>
	    		<form name='ExportForm' action="reportCustom.do?method=exportData" method="post" >
	    		 <input type='button' class="btnyh" value=' TXT报表 '	onClick="Export(this);"/>
	    		 <input type='button' class="btnyh" value=' EXCEL报表 '	onClick="Export(this);"/>
	    		 <input type='button' class="btnyh" value=' CSV报表 '	onClick="Export(this);"/>
	    		 <input type='button' class="btnyh" value=' HTML报表 '	onClick="Export(this);"/>
	    		 <input type='button' class="btnyh" value=' WORD报表 '	onClick="Export(this);"/>
	    	<!--  	 <input type='button' class="btnyh" value=' PDF报表 '	onClick="Export(this);"/>-->
	    		 <input type="button" class="btnyh" value=" 返回 "		onClick="window.location.href='${ctx}/reportCustom.do?method=initPage'"/>
	    		
	    		<input type='hidden' style="width:80px; height: 25px;" name='rc.id' value='${rcid}'/>
	    		 <input type='hidden' style="width:80px; height: 25px;" name='arrays' value="${arrays }"/>
	    		 <input type='hidden' style="width:80px; height: 25px;" name="geshi" value="" id="geshi"/>
	    		 <input type="hidden" style="width:80px; height: 25px;" name="picName" value="${pie }" />
	    		 <input type='hidden' style="width:80px; height: 25px;" name="picName" value="${bar }" />
	    		 <input type='hidden' style="width:80px; height: 25px;" name="picName" value="${lineChart }" />
	    		 <input type='hidden' style="width:80px; height: 25px;" name="picName" value="${spiderChart }" /> 
		    	</form>
	    	</div>
		
			</logic:notEmpty>
		</td>
	</tr>
	
	</table>
	</body>
</html>
