<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
<link href="${ctx}/styles/dbStyles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/right.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/template.css" rel="stylesheet" type="text/css">
<SCRIPT src="${ctx}/scripts/jquery-1.7.2.min.js" type=text/javascript></SCRIPT>
<script type="text/javascript" src="${ctx}/scripts/check.js"></script>
		<title>定级</title>
		<style type="text/css">
.detilTr1 {
	color: darkblue;
	height: 30px;
	background-color: #EEEEEE;
}
table{
	border-left:thin;
	border-bottom:thin;
	font-size: 12px;
}


table td {
    text-align:center;
    border-top:thin;
	border-right:thin;
	line-height: 28px;
	background: none repeat scroll 0px 0px rgb(255, 255, 255);
}
</style>
<SCRIPT src="${ctx}/scripts/jquery-1.7.2.min.js" type=text/javascript></SCRIPT>

		<script type="text/javascript">
		function first(){
		
		

		document.getElementById("broadcastCenter").style.display = 'none';//隐藏
	    	//qita.style.display = 'none';
		document.getElementById("tvCenter").style.display = 'none';//隐藏
		document.getElementById("payChannel").style.display = 'none';//隐藏
		document.getElementById("broadcastingCentre").style.display = 'none';//隐藏
		document.getElementById("cableTelevision").style.display = 'none';//隐藏
		document.getElementById("wirelessCoverage").style.display = 'none';//隐藏

		}
		
		function clearColor(){
			for(var i=1;i<=6;i++){
			  for(var j=1;j<=4;j++){
			    var str=1+":"+i+":"+j;
			    document.getElementById(str).className="";
			  }
			}
			
			for(var i=1;i<=7;i++){
			  for(var j=1;j<=4;j++){
			    var str=2+":"+i+":"+j;
			    document.getElementById(str).className="";
			  }
			}
			
			for(var i=1;i<=6;i++){
			  for(var j=1;j<=2;j++){
			    var str=3+":"+i+":"+j;
			    document.getElementById(str).className="";
			  }
			}
			
			for(var i=1;i<=6;i++){
			    var str=4+":"+i+":";
			    document.getElementById(str).className="";
			}
			
			for(var i=1;i<=5;i++){
			  for(var j=1;j<=2;j++){
			    var str=5+":"+i+":"+j;
			    document.getElementById(str).className="";
			  }
			}			
			
			for(var i=1;i<=2;i++){
			    var str=6+":"+i+":";
			    document.getElementById(str).className="";
			}
		
		}
		
		function setRankGrade(){
			var rankGrade=document.getElementById("rankGrade");
			rankGrade.value="";
			var rankOrganType=document.getElementById("rankOrganType");//机构类别选择
	    	var rankOrganArea=document.getElementById("rankOrganArea");//机构所在区域
	    	var rankBusinessType=document.getElementById("rankBusinessType");//业务种类选择
	    	var str=rankOrganType.value+":"+rankBusinessType.value+":"+rankOrganArea.value;
	    	clearColor();//清空以前的颜色设置
	    	if(document.getElementById(str)!=null){
	    	   document.getElementById(str).className="detilTr1";
	    	}
	    	if(rankOrganType.value==0){
	    	 
	    	}
	    	
	    	if(rankOrganType.value==1){
	    	
	    	 	if(rankOrganArea.value==1 && rankBusinessType.value==1){
	    	  		rankGrade.value="第四级";
	    	  	}else{
	    	  	
					if(rankBusinessType.value!=0 && rankOrganArea.value!=0){
	    	  	   		rankGrade.value="第二级";
	    	  		}
	    	  		if(rankBusinessType.value==1 && rankOrganArea.value!=0 && rankOrganArea.value!=1){
	    	  			rankGrade.value="第三级";
	    	  		}
	    	  	
	    	  		if(rankBusinessType.value==2 && rankOrganArea.value!=0 && rankOrganArea.value!=4){
	    	  			rankGrade.value="第三级";
	    	  		}
	    	  	}
	    	  
	    	}
	    	
	    	if(rankOrganType.value==2){
	    	
	    		if(rankOrganArea.value==1 && rankBusinessType.value==1){
	    	  		rankGrade.value="第四级";
	    	  	}else{
	    	  	
					if(rankBusinessType.value!=0 && rankOrganArea.value!=0){
	    	  	   		rankGrade.value="第二级";
	    	  		}
	    	  		
	    	  		if(rankBusinessType.value==1 && rankOrganArea.value!=0 && rankOrganArea.value!=1){
	    	  			rankGrade.value="第三级";
	    	  		}
	    	  	
	    	  		if(rankBusinessType.value==2 && rankOrganArea.value!=0 && rankOrganArea.value!=4){
	    	  			rankGrade.value="第三级";
	    	  		}
	    	  		if(rankBusinessType.value==3 && rankOrganArea.value!=0 && rankOrganArea.value!=4 && rankOrganArea.value!=3){
	    	  			rankGrade.value="第三级";
	    	  		}
	    	  	}
	    	}
	    	
	    	if(rankOrganType.value==3){
	    	
	    	  if(rankBusinessType.value==1 && rankOrganArea.value!=0 && rankOrganArea.value!==""){
	    	     rankGrade.value="第三级";
	    	  }else{
	    	     if(rankBusinessType.value==1 && rankOrganArea.value==0){
	    	     	rankGrade.value="第三级";
	    	     }else{
	    	     
	    	       if(rankBusinessType.value!=0 && rankOrganArea.value!=0){
	    	       	rankGrade.value="第二级";
	    	       }
	    	     }
	    	  }
	    	}
	    	
	    	if(rankOrganType.value==4){
	    		if(rankBusinessType.value==1 || rankBusinessType.value==2){
	    	       	rankGrade.value="第三级";
	    	     }else{
	    	     
    	     		if(rankBusinessType.value!=0){
    	     			rankGrade.value="第二级";
	    	    	} 
	    	     }
	    	}
	    	
	    	if(rankOrganType.value==5){
	    	
	    	 if(rankBusinessType.value==1 && rankOrganArea.value!=0){
	    	     rankGrade.value="第三级";
	    	   }else{
	    	   	       if(rankBusinessType.value==2 && rankOrganArea.value==1){
	    	   		      rankGrade.value="第三级";
	    	   	        }else{
	    	   	        if(rankBusinessType.value!=0 && rankOrganArea.value!=0){
	    	   		      rankGrade.value="第二级";
	    	   		      }
	    	   	        }
	    	     }
	    	 
	    	}
			    	
	    	if(rankOrganType.value==6){
	    		if(rankBusinessType.value!=0){
	    	   		  rankGrade.value="第二级";
	    	    }
	    	}
		}
		
		
	function gdMessage() { 
		var rankGrade=document.getElementById("rankGrade");
		rankGrade.value="";
	       first();
	    var rankOrganType=document.getElementById("rankOrganType");//机构类别选择
	    var rankOrganArea=document.getElementById("rankOrganArea");//机构所在区域
	    var rankBusinessType=document.getElementById("rankBusinessType");//业务种类选择
	    rankOrganArea.options.length=0;//把机构所在区域选项清空
	    rankBusinessType.options.length=0;//把业务种类选择选项清空
	    if(rankOrganType.value==1||rankOrganType.value==2){
	       rankOrganArea.options.add(new Option("---请选择---","0"));
	       rankOrganArea.options.add(new Option("1-国家级","1"));
	       rankOrganArea.options.add(new Option("2-省级","2"));
	       rankOrganArea.options.add(new Option("3-省会城市、计划单列市","3"));
	       rankOrganArea.options.add(new Option("4-地市及以下","4"));
	       if(rankOrganType.value==1){
	        document.getElementById("broadcastCenter").style.display = 'block';//显示div
	         rankBusinessType.options.add(new Option("---请选择---","0"));
	         rankBusinessType.options.add(new Option("1-播出系统","1"));
	         rankBusinessType.options.add(new Option("2-新闻制播系统","2"));
	         rankBusinessType.options.add(new Option("3-业务支撑系统","3"));
	         rankBusinessType.options.add(new Option("4-媒资系统","4"));
	         rankBusinessType.options.add(new Option("5-综合制作系统","5"));
	         rankBusinessType.options.add(new Option("6-生产管理系统","6"));
	       }else{
	        document.getElementById("tvCenter").style.display = 'block';//显示div
	         rankBusinessType.options.add(new Option("---请选择---","0"));
	         rankBusinessType.options.add(new Option("1-播出系统","1"));
	         rankBusinessType.options.add(new Option("2-新闻制播系统","2"));
	         rankBusinessType.options.add(new Option("3-播出整备系统","3"));
	         rankBusinessType.options.add(new Option("4-业务支撑系统","4"));
	         rankBusinessType.options.add(new Option("5-媒资系统","5"));
	         rankBusinessType.options.add(new Option("6-综合制作系统","6"));
	         rankBusinessType.options.add(new Option("7-生产管理系统","7"));
	       }
	    }

	    if(rankOrganType.value==3){
	     document.getElementById("payChannel").style.display = 'block';//显示div
	      rankOrganArea.options.add(new Option("---请选择---","0"));
	      rankOrganArea.options.add(new Option("1-全国或跨省","1"));
	      rankOrganArea.options.add(new Option("2-全省或跨地市","2"));
	      
	      rankBusinessType.options.add(new Option("---请选择---","0"));
	      rankBusinessType.options.add(new Option("1-播出系统","1"));
	      rankBusinessType.options.add(new Option("2-播出整备系统","2"));
	      rankBusinessType.options.add(new Option("3-媒资系统","3"));
	      rankBusinessType.options.add(new Option("4-综合制作系统","4"));
	      rankBusinessType.options.add(new Option("5-业务支撑系统","5"));
	      rankBusinessType.options.add(new Option("6-生产管理系统","6"));
	    }
	    if(rankOrganType.value==4){
	    document.getElementById("broadcastingCentre").style.display = 'block';//显示div
	      rankBusinessType.options.add(new Option("---请选择---","0"));
	      rankBusinessType.options.add(new Option("1-广播系统","1"));
	      rankBusinessType.options.add(new Option("2-点播系统","2"));
	      rankBusinessType.options.add(new Option("3-媒资系统","3"));
	      rankBusinessType.options.add(new Option("4-综合制作系统","4"));
	      rankBusinessType.options.add(new Option("5-运营支撑系统","5"));
	      rankBusinessType.options.add(new Option("6-生产管理系统","6"));
	    }
	   
	    if(rankOrganType.value==5){
	    document.getElementById("cableTelevision").style.display = 'block';//显示div
	       rankOrganArea.options.add(new Option("---请选择---","0"));
	       rankOrganArea.options.add(new Option("1-全国或跨省","1"));
	       rankOrganArea.options.add(new Option("2-全省或跨地市","2"));
	       
	       rankBusinessType.options.add(new Option("---请选择---","0"));
	       rankBusinessType.options.add(new Option("1-广播系统","1"));
	       rankBusinessType.options.add(new Option("2-点播系统","2"));
	       rankBusinessType.options.add(new Option("3-媒资系统","3"));
	       rankBusinessType.options.add(new Option("4-运营支撑系统","4"));
	       rankBusinessType.options.add(new Option("5-生产管理系统","5"));
	    }
	    if(rankOrganType.value==6){
	    document.getElementById("wirelessCoverage").style.display = 'block';//显示div
	    rankBusinessType.options.add(new Option("---请选择---","0"));
	    rankBusinessType.options.add(new Option("1-生产调度系统","1"));
	    rankBusinessType.options.add(new Option("2-生产管理系统","2"));
	    }

		
	}
	
	

	function goahead() {

			    var rankGrade = document.getElementById("rankGrade").value;
				var rankInfoSysIntr = document.getElementById("rankInfoSysIntr").value;
				if ($.trim(rankGrade)!= "" && $.trim(rankInfoSysIntr)!= "") {
				        if(rankInfoSysIntr.length>1000){
				          alert("您输入的字符长度超过1000！！！");
				            $("#rankInfoSysIntr").focus();
				          return;
				        }
						document.scoreFrm.submit();

					} else {
					    if( $.trim(rankInfoSysIntr)== ""){
					    	alert("信息填写完整再下一步操作！！！");
						 	$("#rankInfoSysIntr").focus();
							return;
					    }else{
					        alert("信息填写完整再下一步操作！！！");
					        $("#rankGrade").focus();
					        
							return;
					    }
						
				}


	}

