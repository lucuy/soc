<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>系统备案</title>
<meta http-equiv="Cache-Control" content="private" />
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet" type="text/css">
<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet"
	type="text/css"> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">

<script type="text/javascript" src="${ctx}/scripts/js/sortupdatedata.js"></script>
<script type="text/javascript" src="${ctx}/scripts/check.js"></script>

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.ui.dialog.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<style type="text/css">
     .biaoti{
           font-size: 12px ;
           font-weight: bold;
          
     }
     td{
     	font-size: 12px
     }


</style>
<script type="text/javascript">

	jQuery(document).ready(function()
	{
	$("#dialog-extQuery").dialog({
			autoOpen : false,
			minWidth : 600,
			minHeight : 300,
			buttons : {
				"确定[Enter]" : function() {
					extQuery(0);
				 $(this).dialog("close"); 
				},

				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
	$("#dialog-recordDocList").dialog({
		autoOpen: false,
		minWidth: 700,
		minHeight:400,
		buttons: {
			"关闭[Enter]": function() {								
				$(this).dialog("close"); 
			}	
	
		}
	});	
	});
	
	function closeDia(){
		$("#dialog-recordDocList").dialog("close");
	}
	
  	function recordDocListDlg(recordId) {
  	  	recordDocListSearch(recordId,0);
  		$("#dialog-recordDocList").dialog("open");
	}
	
	function recordDocListSearch(recordId,num) {
		recordDocListjsonForAjax("${ctx}/recordDocList/recordDocListSearch.action?recordId="+encodeURI(encodeURI(recordId,"UTF-8"))+"&t="+new Date());
   }
   
   function recordDocListjsonForAjax(url){
		var htmlStr = "";	
		$.getJSON(url ,function(result){
			$("#table-record2 tr:not(:first)").remove();
			$.each(result.processLog, function(i,item) {
			 	var rowNum = $("#table-record2 tr").length-1;
			 	if(item.recordDocListId != null){
			 		htmlStr = "";
			 		htmlStr += "<tr>";

			 			htmlStr += "<td  align=\"center\">";
			 				htmlStr += "<a href=\"${ctx}/recordDocList/recordLoad.action?recordDocListId="+encodeURI(encodeURI(item.recordDocListId,"utf-8"))+"&recordDocListDocmentName="+encodeURI(item.recordDocListDocmentName,"utf-8")+"\" onclick=\"closeDia()\" >"+(item.recordDocListDocmentName == null ? "" : item.recordDocListDocmentName)+"</a>";	
			 			htmlStr += "</td>";
			 			
			 			 htmlStr += "<td  align=\"center\">";
			 				htmlStr += (item.recordDocListProduceDate == null ? "" : item.recordDocListProduceDate);			 			
			 			htmlStr += "</td>"; 
			 			 htmlStr += "<td  align=\"center\">";
			 				htmlStr += (item.recordDocListDownDate == null ? "" : item.recordDocListDownDate);	
			 			htmlStr += "</td>"; 
			 			htmlStr += "<td  align=\"center\">";
			 				htmlStr += (item.recordDocListDownloads == null ? "" : item.recordDocListDownloads);	
			 			htmlStr += "</td>"; 
			 		htmlStr += "</tr>";
			 	}
			 	$(htmlStr).insertAfter($("#table-record2 tr:eq("+rowNum+")"));
			 });	
			 });
			 }			

	
	function createRecord(recordId){
		var btnSub1=document.getElementById("one"+recordId);
		var btnSub2=document.getElementById("two"+recordId);
		var btnSub3=document.getElementById("three"+recordId);
        btnSub1.disabled="disabled";
        btnSub2.disabled="disabled";
        btnSub3.disabled="disabled";
		window.location.href="${ctx}/record/createDoc.action?recordId="+recordId;
	}
	
	function down(){
		alert("down");
	}
	
	function record(recordId){
		var btnSub1=document.getElementById("one"+recordId);
		var btnSub2=document.getElementById("two"+recordId);
		var btnSub3=document.getElementById("three"+recordId);
        btnSub1.disabled="disabled";
        btnSub2.disabled="disabled";
        btnSub3.disabled="disabled";
		window.location.href="${ctx}/record/sysRecord.action?recordId="+recordId;
	
	}
	
 	$(document).ready(function() {
		goSearch(0);

	} 

	);
	$("#keyword").keydown(function(event) {
		if (event.keyCode == 13) {
			query();
		}
	});
	$("#chkAll").live("click", function(event) {
		if ($("#chkAll").hasClass('not_checked')) {
			$("#chkAll").removeClass('not_checked');
			$(".check-box").attr('checked', true);
		} else {
			$("#chkAll").addClass('not_checked');
			$(".check-box").attr('checked', false);
		}
	});

	function jsonForAjax(url) {
		var htmlStr = "";
		var htmlPage = "";
		var ShouDingvalue = document.getElementById("ShouDingId").value;
		$.getJSON(url, function(result) {
			$("#tbodyuse").html("<tr style=\"display:none\"></tr>");
			$.each(result.processLog, function(i, item) {

				var tBodyHtml = $("#tbodyuse").html();
				if (item.recordId != null) {
					htmlStr = "";
					htmlStr += "<tr>";
					/* htmlStr += "<td align=\"center\">";
						htmlStr += "<input name=\"ids\"  type=\"checkbox\" class=\"check-box\" value=\""+ item.id+"\"/>";
					htmlStr += "</td>"; */
					htmlStr += "<td  align=\"center\">";
					htmlStr += (item.sysInFoName == null ? "" : item.sysInFoName);
					htmlStr += "</td>";
					htmlStr += "<td  align=\"center\">";
					htmlStr += (item.sysInFoId == null ? "" : item.sysInFoId);
					htmlStr += "</td>";
					htmlStr += "<td  align=\"center\"><xmp>";
					htmlStr += (item.sysInFoBusDescription == null ? "" : item.sysInFoBusDescription);
					htmlStr += "</xmp></td>";
					htmlStr += "<td  align=\"center\">";
					htmlStr += (item.rankGrade == null ? "" : item.rankGrade);
					htmlStr += "</td>";
					htmlStr += "<td  align=\"center\">";
					
					if(ShouDingvalue==1){
						htmlStr += "<input type=\"button\" class=\"btnstyle1\"  value=\"生成新文档\" >";
					}else{
						htmlStr += "<input type=\"button\" class=\"btnstyle1\"  value=\"生成新文档\" id=\"one"+item.recordId+"\" onclick=\"createRecord('"+item.recordId+"')\">";
					}
					htmlStr += "</td>";
					htmlStr += "<td  align=\"center\">";
					
					if(ShouDingvalue==1){
						htmlStr += "<input type=\"button\" class=\"btnstyle1\"  value=\"下载备案文档\"  >";
					
					}else{
						htmlStr += "<input type=\"button\" class=\"btnstyle1\" id=\"two"+item.recordId+"\" value=\"下载备案文档\" onclick=\"recordDocListDlg('"+item.recordId+"')\" >";
						htmlStr += "<a href=\"${ctx}/sysRecord/recordLoad.action?id=" + item.id + "\">" + (item.docment == null ? "" : item.docment) + "</a>";
					}
					htmlStr += "</td>";
					htmlStr += "<td  align=\"center\">";
					if(ShouDingvalue==1){
						htmlStr += "<input type=\"button\" class=\"btnstyle\" value=\"系统备案\" >";
						}else{
						    if(item.recordDate != null){
						        htmlStr += "<input type=\"button\" class=\"btnstyle\" value=\"系统备案\" id=\"three"+item.recordId+"\" onclick=\"record('"+item.recordId+"')\">";
						    }else{
						        htmlStr += "<input type=\"button\" class=\"btnstyle\" disabled=\"disabled\" value=\"系统备案\" id=\"three"+item.recordId+"\" onclick=\"record('"+item.recordId+"')\">";
						    }
							
							}
						htmlStr += "</td>";
					htmlStr += "</tr>";

				}
				$("#tbodyuse").html(tBodyHtml + htmlStr);

			});
			if (result.page.startIndex != null) {

				$("#tbodyuseTr").html("");
				htmlPage += "<td colspan=\"7\" width=\"30%\">";
				htmlPage += "<table width='100%' border='0' cellspacing='1' cellpadding='0' class='tab2' >";
				htmlPage += "<tr>";
				htmlPage += "<td>";
				htmlPage += "<table align='right' style='font-size: 12px' >";
				htmlPage += "<tr>";
				htmlPage += "<td>";
				htmlPage += "共" + result.page.totalCount + "条记录";
				htmlPage += "<input type='hidden' name='startIndex' id='startIndex' value='0'>";
				htmlPage += "<input type='hidden' name='lastIndex' id='lastIndex' value="+ result.page.lastIndex + ">";
				htmlPage += "</td>";
				htmlPage += "<td>";
				if (result.page.startIndex != 0) {
					htmlPage += "<a href=" + "javascript:goSearch('0')" + " >首页</a>";
				} else {
					htmlPage += "首页";
				}
				htmlPage += "</td>";
				htmlPage += "<td>";
				if (result.page.startIndex != 0) {
					htmlPage += "<a href=" + "javascript:goSearch(" + result.page.previousIndex + ") " + " >上一页</a>";
				} else {
					htmlPage += "上一页";
				}
				htmlPage += "</td>";
				htmlPage += "<td>";
				if (result.page.nextIndex > result.page.startIndex) {
					htmlPage += "<a href=" + "javascript:goSearch(" + result.page.nextIndex + ")" + " >下一页</a>";
				} else {
					htmlPage += "下一页";
				}
				htmlPage += "</td>";
				htmlPage += "<td>";
				if (result.page.lastIndex == result.page.startIndex) {
					htmlPage += "末页";
				} else {
					htmlPage += "<a href=" + "javascript:goSearch(" + result.page.lastIndex + ")" + " >末页</a>";
				}
				htmlPage += "</td>";
				htmlPage += "<td>";
				htmlPage += " <input type='text' style='width: 30px' name='page' id='page'  size='3' onkeyup=\"this.value=this.value.replace(/[^\\d]/g,'')\"/>";
				htmlPage += "&nbsp;<input type='button' value='GO' class='btn1' onclick=" + "goToPage(" + result.page.lastIndex + ")" + ">";
				if(result.page.currentPage==0||result.page.pageCount){
					htmlPage +=" 当前第" + 1+ "/" + 1 + "页";
				}else{
				htmlPage +=" 当前第" + result.page.currentPage + "/" + result.page.pageCount + "页";
				}
				htmlPage += "</td>";
				htmlPage += "</tr>";
				htmlPage += "</table>";
				htmlPage += "</td>";
				htmlPage += "</tr>";
				htmlPage += "</table>";
				htmlPage += "</td>";
				$("#tbodyuseTr").html(htmlPage);

			}
		});
	}
	//高级搜索
	function extQuery(num) {
		var sysname = $.trim($("#hsysname").val());
		var sysid = $.trim($("#hsysid").val());
		var ranklevel = $.trim($("#hranklevel").val());
		 if(sysname.length>50){
			alert("搜索长度不能大于50");
			$('#hsysname').val('');
			$('#hsysname').focus();
			return ;
		}
		if(sysid.length>50){
			alert("搜索长度不能大于50");
			$('#hsysid').val('');
			$('#hsysid').focus();
			return ;
		}
		if(sysname=="" && sysid=="" && ranklevel=="0"){
		  return;
		}
		document.getElementById("goToSearch").value="";//设置快速搜索为空
		document.getElementById("flag").value="高级";//设置标志为高级搜索
		jsonForAjax("${ctx}/record/extqueryrecord.action?startIndex="
				+ encodeURI(num, "utf-8") + "&sysname="
				+ encodeURI(encodeURI(sysname, "utf-8")) + "&sysid="
				+ encodeURI(encodeURI(sysid, "utf-8")) + "&ranklevel="
				+ encodeURI(encodeURI(ranklevel, "utf-8"))
				+ "&t=" + new Date());
		
		
	}
  function extQueryDlg() {
		$("#dialog-extQuery").dialog("open");
	}
	function goToPage(lastnum) {
		var page = $("#page").val();
		var num = parseInt(page * 15) - parseInt(15);

		if (num > lastnum) {
			alert("错误页数");
			return false;
		}
		if (num < 0)
			num = 0;
		$("#startIndex").value = num;
		goSearch(num);
	}
	
	function goSearchBefore(){
	   document.getElementById("hsysname").value="";
	   document.getElementById("hsysid").value="";
	   document.getElementById("hranklevel").value="0";
	   goSearch(0);
	}
	function goSearch(num) {
	
		var keyword = document.getElementById("goToSearch").value;
		if(keyword.length>50){
			alert("搜索长度不能大于50");
			$('#goToSearch').val('');
			$('#goToSearch').focus();
			return ;
		}
		var flag=document.getElementById("flag");
		if(keyword!=""){
		    flag.value="快速";//设置标志为高级搜索
         }
           var hsysname=$.trim($("#hsysname").val());
           var hsysid=$.trim($("#hsysid").val());
           var hranklevel=$.trim($("#hranklevel").val());
          
         if(hsysname !="" || hsysid !="" || hranklevel !="0"){
            flag.value="高级";//设置标志为高级搜索
         }
         
        
         if((keyword=="")&&(hsysname == "" && hsysid ==""  && hranklevel =="0")){
             flag.value="无";
         }
         
         if(num ==0 && flag.value=="高级"){
               extQuery(num);
		       return ;
        }
		if (keyword != '' || num != '') {
			if(flag.value=="快速"){
		      jsonForAjax("${ctx}/record/jsonForAjax.action?keyword=" + encodeURI(encodeURI(keyword, "UTF-8")) + "&startIndex=" + encodeURI(num, "UTF-8") + "&t=" + new Date());
		       return ;
		    }
		    if(flag.value=="高级"){
		       extQuery(num);
		       return ;
		    }
		    jsonForAjax("${ctx}/record/jsonForAjax.action?keyword=" + encodeURI(encodeURI(keyword, "UTF-8")) + "&startIndex=" + encodeURI(num, "UTF-8") + "&t=" + new Date());
			
		} else {
			jsonForAjax("${ctx}/record/jsonForAjax.action?keyword=" + encodeURI(encodeURI(keyword, "UTF-8")) + "&t=" + new Date());
		}
	}
</script>
</head>

<body>
<input type="hidden" id="ShouDingId" value='<%=session.getAttribute("SSI_LOGIN_Status")%>'/>
<input type="hidden" id="flag" value="无"/>
	<form id="systeminfo" name="cpSystemInfoForm" method="post" action="query.action" namespace="sysRecord">

		<input type="hidden" name="sysName" value="${sysName}">
		<table width="99.5%" border="0" cellspacing="0" cellpadding="0" >
			<tr>
				<td>
					<div class="caozuobox" style="height:100%; fongt-size:12px;">
					&nbsp;
					<s:if test='#session.SSI_LOGIN_Status=="1"'>
				        <input type="hidden" id="goToSearch" name="searchSysName" onblur="yanzheng1(this)"> &nbsp;
						<a href="#"	onclick="extQueryDlg();"></a> 
					</s:if>
					<s:else>
					<span class="kuaiju" style="font-size:12px;">&nbsp;快速搜索&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="goToSearch" name="searchSysName" onblur="yanzheng1(this)"> </span> 
						<img src="${ctx}/images/search.jpg" onclick="goSearchBefore();" style="margin-left: 5px; cursor: pointer;vertical-align: middle;" />&nbsp;
						<a href="#"	onclick="extQueryDlg();" style="font-size:12px;">高级</a> 
					</s:else>
					</div>
					
				</td>
			</tr>
			<tr>
				<td>
					<div class="sbox" style="font-size: 12px;">
						<div class="cont">
							<!-- information area -->
							<div id="dataList">
								<table width="100%" border="0" cellspacing="1" cellpadding="0" id="table-record1" class="tab2">
									<thead>
										<tr height="28">
											<td width="15%" class="biaoti" onclick="sortAble('table-record1',0)" style="cursor:pointer">系统名称</td>
											<td width="15%" class="biaoti" onclick="sortAble('table-record1',1)" style="cursor:pointer">系统编号</td>
											<td width="10%" class="biaoti" onclick="sortAble('table-record1',2)" style="cursor:pointer">业务描述</td>
											<td width="15%" class="biaoti" onclick="sortAble('table-record1',3)" style="cursor:pointer">当前等级</td>
											<td width="15%" class="biaoti" >生成新备案文档</td>
											<td width="15%" class="biaoti" >已备案文档</td>
											<td width="15%" class="biaoti" >系统备案</td>
										
										</tr>
									</thead>

									<tbody id="tbodyuse">

									</tbody>
									<tr id="tbodyuseTr"></tr>

								</table>
							</div>
							<!-- information area -->
						</div>
					</div></td>
			</tr>
		</table>
		<!-- ui-dialog-employee -->
	<div id="dialog-recordDocList" title="备案文档下载">
		<table width="97%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2" id="table-record2">
						<tr >
							<td width="50%" align="center" class="biaoti">备案文档名</td>
							<td width="20%" align="center" class="biaoti">生成时间</td>
							<td width="20%" align="center" class="biaoti">下载时间</td>
							<td width="10%" align="center" class="biaoti">下载次数</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>
	
	<input type="hidden" id="recordId">
	</form>
<!-- ui-dialog -->
	<div id="dialog-extQuery" title="系统备案高级查询">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px; font-size:12px;">
			<tr>
				<td width="25%">系统名称:</td>
				<td><input id="hsysname" name="hsysname" type="text"
					onkeyup="value=value.replace(/[^\w\.\/\u4E00-\u9FA5]/ig,'')"
					style="width:250px;"
					onkeypress="if

(event.keyCode==13)extQuery();" /></td>
			</tr>
			<tr>
				<td width="25%">系统编号：</td>
				<td><input id="hsysid" name="hsysid" type="text"
					style="width:250px;"
					onkeyup="value=value.replace(/[^\w\.\/\u4E00-\u9FA5]/ig,'')"
					onkeypress="if(event.keyCode==13)extQuery();" /></td>
			</tr>
			<tr>
				<td width="25%">当前等级：</td>
				<td><select name="hranklevel" id="hranklevel">
												<option value="0" >
													请选择
												</option>
												<option value="第二级">
													第二级
												</option>
												<option value="第三级">
													第三级
												</option>
												<option value="第四级">
													第四级
												</option>
												
											</select></td>
			</tr>
			

		</table>
	</div>


</body>
</html>
