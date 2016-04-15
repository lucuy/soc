<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<html>
<head>
<title>添加单位基本情况</title>
<meta http-equiv="Cache-Control" content="private" />
<link href="${ctx}/styles/dbStyles/pageCss.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/right.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/template.css" rel="stylesheet" type="text/css">
<SCRIPT src="${ctx}/scripts/jquery-1.7.2.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/jquery-ui.custom.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/util.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/popuplayer.js" type=text/javascript></SCRIPT>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/check.js"></script>
<style type="text/css">
.btnstyle {
	 background:url(/soc/images/btnbg.jpg) no-repeat; 
	 border:none; 
	 width:66px; 
	 height:21px;
	 cursor:pointer;
	 color:#265D86;
	 align:center;
} 
</style>
<script type="text/javascript">
	jQuery(document).ready(function() {
		var msgPost = document.getElementById("postcode").value;

		if (msgPost == 0) {
			document.getElementById("postcode").value = "";
		}

		var msgPost = document.getElementById("depTel").value;

		if (msgPost == 0) {
			document.getElementById("depTel").value = "";
		}

		var msgPost = document.getElementById("depMobile").value;

		if (msgPost == 0) {
			document.getElementById("depMobile").value = "";
		}

		//var msgUnit = document.getElementById("unitType").value;
		document.getElementById("unitType").checked = true;

		//var msgEmp = document.getElementById("MsgEmp").value;
		document.getElementById("MsgEmp").checked = true;

		//var msgSub = document.getElementById("MsgSub").value;
		document.getElementById("MsgSub").checked = true;

	});
	function save() {
	    var btnSub=document.getElementById("btnSub");
        btnSub.disabled="disabled";
		if ("" == $.trim($("#unitName").val())) {
			alert("单位名称不能为空");
			btnSub.disabled="";
			$("#unitName").focus();
			return false;
		}
		if ($.trim($("#unitName").val()).length > 20) {
			alert("单位名称不能大于20个字符");
			btnSub.disabled="";
			$("#unitName").focus();
			return false;
		}
		
		if ("" == $.trim($("#unitLeader").val())) {
			alert("单位负责人姓名不能为空");
			btnSub.disabled="";
			$("#unitLeader").focus();
			return false;
		}
		if ($.trim($("#unitLeader").val()).length > 5) {
			alert("单位负责人姓名不能大于5个字符");
			btnSub.disabled="";
			$("#unitLeader").focus();
			return false;
		}
		
		if ($.trim($("#duty").val()).length > 5) {
			alert("单位负责人职务/职称不能大于5个字符");
			btnSub.disabled="";
			$("#duty").focus();
			return false;
		}
		
		if ($.trim($("#unitDep").val()).length > 20) {
			alert("责任部门不能大于20个字符");
			btnSub.disabled="";
			$("#unitDep").focus();
			return false;
		}
		
		if ($.trim($("#depContact").val()).length > 10) {
			alert("责任人部门联系人姓名不能大于10个字符");
			btnSub.disabled="";
			$("#depContact").focus();
			return false;
		}
		
		if ($.trim($("#depDuty").val()).length > 10) {
			alert("责任人部门联系人职务/职称不能大于10个字符");
			btnSub.disabled="";
			$("#depDuty").focus();
			return false;
		}
		
		//判断点选其他单选按钮，则文本框不空
		if($("#subsetEnabled").is(":checked")){
			if($("#subEnabled").val()==""){
				alert("请填写其他隶属关系");
				btnSub.disabled="";
				return false;
			}
		}
		//判断点选其他单选按钮，则文本框不空
		if($("#unitTypeEnable").is(":checked")){
			if($("#otherUnitType").val()==""){
				alert("请填写其他单位类型");
				btnSub.disabled="";
				return false;
			}
		}
		//判断点选其他单选按钮，则文本框不空
		if($("#empsetEnable").is(":checked")){
			if($("#empEnable").val()==""){
				alert("请填写其他行业类别");
				btnSub.disabled="";
				return false;
			}
		}
		$("#saveUnitInfo").submit();

	}
	function query() {
		location.href = "${ctx}/unitInfo/query.action";
	}

	function setEnable() {
		//其他单位类型：单选框控制文本框是否可编译
		if (document.getElementById("unitTypeEnable").checked) {
		//alert("");
			document.getElementById("otherUnitType").disabled = false;
		} else {
			document.getElementById("otherUnitType").disabled = true;
			document.getElementById("otherUnitType").value = "";
		}
		//行业类别：单选框控制文本框是否可编译
		if (document.getElementById("empsetEnable").checked) {
			document.getElementById("empEnable").disabled = false;
		} else {
			document.getElementById("empEnable").disabled = true;
			document.getElementById("empEnable").value = "";
		}
		//隶属关系：单选框控制文本框是否可编译
		if (document.getElementById("subsetEnabled").checked) {
			document.getElementById("subEnabled").disabled = false;
		} else {
			document.getElementById("subEnabled").disabled = true;
			document.getElementById("subEnabled").value = "";
		}
	}
	function checkPost() {
		var sPost = document.getElementById("postcode").value;
		if(sPost==""){
		return;
		}
		var re = /^[0-9]{6}$/;
		if (!(re.test(sPost))) {
			alert("邮政编码格式不正确");
			document.getElementById("postcode").value=""; 
			return false;
		}
	}
	function checkNum() {
		var msgNum = /^\d+$/;
		if (!msgNum.test(document.getElementById("depTel").value)) {
			alert("请输入数字");
			return false;
		}

	}
	String.prototype.IsSixNum = function() {
		var myReg = /^\d{6}$/;
		if (myReg.test(this)) {
			return true;
		} else {
			return false;
		}
	};
	String.prototype.IsEmail = function() {
		var myReg = /^(([0-9a-zA-Z]+)|([0-9a-zA-Z]+[_.0-9a-zA-Z-]*[0-9a-zA-Z-]+))@([a-zA-Z0-9-]+[.])+([a-zA-Z]|net|NET|asia|ASIA|com|COM|gov|GOV|mil|MIL|org|ORG|edu|EDU|int|INT|cn|CN|cc|CC)$/;
		if (myReg.test(this)) {
			return true;
		} else {
			return false;
		}
	};

	String.prototype.IsMobile = function() {
		//var myReg = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-?(\d{3,}))?$/;
		var myReg = /^(\d{3,4}\-?)?\d{7,8}$/;
		if (myReg.test(this)) {
			return true;
		} else {
			return false;
		}
	};

	String.prototype.IsPhone = function() {
		var myReg = /^0?(13[0-9]|15[012356789]|18[0236789]|14[57])[0-9]{8}$/;
		if (myReg.test(this)) {
			return true;
		} else {
			return false;
		}
	};

	function checkMobile(val) {
		var mobile = $("#" + val + "").val();
		if (mobile && mobile != "") {
			if (!mobile.IsMobile()) {
				alert("办公电话电话格式不正确！");
				$("#" + val + "").val("");
				$("#" + val + "").focus();
			}
		}
	}

	function checkPhone(val) {
		var phone = $("#" + val + "").val();
		if (phone && phone != "") {
			if (!phone.IsPhone()) {
				alert("移动电话格式不正确！");
				$("#" + val + "").val("");
				$("#" + val + "").focus();
			}
		}
	}

	function checkMail(val) {
		var mail = $("#" + val + "").val();
		if (mail && mail != "") {
			if (!mail.IsEmail()) {
				alert("电子邮箱格式不正确！");
				$("#" + val + "").val("");
				$("#" + val + "").focus();
			}
		}
	}

	function checkSixNum(val) {
		var sixNum = $("#" + val + "").val();
		if (sixNum && sixNum != "") {
			if (!sixNum.IsSixNum()) {
				alert("行政区代码不正确！");
				$("#" + val + "").val("");
				$("#" + val + "").focus();
			}
		}
	}
	
	
