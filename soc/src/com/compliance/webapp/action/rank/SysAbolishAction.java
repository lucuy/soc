package com.compliance.webapp.action.rank;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;


import com.compliance.model.basicinfo.system.SystemManager;
import com.compliance.model.rank.SysAbolish;
import com.compliance.service.basicinfo.system.SystemService;
import com.compliance.service.rank.SysAbolishService;
import com.compliance.webapp.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import com.util.DateUtil;
import com.util.FileUtil;

public class SysAbolishAction extends BaseAction implements ModelDriven<SysAbolish> {

	private SysAbolishService sysAbolishService;
	private SystemService systemService;
	private List<SystemManager> systemManagers;
	private SysAbolish sysAbolish = new SysAbolish();
	private int pkSysInfo;
	//系统废止信息删除证明附件上传
	private File fsysAccess;
	private String fsysAccessFileName;
	private String fsysAccessPath;
	private String reFsysAccessFileName;
	//设备清除证明附件上传
	private File fdevAccess;
	private String fdevAccessFileName;
	private String fdevAccessPath;
	private String reFdevAccessFileName;
	//存储清除证明附件上传
	private File fstorAccess;
	private String fstorAccessFileName;
	private String fstorAccessPath;
	private String reFstorAccessFileName;
	/**
	 * 审计接口
	 */
//	private AuditService auditService;
	public List<SystemManager> getSystemManagers() {
		return systemManagers;
	}

	public void setSystemManagers(List<SystemManager> systemManagers) {
		this.systemManagers = systemManagers;
	}

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

	public SysAbolish getSysAbolish() {
		return sysAbolish;
	}

	public void setSysAbolish(SysAbolish sysAbolish) {
		this.sysAbolish = sysAbolish;
	}

	public int getPkSysInfo() {
		return pkSysInfo;
	}

	public void setPkSysInfo(int pkSysInfo) {
		this.pkSysInfo = pkSysInfo;
	}
	

	public File getFsysAccess() {
		return fsysAccess;
	}

	public void setFsysAccess(File fsysAccess) {
		this.fsysAccess = fsysAccess;
	}

	public String getFsysAccessFileName() {
		return fsysAccessFileName;
	}

	public void setFsysAccessFileName(String fsysAccessFileName) {
		this.fsysAccessFileName = fsysAccessFileName;
	}

	public String getFsysAccessPath() {
		return ServletActionContext.getServletContext().getRealPath(fsysAccessPath);
	}

	public void setFsysAccessPath(String fsysAccessPath) {
		this.fsysAccessPath = fsysAccessPath;
	}
	

	public File getFdevAccess() {
		return fdevAccess;
	}

	public void setFdevAccess(File fdevAccess) {
		this.fdevAccess = fdevAccess;
	}

	public String getFdevAccessFileName() {
		return fdevAccessFileName;
	}

	public void setFdevAccessFileName(String fdevAccessFileName) {
		this.fdevAccessFileName = fdevAccessFileName;
	}	

	public String getFdevAccessPath() {
		return ServletActionContext.getServletContext().getRealPath(fdevAccessPath);
	}

	public void setFdevAccessPath(String fdevAccessPath) {
		this.fdevAccessPath = fdevAccessPath;
	}
	

	public File getFstorAccess() {
		return fstorAccess;
	}

	public void setFstorAccess(File fstorAccess) {
		this.fstorAccess = fstorAccess;
	}

	public String getFstorAccessFileName() {
		return fstorAccessFileName;
	}

	public void setFstorAccessFileName(String fstorAccessFileName) {
		this.fstorAccessFileName = fstorAccessFileName;
	}

	public String getFstorAccessPath() {
		return ServletActionContext.getServletContext().getRealPath(fstorAccessPath);
	}

	public void setFstorAccessPath(String fstorAccessPath) {
		this.fstorAccessPath = fstorAccessPath;
	}

	public String getReFsysAccessFileName() {
		return reFsysAccessFileName;
	}

	public void setReFsysAccessFileName(String reFsysAccessFileName) {
		this.reFsysAccessFileName = reFsysAccessFileName;
	}

	public String getReFdevAccessFileName() {
		return reFdevAccessFileName;
	}

	public void setReFdevAccessFileName(String reFdevAccessFileName) {
		this.reFdevAccessFileName = reFdevAccessFileName;
	}

	public String getReFstorAccessFileName() {
		return reFstorAccessFileName;
	}

	public void setReFstorAccessFileName(String reFstorAccessFileName) {
		this.reFstorAccessFileName = reFstorAccessFileName;
	}

	public String querySysInFo() {
		HttpServletRequest request = super.getRequest();
		String str = request.getParameter("pkSysInfo");
		pkSysInfo=Integer.parseInt(str);
		sysAbolish = sysAbolishService.query(pkSysInfo);
		if(sysAbolish==null){
			sysAbolish=new SysAbolish();
		}
		sysAbolish.setPkSysInfo(pkSysInfo);
		return SUCCESS;

	}
	
	
	public String queryForJson() {
		
		systemManagers=systemService.query(null);
		
		return SUCCESS;

	}

