<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="mainoffer.inc"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>

<%

String path = request.getContextPath();

	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
 
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

<title>定制输出列</title>
<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
<%-- <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> --%>
	<link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>

<script type="text/javascript">
	$(document).ready(function clicktd(){
	//找到name=event_td的单元格
	var numTd = $("td[name='event_td']");
	
	//单击这些td时，创建文本框


	numTd.click(function clicktd(){
		
		//获取当前点击的单元格对象
		var tdobj = $(this);
		
		//得到当前对象的父对象
		var objparent = tdobj.parent();
		//得到父对象中的隐藏值 需要更改其值


		var valueObj = objparent.children("td").eq(8).children("input");
		var arrays = valueObj.val().split(";");
		
		//获取单元格中的文本


		var text = tdobj.html();
		//创建对象
		var inputobj ;
		var flag = 0 ;
		//判断是创建文本框还是选择框


	
		
		if(text.lastIndexOf("&nbsp;") != -1){
			text = text.substring(0,text.lastIndexOf("&nbsp;"));
		}
		if(text == arrays[0]){
			inputobj = $("<input type='text'>");
		}else if(text == arrays[1]){
			inputobj = $("<input type='text' maxlength='3'>");
			flag = 1;
		}else if(text.indexOf("居左") != -1 ||text.indexOf("居中") != -1||text.indexOf("居右") != -1){
			inputobj = $("<select><option value='0'>居左</option><option value='1'>居中</option><option value='2'>居右</option></select>");
			flag = 2;
		}else if(text.indexOf("简单输出") != -1  ||text.indexOf("求和输出") != -1  ||  text.indexOf("求记录数输出") != -1){
			inputobj = $("<select><option value='1'>简单输出</option><option value='2'>求和输出</option><option value='3'>求记录数输出</option></select>");
			flag = 3;
		}
		//如果当前单元格中有文本框，就直接跳出方法
		//注意：一定要在插入文本框前进行判断


		if(tdobj.children("input").length>0){
			return false;
		}
		//清空单元格的文本
		tdobj.html("");
		
		inputobj.css("border","0")
				.css("font-size",tdobj.css("font-size"))
				.css("font-family",tdobj.css("font-family"))
				.css("background-color",tdobj.css("background-color"))
				.css("color","#C75F3E")
				.width(tdobj.width())
				.val(text)
				.appendTo(tdobj);
				
		inputobj.get(0).focus();
		//阻止文本框的点击事件
		inputobj.click(function(){
			return false;
		});
		
		
		
		//处理文本框上回车和esc按键的操作


		//jQuery中某个事件方法的function可以定义一个event参数，jQuery会屏蔽浏览器的差异，传递给我们一个可用的event对象
		inputobj.blur(function(){
			//alert(arrays[0]+ "  " + arrays[1]);
			var inputtext = $(this).val();
			
			if(!check(flag,inputtext)){
				return false;
			}
			if(flag == 0){
				addCoorder("coordx");
				addCoorder("coordy");			
			}else if(flag ==2){
				if( inputtext== 0){
					inputtext = "居左";
				}else if( inputtext== 1){
					inputtext = "居中";
				}else if( inputtext== 2){
					inputtext = "居右";
				}
			}else if(flag ==3){
				if( inputtext== 1){
					inputtext = "简单输出";
				}else if( inputtext== 2){
					inputtext = "求和输出";
				}else if( inputtext== 3){
					inputtext = "求记录数输出";
				}
			}
			
			tdobj.html(inputtext);
			
		});
		inputobj.keyup(function(event){
			//获取当前按键的键值


			//jQuery的event对象上有一个which的属性可以获得键盘按键的键值


			var keycode = event.which;
			//处理回车的情况


			if(keycode==13){
				var inputtext = $(this).val();
				if(!check(flag,inputtext)){
					return false;
				}
				if(flag == 0){
					addCoorder("coordx");
					addCoorder("coordy");			
				}else if(flag ==2){
					if( inputtext== 0){
						inputtext = "居左";
					}else if( inputtext== 1){
						inputtext = "居中";
					}else if( inputtext== 2){
						inputtext = "居右";
					}
				}else if(flag ==3){
					if( inputtext== 1){
						inputtext = "简单输出";
					}else if( inputtext== 2){
						inputtext = "求和输出";
					}else if( inputtext== 3){
						inputtext = "求记录数输出";
					}
				}
				tdobj.html(inputtext);
			}
			//处理esc的情况


			if(keycode == 27){
				//将td中的内容还原成text
				tdobj.html(text);
			}
		});
		//验证新输入的值


		function check(flag,inputtext){
			if(flag == 0){
				if(inputtext == ""){
					alert("列名不能为空！");
					return false;
				}else{
					if(!isNaN(Number(inputtext)) || Number(inputtext)>0){
						alert("列名不能全部是数字！");
						return false;
					}
					var reg = /^[^^“||~@&%\t-]+$/;
					if(inputtext.match(reg) == null){
						alert("列名不能输入怪异字符！");
						return false ;
					}
					arrays[0] = inputtext;
					back();
					
				}
			}else if(flag == 1 ){
				if(inputtext == ""){
					alert("列宽不能为空！");
					return false;
				}else{
					if(isNaN(Number(inputtext)) || Number(inputtext)<0){
						alert("列宽必须是有效数字！");
						return false;
					}
					arrays[1] = inputtext;
					back();
					
				}
			}else if(flag == 2){
				arrays[2] = inputtext;
				back();
			}else if(flag == 3){
				arrays[7] = inputtext;
				back();
			}
			return true;
		}
		
		function back(){
			var arraytext = "";
			//把改变的值传回隐藏值中
			for(var i=0; i<arrays.length;i++){
				if(arrays[i].lastIndexOf("&nbsp;") != -1){
					arraytext += arrays[i].substring(0,arrays[i].lastIndexOf("&nbsp;"))+";";
				}else{
					arraytext += arrays[i]+";";
				}
			}
			arraytext = arraytext.substring(0,arraytext.length-1);
			valueObj.val(arraytext);
		}
		
	});
});
	
		function nexts(){
			//定义一个数组 这个数组中存放添加的这些定制的输出列的信息



			var i=contentTable.rows.length-1;
			var array="";
			f=document.ReportForm;
			var k=0;
			for(var i=0;i<f.elements.length;i++){
				if(f.elements[i].type=="checkbox" && f.elements[i].name=="rowvalues"){
					var tempv=f.elements[i].value;
					var tempArr=tempv.split(";");
					for(var j=0;j<tempArr.length;j++){
						array=array+tempArr[j]+",";
					}
					array=array+"|";
					k++;	
				}
			}
			//alert(array);
			//有图形显示增加判断X轴和Y轴


			var graphic = document.getElementById("graphic").value;
			if(graphic==1){
				if(f.coordx.value==""){
					alert("请选择以哪列作为横轴！");
					return false;
				}
				if(f.coordy.value==""){
					alert("请选择以哪列作为纵轴！");
					return false;
				}
				if(f.coordx.value==f.coordy.value){
					alert("横轴和纵轴不能相同！");
					return false;
				}
				var tempArr = array.split("|");
				var total = 1;
				//X轴必须为简单输出


				for(var i=0;i<tempArr.length;i++){
					var tempStr = tempArr[i].split(",");
					if(f.coordx.value == tempStr[0]){
						if(tempStr[7] != 1){
							alert("横轴必须是简单输出！");
							return false;
						}
					}
					if(tempStr[7] == 1){
						total++;
					}
				}
				
				//至少有列为求和输出或求记录数输出
				if(total == tempArr.length){
					alert("图形显示至少选择一列为求和输出或求记录数输出！");
					return false;
				}
			}
			if(k>0){
				var bool=false;
				document.getElementById("arrays").value = array;
				
				<logic:notEqual name="useraction" value="editReport">
					if(bool){
						f.action="newreportform.do?method=creatReportFormStep5&leveltwo="+f.leveltwo.value+"&levelthree="+f.levelthree.value;
						f.submit();
					}else{
						f.action="newreportform.do?method=creatReportFormStep4&leveltwo="+f.leveltwo.value+"&levelthree="+f.levelthree.value;
						f.submit();
					}
				</logic:notEqual>
				<logic:equal name="useraction" value="editReport">
					if(bool){
						
						f.action="editreportform.do?method=editReportFormStep5&leveltwo="+f.leveltwo.value+"&levelthree="+f.levelthree.value;
						f.submit();
					}else{
						
						f.action="editreportform.do?method=editReportFormStep4&leveltwo="+f.leveltwo.value+"&levelthree="+f.levelthree.value;
						f.submit();
					}
				</logic:equal>
			}else{
				alert("请定制输出列！");
				return false;
			}
			
			
		}
		function pres(){
			f=document.ReportForm;
			var reportinfo = f.reportinfo.value;
			var modelname1='${modelname1}';
			<logic:notEqual name="useraction" value="editReport">
				f.action="newreportform.do?method=creatReportFormStep2&reportinfo="+encodeURIComponent(encodeURIComponent(reportinfo))+"&leveltwo="+f.leveltwo.value+"&levelthree="+f.levelthree.value+"&modelname1="+encodeURIComponent(modelname1);
				f.submit();
			</logic:notEqual>
			<logic:equal name="useraction" value="editReport">
				f.action="editreportform.do?method=editReportFormStep2&reportinfo="+encodeURIComponent(encodeURIComponent(reportinfo))+"&leveltwo="+f.leveltwo.value+"&levelthree="+f.levelthree.value+"&modelname1="+encodeURIComponent(modelname1);
				f.submit();
			</logic:equal>
		}
		function additem(objform){
			if(objform.colname.value==""){
				alert("列名不能为空!");
				return false;
			}
			if(objform.colwidth.value==""){
				alert("列宽不能为空！");
				return false;
			}
			if(isNaN(Number(objform.colwidth.value)) ||Number(objform.colwidth.value)<0){
				alert("列宽必须是有效数字！");
				return false;
			}
			if(objform.choosetable.value==""){
				alert("请选择对应数据表！");
				return false;
			}
			f=objform;
			var array ;
			for(var i=0;i<f.elements.length;i++){
				if(f.elements[i].type=="checkbox" && f.elements[i].name=="rowvalues"){
					var tempv=f.elements[i].value;
					var tempArr=tempv.split(";");
					for(var j=0;j<tempArr.length;j++){
						array=array+tempArr[j]+",";
					}
					array=array+"|";
					//k++;
				}
			}
	var tempArr ;
			if(array != "" && null != array){
				tempArr = array.split("|");
			
				var _value = objform.colType.value.split(",")[1];
				for(var i=0;i<tempArr.length;i++){
					var tempStr = tempArr[i].split(",");
					if(tempStr[5] == _value){
						alert("不能选择重复列！");
						return false;
					}
				}
			}
			contentTable.insertRow();
			var i=contentTable.rows.length-1;
			contentTable.rows(i).insertCell().innerHTML=i;
			//列名
			var colname_td = contentTable.rows(i).insertCell();
			colname_td.name = "event_td";
			colname_td.innerHTML=objform.colname.value+"&nbsp;";
			//列宽
			var colwidth_td = contentTable.rows(i).insertCell();
			colwidth_td.name = "event_td";
			colwidth_td.innerHTML=objform.colwidth.value+"&nbsp;";
			
			var al="居左";
			if(objform.alignment.value=="0"){
				al="居左";
			}if(objform.alignment.value=="1"){
				al="居中";
			}if(objform.alignment.value=="2"){
				al="居右";
			}
			//对齐方式
			var alignment_td = contentTable.rows(i).insertCell();
			alignment_td.name="event_td";
			alignment_td.innerHTML=al;
			var temp=objform.choosetable.value.split(",");
			var temp2=temp[0].split(";");
			contentTable.rows(i).insertCell().innerHTML=temp[1]+"&nbsp;";
			var coltemp=objform.colType.value.split(",");
			var coltemp2=coltemp[2].split("$");
			contentTable.rows(i).insertCell().innerHTML=coltemp[0]+"&nbsp;";	
			var outtypes="";
			var outtypess="";
			if(objform.outtype[0].checked==true){
				outtypes="简单输出";
				outtypess="1";
			}if(objform.outtype[1].checked==true){
				outtypes="求和输出";
				outtypess="2";
			}if(objform.outtype[2].checked==true){
				outtypes="求记录数输出";
				outtypess="3";
			}
			var outtype_td = contentTable.rows(i).insertCell();
			outtype_td.name = "event_td";
			outtype_td.innerHTML=outtypes+"&nbsp;";
			contentTable.rows(i).insertCell().innerHTML="<input type='button'  value='删   除' class='btnstyle' onclick='deleteitem("+j+")'/><input type='button' style='margin-left:3px' value='上   移' class='btnstyle' onclick='moveup("+j+")'/><input type='button' style='margin-left:3px' value='下   移' class='btnstyle' onclick='movedown("+j+")'/>";
			//将每一行中的每一列的内容都复制给 每一行的隐藏域



			var allcolv=objform.colname.value+";"+objform.colwidth.value+";"+objform.alignment.value+";"+temp2[0]+";"+temp2[1]+";"+coltemp[1]+";"+coltemp2[0]+";"+outtypess;
			
			contentTable.rows(i).insertCell().innerHTML="<input type='checkbox' name='rowvalues' value='"+allcolv+"' style='display:none'>";
			
			for(var j=0;j<8;j++){
				contentTable.rows(i).cells(j).align="center";
			}
			addCoorder("coordx");
			addCoorder("coordy");
			clicktd();
		}
		
		function deleteitem(k){
			var i=contentTable.rows.length;
			if(i>2){
				for(var j=0;j<i;j++){
					var a=contentTable.rows(j).cells(0).innerHTML;
					if(a==k){
						contentTable.deleteRow(j);
						break;
					}
				}
			}else{
				contentTable.deleteRow(1);
			}
			var len = contentTable.rows.length;
			for(var j=1;j<len;j++){
				contentTable.rows(j).cells(0).innerHTML=j;
				contentTable.rows(j).cells(7).innerHTML="<input type='button'  value='删   除' class='btnstyle' onclick='deleteitem("+j+")'/><input type='button' style='margin-left:3px' value='上    移' class='btnstyle' onclick='moveup("+j+")'/><input type='button' style='margin-left:3px' value='下    移' class='btnstyle' onclick='movedown("+j+")'/>";
			}
			addCoorder("coordx");
			addCoorder("coordy");
		}
		
		function moveup(k){
			var i=contentTable.rows.length;
			var coln="";
			var colw="";
			var ali="";
			var totable="";
			var tocol="";;
			var outt="";
			var allvalue="";
			if(k==1){
				alert("已经是首行，无法在上移！");
			}else{
				if(i>2){
					for(var j=1;j<i;j++){
						//找到要上移的行的前一行



						var a=contentTable.rows(j).cells(0).innerHTML;
						
						if(a==k-1){
							coln=contentTable.rows(j).cells(1).innerHTML;
							colw=contentTable.rows(j).cells(2).innerHTML;
							ali=contentTable.rows(j).cells(3).innerHTML;
							totable=contentTable.rows(j).cells(4).innerHTML;
							tocol=contentTable.rows(j).cells(5).innerHTML;
							outt=contentTable.rows(j).cells(6).innerHTML;
							allvalue=contentTable.rows(j).cells(8).innerHTML;
							
							contentTable.rows(j).cells(1).innerHTML=contentTable.rows(j+1).cells(1).innerHTML;
							contentTable.rows(j).cells(2).innerHTML=contentTable.rows(j+1).cells(2).innerHTML;
							contentTable.rows(j).cells(3).innerHTML=contentTable.rows(j+1).cells(3).innerHTML;
							contentTable.rows(j).cells(4).innerHTML=contentTable.rows(j+1).cells(4).innerHTML;
							contentTable.rows(j).cells(5).innerHTML=contentTable.rows(j+1).cells(5).innerHTML;
							contentTable.rows(j).cells(6).innerHTML=contentTable.rows(j+1).cells(6).innerHTML;
							contentTable.rows(j).cells(8).innerHTML=contentTable.rows(j+1).cells(8).innerHTML;
							
							contentTable.rows(j+1).cells(1).innerHTML=coln;
							contentTable.rows(j+1).cells(2).innerHTML=colw;
							contentTable.rows(j+1).cells(3).innerHTML=ali;
							contentTable.rows(j+1).cells(4).innerHTML=totable;
							contentTable.rows(j+1).cells(5).innerHTML=tocol;
							contentTable.rows(j+1).cells(6).innerHTML=outt;
							contentTable.rows(j+1).cells(8).innerHTML=allvalue;
							break;
						}
					}
				}else{
					alert("已经是首行，无法在上移！");
				}
			}
		}
		
		function movedown(k){
			var i=contentTable.rows.length;
			var coln="";
			var colw="";
			var ali="";
			var totable="";
			var tocol="";;
			var outt="";
			var allvalue="";
			if(k==i-1){
				alert("已经是最后一行，无法在下移！");
			}else{
				if(i>2){
					for(var j=1;j<i;j++){
						//找到要上移的行的前一行



						var a=contentTable.rows(j).cells(0).innerHTML;
						if(a==k){
							coln=contentTable.rows(j).cells(1).innerHTML;
							colw=contentTable.rows(j).cells(2).innerHTML;
							ali=contentTable.rows(j).cells(3).innerHTML;
							totable=contentTable.rows(j).cells(4).innerHTML;
							tocol=contentTable.rows(j).cells(5).innerHTML;
							outt=contentTable.rows(j).cells(6).innerHTML;
							allvalue=contentTable.rows(j).cells(8).innerHTML;
							
							contentTable.rows(j).cells(1).innerHTML=contentTable.rows(j+1).cells(1).innerHTML;
							contentTable.rows(j).cells(2).innerHTML=contentTable.rows(j+1).cells(2).innerHTML;
							contentTable.rows(j).cells(3).innerHTML=contentTable.rows(j+1).cells(3).innerHTML;
							contentTable.rows(j).cells(4).innerHTML=contentTable.rows(j+1).cells(4).innerHTML;
							contentTable.rows(j).cells(5).innerHTML=contentTable.rows(j+1).cells(5).innerHTML;
							contentTable.rows(j).cells(6).innerHTML=contentTable.rows(j+1).cells(6).innerHTML;
							contentTable.rows(j).cells(8).innerHTML=contentTable.rows(j+1).cells(8).innerHTML;
							
							contentTable.rows(j+1).cells(1).innerHTML=coln;
							contentTable.rows(j+1).cells(2).innerHTML=colw;
							contentTable.rows(j+1).cells(3).innerHTML=ali;
							contentTable.rows(j+1).cells(4).innerHTML=totable;
							contentTable.rows(j+1).cells(5).innerHTML=tocol;
							contentTable.rows(j+1).cells(6).innerHTML=outt;
							contentTable.rows(j+1).cells(8).innerHTML=allvalue;
							break;
						}
					}
				}else{
					alert("已经是最后一行，无法在下移！");
				}
			}
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
					//alert(choosetables[i].value);
					break;
				}
			}
		}
	}
	//生成对应表的列名称


