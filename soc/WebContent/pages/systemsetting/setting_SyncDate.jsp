<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

<link href="${ctx}/styles/systemsetting/systemsetting.css"
	rel="stylesheet" type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<script type='text/javascript'
	src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
<script>
	function subdata() {
		var len = document.getElementById("syncDate").value.length;
		if ($("#syncDate").val() == "") {

			alert("请输入NTP服务器地址...");
			$('#syncDate').focus();
			return;
		} else if (len > 50) {

			alert("地址长度必须小于50");
			return;

		} else {
            if (confirm("执行此操作后,系统将会重启，确认执行此操作吗？")) {
			    $("form").submit();
		}
			

		}

	}

	function setTime() {

		var val = $("#installTime").val();
		if (val == "" || val == null) {
			alert("请选择日期...");
			return;
		} else {
			$.post("${ctx}/syncDate/installTime.action", {
				"installTime" : val
			}, function(data) {

				alert("时间设置成功!系统稍后会重启...");
			});

		}

	}
	
	function syncServerDate(){
		
		if(window.confirm("注意：时间同步会修改所有审计设备的时间，请谨慎操作")){
			$.post("${ctx}/syncDate/syncServerDate.action",{},function(obj){
				
				if(1==obj){
					alert("时间同步成功！");
				}
				
			});
		}
		
		
	}
	
	
</script>
</head>

<body style="margin-top: 2px">
	<s:form id="setting-checking" action="settingSyncDate"
		namespace="/syncDate" method="post" theme="simple" name="">
		<!--  左侧table-->
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<td width="100%" valign="top">
					<div class="framDiv">
						<!--  左侧table-->
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td class="r2titler"><b>同步系统时间</b>&nbsp;&nbsp;&nbsp; <font
									id="message1"></font>
								</td>
							</tr>

							<tr>
								<td align="right"><font color="red"><s:actionmessage />
								</font></td>
							</tr>

							<tr>
								<td>
									<table width="90%" border="0" cellspacing="0" cellpadding="0"
										style="margin-left: 18px;">
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td width="20%">当前时间：</td>
											<td align="left" width="60%"><s:date name="currenDate"
													format="yyyy年MM月dd日  HH:mm:ss" />
													
													<!-- 审计设备同步时间 屏蔽该功能。只为测评中心功能-->
													<!-- <input type="button" class="btnyh2"
													value="时间同步" onclick="syncServerDate();" />
													 -->
													</td>

										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td width="20%">变更时间：</td>
											<td align="left" width="60%"><input name="installTime"
												class="Wdate" id="installTime" type="text"
												style="width: 270px;"
												onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"
												readonly="readonly" /> <input type="button" class="btnyh2"
												value="更改" onclick="setTime();" /></td>



										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td width="20%">NTP地址：</td>
											<td align="left" width="60%"><input type="text"
												id="syncDate" name="syncDate" style="width:270px;"
												value="${synceDateQuery }" /> <input type="button"
												id="syncDate" value="同步" onclick="subdata();" class="btnyh2" />
												&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${msg}</font></td>

										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

									</table>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>

			<tr>
				<td class="td_detail_separator"></td>
			</tr>
		</table>
	</s:form>
</body>
</html>