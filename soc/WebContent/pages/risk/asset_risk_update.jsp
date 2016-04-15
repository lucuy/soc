<%@page import="com.soc.model.knowledge.MessageCenter"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="${ctx}/styles/css.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/library.css" rel="stylesheet"
	type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
	<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/stylesalidationEngine.jquery.css" rel="stylesheet"
	type="text/css">
		<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
		type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<%-- <script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>--%>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>





<link href="${ctx}/styles/systemsetting/systemsetting.css"
	rel="stylesheet" type="text/css">
<link href="${ctx}/styles/user/user.css" rel="stylesheet" type="text/css">

<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">

<script type='text/javascript' >

jQuery(document).ready(function() {
		jQuery("#from").validationEngine();
		
		
	});
	function check(){
		if($('#riskAssetId').val()==''){
			alert("请选择资产");
			return false;
		}
		
		if(parseInt($('#riskPossibility').val())==-1){
			alert("请选择威胁可能性");
			return false;
		}
		if(parseInt($('#riskInfluence').val())==-1){
			alert("请选择威胁影响");
			return false;
		}
		$('#from').submit();
	}
	function getResult(){
		var riskPossibilityResult = $('#riskPossibility').val();
		var riskInfluenceResult = $('#riskInfluence').val();
		var result;
		if(riskInfluenceResult==1){
			result = 1;
		}else if(riskInfluenceResult==2){
			if(riskPossibilityResult==1){
				result = 1;
			}else if(riskPossibilityResult==2){
				result = 1;
			}else if(riskPossibilityResult==3){
				result = 2;
			}else if(riskPossibilityResult==4){
				result = 2;
			}else if(riskPossibilityResult==5){
				result = 2;
			}
			
		}else if(riskInfluenceResult==3){
			if(riskPossibilityResult==1){
				result = 1;
			}else if(riskPossibilityResult==2){
				result = 2;
			}else if(riskPossibilityResult==3){
				result = 2;
			}else if(riskPossibilityResult==4){
				result = 3;
			}else if(riskPossibilityResult==5){
				result = 3;
			}
		}else if(riskInfluenceResult==4){
			if(riskPossibilityResult==1){
				result = 1;
			}else if(riskPossibilityResult==2){
				result = 2;
			}else if(riskPossibilityResult==3){
				result = 3;
			}else if(riskPossibilityResult==4){
				result = 4;
			}else if(riskPossibilityResult==5){
				result = 4;
			}
		}else if(riskInfluenceResult==5){
			if(riskPossibilityResult==1){
				result = 2;
			}else if(riskPossibilityResult==2){
				result = 2;
			}else if(riskPossibilityResult==3){
				result = 3;
			}else if(riskPossibilityResult==4){
				result = 4;
			}else if(riskPossibilityResult==5){
				result = 5;
			}
		}
		
		$('#riskResult').val(result);
	}
	

</script>




</head>

<body style="margin:8px;background-color : white; text-align:left;" >

