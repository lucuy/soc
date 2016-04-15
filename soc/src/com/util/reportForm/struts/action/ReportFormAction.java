package com.util.reportForm.struts.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;*/
import org.apache.commons.io.FileUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.util.FileUtil;
import com.util.reportForm.datadeal.BaseDao;
import com.util.reportForm.datadeal.ExtendedActionServlet;
import com.util.reportForm.datadeal.model.Reportforms;
import com.util.reportForm.model.QueryModel;
import com.util.reportForm.service.ExportService;
import com.util.reportForm.service.InportService;
import com.util.reportForm.service.ReportFormService;
import com.util.reportForm.util.encrypt.FileEncryptAndDecrypt;
import com.util.reportForm.util.export.Realisezip;
import com.util.reportForm.util.hibernate.hibernateUtil.HibernateUtil;
import com.util.reportForm.util.page.Pager;

public class ReportFormAction extends DispatchAction {
	private BaseDao dao = new BaseDao();
	private SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private ReportFormService service = new ReportFormService();

	public InputStream is;
	public BufferedInputStream bis;
	public OutputStream os;
	public BufferedOutputStream bos;
	public FileEncryptAndDecrypt fed = new FileEncryptAndDecrypt();
	byte[] data = new byte[1024];
	int i = 0;

	public ActionForward initPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// String typeHqlStr = "from Reportformstype order by id";

		// 获得所有的组态报表类型的列表
		// List typeList = (ArrayList) dao.getNamedQuery(typeHqlStr, null);

		QueryModel model = service.getQueryModl(request);

		// 存放查询的结果


		List pageList = service.getQueryList(model, request);

		// 日志类型列表
		// request.setAttribute("typeList", typeList);

		// 初始化类型为“所有类型”


		request.setAttribute("formType", "all");

		// 返回的查询结果

		request.setAttribute("resultList", pageList);
		Pager pagera = service.getPager();
		// pager
		request.setAttribute("page", service.getPager());

