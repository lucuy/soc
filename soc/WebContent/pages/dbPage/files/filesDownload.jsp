<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<html>
<head>
<LINK href="${ctx}/css/basic/css.css" type=text/css rel=stylesheet>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/sorttalbe.js"></script>
<script language="javascript">
</script>
</head> 
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<!-- 空行 -->
		<tr>
			<td height="2px"></td>
		</tr>
		<tr>
			<td>

				<div class=caozuobox>

					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr><c:if test="${filestype==1}">
					<td class="r2titler">
					 <b>文档下载--安全管理文档</b>&nbsp;&nbsp;&nbsp;
				    </td>
				    </c:if>
				    <c:if test="${filestype==11}">
				    <td class="r2titler">
					 <b>文档下载--信息删除证明</b>&nbsp;&nbsp;&nbsp;
				    </td>
				    </c:if>
				    <c:if test="${filestype==12}">
				    <td class="r2titler">
					 <b>文档下载--设备清除证明</b>&nbsp;&nbsp;&nbsp;
				    </td>
				    </c:if>
				    <c:if test="${filestype==13}">
				    <td class="r2titler">
					 <b>文档下载--存储清除证明</b>&nbsp;&nbsp;&nbsp;
				    </td>
				    </c:if>
				    <c:if test="${filestype==2}">
				    <td class="r2titler">
					 <b>文档下载--等级测评单位信息文档</b>&nbsp;&nbsp;&nbsp;
				    </td>
				    </c:if>
				    <c:if test="${filestype==3}">
				    <td class="r2titler">
					 <b>文档下载--系统定级报告</b>&nbsp;&nbsp;&nbsp;
					 
				    </td>
				    </c:if>
				    <c:if test="${filestype==4}">
				    <td class="r2titler">
					 <b>文档下载--系统拓扑结构说明文档</b>&nbsp;&nbsp;&nbsp;
					 
				    </td>
				    </c:if>
				    <c:if test="${filestype==5}">
				    <td class="r2titler">
					 <b>文档下载--系统安全组织机构及管理制度文档</b>&nbsp;&nbsp;&nbsp;
					 
				    </td>
				    </c:if>
				    <c:if test="${filestype==6}">
				    <td class="r2titler">
					 <b>文档下载--系统安全保护实施设计实施方案或改建实施方案</b>&nbsp;&nbsp;&nbsp;
					 
				    </td>
				    </c:if>
				    <c:if test="${filestype==7}">
				    <td class="r2titler">
					 <b>文档下载--系统使用的安全产品清单及认证、销售许可证明</b>&nbsp;&nbsp;&nbsp;
					 
				    </td>
				    </c:if>
				    <c:if test="${filestype==8}">
				    <td class="r2titler">
					 <b>文档下载--系统等级测评报告</b>&nbsp;&nbsp;&nbsp;
					 
				    </td>
				    </c:if>
				    <c:if test="${filestype==9}">
				    <td class="r2titler">
					 <b>文档下载--专家评审报告</b>&nbsp;&nbsp;&nbsp;
					 
				    </td>
				    </c:if>
				    <c:if test="${filestype==10}">
				    <td class="r2titler">
					 <b>文档下载--上级主管部门审批意见</b>&nbsp;&nbsp;&nbsp;
					 
				    </td>
				    </c:if>
			   </tr>
				</table>

				</div> <!-- toolbar area --></td>
		</tr>

		<tr>
			<td>
				<div class="sbox">
					<div class="cont">
						<!-- information area -->
						<table width="100%" border="0" cellspacing="1"
							id="table-userManage" cellpadding="0" class="tab2">
							<thead>
								<c:if test="${filestype==1}">
									<tr>
										<th onclick="sortAble('table-userManage', 0)" class="biaoti"
											style="cursor:pointer;width:20%">文档名称</th>
										<th onclick="sortAble('table-userManage', 1)" class="biaoti"
											style="cursor:pointer;width:20%">所属信息系统</th>
										<th style="width:30%" class="biaoti">主要内容</th>
										<th onclick="sortAble('table-userManage', 3)" class="biaoti"
											style="cursor:pointer;width:20%">重要程度</th>
									</tr>
								</c:if>
								<c:if test="${filestype==11||filestype==12||filestype==13}">
									<tr>
										<th width="30%" class="biaoti"
											onclick="sortAble('table-userManage', 0)"
											style="cursor:pointer">文档名称</th>
										<th width="20%" class="biaoti"
											onclick="sortAble('table-userManage', 1)"
											style="cursor:pointer">所属信息系统</th>
										<th width="50%" class="biaoti">文档说明</th>

									</tr>
								</c:if>
							</thead>
							
							<tbody id="tbody">
								<s:iterator value="abolishfiles" id="afiles">
									<tr>


										<td align="center"><c:if test="${filestype==11}">
												<a
													href="${ctx}/files/downloadfile.action?pkSysInfo=<s:property value="#afiles.pkSysInfo"/>&filename=<s:property value="#afiles.sysAccess" />&rename=<s:property value="#afiles.reSysAccess"/>&filetype=11"><s:property
														value="#afiles.sysAccess" />
												</a>
											</c:if> <c:if test="${filestype==12}">
												<a
													href="${ctx}/files/downloadfile.action?pkSysInfo=<s:property value="#afiles.pkSysInfo"/>&filename=<s:property value="#afiles.devAccess" />&rename=<s:property value="#afiles.reDevAccess"/>&filetype=12"><s:property
														value="#afiles.devAccess" />
												</a>
											</c:if> <c:if test="${filestype==13}">
												<a
													href="${ctx}/files/downloadfile.action?pkSysInfo=<s:property value="#afiles.pkSysInfo"/>&filename=<s:property value="#afiles.storAccess" />&rename=<s:property value="#afiles.reStorAccess"/>&filetype=13"><s:property
														value="#afiles.storAccess" />
												</a>
											</c:if></td>
										<td align="center"><s:property
												value="#afiles.abolishsysnames" /> <%-- ${sysnames} --%></td>
										<td align="center"><c:if test="${filestype==11}">
												<s:property value="#afiles.sysDescription" />
											</c:if> <c:if test="${filestype==12}">
												<s:property value="#afiles.devDescription" />
											</c:if> <c:if test="${filestype==13}">
												<s:property value="#afiles.storDescription" />
											</c:if></td>
									</tr>
								</s:iterator>
								<s:iterator value="rankfiles" id="rfiles">
									<tr>
										<td align="center"><c:if test="${filestype==2}">
												<a
													href="${ctx}/files/downloadfile.action?sysInfoId=<s:property value="#rfiles.sysInfoId"/>&filename=<s:property value="#rfiles.rankEvalRelAccess" />&filetype=2"><s:property
														value="#rfiles.rankEvalRelAccess" />
												</a>
											</c:if> <c:if test="${filestype==3}">
												<a
													href="${ctx}/files/downloadfile.action?sysInfoId=<s:property value="#rfiles.sysInfoId"/>&filename=<s:property value="#rfiles.rankAccess" />&filetype=3"><s:property
														value="#rfiles.rankAccess" />
												</a>
											</c:if> <c:if test="${filestype==4}">
												<a
													href="${ctx}/files/downloadfile.action?sysInfoId=<s:property value="#rfiles.sysInfoId"/>&filename=<s:property value="#rfiles.rankTopRelAcc" />&filetype=4"><s:property
														value="#rfiles.rankTopRelAcc" />
												</a>
											</c:if> <c:if test="${filestype==5}">
												<a
													href="${ctx}/files/downloadfile.action?sysInfoId=<s:property value="#rfiles.sysInfoId"/>&filename=<s:property value="#rfiles.rankSysManRel" />&filetype=5"><s:property
														value="#rfiles.rankSysManRel" />
												</a>
											</c:if> <c:if test="${filestype==6}">
												<a
													href="${ctx}/files/downloadfile.action?sysInfoId=<s:property value="#rfiles.sysInfoId"/>&filename=<s:property value="#rfiles.rankSysPlanRel" />&filetype=6"><s:property
														value="#rfiles.rankSysPlanRel" />
												</a>
											</c:if> <c:if test="${filestype==7}">
												<a
													href="${ctx}/files/downloadfile.action?sysInfoId=<s:property value="#rfiles.sysInfoId"/>&filename=<s:property value="#rfiles.rankSysLiceRel" />&filetype=7"><s:property
														value="#rfiles.rankSysLiceRel" />
												</a>
											</c:if> <c:if test="${filestype==8}">
												<a
													href="${ctx}/files/downloadfile.action?sysInfoId=<s:property value="#rfiles.sysInfoId"/>&filename=<s:property value="#rfiles.rankSysReportRel" />&filetype=8"><s:property
														value="#rfiles.rankSysReportRel" />
												</a>
											</c:if> <c:if test="${filestype==9}">
												<a
													href="${ctx}/files/downloadfile.action?sysInfoId=<s:property value="#rfiles.sysInfoId"/>&filename=<s:property value="#rfiles.rankPeerRevRel" />&filetype=9"><s:property
														value="#rfiles.rankPeerRevRel" />
												</a>
											</c:if> <c:if test="${filestype==10}">
												<a
													href="${ctx}/files/downloadfile.action?sysInfoId=<s:property value="#rfiles.sysInfoId"/>&filename=<s:property value="#rfiles.rankSuperOpinRel" />&filetype=10"><s:property
														value="#rfiles.rankSuperOpinRel" />
												</a>
											</c:if></td>
										<td align="center"><s:property
												value="#rfiles.sysInfoName" /></td>
									</tr>
								</s:iterator>
							</tbody>
							<tr id="fenye">
								<td width="100%" colspan="4"><jsp:include
										page="../commons/page.jsp"></jsp:include></td>
							</tr>
						</table>


					</div>
				</div>
			</td>
		</tr>

	</table>
</body>
</html>
