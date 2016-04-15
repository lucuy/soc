package com.soc.webapp.action.systemsetting;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.net.aso.s;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.soc.dao.systemsetting.SettingArchiveDao;
import com.soc.model.systemsetting.Setting;
import com.soc.model.systemsetting.SettingArchive;
import com.soc.service.systemsetting.SettingArchiveService;
import com.soc.service.systemsetting.SettingService;
import com.soc.webapp.action.BaseAction;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <归档> <功能详细描述>
 * 
 * @author yinhaiping
 * @version [版本号, 2012-12-5]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SettingArchiveAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private SettingArchiveService archiveManager;

	private List<SettingArchive> archiveList;

	private SettingArchiveDao archiveDao;

	private SettingArchive archive;
	private String archiveId;
	private String archiveDate;
	private String archiveErr;
	private String archiveName;
	private long archiveStatus; // 状态
	private String msg;			// 传到前台的提示
	private Setting setting;
	private String statuss;
	private String archiveAutoTime;
	private String keyword;

	private SettingService settingManager;
	//归档路径的设置
    private String archiveAutoPath;
    //文件容量
    private long totalCapacity;
    //告警的临界值
    private long thresholds;
    //告警邮箱设置
    private String archiveMail;
	/**
	 * <查询归档的表> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String archiveList() {
		Page page = null;
		SearchResult sr = null;
		Map<String, Object> map = new HashMap<String, Object>();
		HttpServletRequest request = super.getRequest();
		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		
		if (StringUtil.isNotBlank(statuss)) {
			archiveStatus = Long.parseLong(statuss);
			map.put("archiveStatus", archiveStatus);
		}
		if(StringUtil.isNotBlank(archiveDate)){
			map.put("archiveDate", archiveDate);
		}
		if (StringUtil.isNotBlank(archiveName)) {
			map.put("archiveName", archiveName);
		}
		if (StringUtil.isNotBlank(keyword)) {
			map.put("keyword", keyword);
		}
		
		// 根据map中存储的查询条件，查找符合条件的用户列表

		sr = archiveManager.queryPage(map, page);

		// 对查找的结果为分页赋值
		if (sr != null) {
			archiveList = (List<SettingArchive>) sr.getList();
			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}

		return SUCCESS;
	}

	/**
	 * <执行操作> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String updateArchive() {
		archive = new SettingArchive();

		if (StringUtil.isNotBlank(archiveId)) {
			String[] checked = archiveId.split(",");

			for (String checkid : checked) {
				archive = this.archiveManager.queryById(Long.parseLong(checkid));
				archive.setArchiveStatus(archiveStatus);
				archiveErr = archiveManager.updataById(archive, archiveStatus,
						Long.parseLong(checkid));
				if (archiveStatus == 2) {
					this.Download(getResponse());
				}
			}
			if (archiveStatus != 2) {
				return SUCCESS;
			}
		}
		return null;
	}

	/* 下载 */
	public HttpServletResponse Download(HttpServletResponse response) {
		if (archiveStatus == 2) {
			// start
			//String path = "/home/archiveDownload/archive.zip";
			archiveAutoPath = settingManager.queryByKey("archiveAutoPath");//李长红修改
			
			String path = archiveAutoPath+"/archiveDownload/archive.zip";
			
			try {
				// path是指欲下载的文件的路径。
				File file = new File(path);
				// 取得文件名。
				String filename = file.getName();
				// 取得文件的后缀名。
				String ext = filename.substring(filename.lastIndexOf(".") + 1)
						.toUpperCase();

				// 以流的形式下载文件。
				InputStream fis = new BufferedInputStream(new FileInputStream(
						path));
				byte[] buffer = new byte[fis.available()];
				fis.read(buffer);
				fis.close();
				// 清空response
				response.reset();
				// 设置response的Header
				response.addHeader("Content-Disposition",
						"attachment;filename="
								+ new String(filename.getBytes()));
				response.addHeader("Content-Length", "" + file.length());
				OutputStream toClient = new BufferedOutputStream(
						response.getOutputStream());
				response.setContentType("application/octet-stream");
				toClient.write(buffer);
				toClient.flush();
				toClient.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return response;
	}

	public String auto() {
		setting = new Setting();
		if (StringUtil.isNotBlank(archiveAutoTime)) {
			//保存告警时间
			setting.setKey("archiveAutoTime");
			setting.setValue(archiveAutoTime);
			settingManager.updateByKey("archiveAutoTime", setting);
			//保存
			setting.setKey("archiveAutoPath");
            setting.setValue(archiveAutoPath);
            settingManager.updateByKey("archiveAutoPath", setting);
            //保存文件容量
            setting.setKey("totalCapacity");
            setting.setValue(totalCapacity+"");
            settingManager.updateByKey("totalCapacity", setting);
            //保存告警的临界值
            setting.setKey("thresholds");
            setting.setValue(thresholds+"");
            settingManager.updateByKey("thresholds", setting);
            //保存告警邮箱
            setting.setKey("archiveMail");
            setting.setValue(archiveMail);
            settingManager.updateByKey("archiveMail", setting);
			msg="保存成功！";
		} else {
			if (settingManager.queryByKey("archiveAutoTime") != null) {
				archiveAutoTime = settingManager.queryByKey("archiveAutoTime");
				archiveAutoPath = settingManager.queryByKey("archiveAutoPath");
				totalCapacity = Long.parseLong(settingManager.queryByKey("totalCapacity"));
				thresholds = Long.parseLong(settingManager.queryByKey("thresholds"));
				archiveMail = settingManager.queryByKey("archiveMail");
				return SUCCESS;
			} else {
				msg="请输入自动归档时间。";
				return SUCCESS;
			}
		}
		
		return SUCCESS;
	}
	public SettingArchiveService getArchiveManager() {
		return archiveManager;
	}

	public void setArchiveManager(SettingArchiveService archiveManager) {
		this.archiveManager = archiveManager;
	}

	public List<SettingArchive> getArchiveList() {
		return archiveList;
	}

	public void setArchiveList(List<SettingArchive> archiveList) {
		this.archiveList = archiveList;
	}

	public String getArchiveId() {
		return archiveId;
	}

	public void setArchiveId(String archiveId) {
		this.archiveId = archiveId;
	}

	public SettingArchive getArchive() {
		return archive;
	}

	public void setArchive(SettingArchive archive) {
		this.archive = archive;
	}

	public SettingArchiveDao getArchiveDao() {
		return archiveDao;
	}

	public void setArchiveDao(SettingArchiveDao archiveDao) {
		this.archiveDao = archiveDao;
	}

	public String getArchiveErr() {
		return archiveErr;
	}

	public void setArchiveErr(String archiveErr) {
		this.archiveErr = archiveErr;
	}

	public String getArchiveAutoTime() {
		return archiveAutoTime;
	}

	public void setArchiveAutoTime(String archiveAutoTime) {
		this.archiveAutoTime = archiveAutoTime;
	}

	public Setting getSetting() {
		return setting;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}

	public SettingService getSettingManager() {
		return settingManager;
	}

	public void setSettingManager(SettingService settingManager) {
		this.settingManager = settingManager;
	}

	public long getArchiveStatus() {
		return archiveStatus;
	}

	public void setArchiveStatus(long archiveStatus) {
		this.archiveStatus = archiveStatus;
	}

	public String getArchiveName() {
		return archiveName;
	}

	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}

	public String getStatuss() {
		return statuss;
	}

	public void setStatuss(String statuss) {
		this.statuss = statuss;
	}

	public String getArchiveDate() {
		return archiveDate;
	}

	public void setArchiveDate(String archiveDate) {
		this.archiveDate = archiveDate;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getArchiveAutoPath() {
		return archiveAutoPath;
	}

	public void setArchiveAutoPath(String archiveAutoPath) {
		this.archiveAutoPath = archiveAutoPath;
	}

	public long getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(long totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public long getThresholds() {
		return thresholds;
	}

	public void setThresholds(long thresholds) {
		this.thresholds = thresholds;
	}

	public String getArchiveMail() {
		return archiveMail;
	}

	public void setArchiveMail(String archiveMail) {
		this.archiveMail = archiveMail;
	}

	
	

}