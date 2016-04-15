<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    
    <title>设备类型信息的添加和修改页面</title>
   <link href="${ctx}/styles/dbStyles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/right.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/template.css" rel="stylesheet" type="text/css">
<LINK href="${ctx}/css/style/jquery-ui.custom.css" type=text/css rel=stylesheet>
<LINK href="${ctx}/css/style/jquery.popuplayer.css" type=text/css rel=stylesheet>
<link href="${ctx}/css/imageselect.css" media="screen" rel="stylesheet" type="text/css" /> 

<SCRIPT src="${ctx}/scripts/util.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/popuplayer.js" type=text/javascript></SCRIPT>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.ui.dialog.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-ui.custom.min.js"></script>

 
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
	
	var devicecategoryname = $("#deviceCategoryName").val();
	var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
	//alert(devicecategoryname);
		var nameFlag = true;
		//var nameindex="1";
		function checkDeviceCategoryName(){
		//document.deviceCategoryForm.deviceCategoryName.style.color='black';
		var dName=$("#deviceCategoryName").val();
		if(dName==devicecategoryname){
			return;
		}
		if(!rege.test(dName)){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#dName').val('');
			$('#dName').focus();
			return;
		}
		if(dName !=""){
			if(dName.length >20){
			nameFlag=true;
			$("#div_deviceCategoryName").html("");
			$("#div_deviceCategoryName").removeClass("spangreen");
			$("#div_deviceCategoryName").addClass("spanred");
			$("#div_deviceCategoryName").html("设备类型名称长度不能大于20个汉字");
			}else{
				$("#div_deviceCategoryName").html("");
				
				$.ajax({
			      type : "post",
				   url : "${ctx}/deviceCategory/judgeDeviceCategoryNameExist.action",
				   dataType : "json",
				   data : "&name="+dName,
				   success : function(result) {
					  if(1==result.resultByAjax){
					  //alert("该设备类型名已经被占用，请重新命名！");
					  nameFlag=true;
					  
					  $("#div_deviceCategoryName").html("");
					  $("#div_deviceCategoryName").removeClass("spangreen");
					  $("#div_deviceCategoryName").addClass("spanred");
					  $("#div_deviceCategoryName").html("该设备类型名已经被占用，请重新命名！");
					 
					  }else if(0==result.resultByAjax){
					  	nameFlag=false;
					  	$("#div_deviceCategoryName").html("");
					  	 $("#div_deviceCategoryName").removeClass("spanred");
					  	 $("#div_deviceCategoryName").addClass("spangreen");
					     $("#div_deviceCategoryName").html("名称可用！");
					     
					  }
				   }
			 }); 
				
			//	nameFlag=false;
			}
		}else{
			nameFlag=true;
			$("#div_deviceCategoryName").removeClass("spangreen");
			$("#div_deviceCategoryName").addClass("spanred");
			$("#div_deviceCategoryName").html("");
			$("#div_deviceCategoryName").html("设备名称不能为空！");
			
		}
		
		
		
	}
	
	function save(){
	
		
		var Name=$("#deviceCategoryName").val();
		if(Name.length==0){
			alert('请输入设备名称');
			return;
		}
		
		
		
		var id = $("#id").val();
		//alert(Name);
		//alert(devicecategoryname);
		//alert(nameindex);
		if(id!="0"){
			
			if(Name==devicecategoryname){
				//alert("123");
				nameFlag=false;
			}else{
				//nameFlag=true;
				//alert("345");
				checkDeviceCategoryName();
			}
		}else if(document.deviceCategoryForm.deviceCategoryName.style.color!='black'){
				alert('请检查设备类型名称');
				return;
			}
		else {
			//nameFlag=true;
			checkDeviceCategoryName();
		}
		
		if(nameFlag){
			alert('请检查设备类型名称');
			return;
		}
		
		$("#deviceCategoryForm").submit();
	}
	
	function back(){
		location.href="${ctx}/deviceCategory/queryAllDeviceCategory.action";
	}
	$(document).ready(function(){
		devicecategoryname = $("#deviceCategoryName").val();
		//nameindex+="1";
		//alert(devicecategoryname);
		
		//屏蔽enter键自动提交表单
	 $("form").keypress(function(e){  
	        if(e.keyCode == 13) {  
	            e.preventDefault();  
	        }  
	    });
		
	});
	
	var categoryFlag = true;
	function getPhotoName(){
		//alert("123");
		categoryname = $("#deviceCategoryName").val();
		if(categoryFlag||categoryname.length==0||document.deviceCategoryForm.deviceCategoryName.style.color!='black'){
			var photoPath=$("#deviceCategoryPhotoPath").val();
		var url = "${ctx}/deviceCategory/photoName.action";  
		$.ajax({
                  type : "post",
                   url: url,
                   data:{photoPath:photoPath},
                   success : function(result) {
                //        alert(result.resultByAjax);
               
                        $("#deviceCategoryName").val(result);
                  //     alert(result);
                   }
             }); 
             document.deviceCategoryForm.deviceCategoryName.style.color='#999';
		}
		
	}
	
	function onClickName(){
		//devicecategoryname = $("#deviceCategoryName").val();
		categoryFlag = false;
		var id = $("#id").val();
		//alert(id);
		if(id!="0"){
			document.deviceCategoryForm.deviceCategoryName.style.color='black';
		}else if(document.deviceCategoryForm.deviceCategoryName.style.color!='black'){
			$("#deviceCategoryName").val("");
			document.deviceCategoryForm.deviceCategoryName.style.color='black';
		}
		
	}
	
	
	
	var bV=parseInt(navigator.appVersion);