<s:form action="updateRisk.action" namespace="/assetRiskValue"  method="post" theme="simple"  cssStyle="width:550px;" id ="from" >
 <s:token/>
	<div class="framDiv"  style="width:550px;">
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<!-- 威胁信息 -->
			<tr>
				<td class='r2titler' colspan='3' style="font-size: 12px;"><b>威胁信息</b></td>
			</tr>
			<tr>
				<td>
					<!-- 威胁基本信息 -->
					<table width="100%" border="0" align="center" cellspacing="0"
						cellpadding="0">
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<tr>
							<td align="right" style="padding-left:10px;font-size:12px;"><span class="spanred"></span>资产名称:</td>
							<td align="left" class="padding">
							<select name="riskValue.riskAssetId" id="riskAssetId" style="width: 270px" >
							<option value="">--请选择--</option>
							<s:iterator value="assetList" status="stat">
							<option value="${assetId}" <c:if test="${riskValue.riskAssetId eq assetId}">selected="selected"
								</c:if> >${assetName}</option>
							
								</s:iterator>
							</select>
							
							</td>
							<td><span id="div_ipEnd"></span></td>
						</tr>
							<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<tr>
							<td align="right" width="20%" style="padding-left:10px;font-size:12px;"><span
								class="spanred"></span>名称:</td>
							<td align="left" class="padding">
								<select name="riskValue.riskName" id="riskName" style="width: 270px">
									<option value="病毒和恶意代码攻击" <c:if test="${riskValue.riskName eq '病毒和恶意代码攻击' }">selected="selected"</c:if>>病毒和恶意代码攻击</option>
									<option value="利用系统和软件漏洞取得权限" <c:if test="${riskValue.riskName eq '利用系统和软件漏洞取得权限' }">selected="selected"</c:if>>利用系统和软件漏洞取得权限</option>
									<option value="口令猜测" <c:if test="${riskValue.riskName eq '口令猜测' }">selected="selected"</c:if>>口令猜测</option>
									<option value="滥用威胁" <c:if test="${riskValue.riskName eq '滥用威胁' }">selected="selected"</c:if>>滥用威胁</option>
									<option value="嗅探攻击" <c:if test="${riskValue.riskName eq '嗅探攻击' }">selected="selected"</c:if>>嗅探攻击</option>
									<option value="审计行为失效" <c:if test="${riskValue.riskName eq '审计行为失效' }">selected="selected"</c:if>>审计行为失效</option>
									<option value="信息探测" <c:if test="${riskValue.riskName eq '信息探测' }">selected="selected"</c:if>>信息探测</option>
									<option value="社会工工程" <c:if test="${riskValue.riskName eq '社会工工程' }">selected="selected"</c:if>>社会工工程</option>
								</select>
								<input name="riskValue.riskId" type="hidden" value="${riskValue.riskId}"/>
							</td>
							<td><span id="check_loginname_msg"></span></td>
						</tr>
						<!-- 空行-->
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
							<tr>
							<td align="right" width="20%" style="padding-left:10px;font-size:12px;"><span
								class="spanred"></span>可能性:</td>
							<td align="left" class="padding">
								<select id="riskPossibility" name="riskValue.riskPossibility" style="width: 270px" onchange="getResult()">
									<option value="-1">--请选择--</option>
									<option value="1" <c:if test="${riskValue.riskPossibility==1 }">selected="selected"</c:if>>不可能</option>
									<option value="2" <c:if test="${riskValue.riskPossibility==2 }">selected="selected"</c:if>>可能性很小</option>
									<option value="3" <c:if test="${riskValue.riskPossibility==3 }">selected="selected"</c:if>>可能</option> 
									<option value="4" <c:if test="${riskValue.riskPossibility==4 }">selected="selected"</c:if>>非常可能</option>
									<option value="5" <c:if test="${riskValue.riskPossibility==5 }">selected="selected"</c:if>>不可避免</option>
								</select>
							</td>
							<td><span id="check_loginname_msg"></span></td>
						</tr>
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<tr>
							<td align="right" style="padding-left:10px;font-size:12px;"><span class="spanred"> </span><span
								class="spanred"></span>影响:</td>
							<td align="left" class="padding">
								<select id="riskInfluence" name="riskValue.riskInfluence" style="width: 270px"  onchange="getResult()">
									<option value="-1">--请选择--</option>
									<option value="1" <c:if test="${riskValue.riskInfluence==1 }">selected="selected"</c:if>>可忽略</option>
									<option value="2" <c:if test="${riskValue.riskInfluence==2 }">selected="selected"</c:if>>可忍受</option>
									<option value="3" <c:if test="${riskValue.riskInfluence==3 }">selected="selected"</c:if>>明显损失</option> 
									<option value="4" <c:if test="${riskValue.riskInfluence==4 }">selected="selected"</c:if>>重大损失</option>
									<option value="5" <c:if test="${riskValue.riskInfluence==5 }">selected="selected"</c:if>>全部损失</option>
								</select>
							</td>
							<td><span id="check_gate_msg"></span></td>
						</tr>
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<tr>
							<td align="right" style="padding-left:10px;font-size:12px;"><span class="spanred"></span>威胁值结果:</td>
							<td align="left" class="padding">
								
								<input name="riskValue.riskResult"  id="riskResult" readonly="readonly" value="${riskValue.riskResult }"  style="width: 270px" />
							</td>
							<td><span id="div_ipEnd"></span></td>
						</tr>
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						
					
						<tr>
							<td height="5px"></td>
						</tr>

					</table>
				</td>
			</tr>
		</table>

		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px;">
			<tr>
				<td class="td_detail_separator"></td>
			</tr>
		</table>
	</div>

<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px;">
			<tr>
				<td class="td_detail_separator"></td>
			</tr>

			<tr>
				<td align="right"><input type="button" class="btnyh"
					id="btnSave" value="保  存" onclick="check()" /> <input
					type="button" class="btnyh" id="btnCancel" value="取  消"
					onclick="window.history.back();" />
				</td>
			</tr>
		</table>


</s:form>




	
	

	<!-- ext query from -->




</body>
</html>
