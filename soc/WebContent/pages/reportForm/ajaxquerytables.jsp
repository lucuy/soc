<%@ include file="mainoffer.inc"%>
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

    <title>My JSP 'ajaxquerytables.jsp' starting page</title>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
</script>
  </head>
  
  <body>
	  
	<table width="100%" border="1" cellpadding="0"cellspacing="0" bordercolorlight="#666666"
			bordercolordark="#ffffff" bgcolor="#FFFFFF" id="contentTable">
		<logic:notEmpty name="leveltwolist">
			<logic:iterate id="list" name="leveltwolist">
				<tr>
					<td height="20" align="center"width="15%"><input type="checkbox" id="leveltwo" name="leveltwo"
						  value="${list.id }" onclick="javascript:additems('two',3,'${list.id}',this.checked);"></td>
					<td align="center" width="85%">&nbsp;${list.name }</td>
				</tr>
			</logic:iterate>
		</logic:notEmpty>
		<logic:notEmpty name="levelthreelist">
			<logic:iterate id="list" name="levelthreelist">
				<tr>
					<td height="20" align="center"width="15%"><input type="checkbox" id="levelthree" name="levelthree"
						  value="${list.id }"></td>
					<td align="center" width="85%">&nbsp;${list.tableNameDescription }</td>
				</tr>	
			</logic:iterate>
		</logic:notEmpty>	
	</table>	    		
    		
	 
  </body>
</html>
