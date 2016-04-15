<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<html >
<head>
	<title>编辑信息系统定级情况表</title>
<link href="${ctx}/styles/dbStyles/pageCss.css" rel="stylesheet" type="text/css">	
<link href="${ctx}/styles/dbStyles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/right.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/template.css" rel="stylesheet" type="text/css">
 <SCRIPT src="${ctx}/scripts/Calendar.js" type=text/javascript></SCRIPT>
 <SCRIPT src="${ctx}/scripts/jquery-1.7.2.min.js" type=text/javascript></SCRIPT>
 <script type="text/javascript" >
 function gohead(){
 		var btnSub=document.getElementById("btnSub");
        btnSub.disabled="disabled";
 		document.scoreresultForm.target="mainFrame";
 		document.scoreresultForm.action="";
	    var formName = document.getElementById("scoreresultForm");
		formName.action = "${ctx}/rank/rankAction_rankSix.action";
		formName.submit();	
 }
 function uptop(){
    var topAccess=document.getElementById("ftopRelAcc1");
	if($.trim(topAccess.value)==""){
      alert("请选择拓扑上传文件！");
       return ;
    }
	var divornot=document.getElementById("msg");
	 if(divornot!=null){
		divornot.removeAttribute("id");
	} 
	
 	 document.scoreresultForm.target="hiddenframe";
	 document.scoreresultForm.action="${ctx}/rank/uptopfile.action";
	 document.scoreresultForm.submit();
	 var span=document.getElementById("msg1");
	var divname=document.createElement("div");
	divname.id="msg";
	span.appendChild(divname);
 }
 function upsysman(){
 	var sysAccess=document.getElementById("fsysManRel1");
	if($.trim(sysAccess.value)==""){
      alert("请选择系统安全组织与管理上传文件！");
       return ;
    }
	var divornot=document.getElementById("msg");
	 if(divornot!=null){
		divornot.removeAttribute("id");
	} 
	
 	 document.scoreresultForm.target="hiddenframe";
	 document.scoreresultForm.action="${ctx}/rank/upsysmanfile.action";
	 document.scoreresultForm.submit();
	 var span=document.getElementById("msg2");
	var divname=document.createElement("div");
	divname.id="msg";
	span.appendChild(divname);
 }
 function upsysplan(){
 	var planAccess=document.getElementById("fsysPlanRel1");
	if($.trim(planAccess.value)==""){
      alert("请选择系统安全保护设施上传文件！");
       return ;
    }
	var divornot=document.getElementById("msg");
	 if(divornot!=null){
		divornot.removeAttribute("id");
	} 
	
 	 document.scoreresultForm.target="hiddenframe";
	 document.scoreresultForm.action="${ctx}/rank/upsysplanfile.action";
	 document.scoreresultForm.submit();
	 var span=document.getElementById("msg3");
	var divname=document.createElement("div");
	divname.id="msg";
	span.appendChild(divname);

 }
 function upsysre(){
 	var reportAccess=document.getElementById("fsysReportRel1");
	if($.trim(reportAccess.value)==""){
      alert("请选择系统报告上传文件！");
       return ;
    }
	var divornot=document.getElementById("msg");
	 if(divornot!=null){
		divornot.removeAttribute("id");
	} 
	
 	 document.scoreresultForm.target="hiddenframe";
	 document.scoreresultForm.action="${ctx}/rank/upsysrefile.action";
	 document.scoreresultForm.submit();
	 var span=document.getElementById("msg5");
	var divname=document.createElement("div");
	divname.id="msg";
	span.appendChild(divname);
 }
 function uppeer(){
 var peerAccess=document.getElementById("fpeerRevRel1");
	if($.trim(peerAccess.value)==""){
      alert("请选择专家评审意见上传文件！");
       return ;
    }
	var divornot=document.getElementById("msg");
	 if(divornot!=null){
		divornot.removeAttribute("id");
	} 
	
      document.scoreresultForm.target="hiddenframe";
	 document.scoreresultForm.action="${ctx}/rank/uppeerfile.action";
	 document.scoreresultForm.submit();
	 var span=document.getElementById("msg6");
	var divname=document.createElement("div");
	divname.id="msg";
	span.appendChild(divname);
 }
 function upsuper(){
 	var superAccess=document.getElementById("fsuperOpinRel1");
	if($.trim(superAccess.value)==""){
      alert("请选择上级主管部门审批上传文件！");
       return ;
    }
	var divornot=document.getElementById("msg");
	 if(divornot!=null){
		divornot.removeAttribute("id");
	} 
	
 	 document.scoreresultForm.target="hiddenframe";
	 document.scoreresultForm.action="${ctx}/rank/upsuperfile.action";
	 document.scoreresultForm.submit();
	 var span=document.getElementById("msg7");
	var divname=document.createElement("div");
	divname.id="msg";
	span.appendChild(divname);
 }
 function upsyslice(){
 	 var sliceAccess=document.getElementById("fsysLiceRel1");
	if($.trim(sliceAccess.value)==""){
      alert("请选择系统清单上传文件！");
       return ;
    }
	var divornot=document.getElementById("msg");
	 if(divornot!=null){
		divornot.removeAttribute("id");
	} 
	
     document.scoreresultForm.target="hiddenframe";
	 document.scoreresultForm.action="${ctx}/rank/upsyslicefile.action";
	 document.scoreresultForm.submit();
	 var span=document.getElementById("msg4");
	var divname=document.createElement("div");
	divname.id="msg";
	span.appendChild(divname);
 }
 function callback(msg)    
	{    
    	document.getElementById( "msg" ).innerHTML =  "<font color='red'>" +msg+ "</font>" ;    
	}
