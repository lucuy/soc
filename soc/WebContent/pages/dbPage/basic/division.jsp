<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>组织部门信息</title>
    <link href="${ctx}/styles/pageCss.css" rel="stylesheet" type="text/css">
     <LINK href="${ctx}/css/basic/css.css" type=text/css rel=stylesheet>
	<link rel="stylesheet" type=text/css href="${ctx}/css/dtree.css" /> 
	<link rel="stylesheet" href="${ctx}/css/basic/css.css" type="text/css"></link>
	<script type="text/javascript" src="${ctx}/scripts/dtree.js" ></script>
	<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		function queryTree(){
			d = new dTree('d');
			$.getJSON("${ctx}/division/query.action" ,function(json){
				if(json!=0){
					$.each(json, function(i,item) {
						if(item.parentId == 0){
							d.add(0,-1,item.depName ,"javascript:queryInfo("+item.id+")",'','','','',true);
						}
						if(item.parentId != 0){
							d.add(i,XNodeTree(json,item.parentId),item.depName ,"javascript:queryInfo("+item.id+")",'','','','',true);
						}
					});
					$("#td-dtree").html("");
					$("#td-dtree").html(d.toString());
				}else{
					$("#td-dtree").html("");
				}
			});
		}
		
		function queryRoot(){
			$.getJSON("${ctx}/division/query.action" ,function(json){
				if(json!=0){
					$.each(json, function(i,item) {
						if(item.parentId == 0){
							queryInfo(item.id);
						}
					});
				}else{
					var html="";
					html = "<table width=\"100%\" border=\"1\" bordercolor=\"#5AA4D1\" cellpadding=\"0\" cellspacing=\"0\">";
						html += "<tr  height=\"25px\" >";
							html += "<td colspan=\"2\"  >"; 
								html += "<input type=\"button\" value=\"添加根部门\" style=\" background:url(${ctx}/images/btn_tjyh1.jpg) no-repeat;  border:none; height:21px; cursor:pointer; color:#265D86;text-align:center; width:100px;\"  onclick=\"addInfo("+ json.id +")\"/>";
								html +="&nbsp";
								html +=	"<input type=\"button\" class=\"btnstyle\"  value=\"返回\" tyle=\" background:url(${ctx}/images/btnbgcancel.jpg) no-repeat;  border:none; height:22px; cursor:pointer; color:#265D86;align:right;   width:80px;\" />";
								html += "<input type=\"hidden\" id=\"json-parentId\" value=\"0\" />";
							html +=	"</td>";
						html += "</tr>";
						html +=	"<tr>";
							html +=	"<td width=\"20%\" align=\"right\">部门名称:</td><td><input type=\"text\" id=\"json-depName\" on  style=\"width:99%; \"/></td>";
						html +=	"</tr>";
						html +=	"<tr>";
							html +=	"<td   align=\"right\">部门安全员:</td><td><input type=\"text\" id=\"json-depEmp\" style=\"width:99%\"/></td>";
						html +=	"</tr>";
						html +=	"<tr>";
							html +=	"<td   align=\"right\">部门安全领导:</td><td><input type=\"text\" id=\"json-depHead\" style=\"width:99%\"/></td>";
						html +=	"</tr>";
						html +=	"<tr>";
							html +=	"<td  align=\"right\">部门描述:</td><td><textarea rows=\"4\"  id=\"json-depDescription\" style=\"width:99%\"></textarea></td>";
						html +=	"</tr>";
					html +=	"</table>";
					$("#td-info").html("");
					$("#td-info").html(html);	
				}
			});
		}
		
		function XNodeTree(json,  parentId){
			var index = 0;
			var flg = false;
			$.each(json, function(j,itemj) {
				if(parentId == itemj.id){
					flg = true;
					index = j;
				}
			});
			if(flg){
				return index;
			}
		}
		
		function queryInfo(id){
			$.getJSON("${ctx}/division/queryInfo.action?id="+id ,function(json){
				var html="";
				html += "<table width=\"100%\" border=\"1\" bordercolor=\"#5AA4D1\" cellpadding=\"0\" cellspacing=\"0\">";
					html += "<tr  height=\"25px\">";
						html += "<td colspan=\"2\" >";
						html += "<input type=\"button\"   style=\" background:url(${ctx}/images/btnEdit.jpg) no-repeat;  border:none; height:21px; cursor:pointer; color:#265D86;align:right; text-align:right; width:60px;\"  onclick=\"editInfo("+ json.id+")\"/>";
							html +="&nbsp";
							html +=	"<input type=\"button\"   style=\" background:url(${ctx}/images/btnDel.jpg) no-repeat;  border:none; height:21px; cursor:pointer; color:#265D86;align:right; text-align:right; width:60px;\"  onclick=\"deleteInfo("+ json.id+",'"+json.depName+"')\" />";
							html +="&nbsp";
							html +=	"<input type=\"button\" value=\"添加子部门 \" style=\" background:url(${ctx}/images/btn_tjyh1.jpg) no-repeat;  border:none; height:21px; cursor:pointer; color:#265D86; text-align:center; width:100px;\"   onclick=\"addCNodeInfo("+ json.id+",false)\"/>"; 
							if(json.parentId!=0)html +=	"&nbsp;<input type=\"button\" value=\"添加同级\" style=\" background:url(${ctx}/images/btn_tjyh1.jpg) no-repeat;  border:none; height:21px; cursor:pointer; color:#265D86; text-align:center; width:100px;\"  onclick=\"addCNodeInfo("+ json.id+",true)\"/>";
						html +=	"</td>";
					html += "</tr>";
					html +=	"<tr>";
						html +=	"<td width=\"25%\" align=\"right\">部门名称:</td><td>"+ json.depName +"&ensp;"+"</td>";
					html +=	"</tr>";
					html +=	"<tr>";
						html +=	"<td width=\"25%\" align=\"right\">部门安全员:</td><td>" + json.depEmp + "&ensp;"+"</td>";
					html +=	"</tr>";
					html +=	"<tr>";
						html +=	"<td width=\"25%\" align=\"right\">部门安全领导:</td><td>" + json.depHead + "&ensp;"+"</td>";
					html +=	"</tr>";
					html +=	"<tr>";
						html +=	"<td width=\"25%\" align=\"right\">部门描述:</td><td>"+ json.depDescription +"&ensp;"+"</td>";
					html +=	"</tr>";
				html +=	"</table>";
				$("#td-info").html("");
				$("#td-info").html(html);
			});
		}
		
		function depNameCheck1(){
		   document.getElementById("addSacve").disabled = true;
		}
		
		function depNameCheck2(){
		  var depName=document.getElementById("json-depName");
		  if($.trim(depName.value)!=""){
		               $.post("${ctx}/division/depNameCheck.action", {depName:depName.value} ,function(json){
		                  if(json=="1"){
		                    alert("此部门已经存在！");
		                    depName.value="";
		                    return;
		                  }else{
		                   document.getElementById("addSacve").disabled = "";
		                  }
					   });
		  }   
		}
		
		var primaryDepName="";
		function depNameCheck3(depNameId){
		  document.getElementById("editSacve").disabled = true;
          primaryDepName=document.getElementById(depNameId).value;
		}
		
		function depNameCheck4(depNameId){
		 var editSacve=document.getElementById("editSacve");
          var depName=$.trim($("#"+depNameId).val());
          if(primaryDepName==depName){
           editSacve.disabled = "";
          	return;          
          }
		  if(depName!=""){
		               $.post("${ctx}/division/depNameCheck.action", {depName:depName} ,function(json){
		                  if(json=="1"){
		                    alert("此部门已经存在！");
		                    document.getElementById(depNameId).value="";
		                    return;
		                  }else{
		                   editSacve.disabled = "";
		                  }
					   });
		  }   
		}
		
		
		//编辑部门信息  
		function editInfo(id){
			$.getJSON("${ctx}/division/queryInfo.action?id="+id ,function(json){
				var html="";
				html += "<table width=\"100%\" border=\"1\" bordercolor=\"#5AA4D1\" cellpadding=\"0\" cellspacing=\"0\" >";
					html += "<tr height=\"25px\">";
						html += "<td colspan=\"2\">";
							html += "<input type=\"button\" value=\"保存\" id=\"editSacve\"   style=\" background:url(${ctx}/images/btnbgsave.jpg) no-repeat;  border:none; height:22px; cursor:pointer; color:#265D86;text-align:center; width:80px;\" onclick=\"updateInfo("+ json.id+")\"/>";
							html +=	"<input type=\"button\"  value=\"取消\"  style=\" background:url(${ctx}/images/btnbgcancel.jpg) no-repeat;  border:none; height:22px; cursor:pointer; color:#265D86;text-align:center;width:80px;\" onclick=\"queryInfo("+ json.id+")\"/>";
							html += "<input type=\"hidden\" id=\"json-id-"+ json.id +"\" value=\"" + json.id + "\" />";
							html += "<input type=\"hidden\" id=\"json-parentId-"+ json.id +"\" value=\"" + json.parentId + "\" />";
							html += "<input type=\"hidden\" id=\"json-parentDepEmp-"+ json.id +"\" value=\"" + json.parentDepEmp + "\" />";
							html += "<input type=\"hidden\" id=\"json-parentDepHead-"+ json.id +"\" value=\"" + json.parentDepHead + "\" />";
						html +=	"</td>";
					html += "</tr>";
					html +=	"<tr>";
						html +=	"<td width=\"25%\" align=\"right\">部门名称:</td><td><input type=\"text\" id=\"json-depName-"+ json.id +"\" value=\""+ json.depName +"\"  onfocus=\"depNameCheck3('"+"json-depName-"+json.id+"')\"  onblur=\"depNameCheck4('"+"json-depName-"+json.id+"')\" style=\"width:99%\"  /></td>";
					html +=	"</tr>";
					html +=	"<tr>";
						html +=	"<td width=\"25%\" align=\"right\">部门安全员:</td><td><input type=\"text\" id=\"json-depEmp-"+ json.id +"\" value=\"" + json.depEmp + "\" style=\"width:99%\"/></td>";
					html +=	"</tr>";
					html +=	"<tr>";
						html +=	"<td width=\"25%\" align=\"right\">部门安全领导:</td><td><input type=\"text\" id=\"json-depHead-"+ json.id +"\" value=\"" + json.depHead + "\" style=\"width:99%\"/></td>";
					html +=	"</tr>";
					html +=	"<tr>";
						html +=	"<td width=\"25%\" align=\"right\">部门描述:</td><td><textarea id=\"json-depDescription-"+ json.id +"\"  style=\"width:99%\">"+ json.depDescription +"</textarea></td>";
					html +=	"</tr>";                                                
				html +=	"</table>";
				$("#td-info").html("");
				$("#td-info").html(html);
			});
		}
		
		//点击添加子组织部门   
		function addCNodeInfo(id,flg){
			$.getJSON("${ctx}/division/queryInfo.action?id="+id ,function(json){	
				var html="";
				html = "<table width=\"100%\" border=\"1\" bordercolor=\"#5AA4D1\" cellpadding=\"0\" cellspacing=\"0\" >";
					html += "<tr height=\"25px\">";
						html += "<td colspan=\"2\" >";
						html += "<input type=\"button\" value=\"保存\"  id=\"addSacve\" style=\" background:url(${ctx}/images/btnbgsave.jpg) no-repeat;  border:none; height:22px; cursor:pointer; color:#265D86; text-align:center; width:80px;\"  onclick=\"addInfo("+ json.id +")\"/>";
						html +=	"<input type=\"button\" value=\"取消\" style=\" background:url(${ctx}/images/btnbgcancel.jpg) no-repeat;  border:none; height:22px; cursor:pointer; color:#265D86;text-align:center;   width:80px;\"  onclick=\"queryInfo("+ json.id +")\"/>";
							if(flg){
								html += "<input type=\"hidden\" id=\"json-parentId\" value=\"" + json.parentId +"\" />";
							}else{
								html += "<input type=\"hidden\" id=\"json-parentId\" value=\"" + json.id +"\" />";
							}
						html +=	"</td>";
					html += "</tr>";
					html +=	"<tr>";
						html +=	"<td width=\"25%\" align=\"right\">部门名称:</td><td><input type=\"text\" id=\"json-depName\" onfocus=\"depNameCheck1()\"  onblur=\"depNameCheck2()\" style=\"width:99%\"/></td>";
					html +=	"</tr>";
					html +=	"<tr>";
						html +=	"<td width=\"25%\" align=\"right\">部门安全员:</td><td><input type=\"text\" id=\"json-depEmp\" style=\"width:99%\"/></td>";
					html +=	"</tr>";
					html +=	"<tr>";
						html +=	"<td width=\"25%\" align=\"right\">部门安全领导:</td><td><input type=\"text\" id=\"json-depHead\" style=\"width:99%\"/></td>";
					html +=	"</tr>";
					html +=	"<tr>";
						html +=	"<td width=\"25%\" align=\"right\">上级部门安全员:</td><td id=\"json-parentDepEmp\">"+json.depEmp+"</td>";
					html +=	"</tr>";
					html +=	"<tr>";
						html +=	"<td width=\"25%\" align=\"right\">上级部门领导:</td><td id=\"json-parentDepHead\">"+json.depHead+"</td>";
					html +=	"</tr>";
					html +=	"<tr>";
						html +=	"<td width=\"25%\" align=\"right\">部门描述:</td><td><textarea id=\"json-depDescription\" style=\"width:99%\"></textarea></td>";
					html +=	"</tr>";
				html +=	"</table>";
				$("#td-info").html("");
				$("#td-info").html(html);
			});
		}
		
		//添加组织部门信息
		function addInfo(id){
			var depName = $.trim($("#json-depName").val());
			var depEmp = $.trim($("#json-depEmp").val());
			var depHead = $.trim($("#json-depHead").val());
			var depDescription = $.trim($("#json-depDescription").val());
			var parentId = $.trim($("#json-parentId").val());
			var parentDepEmp = $.trim($("#json-parentDepEmp").text());
			var parentDepHead = $.trim($("#json-parentDepHead").text());
			if(depName!="" && depEmp !="" && depHead!=""){
				$.post("${ctx}/division/addInfo.action",{depName:depName,depEmp:depEmp,depHead:depHead,depDescription:depDescription,parentId:parentId,parentDepEmp:parentDepEmp,parentDepHead:parentDepHead} ,function(json){
					if(json != "ERROR"){
						queryTree();
						queryInfo(json);
					}
				});
			}else{
			    if(depName==""){
			       alert("部门名称不能为空！");
			       $("#json-depName").focus;
			       return;
			    }
			    if(depEmp==""){
			       alert("部门安全员不能为空！");
			       $("#json-depEmp").focus;
			       return;
			    }
			    if(depHead==""){
			       alert("部门安全领导不能为空！");
			       $("#json-depHead").focus;
			       return;
			    }
				
			}
		}
		
		//更改组织部门信息
		function updateInfo(id){
			var id = $.trim($("#json-id-"+id).val());
			var depName = $.trim($("#json-depName-"+id).val());
			var depEmp = $.trim($("#json-depEmp-"+id).val());
			var depHead = $.trim($("#json-depHead-"+id).val());
			var depDescription = $.trim($("#json-depDescription-"+id).val());
			var parentId = $.trim($("#json-parentId-"+id).val());
			var parentDepEmp = $.trim($("#json-parentDepEmp-"+id).val());
			var parentDepHead = $.trim($("#json-parentDepHead-"+id).val());
			if(depName!=""){
				$.post("${ctx}/division/updateInfo.action",{id:id,depName:depName,depEmp:depEmp,depHead:depHead,depDescription:depDescription,parentId:parentId,parentDepEmp:parentDepEmp,parentDepHead:parentDepHead} ,function(json){
					if(json == "SUCCESS"){
						queryTree();
						queryInfo(id);
					}
				});
			}else{
				alert("部门名称不能为空！");
			}
		}
		
		//删除组织部门信息
		function deleteInfo(id,depName){
		  if(confirm("确认执行操作吗？")){
			$.post("${ctx}/division/deleteCheck.action", {id:id,depName:depName} ,function(json){
			        if(json== "3"){
				        alert("父部门禁止直接删除！");
			          	return;
			        }else{
			          if(json== "1"){
			            $.post("${ctx}/division/deleteInfo.action", {id:id} ,function(json){
						  queryTree();
						  queryRoot();
					   });
					   return;
			          }else{
			          	alert("该部门有成员存在，禁止删除！");
			          	return;
			          }
			          
			        } 
					
				});
				
			} 
		}
	</script>
</head>
<body >
	<div id="td-info" style="width:560px; float: left; margin: 0px; padding: 0px; ">
	</div>
	<div id="td-dtree" style="float: left; margin: 0px; padding: 0px;">
		<table>
					<tr>
						<td>
							<div class="dtree" style="float: left; padding: 0px; margin: 0px;">
								  
								<p><a href="javascript: d.openAll();">open all</a> | <a href="javascript: d.closeAll();">close all</a></p>
								
								<script type="text/javascript">
									queryTree();
									queryRoot();
								</script>
							</div>
						</td>
					</tr>
					
				</table>
	</div>

</body>
</html>