		return mapping.findForward("initPage");
	}

	// 分页搜索
	public ActionForward pageSelect(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String typeHqlStr = "from Reportformstype order by id";

		// 获得所有的组态报表类型的列表
		List typeList = (ArrayList) dao.getNamedQuery(typeHqlStr, null);
		request.setAttribute("typeList", typeList);

		// 模板名称
		String reportFormName = request.getParameter("reportFormName");

		// 模板类型
		String reportFormType = request.getParameter("reportFormType");
		
		// 开始时间

		String startTime = request.getParameter("startTime");

		// 结束时间
		String endTime = request.getParameter("endTime");

		QueryModel model = service.getQueryModl(request);

		// 存放查询的结果


		List pageList = service.getQueryList(model, request);

		// 模板名称
		request.setAttribute("reportFormName", null == reportFormName ? ""
				: reportFormName.trim());

		// 模板类型
		request.setAttribute("formType", null == reportFormType ? ""
				: reportFormType.trim());

		// 开始时间
		request.setAttribute("startTime", null == startTime ? "" : startTime
				.trim());

		// 结束时间
		request.setAttribute("endTime", null == endTime ? "" : endTime.trim());

		// 返回的查询结果
		request.setAttribute("resultList", pageList);

		// pager
		request.setAttribute("page", service.getPager());
		
		//查询字段
		request.setAttribute("keyword", request.getParameter("keyword"));

		return mapping.findForward("selectList");
	}

	// 删除
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String formIds = request.getParameter("ids");
		if (null != formIds && !formIds.trim().equals("")) {
			List<Reportforms> deleteList = new ArrayList<Reportforms>();
			String[] ids = formIds.split(",");

			String hql = "from Reportforms where id in(" + formIds + ")";

			deleteList = (List<Reportforms>) dao.getNamedQuery(hql, null);

			String reportIds = "";
			for (Reportforms rf : deleteList) {
				reportIds += rf.getReportFormId().toString() + ",";
			}

			dao.deleteAll(deleteList);
			if (!"".equals(reportIds)) {
				reportIds = reportIds.substring(0, reportIds.length() - 1);
			}

			hql = "from Reportformsinfo where reportFormId in (" + reportIds
					+ ")";

			deleteList.clear();
			List list = (List) dao.getNamedQuery(hql, null);
			dao.deleteAll(list);
		}


		String reportFormName = request.getParameter("reportFormName");// 模板名称

		String reportFormType = request.getParameter("reportFormType");// 模板类型

		String startTime = request.getParameter("startTime");// 开始时间


		String endTime = request.getParameter("endTime");// 结束时间

		// 获得查询使用的model
		QueryModel model = service.getQueryModl(request);

		List pageList = service.getQueryList(model, request);// 存放查询的结果


		request.setAttribute("reportFormName", null == reportFormName ? ""
				: reportFormName.trim());// 模板名称

		request.setAttribute("formType", null == reportFormType ? ""
				: reportFormType.trim());// 模板类型

		request.setAttribute("startTime", null == startTime ? "" : startTime
				.trim());// 开始时间


		request.setAttribute("endTime", null == endTime ? "" : endTime.trim());// 结束时间

		request.setAttribute("resultList", pageList);// 返回的查询结果

		request.setAttribute("page", service.getPager());// pager

		return mapping.findForward("selectList");
	}

	// 删除所有
	public ActionForward deleteAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Session session = HibernateUtil.getCurrentSession();
		try {
			Transaction tx = session.beginTransaction();
			// 删除记录
			Query query = session.createQuery("delete from Reportforms");
			query.executeUpdate();
			query = session.createQuery("delete from Reportformsinfo");
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}
		return mapping.findForward("selectList");
	}

	// 组态报表的详细信息
	public ActionForward getDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String hql = "from Reportforms f where f.id=:id";
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("id", Integer.parseInt(id));
		List<Reportforms> resultList = (List<Reportforms>) dao.getNamedQuery(
				hql, params);
		Reportforms resultModel = null;
		String msg = "";
		if (null != resultList && resultList.size() > 0) {
			resultModel = resultList.get(0);
		}
		if (null == resultList || 0 == resultList.size()) {
			msg = "对不起，该记录已被删除！";
		}
		request.setAttribute("displayDate", format.format(resultModel
				.getCreateDate()));
		request.setAttribute("formModel", resultModel);
		request.setAttribute("msg", msg);
		return mapping.findForward("showDetail");
	}

	// 导出组态报表模板


	public ActionForward exportReportForm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ExportService service = new ExportService();
		// 生成zip文件
		service.export();

		File file = new File(ExportService.zipFileNameAndPath);
		OutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			response.reset();
			// 设置为下载application/force-download
			response.setContentType("application/force-download");
			response.setHeader("Location", ExportService.zipFileNameAndPath);
			response.setHeader("Content-Disposition", "attachment; filename="
					+ ExportService.zipFileName);
			outputStream = response.getOutputStream();
			inputStream = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, i);
			}
			outputStream.flush();
		} catch (Exception e) {

		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
					inputStream.close();
					file.delete();
				}
			} catch (Exception e) {

			}
		}
		return null;
	}
	/**
	 * 跳转到上传页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward updateReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String name = request.getParameter("name");
		request.setAttribute("name", name);
		return mapping.findForward("upload");
	}

	// 导入组态报表模板


	public ActionForward inportReportForm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String rootPath = ExtendedActionServlet.setupPath.substring(0,
				ExtendedActionServlet.setupPath.indexOf("webapps"));
		
		DynaActionForm uploadForm = (DynaActionForm) form;
		if (null == uploadForm) {
			return null;
		}
		
		//String path1 = rootPath + "webapps\\fort\\tmp\\reportForm.zip";
		String path1 = rootPath + "webapps/soc/tmp/reportForm.zip";
		
		try {
			Realisezip ie = new Realisezip();
			ie.realiseZipFile(path1, rootPath);
			File zip = new File(path1);
			if (zip.exists()) {
				zip.delete();
			}
			InportService service = new InportService();
			service.inport(rootPath + "reportForm.xml");
			
			File file = new File(rootPath + "reportForm.xml");
			if(file.exists()){
				file.delete();
			}
			request.setAttribute("message", "ok");
		} catch (Exception e) {
			request.setAttribute("mes", "文件导入失败！");
			request.setAttribute("name", request.getParameter("logname"));
			e.printStackTrace();
			return mapping.findForward("upload");
		}
		return mapping.findForward("upload");
	}

}