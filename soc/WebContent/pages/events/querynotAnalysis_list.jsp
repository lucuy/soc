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
 
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

<link href="${ctx}/styles/audit/audit.css" rel="stylesheet"
	type="text/css">
<link type="text/css" href="/soc/styles/jquery-ui-1.8.16.custom.css"
	rel="stylesheet" />
	<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery-latest.js"></script>
<script type="text/javascript"
	src="${ctx }/scripts/jquery.tablesorter.js"></script>
	<script type='text/javascript'
	src="/soc/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="/soc/scripts/jquery-ui-1.8.16.custom.min.js"></script>
 <link href="/soc/styles/jquery-ui.custom.css" rel="stylesheet"
    type="text/css">
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<style type="text/css">
body {
	padding: 0px 0px 0px 0px;
	maring: 0px 0px 0px 0px;
}
.display {
	display: none;
}
<%-- .conn
{
    padding:0px 0px 0px 0px;
    margin:2px 0px 0px 0px;
    position: absolute;
    z-index:-1; 
    width:99%;"
}
.box{
    margin:0px auto;
    border:1px solid #D1D2D4;
    background:url(/soc/images/jetsen/rightDh.jpg) repeat-x 0 0; 
    height:33px; 
    line-height:30px;
    text-align: center;
}
.sbox {
    clear: both;
    margin-bottom: 10px;
    overflow: hidden;
}
.hand
{
      cursor:   hand;
      background:#ccccff;
}


