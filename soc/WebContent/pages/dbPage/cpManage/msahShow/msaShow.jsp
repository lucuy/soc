<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>通用物理整改需求汇总</title>
    <meta http-equiv="Cache-Control" content="private" />
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet" type="text/css">
<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet"
	type="text/css"> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.ui.dialog.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'  src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/sorttalbe.js"></script>
<style type="text/css">
     .biaoti{
           font-size: 12px ;
           font-weight: bold;
          
     }
</style>
<script type="text/javascript">
     
     

$(document).ready(function() {
		var msaDateFirst=$.trim($("#msaDateFirst").val());
		var typeFirst=$.trim($("#typeFirst").val());
		if(msaDateFirst!=""){
		   document.getElementById("msaDate").value=msaDateFirst;
		   document.getElementById("type").value=typeFirst;
		   document.getElementById("msaDateFirst").value="";
		   document.getElementById("typeFirst").value="";
		}
		goSearch(0);
		});
		
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
     //模糊快速搜索
function goSearch(num)
  {
	 
	var keyword = $.trim($("#goToSearch").val());
	 var msaDate=$.trim($("#msaDate").val());
	 var type=$.trim($("#type").val());
	 if(keyword != ''){
		jsonForAjax("${ctx}/msaShow/queryAjaxMsaShowNoFit.action?msaDate="+msaDate+"&type="+type+"&startIndex="+encodeURI(num,"utf-8")+ "&keyword=" + encodeURI(encodeURI(keyword,"utf-8"))+"&data="+new Date);
	}else if(keyword == ''){
			jsonForAjax("${ctx}/msaShow/queryNotKeyAjaxMsaShowNoFit.action?msaDate="+msaDate+"&type="+type+"&startIndex="+encodeURI(num,"utf-8")+"&data="+new Date );
	} 
  }
	/* 数据分页 */
	function jsonForAjax(url) {
		var htmlStr = "";
		var htmlPage = "";
		$.getJSON(url,function(result) {
							$("#tbodyuse").html("");
							//$("#tbodyuser").setAttribute("style","font-size:12px;");
							$.each(result.mapMsaShows,function(i, item) {
												var tBodyHtml = $("#tbodyuse").html();
												htmlStr = "";
												htmlStr += "<tr>";
												htmlStr += "<td style=\"cursor:pointer;text-align:left\">";
												htmlStr +=item.g;
												htmlStr += "</td>";
												htmlStr += "<td style=\"cursor:pointer;text-align:left\">";
												htmlStr += item.f;
												htmlStr +="</td>";
												htmlStr += "<td style=\"cursor:pointer;text-align:left\">";
												htmlStr +=item.s;
												htmlStr +="</td>";
												htmlStr += "</tr>";
												
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
  
  <body  style="margin: 0;position: 0;padding-left:2px">
		<table width="99.5%" border="0" cellspacing="0" cellpadding="0"  >
			<tr>
				<td>
					<div class="caozuobox"  style="font-size:12px;">
						<input type="hidden" name="file" id="file"> 
						<span class="kuaiju">&nbsp;快速搜索&nbsp;&nbsp; <input type="text" id="goToSearch" name="keyword"> </span> 
						<img src="${ctx}/images/search.jpg" onclick="goSearch(0)" style="margin-left: 5px; cursor: pointer" class="hand" /> 
					</div> 
				</td>

			</tr>
			<tr>
				<td>
					<div class="sbox">
						<div class="cont">
							<!-- information area -->
							<div id="dataList">
								<table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2" id="table-userManage" style="font-size:12px;">
								<thead>
									<tr >				 
										<th width="10%" class="biaoti" onclick="sortAble('table-userManage', 0)"  style="cursor:pointer" >控制域</th>
										<th width="10%" class="biaoti" onclick="sortAble('table-userManage', 1)"  style="cursor:pointer" >控制单元</th>
										<th width="*"   class="biaoti" onclick="sortAble('table-userManage', 2)"  style="cursor:pointer" >不符合项</th>
									</tr>
									</thead>
									<tbody id="tbodyuse">
									 </tbody>
									 <tr id="tbodyuseTr"></tr>
								</table>
							</div>

						</div>
					</div>
				</td>
			</tr>
		</table>
    <input type="hidden" name="msaDate" id="msaDate"  value="<s:property value="#parameters.msaDate"/>">
    <input type="hidden" name="type" id="type" value="<s:property value="#parameters.type"/>">
    <input type="hidden" id="msaDateFirst"  value="<s:property value="#request.msaDateFirst"/>">
    <input type="hidden" id="typeFirst"     value="<s:property value="#request.typeFirst"/>">
  </body>
</html>
