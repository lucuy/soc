package com.compliance.webapp.action.rank;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.compliance.model.basicinfo.system.SystemManager;
import com.compliance.model.basicinfo.unitinfo.UnitInfo;
import com.compliance.model.rank.Rank;
import com.compliance.model.rank.Record;
import com.compliance.model.rank.RecordDocList;
import com.compliance.model.rank.RecordHistory;
import com.compliance.service.basicinfo.system.SystemService;
import com.compliance.service.basicinfo.unitinfo.UnitInfoService;
import com.compliance.service.rank.RankService;
import com.compliance.service.rank.RecordDocListService;
import com.compliance.service.rank.RecordHistoryService;
import com.compliance.service.rank.RecordService;
import com.compliance.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.load.RecordDocCreate;
import com.util.page.Page;
import com.util.page.SearchResult;
/**
 * 备案action
 * @author quyongkun
 *
 */
public class RecordAction extends BaseAction { 

	/**
	 * 备案业务接口
	 */
	private RecordService recordService;
	
	/**
	 * 定级业务接口
	 */
	private RankService rankService;
	
	/**
	 * 单位信息业务接口
	 */
	private UnitInfoService unitInfoService;
	
	/**
	 * 信息系统业务接口
	 */
	private SystemService systemService;
	
	/**
	 * 定级历史业务接口
	 */
	private RecordHistoryService recordHistoryService;
	
	/**
	 * 备案集合
	 */
	private List<Record> records;
	
	/**
	 * 备案实体
	 */
	private Record record;
	
	/**
	 * 审计业务接口
	 */
//	public AuditService auditService;
//	
	/**
	 * 页面传递的参数
	 */
	private String keyword;
	
