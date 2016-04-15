package com.util.reportForm.struts.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.util.reportForm.datadeal.BaseDao;
import com.util.reportForm.datadeal.model.Alltablefieldname;
import com.util.reportForm.util.AjaxUtil;



public class AjaxQueryColAction extends DispatchAction {
	BaseDao dao=new BaseDao();
	//格式如下：列名1,id1$字段类型1|列名2,id2$字段类型2 
	//如： 计算机名称,1$1|设备金额,2$0|领用人,3$1
	public ActionForward getColumQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String tableid=request.getParameter("tableid");		
		//根据表的id tableid 从AllTableFieldName中查出此表中的所以字段
		String hql="from Alltablefieldname at where at.tableNameId=:tableid";
		Map map=new HashMap();
		map.put("tableid", Integer.parseInt(tableid));
		String resultStr = "";
		StringBuilder sBuilder = new StringBuilder();
		if(tableid!=null && !tableid.equals("")){
			List<Alltablefieldname> fieldlist=(List<Alltablefieldname>)dao.getNamedQuery(hql, map);
			for(Alltablefieldname item : fieldlist){
				//sBuilder.append(item.getTableFieldDescription()).append(",").append(item.getId()).append(",").append("\""+item.getTableFieldName()+"\"").append("$").append(item.getTableFieldType()).append("|");
				item.setTableFieldName("#"+item.getTableFieldName()+"#");
				sBuilder.append(item.getTableFieldDescription()).append(",").append(item.getId()).append(",").append(item.getTableFieldName()).append("$").append(item.getTableFieldType()).append("|");
			}
			resultStr=sBuilder.substring(0,sBuilder.lastIndexOf("|"));
		}
		
		AjaxUtil.responseWrite(response, resultStr);
		return null;
		
	}
}
