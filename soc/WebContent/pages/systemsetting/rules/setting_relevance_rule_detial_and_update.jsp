<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

<link href="${ctx}/styles/systemsetting/systemsetting.css"
	rel="stylesheet" type="text/css">
<link href="/cms/styles/user/user.css" rel="stylesheet" type="text/css">
<link href="/cms/pages/knowledgemanger/Default.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
	<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/js/jquery-ui-1.7.3.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<link href="${ctx}/styles/systemsetting/systemsetting.css"
	rel="stylesheet" type="text/css">
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
<!-- ------- -->


<link href="form.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/filterrule/tab.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/filterrule/closeableTab.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/filterrule/properties.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/filterrule/displaytree.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/filterrule/style.css" rel="stylesheet"
	type="text/css">
<link type="${ctx}/styles/filterrule/text/css" rel="StyleSheet"
	href="listToolButton.css" />
<link href="${ctx}/styles/filterrule/common.css" type="text/css"
	rel="stylesheet" />
<link href="${ctx}/styles/filterrule/viewPath.css" type="text/css"
	rel="stylesheet" />

<script type="text/javascript"
	src="${ctx}/scripts/filterrule/prototype-1.5.1.js"></script>
<script type="text/javascript" src="${ctx}/scripts/filterrule/xtree.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/filterrule/tabpane.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/filterrule/closeableTab.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/filterrule/WebFXDisplayTreeItem.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/filterrule/checkFormat.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/filterrule/build/ui.js"></script>
<script type="text/javascript" src="${ctx}/scripts/filterrule/common.js"></script>
<script type="text/javascript" src="${ctx}/scripts/filterrule/i18n.jsp"></script>

<style type="text/css">
@import url(jscalendar/skins/calendar-blue.css);
</style>
<script type="text/javascript" src="${ctx}/styles/scripts/calendar.js"></script>
<script type="text/javascript"
	src="${ctx}/styles/scripts/lang/calendar-zh.js"></script>
<script type="text/javascript"
	src="${ctx}/styles/scripts/calendar-setup.js"></script>





<!-- ------- -->
<script type='text/javascript'>
	jQuery(document).ready(function() {
		jQuery("#from").validationEngine();

	});
</script>

</head>

