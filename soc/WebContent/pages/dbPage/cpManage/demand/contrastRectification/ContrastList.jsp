<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>整改需求汇总列表</title>
<meta http-equiv="Cache-Control" content="private" />
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet" type="text/css">
<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet"
	type="text/css"> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css"><SCRIPT src="${ctx}/scripts/jquery-1.7.2.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/jquery-ui.custom.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/util.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/popuplayer.js" type=text/javascript></SCRIPT>
<script type="text/javascript" src="${ctx}/scripts/sorttalbe.js"></script>
<style type="text/css">
     .biaoti{
           font-size: 12px ;
           font-weight: bold;
          
     }


</style>
<script type="text/javascript">
function exportExcel(){
  document.formdown.submit();
}
</script>  
</head>
<body style="margin: 0;position: 0;padding-left:2px">

	<!--  <form id="systeminfo" name="cpSystemInfoForm" method="post" action="/compliance/system/query.action">-->
		<table width="99.5%" border="0" cellspacing="0" cellpadding="0">
			<!-- 空行 -->
			<tr>
				<td height="2px">
				<input type="hidden" id="FK_CA" value="${FK_CA}">
				</td>
			</tr>
			<tr>
				<td>
					<div class="caozuobox">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" style="font-size:12px;">
							<tr height="30">
								<td width="1%"></td>
								<%--<td width="30%" valign="middle" align="left">
									快速搜索： <input type="text" name="searchSysName" id="goToSearch"> <img src="/compliance/images/search.jpg" onclick="goSearch(0);" style="cursor: pointer" class="hand" />
								</td>
								--%><td align="left">
								 <form name="formdown" id="formdown" method="post"  action="${ctx}/contrast/extoexcel.action">
								 <!-- 评估结果 --> 
								<input type="hidden" id="CORRRECOM_AssessResult" name="CORRRECOM_AssessResult" value="${CORRRECOM_AssessResult}">
								<!-- 系统名称 -->
								<input type="hidden" id="CORRRECOM_SysName" name="CORRRECOM_SysName" value="${CORRRECOM_SysName}">
								<!-- 系统id -->
								<input type="hidden" id="CORRRECOM_SysId" name="CORRRECOM_SysId" value="${CORRRECOM_SysId}">
								<s:if test='#session.SSI_LOGIN_Status=="1"'>
								</s:if>
								<s:else>
								 <input type="button" class="btnstyle" onclick="exportExcel();" value="导出列表">
								 </s:else>	
								 
								    </form>
								 </td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="sbox">
						<div class="cont">
							<!-- information area -->
							<div id="dataList">
							<form action="${ctx}/contrast/queryContrastList.action" method="post">
								 
								<table style="font-size: 12px;" width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2" id="table-manageList">
									<thead>
										 <tr height="28">
											<th width="15%" class="biaoti" onclick="sortAble('table-manageList', 0)" style="cursor:pointer">控制域</th>
											<th width="15%" class="biaoti" onclick="sortAble('table-manageList', 1)" style="cursor:pointer">控制单元</th>
											<th width="31%" class="biaoti" onclick="sortAble('table-manageList', 2)" style="cursor:pointer">不符合项</th>
											<th width="25%" class="biaoti">整改建议</th>
											<th width="*" class="biaoti">整改时间</th>
										</tr>
									 </thead>
									<tbody id="tbodyuse">
									<s:iterator value="rectificationProposalsList" status="demdcol">
									 <tr>
									 <td align="center">${CORRRECOM_ControlDomainName}</td>
									 <td align="center">${CORRRECOM_ControlUnitName}</td>
									  <td colspan="3">
									  <table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2" id="innertable" style="font-size:12px;">
									     <s:iterator value="list" status="demdcol">
									     <tr>
									        <td width="44.3%" align="center" style="font-size:12px;">${CORRRECOM_ItemNumber}:${CORRRECOM_Content}</td>
									        <td width="36%" align="center">${CORRRECOM_Advise}</td>
									         <td width="*" align="center"> ${CORRRECOM_Date}</td>
									      </tr>
									      </s:iterator>
									  </table>
									  </td>
									 </tr>
									</s:iterator>
									</tbody>
									<tr id="tbodyuseTr"></tr></table>
								 </form>
							</div>
							<!-- information area -->
						</div>
					</div>
				</td>
			</tr>
		</table>
	 
	</body>
</html>
