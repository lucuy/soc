<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">
	<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/fontColor.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery-latest.js"></script>
<script type="text/javascript"
	src="${ctx }/scripts/jquery.tablesorter.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<script language="javascript">
var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
		//快速查找
		function query(){ 
			var keyword=$("#keyword").val();
			if($('#keyword').val().length>50){
				alert("搜索长度不能大于50");
				$('#keyword').val('');
				$('#keyword').focus();
				return ;
			}
			if(!rege.test($('#keyword').val())){
				alert("输入的内容包含特殊字符，请重新输入");
				$('#keyword').val('');
				$('#keyword').focus();
				return;
			}
			location.href = "${ctx}/warn/query.action?keyword=" + encodeURIComponent(encodeURIComponent(keyword));
		}
		$(document).ready(function(){
			$('#keyword').keydown(function(event){
				if(event.keyCode == 13){
					query();
				}
			});
			
			//全选反选
			$("#checkbx").click(function(){
				if($(this).is(":checked")){
					$(":checkbox[name='ids']").each(function(){
						$(this).attr("checked",true);
					});
				}else{
					$(":checkbox[name='ids']").each(function(){
						$(this).attr("checked",false);
					});
				}
			});
	       $('#listTable').tablesorter();
		});
		
		//删除
		function deleteWarn(){
			var ids = "";
			$("[name=ids]:checkbox:checked").each(function() {
				if (ids != "") {
					ids += "," + $(this).val();
				} else {
					ids = $(this).val();
				}
			});
			if ($(":checkbox[checked = true]").size() < 1) {
				alert("请至少选择一个预警发布信息...");
				return;
			}
			if (confirm("确认执行操作吗？")) {
					var s = document.getElementsByName("ids");
				var result="";
				for(var i=0;i<s.length;i++){
					if(s[i].checked==true){
						  result+=s[i].value+","; 
					}
				}
				location.href="${ctx}/warn/deleteById.action?result="+result;
				}	
		}
		
		
		
			//点击高级弹出框的开始
		$(document).ready(function() {
		// 高级-Dialog			
			$('#dialog-extQuery').dialog({
				autoOpen : false,
				width : 450,
				height : 230,
				buttons : {
					"查询[Enter]" : function() {
						higquery();
						$(this).dialog("close");
					},
					"取消[Esc]" : function() {
						$(this).dialog("close");
					}
				}
			});	
		//点击查看详情后显示具体影响的资产信息 的表格
		$('#dialoginfo').dialog({
			autoOpen : false,
			width: 500,
			height:370,
			buttons:{
				"确定[Enter]":function(){
					$(this).dialog("close");
				}
			}
		});
		
		});
			
		function extQueryDlg() {
		$('#WarnName').val('');
		$('#WarnType').val('');
		$('#SeriousLevel').val('');
		$('#dialog-extQuery').dialog('open');
		}
		//高级查询所得值
		function higquery(){
			    $('#selStatus').val($('#Status').val());
				$('#selWarnName').val($('#WarnName').val());
				$('#selWarnType').val($('#WarnType').val());
				$('#selSeriousLevel').val($('#SeriousLevel').val());
				if($('#WarnName').val().length>50){
					alert("预警名称长度不能大于50");
					$('#WarnName').val('');
					$('#WarnName').focus();
					return ;
				}
				if(!rege.test($('#WarnName').val())){
					alert("输入的内容包含特殊字符，请重新输入");
					$('#WarnName').val('');
					$('#WarnName').focus();
					return;
				}
				$('#queryForm').submit();
				$(this).dialog("close");
		}
		
		//点击查询详细超链接，查看受影响的资产
		function lookingFor(obj){
			    var originalStr = obj.id ; 
			    //var str = obj.replace(/[\u4E00-\u9FA5]/g,"Object").replace(/ /g,"|");
			    var str = originalStr.replace(/ /g,"|");
			    var titleInfo = $(obj).attr('name');
			   // var address="${ctx}/warn/getData.action?asset.assetSupportDevice="+str;  
			    var address="${ctx}/warn/getData.action";  
			    var jsonData = {"asset.assetSupportDevice":str} ; 
			 
			    $('#dialoginfoTable').empty(); 
			    $.post("${ctx}/warn/getData.action",{"asset.assetSupportDevice":str},function(data){
		    		$.each(data,function(commentIndex,asset){
		    			var trInfo="<tr align='center'>"+"<td>"+asset.assetId+"</td>"+"<td>"+asset.assetName+"</td>"+"<td>"+asset.assetMac+"</td>"+"</tr>";
                        $('#dialoginfoTable').append(trInfo);
		    		});
		    		$('#dialoginfo').dialog({title:titleInfo+' 影响的资产信息'});
		    		$('#dialoginfo').dialog('open');
			    });
		}
		
	</script>

