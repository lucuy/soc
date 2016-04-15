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

<title>系统废止信息</title>
<meta http-equiv="Cache-Control" content="private" />
<LINK href="${ctx}/css/basic/css.css" type=text/css rel=stylesheet>
<LINK href="${ctx}/css/basic/basic.css" type=text/css rel=stylesheet>
<LINK href="${ctx}/css/style/jquery-ui.custom.css" type=text/css rel=stylesheet>
<LINK href="${ctx}/css/style/jquery.popuplayer.css" type=text/css rel=stylesheet>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.ui.dialog.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type="text/javascript">
function edit(){
  document.editAbolish.submit();
  $("#frm").attr("disabled","disabled");
}
</script>

</head>

<body>
	<form  name="editAbolish"  id="frm" action="${ctx}/sysAbolish/editBegin.action"  method="post">
	    <input type="hidden" name="pkSysInfo" value="<s:property value="sysAbolish.pkSysInfo"/>"/>
	     <input type="hidden" name="sysAbolishId" value="<s:property value="sysAbolish.sysAbolishId"/>"/>
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px;">
			<tr>
				<td>
				<s:if test='#session.SSI_LOGIN_Status=="1"'>
				<div class="caozuobox">
						<input type="hidden" value="" onclick="edit()" class="btnstyle" />
					</div>
					</s:if>
					<s:else>
					
					<div class="caozuobox">
						<input type="button" style="font-size: 12px;margin-left:10px;margin-top: 5px;" value="编辑" onclick="edit()"  class="btnstyle" />
					</div>
					</s:else>
					
				</td>
			</tr>
			<tr>
			<td height="5px;"></td>
			</tr>
			<tr style="width: 100%">
				<td>
				 <div class="sbox">
						<div class="cont">
							<!-- information area -->
							<div id="dataList">
							<table  width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2" id="table-userManage">
	                        <thead>
								<tr height="28">
									<th width="10%"  class="biaoti" style="text-align: center;font-size: 12px;" align="center">相关证明</th>
									<th width="50%" class="biaoti" style="text-align: center;font-size: 12px;"  align="center" >相关描述</th>
									<th width="40%" class="biaoti" style="text-align: center;font-size: 12px;" align="center">相关附件</th>

								</tr>
								</thead>
								<tr>
									<td class="tdhead" style="text-align:center; background-color:#E1DEE4;font-size: 12px;">信息删除证明：</td>
									<td>${sysAbolish.sysDescription}</td>
									<td>${sysAbolish.sysAccess}</td> 
								</tr>
								<tr>
									<td class="tdhead" style="text-align:center; background-color:#E1DEE4;font-size: 12px;">设备清除证明：</td>
									<td>${sysAbolish.devDescription}</td>
									<td>${sysAbolish.devAccess}</td>
								</tr>
								<tr>
									<td class="tdhead" style="text-align:center; background-color:#E1DEE4;font-size: 12px;">存储清除证明：</td>
									<td>${sysAbolish.storDescription}</td>
									<td>${sysAbolish.storAccess}</td>

								</tr>
							</table>
							</div>
							</div></div>
							</td>
			</tr>
			
		</table>
	</form>
</body>
</html>