function callback2(name,msg)    
	{    
    	document.getElementById( "msg" ).innerHTML +=  "<input type='hidden' name='"+name+"' value='"+msg+"'>"; ;    
	} 
	
	function divShow(var1,var2){//div显示隐藏
	var doc2=document.getElementById(var2);
	if(var1.value=="1"){
		doc2.style.display = 'block';
	}else{
		doc2.style.display = 'none';
	}

	}
 </script>
</head >
<body >
<iframe name="hiddenframe" id="hiddenframe" style="display:none"></iframe>
  <form id="scoreresultForm" name="scoreresultForm" action="" method="post" style="width:99%" enctype="multipart/form-data">
<!-- 第一页面 -->
<input type="hidden" name="rankId" value="<s:property value="rank.rankId"/>" />
<input type="hidden" name="sysInfoName" value="<s:property value="rank.sysInfoName"/>" />
<input type="hidden" name="sysInfoId" value="<s:property value="rank.sysInfoId"/>" /> 
<input type="hidden" name="sysInfoBusDescription" value="<s:property value="rank.sysInfoBusDescription"/>" />
<input type="hidden" name="rankEvalUnitName" value="<s:property value="rank.rankEvalUnitName"/>" />
<input type="hidden" name="rankUseDate" value="<s:property value="rank.rankUseDate"/>" />
<input type="hidden" name="rankFlag" value="<s:property value="rank.rankFlag"/>" />
<input type="hidden" name="rankParentSysName" value="<s:property value="rank.rankParentSysName"/>" />
<input type="hidden" name="rankParentUnitName" value="<s:property value="rank.rankParentUnitName"/>" />
<input type="hidden" name="rankEvalRelAccess" value="<s:property value="rank.rankEvalRelAccess"/>" />
<!-- 重命名字段值 -->
<input type="hidden" name="reRankEvalRelAccess" value="<s:property value="rank.reRankEvalRelAccess"/>" />
<input type="hidden" name="rankCoveArea" value="<s:property value="rank.rankCoveArea"/>" />
<input type="hidden" name="rankOthArea" value="<s:property value="rank.rankOthArea"/>" />
<input type="hidden" name="rankNetworkProp" value="<s:property value="rank.rankNetworkProp"/>" />
<input type="hidden" name="rankOthNetworkProp" value="<s:property value="rank.rankOthNetworkProp"/>" />
<input type="hidden" name="rankSysConn" value="<s:property value="rank.rankSysConn"/>" />
<input type="hidden" name="rankOtherSysConn" value="<s:property value="rank.rankOtherSysConn"/>" />
<!-- 第二页面 -->
<input type="hidden" name="rankSecCount" value="<s:property value="rank.rankSecCount"/>" />
<input type="hidden" name="rankSecUse" value="<s:property value="rank.rankSecUse"/>" />
<input type="hidden" name="partRankSecUse" value="<s:property value="rank.partRankSecUse"/>" />
<input type="hidden" name="rankNetCount" value="<s:property value="rank.rankNetCount"/>" />
<input type="hidden" name="rankNetUse" value="<s:property value="rank.rankNetUse"/>" />
<input type="hidden" name="partRankNetUse" value="<s:property value="rank.partRankNetUse"/>" />
<input type="hidden" name="rankSysCount" value="<s:property value="rank.rankSysCount"/>" />
<input type="hidden" name="rankSysUse" value="<s:property value="rank.rankSysUse"/>" />
<input type="hidden" name="partRankSysUse" value="<s:property value="rank.partRankSysUse"/>" />
<input type="hidden" name="rankSqlCount" value="<s:property value="rank.rankSqlCount"/>" />
<input type="hidden" name="rankSqlUse" value="<s:property value="rank.rankSqlUse"/>" />
<input type="hidden" name="partRankSqlUse" value="<s:property value="rank.partRankSqlUse"/>" />
<input type="hidden" name="rankSerCount" value="<s:property value="rank.rankSerCount"/>" />
<input type="hidden" name="rankSerUse" value="<s:property value="rank.rankSerUse"/>" />
<input type="hidden" name="partRankSerUse" value="<s:property value="rank.partRankSerUse"/>" />
<input type="hidden" name="rankOthProd" value="<s:property value="rank.rankOthProd"/>" />
<input type="hidden" name="rankOthProdCount" value="<s:property value="rank.rankOthProdCount"/>" />
<input type="hidden" name="rankOthProdUse" value="<s:property value="rank.rankOthProdUse"/>" />
<input type="hidden" name="partRankOthProdUse" value="<s:property value="rank.partRankOthProdUse"/>" />

