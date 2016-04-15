<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
 
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

<meta http-equiv="Expires" content="0">
<meta http-equiv="kiben" content="no-cache">
<link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/treeview.js"></script>
<script language="javascript">
	function edit(groupId) {
		if(groupId!=${groupId}){
			
		document.getElementById('frmGroup').contentWindow.location.href = '${ctx}/assetGroup/edit.action?assetGroupId='
				+ groupId;
		}
		$("input:[name=assetGroupId]:radio").each(function() {
			if (this.value == groupId) {
				this.checked = true;
			}
		});
	}

	function addBrother() {
		var id = $('input:[name=assetGroupId]:radio:checked').val();
		if(id==1)
		{
			alert("全部资产不允许添加同级");
			return;
		}
		if (id != undefined){
			if(id==${groupId}){
				alert("当前关联的资产组不能添加同级！");
			}else{
			document.getElementById('frmGroup').contentWindow.location.href = '${ctx}/assetGroup/insert.action?type=b&assetGroupId='
					+ id;}
		}else{
			alert("请选择一个节点再添加同级");}
	}
	function edit1() {
		var id = $('input:[name=assetGroupId]:radio:checked').val();
		if(id == 1 || id ==2){
			alert("全部分组和未分组不能编辑");
			return ;
		}
		if (id != undefined) {
			if(id==${groupId}){
				alert("当前关联的资产组不能编辑！");
			}else{
			document.getElementById('frmGroup').contentWindow.location.href = '${ctx}/assetGroup/edit.action?assetGroupId='
					+ id;
			$("input:[name=assetGroupId]:radio").each(function() {
				if (this.value == id) {
					this.checked = true;
				}
			});
			}
		} else {
			alert("请选择一个节点再进行修改");
		}
	}
	function addSon() {
		var id = $('input:[name=assetGroupId]:radio:checked').val();
		if(id ==2){
			alert("未分组不能添加子组");
			return ;
		}
			
		if (id != undefined)
			document.getElementById('frmGroup').contentWindow.location.href = '${ctx}/assetGroup/insert.action?type=s&assetGroupId='
					+ id;
		else
			alert("请选择一个节点再添加下级");
	}

	function addRoot() {
		document.getElementById('frmGroup').contentWindow.location.href = '${ctx}/assetGroup/insert.action?type=r';
	}

	function del() {
		var id = $('input:[name=assetGroupId]:radio:checked').val();
		
		if(id == 1 || id ==2){
			alert("全部资产和未分组不能删除");
			return ;
		}
		if (id != undefined) {
			if(id==${groupId}){
				alert("不能删除当前关联的资产组！");
			}else{
			if (confirm("确认删除吗？")) {
				document.getElementById('frmGroup').contentWindow.location.href = '${ctx}/assetGroup/deleteAssetGroup.action?assetGroupId='
						+ id;
			}
			}
		} else {
			alert("请选择一个节点再删除");
		}
	}
	
	function check(){
		var id = $('input:[name=assetGroupId]:radio:checked').val();
		
		if (id != undefined) {
		if(id==1||id ==2)
		{
		   del();
		   return;
		}
		  $.ajax({
							type : "post",
							url : "${ctx}/assetGroup/checkAssetGroup.action",
							async : false,
							dataType : "text",
							data : "&assetGroupId=" + id,
							success : function(result) {
							     if(result=='true')
							     {
							          alert("请先删除组内的资产,再删除组!");
							     }
							     else
							     {
							       del();
							     }
							}
		                  });
		
		}else
	   {
	      alert("请选择一个节点在删除");
	   }
	}
</script>

</head>

<body style="margin-top: 2px">
	<table width="99%" border="0" cellspacing="0" cellpadding="0"
		style="margin-left: 4px">
		<tr>
			<td width="49%" valign="top">
				<div class="framDiv">
					<!--  左侧table-->
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td class="r2titler">资产组管理</td>
						</tr>
						<tr>
							<td align="left"><s:property value="htmlBuffer"
									escape="false" />
							</td>
						</tr>
						<tr height="10px" align="center">
							<td colspan="2"><div class="xuxian"></div></td>
						</tr>

						<tr>
							<td align="left">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="100%" align="left" style="padding-left: 6px">
											<s:if test="htmlBuffer eq ''">
												<input type="button" id="btnAdd1" class="btnstyle2"
													style="margin-left: 20px" onclick="addRoot()" />
												<label for="btnAdd1" class="btnValueAdd1">添加根</label>
											</s:if> <s:else>
												<input type="button" id="btnAdd2" class="btnstyle2"
												onclick="addBrother()" />
												<label for="btnAdd2" class="btnValueAdd1">添加同级</label>
												<input type="button" id="btnAdd3" class="btnstyle2"
													onclick="addSon()" />
												<label for="btnAdd3" class="btnValueAdd1">添加下级</label>
												<input type="button" id="btnAdd4" class="btnstyle5"
													onclick="edit1();" />
												<label for="btnAdd4" class="btnValueAdd1">修改信息</label>
												<img class="hand" src="${ctx}/images/deletegroup.jpg"
													onclick="check()" />
											</s:else>
										</td>
									</tr>
									<tr>
										<td class="td_detail_separator"></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</td>
			<td width="50%" valign="top"><iframe id="frmGroup"
					name="frmGroup" width="100%" height="500px" frameborder="0"
					style="visibility:visible;display:block"
					onload="SetCwinHeight('frmGroup');"> </iframe>
			</td>
		</tr>
	</table>
</body>
</html>
