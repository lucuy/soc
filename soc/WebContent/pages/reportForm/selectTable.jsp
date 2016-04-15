<%@ include file="mainoffer.inc"%>
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
	
		<title>table content</title>
		<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<table border="1" align="left"
			style="table-layout: fixed; word-wrap: break-word;" cellpadding="2"
			cellspacing="0" bordercolorlight="#666666" >
			<tr>
				<logic:notEmpty name="titlelist">
					<logic:iterate id="tlist" name="titlelist">
						<td width="${tlist.colWidth }" title="${tlist.colName }"
							align="<logic:equal name="tlist" property="alignType" value="0">left</logic:equal><logic:equal name="tlist" property="alignType" value="1">center</logic:equal><logic:equal name="tlist" property="alignType" value="2">right</logic:equal>"
							background="../images/title_bg.gif">
							<div
								style="overflow: hidden; text-overflow: ellipsis; width: ${( tlist . colWidth * 0.9 )}">
								${tlist.colName}
							</div>
						</td>
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
			<logic:notEmpty name="page">
				<form action="" method="post" name="SelectCom">
					<input type="hidden" name="arrays" value="${arrays }" />
					<input type="hidden" name="tempHaving" value="${tempHaving }" />
					<input type="hidden" name="pageoffset" value="${pageoffset }" />
				<tr>
					<td colspan="${title }">
						<table width="100%" border="0">
							<tr align='right'>
								<td valign='top'>
									<%@ include file="selectTable.inc"%>
									<bean:message key="pager.findpage" />
									<input type="hidden" value="findPage" name="action" />
									<input type="hidden"
										value='<bean:write name="page" property="totalPages"/>'
										name="totalPages" id="totalPages" />
									<input name="page" id="page" size="1" maxlength="5"
										onkeydown="return onkeyPageGo();"/>
									<bean:message key="pager.page" />
									<input type="button" value="go" onclick="pagego();" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
				</form>
			</logic:notEmpty>
		</table>

	</body>
</html>
