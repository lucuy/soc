<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ include file="mainoffer.inc"%>
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
	
		<title>报表统计</title>
		<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css" />
		<%-- <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet" type="text/css"> --%>
		<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet" type="text/css">
		<script type='text/javascript'
			src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
		<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
		<script language="javascript" type="text/javascript"
			src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>

		 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
			type="text/css"> 
			<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
		<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
			type="text/css">
		<script type='text/javascript'
			src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
		<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
		<style type="text/css">
#leftDiv,#rightDiv {
	width: 99%;
	height: 360px;
	margin-top: 10px;
	border: 1px solid #5AA4D1;
	overflow-x: hidden;
	overflow-y: scroll;
}

select {
	width: 110px;
}

#queryCondition tr,#chartTable tr {
	height: 18px;
}
</style>

		<script type="text/javascript">
	//页面出现的提示信息

	var message = "";
	function showChartFun(object){
		if(!object.checked){
			var chartTable = document.getElementById("chartTable");
			chartTable.style.display = "none";
		}else{
			var chartTable = document.getElementById("chartTable");
			chartTable.style.display = "block";
		}
	}
	
	function selDisplay(object){
		var objVal = object.value;
		var displayChks = document.getElementsByName("display");
		for(var i=0; i<displayChks.length; i++){
			if(objVal != displayChks[i].value){
				displayChks[i].checked = !object.checked;
			}
		}
	}
	
	function initPage(){
		document.getElementById("showChart").checked = true;
		//document.getElementsByName("display")[0].checked = true;
		var formType = document.getElementById("formType");
		var formName = document.getElementById("formName");
		var formTypeIndex = 0;
		var formNameIndex = 0;
		var tmpOptName = "";
		var tmpOptValue = 0;
		
		<logic:present name="modelList">
		<logic:notEmpty name="modelList">
			<logic:iterate id="queryModel" name="modelList">
				//tmpOptValue = <bean:write name='queryModel' property='typeId' />;
				//tmpOptName = "<bean:write name='queryModel' property='templateName' />";
				// 屏蔽掉模板类别 -- start --
				//formType.options[formTypeIndex++] = new Option(tmpOptName, tmpOptValue);
				// 屏蔽掉模板类别 -- end --			
			</logic:iterate>
			
			// 屏蔽掉模板类别-- start -- 
			//changeFormType();
			
			// 屏蔽掉模板类别 -- end ---
			getFormNameSel(""); // 屏蔽掉模板类别 add 
			changeCondition();
		</logic:notEmpty>
		</logic:present>
	}
	
	// 屏蔽掉模板类别  --start---
	//更换模板类别
	function changeFormType(){
		setMsg("");
		var formType = document.getElementById("formType");
		var formName = document.getElementById("formName");
		var typeValue = 0;
		for(var i=0; i< formType.options.length; i++){
			if(formType.options[i].selected){
				typeValue = formType.options[i].value;
				formName.options.length = 0;
				getFormNameSel(typeValue);
			}
		}
		changeCondition();
		
		
	}
	
	//   // 屏蔽掉模板类别   --  end --- 
	
	function changeCondition(){
		
		var formName = document.getElementById("formName");
		var selValue = "";
		var reportFormId;
		var selTerm;
		for(var i = 0; i < formName.options.length; i++ ){
			if(formName.options[i].selected){
				selValue = trim(formName.options[i].value);
				break;
			}
		}
		//判断是否为空值

		if(!Boolean(selValue)){
			var queryCondition = document.getElementById("queryCondition");
			while(queryCondition.rows.length > 0){
				queryCondition.removeChild(queryCondition.firstChild);
			}
			return;
		}
		<logic:present name="modelList">
		<logic:notEmpty name="modelList">
			<logic:iterate id="queryModel" name="modelList">
				<logic:notEmpty name="queryModel" property="reportFormList">
					<logic:iterate id="reportForm" name="queryModel" property="reportFormList">
						reportFormId = trim("<bean:write name='reportForm' property='reportFormId'/>");
						if(reportFormId == selValue.split(";")[0]){
							selTerm = trim("<bean:write name='reportForm' property='selTerm' />");
							selTerm = getSelTerm(selTerm);
							var n = "${reportForm.reportFormType}";
							if(n == 0){
								var arr = getHaving(selTerm);
								getCondition(arr[0]);
								addHaving(arr[1]);
							}else{
								clearRow();
							}
							// 图形显示
							var coordx = "${reportForm.coordx}";
							var coordy = "${reportForm.coordy}";
							if(coordx == "" && coordy == ""){
								var chat = document.getElementById("showChart");
								chat.checked = false;
								chat.disabled = true;
								showChartFun(chat);
							}else{
								var chat = document.getElementById("showChart");
								chat.checked = true;
								chat.disabled = false;
								showChartFun(chat);
							}
							
						}
					</logic:iterate>
				</logic:notEmpty>
			</logic:iterate>
		</logic:notEmpty>
		</logic:present>
		
	}
	
	function getSelTerm(selTerm){
		var sel = "";
		var conds = selTerm.split("||");
		for(var i=0;i<conds.length;i++){
			var temp = "";
			if(conds[i] != ""){
				temp = conds[i].split(";")[1];
			}
			if( temp != "" ){
				if(temp.indexOf("group by") != -1
				   || temp.indexOf("order by") != -1){
				   	
				}else{
					sel += conds[i]+"||";
				}
			}
		}
		return sel;
	}
	
	function getHaving(selTerm){
		var sel = "";
		var selTer = "";
		var conds = selTerm.split("||");
		for(var i=0;i<conds.length;i++){
			var temp = "";
			if(conds[i] != ""){
				if(conds[i].indexOf("having") != -1){
					sel += conds[i]+"||";
				}else{
					selTer += conds[i]+"||";
				}
			}
		}
		var arr = [selTer,sel] ;
		return arr;
	}
	
	//根据模板类型ID得到所有模板


	function getFormNameSel(formId){
		var formName = document.getElementById("formName");
		var formNameIndex = 0;
		var tmpOptName = "";
		var tmpOptValue = "";
		var reportFormId = 0;
		<logic:present name="modelList">
		<logic:notEmpty name="modelList">
			<logic:iterate id="queryModel" name="modelList">
				<logic:notEmpty name="queryModel" property="reportFormList">
					<logic:iterate id="reportForm" name="queryModel" property="reportFormList">
						//屏蔽掉模板类型


						// reportFormId = <bean:write name="reportForm" property="reportFormSort"/>;
						//if(reportFormId == formId){
							tmpOptValue = "<bean:write name='reportForm' property='reportFormId' />;<bean:write name='reportForm' property='id' />";
							tmpOptName = "<bean:write name='reportForm' property='reportFormName' />";
							formName.options[formNameIndex++] = new Option(tmpOptName, tmpOptValue);
							//alert(tmpOptValue+"  " + tmpOptName);
						//}
					</logic:iterate>
				</logic:notEmpty>
			</logic:iterate>
		</logic:notEmpty>
		</logic:present>
		if(formName.options.length == 0){
			formName.options[0] = new Option("没有对应的报表", "");
			setMsg("该模板类型没有对应的列表");
		}
	}
	//静态模板清除已增加的行
	function clearRow(){
		var queryCondition = document.getElementById("queryCondition");
		while(queryCondition.rows.length > 0){
			queryCondition.removeChild(queryCondition.firstChild);
		}
		queryCondition.insertRow(0).insertCell(0).innerHTML = "<span style='padding-left:40px;'><font color='red'>静态模板没有查询条件！</font></span>";
	
	}
	
	
	//动态模板解析从客户端传来的条件串


	function getCondition(conditionStr){
		var queryCondition = document.getElementById("queryCondition");
		while(conditionStr.indexOf("&gt;") != -1 || conditionStr.indexOf("&lt;") != -1){
			conditionStr = conditionStr.replace("&gt;",">");
			conditionStr = conditionStr.replace("&lt;","<");
		}		
		var str = conditionStr;
		var strArr = str.split("||");
		var temStr = "";
		var inputName = "";
		var inputDisName = ""; 
		var dataType = "";
		var temArr;
		var newRow;
		var cell0;
		var cell1;
		var innerHtmlStr = "";
		while(queryCondition.rows.length > 0){
			queryCondition.removeChild(queryCondition.firstChild);
		}
		if(!Boolean(conditionStr)){
			return;
		}
		for(i=0; i<strArr.length-1;i++){
			temStr = strArr[i];
			temArr = temStr.split(";");
			
			inputDisName = getName(temArr[0]);
			inputName = getName(temArr[1]);
			dataType = strArr[i].substring(strArr[i].indexOf("$")+1,strArr[i].length);
			if(inputDisName != ""){
				inputDisName =  trim(inputDisName);
			}
			if(inputName != ""){
				inputName = trim(inputName);
			}
			if(dataType != ""){
				dataType = trim(dataType);
			}
			
			if(Boolean(inputDisName)== false || Boolean(inputName) == false){
				continue;
			}
			newRow = queryCondition.insertRow(i);
			cell0 = newRow.insertCell(0);
			cell0.width="40%";
			cell0.align="right";
			var likevalue = "";
			if(inputDisName.indexOf("NOT LIKE") != -1){
				likevalue = "NOT LIKE";
			}else if(inputDisName.indexOf("LIKE") != -1){
				likevalue = "LIKE";
			}
			var _value = likevalue == "" ? toChineseCharacter(inputDisName.split(" ")[1]) : toChineseCharacter(likevalue);
			cell0.innerHTML = inputDisName.split(" ")[0]+" "+ _value + ":";
			cell1 = newRow.insertCell(1);
			cell1.width="60%";
			cell1.align="left";
			
			innerHtmlStr = "";
			if(dataType == "3"){
				cell1.innerHTML="<input type='text' name='"+inputName+"' style='width: 114pt;' value='' class='Wdate' onfocus='WdatePicker({isShowWeek:true})' readonly />"
										+ "<input type='hidden' name='params' value='" + inputName + "'>"
							 			+ "<input type='hidden' name='checkParam' value='" + inputName + "|" + dataType + "|" + inputDisName + "'>";
			}else{
				cell1.innerHTML = "<input type='text' name='" + inputName + "'  maxlength='20'> " 
							 	+ "<input type='hidden' name='params' value='" + inputName + "'>" 
							 	+ "<input type='hidden' name='checkParam' value='" + inputName + "|" + dataType + "|" + inputDisName + "'>";
			}
		}
		document.getElementById("arparam").innerHTML = "<input type='hidden' id='arrays' name='arrays' value='"+conditionStr+"'>";
	}
	function addHaving(conditionStr){
		while(conditionStr.indexOf("&gt;") != -1 || conditionStr.indexOf("&lt;") != -1){
			conditionStr = conditionStr.replace("&gt;",">");
			conditionStr = conditionStr.replace("&lt;","<");
		}	
		var strArr = conditionStr.split("||");
		var displayName = "" , paramName="";
		var newRow;
		var cell0 ,cell1;
		for(i=0; i<strArr.length-1;i++){
			var temStr = strArr[i];
			if(temStr == "" ){
				break;
			}
			temArr = temStr.split(";");
			displayName = getName(temArr[0]);
			
			paramName = temArr[1].substring("having".length+1,temArr[1].indexOf(")")+4);
			
			var k = queryCondition.rows.length;
			newRow = queryCondition.insertRow(k);
			cell0 = newRow.insertCell(0);
			cell0.width="40%";
			cell0.align="right";
			cell0.innerHTML = displayName;
			cell1 = newRow.insertCell(1);
			cell1.width="60%";
			cell1.align="left";
			
			cell1.innerHTML = "<input type='text' value='' name='hav' onblur='changeValue(this)'>"
								+"<input type='hidden' name='haval' value='"+paramName+"'>"
								+"<input type='hidden' name='having' value=''>";
		}
	}
	
	function changeValue(obj){
		var childs = obj.parentNode.children;
		if(!checkInteger(obj,"")){
			return false;
		}
		var Value = childs[1].value + obj.value;
		childs[2].value = Value;
	}
	
	function getName(inputName){
		if(inputName.indexOf("and") != -1){
			if(inputName.indexOf("LIKE") != -1){
				return inputName.substring(inputName.indexOf("and") + "and".length,inputName.indexOf("LIKE")+4);
			}else if(inputName.indexOf("=")!= -1){
				return inputName.substring(inputName.indexOf("and") + "and".length,inputName.indexOf("=")+1);
			}else if(inputName.indexOf(">") != -1){
				return inputName.substring(inputName.indexOf("and") + "and".length,inputName.indexOf(">")+1);
			}else if(inputName.indexOf("<") != -1){
				return inputName.substring(inputName.indexOf("and") + "and".length,inputName.indexOf("<")+1);
			}
			
		}else if(inputName.indexOf("or") != -1){
			
			if(inputName.indexOf("LIKE") != -1){
				return inputName.substring(inputName.indexOf("or") + "or".length,inputName.indexOf("LIKE")+4);
			}else if(inputName.indexOf("=")!= -1){
				return inputName.substring(inputName.indexOf("or") + "or".length,inputName.indexOf("=")+1);
			}else if(inputName.indexOf(">") != -1){
				return inputName.substring(inputName.indexOf("or") + "or".length,inputName.indexOf(">")+1);
			}else if(inputName.indexOf("<") != -1){
				return inputName.substring(inputName.indexOf("or") + "or".length,inputName.indexOf("<")+1);
			}
		}else if(inputName.indexOf("having") != -1){
			var col = "";     
			var start =0;
			col = inputName.indexOf("sum") == -1 ? "求记录 " : "求和 ";
			start = inputName.indexOf("sum") == -1 ? inputName.indexOf("count")+5 : inputName.indexOf("sum")+3 ;
			if(inputName.indexOf(">=") != -1){
				return col + inputName.substring(start,inputName.indexOf(">=")) + " 大于等于";
			}else if(inputName.indexOf("<=") != -1){
				return col + inputName.substring(start,inputName.indexOf("<=")) +  " 小于等于 ";
			}else if(inputName.indexOf("!=") != -1){
				return col + inputName.substring(start,inputName.indexOf("!=")) + " 不等于 ";
			}else if(inputName.indexOf(">") != -1){
				return col + inputName.substring(start,inputName.indexOf(">")) + " 大于 ";
			}else if(inputName.indexOf("<") != -1){
				return col + inputName.substring(start,inputName.indexOf("<")) + " 小于 ";
			}else if(inputName.indexOf("=") != -1){
				return col + inputName.substring(start,inputName.indexOf("=")) + " 等于 ";
			}
		}
	}
	
	function setMsg(msg){
		var msgDiv = document.getElementById("msgDiv");
		if(!msg){
			msgDiv.innerHTML = "";
		}else if(msg){
			msgDiv.innerHTML = "<font color='red'>" + msg + "</font>";
		}
	}
