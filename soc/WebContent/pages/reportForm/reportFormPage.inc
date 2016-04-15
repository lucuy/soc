<bean:message key="pager.total"/>
<bean:write name="page" property="totalRows"/>
<bean:message key="pager.totalRows"/>

<logic:equal name="page" property="hasFirstPage" value="true">
<logic:equal name="page" property="currentPage" value="1">
<bean:message key="pager.firstpage"/>
</logic:equal>
<logic:greaterThan name="page" property="currentPage" value="1">
<html:link page="/reportFormQuery.do?method=pageSelect&reportFormName=${reportFormName }&reportFormType=${formType }&startTime=${startTime }&endTime=${endTime }&action=first"><bean:message key="pager.firstpage"/></html:link>
</logic:greaterThan>
</logic:equal>
<logic:equal name="page" property="hasPreviousPage" value="false">
<bean:message key="pager.previouspage"/>
</logic:equal>
<logic:equal name="page" property="hasPreviousPage" value="true">
<html:link page="/reportFormQuery.do?method=pageSelect&reportFormName=${reportFormName }&reportFormType=${formType }&startTime=${startTime }&endTime=${endTime }&action=previousPage"><bean:message key="pager.previouspage"/></html:link>
</logic:equal>

<logic:equal name="page" property="hasNextPage" value="false">
<bean:message key="pager.nextpage"/>
</logic:equal>
<logic:equal name="page" property="hasNextPage" value="true">
<html:link page="/reportFormQuery.do?method=pageSelect&reportFormName=${reportFormName }&reportFormType=${formType }&startTime=${startTime }&endTime=${endTime }&action=nextPage"><bean:message key="pager.nextpage"/></html:link>
</logic:equal>

<logic:equal name="page" property="hasLastPage" value="true">
<logic:equal name="page" property="currentPage" value="${page.totalPages}">
<bean:message key="pager.lastpage"/>
</logic:equal>
<logic:lessThan name="page" property="currentPage" value="${page.totalPages}">
<html:link page="/reportFormQuery.do?method=pageSelect&reportFormName=${reportFormName }&reportFormType=${formType }&startTime=${startTime }&endTime=${endTime }&action=last"><bean:message key="pager.lastpage"/></html:link>
</logic:lessThan>
</logic:equal>

<bean:message key="pager.total"/>
<bean:write name="page" property="totalPages"/>
<bean:message key="pager.page"/>
&nbsp;&nbsp;
<bean:message key="pager.current"/>
<bean:write name="page" property="currentPage"/>
<bean:message key="pager.page"/>&nbsp;&nbsp;
