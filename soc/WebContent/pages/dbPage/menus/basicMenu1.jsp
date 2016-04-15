<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>基础数据</title>
<link rel="stylesheet" href="${ctx}/css/leftTree.css" type="text/css"></link>
<script type="text/javascript" src="${ctx}/scripts/jquery-1.7.2.min.js"></script>	
<script type="text/javascript" src="${ctx}/scripts/jquery.tree.js"></script>	
<script type="text/javascript">
$(function(){
	$('#files').tree({
		expanded: 'li:first'
	});
});
</script>		
 </head>
<body>	
<table width="100%" cellSpacing="0" cellPadding="0" >
  <tbody>
    <tr>
      <td width=1% height=3%><img src="${ctx}/images/bg_left_tl.gif"/></td>
      <td width="*"   style="FONT-SIZE: 12px; BACKGROUND-IMAGE: url(${ctx}/images/bg_left_tc.gif); COLOR: white; FONT-FAMILY: system">
     </td>
      <td width=1%><img src="${ctx}/images/bg_left_tr.gif"/></td>
    </tr>
    <tr>
      <td style="BACKGROUND-IMAGE: url(${ctx}/images/bg_left_ls.gif)"></td>
      <td id="menuTree" style="PADDING-RIGHT: 10px; PADDING-LEFT: 0px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px; HEIGHT: 100%; BACKGROUND-COLOR: white" 
    vAlign="top">
    <%--
   树形导航
    --%><ul id="files">
	<li><a href="${ctx}/unitInfo/query.action" target="mainFrame" title="单位基本情况">单位基本情况</a>
	</li>
	<li><a href="${ctx}/pages/basic/manager.jsp" target="mainFrame" title="信息系统管理">信息系统管理</a>
	</li>
	<li><a href="javascript:void(0);">资产管理</a>
	   	<ul>
			<li><a href="${ctx}/pages/basic/assets/softassets.jsp" target="mainFrame" title="业务应用软件">业务应用软件</a></li>
			<li><a href="${ctx}/pages/basic/assets/dataassets.jsp" target="mainFrame" title="关键数据类别">关键数据类别</a></li>
			<li><a href="${ctx}/pages/basic/assets/compassets.jsp" target="mainFrame" title="主机存储设备">主机存储设备</a></li>
			<li><a href="${ctx}/pages/basic/assets/networkassets.jsp" target="mainFrame" title="网络互连设备">网络互连设备</a></li>
			<li><a href="${ctx}/pages/basic/assets/devassets.jsp" target="mainFrame" title="安全设备">安全设备</a></li>
			<li><a href="${ctx}/pages/basic/assets/empassets.jsp" target="mainFrame" title="安全相关人员">安全相关人员</a></li>
			<li><a href="${ctx}/pages/basic/assets/docassets.jsp" target="mainFrame" title="安全管理文档">安全管理文档</a></li>
		</ul>
	</li>
</ul>
    
    </td>
      <td style="BACKGROUND-IMAGE: url(${ctx}/images/bg_left_rs.gif)"></td>
    </tr>
    <tr>
      <td width=10><img src="${ctx}/images/bg_left_bl.gif"/></td>
      <td style="BACKGROUND-IMAGE: url(${ctx}/images/bg_left_bc.gif)"></td>
      <td width=10><img src="${ctx}/images/bg_left_br.gif"/></td>
    </tr>
  </tbody>
</table>
 
</body>
</html>