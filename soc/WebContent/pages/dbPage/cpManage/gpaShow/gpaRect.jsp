<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>通用物理整改建议</title>
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
<style type="text/css">
     .biaoti{
           font-size: 12px ;
           font-weight: bold;
          
     }
</style>
 <script type="text/javascript" src="${ctx}/scripts/sorttalbe.js"></script>

	<script type="text/javascript">
	function gpaRectDocCreate(){
	    var gpaDate=document.getElementById("paraGpaDate");
	    if(gpaDate.value==null){
	     alert("请选择测评日期！");
	     return ;
	    }
	    if($.trim(gpaDate.value)==""){
	     alert("请选择测评日期！");
	     return ;
	    }
	    window.location="${ctx}/gpaRect/gpaRectDocCreate.action?gpaDate="+gpaDate.value;
	}
	
	jQuery(document).ready(function()
	{
	
	$("#dialog-gpaRectUpdate").dialog({
      autoOpen: false,
      width:1000,
      height:300,
      modal:true,
		buttons: {
		}
	});	
	});
	
		jQuery(document).ready(function()
	{
	$("#dialog-gpaRectInsert").dialog({
      autoOpen: false,
      width:1000,
      height:300,
      modal:true,
		buttons: {
		}
	});	
	});
	
	function gpaRectUpdateOpen(str1,str2,str3) {
 	    var gpaRectId=document.getElementById("updateGpaRectId");
	    var acviceContent=document.getElementById("acvice");
	    var sonContent=document.getElementById("updateSonContent");
	    gpaRectId.value=str1;
	    acviceContent.value=str2; 
	    sonContent.value=str3;
  		$("#dialog-gpaRectUpdate").dialog("open");
	}
	
	function gpaRectInsertOpen(str1,str2,str3,str4,str5,str6) {
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
  		$("#dialog-gpaRectInsert").dialog("open");
	}
	
	function gpaRectUpdateClose() {
  		$("#dialog-gpaRectUpdate").dialog("close");
	}
	
	function gpaRectInsertClose() {
  		$("#dialog-gpaRectInsert").dialog("close");
	}	
	
	function gpaRectUpdate(doc) {
  		doc.disabled="disabled";
  		var gpaRectId=document.getElementById("updateGpaRectId").value;
	    var acviceContentUpdate=document.getElementById("acvice").value;
	    var gpaDateUpdate=document.getElementById("gpaDateUpdate").value;
	    var typeUpdate=document.getElementById("typeUpdate").value;
	    if(acviceContentUpdate.length>1000){
		  		alert("您输入的整改建议长度超过1000！！！");
		  		doc.disabled="";
		  		return;
		    }
	  //  alert(gpaRectId.value+"---"+acviceContentUpdate.value+"--"+gpaDateUpdate+"--"+typeUpdate);
  		window.location.href="${ctx}/gpaRect/gpaRectUpdate.action?gpaRectId="+gpaRectId+"&gpaRectAdvise="+encodeURI(encodeURI(acviceContentUpdate,"utf-8"))+"&gpaDateUpdate="+gpaDateUpdate+"&typeUpdate="+encodeURI(encodeURI(typeUpdate,"utf-8"));
  		$("#gpaRectUpdate").attr("disabled","disabled");
	}
	
	function btnSub(doc){
	    doc.disabled="disabled";
	    if(document.getElementById("gpaRectAdvise").value.length>1000){
		  		alert("您输入的整改建议长度超过1000！！！");
		  		doc.disabled="";
		  		return;
		    }
		$("#gpaRectInsert").submit();
	}
	
	function gpaRectDelete(id) {
	    var gpaDateDelete=document.getElementById("gpaDateDelete").value;
	    var typeDelete=document.getElementById("typeDelete").value;
  		window.location.href="${ctx}/gpaRect/gpaRectDelete.action?gpaRectId="+id+"&gpaDateDelete="+gpaDateDelete+"&typeDelete="+encodeURI(encodeURI(typeDelete,"utf-8"));
	}
	</script>
  </head>
  <body >
  <input type="hidden" id="gpaDateDelete" value="${gpaDate}">
  <input type="hidden" id="typeDelete" value="${type}">
 
  <form action="${ctx}/gpaRect/gpaRectList.action" method="post" id="frm">
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
								    <tr>				 
										<th  colspan="6" align="left"><input type="button" class="btnstyle" value="生成报表" onclick="gpaRectDocCreate()"/></th>
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
									 <c:forEach  var="item" items="${mapgpaRect}">
									<tr>
									<td style="cursor:pointer;text-align:left">
                                     ${item.gFatherSort} ${item.gFatherName}
                                    </td>
									<td style="cursor:pointer;text-align:left"> ${item.fatherSort} ${item.fatherName}</td>
									<td style="cursor:pointer;text-align:left">${item.sonContent}</td>
									<td align="center">
									<c:if test="${item.gpaRectAdvise==null}">
									<input  type="button"   class="addOpener" style=" background:url(${ctx}/images/btn1.jpg) no-repeat; border:none; width:58px; height:21px; cursor:pointer" onclick="gpaRectInsertOpen('${item.gFatherSort}','${item.gFatherName}','${item.fatherSort}','${item.fatherName}','${item.sonSort}','${fn:trim(item.sonContent)}')"/>
									</c:if>
									<c:if test="${item.gpaRectAdvise!=null}">
									   ${item.gpaRectAdvise}
									</c:if>
									</td>
									<td align="center">
										<c:if test="${item.gpaRectAdvise!=null}">
										${item.gpaRectDate}
										</c:if>
									</td>
									<td align="center">
									<c:if test="${item.gpaRectAdvise==null}">
										<!--<span style="color: gray;">修改整改建议&nbsp;&nbsp;删除整改建议</span>-->
										<input  type="button" style=" background:url(${ctx}/images/btnEdit_gray.jpg) no-repeat; border:none;  width:58px;  height:21px; cursor:pointer;">
										    &nbsp;&nbsp;
										<input  type="button" style=" background:url(${ctx}/images/btnDel_gray.jpg) no-repeat; border:none;  width:58px;  height:21px; cursor:pointer;" >
										</c:if>
									<c:if test="${item.gpaRectAdvise!=null}">
										 <input type="button"   class="changeOpener"  style=" background:url(${ctx}/images/btnEdit.jpg) no-repeat; border:none; width:58px; height:21px; cursor:pointer;" onclick="gpaRectUpdateOpen('${item.gpaRectId}','${item.gpaRectAdvise}','${fn:trim(item.sonContent)}')" />
										 &nbsp;&nbsp; 
										  <input type="button"  class="proDelete"  style=" background:url(${ctx}/images/btnDel.jpg) no-repeat; border:none;  width:58px;  height:21px; cursor:pointer;"  onclick="gpaRectDelete('${item.gpaRectId}')" />
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
    <input type="hidden"  name="gpaDate" id="paraGpaDate"  value="<s:property value="#parameters.gpaDate"/>">
    <input type="hidden" name="type" id="type" value="<s:property value="#parameters.type"/>">
    </form>
    
    <div id="dialog-gpaRectUpdate" title="修改整改建议">
        <input type="hidden" id="updateGpaRectId" value="">
           <input type="hidden" id="gpaDateUpdate" value="${gpaDate}">
              <input type="hidden" id="typeUpdate" value="${type}">
		<table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2">
						<tr  height="30">
							<td width="20%" align="center" style="text-align: right">差距项:</td>
							<td width="80%"  align="center" style="text-align:left"><input id="updateSonContent" value="" style="width: 100%" readonly="readonly"/></td>						
						</tr>
						<tr  height="50">
						   <td width="20%" align="center" style="text-align: right">整改建议:</td>
						   <td width="80%" align="center" style="text-align:left">
						     <textarea id="acvice" style="width: 100%;height: 100%"></textarea>
						   </td>	
					    </tr>
					    <tr height="20">
					       <td colspan="2" style="text-align:right">
					       <input type="button" value="保存" class="btnyh"  onclick="gpaRectUpdate(this)"/>
					       <input type="button" value="退出" class="btnyh"  onclick="gpaRectUpdateClose()"/>
					    </tr>
		</table>
	</div>
	<div id="dialog-gpaRectInsert" title="添加整改建议">
	  <form name="gpaRectInsert" id="gpaRectInsert" action="${ctx}/gpaRect/gpaRectInsert.action?gpaDate=${gpaDate}+&type=${type}" method="post">
        
        <input type="hidden" id="gpaRectDate" name="gpaRectDate"  value="">
        <input type="hidden" id="gFatherSort" name="gFatherSort"  value="">
        <input type="hidden" id="gFatherName" name="gFatherName"  value="">
        <input type="hidden" id="fatherSort"  name="fatherSort"   value="">
        <input type="hidden" id="fatherName"  name="fatherName"   value="">
        <input type="hidden" id="sonSort"     name="sonSort"      value="">
        <input type="hidden"      name="gpaAssessResult"      value="<s:property value="#parameters.type"/>">
		<table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2">
						<tr  height="30">
							<td width="20%" align="center" style="text-align: right">差距项:</td>
							<td width="80%"  align="center" style="text-align:left"><input id="sonContent" name="sonContent" value="" style="width: 100%" readonly="readonly"/></td>						
						</tr>
						<tr  height="50">
						   <td width="20%" align="center" style="text-align: right">整改建议:</td>
						   <td width="80%" align="center" style="text-align:left">
						     <textarea id="gpaRectAdvise" name="gpaRectAdvise" style="width: 100%;height: 100%"></textarea>
						   </td>	
					    </tr>
					    <tr height="20">
					       <td colspan="2" style="text-align:right">
					       <input type="button" class="btnyh"  value="保存"  onclick="btnSub(this)" />
					       <input type="button" class="btnyh"  value="退出" onclick="gpaRectInsertClose()"/>
					    </tr>
		</table>
	   </form>
	</div>
  </body>
</html>
