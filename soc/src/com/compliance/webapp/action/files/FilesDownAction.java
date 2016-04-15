package com.compliance.webapp.action.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.compliance.model.basicinfo.assets.DocAssets;
import com.compliance.model.basicinfo.system.SystemManager;
import com.compliance.model.rank.Rank;
import com.compliance.model.rank.SysAbolish;
import com.compliance.service.basicinfo.system.SystemService;
import com.compliance.service.files.FilesService;
import com.compliance.service.rank.RankReportService;
import com.compliance.service.rank.RankService;
import com.compliance.service.rank.SysAbolishService;
import com.compliance.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class FilesDownAction extends BaseAction {
	private String filetype;
	private FilesService fileservice;
	private List<SysAbolish> abolishfiles;
	private List<Rank> rankfiles;
	private String filename;
	private String rename;
	private String inputpath;
	private String inputpath2;
	private String inputpath3;
	private String inputpath4;
	private String inputpath5;
	private String inputpath6;
	private String inputpath7;
	private String inputpath8;
	private String inputpath9;
	private String inputpath10;
	private String inputpath11;
	private String inputpath12;
	private String inputpath13;
	//信息系统
	private SystemService systemService;

	//系统废止
	private SysAbolishService sysAbolishService;
	//系统定级文档
	private RankService rankService;

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public SysAbolishService getSysAbolishService() {
		return sysAbolishService;
	}

	public void setSysAbolishService(SysAbolishService sysAbolishService) {
		this.sysAbolishService = sysAbolishService;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public FilesService getFileservice() {
		return fileservice;
	}

	public void setFileservice(FilesService fileservice) {
		this.fileservice = fileservice;
	}


	public RankService getRankService() {
		return rankService;
	}

	public void setRankService(RankService rankService) {
		this.rankService = rankService;
	}

	public List<Rank> getRankfiles() {
		return rankfiles;
	}

	public void setRankfiles(List<Rank> rankfiles) {
		this.rankfiles = rankfiles;
	}

	public List<SysAbolish> getAbolishfiles() {
		return abolishfiles;
	}

	public void setAbolishfiles(List<SysAbolish> abolishfiles) {
		this.abolishfiles = abolishfiles;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename)
			throws UnsupportedEncodingException {
		this.filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
	}

	public String getRename() {
		return rename;
	}

	public void setRename(String rename) {
		this.rename = rename;
	}

	public String getInputpath() {
		return inputpath;
	}

	public void setInputpath(String inputpath) {
		this.inputpath = inputpath;
	}

	public String getInputpath2() {
		return inputpath2;
	}

	public void setInputpath2(String inputpath2) {
		this.inputpath2 = inputpath2;
	}

	public String getInputpath3() {
		return inputpath3;
	}

	public void setInputpath3(String inputpath3) {
		this.inputpath3 = inputpath3;
	}

	public String getInputpath4() {
		return inputpath4;
	}

	public void setInputpath4(String inputpath4) {
		this.inputpath4 = inputpath4;
	}

	public String getInputpath5() {
		return inputpath5;
	}

	public void setInputpath5(String inputpath5) {
		this.inputpath5 = inputpath5;
	}

	public String getInputpath6() {
		return inputpath6;
	}

	public void setInputpath6(String inputpath6) {
		this.inputpath6 = inputpath6;
	}

	public String getInputpath7() {
		return inputpath7;
	}

	public void setInputpath7(String inputpath7) {
		this.inputpath7 = inputpath7;
	}

	public String getInputpath8() {
		return inputpath8;
	}

	public void setInputpath8(String inputpath8) {
		this.inputpath8 = inputpath8;
	}

	public String getInputpath9() {
		return inputpath9;
	}

	public void setInputpath9(String inputpath9) {
		this.inputpath9 = inputpath9;
	}

	public String getInputpath10() {
		return inputpath10;
	}

	public void setInputpath10(String inputpath10) {
		this.inputpath10 = inputpath10;
	}

	public String getInputpath11() {
		return inputpath11;
	}

	public void setInputpath11(String inputpath11) {
		this.inputpath11 = inputpath11;
	}

	public String getInputpath12() {
		return inputpath12;
	}

	public void setInputpath12(String inputpath12) {
		this.inputpath12 = inputpath12;
	}

	public String getInputpath13() {
		return inputpath13;
	}

	public void setInputpath13(String inputpath13) {
		this.inputpath13 = inputpath13;
	}

//	public InputStream getInputStream() throws UnsupportedEncodingException,
//			FileNotFoundException {
//		HttpServletResponse response = ServletActionContext.getResponse();
//
//		FileInputStream inputstream = null;
//		if ("11".equals(this.getFiletype())) {
//			inputstream = new FileInputStream(ServletActionContext
//					.getServletContext().getRealPath(
//							"/" + inputpath11 + rename ));
//
//		} else if ("12".equals(this.getFiletype())) {
//			inputstream = new FileInputStream(ServletActionContext
//					.getServletContext().getRealPath(
//							"/" + inputpath12 + rename));
//
//		} else if ("13".equals(this.getFiletype())) {
//			inputstream = new FileInputStream(ServletActionContext
//					.getServletContext().getRealPath(
//							"/" + inputpath13 + rename));
//
//		}
//		response.setHeader("Content-Disposition", "attachment;fileName="
//				+ java.net.URLEncoder.encode(filename, "UTF-8"));
//
//		// 返回文件下载入口输入流
//		return inputstream;
//
//	}
//	
	public String queryFilesByType() {
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
		SearchResult sr = new SearchResult();
		if ("11".equals(this.getFiletype())|| "12".equals(this.getFiletype())|| "13".equals(this.getFiletype())) {
			if ("11".equals(this.getFiletype())) {
				map.put("keyword1", "11");

			} else if ("12".equals(this.getFiletype())) {
				map.put("keyword2", "12");

			} else if ("13".equals(this.getFiletype())) {
				map.put("keyword3", "13");

			}
			sr = fileservice.query(map, page, "13");
			this.abolishfiles = (List<SysAbolish>) sr.getList();
			for(int i=0;i<this.abolishfiles.size();i++){
				SysAbolish abolish=this.abolishfiles.get(i);
				SystemManager abolishsystem=systemService.queryById(abolish.getPkSysInfo());
				abolish.setAbolishsysnames(abolishsystem.getSysName());
			}
			request.setAttribute("abolishfiles", sr.getList());
		}else{
			if ("2".equals(this.getFiletype())) {
				map.put("keyword1", "1");

			} else if ("3".equals(this.getFiletype())) {
				map.put("keyword2", "1");

			} else if ("4".equals(this.getFiletype())) {
				map.put("keyword3", "1");

			} else if ("5".equals(this.getFiletype())) {
				map.put("keyword4", "1");

			} else if ("6".equals(this.getFiletype())) {
				map.put("keyword5", "1");

			} else if ("7".equals(this.getFiletype())) {
				map.put("keyword6", "1");

			} else if ("8".equals(this.getFiletype())) {
				map.put("keyword7", "1");

			} else if ("9".equals(this.getFiletype())) {
				map.put("keyword8", "1");

			} else if ("10".equals(this.getFiletype())) {
				map.put("keyword9", "1");

			}
			sr = fileservice.query(map, page, "8");
			this.rankfiles = (List<Rank>) sr.getList();
			request.setAttribute("rankfiles", sr.getList());
			
		}

		request.setAttribute("Page", sr.getPage());
		request.setAttribute("filestype", this.getFiletype());
		return SUCCESS;

	}

	public void downfiles() throws Exception {
//		//审计管理
//		String userName = (String) super.getSession().getAttribute(
//				"SSI_LOGIN_USER");
//		String time = DateUtil.putDateToTimeStr19(new Date());
		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		String pkSysInfo=request.getParameter("pkSysInfo");
		String sysInfoId=request.getParameter("sysInfoId");
		String inputPath=null;
		String filename=null;
		String refilename=null;
		if ("11".equals(this.getFiletype())|| "12".equals(this.getFiletype())|| "13".equals(this.getFiletype())) {
			SysAbolish sysAbolish=sysAbolishService.query(Integer.parseInt(pkSysInfo));
			if ("11".equals(this.getFiletype())){
				inputPath=inputpath11;
				filename=sysAbolish.getSysAccess();
				refilename=sysAbolish.getReSysAccess();
			}else if("12".equals(this.getFiletype())){
				inputPath=inputpath12;
				filename=sysAbolish.getDevAccess();
				refilename=sysAbolish.getReDevAccess();
				
			}else if("13".equals(this.getFiletype())){
				inputPath=inputpath13;
				filename=sysAbolish.getStorAccess();
				refilename=sysAbolish.getReStorAccess();
			}
		}else {
			Rank rank=rankService.queryBySysId(sysInfoId);
			if ("2".equals(this.getFiletype())) {
				inputPath=inputpath2;
				filename=rank.getRankEvalRelAccess();
				refilename=rank.getReRankEvalRelAccess();
				
			} else if ("3".equals(this.getFiletype())) {
				inputPath=inputpath3;
				filename=rank.getRankAccess();
				refilename=rank.getReRankAccess();
			} else if ("4".equals(this.getFiletype())) {
				inputPath=inputpath4;
				filename=rank.getRankTopRelAcc();
				refilename=rank.getReRankTopRelAcc();
			} else if ("5".equals(this.getFiletype())) {
				inputPath=inputpath5;
				filename=rank.getRankSysManRel();
				refilename=rank.getReRankSysManRel();
			} else if ("6".equals(this.getFiletype())) {
				inputPath=inputpath6;
				filename=rank.getRankSysPlanRel();
				refilename=rank.getReRankSysPlanRel();
			} else if ("7".equals(this.getFiletype())) {
				inputPath=inputpath7;
				filename=rank.getRankSysLiceRel();
				refilename=rank.getReRankSysLiceRel();
			} else if ("8".equals(this.getFiletype())) {
				inputPath=inputpath8;
				filename=rank.getRankSysReportRel();
				refilename=rank.getReRankSysLiceRel();
			} else if ("9".equals(this.getFiletype())) {
				inputPath=inputpath9;
				filename=rank.getRankPeerRevRel();
				refilename=rank.getReRankPeerRevRel();
			} else if ("10".equals(this.getFiletype())) {
				inputPath=inputpath10;
				filename=rank.getRankSuperOpinRel();
				refilename=rank.getReRankSuperOpinRel();
			}
		}
		AttachmentDownload(response,inputPath,refilename,filename);
	}
	/*
	 * 下载文档
	 */
	public static void AttachmentDownload(HttpServletResponse response,String inputPath,String refilename,String fileName){	
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/x-msdownload;charset=UTF-8");		
		//String path = "load/";
		String recordPath =ServletActionContext.getServletContext().getRealPath("/" + inputPath + refilename);
		File file = null;
		
		try {
			//file = new File(path + fileName);
			//fileName = "年前工作安排.doc";
			file = new File(recordPath);
			if (file.isFile() && file.exists()) {
				
				response.addHeader("Content-Disposition","attachment;filename="+java.net.URLEncoder.encode(fileName, "UTF-8"));
				
				FileInputStream fileInputStream = null;
				byte[] buf = new byte[1024];
				int readLength;
				try {
					fileInputStream = new FileInputStream(file);
					while ((readLength = fileInputStream.read(buf)) != -1) {
						response.getOutputStream().write(buf, 0, readLength);
					}
					response.getOutputStream().flush();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						fileInputStream.close();
						response.getOutputStream().close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			} else {
				//System.out.println("File does not exist !");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
