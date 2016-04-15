package com.util.reportForm.struts.action;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.util.reportForm.model.TemplateQueryModel;
import com.util.reportForm.service.TemplateQueryService;

public class QueryStatAction extends DispatchAction {

	private TemplateQueryService queryService = new TemplateQueryService();
	private static SimpleDateFormat dateformat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	// 初始化页面
	public ActionForward initPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String rName = request.getParameter("queryForm");
		String rType = request.getParameter("rfType");
		String sTime = request.getParameter("startTime");
		String eTime = request.getParameter("endTime");

		if (sTime != null && !sTime.equals("") && eTime != null
				&& !eTime.equals("")) {
			sTime = String.valueOf(dateformat.parse(sTime).getTime());
			eTime = String.valueOf(dateformat.parse(eTime).getTime());
		}

		// 获得所有的模板信息组成的链表
		List<TemplateQueryModel> modelList = queryService.getQueryModelList(
				rName, rType, sTime, eTime);

		request.setAttribute("modelList", modelList);
		request.setAttribute("queryForm", rName);
		request.setAttribute("rfType", rType);
		request.setAttribute("startTime", request.getParameter("startTime"));
		request.setAttribute("endTime", request.getParameter("endTime"));

		return mapping.findForward("initPage");
	}

	public ActionForward queryStat(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 存放查询的参数
		Map<String, String> params = new HashMap<String, String>();

		// 获得所有的参数名称
		String[] paramStrArr = request.getParameterValues("params");

		// 获得参数值
		for (String param : paramStrArr) {
			String paramValue = request.getParameter(param);
			if (null != paramValue && !"".equals(param)) {
				params.put(param, paramValue);
			}
		}

		/* 拼接查询字符串，开始查询 */

		return mapping.findForward(null);
	}

	// public ActionForward test(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response)
	// throws Exception {
	// ArrayList<TestBean> list = new ArrayList<TestBean>();
	//		
	// for(int i=1; i< 11; i++){
	// TestBean bean = new TestBean();
	// bean.setName("name- " + i + " -");
	// List<String> namelist = new ArrayList<String>();
	// for(int j=11; j<16; j++){
	// namelist.add("name" + i + "*list*" + j);
	// }
	// bean.setNameList(namelist);
	// list.add(bean);
	// }
	// //System.out.println("size --- " + list.size());
	// for(TestBean t : list){
	// //System.out.println("-----------***************----------");
	// //System.out.println(t.getName());
	// List<String> myList = t.getNameList();
	// //System.out.println("{");
	// for(String s : myList){
	// //System.out.println(s+" , ");
	// }
	// //System.out.println("}");
	// //System.out.println("-----------***************----------");
	// }
	// request.setAttribute("list", list);
	// return mapping.findForward("test");
	// }

}
