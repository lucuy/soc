package com.soc.webapp.action.systemsetting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.soc.webapp.action.BaseAction;
import com.util.FileUtil;
import com.util.OSUtil;

/**
 * 数据备份与恢复
 * 
 * @author zsa
 * 
 */
public class DataSettingAction extends BaseAction {

	private File upSql;
	private String upSqlFileName;

	private InputStream in;
	private String filename;

	public InputStream getIn() {
		return in;
	}

	public void setIn(InputStream in) {
		this.in = in;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * 跳转到备份页面
	 * 
	 * @return
	 */
	public String toUpdate() {
		return SUCCESS;
	}

	/**
	 * 上传sql文件
	 * 
	 * @return
	 */
	public String upData() {
		if (!OSUtil.getOSName().toLowerCase().contains("linux")) {
			super.addActionMessage("数据恢复功能必须部署在liunx系统上");
			return "syserror";
		} else {
			super.clearErrorsAndMessages();
			String path = ServletActionContext.getServletContext().getRealPath(
					"/sqlBak");
			File sqlFile = new File(new File(path), upSqlFileName);

			if (!sqlFile.getParentFile().exists()) {
				sqlFile.getParentFile().mkdirs();
			}
			try {
				FileUtils.copyFile(upSql, sqlFile);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			File f = new File(path + "/" + "fortbak.sql");
			if (f.isFile()) {
				Process process = null;
				String pro = "bash /root/src.sh";
				try {
					process = Runtime.getRuntime().exec(pro);
					StringBuffer buffer = new StringBuffer();
					String buf;
					BufferedReader bfRd = new BufferedReader(
							new InputStreamReader(process.getInputStream()));
					while ((buf = bfRd.readLine()) != null) {
						buffer.append(buf);
					}
					log.info("upsql inputstream:" + buffer.toString());
					if (bfRd != null) {
						bfRd.close();
					}

					buffer = new StringBuffer();
					buf = "";
					bfRd = new BufferedReader(new InputStreamReader(
							process.getErrorStream()));
					while ((buf = bfRd.readLine()) != null) {
						buffer.append(buf);
					}
					log.info("upsql errorstream:" + buffer.toString());
					if (bfRd != null) {
						bfRd.close();
					}
					addActionMessage("升级sql成功");
					f.delete();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				addActionMessage("升级sql文件不正确...");
			}
		}
		return SUCCESS;
	}

	/**
	 * 数据备份
	 */
	public void dataBak() {
		HttpServletResponse response = super.getResponse();
		if (!OSUtil.getOSName().toLowerCase().contains("linux")) {
			try {
				response.getWriter().write("数据备份功能必须部署在liunx系统上");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				Thread.sleep(100);
				Process process = Runtime.getRuntime().exec("/root/fort.sh");
				response.getWriter().write("备份数据库成功...");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 下载数据文件辅助方法
	 * 
	 * @return
	 */
	public InputStream getTargetFile() {
		try {
			File file = new File("/root/" + filename);
			InputStream in = new FileInputStream(file);

			//System.out.println("leave method getTargetFile()");
			return in;
		} catch (FileNotFoundException e) {
			//System.out.println("[FileNotFoundException" + e);
		}
		//System.out.println("leave method getTargetFile()");
		return null;
	}

	/**
	 * 下载数据文件
	 * 
	 * @return
	 */
	public String download() {
		filename = "fortbak.sql";
		File file = new File("/root/" + filename);
		if (!file.isFile()) {
			filename = "";
			super.addActionMessage("没有任何数据导出...");
			return "error";
		}
		return SUCCESS;
	}

	public File getUpSql() {
		return upSql;
	}

	public void setUpSql(File upSql) {
		this.upSql = upSql;
	}

	public String getUpSqlFileName() {
		return upSqlFileName;
	}

	public void setUpSqlFileName(String upSqlFileName) {
		this.upSqlFileName = upSqlFileName;
	}

}