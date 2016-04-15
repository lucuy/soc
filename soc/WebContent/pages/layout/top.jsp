
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@page import="com.soc.model.user.User"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8" import="java.lang.*"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
<head>

<link href="${ctx}/styles/top.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css">
<style type="text/css">
ul{list-style:none;width:1600px;}
li{display:block;float:left;} 
#menu_bg{width:1165px;height:31px;}
#left a{float:left;width:50px;display:block;height:20px; }
#right  a{float:left;width:50px;display:block;height:20px; }
#left{margin-top:8px;float:left;}
#right{margin-top:8px;float:left;}
#menu_bg #menu{width:89%;height:20px;overflow:hidden;float:left;margin-top:8px;}
/* span{margin:9px auto;} */
</style>

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<link type="text/css"
	href="${ctx}/styles/css/ui-lightness/jquery-ui-1.7.3.custom.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${ctx}/scripts/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/js/jquery-ui-1.7.3.custom.min.js"></script>

<script language="javascript">
	
	getAlertTrance();
	var count = 0;
	var time;
	var weekday=new Array(7);
	weekday[0]="星期日";
	weekday[1]="星期一";
	weekday[2]="星期二";
	weekday[3]="星期三";
	weekday[4]="星期四";
	weekday[5]="星期五";
	weekday[6]="星期六";
	
	$(document).ready(function() {
		
		$("ul li a").click(function() {
			$("ul li a").css("color", "");
			$(this).css("color", "#faec06");
		});
		
		time=new Date(parseInt($("#dateTime").val()));
		//alert(time);
	});
	
  //  window.onbeforeunload = onbeforeunload_handler;
    //window.onunload = onunload_handler;
   ////  function onbeforeunload_handler(){
     
     //alert(window.event.clientY);
       //清除session    
       // alert("123");
             // var n = window.event.screenX - window.screenLeft;    
            //  var b = n > document.documentElement.scrollWidth-20;
            //  alert(n);
            //  alert(b);    
            ///  if((b && window.event.clientY < 0)|| window.event.altKey)    
            //  {    
              //       alert("是关闭而非刷新");    
                     //window.event.returnValue = ""; //这里可以放置你想做的操作代码  
                 // alert(window.event.clientY);
                 // alert(window.event.altKey);
             //   if(b && (window.event.clientY < 0) || window.event.altKey)
                //{
                  //  alert("关闭");
             //   }
              // else
               // {
               //    alert("kk");
            //    }
                
                 /*  if(window.event.clientY < 0)
                  {
                    test();
                  } */
            //  }    
       
      //  var warning="确认退出?";   
       // return warning;	
      // CloseOpen(window.event);	
   // }
     

	
	function test()
	{
	   parent.parent.location.href = '${ctx}/home/test.action';
	}
	var permissionMenuIds = "${sessionScope.SOC_LOGON_PERMISSIONS }";
	function linkTo(linkType) {
		if (linkType == 'home_page') {
			// 首页
			parent.leftFrame.location.href = '${ctx}/pages/home/home_page_menu.jsp';
			parent.mainFrame.location.href = '${ctx}/home/queryHome.action';
		} else if (linkType == 'user') {
			// 用户模块
			parent.leftFrame.location.href = '${ctx}/pages/user/user_menu.jsp';
		    if(permissionMenuIds.indexOf(",3,") != -1){ //用户管理
				  parent.mainFrame.location.href = '${ctx}/user/queryUser.action';
			     }else  if (permissionMenuIds.indexOf(",4,") != -1) {//角色管理
			      parent.mainFrame.location.href = '${ctx}/role/queryRole.action';
			    }else  if (permissionMenuIds.indexOf(",5,") != -1) {//策略管理
			      parent.mainFrame.location.href = '${ctx}/role/queryRole.action';
			    }
		} else if (linkType == 'asset') {
			//资产
			parent.leftFrame.location.href = '${ctx}/assetGroup/showGroupTree.action';
			  if(permissionMenuIds.indexOf(",7,") != -1){ //资产管理
					parent.mainFrame.location.href = '${ctx}/asset/queryAsset.action';
			     }
		} else if (linkType == 'monitor') {
			//监控
			parent.leftFrame.location.href = '${ctx}/monitorGroup/showGroupTree.action';
			 if(permissionMenuIds.indexOf(",9,") != -1){ //监控管理
					parent.mainFrame.location.href = '${ctx}/pages/monitor/realtim-etevents.jsp';
			     }
		} else if (linkType == 'events') {
			// 事件模块
			parent.leftFrame.location.href = '${ctx}/events/queryEventsTrre.action';
			 if(permissionMenuIds.indexOf(",11,") != -1){ // 事件管理
					parent.mainFrame.location.href = '${ctx}/events/queryRecentEvents.action';
			     }
		} else if (linkType == 'report') {
			// 组态报表
			parent.leftFrame.location.href = '${ctx}/pages/reportForm/reportMenu.jsp';
			 if(permissionMenuIds.indexOf(",16,") != -1){ // 报表管理
					parent.mainFrame.location.href = '${ctx}/reportFormQuery.do?method=initPage';
			     }else  if(permissionMenuIds.indexOf(",17,") != -1){ // 报表统计
					parent.mainFrame.location.href = '${ctx}/queryStat.do?method=initPage';
			     }else  if(permissionMenuIds.indexOf(",18,") != -1){ // 定时报表
					parent.mainFrame.location.href = '${ctx}/reportTime.do?method=initPage';
			     }
			     else  if(permissionMenuIds.indexOf(",19,") != -1){ // 自定义报表管理
						parent.mainFrame.location.href = '${ctx}/reportCustom.do?method=initPage';
				     }
		} else if (linkType == 'audit') {
			// 审计模块
			parent.leftFrame.location.href = '${ctx}/auditReport/displayLeftManageTree.action';
			 if(permissionMenuIds.indexOf(",13,") != -1){ // 内部审计
					parent.mainFrame.location.href = '${ctx}/audit/query.action';
			     }else  if(permissionMenuIds.indexOf(",14,") != -1){ // 外部审计
					parent.mainFrame.location.href = '${ctx}auditReport/queryReportByAuditReportId.action?auditReportId=5';
			     }
		} else if (linkType == 'rules') {
			// 规则管理
			parent.leftFrame.location.href = '${ctx}/relevanceGroup/displayLeftTree.action';
			if(permissionMenuIds.indexOf(",21,") != -1){ // 关联规则
				parent.mainFrame.location.href = '${ctx}/settingAssociationRules/AssociationRules.action';
		     }else  if(permissionMenuIds.indexOf(",22,") != -1){ // 解析规则
				parent.mainFrame.location.href = '${ctx}/settingAnalysisRules/AnalysisRules.action';
		     }else  if(permissionMenuIds.indexOf(",23,") != -1){ // 过滤规则
				parent.mainFrame.location.href = '${ctx}/filteringRules/queryFilterRuleList.action';
		     }
		     else  if(permissionMenuIds.indexOf(",55,") != -1){ // 自定义关联规则
					parent.mainFrame.location.href = '${ctx}/relevanceGroup/showGroupList.action';
			     }
		} else if (linkType == 'repository') {
			// 知识库
			parent.leftFrame.location.href = '${ctx}/vulnerability/queryTreeAction.action';
			 if(permissionMenuIds.indexOf(",27,") != -1){ // 安全公告
				parent.mainFrame.location.href = '${ctx}/securityBulletin/querySecurityBulletin.action';
		     }else  if(permissionMenuIds.indexOf(",28,") != -1){ // 预警发布
				parent.mainFrame.location.href = '${ctx}/warn/query.action';
		     } else if(permissionMenuIds.indexOf(",29,") != -1){ // 漏洞库管理
				parent.mainFrame.location.href = '${ctx}/vulnerability/query.action';
		     }else  if(permissionMenuIds.indexOf(",31,") != -1){ // 事件库  默认是木马病毒
					parent.mainFrame.location.href = '${ctx}/events/queryEventLibraryList.action?eventLibraryType=10';
			     }
		} else if (linkType == 'showSysInfo') {
			// 系统设置模块
					parent.leftFrame.location.href = '${ctx}/pages/systemsetting/setting_menu.jsp';
			if(permissionMenuIds.indexOf(",44,") != -1){ // 系统状态
				parent.mainFrame.location.href = '${ctx}/showSysInfo/getSysInfo.action';
		     }else  if(permissionMenuIds.indexOf(",35,") != -1){ // 网络设置
				parent.mainFrame.location.href = '${ctx}/settingNetwork/settingNetwork.action';
		     }else  if(permissionMenuIds.indexOf(",36,") != -1){ //路由表
				parent.mainFrame.location.href = '${ctx}/settingRoute/settingRoute.action';
		     } else  if(permissionMenuIds.indexOf(",39,") != -1){ //SYSLOG
				parent.mainFrame.location.href = '${ctx}/settingSysLog/settingSysLog.action';
			  }else  if(permissionMenuIds.indexOf(",40,") != -1){ // SNMP
				parent.mainFrame.location.href = '${ctx}/settingSNMP/settingSNMP.action';
			   }else  if(permissionMenuIds.indexOf(",38,") != -1){ // 邮箱设置
					parent.mainFrame.location.href = '${ctx}/settingMail/settingMail.action';
			     }else  if(permissionMenuIds.indexOf(",51,") != -1){ // 采集器管理
					parent.mainFrame.location.href = '${ctx}/settingLogger/logger.action';
			     }else  if(permissionMenuIds.indexOf(",45,") != -1){ // 配置阀值
						parent.mainFrame.location.href = '${ctx}/settingStorage/storage.action';
			     } else  if(permissionMenuIds.indexOf(",43,") != -1){ //告警配置
						parent.mainFrame.location.href = '${ctx}/alertSetting/queryAlert.action';
				 }else if(permissionMenuIds.indexOf(",66,") != -1){ // 同步服务器时间
							parent.mainFrame.location.href = '${ctx}/syncDate/syncDate.action';
				 }else  if(permissionMenuIds.indexOf(",54,") != -1){ // 资产设备设置
						parent.mainFrame.location.href = '${ctx}/category/queryCategory.action';
				  } else  if(permissionMenuIds.indexOf(",76,") != -1){ // 操作系统设置
							parent.mainFrame.location.href = '${ctx}/assetSystem/queryAssetSystem.action';
				  }else  if(permissionMenuIds.indexOf(",71,") != -1){ // 级联管理
							parent.mainFrame.location.href = '${ctx}/nodeGroup/editnodeGroup.action';
					     }else  if(permissionMenuIds.indexOf(",77,") != -1){ //级联拓扑
							parent.mainFrame.location.href = '${ctx}/nodeGroup/nodeGroupNetworkTopolog.action';
					     } else  if(permissionMenuIds.indexOf(",41,") != -1){ // 关闭系统
								parent.mainFrame.location.href = '${ctx}/pages/systemsetting/setting_shutdown.jsp';
						     }else  if(permissionMenuIds.indexOf(",57,") != -1){ // 升级
									parent.mainFrame.location.href = '${ctx}/settingUpgrade/display.action';
						     }else 		if(permissionMenuIds.indexOf(",48,") != -1){ // 归档
									parent.mainFrame.location.href = '${ctx}/settingArchive/archiveList.action';
						     }else  if(permissionMenuIds.indexOf(",49,") != -1){ // 自动归档设置
								parent.mainFrame.location.href = '${ctx}/settingArchive/auto.action';
						     }else  if(permissionMenuIds.indexOf(",50,") != -1){ // 通信配置
								parent.mainFrame.location.href = '${ctx}/settingCenterip/centerIp.action';
						     } else  if(permissionMenuIds.indexOf(",52,") != -1){ // Agent下载
									parent.mainFrame.location.href = '${ctx}/pages/systemsetting/setting_agentDownload.jsp';
							     }
			//parent.mainFrame.location.href = '${ctx}/showSysInfo/getSysInfo.action';
			//parent.leftFrame.location.href = '${ctx}/settingLogger/queryMenu.action';
		} else if (linkType == 'workOrder') {
			//工单管理模块
			parent.leftFrame.location.href = '${ctx}/pages/workorder/workOrder_menu.jsp';
			parent.mainFrame.location.href = '${ctx}/workOrder/query.action?falg=1';
		}else if (linkType == 'risk') {
			// 风险管理
			parent.leftFrame.location.href = '${ctx}/pages/risk/risk_menu.jsp';
			parent.mainFrame.location.href = '${ctx}/assetRiskGroup/queryAssetRiskEvaluation.action';
		}else if(linkType=='rank'){
			//等级保护和规管理
			parent.leftFrame.location.href='${ctx}/sysAbolish/queryForJson.action';
			parent.mainFrame.location.href='${ctx}/unitInfo/query.action';
		} else if(linkType=='addRules'){
			//添加解析规则
			parent.leftFrame.location.href='${ctx}/pages/addRules/addRules_menu.jsp';
			parent.mainFrame.location.href='${ctx}/pages/addRules/addRules.jsp';
		} 
		else if(linkType=='oem'){
			//替换oem包
			parent.leftFrame.location.href='${ctx}/pages/systemsetting/setting_oem_menu.jsp';
			parent.mainFrame.location.href='${ctx}/settingOEM/toUpdate.action';
		} else if (linkType == 'topo') {
			// 拓扑
			parent.leftFrame.location.href = '${ctx}/pages/topoPage/device/device_menu.jsp';
			parent.mainFrame.location.href = '${ctx}/device/queryAllDevice.action';
		} else if(linkType=='satellite'){
			//直播星事件
			parent.leftFrame.location.href='${ctx}/pages/satellite/satellite_menu.jsp';
			parent.mainFrame.location.href='${ctx}/satelliteEvents/queryEvents.action';
		}else if(linkType=='about')
		{
			// 关于产品
			parent.leftFrame.location.href='${ctx}/pages/about/about_menu.jsp';
			parent.mainFrame.location.href='${ctx}/about/about.action';
		}else if(linkType=='securityPolicy'){
			//安全策略管理
			parent.leftFrame.location.href='${ctx}/pages/securityPolicy/security_policy_menu.jsp';
			parent.mainFrame.location.href='${ctx}/securityPolicy/query.action';
		}
		
	}

	function logout() {
		if (confirm("你确认要注销吗？")) {
			delCookie("saveTime");//清除cookie
			location.href = '${ctx}/login/logout.action';
		}
	}
