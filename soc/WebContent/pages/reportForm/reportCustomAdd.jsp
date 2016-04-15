<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="mainoffer.inc"%>
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
	
		<title>自定义报表</title>
		<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
		<script type='text/javascript'
			src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
			<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
	<script type="text/javascript">
	var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
		function sub(){
			if($('#rcname').val().length>50){
				alert("报表名称长度不能大于50");
				$('#rcname').val('');
				$('#rcname').focus();
				return ;
			}
			if(!rege.test($('#rcname').val())){
				alert("输入的内容包含特殊字符，请重新输入");
				$('#rcname').val('');
				$('#rcname').focus();
				return;
			}
			if($('#memo').val().length>50){
				alert("搜索长度不能大于50");
				$('#memo').val('');
				$('#memo').focus();
				return ;
			}
			document.ReportCustomForm.submit();
		}
		function cancel(){
			window.location = "${ctx}/reportCustom.do?method=initPage";
		}
	</script>
	</head>

	<body>
		<html:form action="reportCustom.do?method=add" method="post">
				<!--  主table-->
		<table border="0" cellspacing="0" cellpadding="0"
			style="width:45%; margin-left: 4px; margin-top: 2px">
			<tr>
				<td valign="top">
					<div class="framDiv">

						<!--  左侧table-->
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td class="r2titler" colspan="2">
							<b>自定义报表</b>
						</td>
					</tr>


					<!-- 空行 -->
					<tr>
						<td class="td_detail_separator">
						</td>
					</tr>
				
					<tr> 
						<td align="right" width="25%" >
							报表名称：
						</td>
						<td style="padding-left: 10px">
						<input name="rc.name" id="rcname"  style="width:225px;margin-left: -10px;">
							<%-- <html:text property="rc.name"  onblur="" style="width:225px;"></html:text>--%>
						</td>
					</tr>
					<tr>
						<td class="td_detail_separator">
							&nbsp;
						</td>
					</tr>
					<tr> 
						<td align="right" width="25%" >
							sql语句：
						</td>
						<td style="padding-left: 10px">
							<s:textarea name="rc.customSql" rows="4" cols="30" value="" >
							</s:textarea>
						</td>
					</tr>
					
					
					
					<tr>
						<td class="td_detail_separator">
							&nbsp;
						</td>
					</tr>
					<tr> 
						<td align="right" width="25%" >
							描述：
						</td>
						<td style="padding-left: 10px">
							<s:textarea name="rc.memo" id="memo" rows="4" cols="30"></s:textarea>
						</td>
					</tr>
					<!-- 空行 -->
					<tr>
						<td class="td_detail_separator">
						</td>
					</tr>
				</table>
				</div>
				<tr><td>
				<table width="45%" border="0" cellspacing="0" cellpadding="0">
				<!-- 空行 -->
				<tr>
					<td height="8">
					</td>
				</tr>

				<tr>
					<td align="left">
						<input type="button" class="btnyh" id="btnSave" value="保  存" onclick="sub();" />
						<input type="button" class="btnyh" id="btnCancel" value="取  消" onclick="cancel()" />
					</td>
				</tr>
			</table>
			</td>
			</tr>
			</table>
		</html:form>
	</body>
</html>
