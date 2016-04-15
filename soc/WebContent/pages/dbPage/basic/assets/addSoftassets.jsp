﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>业务应用软件</title>
<link href="${ctx}/styles/dbStyles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/right.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/template.css" rel="stylesheet" type="text/css">
<LINK href="${ctx}/css/style/jquery-ui.custom.css" type=text/css rel=stylesheet>
<LINK href="${ctx}/css/style/jquery.popuplayer.css" type=text/css rel=stylesheet>

<SCRIPT src="${ctx}/scripts/util.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/popuplayer.js" type=text/javascript></SCRIPT>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.ui.dialog.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/check.js"></script>
<style type="text/css">
 	.tdleft{
 		width: 20%;
 		text-align: right;
 	}
 	td{
 		font-size: 12px;
 	}
 	
 
 </style>
<script type="text/javascript">

var msgSysNames="";
var msgSysNamesTemp="";
var msgSysNamesTempVV="";
var msgSysName = "";
var sysNameTemp = ";";
var resNameTemp = ";";
var action = "";
function checkAll() {
	var flag = true;
	var cked;
	if (action == "sysname") {
		cked = $("input[name='ids']"); 
	} else {
		cked= $("input[name='softs']");
	}
	if (cked.length) {
		for (var i = 0; i < cked.length; i++) {
			if (!$(cked[i]).attr("checked")) {
				flag = false;
			}
		}
	} else {
		flag = false;
	}
	return flag;
}
function addSysNameCheck(str) {
	var regex = new RegExp(";" + str + ";");
	if (!regex.test(sysNameTemp)) {
		sysNameTemp += str + ";";
	}
}
function addResNameCheck(str) {
	var regex = new RegExp(";" + str + ";");
	if (!regex.test(resNameTemp)) {
		resNameTemp += str + ";";
	}
}
function delSysNameCheck(str) {
	var regex = new RegExp(";" + str + ";");
	sysNameTemp = sysNameTemp.replace(regex, ";");
}
function delResNameCheck(str) {
	var regex = new RegExp(";" + str + ";");
	resNameTemp = resNameTemp.replace(regex, ";");
}

	$(function()
	{
	    $("form").keypress(function(e){  
	        if(e.keyCode == 13) {  
	            e.preventDefault();  
	        }  
	    });
	$("input[name='ids']").live("click", function() {
		if ($(this).attr("checked")) {
			addSysNameCheck($(this).val());
		} else {
			delSysNameCheck($(this).val());
		}
		if (checkAll()) {
			$("#chkAll").attr("checked", true);
		} else {
			$("#chkAll").attr("checked", false);
		}
	});
	$("input[name='softs']").live("click", function() {
		if ($(this).attr("checked")) {
			addResNameCheck($(this).val());
		} else {
			delResNameCheck($(this).val());
		}
		if (checkAll()) {
			$("#chkAll1").attr("checked", true);
		} else {
			$("#chkAll1").attr("checked", false);
		}
	});
	$("#chkAll").live("click",function(event) {
		var cked = $("input[name='ids']");
		if ($("#chkAll").attr("checked")) {
			cked.attr("checked", true);
			for (var i = 0; i < cked.length; i++) {
				addSysNameCheck($(cked[i]).val());
			}
		} else {
			cked.attr("checked", false);
			for (var i = 0; i < cked.length; i++) {
				delSysNameCheck($(cked[i]).val());
			}
		}
	});
	$("#chkAll1").live("click",function(event) {
		var cked = $("input[name='softs']");
		if ($("#chkAll1").attr("checked")) {
			cked.attr("checked", true);
			for (var i = 0; i < cked.length; i++) {
				addResNameCheck($(cked[i]).val());
			}
		} else {
			cked.attr("checked", false);
			for (var i = 0; i < cked.length; i++) {
				delResNameCheck($(cked[i]).val());
			}
		}
	});
	//所属信息系统
	$("#dialog-employee").dialog({
		autoOpen: false,
		minWidth: 600,
		minHeight: 460,
		buttons: {
			"确定[Enter]": function() {
				if (sysNameTemp != ";") {
					$("input[name='sysName']").val(sysNameTemp.substring(1, sysNameTemp.length - 1));
				} else {
					$("input[name='sysName']").val("");
				}
				action = "";
				sysNameTemp = ";";
				$(this).dialog("close");
			},
			
			"取消[Esc]": function() {
				action = "";
				resetCheckBox();
				sysNameTemp = ";";
				$(this).dialog("close"); 
			} 
		}
	});	
	
	
	//业务应用软件
	$("#dialog-softUse").dialog({
		autoOpen: false,
		minWidth: 600,
		minHeight: 460,
		buttons: {
			"确定[Enter]": function() {
				$("input[name='resName']").val(resNameTemp.substring(1, resNameTemp.length - 1));
				action = "";
				resNameTemp = ";";
				$(this).dialog("close"); 
			},
			
			"取消[Esc]": function() { 
				resetCheckBox();
				action = "";
				resNameTemp = ";";
				$(this).dialog("close");
			} 
		}
	});	
	});
 	
  	function employeeDlg() {
  	action = "sysname";
  	$("#dlg-keyword-sysName").val();
  	if(!$("#chkAll-sysName").hasClass('not-checked-sysName'))
  	{
  		$("#chkAll-sysName").addClass('not-checked-sysName');
  		$("#chkAll-sysName").attr('checked',true);
  	}
  	else
  	{
  		$("#chkAll-sysName").addClass('not-checked-sysName');
  		$("#chkAll-sysName").attr('checked',false);
  	}
  	
  	goSearch(0);
  	$("#dialog-employee").dialog("open");
}

