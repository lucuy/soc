<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<html>
<head>

<title>单位基本情况</title>
<meta http-equiv="Cache-Control" content="private" />
<link href="${ctx}/styles/dbStyles/pageCss.css" rel="stylesheet" type="text/css">
 <link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
 <link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<SCRIPT src="${ctx}/scripts/jquery-1.7.2.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/jquery-ui.custom.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/util.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/popuplayer.js" type=text/javascript></SCRIPT>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {

		 var msgUnit = document.getElementsByName("unitType");

		for ( var i = 0; i < msgUnit.length; i++) {

			msgUnit[i].disabled = true;
		}
 
		var msgEmp = document.getElementsByName("employment");

		for ( var j = 0; j < msgEmp.length; j++) {
			msgEmp[j].disabled = true;
		}

		var msgSub = document.getElementsByName("subordinate");

		for ( var k = 0; k < msgSub.length; k++) {
			msgSub[k].disabled = true;
		}

	});

	function save() {
	//alert("");
		//var Fother=document.getElementById("subordinateid");
		//var Sother=document.getElementById("unitTypeid");
		//var Tother=document.getElementById("employmentid");
		/* if($("#subordinateid").is(":checked")){
			alert("");
		} */
		$("#saveUnitInfo").submit();
	}
	function query() {
	    var btnSub=document.getElementById("subBtn");
        btnSub.disabled="disabled";
		location.href = "${ctx}/unitInfo/edit.action";
	}
