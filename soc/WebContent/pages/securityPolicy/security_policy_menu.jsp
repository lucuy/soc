<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8" import="java.util.*" %>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
<head>
<link href="${ctx}/styles/left.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src="/soc/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type="text/javascript" src="${ctx}/dwr/engine.js"></script>
<script type="text/javascript" src="${ctx}/dwr/interface/MessagePush.js"></script>
<script type='text/javascript' src="${ctx}/dwr/util.js"></script>
<script type="text/javascript" src="${ctx}/scripts/dwrManager.js"></script>
  <link type="text/css" href="${ctx}/styles/css/ui-lightness/jquery-ui-1.7.3.custom.css" rel="stylesheet" /> 
<style type="text/css">
.evnTitleTop {
	margin-left:6px;
	  margin-top:0px;
	  border:1px solid #E3E6EB; 
	  width:180px;
	  height:528px;
	  background-color:#EEF1F8;
	  float:left;
	  padding-bottom:10px; 
}

.manager {
	padding-right: 6px;
}

.wz {
	vertical-align: 4px
}

.eventstrees {
	margin: 0px 5px 5px 25px;
	padding: 0px 0px 0px 0px;
	overflow-x:auto;
	overflow-y: auto;
	white-space:nowrap;
	scrollbar-face-color: #FFFFFF;
	scrollbar-shadow-color: #D2E5F4;
	scrollbar-highlight-color: #D2E5F4;
	scrollbar-3dlight-color: #FFFFFF;
	scrollbar-darkshadow-color: #FFFFFF;
	scrollbar-track-color: #FFFFFF;
	scrollbar-arrow-color: #D2E5F4
}

.eventstrees ul {
	list-style: none;
	margin: 0px 0px 5px 0px;
}

.eventstrees ul li {
	cursor: hand;
	width:auto;
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
       			parent.frames[0].NewMessage();
       		}
       		
      	 	
       }
       dwr.engine.setErrorHandler(function(){});
       /**function showmessage(val){
  		
      	}*/

/**
    打开与关闭树并改变图片
*/
	function action(elementId,eventsType)
	{
	         var temp = eventsType+elementId;
	        // alert(temp);
	        
	        changeIcon($("#"+eventsType+elementId+""));
	        
	        
            $("#query_"+elementId+" >ul").toggle("slow");
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
        	//遮罩的方法开始
	 function reload() {
	    document.onmousedown=ContextMenu;
        $("#mack").addClass("ui-widget-overlay");
        //alert(document.body.clientHeight);
       $("#mack").css("height",document.documentElement.clientHeight);
    }
    	function ContextMenu(){
      if (event.button==2 || event.button==1) {  
             alert("加载数据中...");
             return false;
        }
  }
  function cancel() {
		document.onmousedown = null;
		$("#mack").removeClass("ui-widget-overlay");
		$("#mack").css("height",0);
	}
	//遮罩的方法结束
</script>
</head>

<body onload="dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);onLoad();">

	<div class="titleTop">
		<div style="background-image:url(${ctx}/images/leftDh.jpg)">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt=""
						width="25" height="28" />
					</td>
					<td class="leftDhxx">安全策略管理</td>
				</tr>
			</table>
		</div>
		
		<table width="99%" border="0" cellspacing="5px" cellpadding="0px"
			style="margin-top:10px;margin-left:-17px;">
			
			<%
				String permissionIds = (String)session.getAttribute("SOC_LOGON_PERMISSIONS");
				if(permissionIds.indexOf(",80,") != -1)
				{
			%>
			<tr>
				<td align="left"><a href="#" class="common_node"
					onclick="parent.mainFrame.location.href='${ctx}/securityPolicy/query.action';">
						&nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:top">安全策略管理</span></a></td>
			</tr>
			<%
			}
			%>
			<%
				
				if(permissionIds.indexOf(",81,") != -1)
				{
			%>
			<tr>
				<td align="left"><a href="#" class="common_node"
					onclick="parent.mainFrame.location.href='${ctx}/scanTask/query.action';">
						&nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:top">漏扫联动</span></a></td>
			</tr>
			<%
			}
			%>
		</table>
		
	</div>
	<div class="ui-overlay">
        <div id="mack"></div>
     
    </div>
</body>
</html>