function jsonForAjax(url){
		var htmlStr = "";	
		var htmlPage = "";
		var sysN = "";
		var sysId = "";
		var busDescription = "";
		var rowNum = 0;
 		if (sysNameTemp == ";" && $("#sysName").val() != "") {
 			sysNameTemp += $("#sysName").val() + ";";
 		}
		$.getJSON(url ,function(result){
			$("#table-sysName tr:not(:first)").remove();
			$.each(result.processLog, function(i,item) {
			 	rowNum = $("#table-sysName tr").length-1;
			 	if(item.id != null){
		 			sysN = item.sysName;
		 			sysId = item.sysId;
		 			busDescription = item.busDescription;
			 		htmlStr = "";
			 		htmlStr += "<tr>";
			 		htmlStr += "<td align=\"center\">";
			 		var regex = new RegExp(";" + sysN + ";");
			 		if (regex.test(sysNameTemp)) {
			 			addSysNameCheck(sysN);
				 		htmlStr += "<input name=\"ids\" type=\"checkbox\"  class=\"check-box\" checked=\"checked\"  value=\""+ sysN+"\"/>";
			 		} else {
			 			htmlStr += "<input name=\"ids\" type=\"checkbox\"  class=\"check-box\" value=\""+ sysN+"\"/>";
					 			
			 		}
			 			htmlStr += "</td>";
			 			htmlStr += "<td  align=\"center\">";
			 				htmlStr += (sysN == null ? "" : sysN);
			 			htmlStr += "</td>";
			 			 htmlStr += "<td  align=\"center\">";
			 				htmlStr += (sysId == null ? "" : sysId);			 			
			 			htmlStr += "</td>"; 
			 			 htmlStr += "<td  align=\"center\"><xmp>";
			 				htmlStr += (busDescription == null ? "" : busDescription);	
			 			htmlStr += "</xmp></td>"; 
			 		htmlStr += "</tr>";
			 	}
			 	$(htmlStr).insertAfter($("#table-sysName tr:eq("+rowNum+")"));
			 });				
			if(result.page.startIndex != null){		
				rowNum =  $("#table-sysName tr").size()-1;
			    htmlPage += "<tr>";
			    	htmlPage +="<td colspan=\"4\" width=\"100%\">";
					htmlPage +="<table width='100%' border='0' cellspacing='1' cellpadding='0' class='tab2' >";
					htmlPage +="<tr>";
						htmlPage +="<td>";
							htmlPage +="<table align='right' >";
								htmlPage +="<tr>";
								htmlPage +="<td>";
								htmlPage +="共"+ result.page.totalCount +"记录";
								htmlPage +="<input type='hidden' name='startIndex'  value='0'>";
								htmlPage +="<input type='hidden' name='lastIndex'  value="+ result.page.lastIndex + ">";
								htmlPage +="</td>";
								htmlPage +="<td>";
								if(result.page.startIndex != 0){
									htmlPage +="<a href=" +"javascript:goSearch('0')"+" >首页</a>";
								}else{
									htmlPage +="首页";
								}																							
								htmlPage +="</td>";
								htmlPage +="<td>";
								if(result.page.startIndex != 0){
									htmlPage +="<a href=" + "javascript:goSearch(" + result.page.previousIndex + ") "+ " >上一页</a>";
								}else{
									htmlPage +="上一页";
								}
								htmlPage +="</td>";
								htmlPage +="<td>";
								if(result.page.nextIndex > result.page.startIndex){
									htmlPage +="<a href=" + "javascript:goSearch(" + result.page.nextIndex + ")" + " >下一页</a>";
								}else{
									htmlPage +="下一页";
								}	
								htmlPage +="</td>";
								htmlPage +="<td>";
								if(result.page.lastIndex == result.page.startIndex){
									htmlPage +="末页";
								}else{
									htmlPage +="<a href=" + "javascript:goSearch(" + result.page.lastIndex + ")" + " >末页</a>";
								}									
								htmlPage +="</td>";
								htmlPage +="<td>";
								htmlPage +=" <input type='text' style='width: 30px' name='page' id='page'  size='3' onkeyup=\"this.value=this.value.replace(/[^\\d]/g,'')\"value=''>";
								htmlPage +="<input type='button' value='GO' class='btn1' onclick=" + "goToPage(" + result.page.lastIndex + ")" + ">";
								if(result.page.currentPage==0||result.page.pageCount){
									htmlPage +=" 当前第" + 1+ "/" + 1 + "页";
								}else{
								htmlPage +=" 当前第" + result.page.currentPage + "/" + result.page.pageCount + "页";
								}													
								htmlPage +="</td>";								htmlPage +="</tr>";
							htmlPage +="</table>";
						htmlPage +="</td>";
					htmlPage +="</tr>";
				htmlPage +="</table>";
				htmlPage +="</td>";
			htmlPage +="</tr>";
			$(htmlPage).insertAfter($("#table-sysName tr:eq("+rowNum+")"));	
			}
			if (checkAll()) {
				$("#chkAll").attr("checked", true);
			} else {
				$("#chkAll").attr("checked", false);
			}
		});
	}
	
  
