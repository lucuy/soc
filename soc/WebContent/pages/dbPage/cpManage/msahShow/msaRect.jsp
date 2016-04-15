<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <base href="<%=basePath%>">
    
    <title>My JSP 'msaShow.jsp' starting page</title>
<meta http-equiv="Cache-Control" content="private" />
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet" type="text/css">
<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet"
	type="text/css"> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
	<SCRIPT src="${ctx}/scripts/jquery-1.7.2.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/jquery-ui.custom.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/util.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/popuplayer.js" type=text/javascript></SCRIPT>
<script type="text/javascript" src="${ctx}/scripts/sorttalbe.js"></script>
<style type="text/css">
     .biaoti{
           font-size: 12px ;
           font-weight: bold;
          
     }


</style>
	<script type='text/javascript'>
	

	

	jQuery(document).ready(function()
	{
	
	$("#dialog-msaRectUpdate").dialog({
      autoOpen: false,
      width:1000,
      height:300,
      modal:true,
		buttons: {
			/* "提交[Enter]": function() {								
				$(this).dialog("close"); 
			},	
	       "退出[Enter]": function() {								
				$(this).dialog("close"); 
			} */
		}
	});	
	});
	
		jQuery(document).ready(function()
	{
	
	$("#dialog-msaRectInsert").dialog({
      autoOpen: false,
      width:1000,
      height:300,
      modal:true,
		buttons: {
			/* "提交[Enter]": function() {								
				$(this).dialog("close"); 
			},	
	       "退出[Enter]": function() {								
				$(this).dialog("close"); 
			} */
		}
	});	
	});
		function msaRectDocCreate(){
		    var msaDate=document.getElementById("paramsaDate");
		    if(msaDate.value==null){
		     alert("请选择测评日期！");
		     return ;
		    }
		    if($.trim(msaDate.value)==""){
		     alert("请选择测评日期！");
		     return ;
		    }
		    window.location="${ctx}/msaRect/msaRectDocCreate.action?msaDate="+msaDate.value; 
		}
	function msaRectUpdateOpen(str1,str2,str3) {
 	    var msaRectId=document.getElementById("updatemsaRectId");
	    var acvice=document.getElementById("acvice");
	    var sonContent=document.getElementById("updateSonContent");
	    msaRectId.value=str1;
	    acvice.value=str2; 
	    sonContent.value=str3;
  		$("#dialog-msaRectUpdate").dialog("open");
	}
	
	function msaRectInsertOpen(str1,str2,str3,str4,str5,str6) {
 	    var gFatherSort=document.getElementById("gFatherSort");
	    var gFatherName=document.getElementById("gFatherName");
	    var fatherSort=document.getElementById("fatherSort");
	    var fatherName=document.getElementById("fatherName");
	    var sonSort=document.getElementById("sonSort");
	    var sonContent=document.getElementById("sonContent");
	    gFatherSort.value=str1; 
	    gFatherName.value=str2; 
	    fatherSort.value=str3; 
	    fatherName.value=str4; 
	    sonSort.value=str5;
	    sonContent.value=str6; 
	     
  		$("#dialog-msaRectInsert").dialog("open");
	}
	
	function msaRectUpdateClose() {
  		$("#dialog-msaRectUpdate").dialog("close");
	}
	
	function msaRectInsertClose() {
  		$("#dialog-msaRectInsert").dialog("close");
	}	
	
	function msaRectUpdate(doc) {
	    doc.disabled="disabled"; 
  		var msaRectId=document.getElementById("updatemsaRectId");
	    var acvice=document.getElementById("acvice");
	   var mpaDateUpdate=document.getElementById("mpaDateUpdate").value;
	    var typeUpdate=document.getElementById("typeUpdate").value;
  		window.location="${ctx}/msaRect/msaRectUpdate.action?msaRectId="+msaRectId.value+"&msaRectAdvise="+encodeURI(encodeURI(acvice.value,"utf-8"))+"&mpaDateUpdate="+mpaDateUpdate+"&typeUpdate="+encodeURI(encodeURI(typeUpdate,"utf-8"));
	   
	}
	function btnSub(doc) {
	    doc.disabled="disabled";
	     if(document.getElementById("msaRectAdvise").value.length>1000){
		  		alert("您输入的整改建议长度超过1000！！！");
		  		doc.disabled="";
		  		return;
		    }
		$("#msaRectInsert").submit();
	}
	
	
	function msaRectDelete(id) {
	 var mpaDateDelete=document.getElementById("mpaDateDelete").value;
	    var typeDelete=document.getElementById("typeDelete").value;
  		window.location="${ctx}/msaRect/msaRectDelete.action?msaRectId="+id+"&mpaDateDelete="+mpaDateDelete+"&typeDelete="+encodeURI(encodeURI(typeDelete,"utf-8"));
 
	}
		
	//$(document).ready(function() {
	   //    var msaDate=document.getElementById("paramsaDate");
	     //  var type=document.getElementById("type");
	
		//	jsonForAjax("${ctx}/msaRect/msaRectList.action?msaDate="+ msaDate.value+"&type="+type.value+ "&t=" + new Date());

	  //  }
	//);
	
	function jsonForAjax(url){
		var htmlStr = "";	
		$.getJSON(url ,function(result){
			//$("#table-userManage tr:not(:first)").remove();
			$("#tbody").html("");
			$.each(result.processLog, function(i,item) {
			 	//var rowNum = $("#table-userManage tr").length-1;
			 	var tBodyHtml = $("#tbody").html();
			 	
			 	if(item.sonSort!= null){
			 	    var sonContent = item.sonContent.replace(/[\r\n]/g, "");//去除行之间的空格
			 		htmlStr = "";
			 		htmlStr += "<tr>";
			 			htmlStr += "<td  style=\"cursor:pointer;text-align:left\">";
			 			  htmlStr += item.gFatherSort+" "+item.gFatherName;	
			 			htmlStr += "</td>";	
			 			htmlStr += "<td  style=\"cursor:pointer;text-align:left\">";
			 			  htmlStr += item.fatherSort+" "+item.fatherName;
			 			htmlStr += "</td>";	
			 			htmlStr += "<td  style=\"cursor:pointer;text-align:left\">";
			 			  htmlStr += item.sonContent;
			 			htmlStr += "</td>";
			 			
			 				
			 			htmlStr += "<td  style=\"cursor:pointer;text-align:center\">";
			 			if(item.msaRectAdvise==null){ 
			 			//msaRectInsertOpen('a)应访谈物理安全负责人，询问现有机房和办公场地（放置终端计算机设备）的环境条件是否具有基本的防震、防风和防雨等能力；');
			 			   htmlStr +="<button   class=\"addOpener\" style=\" background:url(/compliance/images/btn1.jpg) no-repeat; border:none; width:58px; height:21px; cursor:pointer;\" onclick=\"msaRectInsertOpen('"+item.gFatherSort+"','"+item.gFatherName+"','"+item.fatherSort+"','"+item.fatherName+"','"+item.sonSort+"','"+sonContent+"')\" ></button>";
			 			}else{
			 			 htmlStr += item.msaRectAdvise;
			 			}
			 			htmlStr += "</td>";
			 			
			 			htmlStr += "<td  style=\"cursor:pointer;text-align:center\">";
			 			if(item.msaRectAdvise==null){
			 			}else{
			 			  htmlStr += item.msaRectDate;
			 			}
			 			htmlStr += "</td>";
			 			
			 			htmlStr += "<td  style=\"cursor:pointer;text-align:center\">";
			 			
			 			if(item.msaRectAdvise==null){
			 				htmlStr +="<span style=\"color: gray;\">修改整改建议&nbsp;&nbsp;删除整改建议</span>";
			 			}else{
			 				htmlStr +=" <button   class=\"changeOpener\" style=\" background:url(${ctx}/images/btnEdit.jpg) no-repeat; border:none; width:58px; height:21px; cursor:pointer;\" onclick=\"msaRectUpdateOpen('"+item.msaRectId+"','"+item.msaRectAdvise+"','"+sonContent+"')\"></button>&nbsp;&nbsp;  <button id='msaDel'  class=\"proDelete\" onclick=\"msaRectDelete('"+item.msaRectId+"')\" style=\" background:url(/compliance/images/btnDel.jpg) no-repeat; border:none;  width:58px;  height:21px; cursor:pointer;\" ></button>";
			 			}
			 			htmlStr += "</td>";
		 
			 		htmlStr += "</tr>";
			 	}
			 	//$(htmlStr).insertAfter($("#table-userManage tr:eq("+rowNum+")"));
			 	$("#tbody").html(tBodyHtml+htmlStr);
			 });				
		});
		

	}
	</script>

  </head>
  
  <body style="padding-left: 2px;"> 
   <input type="hidden" id="mpaDateDelete" value="${msaDate}">
  <input type="hidden" id="typeDelete" value="${type}">
  <form action="${ctx}/msaRect/msaRectList.action?mpaDateinsert=${msaDate}+&typeinsert=${type}" method="post" id="frm"> 
		<table width="99.5%" border="0" cellspacing="0" cellpadding="0"  >
			<tr>
				<td><!-- 
					<div class="caozuobox">
						<input type="hidden" name="file" id="file"> 
						<span class="kuaiju">&nbsp;快速搜索:&nbsp;&nbsp; <input type="text" id="goToSearch" name="keyword"> </span> 
						<img src="/compliance/images/search.jpg" onclick="goSearch(0)" style="margin-left: 5px; cursor: pointer" class="hand" /> 
					</div> -->
				</td>

			</tr>
			<tr>
				<td>
					<div class="sbox" style="margin-top: 0px">
						<div class="cont">
							<!-- information area -->
							<div id="dataList">
								<table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2" id="table-userManage" style="font-size:12px;">
								<thead>
								    <tr >				 
										<th  colspan="6" align="left"><input type="button" class="btnstyle" value="生成报表" onclick="msaRectDocCreate()"/></th>
									</tr>
									<tr >				 
										<th width="15%"   class="biaoti" onclick="sortAble('table-userManage', 0)"  style="cursor:pointer" >控制域</th>
										<th width="15%"   class="biaoti" onclick="sortAble('table-userManage', 1)"  style="cursor:pointer" >控制单元</th>
										<th width="30%"   class="biaoti" onclick="sortAble('table-userManage', 2)"  style="cursor:pointer" >不符合项</th>
										<th width="15%"   class="biaoti" onclick="sortAble('table-userManage', 3)"  style="cursor:pointer" >整改建议</th>
										<th width="10%"   class="biaoti" onclick="sortAble('table-userManage', 4)"  style="cursor:pointer" >整改时间</th>
										<th width="*"     class="biaoti" onclick="sortAble('table-userManage', 5)"  style="cursor:pointer" >整改建议操作</th>
									</tr>
									</thead>
									<tbody  id="tbody">
									 <c:forEach  var="item" items="${mapMsaRect}">
									<tr>
									<td style="cursor:pointer;text-align:left">
                                     ${item.gFatherSort} ${item.gFatherName}
                                    </td>
									<td style="cursor:pointer;text-align:left"> ${item.fatherSort} ${item.fatherName}</td>
									<td style="cursor:pointer;text-align:left">${item.sonContent}</td>
									<td align="center">
									<c:if test="${item.msaRectAdvise==null}">
									  <input  type="button"    class="addOpener" style=" background:url(${ctx}/images/btn1.jpg) no-repeat; border:none; width:58px; height:21px; cursor:pointer" onclick="msaRectInsertOpen('${item.gFatherSort}','${item.gFatherName}','${item.fatherSort}','${item.fatherName}','${item.sonSort}','${fn:trim(item.sonContent)}')">
									</c:if>
									<c:if test="${item.msaRectAdvise!=null}">
									   ${item.msaRectAdvise}
									</c:if>
									</td>
									<td align="center">
										<c:if test="${item.msaRectAdvise!=null}">
										${item.msaRectDate}
										</c:if>
									</td>
									<td align="center">
									<c:if test="${item.msaRectAdvise==null}">
										<!--<span style="color: gray;">修改整改建议&nbsp;&nbsp;删除整改建议</span>-->
										<input  type="button" style=" background:url(${ctx}/images/btnEdit_gray.jpg) no-repeat; border:none;  width:58px;  height:21px; cursor:pointer;">
										    &nbsp;&nbsp;
										<input  type="button" style=" background:url(${ctx}/images/btnDel_gray.jpg) no-repeat; border:none;  width:58px;  height:21px; cursor:pointer;" >
										</c:if>
									<c:if test="${item.msaRectAdvise!=null}">
										 <input  type="button"    class="changeOpener"  style=" background:url(${ctx}/images/btnEdit.jpg) no-repeat; border:none; width:58px; height:21px; cursor:pointer;" onclick="msaRectUpdateOpen('${item.msaRectId}','${item.msaRectAdvise}','${fn:trim(item.sonContent)}')" />
										 &nbsp;&nbsp; 
										  <input  type="button"  class="proDelete"  style=" background:url(${ctx}/images/btnDel.jpg) no-repeat; border:none;  width:58px;  height:21px; cursor:pointer;"  onclick="msaRectDelete('${item.msaRectId}')" />
										</c:if>
									</td>
									</tr>
									</c:forEach>
									</tbody>
								 <tr>
									 <td colspan="6"> 
									<jsp:include page="../../commons/pageForList.jsp"></jsp:include>
									 </td>
									</tr>
								</table>
							</div>

						</div>
					</div>
				</td>
			</tr>
		</table>
    <input type="hidden"  name="msaDate" id="paramsaDate"  value="<s:property value="#parameters.msaDate"/>"  >
    <input type="hidden" name="type" id="type" value="<s:property value="#parameters.type"/>"  >
    </form>
    
    
    <div id="dialog-msaRectUpdate" title="修改整改建议">
    <form id="msaRectUpdate">
     <input type="hidden" id="mpaDateUpdate" value="${msaDate}">
              <input type="hidden" id="typeUpdate" value="${type}">
        <input type="hidden" id="updatemsaRectId" value="">
		<table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2">
						<tr  height="30">
							<td width="10%" align="center" style="text-align: right">差距项:</td>
							<td width="*"  align="center" style="text-align:left"><input id="updateSonContent" value="" style="width: 100%" readonly="readonly"/></td>						
						</tr>
						<tr  height="50">
						   <td   align="center" style="text-align: right">整改建议:</td>
						   <td  align="center" style="text-align:left">
						     <textarea id="acvice" style="width: 100%;height: 100%"></textarea>
						   </td>	
					    </tr>
					    <tr height="20">
					       <td colspan="2" style="text-align:right">
					       <input type="button" class="btnyh" value="保存" onclick="msaRectUpdate(this)"/>
					       <input type="button" class="btnyh" value="退出" onclick="msaRectUpdateClose()"/>
					    </tr>
		</table>
		</form>
	</div>
	
	
	<div id="dialog-msaRectInsert" title="添加整改建议">
	  <form name="msaRectInsert" id="msaRectInsert" action="${ctx}/msaRect/msaRectInsert.action?maDateInsert=${msaDate}+&typeInsert=${type}" method="post">
        <input type="hidden" id="msaDate"     name="msaDate"     value="<s:property value="#parameters.msaDate"/>" >
        <input type="hidden" id="msaRectDate" name="msaRectDate"  value="">
        <input type="hidden" id="gFatherSort" name="gFatherSort"  value="">
        <input type="hidden" id="gFatherName" name="gFatherName"  value="">
        <input type="hidden" id="fatherSort"  name="fatherSort"   value="">
        <input type="hidden" id="fatherName"  name="fatherName"   value="">
        <input type="hidden" id="sonSort"     name="sonSort"      value="">
        <input type="hidden"      name="msaAssessResult"      value="<s:property value="#parameters.type"/>">
		<table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2">
						<tr  height="30">
							<td width="10%" align="center" style="text-align: right">差距项:</td>
							<td width="*"  align="center" style="text-align:left"><input id="sonContent" name="sonContent" value="" style="width: 100%" readonly="readonly"/></td>						
						</tr>
						<tr  height="50">
						   <td  align="center" style="text-align: right">整改建议:</td>
						   <td  align="center" style="text-align:left">
						     <textarea id="msaRectAdvise" name="msaRectAdvise" style="width: 100%;height: 100%"></textarea>
						   </td>	
					    </tr>
					    <tr height="20">
					       <td colspan="2" style="text-align:right">
					       <input type="button" class="btnyh" value="保存" onclick="btnSub(this)" />
					       <input type="button" class="btnyh" value="退出" onclick="msaRectInsertClose()"/>
					    </tr>
		</table>
	   </form>
	</div>
  </body>
</html>
