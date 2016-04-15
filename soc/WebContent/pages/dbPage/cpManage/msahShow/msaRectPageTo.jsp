<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <meta http-equiv="Cache-Control" content="private"/>
     <LINK href="${ctx}/css/basic/css.css" type=text/css rel=stylesheet>
    
<LINK href="${ctx}/css/style/jquery-ui.custom.css" type=text/css rel=stylesheet>
<LINK href="${ctx}/css/style/jquery.popuplayer.css" type=text/css rel=stylesheet>
<SCRIPT src="${ctx}/scripts/jquery-1.7.2.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/jquery-ui.custom.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/util.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/popuplayer.js" type=text/javascript></SCRIPT>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>

<script type="text/javascript">
   jQuery(document).ready(function()
	{
	 parent.leftFrame.location.href='${ctx}/pages/dbPage/cpManage/msahShow/msaRectMenus.jsp';
	 //parent.mainFrame.location.href='${ctx}/pages/cpManage/msahShow/msaRect.jsp';
	 parent.mainFrame.location.href='${ctx}/pages/dbPage/cpManage/msahShow/msaRect.jsp';
	});
	</script>
  </head>
  
  <body>
     页面正在跳转…………
  </body>
</html>
