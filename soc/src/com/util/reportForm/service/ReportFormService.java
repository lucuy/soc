package com.util.reportForm.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.util.reportForm.datadeal.BaseDao;
import com.util.reportForm.model.QueryModel;
import com.util.reportForm.model.ReportFormModel;
import com.util.reportForm.util.page.Pager;

public class ReportFormService {
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private BaseDao dao = new BaseDao();
	private Pager pager;
	
//	public void hqlTest(){
//		String hql = "";
//	}
	
	//拼接sql语句
	public QueryModel getQueryModl(HttpServletRequest request){
		// 模板名称
		String reportFormName = request.getParameter("reportFormName");
		
		// 模板类型
		String reportFormType = request.getParameter("reportFormType");
		
		// 开始时间
		String startTime = request.getParameter("startTime");
		
		// 结束时间
		String endTime = request.getParameter("endTime");
		
		//快速搜索
		String keyword = request.getParameter("keyword");
		
		// 构造hql语句
		StringBuilder queryBuilder = new StringBuilder();
		
		//存放查询参数
		Map params = new HashMap();
		queryBuilder
				.append("select new com.util.reportForm.model.ReportFormModel(f.id,f.reportFormType,f.reportFormId,f.reportFormName,f.createDate,f.reportFormDescription) from Reportforms f where");

		// 模板名称
		if (null != reportFormName && !reportFormName.trim().equals("")) {
			queryBuilder.append(" f.reportFormName like :reportFormName and ");
			params.put("reportFormName", "%" + reportFormName + "%");
		}

		// 模板类型
		if (null != reportFormType && !reportFormType.trim().equals("") && !reportFormType.trim().equals("all")) {
			queryBuilder.append(" f.reportFormType=:reportFormType and ");
			Integer reportFormTypeInt = Integer.parseInt(reportFormType);
			params.put("reportFormType", reportFormTypeInt);
		}

		// 开始时间和结束时间
		if ((null != startTime && !startTime.trim().equals("")) && null != endTime && !endTime.trim().equals("")) {
			Date startDate = null;
			Date endDate = null;
			Long startTimeLong = null; //开始时间对应的整数值
			Long endTimeLong = null;   //结束时间对应的整数值
			try {
				startDate = format.parse(startTime);
				endDate = format.parse(endTime);
				startTimeLong = startDate.getTime();
				endTimeLong = endDate.getTime();
			} catch (ParseException e) {
				startTimeLong = 0L;
				endTimeLong = 0L;
				//System.out.println(e.getMessage());
			}
			
			queryBuilder.append(" f.createDate>=:startTime and f.createDate<=:endTime and ");
			params.put("startTime", startTimeLong);
			params.put("endTime", endTimeLong);
		}
		
		//快速搜索
		if(null != keyword && !("").equals(keyword)) {
			/*Date startDate = null;
			Long startTimeLong = null; //开始时间对应的整数值


			try {
				startDate = format.parse(keyword);
				startTimeLong = startDate.getTime();
			} catch (ParseException e) {
				startTimeLong = 0L;
				//System.out.println(e.getMessage());
			}*/
			
			/*queryBuilder.append(" (f.reportFormName like :reportFormName or f.reportFormType=:reportFormType or f.createDate>=:startTime) and ");
			params.put("reportFormName", "%" + keyword + "%");
			params.put("reportFormType", keyword);
			params.put("startTime", keyword);*/
			
			/*String str = " (f.reportFormName like :reportFormName or f.createDate>=:startTime) ";
			params.put("reportFormName", "%" + keyword + "%");
			if("动".equals(keyword) || "动态".equals(keyword) || "动态模".equals(keyword) || "动态模板".equals(keyword)) {
				str = "( f.reportFormName like :reportFormName or f.reportFormType=:reportFormType or f.createDate>=:startTime) ";
				params.put("reportFormType", 0);
			} else if("静".equals(keyword) || "静态".equals(keyword) || "静态模".equals(keyword) || "静态模板".equals(keyword)) {
				str = " (f.reportFormName like :reportFormName or f.reportFormType=:reportFormType or f.createDate>=:startTime) ";
				params.put("reportFormType", 1);
			} */
			String str = " f.reportFormName like :reportFormName ";
			params.put("reportFormName", "%" + keyword + "%");
			if("动".equals(keyword) || "动态".equals(keyword) || "动态模".equals(keyword) || "动态模板".equals(keyword)) {
				str = "( f.reportFormName like :reportFormName or f.reportFormType=:reportFormType) ";
				params.put("reportFormType", 0);
			} else if("静".equals(keyword) || "静态".equals(keyword) || "静态模".equals(keyword) || "静态模板".equals(keyword)) {
				str = " (f.reportFormName like :reportFormName or f.reportFormType=:reportFormType) ";
				params.put("reportFormType", 1);
			} 
			queryBuilder.append(str + "and ");
			//params.put("reportFormType", keyword);
			//params.put("startTime", keyword);
		}

		//queryBuilder.append(" t.id=f.reportFormSort");
		queryBuilder.append(" 1=1");
//		queryBuilder.append("group by f.createDate order by f.createDate desc");
		String hql = queryBuilder.toString();
		QueryModel model = new QueryModel();
		model.setQueryhql(hql);
		model.setParams(params);
		return model;
	}
	