<!-- 第三页面 -->
<input type="hidden" name="rankIfGradeEval" value="<s:property value="rank.rankIfGradeEval"/>" />
<input type="hidden" name="rankSerGradeType" value="<s:property value="rank.rankSerGradeType"/>" />
<input type="hidden" name="rankIfRiskEval" value="<s:property value="rank.rankIfRiskEval"/>" />
<input type="hidden" name="rankSerRiskType" value="<s:property value="rank.rankSerRiskType"/>" />
<input type="hidden" name="rankIfSuffReco" value="<s:property value="rank.rankIfSuffReco"/>" />
<input type="hidden" name="rankIfSuffRecoType" value="<s:property value="rank.rankIfSuffRecoType"/>" />
<input type="hidden" name="rankIfResponse" value="<s:property value="rank.rankIfResponse"/>" />
<input type="hidden" name="rankResponseType" value="<s:property value="rank.rankResponseType"/>" />
<input type="hidden" name="rankIfSysInte" value="<s:property value="rank.rankIfSysInte"/>" />
<input type="hidden" name="rankSysInteType" value="<s:property value="rank.rankSysInteType"/>" />
<input type="hidden" name="rankIfSecCon" value="<s:property value="rank.rankIfSecCon"/>" />
<input type="hidden" name="rankSecConypeType" value="<s:property value="rank.rankSecConypeType"/>" />
<input type="hidden" name="rankIfSecTrain" value="<s:property value="rank.rankIfSecTrain"/>" />
<input type="hidden" name="rankSecTrainType" value="<s:property value="rank.rankSecTrainType"/>" />
<input type="hidden" name="rankOthSerName" value="<s:property value="rank.rankOthSerName"/>" />
<input type="hidden" name="rankIfOthSer" value="<s:property value="rank.rankIfOthSer"/>" />
<input type="hidden" name="rankOthUseType" value="<s:property value="rank.rankOthUseType"/>" />
<!-- 第四页面 -->
<input type="hidden" name="rankOrganType" value="<s:property value="rank.rankOrganType"/>" />
<input type="hidden" name="rankOrganArea" value="<s:property value="rank.rankOrganArea"/>" />
<input type="hidden" name="rankBusinessType" value="<s:property value="rank.rankBusinessType"/>" />
<input type="hidden" name="rankGrade" value="<s:property value="rank.rankGrade"/>" />
<input type="hidden" name="rankInfoSysIntr" value="<s:property value="rank.rankInfoSysIntr"/>" />

