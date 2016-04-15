<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<html>
<head>
<title>编辑信息系统定级情况表</title>
<link href="${ctx}/styles/dbStyles/pageCss.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/dbStyles/style.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/dbStyles/right.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/dbStyles/template.css" rel="stylesheet"
	type="text/css">
<SCRIPT src="${ctx}/scripts/Calendar.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/jquery-1.7.2.min.js" type=text/javascript></SCRIPT>
<script type="text/javascript">
	function ifShowDocDiv(docu, btn1Name, btn2Name) {
		if (docu.value == "1") {
			document.getElementById(btn2Name).disabled = "";

		}
		document.getElementsByName(btn1Name)[1].disabled = "disabled";
	}
	function gohead() {
		var btnSub = document.getElementById("btnSub");
		btnSub.disabled = "disabled";
		document.scoreresultForm.target = "mainFrame";
		document.scoreresultForm.action = "";
		var formName = document.getElementById("scoreresultForm");
		formName.action = "${ctx}/rank/rankAction_rankSixUpdate.action";
		formName.submit();
	}
	function addtopFiles() {
		if (!confirm('确定替换原文件吗？')) {
			return;
		}
		var filesDiv = document.getElementById("divtop_main");
		var fileInput = document.createElement("input");

		fileInput.type = "file";
		fileInput.name = "ftopRelAcc1";
		fileInput.id = "ftopRelAcc1";
		var upButton = document.createElement("input");
		upButton.type = "button";
		upButton.value = "上传文件";

		upButton.onclick = function upfile() {
			var frankAccess = document.getElementById("ftopRelAcc1");
			if ($.trim(frankAccess.value) == "") {
				alert("请选择上传文件！");
				return;
			}
			document.scoreresultForm.target = "hiddenframe";
			document.scoreresultForm.action = "${ctx}/rank/uptopfile.action";
			document.scoreresultForm.submit();
			var divornot = document.getElementById("msg");
			if (divornot != null) {
				divornot.removeAttribute("id");
			}
			var span = document.getElementById("msg1");
			var divname = document.createElement("div");
			divname.id = "msg";
			span.appendChild(divname);
		};

		var upbuh = document.getElementById("ftopRelAcc1FileNamehidden");
		filesDiv.removeChild(upbuh);
		var upbu = document.getElementById("updatebutton1");
		filesDiv.removeChild(upbu);
		var divname = document.createElement("div");
		divname.appendChild(fileInput);
		divname.appendChild(upButton);
		filesDiv.appendChild(divname);
	}
	function addsafeFiles() {
		if (!confirm('确定替换原文件吗？')) {
			return;
		}
		var filesDiv = document.getElementById("divsafe_main");
		var fileInput = document.createElement("input");

		fileInput.type = "file";
		fileInput.name = "fsysManRel1";
		fileInput.id = "fsysManRel1";
		var upButton = document.createElement("input");
		upButton.type = "button";
		upButton.value = "上传文件";

		upButton.onclick = function upfile() {
			var frankAccess = document.getElementById("fsysManRel1");
			if ($.trim(frankAccess.value) == "") {
				alert("请选择上传文件！");
				return;
			}
			document.scoreresultForm.target = "hiddenframe";
			document.scoreresultForm.action = "${ctx}/rank/upsysmanfile.action";
			document.scoreresultForm.submit();
			var divornot = document.getElementById("msg");
			if (divornot != null) {
				divornot.removeAttribute("id");
			}
			var span = document.getElementById("msg2");
			var divname = document.createElement("div");
			divname.id = "msg";
			span.appendChild(divname);
		};

		var upbuh = document.getElementById("fsysManRel1FileNamehidden");
		filesDiv.removeChild(upbuh);
		var upbu = document.getElementById("updatebutton2");
		filesDiv.removeChild(upbu);
		var divname = document.createElement("div");
		divname.appendChild(fileInput);
		divname.appendChild(upButton);
		filesDiv.appendChild(divname);
	}
	function addplanFiles() {
		if (!confirm('确定替换原文件吗？')) {
			return;
		}
		var filesDiv = document.getElementById("divplan_main");
		var fileInput = document.createElement("input");

		fileInput.type = "file";
		fileInput.name = "fsysPlanRel1";
		fileInput.id = "fsysPlanRel1";
		var upButton = document.createElement("input");
		upButton.type = "button";
		upButton.value = "上传文件";

		upButton.onclick = function upfile() {
			var frankAccess = document.getElementById("fsysPlanRel1");
			if ($.trim(frankAccess.value) == "") {
				alert("请选择上传文件！");
				return;
			}
			document.scoreresultForm.target = "hiddenframe";
			document.scoreresultForm.action = "${ctx}/rank/upsysplanfile.action";
			document.scoreresultForm.submit();
			var divornot = document.getElementById("msg");
			if (divornot != null) {
				divornot.removeAttribute("id");
			}
			var span = document.getElementById("msg3");
			var divname = document.createElement("div");
			divname.id = "msg";
			span.appendChild(divname);
		};

		var upbuh = document.getElementById("fsysPlanRel1FileNamehidden");
		filesDiv.removeChild(upbuh);
		var upbu = document.getElementById("updatebutton3");
		filesDiv.removeChild(upbu);
		var divname = document.createElement("div");
		divname.appendChild(fileInput);
		divname.appendChild(upButton);
		filesDiv.appendChild(divname);
	}
	function addliseFiles() {
		if (!confirm('确定替换原文件吗？')) {
			return;
		}
		var filesDiv = document.getElementById("divlise_main");
		var fileInput = document.createElement("input");

		fileInput.type = "file";
		fileInput.name = "fsysLiceRel1";
		fileInput.id = "fsysLiceRel1";
		var upButton = document.createElement("input");
		upButton.type = "button";
		upButton.value = "上传文件";

		upButton.onclick = function upfile() {
			var frankAccess = document.getElementById("fsysLiceRel1");
			if ($.trim(frankAccess.value) == "") {
				alert("请选择上传文件！");
				return;
			}
			document.scoreresultForm.target = "hiddenframe";
			document.scoreresultForm.action = "${ctx}/rank/upsyslicefile.action";
			document.scoreresultForm.submit();
			var divornot = document.getElementById("msg");
			if (divornot != null) {
				divornot.removeAttribute("id");
			}
			var span = document.getElementById("msg4");
			var divname = document.createElement("div");
			divname.id = "msg";
			span.appendChild(divname);
		};

		var upbuh = document.getElementById("fsysLiceRel1FileNamehidden");
		filesDiv.removeChild(upbuh);
		var upbu = document.getElementById("updatebutton4");
		filesDiv.removeChild(upbu);
		var divname = document.createElement("div");
		divname.appendChild(fileInput);
		divname.appendChild(upButton);
		filesDiv.appendChild(divname);
	}
	function addrepFiles() {
		if (!confirm('确定替换原文件吗？')) {
			return;
		}
		var filesDiv = document.getElementById("divrep_main");
		var fileInput = document.createElement("input");

		fileInput.type = "file";
		fileInput.name = "fsysReportRel1";
		fileInput.id = "fsysReportRel1";
		var upButton = document.createElement("input");
		upButton.type = "button";
		upButton.value = "上传文件";

		upButton.onclick = function upfile() {
			var frankAccess = document.getElementById("fsysReportRel1");
			if ($.trim(frankAccess.value) == "") {
				alert("请选择上传文件！");
				return;
			}
			document.scoreresultForm.target = "hiddenframe";
			document.scoreresultForm.action = "${ctx}/rank/upsysrefile.action";
			document.scoreresultForm.submit();
			var divornot = document.getElementById("msg");
			if (divornot != null) {
				divornot.removeAttribute("id");
			}
			var span = document.getElementById("msg5");
			var divname = document.createElement("div");
			divname.id = "msg";
			span.appendChild(divname);
		};

		var upbuh = document.getElementById("fsysReportRel1FileNamehidden");
		filesDiv.removeChild(upbuh);
		var upbu = document.getElementById("updatebutton5");
		filesDiv.removeChild(upbu);
		var divname = document.createElement("div");
		divname.appendChild(fileInput);
		divname.appendChild(upButton);
		filesDiv.appendChild(divname);
	}
	function addpeerFiles() {
		if (!confirm('确定替换原文件吗？')) {
			return;
		}
		var filesDiv = document.getElementById("divpeer_main");
		var fileInput = document.createElement("input");

		fileInput.type = "file";
		fileInput.name = "fpeerRevRel1";
		fileInput.id = "fpeerRevRel1";
		var upButton = document.createElement("input");
		upButton.type = "button";
		upButton.value = "上传文件";

		upButton.onclick = function upfile() {
			var frankAccess = document.getElementById("fpeerRevRel1");
			if ($.trim(frankAccess.value) == "") {
				alert("请选择上传文件！");
				return;
			}
			document.scoreresultForm.target = "hiddenframe";
			document.scoreresultForm.action = "${ctx}/rank/uppeerfile.action";
			document.scoreresultForm.submit();
			var divornot = document.getElementById("msg");
			if (divornot != null) {
				divornot.removeAttribute("id");
			}
			var span = document.getElementById("msg6");
			var divname = document.createElement("div");
			divname.id = "msg";
			span.appendChild(divname);
		};

		var upbuh = document.getElementById("fpeerRevRel1FileNamehidden");
		filesDiv.removeChild(upbuh);
		var upbu = document.getElementById("updatebutton6");
		filesDiv.removeChild(upbu);
		var divname = document.createElement("div");
		divname.appendChild(fileInput);
		divname.appendChild(upButton);
		filesDiv.appendChild(divname);
	}
	function addsuperFiles() {
		if (!confirm('确定替换原文件吗？')) {
			return;
		}
		var filesDiv = document.getElementById("divsuper_main");
		var fileInput = document.createElement("input");

		fileInput.type = "file";
		fileInput.name = "fsuperOpinRel1";
		fileInput.id = "fsuperOpinRel1";
		var upButton = document.createElement("input");
		upButton.type = "button";
		upButton.value = "上传文件";

		upButton.onclick = function upfile() {
			var frankAccess = document.getElementById("fsuperOpinRel1");
			if ($.trim(frankAccess.value) == "") {
				alert("请选择上传文件！");
				return;
			}
			document.scoreresultForm.target = "hiddenframe";
			document.scoreresultForm.action = "${ctx}/rank/upsuperfile.action";
			document.scoreresultForm.submit();
			var divornot = document.getElementById("msg");
			if (divornot != null) {
				divornot.removeAttribute("id");
			}
			var span = document.getElementById("msg7");
			var divname = document.createElement("div");
			divname.id = "msg";
			span.appendChild(divname);
		};

		var upbuh = document.getElementById("fsuperOpinRel1FileNamehidden");
		filesDiv.removeChild(upbuh);
		var upbu = document.getElementById("updatebutton7");
		filesDiv.removeChild(upbu);
		var divname = document.createElement("div");
		divname.appendChild(fileInput);
		divname.appendChild(upButton);
		filesDiv.appendChild(divname);
	}
	function callback(msg) {
		document.getElementById("msg").innerHTML = "<font color='red'>" + msg
				+ "</font>";
	}
	function callback2(name,msg)    
	{    
    	document.getElementById( "msg" ).innerHTML +=  "<input type='hidden' name='"+name+"' value='"+msg+"'>"; ;    
	} 