</script>
	</head>

	<body onload="first()">
		<form id="scoreFrm" name="scoreFrm" method="post"
			action="${ctx}/rank/rankAction_rankFourUpdate.action"style="width:99%">
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
				 <table width="100%" border="1" bordercolor="#5AA4D1" cellpadding="0" cellspacing="0">
						<tr>
							<td width="30%"   class="tdhead">
								信息系统安全保护等级							
							</td>
							<td width="70%" colspan="3">
								<table border="0" width="100%" >
									<tr>
										<td style="text-align:right;width:50%" >
											<font color="#ff0000">*</font>机构类别选择：
										</td>
										<td  style="text-align:left;width:50%">
											<select name="rankOrganType" id="rankOrganType"
												onchange="gdMessage()"  >
												<option value="0">
													&nbsp;&nbsp;&nbsp;---请选择---&nbsp;&nbsp;&nbsp;
												</option>
												<option value="1" >
													&nbsp;表5  各级广播中心播出相关信息系统安全保护等级
												</option>
												<option value="2"  >
													&nbsp;表6  各级电视中心播出相关信息系统安全保护等级
												</option>
												<option value="3"  >
													&nbsp;表7  付费频道播出相关信息系统安全保护等级
												</option>
												<option value="4" >
													&nbsp;表8  集成播控平台相关信息系统安全保护等级
												</option>
												<option value="5"  >
													&nbsp;表9  有线电视网络相关信息系统安全保护等级
												</option>
												<option value="6"  >
													&nbsp;表10  无线覆盖等其它类相关信息系统安全保护等级
												</option>
											</select>
										</td>
									</tr>
									<tr>
										<td style="text-align:right;width:50%">
											<font color="#ff0000">*</font>机构所在区域：
										</td>
										<td  style="text-align:left;width:50%">
											<select name="rankOrganArea" id="rankOrganArea"  onchange="setRankGrade()">
										         
											</select>
										</td>
									</tr>
									<tr>
										<td style="text-align:right;width:50%">
											<font color="#ff0000">*</font>业务种类选择：
										</td>
										<td  style="text-align:left;width:50%">
											<select name="rankBusinessType" id="rankBusinessType"  onchange="setRankGrade()">
												
											</select>
										</td>
									</tr>
									<tr>
									    <td style="width:50%"></td>
										<td style="text-align:left;width:50%" >
											<input name="rankGrade" id="rankGrade"  readonly="readonly">
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="31"  class="tdhead">
								<font color="#ff0000">*</font>信息系统说明：
							</td>
							<td colspan="3">
								<textarea id="rankInfoSysIntr" name="rankInfoSysIntr" 
									style="height:100%;width:95%; overflow-x: visible; overflow-y: visible;"
									class="txtRequired 100"><s:property value="rank.rankInfoSysIntr"/></textarea>
							</td>
						</tr>
					</table>
					<br>
			<%--广播中心--%>
			<div id="broadcastCenter">
					<table width="100%" border="1" bordercolor="#5AA4D1" cellpadding="0" cellspacing="0">
					   <tr >
					    <td colspan="6">表5  各级广播中心播出相关信息系统安全保护等级</td>
					   </tr>
					   <tr>
					    <td>序号</td>
					    <td>信息系统分类</td>
					    <td>国家级</td>
					    <td>省级</td>
					    <td>省会城市、计划单列市</td>
					    <td>地市及以下</td>
					   </tr>

	                   <tr>
					    <td>1</td>
					    <td>播出系统</td>
					    <td id="1:1:1">第四级</td>
					    <td id="1:1:2">第三级</td>
					    <td id="1:1:3">第三级</td>
					    <td id="1:1:4">第三级</td>
					   </tr>
					   
					    <tr>
					    <td>2</td>
					    <td>新闻制播系统</td>
					    <td id="1:2:1">第三级</td>
					    <td id="1:2:2">第三级</td>
					    <td id="1:2:3">第三级</td>
					    <td id="1:2:4">第二级</td>
					   </tr>
					   
					    <tr>
					    <td>3</td>
					    <td>业务支撑系统</td>
					    <td id="1:3:1">第二级</td>
					    <td id="1:3:2">第二级</td>
					    <td id="1:3:3">第二级</td>
					    <td id="1:3:4">第二级</td>
					   </tr>
					   
					    <tr>
					    <td>4</td>
					    <td>媒资系统</td>
					    <td id="1:4:1">第二级</td>
					    <td id="1:4:2">第二级</td>
					    <td id="1:4:3">第二级</td>
					    <td id="1:4:4">第二级</td>
					   </tr>
					   
					    <tr>
					    <td>5</td>
					    <td>综合制作系统</td>
					    <td id="1:5:1">第二级</td>
					    <td id="1:5:2">第二级</td>
					    <td id="1:5:3">第二级</td>
					    <td id="1:5:4">第二级</td>
					   </tr>
					   
 					  <tr>
					    <td>6</td>
					    <td>生产管理系统</td>
					    <td id="1:6:1">第二级</td>
					    <td id="1:6:2">第二级</td>
					    <td id="1:6:3">第二级</td>
					    <td id="1:6:4">第二级</td>
					   </tr>
				</table>
			</div>
			
			<%--电视中心--%>
			<div id="tvCenter">
					<table width="100%" border="1" bordercolor="#5AA4D1" cellpadding="0" cellspacing="0">
					    <tr >
					     <td colspan="6">表6  各级电视中心播出相关信息系统安全保护等级</td>
					   </tr>
					   <tr>
					    <td>序号</td>
					    <td>信息系统分类</td>
					    <td>国家级</td>
					    <td>省级</td>
					    <td>省会城市、计划单列市</td>
					    <td>地市及以下</td>
					   </tr>

	                   <tr>
					    <td>1</td>
					    <td>播出系统</td>
					    <td id="2:1:1">第四级</td>
					    <td id="2:1:2">第三级</td>
					    <td id="2:1:3">第三级</td>
					    <td id="2:1:4">第三级</td>
					   </tr>
					   
					    <tr>
					    <td>2</td>
					    <td>新闻制播系统</td>
					    <td id="2:2:1">第三级</td>
					    <td id="2:2:2">第三级</td>
					    <td id="2:2:3">第三级</td>
					    <td id="2:2:4">第二级</td>
					   </tr>
					   
					  <tr>
					    <td>3</td>
					    <td>播出整备系统</td>
					    <td id="2:3:1">第三级</td>
					    <td id="2:3:2">第三级</td>
					    <td id="2:3:3">第二级</td>
					    <td id="2:3:4">第二级</td>
					   </tr>
					   
					   <tr>
					    <td>4</td>
					    <td>业务支撑系统</td>
					    <td id="2:4:1">第二级</td>
					    <td id="2:4:2">第二级</td>
					    <td id="2:4:3">第二级</td>
					    <td id="2:4:4">第二级</td>
					   </tr>
					   
					    <tr>
					    <td>5</td>
					    <td>媒资系统</td>
					    <td id="2:5:1">第二级</td>
					    <td id="2:5:2">第二级</td>
					    <td id="2:5:3">第二级</td>
					    <td id="2:5:4">第二级</td>
					   </tr>
					   
					    <tr>
					    <td>6</td>
					    <td>综合制作系统</td>
					    <td id="2:6:1">第二级</td>
					    <td id="2:6:2">第二级</td>
					    <td id="2:6:3">第二级</td>
					    <td id="2:6:4">第二级</td>
					   </tr>
					   
 					  <tr>
					    <td>7</td>
					    <td>生产管理系统</td>
					    <td id="2:7:1">第二级</td>
					    <td id="2:7:2">第二级</td>
					    <td id="2:7:3">第二级</td>
					    <td id="2:7:4">第二级</td>
					   </tr>
				</table>
			</div>
			
			<%--付费频道--%>
			<div id="payChannel">
					<table width="100%" border="1" bordercolor="#5AA4D1" cellpadding="0" cellspacing="0">
					   <tr >
					     <td colspan="4">表7  付费频道播出相关信息系统安全保护等级</td>
					   </tr>
					   <tr>
					    <td>序号</td>
					    <td>信息系统分类</td>
					    <td>全国或跨省</td>
					    <td>全省或跨地市</td>
					   </tr>

	                   <tr>
					    <td>1</td>
					    <td>播出系统</td>
					    <td id="3:1:1">第三级</td>
					    <td id="3:1:2">第三级</td>
					   </tr>
					   
					  <tr>
					    <td>2</td>
					    <td >播出整备系统</td>
					    <td id="3:2:1">第三级</td>
					    <td id="3:2:2">第二级</td>
					   </tr>
					   
					  <tr>
					    <td>3</td>
					    <td>媒资系统</td>
					    <td id="3:3:1">第二级</td>
					    <td id="3:3:2">第二级</td>
					   </tr>
					   
					   <tr>
					    <td>4</td>
					    <td>综合制作系统</td>
					    <td id="3:4:1">第二级</td>
					    <td id="3:4:2">第二级</td>
					   </tr>
					   
					    <tr>
					    <td>5</td>
					    <td>业务支撑系统</td>
					    <td id="3:5:1">第二级</td>
					    <td id="3:5:2">第二级</td>
					   </tr>
					   
					    <tr>
					    <td>6</td>
					    <td>生产管理系统</td>
					    <td id="3:6:1">第二级</td>
					    <td id="3:6:2">第二级</td>
					   </tr>
					   
				</table>
			</div>
			
			<%--集成播控--%>
			<div id="broadcastingCentre">
					<table width="100%" border="1" bordercolor="#5AA4D1" cellpadding="0" cellspacing="0">
					   <tr >
					     <td colspan="3">表8  集成播控平台相关信息系统安全保护等级</td>
					   </tr>
					   <tr>
					    <td>序号</td>
					    <td>信息系统分类</td>
					    <td>安全等级</td>
					   </tr>

	                   <tr>
					    <td>1</td>
					    <td>广播系统</td>
					    <td id="4:1:">第三级</td>
					   </tr>
					   
					  <tr>
					    <td>2</td>
					    <td>点播系统</td>
					    <td id="4:2:">第三级</td>
					   </tr>
					   
					  <tr>
					    <td>3</td>
					    <td>媒资系统</td>
					    <td id="4:3:">第二级</td>
					   </tr>
					   
					   <tr>
					    <td>4</td>
					    <td>综合制作系统</td>
					    <td id="4:4:">第二级</td>
					   </tr>
					   
					    <tr>
					    <td>5</td>
					    <td>运营支撑系统</td>
					    <td id="4:5:">第二级</td>
					   </tr>
					   
					    <tr>
					    <td>6</td>
					    <td>生产管理系统</td>
					    <td id="4:6:">第二级</td>
					   </tr>
					   
				</table>
			</div>
			
			
			<%--有线电视--%>
			<div id="cableTelevision">
					<table width="100%" border="1" bordercolor="#5AA4D1" cellpadding="0" cellspacing="0">
					   <tr >
					     <td colspan="4">表9  有线电视网络相关信息系统安全保护等级</td>
					   </tr>
					   <tr>
					    <td>序号</td>
					    <td>信息系统分类</td>
					    <td>全国或跨省</td>
					    <td>全省或跨地市</td>
					   </tr>

	                   <tr>
					    <td>1</td>
					    <td>广播系统</td>
					    <td id="5:1:1">第三级</td>
					    <td id="5:1:2">第三级</td>
					   </tr>
					   
					  <tr>
					    <td>2</td>
					    <td>点播系统</td>
					    <td id="5:2:1">第三级</td>
					    <td id="5:2:2">第二级</td>
					   </tr>
					   
					  <tr>
					    <td>3</td>
					    <td>媒资系统</td>
					    <td id="5:3:1">第二级</td>
					    <td id="5:3:2">第二级</td>
					   </tr>
					   
					   <tr>
					    <td>4</td>
					    <td>运营支撑系统 </td>
					    <td id="5:4:1">第二级</td>
					    <td id="5:4:2">第二级</td>
					   </tr>
					   
					    <tr>
					    <td>5</td>
					    <td>生产管理系统</td>
					    <td id="5:5:1">第二级</td>
					    <td id="5:5:2">第二级</td>
					   </tr> 
				</table>
			</div>
			
			<%--无线覆盖--%>
			<div id="wirelessCoverage">
					<table width="100%" border="1" bordercolor="#5AA4D1" cellpadding="0" cellspacing="0">
					   <tr >
					     <td colspan="3">表10  无线覆盖等其它类相关信息系统安全保护等级</td>
					   </tr>
					   <tr>
					    <td>序号</td>
					    <td>信息系统分类</td>
					    <td>安全等级</td>
					   </tr>

	                   <tr>
					    <td>1</td>
					    <td>生产调度系统</td>
					    <td id="6:1:">第二级</td>
					   </tr>
					   
					  <tr>
					    <td>2</td>
					    <td>生产管理系统</td>
					    <td id="6:2:">第二级</td>
					   </tr>
				</table>
			</div>
				<table width="100%" >
					<tr>
						<td style="text-align: right">
							<input type="button" value="下一步"  class="btnyh" onclick="goahead()"/>
						</td>

					</tr>
				</table>
		</form>
	</body>
</html>
