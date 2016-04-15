<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>关键数据类别</title>
        <meta http-equiv="Cache-Control" content="private"/>
         <LINK href="${ctx}/css/basic/css.css" type=text/css rel=stylesheet>
    
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
<script type="text/javascript">
jQuery(document).ready(function()
	{
		$("#dialog-extQuery").dialog({
			autoOpen : false,
			minWidth : 600,
			minHeight : 460,
			buttons : {
				"确定[Enter]" : function() {
					$('#keyword').val("");
					extQuery(0);
					$(this).dialog("close");
				},
				"取消[Esc]" : function() {
					$("#hdatatype").val("");$("#hsysname").val("");$("#hbusinesssoft").val("");$("#hbusinesssoft").val("");
					$("#himdegree").val("");
					$(this).dialog("close");
				}
			}
		});
		goSearch(0);
	});
	$("#chkAll").live("click", function(event) {
		if ($("#chkAll").hasClass('not_checked')) {
			$("#chkAll").removeClass('not_checked');
			$(".check-box").attr('checked', true);
		} else {
			$("#chkAll").addClass('not_checked');
			$(".check-box").attr('checked', false);
		}
	});
	
	function goSearchBefor(){
	   document.getElementById("hdatatype").value="";
	   document.getElementById("hsysname").value="";
	   document.getElementById("hbusinesssoft").value="";
	   document.getElementById("hdevdescription").value="";
	   document.getElementById("himdegree").value="";
	   goSearch(0);
	}
