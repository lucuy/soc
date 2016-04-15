<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
<head>
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript'
	src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
<link type="text/css" href="${ctx}/styles/jquery-ui-1.8.16.custom.css"
	rel="stylesheet" />
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>

<script type="text/javascript"
	src="${ctx}/scripts/jquery-ui-1.8.16.custom.min.js"></script>
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
    type="text/css">
<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<%-- <script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script> --%>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>

<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>

<%-- <script type='text/javascript'
    src="${ctx}/scripts/jquery-ui.custom.min.js"></script> 
<script type='text/javascript' src="${ctx}/scripts/treeview.js"></script>--%>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>



<style type="text/css">
.row {
	text-align: right;
	line-height: 29px;
	padding: 5px 0px 5px 0px;
	width: 20%;
}

.check_di {
	background: #ffffff;
	height: 45px;
	width: 201px;
	font-size: 12px;
}

.check_zhong {
	background: #ffff66;
	height: 45px;
	width: 165px;
}

.check_gao {
	background: #ff6666;
	height: 120px;
	width: 209px;
}

.qEvents {
	margin: 1px 1px 0px 1px;
}

.qEvents tr td {
	margin: 10px 0px 10px 0px;
	line-height: 29px;
}

.display {
	display: none;
}

.datasourceUl {
	list-style: none;
}

.hand {
	cursor: hand;
	background: #ccccff;
}

.back {
	background: #FFFFFF;
}
</style>
<script language="javascript">
    jQuery(document).ready(function() {
       
         
    });
    var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
    var assetFlag ;
    function checkName(){
 	   var trendName =$("#trendName").val();
 	   var queryEventsName = $.trim($("#customQueryEventsName").val());
 	   if(queryEventsName==null||queryEventsName==''){
 		  $("#nameError").html("* 填写名称");
 	   }else{
 	  if(trendName==queryEventsName){
 		  assetFlag= true;
 	  }else{
 		  if(!rege.test(queryEventsName)){
 			 $("#nameError").html("名称中有非法字符，请重新输入！");  
 			assetFlag =false;
 		  }else{
 	   $
 		.ajax({
 			type : "post",
 			url : "${ctx}/customTrend/checkName.action",
 			async : false,
 			dataType : "json",
 			data : "&trendName=" + queryEventsName,
 			success : function(result) {
 				if(result){
 					 $("#nameError").html(" 名称已经被占用，请更换！");	
 					assetFlag =false;
 				}else{
 					 $("#nameError").html("*");
 					 assetFlag= true;
 				}
 				
 			}
 		});
 		  }
 	  }
 	   }
    }  
    
    
     //提交数据
    function subDate()
    {
        var queryEventsName = $.trim($("#customQueryEventsName").val());
        var beginTime = $.trim($("#beginTime").val());
        var endTime = $.trim($("#endTime").val());
      	var add =$("#addr").val();
      	var reIp = /^([0-9]|[1-9]\d|1\d{1,2}|2\d|2[0-3]\d|24[0-7])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])$/; // 匹配IP地址的正则表达式;
		var add = $("#addr").val();
		if (add == null || add == '') {
			alert("请输入ip地址");
			return;
		} else {
			var addrs = add.split(',');
			for ( var i = 0; i < addrs.length; i++) {
				var ip = addrs[i];
				var result=reIp.test(ip);
				if (!result) {
					if (!(RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)) {
					alert("IP输入错误，请检查");
					return;
					}
				}
			}
		}
      	
      	
        if(queryEventsName =="")
        {
            $("#nameError").html("* 填写名称");
            return false;
        }else{
        	checkName();
        	if(!assetFlag){
        	return;	
        	}
        } 
        
        
        
        
        
      if((beginTime != "" && endTime == "") ||(beginTime == "" && endTime != ""))
      {
         $("#timeEror").html("请输入完整");
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
           $("#timeEror").html("请输入时间段应从小到大");
           return false;
        }
        else if(Date.parse(splitDate(beginTime)) > Date.parse(dayTime))
        {
            $("#beginTime").val(year+"-"+month+"-"+date);
            $("#timeEror").html("最小日期不能超过今日");
            return false;
        }
        else if(Date.parse(splitDate(endTime)) > Date.parse(dayTime))
        {
            $("#endTime").val(year+"-"+month+"-"+date);
            $("#timeEror").html("最大日期不能超过今日");
            return false;
        }
      }
        $("#queryRuleForm").submit();
    }
    
    //自定义时间范围
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
        	$("#time1").val('');
        	$("#time7").val('');
        	$("#time30").val('');
            $("#default").addClass("display");
            $("#custom").removeClass("display");
        }
        if(type == "oth")
        {
            $("#"+type).removeClass("display");
        }
    }
    $(function(){
   
   	//时间部分
   	if("${customTrend.days} "!=" "){
   		$("#time${customTrend.days}").attr("checked", true);
   	}
   	if("${customTrend.startTime}"!=""){
   		custom('custom');
   	}	 
     });
   
    //格式化日期
  function splitDate(date)
    {
        var dates = date.split("-");
        var time = dates[0]+"/"+dates[1]+"/"+dates[2];
        return time;
    }
      
    
    
 function gobalk(){
	 parent.mainFrame.location.href='${ctx}/customTrend/query.action';
 }
 
  
  
