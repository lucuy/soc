package com.soc.webapp.action.addrules;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.struts2.ServletActionContext;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.soc.model.knowledge.Security;
import com.soc.model.systemsetting.rules.AnalysisRules;
import com.soc.model.systemsetting.rules.Device_category;
import com.soc.model.systemsetting.rules.QueryEvents_group;
import com.soc.model.systemsetting.rules.RulesVO;
import com.soc.model.user.User;
import com.soc.service.addrules.AddRulesSerive;
import com.soc.service.audit.AuditService;
import com.soc.service.knowledge.parse.Parse;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.FileUtil;

public class AddRulesAction extends BaseAction{
	//上传的文件
	private File rules;
	//上传的文件名
	private String rulesFileName;
	
	private AddRulesSerive addRulesManager;
	
	//传给页面的参数
	private String msg;
	
	private AuditService auditManager;
	/**
	 * <解析规则的导入> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String importRules() {

		log.info("[SecurityBulletinAction] Enter method importSecurity.....");
		log.info(rules);
		if (rules != null && rulesFileName != null) {
			String path = ServletActionContext.getServletContext().getRealPath(
					"/import");

			File importXML = new File(new File(path), rulesFileName);

			try {
				FileUtil.copyFile(rules, importXML);
			} catch (IOException e) {
				e.printStackTrace();
			}

			List<RulesVO> list = new ArrayList<RulesVO>();

			list =parse(importXML);
			Map<String, String> map=new HashMap<String, String>();
			
			
			
			for (int i = 0; i < list.size(); i++) {
				map.put("keyword", list.get(i).getAnalysisRules().getAnalysisName());
				List<String> checkNameList = addRulesManager.selectALLAnalysisName(map);
				List<String> fieldList = new ArrayList<String>();
				fieldList.add(list.get(i).getAnalysisRules().getAnalysisName() + "(" + 
						list.get(i).getAnalysisRules().getAnalysisName() + ")");
				String logString = "";
				if (checkNameList == null || checkNameList.size() == 0) {
					// 3个表各插入一条信息
					addRulesManager.insertAnalysisRules(list.get(i).getAnalysisRules());
					addRulesManager.insertDevice_category(list.get(i).getDevice_category());
					addRulesManager.insertQueryEvents_group(list.get(i).getQueryevents_group());
					msg="增加成功！";
					
					//加入数据库审计
					
					auditManager.insertByInsertOperator(((User) this.getSession()
							.getAttribute("SOC_LOGON_USER")).getUserId(), "解析规则", super
							.getRequest().getRemoteAddr(), fieldList);

					String logString1 = "";
					logString1 = "登录名："
							+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
									.getUserLoginName() + "  源IP:"
							+ getRequest().getRemoteAddr() + "   操作时间："
							+ DateUtil.curDateTimeStr19() + "   操作类型:添加解析规则";
				}else{
					msg="解析规则有重复项，请检查！";
				}
			}

		}
		return SUCCESS;
	}
	
	
	
	public List<RulesVO> parse(File file){
		List<RulesVO> list=new ArrayList<RulesVO>();
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
			//得到所有data节点
			NodeList nlist=root.getElementsByTagName("data");
			//遍历date节点
			for (int i = 0; i < nlist.getLength(); i++) {
				RulesVO rulesvo=new RulesVO();
				AnalysisRules analysisRules=new AnalysisRules();
				Device_category devCategory=new Device_category();
				QueryEvents_group queryeGroup=new QueryEvents_group();
				//得到第i个data节点
				Node n=nlist.item(i);
				//得到所有子节点
				NodeList nodelist=n.getChildNodes();
				//遍历所有子节点
				for (int j = 0; j < nodelist.getLength(); j++) {
					
					//为AnalysisRules类赋值
					if (nodelist.item(j).getNodeName().equals("tbl_analysis_ANALYSIS_ID")&&
							nodelist.item(j).getTextContent()!=null&&!nodelist.item(j).getTextContent().equals("")) {
						analysisRules.setAnalysisId(nodelist.item(j).getTextContent());
					}
					if (nodelist.item(j).getNodeName().equals("tbl_analysis_ANALYSIS_NAME")&&
							nodelist.item(j).getTextContent()!=null&&!nodelist.item(j).getTextContent().equals("")) {
						analysisRules.setAnalysisName(nodelist.item(j).getTextContent());
					}
					if (nodelist.item(j).getNodeName().equals("tbl_analysis_ANALYSIS_CONDITION")&&
							nodelist.item(j).getTextContent()!=null&&!nodelist.item(j).getTextContent().equals("")) {
						analysisRules.setAnalysisCreator(nodelist.item(j).getTextContent());
					}
					if (nodelist.item(j).getNodeName().equals("tbl_analysis_ANALYSIS_DESCRIPTION")&&
							nodelist.item(j).getTextContent()!=null&&!nodelist.item(j).getTextContent().equals("")) {
						analysisRules.setAnalysisDescription(nodelist.item(j).getTextContent());
					}
					if (nodelist.item(j).getNodeName().equals("tbl_analysis_ANALYSIS_CREATE_TIME")&&
							nodelist.item(j).getTextContent()!=null&&!nodelist.item(j).getTextContent().equals("")) {
						analysisRules.setAnalysisCreateTime(sdf.parse(nodelist.item(j).getTextContent()));
					}
					if (nodelist.item(j).getNodeName().equals("tbl_analysis_ANALYSIS_CREATOR")&&
							nodelist.item(j).getTextContent()!=null&&!nodelist.item(j).getTextContent().equals("")) {
						analysisRules.setAnalysisCreator(nodelist.item(j).getTextContent());
					}
					if (nodelist.item(j).getNodeName().equals("tbl_analysis_ANALYSIS_UPDATE_TIME")&&
							nodelist.item(j).getTextContent()!=null&&!nodelist.item(j).getTextContent().equals("")) {
						analysisRules.setAnalysisUpdateTime(sdf.parse(nodelist.item(j).getTextContent()));
					}
					if (nodelist.item(j).getNodeName().equals("tbl_analysis_ANALYSIS_TYPE")&&
							nodelist.item(j).getTextContent()!=null&&!nodelist.item(j).getTextContent().equals("")) {
						analysisRules.setAnalysisType(Integer.parseInt(nodelist.item(j).getTextContent()));
					}
					
					//为Device_category类赋值
					if (nodelist.item(j).getNodeName().equals("tbl_device_category_DEVICE_CATEGORY_ID")&&
							nodelist.item(j).getTextContent()!=null&&!nodelist.item(j).getTextContent().equals("")) {
						devCategory.setID(Integer.parseInt(nodelist.item(j).getTextContent()));
					}
					if (nodelist.item(j).getNodeName().equals("tbl_device_category_DEVICE_CATEGORY_NAME")&&
							nodelist.item(j).getTextContent()!=null&&!nodelist.item(j).getTextContent().equals("")) {
						devCategory.setName(nodelist.item(j).getTextContent());
					}
					if (nodelist.item(j).getNodeName().equals("tbl_device_category_HIGHER_UPS_ID")&&
							nodelist.item(j).getTextContent()!=null&&!nodelist.item(j).getTextContent().equals("")) {
						devCategory.setHigherUpsId(Integer.parseInt(nodelist.item(j).getTextContent()));
					}
					if (nodelist.item(j).getNodeName().equals("tbl_device_category_EVENTS_DEVNAME")&&
							nodelist.item(j).getTextContent()!=null&&!nodelist.item(j).getTextContent().equals("")) {
						devCategory.setDevname(nodelist.item(j).getTextContent());
					}
					if (nodelist.item(j).getNodeName().equals("tbl_device_category_EVENTS_PROGRAMTYPE")&&
							nodelist.item(j).getTextContent()!=null&&!nodelist.item(j).getTextContent().equals("")) {
						devCategory.setProgramtype(nodelist.item(j).getTextContent());
					}
					if (nodelist.item(j).getNodeName().equals("tbl_device_category_EVENTS_CUSTOMD1")&&
							nodelist.item(j).getTextContent()!=null&&!nodelist.item(j).getTextContent().equals("")) {
						devCategory.setCustomd1(Integer.parseInt(nodelist.item(j).getTextContent()));
					}
					if (nodelist.item(j).getNodeName().equals("tbl_device_category_EVENTS_RAWID")&&
							nodelist.item(j).getTextContent()!=null&&!nodelist.item(j).getTextContent().equals("")) {
						devCategory.setRawid(Integer.parseInt(nodelist.item(j).getTextContent()));
					}
					if (nodelist.item(j).getNodeName().equals("tbl_device_category_DEVICE_CATEGORY_CODE")&&
							nodelist.item(j).getTextContent()!=null&&!nodelist.item(j).getTextContent().equals("")) {
						devCategory.setCode(nodelist.item(j).getTextContent());
					}
					
					
					//为QueryEvents_group类赋值
					if (nodelist.item(j).getNodeName().equals("tbl_queryEvents_group_QUERY_EVENTS_GROUP_ID")&&
							nodelist.item(j).getTextContent()!=null&&!nodelist.item(j).getTextContent().equals("")) {
						queryeGroup.setId(Integer.parseInt(nodelist.item(j).getTextContent()));
					}
					if (nodelist.item(j).getNodeName().equals("tbl_queryEvents_group_QUERY_EVENTS_GROUP_NAME")&&
							nodelist.item(j).getTextContent()!=null&&!nodelist.item(j).getTextContent().equals("")) {
						queryeGroup.setName(nodelist.item(j).getTextContent());
					}
					if (nodelist.item(j).getNodeName().equals("tbl_queryEvents_group_QUERY_EVENTS_GROUP_PARENT_ID")&&
							nodelist.item(j).getTextContent()!=null&&!nodelist.item(j).getTextContent().equals("")) {
						queryeGroup.setParent_id(Integer.parseInt(nodelist.item(j).getTextContent()));
					}
					if (nodelist.item(j).getNodeName().equals("tbl_queryEvents_group_QUERY_EVENTS_GROUP_TYPE")&&
							nodelist.item(j).getTextContent()!=null&&!nodelist.item(j).getTextContent().equals("")) {
						queryeGroup.setType(Integer.parseInt(nodelist.item(j).getTextContent()));
					}
					if (nodelist.item(j).getNodeName().equals("tbl_queryEvents_group_QUERY_EVENTS_CONDITIONS")&&
							nodelist.item(j).getTextContent()!=null&&!nodelist.item(j).getTextContent().equals("")) {
						queryeGroup.setConditions(nodelist.item(j).getTextContent());
					}
					
				}
				//把3个类赋值到VO类里面
				rulesvo.setAnalysisRules(analysisRules);
				rulesvo.setDevice_category(devCategory);
				rulesvo.setQueryevents_group(queryeGroup);
				list.add(rulesvo);
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
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}



	public File getRules() {
		return rules;
	}



	public void setRules(File rules) {
		this.rules = rules;
	}



	public String getRulesFileName() {
		return rulesFileName;
	}



	public void setRulesFileName(String rulesFileName) {
		this.rulesFileName = rulesFileName;
	}



	public AddRulesSerive getAddRulesManager() {
		return addRulesManager;
	}



	public void setAddRulesManager(AddRulesSerive addRulesManager) {
		this.addRulesManager = addRulesManager;
	}



	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}



	public AuditService getAuditManager() {
		return auditManager;
	}



	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}
	
	
	
	
	
}