//模糊快速搜索
function goSearch(num){
	resetCheckBox();
	var keyword = $.trim($("#goToSearch").val());
	if(keyword!=""){
		flag.value="快速";//设置标志为高级搜索
    }
           var hdatatype=$.trim($("#hdatatype").val());
           var hsysname=$.trim($("#hsysname").val());
           var hbusinesssoft=$.trim($("#hbusinesssoft").val());
           var hdevdescription=$.trim($("#hdevdescription").val());
           var himdegree=$.trim($("#himdegree").val());
         if(hdatatype !="" || hsysname !="" || hbusinesssoft !="" || hdevdescription !="" || himdegree !="" ){
            flag.value="高级";//设置标志为高级搜索
         }
        
         if((keyword=="")&&(hdatatype == "" && hsysname ==""  && hbusinesssoft =="" && hdevdescription =="" && himdegree =="")){
             flag.value="无";
         }
        if(num ==0 && flag.value=="高级"){
               extQuery(num);
		       return ;
        }
	if(keyword != ''|| num !=''){
	        if(flag.value=="快速"){
		      jsonForAjax("${ctx}/dataAssets/queryAjaxDataAssets.action?startIndex="+encodeURI(num,"utf-8")+ "&keyword=" + encodeURI(encodeURI(keyword,"utf-8")) + "&t=" + new Date());
		       return ;
		    }
		    if(flag.value=="高级"){
		       extQuery(num);
		       return ;
		    }
		jsonForAjax("${ctx}/dataAssets/queryAjaxDataAssets.action?startIndex="+encodeURI(num,"utf-8")+ "&keyword=" + encodeURI(encodeURI(keyword,"utf-8")) + "&t=" + new Date());
	}else{
		jsonForAjax("${ctx}/dataAssets/queryAjaxDataAssets.action?t=" + new Date());
	}
  	<%--var msgKey = document.getElementById("goToSearch").value;
  	location.href="${ctx}/dataAssets/kquery.action?keyword="+msgKey;--%>
  
  }
  //高级搜索
	function extQuery(num) {
		var dataType = $.trim($("#hdatatype").val());
		var sysName = $.trim($("#hsysname").val());
		var softName = $.trim($("#hbusinesssoft").val());
		var dataDescription = $.trim($("#hdevdescription").val());
		var impDegree = $.trim($("#himdegree").val());
		if(dataType=="" && sysName=="" && softName==""  && dataDescription==""  && impDegree==""){
		  return;
		}
		document.getElementById("goToSearch").value="";//设置快速搜索为空
		document.getElementById("flag").value="高级";//设置标志为高级搜索
		jsonForAjax("${ctx}/dataAssets/extQuery.action?startIndex="
				+ encodeURI(num, "utf-8") + "&dataType="
				+ encodeURI(encodeURI(dataType, "utf-8")) +"&softName="
				+ encodeURI(encodeURI(softName, "utf-8")) + "&sysName="
				+ encodeURI(encodeURI(sysName, "utf-8")) + "&dataDescription="
				+ encodeURI(encodeURI(dataDescription, "utf-8"))
				+ "&impDegree=" + encodeURI(encodeURI(impDegree, "utf-8"))
				+ "&t=" + new Date());
		//$("#hdatatype").val("");$("#hsysname").val("");$("#hbusinesssoft").val("");$("#hbusinesssoft").val("");
		//$("#himdegree").val("");
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
		 	if(item.id != null){
		 		htmlStr = "";
		 		htmlStr += "<tr>";
		 		if(ShouDingvalue==1){
			 		}else{
		 			htmlStr += "<td align=\"center\">";
		 				htmlStr += "<input name=\"ids\" type=\"checkbox\" class=\"check-box\" value=\""+item.id+"\"/>";
		 			htmlStr += "</td>";
		 			}
		 			htmlStr += "<td  align=\"center\">";
		 			if(ShouDingvalue==1){
			 			 htmlStr +=item.dateType;
			 			}else{
		 			    htmlStr +="<a href=\"${ctx}/dataAssets/edit.action?id="+item.id+"\">"+item.dateType+"</a>";
		 			    }
		 			htmlStr += "</td>";
		 			htmlStr += "<td  align=\"center\">";
		 				htmlStr += item.sysName;
		 			htmlStr += "</td>";
		 			htmlStr += "<td  align=\"center\">";
		 				 htmlStr +=item.resName;
		 			htmlStr += "</td>";
		 			htmlStr += "<td  align=\"center\"><xmp>";
	 				 htmlStr +=item.devDescription;
	 			htmlStr += "</xmp></td>";
	 			htmlStr += "<td  align=\"center\">";
				 htmlStr +=item.impDegree;
			htmlStr += "</td>";
		 		htmlStr += "</tr>";
		 	}
		 	$("#tbodyuse").html(tBodyHtml+htmlStr);
		 });				
		if(result.page.startIndex != null){		
			$("#tbodyuseTr").html("");
		   /*  htmlPage += "<tr>"; */
		    	htmlPage +="<td colspan=\"8\" width=\"100%\">";
				htmlPage +="<table width='100%' style='font-size: 12px' border='0' cellspacing='1' cellpadding='0' class='tab2' >";
				htmlPage +="<tr>";
					htmlPage +="<td>";
						htmlPage +="<table align='right' style='font-size: 12px' >";
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
		/* htmlPage +="</tr>"; */
		$("#tbodyuseTr").html(htmlPage);
		}		
	});
}
  function extQueryDlg() {
		$("#dialog-extQuery").dialog("open");
	}
  function save()
  {
  	location.href="${ctx}/dataAssets/edit.action";
  }
  function deletes() {
    	var ids="";
    	$("[name=ids]:checkbox:checked").each(function(){  
			if(ids!="")   
			{
				ids+=","+$(this).val();   
			}
			else 
				ids=$(this).val(); 	
    	});  
    	if(ids=="")
    	{
    		alert("请至少选择一个关键数据，再执行删除");
    		return;
    	}
    	if(confirm("确认执行操作吗？")) {
    		location.href="${ctx}/dataAssets/delete.action?ids=" + ids ;
    	}
    }