<body style="margin:5px;">



	<s:form action="updateRelevanceRule.action" namespace="/relevanceRules"
		method="post" theme="simple" id="from">



		<!-- 公司table-->
		<div class="framDiv" style="width:100%;">
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<!-- 用户信息 -->
				<tr>
					<td class='r2titler' colspan='3'><b>自定义规则</b></td>
				</tr>
				<tr>
					<td>

						<table width="99%" border="0" align="center" cellspacing="0"
							cellpadding="1">
							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator"><input type="text"
									style="display:none;" name="relevanceRule.relevanceRuleId"
									value="${relevanceRule.relevanceRuleId }" /></td>
							</tr>
							<!-- 公告标题 -->
							<tr>
								<td align="right" width="30%"
									style="padding-left:10px;font-size:12px;"><span
									class="spanred">*&nbsp;</span>关联名称:</td>
								<td align="left" class="padding"><input
									class="validate[required,custom[onlyLetterNumber]] text-input"
									name="relevanceRule.relevanceRuleName"
									value="${relevanceRule.relevanceRuleName }" type="text"
									size="40" id="assetName" maxlength="255" style="width: 270px" />
								</td>

							</tr>
							<!-- 空行-->
							<tr>
								<td class="td_detail_separator"></td>
							</tr>

							<!-- 创建者 -->
							<tr>
								<td align="right" style="padding-left:10px;font-size:12px;"><span
									class="spanred" style="padding-left:10px;font-size:12px;">*&nbsp;</span>创建者:</td>
								<td align="left" class="padding"><input
									class="validate[required,custom[onlyLetterNumber]] text-input"
									name="relevanceRule.relevanceRuleCreater" readonly="readonly"
									value="${relevanceRule.relevanceRuleCreater }" type="text"
									size="40" id="assetName" maxlength="255" style="width: 270px" />
								</td>

								</td>
							</tr>
							<!-- 空行-->
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
							<!--来源 -->
							<tr>
								<td align="right" style="padding-left:10px;font-size:12px;"><span
									class="spanred"> </span><span class="spanred">*&nbsp;</span>创建时间:</td>
								<td align="left" class="padding"><input
									name="relevanceRule.relevanceRuleCreateDate" id="assetGateWays"
									value='<s:date
										name="relevanceRule.relevanceRuleCreateDate" format="yyyy-MM-dd HH:mm:ss" />'
									type="text"
									class="validate[required,custom[settingZifang]] text-input"
									size="40" id="assetGateWay" maxlength="255"
									style="width: 270px" onblur="javascript:checkAssetGateWay();"
									readonly="readonly" />
								</td>

								</td>
							</tr>
							<tr>
								<td class="td_detail_separator"></td>
							</tr>

							<tr>
								<td align="right" style="padding-left:10px;font-size:12px;"><span
									class="spanred"> </span><span class="spanred">*&nbsp;</span>修改者:</td>
								<td align="left" class="padding"><input
									class="validate[required,custom[onlyLetterNumber]] text-input"
									name="relevanceRule.relevanceRuleMender" readonly="readonly" readonly="readonly"
									value="${relevanceRule.relevanceRuleMender }" type="text"
									size="40" id="assetName" maxlength="255" style="width: 270px" />
								</td>

								</td>
							</tr>
							<tr>
								<td class="td_detail_separator"></td>
							</tr>



							<tr>
								<td align="right" style="padding-left:10px;font-size:12px;"><span
									class="spanred"> </span><span class="spanred">*&nbsp;</span>修改时间:</td>
								<td align="left" class="padding"><input
									name="relevanceRule.relevanceRuleMenderDate" id="assetGateWays"
									value='<s:date
										name="relevanceRule.relevanceRuleMenderDate" format="yyyy-MM-dd HH:mm:ss" />'
									type="text"
									class="validate[required,custom[settingZifang]] text-input"
									size="40" id="assetGateWay" maxlength="255"
									style="width: 270px" onblur="javascript:checkAssetGateWay();"
									readonly="readonly">
								</td>

								</td>
							</tr>
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
							<tr>
								<td align="right" style="padding-left:10px;font-size:12px;"><span
									class="spanred"> </span><span class="spanred">*&nbsp;</span>是否启用:</td>
								<td align="left" class="padding"><input type="radio"
									name="relevanceRule.relevanceRuleType" value="0" title="启用"
									<c:if test="${relevanceRule.relevanceRuleType==0}">checked="checked"</c:if> /><span
									style="font-size:12px;">启用</span> <input type="radio"
									name="relevanceRule.relevanceRuleType" value="1" title="停用"
									<c:if test="${relevanceRule.relevanceRuleType==1}">checked="checked"</c:if> /><span
									style="font-size:12px;">停用</span>
								</td>

								</td>
							</tr>


							<%--<!-- 空行 -->
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<!-- 虚线分割线 -->
						<tr>
							<td colspan="3">
								<div class="xuxian"></div></td>
						</tr>
						--%>
							<tr>
								<td height="5px"></td>
							</tr>

							<tr>
								<td colspan="3"></td>
							</tr>




						</table>
					</td>




					<td>

						<table width="99%" border="0" align="center" cellspacing="0"
							cellpadding="1">
							<!-- 空行 -->

							<tr>

								<td align="right" style="vertical-align:20%"
									style="padding-left:10px;font-size:12px;"><span
									class="spanred">*&nbsp;</span>关联描述:</td>
								<td class="padding"><textarea
										name="relevanceRule.relevanceRuleDes" id="assetMemo" cols="37"
										rows="8" style="width:273px;resize:none;">${relevanceRule.relevanceRuleDes }</textarea>
								</td>
							</tr>

							</td>
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
				<tr>
					<td class="td_detail_separator"></td>
				</tr>
			</table>
		</div>

		<div class="td_detail_separator"></div>
		<div class="framDiv" style="width:100%;">
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<!-- 用户信息 -->
				<tr>
					<td class='r2titler' colspan='3'><b>自定义关联条件</b>&nbsp;<span style = "font-size:12px;font-weight:normal;"><a href="${ctx}/riskfile/relevance.doc" class="jianju" style="color:#F00">书写说明</a></span></td>
				</tr>

			</table>
			<!-- ----------------------------------------------------------------------------------------------- -->


<table width="99%" border="0"  cellspacing="0"
				cellpadding="1" style = "text-align:center">
				<!-- 空行 -->
<tr>
								<td class="td_detail_separator"></td>
							</tr>
				<tr>

					
						
						
					<td ><span class="spanred" style = "font-size:12px">*&nbsp;</span><span style = "font-size:12px">自定义关联条件:&nbsp;&nbsp;</span><textarea
							name="relevanceRule.relevanceRuleCondition" id="assetMemo" cols="37"
							rows="8" style="width:500px;resize:none;">${relevanceRule.relevanceRuleCondition }</textarea>
					</td>
				</tr>

				</td>
				</tr>




			</table>
			<!-- -------------------------------------------- -->
			<table width="99%" border="0" cellspacing="0" cellpadding="0"
				style="margin-left: 4px;">
				<tr>
					<td class="td_detail_separator"></td>
				</tr>

				<tr>
					<td align="center"><input type="submit" class="btnyh"
						id="btnSave" value="保  存" /> <input type="button" class="btnyh"
						id="btnCancel" value="取  消" onclick="window.history.back();" />
					</td>
				</tr>
				<tr>
					<td class="td_detail_separator"></td>
				</tr>
			</table>










		</div>













	</s:form>


	<!-- ext query from -->




</body>
</html>
