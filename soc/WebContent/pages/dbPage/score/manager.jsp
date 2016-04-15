<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>系统定级主界面</title>
<LINK href="${ctx}/css/basic/css.css" type=text/css rel=stylesheet>
<LINK href="${ctx}/css/style/jquery-ui.custom.css" type=text/css rel=stylesheet>
<LINK href="${ctx}/css/style/jquery.popuplayer.css" type=text/css rel=stylesheet>

<script type="text/javascript" src="${ctx}/scripts/sorttalbe.js"></script>
<script type="text/javascript" src="${ctx}/scripts/check.js"></script>

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
<script type="text/javascript">
	jQuery(document).ready(function()
	{
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
	
	});
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
	
	function goSearchBefor(){
	   document.getElementById("hsysname").value="";
	   document.getElementById("hsysid").value="";
	   document.getElementById("hranklevel").value="0";
	   document.getElementById("hstatus").value="0";
	   goSearch(0);
	}
      //快速搜索
     function goSearch(num){

		var keyword = $.trim($("#goToSearch").val());
		if(keyword.length>50){
			alert("搜索长度不能大于50");
			$('#goToSearch').val('');
			$('#goToSearch').focus();
			return ;
		}
		var flag=document.getElementById("flag");
		
		 if(keyword!=""){
		    flag.value="快速";//设置标志为高级搜索
         }
           var hsysname=$.trim($("#hsysname").val());
           var hsysid=$.trim($("#hsysid").val());
           var hranklevel=$.trim($("#hranklevel").val());
           var hstatus=$.trim($("#hstatus").val());
         if(hsysname !="" || hsysid !="" || hranklevel !="0" || hstatus !="0"){
            flag.value="高级";//设置标志为高级搜索
         }
        
         if((keyword=="")&&(hsysname == "" && hsysid ==""  && hranklevel =="0"  && hstatus=="0")){
             flag.value="无";
         }
        if(num ==0 && flag.value=="高级"){
               extQuery(num);
		       return ;
        }
		if( num !="" || keyword !="" ){
		    if(flag.value=="快速"){
		       jsonForAjax("${ctx}/rank/queryAjaxRank.action?startIndex="+encodeURI(num,"utf-8")+ "&keyword=" + encodeURI(encodeURI(keyword,"utf-8")) + "&t=" + new Date());
		       return ;
		    }
		    if(flag.value=="高级"){
		       extQuery(num);
		       return ;
		    }
		     jsonForAjax("${ctx}/rank/queryAjaxRank.action?startIndex="+encodeURI(num,"utf-8")+ "&keyword=" + encodeURI(encodeURI(keyword,"utf-8")) + "&t=" + new Date());
		}else{
			jsonForAjax("${ctx}/rank/queryAjaxRank.action?t=" + new Date());
		}	
	}
	
		/* 数据分页 */
	function jsonForAjax(url){
		var htmlStr = "";	
		var htmlPage = "";
		var ShouDingvalue = document.getElementById("ShouDingId").value;
		$.getJSON(url ,function(result){
			
			$("#tbody").html("");
			$.each(result.processLog, function(i,item) {
			 
			 	var tBodyHtml = $("#tbody").html();
			 	if(item.rankId != null){
			 		htmlStr = "";
			 		htmlStr += "<tr>";
			 			
			 			 htmlStr += "<td  align=\"center\">";
			 			 if(item.rankGrade!=""){
			 				 if($("#loginRoleAjax").val() == 'true'){
			 					 if(ShouDingvalue==1){
			 					 htmlStr +=item.sysInfoName;
			 						}else{
			 					htmlStr +="<a href=\"rankAction_updateRankInfo.action?rankId="+item.rankId+"\" target=\"mainFrame\" >"+item.sysInfoName+"</a>";						
			 						}
			 				 }else{
			 					htmlStr +="<a href=\"rankAction_updateRankInfo.action?rankId="+item.rankId+"\" target=\"mainFrame\" >"+item.sysInfoName+"</a>";						
			 				 }
			 			}else{
			 				htmlStr +=item.sysInfoName;	
			 			}
			 			htmlStr += "</td>";	
			 			htmlStr += "<td  align=\"center\">";
			 				htmlStr += item.sysInfoId;
			 			htmlStr += "</td>";	
			 			htmlStr += "<td  align=\"center\">";
			 				htmlStr += item.sysInfoBusDescription;
			 															
			 			htmlStr += "</td>";	
			 			htmlStr += "<td  align=\"center\">";
			 				htmlStr += item.rankGrade;
			 			htmlStr += "</td>";	
			 			
			 			htmlStr += "<td  align=\"center\">";
			 			if(item.rankGrade!=""){
			 			if(ShouDingvalue==1){
			 					 htmlStr +="查看";
			 						}else{
			 			htmlStr +="<a href=\"${ctx}/rank/rankAction_show.action?rankId="+item.rankId+" \">查看</a>";
			 						}
			 			}else{
			 			htmlStr += "查看";
			 			}
			 			htmlStr += "</td>";		 
			 			htmlStr += "<td  align=\"center\">";
			 			if(item.rankGrade!=""){
			 			htmlStr +="已定级";
			 			}else{
			 			
			 					if(ShouDingvalue==1){
			 					 htmlStr +="开始定级";
			 						}else{
			 			htmlStr += "<a style=\"color: red;cursor: pointer;cursor: hand;\"  onclick=\"goRank('"+item.rankId+"')\" class=\"hand\">开始定级</a>";
			 							}
			 			}
			 			htmlStr += "</td>";		 
			 		htmlStr += "</tr>";
			 	}
			 	//$(htmlStr).insertAfter($("#table-userManage tr:eq("+rowNum+")"));
			 	$("#tbody").html(tBodyHtml+htmlStr);
			 });				
			if(result.page.startIndex != null){		
				//rowNum =  $("#table-userManage tr").size()-1;
			    	htmlPage +="<td colspan=\"8\" width=\"100%\">";
					htmlPage +="<table width='100%' border='0' cellspacing='1' cellpadding='0' class='tab2' >";
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
									htmlPage +=" <input type='text' style='width: 30px' name='page' id='page'   size='3' onkeyup=\"this.value=this.value.replace(/[^\\d]/g,'')\">";
      
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
			//$(htmlPage).insertAfter($("#table-userManage tr:eq("+rowNum+")"));
			$("#fenye").html(htmlPage);	
			}		
		});
		

	}
  
  // 通用函数获取页面上选中的radio的值
  function GetRadioValue(RadioName){
    var obj;    
    obj=document.getElementsByName(RadioName);
    if(obj!=null){
        var i;
        for(i=0;i<obj.length;i++){
            if(obj[i].checked){
                return obj[i].value;            
            }
        }
    }
    return null;
}
//高级搜索
	function extQuery(num) {
		var sysname = $.trim($("#hsysname").val());
		var sysid = $.trim($("#hsysid").val());
		var ranklevel = $.trim($("#hranklevel").val());
		var rankstatus = $.trim($("#hstatus").val());
		 if(sysname.length>50){
			alert("搜索长度不能大于50");
			$('#hsysname').val('');
			$('#hsysname').focus();
			return ;
		}
		 if(sysid.length>50){
			alert("搜索长度不能大于50");
			$('#hsysid').val('');
			$('#hsysid').focus();
			return ;
		}
		if(sysname=="" && sysid=="" && ranklevel=="0" && rankstatus=="0"){
		  return;
		}
		document.getElementById("goToSearch").value="";//设置快速搜索为空
		document.getElementById("flag").value="高级";//设置标志为高级搜索
		jsonForAjax("${ctx}/rank/precisequeryrank.action?startIndex="
				+ encodeURI(num, "utf-8") + "&sysname="
				+ encodeURI(encodeURI(sysname, "utf-8")) + "&sysid="
				+ encodeURI(encodeURI(sysid, "utf-8")) + "&ranklevel="
				+ encodeURI(encodeURI(ranklevel, "utf-8"))
				+ "&rankstatus=" + encodeURI(encodeURI(rankstatus, "utf-8"))
				+ "&t=" + new Date());
				
		
	}
  function extQueryDlg() {
		$("#dialog-extQuery").dialog("open");
	}
  function goRank(radioValue)
  {
    if(radioValue!=null)
    {
    location.href="${ctx}/rank/rankAction_updateInfo.action?rankId="+radioValue;
    }
    else
    {
      alert("请选择要进行定级的系统,如果要修改定级请单击要修改的系统名称！");
    }
  }
