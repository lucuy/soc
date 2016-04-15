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
 <script type="text/javascript" src="${ctx}/scripts/check.js"></script>
 <script type='text/javascript'
	src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
 <script type="text/javascript" >
 
 function first(){
  document.getElementById("docDiv").style.display="none";
 }
 
 function ifShowDocDiv(docu){
      if(docu.value=="1"){
         document.getElementById("docDiv").style.display="block";
      }else{
        document.getElementById("docDiv").style.display="none";
      }
 }

 function hasDep(){
	 var isDeps=document.getElementsByName("rankIsDep");
	 var depJudges=document.getElementsByName("rankDepJudge");
	 var depName=document.getElementById("rankDepName");
	 if(isDeps[0].checked){
		 depName.disabled="";
		 depJudges[0].disabled="";
		 depJudges[1].disabled="";
	 }else{
		 depName.disabled="disabled";
		 depName.value="";
		 depJudges[0].disabled="disabled";
		 depJudges[0].checked="";
		 depJudges[1].disabled="disabled";
		 depJudges[1].checked="";
	 }
 }
 function goahead(){
     var btnSub=document.getElementById("btnSub");
     btnSub.disabled="disabled";
	 document.scoreresultForm.target="mainFrame";
     //定级时间必须填写
	 var rankTime=document.getElementById("rankTime");
	 if($.trim(rankTime.value)==""){
		alert("定级时间必须填写！");
		btnSub.disabled="";
       return;
	 }
	 
     //填表人必须填写
	 var rankInformant=document.getElementById("rankInformant");
	 if($.trim(rankInformant.value) ==''){
	   alert('填表人必须填写！');
	   btnSub.disabled="";
       return;
	 }
	 
     //填表时间必须填写
	 var rankDate=document.getElementById("rankDate");
	 if($.trim(rankDate.value) ==''){
	   alert('填表时间必须填写！');
	   btnSub.disabled="";
       return;
	 }
	 

	 var isDeps=document.getElementsByName("rankIsDep");
	 var depJudges=document.getElementsByName("rankDepJudge");
	 var depName=document.getElementById("rankDepName");

	 if(isDeps[0].checked){

		 if($.trim(depName.value)=="" ){
            alert('主管部门名称必须填写！');
            btnSub.disabled="";
            return;
		 }
		 if(!(depJudges[0].checked) && !(depJudges[1].checked)){
			 alert('主管部门审批定级情况必须选择！');
			 btnSub.disabled="";
	            return;
		 }

	 }
	document.scoreresultForm.action="";
			var formName = document.getElementById("scoreresultForm");
			formName.action = "${ctx}/rank/rankAction_rankFive.action";
			formName.submit();
 }
 function upfile(){
 			var rankAccess=document.getElementById("frankAccess");
			if($.trim(rankAccess.value)==""){
      				alert("请选择上传文件！");
      					 return ;
    		}
			document.scoreresultForm.target="hiddenframe";
			document.scoreresultForm.action="${ctx}/rank/uprankfile.action";
			document.scoreresultForm.submit();
			
		}
 function callback(msg)    
	{  
    	document.getElementById( "msg" ).innerHTML =  "<font color='red'>" +msg+ "</font>" ;    
	} 
function callback2(msg)    
	{  
    	document.getElementById( "msg" ).innerHTML +=  "<input type='hidden' name='reFrankAccessFileName' value='"+msg+"'>";
	}
	

 </script>
</head >
<body onload="first()">
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
<input type="hidden" name="rankInfoSysIntr" value="<s:property value="rank.rankInfoSysIntr"/>" />


