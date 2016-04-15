<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="mainoffer.inc"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>


<%

String path = request.getContextPath();

	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>


		<title>筛选数据表</title>
		<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
		<script type="text/javascript"
			src="${ctx}/scripts/reportForm/prototype.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/ajax.js"></script>
		<script type="text/javascript">
	function nexts() {
		var f = document.ReportForm;
		var tables = "";
		var items = "";
		for ( var i = 0; i < f.elements.length; i++) {
			if (f.elements[i].name == "levelthree") {
				tables = tables + f.elements[i].value + ";";
			}
		}
		for ( var i = 0; i < f.elements.length; i++) {
			if (f.elements[i].name == "leveltwo") {
				items = items + f.elements[i].value + ";";
			}
		}
		var reportinfo = encodeURIComponent(f.reportinfo.value);
		if (tables != "") {
			tables = tables.substring(0, tables.length - 1);
			<logic:notEqual name="useraction" value="editReport">
			f.action = "newreportform.do?method=creatReportFormStep3&levelthree="
					+ tables
					+ "&reportinfo="
					+ encodeURIComponent(encodeURIComponent(reportinfo))
					+ "&leveltwo="
					+ items;
			f.submit();
			</logic:notEqual>
			<logic:equal name="useraction" value="editReport">
			f.action = "editreportform.do?method=editReportFormStep3&levelthree="
					+ tables
					+ "&reportinfo="
					+ encodeURIComponent(encodeURIComponent(reportinfo))
					+ "&leveltwo="
					+ items;
			f.submit();
			</logic:equal>
		} else {
			alert("三级菜单中的条目不能为空！");
			return false;
		}

	}

	function pre() {
		var f = document.ReportForm;
		var modelname1='${modelname1}';
		<logic:notEqual name="useraction" value="editReport">
		f.action = "newreportform.do?method=creatReportFormStep1&flag=1"
				+"&modelname1="+encodeURIComponent(encodeURIComponent(modelname1));
		f.submit();
		</logic:notEqual>
		<logic:equal name="useraction" value="editReport">
		f.action = "editreportform.do?method=editReportFormStep1&flag=1"
				+"&modelname1="+encodeURIComponent(encodeURIComponent(modelname1));
		f.submit();
		</logic:equal>
	}
	function additems(str, ordernum, id, checked) {
		var f = document.ReportForm;
		//itemid 是选择的条目的id
		//			var itemid="";
		//			for(var i=0;i<f.elements.length;i++){
		//				if(f.elements[i].name.indexOf(str)!=-1 && f.elements[i].checked==true){
		//					var va=f.elements[i].value;
		//					itemid=itemid+va+";";
		//				}
		//			}
		//			if(itemid==""){
		//				alert("请选择添加条目！");
		//				return false;
		//			}
		var threeids = "";
		var twoids = "";
		for ( var j = 0; j < f.elements.length; j++) {
			if (f.elements[j].name.indexOf("three") != -1
					&& f.elements[j].type == "checkbox") {
				var val = f.elements[j].value;
				threeids = threeids + val + ",";
			}
			if (f.elements[j].name.indexOf("two") != -1
					&& f.elements[j].type == "checkbox") {
				var val = f.elements[j].value;
				//twoids=twoids+val+",";
			}
		}
		if (ordernum == "2") {
			getHTML("twolevel", "ajaxquerytables.do?method=getTablesQuery",
					"orderNum=" + ordernum + "&paterOrders=" + id + "&checked="
							+ checked + "&twoids=" + twoids, "post");
		}
		if (ordernum == "3") {
			getHTML("threelevel", "ajaxquerytables.do?method=getTablesQuery",
					"orderNum=" + ordernum + "&paterOrders=" + id + "&checked="
							+ checked + "&threeids=" + threeids, "post");
		}
	}

	function getHTML(id, url, pars, meth) {

		new Ajax.Updater({
			success : id
		}, url, {
			method : meth,
			parameters : pars,
			onFailure : reportError,
			evalScripts : true
		});
	}

	function reportError() {

	}

	function delitems(str, ordernum) {
		var f = document.ReportForm;
		//itemid 是选择的条目的id
		
		var checkitemid = "";
		var unckeckitmeid = "";
		var threeids = "";
		for ( var i = 0; i < f.elements.length; i++) {
			var va = f.elements[i].value;
			if (f.elements[i].name.indexOf(str) != -1
					&& f.elements[i].checked == true) {
				checkitemid = checkitemid + va + ",";
			} else if (f.elements[i].name.indexOf(str) != -1
					&& f.elements[i].checked == false) {
				unckeckitmeid = unckeckitmeid + va + ",";
			}
		}
		if (checkitemid == "") {
			alert("请选择删除条目！");
			return false;
		}
		if (ordernum == "2") {
			var twoids = "";
			for ( var j = 0; j < f.elements.length; j++) {
				if (f.elements[j].name.indexOf("three") != -1
						&& f.elements[j].type == "checkbox") {
					var val = f.elements[j].value;
					threeids = threeids + val + ",";
				}
				if (f.elements[j].name.indexOf("two") != -1
						&& f.elements[j].type == "checkbox"
						&& f.elements[j].checked == false) {
					var val = f.elements[j].value;
					twoids = twoids + val + ",";
				}
			}
			getHTML("twolevel",
					"ajaxquerytables.do?method=getDelTwoTablesQuery",
					"orderNum=" + ordernum + "&checkitemid=" + checkitemid
							+ "&unckeckitmeid=" + unckeckitmeid + "&twoids="
							+ twoids, "post");
			getHTML("threelevel",
					"ajaxquerytables.do?method=getDelThreeTablesQuery",
					"orderNum=" + ordernum + "&checkitemid=" + checkitemid
							+ "&unckeckitmeid=" + unckeckitmeid + "&threeids="
							+ threeids, "post");
		}
		if (ordernum == "3") {
			var twoids = "";
			for ( var j = 0; j < f.elements.length; j++) {
				if (f.elements[j].name.indexOf("two") != -1
						&& f.elements[j].type == "checkbox") {
					var val = f.elements[j].value;
					twoids = twoids + val + ",";
				}
			}
			getHTML("threelevel",
					"ajaxquerytables.do?method=getDelThreeTablesQuery",
					"orderNum=" + ordernum + "&checkitemid=" + checkitemid
							+ "&unckeckitmeid=" + unckeckitmeid + "&twoids="
							+ twoids, "post");
		}
	}
	function cancels() {
		window.location = "reportFormQuery.do?method=initPage";
	}

	function disable(obj, edit) {
		var r = table1.rows.length;
		var checked = false;
		var all = 0;
		var ch = document.getElementsByName("levelfirst");
		for ( var i = 0; i < r; i++) {
			var c = ch[i].checked;
			if (c == true) {
				var con = table1.rows(i).cells(1).innerHTML;
				for ( var j = 0; j < r; j++) {
					var col = table1.rows(j).cells(1).innerHTML;
					if (con == "&nbsp;资产信息" || con == "&nbsp;策略信息"
							|| con == "&nbsp;客户端日志信息" || con == "&nbsp;安全信息") {
						if (!(col == "&nbsp;资产信息" || col == "&nbsp;策略信息"
								|| col == "&nbsp;客户端日志信息" || col == "&nbsp;安全信息")) {
							var che = table1.rows(j).cells(0).innerHTML;
							if (che.indexOf("disabled") < 0) {
								//table1.rows(j).cells(0).innerHTML = che.substring(0,che.length-1)+' disabled>';
							}
						}
					} else if (con == "&nbsp;补丁信息") {
						if (!(col == "&nbsp;补丁信息")) {
							var che = table1.rows(j).cells(0).innerHTML;
							if (che.indexOf("disabled") < 0) {
								//table1.rows(j).cells(0).innerHTML = che.substring(0,che.length-1)+' disabled>';
							}
						}
					} else {
						if (!(col == "&nbsp;服务器日志信息" || col == "&nbsp;服务器信息")) {
							var che = table1.rows(j).cells(0).innerHTML;
							if (che.indexOf("disabled") < 0) {
								//table1.rows(j).cells(0).innerHTML = che.substring(0,che.length-1)+' disabled>';
							}
						}
					}
				}
				all++;
			}
			if (ch[i].value == obj && ch[i].checked == true) {
				checked = true;
			}
		}

		if (all == 0) {
			for ( var i = 0; i < r; i++) {
				var che = table1.rows(i).cells(0).innerHTML;
				if (che.indexOf("disabled") > 0) {
					table1.rows(i).cells(0).innerHTML = che.substring(0, che
							.indexOf("disabled"))
							+ che.substring(che.lastIndexOf("disabled") + 8,
									che.length);
				}
			}
			if (edit == "check") {
				getHTML("twolevel",
						"ajaxquerytables.do?method=getDelTwoTablesQuery",
						"orderNum=2&unckeckitmeid=", "post");
				getHTML("threelevel",
						"ajaxquerytables.do?method=getDelThreeTablesQuery",
						"orderNum=3&unckeckitmeid=", "post");
			}
		} else {
			if (edit == "check")
				additems('first', 2, obj, checked);
		}
	}

	function letwo(id, checked) {
		if (checked == true) {
			additems('two', 3, id, true);
		}
	}
