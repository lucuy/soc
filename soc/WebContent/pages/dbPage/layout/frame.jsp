<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<head>
<title>捷成安全管理与综合审计系统</title>
<link rel="shortcut icon" href="${ctx}/images/favicon.ico" />
<script language="JavaScript">
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
	${sessionScope.MAIL_LOGIN_ROLE};
}
</script>
</head>

<frameset rows="100,*,40" frameborder="no" border="0" framespacing="0">
  <frame src="<tiles:getAsString name="topFrame"/>" name="topFrame" id="topFrame" frameborder="0" scrolling="No" noresize="noresize" id="topFrame" title="topFrame">
  <frameset cols="210,0,*" frameborder="no" border="0" framespacing="0" >
		<frame src="<tiles:getAsString name="leftFrame${sessionScope.MAIL_LOGIN_ROLE}"/>" name="leftFrame" id="leftFrame" frameborder="0" noresize title="leftFrame"  >
		<frame src="" frameborder="0" name="mapFrame" scrolling="NO" noresize title="mapFrame" >
		<frame src="<tiles:getAsString name="mainFrame${sessionScope.MAIL_LOGIN_ROLE}" />" name="mainFrame" id="mainFrame" frameborder="0" id="mainFrame" title="mainFrame">
  </frameset>
  <frame src="<tiles:getAsString name="bottomFrame"/>" name="bottomFrame" id="bottomFrame" frameborder="0" scrolling="No" noresize="noresize" id="bottomFrame" title="bottomFrame">
</frameset>
<noframes>
	<body onload="load()">
	</body>
</noframes>
</html>

<body>

</body>
</html>
