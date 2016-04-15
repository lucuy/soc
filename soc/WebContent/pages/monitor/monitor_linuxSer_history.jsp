<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@page import="com.soc.model.user.User"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<title>历史linux服务</title>
<link href="${ctx}/images/favicon.ico" rel="shortcut icon" />
<link href="${ctx}/images/favicon.ico" rel="Bookmark" />
<meta http-equiv="Pragma" content="no-cache" />
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet" type="text/css">
<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet"
	type="text/css"> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery-latest.js"></script>
<script type="text/javascript"
	src="${ctx }/scripts/jquery.tablesorter.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>
<script type="text/javascript" src="${ctx }/scripts/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" >
	$(document).ready(function() {
		$("#keyword").keydown(function(event) {
			if (event.keyCode == 13) {
				query();
			}
		});

			//同步账户
		$('#dialog-account').dialog({
			autoOpen : false,
			width : 400,
			height : 200,
			buttons : {
				"确定[Enter]" : function() {
					queryAll();
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});

		$("#myTable").tablesorter();

	});

	
	function accountShow() {
		$("#startTime").val("");
		$("#endTime").val("");
		$('#dialog-account').dialog('open');
	}
	function queryAll(){
		var beginTime = $.trim($("#startTime").val());
        var endTime = $.trim($("#endTime").val());
       
        if((beginTime != "" && endTime == "") ||(beginTime == "" && endTime != "")||(beginTime == "" && endTime == ""))
        {
        	 alert("请输入完整");
           return false;
        }
        if(beginTime != "" && endTime != "")
        {
          var dateTime = new Date();
          var year = dateTime.getFullYear();
          var month = dateTime.getMonth()+1;
          var date = dateTime.getDate();
          var dayTime = year+"/"+month+"/"+date;
          if(Date.parse(splitDate(beginTime)) > Date.parse(splitDate(endTime)))
          {
        	  alert("请输入时间段应从小到大");
             return false;
          }
          else if(Date.parse(splitDate(beginTime)) > Date.parse(dayTime))
          {
              $("#beginTime").val(year+"-"+month+"-"+date);
              alert("最小日期不能超过今日");
              return false;
          }
          else if(Date.parse(splitDate(endTime)) > Date.parse(dayTime))
          {
              $("#endTime").val(year+"-"+month+"-"+date);
              alert("最大日期不能超过今日");
              return false;
          }
        }
        var ip = $("#ip").val();
    	var falg=$("#falg").val();
    	var biaozhi=$("#biaozhi").val();
        location.href='${ctx}/monitorGroup/queryMonitorAPM.action?ip='+ip+'&falg='+falg+'&startTime='+beginTime+'&endTime='+endTime+'&biaozhi='+biaozhi;
        $('#dialog-account').dialog("close");
	}

	//格式化日期
	  function splitDate(date)
	    {
	        var dates = date.split("-");
	        var time = dates[0]+"/"+dates[1]+"/"+dates[2];
	        return time;
	    }
	
	

</script>
</head>

<body style="margin-top:2px;">
	
	<s:form action="queryMonitorAPM.action" namespace="/monitorGroup" method="post"
		theme="simple" id="userForm" name="userForm">
		<input type="hidden" id="ip" name="ip"
			value="${ip}" />
		<input type="hidden" name="startTime" value="${startTime}" />
		<input type="hidden" name="endTime"
			value="${endTime}" />
			<input type="hidden" id="falg" name="falg"
			value="${falg}" />
			<input type="hidden" id="biaozhi" name="biaozhi"
			value="${biaozhi}" />
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<!-- 空行 -->
			<tr>
				<td></td>
			</tr>
			<tr>
				<td>
					<div class="box">

						<div class="right">
					   <input type="button" value="高级查询" class="btnstyle"
								style="margin-right:5px;margin-top:-2px"
								onclick="accountShow();" />  
							
						</div>

					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="sbox">
						<div class="cont">
							<!-- information area -->
							<div id="dataList">
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="tab2" id="myTable">
									<thead>
										<tr height="28" class="biaoti">
											
											<th width="50%" class="biaoti">
											服务内容</th>
											
											
											<th width="50%" class="biaoti">
											接收时间</th>
										</tr>
									</thead>
									<tbody>
										
										<s:iterator value="linuxServiceMsgs" status="stat">
											<tr>
												
												<td valign="middle">
													${content}
												</td>
												
												<td valign="middle" class="td_list_data"><s:date
										name="fromDate" format="yyyy-MM-dd HH:mm:ss" /></td>
											</tr>
											
										</s:iterator>
									</tbody>
									<tr>
										<td colspan="2" width="100%"><jsp:include
												page="../commons/page.jsp"></jsp:include><br></td>
									</tr>

								</table>
							</div>
							<!-- information area -->
						</div>
					</div></td>
			</tr>
		</table>
	</s:form>

	
	<div id="dialog-account" title="高级查询">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr>
				<td width="25%">开始时间:</td>                        
				<td><input  class="Wdate" type="text" onClick="WdatePicker()" id="startTime" 
					style="width:250px;" onkeypress="if(event.keyCode==13)accountAjax();" />
				</td>
			</tr>
			<tr>
				<td>结束时间:</td>
				<td><input class="Wdate" type="text" onClick="WdatePicker()" id="endTime" 
					style="width:250px;" onkeypress="if(event.keyCode==13)accountAjax();"  />
				</td>
				<td><font color="red" id="timeEror"></font></td>
			</tr>
			
		</table>
	</div>

</body>
</html>
