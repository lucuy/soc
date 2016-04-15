<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>系统备案历史</title>
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
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/js/sortupdatedata.js"></script>
<script type="text/javascript" src="${ctx}/scripts/check.js"></script>
<SCRIPT src="${ctx}/scripts/Calendar.js" type=text/javascript></SCRIPT>
<script type='text/javascript'
	src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
     .biaoti{
           font-size: 12px ;
           font-weight: bold;
          
     }
</style>
<script type="text/javascript">

	 $(document).ready(function()
  {
  		$("#dialog-extQuery").dialog({
			autoOpen : false,
			minWidth : 600,
			minHeight : 360,
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
  		goSearch(0);
  	
  	}
  
  );
  function extQueryDlg() {
		$("#dialog-extQuery").dialog("open");
	}
  //高级搜索
	function extQuery(num) {
		var sysname = $.trim($("#hsysname").val());
		var ranklevel = $.trim($("#hlevel").val());
		var docname = $.trim($("#hdocname").val());
		var rankdate = $.trim($("#hdate").val());
		if(sysname.length>50){
			alert("搜索长度不能大于50");
			$('#hsysname').val('');
			$('#hsysname').focus();
			return ;
		}
		if(docname.length>50){
			alert("搜索长度不能大于50");
			$('#hdocname').val('');
			$('#hdocname').focus();
			return ;
		}
		if(sysname=="" && ranklevel=="0" && docname=="" && rankdate==""){
		  return;
		}
		document.getElementById("goToSearch").value="";//设置快速搜索为空
		document.getElementById("flag").value="高级";//设置标志为高级搜索
		jsonForAjax("${ctx}/recordHistory/extqueryhistory.action?startIndex="
				+ encodeURI(num, "utf-8") + "&sysname="
				+ encodeURI(encodeURI(sysname, "utf-8")) + "&ranklevel="
				+ encodeURI(encodeURI(ranklevel, "utf-8")) + "&docname="
				+ encodeURI(encodeURI(docname, "utf-8"))
				+ "&recorddate=" + encodeURI(encodeURI(rankdate, "utf-8"))
				+ "&t=" + new Date());
		
	}
  $("#keyword").keydown(function(event) {
			if (event.keyCode == 13)
			 {
				query();
				}
		}	
		);
  
		$("#chkAll").live("click",function(event) {
		if($("#chkAll").hasClass('not_checked')) {
			$("#chkAll").removeClass('not_checked');
			$(".check-box").attr('checked',true);
		} else {
			$("#chkAll").addClass('not_checked');
			$(".check-box").attr('checked',false);
		}
	});

function jsonForAjax(url){

		var htmlStr = "";	
		var htmlPage = "";
		var ShouDingvalue = document.getElementById("ShouDingId").value;
		$.getJSON(url ,function(result){
		$("#tbodyuse").html("<tr style=\"display:none\"></tr>");
			$.each(result.processLog, function(i,item) {
			 	//var rowNum = $("#table-record tr").length-1;
			var tBodyHtml = $("#tbodyuse").html();
			 	if(item.recordHistoryId != null){

			 		htmlStr = "";
			 		htmlStr += "<tr style='font-size:12px;'>";
			 		/* 	htmlStr += "<td align=\"center\">";
			 				htmlStr += "<input name=\"ids\"  type=\"checkbox\" class=\"check-box\" value=\""+ item.id+"\"/>";
			 			htmlStr += "</td>"; */
			 			
			 			htmlStr += "<td align=\"center\">";
			 				htmlStr += (item.sysInFoName == null ? "" : item.sysInFoName);
			 			htmlStr += "</td>";
			 			 htmlStr += "<td  align=\"center\">";
			 				htmlStr += (item.rankGrade == null ? "" : item.rankGrade);			 			
			 			 htmlStr += "</td>"; 
			 			 
			 			  htmlStr += "<td  align=\"center\">";
			 			  if(ShouDingvalue==1){
								htmlStr += (item.recordHistoryDocument == null ? "" : item.recordHistoryDocument);	
								}else{
							htmlStr += "<a href=\"${ctx}/recordHistory/recordLoad.action?recordHistoryId="+encodeURI(item.recordHistoryId,"utf-8")+"&recordHistoryDocument="+encodeURI(item.recordHistoryDocument,"utf-8")+"\">"+(item.recordHistoryDocument == null ? "" : item.recordHistoryDocument)+"</a>";	
			 			
									}
			 				htmlStr += "</td>";
			 		
			 			htmlStr += "<td  align=\"center\">";
			 				htmlStr += (item.recordHistoryDate == null ? "" : item.recordHistoryDate);	
			 			htmlStr += "</td>";
			 		htmlStr += "</tr>";
			 	}
			 	$("#tbodyuse").html(tBodyHtml+htmlStr);
			 	//$(htmlStr).insertAfter($("#table-record tr:eq("+rowNum+")"));
			 });				
			if(result.page.startIndex != null){		
				//rowNum =  $("#table-record tr").size()-1;
			    $("#tbodyuseTr").html("");
			    	htmlPage +="<td colspan=\"5\" width=\"30%\">";
					htmlPage +="<table width='100%' border='0' cellspacing='1' cellpadding='0' class='tab2' >";
					htmlPage +="<tr>";
						htmlPage +="<td>";
							htmlPage +="<table align='right' style='font-size: 12px' >";
								htmlPage +="<tr>";
									htmlPage +="<td>";
									htmlPage +="共"+ result.page.totalCount +"条记录";
									htmlPage +="<input type='hidden' name='startIndex' id='startIndex' value='0'>";
									htmlPage +="<input type='hidden' name='lastIndex' id='lastIndex' value="+ result.page.lastIndex + ">";
									htmlPage +="</td>";
									htmlPage +="<td>";
									if(result.page.startIndex != 0){
										htmlPage +="<a href=" +"javascript:goSearch('0')"+" >首页</a>";
									}else{
										htmlPage +="首页";
									}																							
									htmlPage +="</td>";
									htmlPage +="<td>";
									if(result.page.startIndex != 0){
										htmlPage +="<a href=" + "javascript:goSearch(" + result.page.previousIndex + ") "+ " >上一页</a>";
									}else{
										htmlPage +="上一页";
									}
									htmlPage +="</td>";
									htmlPage +="<td>";
									if(result.page.nextIndex > result.page.startIndex){
										htmlPage +="<a href=" + "javascript:goSearch(" + result.page.nextIndex + ")" + " >下一页</a>";
									}else{
										htmlPage +="下一页";
									}	
									htmlPage +="</td>";
									htmlPage +="<td>";
									if(result.page.lastIndex == result.page.startIndex){
										htmlPage +="末页";
									}else{
										htmlPage +="<a href=" + "javascript:goSearch(" + result.page.lastIndex + ")" + " >末页</a>";
									}									
									htmlPage +="</td>";
									htmlPage +="<td>";
									htmlPage +=" <input type='text' style='width: 30px' name='page' id='page'  size='3' onkeyup=\"this.value=this.value.replace(/[^\\d]/g,'')\">";
									htmlPage +="&nbsp;<input type='button' value='GO' class='btn1' onclick=" + "goToPage(" + result.page.lastIndex + ")" + ">";
									if(result.page.currentPage==0||result.page.pageCount){
										htmlPage +=" 当前第" + 1+ "/" + 1 + "页";
									}else{
									htmlPage +=" 当前第" + result.page.currentPage + "/" + result.page.pageCount + "页";
									}
									htmlPage +="</td>";
								htmlPage +="</tr>";
							htmlPage +="</table>";
						htmlPage +="</td>";
					htmlPage +="</tr>";
				htmlPage +="</table>";
				htmlPage +="</td>";
			$("#tbodyuseTr").html(htmlPage);
			//$(htmlPage).insertAfter($("#table-record tr:eq("+rowNum+")"));	
			}		
		});
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
	   document.getElementById("hlevel").value="0";
	   document.getElementById("hdocname").value="";
	   document.getElementById("hdate").value="";
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
           var hlevel=$.trim($("#hlevel").val());
           var hdocname=$.trim($("#hdocname").val());
           var hdate=$.trim($("#hdate").val());
         if(hsysname !="" || hlevel !="0" || hdocname !="" || hdate !=""){
            flag.value="高级";//设置标志为高级搜索
         }
        
         if((keyword=="")&&(hsysname == "" && hlevel =="0"  && hdocname =="" &&  hdate =="")){
             flag.value="无";
         }
         
           if(num ==0 && flag.value=="高级"){
               extQuery(num);
		       return ;
        }
	if (keyword != '' || num != '') {
		if(flag.value=="快速"){
		     jsonForAjax("${ctx}/recordHistory/jsonForAjax.action?keyword="+encodeURI(encodeURI(keyword,"UTF-8"))+"&startIndex="+encodeURI(num,"UTF-8")+"&t="+new Date());
		       return ;
		    }
		    if(flag.value=="高级"){
		       extQuery(num);
		       return ;
		    }
		     jsonForAjax("${ctx}/recordHistory/jsonForAjax.action?keyword="+encodeURI(encodeURI(keyword,"UTF-8"))+"&startIndex="+encodeURI(num,"UTF-8")+"&t="+new Date());
	} else {
		jsonForAjax("${ctx}/recordHistory/jsonForAjax.action?keyword="+encodeURI(encodeURI(keyword,"UTF-8"))+"&t="+ new Date());
	}
}

</script>

</head>

<body>

<input type="hidden" id="ShouDingId" value='<%=session.getAttribute("SSI_LOGIN_Status")%>'/>
<input type="hidden" id="flag" value="无"/>
	<form id="systeminfo" action="query.action" namespace="sysRecord">
		<table width="99.5%" border="0" cellspacing="0" cellpadding="0" >
			<!-- 空行 -->

			<tr>
				<td >
					<div class="caozuobox" style="height:100%; font-size:12px;" >
						<s:if test='#session.SSI_LOGIN_Status=="1"'>
					      &nbsp;<span class="kuaiju"><input type="hidden" name="searchSysName" id="goToSearch"  onblur="yanzheng1(this)"> </span> &nbsp;<span style="margin-left:24px"></span>
					      <a href="#"	onclick="extQueryDlg();"></a> 
					</s:if>
					<s:else>
					&nbsp;<span class="kuaiju" style="font-size:12px;">&nbsp;快速搜索&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="searchSysName" id="goToSearch"  onblur="yanzheng1(this)" onkeyup="value=value.replace(/[^\w\.\/\u4E00-\u9FA5]/ig,'')"> </span> <img src="${ctx}/images/search.jpg" onclick="goSearchBefore()"  style="margin-left: 5px; cursor: pointer;vertical-align: middle;"/>&nbsp;
					<a href="#"	onclick="extQueryDlg();"style="font-size:12px;">高级</a> 
					</s:else>	
					</div></td>


			</tr>
			<tr>
				<td>
					<div class="sbox">
						<div class="cont">
							<!-- information area -->
							<div id="dataList">
								<table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2" id="table-record">
									<thead>
										<tr height="28">

											<td width="17%" class="biaoti" onclick="sortAble('table-record',0)">系统名称</td>
											<td width="15%" class="biaoti" onclick="sortAble('table-record',1)">安全等级</td>
											<td width="15%" class="biaoti" onclick="sortAble('table-record',2)">备案文件名</td>
											<td width="15%" class="biaoti" onclick="sortAble('table-record',3)">备案日期</td>
										</tr>
									</thead>
									<tbody id="tbodyuse">

									</tbody>
									<tr id="tbodyuseTr"></tr>

								</table>
							</div>
							<!-- information area -->
						</div>
					</div>
				</td>
			</tr>
		</table>

	</form>

<!-- ui-dialog -->
	<div id="dialog-extQuery" title="备案历史高级查询" >
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px; font-size:12px;">
			<tr>
				<td width="25%">系统名称:</td>
				<td><input id="hsysname" name="hsysname" type="text"
					style="width:250px;"
					onkeyup="value=value.replace(/[^\w\.\/\u4E00-\u9FA5]/ig,'')"
					onkeypress="if

(event.keyCode==13)extQuery();" /></td>
			</tr>
			
			<tr>
				<td width="25%">安全等级：</td>
				<td><select name="hlevel" id="hlevel">
												<option value="0">
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
			<tr>
				<td width="25%">备案文档名称：</td>
				<td><input id="hdocname" name="hdocname" type="text"
					style="width:250px;"
					onkeyup="value=value.replace(/[^\w\.\/\u4E00-\u9FA5]/ig,'')"
					onkeypress="if(event.keyCode==13)extQuery();" /></td>
			</tr>
			<tr>
				<td width="25%">备案日期:</td>
				<td>
<input id="hdate" name="hdate" type="text"
					style="width:250px;"
					readonly="readonly"  onclick="WdatePicker();" /></td>
			</tr>

		</table>
	</div>


</body>
</html>