function updateNum(num){
	$('#AlarmCount').html("( " + num + " )");
	if(num==0){
		clearInterval(startWAV);
	}
}
	//获得是否开启告警设置
	function getAlertTrance() {
		$.ajax({
			type : "POST",
			url : "${ctx}/alertSetting/queryAlertTrance.action",
			data : "",
			dataType : 'text',
			success : function(msg) {
					getUnfinshedWorkOrderCount();
				var results = msg.split(",");
				checkDisk(results[1]);
				if (results[0] == 'true') {
					getalertMessage();
					setTimeout("getAlertTrance()", 60000);
				} else {
					setTimeout("getAlertTrance()", 60000);
				}
			}
		});
	}
	function getUnfinshedWorkOrderCount(){
		$.ajax({
			type : "POST",
			url : "${ctx}/workOrder/queryUnfinshWorkOrderCount.action",
			data : "",
			dataType : 'text',
			success : function(msg) {
				var html="";
				var result=msg.split("$$");
				if (result[0] != "0") {
					// html="<a href=\"javascript:void(0);\">未处理工单："+msg+"</a>";
					 html=result[0];
					 
						$("#apply").html(html);
						$("#apply").addClass("workOrderCount");
					 $('#apply')
						.click(
								function() {
									parent.mainFrame.location.href = '${ctx}/workOrder/query.action?falg=1';
									parent.leftFrame.location.href = '${ctx}/pages/workorder/workOrder_menu.jsp';
								});
				}else{
					 html="0";
						$("#apply").html(html);
					
				}
				if (result[1] != "0") {
					// html="<a href=\"javascript:void(0);\">未处理工单："+msg+"</a>";
					 html=result[1];
					 
						$("#apply1").html(html);
						$("#apply1").addClass("workOrderCount");
					 $('#apply1')
						.click(
								function() {
									parent.mainFrame.location.href = '${ctx}/workOrder/query.action?falg=3';
									parent.leftFrame.location.href = '${ctx}/pages/workorder/workOrder_menu.jsp';
								});
				}else{
					 html="0";
						$("#apply1").html(html);
					
				}
			}
		});
	}
	//查询磁盘空间是否占满。
	function checkDisk(msg){
				if (msg == 'true') {
					$("#ShowDiv1").html("");
				} else if(msg == 'false') {
					$("#ShowDiv1").html("磁盘空间已满，平台停止接收事件!");
				}else{
					$("#ShowDiv1").html("用户已在其他地方登录或平台已停止!");
				}
		
	}
	var startWAV;
	var checkNum=1;
	function getalertMessage() {
		$
				.ajax({
					type : "POST",
					url : "${ctx}/alertMessage/queryCurrent.action",
					data : "",
					dataType : 'text',
					success : function(msg) {
						if (msg != "0") {
							count = Number(msg);
                            //alert(count);
							$('#AlarmCount').html("( " + count + " )");
							if(checkNum==1){
							startWAV=setInterval("play()", 2000);
							parent.mainFrame.isCloseAlertMessage();
							checkNum++;
							}else{
								checkNum=1;	
							}
							$('#AlarmCount').addClass("hand");
							parent.start();

							$('#AlarmCount')
									.click(
											function() {
												parent.mainFrame.location.href = '${ctx}/alertMessage/alertMessageQuery.action';
												parent.leftFrame.location.href = '${ctx}/events/queryEventsTrre.action';
											});

						} else {
							clearInterval(startWAV);
							$('#AlarmCount').html("( 0 )");
							parent.stopBlinkNewMsg();
						}
					}
				});
	}
	
	function play() {
		document.all.bgs.src = "${ctx}/images/BEEP1.WAV";
	}
	
	/* function blink() {
		//$('#AlarmCount').css("visibility", "visible");
		//$('#soccer').show(2000);
		var temp = ($('#AlarmCount').css("visibility") == "hidden") ? "visible"
				: "hidden";
		if (temp == "hidden") {
			$('#AlarmCount').css("visibility", "hidden");
		} else {
			$('#AlarmCount').css("visibility", "visible");
		}
		//counter += 1;
		//if (flag == 1) {
		//alert("1243");
		setTimeout("blink()", 500);
		//} else {
		//$('#soccer').css("visibility", "visible");
		//} 
	} */
	/* function showAlarm() {
	
		parent.mainFrame.location.href = '${ctx}/alertMessage/alertMessageQuery.action';

		count = 0;
		//parent.leftFrame.location.href=''
	} */

	function reload() {

		$("#mack").addClass("ui-widget-overlay");
	}
		
		function LoadTimer(){
			
		    var secs = 120; //倒计时的秒数
			for(var i=secs ; i>=0;i--)
			{
				
				window.setTimeout('doUpdate(' + i + ')', (secs-i) * 1000);
			}
		}
		function doUpdate(num)
		{	
			document.cookie="saveTime="+num;//把当前的数字放在cookie里。
			//alert(document.cookie);
			document.getElementById("ShowDiv").innerHTML = '将在'+num+'秒后自动退出' ;
			var num1=num-1;
			//alert(num1);
			if(num1 == 0) {
				delCookie("saveTime");//清除cookie
				location.href = '${ctx}/login/logout.action';
				
				
			}
		
		}

	function cancel() {
		
		$("#mack").removeClass("ui-widget-overlay");
	}

	function NewMessage(){
		
	}
	var m ; //定时更新记时器
	var ShowDiv; //字时更新对象
	var refres; //字时更新对象
	var sourceType; //查询条件类型
	function exits(){
		//delCookie("saveTime");
		//获取指定cookie的值
		var times=document.cookie;
		var timess=times.split(";");
		for(var i=0;i<timess.length;i++){
			var t=timess[i].split("=");
			if(t[0]=="saveTime"){
				for(var ii=t[1] ; ii>=0;ii--)
				{
					document.cookie="saveTime="+ii;
					if(ii>0){
						window.setTimeout('doUpdate(' + ii + ')', (t[1]-ii) * 1000);
					}
					
				}
			}
		}
	}
	//删除cookie
	function delCookie(name)//删除cookie
	{
		
		 //alert("cesjo1");
		  var date=new Date();
          date.setTime(date.getTime()-10000);
          document.cookie=name+"=v; expire="+date.toGMTString();
	      //alert(document.cookie);
	}
	
	function updateTime(){
		time.setSeconds(time.getSeconds()+1);
		$("#time").html(time.toLocaleString());
	}
	setInterval("updateTime()", 1000);
	