<!-- 第六页面 -->
<input type="hidden" name="rankTopStruct" value="<s:property value="rank.rankTopStruct"/>" />
<input type="hidden" name="rankTopRelAcc" value="<s:property value="rank.rankTopRelAcc"/>" />
<!-- 重命名列 -->
<input type="hidden" name="reRankTopRelAcc" value="<s:property value="rank.reRankTopRelAcc"/>" />
<input type="hidden" name="rankSysManage" value="<s:property value="rank.rankSysManage"/>" />
<input type="hidden" name="rankSysManRel" value="<s:property value="rank.rankSysManRel"/>" />
<!-- 重命名列 -->
<input type="hidden" name="reRankSysManRel" value="<s:property value="rank.reRankSysManRel"/>" />
<input type="hidden" name="rankSysPlan" value="<s:property value="rank.rankSysPlan"/>" />
<input type="hidden" name="rankSysPlanRel" value="<s:property value="rank.rankSysPlanRel"/>" />
<!-- 重命名列 -->
<input type="hidden" name="reRankSysPlanRel" value="<s:property value="rank.reRankSysPlanRel"/>" />
<input type="hidden" name="rankSysLicense" value="<s:property value="rank.rankSysLicense"/>" />
<input type="hidden" name="rankSysLiceRel" value="<s:property value="rank.rankSysLiceRel"/>" />
<!-- 重命名列 -->
<input type="hidden" name="reRankSysLiceRel" value="<s:property value="rank.reRankSysLiceRel"/>" />
<input type="hidden" name="rankSysReport" value="<s:property value="rank.rankSysReport"/>" />
<input type="hidden" name="rankSysReportRel" value="<s:property value="rank.rankSysReportRel"/>" />
<!-- 重命名列 -->
<input type="hidden" name="reRrankSysReportRel" value="<s:property value="rank.reRrankSysReportRel"/>" />
<input type="hidden" name="rankPeerRev" value="<s:property value="rank.rankPeerRev"/>" />
<input type="hidden" name="rankPeerRevRel" value="<s:property value="rank.rankPeerRevRel"/>" />
<!-- 重命名列 -->
<input type="hidden" name="reRankPeerRevRel" value="<s:property value="rank.reRankPeerRevRel"/>" />
<input type="hidden" name="rankSuperOpinRel" value="<s:property value="rank.rankSuperOpinRel"/>" />
<!-- 重命名列 -->
<input type="hidden" name="reRankSuperOpinRel" value="<s:property value="rank.reRankSuperOpinRel"/>" />
<input type="hidden" name="rankSuperOpin" value="<s:property value="rank.rankSuperOpin"/>" />
<input type="hidden" name="rankCount" value="<s:property value="rank.rankCount"/>" />
 
 
    <table  width=100% border="1" bordercolor="#5AA4D1" cellpadding="0" cellspacing="0" >
	    <tr >
	      <td colspan="4"  class='r2titler'>信息系统定级情况</td>
      </tr>

	    <tr>	
	        <td width="15%" align="right">01信息系统安全保护等级</td>
		    <td >
		     <input type="text" name="rankGrade" id="rankGrade" value="<s:property value="rank.rankGrade"/>" readonly="readonly" style="width: 200px"/>
		    </td>
		    <td align="right"><font color="#ff0000">*</font>02定级时间</td>
		    <td  ><input type="text" name="rankTime" id="rankTime" style="width:200px" readonly="readonly"  onclick="WdatePicker();" ></td>
	    </tr>
	    <tr>
		      <td align="right"> 
			    03专家评审情况 
			    </td>
		    <td  >
			    <div id="div2" class="article"></div>
			    <input type="radio" name="rankJudge" value="1">已评审
			    <input type="radio" name="rankJudge" value="0" checked="checked">未评审			  </td>
		    <td align="right"> 
			    04是否有主管部门			  </td>
		    <td >
			    <input type="radio" name="rankIsDep" onclick="hasDep()"   value="1" checked="checked" > 有
			    <input type="radio" name="rankIsDep" value="0" onclick="hasDep()"    >无（如选择有请填下两项）			  </td>
	    </tr>
	    <tr>
		    <td align="right"><font color="#ff0000">*</font>05主管部门名称</td>
		    <td >				
			    <input type="text" name="rankDepName" id="rankDepName" maxlength="50" style="width: 200px" class="txt 100" onblur="yanzheng1(this)">			  </td>
		    <td align="right"><font color="#ff0000">*</font>06主管部门审批定级情况</td>
		    <td >				
				    <input type="radio" name="rankDepJudge" value="1"   >已审批
				    <input type="radio" name="rankDepJudge" value="0" >未审批			  </td>
	    </tr>
	    <tr>
		    <td align="right">07系统定级报告</td>
		    <td>
			    <input type="radio" name="rankDoc" value="1" onclick="ifShowDocDiv(this)">有
			    <input type="radio" name="rankDoc" value="0" checked="checked" onclick="ifShowDocDiv(this)">无			  
			</td>
		    <td align="right" >定级报告</td>	
		    <td >
		     <div id="docDiv" >
		       <input type="file"	name="frankAccess" maxlength="200" id="frankAccess">
			   <input type="button" name="rankAccess" value="附件上载"  id="rankAccess" class="btnstyle" onclick="upfile();">			  
			   <br>
			   <span id="msg"></span>
			 </div>&nbsp;	
			 </td>	
	    </tr>
	    <tr>
		    <td align="right"><font color="#ff0000">*</font>填表人</td>
		    <td ><input type="text" name="rankInformant" id="rankInformant" maxlength="50" style="width: 200px" onblur="yanzheng1(this)"></td>
		    <td align="right"><font color="#ff0000">*</font>填表日期</td>
		    <td ><input type="text" name="rankDate"  readonly="readonly"  onclick="WdatePicker();" id="rankDate" style="width: 200px"  class="txtRequired 100"></td>
	    </tr>
    </table>
    	 <div id="divAction" style="height:30;text-align: right">
    	 <s:if test='rank.rankGrade=="第一级"'>
				<input type="button" name="btnReturn" value="保存" onclick="goahead()"   class="btnyh" id="btnSub"> 
		 </s:if>
		 <s:else>
		    <s:if test='rank.rankGrade=="第二级"'>
				<input type="button" name="btnReturn" value="保存" onclick="goahead()"   class="btnyh" id="btnSub"> 
		    </s:if>
		    <s:else>
		        <input type="button" name="btnReturn" value="下一步" onclick="goahead()"   class="btnyh" id="btnSub">
		    </s:else>
		 </s:else>
	</div>
 	  </form>
</body>

