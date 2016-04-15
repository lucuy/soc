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
 
<script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>

<meta http-equiv="Expires" content="0">
<meta http-equiv="kiben" content="no-cache">
<link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${ctx}/styles/zTree/zTreeStyle.css" type="text/css">
	<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<%--ztree js--%>
	<script type="text/javascript" src="${ctx}/scripts/zTree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/zTree/jquery.ztree.excheck-3.5.js"></script>
	<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
	<script type="text/javascript"
	src="${ctx}/scripts/jquery-ui-1.8.16.custom.min.js"></script>
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
    type="text/css">
<%--ztree js结束--%>
	<script type='text/javascript'
	src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
	<%--ztree css--%>

		<style type="text/css"><%--
.ztree li span.button.pIcon01_ico_open{ background: url(/soc/styles/zTree/img/diy/2.png) no-repeat scroll 0 0 transparent;}
.ztree li span.button.pIcon01_ico_close{background: url(/soc/styles/zTree/img/diy/1.png) no-repeat scroll 0 0 transparent; }

	--%>
		*{font-size:12px;}
	.ztree li span.button.icon01_ico_docu{ background: url(/soc/styles/zTree/img/diy/1.png) no-repeat scroll 0 0 transparent;}
	.ztree li span.button.icon02_ico_docu{ background: url(/soc/styles/zTree/img/diy/3.png) no-repeat scroll 0 0 transparent;}
	</style>
	<%--ztree css结束--%>
	<SCRIPT type="text/javascript">
		
		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		var zNodes =[
			${htmlBuffer}
		];
		
		var code;
		
		function setCheck() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			py =  "p",
			sy = "s",
			pn = "p",
			sn = "s",
			type = { "Y":py + sy, "N":pn + sn};
			zTree.setting.check.chkboxType = type;
			
			showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
		}
		function showCode(str) {
			if (!code) code = $("#code");
			code.empty();
			code.append("<li>"+str+"</li>");
		}
		
		$(document).ready(function(){
			
			  $('#dialog-dataEventCategory').dialog({
					autoOpen: false,
					width: 300,
					height:350,
					buttons: {
						"确定[Enter]": function() {
							addOk('category','dialog-dataEventCategory');
						},
						"取消[Esc]": function() {
							$(this).dialog("close");
						}
					}
				});
			
			
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			setCheck();
			$("#py").bind("change", setCheck);
			$("#sy").bind("change", setCheck);
			$("#pn").bind("change", setCheck);
			$("#sn").bind("change", setCheck);
			
		});
		//-->
	
		function subData(){
			var ids = "";
			var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");    //获得树对象
			var nodes = zTreeObj.getCheckedNodes(true);
			var node ;
		    for(var j=0;j<nodes.length;j++){
		    	var node = nodes[j];
		    	if(node.iconSkin == 'icon02'){
		    		ids = ids+node.assetId+",";
		    	}
		   }
			var beginTime = $("#beginTime").val();
			var endTime = $("#endTime").val();
			if((beginTime == "" && endTime == "") ||(beginTime != "" && endTime == "") ||(beginTime == "" && endTime != ""))
		      {
		         $("#timeEror").html("请输入完整");
		         return false;
		      }
		      if(beginTime != "" && endTime != "")
		      {
		        var dateTime = new Date();
		        var year = dateTime.getFullYear();
		        var month = dateTime.getMonth()+1;
		        var date = dateTime.getDate();
		        var dayTime = year+"/"+month+"/"+date;
		        if(Date.parse(splitDate(beginTime)) > Date.parse(splitDate(endTime)))
		        {
		           $("#timeEror").html("请输入时间段应从小到大");
		           return false;
		        }
		        else if(Date.parse(splitDate(beginTime)) > Date.parse(dayTime))
		        {
		            $("#beginTime").val(year+"-"+month+"-"+date);
		            $("#timeEror").html("最小日期不能超过今日");
		            return false;
		        }
		        else if(Date.parse(splitDate(endTime)) > Date.parse(dayTime))
		        {
		            $("#endTime").val(year+"-"+month+"-"+date);
		            $("#timeEror").html("最大日期不能超过今日");
		            return false;
		        }
		      }
		      if(ids == ""){
		    	  alert("请选择资产或资产组！");
		    	  return;
		      }
		      //处理时间类型
		      var  categoryIds = "";
		  	$("#category").children("option").each(
					function() {
						if ($(this).parent("select").size() > 0) {
							categoryIds = categoryIds+"'"+ $(this).val()+"',";
						}
					});
 parent.mainFrame.location.href ="${ctx}/auditReport/queryReportByAuditReportId"+
 ".action?auditReportId=6&customs=1&assetIds="+ids+"&beginTime="+beginTime+"&endTime="+endTime+"&categoryIds="+categoryIds;
		}
		   //格式化日期
		  function splitDate(date)
		    {
		        var dates = date.split("-");
		        var time = dates[0]+"/"+dates[1]+"/"+dates[2];
		        return time;
		    }
		  function addCategroyDialog()
		    {
		       $('#dialog-dataEventCategory').dialog('open');
		       $('#dialog-dataEventCategory').focus();
		    }  
		//点击链接选择他左面的box
		  function checkLeftBox(id){
//		  	alert(id);
		  	if ($("#"+id).attr("checked") == false) {	
		  	$("#"+id).attr("checked",true);	
		  	}else{
		  	$("#"+id).attr("checked",false);	
		  		}
		  	}
	</SCRIPT>