function goToPage(lastnum) {
	var page = $("#page").val();
	var num = parseInt(page * 5) - parseInt(5);

	if (num > lastnum) {
		alert("错误页数");
		return false;
	}
	if (num < 0)
		num = 0;
	$("#startIndex").value = num;
		goSearch(num);
}	

function softToPage(lastnum) {
	resetCheckBox();
	var page = $("#softpage").val();
	var num = parseInt(page * 5) - parseInt(5);

	if (num > lastnum) {
		alert("错误页数");
		return false;
	}
	if (num < 0)
		num = 0;
	$("#softstartIndex").value = num;
		softSearch(num);
}
function goSearch(num) {
	resetCheckBox();
	var keyword = document.getElementById("goToSearch").value;
	if (keyword != '' || num != '') {
		jsonForAjax("${ctx}/softUse/queryAjaxSystem.action?keyword="+encodeURI(encodeURI(keyword,"UTF-8"))+"&pageSize=5&startIndex="+encodeURI(num,"UTF-8")+"&t="+new Date());
	} else {
	
		jsonForAjax("${ctx}/softUse/queryAjaxSystem.action?keyword="+encodeURI(encodeURI(keyword,"UTF-8"))+"&pageSize=5&t="+ new Date());
	}
}

function jsonForAjaxSoft(url){

		var htmlStr = "";	
		var htmlPage = "";
 		if (resNameTemp == ";" && $("#resName").val() != "") {
 			resNameTemp += $("#resName").val() + ";";
 		}	
		$.getJSON(url ,function(result){
			$("#table-softUse tr:not(:first)").remove();
			$.each(result.processLog, function(i,item) {
			 	var rowNum = $("#table-softUse tr").length-1;
			
			 	if(item.id != null){
			 		htmlStr = "";
			 		htmlStr += "<tr>";
			 			htmlStr += "<td align=\"center\">";
			 			
			 			var resN=item.resName;
			 			var sysN=item.sysName;
			 			var resMain=item.mianFun;
			 			var resD=item.impDegree;
				 		var regex = new RegExp(";" + resN + ";");
				 		if (regex.test(resNameTemp)) {
				 			addResNameCheck(resN);
				 			htmlStr += "<input name=\"softs\" type=\"checkbox\"  class=\"check-box\" checked=\"checked\"  value=\""+ resN+"\"/>";
		 				} else {
		 					htmlStr += "<input name=\"softs\" type=\"checkbox\"  class=\"check-box\" value=\""+ resN+"\"/>";
		 				}		
			 			htmlStr += "</td>";
			 			htmlStr += "<td  align=\"center\">";
			 				htmlStr += (resN == null ? "" : resN);
			 			htmlStr += "</td>";
			 			 htmlStr += "<td  align=\"center\">";
			 				htmlStr += (sysN == null ? "" : sysN);			 			
			 			htmlStr += "</td>"; 
			 			 htmlStr += "<td  align=\"center\">";
			 				htmlStr += (resMain == null ? "" : resMain);	
			 			htmlStr += "</td>"; 
			 			 htmlStr += "<td  align=\"center\">";
			 				htmlStr += (resD == null ? "" : resD);	
			 			htmlStr += "</td>"; 
			 		htmlStr += "</tr>";
			 	}
			 	$(htmlStr).insertAfter($("#table-softUse tr:eq("+rowNum+")"));
			 
			 });				
			if(result.page.startIndex != null){		
				rowNum =  $("#table-softUse tr").size()-1;
			    htmlPage += "<tr>";
			    	htmlPage +="<td colspan=\"5\" width=\"30%\">";
					htmlPage +="<table width='100%' border='0' cellspacing='1' cellpadding='0' class='tab2' >";
					htmlPage +="<tr>";
						htmlPage +="<td>";
							htmlPage +="<table align='right' >";
								htmlPage +="<tr>";
								htmlPage +="<td>";
								htmlPage +="共"+ result.page.totalCount +"记录";
								htmlPage +="<input type='hidden' name='startIndex'  value='0'>";
								htmlPage +="<input type='hidden' name='lastIndex'  value="+ result.page.lastIndex + ">";
								htmlPage +="</td>";
								htmlPage +="<td>";
								if(result.page.startIndex != 0){
									htmlPage +="<a href=" +"javascript:softSearch('0')"+" >首页</a>";
								}else{
									htmlPage +="首页";
								}																							
								htmlPage +="</td>";
								htmlPage +="<td>";
								if(result.page.startIndex != 0){
									htmlPage +="<a href=" + "javascript:softSearch(" + result.page.previousIndex + ") "+ " >上一页</a>";
								}else{
									htmlPage +="上一页";
								}
								htmlPage +="</td>";
								htmlPage +="<td>";
								if(result.page.nextIndex > result.page.startIndex){
									htmlPage +="<a href=" + "javascript:softSearch(" + result.page.nextIndex + ")" + " >下一页</a>";
								}else{
									htmlPage +="下一页";
								}	
								htmlPage +="</td>";
								htmlPage +="<td>";
								if(result.page.lastIndex == result.page.startIndex){
									htmlPage +="末页";
								}else{
									htmlPage +="<a href=" + "javascript:softSearch(" + result.page.lastIndex + ")" + " >末页</a>";
								}									
								htmlPage +="</td>";
								htmlPage +="<td>";
								htmlPage +=" <input type='text' style='width: 30px' name='page' id='softpage'  size='3' onkeyup=\"this.value=this.value.replace(/[^\\d]/g,'')\"value=''>";
								htmlPage +="<input type='button' value='GO' class='btn1' onclick=" + "softToPage(" + result.page.lastIndex + ")" + ">";
								if(result.page.currentPage==0||result.page.pageCount){
									htmlPage +=" 当前第" + 1+ "/" + 1 + "页";
								}else{
								htmlPage +=" 当前第" + result.page.currentPage + "/" + result.page.pageCount + "页";
								}													
								htmlPage +="</td>";
								htmlPage +="</tr>";
							htmlPage +="</table>";
						htmlPage +="</td>";
					htmlPage +="</tr>";
				htmlPage +="</table>";
				htmlPage +="</td>";
			htmlPage +="</tr>";
			$(htmlPage).insertAfter($("#table-softUse tr:eq("+rowNum+")"));	
			}
			if (checkAll()) {
				$("#chkAll1").attr("checked", true);
			} else {
				$("#chkAll1").attr("checked", false);
			}
		});
	}
