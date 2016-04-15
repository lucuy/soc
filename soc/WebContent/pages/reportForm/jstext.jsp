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
    
    <title>My JSP 'jstext.jsp' starting page</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function closeS(){
			window.close();
		}
		function confirmS(){
			var val=form1.content.value;
			window.opener.document.getElementById("jstext").value=val;
			window.close();
		}
	</script>
  </head>
  
  <body>
  <form name="form1" action="">
    <table>
    	<tr>
    		<td><%String ss=(String)request.getParameter("oldval");%>
    			<textarea rows="10" cols="10" name="content" style="width:350px;height:350px;"><%=ss %></textarea>
    		</td>
    	</tr>
    	<tr>
    		<td align="center"><input type="button" name="confirm" value="确定" onclick="javascript:confirmS();">
    			<input type="button" name="closes" value="关闭" onclick="javascript:closeS();">
    		</td>
    	</tr>
    </table>
   </form>
  </body>
</html>
