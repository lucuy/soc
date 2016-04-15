<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
	<head>
		<link href="${ctx}/styles/left.css" rel="stylesheet" type="text/css">
		 <link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css">
		<script type='text/javascript'
			src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
		<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
		<script type="text/javascript" src="${ctx}/dwr/engine.js"></script>
<script type="text/javascript" src="${ctx}/dwr/interface/MessagePush.js"></script>
<script type='text/javascript' src="${ctx}/dwr/util.js"></script>
<script type="text/javascript" src="${ctx}/scripts/dwrManager.js"></script>
		<script language="javascript">
		 function onLoad(){
	            var userId = '${userinfo}';
	            MessagePush.pageOnLoad(userId);
	       }
	       
	       function showMessage(val){
	       		
	       		if(val!='-1'){
	       			parent.frames[0].LoadTimer();
	       			alert(val);
	       		}else{
	       			parent.frames[0].NewMessage();
	       		}
	       		
	      	 	
	       }
	       dwr.engine.setErrorHandler(function(){});
		</script>
	</head>

	<body onload="dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);onLoad();">
	<div class="titleTop">
			<div style="background-image:url(${ctx}/images/leftDh.jpg)">
				<table width="97%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="25px">
							<img src="${ctx}/images/jtTitle.jpg" alt="" width="25"
								height="28" />
						</td>
						<td class="leftDhxx">
							关于产品
						</td>
					</tr>
				</table>
			</div>
			<table width="99%" border="0" cellspacing="10" cellpadding="10"
				style="margin-left:6px">
			<tr>
				<td align="left">
					<a href="#" class="hand"
						onclick="parent.mainFrame.location.href='${ctx}/about/about.action';">
						&nbsp;
						<img src="${ctx}/images/arrow_03.gif"/>
						&nbsp;<span style="vertical-align:20%">关于产品</span>
					</a>
				</td>
			</tr>
			<tr>
				<td align="left">
					<a href="#" class="hand"
						onclick="parent.mainFrame.location.href='${ctx}/serial/serial.action';">
						&nbsp;
						<img src="${ctx}/images/arrow_03.gif"/>
						&nbsp;<span style="vertical-align:20%">注册码</span>
					</a>
				</td>
			</tr>
		</table>
		</div>
	</body>
</html>
