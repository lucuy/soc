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
<link href="${ctx}/styles/dbStyles/style.css" rel="stylesheet" type="text/css">
 <link href="${ctx}/styles/dbStyles/pageCss.css" rel="stylesheet" type="text/css">
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
  <form id="frm" name="frm"  action="${ctx}/rank/rankAction_rankThree.action" method="post" style="width:99.9%">
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
<div class="xtfw" >
   <table width="100%" border="1" bordercolor="#5AA4D1" cellpadding="0" cellspacing="0" >
        
		<tr><td colspan="6" class='r2titler'>08系统服务情况</td> </tr>
        <tr> 
		<td width="100" rowspan="2" class="tdLabel" style="text-align: center;">标号</td>         
          <td rowspan="2" colspan="2" class="tdLabel" style="text-align: center;">服务类型：</td>
          <td colspan="3" class="tdLabel" style="text-align: center;">服务责任方类型：</td>
        </tr>
        <tr>
		<td width="200" class="tdLabel" style="text-align: center;">本行业（单位）</td>
          <td width="200" class="tdLabel" style="text-align: center;">国内其他服务商</td>
          <td width="200" class="tdLabel" style="text-align: center;">国外服务商</td>
        </tr>
        <tr>
          <td  align="center" width="100">1</td> 
          <td width="200" style="text-align: center;">等级测评:</td>
          <td width="200" align="center">
          <input type="radio" name="rankIfGradeEval" value="1" onclick="yanzheng9('rankIfGradeEval','rankSerGradeType')"    />
          有
            <input type="radio" name="rankIfGradeEval" value="0"  checked="checked" onclick="yanzheng9('rankIfGradeEval','rankSerGradeType')"/>
          无</td>
          <td align="center"><input type='radio' name='rankSerGradeType'  value="1" disabled="disabled"   /></td>
          <td align="center"><input type='radio' name='rankSerGradeType'  value="2" disabled="disabled" /></td>
          <td align="center"><input type='radio' name='rankSerGradeType'  value="3"  disabled="disabled"/></td>
        </tr>
        <tr>
             <td  align="center" width="100">2</td> 
          <td style="text-align: center;">风险评估:</td>
          <td align="center"><input type="radio" name="rankIfRiskEval" value="1" onclick="yanzheng9('rankIfRiskEval','rankSerRiskType')"  />
            有
            <input type="radio" name="rankIfRiskEval" value="0"   checked="checked" onclick="yanzheng9('rankIfRiskEval','rankSerRiskType')" />
            无</td>
          <td align="center"><input type='radio' name='rankSerRiskType'  value="1" disabled="disabled" /></td>
          <td align="center"><input type='radio' name='rankSerRiskType'  value="2"  disabled="disabled" /></td>
          <td align="center"><input type='radio' name='rankSerRiskType'  value="3"  disabled="disabled" /></td>
        </tr>
        <tr>
           <td  align="center" width="100">3</td> 
          <td style="text-align: center;">灾难恢复:</td>
          <td align="center"><input type="radio" name="rankIfSuffReco" value="1" onclick="yanzheng9('rankIfSuffReco','rankIfSuffRecoType')"  />
            有
            <input type="radio" name="rankIfSuffReco" value="0"  checked="checked" onclick="yanzheng9('rankIfSuffReco','rankIfSuffRecoType')" />
            无</td>
          <td align="center"><input type='radio' name='rankIfSuffRecoType'  value="1" disabled="disabled" /></td>
          <td align="center"><input type='radio' name='rankIfSuffRecoType'  value="2" disabled="disabled" /></td>
          <td align="center"><input type='radio' name='rankIfSuffRecoType'  value="3" disabled="disabled" /></td>
        </tr>
        <tr>
            <td  align="center" width="100">4</td> 
          <td style="text-align: center;">应急响应:</td>
          <td align="center"><input type="radio" name="rankIfResponse" value="1" onclick="yanzheng9('rankIfResponse','rankResponseType')" />
            有
            <input type="radio" name="rankIfResponse" value="0"  checked="checked" onclick="yanzheng9('rankIfResponse','rankResponseType')" />
            无</td>
          <td align="center"><input type='radio' name='rankResponseType'  value="1" disabled="disabled" /></td>
          <td align="center"><input type='radio' name='rankResponseType'  value="2" disabled="disabled" /></td>
          <td align="center"><input type='radio' name='rankResponseType'  value="3" disabled="disabled" /></td>
        </tr>
        <tr>
            <td  align="center" width="100">5</td> 
          <td style="text-align: center;">系统集成:</td>
          <td align="center"><input type="radio" name="rankIfSysInte" value="1" onclick="yanzheng9('rankIfSysInte','rankSysInteType')" />
            有
            <input type="radio" name="rankIfSysInte" value="0"  checked="checked" onclick="yanzheng9('rankIfSysInte','rankSysInteType')" />
            无</td>
          <td align="center"><input type='radio' name='rankSysInteType'  value="1" disabled="disabled" /></td>
          <td align="center"><input type='radio' name='rankSysInteType'  value="2" disabled="disabled" /></td>
          <td align="center"><input type='radio' name='rankSysInteType'  value="3" disabled="disabled" /></td>
        </tr>
        <tr>
             <td  align="center" width="100">6</td> 
          <td style="text-align: center;">安全咨询:</td>
          <td align="center"><input type="radio" name="rankIfSecCon" value="1" onclick="yanzheng9('rankIfSecCon','rankSecConypeType')" />
            有
            <input type="radio" name="rankIfSecCon" value="0"  checked="checked" onclick="yanzheng9('rankIfSecCon','rankSecConypeType')" />
            无</td>
          <td align="center"><input type='radio' name='rankSecConypeType'  value="1" disabled="disabled" /></td>
          <td align="center"><input type='radio' name='rankSecConypeType'  value="2" disabled="disabled" /></td>
          <td align="center"><input type='radio' name='rankSecConypeType'  value="3" disabled="disabled" /></td>
        </tr>
        <tr>
            <td  align="center" width="100">7</td> 
          <td style="text-align: center;">安全培训:</td>
          <td align="center"><input type="radio" name="rankIfSecTrain" value="1" onclick="yanzheng9('rankIfSecTrain','rankSecTrainType')" />
            有
            <input type="radio" name="rankIfSecTrain" value="0" checked="checked" onclick="yanzheng9('rankIfSecTrain','rankSecTrainType')" />
            无</td>
          <td align="center"><input type='radio' name='rankSecTrainType'  value="1" disabled="disabled" /></td>
          <td align="center"><input type='radio' name='rankSecTrainType'  value="2" disabled="disabled" /></td>
          <td align="center"><input type='radio' name='rankSecTrainType'  value="3" disabled="disabled" /></td>
        </tr>
        <tr>
            <td  align="center" width="100">8</td> 
          <td style="text-align: center;">其它:
            <input type='text' name='rankOthSerName'  class='showBorder' width="50px" maxlength='50' onblur="yanzheng1(this)"/></td>
          <td align="center"><input type="radio" name="rankIfOthSer" value="1" onclick="yanzheng9('rankIfOthSer','rankOthUseType')" />
            有
            <input type="radio" name="rankIfOthSer" value="0" checked="checked" onclick="yanzheng9('rankIfOthSer','rankOthUseType')" />
            无</td>
          <td align="center"><input type='radio' name='rankOthUseType'  value="1" disabled="disabled" /></td>
          <td align="center"><input type='radio' name='rankOthUseType'  value="2" disabled="disabled" /></td>
          <td align="center"><input type='radio' name='rankOthUseType'  value="3" disabled="disabled" /></td>
        </tr>
  </table>
</div>
          <div >
                   <table width="100%" border="0" cellspacing="0" cellpadding="0">
                       <tr >

					      <td  align="right">
							 <input type="submit" value="下一步"   class="btnyh"   />
                           </td>
						    
                      </tr>
                   </table>
	         </div>

</form>
</body>
</html>