var IE4=((document.all)&&(bV>=4))?true:false;
var NS4=(document.layers)?true:false;
var z=0;
function LayerV(LayerName,V){
    E=eval('document.'+LayerName);
    if(IE4) E=eval('document.all.'+LayerName+'.style');
    E.visibility=(V?'visible':'hidden');
}

//////////////////////////////////////////////////////////
//下面这个函数实现了当点击图片时，把图片选上
function select(image1){
var html,url1
url1=image1.src

html="<img src="+url1+" width=80 height=60 onclick='javascript:select(this)'>"
html=html+"<a href=javascript:LayerV('Zhong',z=1-z)><font style='font-family: Webdings;'>6</font></a>"
pic.innerHTML=html
LayerV('Zhong',z=1-z)

var url2 = url1;

var index = url2.indexOf("images/topo");
var url3 = url2.substring(index);
//alert(url3);
$("#deviceCategoryPhotoPath").val(url3);
//var vvv = $("#deviceCategoryPhotoPath").val();
//alert(vvv);
//var a = $('#defultImage').attr("src");
var id = $("#id").val();
if(id=="0"){
	getPhotoName();
}


}
	
	
</script>

<%-- <script type='text/javascript' src="${ctx}/scripts/devicecategoryphoto/imageselect.js"></script> --%>

  </head>
  
  <body style="margin: 0">
 <s:form action="updateDeviceCategory" method="post" namespace="/deviceCategory" theme="simple" id="deviceCategoryForm"  name="deviceCategoryForm" enctype="multipart/form-data">
      <input type="hidden" name="id" id="id" value="${deviceCategory.deviceCategory_id}"> 
      <!-- <div style="background-color:#A9A389; width:40px; height:40px; position:absolute; z-index:4; left:50px; top:50px;">2</div> -->
      
      <table  width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px"  >
	  	<tr>
				<td width="100%" valign="top">
							<!-- information area -->
							<div class="framDiv">
							<table width="100%" border="0" cellspacing="1" cellpadding="0"   >
                                 <tr>
									<td colspan="4" class='r2titler' style="font-size: 12px">设备类型信息编辑</td>
								</tr>
								<tr>
									<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td class="tdleft">设备类型名称：&nbsp;</td>
									<td style="text-align:left;">
										<input type="text" style="width:250px ;color: #999"  id="deviceCategoryName" width="99%" name="deviceCategoryName"  maxlength="500" value="<c:if test="${deviceCategory.deviceCategory_name!=null}" >${deviceCategory.deviceCategory_name}</c:if><c:if test="${deviceCategory.deviceCategory_name==null}" ></c:if>" onchange="checkDeviceCategoryName()"   onClick="onClickName()"/>
										&nbsp;<span id="div_deviceCategoryName" ></span>
									</td>
								</tr>
								<tr>
										<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td class="tdleft" >设备类型图片：&nbsp;</td>
									<!-- <td style="text-align:left">
										<input type="file" id="deviceCategoryPhoto" name="deviceCategoryPhoto"  value="${deviceCategory.deviceCategory_photo}" style="width:250px" />
									</td> -->
									<td style="text-align:left ;" width="5%">
									<%-- <input type="text" style="width:250px"  id="sysName" width="99%" name="sysName"  maxlength="500" value="${dataAssets.sysName}" readonly="readonly" onclick="$('#goToSearch').val('');employeeDlg()"/> --%>
									<%-- <select style="width: 250px;" name="deviceCategoryPhotoPath"  id="deviceCategoryPhotoPath"  >
										<s:iterator value="deviceCategoryPhotoList">
											  <option value="${deviceCategoryphoto_Path }" <c:if test="${deviceCategoryphoto_Path==deviceCategory.getDeviceCategory_photo() }">selected="selected"</c:if> >${ctx}/${deviceCategoryphoto_Path }</option>
										</s:iterator>
										<s:iterator value="deviceCategoryPhotoList" >
											  <option value="${deviceCategoryphoto_Path }" <c:if test="${deviceCategoryphoto_Path==deviceCategory.deviceCategory_photo}" >selected="selected"</c:if> >${ctx}/${deviceCategoryphoto_Path }</option>
										</s:iterator>
								</select> --%>
						
						<!-- 	<select name="deviceCategoryPhotoPath">
