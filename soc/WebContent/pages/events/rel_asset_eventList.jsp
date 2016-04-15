<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
<head>
<title>安全管理平台综合监控</title>
<link href="/soc/images/favicon.ico" rel="shortcut icon" />
<link href="/soc/images/favicon.ico" rel="Bookmark" />
<link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css">

<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">
<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet"
	type="text/css" /> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>

<style type="text/css">
.box {
	margin: 0px 0px 0px 0px;
	border: 1px solid #c1ddf1;
	background: url(/soc/images/rightDh.jpg) repeat-x 0 0;
	height: 30px;
	line-height: 30px;
	text-align: center;
}

.sbox {
	clear: both;
	margin-bottom: 8px;
	overflow: hidden;
}

.hand {
	cursor: hand;
	background: #ccccff;
}

.eventslist {
	background: none repeat scroll 0 0 #D2E8FA;
	padding: 0px 0px 0px 0px;
	margin: 0px 0px 0px 0px;
}

.eventslist tr td {
	line-height: 28px;
	text-align: center;
}

.eventslist .biaoti {
	background: url("/soc/images/tdBg.jpg") repeat-x scroll 0 0 transparent;
	height: 30px;
	color: black;
	font-weight: bold;
	text-align: center;
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

//导出excel的方法
function exportExcel() {
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
	
	//如果选中了cheAll就弹出提示，否则不弹出
	if(ids == ""){
		if(confirm("你是要导出全部的事件信息...")){
			location.href = "${ctx}/events/exportEventExcel.action?checkids="+ ids+"&conditions="+$("#conditions").val()+"&biaozhi=3";
		}
	}else{
		
			if (confirm("你是要导出选中的事件信息...")) {
				location.href = "${ctx}/events/exportEventExcel.action?checkids="+ ids+"&conditions="+$("#conditions").val()+"&biaozhi=3";
			}
	}
	 $("#dialog-extQuery1").dialog("close");
}

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
//document.getElementById("key").value=encodeURI(encodeURI("资产风险评估图", "utf-8"));
	$("#exportEvent").submit();
	
  $("#dialog-extQuery1").dialog("close");
}
    
    var m = 60;  //定时更新记时器
    var refresh; //字时更新对象
    var refres; //字时更新对象
    var sourceType;  //查询条件类型
    $(document).ready(function() {
    	
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
            $('#dialog-enentsContent').dialog({
            autoOpen : false,
            width : 800,
            height : 470,
            beforeclose: function(event, ui) {
                 $("#hideDiv").removeClass("hideDiv");$("#hideDiv").css("width",0);$("#hideDiv").css("height",0); 
                 $("#loding").hide();
                 for(var i=1; i < 8; i++)
                 {
                     $("#img_"+i).attr("src","${ctx}/images/u319_normal.gif");     
                     $("#column_"+i+" >ul").show();
                 }
                 if(sourceType ==0)
                 { 
                     setTimeout("reFresh("+sourceType+")",1000); 
                 }
             }
        });
        
       
       /*  alert('${sourceAdd}');
        
        alert('${targetAdd}');
        
        alert('${deviceIp}'); */
        if('${sourceAdd}'!="")
        {
           $("#status").val("2");
           $("#ip").val('${sourceAdd}');
        
        }
        
        if('${targetAdd}'!="")
        {
           $("#status").val("3");
           $("#ip").val('${targetAdd}');
        
        }
        
        if('${deviceIp}'!="")
        {
           $("#status").val("1");
           $("#ip").val('${deviceIp}');
        
        }
        /* test(); */
        
    });
    //双击
    function dblclick(queryEventsId,type,tableName,eventsCustomd1)
    {
    	$("#eventsCustomd1").val(eventsCustomd1);
        var docHe =  ($(document).height()/2)-40;
        var docWi =  ($(document).width()/2)-200;
        $("#hideDiv").addClass("hideDiv");
        $("#hideDiv").css({
            width: $(document).width(), 
            height: $(document).height()
        });
        $("#loding").toggle("slow");
        $("#loding").css({top:docHe,left:docWi});
        detailsContent(queryEventsId,tableName);
    }  
    
    //关闭事件详情
    function closeContent()
    {
          $('#dialog-enentsContent').dialog("close");
          if(sourceType == 0)
          {
             setTimeout("reFresh("+sourceType+")",1000);
          }
    }
    
    //事件详情内容
    function detailsContent(eventsLogIdentification,tableName)
    {
        
        $("#otableName").val(tableName);
        $("#oidentification").val(eventsLogIdentification);
        
        var falg = false;
        $.ajax({
                type:"POST",
                url:"${ctx}/events/eventsDetails.action?",
                async : true,
                dataType:"json",
                data:"eventsLogIdentification="+eventsLogIdentification+"&tableName="+tableName,
                success:function(eventsResult)
                {
                   var obj = eventsResult;
                   
                   if(obj != null)
                   {
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
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    if(textStatus != "")
                    {
                        alert("请求错误刷新后重试");
                        $("#hideDiv").removeClass("hideDiv");$("#hideDiv").css("width",0);$("#hideDiv").css("height",0); 
                        $("#loding").hide();
                    }
                }
        });
        return falg;
    }
//删去空格字符    
function del_blank(str)
{
    return str.replace(/^\s*/,"").replace(/\s*$/,"");
}
    
    function action(id)
    {
            changeIcon($("#img_"+id));
            $("#column_"+id+" >ul").toggle("slow");
    
    }
    
     function changeIcon(nainNode)
    {
        if(nainNode)
        {
            if(nainNode.attr("src").indexOf("u321_normal.gif")>=0)
            {
               nainNode.attr("src","${ctx}/images/u319_normal.gif");
               
            }else
            {
               nainNode.attr("src","${ctx}/images/u321_normal.gif");
            }
         }
     }
     
     function test()
     {  
         var tableName = $("#otableName").val();
        
        //var tableName = "tbl_20130530W";
         var identifation = $("#oidentification").val();
         
         //alert('${ctx}/events/queryOriginalEvent.action?tableName='+tableName+'&identifation='+identifation);
         
         //parent.parent.location.href='${ctx}/events/queryOriginalEvent.action?tableName='+tableName+'&eventsLogIdentification='+identifation;
     	 $("#test").attr("href", '${ctx}/events/queryOriginalEvent.action?tableName='+tableName+'&eventsLogIdentification='+identifation);
     }
     
     //根据Ip查询
     function selectnormal()
     {
     
        var temp = $("#status").val();
                 
                 
       var eventType=$("#protocol").val();
                 
        var regex=/^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$/;
        
        var ip =$("#ip").val();
        
        var assetId =$("#assetId").val();
        
        var eventCategory = $("#eventsType").val();
                
                
       
        
        
        if(ip=='')
        {
        	location.href='${ctx}/events/queryByAssetEvents.action?assetId='+assetId+'&protocol='+eventType+'&timeRange=12&flag=1&eventsType='+eventCategory+'&status='+temp;
           //location.href="${ctx}/events/queryByAssetEvents.action?assetId="+assetId+"&timeRange=12&flag=1&eventsType="+eventCategory+"&protocol="+eventType;
        } 
        else
        {
        	 
              if(regex.test(ip)) {
                 
                <%-- if(temp=="1")
                 {
                      location.href="${ctx}/events/queryByAssetEvents.action?assetId="+assetId+"&timeRange=12&deviceIp="+ip+"&flag=1&eventsType="+eventCategory+"&protocol="+eventType;
                 }
                 if(temp=="2")
                 {
                      location.href="${ctx}/events/queryByAssetEvents.action?assetId="+assetId+"&timeRange=12&sourceAdd="+ip+"&flag=1&eventsType="+eventCategory+"&protocol="+eventType;
                 }
                 else
                 {
                      location.href="${ctx}/events/queryByAssetEvents.action?assetId="+assetId+"&timeRange=12&targetAdd="+ip+"&flag=1&eventsType="+eventCategory+"&protocol="+eventType;
                 }--%>
                 location.href='${ctx}/events/queryByAssetEvents.action?assetId='+assetId+'&protocol='+eventType+'&timeRange=12&flag=1&eventsType='+eventCategory+'&ip='+ip+'&status='+temp;
              }
              else
              {
                 alert("请输入正确的IP地址");
                 $("#ip").val("");
              }
        }
     }
     
      
     function extQueryDlg(){
    	 
    	 var value= $("#assetId").val();
    	 
    	 location.href="${ctx}/events/events_assetquery.action?assetId="+value;
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
</script>

</head>

<body>
	<div id="conn" class="conn">
	
		<input type="hidden" id="otableName" /> <input type="hidden"
			id="oidentification"><input type="hidden" id="eventsCustomd1"/>
	     <s:form action="queryEventsContitons.action" namespace="/events" method="post" theme="simple" id="events" name="eventsForm">
		    
		    <input type="hidden" id="assetId" name="assetId" value="${assetId}">
			
			<input type="hidden" id="conditions" name="conditions" value="${conditions}" />
			         	    
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
			
				style="margin-left: 5px; margin-top: 0px;">
				<tr>
				<td></td>
			     </tr>
				<%-- <c:if test="${queryEventsType == 0}">
					<tr>
						<td id="mes"></td>
					</tr>
				</c:if> --%>
				<tr>
				<td>
				<div class="box"> 
				   <span style="text-align:left">
				    IP地址:<input type="text" name="ip" style="height: 15px;" id="ip" value="${ip}"/>
				    
				   <select id="status" class="jianju">
				   
				    <option value="1">设备IP</option>
				   
                   <option value="2"<c:if test="${status =='2'}">selected="selected"</c:if>>来源IP</option>
                   
                    <option value="3"<c:if test="${status =='3'}">selected="selected"</c:if>>目的IP</option>
                   
                   </select> &nbsp;
                   
                   <select id="eventsType" class="jianju">
                   <option value="" selected="selected">---请选择事件类别---</option>
                     <s:iterator value="eventCategoryTagList" status="stat">
					     <option class="passwordPolicy" value="${eventcategorykey}"
					     <c:if test="${eventsType eq eventcategorykey }"> selected="selected"</c:if>
					     >${eventcategoryValue}</option>
					 </s:iterator>
                   </select>
                   
                   <select id="protocol" class="jianju">
                       <option value="" selected="selected">---请选择事件类型---</option>
                       <s:iterator value="eventAllTypeList" status="stat">
					     <option class="passwordPolicy" value="${eventtypetagId}"
					     <c:if test="${protocol eq eventtypetagId }"> selected="selected"</c:if>
					     >${eventtypetagName}</option>
					 </s:iterator>
                   </select>
                   
                   </span>
                   
                   <input type="button" value="执行" style="margin-left:5px"
							class="btnstyle" onclick="selectnormal()" />
							
                   <a href="#" class="jianju" onclick="extQueryDlg();">高级</a>   
				   <input type="button" value="导出" class="btnstyle"
								 align="right" style="margin-left:150px"
								onclick="$('#dialog-extQuery1').dialog('open');" />
				</div>
				</td>
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
												<td width="5%" align="center" >
													<input type="checkbox" id="chkAll" name="chkAll" class="check-box not_checked" />
												</td>
												<th width="6%" class="biaoti">编号</th>
												<th width="8%" class="biaoti">事件级别</th>
												<th width="14%" class="biaoti">事件名称</th>
												<th width="14%" class="biaoti">事件类型</th>
												<th width="10%" class="biaoti">设备名称</th>
												<th width="13%" class="biaoti">接收时间</th>
												<th width="10%" class="biaoti">源IP</th>
												<th width="10%" class="biaoti">目标IP</th>
												<th width="5%" class="biaoti">源端口</th>
												<th width="5%" class="biaoti">目标端口</th>
											</tr>
										</thead>
										<tbody>
											<c:set value="${num}" var="num" />
										<s:iterator var="e" value="relevanceAnalysisList">
											
												<tr class="back" id="eventsTr_${eventsLogId}_${eventsType}"
													onmousemove="this.className='hand'"
													onmouseout="this.className='back'"
													ondblclick="dblclick('${identification}','${eventsType}','${customs1}');">
													
													<td valign="middle" class="td_list_data">
														<input type="checkbox" id="ids" name="ids" value="${identification}" class="check-box"/>
													</td>
												<td valign="middle" align="center">${num+1}</td>
												<td  >
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
																<td valign="middle" align="center">${nameCustomd}</td>
													<td valign="middle" align="center">${typeCustomd}</td>


													<td valign="middle" align="center">${customs5}</td>
													
													
													
													<td valign="middle" align="center">
														${receptTimes}
													</td>

													<td valign="middle" align="center"><s:property value="#e.getsAdd()" /></td>

													<td valign="middle" align="center"> <s:property value="#e.gettAdd()" /></td>
													<td valign="middle" align="center"><s:property value="#e.getsPort()" /></td>
													<td valign="middle" align="center"> <s:property value="#e.getdPort()" /></td>

													<td valign="middle" align="center">${eventsTargetPort}</td>
													
												</tr>
												<c:set value="${num+1}" var="num" />
											</s:iterator>
											<tr style="background-color:#FFFFFF; ">
												<td colspan="11" width="100%" ><jsp:include
														page="../commons/page.jsp"></jsp:include></td>
											</tr>
										</tbody>
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
							</table>
						</li>
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
							</table>
						</li>
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
									<td colspan="3" class="rowRV" id="customs4" style="word-break:break-all" >&nbsp;</td>
								</tr>
								<!-- <tr>
									<td class="rowLT">摘要</td>
									<td colspan="3" class="rowRV" id="digest">&nbsp;</td>
								</tr> -->
							</table>
						</li>
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
							</table>
						</li>
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
		<div style="height:20px;text-align:right">
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
<s:form name="exportEvent" namespace="/events" action="exportEvent" id="exportEvent" method="post" theme="simple">
		<input type="hidden"  name="reportType" id="reportType" value = ""/> 
		<input type="hidden"  name="eventReportId" id="alertReportId" value = "13"/>  
		<input type="hidden"  name="checkids" id="checkids" value=""/>
			<input type="hidden" name="conditions" value="${conditions}" />
			<input type="hidden" name="biaozhi" value="3"/>
		<input type="hidden"  name="queryEventsId" id="queryEventsId" value="${queryEventsId}"/>
	</s:form>


</body>
</html>