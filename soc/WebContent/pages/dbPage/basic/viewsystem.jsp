<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>修改信息系统</title>
<meta http-equiv="Cache-Control" content="private" />
<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<SCRIPT src="${ctx}/scripts/jquery-1.7.2.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/jquery-ui.custom.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/util.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/popuplayer.js" type=text/javascript></SCRIPT>
<script type="text/javascript" src="${ctx}/scripts/check.js"></script>
<script type="text/javascript">
	function checkName() {
		if ($.trim($("#sysName1").val()) == $.trim($("#sysName2").val())) {
			return false;
		};
		var sysName = $.trim($("#sysName1").val());
		if (sysName == "") {
			return false;
		} else {
			$.post('${ctx}/system/queryAjaxSysName.action', {
				sysName : sysName
			}, function(data) {
				if (data > 0) {
					alert("系统名称已存在！");
					$("#sysName1").val("");
					$("#sysName1").focus();
					return false;
				} else {
					return true;
				};
			});
		}
	}
	function saveUnitBaseinfo() {
		if (!checkName) {
			return false;
		}
		var btnSub=document.getElementById("btnSub");
   	    btnSub.disabled="disabled";
		if ("" == $.trim($("#sysName1").val())) {
			alert("系统名称不能为空");
			$("#sysName1").focus();
			btnSub.disabled="";
			return false;
		} else if($.trim($("#sysName1").val()).length > 20) {
			alert("系统名称不能大于20个字符");
			$("#sysName1").focus();
			btnSub.disabled="";
			return false;
		}else if (document.getElementById("ywlxrdo").checked
				&& "" == $("#ywlxval").val()) {
			//业务类型选择了其他
			alert("请输入该系统的业务类型");
			btnSub.disabled="";
			$("#ywlxval").focus();
			return false;
		} else if (document.getElementById("ywlxrdo").checked
				&& $("#ywlxval").val().length >20) {
			//业务类型选择了其他
			alert("该系统的业务类型名称不能大于20个字符");
			btnSub.disabled="";
			$("#ywlxval").focus();
			return false;
		} else if (document.getElementById("ksrdo").checked
				&& "" == $("#ksrval").val()) {
			//跨省

			alert("请输入该系统服务范围跨省个数！");
			btnSub.disabled="";
			$("#ksrval").focus();
			return false;
		} else if ($("#ksrval").val() >2862) {
			alert("全国有省级34个，地级333个，县级2862个,请重新输入！");
			btnSub.disabled="";
			$("#ksrval").focus();
			return false;
		}else if (document.getElementById("kdrdo").checked
				&& "" == $("#kdval").val()) {
			//跨地

			alert("请输入该系统服务范围跨地区个数！");
			btnSub.disabled="";
			$("#kdval").focus();
			return false;
		} else if (document.getElementById("fwfwdo").checked
				&& "" == $("#fwfwval").val()) {
			//其他

			alert("请输入该系统服务范围");
			btnSub.disabled="";
			$("#fwfwval").focus();
		} else if (document.getElementById("fwfwdo").checked
				&& $("#fwfwval").val().length > 20) {
			//其他

			alert("该系统服务范围名称不能大于20个字符");
			btnSub.disabled="";
			$("#fwfwval").focus();
		} else if (document.getElementById("dxrdo").checked
				&& "" == $("#dxrval").val()) {
			//服务范围
			alert("请输入该系统服务对象");
			btnSub.disabled="";
			$("#dxrval").focus();
		}  else if (document.getElementById("dxrdo").checked
				&& $("#dxrval").val().length >20) {
			//服务范围
			alert("该系统服务对象名称不能大于20个字符");
			btnSub.disabled="";
			$("#dxrval").focus();
		} else {
		    if(document.getElementById("busDescription").value.length>1000){
		      alert("您输入的业务描述长度超过1000!!!");
		      btnSub.disabled="";
		      return;
		    }
			document.basicform.submit();
		}

	}

	function setEnable() {
		//业务类型其他输入框 
		if (document.getElementById("ywlxrdo").checked) {
			document.getElementById("ywlxval").disabled = false;
		} else {
			document.getElementById("ywlxval").disabled = true;
			document.getElementById("ywlxval").value = "";
		}
	}
	//服务范围
	function setfwfw() {

		//服务范围 跨省 
		if (document.getElementById("ksrdo").checked) {
			document.getElementById("ksrval").disabled = false;
		} else {
			document.getElementById("ksrval").disabled = true;
			document.getElementById("ksrval").value = "";
		}

		//服务范围 跨地
		if (document.getElementById("kdrdo").checked) {
			document.getElementById("kdval").disabled = false;
		} else {
			document.getElementById("kdval").disabled = true;
			document.getElementById("kdval").value = "";
		}

		//服务范围 其他
		if (document.getElementById("fwfwdo").checked) {
			document.getElementById("fwfwval").disabled = false;
		} else {
			document.getElementById("fwfwval").disabled = true;
			document.getElementById("fwfwval").value = "";
		}

	}
	//服务对象
	function setdx() {

		if (document.getElementById("dxrdo").checked) {
			document.getElementById("dxrval").disabled = false;
		} else {
			document.getElementById("dxrval").disabled = true;
			document.getElementById("dxrval").value = "";
		}

	}

	function checkNum(varStr) {
		var msgNum = /^\d+$/;
		if (!msgNum.test(varStr.value)) {
			alert("请输入数字");
			varStr.value="";
			return false;
		}
	}
