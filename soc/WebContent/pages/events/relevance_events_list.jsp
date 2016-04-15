<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>

<link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css">

<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css"> 
<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet"
	type="text/css" /> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery-latest.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery.tablesorter.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>
<style type="text/css">
.box {
	margin: 0px 0px 4px 0px;
	border: 1px solid #c1ddf1;
	background: url(/soc/images/rightDh.jpg) repeat-x 0 0;
	height: 33px;
	line-height: 30px;
	text-align: center;
}

.sbox {
	clear: both;
	margin-bottom: 10px;
	overflow: hidden;
}

.hand {
	cursor: hand;
	background: #ccccff;
}
<%--
.eventslist {
	background: none repeat scroll 0 0 #EBECF1;
}

.eventslist tr td {
	background: none repeat scroll 0 0 #FFFFFF;
    line-height: 28px;
    text-align: center;
}


.eventslist thead tr .header{
	background-image: url(/soc/images/sortArrows_bg.gif);
	background-repeat: no-repeat;
	background-position: center right;
	cursor: pointer;
}

.eventslist thead tr .headerSortUp {
	background-image: url(/soc/images/sortArrows_asc.gif);
}

.eventslist thead tr .headerSortDown {
	background-image: url(/soc/images/sortArrows_desc.gif);
}

.eventslist thead tr .headerSortDown , .tab2 thead tr .headerSortUp{
	background-color: #8dbdd8;
}

.eventslist .biaoti {
	background: url("/soc/images/tdBg.jpg") repeat-x scroll 0 0 transparent;
	height: 28px;
	color: black;
	font-weight: bold;
	text-align: center;
}--%>

.eventslist {
	 background: none repeat scroll 0 0 #EBECF1;
}

.eventslist tr td {
	line-height: 28px;
	text-align: center;
}


.eventslist .biaoti {
	background: url("/soc/images/tdBg.jpg") repeat-x scroll 0 0 transparent;
    height:28px;
    color: black;
    font-weight: bold;
    text-align: center;
}



.eventslist thead tr .header{

	background-image: url(/soc/images/sortArrows_bg.gif);
	background-repeat: no-repeat;
	background-position: center right;
	cursor: pointer;
}
.eventslist thead tr .headerSortUp {
	background-image: url(/soc/images/sortArrows_asc.gif);
}
.eventslist thead tr .headerSortDown {
	background-image: url(/soc/images/sortArrows_desc.gif);
}
.eventslist thead tr .headerSortDown , .eventslist thead tr .headerSortUp{
	background-color: #8dbdd8;
}

.back {
	background: #FFFFFF;
}

.hideDiv {
	padding: 0px 0px 0px 0px;
	margin: 0px 0px 0px 0px;
	background-image: url("${ctx}/images/hide.png");
	background-position: 0% 0%;
	background-repeat: repeat;
	background-attachment: scroll;
	/*     width:100%;
    height:510px; */
	position: absolute;
	z-index: 1;
	filter: progid:DXImageTransform.Microsoft.Alpha(style=1, opacity=20,
		finishOpacity=20 );
}

.conn {
	padding: 0px 0px 0px 0px;
	margin: 2px 0px 0px 0px;
	position: absolute;
	z-index: -1;
	width: 99%;
}

.refresh {
	text-align: center;
	line-height: 28px;
}

.dialog-enentsContent {
	overflow: auto;
	scrollbar-face-color: #FFFFFF;
	scrollbar-shadow-color: #D2E5F4;
	scrollbar-highlight-color: #D2E5F4;
	scrollbar-3dlight-color: #FFFFFF;
	scrollbar-darkshadow-color: #FFFFFF;
	scrollbar-track-color: #FFFFFF;
	scrollbar-arrow-color: #D2E5F4
}

.btnstyle {
	background: url(/soc/images/btnbg.jpg) no-repeat;
	border: none;
	width: 66px;
	height: 21px;
	cursor: pointer;
	color: #265D86;
	align: center;
}

.column {
	padding: 0px 0px 0px 0px;
	margin: 0px 0px 0px 0px;
	border-left: 1px solid #D2E8FA;
	border-top: 1px solid #D2E8FA;
	border-right: 1px solid #D2E8FA;
}

.title {
	margin: 0px 0px 0px 0px;
	padding: 0px 0px 0px 0px;
	color: #000000;
	background: url(/soc/images/tdBg.jpg) repeat-x left;
	height: 31px;
}

.title_t {
	position: relative;
	left: 10px;
	top: 5px;
}

.img_a {
	vertical-align: -5px;
	cursor: hand;
}

.tit {
	margin: 0px 0px 0px 5px;
}

.rowLT {
	border-bottom: 1px solid #D2E8FA;
	padding: 5px 5px 5px 5px;
	width: 100px;
	background: #F3F9FF;
}

.rowLV {
	border-bottom: 1px solid #D2E8FA;
	border-left: 1px solid #D2E8FA;
	border-right: 0px solid #D2E8FA;
	padding: 5px 5px 5px 5px;
	width: 250px;
}

.rowRT {
	border-bottom: 1px solid #D2E8FA;
	padding: 5px 5px 5px 5px;
	width: 100px;
	background: #F3F9FF;
}

.rowRV {
	border-bottom: 1px solid #D2E8FA;
	border-left: 1px solid #D2E8FA;
	padding: 5px 5px 5px 5px;
}

