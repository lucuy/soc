<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="mainoffer.inc"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<%

String path = request.getContextPath();

	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html:html lang="true">
<head>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

<title>组态报表模板详细信息</title>
		<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript">
function CloseWin()
{
	window.close(); 
}
</script> 
<link href="../css/currentlystyle.css" rel="stylesheet" type="text/css">
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"> 
<div id="msgDiv"></div>
<table width="580" border="1" align="center" cellpadding="2" cellspacing="0" bordercolorlight="#666666" bordercolordark="#FFFFFF">
	 
	 <tr>
	 	<td height="20" colspan="4" align="center">组态报表模板详细信息</td>
	 </tr>
	 <logic:present name="formModel">
	 	<logic:notEmpty name="formModel">
	 		<tr>
				<td width="20%" height="20" align="center">&nbsp;&nbsp;模板名称：</td>
		    	<td height="20" colspan="3" align="left">&nbsp;&nbsp;&nbsp;
		    		<bean:write name="formModel" property="reportFormName"/></td>
			</tr>
			<tr>
				<td width="20%" height="20" align="center">&nbsp;&nbsp;模板类型：</td>
		    	<td height="20" colspan="3" align="left">&nbsp;&nbsp;&nbsp;
		    		<logic:equal name="formModel" property="reportFormType" value="0">动态模板</logic:equal>
            		<logic:equal name="formModel" property="reportFormType" value="1">静态模板</logic:equal></td>
			</tr>
			<tr>
				<td width="20%" height="20" align="center">&nbsp;&nbsp;图形显示：</td>
		    	<td height="20" colspan="3" align="left">&nbsp;&nbsp;&nbsp;
		    		<logic:empty name="formModel" property="coordx">否</logic:empty>
		    		<logic:notEmpty name="formModel" property="coordx">是</logic:notEmpty></td>
			</tr>
			<tr>
				<td width="20%" height="20" align="center">&nbsp;&nbsp;创建时间：</td>
		    	<td height="20" colspan="3" align="left">&nbsp;&nbsp;&nbsp;${displayDate}</td>
			</tr>
			<tr>
				<td width="20%" height="20" align="center">&nbsp;&nbsp;模板描述：</td>
		    	<td height="20" colspan="3" align="left">&nbsp;&nbsp;&nbsp;
		    		<bean:write name="formModel" property="reportFormDescription"/></td>
			</tr>
	 	</logic:notEmpty>
	 </logic:present>
	<tr>
    <td height="20" colspan="4" align="center"><label>
      <input type="button" name="CloseBtn" value="&nbsp;关&nbsp;&nbsp;闭&nbsp;" onClick="CloseWin()"/>
    </label></td>
  </tr>

</table>
<script type="text/javascript">
	 <logic:present name="msg">
	 	var msg = "${requestScope.msg}"
	 	var msgDiv = document.getElementById("msgDiv");
	 	if("" != msg ){
	 		msgDiv.innerHTML = "<br><br><br><center><font color='red'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
	 		+ msg +"</font></center><br><br>"
	 	}
	 </logic:present>
	
</script>
</body>
</html:html>