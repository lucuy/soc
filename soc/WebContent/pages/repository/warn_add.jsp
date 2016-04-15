<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <title>新增预警</title>
    <link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
	<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
 
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">

	function checkNull(){
			if(document.getElementById("warnName").value=="" || document.getElementById("warnName").value==null ){
				alert("预警名称不能为空");
				return false;
				
			}
			if(document.getElementById("warnDescription").value=="" || document.getElementById("warnDescription").value==null ){
				alert("预警描述不能为空");
				return false;
				
			}
			if(document.getElementById("warnHarm").value=="" || document.getElementById("warnHarm").value==null ){
				alert("危害不能为空");
				return false;
				
			}
			var type = $("#warnType").attr("checked") ; 
			if(type){
			if(document.getElementById("leakName").value=="" || document.getElementById("leakName").value==null ){
				alert("新漏洞名称不能为空");
				return false;
				
			}
			}
			if(document.getElementById("solution").value=="" || document.getElementById("solution").value==null ){
				alert("解决方案不能为空");
				return false;
				
			}
			
			if(document.getElementById("source").value=="" || document.getElementById("source").value==null ){
				alert("来源不能为空");
				return false;
				
			}
			if(document.getElementById("publisher").value=="" || document.getElementById("publisher").value==null ){
				alert("发布人不能为空");
				return false;
				
			}
			return true;
		}

		//受影响操作系统表单验证
			 function CheckOSV(){
				var boolOSV = false;
				var OSV= document.getElementById("OSs").value;
				if(OSV=="" || OSV==null){
					alert("请输入受影响的操作系统!");
				}else{
					boolOSV = true;
				}
				return boolOSV;
			} 
			//漏洞编号表单验证
			function CheckOSH(){
				var boolOSH = false;
				var OSH= document.getElementById("Holes").value;
				if(OSH=="" || OSH==null){
				alert("请输入漏洞编号!");
			}else{
				boolOSH = true;
			}
			return boolOSH;
			}
		 //对下拉列表验证
		function sub(){
			var f1 = CheckOSV();
			if(f1)
				var f2 = CheckOSH();
			if(f1&&f2)	
				var f3 = checkNull();
			var form = document.getElementById("frmPost");
			if(	f3){
				form.submit();
			}
			
		} 
	
		//受影响的操作系统的Dialog
		$(document).ready(function() {
			jQuery("#frmPost").validationEngine();
		// 高级-Dialog			
			$('#dialog-addinfluenceSys').dialog({
				autoOpen : false,
				width : 450,
				height : 350,
				buttons : {
					"添加[Enter]" : function() {
						AddItem();
						$(this).dialog("close");
					},
					"取消[Esc]" : function() {
						$(this).dialog("close");
					}
				}
			});	
		});
		//通过参数删除复选框不需要的option
		function DeleteItem(ObjName)
		{	
			var selectObj = eval("document.frmPost."+ObjName);
			var minselected=0;
			if ( selectObj != null )
			{
				for (i=selectObj.length-1; i>=0; i--)
				{
					if (selectObj.options[i].selected)
					{// document.alert(i);
						if (minselected==0 || i<minselected)
						minselected=i;
						selectObj.options[i] = null;
					}
				}
				i=selectObj.length;
		
				if (i>0)
				{
					if (minselected>=i)
					minselected=i-1;
					selectObj.options[minselected].selected=true;
				}
			}
		}
		//点击蠕虫不现实漏洞的相关信息
		function displayNewHoleInfo()
		{
			//if(document.frmPost.warn.warnType[0].checked == true)
			if(document.getElementById("warnType").checked == true)
			{
				document.getElementById("idNewHoleName").style.display="";
				document.getElementById("idNewHoleType").style.display="";
				document.getElementById("idNewHoleLevel").style.display="";
				document.getElementById("fc").style.display="none";
			
			}
			else
			{
				document.getElementById("idNewHoleName").style.display="none";
				document.getElementById("idNewHoleType").style.display="none";
				document.getElementById("idNewHoleLevel").style.display="none";
				document.getElementById("fc").style.display="";
				
			}
		}
		//添加漏洞编号
		function AddItemHoles()
		{
			//if(document.frmPost.warnType[0].checked == true && document.frmPost.Holes.length > 0)
			if(document.getElementById("warnType").checked == true && document.frmPost.Holes.length > 0)
			{
				alert("新漏洞只能输入一个漏洞编号！");
				document.frmPost.ForecastHoleNumber.focus();
				return false;
			}
			var oOption = document.createElement("OPTION");
			if (document.frmPost.ForecastHoleNumber.value=="")
			{
				alert("不能添加空漏洞号");
				document.frmPost.ForecastHoleNumber.focus();
				return false;
			}
			else
			{
				var holeNumberObj = document.frmPost.ForecastHoleNumber;
				
				var str = document.frmPost.ForecastHoleNumber.value; 
				var blankNum = 0;
				for (j=0; j<str.length ;j++)
				{
					if (str.charAt(j) == " ")
					{
						blankNum++;
					}
				}
				if(blankNum == str.length)
				{
					alert("漏洞编号不能全为空格!");
					return false;
				}
				for(i=0;i<3;i++)
				{
					if(document.frmPost.ForecastHoleNumberType[i].checked)
					{
						oOption.value=document.frmPost.ForecastHoleNumberType[i].value+"： "+document.frmPost.ForecastHoleNumber.value;
						oOption.text=document.frmPost.ForecastHoleNumberType[i].value+"： "+document.frmPost.ForecastHoleNumber.value;
						oOption.selected=true;
					}
				}
				
				i = document.frmPost.Holes.length;
				
				if (i>0)
				{
					j=0;
					for (j=0;j<=i-1;j++)
					{
						if (document.frmPost.Holes.options[j].text == oOption.text)
						{
							alert("不能重复输入漏洞号");
							document.frmPost.ForecastHoleNumber.focus();
							return false;
							break;
						}
					}
				}
				
				 if(window.ActiveXObject){
				 		
                      document.frmPost.Holes.add(oOption);
					  
                }else{
                     document.frmPost.Holes.appendChild(oOption); 
                }
				
				
				document.frmPost.ForecastHoleNumber.value="";
				
			}
		}
		//添加受影响的服务器端口
		function AddItemPorts()
		{
			var oOption = document.createElement("OPTION");
			if (document.frmPost.ForecastPort.value=="")
			{
				alert("不能添加空端口号");
				document.frmPost.ForecastPort.focus();
				return false;
			}
			else if(isNaN(document.frmPost.ForecastPort.value)||document.frmPost.ForecastPort.value<=0||document.frmPost.ForecastPort.value>65535)
			{
					alert("端口号必须在1-65535之间");
					return false;
			}
			else
			{
				for(i=0;i<2;i++)
				{
					if(document.frmPost.ForecastPortType[i].checked)
					{
						oOption.value=document.frmPost.ForecastPortType[i].value+" "+document.frmPost.ForecastPort.value;
						oOption.text=document.frmPost.ForecastPortType[i].value+" "+document.frmPost.ForecastPort.value;
						oOption.selected=true;
					}
				}
				i = document.frmPost.Ports.length;
		
				if (i>0)
				{
					j=0;
					for (j=0;j<=i-1;j++)
					{
						if (document.frmPost.Ports.options[j].text == oOption.text)
						{
							alert("不能重复输入端口号");
							document.frmPost.ForecastPort.focus();
							return false;
							break;
						}
					}
				}
				
				 if(window.ActiveXObject){
                      document.frmPost.Ports.add(oOption);
                      
                }else{
                     document.frmPost.Ports.appendChild(oOption); 
                }
				
				
				document.frmPost.ForecastPort.value="";
			}
		}
		//重置时，需要把复选框的内容设置为空
		function reseting(){
			DeleteItem('OSs');
			DeleteItem('Holes');
			DeleteItem('Ports');
		}
		//添加受影响的操作系统
		function AddItemOSs()
		{
			 $('#dialog-addinfluenceSys').dialog('open');
		}
		//添加受影响的操作系统
		function AddItem()
		{	 
			var oOption = document.createElement("OPTION");
			if (document.getElementById("PK_TypeId").value==0)
			{
				alert("不能添加未知操作系统");
				document.getElementById("PK_TypeId").focus();
				return false;
			}
			else
			{
				for(var n=0;n<document.getElementById("BrandId").options.length;n++)
				{
					if (document.getElementById("BrandId").options[n].selected == true)
					{
					oOption.text=document.getElementById("BrandId").options[n].text+" ";
					}
				}
				for(var n=0;n<document.getElementById("PK_TypeId").options.length;n++)
				{
					if (document.getElementById("PK_TypeId").options[n].selected == true)
					{
					oOption.text+=document.getElementById("PK_TypeId").options[n].text+" ";
					}
				}
				for(var n=0;n<document.getElementById("EditionId").options.length;n++)
				{
					if (document.getElementById("EditionId").options[n].selected == true)
					{
					oOption.text+=document.getElementById("EditionId").options[n].text+" ";
					}
				}
				for(var n=0;n<document.getElementById("NumId").options.length;n++)
				{
					if (document.getElementById("NumId").options[n].selected == true)
					{
					oOption.text+=document.getElementById("NumId").options[n].text+" ";
					}
				}
				
				for(var n=0;n<document.getElementById("PK_CpuId").options.length;n++)
				{
					if (document.getElementById("PK_CpuId").options[n].selected == true)
					{
					oOption.text+=document.getElementById("PK_CpuId").options[n].text;
					}
				}
				oOption.value=oOption.text;
				oOption.selected=true;
				
				i = document.getElementById("OSs").length;
		
				if (i>0)
				{
					j=0;
					for (j=0;j<=i-1;j++)
					{
						if (document.getElementById("OSs").options[j].text == oOption.text)
						{
							alert("不能重复输入操作系统");
							document.frmPost.PK_TypeId.focus();
							return false;
							break;
						}
					}
				}
				 if(window.ActiveXObject){
                      document.getElementById("OSs").add(oOption);     
                }else{
                     document.frmPost.OSs.appendChild(oOption);   
                }
				//document.frmPost.OSs.add(oOption);
				document.getElementById("PK_TypeId").value="";
			}
		}
		
		//受影响的操作系统的选项
		function Cha_OsSelect()
		{
		
				var a0 = new Array()
				var b0 = new Array()
				
				var c0 = new Array()
				var d0 = new Array()
				
				var e0 = new Array()
				var f0 = new Array()
				
				var a1 = new Array()
				var b1 = new Array()
				
					a1[0] = '全部'
					b1[0] = '0'
				
				var c1 = new Array()
				var d1 = new Array()
				
					c1[0] = '全部'
					d1[0] = '0'
				
					c1[1] = '4.1'
					d1[1] = '1'
				
					c1[2] = 'SE'
					d1[2] = '2'
				
					c1[3] = 'SP1'
					d1[3] = '3'
				
				var e1 = new Array()
				var f1 = new Array()
				
					e1[0] = 'Microsoft'
					f1[0] = '1'
				
				var a2 = new Array()
				var b2 = new Array()
				
					a2[0] = '全部'
					b2[0] = '0'
				
				var c2 = new Array()
				var d2 = new Array()
				
					c2[0] = '全部'
					d2[0] = '0'
				
					c2[1] = '4.9'
					d2[1] = '1'
				
				var e2 = new Array()
				var f2 = new Array()
				
					e2[0] = 'Microsoft'
					f2[0] = '1'
				
				var a3 = new Array()
				var b3 = new Array()
				
					a3[0] = '全部'
					b3[0] = '0'
				
				var c3 = new Array()
				var d3 = new Array()
				
					c3[0] = '全部'
					d3[0] = '0'
				
					c3[1] = '3.1'
					d3[1] = '1'
				
					c3[2] = '3.5'
					d3[2] = '2'
				
					c3[3] = '3.51'
					d3[3] = '3'
				
					c3[4] = '4.0'
					d3[4] = '4'
				
				var e3 = new Array()
				var f3 = new Array()
				
					e3[0] = 'Microsoft'
					f3[0] = '1'
				
				var a4 = new Array()
				var b4 = new Array()
				
					a4[0] = '全部'
					b4[0] = '0'
				
					a4[1] = 'Professional '
					b4[1] = '1'
				
					a4[2] = 'Server'
					b4[2] = '2'
				
					a4[3] = 'Advanced Server'
					b4[3] = '3'
				
					a4[4] = 'Datacenter Server'
					b4[4] = '4'
				
				var c4 = new Array()
				var d4 = new Array()
				
					c4[0] = '全部'
					d4[0] = '0'
				
					c4[1] = '5.0'
					d4[1] = '1'
				
					c4[2] = 'SP1'
					d4[2] = '2'
				
					c4[3] = 'SP2'
					d4[3] = '3'
				
					c4[4] = 'SP3'
					d4[4] = '4'
				
					c4[5] = 'SP4'
					d4[5] = '5'
				
				var e4 = new Array()
				var f4 = new Array()
				
					e4[0] = 'Microsoft'
					f4[0] = '1'
				
				var a5 = new Array()
				var b5 = new Array()
				
					a5[0] = '全部'
					b5[0] = '0'
				
					a5[1] = 'Professional'
					b5[1] = '1'
				
					a5[2] = 'home'
					b5[2] = '2'
				
					a5[3] = 'Media Center'
					b5[3] = '3'
				
					a5[4] = 'Tablet PC'
					b5[4] = '4'
				
				var c5 = new Array()
				var d5 = new Array()
				
					c5[0] = '全部'
					d5[0] = '0'
				
					c5[1] = '5.1'
					d5[1] = '1'
				
					c5[2] = 'SP1'
					d5[2] = '2'
				
					c5[3] = 'SP2'
					d5[3] = '3'
				
					c5[4] = 'SP3'
					d5[4] = '4'
				
				var e5 = new Array()
				var f5 = new Array()
				
					e5[0] = 'Microsoft'
					f5[0] = '1'
				
				var a6 = new Array()
				var b6 = new Array()
				
					a6[0] = '全部'
					b6[0] = '0'
				
					a6[1] = 'Standard'
					b6[1] = '1'
				
					a6[2] = 'Enterprise'
					b6[2] = '2'
				
					a6[3] = 'Date Center'
					b6[3] = '3'
				
					a6[4] = 'WEB'
					b6[4] = '4'
				
				var c6 = new Array()
				var d6 = new Array()
				
					c6[0] = '全部'
					d6[0] = '0'
				
					c6[1] = '5.2'
					d6[1] = '1'
				
					c6[2] = 'SP1'
					d6[2] = '2'
				
					c6[3] = 'SP2'
					d6[3] = '3'
				
				var e6 = new Array()
				var f6 = new Array()
				
					e6[0] = 'Microsoft'
					f6[0] = '1'
				
				var a7 = new Array()
				var b7 = new Array()
				
					a7[0] = '全部'
					b7[0] = '0'
				
				var c7 = new Array()
				var d7 = new Array()
				
					c7[0] = '全部'
					d7[0] = '0'
				
					c7[1] = 'GOLD'
					d7[1] = '1'
				
					c7[2] = 'SP1'
					d7[2] = '2'
				
					c7[3] = 'SP2'
					d7[3] = '3'
				
				var e7 = new Array()
				var f7 = new Array()
				
					e7[0] = 'Microsoft'
					f7[0] = '1'
				
				var a8 = new Array()
				var b8 = new Array()
				
					a8[0] = '全部'
					b8[0] = '0'
				
				var c8 = new Array()
				var d8 = new Array()
				
					c8[0] = '全部'
					d8[0] = '0'
				
				var e8 = new Array()
				var f8 = new Array()
				
					e8[0] = 'Microsoft'
					f8[0] = '1'
				
				var a9 = new Array()
				var b9 = new Array()
				
					a9[0] = '全部'
					b9[0] = '0'
				
					a9[1] = 'Itanium-Based Systems'
					b9[1] = '1'
				
					a9[2] = 'x64'
					b9[2] = '2'
				
				var c9 = new Array()
				var d9 = new Array()
				
					c9[0] = '全部'
					d9[0] = '0'
				
					c9[1] = 'Gold'
					d9[1] = '1'
				
					c9[2] = 'SP2'
					d9[2] = '2'
				
				var e9 = new Array()
				var f9 = new Array()
				
					e9[0] = 'Microsoft'
					f9[0] = '1'
				
				var a21 = new Array()
				var b21 = new Array()
				
					a21[0] = '全部'
					b21[0] = '0'
				
					a21[1] = 'Stable'
					b21[1] = '1'
				
					a21[2] = 'Current'
					b21[2] = '2'
				
				var c21 = new Array()
				var d21 = new Array()
				
					c21[0] = '全部'
					d21[0] = '0'
				
					c21[1] = '3.x'
					d21[1] = '1'
				
					c21[2] = '4.x'
					d21[2] = '2'
				
					c21[3] = '5.0'
					d21[3] = '3'
				
					c21[4] = '5.1'
					d21[4] = '4'
				
					c21[5] = '5.2'
					d21[5] = '5'
				
					c21[6] = '5.3'
					d21[6] = '6'
				
					c21[7] = '5.4'
					d21[7] = '7'
				
					c21[8] = '5.5'
					d21[8] = '8'
				
					c21[9] = '6.0'
					d21[9] = '9'
				
					c21[10] = '6.1'
					d21[10] = '10'
				
				var e21 = new Array()
				var f21 = new Array()
				
					e21[0] = 'BSD'
					f21[0] = '1'
				
				var a31 = new Array()
				var b31 = new Array()
				
					a31[0] = '全部'
					b31[0] = '0'
				
				var c31 = new Array()
				var d31 = new Array()
				
					c31[0] = '全部'
					d31[0] = '0'
				
					c31[1] = '1.0'
					d31[1] = '1'
				
					c31[2] = '1.1'
					d31[2] = '2'
				
					c31[3] = '1.2'
					d31[3] = '3'
				
					c31[4] = '1.3'
					d31[4] = '4'
				
					c31[5] = '1.4'
					d31[5] = '5'
				
					c31[6] = '1.5'
					d31[6] = '6'
				
					c31[7] = '1.6'
					d31[7] = '7'
				
					c31[8] = '2.0'
					d31[8] = '8'
				
				var e31 = new Array()
				var f31 = new Array()
				
					e31[0] = 'BSD'
					f31[0] = '1'
				
				var a38 = new Array()
				var b38 = new Array()
				
					a38[0] = '全部'
					b38[0] = '0'
				
				var c38 = new Array()
				var d38 = new Array()
				
					c38[0] = '全部'
					d38[0] = '0'
				
					c38[1] = '2.6'
					d38[1] = '1'
				
					c38[2] = '2.7'
					d38[2] = '2'
				
					c38[3] = '2.8'
					d38[3] = '3'
				
					c38[4] = '2.9'
					d38[4] = '4'
				
					c38[5] = '3.0'
					d38[5] = '5'
				
					c38[6] = '3.1'
					d38[6] = '6'
				
					c38[7] = '3.2'
					d38[7] = '7'
				
					c38[8] = '3.3'
					d38[8] = '8'
				
					c38[9] = '3.4'
					d38[9] = '9'
				
					c38[10] = '3.5'
					d38[10] = '10'
				
					c38[11] = '3.6'
					d38[11] = '11'
				
					c38[12] = '3.7'
					d38[12] = '12'
				
					c38[13] = '3.8'
					d38[13] = '13'
				
					c38[14] = '3.9'
					d38[14] = '14'
				
				var e38 = new Array()
				var f38 = new Array()
				
					e38[0] = 'BSD'
					f38[0] = '1'
				
				var a44 = new Array()
				var b44 = new Array()
				
					a44[0] = '全部'
					b44[0] = '0'
				
					a44[1] = 'PowerPC'
					b44[1] = '1'
				
					a44[2] = 'X86'
					b44[2] = '2'
				
				var c44 = new Array()
				var d44 = new Array()
				
					c44[0] = '全部'
					d44[0] = '0'
				
					c44[1] = '10.0'
					d44[1] = '1'
				
					c44[2] = '10.1'
					d44[2] = '2'
				
					c44[3] = '10.2'
					d44[3] = '3'
				
					c44[4] = '10.3'
					d44[4] = '4'
				
					c44[5] = '10.4'
					d44[5] = '5'
				
					c44[6] = '10.5'
					d44[6] = '6'
				
				var e44 = new Array()
				var f44 = new Array()
				
					e44[0] = 'Apple'
					f44[0] = '1'
				
				var a52 = new Array()
				var b52 = new Array()
				
					a52[0] = '全部'
					b52[0] = '0'
				
					a52[1] = 'SPARC'
					b52[1] = '1'
				
					a52[2] = 'X86'
					b52[2] = '2'
				
				var c52 = new Array()
				var d52 = new Array()
				
					c52[0] = '全部'
					d52[0] = '0'
				
					c52[1] = '2.0'
					d52[1] = '1'
				
					c52[2] = '2.1'
					d52[2] = '2'
				
					c52[3] = '2.2'
					d52[3] = '3'
				
					c52[4] = '2.3'
					d52[4] = '4'
				
					c52[5] = '2.4'
					d52[5] = '5'
				
					c52[6] = '2.5'
					d52[6] = '6'
				
					c52[7] = '2.6'
					d52[7] = '7'
				
					c52[8] = '7'
					d52[8] = '8'
				
					c52[9] = '8'
					d52[9] = '9'
				
					c52[10] = '9'
					d52[10] = '10'
				
					c52[11] = '10'
					d52[11] = '11'
				
				var e52 = new Array()
				var f52 = new Array()
				
					e52[0] = 'Sun'
					f52[0] = '1'
				
				var a80 = new Array()
				var b80 = new Array()
				
					a80[0] = '全部'
					b80[0] = '0'
				
					a80[1] = '个人桌面版'
					b80[1] = '1'
				
					a80[2] = '企业服务器版'
					b80[2] = '2'
				
				var c80 = new Array()
				var d80 = new Array()
				
					c80[0] = '全部'
					d80[0] = '0'
				
					c80[1] = '1.0'
					d80[1] = '1'
				
					c80[2] = '1.1'
					d80[2] = '2'
				
					c80[3] = '1.2'
					d80[3] = '3'
				
					c80[4] = '1.3'
					d80[4] = '4'
				
					c80[5] = '2.0'
					d80[5] = '5'
				
					c80[6] = '2.1'
					d80[6] = '6'
				
					c80[7] = '2.2'
					d80[7] = '7'
				
					c80[8] = '2.3'
					d80[8] = '8'
				
					c80[9] = '2.4'
					d80[9] = '9'
				
					c80[10] = '2.5'
					d80[10] = '10'
				
					c80[11] = '2.6'
					d80[11] = '11'
				
				var e80 = new Array()
				var f80 = new Array()
				
					e80[0] = '未知'
					f80[0] = '0'
				
					e80[1] = 'RedHat Linux'
					f80[1] = '1'
				
					e80[2] = 'Fedora Core'
					f80[2] = '2'
				
					e80[3] = 'Debian Linux'
					f80[3] = '3'
				
					e80[4] = 'SUSE Linux'
					f80[4] = '4'
				
					e80[5] = 'Turbo Linux'
					f80[5] = '5'
				
					e80[6] = 'Mandriva Linux'
					f80[6] = '6'
				
					e80[7] = 'Conectiva Linux'
					f80[7] = '7'
				
					e80[8] = 'Ubuntu Linux'
					f80[8] = '8'
				
					e80[9] = 'Slackware Linux'
					f80[9] = '9'
				
					e80[10] = 'Gentoo Linux'
					f80[10] = '10'
				
					e80[11] = '红旗 Linux'
					f80[11] = '11'
				
				var a102 = new Array()
				var b102 = new Array()
				
					a102[0] = '全部'
					b102[0] = '0'
				
					a102[1] = 'PowerPC'
					b102[1] = '1'
				
					a102[2] = 'Power'
					b102[2] = '2'
				
				var c102 = new Array()
				var d102 = new Array()
				
					c102[0] = '全部'
					d102[0] = '0'
				
					c102[1] = '2'
					d102[1] = '2'
				
					c102[2] = '3.1'
					d102[2] = '3'
				
					c102[3] = '4'
					d102[3] = '4'
				
					c102[4] = '4.3'
					d102[4] = '5'
				
					c102[5] = '5.1'
					d102[5] = '6'
				
					c102[6] = '5.2'
					d102[6] = '7'
				
					c102[7] = '5.3'
					d102[7] = '8'
				
				var e102 = new Array()
				var f102 = new Array()
				
					e102[0] = 'IBM'
					f102[0] = '1'
				
				var a103 = new Array()
				var b103 = new Array()
				
					a103[0] = '全部'
					b103[0] = '0'
				
				var c103 = new Array()
				var d103 = new Array()
				
					c103[0] = '全部'
					d103[0] = '0'
				
				var e103 = new Array()
				var f103 = new Array()
				
					e103[0] = 'IBM'
					f103[0] = '1'
				
				var a104 = new Array()
				var b104 = new Array()
				
					a104[0] = '全部'
					b104[0] = '0'
				
				var c104 = new Array()
				var d104 = new Array()
				
					c104[0] = '全部'
					d104[0] = '0'
				
				var e104 = new Array()
				var f104 = new Array()
				
					e104[0] = 'IBM'
					f104[0] = '1'
				
				var a105 = new Array()
				var b105 = new Array()
				
					a105[0] = '全部'
					b105[0] = '0'
				
				var c105 = new Array()
				var d105 = new Array()
				
					c105[0] = '全部'
					d105[0] = '0'
				
				var e105 = new Array()
				var f105 = new Array()
				
					e105[0] = 'IBM'
					f105[0] = '1'
				
				var a106 = new Array()
				var b106 = new Array()
				
					a106[0] = '全部'
					b106[0] = '0'
				
				var c106 = new Array()
				var d106 = new Array()
				
					c106[0] = '全部'
					d106[0] = '0'
				
					c106[1] = '3.2'
					d106[1] = '1'
				
					c106[2] = '4.0'
					d106[2] = '2'
				
					c106[3] = '5.1'
					d106[3] = '3'
				
					c106[4] = '6.5'
					d106[4] = '4'
				
					c106[5] = '6.6'
					d106[5] = '5'
				
				var e106 = new Array()
				var f106 = new Array()
				
					e106[0] = '未知'
					f106[0] = '0'
				
					e106[1] = 'Compaq'
					f106[1] = '1'
				
					e106[2] = 'HP'
					f106[2] = '2'
				
				var a113 = new Array()
				var b113 = new Array()
				
					a113[0] = '全部'
					b113[0] = '0'
				
				var c113 = new Array()
				var d113 = new Array()
				
					c113[0] = '全部'
					d113[0] = '0'
				
					c113[1] = '9.x'
					d113[1] = '1'
				
					c113[2] = '10.x'
					d113[2] = '2'
				
					c113[3] = '11.11'
					d113[3] = '3'
				
					c113[4] = '11.23'
					d113[4] = '4'
				
				var e113 = new Array()
				var f113 = new Array()
				
					e113[0] = 'HP'
					f113[0] = '1'
				
				var a114 = new Array()
				var b114 = new Array()
				
					a114[0] = '全部'
					b114[0] = '0'
				
				var c114 = new Array()
				var d114 = new Array()
				
					c114[0] = '全部'
					d114[0] = '0'
				
				var e114 = new Array()
				var f114 = new Array()
				
					e114[0] = 'HP'
					f114[0] = '1'
				
				var a117 = new Array()
				var b117 = new Array()
				
					a117[0] = '全部'
					b117[0] = '0'
				
				var c117 = new Array()
				var d117 = new Array()
				
					c117[0] = '全部'
					d117[0] = '0'
				
				var e117 = new Array()
				var f117 = new Array()
				
					e117[0] = 'GNU'
					f117[0] = '1'
				
				var a120 = new Array()
				var b120 = new Array()
				
					a120[0] = '全部'
					b120[0] = '0'
				
				var c120 = new Array()
				var d120 = new Array()
				
					c120[0] = '全部'
					d120[0] = '0'
				
					c120[1] = '5.3'
					d120[1] = '1'
				
					c120[2] = '6.3'
					d120[2] = '2'
				
					c120[3] = '6.4'
					d120[3] = '3'
				
					c120[4] = '6.5'
					d120[4] = '4'
				
				var e120 = new Array()
				var f120 = new Array()
				
					e120[0] = 'SGI'
					f120[0] = '1'
				
				var a123 = new Array()
				var b123 = new Array()
				
					a123[0] = '全部'
					b123[0] = '0'
				
				var c123 = new Array()
				var d123 = new Array()
				
					c123[0] = '全部'
					d123[0] = '0'
				
					c123[1] = '3.11'
					d123[1] = '1'
				
					c123[2] = '3.12'
					d123[2] = '2'
				
					c123[3] = '4.10'
					d123[3] = '3'
				
					c123[4] = '4.11'
					d123[4] = '4'
				
					c123[5] = '5.0'
					d123[5] = '5'
				
				var e123 = new Array()
				var f123 = new Array()
				
					e123[0] = 'Novell'
					f123[0] = '1'
				
				var a126 = new Array()
				var b126 = new Array()
				
					a126[0] = '全部'
					b126[0] = '0'
				
				var c126 = new Array()
				var d126 = new Array()
				
					c126[0] = '全部'
					d126[0] = '0'
				
					c126[1] = '7.1.1'
					d126[1] = '1'
				
					c126[2] = '7.1.2'
					d126[2] = '2'
				
					c126[3] = '7.1.3'
					d126[3] = '3'
				
					c126[4] = '7.1.4'
					d126[4] = '4'
				
				var e126 = new Array()
				var f126 = new Array()
				
					e126[0] = 'SCO'
					f126[0] = '1'
				
				var a127 = new Array()
				var b127 = new Array()
				
					a127[0] = '全部'
					b127[0] = '0'
				
				var c127 = new Array()
				var d127 = new Array()
				
					c127[0] = '全部'
					d127[0] = '0'
				
					c127[1] = '5.0'
					d127[1] = '1'
				
					c127[2] = '5.0.1'
					d127[2] = '2'
				
					c127[3] = '5.0.2'
					d127[3] = '3'
				
					c127[4] = '5.0.3'
					d127[4] = '4'
				
					c127[5] = '5.0.4'
					d127[5] = '5'
				
					c127[6] = '5.0.5'
					d127[6] = '6'
				
					c127[7] = '5.0.6'
					d127[7] = '7'
				
					c127[8] = '5.0.7'
					d127[8] = '8'
				
					c127[9] = '6'
					d127[9] = '9'
				
				var e127 = new Array()
				var f127 = new Array()
				
					e127[0] = 'SCO'
					f127[0] = '1'
				
			var temp1 = document.all("PK_TypeId")
			var temp2 = document.all("EditionId")
			var temp3 = document.all("NumId")
			var temp4 = document.all("BrandId")
			if(temp1.options[temp1.selectedIndex].value!=0)
			{
				var temp = eval("a"+temp1.options[temp1.selectedIndex].value)
				var tem = eval("b"+temp1.options[temp1.selectedIndex].value)
				var tempc = eval("c"+temp1.options[temp1.selectedIndex].value)
				var temd = eval("d"+temp1.options[temp1.selectedIndex].value)
				var tempe = eval("e"+temp1.options[temp1.selectedIndex].value)
				var temf = eval("f"+temp1.options[temp1.selectedIndex].value)
				if(temp!=null)
				{
					temp2.options.length = 0
					for(i=0;i<temp.length;i++)
					{
						temp2.options[i]=new Option(temp[i],tem[i])
					}
					temp3.options.length = 0
					for(i=0;i<tempc.length;i++)
					{
						temp3.options[i]=new Option(tempc[i],temd[i])
					}
					temp4.options.length = 0
					for(i=0;i<tempe.length;i++)
					{
						temp4.options[i]=new Option(tempe[i],temf[i])
					}
				}
				else
				{
					while(temp2.length>0)
					{
						temp2.options[0]=null
					}
					while(temp3.length>0)
					{
						temp3.options[0]=null
					}
					while(temp4.length>0)
					{
						temp4.options[0]=null
					}
				}
			}
			else
			{
				temp2.options.length = 0
				temp2.options[0]=new Option("","0")
				temp3.options.length = 0
				temp3.options[0]=new Option("","0")
				temp4.options.length = 0
				temp4.options[0]=new Option("","0")
			}
		}
	</script>
  </head>
  
  <body>
    	<form action="${ctx}/warn/insertWarn.action"  method="post" name="frmPost" id="frmPost"  onkeydown="if(event.keyCode==13){return false;}">
    		<!-- 主table -->
    		<s:token></s:token>
    		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 2px">
    			<tr>
    				<td  width="50%" valign="top">
    					<!-- 左侧table -->
    					<div class="framDiv" style="height: 550px">
    						<table width="100%" border="0" cellspacing="1" cellpadding="0">
				    			<tr>
									<td class='r2title' colspan='3'><b>新增预警</b></td>
								</tr>
								<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td height="20"  align="right"  width="18%">
										<span class="spanred">*</span>预警名称：
									</td>
									<td width="70%" class="msg" style="padding-left:10px" align="left" >
										<input  type="text" class="validate[required,custom[validateLength],custom[spechars]] text-input "   
										 id="warnName" name="warn.warnName"   size="68"/>
									</td>
								</tr>
								<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td height="20" align="right" >
										<span class="spanred">*</span>描述：
									</td>
									<td width="70%" class="msg" style="padding-left:10px" align="left">
										<textarea rows="5" name="warn.warnDescription" id="warnDescription" cols="50" class="validate[required,maxSize[255]]" ></textarea>
									</td>
								</tr>
								<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td height="20" align="right" >
										<span class="spanred">*</span>危害：
									</td>
									<td width="70%"  colspan="2" style="padding-left:10px" align="left">
										<textarea rows="5" name="warn.warnHarm" id="warnHarm"
												class="validate[required]"  cols="50"></textarea>
									</td>
								</tr>
								<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td height="20" align="right" >
										类型：
									</td>
									<td width="70%" class="msg" colspan="2"  style="padding-left:10px" align="left">
										<INPUT type="radio" name="warn.warnType" id="warnType" value="1" checked onClick="displayNewHoleInfo()" />
										新漏洞
										<INPUT type="radio" name="warn.warnType"  value="2"  onClick="displayNewHoleInfo()" />
										蠕虫
									</td>
								</tr>
								<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<tr id="idNewHoleName" >
									<td height="20" align="right" >
										<span class="spanred">*</span>新漏洞名称：
									</td>
									<td width="70%" class="msg" colspan="2" style="padding-left:10px" align="left">
										<input type="text" value="" size="67.50" name="warn.leakName"  id="leakName"
											class="validate[required,custom[validateLength],custom[spechars]] text-input " id="warnName" value="<s:property value="#request.queryId.warnName"/>" maxlength="100">
									</td>
								</tr>
								<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<tr id="idNewHoleType" >
									<td height="20"  align="right">
										新漏洞类型：
									</td>
									<td width="70%"  colspan="2" style="padding-left:10px" align="left">
										<select style="width:370px" name="warn.leakType" id="NewHoleType" >
											<option value=0 >其它</option><option value=1>通用设置</option><option value=2>主机信息</option><option value=3>帐号信息</option><option value=5>端口服务</option><option value=9>风险漏洞</option><option value=51>常规TCP端口扫描方式</option><option value=52>UDP端口扫描方式</option><option value=53>特殊TCP端口扫描方式</option><option value=91>系统漏洞</option><option value=95>数据库漏洞</option><option value=911>Windows NetBios类</option><option value=912>WEB类</option><option value=913>CGI类</option><option value=914>信息收集类</option><option value=915>强力攻击类</option><option value=916>守护进程类</option><option value=917>Mail类</option><option value=918>FTP类</option><option value=919>DNS类</option><option value=920>SNMP类</option><option value=921>Proxy类</option><option value=922>协议欺骗类</option><option value=923>RPC类</option><option value=924>NFS类</option><option value=925>NIS类</option><option value=926>后门类</option><option value=927>网络设备类</option><option value=928>蠕虫病毒类</option><option value=929>Samba服务类</option><option value=930>Apache类</option><option value=940>缓冲区溢出和拒绝服务攻击类</option><option value=951>MSSQL密码攻击类</option><option value=952>MSSQL访问控制类</option><option value=953>MSSQL存储过程校验</option><option value=954>MSSQL扩展存储过程溢出</option><option value=955>MSSQL系统配置</option><option value=956>MSSQL拒绝服务攻击</option><option value=961>Oracle口令猜测类</option><option value=962>Oracle系统漏洞类</option><option value=963>IBM DB2系统漏洞类</option><option value=964>Sybase系统漏洞类</option><option value=967>MySQL密码攻击类</option><option value=968>MySQL系统漏洞类</option><option value=969>MySQL配置漏洞类</option><option value=91101>NT关键问题类</option><option value=91102>NT服务类</option><option value=91103>NT用户类</option><option value=91104>NT口令类</option><option value=91105>NT组类</option><option value=91106>NT口令策略类</option><option value=91107>NT策略事务类</option><option value=91108>NT注册表类</option><option value=91109>NT网络类</option><option value=91110>NT共享类</option><option value=91111>NT DCOM类</option><option value=91112>浏览器类</option><option value=91113>NT应用程序类</option><option value=91114>NT补丁类</option><option value=92701>CISCO网络设备类</option><option value=92702>其他网络设备</option>
										</select>
									</td>
								</tr>
								
								<tr id="idNewHoleLevel" >
									<td height="20" align="right">
										新漏洞等级：
									</td>
									<td width="70%"  colspan="2"  style="padding-left:10px" align="left">
										<INPUT type="radio" name="warn.leakLevel" value="1" checked />
											低级
										<INPUT type="radio" name="warn.leakLevel" value="2" />
											中级
										<INPUT type="radio" name="warn.leakLevel" value="3"  />
											高级
									</td>
								</tr>
								<tr>
									<td height="20" align="right" >
										状态：
									</td>
									<td width="70%"  colspan="2" style="padding-left:10px" align="left">
										<INPUT type="radio" name="warn.status" value="50" checked />
										未发布
										<INPUT type="radio" name="warn.status" value="100" />
										已发布
										<INPUT type="radio" name="warn.status" value="20" />
										已过期
										<INPUT type="radio" name="warn.status" value="0" />
										已消除
									</td>
								</tr>
								<tr>
									<td height="20" align="right" >
										严重程度：
									</td>
									<td width="70%"  colspan="2" style="padding-left:10px" align="left">
										<INPUT type="radio" name="warn.seriousLevel" value="1" checked />
										低级
										<INPUT type="radio" name="warn.seriousLevel" value="2" />
										中低级
										<INPUT type="radio" name="warn.seriousLevel" value="3" />
										中级
										<INPUT type="radio" name="warn.seriousLevel" value="4" />
										中高级
										<INPUT type="radio" name="warn.seriousLevel" value="5" />
										高级
									</td>
								</tr>
								<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td height="20" align="right">
										<span class="spanred">*</span>解决方案：
									</td>
									<td width="70%"  colspan="2" style="padding-left:10px" align="left">
										<textarea rows="5" name="warn.solution" cols="50" id="solution"
											class="validate[required]" ></textarea>
									</td>
								</tr>
    						</table>
    					</div>
    				</td>
					
					<!-- 左右中间间隔 -->
					<td width="5px"></td>
					
 
    				<td valign="top">
    					<!-- 右侧的table -->
    					<div class="framDiv" style="height: 550px">
    						<table width="100%" border="0" cellspacing="1" cellpadding="0">
    							<tr>
									<td class='r2title' colspan='3'><b></b></td>
								</tr>
    							<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td height="20" align="right">
										源发布日期：
									</td>
									<td width="70%"  colspan="2" style="padding-left:10px" align="left">
										<input name="warn.publicDate" class="validate[required]"   type="text"  onclick="WdatePicker()" readonly size="67.50"/>
									</td>
								</tr>
								<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td height="20" align="right" >
										<span class="spanred">*</span>来源：
									</td>
									<td width="70%"  colspan="2" style="padding-left:10px" align="left">
										<input type="text" value="" size="67.50" name="warn.source" id="source"
												class="validate[required,maxSize[50]]"  maxlength="50">
									</td>
								</tr>
								<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td height="20" align="right" >
										<span class="spanred">*</span>发布人：
									</td>
									<td width="70%" colspan="2" style="padding-left:10px" align="left">
										<input type="text" value="" size="67.50" name="warn.publisher"  id="publisher"
										class="validate[required,maxSize[50]]" maxlength="50">
									</td>
								</tr>
								<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<!-- 虚线分割线 -->
								<tr>
									<td colspan="3">
										<div class="xuxian"></div></td>
								</tr>	
								<tr>
									<td height="20" align="right"  width="45%">
										<span class="spanred">*</span>受影响的操作系统：
									</td>
									
									<td width="30%" style="padding-left:10px" align="left">
										<INPUT onClick="AddItemOSs()" type=button  class="btnadd"  width="80">
										<INPUT onClick="DeleteItem('OSs')" type=button  class="btndel" width="80">
										<br>
										<select name="warn.influenceSys" id="OSs"  style="width: 370px" multiple size="5" 
											class="validate[required]"  >
										
										</select>
									</td>
								</tr>
								<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<!-- 虚线分割线 -->
								<tr>
									<td colspan="3">
										<div class="xuxian"></div></td>
								</tr>
								<tr>
									<td height="20" align="right" >
										<span class="spanred">*</span>漏洞编号：
									</td>
									<td width="40%" class="msg" colspan="2" style="padding-left:10px" align="left">
										<INPUT type="radio" name="ForecastHoleNumberType" value="CN-CVE" checked />
										CNCVE
										<INPUT type="radio" name="ForecastHoleNumberType" value="CVE" />
										CVE
										<INPUT type="radio" name="ForecastHoleNumberType" value="Bugtraq" />
										BUGTRAQ
										<input type="text" value="" name="ForecastHoleNumber" class="form1Input" maxlength="20"  ><br>
										<INPUT onClick="AddItemHoles()" type="button"  class="btnadd"  width="80">
										<INPUT onClick="DeleteItem('Holes')" type="button"  class="btndel"  width="80">
										<br>
										<select name="warn.leakNum" id="Holes" style="width: 370px"	
											class="validate[required,max[50]]"   multiple size="3">
										
										</select>
									</td>
								</tr>
								<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<!-- 虚线分割线 -->
								<tr>
									<td colspan="3">
										<div class="xuxian"></div></td>
								</tr>
								<tr>
									<td height="20" align="right" >
										受影响的服务器端口：
									</td>
				
									<td width="70%" class="msg" colspan="2" style="padding-left:10px" align="left">
										<INPUT type="radio" name="ForecastPortType" value="TCP" checked />
										TCP
										<INPUT type="radio" name="ForecastPortType" value="UDP" />
										UDP
										<input type="text" value="" name="ForecastPort"  maxlength="100" ><br>
										<INPUT onClick="AddItemPorts()" type=button  class="btnadd"  width="80">
										<INPUT onClick="DeleteItem('Ports')" type=button  class="btndel"  width="80">
										<br>
										<select name="warn.influenceInterface"  id="Ports" style="width: 370px" multiple size="3" >
										
										</select>
									</td>
								</tr>
    						</table>
    					</div>
    				</td>
    			</tr>
    		</table>
    		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px;">
			<!-- 空行 -->
			<tr>
				<td class="td_detail_separator"></td>
			</tr>

			<tr>
				<td align="right"><input type="button" class="btnyh"
					id="btnSave" value="保  存" onclick="sub();" /> <input
					type="button" class="btnyh" id="btnCancel" value="取  消"
					onclick="location='/soc/warn/query.action';" />
				</td>
			</tr>
		</table>
    		</form>
    	<!-- 受影响的操作系统 -->
    	<div id="dialog-addinfluenceSys" title="受影响的操作系统输入">
    		<table width="400" border="0" cellspacing="1" cellpadding="0" >
    							<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
    							<tr>
    								<td width="30%" align="right">资产操作系统：</td>
    								<td>
    									<select name="PK_TypeId" id="PK_TypeId" onChange="Cha_OsSelect()" style="width:200px">
											<option value=0>==请选择类型==</option><option value=1>Windows 98</option><option value=2>Windows ME</option><option value=3>Windows NT</option><option value=4>Windows 2000</option><option value=5>Windows XP</option><option value=6>Windows Server 2003</option><option value=7>Windows Vista</option><option value=8>Windows 7</option><option value=9>Windows Server 2008</option><option value=21>FreeBSD</option><option value=31>NetBSD</option><option value=38>OpenBSD</option><option value=44>Mac OS X</option><option value=52>Solaris</option><option value=80>Linux</option><option value=102>AIX</option><option value=103>OS/2</option><option value=104>OS/400</option><option value=105>OS/390</option><option value=106>Tru64</option><option value=113>HP-UX</option><option value=114>OpenVMS</option><option value=117>Hurd</option><option value=120>IRIX</option><option value=123>NetWare</option><option value=126>SCO UnixWare</option><option value=127>SCO Open Server</option>
										</select>
									</td>
    							</tr>
    							<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
    							<tr>
    								<td width="30%" align="right">操作系统版本：</td>
    								<td>
    									<select name="EditionId" id="EditionId" style="width:200px">
											<OPTION value="0">全部</OPTION>
											<OPTION value="1">win95</OPTION>
											<OPTION value="2">win98</OPTION>
											<OPTION value="3">win2000</OPTION>
											<OPTION value="3">winME</OPTION>
											<OPTION value="3">winXP</OPTION>
										</select>
    								</td>
    							</tr>
    							<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
    							<tr>
    								<td width="30%" align="right">操作系统版本号：</td>
    								<td>
    									<select name="NumId" id="NumId" style="width:200px">
											<OPTION value="0">全部</OPTION>
											<option value="1">1.0</option>
											<option value="1">2.0</option>
											<option value="1">3.0</option>
											<option value="1">4.0</option>
										</select>
    								</td>
    							</tr>
    							<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
    							<tr>
    								<td width="30%" align="right">操作系统品牌：</td>
    								<td>
    									<select name="BrandId" id="BrandId" style="width:200px" >
											<OPTION value="0">全部</OPTION>
											<OPTION value="0">SCO</OPTION>
											<OPTION value="0">redhat</OPTION>
											<OPTION value="0">Microsoft</OPTION>
											<OPTION value="0">IPHONE</OPTION>
											<OPTION value="0">CentOs</OPTION>
										</select>
    								</td>
    							</tr>
    							<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
    							<tr>
    								<td width="30%" align="right">操作系统架构平台:</td>
    								<td>
    									<select name="PK_CpuId" id="PK_CpuId" style="width:200px">
											<option value=0>其它</option><option value=1>IA32</option><option value=2>IA64</option><option value=3>AMD64</option><option value=4>Sparc32</option><option value=5>Sparc64</option><option value=6>Alpha</option><option value=7>PowerPC</option><option value=8>MIPS</option><option value=9>ARM</option>
										</select>
    								</td>
    							</tr>
								
    		</table>
    	</div>
  </body>
</html>
