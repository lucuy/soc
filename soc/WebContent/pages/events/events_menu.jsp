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
<script type="text/javascript" src="${ctx}/scripts/dwrManager.js"></script>
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
   width:180px;
   height:419px;
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
       		if(val!='-1'){
       			parent.frames[0].LoadTimer();
       			alert(val);
       		}else{
       			alert(val);
       			parent.frames[0].NewMessage();
       		}
       		
      	 	
       }
       dwr.engine.setErrorHandler(function(){});
       /**function showmessage(val){
  		
      	}*/
/**
    打开与关闭树并改变图片
*/
	function action_event(elementId,img,eventsType)
	{
	   if(img == "img")
	   {
	        if(eventsType == -1)
            {
                linkTo(elementId,eventsType); 
            }
            else
            {
                changeIcon($("#img_query_"+elementId+""));
                $("#query_"+elementId+" >ul").toggle("slow");
            }
	   }
	   else
	   {
	        changeIcon($("#img_query_"+elementId+""));
            $("#query_"+elementId+" >ul").toggle("slow");
	   }
	}
	
	function action(elementId,eventsType)
	{
	    var temp = eventsType+elementId;
	       
	    changeIcon($("#"+eventsType+elementId+""));
	        
	        
        $("#query_"+temp+" >ul").toggle("slow");
	}
	
    function changeIcon(nainNode)
    {
        if(nainNode)
        {
            if(nainNode.attr("src").indexOf("u321_normal.gif")>=0)
            {
               nainNode.attr("src","${ctx}/images/u319_normal.gif");
               
            }else
            {
               nainNode.attr("src","${ctx}/images/u321_normal.gif");
            }
         }
     }
     
     function showAssetGroup(id)
     {
       parent.mainFrame.location.href = '${ctx}/events/queryALLAsset.action?assetGroupId='
				+ id;
     }
     
     
    function linkTo(queryEventsId,linkType) 
    {
    
        if(queryEventsId==2){
    	parent.mainFrame.location.href = '${ctx}/events/queryRecentEvents.action';
        }else
        if(linkType == -1)
        {
                parent.mainFrame.location.href = '${ctx}/events/insertCustom.action?queryEventsId='+queryEventsId;
        }
        else if(linkType == 9)
        {
            parent.mainFrame.location.href ='${ctx}/alertRule/alertRuleQuery.action';
        }
        else if(linkType == 8)
        {
             parent.mainFrame.location.href='${ctx}/alertMessage/alertMessageQuery.action';
        }
        else  if(linkType == 10)
        {
            parent.mainFrame.location.href ='${ctx}/threshold/thresholdEdit.action';
        }
        else if(linkType == 26)
        {
            parent.mainFrame.location.href ='${ctx}/alertSetting/queryAlert.action';
        }
        else if(linkType == 2 || linkType == 11)
        {
            parent.mainFrame.location.href = '${ctx}/events/queryRelevanceAnalysis.action?queryEventsId='+queryEventsId+'&queryEventsType='+linkType+'&timeRange=24';
        }
        else if(linkType == -2)
        {
           parent.mainFrame.location.href='${ctx}/notAnalyticEvents/queryAnalysisFailed.action';
        }
        else if(linkType == -5)
        {
        	//关联后事件
           parent.mainFrame.location.href='/soc/events/queryRelevanceEvents.action';
        }
        else if(linkType == -4)
        {
           parent.mainFrame.location.href='/soc/notAnalyticEvents/queryAssetOfEvents.action';
        }
        else if(linkType == 27){
        	parent.mainFrame.location.href = '/soc/monitorGroup/queryMonitorAlarmForList.action';
        }
        else
        {
            parent.mainFrame.location.href = '${ctx}/events/queryEvents.action?queryEventsId='+queryEventsId+'&queryEventsType='+linkType;
        	//parent.mainFrame.location.href = '${ctx}/events/queryRecentEvents.action';
        }  
    }
</script>
</head>

<body>
	<div class="evnTitleTop" >
		<div style="background-image:url(${ctx}/images/leftDh.jpg)">
			<table width="180px" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt=""
						 height="28px" />
					</td>
					<td class="leftDhxx">事件管理</td>
				</tr>
			</table>
		</div>
	

		<div class="over">
						<table width="99%" border="0" cellspacing="5px" cellpadding="0px"
			style="margin-top:9px;margin-left:-17px;margin-bottom:-12px;">
			<tr>
				<td align="left"><a href="#" class="common_node"
					onclick="parent.mainFrame.location.href='${ctx}/events/queryRecentEvents.action';">
						&nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:top">近期事件</span></a></td>
			</tr>		
		</table>
			<div class="eventstree"  >
	        ${treeBuff}
	        </div>
	         <div style="background-image:url(${ctx}/images/leftDh.jpg)">
			<table width="180px" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt=""
						 height="28px" />
					</td>
					<td class="leftDhxx">内置按照资产查询</td>
				</tr>
			</table>
		</div>
		<div class="eventstree" >
	        ${assetBuf}
	        </div>
        </div>
        
       
    </div>
	
</body>
</html>