</script>
</head>

<body onload="exits()">
	<center>
		<!-- banner area -->
		<table width="99%" height="65px" border="0" cellpadding="0"
			cellspacing="0" background="${ctx}/images/topRbj.jpg">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						height="65px;">
						<tr>
							<td><table width="100%" border="0" cellspacing="0"
									cellpadding="0">
									<tr>
										<td valign="top" align="left"><img src="${top}" alt=""
											name="topLogo" width="342" height="65" id="topLogo" style="" />
											<input type="hidden" id="savetime" /> <input type="hidden"
											name="dateTime" id="dateTime" value="${dateTime}" /> <span
											style="position: absolute;margin-top: 40px;" id="sysTime">
												<table>
												
													<tr>
														<td>系统当前时间：</td>
														<td id="time"></td>
														<td id="date"></td>
													</tr>
												</table> </span>
										</td>
										<td align="right"><label id="ShowDiv1"
											style="color: red; font-size: 20px; "></label>
										</td>

										<td align="right"><label id="ShowDiv"
											style="color: red; font-size: 20px; "></label>
										</td>



										<td align="right">
											<div class="topinfo">
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0">
													<tr>
														<td class="topL1">
															<table width="75%" border="0" align="right"
																cellpadding="0" cellspacing="0">
																<tr>
																	<td align="left">帐&nbsp;&nbsp;号：${sessionScope.SOC_LOGON_USER.userLoginName}</td>
																</tr>
																<tr>
																	<td align="left"
																		title="${sessionScope.SOC_LOGON_ROLE.roleNames}" nowrap="nowrap">角&nbsp;&nbsp;色：${sessionScope.SOC_LOGON_ROLE.roleName}</td>
																</tr>
															</table>
														</td>
														 <td class="topL4">
		                            	<table width="100%" border="0">
		                            		<tr align="left">
		                            			<td >未处理工单：<span id="apply">0</span></td>
		                            		</tr>
		                            		<tr align="left">
		                            			<td >已派发工单：<span id="apply1">0</span></td>
		                            		</tr>
		                            	</table>
		                            </td>

														<td><input name="" type="button" class="topL2"
															value="个人设置"
															onclick="parent.mainFrame.location.href='${ctx}/user/personSeting.action';" />
														</td>
														<td><input name="input" type="button" class="topL3"
															value="注销" onclick="logout()" /></td>
													</tr>
												</table>
											</div>
										</td>
									</tr>
								</table></td>
							<td width="1%">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!-- banner area -->

		<table width="99%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="frameDh">
