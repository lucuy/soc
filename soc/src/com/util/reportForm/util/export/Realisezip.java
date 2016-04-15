package com.util.reportForm.util.export;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.struts.actions.DispatchAction;

import com.util.reportForm.datadeal.BaseDao;

public class Realisezip extends DispatchAction {
	BaseDao dao = new BaseDao();
	InputStream is;
	OutputStream os;
	BufferedInputStream bis;
	BufferedOutputStream bos;
	byte[] data = new byte[1024];
	int i = 0;

	// 解压缩文件


	public void realiseZipFile(String unzipfile, String extFold) {
		int intCount = 0;
		ZipInputStream zin = null;
		try {
			zin = new ZipInputStream(new FileInputStream(unzipfile));
			ZipEntry entry;
			while ((entry = zin.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					File directory = new File(extFold, entry.getName());
					if (!directory.exists() && directory.mkdirs())
						System.exit(0);
					zin.closeEntry();
				} else {
					String strFileName = entry.getName();
					if (strFileName.indexOf("/") != -1) {
						makeDirs(extFold, strFileName.substring(0, strFileName
								.lastIndexOf("/")));
					}
					File myFile = new File(strFileName);
					FileOutputStream fout = new FileOutputStream(extFold
							+ myFile.getPath());
					DataOutputStream dout = new DataOutputStream(fout);
					byte[] b = new byte[1024];
					int len = 0;
					while ((len = zin.read(b)) != -1) {
						dout.write(b, 0, len);
					}
					dout.close();
					fout.close();
					zin.closeEntry();
				}
				intCount++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (zin != null) {
					zin.closeEntry();
					zin.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		log.info("Total: [" + intCount + "] files has been created!");
	}

	private static boolean makeDirs(String strBottomFoldName, String strFoldName) {
		boolean blnResult = true;
		File newFold = new File(strBottomFoldName, strFoldName);
		if (!newFold.exists() && !newFold.mkdirs()) {
			blnResult = false;
		}
		return blnResult;
	}

	// jdbc连接数据库


	public Connection getJDBCConnection() {
		Connection conn = null;
		String Driver = "";
		String url = "";
		String user = "";
		String password = "";

		Driver = "org.gjt.mm.mysql.Driver";
		url = "jdbc:mysql://localhost:3306/fort";
		user = "fort";
		password = "2wsx3edc";
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeResult(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块


			e.printStackTrace();
		}
	}

	public static void closeStatement(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void closePreparedStatement(PreparedStatement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