<!-- 第五页面 -->
<input type="hidden" name="rankTime" value="<s:property value="rank.rankTime"/>" />
<input type="hidden" name="rankJudge" value="<s:property value="rank.rankJudge"/>" />
<input type="hidden" name="rankIsDep" value="<s:property value="rank.rankIsDep"/>" />
<input type="hidden" name="rankDepName" value="<s:property value="rank.rankDepName"/>" />
<input type="hidden" name="rankDepJudge" value="<s:property value="rank.rankDepJudge"/>" />
<input type="hidden" name="rankAccess" value="<s:property value="rank.rankAccess"/>" />
<!-- 重命名字段值 -->
<input type="hidden" name="reRankAccess" value="<s:property value="rank.reRankAccess"/>" />
<input type="hidden" name="rankDoc" value="<s:property value="rank.rankDoc"/>" />
<input type="hidden" name="rankInformant" value="<s:property value="rank.rankInformant"/>" />
<input type="hidden" name="rankDate" value="<s:property value="rank.rankDate"/>" />
	
	<div id="divDetail">
		<table width="100%" border="1" bordercolor="#5AA4D1" cellpadding="0" cellspacing="0">
		<tr><td  class='r2titler' colspan="4" >信息系统提交材料情况</td></tr>
			<tr>
				<td width="30%"  align="right">01系统拓扑结构及说明</td>
				<td width="10%"  align="center">
					有 <input type="radio" name="rankTopStruct" value="1" onclick="divShow(this,'file1')"  > 
					无 <input type="radio" name="rankTopStruct" value="0" checked="checked" onclick="divShow(this,'file1')"  >
				</td>
				<td  width="10%" align="right">附件名称</td>
				<td  width="30%">
				   <div id="file1" style="display: none" >
				   	<input type="file" name="ftopRelAcc1" maxlength="200" id="ftopRelAcc1">			
				   	<input type="button" name="rankTopRelAcc" value="附件上载" id="rankTopRelAcc" class="btn" onclick="uptop();">
				   </div><span id="msg1"></span>
				</td>
			</tr>
			<tr>
				<td  align="right">02系统安全组织机构及管理制度</td>
				<td align="center">
					有 <input type="radio" name="rankSysManage" value="1" onclick="divShow(this,'file2')"  > 
					无 <input type="radio" name="rankSysManage" value="0" checked="checked" onclick="divShow(this,'file2')"  >
				</td>
				<td align="right">附件名称</td>
				<td>
				<div id="file2" style="display: none" >
				  <input type="file" name="fsysManRel1" maxlength="200" id="fsysManRel1">					
				   <input type="button" name="rankSysManRel" value="附件上载"  id="rankSysManRel" class="btn" onclick="upsysman();">
				</div><span id="msg2"></span>
				</td>
			</tr>
			<tr>
				<td  align="right">03系统安全保护设施设计实施方案或改建实施方案</td>
				<td align="center">
					有 <input type="radio" name="rankSysPlan" value="1" onclick="divShow(this,'file3')" > 
					无 <input type="radio" name="rankSysPlan" value="0" checked="checked" onclick="divShow(this,'file3')"  >
				</td>
				<td align="right">附件名称</td>
				<td>
					<div id="file3" style="display: none" >		
						<input type="file" name="fsysPlanRel1" maxlength="200" id="fsysPlanRel1">	
						<input type="button" name="rankSysPlanRel" value="附件上载"  id="rankSysPlanRel" class="btn" onclick="upsysplan();">
				   </div>	<span id="msg3"></span>
				</td>
			</tr>
			<tr>
				<td  align="right">04系统使用的安全产品清单及认证、销售许可证明</td>
				<td align="center">
					有 <input type="radio" name="rankSysLicense" value="1" onclick="divShow(this,'file4')" > 
					无 <input type="radio" name="rankSysLicense" value="0" checked="checked" onclick="divShow(this,'file4')" >
				</td>
				<td align="right">附件名称</td>
				<td>
				   <div id="file4" style="display: none" >
				     <input type="file" name="fsysLiceRel1" maxlength="200" id="fsysLiceRel1">
				     <input type="button" name="rankSysLiceRel" value="附件上载"   class="btn" onclick="upsyslice();">
				   </div><span id="msg4"></span>
				</td>
			</tr>
			<tr>
				<td  align="right">05系统等级测评报告</td>
				<td align="center">
					有 <input type="radio" name="rankSysReport" value="1" onclick="divShow(this,'file5')"> 
					无 <input type="radio" name="rankSysReport" value="0" checked="checked" onclick="divShow(this,'file5')" >
				</td>
				<td align="right">附件名称</td>
				<td>
				  <div id="file5" style="display: none" >
				   <input type="file" name="fsysReportRel1" maxlength="200" id="fsysReportRel1">
				   <input type="button" name="rankSysReportRel" value="附件上载"   id="rankSysReportRel" class="btn" onclick="upsysre();">
				  </div><span id="msg5"></span>	
				</td>
			</tr>
			<tr>
				<td  align="right">06专家评审情况</td>
				<td align="center">
					有 <input type="radio" name="rankPeerRev" value="1" onclick="divShow(this,'file6')"> 
					无 <input type="radio" name="rankPeerRev" value="0" checked="checked" onclick="divShow(this,'file6')">
				</td>
				<td align="right">附件名称</td>
				<td>
				  <div id="file6" style="display: none" >
				    <input type="file" name="fpeerRevRel1" maxlength="200" id="fpeerRevRel1">
				    <input type="button" name="rankPeerRevRel" value="附件上载" id="rankPeerRevRel" class="btn" onclick="uppeer();">
				  </div><span id="msg6"></span>
				</td>
			</tr>
			<tr>
				<td  align="right">07上级主管部门审批意见</td>
				<td align="center">
					有 <input type="radio" name="rankSuperOpin" value="1" onclick="divShow(this,'file7')"> 
					无 <input type="radio" name="rankSuperOpin" value="0" checked="checked" onclick="divShow(this,'file7')">
				</td>
				<td align="right">附件名称</td>
				<td>
				  <div id="file7" style="display: none">
					<input type="file" name="fsuperOpinRel1" maxlength="200"  id="fsuperOpinRel1">				
					<input type="button" name="rankSuperOpinRel" value="附件上载"  id="rankSuperOpinRel" class="btn" onclick="upsuper();">
				 </div>	<span id="msg7"></span>

				</td>
			</tr>
		</table>
	</div>
	 <div id="divAction" style="height:30;text-align: right">
		<input type="button" name="btnReturn" value="保存"  id="btnSub"  class="btnyh" onclick="gohead();">
	</div>
 


	  </form>
</body>