.eventslist
{
  background: none repeat scroll 0 0 #D2E8FA;
  padding:0px 0px 0px 0px;
  margin:0px 0px 0px 0px;
}
.eventslist tr td
{
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
.back
{
    background: #FFFFFF;
}
.hideDiv
{
    padding:0px 0px 0px 0px;
    margin:0px 0px 0px 5px;
    background-image:url("${ctx}/images/hide.png");
    background-repeat :  0% 0% ;
    background-repeat : repeat;
    background-attachment : scroll;
    width:99%;
    height:100%; 
    position:absolute;
    float:left;
    z-index:1;
    filter : progid:DXImageTransform.Microsoft.Alpha(style=1,opacity=20,finishOpacity=20);
}
.conn
{
    margin:2px 0px 0px 0px;
    position: absolute;
    z-index:-1; 
    width:99%;"
}

.refresh
{
     text-align: center;
     line-height: 28px;
}
.dialog-enentsContent
{
   overflow:auto;
   scrollbar-face-color: #FFFFFF; 
   scrollbar-shadow-color: #D2E5F4; 
   scrollbar-highlight-color: #D2E5F4; 
   scrollbar-3dlight-color: #FFFFFF; 
   scrollbar-darkshadow-color: #FFFFFF; 
   scrollbar-track-color: #FFFFFF; 
   scrollbar-arrow-color: #D2E5F4
}
.btnstyle{
     background:url(/soc/images/btnbg.jpg) no-repeat; 
     border:none; 
     width:66px; 
     height:21px;
     cursor:pointer;
     color:#265D86;
     align:center;
}
.column
{
    padding:0px 0px 0px 0px;
    margin:0px 0px 0px 0px;
    border-left:1px solid #D2E8FA;
    border-top: 1px solid #D2E8FA;
    border-right: 1px solid #D2E8FA;
}
.title
{
    margin:0px 0px 0px 0px;
    padding:0px 0px 0px 0px;
    color:#000000;
    background: url(/soc/images/tdBg.jpg) repeat-x left;
    height: 31px;
    
}
.title_t
{
    position:relative;
    left:10px;
    top:5px;
}
.img_a
{
    vertical-align: -5px;
    cursor:hand ;
}
.tit
{
    margin:0px 0px 0px 5px;
}

.rowLT
{
   border-bottom: 1px solid #D2E8FA;
   padding:5px 5px 5px 5px;
   width:100px;
   background: #F3F9FF;
}

.rowLV
{
   border-bottom: 1px solid #D2E8FA;
   border-left: 1px solid #D2E8FA;
   border-right: 1px solid #D2E8FA;
   padding:5px 5px 5px 5px;
   width:250px;
   
}
.rowRT
{
     border-bottom: 1px solid #D2E8FA;
     padding:5px 5px 5px 5px;
     width:100px;
     background: #F3F9FF;
}
.rowRV
{
 border-bottom: 1px solid #D2E8FA;
 border-left: 1px solid #D2E8FA;
 padding:5px 5px 5px 5px;
}

.level
{
   padding:0px 0px 0px 0px;
   margin:2px 2px 2px 4px;
   border:1px solid #CCCCC0; 
   height:12px; 
   width:40px; 
}

.levelBa
{
    height:12px; 
    margin:0px 0px 0px 0px;
}
.img_details
{
     cursor:hand ;
}
.loding
{
    font-size:14px;
    border:3px solid #D2E8FA;
    position:absolute;
    z-index:10;
    background: #FFFFFF;
    width:200px;
    height:40px;
    padding-top:25px;
    text-align: center;
    maring:0px auto;
    display: none;
} --%>
</style>
<script language="javascript">

	//搜索
<%--	function query() {

		$('#keyword').val($.trim($('#keyword').val()));
		location.href = "${ctx}/notAnalyticEvents/queryAnalysisFailed.action?keyword="
				+ encodeURI(encodeURI($('#keyword').val(), "utf-8"));
	}

	$(document).ready(function() {
		$('#keyword').keydown(function(event) {
			if (event.keyCode == 13) {
				query();
			}
		});

		// Dialog			
		$('#dialog-extQuery').dialog({
			autoOpen : false,
			width : 450,
			height : 300,
			buttons : {
				"查询[Enter]" : function() {
					extQuery();
					$(this).dialog("close");
				},

				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});

		$('#dialog-enentsContent').dialog({
			autoOpen : false,
			width : 800,
			height : 480,
			beforeclose : function(event, ui) {
				$("#hideDiv").removeClass("hideDiv");$("#hideDiv").css("width",0);$("#hideDiv").css("height",0); 
				$("#loding").hide();
			}
		});
        
		$('#listTable').tablesorter();
	});

	function extQueryDlg() {
		$('#dialog-extQuery').dialog('open');
	}
	Date.prototype.format = function(format) 
	{ 
	var o = { 
	"M+" : this.getMonth()+1, //month 
	"d+" : this.getDate(), //day 
	"h+" : this.getHours(), //hour 
	"m+" : this.getMinutes(), //minute 
	"s+" : this.getSeconds(), //second 
	"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
	"S" : this.getMilliseconds() //millisecond 
	} 
	if(/(y+)/.test(format)) format=format.replace(RegExp.$1, 
	(this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	for(var k in o)if(new RegExp("("+ k +")").test(format)) 
	format = format.replace(RegExp.$1, 
	RegExp.$1.length==1 ? o[k] : 
	("00"+ o[k]).substr((""+ o[k]).length)); 
	return format; 
	} 
	
	function extQuery() {
		 var	t=new Date(); 
		 var flag=false;
		 t=new Date().format("yyyy-MM-dd"); 
			var tt=$('#endTime').val();
			var t2=$('#beginTime').val();
			var html="";
			if(t2!=null){
				html="";
				if(t2>t){
					flag=false;
					html="不能大于当前时间";
				}else{
					if(tt==null||tt==""){
						flag=true;
						html="";
						$('#endTime').val(t);
					}
				}
			}
			if(tt!=null){
				
					html="";
					if(tt>t){
						alert("2");
						flag=false;
						html="不能大于当前时间";
					}else{
						if(t2==null||t2==""){
							alert("1");
							html="";
							flag=false;
							html="请选择开始时间";
						}else{
						flag=true;
						html="";}
					}
				
			}
			alert(flag);
			$("#msgs").append(html);
		$('#selnotAnalyticEventsAssetName').val($.trim($('#notAnalyticEventsAssetName').val()));
		$('#selnotAnalyticEventsCollectorName').val($.trim($('#notAnalyticEventsCollectorName').val()));
		$('#selnotAnalyticEventsAssetName').val($.trim($('#notAnalyticEventsAssetName').val()));
		$('#selbeginTime').val($.trim($('#beginTime').val()));
		$('#selendTime').val($.trim($('#endTime').val()));
		$('#selday').val($.trim($('#day').val()));
		if(flag==true){
			$("#queryForm").submit();
			$(this).dialog("close");
		}
		
	}
	 function custom(type)
	    {
		
	        if(type == "return")
	        {
	            $("#beginTime").val('');
	            $("#endTime").val('');
	            $("#timeEror").html("");
	            $("#default").removeClass("display");
	            $("#custom").addClass("display");
	        }
	        if(type == "custom")
	        {
	            $("#default").addClass("display");
	            $("#custom").removeClass("display");
	        }
	        
	    }--%>
	function goback(){
		location.href = "${ctx}/notAnalyticEvents/queryAnalysisFailed.action";
	}
</script>
</head>
<body>
	<s:form action="queryAnalysisFailed.action"
		namespace="/notAnalyticEvents" method="post" theme="simple"
		id="AnalysisFailed" name="AnalysisFailed">
		<s:hidden id="selnotAnalyticEventsAssetName" name="selnotAnalyticEventsAssetName" />
		<s:hidden id="selnotAnalyticEventsCollectorName" name="selnotAnalyticEventsCollectorName" />
		<s:hidden id="selnotAnalyticEventsContent" name="selnotAnalyticEventsContent" />
			<s:hidden id="selbeginTime" name="selbeginTime" />
		<s:hidden id="selendTime" name="selendTime" />
		<s:hidden id="selday" name="selday" />
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 2px">
			<!-- 空行-->
			<tr>
				<td></td>
			</tr>
			<tr>
				<td>
					<div class="box">

						<div class="right"></div>

						<%--<span class="kuaiju">快速搜索</span> <input type="text" id="keyword"
							value="${keyword}" name="keyword" class="jianju" /> <img
							src="${ctx}/images/search.jpg" onclick="query();"
							style="margin-left:5px">  --%><a href="#" class="jianju"
							onclick="goback();">返回</a> 
					</div>
				</td>
			<tr>
				<td>
					<table style="margin-top: 4px" width="100%" border="0"
						cellspacing="1" cellpadding="0" class="tab2" id="listTable">
						<thead>
						<tr height="28" class="biaoti">
							<th width="20%" align="center" class="biaoti">事件编号</th>
							<th width="30%" align="center" class="biaoti">关联资产</th>
							<th width="20%" align="center" class="biaoti">关联采集器</th>
							<th width="" align="center" class="biaoti">事件详情</th>
						</tr>
                        </thead>

                        <tbody>
                        
						<s:iterator value="#request.notAnayticList" status="stat">
							<%-- <input type="hidden" name="ruleId" id="ruleId" value="${ruleId}" /> --%>

							<tr>
								<td valign="middle" class="td_list_data">${identification}
								</td>


								<td valign="middle" class="td_list_data">${notAnalyticEventsAssetName}</td>


								<td valign="middle" class="td_list_data">
									${notAnalyticEventsCollectorName}</td>
								
								<td valign="middle" class="td_list_data">
								    ${notAnalyticEventsContent}
								</td>
							</tr>

						</s:iterator>
                        </tbody>
						<tr>
							<td colspan="4" width="100%"><jsp:include
									page="../commons/page.jsp"></jsp:include></td>
						</tr>
					</table></td>
			</tr>
		</table>
	</s:form>

	<%-- ext query from --%>
	<%--<s:form action="queryAnalysisFailed.action" namespace="/notAnalyticEvents"
		method="post" theme="simple" id="queryForm" name="queryForm">
		<s:hidden id="selnotAnalyticEventsAssetName" name="selnotAnalyticEventsAssetName" />
		<s:hidden id="selnotAnalyticEventsCollectorName" name="selnotAnalyticEventsCollectorName" />
		<s:hidden id="selnotAnalyticEventsContent" name="selnotAnalyticEventsContent" />
			<s:hidden id="selbeginTime" name="selbeginTime" />
		<s:hidden id="selendTime" name="selendTime" />
		<s:hidden id="selday" name="selday" />
	</s:form>

	--%><%-- 高级查询弹出框 
	<div id="dialog-extQuery" title="高级查询">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr height="30px">
				<td width="20%" align="right">资 产 名:</td>
				<td><input id="notAnalyticEventsAssetName"
					name="notAnalyticEventsAssetName" type="text" style="width:240px;"
					onkeypress="if(event.keyCode==13)extQuery();" /></td>
			</tr>
			<tr height="30px">
				<td width="20%" align="right">采 集 器:</td>
				<td><input id="notAnalyticEventsCollectorName"
					name="notAnalyticEventsCollectorName" type="text"
					style="width:240px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr height="30px">
				<td width="20%" align="right">事件详情:</td>
				<td><input id="notAnalyticEventsContent"
					name="notAnalyticEventsContent" type="text" style="width:240px;"
					onkeypress="if(event.keyCode==13)extQuery();" /></td>
			</tr>
			
			<tr>
				<td  width="20%" align="right"><span>时间范围:</span>
						</td>
				<td ><span class="display" id="custom"> <input value = ""
										id="beginTime" onclick="WdatePicker()" name="beginTime"
										class="Wdate" readonly="readonly"
										style="width:100px; height:19px;">&nbsp;至 <input value = ""
										id="endTime" onclick="WdatePicker()" name="endTime"
										class="Wdate" readonly="readonly"
										style="width:100px; height:19px;">&nbsp;<font
										color="red" id="timeEror"></font> &nbsp;&nbsp; <a
										href="javascript:custom('return');">返回</a> 
										</span> 
										<span id="default" ><select id="day" name="day" style="width:70%;">
								<option value="1" selected="selected" >1天</option>
								<option value="2" >2天</option>
								<option value="3" >3天</option>
								<option value="4" >4天</option>
								<option value="5" >5天</option>
								<option value="6" >6天</option>
								<option value="7" >7天</option>
							</select>
<a
										href="javascript:custom('custom');">自定义</a>
								</span><br/><font color="red"><span id="msgs"></span></font>
								</td>
							</tr>
		</table>
	</div>

	--%><%--  <div id="dialog-enentsContent" title="详细信息" class="dialog-enentsContent">
       <input type="button" value="返回列表" class="btnstyle" style="margin-right:5px;margin-top:-2px" onclick="closeContent();" />
          <input type="button" value="上一条" class="btnstyle" style="margin-right:5px;margin-top:-2px" onclick="" />
          <input type="button" value="下一条" style="margin-right:12px;margin-top:-2px" class="btnstyle" onclick="" />
          
          <div style="margin:5px 0px;"></div>
           <div class="column">
               <div class="title"><span class="title_t"><img src="${ctx}/images/arrow_03.gif" id="img_1" class="img_a" onclick="action(1)" >&nbsp;<span class="title_t_t">时间信息</span></span></div>
               <div id="column_1">
                   <ul class="display">
                       <li>
                            <table cellpadding="0px" cellspacing="0px" width="100%">
                                <tr >
                                    <td class="rowLT">开始时间</td>
                                    <td class="rowLV" id="occurTime"></td>
                                    <td class="rowRT">结束时间</td>
                                    <td class="rowRV" id="sendTime"></td>
                                </tr>
                            </table>
                       </li>
                   </ul>
               </div>
           </div>
           <div style="height: 10px;"></div>
             <div class="column">
               <div class="title"><span class="title_t"><img src="${ctx}/images/arrow_03.gif" id="img_2" class="img_a" onclick="action(2)" >&nbsp;<span class="title_t_t">事件威胁信息</span></span></div>
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
                                </table>
                      </li>
                   </ul>
               </div>
           </div>
           
           <div style="height: 10px;"></div>
             <div class="column">
               <div class="title"><span class="title_t"><img src="${ctx}/images/arrow_03.gif" id="img_3" class="img_a" onclick="action(3)" >&nbsp;<span class="title_t_t">基本信息</span></span></div>
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
                                        <td class="rowLT">源始事件</td>
                                        <td colspan="3" class="rowRV" id="msg"></td>
                                    </tr>
                                </table>
                      </li>
                   </ul>
               </div>
           </div>
           
            <div style="height: 10px;"></div>
             <div class="column">
               <div class="title"><span class="title_t"><img src="${ctx}/images/arrow_03.gif" id="img_4" class="img_a" onclick="action(4)" >&nbsp;<span class="title_t_t">来源信息</span></span></div>
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
                                </table>
                      </li>
                   </ul>
               </div>
           </div>
           
            <div style="height:10px;"></div>
             <div class="column">
               <div class="title"><span class="title_t"><img src="${ctx}/images/arrow_03.gif" id="img_5" class="img_a" onclick="action(5)" >&nbsp;<span class="title_t_t">目标信息</span></span></div>
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
                                </table>
                      </li>
                   </ul>
               </div>
           </div>
           
           <div style="height: 10px;"></div>
             <div class="column">
               <div class="title"><span class="title_t"><img src="${ctx}/images/arrow_03.gif" id="img_6" class="img_a" onclick="action(6)" >&nbsp;<span class="title_t_t">事件分类信息</span></span></div>
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
                                </table>
                      </li>
                   </ul>
               </div>
           </div>
           
             <div style="height: 10px;"></div>
             <div class="column">
               <div class="title"><span class="title_t"><img src="${ctx}/images/arrow_03.gif" id="img_7" class="img_a" onclick="action(7)" >&nbsp;<span class="title_t_t">设备信息</span></span></div>
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
                                </table>
                      </li>
                   </ul>
               </div>
           </div> --%>
	<%--   </div> --%>
</body>
</html>