	//分页查询
	public List getQueryList(QueryModel model, HttpServletRequest request){
		List pageList = new ArrayList();
		//检查参数
		if(null == model){
			return pageList;
		}
		
		//获得hql语句
		String hql = model.getQueryhql();
		
		//获得参数
		Map params = model.getParams();
		
		// 分页
		int startRow = 0;
		int endRow = 0;
		int totalRows = 0;  //总行数
		int pageSize = 0;

		String action = request.getParameter("action");
		if (action == null || action.equals("null")) {
			pager = new Pager();
			pager.setPageSize(15);
		}
		String pageS = request.getParameter("page");
		Integer page = 0;
		if (pageS != null && !pageS.equals("null") && !pageS.equals("")) {
			page = Integer.parseInt(pageS);
		}
		totalRows = dao.getQueryCount(hql, params);
		pager.start(totalRows);

		pageSize = pager.getPageSize();
		if (action == null || action.equals("null")) {

			startRow = pager.getStartRow();
			if (totalRows > pageSize) {
				pager.setHasNextPage(true);
				pager.setEndRow(startRow + pageSize - 1);
			} else {
				pager.setEndRow(totalRows);
			}
			endRow = pager.getEndtRow();

			pageList = (List) dao.getQueryResult(hql, params, startRow, pageSize);

		} else {
			if (action == "previousPage" || action.equals("previousPage")) {

				pager.previous();
				startRow = pager.getStartRow();
				endRow = pager.getEndtRow();
				pageList = (List)dao.getQueryResult(hql, params, startRow, pageSize);

			}
			if (action == "nextPage" || action.equals("nextPage")) {

				pager.next();
				startRow = pager.getStartRow();
				endRow = pager.getEndtRow();
				pageList = (List)dao.getQueryResult(hql, params, startRow, pageSize);

			}
			if (action == "first" || action.equals("first")) {

				pager.first();
				startRow = pager.getStartRow();
				endRow = pager.getEndtRow();
				pageList = (List)dao.getQueryResult(hql, params, startRow, pageSize);

			}
			if (action == "last" || action.equals("last")) {

				pager.last();
				startRow = pager.getStartRow();
				endRow = pager.getEndtRow();
				pageList = (List)dao.getQueryResult(hql, params, startRow, pageSize);

			}
			if (action == "findPage" || action.equals("findPage")) {

				pager.find(page);
				startRow = pager.getStartRow();
				endRow = pager.getEndtRow();
				pageList = (List)dao.getQueryResult(hql, params, startRow, pageSize);
			}
		}
		List resultList = new ArrayList();
		for(Iterator it = pageList.iterator(); it.hasNext();){
			ReportFormModel formModel = (ReportFormModel)it.next();
			//设置符合格式的日期显示
			formModel.setDisplayDate(format.format(new Date(formModel.getCreateDate())));
			resultList.add(formModel);
		}
		
		return resultList;
	}

	public Pager getPager() {
		return pager;
	}
	
	
	

}