	/**
	 * 备案记录业务接口
	 */
	private RecordDocListService recordDocListService;
	/**
	 * 高级搜索页面参数，系统名称，系统编号，当前等级
	 */
	private String sysname;
	private String sysid;
	private String ranklevel;

	
    /**
     * 查询
     * @return
     */
	public String query()
	{
		HttpServletRequest request = super.getRequest();
		Page page = null;
		SearchResult sr = new SearchResult();
		String startIndex = request.getParameter("startIndex");
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		Map mapper = new HashMap();
		sr=recordService.query(mapper, page);

		sr.setPage(page);
		if (sr != null) {
			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * 快速搜索
	 * @return
	 */
	public void jsonForAjax() {
		HttpServletRequest request = super.getRequest();
		Page page = null;
		SearchResult sr = new SearchResult();
		this.getResponse().setCharacterEncoding("UTF-8");
		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		// 接受查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		if (request.getParameter("keyword") != null) {
			try {
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("keyword", keyword);
		}
		sr=recordService.query(map, page);
		records = sr.getList();
		List<Map> mapRecord = new ArrayList<Map>();
		for(Record r : records)
		{
			Map<String, Object> sysRecMap = new HashMap<String, Object>();
			sysRecMap.put("recordId", r.getRecordId());
			sysRecMap.put("sysInFoBusDescription", r.getSysInFoBusDescription());
			sysRecMap.put("sysInFoId", r.getSysInFoId());
			sysRecMap.put("sysInFoName", r.getSysInFoName());
			sysRecMap.put("rankGrade", r.getRankGrade());
			sysRecMap.put("recordDate", r.getRecordDate());
			mapRecord.add(sysRecMap);
		}
		JSONArray jsonArray = JSONArray.fromObject(mapRecord);
		JSONObject jsonObject = JSONObject.fromObject(sr.getPage());
		// Ajax返回
		try {
			getResponse().getWriter().write(
					"{\"processLog\":" + jsonArray.toString() + ",\"page\":"+ jsonObject.toString() + "}");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return;
	}
	/**
	 * 高级搜索
	 */
	public void precisequery(){
		HttpServletRequest request = super.getRequest();
		Page page = null;
		SearchResult sr = new SearchResult();
		this.getResponse().setCharacterEncoding("UTF-8");
		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		// 接受查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		if (request.getParameter("sysname") != null) {
			try {
				sysname = java.net.URLDecoder.decode(sysname, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("keyword1", sysname);
		}
		if (request.getParameter("sysid") != null) {
			try {
				sysid = java.net.URLDecoder.decode(sysid, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("keyword2", sysid);
		}
		if (! "0".equals(request.getParameter("ranklevel")) ) {
			try {
				ranklevel = java.net.URLDecoder.decode(ranklevel, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.put("keyword3", ranklevel);
		}
		sr=recordService.precisequery(map, page);
		records = sr.getList();
		
		
		JSONArray jsonArray = JSONArray.fromObject(records);
		JSONObject jsonObject = JSONObject.fromObject(sr.getPage());
		// Ajax返回
		try {
			getResponse().getWriter().write(
					"{\"processLog\":" + jsonArray.toString() + ",\"page\":"+ jsonObject.toString() + "}");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return;
	}
	/**
	 * 
	 * 系统备案
	 * @return
	 */
	public String sysRecord() {
		HttpServletRequest request = super.getRequest();
		String str=request.getParameter("recordId");
		int recordId=Integer.parseInt(str);
		this.record=recordService.queryById(recordId);
		RecordHistory recordHistory=new RecordHistory();
		recordHistory.setRankGrade(record.getRankGrade());
		recordHistory.setRecordHistoryDate(record.getRecordDate());
		recordHistory.setRecordHistoryDocument(record.getRecordDocument());
		recordHistory.setSysInFoBusDescription(record.getSysInFoBusDescription());
		recordHistory.setSysInFoId(record.getSysInFoId());
		recordHistory.setSysInFoName(record.getSysInFoName());
		recordHistory.setReRecordHistoryDocument(record.getReRecordDocument());
		recordHistoryService.insert(recordHistory);
		insertAudit();
		return SUCCESS;
	}
	
	/**
	 * 添加审计
	 */
	public void insertAudit(){
		/*Audit audit=new Audit();
		audit.setDetailed(record.getSysInFoName());
		audit.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
		audit.setObject("系统备案");
		audit.setTime(DateUtil.curDateTimeStr19());
		audit.setType("备案");
		auditService.instert(audit);*/
	}
	
	/**
	 * 生成备案文档
	 * @return
	 */
	public String createDoc() 
	{
		HttpServletRequest request = super.getRequest();
		String recordId = request.getParameter("recordId");
		record=recordService.queryById(Integer.parseInt(recordId));
		SystemManager systemManager =systemService.queryBySysId(record.getSysInFoId());
		Rank rank =rankService.queryBySysId(record.getSysInFoId());
		//单位信息基本信息
		UnitInfo unitInfo =unitInfoService.query();
		if(unitInfo==null){
			unitInfo = new UnitInfo();
			 
			unitInfo.setCity(" ");
			unitInfo.setCounty(" ");
			unitInfo.setDepContact(" ");
			unitInfo.setDepDuty(" ");
			unitInfo.setDepEmail(" ");
			unitInfo.setDepMobile(" ");
			unitInfo.setDepTel(" ");
			unitInfo.setDivisionCode("      ");
			unitInfo.setDuty(" ");
			unitInfo.setEmployment(" ");unitInfo.setId(0);
			unitInfo.setOtherEmp(" ");
			unitInfo.setOtherSub(" ");
			unitInfo.setPostcode("      ");
			unitInfo.setOtherUnitType("  ");
			unitInfo.setProvince(" ");
			unitInfo.setRelAccess(" ");
			unitInfo.setSubordinate(" ");
			unitInfo.setSysTotal(" ");
			unitInfo.setUnitDep(" ");
			unitInfo.setUnitEmail(" ");
			unitInfo.setUnitLeader(" ");
			unitInfo.setUnitTel(" ");
			unitInfo.setUnitName(" ");
			unitInfo.setUnitType(" ");
		}
		//System.out.println("-------------------------------------");
		Map mapper = new HashMap();
		int countTol = rankService.count(mapper);
		int countSec = rankService.queryByGrade("第二级");
		int countThr = rankService.queryByGrade("第三级");
		int countThir = rankService.queryByGrade("第四级");
		int countMay = rankService.queryByGrade("第五级");
		RecordDocCreate recDocCreate = new RecordDocCreate();
		String recordPath =Thread.currentThread().getContextClassLoader().getResource("").getPath()+"/reccordReport";
		File recordFile = new File(recordPath);
		boolean filePath = recordFile.exists();
		if(!filePath)
			recordFile.mkdirs();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
		String dateString = formatter.format(date);
		String fileName1 = recordPath+"/"+record.getSysInFoName()+"-备案表-"+dateString+".doc";
		File fileName = new File(fileName1);
		//文件重命名
		String newfilename=DateUtil.curDateMselStr18();
		File newfile=new File(recordPath+"/"+newfilename+".doc");
		int flag = recDocCreate.createRecordDoc(unitInfo, systemManager,rank, countTol, countSec, countThr,countThir, countMay,newfile);
		if(flag==1){
			record.setRecordDocument(record.getSysInFoName()+"-备案表-"+dateString+".doc");
			//添加重命名列数据
			record.setReRecordDocument(newfilename+".doc");
			record.setRecordDate(new Date());
			recordService.update(record);
		}
		
		RecordDocList recordDocList=new RecordDocList();
		recordDocList.setRecordId(record.getRecordId());
		recordDocList.setRecordDocListDocmentName(record.getRecordDocument());
		//添加重命名列
		recordDocList.setReRecordDocListDocmentName(record.getReRecordDocument());
		recordDocList.setRecordDocListProduceDate(record.getRecordDate());
		recordDocList.setRecordDocListDownloads(0);
		recordDocListService.insert(recordDocList);
		/*Audit audit=new Audit();
		audit.setDetailed(record.getSysInFoName());
		audit.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
		audit.setObject("系统备案");
		audit.setTime(DateUtil.curDateTimeStr19());
		audit.setType("生成文档");
		auditService.instert(audit);*/
		return SUCCESS;	
	}
	
	public RecordDocListService getRecordDocListService() {
		return recordDocListService;
	}

	public void setRecordDocListService(RecordDocListService recordDocListService) {
		this.recordDocListService = recordDocListService;
	}

	public RecordHistoryService getRecordHistoryService() {
		return recordHistoryService;
	}

	public void setRecordHistoryService(RecordHistoryService recordHistoryService) {
		this.recordHistoryService = recordHistoryService;
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public RankService getRankService() {
		return rankService;
	}

	public void setRankService(RankService rankService) {
		this.rankService = rankService;
	}

	public UnitInfoService getUnitInfoService() {
		return unitInfoService;
	}

	public void setUnitInfoService(UnitInfoService unitInfoService) {
		this.unitInfoService = unitInfoService;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	public RecordService getRecordService() {
		return recordService;
	}

	public void setRecordService(RecordService recordService) {
		this.recordService = recordService;
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSysname() {
		return sysname;
	}

	public void setSysname(String sysname) {
		this.sysname = sysname;
	}

	public String getSysid() {
		return sysid;
	}

	public void setSysid(String sysid) {
		this.sysid = sysid;
	}

	public String getRanklevel() {
		return ranklevel;
	}

	public void setRanklevel(String ranklevel) {
		this.ranklevel = ranklevel;
	}

	/*public AuditService getAuditService() {
		return auditService;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}*/
	
	
}
