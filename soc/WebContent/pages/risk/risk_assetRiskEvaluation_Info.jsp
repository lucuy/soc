<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
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

<link href="${ctx}/styles/library.css" rel="stylesheet"
	type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">
	<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/stylesalidationEngine.jquery.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/fontColor.css" rel="stylesheet"
    type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<%--||$("#eventLibraryName").html()=="" --%>
<script language="javascript">
function subData() {
	if($("#eventLibraryName").val()==""){
		alert('中文名称不能为空');
		return;
	}
	$("#saveEventLibrary").submit();
}

jQuery(document)
			.ready(
					function() {
					$('#dialog-assetDialog').dialog({
							autoOpen : false,
							width : 300,
							height : 400,
							buttons : {
								"确定[Enter]" : function() {

									$(this).dialog("close");
								},

								"取消[Esc]" : function() {
									$(this).dialog("close");
								}
							}
						});
					
					});
     function extQueryDlg()
     {
         $('#dialog-assetDialog').dialog('open');
     }
     
     function setAssetValue()
     {
        var use = $("#usabilityValue").val();

		var secret = $("#secretValue").val();

		var complete = $("#integrityValue").val();
		
		$.ajax({
			type : "post",
			url : "${ctx}/assetRiskGroup/completeAssetValue.action",
			dataType : "text",
			data : "use=" + use + "&secret=" + secret + "&complete="
					+ complete,
			success : function(result) {
			   
				$("#assetValue").val(result);
			}
		});
     }

</script>
</head>

