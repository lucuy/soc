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

	  
	  
	  function yanhzheng3(file,str){
	  
		  if(file.value==100||file.value==0){
		  	document.getElementById(str).value="";
		  	document.getElementById(str).disabled=true;
		  }else{
		   document.getElementById(str).disabled=false;
		  }
		  
	  }


	</script>
  </head>
  
  <body  >
  <form id="frm" name="frm"  action="${ctx}/rank/rankAction_rankTwoUpdate.action"  method="post" style="width:99.9%">
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
<input type="hidden" name="rankCoveArea" value="<s:property value="rank.rankCoveArea"/>" />
<input type="hidden" name="rankOthArea" value="<s:property value="rank.rankOthArea"/>" />
<input type="hidden" name="rankNetworkProp" value="<s:property value="rank.rankNetworkProp"/>" />
<input type="hidden" name="rankOthNetworkProp" value="<s:property value="rank.rankOthNetworkProp"/>" />
<input type="hidden" name="rankSysConn" value="<s:property value="rank.rankSysConn"/>" />
<input type="hidden" name="rankOtherSysConn" value="<s:property value="rank.rankOtherSysConn"/>" />


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

<div  class="gjcp">
<table border="1" bordercolor="#5AA4D1" cellpadding="0" cellspacing="0">
  <tr>
    <td class='r2titler' colspan="6">07 关键产品使用情况</td>
  </tr>  
       <tr>
         <td  rowspan="2"  style="text-align: center;width: 100">序号：</td>
          <td width="202" rowspan="2"  style="text-align: center;">产品类型：</td>
          <td width="173" rowspan="2"  style="text-align: center;">数量：</td>
          <td colspan="3" class="tdLabel" style="text-align: center;">使用国产品率：</td>
        </tr>
        <tr>
          <td width="239" height="37" class="tdLabel" style="text-align: center;">全部使用</td>
          <td width="210" class="tdLabel" style="text-align: center;">全部未使用</td>
          <td width="442" class="tdLabel" style="text-align: center;">部分使用及使用率</td>
        </tr>
        <tr>
           <td align="center">1</td>
          <td style="text-align: center;">安全专用产品:</td>
          <td align="center"><input type="text" name="rankSecCount" value="<s:property value="rank.rankSecCount"/>" id="secCount"  class="txt" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"  maxlength='6'  style="width:50px; text-align:right"/></td>
          <td align="center"><input type='radio' name="rankSecUse" <s:if test="%{100==rank.rankSecUse}">checked</s:if> value="100" onclick="yanhzheng3(this,'partRankSecUse')"   /></td>
          <td align="center"><input type='radio' name="rankSecUse" <s:if test="%{0==rank.rankSecUse}">checked</s:if> value="0"  onclick="yanhzheng3(this,'partRankSecUse')" /></td>
          <td align="center"><input type='radio' name="rankSecUse" <s:if test="%{3==rank.rankSecUse}">checked</s:if> value="3"  onclick="yanhzheng3(this,'partRankSecUse')" />
              <input type="text" name="partRankSecUse" value="<s:property value="rank.partRankSecUse"/>" id="partRankSecUse"  onkeyup="this.value=this.value.replace(/[^\d]/g,'')"  maxlength="2"  class="txt" style=" width:50px;text-align:right"   />
            (%)</td>
        </tr>
        <tr>
            <td align="center">2</td>
          <td style="text-align: center;">网络产品:</td>
          <td align="center"><input type="text" maxlength='6' name="rankNetCount" value="<s:property value="rank.rankNetCount"/>" id="netCount" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" class="txt"  style="width:50px;text-align:right"/></td>
          <td align="center"><input type='radio' name='rankNetUse' <s:if test="%{100==rank.rankNetUse}">checked</s:if> value="100" onclick="yanhzheng3(this,'partRankNetUse')"   /></td>
          <td align="center"><input type='radio' name='rankNetUse' <s:if test="%{0==rank.rankNetUse}">checked</s:if> value="0" onclick="yanhzheng3(this,'partRankNetUse')"   /></td>
          <td align="center"><input type='radio' name='rankNetUse' <s:if test="%{3==rank.rankNetUse}">checked</s:if> value="3"  onclick="yanhzheng3(this,'partRankNetUse')"   />
              <input type="text" name="partRankNetUse" value="<s:property value="rank.partRankNetUse"/>"  id="partRankNetUse"  onkeyup="this.value=this.value.replace(/[^\d]/g,'')"    class="txt" style=" width:50px;text-align:right" maxlength="2"  />
            (%)</td>
        </tr>
        <tr>
            <td align="center">3</td>
          <td style="text-align: center;">操作系统:</td>
          <td align="center"><input type="text" name="rankSysCount" maxlength='6' value="<s:property value="rank.rankSysCount"/>" class="txt" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"  style="width:50px;text-align:right"/></td>
          <td align="center"><input type='radio' name='rankSysUse' value="100" <s:if test="%{100==rank.rankSysUse}">checked</s:if> onclick="yanhzheng3(this,'partRankSysUse')"  /></td>
          <td align="center"><input type='radio' name='rankSysUse' value="0"  <s:if test="%{0==rank.rankSysUse}">checked</s:if> onclick="yanhzheng3(this,'partRankSysUse')"    /></td>
          <td align="center"><input type='radio' name='rankSysUse' value="3"  <s:if test="%{3==rank.rankSysUse}">checked</s:if> onclick="yanhzheng3(this,'partRankSysUse')"   />
              <input type="text" name="partRankSysUse" value="<s:property value="rank.partRankSysUse"/>" id="partRankSysUse" maxlength="2" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"    class="txt" style=" width:50px;text-align:right"   />
            (%)</td>
        </tr>
        <tr>
           <td align="center">4</td>
          <td style="text-align: center;">数据库:</td>
          <td align="center"><input type="text" name="rankSqlCount" maxlength='6' value="<s:property value="rank.rankSqlCount"/>" id="sqlCount" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"    class="txt"  style="width:50px;text-align:right"/></td>
          <td align="center"><input type='radio' name='rankSqlUse' value="100" <s:if test="%{100==rank.rankSqlUse}">checked</s:if> onclick="yanhzheng3(this,'partRankSqlUse')"    /></td>
          <td align="center"><input type='radio' name='rankSqlUse' value="0" <s:if test="%{0==rank.rankSqlUse}">checked</s:if> onclick="yanhzheng3(this,'partRankSqlUse')"   /></td>
          <td align="center"><input type='radio' name='rankSqlUse' value="3"  <s:if test="%{3==rank.rankSqlUse}">checked</s:if> onclick="yanhzheng3(this,'partRankSqlUse')"  />
              <input type="text"  name="partRankSqlUse" value="<s:property value="rank.partRankSqlUse"/>" id="partRankSqlUse" maxlength="2" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"  class="txt" style=" width:50px;text-align:right"   />
            (%)</td>
        </tr>
        <tr>
           <td align="center">5</td>
          <td style="text-align: center;">服务器:</td>
          <td align="center"><input type="text"  name="rankSerCount" maxlength='6' value="<s:property value="rank.rankSerCount"/>" id="serCount"   onkeyup="this.value=this.value.replace(/[^\d]/g,'')"  class="txt"  style="width:50px;text-align:right"/></td>
          <td align="center"><input type='radio' name='rankSerUse' value="100" <s:if test="%{100==rank.rankSerUse}">checked</s:if> onclick="yanhzheng3(this,'serPer')"   /></td>
          <td align="center"><input type='radio' name='rankSerUse' value="0" <s:if test="%{0==rank.rankSerUse}">checked</s:if>  onclick="yanhzheng3(this,'serPer')"  /></td>
          <td align="center"><input type='radio' name='rankSerUse' value="3"   <s:if test="%{3==rank.rankSerUse}">checked</s:if> onclick="yanhzheng3(this,'serPer')" />
              <input type="text" name="partRankSerUse" value="<s:property value="rank.partRankSerUse"/>" id="serPer"  onkeyup="this.value=this.value.replace(/[^\d]/g,'')" maxlength="2"  class="txt" style=" width:50px;text-align:right"   />
            (%)</td>
        </tr>
        <tr>
           <td align="center">6</td>
          <td style="text-align: center;"> 
		  其它:
            <input type='text'  name='rankOthProd' id='otherSer' value="<s:property value="rank.rankOthProd"/>"  class='showBorder' width="50px" maxlength='50' onblur="yanzheng1(this)"/> </td>
          <td align="center"><input type="text" value="<s:property value="rank.rankOthProdCount"/>" maxlength='6' name="rankOthProdCount" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"   class="txt"  style="width:50px;text-align:right"/></td>
          <td align="center"><input type='radio' name='rankOthProdUse' value="100" <s:if test="%{100==rank.rankOthProdUse}">checked</s:if> onclick="yanhzheng3(this,'partRankOthProdUse')"     /></td>
          <td align="center"><input type='radio' name='rankOthProdUse' value="0" <s:if test="%{0==rank.rankOthProdUse}">checked</s:if>  onclick="yanhzheng3(this,'partRankOthProdUse')"  /></td>
          <td align="center"><input type='radio' name='rankOthProdUse' value="3"  <s:if test="%{3==rank.rankOthProdUse}">checked</s:if> onclick="yanhzheng3(this,'partRankOthProdUse')"  />
              <input type="text" name="partRankOthProdUse" value="<s:property value="rank.partRankOthProdUse"/>" id="partRankOthProdUse"  onkeyup="this.value=this.value.replace(/[^\d]/g,'')" maxlength="2"  class="txt" style="width:50px;text-align:right"   />
            (%)</td>
        </tr>
  </table>
</div>
 <div >
                   <table width="100%" border="0" cellspacing="0" cellpadding="0">
                       <tr>

					      <td  align="right">
							 <input type="submit" value="下一步"  class="btnyh"   />
                           </td>
						    
                      </tr>
                   </table>
	         </div>

</form>
</body>
</html>
