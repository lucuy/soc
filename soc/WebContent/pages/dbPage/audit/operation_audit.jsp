<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>审计管理</title>
<meta http-equiv="Cache-Control" content="private" />
<LINK href="${ctx}/css/basic/css.css" type=text/css rel=stylesheet>
<LINK href="${ctx}/css/basic/basic.css" type=text/css rel=stylesheet>
<LINK href="${ctx}/css/style/jquery-ui.custom.css" type=text/css rel=stylesheet>
<LINK href="${ctx}/css/style/jquery.popuplayer.css" type=text/css rel=stylesheet>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.ui.dialog.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/check.js"></script>
<script type="text/javascript" src="${ctx}/scripts/js/sortupdatedata.js"></script>
 <SCRIPT src="${ctx}/scripts/Calendar.js" type=text/javascript></SCRIPT>
<script type="text/javascript">
	var resourceNum;
	var totCount;
	$(document).ready(function() {
	$("#dialog-extQuery").dialog({
			autoOpen : false,
			minWidth : 600,
			minHeight : 460,
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
		$("#keyword").keydown(function(event) {
			if (event.keyCode == 13) {
				query();
			}
		});

		 
		
	});

 
	//高级搜索
	function extQuery(num) {
		var audit_name = $.trim($("#UerName").val());
		var audit_object = $.trim($("#auditObject").val());
		var audit_detailed = $.trim($("#auditDetailed").val());
		var audit_type = $.trim($("#auditType").val());
		var audit_time = $.trim($("#ayditTime").val());
	     if(audit_name=="" && audit_object=="" && audit_detailed==""  && audit_type=="0"  && audit_time==""){
		   return;
		 }
		document.getElementById("goToSearch").value="";//设置快速搜索为空
		document.getElementById("flag").value="高级";//设置标志为高级搜索
		jsonForAjax("${ctx}/audit/auditGQueryAjax.action?startIndex="
				+ encodeURI(num, "utf-8") + "&audit_name="
				+ encodeURI(encodeURI(audit_name, "utf-8")) + "&audit_object="
				+ encodeURI(encodeURI(audit_object, "utf-8")) + "&audit_detailed="
				+ encodeURI(encodeURI(audit_detailed, "utf-8")) + "&audit_type="
				+ encodeURI(encodeURI(audit_type, "utf-8")) + "&audit_time="
				+ encodeURI(encodeURI(audit_time, "utf-8")) 
				+ "&t=" + new Date());
	}
 
 function goSearchBefor(){
	   document.getElementById("UerName").value="";
	   document.getElementById("auditObject").value="";
	   document.getElementById("auditDetailed").value="";
	   document.getElementById("auditType").value="";
	   document.getElementById("ayditTime").value="";
	   goSearch(0);
	}
	//快速搜索
	function goSearch(num) {
		resetCheckBox();
		var keyword = $.trim($("#goToSearch").val());
		
		if(keyword!=""){
		    flag.value="快速";//设置标志为高级搜索
         }
           var UerName=$.trim($("#UerName").val());
           var auditObject=$.trim($("#auditObject").val());
           var auditDetailed=$.trim($("#auditDetailed").val());
           var auditType=$.trim($("#auditType").val());
           var ayditTime=$.trim($("#ayditTime").val());
         if(UerName !="" || auditObject !="" || auditDetailed !="" || auditType !="" || ayditTime !=""){
            flag.value="高级";//设置标志为高级搜索
         }
        
         if((keyword=="")&&(UerName == "" && auditObject ==""  && auditDetailed ==""  && auditType==""  && ayditTime=="")){
             flag.value="无";
         }
        if(num ==0 && flag.value=="高级"){
               extQuery(num);
		       return ;
        }

		if (keyword != '' || num != '') {
		 if(flag.value=="快速"){
		      jsonForAjax("${ctx}/audit/auditAjax.action?startIndex=" + encodeURI(num, "utf-8") + "&keyword=" + encodeURI(encodeURI(keyword, "utf-8")) + "&t=" + new Date());
		       return ;
		    }
		    if(flag.value=="高级"){
		       extQuery(num);
		       return ;
		    }
			jsonForAjax("${ctx}/audit/auditAjax.action?startIndex=" + encodeURI(num, "utf-8") + "&keyword=" + encodeURI(encodeURI(keyword, "utf-8")) + "&t=" + new Date());
		} else {
			jsonForAjax("${ctx}/audit/auditAjax.action?t=" + new Date());
		}
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

	/* 数据分页 */
	function jsonForAjax(url) {
		var htmlStr = "";
		var htmlPage = "";
		$.getJSON(url, function(result) {
			resourceNum = result.resourceNum;
			totCount = result.totCount;
			$("#tbodyuse").html("<tr style=\"display:none\"></tr>");
			$.each(result.dataList, function(i, item) {
				var tBodyHtml = $("#tbodyuse").html();
				if (item.id != null) {
					htmlStr = "";
					htmlStr += "<tr align=\"center\">";
					htmlStr += "<td >";
					 htmlStr +=item.name;
					htmlStr += "</td>";
					htmlStr += "<td id=\"sysId\">";
					htmlStr += item.object;
					htmlStr += "</td>";
					htmlStr += "<td>";
					htmlStr += item.detailed;
					htmlStr += "</td>";
					htmlStr += "<td>";
					htmlStr += item.type;
					htmlStr += "</td>";
					htmlStr += "<td>";
					htmlStr += item.time;
					htmlStr += "</td>";
					htmlStr += "</tr>";
				}
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
	}
	function extQueryDlg() {
		$("#dialog-extQuery").dialog("open");
	}
 

</script>
</head>
<body style="margin: 0;position: 0">
<input type="hidden" id="ShouDingId" value='<%=session.getAttribute("SSI_LOGIN_Status")%>'/>
<input type="hidden" id="flag" value="无"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<!-- 空行 -->
			<tr>
				<td height="2px"></td>
			</tr>
			<tr>
				<td>
					<div class="caozuobox">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr height="30">
								<td width="10"></td>
								<td width="400" valign="middle" align="left">快速搜索： 
								  <input type="text" name="searchSysName" id="goToSearch" onblur="yanzheng1(this)"> <img src="/compliance/images/search.jpg" onclick="goSearchBefor()" style="cursor: pointer" class="hand" /> <a href="#" onclick="extQueryDlg();">高级</a>
							  </td>
								<td width="*"></td>
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
								<table width="100%" height="55" border="0" cellpadding="0" cellspacing="1" class="tab2" id="table-manageList">
									<thead>
										<tr height="28">
											<th width="325" class="biaoti" onclick="sortAble('table-manageList', 0)" style="cursor:pointer;writing-mode:lr-tb">操作用户名</th>
											<th width="326" class="biaoti" onclick="sortAble('table-manageList', 1)" style="cursor:pointer">操作模块</th>
											<th width="115" class="biaoti" onclick="sortAble('table-manageList', 2)" style="cursor:pointer">操作对象</th>
											<th width="245" class="biaoti" onclick="sortAble('table-manageList', 3)" style="cursor:pointer">操作类型</th>
											<th width="300" class="biaoti" onclick="sortAble('table-manageList', 4)" style="cursor:pointer">操作时间</th>
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
 
 
	<div id="dialog-extQuery" title="高级查询">
		<table width="90%" border="0" cellspacing="5" cellpadding="5" style="margin-left:20px;">
			<tr>
				<td width="25%">操作用户名:</td>
				<td><input id="UerName" name="UerName" type="text"   maxlength="50" style="width:250px;" onkeypress="if

(event.keyCode==13)extQuery();" /></td>
			</tr>
			<tr>
				<td width="25%">操作模块:</td>
				<td><input id="auditObject" name="auditObject" type="text"   maxlength="50" style="width:250px;" onkeypress="if
(event.keyCode==13)extQuery();" /></td>
			</tr>
			<tr>
				<td width="25%">操作对象:</td>
				<td><input id="auditDetailed" name="auditDetailed" type="text"   maxlength="50" style="width:250px;" onkeypress="if

(event.keyCode==13)extQuery();" /></td>
			</tr>
			<tr>
				<td width="25%">操作类型:</td>
				<td><input id="auditType" name="auditType" type="text"   maxlength="50" style="width:250px;" onkeypress="if
(event.keyCode==13)extQuery();" /></td>
			</tr>
			<tr>
				<td width="25%">操作时间:</td>
				<td><input id="ayditTime" name="ayditTime" type="text" onclick="SelectDate(this)"  maxlength="50" style="width:250px;" onkeypress="if
(event.keyCode==13)extQuery();" /></td>
			</tr>
		</table>
	</div>
</body>
</html>
