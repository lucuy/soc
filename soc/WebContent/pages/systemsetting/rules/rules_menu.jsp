 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
<head>
<link href="${ctx}/styles/left.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css">

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type="text/javascript" src="${ctx}/dwr/engine.js"></script>
<script type="text/javascript" src="${ctx}/dwr/interface/MessagePush.js"></script>
<script type='text/javascript' src="${ctx}/dwr/util.js"></script>
<style type="text/css">
html{
   overflow: hidden;
   }
body{maring:0px auto;padding:0px 0px 0px 0px;}

.evnTitleTop
{   
      padding:0px 0px 0px 0px ;
      margin:2px 0px 0px 6px;
      width:180px;
      border:1px solid #c9e1f3;
      float: left;
}
.over
{
   width:170px;
   height:449px;
   overflow-x:auto;
   overflow-y:auto;
   scrollbar-face-color: #FFFFFF; 
   scrollbar-shadow-color: #D2E5F4; 
   scrollbar-highlight-color: #D2E5F4; 
   scrollbar-3dlight-color: #FFFFFF; 
   scrollbar-darkshadow-color: #FFFFFF; 
   scrollbar-track-color: #FFFFFF; 
   scrollbar-arrow-color: #D2E5F4;
}

</style>
<script language="javascript">
function onLoad(){
            var userId = '${userinfo}';
            MessagePush.pageOnLoad(userId);
       }
       
       function showMessage(val){
       		parent.frames[0].LoadTimer();
      	 	alert(val);
       }
       function showmessage(val){
     		
     	}
	function action(elementId, eventsType) {
		if (eventsType != "img" && eventsType != 6) {
			if (eventsType == 1) {
				parent.mainFrame.location.href = '${ctx}/events/insertCustom.action';
			} else {
				linkTo(elementId, eventsType);
			}
		} else {
			changeIcon($("#img_query_" + elementId + ""));
			$("#query_" + elementId + " >ul").toggle("slow");
		}
	}
	function changeIcon(nainNode) {
		if (nainNode) {
			if (nainNode.attr("src").indexOf("u321_normal.gif") >= 0) {
				nainNode.attr("src", "${ctx}/images/u319_normal.gif");

			} else {
				nainNode.attr("src", "${ctx}/images/u321_normal.gif");
			}
		}
	}

	function linkTo(queryid) {
		parent.mainFrame.location.href = '${ctx}/settingLogger/queryId.action?queryId='
				+ queryid;
	}
	 function showRule(id)
     {
		 if(id==1){
			  parent.mainFrame.location.href = '${ctx}/relevanceGroup/showGroupList.action'; 
		 }else{
       parent.mainFrame.location.href = '${ctx}/relevanceRules/queryRelevanceRuleList.action?groupId='+ id;
      // alert('${ctx}/relevanceRules/queryRelevanceRuleList.action?groupId='+ id);
		 }
     }
</script>
</head>

<body onload="dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);onLoad();">
	 <div class="evnTitleTop" >
	  	 <div style="background-image:url(${ctx}/images/leftDh.jpg)">
	   	  <table width="97%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt="" width="25" height="28" /></td>
	            <td class="leftDhxx">规则管理</td>
	          </tr>
	      </table>       
	    </div>
	    

<div class="over">
	<%
				String permissionIds = (String)session.getAttribute("SOC_LOGON_PERMISSIONS");
				if(permissionIds.indexOf(",21,") != -1)
				{
			%>
				
			<div style = "margin-left:13px;margin-top:14px;" >
	      <a href="#" class="hand"
					onclick="parent.mainFrame.location.href='${ctx}/settingAssociationRules/AssociationRules.action';">
						&nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">关联规则</span></a>
	        </div>
	        <%}
				if(permissionIds.indexOf(",22,") != -1)
				{
			%>
				
			<div style = "margin-left:13px;margin-top:14px;" >
	      <a href="#" class="hand"
					onclick="parent.mainFrame.location.href='${ctx}/settingAnalysisRules/AnalysisRules.action';">
						&nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">解析规则</span></a>
	        </div>
	        <%}
				if(permissionIds.indexOf(",23,") != -1)
				{
			%>
				
			<div style = "margin-left:13px;margin-top:14px;" >
	      <a href="#" class="hand"
					onclick="parent.mainFrame.location.href='${ctx}/filteringRules/queryFilterRuleList.action';">
						&nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">过滤规则</span></a>
	        </div>
		 <%	}
	%>
			<div class="eventstree" >
	           ${htmlBuffer}
	        </div>
	       
        </div>
			</div>
			<div class="ui-overlay"></div>
		<div id="mack"></div>
	</body>
</html>
