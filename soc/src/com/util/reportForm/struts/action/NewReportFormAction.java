package com.util.reportForm.struts.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.util.reportForm.datadeal.BaseDao;
import com.util.reportForm.datadeal.model.Alltablefieldname;
import com.util.reportForm.datadeal.model.Alltablename;
import com.util.reportForm.datadeal.model.Reportforms;
import com.util.reportForm.datadeal.model.Reportformsinfo;
import com.util.reportForm.datadeal.model.Reportformstype;
import com.util.reportForm.framework.exceptions.DatastoreException;
import com.util.reportForm.model.ColConversion;
import com.util.reportForm.model.ExportColumnModel;
import com.util.reportForm.model.QueryModel;
import com.util.reportForm.service.ReportFormQueryService;
import com.util.reportForm.service.ReportFormService;
import com.util.reportForm.struts.form.DataSource;
import com.util.reportForm.util.JDBC;
import com.util.reportForm.util.hibernate.hibernateUtil.HibernateUtil;

public class NewReportFormAction extends DispatchAction {
	BaseDao dao = new BaseDao();
	private ReportFormService service = new ReportFormService();
	String modelname = "";
	String modeldescription = "";
	String modeltype = "";
	String modelcatogry = "";
	String reportinfo = "";
	// choosbletable这个列表 在定制输出列和设置查询条件页面中选择表的内容，此处设为公用的
	List choosbletable = new ArrayList();
	// 定制输出列串
	String outcols = "";

	// 跳转到模板基本信息页面

	// ReportFormsType表中获取模板类别
	public ActionForward creatReportFormStep1(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String hql = "from Reportformstype";
		Collection<Reportformstype> list = dao.getNamedQuery(hql, null);
		request.setAttribute("typelist", list);
		modelname = "";
		modeldescription = "";
		modeltype = "";
		modelcatogry = "";
		String flag = request.getParameter("flag");
		if (flag != null && !flag.equals("") && flag.equals("1")) {
			String[] reportinfos = reportinfo.split(";");
			if (reportinfos.length > 3) {
				modelname = reportinfos[0];
				modelcatogry = reportinfos[1];
				modeltype = reportinfos[2];
				modeldescription = reportinfos[3];
			} else {
				modelname = reportinfos[0];
				modelcatogry = reportinfos[1];
				modeltype = reportinfos[2];
			}
		}
		String isCheck1 = "";
		String isCheck2 = "";
		if (modeltype.equals("0")) {
			isCheck1 = "checked";
		}
		if (modeltype.equals("1")) {
			isCheck2 = "checked";
		}
		request.setAttribute("modelname", modelname);
		request.setAttribute("modelcatogry", modelcatogry);
		request.setAttribute("isCheck1", isCheck1);
		request.setAttribute("isCheck2", isCheck2);
		request.setAttribute("modeldescription", modeldescription);

		request.setAttribute("title", "新建模板");

		return mapping.findForward("step1");

	}

	// ajax验证ReportFormName是否存在
	public ActionForward exitReportFormName(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String sql = "from Reportforms";
		Map m = new HashMap();
		List<Reportforms> list = new ArrayList<Reportforms>();
		list = (List) dao.getNamedQuery(sql, m);
		Iterator<Reportforms> iter = list.iterator();
		int keywords = 0;
		while (iter.hasNext()) {
			Reportforms rf = new Reportforms();
			rf = iter.next();
			if (rf.getReportFormName().equals(name)) {
				keywords = 1;
			}
		}
		out.println(keywords);

		return null;
	}

