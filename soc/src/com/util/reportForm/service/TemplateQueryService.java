package com.util.reportForm.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.util.reportForm.datadeal.BaseDao;
import com.util.reportForm.datadeal.model.Reportforms;
import com.util.reportForm.datadeal.model.Reportformstype;
import com.util.reportForm.model.TemplateQueryModel;

public class TemplateQueryService {
	BaseDao dao = new BaseDao();

	/**
	 * 查找所有的返回模板以及对应的组态报表及组态报表的详细信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TemplateQueryModel> getQueryModelList(String rName,
			String rType, String sTime, String eTime) {
		List<TemplateQueryModel> queryModelList = new ArrayList<TemplateQueryModel>();

		// 获得所有组态报表模板
		// 屏蔽掉模板类型
		//
		// List<Reportformstype> typeList = getAllTypesList();
		//
		// // 获得每个模板类型对应的组态报表
		//
		// for (Reportformstype type : typeList) {
		//
		// TemplateQueryModel model = new TemplateQueryModel();
		// // 获得模板类型Id
		// //model.setTypeId((long) type.getId());
		//
		// // 获得模板类型名称
		// //model.setTemplateName(type.getReportType());
		//
		// // 获得对应模板类型的组态报表
		//
		// List<Reportforms> formsTempList = getReportformsList((long) type
		// .getId());
		// List<Reportforms> formsList = new ArrayList<Reportforms>();
		// for (Reportforms r : formsTempList) {
		// // 去掉查询条件中的引号
		// String selTerm = r.getSelTerm().replace("'", "");
		// r.setSelTerm(selTerm);
		// formsList.add(r);
		// }
		// model.setReportFormList(formsList);
		//
		// // 把构造的model加入到List中
		//
		// queryModelList.add(model);
		// }
		Map<String, String> map = new HashMap<String, String>();
		
		String hql = "from Reportforms where";
		
		if(rName != null && !rName.equals("")){
			map.put("rname", "%"+rName+"%");
			hql += " reportFormName like :rname and";
		}
		if(rType != null && !rType.equals("") && !rType.equals("all")){
			map.put("rtype", rType);
			hql += " reportFormType =:rtype and";
		}
		if(sTime != null && !sTime.equals("") && eTime != null && !eTime.equals("")){
			map.put("stime", sTime);
			map.put("etime", eTime);
			hql += " createDate between :stime and :etime and";
		}
		
		hql += " 1=1 order by createDate desc";

		List<Reportforms> list = (List<Reportforms>) dao.getNamedQuery(hql,
				map);
		List<Reportforms> formsList = new ArrayList<Reportforms>();

		TemplateQueryModel model = new TemplateQueryModel();
		for (Reportforms r : list) {
			// 去掉查询条件中的引号
			String selTerm = r.getSelTerm().replace("'", "");
			r.setSelTerm(selTerm);
			formsList.add(r);
		}
		model.setReportFormList(formsList);

		queryModelList.add(model);

		return queryModelList;
	}

	/**
	 * 获得所有组态报表模板
	 * 
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Reportformstype> getAllTypesList() {
		// 获得所有组态报表模板
		String typeHql = "from Reportformstype order by id";
		List<Reportformstype> typeList = (List<Reportformstype>) dao
				.getNamedQuery(typeHql, null);
		return typeList;
	}

	/**
	 * 根据模板类型查找该类型所有的组态报表模板
	 * 
	 * 
	 * @param templateId
	 *            模板类型的id
	 * @return 该模板类型的对应的所有组态报表模板
	 */
	@SuppressWarnings("unchecked")
	public List<Reportforms> getReportformsList(Long templateId) {

		// 参数校验
		if (null == templateId) {
			return new ArrayList<Reportforms>();
		}

		// 查询语句
		String hql = "from Reportforms where reportFormSort=:reportFormId order by id";

		// 设置参数
		Map params = new HashMap();
		params.put("reportFormId", templateId);
		List<Reportforms> reportFormsList = (List<Reportforms>) dao
				.getNamedQuery(hql, params);

		return reportFormsList;
	}

	/**
	 * 根据模板id查找该模板的详细信息
	 * 
	 * @return
	 */
	// public List<Reportformsinfo> getReportFormsInfoList(){
	//		
	// //查询语句
	// String hql = "from Reportformsinfo order by id";
	//		
	// Map params = new HashMap();
	//		
	// List<Reportformsinfo> formsList =
	// (List<Reportformsinfo>)dao.getNamedQuery(hql, params);
	//		
	// return formsList;
	// }
	/**
	 * 根据模板id查找该模板的详细信息
	 * 
	 * @param reportFormId
	 * @return
	 */
	// public List<Reportformsinfo> getReportFormsInfoList(Long reportFormId){
	//		
	// //参数检验	// if(null == reportFormId){
	// return new ArrayList<Reportformsinfo>();
	// }
	// //查询语句
	// String hql = "from Reportformsinfo where reportFormId=:reportFormId order
	// by id";
	//		
	// Map params = new HashMap();
	// params.put("reportFormId", reportFormId);
	//		
	// List<Reportformsinfo> formsList =
	// (List<Reportformsinfo>)dao.getNamedQuery(hql, params);
	// //System.out.println("reportFormId-- " + reportFormId + " -resSize-" +
	// formsList.size());
	// return formsList;
	// }
}