function sysUseDlg() {
	action = "resname";
  	softSearch('0');
  	$("#dialog-softUse").dialog("open");
}
function checkName() {
	var res = $.trim($("#resName").val());
	var res1 = $.trim($("#resName1").val());
  	if(""==res){
  		alert("软件名称不能为空");
  		btnSub.disabled="";
  		$("#resName").focus();
  		return false;
  	} else if (res1 == res) {
  		return true;
  	}
  		$.post("${ctx}/softUse/queryName.action?softName=" + encodeURI(encodeURI(res,"UTF-8")), function(result) {
  			if (res1 == "" && result > 0) {
  		  		alert("软件名称不能为重名！");
  		  		$("#resName").val("");
  		  		$("#resName").focus();
  		  		return false;  				
  			} else if (res1 != "" && result > 0){
  		  		alert("软件名称不能为重名！");
  		  		$("#resName").val(res1);
  		  		$("#resName").focus();
  		  		return false;  	
  			} else {
  				return true;
  			}
  		});
}
function softSearch(num) {

	 var keyword = document.getElementById("softUseId").value;
	
	if (num != '' || keyword != '') {
	
		jsonForAjaxSoft("${ctx}/softUse/jsonAjaxSoft.action?keyword="+encodeURI(encodeURI(keyword,"UTF-8"))+"&pageSize=5&startIndex="+encodeURI(num,"UTF-8")+"&t="+new Date());
	} else {
		
		jsonForAjaxSoft("${ctx}/softUse/jsonAjaxSoft.action?keyword="+encodeURI(encodeURI(keyword,"UTF-8"))+"&pageSize=5&t="+ new Date());
	} 
}
function save(){
	if (!checkName) {
		return false;
	}
  	var btnSub=document.getElementById("btnSub");
    btnSub.disabled="disabled";
  	if(""==$.trim($("#resName").val())){
  		alert("软件名称不能为空");
  		btnSub.disabled="";
  		$("#resName").focus();
  		return false;
  	}else if(""==$.trim($("#sysName").val())){
  		alert("请选择所属系统名称");
  		btnSub.disabled="";
  		return false;
  	}else if(""==$.trim($("#impDegree").val())){
  		alert("系统的重要程度不能为空");
  		btnSub.disabled="";
  		$("#impDegree").focus();
  		return false;
  	}else{
  		$("#softUseAdd").submit();
  	}
 }
  function back()
{
	location.href="${ctx}/pages/dbPage/basic/assets/softassets.jsp";
}
    //验证软件名称，只能输入字母、汉字、字母汉字组合，禁止输入特殊字符 否则则清空
      function ChooseName()
      {
          var reg=/^([\d\u0391-\uFFE5A-Za-z])+$/;
          if(reg.test($.trim($("#resName").val())) || $.trim($("#resName").val())=="")
          {
            return true;
          }else
          {
             alert("输入字符不符合,只能输入数字、字母、汉字、字母汉字组合，禁止输入特殊字符");
             document.getElementById("resName").value="";
          }
      }
      //验证重要程度
     function ChooseImpDegree()
      {
          var regs=/^([\d\u0391-\uFFE5A-Za-z])+$/;
          if(regs.test($.trim($("#impDegree").val())) ||$.trim($("#impDegree").val())=="" )
          {
            return true;
          }else
          {
             alert("输入字符不符合,只能输入数字、字母、汉字、字母汉字组合，禁止输入特殊字符");
             document.getElementById("impDegree").value="";
          }
          
      }


