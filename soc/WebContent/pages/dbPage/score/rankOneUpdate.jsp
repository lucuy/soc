<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<html>
  <head>
  <title>定级单位信息</title>
<SCRIPT src="${ctx}/scripts/Calendar.js" type=text/javascript></SCRIPT>
<link href="${ctx}/styles/dbStyles/pageCss.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/dbStyles/right.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/template.css" rel="stylesheet" type="text/css">
<SCRIPT src="${ctx}/scripts/jquery-1.7.2.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/jquery-ui.custom.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/util.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/popuplayer.js" type=text/javascript></SCRIPT>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/check.js"></script>
 
	<script type="text/javascript">
	  function yanzheng10(file,id,str){
			   if(file.value==str){
			  	document.getElementById(id).disabled=false;
			   }else{  
			    document.getElementById(id).value="";
			  	document.getElementById(id).disabled=true;
			   }
	
		}

		function goahead(){
			var formName = document.getElementById("frm");
			formName.action = "${ctx}/rank/rankAction_rankOneUpdate.action";
			formName.submit();
		}

	</script>
  </head>
  
  <body>
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
<input type="hidden" name="rankEvalUnitName" value="<s:property value="rank.rankEvalUnitName"/>" />
<input type="hidden" name="rankUseDate" value="<s:property value="rank.rankUseDate"/>" />
<input type="hidden" name="rankFlag" value="<s:property value="rank.rankFlag"/>" />
<input type="hidden" name="rankParentSysName" value="<s:property value="rank.rankParentSysName"/>" />
<input type="hidden" name="rankParentUnitName" value="<s:property value="rank.rankParentUnitName"/>" />
<input type="hidden" name="rankEvalRelAccess" value="<s:property value="rank.rankEvalRelAccess"/>" />
<div>
  <table  width="100%" border="1" bordercolor="#5AA4D1" cellpadding="0" cellspacing="0"  >
  <tr>
    <td colspan="6"  class='r2titler'>信息系统情况</td>
  </tr>
  <tr>
    <td colspan="2" align="right" width="20%">01系统名称:</td>
    <td style="text-align: left;"><input type="text" name="sysInfoName"  readonly="readonly" value="<s:property value="rank.sysInfoName"/>"  style="width:200px"/></td>
    <td colspan="2" align="right">02系统编号:</td>
    <td  style="text-align: left;"><input type="text" name="sysInfoId"    readonly="readonly"  value="<s:property value="rank.sysInfoId"/>"  style="width:200px"/></td>
  </tr>
  <tr>
    <td align="right" rowspan="2">05系统网络平台:</td>
    <td align="right" width="10%">覆盖范围:</td>
    <td colspan="4">
	      <input type="radio" name="rankCoveArea" value="1" <s:if test="%{1==rank.rankCoveArea}">checked</s:if>  onclick="yanzheng10(this,'otherArea','4')" />
           1 局域网&nbsp;&nbsp;&nbsp;
            <input type="radio" name="rankCoveArea" value="2" <s:if test="%{2==rank.rankCoveArea}">checked</s:if> onclick="yanzheng10(this,'otherArea','4')" />
           2 城域网&nbsp;
            <input type="radio" name="rankCoveArea" value="3" <s:if test="%{3==rank.rankCoveArea}">checked</s:if> onclick="yanzheng10(this,'otherArea','4')"/>
           3 广域网&nbsp;
            <input type="radio" name="rankCoveArea" value="4" <s:if test="%{4==rank.rankCoveArea}">checked</s:if> onclick="yanzheng10(this,'otherArea','4')"/>
           9 其它
            <input type="text" name="rankOthArea" value="<s:property value="rank.rankOthArea"/>"  id="otherArea"  class="showBorder" maxlength="50"  style="width:100px" onblur="yanzheng1(this)"/>	
    </td>
  </tr>
  <tr>
    <td align="right">网络性质:</td>
    <td colspan="4">
	 <input type="radio" name="rankNetworkProp" <s:if test="%{1==rank.rankNetworkProp}">checked</s:if> value="1" onclick="yanzheng10(this,'otherProp','3')"  />
            1 业务专网&nbsp;
            <input type="radio" name="rankNetworkProp" <s:if test="%{2==rank.rankNetworkProp}">checked</s:if> value="2" onclick="yanzheng10(this,'otherProp','3')" />
           2 互联网&nbsp;
            <input type="radio" name="rankNetworkProp" <s:if test="%{3==rank.rankNetworkProp}">checked</s:if> value="3" onclick="yanzheng10(this,'otherProp','3')" />
            9 其它
            <input type="text" name="rankOthNetworkProp"  value="<s:property value="rank.rankOthNetworkProp"/>"   id="otherProp"  class="showBorder" maxlength="50"  style="width:100px" onblur="yanzheng1(this)" />	</td>
  </tr>
  <tr>
    <td align="right" colspan="2">06系统互联情况:  </td>
   <td colspan="4"><input type="radio" name="rankSysConn" <s:if test="%{1==rank.rankSysConn}">checked</s:if> value="1" onclick="yanzheng10(this,'otherType','4')"   />
             1 与其他行业系统连接&ensp;&ensp;
             <input type="radio" name="rankSysConn" value="2" <s:if test="%{2==rank.rankSysConn}">checked</s:if> onclick="yanzheng10(this,'otherType','4')"/>
               2 与本行业其他单位系统连接 
               <br> 
               <input type="radio" name="rankSysConn" value="3" <s:if test="%{3==rank.rankSysConn}">checked</s:if> onclick="yanzheng10(this,'otherType','4')" />
                   3 与本单位其他系统连接
                <input type="radio" name="rankSysConn" value="4" <s:if test="%{4==rank.rankSysConn}">checked</s:if> onclick="yanzheng10(this,'otherType','4')" />
               9 其它
                <input type="text" name="rankOtherSysConn"  value="<s:property value="rank.rankOtherSysConn"/>" id="otherType"  class="showBorder" maxlength="50"  style="width:100px" onblur="yanzheng1(this)" /></td> 
  </tr>
</table>
</div>

          <div >
                   <table width="100%" border="0" cellspacing="0" cellpadding="0">
                       <tr>

					      <td  align="right">
							 <input type="button" value="下一步" onclick="goahead()" class="btnyh"   />
                           </td>
						    
                      </tr>
                   </table>
	         </div>
	         
	         
	         

</form>
</body>
</html>
