
<%@ include file="mainoffer.inc"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%

String path = request.getContextPath();

	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
 
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>


	<link href="${ctx}/styles/currentlystyle.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
		function   closeaftersubmit(){
			document.forms[0].target = "_blank";   
	  		document.forms[0].submit();
	  		window.close();
 		}   
	</script>
</head>
<body topmargin="0" leftmargin="0" bgcolor="#FFFFFF">
	<br>
	<div align="center"><font size=2>填写报表导出信息 </font> </div>
	<form name="form1" action="/soc/queryformcount.do?method=export" method="post">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
	<td colspan="4" align="left" valign="top">
		<table cellSpacing=4 cellPadding=2 border=0>
			<tbody><tr>
				<td align="right">
					<font size=2>模板创建人：</font><font size=2> <B></B> </font>
				</td>
				<td>
					<input type="text" name="createUser" value="">
				</td></tr><tr>
				<td align="right">
					<font size=2>报表生成人： </font><font size=2><B></B> </font>
				</td>
				<td>
					<input type="text" name="exportUser" value="">
				</td></tr><tr>
				<td align="right">
					<font size=2>单位： </font><font size=2><B></B> </font>
				</td>
				<td>
					<input type="text" name="company" value="">
				</td></tr></tbody></table>
				<input type="hidden" name="geshi" value="${param.geshi }"/>
				<input type="hidden" name="reportFormId" value="${param.reportFormId }"/>
				<input type="hidden" name="picpath" value="${param.picpath }"/>
		</td>
		</tr>
		
		<tr>
			<td align="center">
				<input type="button" value="提交" onclick="closeaftersubmit();">
				<input type="reset" value="重置">
			</td>
		</tr>
	</table>
	
	</form>
</body>
</html>