.level {
	padding: 0px 0px 0px 0px;
	margin: 2px 2px 2px 4px;
	border: 1px solid #CCCCC0;
	height: 12px;
	width: 40px;
}

.levelBa {
	height: 12px;
	margin: 0px 0px 0px 0px;
}

.loding {
	font-size: 14px;
	border: 3px solid #D2E8FA;
	position: absolute;
	z-index: 10;
	background: #FFFFFF;
	width: 200px;
	height: 40px;
	padding-top: 25px;
	text-align: center;
	maring: 0px auto;
	display: none;
}
</style>
<script language="javascript">

	//全选及反选
	$("#chkAll").live("click", function(event) {
		if ($("#chkAll").hasClass('not_checked')) {
			$("#chkAll").removeClass('not_checked');
			$(".check-box").attr('checked', true);
		} else {
			$("#chkAll").addClass('not_checked');
			$(".check-box").attr('checked', false);
		}
	});
	
	//导出doc的方法
	function Export(reportType){
		$("#reportType").val(reportType);
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "")
			   {
				ids += "," + $(this).val();
				
				}
			else
				{
				
				ids = $(this).val();
				
				}

		});
		$("#checkids").val(ids);
		//document.getElementById("key").value=encodeURI(encodeURI("事件信息数据表", "utf-8"));
		$("#exportEvent").submit();
        $("#dialog-extQuery").dialog("close");
	}
	
	/*PL._init();

	PL.joinListen("/zhaoyang/hi");
	var eventid =0;
	function onData(event) {
		var html =event.get("eventsid");
		alert("111");
	//	alert(eventid);
		alert(event.get("eventsid"));
		if (parseInt(event.get("eventsid")) > 0) {
		//	alert(event.get("eventsid"));
			if(eventid==event.get("eventsid")){
				
			}else{
					$.ajax({
						type : "POST",
						url : "${ctx}/events/eventsDetail.action?",
						async : true,
						dataType : "JSONObject",
						data : "eventsId=" + event.get("eventsid"),
						success : function(eventsResult) {
						//	alert("A");
							var obj = jQuery.parseJSON(eventsResult);
						//	alert(eventsResult);
							if (obj != null) {
							//	alert("a");
							var htm="";
								if(parseInt(obj.priority)<=1){
									htm+="<div class='levelBa' style='background-color:#CCCCCC; width:"+obj.priority*8+"px;'></div><span style='position:relative;left:0px;top:-20px;'>"
									+ obj.priority
									+ "</span>";
								}
								if(obj.priority<=3&& obj.priority>1){
									htm+="<div class='levelBa' style='background-color:#ffcc33; width:"+obj.priority*8+"px;'></div><span style='position:relative;left:0px;top:-20px;'>"
									+ obj.priority
									+ "</span>";
								}
								if(parseInt(obj.priority)>=4){
									htm+="<div class='levelBa' style='background-color:#ff3333; width:"+obj.priority*8+"px;'></div><span style='position:relative;left:0px;top:-20px;'>"
									+ obj.priority
									+ "</span>";
								}
								htmll ="<tr class='back' id='eventsTr_"
									+ obj.eventsLogId
									+ "_"
									+ obj.eventsType
									+ "'"
									+ "onmousemove=\"this.className='hand'\" onmouseout=\"this.className='back'\"  ondblclick=\"dblclick('"
									+ obj.identification + "'" + "," + "'"
									+ obj.eventsType + "'" + "," + "'"
									+ obj.customs1+
							"');\"\">"
									+ "<td valign='middle' align='center'>"+obj.eventsId+"</td> <td valign='middle' align='center'>  <div class='level'> "
									
									+htm
									
									+"</div></td>"
									+ "<td valign='middle' align='center'>"
									+ obj.type
									+ "</td><td valign='middle' align='center'>"
									+ obj.sAddr
									+ "</td><td valign='middle' align='center'>"
									+ obj.sPort
									+ "</td><td valign='middle' align='center'>"
									+ obj.dAddr
									+ "</td><td valign='middle' align='center'>"
									+ obj.dPort
									+ "</td><td valign='middle' align='center'>"
									+ obj.aggregatedCount
									+ "</td><td valign='middle' align='center'>"
									+ obj.receptTimes + "</td></tr>";
									//	alert(obj);
										
									var htmlls=	html+htmll;
							
										html=htmll;	
								//	alert(html);
									 $("#tbody").append(htmlls).slideDown(1000);
							}else{
							//	alert("b");
								
							}

						}
						
					});
					eventid =	event.get("eventsid");
				//	alert(eventid);
			}
		} else {
		//	alert("11");
			
			
			

		}
		var htmlls=	html;
		html+=htmlls;
		$("#tbody").append(html).slideDown(1000);

	}*/

	var m = 60; //定时更新记时器
	var refresh; //字时更新对象
	var refres; //字时更新对象
	var sourceType; //查询条件类型
	$(document).ready(
			function() {
			    
				$.post("${ctx}/events/queryUserByUserId.action",function(user){
					var thField = user.showEventFiled ; 
					if(thField != "all"){
						if(thField.indexOf(",") != -1){
							$.each(thField.split(","),function(n,value){
								$("#"+value).hide() ; 
								$("td [id='"+value+"']").hide("slow");
								$("input[id='id_"+value+"']").attr("checked",false);
							});
						}else{
							$("#"+thField).hide() ; 
							$("td [id='"+thField+"']").hide("slow");
							$("input[id='id_"+thField+"']").attr("checked",false);

						}
					}
				});
				
				//导出
				$('#dialog-extQuery1').dialog({
					autoOpen : false,
					width : 480,
					height : 200,
					buttons: {
						"取消[Esc]": function() {
							$(this).dialog("close");
						}
					}
				});
				//事件详细信息
				$('#dialog-enentsContent').dialog(
						{
							autoOpen : false,
							width : 800,
							height : 470,
							beforeclose : function(event, ui) {
								$("#hideDiv").removeClass("hideDiv");$("#hideDiv").css("width",0);$("#hideDiv").css("height",0); 
								$("#loding").hide();
								for ( var i = 1; i < 8; i++) {
									$("#img_" + i).attr("src",
											"${ctx}/images/u319_normal.gif");
									$("#column_" + i + " >ul").show();
								}
								if (sourceType == 0) {
									setTimeout("reFresh()",
											1000);
								}
							},
							close : function(event,ui){
								reFresh();
							}
						});
				
				$('#dialog-fieldSelect').dialog({
					autoOpen : false,
					width : 400,
					height : 330,
					buttons:{
						"确定[Enter]":function(){
							buttonOK();
							$('#dialog-fieldSelect').dialog("close") ; 
						},
						"取消[Esc]":function(){
							$('#dialog-fieldSelect').dialog("close") ; 
						}
						}
					});
				/* test(); */
				$('#event').tablesorter();

			});
	//点击确定按钮
	function buttonOK(){
			var str = "";
		$("#dialog-fieldSelect input[name='checkbox_th'][type='checkbox']").each(function(){
			var flag = $(this).attr("checked"); 
			var idVal = $(this).val();
			if(!flag){
				$("#"+idVal).hide() ; 
				$("td [id='"+idVal+"']").hide("slow");
				if(str == ""){
				    str = str+idVal ; 
				}else{
					str = str + "," + idVal ; 
				}					
			}else{
				$("#"+idVal).show() ; 
				$("td [id='"+idVal+"']").show("slow");
			}
		});
		$.post("${ctx}/events/updateFaild.action",{"strFaild":str},function(){});
	}
	
	//双击
	function dblclick(queryEventsId, type, tableName, eventsCustomd1) {
		//alert(queryEventsId);alert(type);alert(tableName);alert(eventype);
		var docHe = ($(document).height() / 2) - 40;
		var docWi = ($(document).width() / 2) - 200;
		$("#eventsCustomd1").val(eventsCustomd1);
		$("#hideDiv").addClass("hideDiv");
		$("#hideDiv").css({
			width : $(document).width(),
			height : $(document).height()
		});
		$("#loding").toggle("slow");
		$("#loding").css({
			top : docHe,
			left : docWi
		});
		detailsContent(queryEventsId, tableName);
	}

	//关闭事件详情
	function closeContent() {
		$('#dialog-enentsContent').dialog("close");
		
			setTimeout("reFresh()", 1000);
		
	}

	//事件详情内容
	function detailsContent(eventsLogIdentification, tableName) {

		$("#otableName").val(tableName);
		$("#oidentification").val(eventsLogIdentification);

		var falg = false;
		$.ajax({
			type : "POST",
			url : "${ctx}/events/eventsDetails.action?",
			async : true,
			dataType : "JSONObject",
			data : "eventsLogIdentification=" + eventsLogIdentification
					+ "&tableName=" + tableName,
			success : function(eventsResult) {
				var obj = jQuery.parseJSON(eventsResult);
				if (obj != null) {
					$("#occurTime").text(obj.receptTimes);
					$("#sendTime").text(obj.sendTimes);
					$("#priority").text(obj.priority);
					$("#type").text(obj.typeCustomd);
					$("#count").text(obj.aggregatedCount);
					$("#name").text(obj.nameCustomd);
					$("#customs4").text(obj.customs4);
					$("#msg").text(obj.msg);
					$("#sAdd").text(obj.sAdd);
					$("#sPort").text(obj.sPort);
					$("#sUser").text(obj.suserName);
					$("#tAdd").text(obj.tAdd);
					$("#digest").text(obj.nameCustomd);
					$("#tPort").text(obj.dPort);
					$("#tUser").text(obj.dUserName);
					$("#category").text(obj.cateGoryCustomd);
					$("#devName").text(obj.customs5);
					$("#devCategory").text(obj.devCategory);
					$("#action").text(obj.action);
					$("#result").text(obj.result);
					$("#dUserName").text(obj.dUserName);
					$("#devAddr").text(obj.devAddT);
					$("#devVendor").text(obj.devVendor);
					$("#devType").text(obj.devproduct);
					$("sensorName").text(obj.sensorName);
				}
				$("#loding").toggle("slow");
				$('#dialog-enentsContent').dialog('open');
				clearInterval(refresh);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				if (textStatus != "") {
					alert("请求错误刷新后重试");
					$("#hideDiv").removeClass("hideDiv");$("#hideDiv").css("width",0);$("#hideDiv").css("height",0); 
					$("#loding").hide();
				}
			}
		});
		return falg;
	}
	//删去空格字符    
	function del_blank(str) {
		return str.replace(/^\s*/, "").replace(/\s*$/, "");
	}

	//定时更新数据
	function reFresh() {		
			if (m >= 1) {
				$("#mes").html(
						"<div class='box'><div id='refresh' class='refresh'><b>"
						+ m
						+ "</b> 秒后更新数据 "
						/**
						+ " <input type=\"button\" value=\"打印\" style=\"margin-left:5px;float:right;margin:4.5px 5px;\" class=\"btnstyle\" onclick=\"preview();\">" 
						*/
						+ "</div></div>");

				refresh = setTimeout("reFresh()", 1000);
				m--;
			} else {
				// parent.mainFrame.location.href = '${ctx}/events/queryEvents.action?queryEventsType=0';
				parent.mainFrame.location.href = '${ctx}/events/queryRelevanceEvents.action';
			}
		

	}

	function action(id) {
		changeIcon($("#img_" + id));
		$("#column_" + id + " >ul").toggle("slow");

	}

	function changeIcon(nainNode) {
		if (nainNode) {
			if (nainNode.attr("src").indexOf("u321_normal.gif") >= 0) {
				nainNode.attr("src", "${ctx}/images/u319_normal.gif");

			} else {
				nainNode.attr("src", "${ctx}/images/u321_normal.gif");
			}
		}
	}

	function test() {
		var tableName = $("#otableName").val();

		//var tableName = "tbl_20130530W";
		var identifation = $("#oidentification").val();

		//alert('${ctx}/events/queryOriginalEvent.action?tableName='+tableName+'&identifation='+identifation);

		//parent.parent.location.href='${ctx}/events/queryOriginalEvent.action?tableName='+tableName+'&eventsLogIdentification='+identifation;
		
		$("#test").attr(
				"href",
				'${ctx}/events/queryOriginalEvent.action?tableName='
						+ tableName + '&eventsLogIdentification='
						+ identifation);
	}

	
	function openFiledTable(){
		$('#dialog-fieldSelect').dialog("open");
	}
	
	function clickFaild(obj){
		$("#"+obj).attr("checked",!$('#'+obj).attr('checked'));
	}

	function test1()
    {  
       	var queryEventsId = $("#eventsCustomd1").val();
        //alert('${ctx}/events/queryOriginalEvent.action?tableName='+tableName+'&identifation='+identifation);
         
        //parent.parent.location.href='${ctx}/events/queryOriginalEvent.action?tableName='+tableName+'&eventsLogIdentification='+identifation;
     	var url="${ctx}/vulnerability/queryEventLibraryList.action?eventLibraryId="+queryEventsId+"&detail=info";
        var url1="${ctx}/vulnerability/checkEventLibrary.action";
     	var flag=false;
     	$.ajax({
			type : "POST",
			url : url1,
			async : false,
			dataType : "JSONObject",
			data : "eventLibraryId=" + queryEventsId,
			success : function(eventLibrary) {
				var obj = jQuery.parseJSON(eventLibrary);
				if (obj == null) {
					alert("事件库里没有此类事件，请更新事件库！");
					flag=false;
				}else{
					flag=true;
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				if (textStatus != "") {
					alert("请求错误刷新后重试");
					$("#hideDiv").removeClass("hideDiv");$("#hideDiv").css("width",0);$("#hideDiv").css("height",0); 
					$("#loding").hide();
				}
			}
     	});
     	if(flag){
     		document.getElementById("test1").setAttribute("href", url);
     	}else{
     		document.getElementById("test1").setAttribute("href", "#");
     	}
     		

     }
	//打印用的js
	function pagesetup_null(){
		try{
		var RegWsh = new ActiveXObject("WScript.Shell")
		hkey_key="header" 
		RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"")
		hkey_key="footer"
		RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"")
		}catch(e){
		}
		} 
	
	function preview(){
		try{
		//pagesetup_null();
		//$("#mes").html("");
		document.getElementById("mes").style.display='none';
			document.getElementById("over").style.display='none';
		newwin=window.open("", "newwin", "height="+window.screen.height+",width="+window.screen.width+",toolbar=no,scrollbars=auto,menubar=no");
		newwin.document.body.innerHTML=$("#dataList").html();
		
		newwin.window.print();
		newwin.window.close();
		document.getElementById("mes").style.display='block';
		document.getElementById("over").style.display='block';
		pagesetup_default();
		}catch(e){}
		}
	//打印用的js结束
