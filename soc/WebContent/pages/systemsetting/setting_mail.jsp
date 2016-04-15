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

<link href="${ctx}/styles/systemsetting/systemsetting.css"
	rel="stylesheet" type="text/css">
<style type="text/css">
ul {
	list-style: none;
}
</style>
<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
	<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script language="javascript">
	jQuery(document).ready(function() {
		jQuery("#mailForm").validationEngine();
	});
	
	function mailto() {
		var mailAddr = $("#mailAddr").val();
		if ($.trim($("#mailAddr").val()) == '') {
			alert("邮箱不能为空...");
			$("#mailAddr").val("");
			$("#mailAddr").focus();
			return false;
		} else {
			var url = "${ctx}/settingMail/mailInstance.action";
			$.ajax({
				type : "post",
				url : url,
				dataType : "text",
				data : "mail=" + mailAddr,
				success : function(result) {
					var info = String($.trim(result));
					if (info.indexOf("|") != -1) {
						var infos = info.split("|");
						$("#smtpServer").val(infos[0]);
						$("#mailUserName").val(infos[1]);
						$("#mailSmtpPort").val(infos[2]);
						$("#isNeedSsl").val(infos[3]);
						if (infos[3] == 1) {
							$("#chk-needssl").attr("checked", true);
						} else {
							$("#chk-needssl").attr("checked", false);
						}
					}
				}
			});
		}

	}
	//自己添加的查询端口号的函数
	function checkport(){
	   var smtpServer=$("#mailAddr").val();
	      
       if($("#chk-needssl").attr("checked")==false)
       {
           $("#mailSmtpPort").val("25");
       }
       else
       {
          var url = "${ctx}/settingMail/selectPort.action";
          $.ajax({
          type : "post",
          url : url,
          dataType : "text",
          data : "smtpServer=" + smtpServer,
          success : function(result) {
           var info = String($.trim(result));
             $("#mailSmtpPort").val(info);
          }
          });
       }
	}
	function subdata() {
		//验证smtp地址
		var low=$("#smtpServer").val();
		var regexServer=low.toLowerCase().match(/^smtp.(.*).(.*)$/); 
		if(regexServer==null){
			alert("您输入的SMTP地址不正确！格式应该为：smtp.xxx.com");
			return;
		}else{
			$("#smtpServer").val(low.toLowerCase());
		}
		//验证smtp端口
		var port=$("#mailSmtpPort").val();
		var regexPort=port.toLowerCase().match(/^(([1-9])|([1-9][0-9]{1,3})|([1-6][0-4][0-9]{3})|([1-6][0-5][0-4][0-9]{2})|([1-6][0-5][0-5][0-2][0-9])|([1-6][0-5][0-5][0-3][0-5]))$/); 
		if(regexPort==null){
			alert("您输入的SMTP端口不正确！格式应该在1--65535之间");
			return;
		}else{
			$("#mailSmtpPort").val(port.toLowerCase());
		}
		$("form").submit();
	}
</script>
</head>

<body>
	<!--  主table-->

	<s:form action="update-e.action" namespace="/settingMail" method="post" theme="simple" id="mailForm" name="mailForm">
		<!--  主table-->
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 2px">
			<tr>
				<!-- left area -->
				<td valign="top">
					<div class="framDiv">
						<!--  左侧table-->
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							style="padding-top:1px;padding-left:1px;padding-right:1px;">
							<tr>
								<td class="r2titler"><b>发送人信息</b></td>
							</tr>

							<!-- 分隔符黑线 -->
							<tr>
								<td height="3px"></td>
							</tr>

							<tr>
								<td align="right"><font color="red"><s:actionmessage />
								</font>
								</td>
							</tr>

							<tr>
								<td>
									<table width="99%" border="0" cellspacing="0" cellpadding="0"
										style="margin-left: 5px">

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<tr>
											<td width="25%" align="right">发件人昵称：</td>
											<td style="padding-left: 20px"><s:textfield id="nicky"
													name="nicky" size="40" cssStyle="width:270px" /></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 状态 -->
										<tr>
											<td align="right">邮件地址：</td>
											<td style="padding-left: 20px">
												<input name="mailAddr"  value="${mailAddr}" type="text" size="40" id="mailAddr" class="validate[custom[email]] text-input "
												maxlength="255" style="width: 270px" /><a onclick="mailto();"
												style="cursor:pointer;">自动搜索配置</a>
												</td>
										</tr>

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<tr>
											<td align="right">签名：</td>
											<td style="padding-left: 20px"><s:textarea rows="5"
													cols="32" id="mailSign" name="mailSign"></s:textarea></td>
										</tr>
									</table></td>
							</tr>
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
							<tr>
								<td style="padding-left: 15px" class="td_detail_title"><b>邮件服务器配置</b>
								</td>
							</tr>
							<tr>
								<td colspan="2"><div class="xuxian"></div>
								</td>
							</tr>
							<!-- 分隔符黑线 -->
							<tr>
								<td height="3px"></td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										style="margin-left: 5px">

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<tr>
											<td width="25%" align="right">发送邮件服务器(SMTP)地址 ：</td>
											<td style="padding-left: 20px"><s:textfield
													id="smtpServer" name="smtpServer" size="40"
													cssStyle="width:270px" /></td>
										</tr>
										<tr>
											<td></td>
											<td style="padding-left: 20px"><input type="checkbox"
												id="chk-needauth" name="isNeedAuth" value="1"
												<s:if test="isNeedAuth eq 1">checked</s:if> />
												&nbsp;发送邮件服务器需要身份验证</td>
										</tr>

										<!-- 状态 -->
										<tr>
											<td align="right">邮箱帐户：</td>
											<td style="padding-left: 20px"><s:textfield
													id="mailUserName" name="mailUserName" size="40"
													cssStyle="width:270px" /></td>
										</tr>

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<tr>
											<td align="right">邮箱密码 ：</td>
											<td style="padding-left: 20px"><s:password
													id="mailUserPwd" name="mailUserPwd" size="40"
													cssStyle="width:270px" /></td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td align="right">发送邮件服务器(SMTP)端口号 ：</td>
											<td style="padding-left: 20px"><s:textfield
													id="mailSmtpPort" name="mailSmtpPort" size="5"
													cssStyle="width:100px" /></td>
										</tr>
										<tr>
											<td></td>
											<td style="padding-left: 20px"><input type="checkbox"
												id="chk-needssl" name="isNeedSsl" value="1" onclick="checkport();"
												<s:if test="isNeedSsl eq 1">checked</s:if> />&nbsp;发送邮件服务器需要安全连接(SSL)
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
									</table></td>
							</tr>
						</table>
					</div></td>

			</tr>
		</table>

		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<!-- 空行 -->
			<tr>
				<td class="td_detail_separator"></td>
			</tr>
			<tr>
				<td width="4px">&nbsp;</td>
				<td align="right"><input type="button" class="btnyh" id="btnSave" style="margin-right:6px;"
					value="保存设置" onclick="subdata()" /></td>
			</tr>

		</table>

	</s:form>
</body>
</html>