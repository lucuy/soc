<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8" import="java.util.*"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<link href="${ctx}/styles/left.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/treeview.js"></script>
<script type="text/javascript" src="${ctx}/dwr/engine.js"></script>
<script type="text/javascript" src="${ctx}/dwr/interface/MessagePush.js"></script>
<script type='text/javascript' src="${ctx}/dwr/util.js"></script>
<script type="text/javascript" src="${ctx}/scripts/dwrManager.js"></script>
<link type="text/css"
	href="${ctx}/styles/css/ui-lightness/jquery-ui-1.7.3.custom.css"
	rel="stylesheet" />
	<script type="text/javascript"
	src="${ctx}/scripts/js/jquery-ui-1.7.3.custom.min.js"></script>
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
<script type="text/javascript">
   
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
       /**function showmessage(val){
  		
      	}*/
	jQuery(document).ready(function() {
		$("#group").attr("style", "display:none");
		$("#group1").attr("style", "display:none");
		$("#imgline").attr("style", "display:none");
		$("#s1").hide();
		if ('${isOnclick}' == "true") {
			$("#group").attr("style", "display:block");
			$("#group1").attr("style", "display:block");
			$("#imgline").attr("style", "display:block");
			$("#s1").show();
			$("#task").attr("style", "display:none");
		}
	});
	
	
	function show(url) {
		parent.mainFrame.location.href = url;
	}
	
	function action(elementId,eventsType)
	{
	        changeIcon($("#img_query_"+elementId+""));
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
        
    
	 function showReport(id)
     {
		 if(id==1){
		 	action(id,"img");
			return;
		 }
       parent.mainFrame.location.href = '${ctx}/auditReport/queryReportByAuditReportId.action?auditReportId='
				+ id;
     }
	 function reload() {
		    document.onmousedown=ContextMenu;
	        $("#mack").addClass("ui-widget-overlay");
	        //alert(document.body.clientWidth);
	      // $("#mack").css("height",document.body.clientHeight);
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
			//$("#mack").css("height",0);
		}
		
</script>
<html>
<head>
<link href="${ctx}/styles/left.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script language="javascript">
	
</script>
</head>

<body onload="dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);onLoad();">
	 <div class="evnTitleTop" >
	  	 <div style="background-image:url(${ctx}/images/leftDh.jpg)">
	   	  <table width="97%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt="" width="25" height="28" /></td>
	            <td class="leftDhxx">审计信息</td>
	          </tr>
	      </table>       
	    </div>
	    

					<div class="over">
	<%
				String permissionIds = (String)session.getAttribute("SOC_LOGON_PERMISSIONS");
				if(permissionIds.indexOf(",13,") != -1)
				{
			%>
				
			<div style = "margin-left:13px;margin-top:14px;" >
	      <a href="#" class="hand"
					onclick="parent.mainFrame.location.href='${ctx}/audit/query.action';">
						&nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">内部审计 </span></a>
	        </div>
			<%
			   }
			   if(permissionIds.indexOf(",14,") != -1)
				{
			 %>	
	
			<div class="eventstree" >
	       ${htmlBuffer}
	        </div>
	        <%
				} if(permissionIds.indexOf(",14,") != -1)
				{
			%>
				<div style = "margin-left:13px;margin-top:14px;" >
	      <a href="#" class="hand"
					onclick="parent.mainFrame.location.href='${ctx}/auditReport/customs.action';">
						&nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">自定义报表</span></a>
	        </div>
			<%
			   }
			 
			 %>	
        </div>
			</div>
			<div class="ui-overlay"></div>
		<div id="mack"></div>
	</body>
</html>
