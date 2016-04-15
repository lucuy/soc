<%@ include file="mainoffer.inc"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%

String path = request.getContextPath();

	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

    
    <title>查询结果</title>
	<link href="${ctx}/styles/currentlystyle.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
	</script>
  </head>
  
  <body>
  <div>
    <table width="100%" align="center" border="0" cellspacing="1" cellpadding="0" class="tab2">
    <tr height="40">
    	<logic:notEmpty name="titlelist">
    		<logic:iterate id="tlist" name="titlelist">
    			
    				${tlist }
    			
    		</logic:iterate>
    	</logic:notEmpty>
    </tr>
    	<logic:notEmpty name="datalist">
			<logic:iterate id="dlist" name="datalist">
				<tr>
					<logic:iterate id="list" name="dlist">
						<logic:iterate id="li" name="list">
							${li }		
						</logic:iterate>
					</logic:iterate>
				</tr>
			</logic:iterate>
		</logic:notEmpty>
		<logic:notEmpty name="data">
			<tr>
				<td colspan="${titleNum }" align="center">${data }</td>
			</tr>
	    </logic:notEmpty>
    </table>
    </div>
  </body>
</html>