</head>

<body style="margin-top: 2px">
<div class="r2titler"  style = "margin-left: 4px;width:97.3%;margin-bottom:0px;height:8%">自定义查询条件</div>
	<table width="99.4%" border="0" cellspacing="0" cellpadding="0"
		style="margin-left: 3px;" >
		<tr>
			<td width="49%" valign="top">
				<div class="framDiv">
					<!--  左侧table-->
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td class="r2titler">资产(组)选择</td>
						</tr>
						<tr>
							<td align="left">


	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>


							</td>
			
						</tr>

					</table>
				</div>
			</td>
			
			
			
			
			
			
			<td width="49%" valign="top">
				<div class="framDiv">
					<!--  左侧table-->
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
					
					
					
					
						<tr>
							<td class="r2titler" colspan="2">时间选择</td>
						</tr>
						<tr>
											<td class="td_detail_separator"></td>
										</tr>
					
						<tr>
						<td class="row" style="width:90px" align="right"><span style="position:relative; left:10px;">时间：&nbsp;&nbsp;&nbsp;&nbsp;</span>
								</td>
							<td align="left" >
<span class="display" id="custom"> <input
										id="beginTime" onclick="WdatePicker()" name="beginTime"
										class="Wdate" readonly="readonly"
										style="width:113px; height:19px;">&nbsp;至 <input
										id="endTime" onclick="WdatePicker()" name="endTime"
										class="Wdate" readonly="readonly"
										style="width:113px; height:19px;">&nbsp;<font
										color="red" id="timeEror"></font> &nbsp;&nbsp;  </span>


							</td>
			
						</tr>
						<tr>
								<td class="row" style="width:90px" align="right"><span style="position:relative; top:-10px;left:10px;">事件类型：&nbsp;&nbsp;&nbsp;&nbsp;</span>
								</td>
								</td>
								<td style="padding:5px 0px 5px 0px;"><span> <select
										id="category" name="category" style="width:270px;" size="3"></select> </span> <br />
									<div style="height: 5px;"></div>
										<span> 
											<input type="button" class="btnadd" onclick="addCategroyDialog();defualtSeleted('category');" />
											<input type="button" class="btndel" onclick="delOption('category','chk-eventcategroy');" />
										</span>
									</td>
							</tr>
					<tr>	
											<td colspan="2"><input type="button" class="btnyh" 
					id="btnSave" value="查   询" onclick="subData();" 	style="float:right"/> </td>
										</tr>				
										
										
					</table>
				</div>
			</td>
			
			
			
			
			
			
			
			
			
					</tr>
	</table>
<div id="dialog-dataEventCategory" title="事件类别" >
		 <table width='96%' cellspacing="0" cellpadding="0" border="0" align="center">
		   <s:iterator value='eventCategoryTagList' status='stat'>
				<tr style="line-height: 25px;">
					<td width="20%" align="center"><input type="checkbox"
						name="chk-eventcategroy" id="chk-category-${eventcategorykey}"
						value="${eventcategorykey}" /></td>
					<td width="80%"><a style='color:#555555'
						href="javascript:void(0);"
						onclick="checkLeftBox('chk-category-${eventcategorykey}')">${eventcategoryValue}</a>
					</td>
				</tr>
			</s:iterator>
		 </table>
	</div></body>
</html>
