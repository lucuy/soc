<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function changeRecord(num) {
		document.getElementById('page').value = num;
		document.forms[0].submit();
	}
	function gopage(lastnum) {
		var gpage = document.getElementById('gpage').value;
		if (gpage > lastnum) {
			alert("错误页数");
			return false;
		}
		 
		if (gpage < 1){
			lastnum = parseInt(1);
		}else{
			lastnum = parseInt(gpage);
		}
			
		document.getElementById('page').value = lastnum;
		document.forms[0].submit();
	}
</script>
  <c:if test="${!empty Page}">
	<table align="right" style="font-size: 12px">
		<tr>
			<td>
			          共${Page.totalRows}记录 <input type="hidden" name="page" id="page" value="0"> 
			</td>
			<td>
			   <c:choose>
					<c:when test="${Page.page ne '1'}">
						<a href="javascript:changeRecord('1')">首页</a>
					</c:when>
					<c:otherwise>
						<!-- 首页 -->
						 首页
                     </c:otherwise>
				</c:choose>
			</td>
			<td>
 
			    <c:choose>
					<c:when test="${Page.page ne '1'}">
						<a href="javascript:changeRecord('${Page.page-1}')">上一页</a>
					</c:when>
					<c:otherwise>
					       上一页
					 </c:otherwise>
				</c:choose>
			 </td>
			<td>
			    <c:choose>
					<c:when test="${Page.page<Page.totalPages}">
						<a href="javascript:changeRecord('${Page.page+1}')">下一页</a>
					</c:when>
					<c:otherwise>
			    	下一页
			      </c:otherwise>
				</c:choose>
		  </td>
			<td>
			
			    <c:choose>
					<c:when test="${(Page.page eq Page.totalPages)||(Page.totalPages==1)}">
			       	末页
			        </c:when>
					<c:otherwise>
						<a href="javascript:changeRecord('${Page.totalPages}')">末页</a>
					</c:otherwise>
				</c:choose>
		    </td>
			<td>
			   <input type="text" class="showBorder" style="width: 30px" name="gpage" id="gpage" size="3" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"> 
			   <input type="button" value="GO" class="btn1" style="font-size: 12px" onclick="gopage('${Page.totalPages}')">
				当前第${Page.page}/${Page.totalPages}页
			</td>
			<td>
			
		</tr>
	</table>
</c:if>
      