<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>信息系统管理</title>
<meta http-equiv="Cache-Control" content="private" />
<LINK href="${ctx}/css/basic/css.css" type=text/css rel=stylesheet>

<LINK href="${ctx}/css/style/jquery-ui.custom.css" type=text/css
	rel=stylesheet>
<LINK href="${ctx}/css/style/jquery.popuplayer.css" type=text/css
	rel=stylesheet>
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
<script type="text/javascript" src="${ctx}/scripts/check.js"></script>
<script type="text/javascript">
	//var resourceNum;
	var totCount;
	$(document).ready(function() {
	parent.leftFrame.location.href='${ctx}/sysAbolish/queryForJson.action';
		goSearch(0);
		$("#keyword").keydown(function(event) {
			if (event.keyCode == 13) {
				query();
			}
		});

		/* $('#importdiv').dialog({
			autoOpen : false,
			width : 380,
			height : 300,
			buttons : {
				"确定[Enter]" : function() {
					importExcel();
					$(this).dialog("close");
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}

		}); */
		$("#dialog-extQuery").dialog({
			autoOpen : false,
			minWidth : 450,
			minHeight : 200,
			position: [300, 100],
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
	});

	function importXML() {
		if (confirm("提示：当前单位信息已经存在，重新导入会覆盖当前信息\r\n单击“确定”继续，单击“取消”停止")) {
			//clearFile();
			$("#importdiv").dialog("open");
		}

	};
	//高级搜索
	function extQuery(num) {
		var softName = $.trim($("#hsoftname").val());
		var sysName = $.trim($("#hsysname").val());
	if(softName=="" && sysName==""){
		  return;
		}
		document.getElementById("goToSearch").value="";//设置快速搜索为空
		document.getElementById("flag").value="高级";//设置标志为高级搜索
		jsonForAjax("${ctx}/system/extQuery.action?startIndex="
				+ encodeURI(num, "utf-8") + "&sysNames="
				+ encodeURI(encodeURI(softName, "utf-8")) + "&sysID="
				+ encodeURI(encodeURI(sysName, "utf-8")) 
				+ "&t=" + new Date());
		$("#hsoftname").val("");
		$("#hsysname").val("");
	}
	$("#chkAll").live("click", function(event) {
		if ($("#chkAll").hasClass('not_checked')) {
			$("#chkAll").removeClass('not_checked');
			$(".check-box").attr('checked', true);
		} else {
			$("#chkAll").addClass('not_checked');
			$(".check-box").attr('checked', false);
		}
	});

	function deleteSearch() {
		var ids = "";
		var sysInfo_id = "";
		
		if ($("[name=ids]:checkbox:checked").size() < 1) {
			alert("请至少选择一项...");
			return;
		}
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != ""){
				ids += "," + $(this).val();
				sysInfo_id+="," + $(this).parents("tr").children("td:nth-child(3)").text();
				}
			else{
				ids = $(this).val();
				sysInfo_id+=$(this).parents("tr").children("td:nth-child(3)").text();
				}
		});
		ids = "," + ids + ",";
		var containIds="";
		 if (confirm("确认执行操作吗？")) {
		if(sysInfo_id != ""){
			 $.ajax({
			      type : "post",
				   url : "${ctx}/rank/queryByIdAjax.action",
				   dataType : "json",
				   data : "&sysInfo_id="+sysInfo_id,
				   success : function(result) {
					   $.each(result.resultByAjax, function(i, item) {
						   alert("系统"+item.sysInfoName+"已定级，禁止删除");
						   sysName = item.sysInfoName;
						   ids=ids.replace("," + item.rankId + ",",",");
						});
					   ids = ids.substring(1, ids.length - 1);
					   location.href = "${ctx}/system/delete.action?ids=" + ids;
				   }
			 }); 
		}
			 
		 }
	}
	function checkRank(sysId, id) {
		 $.ajax({
		      type : "post",
			   url : "${ctx}/rank/queryByIdAjax.action",
			   dataType : "json",
			   data : "&sysInfo_id="+sysId,
			   success : function(result) {
				   if (result.resultByAjax.length == 1) {
					   alert("系统已经定级， 禁止修改！");
				   } else {
					   location.href = "${ctx}/system/queryById.action?id=" + id;
				   }
			   }
		 });
	}
	//快速搜索
	function goSearch(num) {
		resetCheckBox();
		var keyword = $.trim($("#goToSearch").val());

		var flag=document.getElementById("flag");

	   if(keyword!=""){
		    flag.value="快速";//设置标志为高级搜索
         }
           var hsoftname=$.trim($("#hsoftname").val());
           var hsysname=$.trim($("#hsysname").val());
         if(hsoftname !="" || hsysname !=""){
            flag.value="高级";//设置标志为高级搜索
         }
        
         if((keyword=="")&&(hsoftname == "" && hsysname =="")){
             flag.value="无";
         }
        if(num ==0 && flag.value=="高级"){
               extQuery(num);
		       return ;
        }
		//alert(keyword);
	if (keyword != '' || num != '') {
			 if(flag.value=="快速"){
		      jsonForAjax("${ctx}/system/queryAjaxSystem.action?startIndex=" + encodeURI(num, "utf-8") + "&keyword=" + encodeURI(encodeURI(keyword, "utf-8")) + "&t=" + new Date());
		       return ;
		    }
		    if(flag.value=="高级"){
		       extQuery(num);
		       return ;
		    }
			jsonForAjax("${ctx}/system/queryAjaxSystem.action?startIndex=" + encodeURI(num, "utf-8") + "&keyword=" + encodeURI(encodeURI(keyword, "utf-8")) + "&t=" + new Date());
		} else {
			jsonForAjax("${ctx}/system/queryAjaxSystem.action?t=" + new Date());
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
		var ShouDingvalue = document.getElementById("ShouDingId").value;
		$.getJSON(url, function(result) {
			//resourceNum = result.resourceNum;
			totCount = result.totCount;
			$("#tbodyuse").html("<tr style=\"display:none\"></tr>");
			$.each(result.processLog, function(i, item) {
				var tBodyHtml = $("#tbodyuse").html();
				
				if (item.id != null) {
					htmlStr = "";
					htmlStr += "<tr align=\"center\">";
					if(ShouDingvalue==1){
			 		}else{
					htmlStr += "<td>";
					htmlStr += "<input name=\"ids\" type=\"checkbox\" class=\"check-box\" value=\""+item.id+"\"/>";
					htmlStr += "</td>";
					}
					htmlStr += "<td >";
					if(ShouDingvalue==1){
					 htmlStr +=item.sysName;
			 			}else{
	 						//htmlStr += "<a href=\"${ctx}/system/queryById.action?id=" + item.id + "\">" + item.sysName + "</a>";
			 				htmlStr += "<a href=\"javascript:void(0)\" onclick='checkRank(\"" + item.sysId + "\"," + item.id + ")'>" + item.sysName + "</a>";
			 			}
					htmlStr += "</td>";
					htmlStr += "<td id=\"sysId\">";
					htmlStr += item.sysId;
					htmlStr += "</td>";
					htmlStr += "<td><xmp>";
					htmlStr += item.busDescription;
					htmlStr += "</xmp></td>";
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
				/* htmlPage +="</tr>"; */
				$("#tbodyuseTr").html(htmlPage);
			}
			
		});
	}
	function extQueryDlg() {
		$("#dialog-extQuery").dialog("open");
	}
	function addSystem(){
		/* if(resourceNum-totCount < 0){
			alert("注册信息资产数为"+resourceNum+",现已使用"+totCount+",无法添加资产！");
		}else{ */
			location.href='${ctx}/pages/dbPage/basic/addsystem.jsp';
		/* } */
	}
	
	function goSearchBefor(){
	   document.getElementById("hsoftname").value="";
	   document.getElementById("hsysname").value="";
	   goSearch(0);
	}

</script>
<script type="text/javascript" src="../../scripts/js/sortupdatedata.js"></script>
</head>

<body style="margin: 0;position: 0:">
	<input type="hidden" id="ShouDingId"
		value='<%=session.getAttribute("SSI_LOGIN_Status")%>' />
	<input type="hidden" id="flag" value="无" />
	<form id="systeminfo" name="cpSystemInfoForm" method="post"
		action="${ctx}/system/query.action">
		<table width="99.5%" border="0" cellspacing="0" cellpadding="0">
			<!-- 空行 -->
			<tr>
				<td height="2px"></td>
			</tr>
			<tr>
				<td>
					<div class="caozuobox">
						<s:if test='#session.SSI_LOGIN_Status=="1"'>
						</s:if>
						<s:else>
							<table width="100%" border="0" cellspacing="0" cellpadding="0"
								style="font-size:12px;">
								<tr height="30">
									<td width="1%"></td>
									<td width="30%" valign="middle" align="left">快速搜索&nbsp;<input
										type="text" name="searchSysName" id="goToSearch"
										onblur="yanzheng1(this)"> <img
										src="${ctx}/images/search.jpg" onclick="goSearchBefor();"
										style="cursor: pointer" class="hand" /> <a href="#"
										onclick="extQueryDlg();">高级</a></td>
									<td width="20%" valign="middle"
										style="float:right; padding-right:10px; padding-top:10px"
										align="right"><input type="button" value="添加"
										class="btnstyle" style="margin-right:5px;margin-top:-10px"
										onclick="addSystem();" /> <input type="button" value="删除"
										style="margin-right:12px;margin-top:-10px" class="btnstyle"
										onclick="deleteSearch();" />
									</td>
									<td></td>
								</tr>
							</table>
						</s:else>
					</div>
				</td>
			<tr>
				<td height="3px"></td>
			</tr>
			</tr>

			<tr>
				<td>
					<div class="sbox">
						<div class="cont">
							<!-- information area -->
							<div id="dataList">
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="tab2" id="table-manageList">

									<thead>
										<tr height="28">
											<s:if test='#session.SSI_LOGIN_Status=="1"'>
											</s:if>
											<s:else>
												<th width="6%" class="biaoti"><input type="checkbox"
													id="chkAll" name="chkAll" class="check-box not_checked" />
												</th>
											</s:else>
											<th width="26%" class="biaoti"
												onclick="sortAble('table-manageList', 1)"
												style="cursor:pointer;font-size: 12px">系统名称</th>
											<th width="26%" class="biaoti"
												onclick="sortAble('table-manageList', 2)"
												style="cursor:pointer;font-size: 12px">系统编号</th>
											<th width="45%" class="biaoti" style="font-size: 12px">业务描述</th>
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
	<div id="dialog-extQuery" title="信息系统高级查询">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr>
				<td width="25%" style="font-size: 12px">系统名称:</td>
				<td><input id="hsoftname" name="hsoftname"
					onblur="yanzheng1(this)" type="text"
					style="width:250px;font-size: 12px;"
					onkeypress="if

(event.keyCode==13)extQuery();" /></td>
			</tr>
			<tr>
				<td width="25%" style="font-size: 12px">系统编号:</td>
				<td><input id="hsysname" name="hsysname" type="text"
					onblur="yanzheng1(this)" style="width:250px;font-size: 12px;"
					onkeypress="if

(event.keyCode==13)extQuery();" /></td>
			</tr>

		</table>
	</div>


</body>
</html>