</script>
</head>

<body >
	<form id="basicform" name="cpUnitBaseinfoForm" method="post"
		action="${ctx}/system/update.action" enctype="multipart/form-data">
		<table width="99.5%" >
			<tr>
				<td>
							<!-- information area -->
							<table width="100%" class="framDiv">
								<tr>
									<td colspan="4" class='r2titler'>信息系统编辑</td>
								</tr>
								<tr>
									<td class="td_detail_separator" colspan="4"></td>
								</tr>
								<tr>
									<td width="10%" align="right">系统名称：<font color="#ff0000">*</font>
									<input id="sysName" type="hidden" name="system.id" value="<s:property value='#request.system.id' />">
									<input id="sysName2" type="hidden" value="<s:property value='#request.system.sysName' />">
									</td>
									<td width="20%" style="text-align: left;"><input
										type="text" maxlength="50" id="sysName1" style="width:200px"
										name="system.sysName"onblur="yanzheng1(this);checkName()"
										value="<s:property value='#request.system.sysName'/>" />
									</td>
									<td width="8%" align="right">系统编号：<font color="#ff0000">*</font></td>
									<td width="52%" style="text-align: left;">
										<input type="text" size="8" style="width:200px" name="system.sysId" readonly="readonly" value="<s:property value='#request.system.sysId'/>" />
									</td>
								</tr>
								<tr>
									<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td style="text-align: right">业务类型：</td>
									<td colspan="3" style="text-align: left;"><input
										type="radio" name="system.busType" value="1"
										onclick="setEnable()"
										<s:if test="%{1==#request.system.busType}">checked</s:if> />
										生产作业 <input type="radio" name="system.busType" value="2"
										onclick="setEnable()"
										<s:if test="%{2==#request.system.busType}">checked</s:if> />
										指挥调度 <input type="radio" name="system.busType" value="3"
										onclick="setEnable()"
										<s:if test="%{3==#request.system.busType}">checked</s:if> />
										管理控制 <input type="radio" name="system.busType" value="4"
										onclick="setEnable()"
										<s:if test="%{4==#request.system.busType}">checked</s:if> />
										内部办公 <input type="radio" name="system.busType" value="5"
										onclick="setEnable()"
										<s:if test="%{5==#request.system.busType}">checked</s:if> />
										公众服务 <input type="radio" name="system.busType" value="9"
										onclick="setEnable()" id="ywlxrdo"
										<s:if test="%{9==#request.system.busType}">checked</s:if> />
										其它 <input type="text" maxlength="50" name="system.otherBusType"
										class="txt" id="ywlxval"
										value="<s:property value='#request.system.otherBusType'/>"
										style="width:100px;" onblur="yanzheng1(this)" <s:if test="%{9!=#request.system.busType}">disabled</s:if>/>
									</td>
								</tr>
								<tr>
									<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td style="text-align: right">业务描述：</td>
									<td colspan="3"><textarea rows="10" cols="30"
											style="text-align:left;width:100%"
											name="system.busDescription" id="busDescription" maxlength="50"><s:property value='#request.system.busDescription' /></textarea>
											</td>
								</tr>
								<tr>
									<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td style="text-align: right">服务范围：</td>
									<td colspan="3" style="text-align:left"><input
										type="radio" name="system.serArea" value="10"
										onclick="setfwfw()"
										<s:if test="%{10==#request.system.serArea}">checked</s:if> />全国
										<input type="radio" name="system.serArea" value="11"
										onclick="setfwfw()" id="ksrdo"
										<s:if test="%{11==#request.system.serArea}">checked</s:if> />跨省（区、市）
										跨 <input type="text" style="width:50px"
										name="system.proTotal" class="txt"
										maxlength="4" id="ksrval"
										onblur="checkNum(this)"
										value="<s:property value='#request.system.proTotal'/>" <s:if test="%{11!=#request.system.serArea}">disabled</s:if>/>个&nbsp;&nbsp;
										<input type="radio" name="system.serArea" onclick="setfwfw()"
										value="20"
										<s:if test="%{20==#request.system.serArea}">checked</s:if> />全省（区、市）
										<input type="radio" name="system.serArea" onclick="setfwfw()"
										id="kdrdo" value="21"
										<s:if test="%{21==#request.system.serArea}">checked</s:if> />跨地（市、区）
										跨 <input type="text" style="width:50px"
										name="system.cityTotal"  class="txt"
										id="kdval" maxlength="4"
										onblur="checkNum(this)"
										value="<s:property value='#request.system.cityTotal'/>" <s:if test="%{21!=#request.system.serArea}">disabled</s:if>/>个&nbsp;&nbsp;
										<input type="radio" name="system.serArea" onclick="setfwfw()"
										value="30"
										<s:if test="%{30==#request.system.serArea}">checked</s:if> />地（市、区）内
										<input type="radio" name="system.serArea" onclick="setfwfw()"
										value="99" id="fwfwdo"
										<s:if test="%{99==#request.system.serArea}">checked</s:if> />其它
										<input type="text" name="system.otherArea" class="txt"
										maxlength="50" id="fwfwval" 
										value='<s:property value='#request.system.otherArea'/>'
										style="width:100px" <s:if test="%{99!=#request.system.serArea}">disabled</s:if>/>
									</td>
								</tr>
								<tr>
									<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td style="text-align: right">服务对象：</td>
									<td colspan="3" style="text-align: left;"><input
										type="radio" name="system.serObj" value="1" onclick="setdx()"
										<s:if test="%{1==#request.system.serObj}">checked</s:if> />单位内部人员
										<input type="radio" name="system.serObj" value="2"
										onclick="setdx()"
										<s:if test="%{2==#request.system.serObj}">checked</s:if> />社会公众人员
										<input type="radio" name="system.serObj" value="3"
										onclick="setdx()"
										<s:if test="%{3==#request.system.serObj}">checked</s:if> />两者均包括
										<input type="radio" name="system.serObj" value="9"
										onclick="setdx()" id="dxrdo"
										<s:if test="%{9==#request.system.serObj}">checked</s:if> />其他
										<input type="text" name="system.otherObj" class="txt"
										maxlength="50" id="dxrval" 
										value='<s:property value='#request.system.otherObj'/>'
										style="width:100px" <s:if test="%{9!=#request.system.serObj}">disabled</s:if>/>
									</td>
								</tr>
								<tr>
									<td class="td_detail_separator" colspan="4"></td>
								</tr>
							</table>
				</td>
			</tr>

			<tr>
				<td>
					
						<table width="100%" >
							<tr >
								<td width="*" valign="middle" align="right"><input
									type="button" value="取消"
									class="btnyh"
									onClick="window.location.href='${ctx}/pages/dbPage/basic/manager.jsp';" />
									<input type="button"
									 class="btnyh" id="btnSub"
									onClick="saveUnitBaseinfo()" value="保存" />
								</td>
							</tr>
						</table>

				</td>
			</tr>
		</table>
	</form>
</body>
</html>
