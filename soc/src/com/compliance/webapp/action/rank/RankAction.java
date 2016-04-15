package com.compliance.webapp.action.rank;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.compliance.model.rank.Rank;
import com.compliance.model.rank.Record;
import com.compliance.service.rank.RankService;
import com.compliance.service.rank.RecordService;
import com.compliance.webapp.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import com.util.DateUtil;
import com.util.FileUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 定级的action
 * 
 * @author quyongkun
 * 
 */
public class RankAction extends BaseAction implements ModelDriven<Rank> {

	/**
	 * 定级对象
	 */
	public Rank rank = new Rank();

	/**
	 * 备案业务接口
	 */
	public RecordService recordService;

	/**
	 * 定级业务接口
	 */
	public RankService rankService;

	/**
	 * 定级集合
	 */
	public List<Rank> ranks;

	/**
	 * 模糊查询字段
	 */
	public String keyword;

	/**
	 * 查询数据条数
	 */
	public int count = 0;

	/**
	 * 查询定级编号
	 */
	public int rankId;

	/**
	 * 信息系统编号
	 */
	public String sysInfoId;

	/**
	 * 审计业务接口
	 */
	/* public AuditService auditService; */

	/**
	 * 高级搜索页面参数，系统名称，系统编号，当前等级,定级状态
	 */
	public String sysname;
	public String sysid;
	public String ranklevel;
	public String rankstatus;
	/**
	 * 定级第一步上传附件相关信息
	 */
	// 封装文件
	private File frankEvalRelAccess;
	// 封装文件名
	private String frankEvalRelAccessFileName;
	// 文件保存路径
	private String frankEvalRelAccessPath;
	// 重命名第一步文件
	private String reFrankEvalRelAccessFileName;
	/**
	 * 定级第五步附件上传
	 */
	// 封装文件
	private File frankAccess;
	// 封装文件名
	private String frankAccessFileName;
	// 封装文件保存路径
	private String frankAccessPath;
	// 重命名第五步文件
	private String reFrankAccessFileName;
	/**
	 * 第六步附件上传
	 */
	// 系统拓扑结构附件
	private File ftopRelAcc1;
	private String ftopRelAcc1FileName;
	private String ftopRelAcc1SavePath;
	private String reFtopRelAcc1FileName;
	// 系统安全组织附件
	private File fsysManRel1;
	private String fsysManRel1FileName;
	private String fsysManRel1SavePath;
	private String reFsysManRel1FileName;
	// 系统安全保护设施附件
	private File fsysPlanRel1;
	private String fsysPlanRel1FileName;
	private String fsysPlanRel1SavePath;
	private String reFsysPlanRel1FileName;
	// 系统使用的安全产品附件
	private File fsysLiceRel1;
	private String fsysLiceRel1FileName;
	private String fsysLiceRel1SavePath;
	private String reFsysLiceRel1FileName;
	// 系统等级测评报告附件
	private File fsysReportRel1;
	private String fsysReportRel1FileName;
	private String fsysReportRel1SavePath;
	private String reFsysReportRel1FileName;
	// 专家评审情况附件
	private File fpeerRevRel1;
	private String fpeerRevRel1FileName;
	private String fpeerRevRel1SavePath;
	private String reFpeerRevRel1FileName;
	// 上级主管部门审批附件
	private File fsuperOpinRel1;
	private String fsuperOpinRel1FileName;
	private String fsuperOpinRel1SavePath;
	private String reFsuperOpinRel1FileName;

	/**
	 * 定级第一步上传文件调用的方法
	 * 
	 * @throws IOException
	 */
	public void upfile() throws IOException {
		String str = "";
		File savedir = new File(getFrankEvalRelAccessPath());
		if (!savedir.exists()) {
			savedir.mkdirs();
		}

		// 重命名源文件以配置文件中的路径及上传文件名创建输出流
		reFrankEvalRelAccessFileName = FileUtil.renameFileName(this
				.getFrankEvalRelAccessFileName());
		// 目的目录
		String target="";
		if(System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS")==0){
			target = savedir + "\\" + reFrankEvalRelAccessFileName;
		}else{
			target = savedir + "/" + reFrankEvalRelAccessFileName;
		}
		// 源文件
		FileInputStream fis = new FileInputStream(getFrankEvalRelAccess());
		Boolean upornot = FileUtil.upFile(fis, target);
		str += this.getFrankEvalRelAccessFileName();
		if (upornot) {
			str += "上传成功！";
		} else {
			str += "上传失败！";
		}
		HttpServletResponse res = ServletActionContext.getResponse();
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		
		out.println("<script type=\"text/javascript\">parent.callback('"+ str.toString() + "')</script>");
		out.println("<script type=\"text/javascript\">parent.callback2('"+ reFrankEvalRelAccessFileName.toString()+"')</script>");
		
		return;
	}