<body style="margin-top:2px;">
	<!--  主table-->
		<table width="70%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<!-- left area -->
				<td width="50%" valign="top">
					<!--  左侧table-->
					<div class="framDiv">
					<c:set value="1" var="root" />
										<c:set value="" var="resStr" />
										<s:property value="#session.XXX" />
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<!-- 信息内容 -->
							<tr>
								<td class='r2titler' colspan='2'><b>资产风险赋值</b></td>
							</tr>

							<tr>
								<td>
								
								<s:form action="updateRiskValue.action" namespace="/assetRiskGroup"
								method="post" id="saveEventLibrary" name="saveEventLibrary" theme="simple">
								
								<input type="hidden" name="assetRiskEvaluation.assetRiskEvaluationId" value="${assetRiskEvaluation.assetRiskEvaluationId}">
									<!-- 用户信息内容 -->
									<table width="99%" border="0" align="center" cellspacing="0"
										cellpadding="0">
                                        <tr>
											<td height="5px"></td>
										</tr>
										<tr>
										<td align="right">资产名称:</td>
										<td align="left" class="padding"><input 
												type="text" size="40" id="assetName" maxlength="255"
												name="assetRiskEvaluation.assetName"
												value="${assetRiskEvaluation.assetName}"
												style="width: 270px" disabled ="disabled" />
												<input name="assetRiskName" value="${assetRiskEvaluation.assetName}" type="hidden"/>
										</td>
										</tr>
										<!-- 保密性价值 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
										<tr>
											<td align="right">保密性价值：</td>
											<td class="padding"><select style="width:274px"
												id="secretValue" name="assetRiskEvaluation.assetSecretValue"
												onchange="javascript:setAssetValue();">
													<option value="1"
														<c:if test="${assetRiskEvaluation.assetSecretValue==1}">selected="selected"</c:if>>1</option>
													<option value="2"
														<c:if test="${assetRiskEvaluation.assetSecretValue==2}">selected="selected"</c:if>>2</option>
													<option value="3"
														<c:if test="${assetRiskEvaluation.assetSecretValue==3}">selected="selected"</c:if>>3</option>
													<option value="4"
														<c:if test="${assetRiskEvaluation.assetSecretValue==4}">selected="selected"</c:if>>4</option>
													<option value="5"
														<c:if test="${assetRiskEvaluation.assetSecretValue==5}">selected="selected"</c:if>>5</option>
											</select>
											</td>
											<td></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<!-- 完整性 价值-->
										<tr>
											<td align="right">完整性价值：</td>
											<td class="padding"><select style="width:274px"
												id="integrityValue" name="assetRiskEvaluation.assetIntegrityValue"
												onchange="javascript:setAssetValue();">
													<option value="1"
														<c:if test="${assetRiskEvaluation.assetIntegrityValue==1}">selected="selected"</c:if>>1</option>
													<option value="2"
														<c:if test="${assetRiskEvaluation.assetIntegrityValue==2}">selected="selected"</c:if>>2</option>
													<option value="3"
														<c:if test="${assetRiskEvaluation.assetIntegrityValue==3}">selected="selected"</c:if>>3</option>
													<option value="4"
														<c:if test="${assetRiskEvaluation.assetIntegrityValue==4}">selected="selected"</c:if>>4</option>
													<option value="5"
														<c:if test="${assetRiskEvaluation.assetIntegrityValue==5}">selected="selected"</c:if>>5</option>
											</select>
											</td>
											<td></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 可用性价值: -->
										<tr>
											<td align="right">可用性价值：</td>
											<td class="padding"><select style="width:274px"
												id="usabilityValue" name="assetRiskEvaluation.assetUsabilityValue"
												onchange="javascript:setAssetValue();">
													<option value="1"
														<c:if test="${assetRiskEvaluation.assetUsabilityValue==1}">selected="selected"</c:if>>1</option>
													<option value="2"
														<c:if test="${assetRiskEvaluation.assetUsabilityValue==2}">selected="selected"</c:if>>2</option>
													<option value="3"
														<c:if test="${assetRiskEvaluation.assetUsabilityValue==3}">selected="selected"</c:if>>3</option>
													<option value="4"
														<c:if test="${assetRiskEvaluation.assetUsabilityValue==4}">selected="selected"</c:if>>4</option>
													<option value="5"
														<c:if test="${assetRiskEvaluation.assetUsabilityValue==5}">selected="selected"</c:if>>5</option>
											</select>
											</td>
											<td></td>
										</tr>

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 可用性价值: -->
										<tr>
											<td align="right">资产值：</td>
											<td align="left" class="padding"><input class="readonly"
												type="text" size="40" id="assetValue" maxlength="255"
												name="assetRiskEvaluation.assetAssetValue"
												value="<c:if test="${assetRiskEvaluation==null}">1</c:if><c:if test="${assetRiskEvaluation!=null}">${assetRiskEvaluation.assetAssetValue}</c:if>"
												style="width: 270px" readonly="readonly" />
											</td>
											<td></td>
										</tr>
									</table>
									</s:form>
									</td>
							</tr>

							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
						</table>
						
					</div>
				</td>
			</tr>
		</table>
		<table width="70%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px;">
			<!-- 空行 -->
			<tr>
				<td class="td_detail_separator"></td>
			</tr>
			
			<tr>
				<td align="right"><input
					type="button" class="btnyh" id="btnSave" value="保  存"
					onclick="subData();" /> <input type="button" class="btnyh"
					id="btnCancel" value="取  消"
					onclick="history.back();" />
				</td>
			</tr>

			<!-- <tr>
			
				<td align="right">
				<input style="background: url(/soc/images/jetsen/btnOK.png) no-repeat;" type="button" class="btnyh"
					id="btnSave" value="保  存" onclick="subData();" /> <input
					type="button" class="btnyh" style="background: url(/soc/images/jetsen/btnOK.png) no-repeat;" id="btnCancel" value="取  消"
					onclick="history.back();" />
				</td>
			</tr> -->
		</table>
		<div id="dialog-assetDialog" title="资产选择">
		<table width='96%' cellspacing='0' cellpadding='0' border='0'
			align='center'>
			<s:iterator value='assetList' status='stat'>
				<tr style="line-height: 25px;">
					<td width="20%" align="center"><input type="radio"
						name="chk-asset" id="chk-asset-${assetId}"
						value="${assetId}" /></td>
					<td width="80%">
					     ${assetName}
					</td>
				</tr>
			</s:iterator>
		</table>
	   </div>
</body>
</html>
