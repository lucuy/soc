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
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
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

	  
	  
	  function yanzheng9(file1,file2){
	 	 var var1=document.getElementsByName(file1);
	 	 var var2=document.getElementsByName(file2);
	 	 if(var1[0].checked){
	 	  for(var i=0;i<var2.length;i++){
	 	   var2[i].disabled=false;
	 	  }
	 	 
	 	 }else{
	 	   for(var i=0;i<var2.length;i++){
	 	     var2[i].disabled=true;
	 	   }
	 	 
	 	 }
	 	 
	  }
	  

	</script>
  </head>
  
  <body  >
  <form id="frm" name="frm"  action="${ctx}/rank/rankAction_rankThreeUpdate.action"  method="post" style="width:99.9%">
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
<div class="xtfw" >
 <table  width="100%" border="1" bordercolor="#5AA4D1" cellpadding="0" cellspacing="0"  >
		<tr><td colspan="6" class='r2titler'>08 系统采用服务情况</td> </tr>
        <tr>          
          <td rowspan="2" style="text-align: center;">序号</td>
          <td colspan="2" rowspan="2" style="text-align: center;"> 服务类型：</td>
          <td colspan="5" style="text-align: center;">服务责任方类型：</td>
        </tr>
        <tr>
        
          <td style="text-align: center;"><span class="tdLabel" style="text-align: center;">本行业（单位）</span></td>
          <td width="18%" class="tdLabel" style="text-align: center;">国内其他服务商</td>
          <td width="18%" class="tdLabel" style="text-align: center;">国外服务商</td>
        </tr>
        <tr>
          <td align="center">1</td>
          <td width="18%" style="text-align: center;">等级测评:</td>
          <td width="30%" align="center"><input type="radio" name="rankIfGradeEval" <s:if test="%{1==rank.rankIfGradeEval}">checked</s:if> value="1" onclick="yanzheng9('rankIfGradeEval','rankSerGradeType')"    />
            有
            <input type="radio" name="rankIfGradeEval" value="0" <s:if test="%{0==rank.rankIfGradeEval}">checked</s:if>   onclick="yanzheng9('rankIfGradeEval','rankSerGradeType')"/>
          无</td>
          <td align="center"><input type='radio' <s:if test="%{1==rank.rankSerGradeType}">checked</s:if> name='rankSerGradeType'  value="1"    /></td>
          <td align="center"><input type='radio' <s:if test="%{2==rank.rankSerGradeType}">checked</s:if> name='rankSerGradeType'  value="2"  /></td>
          <td align="center"><input type='radio' <s:if test="%{3==rank.rankSerGradeType}">checked</s:if> name='rankSerGradeType'  value="3"  /></td>
        </tr>
        <tr>
           <td align="center">2</td>
          <td style="text-align: center;">风险评估:</td>
          <td align="center"><input type="radio" name="rankIfRiskEval" <s:if test="%{1==rank.rankIfRiskEval}">checked</s:if> value="1" onclick="yanzheng9('rankIfRiskEval','rankSerRiskType')"  />
            有
            <input type="radio" name="rankIfRiskEval" value="0"  <s:if test="%{0==rank.rankIfRiskEval}">checked</s:if>  onclick="yanzheng9('rankIfRiskEval','rankSerRiskType')" />
            无</td>
          <td align="center"><input type='radio' name='rankSerRiskType' <s:if test="%{1==rank.rankSerRiskType}">checked</s:if> value="1"  /></td>
          <td align="center"><input type='radio' name='rankSerRiskType' <s:if test="%{2==rank.rankSerRiskType}">checked</s:if> value="2"   /></td>
          <td align="center"><input type='radio' name='rankSerRiskType'  <s:if test="%{3==rank.rankSerRiskType}">checked</s:if> value="3"   /></td>
        </tr>
        <tr>
         <td align="center">3</td>
          <td style="text-align: center;">灾难恢复:</td>
          <td align="center"><input type="radio" name="rankIfSuffReco" value="1"  <s:if test="%{1==rank.rankIfSuffReco}">checked</s:if> onclick="yanzheng9('rankIfSuffReco','rankIfSuffRecoType')"  />
            有
            <input type="radio" name="rankIfSuffReco" value="0" <s:if test="%{0==rank.rankIfSuffReco}">checked</s:if>  onclick="yanzheng9('rankIfSuffReco','rankIfSuffRecoType')" />
            无</td>
          <td align="center"><input type='radio' name='rankIfSuffRecoType' <s:if test="%{1==rank.rankIfSuffRecoType}">checked</s:if>  value="1"  /></td>
          <td align="center"><input type='radio' name='rankIfSuffRecoType'  <s:if test="%{2==rank.rankIfSuffRecoType}">checked</s:if> value="2"  /></td>
          <td align="center"><input type='radio' name='rankIfSuffRecoType' <s:if test="%{3==rank.rankIfSuffRecoType}">checked</s:if>  value="3"  /></td>
        </tr>
        <tr>
          <td align="center">4</td>
          <td style="text-align: center;">应急响应:</td>
          <td align="center"><input type="radio" name="rankIfResponse" value="1" <s:if test="%{1==rank.rankIfResponse}">checked</s:if> onclick="yanzheng9('rankIfResponse','rankResponseType')" />
            有
            <input type="radio" name="rankIfResponse" value="0" <s:if test="%{0==rank.rankIfResponse}">checked</s:if>  onclick="yanzheng9('rankIfResponse','rankResponseType')" />
            无</td>
          <td align="center"><input type='radio' name='rankResponseType' <s:if test="%{1==rank.rankResponseType}">checked</s:if>  value="1"  /></td>
          <td align="center"><input type='radio' name='rankResponseType' <s:if test="%{2==rank.rankResponseType}">checked</s:if> value="2"  /></td>
          <td align="center"><input type='radio' name='rankResponseType' <s:if test="%{3==rank.rankResponseType}">checked</s:if> value="3"  /></td>
        </tr>
        <tr>
          <td align="center">5</td>
          <td style="text-align: center;">系统集成:</td>
          <td align="center"><input type="radio" name="rankIfSysInte" value="1" <s:if test="%{1==rank.rankIfSysInte}">checked</s:if> onclick="yanzheng9('rankIfSysInte','rankSysInteType')" />
            有
            <input type="radio" name="rankIfSysInte" value="0"   onclick="yanzheng9('rankIfSysInte','rankSysInteType')" />
            无</td>
          <td align="center"><input type='radio' name='rankSysInteType' <s:if test="%{1==rank.rankSysInteType}">checked</s:if>  value="1"  /></td>
          <td align="center"><input type='radio' name='rankSysInteType' <s:if test="%{2==rank.rankSysInteType}">checked</s:if> value="2"  /></td>
          <td align="center"><input type='radio' name='rankSysInteType' <s:if test="%{3==rank.rankSysInteType}">checked</s:if> value="3"  /></td>
        </tr>
        <tr>
           <td align="center">6</td>
          <td style="text-align: center;">安全咨询:</td>
          <td align="center"><input type="radio" name="rankIfSecCon" value="1" <s:if test="%{1==rank.rankIfSecCon}">checked</s:if> onclick="yanzheng9('rankIfSecCon','rankSecConypeType')" />
            有
            <input type="radio" name="rankIfSecCon" value="0" <s:if test="%{0==rank.rankIfSecCon}">checked</s:if>   onclick="yanzheng9('rankIfSecCon','rankSecConypeType')" />
            无</td>
          <td align="center"><input type='radio' name='rankSecConypeType' <s:if test="%{1==rank.rankSecConypeType}">checked</s:if> value="1"  /></td>
          <td align="center"><input type='radio' name='rankSecConypeType' <s:if test="%{2==rank.rankSecConypeType}">checked</s:if> value="2"  /></td>
          <td align="center"><input type='radio' name='rankSecConypeType' <s:if test="%{3==rank.rankSecConypeType}">checked</s:if> value="3"  /></td>
        </tr>
        <tr>
          <td align="center">7</td>
          <td style="text-align: center;">安全培训:</td>
          <td align="center"><input type="radio" name="rankIfSecTrain" value="1" <s:if test="%{1==rank.rankIfSecTrain}">checked</s:if> onclick="yanzheng9('rankIfSecTrain','rankSecTrainType')" />
            有
            <input type="radio" name="rankIfSecTrain" value="0" <s:if test="%{0==rank.rankIfSecTrain}">checked</s:if>   onclick="yanzheng9('rankIfSecTrain','rankSecTrainType')" />
            无</td>
          <td align="center"><input type='radio' name='rankSecTrainType' <s:if test="%{1==rank.rankSecTrainType}">checked</s:if>  value="1"  /></td>
          <td align="center"><input type='radio' name='rankSecTrainType' <s:if test="%{2==rank.rankSecTrainType}">checked</s:if> value="2"  /></td>
          <td align="center"><input type='radio' name='rankSecTrainType' <s:if test="%{3==rank.rankSecTrainType}">checked</s:if> value="3"  /></td>
        </tr>
        <tr>
          <td align="center">8</td>
          <td style="text-align: center;">其它:
            <input type='text' name='rankOthSerName' value="<s:property value="rank.rankOthSerName"/>" class='showBorder' width="50px" maxlength='50' onblur="yanzheng1(this)"/></td>
          <td align="center"><input type="radio" name="rankIfOthSer" value="1" <s:if test="%{1==rank.rankIfOthSer}">checked</s:if> onclick="yanzheng9('rankIfOthSer','rankOthUseType')" />
            有
            <input type="radio" name="rankIfOthSer" value="0" <s:if test="%{0==rank.rankIfOthSer}">checked</s:if>  onclick="yanzheng9('rankIfOthSer','rankOthUseType')" />
            无</td>
          <td align="center"><input type='radio' name='rankOthUseType' <s:if test="%{1==rank.rankOthUseType}">checked</s:if>  value="1"  /></td>
          <td align="center"><input type='radio' name='rankOthUseType' <s:if test="%{2==rank.rankOthUseType}">checked</s:if> value="2"  /></td>
          <td align="center"><input type='radio' name='rankOthUseType'  <s:if test="%{3==rank.rankOthUseType}">checked</s:if> value="3"  /></td>
        </tr>
  </table>
</div>
          <div >
                   <table width="100%" border="0" cellspacing="0" cellpadding="0">
                       <tr >

					      <td  align="right">
							 <input type="submit" value="下一步"  class="btnyh"   />
                           </td>
						    
                      </tr>
                   </table>
	         </div>

</form>
</body>
</html>