	/**
	 * 查询所有定级
	 * 
	 * @return
	 */
	public String queryRank() {
		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		response.setCharacterEncoding("UTF-8");
		Page page = null;
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
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.put("keyword", keyword);
		}
		// System.out.println("-------keyword--------" + keyword);
		SearchResult sr = this.rankService.query(map, page);
		this.ranks = null;
		this.ranks = (List<Rank>) sr.getList();
		request.setAttribute("Page", sr.getPage());
		return SUCCESS;
	}

	/**
	 * 根据sysinfoId到定级数据库中查询是否有该数据
	 */
	public void queryByIdAjax() {

		String[] sysInfo_ids = super.getRequest().getParameter("sysInfo_id")
				.split(",");
		List<Rank> list = new ArrayList<Rank>();
		for (String sysInfo_id : sysInfo_ids) {
			rank = rankService.queryBySysId(sysInfo_id);
			String sysName = rank.getSysInfoName();
			String rankGrade = rank.getRankGrade();
			// 已经定级的信息系统
			if (StringUtil.isNotBlank(rankGrade)) {
				Rank rank1 = new Rank();
				rank1.setRankId(rank.getRankId());
				rank1.setSysInfoName(sysName);
				rank1.setSysInfoId(rank.getSysInfoId());
				list.add(rank1);
			}
		}
		JSONArray jsonArray = JSONArray.fromObject(list);
		try {
			getResponse().getWriter().write(
					"{\"resultByAjax\":" + jsonArray.toString() + "}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 高级搜索定级信息
	 * 
	 * @return
	 */
	public void queryPreciseRank() {
		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		response.setCharacterEncoding("UTF-8");
		Page page = null;
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
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.put("keyword1", sysname);
		}
		if (request.getParameter("sysid") != null) {
			try {
				sysid = java.net.URLDecoder.decode(sysid, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.put("keyword2", sysid);
		}
		if (!"0".equals(request.getParameter("ranklevel"))) {
			try {
				ranklevel = java.net.URLDecoder.decode(ranklevel, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.put("keyword3", ranklevel);
			map.put("keyword4", "1");

		} else if ("0".equals(request.getParameter("ranklevel"))) {
			if ("1".equals(request.getParameter("rankstatus"))) {
				map.put("keyword4", rankstatus);
			} else if ("2".equals(request.getParameter("rankstatus"))) {
				map.put("keyword5", rankstatus);
			}

		}
		SearchResult sr = this.rankService.preciseQueryRank(map, page);
		JSONArray jsonArray = JSONArray.fromObject(sr.getList());
		this.ranks = null;
		this.ranks = sr.getList();
		JSONObject jsonObject = JSONObject.fromObject(sr.getPage());
		// Ajax返回
		try {
			getResponse().getWriter().write(
					"{\"processLog\":" + jsonArray.toString() + ",\"page\":"
							+ jsonObject.toString() + "}");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * ajax查询定级分页
	 * 
	 * @return
	 */
	public void queryAjaxRank() {
		HttpServletRequest request = super.getRequest();
		super.getResponse().setCharacterEncoding("UTF-8");
		Page page = null;

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
		SearchResult sr = this.rankService.query(map, page);
		JSONArray jsonArray = JSONArray.fromObject(sr.getList());
		this.ranks = null;
		this.ranks = sr.getList();
		JSONObject jsonObject = JSONObject.fromObject(sr.getPage());
		// Ajax返回
		try {
			getResponse().getWriter().write(
					"{\"processLog\":" + jsonArray.toString() + ",\"page\":"
							+ jsonObject.toString() + "}");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;

	}

	/**
	 * 定级显示调用的方法
	 * 
	 * @return
	 */
	public String show() {
		String str = ServletActionContext.getRequest().getParameter("rankId");
		this.rankId = Integer.parseInt(str);
		this.rank = this.rankService.queryById(this.rankId);
		return "show";
	}

	/**
	 * 定级时据编号查询定级信息方法
	 * 
	 * @return
	 */
	public String updateInfo() {
		String str = ServletActionContext.getRequest().getParameter("rankId");
		this.rankId = Integer.parseInt(str);
		this.rank = this.rankService.queryById(this.rankId);
		return "updateInfo";
	}

	/**
	 * 定级修改时根据编号查询定级信息方法
	 * 
	 * @return
	 */
	public String updateRankInfo() {
		String str = ServletActionContext.getRequest().getParameter("rankId");
		this.rankId = Integer.parseInt(str);
		this.rank = this.rankService.queryById(this.rankId);
		return "updateRankInfo";
	}

	/**
	 * 定级第一步
	 * 
	 * @return
	 * @throws IOException
	 */
	public String rankOne() {
		return "rankOne";
	}

	/**
	 * 定级第二步
	 * 
	 * @return
	 */
	public String rankTwo() {
		return "rankTwo";
	}

	/**
	 * 定级第三步
	 * 
	 * @return
	 */
	public String rankThree() {
		return "rankThree";
	}

	/**
	 * 定级第三步和第四步之间
	 * 
	 * @return
	 */
	public String rankFourBefore() {
		rank.setRankEvalRelAccess(frankEvalRelAccessFileName);
		rank.setReRankEvalRelAccess(reFrankEvalRelAccessFileName);
		System.out.println(rank);;
		return "rankFourBefore";
	}

	/**
	 * 定级第四步
	 * 
	 * @return
	 */
	public String rankFour() {
		return "rankFour";
	}

	/**
	 * 定级第五步文件上传
	 * 
	 * @throws IOException
	 * 
	 */
	public void uprankfile() throws IOException {
		String str = "";
		File savedir = new File(getFrankAccessPath());
		if (!savedir.exists()) {
			savedir.mkdirs();
		}
		// 重命名源文件以配置文件中的路径及上传文件名创建输出流
		reFrankAccessFileName = FileUtil.renameFileName(this.getFrankAccessFileName());
		// 目的目录
		String target="";
		if(System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS")==0){
			target = savedir + "\\" + reFrankAccessFileName;
		}else{
			target = savedir + "/" + reFrankAccessFileName;
		}
		// 源文件
		FileInputStream fis = new FileInputStream(getFrankAccess());
		Boolean upornot = FileUtil.upFile(fis, target);
		str += this.getFrankAccessFileName();
		if (upornot) {
			str += "上传成功！";
		} else {
			str += "上传失败！";
		}
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<script type=\"text/javascript\">parent.callback('"
				+ str.toString() + "')</script>");
		out.println("<script type=\"text/javascript\">parent.callback2('"
				+ reFrankAccessFileName.toString() + "')</script>");
		return;
	}

	/**
	 * 定级第五步
	 * 
	 * @return
	 */
	public String rankFive() {

		if ("第二级".equals(this.rank.getRankGrade())) {
			caozuo(this.rank);
			return "rankSix";
		}
		if ("第一级".equals(this.rank.getRankGrade())) {
			caozuo(this.rank);
			return "rankSix";
		}
		rank.setRankAccess(this.getFrankAccessFileName());
		rank.setReRankAccess(reFrankAccessFileName);
		return "rankFive";
	}

	public void caozuo(Rank rank) {

		this.rankService.update(rank);
		Record record = new Record();
		record.setSysInFoId(rank.getSysInfoId());
		record.setSysInFoName(rank.getSysInfoName());
		record.setSysInFoBusDescription(rank.getSysInfoBusDescription());
		record.setRankGrade(rank.getRankGrade());
		recordService.insert(record);
	}

	// 上传拓扑附件
	public void uptopfile() throws IOException {
		String str = "";
		File savedir = new File(getFtopRelAcc1SavePath());
		if (!savedir.exists()) {
			savedir.mkdirs();
		}
		// 重命名源文件以配置文件中的路径及上传文件名创建输出流
		reFtopRelAcc1FileName = FileUtil.renameFileName(this.getFtopRelAcc1FileName());
		// 目的目录
		String target="";
		if(System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS")==0){
			target = savedir + "\\" + reFtopRelAcc1FileName;
		}else{
			target = savedir + "/" + reFtopRelAcc1FileName;
		}
		// 源文件
		FileInputStream fis = new FileInputStream(getFtopRelAcc1());
		Boolean upornot = FileUtil.upFile(fis, target);
		str += this.getFtopRelAcc1FileName();
		if (upornot) {
			str += "上传成功！";
		} else {
			str += "上传失败！";
		}
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<script type=\"text/javascript\">parent.callback('"
				+ str.toString() + "')</script>");
		out.println("<script type=\"text/javascript\">parent.callback2('reFtopRelAcc1FileName','"
				+ reFtopRelAcc1FileName.toString() + "')</script>");
		return;
	}

	// 上传系统安全组织机构及管理制度附件
	public void upsysmanfile() throws IOException {
		String str = "";
		File savedir = new File(getFsysManRel1SavePath());
		if (!savedir.exists()) {
			savedir.mkdirs();
		}
		// 重命名源文件以配置文件中的路径及上传文件名创建输出流
		reFsysManRel1FileName = FileUtil.renameFileName(this.getFsysManRel1FileName());
		// 目的目录
		String target="";
		if(System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS")==0){
			target = savedir + "\\" + reFsysManRel1FileName;
		}else{
			target = savedir + "/" + reFsysManRel1FileName;
		}
		// 源文件
		FileInputStream fis = new FileInputStream(getFsysManRel1());
		Boolean upornot = FileUtil.upFile(fis, target);
		str += this.getFsysManRel1FileName();
		if (upornot) {
			str += "上传成功！";
		} else {
			str += "上传失败！";
		}
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<script type=\"text/javascript\">parent.callback('"
				+ str.toString() + "')</script>");
		out.println("<script type=\"text/javascript\">parent.callback2('reFsysManRel1FileName','"
				+ reFsysManRel1FileName.toString() + "')</script>");
		return;
	}

	// 系统安全保护设施附件上传
	public void upsysplanfile() throws IOException {
		String str = "";
		File savedir = new File(getFsysPlanRel1SavePath());
		if (!savedir.exists()) {
			savedir.mkdirs();
		}
		// 重命名源文件以配置文件中的路径及上传文件名创建输出流
		reFsysPlanRel1FileName = FileUtil.renameFileName(this.getFsysPlanRel1FileName());
		// 目的目录
		String target="";
		if(System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS")==0){
			target = savedir + "\\" + reFsysPlanRel1FileName;
		}else{
			target = savedir + "/" + reFsysPlanRel1FileName;
		}
		// 源文件
		FileInputStream fis = new FileInputStream(getFsysPlanRel1());
		Boolean upornot = FileUtil.upFile(fis, target);
		str += this.getFsysPlanRel1FileName();
		if (upornot) {
			str += "上传成功！";
		} else {
			str += "上传失败！";
		}
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<script type=\"text/javascript\">parent.callback('"
				+ str.toString() + "')</script>");
		out.println("<script type=\"text/javascript\">parent.callback2('reFsysPlanRel1FileName','"
				+ reFsysPlanRel1FileName.toString() + "')</script>");
		return;
	}

	// 第六个页面上安全产品附件上传
	public void upsyslicefile() throws IOException {
		String str = "";
		File savedir = new File(getFsysLiceRel1SavePath());
		if (!savedir.exists()) {
			savedir.mkdirs();
		}
		// 重命名源文件以配置文件中的路径及上传文件名创建输出流
		reFsysLiceRel1FileName = FileUtil.renameFileName(this.getFsysLiceRel1FileName());
		// 目的目录
		String target="";
		if(System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS")==0){
			target = savedir + "\\" + reFsysLiceRel1FileName;
		}else{
			target = savedir + "/" + reFsysLiceRel1FileName;
		}
		// 源文件
		FileInputStream fis = new FileInputStream(getFsysLiceRel1());
		Boolean upornot = FileUtil.upFile(fis, target);
		str += this.getFsysLiceRel1FileName();
		if (upornot) {
			str += "上传成功！";
		} else {
			str += "上传失败！";
		}
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<script type=\"text/javascript\">parent.callback('"
				+ str.toString() + "')</script>");
		out.println("<script type=\"text/javascript\">parent.callback2('reFsysLiceRel1FileName','"
				+ reFsysLiceRel1FileName.toString() + "')</script>");
		return;

	}

	// 第六个页面上系统等级测评报告附件上传
	public void upsysrefile() throws IOException {
		String str = "";
		File savedir = new File(getFsysReportRel1SavePath());
		if (!savedir.exists()) {
			savedir.mkdirs();
		}
		// 重命名源文件以配置文件中的路径及上传文件名创建输出流
		reFsysReportRel1FileName = FileUtil.renameFileName(this.getFsysReportRel1FileName());
		// 目的目录
		String target="";
		if(System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS")==0){
			target = savedir + "\\" + reFsysReportRel1FileName;
		}else{
			target = savedir + "/" + reFsysReportRel1FileName;
		}
		// 源文件
		FileInputStream fis = new FileInputStream(getFsysReportRel1());
		Boolean upornot = FileUtil.upFile(fis, target);
		str += this.getFsysReportRel1FileName();
		if (upornot) {
			str += "上传成功！";
		} else {
			str += "上传失败！";
		}
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<script type=\"text/javascript\">parent.callback('"
				+ str.toString() + "')</script>");
		out.println("<script type=\"text/javascript\">parent.callback2('reFsysReportRel1FileName','"
				+ reFsysReportRel1FileName.toString() + "')</script>");
		return;
	}

	// 处理第六个页面上专家评审情况附件
	public void uppeerfile() throws IOException {
		String str = "";
		File savedir = new File(getFpeerRevRel1SavePath());
		if (!savedir.exists()) {
			savedir.mkdirs();
		}
		// 重命名源文件以配置文件中的路径及上传文件名创建输出流
		reFpeerRevRel1FileName = FileUtil.renameFileName(this.getFpeerRevRel1FileName());
		// 目的目录
		String target="";
		if(System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS")==0){
			target = savedir + "\\" + reFpeerRevRel1FileName;
		}else{
			target = savedir + "/" + reFpeerRevRel1FileName;
		}
		// 源文件
		FileInputStream fis = new FileInputStream(getFpeerRevRel1());
		Boolean upornot = FileUtil.upFile(fis, target);
		str += this.getFpeerRevRel1FileName();
		if (upornot) {
			str += "上传成功！";
		} else {
			str += "上传失败！";
		}
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<script type=\"text/javascript\">parent.callback('"
				+ str.toString() + "')</script>");
		out.println("<script type=\"text/javascript\">parent.callback2('reFpeerRevRel1FileName','"
				+ reFpeerRevRel1FileName.toString() + "')</script>");
		return;
	}

	// 处理第六个页面上上级主管部门审批附件
	public void upsuperfile() throws IOException {
		String str = "";
		File savedir = new File(getFsuperOpinRel1SavePath());
		if (!savedir.exists()) {
			savedir.mkdirs();
		}
		// 重命名源文件以配置文件中的路径及上传文件名创建输出流
		reFsuperOpinRel1FileName = FileUtil.renameFileName(this.getFsuperOpinRel1FileName());
		// 目的目录
		String target="";
		if(System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS")==0){
			target = savedir + "\\" + reFsuperOpinRel1FileName;
		}else{
			target = savedir + "/" + reFsuperOpinRel1FileName;
		}
		// 源文件
		FileInputStream fis = new FileInputStream(getFsuperOpinRel1());
		Boolean upornot = FileUtil.upFile(fis, target);
		str += this.getFsuperOpinRel1FileName();
		if (upornot) {
			str += "上传成功！";
		} else {
			str += "上传失败！";
		}
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<script type=\"text/javascript\">parent.callback('"
				+ str.toString() + "')</script>");
		out.println("<script type=\"text/javascript\">parent.callback2('reFsuperOpinRel1FileName','"
				+ reFsuperOpinRel1FileName.toString() + "')</script>");
		return;
	}

	/**
	 * 定级第六步
	 * 
	 * @return
	 */
	public String rankSix() {
		rank.setRankTopRelAcc(ftopRelAcc1FileName);
		rank.setRankSysManRel(fsysManRel1FileName);
		rank.setRankSysLiceRel(fsysLiceRel1FileName);
		rank.setRankPeerRevRel(fpeerRevRel1FileName);
		rank.setRankSysReportRel(fsysReportRel1FileName);
		rank.setRankSysPlanRel(fsysPlanRel1FileName);
		rank.setRankSuperOpinRel(fsuperOpinRel1FileName);
		//重命名列
		rank.setReRankTopRelAcc(reFtopRelAcc1FileName);
		rank.setReRankSysManRel(reFsysManRel1FileName);
		rank.setReRankSysLiceRel(reFsysLiceRel1FileName);
		rank.setReRankPeerRevRel(reFpeerRevRel1FileName);
		rank.setReRrankSysReportRel(reFsysReportRel1FileName);
		rank.setReRankSysPlanRel(reFsysPlanRel1FileName);
		rank.setReRankSuperOpinRel(reFsuperOpinRel1FileName);
		caozuo(this.rank);
		return "rankSix";
	}

	/**
	 * 定级修改第一步
	 * 
	 * @return
	 */
	public String rankOneUpdate() {
		return "rankOneUpdate";
	}

	/**
	 * 定级修改第二步
	 * 
	 * @return
	 */
	public String rankTwoUpdate() {
		return "rankTwoUpdate";
	}

	/**
	 * 定级修改第三步
	 * 
	 * @return
	 */
	public String rankThreeUpdate() {
		return "rankThreeUpdate";
	}

	/**
	 * 定级修改第三步和第四步之间
	 * 
	 * @return
	 */
	public String rankFourBeforeUpdate() {
		if (frankEvalRelAccessFileName != null) {
			rank.setRankEvalRelAccess(frankEvalRelAccessFileName);
			rank.setReRankEvalRelAccess(reFrankEvalRelAccessFileName);
		}
		return "rankFourBeforeUpdate";
	}

	/**
	 * 定级修改第四步
	 * 
	 * @return
	 */
	public String rankFourUpdate() {
		if ("第二级".equals(this.rank.getRankGrade())) {
			rank.setRankTopStruct(null);
			rank.setRankSysManage(null);
			rank.setRankSysPlan(null);
			rank.setRankSysLicense(null);
			rank.setRankSysReport(null);
			rank.setRankPeerRev(null);
			rank.setRankSuperOpin(null);
			rank.setRankTopRelAcc(null);
			rank.setReRankTopRelAcc(null);
			rank.setRankSysManRel(null);
			rank.setReRankSysManRel(null);
			rank.setRankSysLiceRel(null);
			rank.setReRankSysLiceRel(null);
			rank.setRankPeerRevRel(null);
			rank.setReRankPeerRevRel(null);
			rank.setReRankSysPlanRel(null);
			rank.setRankSysReportRel(null);
			rank.setReRrankSysReportRel(null);
			rank.setRankSysPlanRel(null);
			rank.setRankSuperOpinRel(null);
			rank.setReRankSuperOpinRel(null);
		}
		return "rankFourUpdate";
	}

	/**
	 * 定级修改第五步
	 * 
	 * @return
	 */
	public String rankFiveUpdate() {
		// System.out.println("第五步" + frankAccessFileName);
		if (frankAccessFileName != null) {
			rank.setRankAccess(frankAccessFileName);
			rank.setReRankAccess(reFrankAccessFileName);
		}
		List<Record> records = null;
		if ("第二级".equals(this.rank.getRankGrade())
				|| "第一级".equals(this.rank.getRankGrade())) {
			records = recordService.queryBySysId(rank.getSysInfoId());
		}
		if ("第二级".equals(this.rank.getRankGrade())) {
			this.rankService.update(this.rank);
			if (records != null && records.size() != 0) {
				Record record = records.get(0);
				record.setRankGrade(rank.getRankGrade());
				recordService.update(record);
			}
			// -------------------------------------------------------------------------------//审计记录
			insertAudit();
			return "rankSixUpdate";
		}
		if ("第一级".equals(this.rank.getRankGrade())) {

			this.rankService.update(this.rank);
			if (records != null && records.size() != 0) {
				Record record = records.get(0);
				record.setRankGrade(rank.getRankGrade());
				recordService.update(record);
			}
			// -------------------------------------------------------------------------------//审计记录
			insertAudit();
			return "rankSixUpdate";
		}
		return "rankFiveUpdate";
	}

	/**
	 * 定级修改第六步
	 * 
	 * @return
	 */
	public String rankSixUpdate() {
		if (ftopRelAcc1FileName != null){
			rank.setRankTopRelAcc(ftopRelAcc1FileName);
			rank.setReRankTopRelAcc(reFtopRelAcc1FileName);
		}
			
		if (fsysManRel1FileName != null){
			rank.setRankSysManRel(fsysManRel1FileName);
			rank.setReRankSysManRel(reFsysManRel1FileName);
		}
			
		if (fsysPlanRel1FileName != null){
			rank.setRankSysPlanRel(fsysPlanRel1FileName);
			rank.setReRankSysPlanRel(reFsysPlanRel1FileName);
		}
			
		if (fsysLiceRel1FileName != null){
			rank.setRankSysLiceRel(fsysLiceRel1FileName);
			rank.setReRankSysLiceRel(reFsysLiceRel1FileName);
		}
			
		if (fsysReportRel1FileName != null){
			rank.setRankSysReportRel(fsysReportRel1FileName);
			rank.setReRrankSysReportRel(reFsysReportRel1FileName);
		}
			
		if (fpeerRevRel1FileName != null){
			rank.setRankPeerRevRel(fpeerRevRel1FileName);
			rank.setReRankPeerRevRel(reFpeerRevRel1FileName);
		}
			
		if (fsuperOpinRel1FileName != null){
			rank.setRankSuperOpinRel(fsuperOpinRel1FileName);
			rank.setReRankSuperOpinRel(reFsuperOpinRel1FileName);
		}
			
		this.rankService.update(this.rank);
		List<Record> records = recordService.queryBySysId(rank.getSysInfoId());
		if (records != null && records.size() != 0) {
			Record record = records.get(0);
			record.setRankGrade(rank.getRankGrade());
			recordService.update(record);
		}
		// -------------------------------------------------------------------------------//审计记录
		insertAudit();
		return "rankSixUpdate";
	}

	/**
	 * 添加审计
	 */
	public void insertAudit() {
		// Audit audit=new Audit();
		// audit.setDetailed(rank.getSysInfoName());
		// audit.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
		// audit.setObject("系统定级");
		// audit.setTime(DateUtil.curDateTimeStr19());
		// audit.setType("修改");
		// auditService.instert(audit);
	}

	public RecordService getRecordService() {
		return recordService;
	}

	public void setRecordService(RecordService recordService) {
		this.recordService = recordService;
	}

	public String getSysInfoId() {
		return sysInfoId;
	}

	public void setSysInfoId(String sysInfoId) {
		this.sysInfoId = sysInfoId;
	}

	public Rank getRank() {
		return rank;
	}

	public RankService getRankService() {
		return rankService;
	}

	public List<Rank> getRanks() {
		return ranks;
	}

	public String getKeyword() {
		return keyword;
	}

	public int getCount() {
		return count;
	}

	public int getRankId() {
		return rankId;
	}

	public void setRankId(int rankId) {
		this.rankId = rankId;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public void setRankService(RankService rankService) {
		this.rankService = rankService;
	}

	public void setRanks(List<Rank> ranks) {
		this.ranks = ranks;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Rank getModel() {
		return this.rank;
	}

	public File getFrankEvalRelAccess() {
		return frankEvalRelAccess;
	}

	public void setFrankEvalRelAccess(File frankEvalRelAccess) {
		this.frankEvalRelAccess = frankEvalRelAccess;
	}

	public String getFrankEvalRelAccessFileName() {
		return frankEvalRelAccessFileName;
	}

	public void setFrankEvalRelAccessFileName(String frankEvalRelAccessFileName) {
		this.frankEvalRelAccessFileName = frankEvalRelAccessFileName;
	}

	public String getFrankEvalRelAccessPath() {
		return ServletActionContext.getServletContext().getRealPath(
				frankEvalRelAccessPath);
	}

	public void setFrankEvalRelAccessPath(String frankEvalRelAccessPath) {
		this.frankEvalRelAccessPath = frankEvalRelAccessPath;
	}

	public File getFrankAccess() {
		return frankAccess;
	}

	public void setFrankAccess(File frankAccess) {
		this.frankAccess = frankAccess;
	}

	public String getFrankAccessFileName() {
		return frankAccessFileName;
	}

	public void setFrankAccessFileName(String frankAccessFileName) {
		this.frankAccessFileName = frankAccessFileName;
	}

	public String getFrankAccessPath() {
		return ServletActionContext.getServletContext().getRealPath(
				frankAccessPath);
	}

	public void setFrankAccessPath(String frankAccessPath) {
		this.frankAccessPath = frankAccessPath;
	}

	public File getFtopRelAcc1() {
		return ftopRelAcc1;
	}

	public void setFtopRelAcc1(File ftopRelAcc1) {
		this.ftopRelAcc1 = ftopRelAcc1;
	}

	public String getFtopRelAcc1FileName() {
		return ftopRelAcc1FileName;
	}

	public void setFtopRelAcc1FileName(String ftopRelAcc1FileName) {
		this.ftopRelAcc1FileName = ftopRelAcc1FileName;
	}

	public String getFtopRelAcc1SavePath() {
		return ServletActionContext.getServletContext().getRealPath(
				ftopRelAcc1SavePath);
	}

	public void setFtopRelAcc1SavePath(String ftopRelAcc1SavePath) {
		this.ftopRelAcc1SavePath = ftopRelAcc1SavePath;
	}

	public File getFsysManRel1() {
		return fsysManRel1;
	}

	public void setFsysManRel1(File fsysManRel1) {
		this.fsysManRel1 = fsysManRel1;
	}

	public String getFsysManRel1FileName() {
		return fsysManRel1FileName;
	}

	public void setFsysManRel1FileName(String fsysManRel1FileName) {
		this.fsysManRel1FileName = fsysManRel1FileName;
	}

	public String getFsysManRel1SavePath() {
		return ServletActionContext.getServletContext().getRealPath(
				fsysManRel1SavePath);
	}

	public void setFsysManRel1SavePath(String fsysManRel1SavePath) {
		this.fsysManRel1SavePath = fsysManRel1SavePath;
	}

	public File getFsysPlanRel1() {
		return fsysPlanRel1;
	}

	public void setFsysPlanRel1(File fsysPlanRel1) {
		this.fsysPlanRel1 = fsysPlanRel1;
	}

	public String getFsysPlanRel1FileName() {
		return fsysPlanRel1FileName;
	}

	public void setFsysPlanRel1FileName(String fsysPlanRel1FileName) {
		this.fsysPlanRel1FileName = fsysPlanRel1FileName;
	}

	public String getFsysPlanRel1SavePath() {
		return ServletActionContext.getServletContext().getRealPath(
				fsysPlanRel1SavePath);
	}

	public void setFsysPlanRel1SavePath(String fsysPlanRel1SavePath) {
		this.fsysPlanRel1SavePath = fsysPlanRel1SavePath;
	}

	public File getFsysLiceRel1() {
		return fsysLiceRel1;
	}

	public void setFsysLiceRel1(File fsysLiceRel1) {
		this.fsysLiceRel1 = fsysLiceRel1;
	}

	public String getFsysLiceRel1FileName() {
		return fsysLiceRel1FileName;
	}

	public void setFsysLiceRel1FileName(String fsysLiceRel1FileName) {
		this.fsysLiceRel1FileName = fsysLiceRel1FileName;
	}

	public String getFsysLiceRel1SavePath() {
		return ServletActionContext.getServletContext().getRealPath(
				fsysLiceRel1SavePath);
	}

	public void setFsysLiceRel1SavePath(String fsysLiceRel1SavePath) {
		this.fsysLiceRel1SavePath = fsysLiceRel1SavePath;
	}

	public File getFsysReportRel1() {
		return fsysReportRel1;
	}

	public void setFsysReportRel1(File fsysReportRel1) {
		this.fsysReportRel1 = fsysReportRel1;
	}

	public String getFsysReportRel1FileName() {
		return fsysReportRel1FileName;
	}

	public void setFsysReportRel1FileName(String fsysReportRel1FileName) {
		this.fsysReportRel1FileName = fsysReportRel1FileName;
	}

	public String getFsysReportRel1SavePath() {
		return ServletActionContext.getServletContext().getRealPath(
				fsysReportRel1SavePath);
	}

	public void setFsysReportRel1SavePath(String fsysReportRel1SavePath) {
		this.fsysReportRel1SavePath = fsysReportRel1SavePath;
	}

	public File getFpeerRevRel1() {
		return fpeerRevRel1;
	}

	public void setFpeerRevRel1(File fpeerRevRel1) {
		this.fpeerRevRel1 = fpeerRevRel1;
	}

	public String getFpeerRevRel1FileName() {
		return fpeerRevRel1FileName;
	}

	public void setFpeerRevRel1FileName(String fpeerRevRel1FileName) {
		this.fpeerRevRel1FileName = fpeerRevRel1FileName;
	}

	public String getFpeerRevRel1SavePath() {
		return ServletActionContext.getServletContext().getRealPath(
				fpeerRevRel1SavePath);
	}

	public void setFpeerRevRel1SavePath(String fpeerRevRel1SavePath) {
		this.fpeerRevRel1SavePath = fpeerRevRel1SavePath;
	}

	public File getFsuperOpinRel1() {
		return fsuperOpinRel1;
	}

	public void setFsuperOpinRel1(File fsuperOpinRel1) {
		this.fsuperOpinRel1 = fsuperOpinRel1;
	}

	public String getFsuperOpinRel1FileName() {
		return fsuperOpinRel1FileName;
	}

	public void setFsuperOpinRel1FileName(String fsuperOpinRel1FileName) {
		this.fsuperOpinRel1FileName = fsuperOpinRel1FileName;
	}

	public String getFsuperOpinRel1SavePath() {
		return ServletActionContext.getServletContext().getRealPath(
				fsuperOpinRel1SavePath);
	}

	public void setFsuperOpinRel1SavePath(String fsuperOpinRel1SavePath) {
		this.fsuperOpinRel1SavePath = fsuperOpinRel1SavePath;
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

	public String getRankstatus() {
		return rankstatus;
	}

	public void setRankstatus(String rankstatus) {
		this.rankstatus = rankstatus;
	}

	public String getReFrankEvalRelAccessFileName() {
		return reFrankEvalRelAccessFileName;
	}

	public void setReFrankEvalRelAccessFileName(
			String reFrankEvalRelAccessFileName) {
		this.reFrankEvalRelAccessFileName = reFrankEvalRelAccessFileName;
	}

	public String getReFrankAccessFileName() {
		return reFrankAccessFileName;
	}

	public void setReFrankAccessFileName(String reFrankAccessFileName) {
		this.reFrankAccessFileName = reFrankAccessFileName;
	}

	public String getReFtopRelAcc1FileName() {
		return reFtopRelAcc1FileName;
	}

	public void setReFtopRelAcc1FileName(String reFtopRelAcc1FileName) {
		this.reFtopRelAcc1FileName = reFtopRelAcc1FileName;
	}

	public String getReFsysManRel1FileName() {
		return reFsysManRel1FileName;
	}

	public void setReFsysManRel1FileName(String reFsysManRel1FileName) {
		this.reFsysManRel1FileName = reFsysManRel1FileName;
	}

	public String getReFsysPlanRel1FileName() {
		return reFsysPlanRel1FileName;
	}

	public void setReFsysPlanRel1FileName(String reFsysPlanRel1FileName) {
		this.reFsysPlanRel1FileName = reFsysPlanRel1FileName;
	}

	public String getReFsysLiceRel1FileName() {
		return reFsysLiceRel1FileName;
	}

	public void setReFsysLiceRel1FileName(String reFsysLiceRel1FileName) {
		this.reFsysLiceRel1FileName = reFsysLiceRel1FileName;
	}

	public String getReFsysReportRel1FileName() {
		return reFsysReportRel1FileName;
	}

	public void setReFsysReportRel1FileName(String reFsysReportRel1FileName) {
		this.reFsysReportRel1FileName = reFsysReportRel1FileName;
	}

	public String getReFpeerRevRel1FileName() {
		return reFpeerRevRel1FileName;
	}

	public void setReFpeerRevRel1FileName(String reFpeerRevRel1FileName) {
		this.reFpeerRevRel1FileName = reFpeerRevRel1FileName;
	}

	public String getReFsuperOpinRel1FileName() {
		return reFsuperOpinRel1FileName;
	}

	public void setReFsuperOpinRel1FileName(String reFsuperOpinRel1FileName) {
		this.reFsuperOpinRel1FileName = reFsuperOpinRel1FileName;
	}

}