<title>预警发布</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body style="margin-top:2px;">
	<s:form action="query.action" namespace="/warn" method="post" id="empForm" name="empForm">
	<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
		<tr><td>
		<div class="box">
			<div class="right">

				<input type="button" value="添加" class="btnstyle"
					style="margin-right:5px;margin-top:-2px"
					onclick="location.href='${ctx}/pages/repository/warn_add.jsp'" /> <input
					type="button" value="删除" style="margin-right:12px;margin-top:-2px"
					class="btnstyle" onclick="deleteWarn();" />
			</div>
			 <span class="kuaiju">快速搜索</span> <input type="text" id="keyword" style="height: 15px;"
				value="${keyword}" name="keywordTest" class="jianju" /> <img
				src="${ctx}/images/search.jpg" onclick="query();"
				style="margin-left:5px"> <a href="#" class="jianju"
				onclick="extQueryDlg();">高级</a>
				
		</div></td></tr>
		<tr><td>
		<div class="sbox">
			<div class="cont">
				<!-- information area -->
				<div id="dataList">
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						class="tab2" id="listTable">
						<thead>
						<tr height="28" class="biaoti">
							<td width="6%" class="biaoti"><input type="checkbox"
								id="checkbx" value="<s:property value="#warn.warnId"/>"
								class="check-box" />
							</td>
							<th width="15%" class="biaoti">预警名称</th>
							<th width="25%" class="biaoti">漏洞名称</th>
							<th width="15%" class="biaoti">类型</th>
							<th width="10%" class="biaoti">发布日期</th>
							<th width="10%" class="biaoti">严重程度</th>
							<th width="10%" class="biaoti">影响范围</th>
							<th width="*" class="biaoti">风险</th>
						</tr>
						</thead>
						<tbody>
						<s:iterator value="#request.allList" id="warn" status="up">
							<tr>
								<td class="biaocm" valign="middle"><input type="checkbox"
									name="ids" value="<s:property value="#warn.warnId"/>"
									class="check-box" /></td>
								<td valign="middle"><a
									href="${ctx}/warn/queryById.action?warnId=<s:property value="#warn.warnId"/>"><s:property
											value="#warn.warnName" />
								</a></td>
								<td valign="middle">
							<c:out value="${leakName }"></c:out>	
								<!-- <c:if test="${status ==50}">未发布</c:if>
									<c:if test="${status ==100}">已发布</c:if> <c:if
										test="${status ==20}">已过期</c:if> <c:if test="${status ==0}">已消除</c:if> -->
								</td>
								<td valign="middle"><c:if test="${warnType ==1}">新漏洞</c:if>
									<c:if test="${warnType ==2}">蠕虫</c:if></td>
								<td valign="middle"><s:date name="#warn.publicDate"
										format="yyyy-MM-dd" />
								</td>
								<td valign="middle"><c:if test="${seriousLevel ==1}">低级</c:if>
									<c:if test="${seriousLevel ==2}">中低级</c:if> <c:if
										test="${seriousLevel ==3}">中级</c:if> <c:if
										test="${seriousLevel ==4}">中高级</c:if> <c:if
										test="${seriousLevel ==5}">高级</c:if></td>
								<td valign="middle"><a id="<s:property value='influenceSys'/>" name="<s:property
											value='#warn.warnName' />"  onclick="lookingFor(this);"
									style= "text-decoration: none; cursor: pointer ;">点击查看</a>
								</td>
								<td valign="middle">
									<c:if test="${leakLevel ==1}">低级</c:if>
									<c:if test="${leakLevel ==2}">中级</c:if> <c:if
										test="${leakLevel ==3}">高级</c:if> 
								</td>
							</tr>
						</s:iterator>
						</tbody>
						<tr>
							<td colspan="8" width="100%"><jsp:include
									page="../commons/page.jsp"></jsp:include></td>
						</tr>
					</table>
				</div>
			</div>
		</div><input type="hidden" name="selWarnName" value="${selWarnName}" />
		<input type="hidden" name="selWarnType" value="${selWarnType}" />
		<input type="hidden" name="selSeriousLevel" value="${selSeriousLevel}" />
		</td></tr>
		</table>
	</s:form>
	
	
	
	
	<!-- warn query from -->
	<s:form action="query.action" namespace="/warn" method="post"
		theme="simple" id="queryForm" name="queryForm">
		<s:hidden name="selStatus" id="selStatus" />
		<s:hidden name="selWarnName" id="selWarnName" />
		<s:hidden name="selWarnType" id="selWarnType" />
		<s:hidden name="selSeriousLevel" id="selSeriousLevel" />
	</s:form>
	
	<div id="dialoginfo" title="预警资产信息">
	   <table width="100%" border="0" cellspacing="1" cellpadding="0"
						class="tab2" id="dialoginfoTable">
	       <tr style="background-color:#F0F8FF; font-weight: bold;" align="center" >
	          <td>资产id</td>
	          <td>资产名称</td>
	          <td>IP地址</td>
	       </tr>
	    </table>
	</div>
	
	<!-- over layer -->
	<div id="blk" style="display:none;">
		<div class="blk">
			<div class="head">
				<div class="head-right"></div>
			</div>
			<div class="main">
				<h2 id="blk_header">#header#</h2>
				<div id="blk_content">#content#</div>
			</div>
			<div class="foot">
				<div class="foot-right"></div>
			</div>
		</div>
	</div>
	<!-- ui-dialog -->
	<div id="dialog-extQuery" title="高级查询">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr>
				<td width="25%">预警名称:</td>
				<td><input id="WarnName" name="WarnName" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr>
			    <td>状态</td>
			    <td>
			      <select id="Status" style="width:255px;">
                     <option value="">===全部===</option>
			         <option value="50">未发布</option>
			         <option value="100">已发布</option>
			         <option value="20">已过期</option>
			         <option value="0">已消除</option>
			      </select>
			    </td>
			</tr>
			<tr>
				<td>类型:</td>
				<td>
				  <select id="WarnType" style="width: 255px">
				  	<option value="">===全部===</option>
				  	<option value="1">新漏洞</option>
				  	<option value="2">蠕虫</option>				  
				  </select>
				</td>
			</tr>
			<tr>
				<td>严重程度:</td>
				<td>
					<select id="SeriousLevel" style="width: 255px">
				  	<option value="">===全部===</option>
				  	<option value="1">低级</option>
				  	<option value="2">中低级</option>				  
				  	<option value="3">中级</option>				  
				  	<option value="4">中高级</option>				  
				  	<option value="5">高级</option>				  
				  </select>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
