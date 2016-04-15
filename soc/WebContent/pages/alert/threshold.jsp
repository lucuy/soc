<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@page import="com.lowagie.text.Document"%>
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


<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">
	<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript'
	src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
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
<%-- <script type='text/vbscript' src="${ctx}/scripts/ascii.vbs"></script> --%>
<script type='text/javascript' src="${ctx}/scripts/pinyin.js"></script>
<script language="javascript">

	jQuery(document).ready(function() {
		jQuery("#thresholdForm").validationEngine();
	});

	/* $.ajax({
		type : "post",
		url : "${ctx}/threshold/thresholdEdit.action",
		dataType : "text",
		success : function(result) {
			//obj = eval("(" + result + ")");
			if(result != undefined)
			{
				alert(result);
			}
			
			//$("#cpu_rank option[value="+ obj.cpuRank + "]").attr("selected", "selected");
		}
	}); */
	//设置保存
	function subData() {
		if($('#cpuThreshold').val() == 0) {
			$('#cpuThreshold').val(80) ;
		}
		if($('#cpuTime').val() == 0) {
			$('#cpuTime').val(5) ;
		}
		if($('#hddThreshold').val() == 0) {
			$('#hddThreshold').val(80) ;
		}
		if($('#hddTime').val() == 0) {
			$('#hddTime').val(30) ;
		}
		if($('#ramThreshold').val() == 0) {
			$('#ramThreshold').val(80) ;
		}
		if($('#ramTime').val() == 0) {
			$('#ramTime').val(1) ;
		}
		if($('#flawThresholdUp').val() == 0) {
			$('#flawThresholdUp').val(100) ;
		}
		if($('#flawThresholdDown').val() == 0) {
			$('#flawThresholdDown').val(100) ;
		}
		
		
		$('#thresholdForm').submit();
	}
</script>

</head>

