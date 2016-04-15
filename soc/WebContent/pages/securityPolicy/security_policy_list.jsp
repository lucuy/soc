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
 
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
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
<script type='text/javascript' src="${ctx}/scripts/filterpage.js"></script>

<script language="javascript">

	var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
	//模糊查询
	function query() {
	    //去除输入文字的首尾空格
		var keyword = $.trim($("#keyword").val());//模糊
		/* var order = $("#order").val();//最近更新 */
		if($('#keyword').val().length>50){
			alert("搜索长度不能大于50");
			$('#keyword').val('');
			$('#keyword').focus();
			return ;
		}
		if(!rege.test($('#keyword').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#keyword').val('');
			$('#keyword').focus();
			return;
		}
		location.href = "${ctx}/securityPolicy/query.action?keyword="
				+ encodeURIComponent(encodeURIComponent(keyword));
	}

	/* 执行标记删除的功能*/
	function deleteQuery() {
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "") {
				ids += "," + $(this).val();
			} else {
				ids = $(this).val();
			}
		});
		//alert($("[name=ids]:checkbox:checked").size());
		if ($("[name=ids]:checkbox:checked").size() < 1) {
			alert("请至少选择一个策略信息...");
			return;
		}
		if (confirm("确认执行操作吗？")) {
			location.href = "${ctx}/securityPolicy/deleteSecurityPolicy.action?ids="
					+ ids;

		}
	}

	$(document).ready(function() {
		$("#keyword").keydown(function(event) {
			if (event.keyCode == 13) {
				query();
			}
		});

		// Dialog			
		$('#dialog-extQuery').dialog({
			autoOpen : false,
			width : 450,
			height : 220,
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
		$('#deviceByNameDlg-open').dialog({
			autoOpen : false,
			width : 450,
			height : 450,
			buttons : {
				"确定[Enter]" : function() {
				var ids = "";
		$("[name=ASSET_NAME1]:checkbox:checked").each(function() {
			if (ids != "") {
				ids += "," + $(this).val();
			} else {
				ids = $(this).val();
			}
		});
	
		if ($(":checkbox[checked = true]").size() < 1) {
			alert("请至少选择一个策略信息...");
			return;
		}
		if (confirm("确认执行操作吗？")) {
			$(this).dialog("close");
		  parent.frames[0].reload(); 
          parent.frames[1].reload(); 
          parent.frames[3].reload();
          parent.frames[4].reload();
	$.post("${ctx}/securityPolicy/issuedPolicy.action?ids="+ ids+"&policyId="+relPolicyName,{},function(data){
	alert(data);
	     parent.frames[0].cancel();
         parent.frames[1].cancel();
         parent.frames[3].cancel();
         parent.frames[4].cancel();
         //alert(policyMemo);
         //去掉弹出框的多选框的选择状态
         	$("[name=ASSET_NAME1]:checkbox:checked").each(function() {
			
		$(this).attr('checked',false);
			
		});
		});

		}
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		
		
		
		
		
		//查询结果的div
				$('#policyNameAsset-open').dialog({
			autoOpen : false,
			width : 450,
			height : 460,
			buttons : {
				"确定[Enter]" : function() {
				$(this).dialog("close");
				}
			}
		});
		
				// 结果执行内容			
		$('#policyScript-open').dialog({
			autoOpen : false,
			width : 450,
			height : 220,
			buttons : {
				"确定[Enter]" : function() {
			
					$(this).dialog("close");
				//	$('#policyNameAsset-open').dialog('close');
						$('#policyNameAsset-open').dialog("open");
				}
			}
		});
		
		
		
	    $("#listTable").tablesorter();
	});

	$("#chkAll").live("click", function(event) {
		if ($("#chkAll").hasClass('not_checked')) {
			$("#chkAll").removeClass('not_checked');
			$(".check-box").attr('checked', true);
		} else {
			$("#chkAll").addClass('not_checked');
			$(".check-box").attr('checked', false);
		}
	});

	$(document).click(function(event) {
		//shiftForCheckBoxFun(event);
		destroyOverDlg();
	});

	function overDlg(e, header, content, num) {
		$('#blk_header').html(header);
		$('#blk_content').html(content);

		createOverDiv(e, 10, 10, $('#blk').html());
	}

	function extQueryDlg() {
		$('#dialog-extQuery').dialog('open');
		
	}
	var relPolicyName =  ""; //策略文件的名字 下发的时候传到后台
	var policyName =  ""; //策略名字
	function extFileDlg(relPolicyNamePar) {
	relPolicyName = relPolicyNamePar;
		$('#deviceByNameDlg-open').dialog('open');
	}
	//查看下发执行结果的的对话框
	var  policyResultArray;
	function extResult(policyId) {
//	policyName = relPolicyNamePar;
		//$.post("${ctx}/securityPolicy/policyResult.action?assetName="+assetName+"&policyMemo="policyName,{},function(data){
		$.post("${ctx}/securityPolicy/policyResult.action?policyId="+policyId,{},function(data){
		if(data){
		policyResultArray = data;
         //去掉弹出框的多选框的选择状态
        // 	$("[name=ASSET_NAME1]:checkbox:checked").each(function() {
		//$(this).attr('checked',false);
		//});
		var tableBody = $("#policyNameAssetBody");
		tableBody.html("");
		tableBody.html("<tr height=\"28\" id =\"policyNameAssetTr\"><td width=\"420px\" align=\"center\" class=\"biaoti\">设备名称</td></tr>")
				for (var i = 0; i < data.length; i++) { 
    var obj = data[i]; 
    var assetName = obj.assetName; // 资产的名字
    var assetNameFileContext = obj.assetNameFileContext; //脚本结果文件内容
    
    tableBody.html( tableBody.html()+"<tr>	<td valign=\"middle\"   style='text-align:center'  class=\"td_t\" width=\"400px\"><a"+
						" href=\"javascript:void(0);\" "+
					"	onclick=\"showScriptResult('"+assetName+"')\">资产名："+assetName+" IP : "+obj.assetIp+"</a></td></tr>");
}
		
		

	$('#policyNameAsset-open').dialog('open');
			goPage(1,10,'dlg-table-asset-policy','dlg-table-asset-policy-page');
		}else{
		alert("策略未下发!");
		}
		
		});
	
	}
	
	//查看脚本执行结果的方法
	function showScriptResult(assetName) {
//	policyName = relPolicyNamePar;
					for (var i = 0; i < policyResultArray.length; i++) { 
    var obj = policyResultArray[i]; 
    var assetNameTmp = obj.assetName; // 资产的名字
    if(assetNameTmp ==assetName ){
    $("#policyScript-open").html(obj.assetNameFileContext);
    }
    
}$('#policyNameAsset-open').dialog('close');
		$('#policyScript-open').dialog('open');
	
	}
	
	
	
	function extQuery() {
		if($('#empName').val().length>50){
			alert("策略名称长度不能大于50");
			$('#empName').val('');
			$('#empName').focus();
			return ;
		}
		if(!rege.test($('#empName').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#empName').val('');
			$('#empName').focus();
			return;
		}
		if($('#timeDescribe').val().length>255){
			alert("描述长度不能大于255");
			$('#timeDescribe').val('');
			$('#timeDescribe').focus();
			return ;
		}
		$('#timePolicyName').val($('#empName').val());
		$('#timePolicyMemo').val($('#timeDescribe').val());
		$('#timePolicyStatus').val($('#timePolicySates').val());
		$('#timePolicyEffectWay').val($('#timePolicyEffectWay1').val());
		/* $('#selGroupName').val($('#groupName').val());
		$('#selLoginName').val($('#loginName').val());
		$('#selCreateIP').val($('#createIP').val()); */
		$('#queryForm').submit();
	}
	$(function(){
		//前台分页 policy
		goPage(1,10,'dlg-table-asset','dlg-table-asset-page');
	
	});
	//遮罩的方法开始
	    var mack;
	 function reload() {
	    document.onmousedown=ContextMenu;
	    mack = document.getElementById("mack");
	    mack.className="ui-widget-overlay";
	    //$("#mack").addClass("ui-widget-overlay");
	    mack.style.height=document.documentElement.clientHeight;
        //alert(document.body.clientWidth);
      // $("#mack").css("height",document.documentElement.clientHeight);
    }
    	function ContextMenu(){
      if (event.button==2 || event.button==1) {  
             alert("加载数据中...");
             return false;
        }
  }
  function cancel() {
		document.onmousedown = null;
		 mack.className="";
	    mack.style.height=0;
		//$("#mack").removeClass("ui-widget-overlay");
		//$("#mack").css("height",0);
	}
	//遮罩的方法结束
	//点击链接选择他左面的box
function checkLeftBox(id){
//	alert(id);
	if ($("#"+id).attr("checked") == false) {	
	$("#"+id).attr("checked",true);	
	}else{
	$("#"+id).attr("checked",false);	
		}
	};
</script>
</head>

<body style="margin-top:2px;">
<div class="ui-overlay">
        <div id="mack"></div>
     
    </div>
	<s:form action="query.action" namespace="/sucuityPolicy" method="post"
		theme="simple" id="timeForm" name="timeForm">
		<input type="hidden" name="timePolicyName" value="${timePolicyName}" />
		<input type="hidden" name="timePolicyMemo" value="${timePolicyMemo}" />
		<input type="hidden" name="timePolicyStatus" value="${timePolicyStatus}" />
		<input type="hidden" name="timePolicyEffectWay" value="${timePolicyEffectWay}" />
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<!-- 空行 -->
			<!-- <tr>
				<td><s:hidden name="time" id="time" /> <s:hidden
						name="passwordPolicyStatus" id="passwordPolicyStatus" /> <s:hidden
						name="order" id="order" />
				</td>

			</tr> -->

			<tr>
				<td>
				<div class="box">
				
				<div class="right">
				   
				   <input type="button" value="添加" class="btnstyle" style="margin-right:5px;margin-top:-2px"
				   onclick="location.href='${ctx}/securityPolicy/edit.action';"/>
				   
				   <input type="button" value="删除" style="margin-right:12px;margin-top:-2px" class="btnstyle" onclick="deleteQuery();"/>
				   </div>
				
				   <span class="kuaiju">快速搜索</span>
				   
				   <input type="text" id="keyword" value="${keyword}" name="keyword" class="jianju" style="height: 15px;" />
				   
				   <img src="${ctx}/images/search.jpg" onclick="query();" style="margin-left:5px">
				   
				   <a href="#" class="jianju" onclick="extQueryDlg();">高级</a>
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
									class="tab2" id="listTable">
									<thead>
										<tr height="28" class="biaoti">
											<td width="4%" class="biaoti"><input type="checkbox"
												id="chkAll" name="chkAll" class="check-box not_checked" />
											</td>
												<!-- <th width="8%" class="biaoti">编号</th> -->
											<th width="10%" class="biaoti">策略名称</th>
											<th width="10%" class="biaoti">创建者</th>
											<th width="10%" class="biaoti">修改者</th>
											<th width="12%" class="biaoti">创建时间</th>
											<th width="12%" class="biaoti">修改时间</th>
											<th width="*" class="biaoti">描述</th>
											<th width="9%" class="biaoti">下发文件</th>
											<th width="9%" class="biaoti">查看结果</th>
										</tr>
									</thead>
									<tbody>
										<%-- 	<c:set value="${num}" var="num" /> --%>
										<s:property value="#session.XXX" />
										
										<s:iterator value="securityPolicyList" status="stat">
											
											<tr>
												<td class="biaocm" valign="middle"><input
													type="checkbox" name="ids" id="${id}"
													value="${id}" class="check-box" /></td>
												<td valign="middle" class="td_list_data"><a
													href="${ctx}/securityPolicy/edit.action?policyId=${id}">${policyName}</a>
												</td>
												<td valign="middle" align="center" class="td_list_data">
												${createUsername }
												</td>
												<td valign="middle" class="td_list_data" align="center">
												${modifyUsername }
												</td>
												<td valign="middle" align="center" class="td_list_data">${createTime}</td>
													<td valign="middle" align="center" class="td_list_data">${modifyTime}</td>
												<td valign="middle" class="td_list_data" align="center"><c:out value="${desc}"></c:out></td>
											<td valign="middle" class="td_list_data"><a style="cursor: pointer ;"
													onclick="extFileDlg('${id}');">下发</a>
												</td>
													<td valign="middle" class="td_list_data"><a style="cursor: pointer ;"
													onclick="extResult('${id}');">查看结果</a>
												</td>
											</tr>
											
										</s:iterator>
									</tbody>
									<tr>
										<td colspan="9" width="100%"><jsp:include
												page="../commons/page.jsp"></jsp:include></td>
									</tr>
								</table>
							</div>
							<!-- information area -->
						</div>
					</div></td>
			</tr>
		</table>
	</s:form>

	<!-- ext query from -->
	<s:form action="query.action" namespace="/timePolicy" method="post"
		theme="simple" id="queryForm" name="queryForm">
		<!-- 高级查询用 -->
		<s:hidden name="timePolicyName" id="timePolicyName" />
		<s:hidden name="timePolicyMemo" id="timePolicyMemo" />
		<s:hidden name="timePolicyStatus" id="timePolicyStatus" />
		<s:hidden name="timePolicyEffectWay" id="timePolicyEffectWay" />
		<!-- <s:hidden name="selpasswordPolicyDESC" id="selpasswordPolicyDESC" />
		<s:hidden name="selRealName" id="selLoginName" />
		<s:hidden name="selCreatorIp" id="selCreatorIp" /> -->
	</s:form>

	<!-- over layer -->
	<div id="blk" style="display:none;">
		<div class="blk">
			<div class="head">
				<div class="head-right"></div>
			</div>
			<div class="main">
				<h2 id="blk_header">#header#</h2>
				<div id="blk_content">#content#</div>
			</div>
			<div class="foot">
				<div class="foot-right"></div>
			</div>
		</div>
	</div>

	<!-- ui-dialog -->
	<div id="dialog-extQuery" title="高级查询">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr>
				<td width="25%">策略名称:</td>
				<td><input id="empName" name="empName" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr>
				<td>描述:</td>
				<td><input id="timeDescribe" name="timeDescribe" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
		
		</table>
	</div>
		<!-- ui-dialog -->
	
</body>
	<!-- 设备名称选择的dialog -->
	<div id="deviceByNameDlg-open" title="设备名称选择">
		<table id="dlg-table-asset" width='96%' cellspacing='1' cellpadding='0'
			border='0' align='center' class="tab2">
			<tr height="28">
				<td width="6%" align="center" class="biaoti">
				</td>
				<td width="400px" align="center" class="biaoti">设备名称</td>
			</tr>
			<c:set value="1" var="root" />

			<s:iterator value='assetListDiv' status='stat'>
				<tr>
					<td valign="middle" align="center" class="biaocm" width="6%"><input
						type="checkbox" name="ASSET_NAME1" class="check-box-deviceName"
						id="deshebeiName-${assetId}" value="${assetId}"
						onclick="event.cancelBubble=true;" /> <input type="hidden"
						value="0" />
					</td>

					<td valign="middle" class="td_t" width="400px"><a
						style='color: #555555' href="javascript:void(0);"
						onclick="checkLeftBox('deshebeiName-${assetId}')">${assetName}&nbsp;&nbsp;&nbsp;&nbsp;IP地址：${assetMac}</a>
						<input type="hidden" name="hid-dlgName-user"
						id="hid-dlgName-user-${assetId}" value="${assetName}" />
					</td>

					<c:set value="${root+1}" var="root" />
				</tr>
			</s:iterator>
		</table>
		<table width="80%" align="right">
	<tr>
		<td>
			<div id="dlg-table-asset-page" name="barcon"></div>
	</td>
</tr>
</table>
	</div>
	
	
		<!-- 查看结果列表 -->
	<div id="policyNameAsset-open" title="查看结果">
		<table id="dlg-table-asset-policy" width='96%' cellspacing='1' cellpadding='0'
			border='0' align='center' class="tab2">
			<tbody id= "policyNameAssetBody" ></tbody>
		</table>
		<table width="80%" align="right">
	<tr>
		<td>
			<div id="dlg-table-asset-policy-page" name="barcon"></div>
	</td>
</tr>
</table>
	</div>
			<!-- 查看脚本执行结果的div -->
	<div id="policyScript-open" title="执行结果">

	</div>
	
</html>