	public String editBegin() {

		int pkSysInfo=this.sysAbolish.getPkSysInfo();
		sysAbolish=sysAbolishService.query(this.sysAbolish.getPkSysInfo());
		if(sysAbolish==null){
			sysAbolish=new SysAbolish();
			sysAbolish.setPkSysInfo(pkSysInfo);
		}
		return SUCCESS;
	}
	
	public String sysAbolishEdit() {
		//System.out.println("======================="+fstorAccessFileName);
		SysAbolish abolish=sysAbolishService.query(this.sysAbolish.getPkSysInfo());
		if(fsysAccessFileName!=null){
			sysAbolish.setSysAccess(new String(fsysAccessFileName));
			sysAbolish.setReSysAccess(reFsysAccessFileName);
		}
		
		if(fdevAccessFileName!=null){
			sysAbolish.setDevAccess(fdevAccessFileName);
			sysAbolish.setReDevAccess(reFdevAccessFileName);
		}
		
		if(fstorAccessFileName!=null){
			sysAbolish.setStorAccess(fstorAccessFileName);
			sysAbolish.setReStorAccess(reFstorAccessFileName);
		}
		
		/*Audit audit=new Audit();
		audit.setDetailed(systemService.queryById(this.sysAbolish.getPkSysInfo()).getSysName());
		audit.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
		audit.setObject("系统废止");
		audit.setTime(DateUtil.curDateTimeStr19());*/
		if(abolish==null){	
			sysAbolishService.insert(this.sysAbolish);
			//audit.setType("添加");
		}else{			
			sysAbolishService.update(this.sysAbolish);
			//audit.setType("修改");
		}
		//auditService.instert(audit);
		return SUCCESS;
	}
	/**
	 * 信息证明附件上传
	 * @throws IOException 
	 */
	public void upsysabfile() throws IOException{
		String str = "";
		File savedir = new File(getFsysAccessPath());
		System.out.println(getFsysAccessPath());
		if (!savedir.exists()) {
			savedir.mkdirs();
		}
		// 重命名源文件以配置文件中的路径及上传文件名创建输出流
		reFsysAccessFileName = FileUtil.renameFileName(this.getFsysAccessFileName());
		// 目的目录
		String target="";
		if(System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS")==0){
				target = savedir + "\\" + reFsysAccessFileName;
		}else{
				target = savedir + "/" + reFsysAccessFileName;
		}
				
		// 源文件
		FileInputStream fis = new FileInputStream(getFsysAccess());
		Boolean upornot = FileUtil.upFile(fis, target);
		str += this.getFsysAccessFileName();
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
		out.println("<script type=\"text/javascript\">parent.callback2('reFsysAccessFileName','"
				+ reFsysAccessFileName.toString() + "')</script>");
		return;
		
	}
	/**
	 * 设备清除证明附件上传
	 * @throws IOException 
	 */
	public void updevabfile() throws IOException{
		String str = "";
		File savedir = new File(getFdevAccessPath());
		if (!savedir.exists()) {
			savedir.mkdirs();
		}
		// 重命名源文件以配置文件中的路径及上传文件名创建输出流
		reFdevAccessFileName = FileUtil.renameFileName(this.getFdevAccessFileName());
		// 目的目录
		String target="";
		if(System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS")==0){
				target = savedir + "\\" + reFdevAccessFileName;
		}else{
				target = savedir + "/" + reFdevAccessFileName;
		}
						
		// 源文件
		FileInputStream fis = new FileInputStream(getFdevAccess());
		Boolean upornot = FileUtil.upFile(fis, target);
		str += this.getFdevAccessFileName();
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
		out.println("<script type=\"text/javascript\">parent.callback2('reFdevAccessFileName','"
				+ reFdevAccessFileName.toString() + "')</script>");
		return;
	}
	/**
	 * 设备清除证明附件上传
	 * @throws IOException 
	 */
	public void upstoreabfile() throws IOException{
		String str = "";
		File savedir = new File(getFstorAccessPath());
		if (!savedir.exists()) {
			savedir.mkdirs();
		}
		// 重命名源文件以配置文件中的路径及上传文件名创建输出流
		reFstorAccessFileName = FileUtil.renameFileName(this.getFstorAccessFileName());
		// 目的目录
		String target="";
		if(System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS")==0){
				target = savedir + "\\" + reFstorAccessFileName;
		}else{
				target = savedir + "/" + reFstorAccessFileName;
		}
								
		// 源文件
		FileInputStream fis = new FileInputStream(getFstorAccess());
		Boolean upornot = FileUtil.upFile(fis, target);
		str += this.getFstorAccessFileName();
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
		out.println("<script type=\"text/javascript\">parent.callback2('reFstorAccessFileName','"
				+ reFstorAccessFileName.toString() + "')</script>");
		return;
	}
	public SysAbolish getModel() {

		return sysAbolish;
	}
	
}
