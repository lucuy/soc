package com.soc.webapp.action.knowledgemanger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.soc.model.knowledge.Leak;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.knowledge.LeakService;
import com.soc.service.knowledge.exportleak.ExcelLeak;
import com.soc.service.knowledge.importLeak.ImportLeak;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.FileUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <显示漏洞库信息> <显示查询漏洞库相关信息>
 * 
 * @author gaosong
 * @version [版本号, 2013-1-18]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class KnowledgeMangerLeakAction extends BaseAction {

	// server类
	private LeakService leakManager;

	// 查询返回的list
	private List<Leak> list;

	// 查询返回的漏洞对象
	private Leak leak;

	// id
	private int id;

	// 传过来的字符串数组 用来导出
	private String ids;

	// 快速查询用到的关键字
	private String keyword;

	// 查询用到的漏洞等级
	private String leakLevel;

	// 上传的文件
	private File upTar;

	// 文件名称
	private String upTarFileName;
	
	private String filePath;
	
	// 审计业务管理类
	private AuditService auditManager;

	/**
	 * <查询漏洞用来> <根据不同的条件来查询>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String queryLeak() {

		log.info("[KnowledgeMangerLeakAction] enter queryLeak.....");
		// 对前台获得的等级和关键字中文编码进行处理

		try {
			if (leakLevel != null) {
				leakLevel = java.net.URLDecoder.decode(leakLevel, "UTF-8")
						.trim();
			}
			if (keyword != null) {
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8").trim();
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 对分页的处理
		Page page = null;
		HttpServletRequest request = super.getRequest();
		SearchResult sr = null;

		String startIndex = request.getParameter("startIndex");
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(15, Integer.valueOf(startIndex));
		} else {
			page = new Page(15, 0);
		}
		// 用来存放查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		if ("所有级别".equals(leakLevel)) {
			leakLevel = null;
		}
		map.put("leakLevel", leakLevel);
		// 根据前台获得的条件来调用不同的查询方法
		sr = leakManager.queryLeak(map, page);
		if (sr != null) {
			list = (List<Leak>) sr.getList();
			request.setAttribute("Page", sr.getPage());
			sr.setPage(page);
		}

		return SUCCESS;
	}

	/**
	 * <根据id查询漏洞信息> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String selectLeakById() {
		log.info("[KnowledgeMangerLeakAction] enter selectLeakById.....");
		leak = leakManager.selectLeakById(id);
		return SUCCESS;
	}

	/**
	 * <导入漏洞库文件> <功能详细描述>
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public String importLeak() {

		log.info("[KnowledgeMangerLeakAction] Enter method importLeak.....");
	//	upTar = new File(filePath);
		//System.out.println(upTar + upTarFileName);
	//	upTarFileName = upTar.getName();
		if (upTar != null && upTarFileName != null) {
			String path = ServletActionContext.getServletContext().getRealPath(
					"/import");

			File importExcel = new File(new File(path), upTarFileName);

			try {
				FileUtil.copyFile(upTar, importExcel);
			} catch (IOException e) {
				e.printStackTrace();
			}

			List<Leak> list = new ArrayList<Leak>();

			list = ImportLeak.readRead(upTar);

			for (int i = 0; i < list.size(); i++) {
				List<Leak> list1 = leakManager.queryByName(list.get(i)
						.getLeakName());

				if (list1 == null || list1.isEmpty()) { // 插入漏洞信息
					leakManager.insertLeak(list.get(i));
				}
			}
			List<String> fieldList = new ArrayList<String>();
			fieldList.add("导入漏洞库" + "("+ "导入漏洞库" +")");
	        // 审计日志
			auditManager.insertBySystemOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "导入漏洞库", super
					.getRequest().getRemoteAddr(), fieldList);

	}

		return SUCCESS;
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public void export() {
		log.info("[KnowledgeMangerLeakAction] enter export...");

		HttpServletResponse response = super.getResponse();
		ExcelLeak excelleak = new ExcelLeak();
		Map<String, String> limit = new HashMap<String, String>();

		if (StringUtil.isNotBlank(ids)) {
			limit.put("ids", ids);
		}

		excelleak.export(leakManager.export(limit), "漏洞Excel表");
		try {
			// 中文文件名需要编码
			String fileName = "leak_" + DateUtil.curDateStr8();
			response.setContentType("application/ms-excel");
			response.setHeader("Content-Disposition", "attachment;Filename="
					+ fileName + ".xls");
			OutputStream os = response.getOutputStream();
			excelleak.getGwb().write(os);

			os.flush();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public List<Leak> getList() {
		return list;
	}

	public void setList(List<Leak> list) {
		this.list = list;
	}

	public Leak getLeak() {
		return leak;
	}

	public void setLeak(Leak leak) {
		this.leak = leak;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LeakService getLeakManager() {
		return leakManager;
	}

	public void setLeakManager(LeakService leakManager) {
		this.leakManager = leakManager;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getLeakLevel() {
		return leakLevel;
	}

	public void setLeakLevel(String leakLevel) {
		this.leakLevel = leakLevel;
	}

	public File getUpTar() {
		return upTar;
	}

	public void setUpTar(File upTar) {
		this.upTar = upTar;
	}

	public String getUpTarFileName() {
		return upTarFileName;
	}

	public void setUpTarFileName(String upTarFileName) {
		this.upTarFileName = upTarFileName;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}

}