	public ActionForward creatReportFormStep2(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 获得第一步中的新建模板的基本信息
		
		try {
			reportinfo = java.net.URLDecoder.decode(request.getParameter("reportinfo"), "UTF-8");
					//new String(request.getParameter("reportinfo").getBytes("ISO-8859-1"),"UTF-8");
			//System.out.println("reportinfo::"+reportinfo);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String[] reportinfos = reportinfo.split(";");
		if (reportinfos.length > 3) {
			modelname = reportinfos[0];
			modelcatogry = reportinfos[1];
			modeltype = reportinfos[2];
			modeldescription = reportinfos[3];
		} else {
			modelname = reportinfos[0];
			modelcatogry = reportinfos[1];
			modeltype = reportinfos[2];
		}
		request.setAttribute("reportinfo", reportinfo);
		// 一级菜单

		String hql1 = "from Tableorder where orderNum=1";
		List list1 = (List) dao.getNamedQuery(hql1, null);
		request.setAttribute("levelfirstlist", list1);
		// 三级菜单
 		String tableids = request.getParameter("levelthree");
		List list = new ArrayList();
		if (tableids != null && !tableids.equals("")) {
			String[] tas = tableids.split(";");
			String ids = "";
			for (int i = 0; i < tas.length; i++) {
				ids = ids + tas[i] + ",";
			}
			ids = ids.substring(0, ids.length() - 1);
			// 查询出表的中文名字

			String hql2 = "from Alltablename where id in(" + ids + ")";
			list = (List) dao.getNamedQuery(hql2, null);
		}

		// 二级菜单
		String levevtwo = request.getParameter("leveltwo");
		List list2 = new ArrayList();
		if (levevtwo != null && !levevtwo.equals("")) {
			String[] levevtwos = levevtwo.split(";");
			String levevt = "";
			for (int i = 0; i < levevtwos.length; i++) {
				levevt = levevt + levevtwos[i] + ",";
			}
			levevt = levevt.substring(0, levevt.length() - 1);
			String hqlt = "from Tableorder where id in(" + levevt + ")";
			list2 = (List) dao.getNamedQuery(hqlt, null);
		}
		request.setAttribute("levelthreelist", list);
		request.setAttribute("leveltwolist", list2);
		request.setAttribute("title", "筛选数据表");
		return mapping.findForward("step2");

	}

	public ActionForward creatReportFormStep3(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("reportinfo", reportinfo);

		// request.getParameter("tables")获得所选择的表
		// tableids 是Tableorder 表中的 id
		String tableids = request.getParameter("levelthree");
		String[] tas = tableids.split(";");
		String ids = "";
		for (int i = 0; i < tas.length; i++) {
			ids = ids + tas[i] + ",";
		}
		ids = ids.substring(0, ids.length() - 1);
		// 查询出表的中文名字

		String hql2 = "from Alltablename where id in(" + ids + ")";
		choosbletable = (List) dao.getNamedQuery(hql2, null);
		// 显示定制输出列中的内容

		Date date = new Date();
		Long reprotFromId = date.getTime();
		String arrays = request.getParameter("arrays");
		List list2 = new ArrayList();
		if (arrays != null && !arrays.equals("")) {
			arrays = arrays.substring(0, arrays.lastIndexOf(",|"));
			String[] temp = arrays.split(",\\|");
			String tempcol = "";
			for (int i = 0; i < temp.length; i++) {
				if (temp[i].startsWith(",")) {
					tempcol = temp[i].substring(1, temp[i].length());
				} else {
					tempcol = temp[i];
				}
				String[] tempcols = tempcol.split(",");
				ExportColumnModel em = new ExportColumnModel();
				em.setReportFormId(reprotFromId);
				em.setCoordx(request.getParameter("coordx"));
				em.setCoordy(request.getParameter("coordy"));
				em.setColName(tempcols[0]);
				em.setColWidth(tempcols[1]);
				em.setAlignType(Integer.valueOf(tempcols[2]));
				String al = "居左";
				if (tempcols[2].equals("0")) {
					al = "居左";
				}
				if (tempcols[2].equals("1")) {
					al = "居中";
				}
				if (tempcols[2].equals("2")) {
					al = "居右";
				}
				em.setAligndescription(al);
				em.setCorrespondingTable(Integer.valueOf(tempcols[3]));
				em.setTablename(tempcols[4]);
				String sql1 = "from Alltablename where id=" + tempcols[3];
				List l = (List) dao.getNamedQuery(sql1, null);
				String tdes = "";
				for (Iterator it = l.iterator(); it.hasNext();) {
					Alltablename a = (Alltablename) it.next();
					tdes = a.getTableNameDescription();
				}
				em.setTabledescription(tdes);
				em.setCorrespondingField(Integer.valueOf(tempcols[5]));
				em.setFieldname(tempcols[6]);
				String sql2 = "from Alltablefieldname where id=" + tempcols[5];
				String fdes = "";
				List ll = (List) dao.getNamedQuery(sql2, null);
				for (Iterator it = ll.iterator(); it.hasNext();) {
					Alltablefieldname a = (Alltablefieldname) it.next();
					fdes = a.getTableFieldDescription();
				}
				em.setFielddescription(fdes);
				em.setExportType(tempcols[7]);
				String outtypes = "";
				if (tempcols[7].equals("1")) {
					outtypes = "简单输出";
				}
				if (tempcols[7].equals("2")) {
					outtypes = "求和输出";
				}
				if (tempcols[7].equals("3")) {
					outtypes = "求记录数输出";
				}
				em.setExportdescription(outtypes);
				list2.add(em);
			}
		}
		request.setAttribute("exportcolumns", list2);
		request.setAttribute("tablelist", choosbletable);
		request.setAttribute("leveltwo", request.getParameter("leveltwo"));
		request.setAttribute("levelthree", request.getParameter("levelthree"));
		String coordx = request.getParameter("coordx");
		String coordy = request.getParameter("coordy");
		
		request.setAttribute("coordx", coordx);
		request.setAttribute("coordy", coordy);
		request.setAttribute("title", "定制输出列");

		return mapping.findForward("step3");
	}

	public ActionForward creatReportFormStep4(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("leveltwo", request.getParameter("leveltwo"));
		request.setAttribute("levelthree", request.getParameter("levelthree"));
		request.setAttribute("arrays", request.getParameter("arrays"));
		request.setAttribute("buttons", request.getParameter("buttons"));
		request.setAttribute("coordx", request.getParameter("coordx"));
		request.setAttribute("coordy", request.getParameter("coordy"));
		request.setAttribute("choosbletable", choosbletable);
		request.setAttribute("title", "设置查询条件");
		request.setAttribute("biaozhi", 2);
		return mapping.findForward("step5");

	}

	

	public ActionForward saveReportModel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		ReportFormQueryService queryService = new ReportFormQueryService();
		String reportFormId = request.getParameter("reportFormId");
		if (null != reportFormId && !"".equals(reportFormId)) {
			Reportforms re = (Reportforms) dao.get(Reportforms.class, Integer
					.parseInt(reportFormId));//获取原来的信息
			reportFormId = re.getReportFormId().toString();
		}
		String coordx=request.getParameter("coordx");
		String coordy=request.getParameter("coordy");
		Date date = new Date();
		Long datelong = date.getTime();
		if (reportFormId == null || reportFormId.equals("")) {
			reportFormId = Long.toString(datelong);
		}
		String getreportinfo;
		try {
			getreportinfo = java.net.URLDecoder.decode(request.getParameter("reportinfo"),"UTF-8");
			if(!"".equals(getreportinfo)&&getreportinfo!=null){
				if(!getreportinfo.equals(reportinfo)){
					reportinfo = getreportinfo;
				}
			}
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		
		String[] reportinfos = reportinfo.split(";");
		String modelname = "";
		String modeldescription = "";
		String modeltype = "";
		// String modelcatogry = "";
		if (reportinfos.length > 3) {
			modelname = reportinfos[0];
			modelcatogry = reportinfos[1];
			modeltype = reportinfos[2];
			modeldescription = reportinfos[3];
		} else {
			modelname = reportinfos[0];
			modelcatogry = reportinfos[1];
			modeltype = reportinfos[2];
		}
		String cond="";
		try {
			cond = java.net.URLDecoder.decode(request.getParameter("queryResults"),"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		if (null != cond && !"null".equals(cond) && !"".equals(cond)) {
			cond = cond.replace("％", "%");
		}

		String value = "";
		if (cond != null && cond.length() > 0) {
			String[] conds = cond.split("\\|\\|");
			for (int i = 0; i < conds.length; i++) {
				if (!"".equals(conds[i])) {
					String[] condss = conds[i].split(";");
					if (null != condss && !"".equals(condss)
							&& condss.length > 1) {
						value = value + (condss[1].split("\\$"))[0] + " ";
					}
				}
			}
		}

		String outcol = "";
		String tableids="";
		try {
			outcol = java.net.URLDecoder.decode(request.getParameter("arrays"),"UTF-8");
			tableids = java.net.URLDecoder.decode(request.getParameter("levelthree"),"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String[] tas = tableids.split(";");
		String ids = "";
		for (int i = 0; i < tas.length; i++) {
			ids = ids + tas[i] + ",";
		}
		ids = ids.substring(0, ids.length() - 1);

		// 查询出表的中文名字

		String hql2 = "from Alltablename where id in(" + ids + ")";
		List choosbletable = (List) dao.getNamedQuery(hql2, null);
		String temptable = "";
		for (Iterator it = choosbletable.iterator(); it.hasNext();) {
			Alltablename at = (Alltablename) it.next();
			temptable = temptable + at.getTableName() + ",";
		}
		// 模板的基本信息保存在ReportForms表中

		Reportforms ref = new Reportforms();
		ref.setReportFormId(Long.parseLong(reportFormId));
		ref.setReportFormName(modelname);
		String saveas = request.getParameter("saveas");
		if (saveas != null && !saveas.equals("")) {
			ref.setReportFormName(request.getParameter("asmodelname"));		
		}
		ref.setReportFormDescription(modeldescription);
		ref.setReportFormType(Integer.parseInt(modeltype));
		// ref.setReportFormSort(Integer.parseInt(modelcatogry));
		ref.setCreateDate(datelong);
		String biaozhi = request.getParameter("biaozhi");
		String sql = this.getSQL(request, outcol, tableids, value,biaozhi);  // 这是重点
		String[] arr = queryService.getCond(cond);
		ref.setReportFormSql(sql);
		ref.setSelTerm(cond == null || "null".equals(cond) ? "" : cond);
		ref.setTables(temptable);
		ref.setCoordx(coordx);
		ref.setCoordy(coordy);
		ref.setOrderby(arr[1]);
		
		// 定制的输出列保存在ReportFormsInfo表中

		// String outcol=request.getParameter("arrays");
		// 列名,列宽,对齐方式,对应表id,对应表,对应字段id,对应字段,输出方式
		String[] temp = outcol.split(",\\|");
		String tempcol = "";
		List list = new ArrayList();
		String cc = "";
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].startsWith(",")) {
				tempcol = temp[i].substring(1, temp[i].length());
			} else {
				tempcol = temp[i];
			}
			String[] tempcols = tempcol.split(",");
			Reportformsinfo rf = new Reportformsinfo();
			rf.setReportFormId(Long.parseLong(reportFormId));
			rf.setColName(tempcols[0]);
			rf.setColWidth(tempcols[1]);
			rf.setAlignType(Integer.valueOf(tempcols[2]));
			rf.setCorrespondingTable(Integer.valueOf(tempcols[3]));
			rf.setCorrespondingField(Integer.valueOf(tempcols[5]));
			rf.setExportType(tempcols[7]);
			list.add(rf);
				if (coordx.equals(tempcols[0])) {
					cc = tempcols[4] + "." + tempcols[6] + "," + cc;
				} else if (coordy.equals(tempcols[0])) {
					cc = cc + tempcols[4] + "." + tempcols[6] + ",";
				}
		}
		cc = cc + arr[2]; // 修改语句把 "" 加入
		if (cc.length() > 0) {
			ref.setGroupby(cc.substring(0, cc.length() - 1));
		}

		
		// 另存为
		if (saveas != null && !saveas.equals("")) {
			if(modelname!=null){
//				try {
//					//modelname = new String(request.getParameter("modelname").getBytes("ISO-8859-1"),"UTF-8");
//					reportinfo = java.net.URLDecoder.decode(request.getParameter("reportinfo"),"UTF-8");
//					
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
				ref.setReportFormName(modelname);
			}
			dao.save(ref);
			dao.saveAll(list);
			request.setAttribute("msg", "另存为成功，窗口将会在三秒钟内关闭！");
			// dao.saveAll(listbut);
			// 保存
		} else {
			String dhql1 = "delete from Reportforms where reportFormId='"
					+ reportFormId + "'";
			String dhql2 = "delete from Reportformsinfo where reportFormId='"
					+ reportFormId + "'";
			//String dhql3 = "delete from Buttoninfo where reportFormId='"+ reportFormId + "'";
			// //System.out.println("reportFormId:"+reportFormId);
			try {
				this.delOldMethod(dhql1);
				this.delOldMethod(dhql2);
				//this.delOldMethod(dhql3);
			} catch (DatastoreException e) {
				e.printStackTrace();
			}
			dao.save(ref);
			dao.saveAll(list);
		}

		// String typeHqlStr = "from Reportformstype order by id";

		// 获得所有的组态报表类型的列表
		// List typeList = (ArrayList) dao.getNamedQuery(typeHqlStr, null);

		QueryModel model = service.getQueryModl(request);

		// 存放查询的结果

		List pageList = service.getQueryList(model, request);

		// 日志类型列表
		// request.setAttribute("typeList", typeList);

		request.setAttribute("resultList", pageList);

		request.setAttribute("page", service.getPager());

		// 返回开始页面

		if (saveas != null && !saveas.equals("")) {
			return mapping.findForward("saveas");
		} else {
			return mapping.findForward("initPage");
		}

	}

	// and 计算机名称 != 'aa';and computer.ComputerName != 'aa'$1||
	// order by 计算机名称 asc ;order by computer.ComputerName asc $1||
	// group by 计算机名称;group by computer.ComputerName$1||

	public String[] getCond(String cond) {
		String[] selCond = new String[3];
		String orderby = "";
		String groupby = "";
		String condValue = "";
		if (cond != null && cond.length() > 0) {
			String[] conds = cond.split("\\|\\|");
			for (int i = 0; i < conds.length; i++) {
				String temp = conds[i].split("\\;")[1];
				if (!"".equals(conds[i])) {
					if (conds[i].indexOf("order by") != -1) {
						if (conds[i].indexOf(" desc ") != -1) {
							orderby += temp.substring(
									temp.indexOf("order by") + 9, temp
											.indexOf(" desc ") + 5)
									+ ",";
						} else if (conds[i].indexOf(" asc ") != -1) {
							orderby += temp.substring(
									temp.indexOf("order by") + 9, temp
											.indexOf(" asc ") + 4)
									+ ",";
						}
					} else if (conds[i].indexOf("group by") != -1) {
						groupby += temp.substring(temp.indexOf("group by") + 9)
								+ ",";
					} else {
						condValue += conds[i] + "||";
					}
				}
			}

		}
		if (!"".equals(condValue)) {
			selCond[0] = condValue.substring(0, condValue.length() - 1);
		} else {
			selCond[0] = "";
		}
		if (!"".equals(orderby)) {
			selCond[1] = orderby.substring(0, orderby.length() - 1);
		} else {
			selCond[1] = "";
		}
		if (!"".equals(groupby)) {
			selCond[2] = groupby; // .substring(0,groupby.length()-1);
		} else {
			selCond[2] = "";
		}
		return selCond;
	}

	// 拼查询串 
	public String getSQL(HttpServletRequest request, String outc,
			String tableids, String cond,String biaozhi) {
		StringBuffer strbHQL = new StringBuffer();
		// 获得查询条件
		// String cond=request.getParameter("");
		// 拼输出列 列名,列宽,对齐方式,对应表id,对应表,对应字段id,对应字段,输出方式
		// arr=l,a,居左(0左,1中,2右),1,Computer,1,computerName,简单输出,|
		// String outc=outcols.substring(0,outcols.lastIndexOf(",|"));
		// //System.out.println("oooooooooo="+outcols);
		// String outc=request.getParameter("arrays");

		String[] arrs = outc.split(",\\|");
		String temp = "";
		String tempcol = "";
		List collist = new ArrayList();
		boolean flag = false;
		for (int i = 0; i < arrs.length; i++) {
			if (arrs[i].startsWith(",")) {
				temp = arrs[i].substring(1, arrs[i].length());
			} else {
				temp = arrs[i];
			}
			String[] temps = temp.split(",");
			String export = "";
			if (temps[7].equals("1")) {
				export = "";
			}
			if (temps[7].equals("2")) {
				flag = true;
				export = "sum";
				// groupby = groupby + temps[4] + "." + temps[6] + ",";
			}
			if (temps[7].equals("3")) {
				flag = true;
				export = "count";
				// groupby = groupby + temps[4] + "." + temps[6] + ",";
			}
			if(biaozhi.equals("2")){
				tempcol = tempcol + export + "(" + temps[4] + "." + temps[6]
						+ ") \"" + temps[0] + "\",";
			}else{
				tempcol = tempcol + export + "(" + temps[4] + ".#" + temps[6]
						+ "#) \"" + temps[0] + "\",";
			}
			
			collist.add(temps[0]);

		}
		// 多少列

		request.setAttribute("colnumlist", collist);
		// 去掉字符串最后的逗号
		tempcol = tempcol.substring(0, tempcol.length() - 1);
		// 拼查询的数据库表
		String temptable = "";
		// String tableids=request.getParameter("levelthree");
		String[] tas = tableids.split(";");
		String ids = "";
		for (int i = 0; i < tas.length; i++) {
			ids = ids + tas[i] + ",";
		}
		ids = ids.substring(0, ids.length() - 1);

		// 查询出表的中文名字

		String hql2 = "from Alltablename where id in(" + ids + ")";
		choosbletable = (List) dao.getNamedQuery(hql2, null);
		for (Iterator it = choosbletable.iterator(); it.hasNext();) {
			Alltablename at = (Alltablename) it.next();
			if (temptable.indexOf(at.getTableName()) != -1) {
				continue;
			}

			temptable = temptable + at.getTableName() + " " + at.getTableName()
					+ ",";

		}
		// 去掉字符串最后的逗号
		temptable = temptable.substring(0, temptable.length() - 1);
		// if (flag) {
		// groupby = groupby.substring(0, groupby.length() - 1);
		// } else {
		// groupby = "";
		// }
		strbHQL.append("select " + tempcol + " from " + temptable);
		return strbHQL.substring(0);
	}

	// 临时查询
	public ActionForward queryTempRes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		String cond111 = null;
		try {
			cond111 = java.net.URLDecoder.decode(request.getParameter("queryResults"),"UTF-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		cond111 = cond111.replaceAll("％", "%");
		String outc = request.getParameter("arrays");

		String tableids = request.getParameter("levelthree");
		ReportFormQueryService queryService = new ReportFormQueryService();
		String arr[] = queryService.getCond(cond111);
		String cond = arr[0];
		String groupbycol = arr[2];
		String orderby = arr[1];
		String having = arr[3];

		if (having.indexOf("and") > -1) {
			having = having.replaceAll("and", ") and (");
			having = having.substring(having.indexOf(")") + 1, having.length())
					+ ")";
		}

		if (having.indexOf("？") > -1) {
			having = having.replaceAll("？", "'' or 1=1 ");
		}

		if (!"".equals(orderby)) {
			orderby = " order by " + orderby;
		}

		if (!"".equals(having)) {
			having = " having 1=1" + having;
		}
		
		String biaozhi = request.getParameter("biaozhi");
		String strbHQL = this.getSQL(request, outc, tableids, cond,biaozhi);

		// 联想版本内容
		// 拦截事件变更信息，替换查询条件中的主类型名
		if (cond.indexOf("client_change_info.MajorType") > -1) {
			String[] temp = cond.split("client_change_info.MajorType");
			cond = temp[0];
			for (int i = 0; i < temp.length; i++) {
				if (i > 0) {
					String old = temp[i].substring(temp[i].indexOf(" '") + 2,
							temp[i].indexOf("'$"));
					String news = "";
					if (!old.equals("？")) {
						Iterator<Entry<String, String>> set = new DataSource()
								.getMajorType().entrySet().iterator();
						while (set.hasNext()) {
							Entry<String, String> entry = set.next();
							if (entry.getValue().equals(old)) {
								news = entry.getKey();
							}
						}
					}
					temp[i] = temp[i].replaceFirst(old, news);
					cond += ("client_change_info.MajorType" + temp[i]);
				}
			}
		}
		// 拦截事件变更信息，替换查询条件中的次类型名
		if (cond.indexOf("client_change_info.MinorType") > -1) {
			String[] temp = cond.split("client_change_info.MinorType");
			cond = temp[0];
			for (int i = 0; i < temp.length; i++) {
				if (i > 0) {
					String old = temp[i].substring(temp[i].indexOf(" '") + 2,
							temp[i].indexOf("'$"));
					String news = "";
					if (!old.equals("？")) {
						Collection<Map<String, String>> list = new DataSource()
								.getMap().values();
						for (Map<String, String> m : list) {
							Iterator<Entry<String, String>> set = m.entrySet()
									.iterator();
							while (set.hasNext()) {
								Entry<String, String> entry = set.next();
								if (entry.getValue().equals(old)) {
									news = entry.getKey();
								}
							}
						}
					}
					temp[i] = temp[i].replaceFirst(old, news);
					cond += ("client_change_info.MinorType" + temp[i]);
				}
			}
		}
		// 拦截事件变更信息，替换查询条件中的变化类型
		if (cond.indexOf("client_change_info.ChangeType") > -1) {
			String[] temp = cond.split("client_change_info.ChangeType");
			cond = temp[0];
			for (int i = 0; i < temp.length; i++) {
				if (i > 0) {
					String old = temp[i].substring(temp[i].indexOf(" '") + 2,
							temp[i].indexOf("'$"));
					String news = "";
					if (old.equals("增加")) {
						news = "0";
					} else if (old.equals("删除")) {
						news = "1";
					} else if (old.equals("修改")) {
						news = "2";
					} else {
						news = old;
					}
					temp[i] = temp[i].replaceFirst(old, news);
					cond += ("client_change_info.ChangeType" + temp[i]);
				}
			}
		}

		String value = "";
		if (cond != null && cond.length() > 0) {

			String[] conds = cond.split("\\|\\|");
			for (int i = 0; i < conds.length; i++) {
				String[] condss = conds[i].split(";");
				String _value = condss[1].split("\\$")[0];
				// 如果条件值为空，去除条件
				if (queryService.isEmpty(_value.toCharArray())) {
					continue;
				}
				value = value + (condss[1].split("\\$"))[0] + " ";
			}
		}

		String tableNames = "";
		String[] tempTable = strbHQL.substring(strbHQL.indexOf(" from ") + 6)
				.split(",");
		for (String s : tempTable) {
			String[] temp = s.split(" ");
			tableNames += (temp[0] + ",");
		}
		String tableLink = queryService.tableLink(tableNames);
		strbHQL = strbHQL + " where 1=1 " + tableLink + value;

		String strSQL = "";
		if (!"".equals(groupbycol) && null != groupbycol) {
			strSQL = strbHQL + " group by "
					+ groupbycol.substring(0, groupbycol.length() - 1);
		} else {
			if (strbHQL.indexOf(" count") != -1
					|| strbHQL.indexOf(" sum") != -1) {
				strSQL = strbHQL + " group by null";
			} else {
				strSQL = strbHQL;
			}
		}

		strSQL += having + orderby;

		strSQL = strSQL + " limit 1000";
		strSQL = strSQL.replace("#", "\"");
		JDBC db = new JDBC();
		// 设置总数
		int totalRows = 0;
		String totalRowCount = "select count(1) "
				+ strSQL.substring(strSQL.indexOf(" from "));
		
		try {
			//数据库适配
			ResultSet rs = db.getResultSet(totalRowCount);
			if (totalRowCount.indexOf(" group by ") != -1) {
				rs.last();
				totalRows = rs.getRow();
			} else {
				rs.next();
				totalRows = rs.getInt(1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			db.close();
		}

		// 获得列表的列名
		String titlename = "";

		List titlelist = new ArrayList();

		// 拦截服务器日志，在查询结果中标记日志类型
		String event = "";
		if (strSQL.indexOf("(serverlog.eventid)") > -1) {
			event = strSQL
					.substring(strSQL.indexOf("(serverlog.eventid)") + 21,
							strSQL.length()).split("\"")[0];
		}
		// 联想版内容
		// 拦截事件变更信息，在查询结果中标记主类型名
		String major = "";
		if (strSQL.indexOf("(client_change_info.MajorType)") > -1) {
			major = strSQL.substring(
					strSQL.indexOf("(client_change_info.MajorType)") + 32,
					strSQL.length()).split("\"")[0];
		}
		// 拦截事件变更信息，在查询结果中标记次类型名
		String minor = "";
		if (strSQL.indexOf("(client_change_info.MinorType)") > -1) {
			minor = strSQL.substring(
					strSQL.indexOf("(client_change_info.MinorType)") + 32,
					strSQL.length()).split("\"")[0];
		}
		// 拦截时间变更信息，在查询结果中标记变化类型
		String type = "";
		if (strSQL.indexOf("(client_change_info.ChangeType)") > -1) {
			type = strSQL.substring(
					strSQL.indexOf("(client_change_info.ChangeType)") + 33,
					strSQL.length()).split("\"")[0];
		}

		// 拼输出列 列名,列宽,对齐方式,对应表id,对应表,对应字段id,对应字段,输出方式
		String[] temp = outc.split(",\\|");
		for (int i = 0; i < temp.length; i++) {
			String[] temp2 = temp[i].split(",");
			String align = "";
			if (temp2[2].equals("0")) {
				align = "left";
			}
			if (temp2[2].equals("1")) {
				align = "center";
			}
			if (temp2[2].equals("2")) {
				align = "right";
			}
			titlename = titlename + temp2[0] + "," + temp2[1] + "," + temp2[2]
					+ "||";
			String coltitle = "<td class=\"biaoti\" width='"
					+ temp2[1]
					+ "' align=\""
					+ align
					+ "\" title=\""
					+ temp2[0]
					+ "\">"
					+ temp2[0] + "</td>";
			titlelist.add(coltitle);
		}

		request.setAttribute("titlelist", titlelist);
		

		// 数据
		String[] titlenames = null;
		List datalist = new ArrayList();
		try {
			ResultSet rs = db.getResultSet(strSQL);
			List li = null;
			titlenames = titlename.split("\\|\\|");

			while (rs.next()) {
				li = new ArrayList();
				for (int i = 0; i < titlenames.length; i++) {
					List listt = new ArrayList();
					String[] titles = titlenames[i].split(",");
					String colnametemp = (titles[0]);
					String content = rs.getString(colnametemp);
					String wid = titles[1];
					String alg = titles[2];
					// 进行服务器日志类型查询结果替换
					if (content != null && colnametemp.equals(event)) {
						ColConversion conversion = new ColConversion();
						conversion.setColName(ColConversion.eventId);
						content = conversion.getColValue(content);
					}
					// 联想版内容
					// 进行事件变更信息 变更事件主类型查询结果替换
					if (content != null && colnametemp.equals(major)) {
						Map<String, String> map = new DataSource()
								.getMajorType();
						content = map.get(content);
					}
					// 进行事件变更信息 变更事件次类型查询结果替换
					if (content != null && colnametemp.equals(minor)) {
						Map<String, Map<String, String>> map = new DataSource()
								.getMap();
						Collection<Map<String, String>> list = map.values();
						for (Map<String, String> m : list) {
							if (m.containsKey(content.toLowerCase())) {
								content = m.get(content.toLowerCase());
								break;
							}
						}
					}
					// 进行时间变更信息 变更类型查询结果替换
					if (content != null && colnametemp.equals(type)) {
						if (content.equals("0")) {
							content = "增加";
						} else if (content.equals("1")) {
							content = "删除";
						} else {
							content = "修改";
						}
					}
					// 表格的对齐方式
					String align = "";
					if (alg.equals("0")) {
						align = "left";
					}
					if (alg.equals("1")) {
						align = "center";
					}
					if (alg.equals("2")) {
						align = "right";
					}

					String tdcontent = "<td width='"
							+ wid
							+ "' align='"
							+ align
							+ "' title='"
							+ content
							+ "'>"
							+ "<div style='overflow: hidden;text-overflow:ellipsis;width:"
							+ (Integer.valueOf(wid) * 0.9)
							+ "px;white-space:nowrap'>" + content
							+ "</div></td>";
					listt.add(tdcontent);
					li.add(listt);
				}
				datalist.add(li);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		// 如果预览数据大于1000条提示

		if (totalRows > 1000) {
			List li = new ArrayList();
			for (int i = 0; i < titlenames.length; i++) {
				List lii = new ArrayList();
				if (i == titlenames.length / 2) {
					lii.add("您的数据大于1000条，请进入查询页面查询。");
				} else {
					lii.add("");
				}
				li.add(lii);
			}
			datalist.add(li);
		}
		request.setAttribute("titleNum", titlelist.size());
		request.setAttribute("datalist", datalist);
		return mapping.findForward("query");

	}

	// 删除
	public void delOldMethod(String hql) throws DatastoreException {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.executeUpdate();
			tx.commit();
		} catch (HibernateException ex) {
			HibernateUtil.rollbackTransaction(tx);
			throw DatastoreException.datastoreError(ex);
		} finally {
			// No matter what, close the session
			HibernateUtil.closeSession(session);
		}
	}

	// 跳到另存为模板页面

	public ActionForward saveAsReportModel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("coordx", request.getParameter("coordx"));
		request.setAttribute("coordy", request.getParameter("coordy"));
		String reportinfo = request.getParameter("reportinfo");
		request.setAttribute("reportinfo", request.getParameter("reportinfo"));
		request.setAttribute("leveltwo", request.getParameter("leveltwo"));
		request.setAttribute("levelthree", request.getParameter("levelthree"));
		request.setAttribute("arrays", request.getParameter("arrays"));
		request.setAttribute("queryResults", request
				.getParameter("queryResults"));
		request.setAttribute("saveas", request.getParameter("saveas"));
		return mapping.findForward("saveas");

	}

	// 要统计显示的列

	private String getCol(String hqls) {
		String tempSql = "";
		if (hqls.indexOf("count") != -1 || hqls.indexOf("sum") != -1) {
			String s = hqls.substring(0, hqls.indexOf("from")).substring(
					"select".length() + 1);
			String[] str = s.split("\\,");
			String col_dispalys = "";
			for (String col_display : str) {
				if (col_display.indexOf("count") != -1) {
					tempSql += col_display + " ,";
					col_dispalys += col_display.substring("count".length())
							+ " ,";
				} else if (col_display.indexOf("sum") != -1) {
					tempSql += col_display + " ,";
					col_dispalys += col_display.substring("sum".length())
							+ " ,";
				} else {
					col_dispalys += col_display + " ,";
				}
			}
			hqls = "select "
					+ col_dispalys.substring(0, col_dispalys.length() - 1)
					+ hqls.substring(hqls.indexOf("from"));
		}
		return tempSql + "|" + hqls;
	}

}