function CheckInteger(Object){
    var strInteger = Object.value;
    if (strInteger.length == 0) {
        return true;
    }
    else {
        var pattern = /^-?\d+$/;
        if (strInteger.match(pattern) == null) {
            alert("自定义TOP值只能输入数字");
            Object.focus();
            return false;
        }
        else {
            return true;
        }
    }
}
	function query(){
		var queryForm = document.getElementById("queryStat");
		var formName = document.getElementById("formName");
		
		var top = document.getElementById("top");
		var selfTop = document.getElementById("selfTop");
		
		if(!CheckInteger(selfTop)){
			return false;
		}
		
		if(top.value != 0 && selfTop.value != ""){
			alert("TOP值与自定义TOP只能选择一个");
			return false;
		}

		var formValue = document.getElementById("formName").value;
		if(formValue == ""){
			alert("没有可查询的模板！");
			return false;
		}
		
		var reportFormId="";
		for(var i=0;i<formName.options.length;i++){
			if(formName.options[i].selected){
				var temp=formName.options[i].value;
				var temps=temp.split(";");
				reportFormId=temps[1];
			}
		}
		//alert("id="+reportFormId);
		queryForm.action="queryformcount.do?method=getQueryCount&reportFormId="+reportFormId;
		var flag = checkObj();
		if(true == flag){
			queryForm.submit();
		}		
		return flag;
	}
	
 	function hiddenContet(objId, ishidden){
 		var obj = document.getElementById(objId);
 		//节点不存在

 		if(objId){
 			return;
 		}
 		if(true == ishidden){
 			obj.style.display="none";
 		}else{
 			obj.style.display="block";
 		}
 	}
	
	function checkObj(){
		var checkParamArr = document.getElementsByName("checkParam");
		var paramStr;
		var paramArr;
		var inputName;
		var dataType;
		var displayName;
		var checkFlag;
		var a=0 , b=0;
		for(var i=0; i<checkParamArr.length; i++){
			var arrays = document.getElementById("arrays");
			paramStr = checkParamArr[i].value;
			paramArr = paramStr.split("|");
			var inputNames = document.getElementsByName(paramArr[0]);
			
			if(inputNames.length>1){
				if(a == 0){
					a = inputNames.length;
					b = i+a;
					inputName = inputNames[0];	
				}else{
					inputName = inputNames[i+a-b];
					if(i+a-b == a-1){
						a = 0;
					}
				}
			}else{
				inputName = inputNames[0];
			}
			dataType = paramArr[1];
			displayName = paramArr[2];
			if("1" == dataType){
				if(inputName.value.length != ""){
					var reg = /^[^^“||~@&%\t-]+$/;
					if(inputName.value.match(reg) == null){
						alert(displayName.split(" ")[0]+"不能输入怪异字符！");
						return false ;
					}else{
						arrays.value=setBack(i,arrays.value,inputName.value);
					}
				}else{
					arrays.value=setBack(i,arrays.value,inputName.value);
				}
				
			}else if("2" == dataType){
				checkFlag = checkInteger(inputName,displayName);
				if(!checkFlag){
					return false;
				}else{
					arrays.value=setBack(i,arrays.value,inputName.value);
				}
			}else if("3" == dataType){
				
				arrays.value=setBack(i,arrays.value,inputName.value);
			}
		}
		return true;
	}
	
	function toChineseCharacter(str){
		str = trim(str);
		if(str == "="){
			str = "等于";
		}else if(str == "!="){
			str = "不等于";
		}else if(str.indexOf("NOT") != -1){
			str = "不类似于";
		}else if(str.indexOf("LIKE") != -1){
			str = "类似于";
		}else if(str == ">"){
			str = "大于";
		}else if(str == "<"){
			str = "小于";
		}else if(str == ">="){
			str = "大于等于";
		}else if(str == "<="){
			str = "小于等于";
		}
		return str;
	} 
	
	//把文本框的值重写回查询串中
	function setBack(n,arrays,inputName){
		var str = "";
		while(arrays.indexOf("&gt;") != -1 || arrays.indexOf("&lt;") != -1){
			arrays = arrays.replace("&gt;",">");
			arrays = arrays.replace("&lt;","<");
		}
		//and 主机名 != abcd;and LogHost != abcd$1
		var array = arrays.split("||")[n];
		//and LogHost != abcd$1
		var array_value = array.split(";")[1];
		
		var _name = getName(array_value);
		
		var _value = array_value.substring(array_value.indexOf(_name)+_name.length,array_value.length);
		//alert(_value);
		var  _operator = array_value.substring(0,array_value.indexOf(_name));
		if(_value.indexOf("%") != -1){
			_value = " '%"+inputName +"%'$"+ _value.split("$")[1];
		}else{
			_value = " '"+inputName +"'$"+ _value.split("$")[1];
		}
		//alert(_value);
		array_value=_operator + _name + _value;
		
		array = array.split(";")[0] +";"+ array_value;
		//alert(array);
		var _length = arrays.split("||").length -1 ;
		for(var i=0 ; i<_length ; i++){
			str += i==n ? array +"||" : arrays.split("||")[i] + "||";
		}
		return str;
	}
	
	
	//检查是否为数字
	function checkInteger(Object,InfoTitle){
		var event = document.getElementsByName("serverlog.eventid =");
	    var strInteger = Object.value;
	    if (strInteger.length == 0) {
	        return true;
	    }
	    else {
	        var pattern = /^-?\d+$/;
	        if (strInteger.match(pattern) == null) {
		        if(Object.name.indexOf("serverlog.eventid")==-1){
		            alert( InfoTitle.split(" ")[0] + "只能输入数字");
		            Object.focus();
		            return false;
		        }else{
			        Object.value = changeInput(Object.value);
			        return true;
		        }
	        } else {
	            return true;
	        }
	    }
	}
	function changeInput(obj){
		return 0;
	}
	function trim(str){
		for(var i = 0; i<str.length && (str.charAt(i)==" "||str.charAt(i)=="\t"); i++ );
		for(var j =str.length; j>0 && (str.charAt(j-1)==" " || str.charAt(j-1)=="\t"); j--);
		if(i>j)  return  "";  
		return  str.substring(i,j);  
	}	
	</script>
	</head>
	<body onload="initPage();">
		<form name="queryStat" id="queryStat" action="" method="post">
			<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 2px">
				
				<tr>
					<td valign="top">
						<div class="framDiv">
							<!--  左侧table-->
							<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-top:1px;padding-left:1px;padding-right:1px;">
								<tr>
									<td class="r2titler">
										<b>报表统计</b>
									</td>
								</tr>


								<!-- 空行 -->
								<tr height="5">
									<td>
									</td>
									<td>
										<font color="red"><html:errors />${message}</font>
									</td>
								</tr>
								<tr>
									<td width="100%" height="80%">
										<!--<div id="leftDiv">
											-->
										<table id="leftTable" width="100%" style="font-size: 13px;">
											<tr>
												<td width="5%" style="padding-left: 15px;">
													<b>切换模板：</b>
												</td>
												<td width="40%" align="left">
													<select id="formName" onchange="changeCondition();"
														style="width: 200px">
													</select>
												</td>
											</tr>
											<!-- 空行 -->
											<tr>
												<td height="5" colspan="2">
												</td>
											</tr>
											<tr>
												<td width="5%" style="padding-left: 15px;">
													<b>分页：</b>
												</td>
												<td>
													<select name="pageoffset" style="width: 80px">
														<option value="20">
															20
														</option>
														<option value="50">
															50
														</option>
														<option value="100">
															100
														</option>
													</select>
												</td>
											</tr>
											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator">
												</td>
											</tr>
											<tr>
												<td align="left" colspan="2" style="padding-left: 15px;">
													<b>查询条件设置</b>
												</td>
											</tr>
											<!-- 虚线分割线 -->
											<tr>
												<td colspan="2" style="padding-left: 15px;padding-right:15px;">
													<div class="xuxian"></div>
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<table id="queryCondition" width="72%"
														style="font-size: 13px;">
													</table>
												</td>
											</tr>
											<tr>
												<td align="left" colspan="2" style="padding-left: 15px;padding-right:15px;">
													<b>图形显示设置</b>
												</td>
											</tr>
											<!-- 虚线分割线 -->
											<tr>
												<td colspan="2" style="padding-left: 15px;padding-right:15px;">
													<div class="xuxian"></div>
												</td>
											</tr>
											<tr>
												<td colspan="2" style="padding-left: 40px;">
													<table>
														<tr>
															<td>
																<input type="checkbox" id="showChart" name="showChart"
																	value="show" onclick="showChartFun(this);" />
																图形显示
															</td>
															<td>
																<table width="100%" id="chartTable" align="right"
																	style="font-size: 13px;margin-left: 10px;">
																	<tr>
																		<td width="65%" align="right">
																			&nbsp;T&nbsp;O&nbsp;P：
																		</td>
																		<td width="55%">
																			<select name="top" id="top">
																				<option value="0">
																					选择TOP值
																				</option>
																				<option value="5">
																					--5--
																				</option>
																				<option value="10">
																					--10--
																				</option>
																				<option value="15">
																					--15--
																				</option>
																			</select>
																		</td>
																	</tr>
																	<tr>
																		<td width="65%"  align="right">
																			自定义TOP值：
																		</td>
																		<td>
																			<input type="text" name="selfTop" id="selfTop"
																				maxlength="2">
																		</td>
																	</tr>
																	<tr>
																		<td align="right">
																			&nbsp;横&nbsp;&nbsp;轴：
																		</td>
																		<td>
																			<select name="topX" id="topX">
																				<option value="0">
																					--横轴--
																				</option>
																				<option value="5">
																					--横轴5--
																				</option>
																				<option value="10">
																					--横轴10--
																				</option>
																				<option value="15">
																					--横轴15--
																				</option>
																			</select>
																		</td>
																	</tr>
																	<tr>
																		<td align="right">
																			&nbsp;纵&nbsp;&nbsp;轴：
																		</td>
																		<td>
																			<select name="topY" id="topY">
																				<option value="0">
																					--纵轴--
																				</option>
																				<option value="5">
																					--纵轴5--
																				</option>
																				<option value="10">
																					--纵轴10--
																				</option>
																				<option value="15">
																					--纵轴15--
																				</option>
																			</select>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
								<!--</div>
									-->
								</td>
								<td width="38%">
									<!--<div id="rightDiv">
											-->

									<!--</div>
									-->
								</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
			</table>
			<div id="arparam"></div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<!-- 空行 -->
				<tr>
					<td class="td_detail_separator">
					</td>
				</tr>

				<tr>
					<!-- <td width="0"></td> -->
					<td align="right">
						<input type="button" value="查    询" class="btnyh" id="btnSave"
							onclick="query();" />
						<input type="button" class="btnyh" value="重    置" id="btnCancel" style="margin-right:6px"
							onclick="initPage();" />
					</td>
				</tr>
			</table>
			

		</form>
	</body>
</html>