</script>

</head>

<body onload="reFresh();">
	<div id="conn" class="conn">
		<input type="hidden" id="otableName" /> <input type="hidden"
			id="oidentification"> <input type="hidden" id="eventsCustomd1" />
		<s:form action="queryRelevanceEvents" namespace="/events"
			method="post" theme="simple" id="events" name="eventsForm">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-left: 5px; margin-top: 0px;" >
				
				<tr>
					<td id="mes"></td>

				</tr>
				
				
				<tr>
					<td>
						<div class="sbox">
							<div class="cont">
								<!-- information area -->
								<div id="dataList">
									<table width="100%" cellspacing="1" cellpadding="0" border="0"
										class="eventslist" id="event">
										<thead>
											<tr height="28" class="biaoti">
												<th width="8%" class="biaoti">编号</th>
												<th width="8%" id="event_th_level" class="biaoti">事件级别</th>
												<th width="14%" id="event_th_name" class="biaoti">事件名称</th>
												<th width="*%" id="event_th_type" class="biaoti">事件类型</th>
												<th width="10%" id="event_th_driverName" class="biaoti">事件类别</th>
												<th width="15%" id="event_th_receiveTime" class="biaoti">产生时间</th>
												<th width="6%" id="event_th_receiveTime" class="biaoti">产生源</th>
												
											</tr>
										</thead>
										<tbody>

											<c:set value="${num}" var="num" />
											
											<s:iterator  value="events">
											
												<%--<tr class="back" id="eventsTr_${eventsLogId}_${eventsType}"
													onmousemove="this.className='hand'"
													onmouseout="this.className='back'"
													ondblclick="dblclick('${identification}','${eventsType}','${customs1}','${customd1}');">
													
												--%><tr class="back" id="eventsTr_${eventsLogId}_${eventsType}"
													onmousemove="this.className='hand'"
													onmouseout="this.className='back'"
								>
													<td valign="middle" align="center">${num+1}</td>

												<td  id="event_th_level">
												        <input type="hidden" value="${priority }" />
														<div class="level" style = "margin:0px auto;">
															<c:if test="${priority <= 1}">
																<div class="levelBa"
																	style="background-color:#CCCCCC; width:${priority*8}px;">
																</div>
																<span style="position:relative;left:0px;top:-20px;">${priority}</span>
															</c:if>
															<c:if test="${priority > 1 && priority <= 3}">
																<div class="levelBa"
																	style="background-color:#ffcc33;  width:${priority*8}px;">
																</div>
																<span style="position:relative;left:0px;top:-20px;">${priority}</span>
															</c:if>
															<c:if test="${priority >= 4}">
																<div class="levelBa"
																	style="background-color:#ff3333; width:${priority*8}px;">
																</div>
																<span style="position:relative;left:0px;top:-20px;">${priority}</span>
															</c:if>
														</div></td>
																<td id="event_th_name" valign="middle" align="center">${nameCustomd}</td>
													<td id="event_th_type" valign="middle" align="center">${typeCustomd}</td>
													
													<td id="event_th_driverName" valign="middle" align="center">${cateGoryCustomd}</td>

													<td id="event_th_receiveTime" valign="middle" align="center">
														${sendTimes}
													</td>
													<td id="event_th_receiveTime" valign="middle" align="center">
														<a
									href="queryAtnalyticEvents.action?id=${eventsId}"
									>查看详情</a>
													</td>

												</tr>
												<c:set value="${num+1}" var="num" />
											</s:iterator>
												
											</tbody>
											
											 <tr style="background-color:#FFFFFF; ">
											<td colspan="10" width="100%"> <div id="over"><jsp:include
													page="../commons/page.jsp"></jsp:include></div></td>
										</tr>
										
									</table>

								</div>
							</div>

						</div></td>
				</tr>
			</table>
		</s:form>
	</div>
	<div id="loding" class="loding">
		<font color='#69C3FF'>数据加载中...</font>
	</div>
	<div id="hideDiv"></div>
	<%--
	<div id="dialog-enentsContent" title="详细信息"
		class="dialog-enentsContent">
		<input type="button" value="返回列表" class="btnstyle"
			style="margin-right:5px;margin-top:-2px" onclick="closeContent();" />
		<input type="button" value="上一条" class="btnstyle"
			style="margin-right:5px;margin-top:-2px" onclick="" /> <input
			type="button" value="下一条" style="margin-right:12px;margin-top:-2px"
			class="btnstyle" onclick="" />

		<div style="margin:5px 0px;"></div>
		<div class="column">
			<div class="title">
				<span class="title_t"><img
					src="${ctx}/images/u319_normal.png" id="img_1" class="img_a"
					onclick="action(1)">&nbsp;<span class="title_t_t">时间信息</span>
				</span>
			</div>
			<div id="column_1">
				<ul class="display">
					<li>
						<table cellpadding="0px" cellspacing="0px" width="100%">
							<tr>
								<td class="rowLT">发送时间</td>
								<td class="rowLV" id="sendTime"></td>
								<td class="rowRT">接收时间</td>
								<td class="rowRV" id="occurTime"></td>
							</tr>
						</table></li>
				</ul>
			</div>
		</div>
		<div style="height: 10px;"></div>
		<div class="column">
			<div class="title">
				<span class="title_t"><img
					src="${ctx}/images/u319_normal.png" id="img_2" class="img_a"
					onclick="action(2)">&nbsp;<span class="title_t_t">事件威胁信息</span>
				</span>
			</div>
			<div id="column_2">
				<ul class="display">
					<li>
						<table cellpadding="0px" cellspacing="0px" width="100%">
							<tr>
								<td class="rowLT">后果</td>
								<td class="rowLV"></td>
								<td class="rowRT">漏&nbsp;&nbsp;洞</td>
								<td class="rowRV"></td>
							</tr>
						</table></li>
				</ul>
			</div>
		</div>

		<div style="height: 10px;"></div>
		<div class="column">
			<div class="title">
				<span class="title_t"><img
					src="${ctx}/images/u319_normal.png" id="img_3" class="img_a"
					onclick="action(3)">&nbsp;<span class="title_t_t">基本信息</span>
				</span>
			</div>
			<div id="column_3">
				<ul class="display">
					<li>
						<table cellpadding="0px" cellspacing="0px" width="100%">
							<tr>
								<td class="rowLT">威&nbsp;&nbsp;胁</td>
								<td class="rowLV" id="priority"></td>
								<td class="rowRT">事件类型</td>
								<td class="rowRV" id="type"></td>
							</tr>
							<tr>
								<td class="rowLT">数量</td>
								<td colspan="3" class="rowRV" id="count"></td>
							</tr>
							<tr>
								<td class="rowLT">事件名称</td>
								<td colspan="3" class="rowRV" id="name"></td>
							</tr>
							<tr>
								<td class="rowLT">事件描述</td>
								<td colspan="3" class="rowRV" id="customs4"></td>
							</tr>
							<tr>
								<td class="rowLT">关联资产</td>
								<td colspan="3" class="rowRV" id="msg"></td>
							</tr>
						</table></li>
				</ul>
			</div>
		</div>

		<div style="height: 10px;"></div>
		<div class="column">
			<div class="title">
				<span class="title_t"><img
					src="${ctx}/images/u319_normal.png" id="img_4" class="img_a"
					onclick="action(4)">&nbsp;<span class="title_t_t">来源信息</span>
				</span>
			</div>
			<div id="column_4">
				<ul class="display">
					<li>
						<table cellpadding="0px" cellspacing="0px" width="100%">
							<tr>
								<td class="rowLT">源地址</td>
								<td class="rowLV" id="sAdd"></td>
								<td class="rowRT">源端口</td>
								<td class="rowRV" id="sPort"></td>
							</tr>
							<tr>
								<td class="rowLT">事件来源用户名</td>
								<td colspan="3" class="rowRV" id="sUser"></td>
							</tr>
						</table></li>
				</ul>
			</div>
		</div>

		<div style="height:10px;"></div>
		<div class="column">
			<div class="title">
				<span class="title_t"><img
					src="${ctx}/images/u319_normal.png" id="img_5" class="img_a"
					onclick="action(5)">&nbsp;<span class="title_t_t">目标信息</span>
				</span>
			</div>
			<div id="column_5">
				<ul class="display">
					<li>
						<table cellpadding="0px" cellspacing="0px" width="100%">
							<tr>
								<td class="rowLT">目标地址</td>
								<td class="rowLV" id="tAdd"></td>
								<td class="rowRT">目标端口</td>
								<td class="rowRV" id="tPort"></td>
							</tr>
							<tr>
								<td class="rowLT">事件目标用户名</td>
								<td colspan="3" class="rowRV" id="tUser"></td>
							</tr>
						</table></li>
				</ul>
			</div>
		</div>

		<div style="height: 10px;"></div>
		<div class="column">
			<div class="title">
				<span class="title_t"><img
					src="${ctx}/images/u319_normal.png" id="img_6" class="img_a"
					onclick="action(6)">&nbsp;<span class="title_t_t">事件分类信息</span>
				</span>
			</div>
			<div id="column_6">
				<ul class="display">
					<li>
						<table cellpadding="0px" cellspacing="0px" width="100%">
							<tr>
								<td class="rowLT">事件对象分类</td>
								<td class="rowLV" id="category"></td>
								<td class="rowRT">事件设备分类</td>
								<td class="rowRV" id="devCategory"></td>
							</tr>
							<tr>
								<td class="rowLT">事件动作</td>
								<td class="rowLV" id="action"></td>
								<td class="rowRT">动作结果</td>
								<td class="rowRV" id="result"></td>
							</tr>
							<tr>
								<td class="rowLT">事件采集技术</td>
								<td colspan="3" class="rowRV" id="sensorName"></td>
							</tr>
							<tr>
								<td class="rowLT">事件特征</td>
								<td colspan="3" class="rowRV"></td>
							</tr>
						</table></li>
				</ul>
			</div>
		</div>

		<div style="height: 10px;"></div>
		<div class="column">
			<div class="title">
				<span class="title_t"><img
					src="${ctx}/images/u319_normal.png" id="img_7" class="img_a"
					onclick="action(7)">&nbsp;<span class="title_t_t">设备信息</span>
				</span>
			</div>
			<div id="column_7">
				<ul class="display">
					<li>
						<table cellpadding="0px" cellspacing="0px" width="100%">
							<tr>
								<td class="rowLT">设备名称</td>
								<td class="rowLV" id="devName"></td>
								<td class="rowRT">设备IP地址</td>
								<td class="rowRV" id="devAddr"></td>
							</tr>

							<tr>
								<td class="rowLT">设备厂商</td>
								<td class="rowLV" id="devVendor"></td>
								<td class="rowRT">设备型号</td>
								<td class="rowRV" id="devType"></td>
							</tr>
						</table></li>
				</ul>
			</div>
		</div>
		<div style="height:20px;text-align: right">

			<a id="test" href="" target="view_window" onclick="test();">查看事件原始信息</a>
		</div>
	</div>
     --%>
	<div id="dialog-enentsContent" title="详细信息"
		class="dialog-enentsContent" style="height: 200px">
		<input type="button" value="返回列表" class="btnstyle"
			style="margin-right:5px;margin-top:-2px" onclick="closeContent();" />
		<div style="margin:5px 0px;"></div>
		<div class="column">
			<div class="title">
				<span class="title_t"><img
					src="${ctx}/images/u319_normal.gif" id="img_1" class="img_a"
					onclick="action(1)">&nbsp;<span class="title_t_t">设备信息</span>
				</span>
			</div>
			<div id="column_1">
				<ul class="display" style="list-style-type:none">
					<li>
						<table cellpadding="0px" cellspacing="0px" width="100%">
							<tr>
								<td class="rowLT">设备名称</td>
								<td class="rowLV" id="devName">&nbsp;</td>
								<td class="rowRT">ip地址</td>
								<td class="rowLV" id="devAddr">&nbsp;</td>
							</tr>
							<tr>
								<td class="rowLT">设备厂商</td>
								<td class="rowLV" id="devVendor">&nbsp;</td>
								<td class="rowRT">设备型号</td>
								<td class="rowLV" id="devType">&nbsp;</td>
							</tr>
						</table></li>
				</ul>
			</div>
		</div>

		<div style="height: 10px;"></div>
		<div class="column">
			<div class="title">
				<span class="title_t"><img
					src="${ctx}/images/u319_normal.gif" id="img_2" class="img_a"
					onclick="action(2)">&nbsp;<span class="title_t_t">时间信息</span>
				</span>
			</div>
			<div id="column_2">
				<ul class="display" style="list-style-type:none">
					<li>
						<table cellpadding="0px" cellspacing="0px" width="100%">
							<tr>
								<td class="rowLT">产生时间</td>
								<td class="rowLV" id="sendTime">&nbsp;</td>
								<td class="rowRT">接收时间</td>
								<td class="rowLV" id="occurTime">&nbsp;</td>
							</tr>
						</table></li>
				</ul>
			</div>
		</div>
		<div style="height: 10px;"></div>
		<div class="column">
			<div class="title">
				<span class="title_t"><img
					src="${ctx}/images/u319_normal.gif" id="img_3" class="img_a"
					onclick="action(3)">&nbsp;<span class="title_t_t">基本信息</span>
				</span>
			</div>
			<div id="column_3">
				<ul class="display" style="list-style-type:none">
					<li>
						<table cellpadding="0px" cellspacing="0px" width="100%">
							<tr>
								<td class="rowLT">等&nbsp;&nbsp;级</td>
								<td class="rowLV" id="priority">&nbsp;</td>
								<td class="rowRT">数量</td>
								<td class="rowLV" id="count">&nbsp;</td>
							</tr>

							<tr>
								<td class="rowLT">事件名称</td>
								<td colspan="3" class="rowRV" id="name">&nbsp;</td>
							</tr>
                            <tr>
								   <td class="rowLT">事件类别</td>
								   <td colspan="3" class="rowRV" id="category">&nbsp;</td>
							</tr>
							<tr>
								<td class="rowLT">事件类型</td>
								<td colspan="3" class="rowRV" id="type">&nbsp;</td>
							</tr>
                            
							<tr>
								<td class="rowLT">事件描述</td>
								<td colspan="3" class="rowRV" id="customs4">&nbsp;</td>
							</tr>
						<!-- 	<tr>
								<td class="rowLT">摘要</td>
								<td colspan="3" class="rowRV" id="digest">&nbsp;</td>
							</tr> -->
						</table></li>
				</ul>
			</div>
		</div>

		<div style="height: 10px;"></div>
		<div class="column">
			<div class="title">
				<span class="title_t"><img
					src="${ctx}/images/u319_normal.gif" id="img_4" class="img_a"
					onclick="action(4)">&nbsp;<span class="title_t_t">来源目标信息</span>
				</span>
			</div>
			<div id="column_4">
				<ul class="display" style="list-style-type:none">
					<li>
						<table cellpadding="0px" cellspacing="0px" width="100%">
							<tr>
								<td class="rowLT">源地址</td>
								<td class="rowLV" id="sAdd">&nbsp;</td>
								<td class="rowRT">源端口</td>
								<td class="rowLV" id="sPort">&nbsp;</td>
							</tr>
							<tr>
								<td class="rowLT">目标地址</td>
								<td class="rowLV" id="tAdd">&nbsp;</td>
								<td class="rowRT">目标端口</td>
								<td class="rowLV" id="tPort">&nbsp;</td>
							</tr>
							<tr>
								<td class="rowLT">事件来源用户名</td>
								<td colspan="3" class="rowRV" id="sUser"></td>
						    </tr>
						</table></li>
				</ul>
			</div>

		</div>
			<div style="height: 10px;"></div>
			
			<div class="column">
			<div class="title">
				<span class="title_t"><img
					src="${ctx}/images/u319_normal.gif" id="img_6" class="img_a"
					onclick="action(6)">&nbsp;<span class="title_t_t">事件分类信息</span>
				</span>
			</div>
			
			<div id="column_6">
				<ul class="display">
					<li>
						<table cellpadding="0px" cellspacing="0px" width="100%">
							<!-- <tr>
								<td class="rowLT">事件对象分类</td>
								<td class="rowLV" id="category"></td>
								<td class="rowRT">事件设备分类</td>
								<td class="rowRV" id="devCategory"></td>
							</tr> -->
							<tr>
								<td class="rowLT">事件动作</td>
								<td class="rowLV" id="action"></td>
								<td class="rowRT">动作结果</td>
								<td class="rowRV" id="result"></td>
							</tr>
							<!-- <tr>
								<td class="rowLT">事件采集技术</td>
								<td colspan="3" class="rowRV" id="sensorName"></td>
							</tr>
							<tr>
								<td class="rowLT">事件特征</td>
								<td colspan="3" class="rowRV"></td>
							</tr> -->
						</table></li>
				</ul>
			</div>
		</div>
		<div style="height:20px;text-align: right">
			<a id="test" href="" target="view_window" onclick="test();">查看事件原始信息</a>&nbsp;&nbsp;&nbsp;
			<a id="test1" href="" onclick="test1();">事件库</a>
		</div>
	</div>
	
	<!-- 导出的dialog -->
	<div id="dialog-extQuery1" title="选择导出格式">
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<!-- left area -->
				<td width="100%" valign="top">
					<!--  左侧table-->
					<div class="framDiv" style="border:none;">
						<!-- 报表模板内容 -->
						<table width="70%" border="0" cellspacing="0" cellpadding="0"
							style="margin-left: 4px;" align="center">
							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
				
							<tr>
								<td align="center" width="20%">
								<img src="/soc/images/u21.png">
									<input type="button" class="btnyh" id="btnSave"
									value="导出为DOC格式" onclick="Export('doc');" /></td>
								<td align="center" width="20%">
								<img src="/soc/images/u23.png">
									<input type="button" class="btnyh"
									id="btnSave" value="导出为excel格式" onclick="exportExcel();" />
								</td>
							</tr>
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
							<tr>
								<td class="td_detail_separator"><br><br></td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
	</div>
	
	<!-- 选择字段对话框 -->
	<div id="dialog-fieldSelect" title="要显示的字段名">
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						class="tab2" id="denyPolicy">
						<tr><td valign="middle" class="biaocm" align="center">
						<input type="checkbox" class="check-box-assetGroup" checked="checked" name="checkbox_th" id="id_event_th_level" value="event_th_level" onclick="event.cancelBubble=true;"></td>
						<td valign="middle" class="td_t"><a href="javascript:void(0);" onclick="clickFaild('id_event_th_level');">事件级别</a></td></tr>
						<tr><td valign="middle" class="biaocm" align="center">
						<input type="checkbox" class="check-box-assetGroup" checked="checked" name="checkbox_th" id="id_event_th_name" value="event_th_name" onclick="event.cancelBubble=true;"></td>
						<td valign="middle" class="td_t"><a href="javascript:void(0);" onclick="clickFaild('id_event_th_name');">事件名称</a></td></tr>
						<tr><td valign="middle" class="biaocm" align="center">
						<input type="checkbox" class="check-box-assetGroup" checked="checked" name="checkbox_th" id="id_event_th_type" value="event_th_type" onclick="event.cancelBubble=true;"></td>
						<td valign="middle" class="td_t"><a href="javascript:void(0);" onclick="clickFaild('id_event_th_type');">事件类型</a></td></tr>
						<tr><td valign="middle" class="biaocm" align="center">
						<input type="checkbox" class="check-box-assetGroup" checked="checked" name="checkbox_th" id="id_event_th_driverName" value="event_th_driverName" onclick="event.cancelBubble=true;"></td>
						<td valign="middle" class="td_t"><a href="javascript:void(0);" onclick="clickFaild('id_event_th_driverName');">设备名称</a></td></tr>
						<tr><td valign="middle" class="biaocm" align="center">
						<input type="checkbox" class="check-box-assetGroup" checked="checked" name="checkbox_th" id="id_event_th_receiveTime" value="event_th_receiveTime" onclick="event.cancelBubble=true;"></td>
						<td valign="middle" class="td_t"><a href="javascript:void(0);" onclick="clickFaild('id_event_th_receiveTime');">接收时间</a></td></tr>
						<tr><td valign="middle" class="biaocm" align="center">
						<input type="checkbox" class="check-box-assetGroup" checked="checked" name="checkbox_th" id="id_event_th_ip" value="event_th_ip" onclick="event.cancelBubble=true;"></td>
						<td valign="middle" class="td_t"><a href="javascript:void(0);" onclick="clickFaild('id_event_th_ip');">源IP</a></td></tr>
						<tr><td valign="middle" class="biaocm" align="center">
						<input type="checkbox" class="check-box-assetGroup" checked="checked" name="checkbox_th" id="id_event_th_targetIp" value="event_th_targetIp" onclick="event.cancelBubble=true;"></td>
						<td valign="middle" class="td_t"><a href="javascript:void(0);" onclick="clickFaild('id_event_th_targetIp');">目标IP</a></td></tr>
						<tr><td valign="middle" class="biaocm" align="center">
						<input type="checkbox" class="check-box-assetGroup" checked="checked" name="checkbox_th" id="id_event_th_sorcePort" value="event_th_sorcePort" onclick="event.cancelBubble=true;"></td>
						<td valign="middle" class="td_t"><a href="javascript:void(0);" onclick="clickFaild('id_event_th_sorcePort');">源端口</a></td></tr>
						<tr><td valign="middle" class="biaocm" align="center">
						<input type="checkbox" class="check-box-assetGroup" checked="checked" name="checkbox_th" id="id_event_th_targerPort" value="event_th_targerPort" onclick="event.cancelBubble=true;"></td>
						<td valign="middle" class="td_t"><a href="javascript:void(0);" onclick="clickFaild('id_event_th_targerPort');">目标端口</a></td></tr>
					</table>
				</td>
			</tr>
	       </table>
   </div>

</body>
</html>