<div id="menu_bg">
<div id="left">
<a href="javascript:void(0)" onmousedown="moveLeft()"> << </a></div>
<div id="menu" height="31px">
					<ul>

						<li style="width:70px"><a href="javascript:linkTo('home_page');">首页</a></li>
						<li style="width:5px"><span>|</span> <%
 	String permissionIds = (String) session
 			.getAttribute("SOC_LOGON_PERMISSIONS");
						System.out.print(permissionIds);
 	if (permissionIds.indexOf(",4,") != -1
 			|| permissionIds.indexOf(",3,") != -1
 			|| permissionIds.indexOf(",5,") != -1) {
 %>
						
						<li><a href="javascript:linkTo('user');">用户管理</a></li>
						<li style="width:1px"><span>|</span>
						</li>
						<%
							}

							if (permissionIds.indexOf(",7,") != -1) {
						%>
						<li><a href="javascript:linkTo('asset');">资产管理</a></li>
						<li style="width:1px"><span>|</span>
						</li>
						<%
							}

							if (permissionIds.indexOf(",9,") != -1) {
						%>
						<li><a href="javascript:linkTo('monitor');">监控管理</a></li>
						<li style="width:1px"><span>|</span>
						</li>
						<%
							}
							if (permissionIds.indexOf(",11,") != -1) {
						%>
						<li><a href="javascript:linkTo('events');">事件管理</a></li>
						<li style="width:1px"><span>|</span>
						</li>
						<%
							}
							if (permissionIds.indexOf(",13,") != -1
									|| permissionIds.indexOf(",14,") != -1) {
						%>
						<li><a href="javascript:linkTo('audit');">审计管理</a></li>
						<li style="width:1px"><span>|</span>
						</li>

						<%
							}
							if (permissionIds.indexOf(",16,") != -1
									|| permissionIds.indexOf(",17,") != -1
									|| permissionIds.indexOf(",18,") != -1
									|| permissionIds.indexOf(",19,") != -1) {
						%>
						<li><a href="javascript:linkTo('report');">组态报表</a>
						</li>
						<li style="width:1px"><span>|</span></li>

						<%
							}

							if (permissionIds.indexOf(",21,") != -1
									|| permissionIds.indexOf(",22,") != -1
									|| permissionIds.indexOf(",23,") != -1) {
						%>
						<li><a href="javascript:linkTo('rules');">规则管理</a></li>
						<li style="width:1px"><span>|</span></li>
						<%
							}
							if (permissionIds.indexOf(",25,") != -1) {
						%>
						<li><a href="javascript:linkTo('risk');">风险管理</a></li>
						<li style="width:1px"><span>|</span></li>
						<%
						  }
						    if (permissionIds.indexOf(",80,") != -1)
						    {
						%>
						<li><a href="javascript:linkTo('securityPolicy');">安全策略管理</a></li>
						<li style="width:1px"><span>|</span></li>
						<%
							}
							if (permissionIds.indexOf(",27,") != -1
									|| permissionIds.indexOf(",28,") != -1
									|| permissionIds.indexOf(",29,") != -1
									|| permissionIds.indexOf(",30,") != -1
									|| permissionIds.indexOf(",31,") != -1) {
						%>
						<li><a href="javascript:linkTo('repository');">知识库</a></li>
						<li style="width:1px"><span>|</span></li>
						
						<%
							}
							if (permissionIds.indexOf(",33,") != -1) {
						%>
						<li><a href="javascript:linkTo('workOrder');">工单管理</a></li>
						<li style="width:1px"><span>|</span></li>
						<%
							}
							if (permissionIds.indexOf(",56,") != -1
									|| permissionIds.indexOf(",57,") != -1
									|| permissionIds.indexOf(",58,") != -1
									|| permissionIds.indexOf(",59,") != -1
									|| permissionIds.indexOf(",60,") != -1
									|| permissionIds.indexOf(",61,") != -1
									|| permissionIds.indexOf(",62,") != -1
									|| permissionIds.indexOf(",63,") != -1
									|| permissionIds.indexOf(",64,") != -1) {
						%>
						<li style="width: 100px;"><a style="width: 100px;"
							href="javascript:linkTo('rank');">等级保护合规管理</a></li>
						<li style="width:1px"><span>|</span>
						</li><%
							}if (permissionIds.indexOf(",83,") != -1
							|| permissionIds.indexOf(",84,") != -1
							|| permissionIds.indexOf(",85,") != -1
							|| permissionIds.indexOf(",86,") != -1
							|| permissionIds.indexOf(",87,") != -1
					) {
				%>
						<li><a href="javascript:linkTo('topo');">拓扑管理</a></li>
						<li style="width:1px"><span>|</span>
						</li>
						<%
							}
							if (permissionIds.indexOf(",34,") != -1
									|| permissionIds.indexOf(",35,") != -1
									|| permissionIds.indexOf(",36,") != -1
									|| permissionIds.indexOf(",38,") != -1
									|| permissionIds.indexOf(",39,") != -1
									|| permissionIds.indexOf(",40,") != -1
									|| permissionIds.indexOf(",41,") != -1
									|| permissionIds.indexOf(",43,") != -1
									|| permissionIds.indexOf(",44,") != -1
									|| permissionIds.indexOf(",45,") != -1
									|| permissionIds.indexOf(",47,") != -1
									|| permissionIds.indexOf(",48,") != -1
									|| permissionIds.indexOf(",49,") != -1
									|| permissionIds.indexOf(",50,") != -1
									|| permissionIds.indexOf(",51,") != -1
									|| permissionIds.indexOf(",52,") != -1
									|| permissionIds.indexOf(",66,") != -1
									|| permissionIds.indexOf(",70,") != -1
									|| permissionIds.indexOf(",71,") != -1

							) {
						%>
						<li><a href="javascript:linkTo('showSysInfo');">系统设置</a></li>
						<li style="width:1px"><span>|</span></li>
							<%
						    }
						    if (permissionIds.indexOf(",80,") != -1)
						    {
						%><%--
						<li><a href="javascript:linkTo('satellite');">审计系统</a></li>
						<li style="width:1px"><span>|</span></li>
					
						--%><%
						
						
							}
							if (permissionIds.indexOf(",74,") != -1)
							{
						%>
						<li><a href="javascript:linkTo('oem');">修改OEM</a></li>
						<li style="width:1px"><span>|</span></li>

						<%
							}
							if (permissionIds.indexOf(",69,") != -1) {
						%>
						<li style="width: 95px;"><a
							href="javascript:linkTo('addRules');">添加解析规则</a></li>
						<%
							}
						%>
						 <li><a href="javascript:linkTo('about');">关于产品</a></li>
					</ul>