<option value="1">${ctx}/images/topo/devicecategory/Mid-low-end routers.png</option>
<option value="2">${ctx}/images/topo/devicecategory/R3600E.png</option>
<option value="3">${ctx}/images/topo/devicecategory/图片1.png</option>
<option value="4">${ctx}/images/topo/devicecategory/图片2.png</option>
</select> -->
						<div style="position: relative;">
                          <div id=pic style="border: 1">
                          <font style="font-size:12px">
                          <img src="<c:if test="${deviceCategory.deviceCategory_photo!=null}" >${ctx}/${deviceCategory.deviceCategory_photo}</c:if><c:if test="${deviceCategory.deviceCategory_photo==null}" >${ctx}/images/topo/devicecategory/Bladeserver.png</c:if>"  width=80 height=60 onclick="javascript:LayerV('Zhong',z=1-z)" >
                          
                          <a href="javascript:LayerV('Zhong',z=1-z)">
                          <font style="font-family: Webdings;">6</font>
                          </a>
                          </div>
                          <div id="Zhong" style=" background-color:#fff; visibility:hidden ;width:80px ;height:200px ;overflow:scroll; position:absolute; z-index:4; left:10px; top:60px;"> 
        <s:iterator value="deviceCategoryPhotoList" >
			 <img src="${ctx}/${deviceCategoryphoto_Path }" width=80%  onclick="javascript:select(this)"><br>
		</s:iterator>
      </div>
      
      </div>
                         <input type="hidden" name="deviceCategoryPhotoPath"  id="deviceCategoryPhotoPath"  value="<c:if test="${deviceCategory.deviceCategory_photo!=null}" >${deviceCategory.deviceCategory_photo}</c:if><c:if test="${deviceCategory.deviceCategory_photo==null}" >images/topo/devicecategory/Bladeserver.png</c:if>"/> 
								</td>
								 <td style="text-align:left"><span id="div_photoName" ></span></td>
								
								</tr>
								<tr>
										<td class="td_detail_separator"></td>
								</tr>
								
								<tr>
									<td class="tdleft" >设备类型描述：&nbsp;</td>
									<td colspan="3" style="text-align: left;font-size: 12px;">
									<textarea rows="3"
											style="width:40%"	 id="deviceCategoryDescribe" name="deviceCategoryDescribe" >${deviceCategory.deviceCategory_describe}</textarea>
									</td>
								</tr>
								<tr>
										<td class="td_detail_separator"></td>
								</tr>
								<%-- <tr>
									<td class="tdleft">检测设备在线情况：&nbsp;</td>
									<td colspan="3" style="text-align: left;font-size: 12px"><textarea rows="3"
												style="width:40%" id="dataRemarks" name="dataRemarks" >${dataAssets.dataRemarks}</textarea>
										&nbsp;</td>
								</tr> --%>
								<tr>
										<td class="td_detail_separator"></td>
								</tr>
								
							</table>
							</div>
					</td>
			</tr>
			<!-- <input type="button" value="123" onclick="getPhotoName()"/> -->
	<tr>
	<tr> 
          <td align="left">
                  </td>
                  
        </tr>
        
        <tr> 
    <td></td>
  </tr>
	  		<td>
	
               <div >
				 
                   <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr height="30">							 
									<td align="right"  >
										<input type="button" value="取消"   class="btnyh"  onclick="back()" />
										<input type="button"  value="保存"   class="btnyh" id="btnSub" onclick="save()" />
									</td>
								</tr>
                   </table>
	         </div>
	  	</td>
	 </tr>

