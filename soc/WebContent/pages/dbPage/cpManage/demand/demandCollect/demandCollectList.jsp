<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>整改需求汇总列表</title>
<meta http-equiv="Cache-Control" content="private" />
<link href="${ctx}/styles/user/user.css" rel="stylesheet"	type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"	type="text/css">

<SCRIPT src="${ctx}/scripts/jquery-1.7.2.min.js"     type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/jquery-ui.custom.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/util.js"                 type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/popuplayer.js"           type=text/javascript></SCRIPT>
<script src="${ctx}/scripts/sorttalbe.js"            type="text/javascript"></script>
<style type="text/css">
     .biaoti{
           font-size: 12px ;
           font-weight: bold;
     }
</style>
<script type="text/javascript">
$(document).ready(function() {
		goSearch(0);
		});
//模糊快速搜索
function goSearch(num)
  {
	var keyword = $.trim($("#goToSearch").val());
	 var FK_CA = $.trim($("#FK_CA").val());
	 var CIA_AssessResult = $.trim($("#CIA_AssessResult").val());
	if(keyword != ''&& FK_CA!=""){
		jsonForAjax("${ctx}/demandCollet/queryAjaxDemand.action?startIndex="+encodeURI(num,"utf-8")+ "&keyword=" + encodeURI(encodeURI(keyword,"utf-8")) + "&t=" + new Date()+"&FK_CA="+FK_CA+"&CIA_AssessResult="+CIA_AssessResult);
	}else if(keyword == ''&& FK_CA!=""){
		jsonForAjax("${ctx}/demandCollet/queryNoKeyAjaxDemand.action?startIndex="+encodeURI(num,"utf-8")+ "&keyword=" + encodeURI(encodeURI(keyword,"utf-8")) + "&t=" + new Date()+"&FK_CA="+FK_CA+"&CIA_AssessResult="+CIA_AssessResult);
	} 
  };
  
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
	/* 数据分页 */
	function jsonForAjax(url) {
		var htmlStr = "";
		var htmlPage = "";
		$.getJSON(url,function(result) {
							$("#tbodyuse").html("");
							$.each(result.demandCollets,function(i, item) {
												var tBodyHtml = $("#tbodyuse").html();
												if (item.unitDomainName != null) {
													htmlStr = "";
													htmlStr += "<tr>";
													htmlStr += "<td align=\"left\">";
													htmlStr += item.unitDomainName;
													htmlStr += "</td>";
													htmlStr += "<td   align=\"left\">";
													htmlStr += item.unitName;
													htmlStr += "</td>";
              										  //遍历数组
	              										  	htmlStr += "<td  align=\"left\">";
															htmlStr += "<table style=\"font-size\:12\">";
	              										    for(var i=0; i<item.list.length; i++){
			                								     htmlStr += "<tr>";
													 			htmlStr += "<td style='font-size:12px;'>";
													  			htmlStr += item.list[i];
													 			htmlStr += "</td>";
																htmlStr += " </tr>";
	            										}
	            											htmlStr += "</table>";
															htmlStr += "</td>";
													htmlStr += "</tr>";
												}
												//$("#tbodyuse").setAttribute("style","font-size:12px;");
												$("#tbodyuse").html(tBodyHtml + htmlStr);
											});
			if (result.page.startIndex != null) {
				$("#tbodyuseTr").html("");
				/* htmlPage += "<tr>"; */
				htmlPage += "<td colspan=\"8\" width=\"100%\">";
				htmlPage += "<table width='100%' border='0' cellspacing='1' cellpadding='0' class='tab2' >";
				htmlPage += "<tr>";
				htmlPage += "<td>";
				htmlPage += "<table align='right'  style='font-size: 12px'>";
				htmlPage += "<tr>";
				htmlPage += "<td>";
				htmlPage += "共" + result.page.totalCount + "记录";
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
				htmlPage += " <input type='text' style='width: 30px' name='page' id='page'  size='3' onkeyup=\"this.value=this.value.replace(/[^\\d]/g,'')\">";
				htmlPage += "&nbsp;<input type='button' value='GO' class='btn1' onclick=" + "goToPage(" + result.page.lastIndex + ")" + ">";
				htmlPage += " 当前第" + result.page.currentPage + "/" + result.page.pageCount + "页";
				htmlPage += "</td>";
				htmlPage += "</tr>";
				htmlPage += "</table>";
				htmlPage += "</td>";
				htmlPage += "</tr>";
				htmlPage += "</table>";
				htmlPage += "</td>";
				/* htmlPage +="</tr>"; */
				$("#tbodyuseTr").html(htmlPage);
			}								
						});
	}; 
</script>


</head>

<body style="margin: 0px;position: 0;font-size:12px;padding-left:2px">

		<table width="99.5%" border="0" cellspacing="0" cellpadding="0"  style="font-size:12px;">
			<!-- 空行 -->
			<tr>
				<td height="2px">
				<input type="hidden" id="FK_CA" name="FK_CA" value="${FK_CA}">
				<input type="hidden" id="CIA_AssessResult" name="CIA_AssessResult" value="${CIA_AssessResult}">
				</td>
			</tr>
			<tr>
				<td>
					<div class="caozuobox">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" style="font-size:12px;">
							<tr height="30">
								<td width="1%">
								 
								</td>
								 <td width="30%" style="padding-left: -100px;" valign="middle" align="left">
									&nbsp;快速搜索&nbsp;&nbsp;<input type="text" name="searchSysName" id="goToSearch"> <img src="${ctx}/images/search.jpg" onclick="goSearch(0);" style="cursor: pointer" class="hand" />
								   </td>
								<td></td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="sbox">
						<div class="cont">
							<!-- information area -->
							<div id="dataList">
								<table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2" id="table-manageList"  style="font-size:12">
									<thead>
										 <tr height="28">
											<th width="10%" class="biaoti" onclick="sortAble('table-manageList', 0)" style="cursor:pointer">控制域</th>
											
											<th width="10%" class="biaoti" onclick="sortAble('table-manageList', 1)"   style="cursor:pointer">控制单元</th>
											<th width="*" class="biaoti">不符合项</th>
										</tr>
									 </thead>
									<tbody id="tbodyuse" style="font-size:12px;">
									</tbody>
									
									<tr id="tbodyuseTr" style="font-size:12px;"></tr>
									 </table>
							</div>
						</div>
					</div>
				</td>
			</tr>
		 </table>
	 </body>
</html>
