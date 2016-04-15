<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<html>
  <head>
    <SCRIPT src="${ctx}/scripts/Calendar.js" type=text/javascript></SCRIPT>
    <title>定级单位信息</title>
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
 
	<script type="text/javascript">
	 function first(){
	 ifFlag();
	 
	 }
	
	  function ifFlag(){
	  	var flag=document.getElementsByName("rankFlag");
	    if(flag[0].checked){
			   document.getElementById("parentUnit").disabled=false;
			   document.getElementById("parentName").disabled=false;
	     }else{
	     	 document.getElementById("parentUnit").value="";
	         document.getElementById("parentName").value="";
	         document.getElementById("parentUnit").disabled=true;
			 document.getElementById("parentName").disabled=true;
	     
	     }
	  
	  }
	  function yanzheng10(file,id,str){
			   if(file.value==str){
			  	document.getElementById(id).disabled=false;
			   }else{  
			    document.getElementById(id).value="";
			  	document.getElementById(id).disabled=true;
			   }
	
		}

		function goahead(){
			document.frm.target="mainFrame";
			var rankFlags=document.getElementsByName("rankFlag"); 
			var parentName=document.getElementById("parentName");
			var parentUnit=document.getElementById("parentUnit");
			var rankEvalUnitName=document.getElementById("rankEvalUnitName");
			var rankUseDate=document.getElementById("rankUseDate");
			if($.trim(rankEvalUnitName.value)==""){
				alert('等级评测单位名称不能为空！');
				$("#rankEvalUnitName").focus();
	              return ;
			}
			if($.trim(rankUseDate.value)==""){
				alert('何时投入运行使用不能为空！');
				$("#rankUseDate").focus();
	              return ;
			}
			
			if(rankFlags[0].checked){
				if($.trim(parentName.value)==""){
					alert('上级系统名称不能为空！');
					$("#parentName").focus();
                   return ;
				}
				if($.trim(parentUnit.value)==""){
					alert('上级系统所属单位名称不能为空！');
					$("#parentUnit").focus();
                   return ;
				}
			}
           document.frm.action="";
			var formName = document.getElementById("frm");
			formName.action = "${ctx}/rank/rankAction_rankFourBeforeUpdate.action";
			formName.submit();
		}
		function addFiles(){
		if(!confirm('确定替换原文件吗？')){
			return;
		}
		var filesDiv=document.getElementById("divUploadify_main");
		var fileInput=document.createElement("input");
		
		fileInput.type="file";
		fileInput.name="frankEvalRelAccess";
		fileInput.id="frankEvalRelAccess";
		var upButton=document.createElement("input");
		upButton.type="button";
		upButton.value="上传文件";
		
		upButton.onclick=function upfile(){
			var frankAccess=document.getElementById("frankEvalRelAccess");
			if($.trim(frankAccess.value)==""){
      				alert("请选择上传文件！");
      					 return ;
    		}
			document.frm.target="hiddenframe";
			document.frm.action="${ctx}/rank/upfile.action";
			document.frm.submit();
		};
		var upbu=document.getElementById("updatebutton");
		filesDiv.removeChild(upbu);
		
	    var upbuh=document.getElementById("frankEvalRelAccesshidden");
		filesDiv.removeChild(upbuh); 
		var divname=document.createElement("div");
		divname.appendChild(fileInput);
		divname.appendChild(upButton);
		filesDiv.appendChild(divname);
	}   
	/*执行回调函数  */
		function callback(msg)    
		{    
		
    		document.getElementById( "msg" ).innerHTML =  "<font color='red'>" +msg+ "</font>" ;    
		}
	function callback2(msg)    
		{    
		
    		document.getElementById( "msg" ).innerHTML +=  "<input type='hidden' name='reFrankEvalRelAccessFileName' value='"+msg+"'>";    
		} 	
		
	</script>
  </head>
  
  <body  onload="first()">
  <iframe name="hiddenframe" id="hiddenframe" style="display:none"></iframe>
  <form id="frm" name="frm"  action=""  method="post" style="width:99.9%" enctype="multipart/form-data">
<input type="hidden" name="rankId" value="<s:property value="rank.rankId"/>" />
<input type="hidden" name="sysInfoBusDescription" value="<s:property value="rank.sysInfoBusDescription"/>" />
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
<input type="hidden" name="rankDoc" value="<s:property value="rank.rankDoc"/>" />
<input type="hidden" name="rankInformant" value="<s:property value="rank.rankInformant"/>" />
<input type="hidden" name="rankDate" value="<s:property value="rank.rankDate"/>" />