</script>
</head>
<body >
   <s:form action="insert" namespace="/unitInfo" method="post" theme="simple" id="saveUnitInfo">
	<input type="hidden" id="unitType" value="${unitInfo.unitType }">
	<div style="width: 99.5%" >
			<table  width="100%" border="1" bordercolor="#5AA4D1" cellpadding="0" cellspacing="0" >
				<s:if test='#session.SSI_LOGIN_Status=="1"'>
				</s:if>
				<s:else>
			<tr>
				<td colspan="6" >
					<input type="button" value="编辑"  style="margin-left: 10px" id="subBtn" class="btnstyle" onclick="query()" />
				</td>
			</tr>
		     </s:else>
				<tr>
					<td  align="right" width="16%">单位名称：</td>
					<td  colspan="5" width="84%">&ensp;${unitInfo.unitName}</td>
				</tr>
				<tr>
					<td width="16%" align="right">单位地址：</td>
					<td colspan="5" width="84%">&ensp;${unitInfo.province}省(自治区、直辖市)&nbsp;&nbsp;${unitInfo.city}市(区、市、州、盟)&nbsp;&nbsp;${unitInfo.county}县(区、市、旗)</td>
				</tr>
				<tr>
					<td  align="right" width="16%">邮政编码：</td>
					<td colspan="2" width="32%" >&ensp;<c:choose>
							<c:when test="${unitInfo.postcode==0}"></c:when>
							<c:otherwise>${unitInfo.postcode}</c:otherwise>
						</c:choose>
					</td>
					<td  align="right" width="16%">行政区代码：</td>
					<td colspan="2" width="32%">&ensp;${unitInfo.divisionCode}</td>
				</tr>
				<tr>
					<td rowspan="2" width="16%" align="right">单位负责人：</td>
					<td  scope="row" align="right" width="16%">姓名：</td>
					<td width="16%">&ensp;${unitInfo.unitLeader}</td>
					<td scope="row" align="right" width="16%">职务/职称：</td>
					<td colspan="2" width="32%">&ensp;${unitInfo.duty}</td>
				</tr>
				<tr>
					<td scope="row" align="right" width="16%">办公电话：</td>
					<td  width="16%">&ensp;${unitInfo.unitTel}</td>
					<td scope="row" align="right"  width="16%">电子邮件：</td>
					<td colspan="2"  width="32%">&ensp;${unitInfo.unitEmail}</td>
				</tr>
				<tr>
					<td  align="right"  width="16%">责任部门：</td>
					<td colspan="5"  width="84%">&ensp;${unitInfo.unitDep}</td>
				</tr>
				<tr>
					<td  align="right" rowspan="3"  width="16%">责任人部门联系人：</td>
					<td  align="right"  width="16%">姓名：</td>
					<td  width="16%">&ensp;${unitInfo.depContact}</td>
					<td  align="right"  width="16%">职务/职称：</td>
					<td colspan="2"  width="32%">&ensp;${unitInfo.depDuty}</td>
				</tr>
				
				<tr>
					<td align="right"  width="16%">办公电话：</td>
					<td  width="16%">&ensp;
							
							${UnitInfo.depTel}
						
					</td>
					<td align="right"  width="16%">电子邮件：</td>
					<td colspan="2"  width="32%">&ensp;${unitInfo.depEmail}</td>
				</tr>
				<tr>
					<td  align="right"  width="16%">移动电话：</td>
					<td colspan="4"  width="64%">&ensp;
							
							${unitInfo.depMobile}
						
					</td>
				</tr>
				<tr>
					<td width="16%" align="right">隶属关系：</td>
					<td colspan="5" width="84%">
								<p><input type="radio" name="subordinate" value="中央" <c:if test="${unitInfo.subordinate=='中央'}">checked</c:if> />中央&ensp;&ensp;&ensp;
								<input type="radio" name="subordinate" value="省" <c:if test="${unitInfo.subordinate=='省'}">checked</c:if> />省(自治区、直辖市)&ensp;&ensp;&ensp;
								<input type="radio" name="subordinate" value="地" <c:if test="${unitInfo.subordinate=='地'}">checked</c:if> />地(区、市、州、盟)&ensp;&ensp;&ensp;
								
								<input type="radio" name="subordinate" value="县" <c:if test="${unitInfo.subordinate=='县'}">checked</c:if> />县（区、市、旗）&ensp;&ensp;&ensp;
					            <input id="subordinateid" type="radio" name="subordinate" value="其他" <c:if test="${unitInfo.subordinate=='其他'}">checked</c:if> />其他 &nbsp;&nbsp;<input type="text" id="subordinatetextid" value="${unitInfo.otherSub}" disabled="true">
					            </p>
					</td>
				</tr>
				
				<tr>
					<td width="16%" align="right">单位类型：</td>
					<td colspan="5" width="84%">
						<p><input type="radio" name="unitType" value="1 党委机关" <c:if test="${unitInfo.unitType=='党委机关'}">checked</c:if> />党委机关 &nbsp;&nbsp;&nbsp;
						<input type="radio" name="unitType" value="政府机关" <c:if test="${unitInfo.unitType=='政府机关'}">checked</c:if> />政府机关&nbsp;&nbsp;&nbsp;
						<input type="radio" name="unitType" value="事业单位" <c:if test="${unitInfo.unitType=='事业单位'}">checked</c:if> />事业单位&nbsp;&nbsp;&nbsp; 
						<input type="radio" name="unitType" value="企业" <c:if test="${unitInfo.unitType=='企业'}">checked</c:if> />企业
						<input id="unitTypeid" type="radio" name="unitType" value="其他" <c:if test="${unitInfo.unitType=='其他'}">checked</c:if> />其他
						<input type="text" id="otherUnitTypeid" name="otherUnitType" value="${unitInfo.otherUnitType}" disabled="disabled" /></p>
					</td>
				</tr>
				 
				<tr>
					<td width="16%" align="right">行业类别：</td>
					<td colspan="5" width="84%">
						<input type="radio" name="employment" value="电信" <c:if test="${unitInfo.employment=='电信'}">checked</c:if> />电信&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="employment" value="广电" <c:if test="${unitInfo.employment=='广电'}">checked</c:if> />广电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="employment" value="经营性公众互联网" <c:if test="${unitInfo.employment=='经营性公众互联网'}">checked</c:if> />经营性公众互联网
						<br>
						<input type="radio" name="employment" value="铁路" <c:if test="${unitInfo.employment=='铁路'}">checked</c:if> />铁路&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="employment" value="银行" <c:if test="${unitInfo.employment=='银行'}">checked</c:if> />银行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="employment" value="海关" <c:if test="${unitInfo.employment=='海关'}">checked</c:if> />海关&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    	<input type="radio" name="employment" value="税务" <c:if test="${unitInfo.employment=='税务'}">checked</c:if> />税务&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				        <br>
						<input type="radio" name="employment" value="民航" <c:if test="${unitInfo.employment=='民航'}">checked=</c:if> />民航&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="employment" value="电力" <c:if test="${unitInfo.employment=='电力'}">checked</c:if> />电力&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="employment" value="证券" <c:if test="${unitInfo.employment=='证券'}">checked</c:if> />证券&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="employment" value="保险" <c:if test="${unitInfo.employment=='保险'}">checked</c:if> />保险&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   		<br>
						<input type="radio" name="employment" value="国防科技工业" <c:if test="${unitInfo.employment=='国防科技工业'}">checked</c:if> />国防科技工业&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="employment" value="公安" <c:if test="${unitInfo.employment=='公安'}">checked</c:if> />公安&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    	<input type="radio" name="employment" value="人事劳动和社会保障" <c:if test="${unitInfo.employment=='人事劳动和社会保障'}">checked</c:if> />人事劳动和社会保障
				    	<input type="radio" name="employment" value="财政" <c:if test="${unitInfo.employment=='财政'}">checked</c:if> />财政&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 						<br>
						<input type="radio" name="employment" value="审计" <c:if test="${unitInfo.employment=='审计'}">checked</c:if> />审计&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    	<input type="radio" name="employment" value="商业贸易" <c:if test="${unitInfo.employment=='商业贸易'}">checked</c:if> />商业贸易&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="employment" value="国土资源" <c:if test="${unitInfo.employment=='国土资源'}">checked</c:if> />国土资源&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="employment" value="能源" <c:if test="${unitInfo.employment=='能源'}">checked</c:if> />能源&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    	<br>
						<input type="radio" name="employment" value="交通" <c:if test="${unitInfo.employment=='交通'}">checked</c:if> />交通&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="employment" value="统计" <c:if test="${unitInfo.employment=='统计'}">checked</c:if> />统计&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="employment" value="工商行政管理" <c:if test="${unitInfo.employment=='工商行政管理'}">checked</c:if> />工商行政管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    	<input type="radio" name="employment" value="邮政" <c:if test="${unitInfo.employment=='邮政'}">checked</c:if> />邮政&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    	<br>
						<input type="radio" name="employment" value="教育" <c:if test="${unitInfo.employment=='教育'}">checked</c:if> />教育&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="employment" value="文化" <c:if test="${unitInfo.employment=='文化'}">checked</c:if> />文化&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        	<input type="radio" name="employment" value="卫生" <c:if test="${unitInfo.employment=='卫生'}">checked</c:if> />卫生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="employment" value="农业" <c:if test="${unitInfo.employment=='农业'}">checked</c:if> />农业&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    	<br> 
						<input type="radio" name="employment" value="水利" <c:if test="${unitInfo.employment=='水利'}">checked</c:if> />水利&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    	<input type="radio" name="employment" value="外交" <c:if test="${unitInfo.employment=='外交'}">checked</c:if> />外交&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="employment" value="发展改革" <c:if test="${unitInfo.employment=='发展改革'}">checked</c:if> />发展改革&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="employment" value="科技" <c:if test="${unitInfo.employment=='科技'}">checked</c:if> />科技&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    	<br>
						<input type="radio" name="employment" value="宣传" <c:if test="${unitInfo.employment=='宣传'}">checked</c:if> />宣传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="employment" value="质量监督检验检疫" <c:if test="${unitInfo.employment=='质量监督检验检疫'}">checked</c:if> />质量监督检验检疫&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<br>
						<input id="employmentid" type="radio" name="employment" value="其他" <c:if test="${unitInfo.employment=='其他'}">checked</c:if> />其他<input type="text" id="otherEmpid" name="otherEmp" value="${unitInfo.otherEmp}" disabled="disabled" />
				  </td>
				</tr>
				
				<tr >
					<td width="16%" rowspan="2" align="right">系统信息总数：</td>
					<td width="16%" rowspan="2">&ensp;${sysNum}</td>
					<td width="16%" align="right">第二级信息系统数：</td>
					<td width="16%">&ensp;${sysSecNum}</td>
					<td width="16%" align="right">第三级信息系统数：</td>
					<td width="16%">&ensp;${systhrNum}</td>
				</tr>
				<tr>
				    
					<td width="16%" align="right">第四级信息系统数：</td>
					<td width="16%">&ensp;${systhiNum}</td>
					<td width="16%"align="right">第五级级信息系统数：</td>
					<td width="16%">&ensp;0</td>

				</tr>
			</table>
			<!-- 左侧列结束-->
		</div>
	</s:form>
</body>
</html>
