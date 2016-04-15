package com.util.reportForm.struts.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.util.reportForm.datadeal.BaseDao;
import com.util.reportForm.datadeal.model.Alltablefieldname;
import com.util.reportForm.datadeal.model.Alltablename;
import com.util.reportForm.datadeal.model.Reportforms;
import com.util.reportForm.datadeal.model.Reportformsinfo;
import com.util.reportForm.datadeal.model.Reportformstype;
import com.util.reportForm.datadeal.model.Tableorder;
import com.util.reportForm.model.ExportColumnModel;

public class EditReportFormAction extends DispatchAction {
	BaseDao dao = new BaseDao();
	private String reportFormId = "";
	// 存放用戶可選的表名

	List<Alltablename> choosbletable = new ArrayList<Alltablename>();

	public ActionForward editReportFormStep1(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		reportFormId = request.getParameter("reportFormId");
		request.setAttribute("reportFormId", request
				.getParameter("reportFormId"));
		// 模板类别
		String hql = "from Reportformstype";
		Collection<Reportformstype> list = dao.getNamedQuery(hql, null);
		request.setAttribute("typelist", list);

		String flag = request.getParameter("flag");

		String modelname = "";
		String modeldescription = "";
		int modeltype = 0;
		if (flag == null || flag.equals("")) {
			String hql2 = "from Reportforms where id=" + reportFormId;
			Collection<Reportforms> Reportformslist = dao.getNamedQuery(hql2, null);
			Reportforms rf = (Reportforms) Reportformslist.iterator().next();
			modelname = rf.getReportFormName();
			// 发送模板名称用于AJAx效验
			request.setAttribute("modelname1", modelname);

			modeldescription = rf.getReportFormDescription();
			modeltype = rf.getReportFormType();
			// modelcatogry = rf.getReportFormSort();
		}

		if (flag != null && !flag.equals("") && flag.equals("1")) {
			// 发送模板名称用于AJAx效验
			String modelname1 = request.getParameter("modelname1");
			request.setAttribute("modelname1", modelname1);

			String reportinfo = request.getParameter("reportinfo");
			String[] reportinfos = reportinfo.split(";");
			if (reportinfos.length > 3) {
				modelname = reportinfos[0];
				// modelcatogry = Integer.parseInt(reportinfos[1]);
				modeltype = Integer.parseInt(reportinfos[2]);
				modeldescription = reportinfos[3];
			} else {
				modelname = reportinfos[0];
				// modelcatogry = Integer.parseInt(reportinfos[1]);
				modeltype = Integer.parseInt(reportinfos[2]);
			}
		}
		String isCheck1 = "";
		String isCheck2 = "";
		if (modeltype == 0) {
			isCheck1 = "checked";
		}
		if (modeltype == 1) {
			isCheck2 = "checked";
		}
		request.setAttribute("reportinfo", request.getParameter("reportinfo"));
		request.setAttribute("modelname", modelname);
		// request.setAttribute("modelcatogry", modelcatogry);
		request.setAttribute("isCheck1", isCheck1);
		request.setAttribute("isCheck2", isCheck2);
		request.setAttribute("modeldescription", modeldescription);
		request.setAttribute("useraction", "editReport");

		request.setAttribute("title", "编辑模板");
		return mapping.findForward("step1");

	}