</script>
</head>
<body>
	<iframe name="hiddenframe" id="hiddenframe" style="display:none"></iframe>
	<form id="scoreresultForm" name="scoreresultForm" action=""
		method="post" style="width:99%" enctype="multipart/form-data">
		<!-- 第一页面 -->
		<input type="hidden" name="rankId" value="<s:property value="rank.rankId"/>" />
		 <input type="hidden" name="sysInfoName" value="<s:property value="rank.sysInfoName"/>" />
		<input type="hidden" name="sysInfoId" value="<s:property value="rank.sysInfoId"/>" /> 
		<input type="hidden" name="sysInfoBusDescription" value="<s:property value="rank.sysInfoBusDescription"/>" /> 
		<input	type="hidden" name="rankEvalUnitName" value="<s:property value="rank.rankEvalUnitName"/>" />
		 <input	type="hidden" name="rankUseDate" value="<s:property value="rank.rankUseDate"/>" /> 
		 <input	type="hidden" name="rankFlag"	value="<s:property value="rank.rankFlag"/>" />
		  <input type="hidden" name="rankParentSysName"	value="<s:property value="rank.rankParentSysName"/>" /> 
		  <input type="hidden" name="rankParentUnitName" value="<s:property value="rank.rankParentUnitName"/>" /> 
		  <input type="hidden" name="rankEvalRelAccess"	value="<s:property value="rank.rankEvalRelAccess"/>" /> 
		  <!-- 重命名字段值 -->
