package com.compliance.webapp.filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.compliance.model.about.Serial;
import com.util.DiskUtils;
import com.util.EncodeUtil;
import com.util.Encrypt;
import com.util.StringUtil;

public class LicenseFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hRequest = (HttpServletRequest) request;

		String url = hRequest.getRequestURI();

		// 提交注册码页面直接通过
		if (url.contains("/serial/initSerial.action") || url.contains("/serial/initRegister.action")) {
			chain.doFilter(request, response);
			return;
		} else {
			
			String script = "";
			
			if(resolve(hRequest)){
				if (!"compliance".equals(Serial.SERIAL_NAME)) {
					script = "<script language=javascript>top.location.href='/compliance/serial/initSerial.action' </script>";
				}
				
				// licence判断
				if (!Encrypt.md5AndSha(DiskUtils.getSerialNumber("C")).equals(Encrypt.sha(Serial.SERIAL_SIGN))) {
					script = "<script language=javascript>top.location.href='/compliance/serial/initSerial.action' </script>";
				}
				
				if (new Date().getTime() / 1000 - Serial.SERIAL_GEN_TIME > (Serial.SERIAL_AUTH_DAY * 24 * 60 * 60)) {
					script = "<script language=javascript>top.location.href='/compliance/serial/initSerial.action' </script>";
				}
			}else{
				script = "<script language=javascript>top.location.href='/compliance/serial/initSerial.action' </script>";
			}

			if (StringUtil.isNotBlank(script)) {
				try {
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().print(script);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			} else {
				chain.doFilter(request, response);
				return;
			}
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	// 解析XML
	public Boolean resolve(HttpServletRequest hRequest) {
		Boolean fileIsExist = false;
		try {
			String path = hRequest.getSession().getServletContext().getRealPath("/");
			File file = new File(path + "compliance.licence");
			if (file.exists()) {
				fileIsExist = true;
				FileInputStream in = null;

				in = new FileInputStream(file);
				byte[] buf = new byte[4096];
				int len = in.read(buf, 0, 4096);
				in.close();

				byte[] buffer = new byte[len];
				for (int i = 0; i < len; i++) {
					buffer[i] = buf[i];
				}

				char[] key1 = { 0x3F, 0xEE, 0x3F, 0x5A, 0xAE, 0xFA, 0x1F, 0x0A };
				char[] key2 = { 0x3D, 0xAE, 0x3A, 0x5B, 0x3F, 0x6A, 0x11, 0xAA };
				char[] key3 = { 0x0B, 0x9E, 0xDF, 0x2A, 0xAA, 0xF0, 0x7D, 0x6E };

				buffer = EncodeUtil.xorDecode(buffer, key3);

				buffer = EncodeUtil.xorDecode(buffer, key2);

				buffer = EncodeUtil.xorDecode(buffer, key1);

				// 解析XML
				Document document = DocumentHelper.parseText(new String(buffer));
				Element root = document.getRootElement();
				if (root.getName().equals("reginfo")) {
					List<Element> elementsList = root.elements();
					Iterator<Element> iterator = elementsList.iterator();
					while (iterator.hasNext()) {
						Element element = (Element) iterator.next();
						if (element.getName().equals("name")) {
							Serial.SERIAL_NAME = element.getText();
						} else if (element.getName().equals("versions")) {
							Serial.SERIAL_VERSIONS = element.getText();
						} else if (element.getName().equals("auth_day")) {
							Serial.SERIAL_AUTH_DAY = Integer.parseInt(element.getText());
						} else if (element.getName().equals("gen_time")) {
							Serial.SERIAL_GEN_TIME = Long.parseLong(element.getText());
						} else if (element.getName().equals("resource_num")) {
							Serial.SERIAL_RESOURCE_NUM = Integer.parseInt(element.getText());
						} else if (element.getName().equals("sign")) {
							Serial.SERIAL_SIGN = element.getText();
						}
					}
				}
			}
		} catch (Exception e) {
			fileIsExist = false;
			e.printStackTrace();
			
		}
		return fileIsExist;
	}
}