</script>
  <script type="text/javascript" src="../../../scripts/js/sortupdatedata.js"></script>
  </head>
  
  <body style="margin: 0">
 <input type="hidden" id="ShouDingId" value='<%=session.getAttribute("SSI_LOGIN_Status")%>'/>
 <input type="hidden" id="flag" value="无"/>
  <s:form  name="dataAssets" method="post" action="query" namespace="/dataAssets" theme="simple">
  <table width="99.5%" border="0" cellspacing="0" cellpadding="0"  >
		<!-- 空行 -->
		<tr>
			<td height="2px">
			</td>
		</tr>	
	  	<tr>
	  		<td>
               <div class=caozuobox>
               <s:if test='#session.SSI_LOGIN_Status=="1"'>
					</s:if>
					<s:else>
                   <table width="100%" border="0" cellspacing="0" cellpadding="0" style="font-size:12px;">
                      	<tr height="30">
								<td width="1%"></td>
                       <td valign="middle" align="left">快速搜索&nbsp;<input
									type="text" class="showBorder" id="goToSearch"
									name="goToSearch"
									onblur="yanzheng1(this)"
									> <img
									src="${ctx}/images/search.jpg" onclick="goSearchBefor();"
									class="hand" />
									<a href="#"
									onclick="extQueryDlg();">高级</a>
								</td>
                       
                      
                       <td width="20%" valign="middle" align="right" >
                       
                       <input type="button" value="添加" class="btnstyle"
								style="margin-right:5px;"
								onclick="save();" />

							<input type="button" value="删除"
								style="margin-right:12px;" class="btnstyle"
								onclick="deletes();" />
                            
                       </td>
                             
                      </tr>
                   </table>
                   </s:else>
	         </div>
	 	<!-- toolbar area -->
	  	</td>
	 </tr>
	 	<tr>
				<td height="3px"></td>
			</tr>
	  	<tr>
	  		<td>
	  			<div class="sbox">   
  				 <div class="cont">
	  			<!-- information area -->
                  <table width="100%" border="0" cellspacing="1" id="table-userManage" cellpadding="0" class="tab2">
  <thead>
  <tr>
  <s:if test='#session.SSI_LOGIN_Status=="1"'>
										</s:if>
											<s:else>
    <th width="5%" align="center" class="biaoti"> 
       <input name="chkAll" id="chkAll"type="checkbox" class="check-box not_checked" />
    </th>
    		</s:else>
    <th width="20%" align="center" class="biaoti" onclick="sortAble('table-userManage', 1)" style="cursor:pointer;font-size: 12px">数据类别</th>
    <th width="15%" align="center" class="biaoti" onclick="sortAble('table-userManage', 2)" style="cursor:pointer;font-size: 12px">所属系统名称</th>
    <th width="15%" align="center" class="biaoti" onclick="sortAble('table-userManage', 3)" style="cursor:pointer;font-size: 12px">业务应用软件</th>
    <th width="36%" align="center" class="biaoti" style="font-size: 12px">关键数据类别描述</th>
    <th width="10%" align="center" class="biaoti" onclick="sortAble('table-userManage', 5)" style="cursor:pointer;font-size: 12px">重要程度</th>
  </tr>
  </thead>
  <tbody id="tbodyuse">
	</tbody>
	<tr id="tbodyuseTr"></tr>
</table>
</div></div></td></tr></table>
	</s:form>
	<!-- ui-dialog -->
	<div id="dialog-extQuery" title="关键数据类别高级查询" style="font-size: 12px">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px; font-size:12px;">
			<tr>
				<td width="25%" style="font-size: 12px" >数据类别:</td>
				<td><input id="hdatatype" name="hdatatype" type="text"
					onblur="yanzheng1(this)"
					style="width:250px;font-size: 12px;"
					onkeypress="if
(event.keyCode==13)extQuery();" /></td>
			</tr>
			<tr>
				<td width="25%" style="font-size: 12px" >所属系统名称:</td>
				<td><input id="hsysname" name="hsysname" type="text"
					onblur="yanzheng1(this)"
					style="width:250px;font-size: 12px;"
					onkeypress="if
(event.keyCode==13)extQuery();" /></td>
			</tr>
			<tr>
				<td width="25%" style="font-size: 12px" >业务应用软件:</td>
				<td><input id="hbusinesssoft" name="hbusinesssoft"
					onblur="yanzheng1(this)"
					type="text" style="width:250px;font-size: 12px;"
					onkeypress="if
(event.keyCode==13)extQuery();" /></td>
			</tr>
			<tr>
				<td width="25%" style="font-size: 12px" >关键数据描述:</td>
				<td><input id="hdevdescription" name="hdevdescription" type="text"
					onblur="yanzheng1(this)"
					style="width:250px;font-size: 12px;"
					onkeypress="if
(event.keyCode==13)extQuery();" /></td>
			</tr>
			<tr>
				<td width="25%" style="font-size: 12px" >重要程度:</td>
				<td><input id="himdegree" name="himdegree" type="text"
					onblur="yanzheng1(this)"
					style="width:250px;font-size: 12px;"
					onkeypress="if
(event.keyCode==13)extQuery();" /></td>
			</tr>
		</table>
	</div>
  </body>
</html>