/* 下拉列表联动 */
 function gdMessage(){
	var level=document.getElementById("hranklevel");
	if(level.value!="0"){
		var status=document.getElementById("hstatus");
		//status.
	}
}  
  </script>
</head>

<body  style="position: 0;margin: 0;font-size:12px;" >
<input type="hidden" id="ShouDingId" value='<%=session.getAttribute("SSI_LOGIN_Status")%>'/>
<input type="hidden" id="flag" value="无"/>

	<form action="${ctx}/rank/queryRank.action" method="post">
		<table width="99.5%" border="0" cellspacing="0" cellpadding="0"  >
			<!-- 空行 -->
			<tr >
				<td >
					<div class="caozuobox" style="height:100%; font-size:12px">
					<s:if test='#session.SSI_LOGIN_Status=="1"'>
					</s:if>
					<s:else>
						<input type="hidden" name="file" id="file"> 
						<span class="kuaiju">&nbsp;快速搜索:&nbsp;&nbsp;
						<input type="text" id="goToSearch" name="keyword" onblur="yanzheng1(this)" onkeyup="value=value.replace(/[^\w\.\/\u4E00-\u9FA5]/ig,'')"> </span> 
						<img src="${ctx}/images/search.jpg" onclick="goSearchBefor()" style="margin-left: 5px; cursor: pointer;vertical-align: middle;" />
						<a href="#"	onclick="extQueryDlg();">高级</a> 
					</s:else>
					</div>
				
				</td>
			</tr>
			<tr>
			<td height="5px;"></td>
			</tr>
			<tr style="width: 100%">
				<td>
					<div class="sbox">
						<div class="cont">
							<!-- information area -->
							<div id="dataList">
								<table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2" id="table-userManage">
								<thead>
									<tr>				 
										<th width="22%" class="biaoti" onclick="sortAble('table-userManage', 0)"  style="cursor:pointer;font-size: 12px">系统名称</th>
										<th width="22%" class="biaoti" onclick="sortAble('table-userManage', 1)"  style="cursor:pointer;font-size: 12px" >系统编号</th>
										<th width="22%" class="biaoti" onclick="sortAble('table-userManage', 2)"  style="cursor:pointer;font-size: 12px">业务描述</th>
										<th width="8%" class="biaoti"  onclick="sortAble('table-userManage', 3)"  style="cursor:pointer;font-size: 12px">当前等级</th>
										<th width="8%" class="biaoti"  onclick="sortAble('table-userManage', 4)"  style="cursor:pointer;font-size: 12px">详细信息</th>
										<th width="8%" class="biaoti"  onclick="sortAble('table-userManage', 5)"  style="cursor:pointer;font-size: 12px">定级状态</th>
									</tr>
									</thead>
									<tbody  id="tbody">
										<s:iterator value="ranks" id="rank">
											<tr>
												<td align="center">
												
												<s:if test="#rank.rankGrade!=null">
												
														<s:if test='#session.SSI_LOGIN_Status=="1"'>
															<s:property value="#rank.sysInfoName" /> 
														</s:if>
															<s:else>
															<a href="${ctx}/rank/rankAction_updateRankInfo.action?rankId=${rankId}" target="mainFrame"><s:property value="#rank.sysInfoName" /> </a>
															</s:else>
												</s:if>
												<s:else>
															<s:property value="#rank.sysInfoName" />
												</s:else>
												</td>
												<td align="center"><s:property value="#rank.sysInfoId" />
												</td>
												<td align="center"><s:property value="#rank.sysInfoBusDescription" />

												</td>
												<td align="center"><s:property value="#rank.rankGrade" />
												</td>
												<td align="center">
												<s:if test="#rank.rankGrade!=null">
													<s:if test='#session.SSI_LOGIN_Status=="1"'>
														查看
														</s:if>
														<s:else>
														<a href="${ctx}/rank/rankAction_show.action?rankId=${rankId}">查看</a>
														</s:else>
													</s:if>
													<s:else>
													      查看
													</s:else>
												</td>
												<td align="center">
													<s:if test="#rank.rankGrade!=null">
														已定级  
													</s:if>
													 <s:else>
													<s:if test='#session.SSI_LOGIN_Status=="1"'>
														开始定级
														</s:if>
															<s:else>
													      <a style="color:red;cursor:pointer;"  onclick="goRank(<s:property value='#rank.rankId'/>)" class="hand">开始定级</a>
															</s:else>
													</s:else>
												</td>
											</tr>
										</s:iterator>
										
									</tbody>
									<tr id="fenye">
										<td colspan="7" width="100%"><jsp:include
												page="../commons/page.jsp"></jsp:include></td>
									</tr>
								</table>
							</div>

						</div>
					</div>
				</td>
			</tr>
		</table>
	</form>
	<!-- ui-dialog -->
	<div id="dialog-extQuery" title="定级信息高级查询">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px; font-size:12px;">
			<tr>
				<td width="25%">系统名称:</td>
				<td><input id="hsysname" name="hsysname" type="text"
					style="width:250px;"
					onkeyup="value=value.replace(/[^\w\.\/\u4E00-\u9FA5]/ig,'')"
					onkeypress="if

(event.keyCode==13)extQuery();" /></td>
			</tr>
			<tr>
				<td width="25%">系统编号：</td>
				<td><input id="hsysid" name="hsysid" type="text"
					style="width:250px;"
					onkeyup="value=value.replace(/[^\w\.\/\u4E00-\u9FA5]/ig,'')"
					onkeypress="if(event.keyCode==13)extQuery();" /></td>
			</tr>
			<tr>
				<td width="25%">当前等级：</td>
				<td><select name="hranklevel" id="hranklevel" onchange="gdMessage()" >
											<option value="0">
													请选择
												</option>
												<option value="第二级">
													第二级
												</option>
												<option value="第三级">
													第三级
												</option>
												<option value="第四级">
													第四级
												</option> 
												
											</select></td>
			</tr>
			<tr>
				<td width="25%">定级状态:</td>
				<td>
<select name="hstatus" id="hstatus">
<option value="0">请选择</option>
	<option value="1">已定级</option>
	<option value="2">未定级</option>
</select></td>
			</tr>

		</table>
	</div>
</body>
</html>