<body style="margin-top: 2px">
	<s:form action="updateThreshold.action" namespace="/threshold"
		method="post" theme="simple" id="thresholdForm" name="thresholdForm">
		<!--  主table-->
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<td width="40%" valign="top">
					<div class="framDiv">
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<!-- 标题栏 -->
							<tr>
								<td class='r2titler' colspan='2'><b>警告阀值设置信息</b>
								</td>
							</tr>
							<!-- 阀值设置信息 -->
							<!-- CPU阀值设置 -->
							<tr>
								<td align="center"><font color="red"><s:actionmessage />
								</font>
								</td>
							</tr>
							<tr>
								<td>
									<table width="99%" border="0" align="center" cellspacing="0"
										cellpadding="0">
										<!-- 空行 -->
										<tr>
											<td height="10px"></td>
										</tr>
										<tr>
											<td align="left" style="padding-left: 15px">CPU设置</td>
										</tr>
										<tr>
											<td height="10px"></td>
										</tr>
										<!-- 虚线分割线 -->
										<tr>
											<td colspan="9">
												<div class="xuxian"></div>
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td height="30px"></td>
										</tr>
										<tr>
											<td align="right" width="20%">阀值：</td>
											<td align="left" width="35px"><input type="text"
												width="35px" id="cpuThreshold"
												name="thresholdSr.cpuThreshold"
												value="${thresholdSr.cpuThreshold}"
												class="validate[custom[globalno1]] text-input" />
											</td>
											<td align="left">%</td>

											<td align="right" width="20%">时间：</td>
											<td align="left" width="35px"><input type="text"
												width="35px" id="cpuTime" name="thresholdSr.cpuTime"
												value="${thresholdSr.cpuTime }"
												class="validate[custom[onlyNumberSp]] text-input" /></td>
											<td align="left">秒</td>

											<td align="right" width="20%">级别：</td>
											<td align="left" width="35px"><select id="cpuRank"
												name="thresholdSr.cpuRank" class="jianju">
													<option value="0" <c:if test="${thresholdSr.cpuRank eq 0}">selected="selected"</c:if>>0</option>
													<option value="1" <c:if test="${thresholdSr.cpuRank eq 1}">selected="selected"</c:if>>1</option>
													<option value="2" <c:if test="${thresholdSr.cpuRank eq 2}">selected="selected"</c:if>>2</option>
													<option value="3" <c:if test="${thresholdSr.cpuRank eq 3}">selected="selected"</c:if>>3</option>
													<option value="4" <c:if test="${thresholdSr.cpuRank eq 4}">selected="selected"</c:if>>4</option>
													<option value="5" <c:if test="${thresholdSr.cpuRank eq 5}">selected="selected"</c:if>>5</option>
													<option value="6" <c:if test="${thresholdSr.cpuRank eq 6}">selected="selected"</c:if>>6</option>
													<option value="7" <c:if test="${thresholdSr.cpuRank eq 7}">selected="selected"</c:if>>7</option>
													<option value="8" <c:if test="${thresholdSr.cpuRank eq 8}">selected="selected"</c:if>>8</option>
													<option value="9" <c:if test="${thresholdSr.cpuRank eq 9}">selected="selected"</c:if>>9</option>
													<option value="10" <c:if test="${thresholdSr.cpuRank eq 10}">selected="selected"</c:if>>10</option>
											</select> </td>
											<td align="left" style="padding-right: 60px;"></td>
										</tr>
										<tr>
											<td height="10px"></td>
										</tr>
									</table>
								</td>
							</tr>
							<!-- 硬盘阀值设置 -->
							<tr>
								<td>
									<table width="99%" border="0" align="center" cellspacing="0"
										cellpadding="0">
										<!-- 空行 -->
										<tr>
											<td height="10px"></td>
										</tr>
										<tr>
											<td align="left" style="padding-left: 15px">硬盘设置</td>
										</tr>
										<tr>
											<td height="10px"></td>
										</tr>
										<!-- 虚线分割线 -->
										<tr>
											<td colspan="9">
												<div class="xuxian"></div>
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td height="20px"></td>
										</tr>
										<tr align="center">
											<td align="right" width="20%">阀值：</td>
											<td align="left" width="35px"><input type="text"
												width="35px" id="hddThreshold"
												name="thresholdSr.hddThreshold"
												value="${ thresholdSr.hddThreshold}"
												class="validate[custom[globalno1]] text-input" /></td>
											<td align="left">%</td>

											<td align="right" width="20%">时间：</td>
											<td align="left" width="35px"><input type="text"
												width="35px" id="hddTime" name="thresholdSr.hddTime"
												value="${thresholdSr.hddTime }"
												class="validate[custom[onlyNumberSp]] text-input" /></td>
											<td align="left">天</td>

											<td align="right" width="20%">级别：</td>
											<td align="left" width="35px"><select id="hddRank"
												name="thresholdSr.hddRank" class="jianju">
													<option value="0" <c:if test="${thresholdSr.hddRank eq 0}">selected="selected"</c:if>>0</option>
													<option value="1" <c:if test="${thresholdSr.hddRank eq 1}">selected="selected"</c:if>>1</option>
													<option value="2" <c:if test="${thresholdSr.hddRank eq 2}">selected="selected"</c:if>>2</option>
													<option value="3" <c:if test="${thresholdSr.hddRank eq 3}">selected="selected"</c:if>>3</option>
													<option value="4" <c:if test="${thresholdSr.hddRank eq 4}">selected="selected"</c:if>>4</option>
													<option value="5" <c:if test="${thresholdSr.hddRank eq 5}">selected="selected"</c:if>>5</option>
													<option value="6" <c:if test="${thresholdSr.hddRank eq 6}">selected="selected"</c:if>>6</option>
													<option value="7" <c:if test="${thresholdSr.hddRank eq 7}">selected="selected"</c:if>>7</option>
													<option value="8" <c:if test="${thresholdSr.hddRank eq 8}">selected="selected"</c:if>>8</option>
													<option value="9" <c:if test="${thresholdSr.hddRank eq 9}">selected="selected"</c:if>>9</option>
													<option value="10" <c:if test="${thresholdSr.hddRank eq 10}">selected="selected"</c:if>>10</option>
											</select></td>
											<td align="left" style="padding-right: 60px;"></td>
										</tr>
										<tr>
											<td height="10px"></td>
										</tr>
									</table>
								</td>
							</tr>
							<!-- 内存阀值设置 -->
							<tr>
								<td>
									<table width="99%" border="0" align="center" cellspacing="0"
										cellpadding="0">
										<!-- 空行 -->
										<tr>
											<td height="10px"></td>
										</tr>
										<tr>
											<td align="left" style="padding-left: 15px">内存设置</td>
										</tr>
										<tr>
											<td height="10px"></td>
										</tr>
										<!-- 虚线分割线 -->
										<tr>
											<td colspan="9">
												<div class="xuxian"></div>
											</td>
										</tr>
										<tr>
											<td height="30px"></td>
										</tr>
										<tr align="center">
											<td align="right" width="20%">阀值：</td>
											<td align="left" width="35px"><input type="text"
												width="35px" id="ramThreshold"
												name="thresholdSr.ramThreshold"
												value="${thresholdSr.ramThreshold }"
												class="validate[custom[globalno1]] text-input" /></td>
											<td align="left">%</td>

											<td align="right" width="20%">时间：</td>
											<td align="left" width="35px"><input type="text"
												width="35px" id="ramTime" name="thresholdSr.ramTime"
												value="${thresholdSr.ramTime }"
												class="validate[custom[onlyNumberSp]] text-input" />
											<td align="left">分钟</td>

											<td align="right" width="18%">级别：</td>
											<td align="left" width="35px"><select id="ramRank"
												name="thresholdSr.ramRank" class="jianju">
													<option value="0" <c:if test="${thresholdSr.ramRank eq 0}">selected="selected"</c:if>>0</option>
													<option value="1" <c:if test="${thresholdSr.ramRank eq 1}">selected="selected"</c:if>>1</option>
													<option value="2" <c:if test="${thresholdSr.ramRank eq 2}">selected="selected"</c:if>>2</option>
													<option value="3" <c:if test="${thresholdSr.ramRank eq 3}">selected="selected"</c:if>>3</option>
													<option value="4" <c:if test="${thresholdSr.ramRank eq 4}">selected="selected"</c:if>>4</option>
													<option value="5" <c:if test="${thresholdSr.ramRank eq 5}">selected="selected"</c:if>>5</option>
													<option value="6" <c:if test="${thresholdSr.ramRank eq 6}">selected="selected"</c:if>>6</option>
													<option value="7" <c:if test="${thresholdSr.ramRank eq 7}">selected="selected"</c:if>>7</option>
													<option value="8" <c:if test="${thresholdSr.ramRank eq 8}">selected="selected"</c:if>>8</option>
													<option value="9" <c:if test="${thresholdSr.ramRank eq 9}">selected="selected"</c:if>>9</option>
													<option value="10" <c:if test="${thresholdSr.ramRank eq 10}">selected="selected"</c:if>>10</option>
											</select> </td>
											<td align="left" style="padding-right: 60px;"></td>
										</tr>
										<tr>
											<td height="10px"></td>
										</tr>
									</table>
								</td>
							</tr>
							<!-- 流量阀值设置 -->
							<tr>
								<td>
									<table width="99%" border="0" align="center" cellspacing="0"
										cellpadding="0">
										<!-- 空行 -->
										<tr>
											<td height="10px"></td>
										</tr>
										<tr>
											<td align="left" style="padding-left: 15px">发送流量设置</td>
										</tr>
										<tr>
											<td height="10px"></td>
										</tr>
										<!-- 虚线分割线 -->
										<tr>
											<td colspan="9">
												<div class="xuxian"></div>
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td height="10px"></td>
										</tr>
										<tr align="center">
											<td align="right" width="20%">发送流量：</td>
											<td align="left" width="35px"><input type="text"
												width="35px" id="flawThresholdUp"
												name="thresholdSr.flawThresholdUp"
												value="${thresholdSr.flawThresholdUp }"
												class="validate[custom[settingNumber]] text-input" /></td>
											<td align="left">B/S</td>
											
											<td align="right" width="10%">级别：</td>
											<td align="left" width="35px"><select id="flowRankUp"
												name="thresholdSr.flowRankUp" class="jianju">
													<option value="0" <c:if test="${thresholdSr.flowRankUp eq 0}">selected="selected"</c:if>>0</option>
													<option value="1" <c:if test="${thresholdSr.flowRankUp eq 1}">selected="selected"</c:if>>1</option>
													<option value="2" <c:if test="${thresholdSr.flowRankUp eq 2}">selected="selected"</c:if>>2</option>
													<option value="3" <c:if test="${thresholdSr.flowRankUp eq 3}">selected="selected"</c:if>>3</option>
													<option value="4" <c:if test="${thresholdSr.flowRankUp eq 4}">selected="selected"</c:if>>4</option>
													<option value="5" <c:if test="${thresholdSr.flowRankUp eq 5}">selected="selected"</c:if>>5</option>
													<option value="6" <c:if test="${thresholdSr.flowRankUp eq 6}">selected="selected"</c:if>>6</option>
													<option value="7" <c:if test="${thresholdSr.flowRankUp eq 7}">selected="selected"</c:if>>7</option>
													<option value="8" <c:if test="${thresholdSr.flowRankUp eq 8}">selected="selected"</c:if>>8</option>
													<option value="9" <c:if test="${thresholdSr.flowRankUp eq 9}">selected="selected"</c:if>>9</option>
													<option value="10" <c:if test="${thresholdSr.flowRankUp eq 10}">selected="selected"</c:if>>10</option>
											</select></td>
											
											<td align="right" width="11%"></td>
											<td align="left" width="35px"></td>
											<td align="left"></td>
											
											<td align="left" style="padding-right: 14px;"></td>
											<td align="left"></td>
										</tr>
										<tr>
											<td height="20px"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table width="99%" border="0" align="center" cellspacing="0"
										cellpadding="0">
										<!-- 空行 -->
										<tr>
											<td height="10px"></td>
										</tr>
										<tr>
											<td align="left" style="padding-left: 15px">发送流量设置</td>
										</tr>
										<tr>
											<td height="10px"></td>
										</tr>
										<!-- 虚线分割线 -->
										<tr>
											<td colspan="9">
												<div class="xuxian"></div>
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td height="10px"></td>
										</tr>
										<tr align="center">
											
											<td align="right" width="20%">接收流量：</td>
											<td align="left" width="35px"><input type="text"
												width="35px" id="flawThresholdDown"
												name="thresholdSr.flawThresholdDown"
												value="${thresholdSr.flawThresholdDown }"
												class="validate[custom[settingNumber]] text-input" /></td>
											<td align="left">B/S</td>

											<td align="right" width="10%">级别：</td>
											<td align="left" width="35px"><select id="flowRankDown"
												name="thresholdSr.flowRankDown" class="jianju">
													<option value="0" <c:if test="${thresholdSr.flowRankDown eq 0}">selected="selected"</c:if>>0</option>
													<option value="1" <c:if test="${thresholdSr.flowRankDown eq 1}">selected="selected"</c:if>>1</option>
													<option value="2" <c:if test="${thresholdSr.flowRankDown eq 2}">selected="selected"</c:if>>2</option>
													<option value="3" <c:if test="${thresholdSr.flowRankDown eq 3}">selected="selected"</c:if>>3</option>
													<option value="4" <c:if test="${thresholdSr.flowRankDown eq 4}">selected="selected"</c:if>>4</option>
													<option value="5" <c:if test="${thresholdSr.flowRankDown eq 5}">selected="selected"</c:if>>5</option>
													<option value="6" <c:if test="${thresholdSr.flowRankDown eq 6}">selected="selected"</c:if>>6</option>
													<option value="7" <c:if test="${thresholdSr.flowRankDown eq 7}">selected="selected"</c:if>>7</option>
													<option value="8" <c:if test="${thresholdSr.flowRankDown eq 8}">selected="selected"</c:if>>8</option>
													<option value="9" <c:if test="${thresholdSr.flowRankDown eq 9}">selected="selected"</c:if>>9</option>
													<option value="10" <c:if test="${thresholdSr.flowRankDown eq 10}">selected="selected"</c:if>>10</option>
											</select></td>
											
											<td align="right" width="11%"></td>
											<td align="left" width="35px"></td>
											<td align="left"></td>
											
											<td align="left" style="padding-right: 14px;"></td>
											<td align="left"></td>
										</tr>
										<tr>
											<td height="20px"></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div></td>
			</tr>
		</table>
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="align:right">
			<!-- 空行 -->
			<tr>
				<td class="td_detail_separator"></td>
			</tr>

			<tr>
				<td align="right"><input type="button" class="btnyh"
					id="btnSave" value="保 存 设 置" onclick="subData();" /></td>
			</tr>
		</table>
	</s:form>
</body>
</html>