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
    src="${ctx}/scripts/jquery-ui.custom.min.js"></script> --%>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>

<script type='text/javascript' src="${ctx}/scripts/treeview.js"></script>


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
        jQuery("#queryRuleForm").validationEngine();
        
          //事件名称
            /* $('#dialog-datasource').dialog({
            autoOpen : false,
            width : 350,
            height : 300,
            beforeclose: function(event, ui) {$("[name=check_prot]:checkbox:checked").attr("checked",false);},
            buttons : {
                "确定[Enter]" : function() {
                    var port = "";
                    var protocol = "";
                    $("#portText").empty();
                    $("[name=check_prot]:checkbox:checked").each(function() {
                        if(protocol != "")
                        {
                            protocol +=  "|" + $(this).val();
                        }
                        else
                        {
                             protocol = $(this).val();
                        }
                            port = $(this).attr("id");
                            $("#portText").append("<option id="+ $(this).val() +">" + $.trim($("#portText_"+port).text()) + "</option>");
                    });
                    $("#protocol").val(protocol);
                    $("[name=check_prot]:checkbox:checked").attr("checked",false);
                    $(this).dialog("close");
                },
                "取消[Esc]" : function() {
                    $("[name=check_prot]:checkbox:checked").attr("checked",false);
                    $(this).dialog("close");
                }
            }
        }); */
        
          $('#dialog-dataEventCategory').dialog({
			autoOpen: false,
			width: 300,
			height:350,
			buttons: {
				"确定[Enter]": function() {
					addOk('category','dialog-dataEventCategory');
				},
				"取消[Esc]": function() {
					$(this).dialog("close");
				}
			}
		});
		
		 $('#dialog-dataEventType').dialog({
			autoOpen: false,
			width: 300,
			height:350,
			buttons: {
				"确定[Enter]": function() {
					addOk('event_type','dialog-dataEventType');
				},
				"取消[Esc]": function() {
					$(this).dialog("close");
				}
			}
		});
        //初始化复选框
       initCheckBox('category','chk-eventcategroy');
       
       initCheckBox('event_type','chk-eventtype');
        
    });
    
   /*  function del()
    {
    	
    	var e = $("#protocol").val().split("|");
    	var a=$("#portText").find("option:selected").attr("id");
    	var protocol = "";
        for(var i=0; i<e.length; i++){
        	if(a != e[i]){
        		if(protocol != ""){
        			protocol +=  "|" + e[i];
        		}else{
        			protocol = e[i];
        		}
        	}
        }
        $("#protocol").val(protocol);/*
       
        $("#portText").find("option:selected").remove();
        
    } */
    
    
    /* function opentDatasource()
    {
    	
        var html = "";
        $.ajax({
                type:"POST",
                url:"${ctx}/events/queryEventDefinition.action",
                async : false,
                dataType:"JSON",
                data:"date="+new Date(),
                success:function(result)
                {
                   var m=eval(result);
                   var e = $("#protocol").val();
                    for(var i=0 ; i<m.length; i++){
                        var j=m[i];
                        if(e.indexOf(j.ID) == -1){
                        	html += "<li onmousemove=\"this.className='hand'\" onmouseout=\"this.className='back'\" id=\"portText_"+j.ID+"\" ><input type=\"checkbox\" name=\"check_prot\" id=\""+j.ID+"\" value=\""+j.ID+"\"/>&nbsp;&nbsp;&nbsp;&nbsp;"+j.NAME+"</li>";
                        }else{
                       		html += "<li onmousemove=\"this.className='hand'\" onmouseout=\"this.className='back'\" id=\"portText_"+j.ID+"\" ><input type=\"checkbox\" checked=\"checked\" name=\"check_prot\" id=\""+j.ID+"\" value=\""+j.ID+"\"/>&nbsp;&nbsp;&nbsp;&nbsp;"+j.NAME+"</li>";
                    	}
                    }
                    $("#datasourceUl").html(html);
                },
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    if(textStatus != "")
                    {
                        alert(textStatus + "   " +errorThrown);
                        //alert("请求错误刷新后重试");
                    }
                }
        });
        
        
        $("#dialog-datasource").dialog('open');
        
    } */
    
    /*
     onclick=\"choose("+j.ID+")\"
    function choose(id)
    {
        //$("#"+id).attr("checked",true);
        if($("#"+id).is(":checked") == true){
        	$("#"+id).attr("checked",false);
        }else{
        	$("#"+id).attr("checked",true);
        }
        ;
    }
    */
    function addeventTypeDialog()
    {
       
        $('#dialog-dataEventType').dialog('open');
        $('#dialog-dataEventType').focus();
    }
    
     function addCategroyDialog()
    {
       $('#dialog-dataEventCategory').dialog('open');
       $('#dialog-dataEventCategory').focus();
    }
    
      //选项卡
    $(function() {
        $('#tabs').tabs();
    });
      //验证特殊字符的正常
    var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
     //提交数据
    function subDate()
    {
        var queryEventsName = $.trim($("#customQueryEventsName").val());
        //判断输入字符的长度
        if($('#customQueryEventsName').val().length>50){
			alert("查询名称长度不能大于50");
			$('#customQueryEventsName').val('');
			$('#customQueryEventsName').focus();
			return ;
		}
        //判断输入的是否包含特殊字符
		if(!rege.test($('#customQueryEventsName').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#customQueryEventsName').val('');
			$('#customQueryEventsName').focus();
			return;
		}
        var sourceAdd = $.trim($("#sourceAdd").val());
        var sourcePort = $.trim($("#sourcePort").val());
        var targetAdd = $.trim($("#targetAdd").val());
        var targetPort = $.trim($("#targetPort").val());
        var beginTime = $.trim($("#beginTime").val());
        var endTime = $.trim($("#endTime").val());
        if(queryEventsName =="")
        {
            $("#nameError").html("* 填写名称");
            return false;
        } 
         $("#eventsType").val('');
			$("#category").children("option").each(
					function() {
						if ($(this).parent("select").size() > 0) {
							$("#eventsType").val(
									$("#eventsType").val() + $(this).val()
											+ "|");
						}
					});
	     $("#protocol").val('');
			$("#event_type").children("option").each(
					function() {
						if ($(this).parent("select").size() > 0) {
							$("#protocol").val(
									$("#protocol").val() + $(this).val()
											+ "|");
						}
					});	
        if(sourceAdd != "")
        {
            var meg = checkIp(sourceAdd);
            
            if(meg != "")
            {
                 $("#sourceErr").html(meg);
                 return false;
            }
        }
        if(sourcePort != "")
        {
              var meg = checkPort(sourcePort);
              if(meg != "")
              {
                 $("#sourceErr").html(meg);
                 return false;
              }
        }
        
        if(targetAdd != "")
        {
            var meg = checkIp(targetAdd);
            if(meg != "")
            {
                 $("#targetErr").html(meg);
                 return false;
            }
        }
      if(targetPort != "")
      {
         var meg = checkPort(targetPort);
              if(meg != "")
              {
                 $("#targetErr").html(meg);
                 return false;
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
            $("#default").addClass("display");
            $("#custom").removeClass("display");
        }
        if(type == "oth")
        {
            $("#"+type).removeClass("display");
        }
    }
    
    //事件类型全选
   /*  function allChoose(type)
    {
        $("#all").live("click",function(){
           if($("#all").attr("checked"))
           {
        	   for(var i=0;i<7;i++){
        			 //alert(i);
        			 $("#eventsType"+(1+i)).attr("checked", true);
        			 
        		 }
           }
           else
           {
        	   for(var i=0;i<7;i++){
      			 //alert(i);
      			 $("#eventsType"+(1+i)).attr("checked", false);
      			 
      		 }
             
           }
               
        });
    } */
    //格式化日期
  function splitDate(date)
    {
        var dates = date.split("-");
        var time = dates[0]+"/"+dates[1]+"/"+dates[2];
        return time;
    }
    //检测端口
    function  checkPort(port)
    {
        var reg = /^\d{1,5}$/;
        if(reg.test(port))
        {
             if(port < 1 || port > 65535)
            {
                return "请输入合法的端口1~65535";
            }
            return "";
        }else
        {
            return "请输入合法的端口1~65535";
        }
        return "";
    }
        
    //检测IP
    function checkIp(ip)
    {
        var re=/^([0-9]|[1-9]\d|1\d{1,2}|2\d|2[0-3]\d|24[0-7])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])$/; // 匹配IP地址的正则表达式
            // 此处验证独个ip地址
            var ipbool = re.test(ip);
            if(ipbool){
                if(!( RegExp.$1 <256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256)){
                    return "请输入合法的IP地址...";
                }
                return "";
            }else{
                return "请输入合法的IP地址...";
            }
            return "";
    }
    

  
  function queryData()
  {
    $("#actionType").val("1");
    $("form").submit();
  }
  
  //根据从后台传过来的等级信息在对应的地方打勾
  $(function(){
  
	 //alert(${sizeThreadValue});
	 var threatValueArr = [${threatValue[0]},${threatValue[1]},${threatValue[2]},${threatValue[3]},${threatValue[4]}];
	 for(var i=0;i<5;i++){
		 //alert(i);
		 $("#threatValue"+threatValueArr[i]).attr("checked", true);
		 
	 }
		 //alert(threatValue);
		 
		/* var eventsTypeS = [${eventsTypeString}];//事件类型	
		
		
		if(eventsTypeS.length !=7){
			for(var i=1;i<eventsTypeS.length+1;i++){
				// alert(eventsTypeS[i-1]);updateCustom
				 $("#eventsType"+eventsTypeS[i-1]).attr("checked", true);
				 
			 }
		} */
		 
		
		
	//时间部分
	if("${timeRange} "!=" "){
		$("#time${timeRange}").attr("checked", true);
	}
	if("${beginTime}"!=""){
		custom('custom');
	}	 
  });
  
  function delOption(typeSelect, checkName) {
	
	if($("#"+typeSelect).get(0).selectedIndex == -1) {
		if(confirm('确认删除所有？')) {
			$('input[type="checkbox"][name="'+checkName+'"]:checked').each(function(){
				$(this).attr("checked",false);
			});
			$("#"+typeSelect).empty();
		}
	} else {
		$('input[type="checkbox"][name="'+checkName+'"]:checked').each(function(){
			if($("#"+typeSelect).val() == parseInt($(this).val())){
				$(this).attr("checked",false);
				return false;
			}
		});
		
		$("#"+typeSelect).find("option:selected").remove();
		
	}
}
</script>
</head>

