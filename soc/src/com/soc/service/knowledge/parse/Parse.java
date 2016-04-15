package com.soc.service.knowledge.parse;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.soc.model.knowledge.Security;

public class Parse {
	public static List<Security> parse(File file){
		List<Security> list=new ArrayList<Security>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		//创建解析器工厂
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		try {
			//得到解析器
			DocumentBuilder db=dbf.newDocumentBuilder();
			//解析xml
			Document document=db.parse(file);
			//得到根节点
			Element root=document.getDocumentElement();
			//得到所有security节点
			NodeList nlist=root.getElementsByTagName("security");
			//遍历security节点
			for (int i = 0; i < nlist.getLength(); i++) {
				Security security=new Security();
				//得到第一个security节点
				Node n=nlist.item(i);
				//得到所有子节点
				NodeList nodelist=n.getChildNodes();
				//遍历所有子节点
				for (int j = 0; j < nodelist.getLength(); j++) {
					if (nodelist.item(j).getNodeName().equals("securityTitle")) {
						security.setSecurityTitle(nodelist.item(j).getTextContent());
					}
					if (nodelist.item(j).getNodeName().equals("publisher")) {
						security.setPublisher(nodelist.item(j).getTextContent());
					}
					if (nodelist.item(j).getNodeName().equals("securityDate")) {
						security.setSecurityDate(sdf.parse(nodelist.item(j).getTextContent()));
					}
					if (nodelist.item(j).getNodeName().equals("source")) {
						security.setSource(nodelist.item(j).getTextContent());
					}
					if (nodelist.item(j).getNodeName().equals("securityCreateDate")) {
						security.setSecurityCreateDate(sdf.parse(nodelist.item(j).getTextContent()));
					}
					if (nodelist.item(j).getNodeName().equals("securityDetails")) {
						security.setSecurityDetails(nodelist.item(j).getTextContent());
					}
				}
				list.add(security);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}
}