</div>
<div id="right"><a href="javascript:void(0)" onmousedown="moveRight()">>></a></div>
</div>
				</td>
				<td width="10%" class="frameDh" align="right">
					<%
						if (permissionIds.indexOf(",11,") != -1) {
					%> <input type="hidden" value="1" id="alertFlag" />
					<div style="align:center">
						<span style="color:#ffffff">|</span> <span style="color:#00F5FF">告警信息</span>
						<span id="AlarmCount" style="visibility:visible;color:#00F5FF"></span>
						<bgsound loop="1" id="bgs" />
					</div> <%
 	}
 %>
				</td>
			</tr>

		</table>
		<script language="javascript">
	      var flag = $("#alertFlag").val();
	   //alert(flag);
	   if(flag=="1")
	   {
	       //alert("kaishi1");
	       getAlertTrance();
	   }
	</script>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			style="display: none">
			<tr>
				<td class="frameDhfoot">
					<ul>
						<li></li>
						<li></li>
						<li><a href="#"></a></li>
						<li><a href="#"></a></li>
					</ul>
				</td>
			</tr>
		</table>
	</center>
	<div class="ui-overlay">
		<div id="mack"></div>
	</div>
	<script type='text/javascript'>
window.onload=function(){
	exits();
}
//function $(obj){return document.getElementById(obj)}
var maxWidth=document.getElementById("menu").getElementsByTagName("ul")[0].getElementsByTagName("li").length*105;
var isScroll=false;
var modiLeft;

var targetx = 200;//一次滚动距离
var dx;
var a=null;
function moveLeft(){
var le=parseInt(document.getElementById("menu").scrollLeft);
if(le>200){
targetx=parseInt(document.getElementById("menu").scrollLeft)-200;
}
else{targetx=parseInt(document.getElementById("menu").scrollLeft)-le-1}
scLeft();
}
function scLeft(){
dx=parseInt(document.getElementById("menu").scrollLeft)-targetx;
document.getElementById("menu").scrollLeft-=dx*.3;

clearScroll=setTimeout(scLeft,50);
if(dx*.3<1){clearTimeout(clearScroll)}
}
function moveRight(){
var le=parseInt(document.getElementById("menu").scrollLeft)+200;
var maxL=maxWidth-600;
if(le<maxL){
targetx=parseInt(document.getElementById("menu").scrollLeft)+200;
}
else{targetx=maxL}
scRight();
}
function scRight(){
dx=targetx-parseInt(document.getElementById("menu").scrollLeft);
document.getElementById("menu").scrollLeft+=dx*.3;
a=setTimeout(scRight,50);
if(dx*.3<1){clearTimeout(a)}
}

</script>
</body>
</html>