<!-- 第六页面 -->
<input type="hidden" name="rankTopStruct" value="<s:property value="rank.rankTopStruct"/>" />
<input type="hidden" name="rankTopRelAcc" value="<s:property value="rank.rankTopRelAcc"/>" />
<input type="hidden" name="rankSysManage" value="<s:property value="rank.rankSysManage"/>" />
<input type="hidden" name="rankSysManRel" value="<s:property value="rank.rankSysManRel"/>" />
<input type="hidden" name="rankSysPlan" value="<s:property value="rank.rankSysPlan"/>" />
<input type="hidden" name="rankSysPlanRel" value="<s:property value="rank.rankSysPlanRel"/>" />
<input type="hidden" name="rankSysLicense" value="<s:property value="rank.rankSysLicense"/>" />
<input type="hidden" name="rankSysLiceRel" value="<s:property value="rank.rankSysLiceRel"/>" />
<input type="hidden" name="rankSysReport" value="<s:property value="rank.rankSysReport"/>" />
<input type="hidden" name="rankSysReportRel" value="<s:property value="rank.rankSysReportRel"/>" />
<input type="hidden" name="rankPeerRev" value="<s:property value="rank.rankPeerRev"/>" />
<input type="hidden" name="rankPeerRevRel" value="<s:property value="rank.rankPeerRevRel"/>" />
<input type="hidden" name="rankSuperOpinRel" value="<s:property value="rank.rankSuperOpinRel"/>" />
<input type="hidden" name="rankSuperOpin" value="<s:property value="rank.rankSuperOpin"/>" />
<input type="hidden" name="rankCount" value="<s:property value="rank.rankCount"/>" />
<input type="hidden" name="sysInfoName" value="<s:property value="rank.sysInfoName"/>" />
<input type="hidden" name="sysInfoId" value="<s:property value="rank.sysInfoId"/>" /> 
<input type="hidden" name="rankEvalRelAccess" value="<s:property value="rank.rankEvalRelAccess"/>" />
<input type="hidden" name="rankCoveArea" value="<s:property value="rank.rankCoveArea"/>" />
<input type="hidden" name="rankOthArea" value="<s:property value="rank.rankOthArea"/>" />
<input type="hidden" name="rankNetworkProp" value="<s:property value="rank.rankNetworkProp"/>" />
<input type="hidden" name="rankOthNetworkProp" value="<s:property value="rank.rankOthNetworkProp"/>" />
<input type="hidden" name="rankSysConn" value="<s:property value="rank.rankSysConn"/>" />
<input type="hidden" name="rankOtherSysConn" value="<s:property value="rank.rankOtherSysConn"/>" />
<div style=" width:100%;">
<table width="100%" border="1" bordercolor="#5AA4D1" cellpadding="0" cellspacing="0">

  <tr>
    <td colspan="4"  class='r2titler'>等级测评单位信息</td> 
  </tr>
  <tr>
    <td width="16%" align="right"><font color="#ff0000">*</font>09等级评测单位名称:</td>
    <td width="22%" style="text-align: left;"><input type="text"  style="width:200px"  name="rankEvalUnitName" id="rankEvalUnitName" maxlength="50"  class="txt 100" value="<s:property value="rank.rankEvalUnitName"/>" onblur="yanzheng1(this)" ></td>
    <td width="13%" align="right"><font color="#ff0000">*</font>10何时投入运行使用:</td>
    <td  style="text-align: left;"><input type="text"   style="width:200px" name="rankUseDate" id="rankUseDate" readonly="readonly"  onclick="SelectDate(this)"  class="txt 100" value="<s:property value="rank.rankUseDate"/>" ></td> 
  </tr>
  <tr>
    <td align="right">11系统是否是分系统:</td>
    <td style="text-align: left;"><input type="radio" name="rankFlag" value="1"  <s:if test="%{1==rank.rankFlag}">checked</s:if> onclick="ifFlag()">
      是
      <input type="radio" name="rankFlag" value="0" <s:if test="%{0==rank.rankFlag}">checked</s:if> onclick="ifFlag()"  >
      否（如选择是请填下两项） </td>
    <td align="right">12上级系统名称:</td>
    <td style="text-align: left;"><input type="text" style="width: 200px" name="rankParentSysName" value="<s:property value="rank.rankParentSysName"/>"   id="parentName" class="txt 100" maxlength="50" onblur="yanzheng1(this)"  >    </td> 
  </tr>
  <tr>
    <td align="right">13上级系统所属单位名称:</td>
    <td colspan="3" style="text-align: left;"><input type="text"  style="width:200px" name="rankParentUnitName" value="<s:property value="rank.rankParentUnitName"/>" maxlength="50"  id="parentUnit" class="txt 100"onblur="yanzheng1(this)" >    </td> 
  </tr>
  <tr>
    <td align="right">相关附件:</td>
    <td colspan="3" style="text-align: left;"><div id="divUploadify_main">      
    <input type="hidden" name="frankEvalRelAccessFileName" maxlength="255" id="frankEvalRelAccesshidden" value="<s:property value="rank.rankEvalRelAccess"/>"> 
      <input type="button" value="替换附件" onclick="addFiles();" name="updatebutton" id="updatebutton">
    </div> <span id="msg"></span></td>
  </tr>
</table>
</div>
          <div >
                   <table width="100%" border="0" cellspacing="0" cellpadding="0">
                       <tr >

					      <td  align="right">
							 <input type="button" value="下一步" onclick="goahead()"  class="btnyh"   />
                           </td>
						    
                      </tr>
                   </table>
	         </div>
	         
	         
	         

</form>
</body>
</html>
