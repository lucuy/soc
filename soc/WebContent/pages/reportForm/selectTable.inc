<bean:message key="pager.total"/>
<bean:write name="page" property="totalRows"/>
<bean:message key="pager.totalRows"/>

<logic:equal name="page" property="hasFirstPage" value="true">
<logic:equal name="page" property="currentPage" value="1">
<bean:message key="pager.firstpage"/>
</logic:equal>
<logic:greaterThan name="page" property="currentPage" value="1">
<a href="#" onclick="selectAll(document.SelectCom,'first');"><bean:message key="pager.firstpage"/></a>
</logic:greaterThan>
</logic:equal>

<logic:equal name="page" property="hasPreviousPage" value="false">
<bean:message key="pager.previouspage"/>
</logic:equal>
<logic:equal name="page" property="hasPreviousPage" value="true">
<a href="#" onclick="selectAll(document.SelectCom,'previousPage');"><bean:message key="pager.previouspage"/></a>
</logic:equal>

<logic:equal name="page" property="hasNextPage" value="false">
<bean:message key="pager.nextpage"/>
</logic:equal>
<logic:equal name="page" property="hasNextPage" value="true">
<a href="#" onclick="selectAll(document.SelectCom,'nextPage');"><bean:message key="pager.nextpage"/></a>
</logic:equal>

<logic:equal name="page" property="hasLastPage" value="true">
<logic:equal name="page" property="currentPage" value="${page.totalPages}">
<bean:message key="pager.lastpage"/>
</logic:equal>
<logic:lessThan name="page" property="currentPage" value="${page.totalPages}">
<a href="#" onclick="selectAll(document.SelectCom,'last');"><bean:message key="pager.lastpage"/></a>
</logic:lessThan>
</logic:equal>

<bean:message key="pager.total"/>
<bean:write name="page" property="totalPages"/>
<bean:message key="pager.page"/>
&nbsp;&nbsp;
<bean:message key="pager.current"/>
<bean:write name="page" property="currentPage"/>
<bean:message key="pager.page"/>&nbsp;&nbsp;


