<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%

String path = request.getContextPath();

String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<title>${title}</title>
<link rel="shortcut icon" href="${favicon}">
<link rel="Bookmark" href="${favicon}">
 <%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>
<style type="text/css">


</style>
<script type="text/javascript">
var g_blinkid = 0;

var g_blinkswitch = 0;

var g_blinktitle = document.title;

var g_onlineuser = "";

var g_sysmsg_sound = null;

var g_newmsg_sound = null;

var g_app_num = 0;

var g_appnum = 0;

var g_bappmore = false;

var g_inputtime = 0;


function blinkNewMsg()
{

  document.title = g_blinkswitch % 2==0 ? "【　　　　　】 - " + g_blinktitle : "【新告警信息】 - " + g_blinktitle;

 g_blinkswitch++;

}

 function start(){
       if(g_blinkid==0)
       {
       g_blinkid = setInterval(blinkNewMsg, 500);
       }
  }


function stopBlinkNewMsg()

{
    if (g_blinkid)

    {
        clearInterval(g_blinkid);

        g_blinkid = 0;

        document.title = g_blinktitle;
    }

}

function load(){
	if(document.all)
	{
		if(undefined==window.opener)
		{
			window.open(window.location,"","fullScreen=no;scrollbar=no,resizable=yes,menubar=no,toolbar=no,location=no,status=yes");
			window.opener=null;
			window.close();
		}
		else
		{
			self.moveTo(0,0);
			self.resizeTo(screen.availWidth,screen.availHeight);
		}
	}
}

function reload()
{
    setTimeout("location.reload(true)",60000); 
}

  //  window.onbeforeunload = onbeforeunload_handler;  
    //window.onunload = onunload_handler;  
   // function onbeforeunload_handler(){
    //清除session
       
       
    //    this.location.href = '${ctx}/home/test.action';   
     //   var warning="确认退出?";   
     //   return warning;		
   // }  
      
   /*  function onunload_handler(){  
        var warning="谢谢光临";  
        alert(warning);  
    }  */
	
</script> 
</head>

<frameset rows="100,*,40" frameborder="no" border="0" framespacing="0" >
  <frame src="<tiles:getAsString name="topFrame"/>" name="topFrame" id="topFrame" frameborder="0" scrolling="no" noresize="noresize" id="topFrame" title="topFrame">
  <frameset cols="190,0,*" frameborder="no" border="0"  framespacing="0" >
		<frame src="<tiles:getAsString name="leftFrame"/>" name="leftFrame" id="leftFrame" frameborder="no" border="0" title="leftFrame"  >
		<frame src="" frameborder="0" name="mapFrame" scrolling="NO" noresize title="mapFrame" >
  		<frame src="<tiles:getAsString name="mainFrame" />" name="mainFrame" id="mainFrame" frameborder="no" border="0"  id="mainFrame" title="mainFrame">
  </frameset>
  <frame src="<tiles:getAsString name="bottomFrame"/>" name="bottomFrame" id="bottomFrame" frameborder="no" border="0" scrolling="No" noresize="noresize" id="bottomFrame" title="bottomFrame">
</frameset>
<noframes>
<body onload="load()">
</body>
</noframes></html>