/*
	function getColType(tableId){
		var colType = document.ReportForm.colType;
		var colCondition = document.ReportForm.colCondition;
		var inputValue = document.ReportForm.inputValue;
		var receiveStr = "";
		var i = 0;
		new Ajax.Request('ajaxquerycolumn.do?method=getColumQuery&tableid='+encodeURIComponent(tableId), {
		  method: 'post',
		  asynchronous: 'false',
		  onSuccess: function(transport) {
		     receiveStr=transport.responseText;
		     	if("" != receiveStr){
					var len=colType.options.length;
					for(var i=0;i<len;i++){		    
						colType.options[0]=null;			
					}
					var colArr = receiveStr.split("\|");
					for(var i=0; i < colArr.length; i++){
						var splitStr = colArr[i].split(",");
						colType.options[i] = new Option(splitStr[0],splitStr[0]+","+splitStr[1]+","+splitStr[2]);
					}
				}
				if(null == receiveStr || "" == receiveStr){
						colType.options.length=0;
				}
		     }
		});	
	}
	*/
	function getColType(tableId){
		var colType = document.ReportForm.colType;
		var colCondition = document.ReportForm.colCondition;
		var inputValue = document.ReportForm.inputValue;
		var receiveStr = "";
		var i = 0;
		$.get('ajaxquerycolumn.do?method=getColumQuery&tableid='+encodeURIComponent(tableId),"",function (data){
			receiveStr = data;
			if("" != receiveStr){
				var len=colType.options.length;
				for(var i=0;i<len;i++){		    
					colType.options[0]=null;			
				}
				var colArr = receiveStr.split("\|");
				for(var i=0; i < colArr.length; i++){
					var splitStr = colArr[i].split(",");
					colType.options[i] = new Option(splitStr[0],splitStr[0]+","+splitStr[1]+","+splitStr[2]);
				}
				setTextValue(colType.options[0]);
			}
			if(null == receiveStr || "" == receiveStr){
				colType.options.length=0;
			}
		});
	}
	
	
	function setTextValue(obj){
		var value = obj.value.split(",");
		document.getElementById("colname").value=value[0];
		document.getElementById("colwidth").value = value[0].length*30;
	}
	
	function ReSetScriptText(){

	}
	function reportError(request)
	{
	
	}
	
	function addCoorder(coorder){
		var f=document.ReportForm;
		for(k=0;k<f.elements.length;k++){
			if(f.elements[k].name==coorder){
				f.elements[k].length=0;
				f.elements[k].options[0]=new Option("-----------请选择-----------","");
				var j = 1;
				for(i=0;i<f.elements.length;i++){
				     	if(f.elements[i].name=="rowvalues"){
				     		if(f.elements[i].value!=""){
				     			var tempv=(f.elements[i].value).split(";");
				     			f.elements[k].options[j]=new Option(tempv[0],tempv[0]);
				     			j++;
				     		}
				     	}
				    }
			}
		
		}
	}
	function end(){
		var coordxx = document.ReportForm.coordxx.value;
		var coordyy = document.ReportForm.coordyy.value;
		if(coordxx!=""){
			for (i=0;i<document.ReportForm.coordx.options.length;i++){ 
				if(document.ReportForm.coordx.options[i].value==document.ReportForm.coordxx.value){
					document.ReportForm.coordx.options[i].selected=true;
					break;
				} 
			}
		}
		if(coordyy!=""){
			for (i=0;i<document.ReportForm.coordy.options.length;i++){ 
				if(document.ReportForm.coordy.options[i].value==document.ReportForm.coordyy.value){
					document.ReportForm.coordy.options[i].selected=true;
					break;
				} 
			}
		}
		if(coordxx != "" && coordyy != ""){
			var graphic = document.getElementById("graphic");
			graphic.checked = "checked" ;
			graphicShow(graphic);
		}
		
	}
	
	function graphicShow(obj){
		if(obj.checked){
			obj.value=1;
		}else{
			obj.value=0;
		}
		if(obj.value==1){
			document.getElementById("coordx").disabled="";
			document.getElementById("coordy").disabled="";
		}else{
			document.getElementById("coordx").disabled=true;
			document.getElementById("coordy").disabled=true;
		}
		
		
	}
	function cancels(){
		window.location = "reportFormQuery.do?method=initPage";
	}
	</script>
