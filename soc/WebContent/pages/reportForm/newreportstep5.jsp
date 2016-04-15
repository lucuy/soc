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

		<title>设置查询条件</title>
		<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
		<script type='text/javascript'
			src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
		<script type="text/javascript"
			src="${ctx}/scripts/reportForm/prototype.js"></script>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript">
		function next(){
			window.open("","_self");
		}
		function pre(){
			var f=document.ReportForm;
			var tables="";
			<logic:notEmpty name="choosbletable">
    			<logic:iterate id="list" name="choosbletable">
    				tables=tables+<bean:write name="list" property="id"/>+";";
    			</logic:iterate>
    		</logic:notEmpty>
			tables=tables.substring(0,tables.length-1);
			var bool=false;
			<logic:notEqual name="useraction" value="editReport">
				if(bool){
					f.target="";
					f.action="newreportform.do?method=creatReportFormStep3&tables="+encodeURIComponent(tables)+"&leveltwo="+encodeURIComponent(f.leveltwo.value)+"&buttons="+f.buttons.value;
					f.submit();
				}else{
					f.target="";
					f.action="newreportform.do?method=creatReportFormStep3&tables="+encodeURIComponent(tables)+"&leveltwo="+encodeURIComponent(f.leveltwo.value)+"&buttons="+f.buttons.value;
					f.submit();
				}
			</logic:notEqual>
			<logic:equal name="useraction" value="editReport">
				if(bool){
					f.target="";
					f.action="editreportform.do?method=editReportFormStep3&tables="+encodeURIComponent(tables)+"&leveltwo="+encodeURIComponent(f.leveltwo.value)+"&buttons="+f.buttons.value;
					f.submit();
				}else{
					f.target="";
					f.action="editreportform.do?method=editReportFormStep3&tables="+encodeURIComponent(tables)+"&leveltwo="+encodeURIComponent(f.leveltwo.value)+"&buttons="+f.buttons.value;
					f.submit();
				}
			</logic:equal>
		}
		//更换表后，更换列的显示

	function changeTemplate(){
		
		var choosetables = document.ReportForm.choosetable.options;
		var templateId = "";
		if(choosetables.length > 0){
			for(var i=0; i<choosetables.length; i++){
				if(choosetables[i].selected){
					var temp=choosetables[i].value;
					var temps=temp.split(";");
					getColType(temps[0]);
					
					break;
				}
			}
		}
	}
	//生成对应表的列名称

	function getColType(tableId){
	
		var colType = document.ReportForm.colType;
		var colCondition = document.ReportForm.colCondition;
		var inputValue = document.ReportForm.inputValue;
		var Options = colType.options;
		var receiveStr = "";
		var i = 0;
		new Ajax.Request('ajaxquerycolumn.do?method=getColumQuery&tableid='+encodeURIComponent(tableId), {
		  method: 'post',
		  asynchronous: 'false',
		  onSuccess: function(transport) {
		     receiveStr=transport.responseText;
		     if("" != receiveStr){
		     	var leng  = Options.length;
				for( var j=0; j< leng;j++){
					colType.remove(Options[0]);
				}
				var colArr = receiveStr.split("\|");
				for(; i < colArr.length; i++){
					var splitStr = colArr[i].split(",");
					colType.options[i] = new Option(splitStr[0],splitStr[0]+","+splitStr[1]+","+splitStr[2]);
				}
					changeCol();
			  }
			  if(null == receiveStr || "" == receiveStr){
				 colType.options.length=0;
				 colCondition.options.length = 0;
			  }
		  }
		});	
		
		
	}
	function ReSetScriptText(){

	}
	function reportError(request)
	{
	
	}
	
	function colChnage(obj){
	
		var value = obj.value;
		if(value == " desc "){
			document.ReportForm.inputValue.disabled = true;
			document.ReportForm.relation.disabled = true;
		}else if(value == " asc "){
			document.ReportForm.inputValue.disabled = true;
			document.ReportForm.relation.disabled = true;
		}else if( value == " group by "){
			document.ReportForm.inputValue.disabled = true;
			document.ReportForm.relation.disabled = true;
		}else{
			document.ReportForm.inputValue.disabled = false;
			document.ReportForm.relation.disabled = false;
		}
		
		if(value == "sum" || value == "count"){
			document.ReportForm.dataType.value="number";
			document.ReportForm.conditionvalue.style.display = "";
		}else{
			document.ReportForm.conditionvalue.style.display = "none";
		}
		
	}
	//更换列的显示后，更换查询条件的显示

	function changeCol(){
		var colTypes = document.ReportForm.colType.options;
		for(var i=0; i<colTypes.length; i++){
			if(colTypes[i].selected){
				var optionValue = colTypes[i].value;
				var valArr = optionValue.split("$");
				var itemId = valArr[0];
				var colType = valArr[1];
				//alert(" colType " + );
				getColCondition(colType);
			}
		}
	}
	//生成列的查询条件
	function getColCondition(colType){
		var colCondition = document.ReportForm.colCondition;
		var op = colCondition.options;
		var choosetable = document.ReportForm.choosetable.value;
		var chooserow = document.ReportForm.colType.value;
		//清除掉之前的option
		for(var i=0;i<op.length;i++){
			colCondition.remove(op[i]);
		}
		// 把分组求和查询条件隐藏

		document.ReportForm.conditionvalue.style.display = "none";
		
		var index = 0;
		if(null == colType || "" == colType){
			colCondition.options.length = 0;
		}else if("1" == colType){
			colCondition.options[index++] = new Option("等于","=");
			colCondition.options[index++] = new Option("不等于","!=");
			colCondition.options[index++] = new Option("大于",">");
			colCondition.options[index++] = new Option("小于","<");
			colCondition.options[index++] = new Option("大于等于",">=");
			colCondition.options[index++] = new Option("小于等于","<=");
			colCondition.options[index++] = new Option("类似于"," LIKE ");
			colCondition.options[index++] = new Option("不类似于"," NOT LIKE ");
			colCondition.options[index++] = new Option("降序"," desc ");
			colCondition.options[index++] = new Option("升序"," asc ");
			colCondition.options[index++] = new Option("分组"," group by ");
			colCondition.options[index++] = new Option("求和","sum");
			colCondition.options[index++] = new Option("求记录","count");
			//
			colCondition.options[index++] = new Option("分组和升序"," order by count(*) asc ");
			colCondition.options[index++] = new Option("分组和降序"," order by count(*) desc ");
			
			document.ReportForm.dataType.value="string";
		}else if( "2" == colType ){
			if(!(choosetable.indexOf("serverlog")!=-1 && chooserow.indexOf("eventid")!=-1)){
				colCondition.options[index++] = new Option("等于","=");
				colCondition.options[index++] = new Option("不等于","!=");
				colCondition.options[index++] = new Option("大于",">");
				colCondition.options[index++] = new Option("小于","<");
				colCondition.options[index++] = new Option("大于等于",">=");
				colCondition.options[index++] = new Option("小于等于","<=");
				colCondition.options[index++] = new Option("类似于"," LIKE ");
				colCondition.options[index++] = new Option("不类似于"," NOT LIKE ");
				colCondition.options[index++] = new Option("降序"," desc ");
				colCondition.options[index++] = new Option("升序"," asc ");
				colCondition.options[index++] = new Option("分组"," group by ");
				colCondition.options[index++] = new Option("求和","sum");
				colCondition.options[index++] = new Option("求记录","count");
				//
				colCondition.options[index++] = new Option("分组和升序"," order by count(*) asc ");
				colCondition.options[index++] = new Option("分组和降序"," order by count(*) desc ");
			}else{
				colCondition.options[index++] = new Option("等于","=");
				colCondition.options[index++] = new Option("不等于","!=");
				colCondition.options[index++] = new Option("降序"," desc ");
				colCondition.options[index++] = new Option("升序"," asc ");
				colCondition.options[index++] = new Option("分组"," group by ");
				colCondition.options[index++] = new Option("求和","sum");
				colCondition.options[index++] = new Option("求记录","count");
				//
				colCondition.options[index++] = new Option("分组和升序"," order by count(*) asc ");
				colCondition.options[index++] = new Option("分组和降序"," order by count(*) desc ");
			}
			document.ReportForm.dataType.value="number";
		}else if("3" == colType ){
			colCondition.options[index++] = new Option("等于","=");
			colCondition.options[index++] = new Option("不等于","!=");
			colCondition.options[index++] = new Option("大于",">");
			colCondition.options[index++] = new Option("小于","<");
			colCondition.options[index++] = new Option("大于等于",">=");
			colCondition.options[index++] = new Option("小于等于","<=");
			colCondition.options[index++] = new Option("类似于"," LIKE ");
			colCondition.options[index++] = new Option("不类似于"," NOT LIKE ");
			colCondition.options[index++] = new Option("降序"," desc ");
			colCondition.options[index++] = new Option("升序"," asc ");
			colCondition.options[index++] = new Option("分组"," group by ");
			colCondition.options[index++] = new Option("求和","sum");
			colCondition.options[index++] = new Option("求记录","count");
			//
			colCondition.options[index++] = new Option("分组和升序"," order by count(*) asc ");
			colCondition.options[index++] = new Option("分组和降序"," order by count(*) desc ");
			document.ReportForm.dataType.value= "date" ;
			
		}
	}

	function changeInput(obj){
		if(obj=="登录")
			return 1;
		else if(obj=="登出")
			return 2;
		else if(obj=="新建分组")
			return 3;
		else if(obj=="修改分组")
			return 4;
		else if(obj=="删除分组")
			return 5;
		else if(obj=="新建用户")
			return 6;
		else if(obj=="修改用户")
			return 7;
		else if(obj=="删除用户")
			return 8;
		else if(obj=="新建角色")
			return 9;
		else if(obj=="修改角色")
			return 10;
		else if(obj=="删除角色")
			return 11;
		else if(obj=="新建策略")
			return 12;
		else if(obj=="修改策略")
			return 13;
		else if(obj=="分发策略")
			return 14;
		else if(obj=="删除策略")
			return 15;
		else if(obj=="移动客户端")
			return 16;
		else if(obj=="卸载客户端")
			return 17;
		else if(obj=="下载客户端")
			return 18;
		else if(obj=="生成客户端")
			return 19;
		else if(obj=="删除客户端")
			return 20;
		else if(obj=="服务器设置")
			return 21;
		else if(obj=="消息发送")
			return 22;
		else if(obj=="新建时间对象")
			return 23;
		else if(obj=="修改时间对象")
			return 24;
		else if(obj=="删除系统日志")
			return 31;
		else if(obj=="删除程序行为")
			return 34;
		else if(obj=="删除服务器日志")
			return 36;
		else if(obj=="删除操作系统日志")
			return 38;
		else if(obj=="？")
			return "？";
		else return 0;
	}
	
	//添加查询的选项
	function addCondition(){
		var queryResult = document.ReportForm.queryResult;
		var inputValue = document.ReportForm.inputValue.value; 
		var index = queryResult.options.length;
		var reportinfo=document.ReportForm.reportinfo.value;
		var colCondition = document.ReportForm.colCondition;
		var choosetable = document.ReportForm.choosetable.value;
		var chooserow = document.ReportForm.colType.value;
		var limits=document.ReportForm.limits.value;
		if(document.ReportForm.choosetable.value=="" && limits!=""){
			queryResult.options[index++] = new Option(" limit "+limits , " limit "+limits);
			return false;
		}
		if(document.ReportForm.choosetable.value==""){
			alert("请输入查询条件！");
			return false;
		}
		if(null == inputValue){
			if(colCondition.value == " desc " || colCondition.value==" asc " || colCondition.value==" group by " || colCondition.value==" order by count(*) asc " || colCondition.value==" order by count(*) desc "){
			}else {
				if(reportinfo.split(";")[2]==1){
					alert("静态模板必须输入查询条件的限制值！");
					return;
				}
			}
		}
		inputValue = trim(inputValue);
		if("" == inputValue){
			if(colCondition.value == " desc " || colCondition.value==" asc " || colCondition.value==" group by " || colCondition.value==" order by count(*) asc " || colCondition.value==" order by count(*) desc "){
			}else {
				if(reportinfo.split(";")[2]==1){
					alert("静态模板必须输入查询条件的限制值！");
					return;
				}
				inputValue="？";
			}
		}
		if("number"==document.ReportForm.dataType.value){
			if(choosetable.indexOf("serverlog")!=-1 && chooserow.indexOf("eventid")!=-1)
				inputValue = changeInput(inputValue);
			else
				var intFlag = checkInteger(document.ReportForm.inputValue.value);
			if(false == intFlag){
				document.ReportForm.inputValue.focus();
				return;
			}
		}
		
		//获得radio的值

		var relation = getRelation();
		if("" != relation){
			var colOptions = document.ReportForm.colType.options;
		    var colText = "";
		    var colVal = "";
		    var colId = "";
		    var colname="";
		    var tabname="";
		    var conditionOptions = document.ReportForm.colCondition.options;
		    var choosetable = document.ReportForm.choosetable.options;
		    //获得数据类型
			var dataType = document.ReportForm.dataType.value;
		    var conditionValue = "";
		    for(var j=0; j < colOptions.length; j++){
		    	if(colOptions[j].selected){
		    	    colText = colOptions[j].text;
		    	    colVal = colOptions[j].value;
		    	    colId = (colVal.split("$"))[0];
		    	    coltype=(colVal.split("$"))[1];
		    	    colname=(colId.split(","))[2];
		    	    break; 
		    	}
		    }
		    for(j=0; j<conditionOptions.length; j++){
		    	if(conditionOptions[j].selected){
		    		conditionValue = conditionOptions[j].value;
		    		break;
		    	}
		    }
		    for(j=0; j<choosetable.length; j++){
		    	if(choosetable[j].selected){
		    		colname = choosetable[j].value.split(";")[1]+"."+colname;
		    		break;
		    	}
		    }
		    // 求和或求记录条件
		    var cv = document.ReportForm.conditionvalue.value;
		    
		    //如果是字符串类型
		    if("string" == dataType){
		    	if("=" == conditionValue || "!=" == conditionValue){
		    		queryResult.options[index++] = new Option(relation + " " + colText +  " " + conditionValue + " '" + inputValue + "'", relation + " " +colname+" "+ conditionValue + " '" + inputValue + "'$"+coltype);
		    	}else if(" LIKE " ==  conditionValue || " NOT LIKE " ==  conditionValue){
		    		queryResult.options[index++] = new Option(relation + " " + colText +  " " + conditionValue + " ( '％" + inputValue + "％' )",relation  +" "+colname+" "+  conditionValue + " '％" + inputValue + "％'$"+coltype);
		    	}else if(" desc " ==  conditionValue || " asc " ==  conditionValue ){
		    		queryResult.options[index++] = new Option("order by " + colText + conditionValue , "order by " +colname + conditionValue);
		    	}else if(" group by " ==  conditionValue){
		    		queryResult.options[index++] = new Option("group by " + colText , "group by " + colname);
		    	}else if(" order by count(*) asc "==conditionValue){
		    		queryResult.options[index++] = new Option(" order by count(*) asc " , " order by count(*) asc ");
		    	}else if(" order by count(*) desc "==conditionValue){
		    		queryResult.options[index++] = new Option(" order by count(*) desc " , " order by count(*) desc ");
		    	}else {
		    		queryResult.options[index++] = new Option(relation + " " + colText +  " " + conditionValue + " ('" + inputValue + "')",  relation + " " +colname+" "+ conditionValue + " ('" + inputValue + "')$"+coltype);
		    	}
		    }
		    if("number" == dataType){
		    	if(" desc " ==  conditionValue || " asc " ==  conditionValue ){
		    		queryResult.options[index++] = new Option("order by " + colText + conditionValue , "order by " +colname + conditionValue);
		    	}else if(" group by " ==  conditionValue){
		    		queryResult.options[index++] = new Option("group by " + colText , "group by " + colname);
		    	}else if("sum" ==  conditionValue || "count" ==  conditionValue){
		    		queryResult.options[index++] = new Option("having "+conditionValue +"(" + colText +") " + cv+" " +  inputValue , "having "+conditionValue+ "(" + colname + ") " + cv+" " + inputValue );
		    	}else if(" order by count(*) asc "==conditionValue){
		    		queryResult.options[index++] = new Option(" order by count(*) asc " , " order by count(*) asc ");
		    	}else if(" order by count(*) desc "==conditionValue){
		    		queryResult.options[index++] = new Option(" order by count(*) desc " , " order by count(*) desc ");
		    	}
		    	else {
		    		queryResult.options[index++] = new Option(relation + " " + colText +  " " + conditionValue + " " + inputValue,relation + " "  +colname+" "+ conditionValue + " " + " '"+ inputValue + "'$"+coltype);
		    	}
		    }
		    if("date" == dataType){
		    	if(" desc " ==  conditionValue || " asc " ==  conditionValue ){
		    		queryResult.options[index++] = new Option("order by " + colText + conditionValue , "order by " +colname + conditionValue);
		    	}else if(" group by " ==  conditionValue){
		    		queryResult.options[index++] = new Option("group by " + colText , "group by " + colname);
		    	}else if(" LIKE " ==  conditionValue || " NOT LIKE " ==  conditionValue){
		    		queryResult.options[index++] = new Option(relation + " " + colText +  " " + conditionValue + " '％" + inputValue + "％' ",relation + " "  +colname+" "+ conditionValue + " " + " '％" + inputValue + "％'$"+coltype);
		    	}else if(" order by count(*) asc "==conditionValue){
		    		queryResult.options[index++] = new Option(" order by count(*) asc " , " order by count(*) asc ");
		    	}else if(" order by count(*) desc "==conditionValue){
		    		queryResult.options[index++] = new Option(" order by count(*) desc " , " order by count(*) desc ");
		    	}else{
		    		queryResult.options[index++] = new Option(relation + " " + colText +  " " + conditionValue + " '" + inputValue + "' ",relation + " "  +colname+" "+ conditionValue + " " + " '" + inputValue + "'$"+coltype);
		    	}
		    }
		}
		if(document.ReportForm.choosetable.value!="" && limits!=""){
			queryResult.options[index++] = new Option(" limit "+limits , " limit "+limits);
		}
		document.ReportForm.inputValue.value = "";
	}
	//获得radio选项的值

	function getRelation(){
		var relations = document.ReportForm.relation;
		for(var i=0; i<relations.length; i++){
			if(relations[i].selected){
				return relations[i].value; 
			}
		}
		return "";
	}
	function checkInteger(Object){
	 	var strInteger=Object;
		 if(strInteger.length==0){
	 		 return true;
		 }else{
	 	 var pattern = /^-?\d+$/;
	 	 if(strInteger.match(pattern)==null){
	 	    alert("只能输入数字");
	        return false;
	 	 }else{
	        return true;
	     }
	   }
    }
	//trim函数，去除空格和制表位

	function trim(str){
	    for(var i = 0; i<str.length && (str.charAt(i)==" "||str.charAt(i)=="\t"); i++ );
	    for(var j =str.length; j>0 && (str.charAt(j-1)==" " || str.charAt(j-1)=="\t"); j--);
	    if(i>j)  return  "";  
	    return  str.substring(i,j);  
	}
	//移除添加的选项
	function removeCondition(){
		var queryResult = document.ReportForm.queryResult;
		var queryOptions = queryResult.options;
		var index = queryOptions.length;
		var flag=false;
		for(var i=0; i<index; i++){
			if(queryOptions[i].selected){
				queryResult.remove(i);
				index--;
				flag=true;
			}
		}
		if(!flag){
			alert("选择删除项目！");
			return false;
		}
	}
	//查询
	function queryCondition(objform){
		var queryResults=objform.queryResult.value;
		var condlist=objform.queryResult.length;
	  	for(i=0;i<condlist;i++){
	  		var temp=objform.queryResult.options[i].value;
	  		queryResults=queryResults+objform.queryResult.options[i].text+";"+temp+"||";
	  	}
	  	
	  	var arrays = document.ReportForm.arrays.value;
		var tempArr = arrays.split("|");
		var exp = 0;
		for(var i=0;i<tempArr.length;i++){
			if( "" != tempArr[i]){
				var tempStr = tempArr[i].split(",");
				if(tempStr[7] != 1 && tempStr[7] != ""){
					exp++;
				}
			}
			
		}
		if(exp > 0){
			if(queryResults.indexOf("group by")== -1){
				alert("定制输出列输出方式选择了非简单输出的方式\n         请选择一列作为分组");
				return false;
			}
		}
		if(queryResults.indexOf("ID")== -1){
					alert("请将此表中的 ID 信息添加为分组");
					return false;
		}
		
		var arrays=objform.arrays.value;
		var levelthree=objform.levelthree.value;
		var coordx = objform.coordx.value;
		var coordy = objform.coordy.value;
		var biaozhi = objform.biaozhi.value;
		var condition = "queryResults="+encodeURIComponent(encodeURIComponent(queryResults))
						+"&arrays="+encodeURIComponent(encodeURIComponent(arrays))
						+"&levelthree="+encodeURIComponent(encodeURIComponent(levelthree))
						+"&coordx="+encodeURIComponent(encodeURIComponent(coordx))
						+"&coordy="+encodeURIComponent(encodeURIComponent(coordy))
						+"&biaozhi="+encodeURIComponent(encodeURIComponent(biaozhi));
		//alert(queryResults);
		//window.open("newreportform.do?method=queryTempRes&"+condition,"mydialog","Top=80,Left=150,Width=700,Height=600,help=no,scroll=yes,status=yes");
		objform.target="_blank";
		objform.action="newreportform.do?method=queryTempRes&queryResults="+encodeURIComponent(queryResults);
		objform.submit();
		}
	//保存
	function savemodel(objform){
		var condlist=objform.queryResult.length;
		//alert(condlist);
	  	var queryResult="";
	  	for(i=0;i<condlist;i++){
	  		var temp=objform.queryResult.options[i].value;
	  		
	  		//alert(objform.queryResult.options[i].text);
	  		//alert(temp);
	  		queryResult=queryResult+objform.queryResult.options[i].text+";"+temp+"||";
	  	}
	  	
		var reportinfo=document.ReportForm.reportinfo.value;
		if("" == queryResult && reportinfo.split(";")[2]==0){
			alert("动态模板至少选择一个查询条件！");
			return false;
		}
		
		var arrays = document.ReportForm.arrays.value;
		var tempArr = arrays.split("|");
		var exp = 0;
		for(var i=0;i<tempArr.length;i++){
			
			if( "" != tempArr[i]){
			
				var tempStr = tempArr[i].split(",");
				if(tempStr[7] != 1 && tempStr[7] != ""){
					
					exp++;
				}
			}
			
		}
		
		//alert(queryResult);
		if(exp > 0){
			if(queryResult.indexOf("group by")== -1){
				alert("定制输出列输出方式选择了非简单输出的方式\n         请选择一列作为分组");
				return false;
			}
		}
		/*if(queryResult.indexOf("ID")== -1){
					alert("请将此表中的 ID 信息设置为分组");
					return false;
		}*/
		objform.target="";
		//alert(queryResult);
		objform.action="newreportform.do?method=saveReportModel&queryResults="+encodeURIComponent(encodeURIComponent(queryResult));
		objform.submit();
	}
	//另存为

	function saveasmodel(objform){
		var condlist=objform.queryResult.length;
	  	var queryResult="";
	  	for(i=0;i<condlist;i++){
	  		var temp=objform.queryResult.options[i].value;
	  		queryResult=objform.queryResult.options[i].text+";"+queryResult+temp+"||";
	  	}
		
		var reportinfo=objform.reportinfo.value;
		var levelthree=objform.levelthree.value;
		
//		window.open("newreportform.do?method=saveAsReportModel&queryResults="
//					+encodeURIComponent(queryResult)+"&reportinfo="
//					+encodeURIComponent(reportinfo)+"&levelthree="
//					+encodeURIComponent(levelthree)+"&saveas=saveas&arrays="
//					+encodeURIComponent(objform.arrays.value)+"&levelthree="
//					+encodeURIComponent(objform.levelthree.value)+"&buttons="
//					+encodeURIComponent(objform.buttons.value),"mydialog","Top=80,Left=150,Width=200,Height=200,help=no,scroll=yes,status=yes");

		objform.target="_blank";
		objform.action="newreportform.do?method=saveAsReportModel";
		objform.submit();
		//objform.action="newreportform.do?method=saveReportModel&queryResults="+queryResult;
		//objform.submit();
	}
	
	function cancels(objform){
		objform.target="";
		window.location = "reportFormQuery.do?method=initPage";
	}
	</script>
	</head>

	<body>
		<html:form action="newreportform.do" method="post" target="_blank">

			<logic:notEqual name="useraction" value="editReport">
				<!--  主table-->
				<table width="70%" border="0" cellspacing="0" cellpadding="0"
					style="margin-left: 4px; margin-top: 2px">
					<tr>
						<!-- left area -->
						<td>
							<div class="framDiv">
								<!--  左侧table-->
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									style="padding-left: 1px; padding-top: 1px; padding-right: 1px;">

									<!-- 用户信息 -->
									<tr>
										<td class='r2titler'>
											<b>第四步</b>
										</td>
									</tr>
									<tr>
										<td
											style="padding-left: 100px; padding-top: 10px; padding-bottom: 10px;">
											<table>
												<tr>
													<input type="hidden" name="reportinfo"
														value="${reportinfo }">
													<input type="hidden" name="leveltwo" value="${leveltwo }">
													<input type="hidden" name="levelthree"
														value="${levelthree }">
													<input type="hidden" name="biaozhi"
														value="${biaozhi}">	
													<input type="hidden" name="arrays" value="${arrays }">
													<input type="hidden" name="buttons" value="${buttons }">
													<input type="hidden" name="coordx" value="${coordx }">
													<input type="hidden" name="coordy" value="${coordy }">

													<td>
														请选择表：
													</td>
													<td>
														<select name="choosetable" style="width: 100px;"
															onchange="changeTemplate();">
															<option value="">
																选择表

															</option>
															<logic:notEmpty name="choosbletable">
																<logic:iterate id="list" name="choosbletable">
																	<option value="${list.id };${list.tableName }">
																		${list.tableNameDescription }
																	</option>
																</logic:iterate>
															</logic:notEmpty>
														</select>
													</td>
													<td width="15%" align="center">
														&nbsp;
													</td>
													<td style="width: 300px;" rowspan="5">
														<select name="queryResult" style="width: 85%;" size="6">
														</select>
													</td>
												</tr>
												<tr>
													<td>
														选择字段：

													</td>
													<td>
														<select name="colType" style="width: 100px; "
															onchange="changeCol();">
														</select>
													</td>
													<td align="center">
														<input type="button" value="增   加" class="btnstyle"
															name="addCond" onclick="javascript:addCondition();">
													</td>
												</tr>
												<tr>
													<td>
														条件关系：

													</td>
													<td>
														<select name="colCondition" style="width: 100px;"
															onchange="colChnage(this);">
														</select>
														<input type="hidden" name="dataType">
													</td>
													<td align="center"></td>
												</tr>
												<tr>
													<td>
														<select name="conditionvalue" id="conditionvalue"
															style="width: 80px; display: none">
															<option value="=">
																等于
															</option>
															<option value="!=">
																不等于

															</option>
															<option value="&lt;">
																小于
															</option>
															<option value="&gt;">
																大于
															</option>
															<option value="&lt;=">
																小于等于
															</option>
															<option value="&gt;=">
																大于等于
															</option>
														</select>
													</td>
													<td>
														<input type="text" name="inputValue" size="11">
													</td>
													<td align="center">
														<input type="button" value="删   除" class="btnstyle"
															name="delCond" onclick="removeCondition();">
													</td>
												</tr>
												<tr>
													<td>
														限制条件：

													</td>
													<td>
														<select name="relation" style="width: 100px;">
															<option value="and" selected="selected">
																与

															</option>
															<option value="or">
																或

															</option>
														</select>
													</td>
													<td align="center"></td>
												</tr>
												<tr>
													<td>
														取结果的前：
													</td>
													<td>
														<input type="text" name="limits" value="" size="11">
														条
													</td>
													<td align="center"></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
				<table width="780px" border="0" cellspacing="0" cellpadding="0"
					style="margin-left: 4px;">
					<!-- 空行 -->
					<tr>
						<td class="td_detail_separator">
						</td>
					</tr>

					<tr>
						<td>
							<input type="button" class="btnyh" id="btnSave" value="上一步"
								onclick="pre();" />
							<input type="button" class="btnyh" id="btnSave" value="保   存"
								onclick="savemodel(document.ReportForm);" />
							<input type="button" class="btnyh" id="btnCancel" value="查询预览"
								onclick="queryCondition(document.ReportForm);" />
							<input type="button" class="btnyh" id="btnCancel" value="取  消"
								onclick="cancels(document.ReportForm);" />
						</td>
					</tr>
				</table>
			</logic:notEqual>

			<logic:equal name="useraction" value="editReport">
				<table width="70%" border="0" cellspacing="0" cellpadding="0"
					style="margin-left: 4px; margin-top: 2px">
					<tr>
						<!-- left area -->
						<td>
							<div class="framDiv">
								<!--  左侧table-->
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									style="padding-left: 1px; padding-top: 1px; padding-right: 1px;">

									<!-- 用户信息 -->
									<tr>
										<td class='r2titler'>
											<b>第四步</b>
										</td>
									</tr>
									<tr>
										<td
											style="padding-left: 100px; padding-top: 10px; padding-bottom: 10px;">
											<table>
												<tr>
													增加限定条件
												</tr>
												<tr>
													<input type="hidden" name="reportinfo"
														value="${reportinfo }">
													<input type="hidden" name="leveltwo" value="${leveltwo }">
													<input type="hidden" name="levelthree"
														value="${levelthree }">
														<input type="hidden" name="biaozhi"
														value="${biaozhi}">	
													<input type="hidden" name="arrays" value="${arrays }">
													<input type="hidden" name="buttons" value="${buttons }">
													<input type="hidden" name="reportFormId"
														value="${reportFormId }">
													<input type="hidden" name="coordx" value="${coordx }">
													<input type="hidden" name="coordy" value="${coordy }">

													<td>
														请选择表：
													</td>
													<td>
														<select name="choosetable" style="width: 100px;"
															onchange="changeTemplate();">
															<option value="">
																选择表

															</option>
															<logic:notEmpty name="choosbletable">
																<logic:iterate id="list" name="choosbletable">
																	<option value="${list.id };${list.tableName }">
																		${list.tableNameDescription }
																	</option>
																</logic:iterate>
															</logic:notEmpty>
														</select>
													</td>
													<td width="15%" align="center">
														&nbsp;
													</td>
													<td style="width: 300px;" rowspan="5">
														<select name="queryResult" style="width: 85%;" size="6">
															<!-- 
    				<option 
    					<logic:notEmpty name="listv">
    						<logic:iterate id="list" name="listv">value="${list }" 
    						</logic:iterate>
    					</logic:notEmpty>>
    					<logic:notEmpty name="listt">
	    					<logic:iterate id="list" name="listt">
	    						${list }
	    					</logic:iterate>
    					</logic:notEmpty>
    				</option>
    			 -->
														</select>
														<script type="text/javascript">
    				var values = new Array();
    				var names = new Array() ;
    				var i =0;
    				<logic:notEmpty name="listv">
    					<logic:iterate id="list" name="listv">
    						values[i++]="${list }" 
    					</logic:iterate>
    				</logic:notEmpty>
    				i = 0;
    				<logic:notEmpty name="listt">
    					<logic:iterate id="list" name="listt">
    						names[i++]="${list }" 
    					</logic:iterate>
    				</logic:notEmpty>
    				if(values.length == names.length && values.length > 0){
    					var queryResult = document.ReportForm.queryResult ;
    				//	alert(queryResult);
    					for(i=0;i<values.length;i++){
    						while(names[i].indexOf("%") != -1){
		    					names[i] = names[i].replace("%","％");
		    				}
		    				while(values[i].indexOf("%") != -1){
		    					values[i] = values[i].replace("%","％");
		    				}
    						//alert(names[i]);alert(values[i]);
    						queryResult.options[i] = new Option(names[i],values[i]);
    					}
    				}
    			</script>

													</td>
												</tr>
												<tr>
													<td>
														选择字段：

													</td>
													<td>
														<select name="colType" style="width: 100px;"
															onchange="changeCol();">
														</select>
													</td>
													<td align="center">
														<input type="button" value="增   加" class="btnstyle"
															name="addCond" onclick="javascript:addCondition();">
													</td>
												</tr>
												<tr>
													<td>
														条件关系：

													</td>
													<td>
														<select name="colCondition" style="width: 100px;"
															onchange="colChnage(this);">
														</select>
														<input type="hidden" name="dataType">
													</td>
													<td align="center">
														<br>
													</td>
												</tr>
												<tr>
													<td>
														<select name="conditionvalue" id="conditionvalue"
															style="width: 80px; display: none">
															<option value="=">
																等于
															</option>
															<option value="!=">
																不等于

															</option>
															<option value="&lt;">
																小于
															</option>
															<option value="&gt;">
																大于
															</option>
															<option value="&lt;=">
																小于等于
															</option>
															<option value="&gt;=">
																大于等于
															</option>
														</select>
													</td>
													<td>
														<input type="text" name="inputValue" size="11">
													</td>
													<td align="center">
														<input type="button" value="删   除" class="btnstyle"
															name="delCond" onclick="javascript:removeCondition();">
													</td>
												</tr>
												<tr>
													<td>
														限制条件：

													</td>
													<td>
														<select name="relation" style="width: 100px;">
															<option value="and" selected="selected">
																与

															</option>
															<option value="or">
																或

															</option>
														</select>
													</td>
													<td align="center"></td>
												</tr>
												<tr>
													<td>
														取结果的前：
													</td>
													<td>
														<input type="text" name="limits" value="" size="13">
														条
													</td>
													<td align="center"></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
				<table width="780px" border="0" cellspacing="0" cellpadding="0"
					style="margin-left: 4px;">
					<!-- 空行 -->
					<tr>
						<td class="td_detail_separator">
						</td>
					</tr>

					<tr>
						<td>
							<input type="button" class="btnyh" id="btnSave" value="上一步"
								onclick="pre();" />
							<input type="button" class="btnyh" id="btnSave" value="保   存"
								onclick="savemodel(document.ReportForm);" />
							<input type="button" class="btnyh" id="btnSave" value="另存为"
								onclick="saveasmodel(document.ReportForm);" />
							<input type="button" class="btnyh" id="btnCancel" value="查询预览"
								onclick="queryCondition(document.ReportForm);" />
							<input type="button" class="btnyh" id="btnCancel" value="取  消"
								onclick="cancels(document.ReportForm);" />
						</td>
					</tr>
				</table>
			</logic:equal>
		</html:form>
	</body>
</html>
