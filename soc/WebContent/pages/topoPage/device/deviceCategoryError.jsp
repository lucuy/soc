<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>错误页面</title>
   <link href="${ctx}/styles/dbStyles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/right.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/template.css" rel="stylesheet" type="text/css">
<LINK href="${ctx}/css/style/jquery-ui.custom.css" type=text/css rel=stylesheet>
<LINK href="${ctx}/css/style/jquery.popuplayer.css" type=text/css rel=stylesheet>

<SCRIPT src="${ctx}/scripts/util.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/popuplayer.js" type=text/javascript></SCRIPT>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.ui.dialog.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<style type="text/css">
	.tdleft{
 		width: 20%;
 		text-align: right;
 	}
 	td{
 		font-size: 12px;
 	}

</style>
<script type="text/javascript">
	function save(){
		$("#deviceForm").submit();
	}
</script>

  </head>
  
  <body style="margin: 0">
 <s:form action="updateDevice" method="post" namespace="/device" theme="simple" id="deviceForm">
      <input type="hidden" name="id" value="${device.device_id}"> 
      <h3>您要删除的设备类型中尚有设备存在，不能删除！</h3>
	 
</s:form>
<!-- ui-dialog-sysInfo -->
	<%-- 	<div id="dialog-employee" title="所属信息系统" style="font-size: 12px;">
			<table width="100%" class="tab2" style="width: 100%" border="0" cellspacing="0" cellpadding="0">
			<tr align="left" style="width: 100%;">
				<td align="left" style="float: left;width: 100%;font-size: 12px" >
				 <div style="float: left;">
					快速搜索：<input name="dlg-keyword-sysName"  id="goToSearch" onkeydown="if(event.keyCode==13)queryEmployee();" />
					<img src="${ctx}/images/search.jpg" class="hand"  onclick="goSearch(0)" />
					</div>
				</td>
				 
			</tr>
			<tr height="5"></tr>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2" id="table-sysName">
					<tr height="28">
						<td width="10%" align="center" class="biaoti">
							 <input type="checkbox" id="chkAll" class="check-box not_checked"/>
						</td>
						<td width="30%" align="center" class="biaoti">
							系统名称
						</td>
						<td width="30%" align="center" class="biaoti">
							系统编号
						</td>
						<td width="30%"  align="center" class="biaoti">
							业务描述
						</td>
					</tr>
				</table>
				</td>
			</tr>
			
			</table>
		</div> --%>
		
		<!-- ui-dialog-softInfo -->
		<%-- <div id="dialog-softUse" title="业务应用软件">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					快速搜索：<input name="dlg-keyword-sysName" id="softUseId" onkeydown="if(event.keyCode==13)queryEmployee();" />
					<img src="${ctx}/images/search.jpg" class="hand"  onclick="softSearch(0)" />
				</td>
			</tr>
			<tr height="5"></tr>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2" id="table-softUse">
					<tr height="28">
						<td width="10%" align="center" class="biaoti">
							 <input type="checkbox" id="chkAll" class="check-box not_checked"/>
						</td>
						<td width="30%" align="center" class="biaoti">
							软件名称
						</td>
						<td width="20%" align="center" class="biaoti">
							所属系统名称
						</td>
						<td width="20%"  align="center" class="biaoti">
							软件功能描述
						</td>
						<td width="20%"  align="center" class="biaoti">
							重要程度
						</td>
					</tr>
				</table>
				</td>
			</tr>
			
			</table>
		</div> --%>
	
  </body>
</html>