<body>
	<s:form action="updateEventsConditions.action" method="post" namespace="/events"
		id="queryRuleForm" name="queryRuleForm">
		
		<s:hidden name="eventsType" id="eventsType" />
		<input type="hidden" name="customQueryEventsId"
			value="${customQueryEventsId}" />
		<input type="hidden" name="queryEventsId" value="${queryEventsId}" />
		<input type="hidden" name="protocol" id="protocol" value = "${protocol}"/>
		<input type="hidden" name="actionType" id="actionType" value="0">
		<input type="hidden" name="lastCustomQueryEventsName"  value="${customQueryEventsName}">
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 5px; margin-top: 2px">
			<tr>
				<!-- rigth area -->
				<td width="100%" valign="top">
					<div class="framDiv">
						<table width="100%" cellspacing="1" cellpadding="0" border="0">
							<tr>
								<td class="r2titler" colspan="2"><b>自定义查询</b>
								</td>
							</tr>

							<tr>

								<td class="row"><span>查询名称：</span>
								</td>
								<td><input type="text" name="customQueryEventsName" value = "${customQueryEventsName}"
									id="customQueryEventsName" style="width:270px;" />&nbsp;<font
									color="red" id="nameError">*</font>
								</td>
							</tr>

							<tr>
								<td class="row"><span>级&nbsp;&nbsp;&nbsp;&nbsp;别：</span>
								</td>
								<td><span class="">
										<input type="checkbox" name="threatValue" value="1" id = "threatValue1"/>&nbsp;1&nbsp;&nbsp;
										<input type="checkbox" name="threatValue" value="2" id = "threatValue2"/>&nbsp;2&nbsp;&nbsp;
										<input type="checkbox" name="threatValue" value="3" id = "threatValue3"/>&nbsp;3
									</span>
									<span class="">&nbsp;
										<input type="checkbox" name="threatValue" value="4" id = "threatValue4"/>&nbsp;4&nbsp;&nbsp;
										<input type="checkbox" name="threatValue" value="5" id = "threatValue5"/>&nbsp;5&nbsp;&nbsp;
									</span>
								</td>
							</tr>

							<tr>
								<td class="row"><span style="position:relative;top:-15px; left:0px;">事件类别：</span>
								</td>
								<td style="padding:5px 0px 5px 0px;"><span> <select
										id="category" name="category" style="width:270px;" size="3">
										<s:iterator value="eventRelTagList" status="stat">
																	<option value="${eventcategorykey}">
																		${eventcategoryValue}</option>
																</s:iterator>
										</select> </span> <br />
									<div style="height: 5px;"></div>
										<span> 
											<input type="button" class="btnadd" onclick="addCategroyDialog();defualtSeleted('category');" />
											<input type="button" class="btndel" onclick="delOption('category','chk-eventcategroy');" />
										</span>
									</td>
							</tr>

							<tr>
								<td class="row"><span>源&nbsp;地&nbsp;址：</span>
								</td>
								<td><input type="text" style="width:200px;"
									name="sourceAdd" id="sourceAdd" value = "${sourceAdd}"/>&nbsp;端口&nbsp;<input
									type="text" style="width:70px;" name="sourcePort"
									id="sourcePort" value="${sourcePort}"/>&nbsp;<font color="red" id="sourceErr"></font>
								</td>
							</tr>

							<tr>
								<td class="row"><span>目标地址：</span>
								</td>
								<td><input type="text" style="width:200px;"
									name="targetAdd" id="targetAdd" value = "${targetAdd}"/>&nbsp;端口&nbsp;<input
									type="text" style="width:70px;" name="targetPort"
									id="targetPort" value="${targetPort}"/>&nbsp;<font color="red" id="targetErr"></font>
								</td>
							</tr>


							 <tr>
								<td class="row"><span
									style="position:relative;top:-15px; left:0px;">事件类型：</span>
								</td>
								<td style="padding:5px 0px 5px 0px;"><span> <select
										id="event_type" name="event_type" style="width:270px;" size="3">
										<s:iterator value="eventRelTypeList" status="stat">
																	<option value="${eventtypetagId}">
																		${eventtypetagName}</option>
																</s:iterator>
										</select> </span> <br />
									<div style="height: 5px;"></div>
										<span> 
											<input type="button" class="btnadd" onclick="addeventTypeDialog();defualtSeleted('event_type');" />
											<input type="button" class="btndel" onclick="delOption('event_type','chk-eventtype');" />
										</span>
									</td>
							</tr>


							<tr>
								<td class="row"><span>时间范围：</span>
								</td>
								<td><span class="display" id="custom"> <input value = "${beginTime}"
										id="beginTime" onclick="WdatePicker()" name="beginTime"
										class="Wdate" readonly="readonly"
										style="width:113px; height:19px;">&nbsp;至 <input value = "${endTime}"
										id="endTime" onclick="WdatePicker()" name="endTime"
										class="Wdate" readonly="readonly"
										style="width:113px; height:19px;">&nbsp;<font
										color="red" id="timeEror"></font> &nbsp;&nbsp; <a
										href="javascript:custom('return');">返回</a> </span> <span id="default"><input
										 id = "time12" type="radio" name="timeRange" value="12" checked="checked" />本日&nbsp;&nbsp;<input
										id = "time7" type="radio" name="timeRange" value="7" />最近七天&nbsp;&nbsp;<input
										id = "time30" type="radio" name="timeRange" value="30" />最近三十天&nbsp;&nbsp;<input
										id = "time0" type="radio" name="timeRange" value="0" />本月&nbsp;&nbsp;&nbsp;&nbsp;<a
										href="javascript:custom('custom');">自定义</a>
								</span>&nbsp;&nbsp;&nbsp;&nbsp;<span
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
				<td style="padding:5px 0px 5px 0px; text-align: right;">
				 <input type="button" class="btnyh"
					id="btnCancel" value="保   存" onclick="subDate();" />
				</td>
			</tr>
		</table>
	</s:form>

	<div id="dialog-dataEventType" title="事件类型">
		 <table width='96%' cellspacing="0" cellpadding="0" border="0" align="center">
		   <s:iterator value='eventAllTypeList' status='stat'>
				<tr style="line-height: 25px;">
					<td width="20%" align="center"><input type="checkbox"
						name="chk-eventtype" id="chk-type-${eventtypetagId}"
						value="${eventtypetagId}" /></td>
					<td width="80%"><a style='color:#555555'
						href="javascript:void(0);"
						onclick="addOk_a('${eventtypetagName}',${eventtypetagId},'event_type','dialog-dataEventType')">${eventtypetagName}</a>
					</td>
				</tr>
			</s:iterator>
		 </table>
	</div>
	
	<div id="dialog-dataEventCategory" title="事件类别">
		 <table width='96%' cellspacing="0" cellpadding="0" border="0" align="center">
		   <s:iterator value='eventCategoryTagList' status='stat'>
				<tr style="line-height: 25px;">
					<td width="20%" align="center"><input type="checkbox"
						name="chk-eventcategroy" id="chk-category-${eventcategorykey}"
						value="${eventcategorykey}" /></td>
					<td width="80%"><a style='color: #555555'
						href="javascript:void(0);"
						onclick="addOk_a('${eventcategoryValue}',${eventcategorykey},'category','dialog-dataEventCategory')">${eventcategoryValue}</a>
					</td>
				</tr>
			</s:iterator>
		 </table>
	</div>
</body>
</html>
