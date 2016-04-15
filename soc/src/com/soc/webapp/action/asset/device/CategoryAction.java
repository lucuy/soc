package com.soc.webapp.action.asset.device;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.soc.model.asset.device.Category;
import com.soc.model.user.User;
import com.soc.service.asset.device.CategoryService;
import com.soc.service.audit.AuditService;
import com.soc.webapp.action.BaseAction;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class CategoryAction extends BaseAction{

	private CategoryService categoryManager;
	private String categoryName;
	private long categoryId;
	private String [] options;
	private String assetSystemName;
	private List<Category> categorylist;      //资产设备集合 
	private String ids;  					  //id组
	private Category category;
	private String keyword; 			//快速查询
	private String submitType;
	private AuditService auditManager;
	
	//添加设备类型，厂商
	public String insertCategory(){
		log.info("[CategoryAction] Enter method insertCategory()...");
		Map<String, Object> map = new HashMap<String, Object>();
		//新增设备类型
		if (categoryId==0) {
			map.put("deviceCategoryName",categoryName!=null?categoryName.trim():categoryName);
			map.put("higherUpsId", 0);
			categoryId = categoryManager.insertCategory(map);
			List<String> fieldList1 = new ArrayList<String>();
			fieldList1.add(categoryName + "(" + categoryName
					+ ")");
			auditManager.insertByInsertOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "资产设备", super
					.getRequest().getRemoteAddr(), fieldList1);
			if (categoryId>0 && options!=null && options.length>0) {
				for (int i = 0; i < options.length; i++) {
					if (options[i]!="") {
						map.put("deviceCategoryName",options[i]);
						map.put("higherUpsId", categoryId);
						categoryManager.insertCategory(map);
						List<String> fieldList2 = new ArrayList<String>();
						fieldList2.add(categoryName + "(" + categoryName
								+ ")");
						auditManager.insertByInsertOperator(((User) this.getSession()
								.getAttribute("SOC_LOGON_USER")).getUserId(), "资产设备", super
								.getRequest().getRemoteAddr(), fieldList2);
					}
				}
			}
		}else {
			//判断是做修改操作还是新增资产设备操作
			if (StringUtil.isEmpty(submitType)) {
				categoryManager.delCategoryById(categoryId);
				map.put("deviceCategoryName",categoryName!=null?categoryName.trim():categoryName);
				map.put("higherUpsId", 0);
				categoryId = categoryManager.insertCategory(map);
				List<String> fieldList1 = new ArrayList<String>();
				fieldList1.add(categoryName + "(" + categoryName
						+ ")");
				auditManager.insertByInsertOperator(((User) this.getSession()
						.getAttribute("SOC_LOGON_USER")).getUserId(), "资产设备", super
						.getRequest().getRemoteAddr(), fieldList1);
			}
			//新增资产设备
			if (categoryId>0 && options!=null && options.length>0) {
				for (int i = 0; i < options.length; i++) {
					if (options[i]!="") {
						map.put("deviceCategoryName",options[i]);
						map.put("higherUpsId", categoryId);
						categoryManager.insertCategory(map);
						List<String> fieldList1 = new ArrayList<String>();
						fieldList1.add(categoryName + "(" + categoryName
								+ ")");
						auditManager.insertByInsertOperator(((User) this.getSession()
								.getAttribute("SOC_LOGON_USER")).getUserId(), "资产设备", super
								.getRequest().getRemoteAddr(), fieldList1);
					}
				}
			}
			
		}
		
		
		return SUCCESS;
	}
	
	//查询所有的设备类型，厂商
	public String queryCategory(){
		SearchResult sr = null;
		Page page = null;
		HttpServletRequest request = super.getRequest();
		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		// 接收查询条件，并存储到map中
		if (request.getParameter("keyword") != null) {
			try {
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			map.put("keyword", keyword);
		}
		sr = categoryManager.queryCategories(map, page);
		
		categorylist = sr.getList();
		if (sr!=null) {
			request.setAttribute("Page", sr.getPage());
		}
		return SUCCESS;
	}
	
	//删除资产设备
	public String deleteCategory(){
		log.info("[CategoryAction] Enter deleteCategory...");
		if (StringUtil.isNotBlank(ids)) {
			if (ids.indexOf(",")>0) {
				String [] checked = ids.split(",");
				for (int i = 0; i < checked.length; i++) {
					category = categoryManager.queryCategoryById(Long.parseLong(checked[i]));
					categoryManager.delCategoryById(Long.parseLong(checked[i]));
					List<String> fieldList = new ArrayList<String>();
					fieldList.add(category.getDevice_category_name() + "(" + category.getDevice_category_name()
							+ ")");
					auditManager.insertByDeleteOperator(((User) this.getSession()
							.getAttribute("SOC_LOGON_USER")).getUserId(), "资产设备", super
							.getRequest().getRemoteAddr(), fieldList);
				}
			}else {
				category = categoryManager.queryCategoryById(Long.parseLong(ids));
				categoryManager.delCategoryById(Long.parseLong(ids));
				List<String> fieldList = new ArrayList<String>();
				fieldList.add(category.getDevice_category_name() + "(" + category.getDevice_category_name()
						+ ")");
				auditManager.insertByDeleteOperator(((User) this.getSession()
						.getAttribute("SOC_LOGON_USER")).getUserId(), "资产设备", super
						.getRequest().getRemoteAddr(), fieldList);
			}
		}
		return SUCCESS;
	}
	
	//修改资产设备
	public String toEditCategory(){
		if (categoryId!=0) {
			category = categoryManager.queryCategoryById(categoryId);
			List<String> fieldList = new ArrayList<String>();
			fieldList.add(category.getDevice_category_name() + "(" + category.getDevice_category_name()
					+ ")");
			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "资产设备", super
					.getRequest().getRemoteAddr(), fieldList);
		}
		return SUCCESS;
	}
	//检查设别类别唯一性
	public void checkDeviceName(){
		// 标识此此设备类型是否存在
				String flag = "false";

				// 根据设备类型名查找设备类型
				if (StringUtil.isNotBlank(categoryName)) {
					Category checkCategory=categoryManager.queryCategoryByCategoryName(categoryName);

					// 查找到设备类型，将标识flag设置为true
					if (checkCategory != null) {
						flag = "true";
					}

					// 将flag返回给页面
					try {
						getResponse().getWriter().write(flag);
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
	}
	public CategoryService getCategoryManager() {
		return categoryManager;
	}
	public void setCategoryManager(CategoryService categoryManager) {
		this.categoryManager = categoryManager;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String[] getOptions() {
		return options;
	}
	public void setOptions(String[] options) {
		this.options = options;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public List<Category> getCategorylist() {
		return categorylist;
	}
	public void setCategorylist(List<Category> categorylist) {
		this.categorylist = categorylist;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getAssetSystemName() {
		return assetSystemName;
	}
	public void setAssetSystemName(String assetSystemName) {
		this.assetSystemName = assetSystemName;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getSubmitType() {
		return submitType;
	}
	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}
	

	
	
	
	
}
