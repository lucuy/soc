<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
	<head>
		<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
		<script type='text/javascript'
			src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
			<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
     	<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine.js"></script>
      	<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
    	<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet" type="text/css">
      	<script>
        	jQuery(document).ready(function(){               
            	jQuery("#setting-syslog").validationEngine();
          	});
          	function subdata(){
           		if($('#upFile').val()==""){
           			alert('请选择Licence文件');
           			return;
           		}
				$("form").submit();
           }
     	</script>
	</head>

	<body style="margin-top: 2px">
	  <center>
	  <s:form id="serial" action="initRegister.action" namespace="/serial" method="post" theme="simple" name="serial" enctype="multipart/form-data">
		<!--  左侧table-->
		<table width="40%" border="0" cellspacing="0" cellpadding="0" style="margin-left: 8px; margin-top: 0px">
		<tr height="50"></tr>
		<tr>
			<td width="500px" valign="top">
				<div class="framDiv">
				
				<!--  左侧table-->
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td class="r2titler">
						<b class="fontStyle">产品注册</b>&nbsp;&nbsp;&nbsp;
						<font id="message1"></font>
					</td>
				</tr>
				
				<tr>
					<td align="center"><font color="red"><s:actionmessage /> </font></td>
				</tr>	
						
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-left: 18px;">
							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator">
								</td>
							</tr>
							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator">
								</td>
							</tr>		
							
							<tr>
								<td align="right">
									<b>硬件特征码</b>
								</td>
								<td width="20"></td>
								<td align="left">
									<input type="text" style="width:300px" id="hardwareCode" readonly name="hardwareCode" value="${hardwareCode}"/>
								</td>
							</tr>
							
							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator">
								</td>
							</tr>	
							
							<tr>
								<td width="15%" align="right">
									<b>注册文件</b>
								</td>
								<td width="20"></td>
								<td align="left">
									<input id="upFile"  name="upFile" type="file"/>
								</td>
							</tr>
							
							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator" colspan="2">
								</td>
							</tr>	
							
							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator" colspan="2">
								</td>
							</tr>	
							
						</table>
					</td>
				</tr>
			</table>
			</div>
		</td>
		</tr>
	
		<tr>
			<td class="td_detail_separator">
			</td>
		</tr>
		<tr>
			<td colspan="3" align="center">
				<input type="button" class="btnyh" id="btnSave" value="提交注册码"  onclick="subdata()"/>&nbsp;&nbsp;
				<input type="button" class="btnyh" id="btnSave" value="退出系统"  onclick="location.href='/';"/>
			</td>
		</tr>
		</table>
		</s:form>
	  </center>
	</body>
</html>
