<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8" import="java.lang.*"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
<head>
<title>等级保护合规管理</title>
<link href="${ctx}/css/top.css" rel="stylesheet" type="text/css">
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css">

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<link type="text/css" href="${ctx}/css/ui-lightness/jquery-ui-1.7.3.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/scripts/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/js/jquery-ui-1.7.3.custom.min.js"></script>
<script language="javascript">
	//getAlertTrance();
	var count = 0;
	$(document).ready(function() {
		$("#dialog-extQuery").dialog({
			autoOpen : false,
			minWidth : 600,
			minHeight : 460,
			buttons : {
				"确定[Enter]" : function() {
					extInsert(0);
					$(this).dialog("close");
				},

				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		$("ul li a").click(function() {
			$("ul li a").css("color", "");
			$(this).css("color", "#faec06");
		});
	});
	 function extQueryDlg() {
		$("#dialog-extQuery").dialog("open");
	}
	function extInsert(){
	}
function linkTo(linkType) {

//基础数据
if(linkType=='basicInfo'){
 
 parent.leftFrame.location.href='${ctx}/pages/menus/basicMenu1.jsp';
 parent.mainFrame.location.href='${ctx}/unitInfo/query.action';
 
}

//定级备案
if(linkType=='score'){
 parent.leftFrame.location.href='${ctx}/sysAbolish/queryForJson.action';
 parent.mainFrame.location.href='${ctx}/rank/queryRank.action';
}

//合规管理
if(linkType=='complianceManagement')
{
parent.leftFrame.location.href='${ctx}/pages/menus/cpManagementMenu1.jsp';
parent.mainFrame.location.href='${ctx}/summaryAnalysis/projectShowcase.action';
}

//审计管理
if(linkType=='auditInfo'){
parent.leftFrame.location.href='${ctx}/pages/menus/audit1.jsp';
parent.mainFrame.location.href='${ctx}/pages/audit/operation_audit.jsp';
}

//系统设置
if(linkType=='systemInfo')
{
parent.leftFrame.location.href='${ctx}/pages/menus/sysInfoMenu1.jsp';
parent.mainFrame.location.href='${ctx}/serial/serial.action';
//parent.mainFrame.location.href='${ctx}/serial/initSerial.action';
}
if(linkType=='rank')
{
parent.leftFrame.location.href='${ctx}/pages/menus/allMenus.jsp';
//parent.mainFrame.location.href='${ctx}/serial/serial.action';
//parent.mainFrame.location.href='${ctx}/serial/initSerial.action';
}

}

	function logout() {
		if (confirm("你确认要注销吗？")) {
			location.href = '${ctx}/login/logout.action';
		}
	}
	
	function blink() {
		//$('#AlarmCount').css("visibility", "visible");
		//$('#soccer').show(2000);
		var temp = ($('#AlarmCount').css("visibility") == "hidden") ? "visible"
				: "hidden";
		if (temp == "hidden") {
			$('#AlarmCount').css("visibility", "hidden");
		} else {
			$('#AlarmCount').css("visibility", "visible");
		}
		//counter += 1;
		//if (flag == 1) {
		//alert("1243");
		setTimeout("blink()", 500);
		//} else {
		//$('#soccer').css("visibility", "visible");
		//} 
	}
	
	function reload() {
        document.onmousedown = ContextMenu;
        $("#mack").addClass("ui-widget-overlay");
    }
    
	 function editInfo(id){
		parent.mainFrame.location.href="${ctx}/employee/employeeInfo.action?userId=" + id;
	} 
	
    function cancel()
        {
            document.onmousedown=ContextMenu;
            $("#mack").removeClass("ui-widget-overlay");
        }
    
    function ContextMenu() {

        if (event.button == 2 || event.button == 3) {
            alert("升级中无法操作");
            return false;
        }
    }
</script>
</head>

<body>
	<center>
		<!-- banner area -->
		<table width="100%" height="65px" border="0" cellpadding="0" cellspacing="0" background="${ctx}/images/topRbj.jpg">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" height="65px;">
						<tr>
							<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td valign="top" align="left"><img src="${ctx}/images/logo1.jpg" align="left" /></td>
										<td><font style="position:absolute;left:75px;top:20px; font-size: 30px; color: #4D8ABC; padding-top: 6px;"><b>捷成安全管理与综合审计系统</b>
										</font></td>
										<td align="right">
											<div class="topinfo">
												<table width="100%" border="0" cellpadding="0" cellspacing="0">
													<tr>
														<td class="topL1">
															<table width="70%" border="0" align="right" cellpadding="0" cellspacing="0">
																<tr>
																	<td align="left">帐&nbsp;&nbsp;号：<s:property value="#session.SSI_LOGIN_USER" /></td>
																</tr>
																<tr>
																	<td align="left" title="">角&nbsp;&nbsp;色：<s:property value="#session.SSI_LOGIN_userAuthority" />管理员
																</td>
																</tr>
															</table></td>
														<td><input name="" type="button" class="topL2" value="个人设置" onclick="editInfo(<s:property value="#session.SSI_LOGIN_ID" ></s:property>)" />
														</td>
														<td><input name="input" type="button" class="topL3" value="注销" onclick="logout();" />
														</td>
													</tr>
												</table>
											</div></td>
									</tr>
								</table>
							</td>
							<td width="1%">&nbsp;</td>
						</tr>
					</table></td>
			</tr>
		</table>
		<!-- banner area -->
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="frameDh">
				<ul>
					<li>
						<a href="javascript:linkTo('rank');">等级保护合规管理</a>
					</li>
				</ul>
					<%-- <ul>
						 <li>
						<a href="javascript:linkTo('basicInfo');">基础数据</a>
						</li>
					系统管理员
					<c:if test="${sessionScope.SSI_LOGIN_AuthorityID==0}">
						<li style="width:5px"><span style="color:#FFFFFF;">|</span></li>
						 <li>
						<a href="javascript:linkTo('auditInfo');">审计管理</a>
						</li>
						<li style="width:5px"><span style="color:#FFFFFF;">|</span></li>
						 <li>
						<a href="javascript:linkTo('systemInfo');">系统设置</a>
						</li>
						
					</c:if>
				      安全管理员、超级管理员
				      <c:if test="${sessionScope.SSI_LOGIN_AuthorityID==1||sessionScope.SSI_LOGIN_AuthorityID==2}">
						<li style="width:5px"><span style="color:#FFFFFF;">|</span></li>
						<li><a href="javascript:linkTo('score');">定级备案</a>
						</li>
						<li style="width:5px"><span style="color:#FFFFFF;">|</span></li>
						<li><a href="javascript:linkTo('complianceManagement');">合规管理</a>
						</li>
						<li style="width:5px"><span style="color:#FFFFFF;">|</span></li>
						 <li>
						<a href="javascript:linkTo('auditInfo');">审计管理</a>
						</li>
						<c:if test="${sessionScope.SSI_LOGIN_AuthorityID==2}">
							<li style="width:5px"><span style="color:#FFFFFF;">|</span></li>
						 <li>
						<a href="javascript:linkTo('systemInfo');">系统设置</a>
						</li>
						</c:if>
				      </c:if>
				      
				      
				    </ul> --%></td>
				<td width="15%" class="frameDh" align="right"></td>
			</tr>

		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" style="display: none">
			<tr>
				<td class="frameDhfoot">
					<ul>
						<li><a href="#"></a>
						</li>

						<li><a href="#"></a>
						</li>
					</ul></td>
			</tr>
		</table>
	</center>
	<div class="ui-overlay">
		<div id="mack"></div>
	</div>
	
</body>
</html>