</script>
</head>

<body style="margin: 0;position: 0">
<s:form action="insert" name="unitInfoForm" namespace="/unitInfo" method="post" theme="simple" id="saveUnitInfo">
	<div style="width: 99.5%">
		<input type="hidden" id="id" name="id" value="${unitInfo.id}">
		<input type="hidden" id="unitType" value="${unitInfo.unitType}">
		<input type="hidden" id="MsgEmp" value="${unitInfo.employment}">
		<input type="hidden" id="MsgSub" value="${unitInfo.subordinate}" />
		<table width="100%"     border="1" bordercolor="#5AA4D1" cellpadding="0" cellspacing="0">
			    <tr>
					<td colspan="6">
						<input type="button" class="btnstyle" style="margin-left: 10px" id="btnSub" value="保存" onclick="save();">
						<input type="button" class="btnstyle"  value="取消" onclick="query();"> 
				<tr>
					<td align="right" width="16%">单位名称：<font color="#ff0000">*</font></td>
					<td colspan="5" width="84%"><input type="text" onblur="yanzheng1(this)"  id="unitName" name="unitName" value="${unitInfo.unitName}" maxlength="50" style="width: 200px"><br>
					</td>
				</tr>
				<tr>
					<td width="16%" align="right">单位地址：</td>
					<td colspan="5" width="84%"><input type="text"  class="showBorder" onblur="yanzheng1(this)"  name="province" value="${unitInfo.province}" maxlength="50" style="width: 80px">省(自 治区、直辖市)<input type="text" onblur="yanzheng1(this)"  class="showBorder" name="city" maxlength="50" value="${unitInfo.city}" style="width: 80px">市(区、市、州、盟)<input type="text" onblur="yanzheng1(this)" class="showBorder" name="county" maxlength="50" value="${unitInfo.county}" style="width: 80px">县(区、市、旗)</td>
				</tr>
				<tr>
					<td  align="right" width="16%">邮政编码：</td>
					<td colspan="2" width="32%"><input type="text"  style="width: 100px" id="postcode" name="postcode" value="${unitInfo.postcode}" maxlength="6" onblur="checkPost()">
					</td>
					<td align="right" width="16%">行政区代码：</td>
					<td colspan="2" width="32%"><input type="text"  name="divisionCode" id="divisionCode" value="${unitInfo.divisionCode}" maxlength="6" style="width: 100px" onblur="checkSixNum('divisionCode')">
					</td>
				</tr>
				<tr>
					<td rowspan="2" width="16%" align="right">单位负责人：</td>
					<td  align="right" width="16%">姓名：<font color="#ff0000">*</font></td>
					<td width="16%" style="vertical-align: middle;"><input type="text" onblur="yanzheng1(this)" id="unitLeader" name="unitLeader" value="${unitInfo.unitLeader}" maxlength="50" style="width:200px;"></td>
					<td  align="right" width="16%">职务/职称：</td>
					<td colspan="2" width="32%"><input type="text"  name="duty" onblur="yanzheng1(this)" id="duty" value="${unitInfo.duty}" maxlength="50" style="width:200px" /></td>
				</tr>
				<tr>
					<td scope="row" align="right" width="16%">办公电话：</td>
					<td  width="16%"><input type="text" id="unitTel" name="unitTel" value="${unitInfo.unitTel}" maxlength="50" style="width:200px" onblur="checkMobile('unitTel')" /></td>
					<td scope="row" align="right"  width="16%">电子邮件：</td>
					<td colspan="2"  width="32%"><input type="text"  id="unitEmail" name="unitEmail" value="${unitInfo.unitEmail}" maxlength="50" style="width:200px" onblur="checkMail('unitEmail')" /></td>
				</tr>
				  
				<tr>
					<td align="right"  width="16%">责任部门：</td>
					<td colspan="5"  width="84%"><input type="text" id="unitDep" name="unitDep" value="${unitInfo.unitDep}" maxlength="50" onblur="yanzheng1(this)" style="width: 200px">
					</td>
				</tr>
				<tr>
					<td  align="right" rowspan="3"  width="16%">责任人姓名：</td>
					<td  align="right"  width="16%">姓名：</td>
					<td width="16%"><input type="text" id="depContact" name="depContact" onblur="yanzheng1(this)" value="${unitInfo.depContact}" maxlength="50" style="width: 200px">
					</td>
					<td  align="right"  width="16%">职务/职称：</td>
					<td colspan="2"  width="32%"><input type="text" onblur="yanzheng1(this)" id="depDuty" name="depDuty" value="${unitInfo.depDuty}" maxlength="50" style="width: 200px"></td>
				</tr>
				<tr>
					<td align="right"  width="16%">办公电话：</td>
					<td  width="16%"><input type="text"  id="depTel" name="depTel" value="${unitInfo.depTel}" maxlength="50" style="width:200px" onblur="checkMobile('depTel')">
					</td>
					<td align="right"  width="16%">电子邮件：</td>
					<td colspan="2"  width="32%"><input type="text"  id="depEmail" name="depEmail" value="${unitInfo.depEmail}" maxlength="50" style="width: 200px" onblur="checkMail('depEmail')"></td>
				</tr>
				<tr>
					<td  align="right"  width="16%">移动电话：</td>
					<td colspan="4"  width="64%">
						<input type="text"  id="depMobile" name="depMobile" value="${unitInfo.depMobile}" maxlength="50" style="width:200px" onblur="checkPhone('depMobile')">	
					</td>
				</tr>
				<tr>
				    <td width="16%" align="right">隶属关系：</td>
					<td colspan="5" width="84%">
								<p><input type="radio" name="subordinate" id="中央" value="中央" onclick="setEnable()" <c:if test="${unitInfo.subordinate=='中央'}">checked</c:if> />中央&ensp;&ensp;&ensp;
								<input type="radio" name="subordinate" id="省" value="省" onclick="setEnable()" <c:if test="${unitInfo.subordinate=='省'}">checked</c:if> />省(自治区、直辖市)&ensp;&ensp;&ensp;
								<input type="radio" name="subordinate" id="地" value="地" onclick="setEnable()" <c:if  test="${unitInfo.subordinate=='地'}">checked</c:if> />地(区、市、州、盟)
								
								<input type="radio" name="subordinate" id="县" value="县" onclick="setEnable()" <c:if  test="${unitInfo.subordinate=='县'}">checked</c:if> />县(区、市、旗)
					            <input type="radio" id="subsetEnabled" name="subordinate" value="其他" onclick="setEnable()" <c:if test="${unitInfo.subordinate=='其他'}">checked</c:if>/>其他<input type="text" maxlength="50" id="subEnabled" name="otherSub" value="${unitInfo.otherSub}" onblur="yanzheng1(this)" maxlength="50" style="width:100px">
					            </p>
					</td>		
				</tr>
				
				<tr>
				<td width="16%" align="right">单位类型：</td>
					<td colspan="5" width="84%">
						<p><input type="radio" id="党委机关" name="unitType" value="党委机关" onclick="setEnable()" <c:if test="${unitInfo.unitType=='党委机关'}">checked</c:if>/>党委机关  &nbsp;&nbsp;&nbsp;
						<input type="radio" id="政府机关" name="unitType" value="政府机关" onclick="setEnable()" <c:if test="${unitInfo.unitType=='政府机关'}">checked</c:if>/>政府机关&nbsp;&nbsp;&nbsp;
						<input type="radio" id="事业单位" name="unitType" value="事业单位" onclick="setEnable()"<c:if test="${unitInfo.unitType=='事业单位'}">checked</c:if> />事业单位&nbsp;&nbsp;&nbsp; 
						<input type="radio" id="企业" name="unitType" value="企业" onclick="setEnable()" <c:if test="${unitInfo.unitType=='企业'}">checked</c:if>/>企业
						
						<input id="unitTypeEnable" type="radio" name="unitType" value="其他" onclick="setEnable()" <c:if test="${unitInfo.unitType=='其他'}">checked</c:if>/>其他
						<input id="otherUnitType" onblur="yanzheng1(this)" type="text"  name="otherUnitType" value="${unitInfo.otherUnitType}" maxlength="50" style="width:100px" />
						</p>
					</td>
					
				</tr>
				<tr>
					<td width="16%" align="right">行业类别：</td>
					<td colspan="5" width="84%">
					<input type="radio" name="employment" id="电信" value="电信" <c:if test="${unitInfo.employment=='电信'}">checked</c:if> onclick="setEnable()" />电信&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="employment" id="广电" value="广电" <c:if test="${unitInfo.employment=='广电'}">checked</c:if> onclick="setEnable()" />广电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="employment" id="经营性公众互联网" value="经营性公众互联网" <c:if test="${unitInfo.employment=='经营性公众互联网'}">checked</c:if> onclick="setEnable()" />经营性公众互联网
					<br>
					<input type="radio" name="employment" id="铁路" value="铁路" <c:if test="${unitInfo.employment=='铁路'}">checked</c:if> onclick="setEnable()" />铁路&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="employment" id="银行" value="银行" <c:if test="${unitInfo.employment=='银行'}">checked</c:if> onclick="setEnable()" />银行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="employment" id="海关" value="海关" <c:if test="${unitInfo.employment=='海关'}">checked</c:if> onclick="setEnable()" />海关&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    <input type="radio" name="employment" id="税务" value="税务" <c:if test="${unitInfo.employment=='税务'}">checked</c:if> onclick="setEnable()" />税务
				    <br>
					<input type="radio" name="employment" id="民航" value="民航" <c:if test="${unitInfo.employment=='民航'}">checked</c:if> onclick="setEnable()" />民航&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="employment" id="电力" value="电力" <c:if test="${unitInfo.employment=='电力'}">checked</c:if> onclick="setEnable()" />电力&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="employment" id="证券" value="证券" <c:if test="${unitInfo.employment=='证券'}">checked</c:if> onclick="setEnable()" />证券&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    <input type="radio" name="employment" id="保险" value="保险" <c:if test="${unitInfo.employment=='保险'}">checked</c:if> onclick="setEnable()" />保险
				   	<br>
					<input type="radio" name="employment" id="国防科技工业" value="国防科技工业" <c:if test="${unitInfo.employment=='国防科技工业'}">checked</c:if> onclick="setEnable()" />国防科技工业&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="employment" id="公安" value="公安" <c:if test="${unitInfo.employment=='公安'}">checked</c:if> onclick="setEnable()" />公安&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    <input type="radio" name="employment" id="人事劳动和社会保障" value="人事劳动和社会保障" <c:if  test="${unitInfo.employment=='人事劳动和社会保障'}">checked</c:if> onclick="setEnable()" />人事劳动和社会保障
					<input type="radio" name="employment" id="财政" value="财政" <c:if test="${unitInfo.employment=='财政'}">checked</c:if> onclick="setEnable()" />财政
 					<br>
					<input type="radio" name="employment" id="审计" value="审计" <c:if test="${unitInfo.employment=='审计'}">checked</c:if> onclick="setEnable()" />审计&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<input type="radio" name="employment" id="商业贸易" value="商业贸易" <c:if test="${unitInfo.employment=='商业贸易'}">checked</c:if> onclick="setEnable()" />商业贸易&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="employment" id="国土资源" value="国土资源" <c:if test="${unitInfo.employment=='国土资源'}">checked</c:if> onclick="setEnable()" />国土资源&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="employment" id="能源" value="能源" <c:if test="${unitInfo.employment=='能源'}">checked</c:if> onclick="setEnable()" />能源
				    <br>
					<input type="radio" name="employment" id="交通" value="交通" <c:if test="${unitInfo.employment=='交通'}">checked</c:if> onclick="setEnable()" />交通&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="employment" id="统计" value="统计" <c:if test="${unitInfo.employment=='统计'}">checked</c:if> onclick="setEnable()" />统计&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="employment" id="工商行政管理" value="工商行政管理" <c:if test="${unitInfo.employment=='工商行政管理'}">checked</c:if> onclick="setEnable()" />工商行政管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="employment" id=" 邮政" value="邮政" <c:if test="${unitInfo.employment=='邮政'}">checked</c:if> onclick="setEnable()" />邮政
				    <br>
					<input type="radio" name="employment" id="教育" value="教育" <c:if test="${unitInfo.employment=='教育'}">checked</c:if> onclick="setEnable()" />教育&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="employment" id="文化" value="文化" <c:if test="${unitInfo.employment=='文化'}">checked</c:if> onclick="setEnable()" />文化&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    <input type="radio" name="employment" id="卫生" value="卫生" <c:if test="${unitInfo.employment=='卫生'}">checked</c:if> onclick="setEnable()" />卫生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="employment" id="农业" value="农业" <c:if test="${unitInfo.employment=='农业'}">checked</c:if> onclick="setEnable()" />农业&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<br>
				    <input type="radio" name="employment" id="水利" value="水利" <c:if test="${unitInfo.employment=='水利'}">checked</c:if> onclick="setEnable()" />水利&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="employment" id="外交" value="外交" <c:if test="${unitInfo.employment=='外交'}">checked</c:if> onclick="setEnable()" />外交&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="employment" id=" 发展改革" value="发展改革" <c:if test="${unitInfo.employment=='发展改革'}">checked</c:if> onclick="setEnable()" />发展改革&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="employment" id="科技" value="科技" <c:if test="${unitInfo.employment=='科技'}">checked</c:if> onclick="setEnable()" />科技
					<br>
					<input type="radio" name="employment" id="宣传" value="宣传" <c:if test="${unitInfo.employment=='宣传'}">checked</c:if> onclick="setEnable()" />宣传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="employment" id="质量监督检验检疫" value="质量监督检验检疫" <c:if test="${unitInfo.employment=='质量监督检验检疫'}">checked</c:if> onclick="setEnable()" />质量监督检验检疫
					<br>
					<input type="radio" id="empsetEnable" name="employment"  value="其他" onclick="setEnable()" <c:if test="${unitInfo.employment=='其他'}">checked</c:if>/>其他<input type="text" onblur="yanzheng1(this)" id="empEnable" name="otherEmp" value="${unitInfo.otherEmp}" maxlength="50" style="width:100px" />
					</td>
				</tr>
				
				<tr>
					<td width="16%" rowspan="2" align="right">系统信息总数：</td>
					<td width="16%" rowspan="2">&ensp;${sysNum}</td>
					<td width="16%" align="right">第二级信息系统数：</td>
					<td width="16%">&ensp;${sysSecNum}</td>
					<td width="16%" align="right">第三级信息系统数：</td>
					<td width="16%">&ensp;${systhrNum}</td>
				</tr>
				<tr>					
                    <td width="16%" align="right">第四级信息系统数：</td>
					<td width="16%">&ensp;${systhiNum}</td>
					<td width="16%"align="right">第五级级信息系统数：</td>
					<td width="16%">&ensp;0</td>
				</tr>
			</table>
			
		</div>


	</s:form>

</body>
</html>
