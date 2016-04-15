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
  <form id="frm" name="frm"  action="${ctx}/rank/rankAction_rankTwo.action" method="post" style="width:99.9%">
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
  
<div  class="gjcp">
<table width="100%"  border="1" bordercolor="#5AA4D1" cellpadding="0" cellspacing="0" >
  <tr>
    <td  class='r2titler' colspan="6">07关键产品使用情况</td>
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
          <td align="center"><input type="text" name="rankSecCount" id="secCount" maxlength="6" class="txt" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"    style="width:50px; text-align:right"/></td>
          <td align="center"><input type='radio' name="rankSecUse" value="100" onclick="yanhzheng3(this,'partRankSecUse')" checked="checked"  /></td>
          <td align="center"><input type='radio' name="rankSecUse" value="0"  onclick="yanhzheng3(this,'partRankSecUse')" /></td>
          <td align="center"><input type='radio' name="rankSecUse" value="3"  onclick="yanhzheng3(this,'partRankSecUse')" />
              <input type="text" name="partRankSecUse" id="partRankSecUse" maxlength="2"  onkeyup="this.value=this.value.replace(/[^\d]/g,'')" disabled="disabled"   class="txt" style=" width:50px;text-align:right"   />
            (%)</td>
        </tr>
        <tr>
           <td align="center">2</td>
          <td style="text-align: center;">网络产品:</td>
          <td align="center"><input type="text"  name="rankNetCount"  id="netCount" maxlength="6" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" class="txt"  style="width:50px;text-align:right"/></td>
          <td align="center"><input type='radio' name='rankNetUse' value="100" onclick="yanhzheng3(this,'partRankNetUse')" checked="checked"  /></td>
          <td align="center"><input type='radio' name='rankNetUse' value="0" onclick="yanhzheng3(this,'partRankNetUse')"   /></td>
          <td align="center"><input type='radio' name='rankNetUse' value="3"  onclick="yanhzheng3(this,'partRankNetUse')"   />
              <input type="text" name="partRankNetUse"  id="partRankNetUse" maxlength="2" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" disabled="disabled"   class="txt" style=" width:50px;text-align:right"   />
            (%)</td>
        </tr>
        <tr>
           <td align="center">3</td>
          <td style="text-align: center;">操作系统:</td>
          <td align="center"><input type="text" name="rankSysCount"  class="txt" maxlength="6" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"  style="width:50px;text-align:right"/></td>
          <td align="center"><input type='radio' name='rankSysUse' value="100" onclick="yanhzheng3(this,'partRankSysUse')" checked="checked" /></td>
          <td align="center"><input type='radio' name='rankSysUse' value="0"  onclick="yanhzheng3(this,'partRankSysUse')"    /></td>
          <td align="center"><input type='radio' name='rankSysUse' value="3"  onclick="yanhzheng3(this,'partRankSysUse')"   />
              <input type="text" name="partRankSysUse" id="partRankSysUse" maxlength="2"  onkeyup="this.value=this.value.replace(/[^\d]/g,'')" disabled="disabled"   class="txt" style=" width:50px;text-align:right"   />
            (%)</td>
        </tr>
        <tr>
          <td align="center">4</td>
          <td style="text-align: center;">数据库:</td>
          <td align="center"><input type="text" name="rankSqlCount" id="sqlCount" maxlength="6" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"    class="txt"  style="width:50px;text-align:right"/></td>
          <td align="center"><input type='radio' name='rankSqlUse' value="100" onclick="yanhzheng3(this,'partRankSqlUse')" checked="checked"   /></td>
          <td align="center"><input type='radio' name='rankSqlUse' value="0" onclick="yanhzheng3(this,'partRankSqlUse')"   /></td>
          <td align="center"><input type='radio' name='rankSqlUse' value="3"  onclick="yanhzheng3(this,'partRankSqlUse')"  />
              <input type="text"  name="partRankSqlUse" id="partRankSqlUse" maxlength="2" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" disabled="disabled" class="txt" style=" width:50px;text-align:right"   />
            (%)</td>
        </tr>
        <tr>
          <td align="center">5</td>
          <td style="text-align: center;">服务器:</td>
          <td align="center"><input type="text"  name="rankSerCount" id="serCount" maxlength="6"  onkeyup="this.value=this.value.replace(/[^\d]/g,'')"  class="txt"  style="width:50px;text-align:right"/></td>
          <td align="center"><input type='radio' name='rankSerUse' value="100" onclick="yanhzheng3(this,'serPer')" checked="checked"  /></td>
          <td align="center"><input type='radio' name='rankSerUse' value="0"  onclick="yanhzheng3(this,'serPer')"  /></td>
          <td align="center"><input type='radio' name='rankSerUse' value="3"   onclick="yanhzheng3(this,'serPer')" />
              <input type="text" name="partRankSerUse" id="serPer" maxlength="2" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" disabled="disabled"  class="txt" style=" width:50px;text-align:right"   />
            (%)</td>
        </tr>
        <tr>
          <td align="center">6</td>
          <td style="text-align: center;"> 
		  其它:
            <input type='text'  name='rankOthProd' id='otherSer'   class='showBorder' width="50px" maxlength="50" onblur="yanzheng1(this)"/> </td>
          <td align="center"><input type="text" name="rankOthProdCount" maxlength="6" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"   class="txt"  style="width:50px;text-align:right"/></td>
          <td align="center"><input type='radio' name='rankOthProdUse' value="100" onclick="yanhzheng3(this,'partRankOthProdUse')" checked="checked"    /></td>
          <td align="center"><input type='radio' name='rankOthProdUse' value="0"  onclick="yanhzheng3(this,'partRankOthProdUse')"  /></td>
          <td align="center"><input type='radio' name='rankOthProdUse' value="3"  onclick="yanhzheng3(this,'partRankOthProdUse')"  />
              <input type="text" name="partRankOthProdUse" id="partRankOthProdUse" maxlength="2" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" disabled="disabled"  class="txt" style="width:50px;text-align:right"   />
            (%)</td>
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
