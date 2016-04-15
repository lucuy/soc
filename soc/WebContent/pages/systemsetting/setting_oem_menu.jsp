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
<style type="text/css">
.tree ul {
	list-style: none;
}
</style>
<link type="text/css"
    href="${ctx}/styles/css/ui-lightness/jquery-ui-1.7.3.custom.css"
    rel="stylesheet" />
<script type="text/javascript"
    src="${ctx}/scripts/js/jquery-ui-1.7.3.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/dwr/engine.js"></script>
<script type="text/javascript" src="${ctx}/dwr/interface/MessagePush.js"></script>
<script type='text/javascript' src="${ctx}/dwr/util.js"></script>
<script type="text/javascript" src="${ctx}/scripts/dwrManager.js"></script>
<style type="text/css">
.titleTop {
	padding: 0px 0px 0px 0px;
	margin: 2px 0px 0px 0px;
	border: 1px solid #e3e6eb;
	width: 180px;
	height: 500px;
	float: left;
	margin-left: 6px;
	
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
       
    function reload() {
        document.onmousedown = ContextMenu;
        $("#mack").addClass("ui-widget-overlay");
    }
    
    function cancel()
        {
            document.onmousedown=ContextMenu;
            $("#mack").removeClass("ui-widget-overlay");
        }
    
    function ContextMenu() {

        if (event.button == 2 || event.button == 3) {
            alert("升级中无法操作");
            return false;
        }
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
</script>
</head>

<body onload="dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);onLoad();">
	<div class="titleTop" style="overflow:auto; overflow-x:hidden">
		<div style="background-image:url(${ctx}/images/leftDh.jpg)">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt=""
						width="25" height="28" /></td>
					<td class="leftDhxx">修改OEM</td>
				</tr>
			</table>
		</div>
		<table width="99%"  border="0" cellspacing="10" cellpadding="10"
			style="line-height: 15px">
			
			<tr>
				<td align="left">
					<a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/settingOEM/toUpdate.action';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp<span style="vertical-align:20%">修改OEM</span>
				</a></td>
			</tr>
			<tr>
				<td align="left">
					<a href="#"
					onclick="parent.mainFrame.location.href='${ctx}/settingOEM/functionColtrolDeail.action';">
						&nbsp;<img src="${ctx}/images/arrow_03.gif" />&nbsp<span style="vertical-align:20%">功能控制</span>
				</a></td>
			</tr>
			
		</table>
	</div>
	<div class="ui-overlay">
        <div id="mack"></div>
    </div>
</body>
</html>