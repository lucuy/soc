package com.util.load;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoadWord {
	//附件下载
	public static void AttachmentDownload(HttpServletResponse response,String fileName,String urlfilenamepass){	
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/x-msdownload;charset=UTF-8");		
		//String path = "load/";
		String recordPath =Thread.currentThread().getContextClassLoader().getResource("").getPath()+"reccordReport";
		File file = null;
		try {
			//file = new File(path + fileName);
			//fileName = "年前工作安排.doc";
			file = new File(fileName);
			if (file.isFile() && file.exists()) {
				
				response.addHeader("Content-Disposition","attachment;filename="+java.net.URLEncoder.encode(urlfilenamepass, "UTF-8"));
				
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
	
	
	//附件上传
	public static String AttachmentUpload(File sourceFile){
		InputStream inputStream = null;
		OutputStream outputStream  = null;
		File uploadFile = null;
		String fileName = "";
		if(sourceFile.isFile() && sourceFile.exists()){
			try {
				//基于file创建一个文件输入流		
				inputStream = new FileInputStream(sourceFile);
				//设置上传文件目录
				String uploadPath = "load/";
				File file = new File(uploadPath);
				if(!file.isDirectory()){
					file.mkdirs();
				}
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
				String ss = sdf.format(new Date());
				for(int i=0;i<2;i++){
					ss += String.valueOf((int)(java.lang.Math.random()*10));
				}
				fileName = ss + sourceFile.getName();
				////System.out.println(fileName);
				//设置目标文件
				uploadFile = new File(file,fileName);
				if(!uploadFile.isFile()){
					uploadFile.createNewFile();
				}
				//上传一个输出流
				outputStream = new FileOutputStream(uploadFile);
				//设置缓存
				byte[] buffer = new byte[1024];
				int length = 0;
				
				//读取文件输出上传文件中
				while((length = inputStream.read(buffer))!=-1){
					outputStream.write(buffer, 0, length);
				}
				try {
					inputStream.close();
					outputStream.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		return fileName;
	}
}