<input type="hidden" name="reRankEvalRelAccess" value="<s:property value="rank.reRankEvalRelAccess"/>" />
		  <input type="hidden" name="rankCoveArea"
			value="<s:property value="rank.rankCoveArea"/>" /> <input
			type="hidden" name="rankOthArea"
			value="<s:property value="rank.rankOthArea"/>" /> <input
			type="hidden" name="rankNetworkProp"
			value="<s:property value="rank.rankNetworkProp"/>" /> <input
			type="hidden" name="rankOthNetworkProp"
			value="<s:property value="rank.rankOthNetworkProp"/>" /> <input
			type="hidden" name="rankSysConn"
			value="<s:property value="rank.rankSysConn"/>" /> <input
			type="hidden" name="rankOtherSysConn"
			value="<s:property value="rank.rankOtherSysConn"/>" />
		<!-- 第二页面 -->
		<input type="hidden" name="rankSecCount"
			value="<s:property value="rank.rankSecCount"/>" /> <input
			type="hidden" name="rankSecUse"
			value="<s:property value="rank.rankSecUse"/>" /> <input
			type="hidden" name="partRankSecUse"
			value="<s:property value="rank.partRankSecUse"/>" /> <input
			type="hidden" name="rankNetCount"
			value="<s:property value="rank.rankNetCount"/>" /> <input
			type="hidden" name="rankNetUse"
			value="<s:property value="rank.rankNetUse"/>" /> <input
			type="hidden" name="partRankNetUse"
			value="<s:property value="rank.partRankNetUse"/>" /> <input
			type="hidden" name="rankSysCount"
			value="<s:property value="rank.rankSysCount"/>" /> <input
			type="hidden" name="rankSysUse"
			value="<s:property value="rank.rankSysUse"/>" /> <input
			type="hidden" name="partRankSysUse"
			value="<s:property value="rank.partRankSysUse"/>" /> <input
			type="hidden" name="rankSqlCount"
			value="<s:property value="rank.rankSqlCount"/>" /> <input
			type="hidden" name="rankSqlUse"
			value="<s:property value="rank.rankSqlUse"/>" /> <input
			type="hidden" name="partRankSqlUse"
			value="<s:property value="rank.partRankSqlUse"/>" /> <input
			type="hidden" name="rankSerCount"
			value="<s:property value="rank.rankSerCount"/>" /> <input
			type="hidden" name="rankSerUse"
			value="<s:property value="rank.rankSerUse"/>" /> <input
			type="hidden" name="partRankSerUse"
			value="<s:property value="rank.partRankSerUse"/>" /> <input
			type="hidden" name="rankOthProd"
			value="<s:property value="rank.rankOthProd"/>" /> <input
			type="hidden" name="rankOthProdCount"
			value="<s:property value="rank.rankOthProdCount"/>" /> <input
			type="hidden" name="rankOthProdUse"
			value="<s:property value="rank.rankOthProdUse"/>" /> <input
			type="hidden" name="partRankOthProdUse"
			value="<s:property value="rank.partRankOthProdUse"/>" />

		<!-- 第三页面 -->
		<input type="hidden" name="rankIfGradeEval"
			value="<s:property value="rank.rankIfGradeEval"/>" /> <input
			type="hidden" name="rankSerGradeType"
			value="<s:property value="rank.rankSerGradeType"/>" /> <input
			type="hidden" name="rankIfRiskEval"
			value="<s:property value="rank.rankIfRiskEval"/>" /> <input
			type="hidden" name="rankSerRiskType"
			value="<s:property value="rank.rankSerRiskType"/>" /> <input
			type="hidden" name="rankIfSuffReco"
			value="<s:property value="rank.rankIfSuffReco"/>" /> <input
			type="hidden" name="rankIfSuffRecoType"
			value="<s:property value="rank.rankIfSuffRecoType"/>" /> <input
			type="hidden" name="rankIfResponse"
			value="<s:property value="rank.rankIfResponse"/>" /> <input
			type="hidden" name="rankResponseType"
			value="<s:property value="rank.rankResponseType"/>" /> <input
			type="hidden" name="rankIfSysInte"
			value="<s:property value="rank.rankIfSysInte"/>" /> <input
			type="hidden" name="rankSysInteType"
			value="<s:property value="rank.rankSysInteType"/>" /> <input
			type="hidden" name="rankIfSecCon"
			value="<s:property value="rank.rankIfSecCon"/>" /> <input
			type="hidden" name="rankSecConypeType"
			value="<s:property value="rank.rankSecConypeType"/>" /> <input
			type="hidden" name="rankIfSecTrain"
			value="<s:property value="rank.rankIfSecTrain"/>" /> <input
			type="hidden" name="rankSecTrainType"
			value="<s:property value="rank.rankSecTrainType"/>" /> <input
			type="hidden" name="rankOthSerName"
			value="<s:property value="rank.rankOthSerName"/>" /> <input
			type="hidden" name="rankIfOthSer"
			value="<s:property value="rank.rankIfOthSer"/>" /> <input
			type="hidden" name="rankOthUseType"
			value="<s:property value="rank.rankOthUseType"/>" />
		<!-- 第四页面 -->
		<input type="hidden" name="rankOrganType"
			value="<s:property value="rank.rankOrganType"/>" /> <input
			type="hidden" name="rankOrganArea"
			value="<s:property value="rank.rankOrganArea"/>" /> <input
			type="hidden" name="rankBusinessType"
			value="<s:property value="rank.rankBusinessType"/>" /> <input
			type="hidden" name="rankGrade"
			value="<s:property value="rank.rankGrade"/>" /> <input type="hidden"
			name="rankInfoSysIntr"
			value="<s:property value="rank.rankInfoSysIntr"/>" />

		<!-- 第五页面 -->
		<input type="hidden" name="rankTime"
			value="<s:property value="rank.rankTime"/>" /> <input type="hidden"
			name="rankJudge" value="<s:property value="rank.rankJudge"/>" /> <input
			type="hidden" name="rankIsDep"
			value="<s:property value="rank.rankIsDep"/>" /> <input type="hidden"
			name="rankDepName" value="<s:property value="rank.rankDepName"/>" />
		<input type="hidden" name="rankDepJudge"
			value="<s:property value="rank.rankDepJudge"/>" /> <input
			type="hidden" name="rankAccess"
			value="<s:property value="rank.rankAccess"/>" />
		<!-- 重命名字段值 -->
		<input type="hidden" name="reRankAccess"
			value="<s:property value="rank.reRankAccess"/>" /><input
			type="hidden" name="rankDoc"
			value="<s:property value="rank.rankDoc"/>" /> <input type="hidden"
			name="rankInformant" value="<s:property value="rank.rankInformant"/>" />
		<input type="hidden" name="rankDate"
			value="<s:property value="rank.rankDate"/>" />

		<div id="divDetail">
			<table width=100% border="1" bordercolor="#5AA4D1" cellpadding="0"
				cellspacing="0">
				<tr>
					<td class='r2titler' colspan="4">信息系统提交材料情况</td>
				</tr>
				<tr>
					<td width="300" align="right">01系统拓扑结构及说明</td>
					<td width="200" align="center">有 <input type="radio"
						name="rankTopStruct"
						onclick="ifShowDocDiv(this,'rankTopStruct','updatebutton1')"
						value="1" <s:if test="%{1==rank.rankTopStruct}">checked</s:if>>
						无 <input type="radio" name="rankTopStruct" value="0"
						<s:if test="%{0==rank.rankTopStruct}">checked</s:if>>
					</td>
					<td width="200" align="right">附件名称</td>
					<td width="*">
						<div id="divtop_main">
							<input type="hidden" name="ftopRelAcc1FileName"
								id="ftopRelAcc1FileNamehidden" maxlength="200"
								value="<s:property value="rank.rankTopRelAcc"/>"> <input
								type="button" value="替换附件" onclick="addtopFiles();"
								<s:if test="%{0==rank.rankTopStruct}">disabled</s:if>
								name="updatebutton1" id="updatebutton1">
						</div> <span id="msg1"></span>
					</td>
				</tr>
				<tr>
					<td align="right">02系统安全组织机构及管理制度</td>
					<td align="center">有 <input type="radio" name="rankSysManage"
						value="1"
						onclick="ifShowDocDiv(this,'rankSysManage','updatebutton2')"
						<s:if test="%{1==rank.rankSysManage}">checked</s:if>> 无 <input
						type="radio" name="rankSysManage" value="0"
						<s:if test="%{0==rank.rankSysManage}">checked</s:if>>
					</td>
					<td align="right">附件名称</td>
					<td>

						<div id="divsafe_main">
							<input type="hidden" name="fsysManRel1FileName" maxlength="200"
								id="fsysManRel1FileNamehidden"
								value="<s:property value="rank.rankSysManRel"/>"> <input
								type="button" value="替换附件" onclick="addsafeFiles();"
								<s:if test="%{0==rank.rankSysManage}">disabled</s:if>
								name="updatebutton2" id="updatebutton2">
						</div> <span id="msg2"></span>
					</td>
				</tr>
				<tr>
					<td align="right">03系统安全保护设施设计实施方案或改建实施方案</td>
					<td align="center">有 <input type="radio" name="rankSysPlan"
						value="1"
						onclick="ifShowDocDiv(this,'rankSysPlan','updatebutton3')"
						<s:if test="%{1==rank.rankSysPlan}">checked</s:if>> 无 <input
						type="radio" name="rankSysPlan" value="0"
						<s:if test="%{0==rank.rankSysPlan}">checked</s:if>>
					</td>
					<td align="right">附件名称</td>
					<td>
						<!-- <input type="button" name="rankSysPlanRel" value="附件上载"  id="rankSysPlanRel" class="btn"> -->
						<div id="divplan_main">
							<input type="hidden" name="fsysPlanRel1FileName" maxlength="200"
								id="fsysPlanRel1FileNamehidden"
								value="<s:property value="rank.rankSysPlanRel"/>"> <input
								type="button" value="替换附件" onclick="addplanFiles();"
								<s:if test="%{0==rank.rankSysPlan}">disabled</s:if>
								name="updatebutton3" id="updatebutton3">
						</div> <span id="msg3"></span>
					</td>
				</tr>
				<tr>
					<td align="right">04系统使用的安全产品清单及认证、销售许可证明</td>
					<td align="center">有 <input type="radio" name="rankSysLicense"
						value="1"
						onclick="ifShowDocDiv(this,'rankSysLicense','updatebutton4')"
						<s:if test="%{1==rank.rankSysLicense}">checked</s:if>> 无 <input
						type="radio" name="rankSysLicense" value="0"
						<s:if test="%{0==rank.rankSysLicense}">checked</s:if>>
					</td>
					<td align="right">附件名称</td>
					<td>
						<%-- <input type="text" name="rankSysLiceRel"   value="<s:property value="rank.rankSysLiceRel"/>"  class="btn"> --%>
						<div id="divlise_main">
							<input type="hidden" name="fsysLiceRel1FileName" maxlength="200"
								id="fsysLiceRel1FileNamehidden"
								value="<s:property value="rank.rankSysLiceRel"/>"> <input
								type="button" value="替换附件" onclick="addliseFiles();"
								<s:if test="%{0==rank.rankSysLicense}">disabled</s:if>
								name="updatebutton4" id="updatebutton4">
						</div> <span id="msg4"></span>
					</td>
				</tr>
				<tr>
					<td align="right">05系统等级测评报告</td>
					<td align="center">有 <input type="radio" name="rankSysReport"
						value="1"
						onclick="ifShowDocDiv(this,'rankSysReport','updatebutton5')"
						<s:if test="%{1==rank.rankSysReport}">checked</s:if>> 无 <input
						type="radio" name="rankSysReport" value="0"
						<s:if test="%{0==rank.rankSysReport}">checked</s:if>>
					</td>
					<td align="right">附件名称</td>
					<td>
						<%-- <input type="text" name="rankSysReportRel" value="<s:property value="rank.rankSysReportRel"/>"   disabled="disabled" id="rankSysReportRel" class="btn"> --%>
						<div id="divrep_main">
							<input type="hidden" name="fsysReportRel1FileName"
								maxlength="200" id="fsysReportRel1FileNamehidden"
								value="<s:property value="rank.rankSysReportRel"/>"> <input
								type="button" value="替换附件" onclick="addrepFiles();"
								<s:if test="%{0==rank.rankSysReport}">disabled</s:if>
								name="updatebutton5" id="updatebutton5">
						</div> <span id="msg5"></span>
					</td>
				</tr>
				<tr>
					<td align="right">06专家评审情况</td>
					<td align="center">有 <input type="radio" name="rankPeerRev"
						value="1"
						onclick="ifShowDocDiv(this,'rankPeerRev','updatebutton6')"
						<s:if test="%{1==rank.rankPeerRev}">checked</s:if>> 无 <input
						type="radio" name="rankPeerRev" value="0"
						<s:if test="%{0==rank.rankPeerRev}">checked</s:if>>
					</td>
					<td align="right">附件名称</td>
					<td>
						<%-- <input type="button" name="rankPeerRevRel" value="<s:property value="rank.rankPeerRevRel"/>" id="rankPeerRevRel" class="btn">
				 --%>
						<div id="divpeer_main">
							<input type="hidden" name="fpeerRevRel1FileName" maxlength="200"
								id="fpeerRevRel1FileNamehidden"
								value="<s:property value="rank.rankPeerRevRel"/>"> <input
								type="button" value="替换附件" onclick="addpeerFiles();"
								<s:if test="%{0==rank.rankPeerRev}">disabled</s:if>
								name="updatebutton6" id="updatebutton6">
						</div> <span id="msg6"></span>
					</td>
				</tr>
				<tr>
					<td align="right">07上级主管部门审批意见</td>
					<td align="center">有 <input type="radio" name="rankSuperOpin"
						value="1"
						onclick="ifShowDocDiv(this,'rankSuperOpin','updatebutton7')"
						<s:if test="%{1==rank.rankSuperOpin}">checked</s:if>> 无 <input
						type="radio" name="rankSuperOpin" value="0"
						<s:if test="%{0==rank.rankSuperOpin}">checked</s:if>>
					</td>
					<td align="right">附件名称</td>
					<td>
						<div id="divsuper_main">
							<input type="hidden" name="fsuperOpinRel1FileName"
								maxlength="200" id="fsuperOpinRel1FileNamehidden"
								value="<s:property value="rank.rankSuperOpinRel"/>"> <input
								type="button" value="替换附件" onclick="addsuperFiles();"
								<s:if test="%{0==rank.rankSuperOpin}">disabled</s:if>
								name="updatebutton7" id="updatebutton7">
						</div> <span id="msg7"></span>
					</td>
				</tr>

			</table>
			<div align="right">
				<input type="submit" name="btnReturn" id="btnSub" value="保存"
					class="btnyh" style="margin-right: 0px;padding-right: 0px"
					onclick="gohead();">

			</div>
		</div>

	</form>
</body>