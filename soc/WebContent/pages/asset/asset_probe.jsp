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

<link href="${ctx}/styles/asset/assetinfo.css" rel="stylesheet"
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

<script language="javascript">
	function up(ip){
$("#"+ip).click(function(){
var str = window.location.href;//当前URL地址
var es = /ids=/;
es.exec(str);
var right = RegExp.rightContext;
var mode=$(this).find("td").eq(1).text();
var mode1=$(this).find("td").eq(2).text();
var mode2=$(this).find("td").eq(3).text();
var mode3=$(this).find("td").eq(4).text();
location.href = "${ctx}/assetProbeTask/byValue.action?deviceType="+ mode +"&deviceIp="+ mode2 +"&deviceMemo="+ mode1 +"&deviceMac="+ mode3 +"&right="+right;
  $(this).find("td").each(function(i){
  });

});
}
	//生产xml
	function updateXML(beginIp, endIp) {

		$('#beginIp').val($.trim($('#beginIp').val()));
		$('#endIp').val($.trim($('#endIp').val()));
		$('#sshIp').val($.trim($('#sshIp').val()));
		$('#sshprot').val($.trim($('#sshprot').val()));
		location.href = "${ctx}/assetProbe/outXml.action?beginIp="+ encodeURI(encodeURI($('#beginIp').val() +"&endIp=" + encodeURI(encodeURI($('#endIp').val())) + "&sshIp="+ encodeURI(encodeURI($('#sshIp').val()) + "&sshprot="+ encodeURI(encodeURI($('#sshprot').val()), "utf-8"))));
	}
</script>

</head>

<body style="margin-top:2px;">
	<table width="99%" border="0" cellspacing="0" cellpadding="0"
		style="margin-left: 4px; margin-top: 0px">
		<!-- 空行 -->
		<tr>
			<td></td>
		</tr>
		<tr>
			<td>
				<div class="box">
					<h3>资产信息</h3>
					<div class="right"></div>

				</div>
			</td>

		</tr>
		<tr>
			<td>
				<div class="sbox">
					<div class="cont">
						<!-- information area -->
						<div id="dataList">
							<table width="100%" border="0" cellspacing="1" cellpadding="0"
								class="tab2">
								<tr height="28" class="biaoti">
									<td width="6%" class="biaoti"><input type="checkbox"
										id="chkAll" name="chkAll" class="check-box not_checked" /></td>
									<td width="17%" class="biaoti">设备类型</td>
									<td width="17%" class="biaoti">设备描述</td>
									<td width="17%" class="biaoti">Ip地址</td>
									<td width="17%" class="biaoti">MAC地址</td>
									<td width="26%" class="biaoti">操作</td>
								</tr>
								<s:iterator value="#session.assetProbeList"
									status="assetProbeList" id="slist">
									<input type="hidden" name="assetDeviceType"
										id="assetDeviceType"
										value='<s:property value='#assetProbeList.index'/>' />
									<tr id="<s:property value='#assetProbeList.index'/>">
										<td class="biaocm" valign="middle"><input type="checkbox"
											name="<s:property value='#assetProbeList.index'/>"
											id="<s:property value='#assetProbeList.index'/>"
											value="<s:property value='#assetProbeList.index'/>"
											class="check-box" /></td>

										<td valign="middle">${slist.deviceType}</td>

										<td valign="middle">${slist.deviceMemo}</td>

										<td valign="middle" align="center">${slist.deviceIp}</td>

										<td valign="middle" align="center">${slist.deviceMac}</td>

										<td valign="middle" align="center"><input type="button"
											value="编辑"
											onclick="up(<s:property value='#assetProbeList.index'/>)" />
										</td>

									</tr>
								</s:iterator>
								<tr>
									<td colspan="7" width="100%"><jsp:include
											page="../commons/page.jsp"></jsp:include></td>
								</tr>
							</table>
						</div>
						<!-- information area -->
					</div>
				</div>
			</td>
		</tr>
	</table>

	<!-- 导入form -->

	<!-- 选择文件对话框 -->
	<!-- over layer -->
	<div id="blk" style="display:none;">
		<div class="blk">
			<div class="head">
				<div class="head-right"></div>
			</div>
			<div class="main">
				<h2 id="blk_header">#header#</h2>
				<div id="blk_content">#content#</div>
			</div>
			<div class="foot">
				<div class="foot-right"></div>
			</div>
		</div>
	</div>

	<!-- ui-dialog -->
</body>
</html>
