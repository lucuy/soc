<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="mainoffer.inc"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<% String name = request.getParameter("name"); %>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*"%>

<%

String path = request.getContextPath();

	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>
	
		<title>Insert title here</title>
		<link href="${ctx}/styles/currentlystyle.css" rel="stylesheet" type="text/css">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<script type="text/javascript">
		function sub(){
			var file = document.uploadForm.filepath.value;
			var fileName = file.substring(file.lastIndexOf("\\")+1,file.length);
			if("reportForm.zip"!=fileName){
				alert("您选择的不是组态报表备份文件");
				return false;
			}
			document.uploadForm.submit();
		}
function closewin(){
	window.opener.location.href="${ctx}/reportFormQuery.do?method=initPage";
	window.close();
}  
		</script>
	</head>
	
<body>
		<form name="uploadForm" id="uploadForm" action="${ctx}/fileUpload/upload.action?callbackUrl=reportFormQuery.do?method=inportReportForm" method="post" enctype="multipart/form-data" >
		${mes }
		<table width="90%" height="149" border="0" align="center" cellpadding="0" cellspacing="5">
		  <tr>
		    <td width="30%" align="right">请选择文件：</td>
		  	<td width="70%" align="left">
		  		<input type="file" name="filepath" />
		  	</td>
		  </tr>
		  <tr>
		    <td align="left">&nbsp;</td>
		  	<td align="right"><input type="button" value="开始上传" onclick="sub();" style="width:78px; height:22px;"></td>
		  </tr>
		   <logic:notEmpty name="message">
		   <tr><td colspan="2">
           <div id="clearfloat">
			<a onclick="javascript:window.close();">三秒钟后窗口自动关闭</a>
			<script type="text/javascript">
				setTimeout("closewin();", 3000);
				    window.onbeforeunload = onbeforeunload_handler;   
				    function onbeforeunload_handler(){   
				      window.opener.location.href="${ctx}/reportFormQuery.do?method=initPage";
				    }   
			</script>
			</div></td></tr>
        </logic:notEmpty>
		</table>
		</form>
		
</body>
</html>