</table>

<!-- 测试图片下拉框 -->

<table width="90" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td> 
      <table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="#FF66FF">
        
      </table>
      </td>
  </tr>
  
</table>

</s:form>
<!-- ui-dialog-sysInfo -->
	<%-- 	<div id="dialog-employee" title="所属信息系统" style="font-size: 12px;">
			<table width="100%" class="tab2" style="width: 100%" border="0" cellspacing="0" cellpadding="0">
			<tr align="left" style="width: 100%;">
				<td align="left" style="float: left;width: 100%;font-size: 12px" >
				 <div style="float: left;">
					快速搜索：<input name="dlg-keyword-sysName"  id="goToSearch" onkeydown="if(event.keyCode==13)queryEmployee();" />
					<img src="${ctx}/images/search.jpg" class="hand"  onclick="goSearch(0)" />
					</div>
				</td>
				 
			</tr>
			<tr height="5"></tr>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2" id="table-sysName">
					<tr height="28">
						<td width="10%" align="center" class="biaoti">
							 <input type="checkbox" id="chkAll" class="check-box not_checked"/>
						</td>
						<td width="30%" align="center" class="biaoti">
							系统名称
						</td>
						<td width="30%" align="center" class="biaoti">
							系统编号
						</td>
						<td width="30%"  align="center" class="biaoti">
							业务描述
						</td>
					</tr>
				</table>
				</td>
			</tr>
			
			</table>
		</div> --%>
		
		<!-- ui-dialog-softInfo -->
		<%-- <div id="dialog-softUse" title="业务应用软件">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					快速搜索：<input name="dlg-keyword-sysName" id="softUseId" onkeydown="if(event.keyCode==13)queryEmployee();" />
					<img src="${ctx}/images/search.jpg" class="hand"  onclick="softSearch(0)" />
				</td>
			</tr>
			<tr height="5"></tr>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2" id="table-softUse">
					<tr height="28">
						<td width="10%" align="center" class="biaoti">
							 <input type="checkbox" id="chkAll" class="check-box not_checked"/>
						</td>
						<td width="30%" align="center" class="biaoti">
							软件名称
						</td>
						<td width="20%" align="center" class="biaoti">
							所属系统名称
						</td>
						<td width="20%"  align="center" class="biaoti">
							软件功能描述
						</td>
						<td width="20%"  align="center" class="biaoti">
							重要程度
						</td>
					</tr>
				</table>
				</td>
			</tr>
			
			</table>
		</div> --%>
	
  </body>
</html>
