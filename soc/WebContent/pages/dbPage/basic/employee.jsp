<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>人员管理</title>
<meta http-equiv="Cache-Control" content="private" />
<LINK href="${ctx}/css/basic/css.css" type=text/css rel=stylesheet>
<LINK href="${ctx}/css/basic/basic.css" type=text/css rel=stylesheet>
<LINK href="${ctx}/css/style/jquery-ui.custom.css" type=text/css rel=stylesheet>
<LINK href="${ctx}/css/style/jquery.popuplayer.css" type=text/css rel=stylesheet>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.ui.dialog.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/check.js"></script>

<script language="javascript">
     jQuery(document).ready(function(){
		 
		 $("#dialog-extQuery").dialog({
			autoOpen : false,
			minWidth : 600,
			minHeight : 460,
			buttons : {
				"确定[Enter]" : function() {
					extQuery(0);
					$(this).dialog("close");
				},

				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		goSearch(0);
	 });
	
	//添加员工
    function addEmployee(){
    location.href = "${ctx}/pages/basic/addEmployee.jsp";
    }
    //高级搜索
	function extQuery(num) {
		var userName = $.trim($("#hsoftname").val());
		var trueName = $.trim($("#hsysname").val());
		var userIdNum = $.trim($("#hsoftdscription").val());
		var userAuth = $.trim($("#himdegree").val());
		var userStatus = $.trim($("#huser").val());
		
		if(userName=="" && trueName=="" && userIdNum=="" && userAuth=="3" && userStatus=="3"){
		  return;
		}
		document.getElementById("goToSearch").value="";//设置快速搜索为空
		document.getElementById("flag").value="高级";//设置标志为高级搜索
		jsonForAjax("${ctx}/employee/extQuery.action?startIndex="
				+ encodeURI(num, "utf-8") + "&euserName="
				+ encodeURI(encodeURI(userName, "utf-8")) + "&etrueName="
				+ encodeURI(encodeURI(trueName, "utf-8")) + "&userIdNum="
				+ encodeURI(encodeURI(userIdNum, "utf-8"))
				+ "&userAuth=" + encodeURI(encodeURI(userAuth, "utf-8"))
				+ "&userStatus=" + encodeURI(encodeURI(userStatus, "utf-8"))
				+ "&t=" + new Date());
		
	}
    
     /*多选框  */
     $("#chkAll").live("click", function(event) {
		if ($("#chkAll").hasClass('not_checked')) {
			$("#chkAll").removeClass('not_checked');
			$(".check-box").attr('checked', true);
		} else {
			$("#chkAll").addClass('not_checked');
			$(".check-box").attr('checked', false);
		}
	});
	//删除用户
    function deleteuser(){
	      var ids ="";
	      $("[name=ids]:checkbox:checked").each(function(){
	           if(ids!=""){
	           ids+=","+$(this).val();
	           }else{
	            ids=$(this).val();
	           }
	         
	      });
	      if($("[name=ids]:checkbox:checked").size()<1) {
				alert("请选择要删除的用户");
				return;
			}
	      
			if(ids!=''){
				if(confirm("确认执行操作吗？")){
					location.href="${ctx}/employee/deleteUser.action?ids="+ids;
				}			
			}
	   } 
	   
	   //更改状态
	   function changeStatus(){
	   var status = $("#status").val();
	    var ids ="";
	      $("[name=ids]:checkbox:checked").each(function(){
	           if(ids!=""){
	           ids+=","+$(this).val();
	           }else{
	            ids=$(this).val();
	           }
	         
	      });
	      if($("[name=ids]:checkbox:checked").size()<1) {
				alert("请选择要更改状态的用户");
				return;
			}
			 
			if(ids!=''){
				if(confirm("确认执行操作吗？")){
					location.href="${ctx}/employee/changeStatus.action?ids="+ids+"&status="+status;
				}			
			}
	   }
	   function extQueryDlg() {
		$("#dialog-extQuery").dialog("open");
	}
	
     //快速搜索
     function goSearch(num){
    	 resetCheckBox();
		var keyword = $.trim($("#goToSearch").val());
		var flag=document.getElementById("flag");
		
		 if(keyword!=""){
		    flag.value="快速";//设置标志为高级搜索
         }
           var hsoftname=$.trim($("#hsoftname").val());
           var hsysname=$.trim($("#hsysname").val());
           var hsoftdscription=$.trim($("#hsoftdscription").val());
           var himdegree=$.trim($("#himdegree").val());
           var huser=$.trim($("#huser").val());
         if(hsoftname !="" || hsysname !="" || hsoftdscription !="" || himdegree !="3" || huser !="3"){
            flag.value="高级";//设置标志为高级搜索
         }
        
         if((keyword=="")&&(hsoftname == "" && hsysname ==""  && hsoftdscription ==""  && himdegree=="3" && huser=="3")){
             flag.value="无";
         }
        if(num ==0 && flag.value=="高级"){
               extQuery(num);
		       return ;
        }
		if(keyword != ''|| num !=''){
			 if(flag.value=="快速"){
		       jsonForAjax("${ctx}/employee/queryAjaxEmployee.action?startIndex="+encodeURI(num,"utf-8")+ "&keyword=" + encodeURI(encodeURI(keyword,"utf-8")) + "&t=" + new Date());
		       return ;
		    }
		    if(flag.value=="高级"){
		       extQuery(num);
		       return ;
		    }
			jsonForAjax("${ctx}/employee/queryAjaxEmployee.action?startIndex="+encodeURI(num,"utf-8")+ "&keyword=" + encodeURI(encodeURI(keyword,"utf-8")) + "&t=" + new Date());
		}else{
			jsonForAjax("${ctx}/employee/queryAjaxEmployee.action?t=" + new Date());
		}	
	}
    	function goToPage(lastnum) {
    		var page = $("#page").val();
    		var num = parseInt(page * 15) - parseInt(15);

    		//if(num>lastnum)num=lastnum;
    		if (num > lastnum) {
    			alert("错误页数");
    			return false;
    		}
    		if (num < 0)
    			num = 0;
    		$("#startIndex").value = num;
    		goSearch(num);
    	}
    	
	/* 数据分页 */
	function jsonForAjax(url){
		var htmlStr = "";	
		var htmlPage = "";
		var ShouDingvalue = document.getElementById("ShouDingId").value;
		$.getJSON(url ,function(result){
			$("#tbodyuse").html("<tr style=\"display:none\"></tr>");
			$.each(result.processLog, function(i,item) {
			 	var tBodyHtml = $("#tbodyuse").html();
			 	if(item.userID != null){
			 		htmlStr = "";
			 		htmlStr += "<tr  align=\"center\">";
			 		if(ShouDingvalue==1){
			 		}else{
			 		htmlStr += "<td>";
			 				htmlStr += "<input name=\"ids\" type=\"checkbox\" class=\"check-box\" value=\""+item.userID+"\"/>";
			 			htmlStr += "</td>";
			 		}
			 			
			 			htmlStr += "<td >";
			 			if(ShouDingvalue==1){
			 			 htmlStr +=item.userName;
			 			}else{
			 			 htmlStr +="<a href=\"${ctx}/employee/detailedMessage.action?userId="+item.userID+"\">"+item.userName+"</a>";
			 			}
			 			
			 			htmlStr += "</td>";
			 			htmlStr += "<td>";
			 				htmlStr += item.trueName;
			 			htmlStr += "</td>";
			 			htmlStr += "<td>";
			 				 htmlStr +=item.IDCardNum;
			 			htmlStr += "</td>";
			 			htmlStr += "<td>";
					 			 if(item.userAuthority == "0" ){
					 			 	htmlStr +=  "系统管理员 &nbsp;";
					 			 }else if(item.userAuthority == "1" ){
					 			    htmlStr += " 安全管理员 &nbsp;";
					 			 }else if(item.userAuthority == "2" ){
					 				 htmlStr +="系统管理员 &nbsp;安全管理员";
					 		 }				 		 
			 			htmlStr += "</td>";
			 			htmlStr += "<td >";
			 			if(item.status==0){
			 			htmlStr += "正常";	
			 			}else if (item.status==1){
			 			htmlStr += "锁定";	
			 			}else if (item.status==2){
			 			htmlStr += "禁用";	
			 			} 
			 			htmlStr += "</td>";  
			 			 
			 		htmlStr += "</tr>";
			 	}
			 	$("#tbodyuse").html(tBodyHtml+htmlStr);
			 });				
			if(result.page.startIndex != null){		
				$("#tbodyuseTr").html("");
			   /*  htmlPage += "<tr>"; */
			    	htmlPage +="<td colspan=\"8\" width=\"100%\">";
					htmlPage +="<table width='100%' border='0' cellspacing='1' cellpadding='0' class='tab2' >";
					htmlPage +="<tr>";
						htmlPage +="<td>";
							htmlPage +="<table align='right'  style='font-size: 12px'>";
								htmlPage +="<tr>";
									htmlPage +="<td>";
									htmlPage +="共"+ result.page.totalCount +"记录";
									htmlPage +="<input type='hidden' name='startIndex' id='startIndex' value='0'>";
									htmlPage +="<input type='hidden' name='lastIndex' id='lastIndex' value="+ result.page.lastIndex + ">";
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
									htmlPage +=" <input type='text' style='width: 30px' name='page' id='page'  size='3' onkeyup=\"this.value=this.value.replace(/[^\\d]/g,'')\">";
									htmlPage +="&nbsp;<input type='button' value='GO' class='btn1' onclick=" + "goToPage(" + result.page.lastIndex + ")" + ">";
									htmlPage +=" 当前第" + result.page.currentPage + "/" + result.page.pageCount + "页";													
									htmlPage +="</td>";
								htmlPage +="</tr>";
							htmlPage +="</table>";
						htmlPage +="</td>";
					htmlPage +="</tr>";
				htmlPage +="</table>";
				htmlPage +="</td>";
			/* htmlPage +="</tr>"; */
			$("#tbodyuseTr").html(htmlPage);
			}		
		});
	}
	
  function goSearchBefor(){
	   document.getElementById("hsoftname").value="";
	   document.getElementById("hsysname").value="";
	   document.getElementById("hsoftdscription").value="";
	   document.getElementById("himdegree").value="3";
	   document.getElementById("huser").value="3";
	   goSearch(0);
	}

    </script>

<script type="text/javascript" src="../../scripts/js/sortupdatedata.js"></script>
</head>

<body style="margin: 0">
<input type="hidden" id="ShouDingId" value='<%=session.getAttribute("SSI_LOGIN_Status")%>'/>
<input type="hidden" id="flag" value="无"/>
	<table width="99.5%" border="0" cellspacing="0" cellpadding="0">
		<!-- 空行 -->
		<tr>
			<td height="2px"></td>
		</tr>
		<tr>
			<td>
				<div class=caozuobox>

		<s:if test='#session.SSI_LOGIN_Status=="1"'>
					</s:if>
					<s:else>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr height="30">
							<td width="1%"></td>
							<td width="20%" valign="middle" align="left"><input type="button" onclick="addEmployee()" class="btnadd" style="padding-top:3px;padding-left:10px; margin-top:3px" /> <input type="button" onclick="deleteuser()" class="btndel" style="padding-top:3px;padding-left:10px; margin-top:3px" /></td>
							<td width="8%" style=" padding-top:2px; padding-left:0px" valign="middle">修改状态:</td>
							<td width="7%" valign="middle"><select id="status" style="width: 60px;text-align: center;">
									<option selected="selected" value="0">正常</option>
									<option value="1">锁定</option>
									<option value="2">禁用</option>
							</select></td>
							<td width="12%" valign="middle"><input type="button" value="确定修改" class="btnstyle" onclick="changeStatus()" style="padding-left:10; background:url(${ctx}/images/btn_xgyh.jpg) no-repeat; border:none; width:84px; border:none;  height:21px;  cursor:pointer; " />
							</td>
							<td width="10%" valign="middle">快速搜索：</td>
							<td width="10%" valign="middle"><input type="text" class="showBorder" name="keyword" id="goToSearch" onblur="yanzheng1(this)"/>
							</td>
							<td width="39%" style="padding-left:15px" valign="middle"><img src="${ctx }/images/search.jpg" onclick="goSearchBefor();" class="hand" />
							<a href="#"	onclick="extQueryDlg();">高级</a>
							</td>
						</tr>
					</table>
					
					</s:else>
				</div> <!-- toolbar area --></td>
		</tr>
		<tr>
			<td>
				<div class="sbox">
					<div class="cont">
						<!-- information area -->
						<form action="${ctx}/employee/queryEmployee.action" method="post">
							<table width="100%" border="0" cellspacing="1" id="table-userManage" cellpadding="0" class="tab2">
								<thead>
									<tr height="28">
									<s:if test='#session.SSI_LOGIN_Status=="1"'>
										</s:if>
											<s:else>	
											<th width="5%" align="center" class="biaoti"><label> <input type="checkbox" id="chkAll" name="checkbox" value="checkbox" class="check-box not_checked" /> </label></th>
									</s:else>
										<th width="27%" align="center" class="biaoti" onclick="sortAble('table-userManage', 1)" style="cursor:pointer">用户名</th>
										<th width="17%" align="center" class="biaoti" onclick="sortAble('table-userManage', 2)" style="cursor:pointer">姓名</th>
										<th width="17%" align="center" class="biaoti" onclick="sortAble('table-userManage', 3)" style="cursor:pointer">身份证号</th>
										<th width="17%" align="center" class="biaoti" onclick="sortAble('table-userManage', 4)" style="cursor:pointer">用户权限</th>
										<th width="17%" align="center" class="biaoti" onclick="sortAble('table-userManage', 5)" style="cursor:pointer">状态</th>
									</tr>
								</thead>
								
								<tbody id="tbodyuse">
								</tbody>
								<tr id="tbodyuseTr"></tr>
							</table>

						</form>

					</div>
				</div>
			</td>
		</tr>
	</table>
<!-- ui-dialog -->
	<div id="dialog-extQuery" title="高级查询">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr>
				<td width="25%">用户登录名:</td>
				<td><input id="hsoftname" name="hsoftname" type="text" 
					style="width:250px;"
					onkeypress="if

(event.keyCode==13)extQuery();" /></td>
			</tr>
			<tr>
				<td width="25%">真实姓名:</td>
				<td><input id="hsysname" name="hsysname" type="text" onblur="yanzheng1(this)"
					style="width:250px;"
					onkeypress="if

(event.keyCode==13)extQuery();" /></td>
			</tr>
			<tr>
				<td width="25%">身份证号:</td>
				<td><input id="hsoftdscription" name="hsoftdscription" onblur="isCardNo(this)"
					type="text" style="width:250px;"
					onkeypress="if

(event.keyCode==13)extQuery();" /></td>
			</tr>
			<tr>
				<td width="25%">用户权限:</td>
				<td>
<select name="himdegree" id="himdegree" >
												<option value="3">
													请选择
												</option>
												<option value="0">
													系统管理员
												</option>
												<option value="1">
													安全管理员
												</option>
												<option value="2">
													超级管理员
												</option>
												
											</select></td>
			</tr>
			<tr>
				<td width="25%">用户状态:</td>
				<td>
<select name="huser" id="huser" >
												<option value="3">
													请选择
												</option>
												<option value="0">
													正常
												</option>
												<option value="1">
													锁定
												</option>
												<option value="2">
													禁用
												</option>
												
											</select></td>
			</tr>

		</table>
	</div>
</body>
</html>