/*    //验证主要功能
    
   String.prototype.getBytes = function() {       
    var cArr = this.match(/[^\x00-\xff]/ig);       
    return this.length + (cArr == null ? 0 : cArr.length);       
};    
function textLimitCheck(thisArea, maxLength){    
    var len = thisArea.value.getBytes(); 
    var main=/^([\u0391-\uFFE5A-Za-z0-9])+$/;
   if(len <= maxLength)
   {
     if(main.test($.trim($("#mianFun").val())) || $.trim($("#mianFun").val())=="")
     {
         return true;
      }else
      {
         alert("输入字符不符合,禁止输入特殊字符");
         document.getElementById("mianFun").value="";
      }
      
    }  
     else if (len > maxLength)    
    {    
        alert(maxLength + ' 个字限制. \r超出的将自动去除.');    
        var tempStr = "";    
        var areaStr = thisArea.value.split("");    
        var tempLen = 0;    
        for(var i=0,j=areaStr.length;i<j;i++){    
            tempLen += areaStr[i].getBytes();    
            if(tempLen<=maxLength){    
                tempStr += areaStr[i];    
            }                   
        }               
        thisArea.value = tempStr;    
        thisArea.focus();    
        }    
     
    }  
    
   //验证备注
   function RemarksCheck(thisArea, maxLength){    
    var len = thisArea.value.getBytes(); 
    var main=/^(?:[\u4e00-\u9fa50-9]*\w*\s*)+$/;
   if(len <= maxLength)
   {
     if(main.test($.trim($("#remarks").val())) || $.trim($("#remarks").val())=="")
     {
         return true;
      }else
      {
         alert("输入字符不符合,禁止输入特殊字符");
         document.getElementById("remarks").value="";
      }
      
    }  
     else if (len > maxLength)    
    {    
        alert(maxLength + ' 个字限制. \r超出的将自动去除.');    
        var tempStr = "";    
        var areaStr = thisArea.value.split("");    
        var tempLen = 0;    
        for(var i=0,j=areaStr.length;i<j;i++){    
            tempLen += areaStr[i].getBytes();    
            if(tempLen<=maxLength){    
                tempStr += areaStr[i];    
            }                   
        }               
        thisArea.value = tempStr;    
        thisArea.focus();    
        }    
     
    }  
     */

  </script>