</script>
	</head>

	<body>
		<html:form action="newreportform.do" method="post">
			<!--  主table-->
			<table width="99%" border="0" cellspacing="0" cellpadding="0"
				style="margin-left: 4px; margin-top: 2px">
				<tr>
					<!-- left area -->
					<td>
						<div class="framDiv">
							<!--  左侧table-->
							<table width="100%" border="0" cellspacing="0" cellpadding="0"
								style="padding-left: 1px; padding-top: 1px; padding-right: 1px;">

								<!-- 用户信息 -->
								<tr>
									<td class='r2titler'>
										<b>第二步</b>
										<font color="red">(请慎重选择这一步，不合理的选择将会加大数据库的压力！)</font>
									</td>
								</tr>
								<tr>
									<td style="padding-left: 120px;padding-top: 10px;padding-bottom: 10px;">
										<logic:notEqual name="useraction" value="editReport">
											<table>
												<tr>
													<td>
														<input type="hidden" name="reportinfo"
															value="${reportinfo }">

														<div
															style="border-top: 1px #bacbcb solid; border-right: 1px #bacbcb solid; border-bottom: 1px #bacbcb solid; border-left: 1px #bacbcb solid; background-color: #efefef; height: 20px; width: 220px; line-height: 16pt;"
															align="center">
															<table width="100%">
																<tr>
																	<td align="center" width="13%">
																		&nbsp;
																	</td>
																	<td align="center" width="87%">
																		一级菜单
																	</td>
																</tr>
															</table>
														</div>
														<div
															style="border-right: 1px #bacbcb solid; border-bottom: 1px #bacbcb solid; border-left: 1px #bacbcb solid; height: 220px; width: 220px; line-height: 16pt; overflow-x: hidden; overflow-y: scroll;">
															<table width="100%" border="1" cellpadding="0"
																cellspacing="0" bordercolorlight="#666666"
																bordercolordark="#ffffff" bgcolor="#FFFFFF" id="table1">

																<logic:notEmpty name="levelfirstlist">
																	<logic:iterate id="list" name="levelfirstlist">
																		<tr>
																			<td height="20" align="center" width="15%">
																				<input type="radio" id="levelfirst"
																					name="levelfirst" value="${list.id }"
																					onclick="javascript:disable('${list.id}','check');">
																			</td>
																			<td align="center" width="85%">
																				&nbsp;${list.name }
																			</td>
																		</tr>
																	</logic:iterate>
																</logic:notEmpty>
															</table>
														</div>
													</td>
													<td>
														<label>
															<input type="button" value="<<  删除" class="btnstyle"
																onclick="javascript:delitems('two',2)" />
														</label>
													</td>
													<td>
														<div
															style="border-top: 1px #bacbcb solid; border-right: 1px #bacbcb solid; border-bottom: 1px #bacbcb solid; border-left: 1px #bacbcb solid; background-color: #efefef; height: 20px; width: 220px; line-height: 16pt;"
															align="center">
															<table width="100%">
																<tr>
																	<td align="center" width="13%">
																		&nbsp;
																	</td>
																	<td align="center" width="87%">
																		二级菜单
																	</td>
																</tr>
															</table>
														</div>
														<div id="twolevel"
															style="border-right: 1px #bacbcb solid; border-bottom: 1px #bacbcb solid; border-left: 1px #bacbcb solid; height: 220px; width: 220px; line-height: 16pt; overflow-x: hidden; overflow-y: scroll;">
															<logic:notEmpty name="leveltwolist">
																<table width="100%" border="1" cellpadding="0"
																	cellspacing="0" bordercolorlight="#666666"
																	bordercolordark="#ffffff" bgcolor="#FFFFFF"
																	id="contentTable1">
																	<logic:iterate id="list" name="leveltwolist">
																		<tr>
																			<td height="20" align="center" width="15%">
																				<input type="checkbox" id="leveltwo" name="leveltwo"
																					value="${list.id }"
																					onclick="javascript:additems('two',3,'${list.id}',this.checked);">
																			</td>
																			<td align="center" width="85%">
																				&nbsp;${list.name }
																			</td>
																		</tr>
																	</logic:iterate>
																</table>
															</logic:notEmpty>
														</div>
													</td>
													<td>
														<label>
															<input type="button" value="<<  删除" class="btnstyle"
																onclick="javascript:delitems('three',3)" />
														</label>
													</td>
													<td>
														<div
															style="border-top: 1px #bacbcb solid; border-right: 1px #bacbcb solid; border-bottom: 1px #bacbcb solid; border-left: 1px #bacbcb solid; background-color: #efefef; height: 20px; width: 220px; line-height: 16pt;"
															align="center">
															<table width="100%">
																<tr>
																	<td align="center" width="13%">
																		&nbsp;
																	</td>
																	<td align="center" width="87%">
																		三级菜单
																	</td>
																</tr>
															</table>
														</div>
														<div id="threelevel"
															style="border-right: 1px #bacbcb solid; border-bottom: 1px #bacbcb solid; border-left: 1px #bacbcb solid; height: 220px; width: 220px; line-height: 16pt; overflow-x: hidden; overflow-y: scroll;">
															<logic:notEmpty name="levelthreelist">
																<table width="100%" border="1" cellpadding="0"
																	cellspacing="0" bordercolorlight="#666666"
																	bordercolordark="#ffffff" bgcolor="#FFFFFF"
																	id="contentTable">
																	<logic:iterate id="list" name="levelthreelist">
																		<tr>
																			<td height="20" align="center" width="15%">
																				<input type="checkbox" id="levelthree"
																					name="levelthree" value="${list.id }">
																			</td>
																			<td align="center" width="85%">
																				&nbsp;${list.tableNameDescription }
																			</td>
																		</tr>
																	</logic:iterate>
																</table>
															</logic:notEmpty>
														</div>
													</td>
												</tr>
											</table>
										</logic:notEqual>

										<logic:equal name="useraction" value="editReport">
											<table>
												<tr>
													<td>
														<input type="hidden" name="reportinfo"
															value="${reportinfo }">
														<input type="hidden" name="reportFormId"
															value="${reportFormId }">

														<div
															style="border-top: 1px #bacbcb solid; border-right: 1px #bacbcb solid; border-bottom: 1px #bacbcb solid; border-left: 1px #bacbcb solid; background-color: #efefef; height: 20px; width: 220px; line-height: 16pt;"
															align="center">
															<table width="100%">
																<tr>
																	<td align="center" width="13%">
																		&nbsp;
																		<input type="hidden" id="checkedlevelone"
																			value="${levelone}">
																	</td>
																	<td align="center" width="87%">
																		一级菜单
																	</td>
																</tr>
															</table>
														</div>
														<div
															style="border-right: 1px #bacbcb solid; border-bottom: 1px #bacbcb solid; border-left: 1px #bacbcb solid; height: 220px; width: 220px; line-height: 16pt; overflow-x: hidden; overflow-y: scroll;">
															<table width="100%" border="1" cellpadding="0"
																cellspacing="0" bordercolorlight="#666666"
																bordercolordark="#ffffff" bgcolor="#FFFFFF" id="table1">

																<logic:notEmpty name="levelfirstlist">
																	<logic:iterate id="list" name="levelfirstlist">
																		<tr>
																			<td height="20" align="center" width="15%">
																				<input type="radio" id="levelfirst"
																					name="levelfirst" value="${list.id }"
																					onclick="javascript:disable('${list.id}','check');">
																			</td>
																			<td align="center" width="85%">
																				&nbsp;${list.name }
																			</td>
																		</tr>
																	</logic:iterate>
																</logic:notEmpty>
															</table>
														</div>
													</td>
													<td>
														<label>
															<input type="button" value="<<  删除" class="btnstyle"
																onclick="javascript:delitems('two',2)" />
														</label>
													</td>
													<td>
														<div
															style="border-top: 1px #bacbcb solid; border-right: 1px #bacbcb solid; border-bottom: 1px #bacbcb solid; border-left: 1px #bacbcb solid; background-color: #efefef; height: 20px; width: 220px; line-height: 16pt;"
															align="center">
															<table width="100%">
																<tr>
																	<td align="center" width="13%">
																		&nbsp;
																	</td>
																	<td align="center" width="87%">
																		二级菜单
																	</td>
																	<td width="1%"></td>
																</tr>
															</table>
														</div>
														<div id="twolevel"
															style="border-right: 1px #bacbcb solid; border-bottom: 1px #bacbcb solid; border-left: 1px #bacbcb solid; height: 220px; width: 220px; line-height: 16pt; overflow-x: hidden; overflow-y: scroll;">
															<logic:notEmpty name="leveltwolist">
																<table width="100%" border="1" cellpadding="0"
																	cellspacing="0" bordercolorlight="#666666"
																	bordercolordark="#ffffff" bgcolor="#FFFFFF"
																	id="contentTable">
																	<logic:iterate id="list" name="leveltwolist">
																		<%
																			int j = 0;
																		%>
																		<tr>
																			<td height="20" align="center" width="15%">
																				<input type="checkbox" id="leveltwo" name="leveltwo"
																					value="${list.id }">
																			</td>
																			<td align="center" width="85%">
																				&nbsp;${list.name }
																			</td>
																			<td>
																				<input type="checkbox" name="tworowid"
																					value="<%=j%>" style='display: none'>
																			</td>
																		</tr>
																		<%
																			j++;
																		%>
																	</logic:iterate>
																</table>
															</logic:notEmpty>
														</div>
													</td>
													<td>
														<label>
															<input type="button" value="<<  删除" class="btnstyle"
																onclick="javascript:delitems('three',3)" />
														</label>
													</td>
													<td>
														<div
															style="border-top: 1px #bacbcb solid; border-right: 1px #bacbcb solid; border-bottom: 1px #bacbcb solid; border-left: 1px #bacbcb solid; background-color: #efefef; height: 20px; width: 220px; line-height: 16pt;"
															align="center">
															<table width="100%">
																<tr>
																	<td align="center" width="13%">
																		&nbsp;
																	</td>
																	<td align="center" width="87%">
																		三级菜单
																	</td>
																	<td width="1%"></td>
																</tr>
															</table>
														</div>
														<div id="threelevel"
															style="border-right: 1px #bacbcb solid; border-bottom: 1px #bacbcb solid; border-left: 1px #bacbcb solid; height: 220px; width: 220px; line-height: 16pt; overflow-x: hidden; overflow-y: scroll;">
															<logic:notEmpty name="levelthreelist">
																<table width="100%" border="1" cellpadding="0"
																	cellspacing="0" bordercolorlight="#666666"
																	bordercolordark="#ffffff" bgcolor="#FFFFFF"
																	id="contentTable">
																	<logic:iterate id="list" name="levelthreelist">
																		<%
																			int jj = 0;
																		%>
																		<tr>
																			<td height="20" align="center" width="15%">
																				<input type="checkbox" id="levelthree"
																					name="levelthree" value="${list.id }">
																			</td>
																			<td align="center" width="85%">
																				&nbsp;${list.tableNameDescription }
																			</td>
																			<td>
																				<input type="checkbox" name="threerowid"
																					value="<%=jj%>" style='display: none'>
																			</td>
																		</tr>
																		<%
																			jj++;
																		%>
																	</logic:iterate>
																</table>
															</logic:notEmpty>
														</div>
													</td>
												</tr>
											</table>
											<script type="text/javascript">
  		var checks = document.getElementById("checkedlevelone").value.split(",");
  		var levelone = document.getElementsByName("levelfirst");
  		for(var i=0;i<checks.length;i++){
  			for(var j=0;j<levelone.length;j++){
  				if(checks[i]==levelone[j].value){
  					levelone[j].checked=true;
  					disable(checks[i],'edit');
  				}
  			}
  		}
	 </script>
										</logic:equal>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
			</table>
			<table width="780px" border="0" cellspacing="0" cellpadding="0"
				style="margin-left: 4px;">
				<!-- 空行 -->
				<tr>
					<td class="td_detail_separator">
					</td>
				</tr>

				<tr>
					<td>
						<input type="button" class="btnyh" id="btnSave" value="上一步"
							onclick="pre();" />
						<input type="button" class="btnyh" id="btnSave" value="下一步"
							onclick="nexts();" />
						<input type="button" class="btnyh" id="btnCancel" value="取  消"
							onclick="cancels();" />
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>
