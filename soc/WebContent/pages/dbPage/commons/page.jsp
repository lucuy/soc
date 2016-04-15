<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function changeRecord(num) {
		document.getElementById('startIndex').value = num;
		document.forms[0].submit();
	}
	function gopage(lastnum) {
		var page = document.getElementById('page').value;

		var num = parseInt(page * 15) - parseInt(15);

		//if(num>lastnum)num=lastnum;
		if (num > lastnum) {
			alert("错误页数");
			return false;
		}
		if (num < 0)
			num = 0;

		document.getElementById('startIndex').value = num;
		document.forms[0].submit();
	}
</script>
<c:if test="${!empty Page}">
	<table align="right" style="font-size: 12px">
		<tr>
			<td>
			          共${Page.totalCount}记录 <input type="hidden" name="startIndex" id="startIndex" value="0"> <input type="hidden" name="lastIndex" id="lastIndex" value='${Page.lastIndex}'>
			</td>
			<td>
			   <c:choose>
					<c:when test="${Page.startIndex ne '0'}">
						<a href="javascript:changeRecord('0')">首页</a>
					</c:when>
					<c:otherwise>
						<!-- 首页 -->
						 首页
                     </c:otherwise>
				</c:choose>
			</td>
			<td>
			    <c:choose>
					<c:when test="${Page.startIndex ne '0'}">
						<a href="javascript:changeRecord('${Page.previousIndex}')">上一页</a>
					</c:when>
					<c:otherwise>
					       上一页
					 </c:otherwise>
				</c:choose>
			</td>
			<td>
			    <c:choose>
					<c:when test="${Page.nextIndex>Page.startIndex}">
						<a href="javascript:changeRecord('${Page.nextIndex}')">下一页</a>
					</c:when>
					<c:otherwise>
			    	下一页
			      </c:otherwise>
				</c:choose>
		  </td>
			<td>
			    <c:choose>
					<c:when test="${Page.lastIndex eq Page.startIndex}">
			       	末页
			        </c:when>
					<c:otherwise>
						<a href="javascript:changeRecord('${Page.lastIndex}')">末页</a>
					</c:otherwise>
				</c:choose>
		    </td>
			<td>
			   <input type="text" class="showBorder" style="width: 30px" name="page" id="page" size="3" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"> 
			   <input type="button" value="GO" class="btn1" style="font-size: 12px" onclick="gopage('${Page.lastIndex}')">
				<c:if test="${Page.currentPage > 0 && Page.pageCount>0 }">当前第${Page.currentPage}/${Page.pageCount}页</c:if>
				<c:if test="${Page.currentPage <=0 && Page.pageCount<=0 }">当前第1/1页</c:if>
			</td>
			<td>
		</tr>
	</table>
</c:if>