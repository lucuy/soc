<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<html>
  <head>
    <SCRIPT src="${ctx}/scripts/Calendar.js" type=text/javascript></SCRIPT>
    <title>定级信息系统情况</title>
<link href="${ctx}/styles/dbStyles/pageCss.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/right.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/template.css" rel="stylesheet" type="text/css">
<SCRIPT src="${ctx}/scripts/jquery-1.7.2.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/jquery-ui.custom.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/util.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/popuplayer.js" type=text/javascript></SCRIPT>
<script  src="${ctx}/scripts/jquery-1.7.2.min.js" type='text/javascript'></script>
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
		    document.frm.target="mainFrame";
			document.frm.action="";
			var formName = document.getElementById("frm");
			formName.action = "${ctx}/rank/rankAction_rankOne.action";
			formName.submit();
		}
		function callback(msg)    
		{    
		
    		document.getElementById( "msg" ).innerHTML =  "<font color='red'>" +msg+ "</font>" ;    
		} 
		 

	</script>
  </head>
  
  <body  >
  <iframe name="hiddenframe" id="hiddenframe" style="display:none"></iframe>
  <form  action="" id="frm" name="frm" method="post" style="width:100%" enctype="multipart/form-data">
    <input type="hidden" name="rankId"  value="<s:property value="rank.rankId"/>" />
   <input type="hidden" name="sysInfoBusDescription" value="<s:property value="rank.sysInfoBusDescription"/>" />
<div>
  <table  width="100%" border="1" bordercolor="#5AA4D1" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="6"  class='r2titler'>信息系统情况</td>
  </tr>
  <tr>
    <td  colspan="2" align="right" width="20%">01系统名称:</td>
    <td  style="text-align: left;" ><input type="text" name="sysInfoName"   readonly="readonly" value="<s:property value="rank.sysInfoName"/>"  style="width:100%"/></td>
    <td  colspan="2" align="right">02系统编号:</td>
    <td  style="text-align: left;"><input type="text" name="sysInfoId"    readonly="readonly"  value="<s:property value="rank.sysInfoId"/>" style="width:100%"/></td>
  </tr>
  <tr>
    <td align="right" rowspan="2">05系统网络平台:</td>
    <td align="right" width="10%">覆盖范围:</td>
    <td colspan="4">
	      <input type="radio" name="rankCoveArea" value="1"  onclick="yanzheng10(this,'otherArea','4')" checked="checked"/>
            1 局域网&nbsp;&nbsp;&nbsp;
            <input type="radio" name="rankCoveArea" value="2"  onclick="yanzheng10(this,'otherArea','4')" />
            2 城域网&nbsp;
            <input type="radio" name="rankCoveArea" value="3"  onclick="yanzheng10(this,'otherArea','4')"/>
           3 广域网&nbsp;
            <input type="radio" name="rankCoveArea" value="4"  onclick="yanzheng10(this,'otherArea','4')"/>
          9 其它
            <input type="text" name="rankOthArea" id="otherArea" maxlength="50" disabled="disabled" class="showBorder"  onblur="yanzheng1(this)" style="width:100px" />	
    </td>
  </tr>
  <tr>
    <td align="right" width="10%">网络性质:</td>
    <td colspan="4">
	 <input type="radio" name="rankNetworkProp" value="1" onclick="yanzheng10(this,'otherProp','3')" checked="checked" />
          1 业务专网&nbsp;
            <input type="radio" name="rankNetworkProp" value="2" onclick="yanzheng10(this,'otherProp','3')" />
            2 互联网&nbsp;
            <input type="radio" name="rankNetworkProp" value="3" onclick="yanzheng10(this,'otherProp','3')" />
           9 其它
            <input type="text" name="rankOthNetworkProp"   id="otherProp" disabled="disabled" class="showBorder" maxlength="50" onblur="yanzheng1(this)" style="width:100px"/>	</td>
  </tr>
  <tr>
    <td align="right" colspan="2">06系统互联情况:</td>
    <td colspan="4">
   			 <input type="radio" name="rankSysConn" value="1" onclick="yanzheng10(this,'otherType','4')" checked="checked"  /> 1 与其他行业系统连接&nbsp;&nbsp;
             <input type="radio" name="rankSysConn" value="2"  onclick="yanzheng10(this,'otherType','4')"/> 2 与本行业其他单位系统连接&nbsp;&nbsp;
             <br>
             <input type="radio" name="rankSysConn" value="3" onclick="yanzheng10(this,'otherType','4')" /> 3 与本单位其他系统连接
             <input type="radio" name="rankSysConn" value="4" onclick="yanzheng10(this,'otherType','4')" /> 9 其它
             <input type="text" name="rankOtherSysConn" id="otherType" onblur="yanzheng1(this)" disabled="disabled" class="showBorder" maxlength="50"  style="width:100px" /></td> 
  </tr>
</table>
</div>   <div >
                   <table width="100%" border="0" cellspacing="0" cellpadding="0">
                       <tr >
					      <td  align="right">
							 <input type="button" value="下一步" onclick="goahead()" class="btnyh"   />
                           </td>
                      </tr>
                   </table>
	         </div>

</form>
</body>
</html>