</script>
</head>

<body >
	<s:form action="saveCustomThend.action" method="post" namespace="/customTrend"
		id="queryRuleForm" name="queryRuleForm">
		<input type="hidden" name="customTrend.trendId" value="${customTrend.trendId}"/>
		<input type="hidden" id="trendName" value="${customTrend.trendName}">
		<s:token></s:token>
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 5px; margin-top: 2px">
			<tr>
				<!-- rigth area -->
				<td width="100%" valign="top">
					<div class="framDiv" style = "heigh:1000px">
						<table width="100%"   cellspacing="1" cellpadding="0" border="0">
							<tr>
								<td class="r2titler" colspan="3" ><b>编辑自定义趋势分析</b>
								</td>
							</tr>

							<tr>

								<td class="row"><span>名&nbsp;&nbsp;&nbsp;&nbsp;称：</span>
								</td>
								<td><input type="text" name="customTrend.trendName" class="validate[required,custom[spechars],custom[validateLength]]"
									id="customQueryEventsName" style="width:270px;" value="${customTrend.trendName}" onblur="checkName();" />&nbsp;<font
									color="red" id="nameError">*</font>
								</td>
							</tr>

							<tr>
								<td class="row"><span>创建者：</span>
								</td>
								<td>
								<input type="text"  readonly="readonly" style="width:270px;" value="${customTrend.trendCreateUser}" />
								</td>
							</tr>
							<tr>
								<td class="row"><span>修改者：</span>
								</td>
								<td>
								<input type="text"  readonly="readonly" style="width:270px;" value="${customTrend.trendUpdateUser}" />
								</td>
							</tr>
							<tr>
								<td class="row"><span>创建时间：</span>
								</td>
								<td>
								<input type="text"  readonly="readonly" style="width:270px;"
								value="<s:date
										name='customTrend.trendCreateDateTime' format='yyyy-MM-dd HH:mm:ss' />"
								 />
								</td>
							</tr>
							<tr>
								<td class="row"><span>修改时间：</span>
								</td>
								<td>
								<input type="text"  readonly="readonly" style="width:270px;" value="<s:date
										name='customTrend.trendUpdateDateTime' format='yyyy-MM-dd HH:mm:ss' />" />
								</td>
							</tr>
                           <tr>

											<td align="right" style="padding-left:62px;vertical-align:10%">描述：</td>
											<td>
													<textarea cols="35" id ="timePolicyMemo"
													
													cssStyle="width:280px"
														name="customTrend.trendCustom5"
													
													rows="5">${customTrend.trendCustom5}</textarea>
											</td>
											
										</tr>
                            <tr>
								<td class="row"><select name="customTrend.trendCustom1">
								<option value="1">源地址</option>
								<option value="2" <c:if test="${customTrend.trendCustom1 eq '2' }">selected="selected"</c:if>>目标地址</option>
								<option value="3" <c:if test="${customTrend.trendCustom1 eq '3' }">selected="selected"</c:if>>设备地址</option>
								</select>：
								</td>
								<td >
								<input type="text" style="width:270px;" name="customTrend.trendIp" id="addr" value="${customTrend.trendIp}"/>
									<span style="color: red;">(输入IP地址时，可以输入多个IP或IP地址段，请用“,”分隔，例如：10.70.18.35,10.50.10.0)
									</span>
									</td>
									
							</tr>
						

							<tr>
								<td class="row"><span>时间范围：</span>
								</td>
								<td><span class="display" id="custom"> <input
										id="beginTime" onclick="WdatePicker()" name="customTrend.startTime"
										class="Wdate"  value="${customTrend.startTime}"
										style="width:113px; height:19px;">&nbsp;至 <input
										id="endTime" onclick="WdatePicker()" name="customTrend.endTime"
										class="Wdate"  value="${customTrend.endTime}"
										style="width:113px; height:19px;">&nbsp;<font
										color="red" id="timeEror"></font> &nbsp;&nbsp; <a
										href="javascript:custom('return');">返回</a> </span> <span id="default"><input
									id = "time1"	type="radio" name="customTrend.days" value="1" checked="checked" />本日&nbsp;&nbsp;<input
									id = "time7"	type="radio" name="customTrend.days" value="7"  />最近七天&nbsp;&nbsp;<input
									id = "time30"	type="radio" name="customTrend.days" value="30" />最近三十天&nbsp;&nbsp;<a
										href="javascript:custom('custom');">自定义</a>
								</span>
								</td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;<span
									style="text-align: right; display: none;"><a
										href="javascript:custom('oth');">更多条件</a>
								</span>
								</td>
							</tr>
							<tr id="oth" class="display">
								<td class="row"><span
									style="position:relative;top:-60px; left:0px;">更多条件：</span>
								</td>
								<td>
									<div id="tabs"
										style="border: 1px solid #3399cc; width: 550px; ">
										<ul style="height: 25px;">
											<li style="height: 23px;"><a href="#tabs-3">分&nbsp;&nbsp;类
											</a>
											</li>
											<li style="height: 23px;"><a href="#tabs-2">来&nbsp;&nbsp;源</a>
											</li>
											<li style="height: 23px;"><a href="#tabs-1">目&nbsp;&nbsp;的</a>
											</li>
											<li style="height: 23px;"><a href="#tabs-4">资&nbsp;&nbsp;产</a>
											</li>
											<li style="height: 23px;"><a href="#tabs-5">协&nbsp;&nbsp;议</a>
											</li>
											<li style="height: 23px;"><a href="#tabs-6">描&nbsp;&nbsp;述</a>
											</li>
											<li style="height: 23px; margin-right: 0px;"><a
												href="#tabs-7">设&nbsp;&nbsp;备</a>
											</li>
										</ul>
										<div id="tabs-3" class="tabs">
											<table border="0" width="100%" cellpadding="0"
												cellspacing="0">
												<tr>
													<td><span
														style="position:relative;top:-30px; left:0px;">对&nbsp;&nbsp;&nbsp;&nbsp;象</span>
													</td>
													<td><select style="width:80%" size="3"></select><br />
														<div style="height: 5px;"></div> <span> <input
															type="button" class="btnadd" onclick="" /> <input
															type="button" class="btndel" onclick="" /> </span></td>
													<td style="width: 10px;"></td>
													<td><span
														style="position:relative;top:-30px; left:0px;">技术方式</span>
													</td>
													<td><select style="width:80%" size="3"></select><br />
														<div style="height: 5px;"></div> <span> <input
															type="button" class="btnadd" onclick="" /> <input
															type="button" class="btndel" onclick="" /> </span></td>
												</tr>
											</table>
										</div>
										<div id="tabs-2"></div>
										<div id="tabs-1"></div>
										<div id="tabs-4"></div>
										<div id="tabs-5"></div>
										<div id="tabs-6"></div>
										<div id="tabs-7"></div>
									</div></td>
							</tr>
						</table>
					</div></td>
							</tr>
							
			<tr>
				<td style="padding:5px 0px 5px 0px; text-align: right;" align="right"><input
					type="button" class="btnyh" id="btnSave" value="保   存"
					onclick="subDate();" /> <input type="button" class="btnyh"
					id="btnCancel" value="取  消" onclick="gobalk();" />
				</td>
			</tr>
		</table>
	</s:form>

</body>
</html>