	public ActionForward editReportFormStep2(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String reportinfo= "";
		try {
			reportinfo = java.net.URLDecoder.decode(request.getParameter("reportinfo"), "UTF-8");
					//new String(request.getParameter("reportinfo").getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setAttribute("reportinfo", reportinfo);
		reportFormId = request.getParameter("reportFormId");
		request.setAttribute("reportFormId", request.getParameter("reportFormId"));
		String hql = "from Reportforms where id=" + reportFormId;
		Collection<Reportforms> Reportformslist = dao.getNamedQuery(hql, null);
		String tables = "";
		if (Reportformslist.size() != 0) {
			Reportforms rf = (Reportforms) Reportformslist.iterator().next();
			tables = rf.getTables();
		}
		String[] table = tables.split(",");
		String tabless = "";
		for (int i = 0; i < table.length; i++) {
			tabless = tabless + "'" + table[i] + "',";
		}
		tabless = tabless.substring(0, tabless.length() - 1);
		String hql2 = "from Alltablename where tableName in(" + tabless + ")";
		// 三级菜单
		Collection<Alltablename> list = dao.getNamedQuery(hql2, null);
		String ids = "";
		for (Iterator<Alltablename> it = list.iterator(); it.hasNext();) {
			Alltablename a = it.next();
			ids = ids + "'" + a.getId() + "',";
		}
		ids = ids.substring(0, ids.length() - 1);
		String hql3 = "from Tableorder where name in(" + ids + ")";
		Collection<Tableorder> list2 = dao.getNamedQuery(hql3, null);
		String id2 = "";
		for (Iterator<Tableorder> ite = list2.iterator(); ite.hasNext();) {
			Tableorder t = ite.next();
			id2 = id2 + t.getPaterOrder() + ",";
		}
		id2 = id2.substring(0, id2.length() - 1);
		String hql4 = "from Tableorder where id in(" + id2 + ")";

		// 二级菜单
		Collection<Tableorder> list3 = dao.getNamedQuery(hql4, null);

		// 一级菜单

		String hql1 = "from Tableorder where orderNum=1";
		Collection<Tableorder> list1 = dao.getNamedQuery(hql1, null);
		String levelone = ",";
		for (Iterator<Tableorder> iter = list3.iterator(); iter.hasNext();) {
			Tableorder t = iter.next();
			if (levelone.indexOf("," + t.getPaterOrder().toString() + ",") == -1)
				levelone += t.getPaterOrder() + ",";
		}

		// 三级菜单
		String tableids = request.getParameter("levelthree");
		if (tableids != null && !tableids.equals("")) {
			String[] tas = tableids.split(";");
			String eids = "";
			for (int i = 0; i < tas.length; i++) {
				eids = eids + tas[i] + ",";
			}
			eids = eids.substring(0, eids.length() - 1);
			// 查询出表的中文名字


			String hql5 = "from Alltablename where id in(" + eids + ")";
			list = dao.getNamedQuery(hql5, null);
		}

		// 二级菜单
		String levevtwo = request.getParameter("leveltwo");
		if (levevtwo != null && !levevtwo.equals("")) {
			String[] levevtwos = levevtwo.split(";");
			String levevt = "";
			for (int i = 0; i < levevtwos.length; i++) {
				levevt = levevt + levevtwos[i] + ",";
			}
			levevt = levevt.substring(0, levevt.length() - 1);
			String hqlt = "from Tableorder where id in(" + levevt + ")";
			list3 = dao.getNamedQuery(hqlt, null);
		}

		// //
		// 发送模板名称用于AJAx效验
		
		String modelname1="";
		try {
			modelname1 = java.net.URLDecoder.decode(request.getParameter("modelname1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("modelname1", modelname1);

		request.setAttribute("levelone", levelone);
		request.setAttribute("levelfirstlist", list1);
		request.setAttribute("leveltwolist", list3);
		request.setAttribute("levelthreelist", list);
		request.setAttribute("useraction", "editReport");

		request.setAttribute("title", "筛选数据表");
		return mapping.findForward("step2");

	}

	public ActionForward editReportFormStep3(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String tableids = request.getParameter("levelthree");
		String tableIds[] = tableids.split("\\;");
		
		reportFormId = request.getParameter("reportFormId");
		request.setAttribute("reportFormId", request
				.getParameter("reportFormId"));
		String hql = "from Reportforms where id=" + reportFormId;
		Collection<Reportforms> Reportformslist = dao.getNamedQuery(hql, null);
		Long tables = null;
		String coordx = "";
		String coordy = "";
		if (Reportformslist.size() != 0) {
			Reportforms rf = (Reportforms) Reportformslist.iterator().next();
			tables = rf.getReportFormId();
			coordx = rf.getCoordx();
			coordy = rf.getCoordy();
		}
		String hql3 = "from Reportformsinfo where reportFormId=" + tables +"order by id asc";
		Collection<Reportformsinfo> Reportformsinfolist = dao.getNamedQuery(hql3, null);
		List<ExportColumnModel> colslist = new ArrayList<ExportColumnModel>();
		for (Iterator<Reportformsinfo> it = Reportformsinfolist.iterator(); it.hasNext();) {
			Reportformsinfo rf = it.next();
			boolean b = false;
			for (String t : tableIds) {
				if (t.equals(rf.getCorrespondingTable().toString())) {
					b = true;
				}
			}
			if (b) {

				ExportColumnModel em = new ExportColumnModel();
				em.setReportFormId(rf.getReportFormId());
				em.setCoordx(coordx);
				em.setCoordy(coordy);
				em.setColName(rf.getColName());
				em.setColWidth(rf.getColWidth());
				em.setAlignType(rf.getAlignType());
				String ad = "";
				if (rf.getAlignType() == 0) {
					ad = "居左";
				}
				if (rf.getAlignType() == 1) {
					ad = "居中";
				}
				if (rf.getAlignType() == 2) {
					ad = "居右";
				}
				em.setAligndescription(ad);
				em.setCorrespondingTable(rf.getCorrespondingTable());
				String sql1 = "from Alltablename where id="
						+ rf.getCorrespondingTable();
				Collection<Alltablename> l = dao.getNamedQuery(sql1, null);
				String tn = "";
				String tdes = "";
				for (Iterator<Alltablename> ite = l.iterator(); ite.hasNext();) {
					Alltablename a = ite.next();
					tn = a.getTableName();
					tdes = a.getTableNameDescription();
				}
				em.setTablename(tn);
				em.setTabledescription(tdes);
				em.setCorrespondingField(rf.getCorrespondingField());
				String sql2 = "from Alltablefieldname where id="
						+ rf.getCorrespondingField();
				String fn = "";
				String fdes = "";
				Collection<Alltablefieldname> ll = dao.getNamedQuery(sql2, null);
				for (Iterator<Alltablefieldname> its = ll.iterator(); its.hasNext();) {
					Alltablefieldname a = its.next();
					fn = a.getTableFieldName();
					fdes = a.getTableFieldDescription();
				}
				em.setFieldname(fn);
				em.setFielddescription(fdes);
				em.setExportType(rf.getExportType());
				String edes = "";
				if (rf.getExportType().equals("1")) {
					edes = "简单输出";
				}
				if (rf.getExportType().equals("2")) {
					edes = "求和输出";
				}
				if (rf.getExportType().equals("3")) {
					edes = "求记录数输出";
				}
				em.setExportdescription(edes);
				colslist.add(em);
			}

		}

		// 显示定制输出列中的内容


		Date date = new Date();
		Long reprotFromId = date.getTime();
		String arrays = request.getParameter("arrays");
		if (arrays != null && !arrays.equals("")) {
			colslist.clear();
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
				
				boolean b = false;
				for (String t : tableIds) {
				
					if (tempcols[3].equals(t)) {
						b = true;
					}
				}
				if (b) {
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
					Collection<Alltablename> l = dao.getNamedQuery(sql1, null);
					String tdes = "";
					for (Iterator<Alltablename> it = l.iterator(); it.hasNext();) {
						Alltablename a = it.next();
						tdes = a.getTableNameDescription();
					}
					em.setTabledescription(tdes);
					em.setCorrespondingField(Integer.valueOf(tempcols[5]));
					em.setFieldname(tempcols[6]);
					String sql2 = "from Alltablefieldname where id="
							+ tempcols[5];
					String fdes = "";
					Collection<Alltablefieldname> ll = dao.getNamedQuery(sql2, null);
					for (Iterator<Alltablefieldname> it = ll.iterator(); it.hasNext();) {
						Alltablefieldname a = it.next();
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
					colslist.add(em);
				}
			}
		}

		request.setAttribute("exportcolumns", colslist);

		String[] tas = tableids.split(";");
		String ids = "";
		for (int i = 0; i < tas.length; i++) {
			ids = ids + tas[i] + ",";
		}
		ids = ids.substring(0, ids.length() - 1);

		// 查询出表的中文名字


		String hql2 = "from Alltablename where id in(" + ids + ")";
		choosbletable = (List) dao.getNamedQuery(hql2, null);

		request.setAttribute("tablelist", choosbletable);
		String reportinfo="";
		try {
			reportinfo = java.net.URLDecoder.decode(request.getParameter("reportinfo"), "UTF-8");
					//new String(request.getParameter("reportinfo").getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setAttribute("reportinfo", reportinfo);
		request.setAttribute("leveltwo", request.getParameter("leveltwo"));
		request.setAttribute("levelthree", request.getParameter("levelthree"));
		request.setAttribute("useraction", "editReport");
		request.setAttribute("coordx", coordx);
		request.setAttribute("coordy", coordy);
		request.setAttribute("title", "定制输出列");
		return mapping.findForward("step3");

	}

	public ActionForward editReportFormStep4(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		reportFormId = request.getParameter("reportFormId");
		request.setAttribute("reportFormId", request
				.getParameter("reportFormId"));
		// 保存定制的操作按钮信息

		/*
		 * 取消订制按钮操作 2009-9-24 liang
		 * 
		 * String buttons=request.getParameter("buttons"); List listbut=new
		 * ArrayList(); if(buttons!=null && !buttons.equals("")){ String[]
		 * tempbut=buttons.split(",\\|"); String tempbuts=""; for(int i=0;i<tempbut.length;i++){
		 * if(tempbut[i].startsWith(",")){ tempbuts=tempbut[i].substring(1,
		 * tempbut[i].length()); }else{ tempbuts=tempbut[i]; } String[]
		 * tempb=tempbuts.split(","); Buttoninfo bi=new Buttoninfo();
		 * bi.setRowOrColl(Integer.valueOf(tempb[0]));
		 * bi.setAlignType(Integer.valueOf(tempb[1])); bi.setHrefName(tempb[2]);
		 * bi.setOnClickThing(tempb[3]); bi.setHrefValue(tempb[4]);
		 * bi.setSign(tempb[5]); listbut.add(bi); }
		 * request.setAttribute("buttonlist", listbut); }else{ String
		 * hqlsss="from Reportforms where id="+reportFormId; List
		 * Reportformslist=(List)dao.getNamedQuery(hqlsss, null); Long
		 * tables=null; if(Reportformslist.size()!=0){ Reportforms
		 * rf=(Reportforms) Reportformslist.iterator().next();
		 * tables=rf.getReportFormId(); } String hql="from Buttoninfo where
		 * reportFormId="+tables; List list=(List) dao.getNamedQuery(hql, null);
		 * request.setAttribute("buttonlist", list); }
		 */
		
		String [] tableName = new String[choosbletable.size()];
		for(int i = 0 ;i<choosbletable.size();i++){
			Alltablename all = (Alltablename)choosbletable.get(i);
			tableName[i] = all.getTableName();
		}

		String hql = "from Reportforms where id=" + reportFormId;
		Collection<Reportforms> list = dao.getNamedQuery(hql, null);
		String queryCond = "";
		List<String> listv = new ArrayList<String>();
		List<String> listt = new ArrayList<String>();
		if (list.size() != 0) {
			Iterator<Reportforms> it = list.iterator();
			Reportforms rf = it.next();
			queryCond = rf.getSelTerm();
			if (queryCond != null && !queryCond.equals("")) {
				String[] temp1 = queryCond.split("\\|");
				for (int i = 0; i < temp1.length; i++) {
					String[] temp2 = temp1[i].split(";");
					if (!"".equals(temp2) && null != temp2 && temp2.length > 1) {
						boolean b = false;
						for(String s : tableName){
							if(temp2[1].indexOf(s) != -1) b = true;
						}
						if(b){
							listt.add(temp2[0]);
							listv.add(temp2[1]);
						}
					}
				}
			}

		}
		
//		System.out.println("arrays---->"+request.getParameter("arrays"));
		
		request.setAttribute("listt", listt);
		request.setAttribute("listv", listv);
		String reportinfo= request.getParameter("reportinfo");
		try{
			reportinfo=java.net.URLDecoder.decode(request.getParameter("reportinfo"), "UTF-8");
		}catch (Exception e) {
			// TODO: handle exception
		}
				//request.getParameter("reportinfo");
		request.setAttribute("biaozhi", 1);
		request.setAttribute("reportinfo", reportinfo);
		request.setAttribute("leveltwo", request.getParameter("leveltwo"));
		request.setAttribute("levelthree", request.getParameter("levelthree"));
		request.setAttribute("arrays", request.getParameter("arrays"));
		request.setAttribute("coordx", request.getParameter("coordx"));
		request.setAttribute("coordy", request.getParameter("coordy"));
		request.setAttribute("useraction", "editReport");
		request.setAttribute("choosbletable", choosbletable);
		request.setAttribute("title", "设置查询条件");
		return mapping.findForward("step5");

	}
	/*
	 * 取消订制按钮操作 2009-9-24 liang
	 * 
	 * public ActionForward editReportFormStep5(ActionMapping mapping,
	 * ActionForm form, HttpServletRequest request, HttpServletResponse
	 * response) { reportFormId = request.getParameter("reportFormId");
	 * request.setAttribute("reportFormId", request
	 * .getParameter("reportFormId"));
	 * 
	 * request.setAttribute("useraction", "editReport"); String hql = "from
	 * Reportforms where id=" + reportFormId; List list = (List)
	 * dao.getNamedQuery(hql, null); String queryCond = ""; List listv = new
	 * ArrayList(); List listt = new ArrayList(); if (list.size() != 0) {
	 * Iterator it = list.iterator(); Reportforms rf = (Reportforms) it.next();
	 * queryCond = rf.getSelTerm(); //System.out.println("selTermss " +
	 * queryCond); if (queryCond != null && !queryCond.equals("")) { String[]
	 * temp1 = queryCond.split("\\|"); for (int i = 0; i < temp1.length; i++) {
	 * String[] temp2 = temp1[i].split(";"); // ERROR 数组越界 if (!"".equals(temp2) &&
	 * null != temp2 && temp2.length > 1) { listt.add(temp2[0]);
	 * listv.add(temp2[1]); } } } } request.setAttribute("listt", listt);
	 * request.setAttribute("listv", listv);
	 * request.setAttribute("choosbletable", choosbletable);
	 * request.setAttribute("arrays", request.getParameter("arrays"));
	 * request.setAttribute("reportinfo", request.getParameter("reportinfo"));
	 * request.setAttribute("leveltwo", request.getParameter("leveltwo"));
	 * request.setAttribute("levelthree", request.getParameter("levelthree"));
	 * request.setAttribute("buttons", request.getParameter("buttons"));
	 * request.setAttribute("coordx", request.getParameter("coordx"));
	 * request.setAttribute("coordy", request.getParameter("coordy"));
	 * request.setAttribute("title", "设置查询条件"); // //System.out.println("Edit
	 * step5 "+ // request.getParameter("reportinfo")); return
	 * mapping.findForward("step5"); }
	 */
}
