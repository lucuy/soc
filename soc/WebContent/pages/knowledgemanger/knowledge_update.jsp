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
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
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

		
</script>




</head>

<body style="margin:8px;background-color : white; text-align:left;">

<s:form action="updateMessage.action" namespace="/message"
		method="post" theme="simple"  cssStyle="width:550px;" id ="from">



	<div class="framDiv"  style="width:550px;">
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<!-- 消息信息 -->
			<tr>
				<td class='r2titler' colspan='3'><b>消息处理 </b></td>
			</tr>
			<tr>
				<td>
					<!-- 消息基本信息 -->
					<table width="100%" border="0" align="center" cellspacing="0"
						cellpadding="0">
						<!-- 空行 -->
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<!-- 公告标题 -->
						<tr>
							<td align="right" width="20%" style="padding-left:10px;font-size:12px;"><span
								class="spanred"></span>消息标题:</td>
							<td align="left" class="padding">
							<!-- <input class="validate[required,custom[validateLength],custom[spechars]] text-input " 
								name="security.securityTitle" value="" type="text"
								size="40" id="title" maxlength="255" style="width: 270px"/>  -->
								<input value="${message.messageTitle }" readonly="readonly"  style="width: 270px" />
								<input name="id" type="hidden" value="${message.messageId}"/>
							</td>
							<td><span id="check_loginname_msg"></span></td>
						</tr>
						<!-- 空行-->
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
							<tr>
							<td align="right" width="20%" style="padding-left:10px;font-size:12px;"><span
								class="spanred"></span>消息内容:</td>
							<td align="left" class="padding">
							<!-- <input class="validate[required,custom[validateLength],custom[spechars]] text-input " 
								name="security.securityTitle" value="" type="text"
								size="40" id="title" maxlength="255" style="width: 270px"/>  -->
								<input value="${message.messageContent }" readonly="readonly"  style="width: 270px" />
							</td>
							<td><span id="check_loginname_msg"></span></td>
						</tr>
						<!-- 空行-->
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<tr>
							<td align="right" style="padding-left:10px;font-size:12px;"><span class="spanred"> </span><span
								class="spanred"></span>消息种类:</td>
							<td align="left" class="padding">
							<!-- <input name="security.source" id="assetGateWays" value="" type="text"
								class="validate[required,custom[settingZifang]] text-input"
								size="40" id="assetGateWay" maxlength="255" style="width: 270px"  /> -->
							
								
								<c:if test="${message.messageType==3}"><input value="工单信息" readonly="readonly"  style="width: 270px" /></c:if>
								<c:if test="${message.messageType==1}"><input value="预警信息" readonly="readonly"  style="width: 270px" /></c:if>
								<c:if test="${message.messageType==2}"><input value="告警信息" readonly="readonly"  style="width: 270px" /></c:if> 
							</td>
							<td><span id="check_gate_msg"></span></td>
						</tr>
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<!-- 发布人 -->
						<tr>
							<td align="right" style="padding-left:10px;font-size:12px;"><span class="spanred"></span>发布人:</td>
							<td align="left" class="padding">
								<!-- <input	class="validate[required,custom[onlyLetterNumber]] text-input"
								name="security.publisher" value="" type="text"
								size="40" id="publisher" maxlength="255" style="width: 270px"/> -->
								
								<input name="sendUserloginName" value="${message.sendUser.userLoginName }" readonly="readonly"  style="width: 270px" />
							</td>
							<td><span id="div_ipEnd"></span></td>
						</tr>
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<!-- 处理人 -->
						<tr>
							<td align="right" style="padding-left:10px;font-size:12px;"><span class="spanred"></span>处理人:</td>
							<td align="left" class="padding">
								 <input value="${message.dealUser.userLoginName }" readonly="readonly" style="width: 270px" />
							</td>
							<td><span id="div_ipEnd"></span></td>
						</tr>
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<!-- 创建时间 -->
						<tr>
							<td align="right" style="padding-left:10px;font-size:12px;"><span class="spanred"></span>创建时间:</td>
							<td align="left" class="padding">
							<!-- <input class="validate[required,custom[onlyLetterNumber]] text-input"
								name="security.publisher" value="" type="text"
								size="40" id="publisher" maxlength="255" style="width: 270px"
								/> -->
								<input value="${message.createDate}" readonly="readonly" style="width: 270px"/>
							</td>
							<td><span id="div_ipEnd"></span></td>
						</tr>
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<!-- 最后修改时间 -->
						<tr>
							<td align="right" style="padding-left:10px;font-size:12px;"><span class="spanred"></span>最后修改时间:</td>
							<td align="left" class="padding">
							<!-- <input class="validate[required,custom[onlyLetterNumber]] text-input"
								name="security.publisher" value="" type="text"
								size="40" id="publisher" maxlength="255" style="width: 270px"
								 /> -->
								 <input value="${message.operateDate}" readonly="readonly" style="width: 270px"/>
							</td>
							<td><span id="div_ipEnd"></span></td>
						</tr>
					


						
						<!-- 虚线分割线 -->
						<tr>
							<td colspan="3">
								<div class="xuxian"></div></td>
						</tr>
						<tr>
							<td height="5px"></td>
						</tr>

						<tr>
							<td colspan="3">
								<!-- 描述-->
						<tr>

							<td align="right" style="vertical-align:20%;padding-left:10px;font-size:12px;"><span
								class="spanred"></span>处理意见:</td>
							<td class="padding">
								<textarea name="messageDeadIdea" id="assetMemo" cols="37" rows="8" 
									style="width:273px;resize:none;"
									>${message.messageDealIdea}</textarea></td>
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


<!-- <input type="submit" class="btnyh"
					id="btnSave" value="保  存"  /> <input
					type="button" class="btnyh" id="btnCancel" value="取  消"
					onclick="window.history.back();" /> -->





<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px;">
			<tr>
				<td class="td_detail_separator"></td>
			</tr>

			<tr>
				<td align="right"><input type="submit" class="btnyh"
					id="btnSave" value="保  存"  /> <input
					type="button" class="btnyh" id="btnCancel" value="取  消"
					onclick="window.history.back();" />
				</td>
			</tr>
		</table>








	
		</s:form>
	

	<!-- ext query from -->




</body>
</html>