</head>

<body>
	<html:form action="newreportform.do" method="post">
		<!--  主table-->
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
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
								<td class='r2titler'><b>第三步</b> <font color='red'>(请确保表格内的字体变回黑色，否则您的修改将不被保存)</font>
								</td>
							</tr>
							<tr>
								<td
									style="padding-left: 120px;padding-top: 10px;padding-right: 10px;">
									<!-- 新增 --> <logic:notEqual name="useraction"
										value="editReport">

										<table border="0" cellpadding="0" cellspacing="0" width="800">
											<tr height="30">
												<input type="hidden" name="reportinfo"
													value="${reportinfo }" />
													<input type="hidden" id="modelname1" name="modelname1"
													value="${modelname1 }" />
												<input type="hidden" name="leveltwo" value="${leveltwo }">
												<input type="hidden" name="levelthree"
													value="${levelthree }" />
												<input type="hidden" name="coordxx" value="${coordx}" />
												<input type="hidden" name="coordyy" value="${coordy}" />
												<input type="hidden" name="arrays" id="arrays" />
												<td width="62" align="right">列名：</td>
												<td width="248"><input type="text" name="colname"
													value="" id="colname" size="25"></td>
												<td width="93" align="right">选择表：</td>
												<td width="289"><select name="choosetable"
													onChange="changeTemplate();" style="width: 200px;">
														<option value="">选择表</option>
														<logic:notEmpty name="tablelist">
															<logic:iterate id="list" name="tablelist">
																<option
																	value="${list.id };${list.tableName },${list.tableNameDescription }">
																	${list.tableNameDescription }</option>
															</logic:iterate>
														</logic:notEmpty>
												</select></td>
												<td width="106">&nbsp;</td>
											</tr>
											<tr height="30">
												<td align="right" style="height:15px;">列宽：</td>
												<td><input type="text" name="colwidth" value=""
													id="colwidth" maxlength="3" size="25"></td>
												<td align="right">字段：</td>
												<td><select name="colType" style="width: 200px;"
													onChange="setTextValue(this);">
												</select></td>
												<td><input type="button" value="增   加" class="btnstyle"
													onClick="additem(document.ReportForm)"></td>
											</tr>
											<tr height="30">
												<td align="right">对齐方式：</td>
												<td><select name="alignment" style="width: 185px;">
														<option value="0">------------居左------------</option>
														<option value="1" selected="selected">
															------------居中------------</option>
														<option value="2">------------居右------------</option>
												</select></td>
												<td colspan="3"><input type="radio" name="outtype"
													value="1" checked="checked"> 简单输出 <input
													type="radio" name="outtype" value="2"> 求和输出 <input
													type="radio" name="outtype" value="3"> 求记录数输出 <input
													type="checkbox" id="graphic" value="0"
													onClick="graphicShow(this);"> 图形显示</td>
											</tr>
											<tr>
												<td align="center" colspan="5" bordercolorlight="#666666"
													bordercolordark="#FFFFFF" bgcolor="#FFFFFF">
													<div class="sbox">
														<div class="cont">
															<div align="center"
																style="width: 100%; height: 285px; overflow-x: hidden; overflow-y: auto;">
																<table width="100%" id="contentTable" border="0"
																	cellpadding="0" cellspacing="1" class="tab2">
																	<tr>
																		<td width="6%" height="20" align='center'
																			background="../images/title_bg.gif" class="biaoti">
																			编号</td>
																		<td width="15%" height="20" align='center'
																			background="../images/title_bg.gif" class="biaoti">
																			列名</td>
																		<td width="10%" height="20" align='center'
																			background="../images/title_bg.gif" class="biaoti">
																			列宽</td>
																		<td width="10%" height="20" align='center'
																			background="../images/title_bg.gif" class="biaoti">
																			对齐方式</td>
																		<td width="15%" height="20" align='center'
																			background="../images/title_bg.gif" class="biaoti">
																			对应表</td>
																		<td width="15%" height="20" align='center'
																			background="../images/title_bg.gif" class="biaoti">
																			对应字段</td>
																		<td width="15%" height="20" align='center'
																			background="../images/title_bg.gif" class="biaoti">
																			输出方式</td>
																		<td width="15%" height="20" align='center'
																			background="../images/title_bg.gif" class="biaoti">
																			操作</td>
																	</tr>
																	<logic:notEmpty name="exportcolumns">
																		<%
																			int i = 1;
																		%>
																		<logic:iterate id="list" name="exportcolumns">
																			<tr>
																				<td align="center"><%=i%></td>
																				<td align="center" name="event_td">
																					${list.colName }&nbsp;</td>
																				<td align="center" name="event_td">
																					${list.colWidth }&nbsp;</td>
																				<td align="center" name="event_td">
																					${list.aligndescription }&nbsp;</td>
																				<td align="center">${list.tabledescription
																					}&nbsp;</td>
																				<td align="center">${list.fielddescription
																					}&nbsp;</td>
																				<td align="center" name="event_td">
																					${list.exportdescription }&nbsp;</td>
																				<td align="center"><input type="button"
																					value="删   除" class="btnstyle"
																					onclick='deleteitem(<%=i%>)' /> <input
																					type="button" value="上   移" class="btnstyle"
																					onclick='moveup(<%=i%>)' /> <input type="button"
																					value="下   移" class="btnstyle"
																					onclick='movedown(<%=i%>)' /> <input
																					type='checkbox' name='rowvalues'
																					value="${list.colName };${list.colWidth };${list.alignType };${list.correspondingTable };${list.tablename };${list.correspondingField };${list.fieldname };${list.exportType }"
																					style='display: none'></td>

																			</tr>
																			<%
																				i++;
																			%>
																		</logic:iterate>
																	</logic:notEmpty>
																</table>
															</div>
														</div>
													</div></td>
											</tr>
											<tr>
												<td align="center" colspan="5"><select name="coordx"
													style="width: 150px;" disabled="disabled" id="coordx"></select>
													&nbsp;作为横轴&nbsp;&nbsp;&nbsp;&nbsp; <select name="coordy"
													style="width: 150px;" disabled="disabled" id="coordy"></select>
													&nbsp;作为纵轴</td>
											</tr>
										</table>
									</logic:notEqual> <!-- 更新修改 --> <logic:equal name="useraction"
										value="editReport">

										<table border="0" cellpadding="0" cellspacing="1" width="800">
											<tr height="30">
												<input type="hidden" name="reportinfo"
													value="${reportinfo }" />
												<input type="hidden" name="leveltwo" value="${leveltwo }" />
												<input type="hidden" name="levelthree"
													value="${levelthree }" />
												<input type="hidden" name="reportFormId"
													value="${reportFormId }" />
												<input type="hidden" name="coordxx" value="${coordx}" />
												<input type="hidden" name="coordyy" value="${coordy}" />
												<input type="hidden" name="arrays" id="arrays" />
												<td align="right">列名：</td>
												<td><input type="text" name="colname" value=""
													id="colname"></td>
												<td align="right">选择表：</td>
												<td><select name="choosetable"
													onChange="changeTemplate();" style="width: 155px;">
														<option value="">选择表</option>
														<logic:notEmpty name="tablelist">
															<logic:iterate id="list" name="tablelist">
																<option
																	value="${list.id };${list.tableName },${list.tableNameDescription }">
																	${list.tableNameDescription }</option>
															</logic:iterate>
														</logic:notEmpty>
												</select></td>
												<td>&nbsp;</td>
											</tr>
											<tr height="30">
												<td align="right">列宽：</td>
												<td><input type="text" name="colwidth" value=""
													id="colwidth" maxlength="3"></td>
												<td align="right">字段：</td>
												<td><select name="colType" style="width: 155px;"
													onChange="setTextValue(this);">
												</select></td>
												<td><input type="button" value="增   加" class="btnstyle"
													onClick="additem(document.ReportForm)"></td>
											</tr>
											<tr height="30">
												<td align="right">对齐方式：</td>
												<td><select name="alignment" style="width: 155px;">
														<option value="0">------居左------</option>
														<option value="1">------居中------</option>
														<option value="2">------居右------</option>
												</select></td>
												<td colspan="3"><input type="radio" name="outtype"
													value="1" checked="checked"> 简单输出 <input
													type="radio" name="outtype" value="2"> 求和输出 <input
													type="radio" name="outtype" value="3"> 求记录数输出 <input
													type="checkbox" id="graphic" value="0"
													onClick="graphicShow(this);"> 图形显示</td>
											</tr>
											<tr>
											</tr>
											<tr>
												<td align="center" colspan="5" bordercolorlight="#666666"
													bordercolordark="#FFFFFF" bgcolor="#FFFFFF">
													<div class="sbox">
														<div class="cont">
															<div align="center"
																style="width: 100%; height: 285px; overflow-x: hidden; overflow-y: auto;">
																<table width="100%" id="contentTable" class="tab2"
																	border="0" cellpadding="0" cellspacing="1">
																	<tr>
																		<td width="6%" height="20" align='center'
																			background="../images/title_bg.gif" class="biaoti">
																			编号</td>
																		<td width="15%" height="20" align='center'
																			background="../images/title_bg.gif" class="biaoti">
																			列名</td>
																		<td width="10%" height="20" align='center'
																			background="../images/title_bg.gif" class="biaoti">
																			列宽</td>
																		<td width="10%" height="20" align='center'
																			background="../images/title_bg.gif" class="biaoti">
																			对齐方式</td>
																		<td width="15%" height="20" align='center'
																			background="../images/title_bg.gif" class="biaoti">
																			对应表</td>
																		<td width="15%" height="20" align='center'
																			background="../images/title_bg.gif" class="biaoti">
																			对应字段</td>
																		<td width="15%" height="20" align='center'
																			background="../images/title_bg.gif" class="biaoti">
																			输出方式</td>
																		<td width="15%" height="20" align='center'
																			background="../images/title_bg.gif" class="biaoti">
																			操作</td>
																	</tr>
																	<logic:notEmpty name="exportcolumns">
																		<%
																			int j = 1;
																		%>
																		<logic:iterate id="list" name="exportcolumns">
																			<tr>
																				<td align="center"><%=j%></td>
																				<td align="center" name="event_td">
																					${list.colName }&nbsp;</td>
																				<td align="center" name="event_td">
																					${list.colWidth }&nbsp;</td>
																				<td align="center" name="event_td">
																					${list.aligndescription }&nbsp;</td>
																				<td align="center">${list.tabledescription
																					}&nbsp;</td>
																				<td align="center">${list.fielddescription
																					}&nbsp;</td>
																				<td align="center" name="event_td">
																					${list.exportdescription }&nbsp;</td>
																				<td align="center"><input type="button"
																					value="删   除" class="btnstyle"
																					onclick='deleteitem(<%=j%>)' /> <input
																					type="button" style="margin-left: 3px"
																					" value="上   移" class="btnstyle"
																					onclick='moveup(<%=j%>)' /> <input type="button"
																					style="margin-left: 3px" " value="下   移"
																					class="btnstyle" onclick='movedown(<%=j%>)' /> <input
																					type='checkbox' name='rowvalues'
																					value='${list.colName };${list.colWidth };${list.alignType };${list.correspondingTable };${list.tablename };${list.correspondingField };${list.fieldname };${list.exportType }'
																					style='display: none'></td>
																				<!-- 
    								<input type="hidden" name="coordxx" value="${list.coordx }">
    								<input type="hidden" name="coordyy" value="${list.coordy }">
    								 -->
																			</tr>
																			<%
																				j++;
																			%>
																		</logic:iterate>
																	</logic:notEmpty>
																</table>
															</div>
														</div>
													</div></td>
											</tr>
											<tr>
												<td align="center" colspan="5"><select name="coordx"
													style="width: 150px;" disabled="disabled" id="coordx"></select>
													&nbsp;作为横轴&nbsp;&nbsp;&nbsp;&nbsp; <select name="coordy"
													style="width: 150px;" disabled="disabled" id="coordy"></select>
													&nbsp;作为纵轴</td>
											</tr>
											<tr>
												<td height="5px"></td>
											</tr>
										</table>
									</logic:equal></td>
							</tr>
							<tr>
								<td height="10"></td>
							</tr>
						</table>
					</div></td>
			</tr>
		</table>
		<table width="780px" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px;">
			<!-- 空行 -->
			<tr>
				<td class="td_detail_separator"></td>
			</tr>

			<tr>
				<td><input type="button" class="btnyh" id="btnSave" value="上一步"
					onclick="pres();" /> <input type="button" class="btnyh"
					id="btnSave" value="下一步" onclick="nexts();" /> <input
					type="button" class="btnyh" id="btnCancel" value="取  消"
					onclick="cancels();" /></td>
			</tr>
		</table>
	</html:form>
	<script language="JavaScript">
	    addCoorder("coordx");
		addCoorder("coordy");
		end();
    </script>
</body>
</html>