</head>

<body style="margin-top: 2px">
		<input type="hidden" name="sysNameTemp" id="sysNameTemp" />
		<input type="hidden" name="sysNameTempOO" id="sysNameTempOO" />
								
	
	<s:form action="add" namespace="/softUse" method="post" theme="simple" id="softUseAdd">
		<input type="hidden" name="id" value="${resType_t.id}">
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px"  >
				<tr>
				<td width="100%" valign="top">
							<!-- information area -->
							<div class="framDiv">
							<table width="100%" border="0" cellspacing="1" cellpadding="0"   >
                                 <tr>
									<td colspan="4" class='r2titler'>业务应用软件编辑</td>
								</tr>
								<tr>
										<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td class="tdleft" >资产种类：&nbsp;</td>
									<td >业务应用软件
									</td>
									</tr>
									<tr>
										<td class="td_detail_separator"></td>
								</tr>
									<tr>
									<td class="tdleft">软件名称：<font color="#ff0000">*</font></td>
 
									<td style="text-align:left">
										<input type="text" id="resName" maxlength="50" name="resName" style="width:250px" value="${resType_t.resName}" onblur="ChooseName(this.value);checkName();"/>
										<input type="hidden" id="resName1" value="${resType_t.resName}"/>
									</td>
								</tr>
								<tr>
										<td class="td_detail_separator"></td>
								</tr>
								<tr>
								<td class="tdleft">所属系统名称：<font color="#ff0000">*</font></td>
								<td  colspan="3" style="text-align:left" style="font-size: 12px">
									<input type="text" name="sysName" id="sysName"  maxlength="500" value="${resType_t.sysName}" style="width:250px" readonly="readonly" onclick="$('#goToSearch').val('');employeeDlg()" />
								</td>
									
								</tr>
								<tr>
										<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td class="tdleft">重要程度：<font color="#ff0000">*</font></td>
									<td  style="text-align:left"><input type="text"  maxlength="50" id="impDegree" name="impDegree" style="width:250px" value="${resType_t.impDegree}" onblur="ChooseImpDegree(this.value)"/></td>
								</tr>
								<tr>
										<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td class="tdleft">软件功能描述：&nbsp;</td>
									<td colspan="3" style="text-align: left;font-size: 12px;">
									<textarea rows="3" cols="30" style="width: 40%"id="mianFun" maxlength="50" name="mianFun">${resType_t.mianFun}</textarea>
									</td>
								</tr>
								<tr>
										<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td class="tdleft">备注：&nbsp;</td>
									<td colspan="3" style="text-align: left;font-size: 12px">
										<textarea rows="3" cols="30" style="width:40%" id="remarks" maxlength="50" name="remarks">${resType_t.remarks}</textarea>
									&nbsp;</td>
								</tr>
								<tr>
										<td class="td_detail_separator"></td>
								</tr>
								
							</table>
							</div>
					</td>
			</tr>
				<tr>
					<td>

						<div >
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr height="30">
									<td  valign="middle" align="right"  >
										<input type="button" value="取消"   class="btnyh"  onclick="back()" />
										<input type="button"  value="保存" id="btnSub"  class="btnyh" onclick="save()" />
									</td>
								</tr>
							</table>

						</div> <!-- toolbar area --></td>
				</tr>
			</table>
		 
	</s:form>
	<!-- ui-dialog-employee -->
	<div id="dialog-employee" title="所属信息系统" style="font-size: 12px">
		<table width="97%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="font-size: 12px">快速搜索：<input name="dlg-keyword-sysName" id="goToSearch" onblur="yanzheng1(this)" onkeydown="if(event.keyCode==13)queryEmployee();" /> <img src="${ctx}/images/search.jpg" onclick="goSearch('0');" class="hand"/></td>
			</tr>
			<tr height="5"></tr>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2" id="table-sysName">
						<tr height="28">
							<td width="7%" align="center" class="biaoti"><input type="checkbox" id="chkAll" class="check-box not_checked"/></td>
							<td width="30%" align="center" class="biaoti">系统名称</td>
							<td width="20%" align="center" class="biaoti">系统编号</td>
							<td width="20%" align="center" class="biaoti">业务描述</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>


</body>
</html>
