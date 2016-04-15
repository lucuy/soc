<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	session.setAttribute("userId1", "admin");
%>
<html>
<head>
<base href="<%=basePath%>">
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css">

<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
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
    <script type="text/javascript" src="${ctx}/dwr/engine.js"></script>
<script type="text/javascript" src="${ctx}/dwr/interface/MessagePush.js"></script>
<script type='text/javascript' src="${ctx}/dwr/util.js"></script>
<%-- <script type='text/javascript'
	src="${ctx}/scripts/ajax-pushlet-client.js"></script>--%>
	<script type="text/javascript" src="${ctx}/scripts/dwrManager.js"></script>
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
	<%--PL._init();

	PL.joinListen("/zhaoyang/hi");
	
	function onData(event) {
	var html =event.get("hi");
	
		var htmlls=	html;
		html+=htmlls;
		$("#tbody").append(html).slideDown(1000);
		
	}--%>
	 function onLoad(){
         var userId = "admin";
         MessagePush.pageOnLoad(userId);
    }
	function showmessage(val){
		var html =val;
		$("#tbody").before(html).slideDown(1000);
		parent.frames[0].NewMessage();
	}
	var m = 60; //定时更新记时器
	var refresh; //字时更新对象
	var refres; //字时更新对象
	var sourceType; //查询条件类型
	function reFresh() {
	
	
			if (m >= 1) {
				$("#mes").html(
						"<div class='box'><div id='refresh' class='refresh'><b>"
								+ m + "</b> 秒后更新数据</div></div>");
				refresh = setTimeout("reFresh()", 1000);
				m--;
			} else {
				// parent.mainFrame.location.href = '${ctx}/events/queryEvents.action?queryEventsType=0';
				parent.mainFrame.location.href = '${ctx}/pages/monitor/realtim-etevents.jsp;';
			}
		

	}
	
	$(document).ready(function(){
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
									"${ctx}/images/u319_normal.png");
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
		$('#sortList').tablesorter() ; 
		
	});
	//双击
	function dblclick(queryEventsId, type, tableName, eventype) {
		var docHe = ($(document).height() / 2) - 40;
		var docWi = ($(document).width() / 2) - 200;
		$("#eventype").val(eventype);
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
					$("#type").text(obj.type);
					$("#count").text(obj.aggregatedCount);
					$("#name").text(obj.name);
					$("#customs4").text(obj.customs4);
					$("#msg").text(obj.msg);
					$("#sAdd").text(obj.sAdd);
					$("#sPort").text(obj.sPort);
					$("#sUser").text(obj.suserName);
					$("#tAdd").text(obj.tAdd);
					$("#digest").text(obj.name);
					$("#tPort").text(obj.dPort);
					$("#tUser").text(obj.dUserName);
					$("#category").text(obj.cateGory);
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
					alert("事件尚未入库，请稍后再试！");
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

	function test1()
    {  
       	var queryEventsId = $("#eventype").val();
        //alert('${ctx}/events/queryOriginalEvent.action?tableName='+tableName+'&identifation='+identifation);
         
        //parent.parent.location.href='${ctx}/events/queryOriginalEvent.action?tableName='+tableName+'&eventsLogIdentification='+identifation;
     	var url="${ctx}/vulnerability/queryEventLibraryList.action?eventLibraryId="+queryEventsId+"&detail=info";
     	document.getElementById("test1").setAttribute("href", url);

     }

	
</script>

</head>

<body onload="dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);reFresh();onLoad();">
	<div  class="conn">
		<input type="hidden" id="otableName" /> <input type="hidden"
			id="oidentification"> <input type="hidden" id="eventype" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-left: 5px; margin-top: 0px;">
				<tr><td id="mes"></td></tr>

					<tr><td>
			
				

						<div class="sbox">
							<div class="cont">
								<div >
									<table width="100%" cellspacing="1" cellpadding="0" border="0"
										class="eventslist" id="sortList">
										<thead>
											<tr height="28" class="biaoti">
												<th width="5%" class="biaoti">等级</th>
												<th width="15%" class="biaoti">事件名称</th>
												<th width="20%" class="biaoti">事件类型</th>
												<th width="15%" class="biaoti">源地址</th>
												<th width="5%" class="biaoti">源端口</th>
												<th width="15%" class="biaoti">目标地址</th>
												<th width="6%" class="biaoti">目标端口</th>
												<th width="5%" class="biaoti">数量</th>
												<th width="*" class="biaoti">接收时间</th>
											</tr>
										</thead>
										<tbody id="tbody">
										
										</tbody >
										
									</table>

								</div>
							</div>

						</div>
						</td></tr>
			</table>
	</div>
<div id="loding" class="loding">
		<font color='#69C3FF'>数据加载中...</font>
	</div>
	<div id="hideDiv"></div>
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
</body>
</html>