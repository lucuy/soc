<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>信息系统定级详情</title>
<link href="${ctx}/styles/dbStyles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/right.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/template.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/pageCss.css" rel="stylesheet" type="text/css">
<style type="text/css">
.detilTr1 {
	color: darkblue;
	height: 30px;
	background-color: #EEEEEE;
}

table {
	border-left: thin;
	border-bottom: thin;
	font-size: 12px;
}

table td {
	text-align: center;
	border-top: thin;
	border-right: thin;
	line-height: 28px;
	background: none repeat scroll 0px 0px rgb(255, 255, 255);
}
</style>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function back() {
		location.href = "${ctx}/rank/queryRank.action";
	}
</script>
</head>
<body >
	<input type="hidden" id="rankGrade"
		value="<s:property value="rank.rankGrade"/>" />
	<input type="hidden" id="rankOrganType"
		value="<s:property value="rank.rankOrganType"/>" />
	<input type="hidden" id="rankOrganArea"
		value="<s:property value="rank.rankOrganArea"/>" />
	<input type="hidden" id="rankBusinessType"
		value="<s:property value="rank.rankBusinessType"/>" />
	<table  width="100%" border="1" bordercolor="#5AA4D1" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="6" class='r2titler'>信息系统情况</td>
		</tr>
		<tr>
			<td colspan="2" align="right">01 系统名称：</td>
			<td ><input type="text" value="<s:property value='rank.sysInfoName' />" disabled="disabled">
			</td>
			<td colspan="2" align="right">02 系统编号：</td>
			<td ><input type="text" value="<s:property value='rank.sysInfoId' />" disabled="disabled" />
			</td>
		</tr>

		<tr>
			<td align="right" rowspan="2">05系统网络平台:</td>
            <td align="right" width="10%">覆盖范围:</td>
            <td colspan="4" style="text-align: left" >
                <input type="radio" value="局域网"<s:if test="%{1==rank.rankCoveArea}">checked</s:if> disabled="disabled" /> 1 局域网&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" <s:if test="%{2==rank.rankCoveArea}">checked</s:if> value="城域网" disabled="disabled" /> 2 城域网&nbsp;&nbsp;&nbsp; 
				<input type="radio" <s:if test="%{3==rank.rankCoveArea}">checked</s:if> value="广域网" disabled="disabled" /> 3 广域网&nbsp;&nbsp;&nbsp; 
				<input type="radio" <s:if test="%{4==rank.rankCoveArea}">checked</s:if> value="其它" disabled="disabled" /> 9 其它 
				<input type="text" class="txt"
				maxlength="6" value="<s:property value='rank.rankOthArea' />"
				disabled="disabled" />
			</td>
		</tr>
		<tr>
			<td class="tdLabel" align="right">网络性质：</td>
			<td colspan="4" style="text-align: left">
			    <input type="radio" <s:if test="%{1==rank.rankNetworkProp}">checked</s:if> value="业务专网" disabled="disabled" /> 1 业务专网&nbsp;&nbsp;&nbsp;
				<input type="radio" <s:if test="%{2==rank.rankNetworkProp}">checked</s:if> value="互联网" disabled="disabled" /> 2 互联网&nbsp;&nbsp;&nbsp;
                <input type="radio" <s:if test="%{3==rank.rankNetworkProp}">checked</s:if> value="其它" disabled="disabled" /> 9 其它 
				<input type="text" class="txt" maxlength="25" value="<s:property value='rank.rankOthNetworkProp' />" disabled="disabled" />
			</td>
		</tr>

		<tr>
			<td align="right" width="10%" colspan="2">06系统互联情况:</td>
            <td colspan="4" style="text-align: left">
                            <input type="radio" <s:if test="%{1==rank.rankSysConn}">checked</s:if> disabled="disabled" /> 1 与其他行业系统连接&nbsp;&nbsp;
                            <input type="radio" <s:if test="%{2==rank.rankSysConn}">checked</s:if> disabled="disabled"  /> 2 与本行业其他单位系统连接
                            <br> 
                            <input type="radio" <s:if test="%{3==rank.rankSysConn}">checked</s:if> disabled="disabled"/> 3 与本单位其他系统连接
                            <input type="radio" <s:if test="%{4==rank.rankSysConn}">checked</s:if> disabled="disabled" /> 9 其它 <input type="text" class="txt" maxlength="25" value="<s:property value='rank.rankOtherSysConn' />" disabled="disabled" />
            </td>
		</tr>
	</table>
	
	<table  width="100%" border="1" bordercolor="#5AA4D1" cellpadding="0" cellspacing="0">
		<tr>
			<td width="100%" class='r2titler' colspan="6">07关键产品使用情况</td>
		</tr>
		<tr>
			<td width="10%" rowspan="2" 
				style="text-align: center;">序号：</td>
			<td width="10%" rowspan="2" class="tdLabel"
				style="text-align: center;">产品类型：</td>
			<td width="10%" rowspan="2" class="tdLabel"
				style="text-align: center;">数量：</td>
			<td width="70%" colspan="3" class="tdLabel"
				style="text-align: center;">使用国产品率：</td>
		</tr>
		<tr>
			<td width="15%" class="tdLabel" style="text-align: center;">全部使用</td>
			<td width="15%" class="tdLabel" style="text-align: center;">全部未使用</td>
			<td width="20%" class="tdLabel" style="text-align: center;">部分使用率</td>
		</tr>
		<tr>
			<td style="text-align: center;">1</td>
			<td style="text-align: center;">安全专用产品：</td>
			<td align="center"><input type="text" disabled="disabled"
				value="<s:property value='rank.rankSecCount' />" class="txt"
				maxlength="9" style="width:50px;text-align:right" />
			</td>
			<td align="center"><input type='radio' disabled="disabled"
				value="100" <s:if test="%{100==rank.rankSecUse}">checked</s:if> />
			</td>
			<td align="center"><input type='radio' disabled="disabled"
				value="0" <s:if test="%{0==rank.rankSecUse}">checked</s:if> />
			</td>
			<td align="left"><s:property value='rank.partRankSecUse' />%</td>
		</tr>
		<tr>
			<td style="text-align: center;">2</td>
			<td style="text-align: center;">网络产品：</td>
			<td align="center"><input type="text"
				value="<s:property value='rank.rankNetCount' />" class="txt"
				disabled="disabled" maxlength="9"
				style="width:50px;text-align:right" />
			</td>
			<td align="center"><input type='radio' value="100"
				<s:if test="%{100==rank.rankNetUse}">checked</s:if>
				disabled="disabled" />
			</td>
			<td align="center"><input type='radio' value="0"
				<s:if test="%{0==rank.rankNetUse}">checked</s:if>
				disabled="disabled" />
			</td>
			<td align="left"><s:property value='rank.partRankNetUse' />%</td>
		</tr>
		<tr>
			<td style="text-align: center;">3</td>
			<td style="text-align: center;">操作系统：</td>
			<td align="center"><input type="text" class="txt"
				value="<s:property value='rank.rankSysCount' />" disabled="disabled"
				maxlength="9" style="width:50px;text-align:right" />
			</td>
			<td align="center"><input type='radio' value="100"
				<s:if test="%{100==rank.rankSysUse}">checked</s:if>
				disabled="disabled" />
			</td>
			<td align="center"><input type='radio' value="0"
				<s:if test="%{0==rank.rankSysUse}">checked</s:if>
				disabled="disabled" />
			</td>
			<td align="left"><s:property value='rank.partRankSysUse' />%</td>
		</tr>
		<tr>
			<td style="text-align: center;">4</td>
			<td style="text-align: center;">数据库：</td>
			<td align="center"><input type="text" class="txt"
				disabled="disabled" value="<s:property value='rank.rankSqlCount' />"
				maxlength="9" style="width:50px;text-align:right" />
			</td>
			<td align="center"><input type='radio' value="100"
				disabled="disabled"
				<s:if test="%{100==rank.rankSqlUse}">checked</s:if> />
			</td>
			<td align="center"><input type='radio' value="0"
				disabled="disabled"
				<s:if test="%{0==rank.rankSqlUse}">checked</s:if> />
			</td>
			<td align="left"><s:property value='rank.partRankSqlUse' />%</td>
		</tr>
		<tr>
			<td style="text-align: center;">5</td>
			<td style="text-align: center;">服务器：</td>
			<td align="center"><input type="text"
				value="<s:property value='rank.rankSerCount' />" class="txt"
				disabled="disabled" maxlength="9"
				style="width:50px;text-align:right" />
			</td>
			<td align="center"><input type='radio' value="100"
				<s:if test="%{100==rank.rankSerUse}">checked</s:if>
				disabled="disabled" />
			</td>
			<td align="center"><input type='radio' value="0"
				<s:if test="%{0==rank.rankSerUse}">checked</s:if>
				disabled="disabled" />
			</td>
			<td align="left"><s:property value='rank.partRankSerUse' />%</td>
		</tr>
		<tr>
			<td style="text-align: center;">6</td>
			<td style="text-align: center;">其它： <span style="color: #C0C0C0"><s:property
						value='rank.rankOthProd' />
			</span></td>
			<td align="center"><input type="text"
				value="<s:property value='rank.rankOthProdCount' />" class="txt"
				disabled="disabled" maxlength="9"
				style="width:50px;text-align:right" />
			</td>
			<td align="center"><input type='radio' value="100"
				<s:if test="%{100==rank.rankOthProdUse}">checked</s:if>
				disabled="disabled" />
			</td>
			<td align="center"><input type='radio' value="0"
				<s:if test="%{0==rank.rankOthProdUse}">checked</s:if>
				disabled="disabled" />
			</td>
			<td align="left"><s:property value='rank.partRankOthProdUse' />%</td>
		</tr>
	</table>
	<table width="100%" border="1" bordercolor="#5AA4D1" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="6"  class='r2titler'>08 系统采用服务情况</td>
		</tr>
		<tr>
			<td  rowspan="2"  style="text-align:center;">序号：</td>
			<td rowspan="2" colspan="2" style="text-align: center;">服务类型：</td>
			<td  colspan="3" class="tdLabel" style="text-align: center;">服务责任方类型：</td>
		</tr>
		<tr>
			<td   style="text-align: center;">本行业（单位）</td>
			<td  style="text-align: center;">国内其他服务商</td>
			<td  style="text-align: center;">国外服务商</td>
		</tr>
		<tr>
			<td style="text-align: center;">1</td>
			<td style="text-align: center;">等级测评：</td>
			<td align="center"><input type="radio"
				<s:if test="%{1==rank.rankIfGradeEval}">checked</s:if>
				disabled="disabled" /> 有 <input type="radio" value="0"
				<s:if test="%{0==rank.rankIfGradeEval}">checked</s:if>
				disabled="disabled" /> 无</td>
			<td align="center"><input type='radio' value="1"
				<s:if test="%{1==rank.rankSerGradeType}">checked</s:if>
				disabled="disabled" />
			</td>
			<td align="center"><input type='radio' value="2"
				<s:if test="%{2==rank.rankSerGradeType}">checked</s:if>
				disabled="disabled" />
			</td>
			<td align="center"><input type='radio' value="3"
				<s:if test="%{3==rank.rankSerGradeType}">checked</s:if>
				disabled="disabled" />
			</td>
		</tr>
		<tr>
			<td width="21%" style="text-align: center;">2</td>
			<td style="text-align: center;">风险评估：</td>
			<td align="center"><input type="radio"
				<s:if test="%{1==rank.rankIfGradeEval}">checked</s:if> value="1"
				disabled="disabled" /> 有 <input type="radio" value="0"
				<s:if test="%{0==rank.rankIfGradeEval}">checked</s:if>
				disabled="disabled" /> 无</td>
			<td align="center"><input type='radio'
				<s:if test="%{1==rank.rankSerRiskType}">checked</s:if> value="1"
				disabled="disabled" />
			</td>
			<td align="center"><input type='radio'
				<s:if test="%{2==rank.rankSerRiskType}">checked</s:if> value="2"
				disabled="disabled" />
			</td>
			<td align="center"><input type='radio'
				<s:if test="%{3==rank.rankSerGradeType}">checked</s:if> value="3"
				disabled="disabled" />
			</td>
		</tr>
		<tr>
			<td width="21%" style="text-align: center;">3</td>
			<td style="text-align: center;">灾难恢复：</td>
			<td align="center"><input type="radio" value="1"
				<s:if test="%{1==rank.rankIfSuffReco}">checked</s:if>
				disabled="disabled" /> 有 <input type="radio" value="0"
				<s:if test="%{0==rank.rankIfSuffReco}">checked</s:if>
				disabled="disabled" /> 无</td>
			<td align="center"><input type='radio' value="1"
				<s:if test="%{1==rank.rankIfSuffRecoType}">checked</s:if>
				disabled="disabled" />
			</td>
			<td align="center"><input type='radio' value="2"
				<s:if test="%{2==rank.rankIfSuffRecoType}">checked</s:if>
				disabled="disabled" />
			</td>
			<td align="center"><input type='radio' value="3"
				<s:if test="%{3==rank.rankIfSuffRecoType}">checked</s:if>
				disabled="disabled" />
			</td>
		</tr>
		<tr>
			<td width="21%" style="text-align: center;">4</td>
			<td style="text-align: center;">应急响应：</td>
			<td align="center"><input type="radio" value="1"
				<s:if test="%{1==rank.rankIfResponse}">checked</s:if>
				disabled="disabled" /> 有 <input type="radio" name="rank.respons"
				value="0" <s:if test="%{0==rank.rankIfResponse}">checked</s:if>
				disabled="disabled" /> 无</td>
			<td align="center"><input type='radio' value="1"
				<s:if test="%{1==rank.rankResponseType}">checked</s:if>
				disabled="disabled" />
			</td>
			<td align="center"><input type='radio' value="2"
				<s:if test="%{2==rank.rankResponseType}">checked</s:if>
				disabled="disabled" />
			</td>
			<td align="center"><input type='radio' value="3"
				<s:if test="%{3==rank.rankResponseType}">checked</s:if>
				disabled="disabled" />
			</td>
		</tr>
		<tr>
			<td width="21%" style="text-align: center;">5</td>
			<td style="text-align: center;">系统集成：</td>
			<td align="center"><input type="radio" value="1"
				<s:if test="%{1==rank.rankIfSysInte}">checked</s:if>
				disabled="disabled" /> 有 <input type="radio" value="0"
				<s:if test="%{0==rank.rankIfSysInte}">checked</s:if>
				disabled="disabled" /> 无</td>
			<td align="center"><input type='radio' value="1"
				<s:if test="%{1==rank.rankSysInteType}">checked</s:if>
				disabled="disabled" />
			</td>
			<td align="center"><input type='radio' value="2"
				<s:if test="%{2==rank.rankSysInteType}">checked</s:if>
				disabled="disabled" />
			</td>
			<td align="center"><input type='radio' value="3"
				<s:if test="%{3==rank.rankSysInteType}">checked</s:if>
				disabled="disabled" />
			</td>
		</tr>
		<tr>
			<td width="21%" style="text-align: center;">6</td>
			<td style="text-align: center;">安全咨询：</td>
			<td align="center"><input type="radio" value="1"
				<s:if test="%{1==rank.rankIfSecCon}">checked</s:if>
				disabled="disabled" /> 有 <input type="radio" value="0"
				<s:if test="%{0==rank.rankIfSecCon}">checked</s:if>
				disabled="disabled" /> 无</td>
			<td align="center"><input type='radio'
				<s:if test="%{1==rank.rankSecConypeType}">checked</s:if> value="1"
				disabled="disabled" />
			</td>
			<td align="center"><input type='radio'
				<s:if test="%{2==rank.rankSecConypeType}">checked</s:if> value="2"
				disabled="disabled" />
			</td>
			<td align="center"><input type='radio'
				<s:if test="%{3==rank.rankSecConypeType}">checked</s:if> value="3"
				disabled="disabled" />
			</td>
		</tr>
		<tr>
			<td width="21%" style="text-align: center;">7</td>
			<td style="text-align: center;">安全培训：</td>
			<td align="center"><input type="radio" value="1"
				<s:if test="%{1==rank.rankIfSecTrain}">checked</s:if>
				disabled="disabled" /> 有 <input type="radio" value="0"
				<s:if test="%{0==rank.rankIfSecTrain}">checked</s:if>
				disabled="disabled" /> 无</td>
			<td align="center"><input type='radio'
				<s:if test="%{1==rank.rankSecTrainType}">checked</s:if> value="1"
				disabled="disabled" />
			</td>
			<td align="center"><input type='radio'
				<s:if test="%{2==rank.rankSecTrainType}">checked</s:if> value="2"
				disabled="disabled" />
			</td>
			<td align="center"><input type='radio'
				<s:if test="%{3==rank.rankSecTrainType}">checked</s:if> value="3"
				disabled="disabled" />
			</td>
		</tr>
		<tr>
			<td width="21%" style="text-align: center;">8</td>
			<td style="text-align: center;">其它： <span style="color: #C0C0C0"><s:property
						value='rank.rankOthSerName' />
			</span></td>
			<td align="center"><input type="radio" value="1"
				<s:if test="%{1==rank.rankIfOthSer}">checked</s:if>
				disabled="disabled" /> 有 <input type="radio" value="0"
				<s:if test="%{0==rank.rankIfOthSer}">checked</s:if>
				disabled="disabled" /> 无</td>
			<td align="center"><input type='radio'
				<s:if test="%{1==rank.rankOthUseType}">checked</s:if> value="1"
				disabled="disabled" />
			</td>
			<td align="center"><input type='radio'
				<s:if test="%{2==rank.rankOthUseType}">checked</s:if> value="2"
				disabled="disabled" />
			</td>
			<td align="center"><input type='radio'
				<s:if test="%{3==rank.rankOthUseType}">checked</s:if> value="3"
				disabled="disabled" />
			</td>
		</tr>
	</table>
	<table  width="100%" border="1" bordercolor="#5AA4D1" cellpadding="0" cellspacing="0">
		<tr >
			<td colspan="4" class='r2titler'>等级测评单位信息</td>
		</tr>
		<tr height="20">
			<td width="20%" class="tdLabel" align="right">单位名称：</td>
			<td width="30%"><input type="text" disabled="disabled"
				value="<s:property value='rank.rankEvalUnitName' />">
			</td>
			<td width="20%" align="right">运行使用时间：</td>
			<td width="30%"><input type="text" disabled="disabled"
				value="<s:property value='rank.rankUseDate' />">
			</td>
		</tr>
		<tr height="20">
			<td class="tdLabel" align="right">是否是分系统：</td>
			<td><input type="radio" value="1" disabled="disabled"
				<s:if test="%{1==rank.rankFlag}">checked</s:if> /> 是 <input
				type="radio" value="0" disabled="disabled"
				<s:if test="%{0==rank.rankFlag}">checked</s:if> /> 否</td>
			<td class="tdLabel" align="right">上级系统名称：</td>
			<td><input type="text" width="99%" disabled="disabled"
				value="<s:property value='rank.rankParentSysName' />"></td>
		</tr>
		<tr>
			<td style="text-align:center">上级系统所属单位：</td>
			<td><input type="text" disabled="disabled" value="<s:property value='rank.rankParentUnitName' />"></td>
			<td  align="right">相关附件：</td>
			<td  style="text-align: center"><s:property value='rank.rankEvalRelAccess' /></td>
		</tr>
	</table>
	<table  width="100%" border="1" bordercolor="#5AA4D1" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="4" align="center" class='r2titler'>信息系统定级情况</td>
		</tr>
		<tr>
			<td width="14%" align="right" class="tdLabel">01 信息系统安全保护等级：</td>
			<td >&ensp;<s:property value='rank.rankGrade' />
			<td align="right">02 定级时间：</td>
			<td>&ensp;<s:property value='rank.rankTime' />
			</td>
		</tr>
		<tr>
			<td class="tdLabel" align="right">03 专家评审情况：</td>
			<td>
				<div id="div2" class="article"></div> <input type="radio"
				<s:if test="%{1==rank.rankJudge}">checked</s:if> value="1"
				disabled="disabled">已评审 <input type="radio" value="0"
				<s:if test="%{0==rank.rankJudge}">checked</s:if> disabled="disabled">未评审
			</td>
			<td class="tdLabel" align="right">04 是否有主管部门：</td>
			<td><input type="radio" value="1"
				<s:if test="%{1==rank.rankIsDep}">checked</s:if> disabled="disabled">
				有 <input type="radio" value="0"
				<s:if test="%{0==rank.rankIsDep}">checked</s:if> disabled="disabled">无（如选择有请填下两项）
			</td>
		</tr>
		<tr>
			<td class="tdLabel" align="right">05 主管部门名称：</td>
			<td>
				<div id="div3" class="article"></div> <input type="text"
				value="<s:property  value='rank.rankDepName' />" disabled="disabled">
			</td>
			<td class="tdLabel" align="right">06 主管部门审批定级情况：</td>
			<td><input type="radio" value="1"
				<s:if test="%{1==rank.rankJudge}">checked</s:if> disabled="disabled">已审批
				<input type="radio" value="0"
				<s:if test="%{0==rank.rankJudge}">checked</s:if> disabled="disabled">未审批
			</td>
		</tr>
		<tr>
			<td class="tdLabel" align="right">07 系统定级报告：</td>
			<td><input type="radio"
				<s:if test="%{1==rank.rankDoc}">checked</s:if> disabled="disabled">
				有 <input type="radio" <s:if test="%{0==rank.rankDoc}">checked</s:if>
				disabled="disabled">无</td>
			<td class="tdLabel" align="right">定级报告：</td>
			<td>&ensp;<s:property value='rank.rankAccess' /></td>
		</tr>
		<tr>
			<td class="tdLabel" align="right">填表人：</td>
			<td>&ensp;<s:property value='rank.rankInformant' />
			</td>
			<td  align="right">填表日期：</td>
			<td>&ensp;<s:property value='rank.rankDate' />
			</td>
		</tr>
	</table>
	<div align="right">
  		<input type="button" value="返回"  class="btnyh" onclick="back()"> 
    </div>
</body>
</html>
