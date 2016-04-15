package com.util.reportForm.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.util.reportForm.datadeal.BaseDao;
import com.util.reportForm.datadeal.model.Reportforms;
import com.util.reportForm.datadeal.model.Reportformsinfo;
import com.util.reportForm.model.ReportFormAndFormInfoModel;

public class InportService {
	private BaseDao dao = new BaseDao();
	/*节点名称*/
	private final String rootName = "records"; // 根节点名称
	private final String reportForm = "reportForm"; // 一个模板记录名称
	private final String formInfo = "formInfo"; // 一条模板内容信息
	
	// Reportforms对应的节点名称
	private final String reportFormName = "reportFormName";
	private final String reportFormDescription = "reportFormDescription";
	private final String createDate = "createDate";
	private final String reportFormSort = "reportFormSort";
	private final String reportFormType = "reportFormType";
	private final String tables = "tables";
	private final String selTerm = "selTerm";
	private final String reportFormSql = "reportFormSql";
    private final String coordx = "coordx";
    private final String coordy = "coordy";
    private final String groupby = "groupby";
    private final String orderby = "orderby";
	
	//Reportformsinfo对应的节点名称
	private final String colName = "colName";
	private final String colWidth = "colWidth";
	private final String alignType = "alignType";
	private final String correspondingTable = "correspondingTable";
	private final String correspondingField = "correspondingField";
	private final String exportType = "exportType";
	public InportService(){
		
	}
	
	/**
	 * 读取xml文件，如果数据库中不存在相同名称的记录，保存该记录
	 * @param filePath 导入的文件名
	 */
	@SuppressWarnings("unchecked")
	public void inport(String filePath){
		
		if(null == filePath && "".equals(filePath.trim())){
			return;
		}
		//System.out.println("filePath--"+filePath);
		File xmlFile = new File(filePath);
		
		//得到ReportFormAndFormInfoModel 组成的List
		List modelList = getModelListFromXml(xmlFile);
		if(null != modelList){
			for(Iterator it = modelList.iterator(); it.hasNext();){
				ReportFormAndFormInfoModel model = (ReportFormAndFormInfoModel)it.next();
				Reportforms forms = model.getReportform();
				//如果模板不存在，插入到数据库中
				if(!isExistForm(forms)){
					saveReportFormAndFormInfo(model);
				}
			}
		}
	}
	
	/**
	 * 从xml文件中获得ReportFormAndFormInfoModel链表
	 * @param xmlFile xml文件
	 * @return ReportFormAndFormInfoModel链表
	 */
	@SuppressWarnings("unchecked")
	public List getModelListFromXml(File xmlFile){
		if(null == xmlFile || !xmlFile.exists()){
			return null;
		}
		
		List modelList = new ArrayList();
		
		SAXBuilder builder = new SAXBuilder();
		FileInputStream fis = null;
		Document doc  = null;
		try {
			fis = new FileInputStream(xmlFile);
		} catch (FileNotFoundException e) {
			//System.out.println("读取"+xmlFile.getAbsolutePath()+"文件出错"+e.getMessage());
		}
		try {
			doc = builder.build(fis);
		} catch (JDOMException e) {
			//System.out.println("解析XML文件出错"+e.getMessage());
			return null;
		}
		Element rootNode = doc.getRootElement();
		List reportFormNodeList = rootNode.getChildren(this.reportForm);
		if(null != reportFormNodeList && reportFormNodeList.size() > 0){
			for(Iterator it = reportFormNodeList.iterator(); it.hasNext();){
				Reportforms reportForm = new Reportforms();
				List formInfoList = new ArrayList();
				Element reportFormNode = (Element)it.next();
				//获得Reportforms ”reportFormName“节点的值
				Element reportFormNameNode = reportFormNode.getChild(this.reportFormName);
				String reportFormNameStr = reportFormNameNode.getTextTrim();
				reportForm.setReportFormName(null == reportFormNameStr ? "" : reportFormNameStr);
				
				//获得Reportforms ”reportFormDescription“节点的值
				Element reportFormDescriptionNode = reportFormNode.getChild(this.reportFormDescription);
				String reportFormDescriptionStr = reportFormDescriptionNode.getTextTrim();
				reportForm.setReportFormDescription(null == reportFormDescriptionStr ? "" :reportFormDescriptionStr);
				
				//获得Reportforms ”reportFormSort“节点的值
				Element reportFormSortNode = reportFormNode.getChild(this.reportFormSort);
				String reportFormSortStr = reportFormSortNode.getTextTrim();
				reportForm.setReportFormSort(null == reportFormSortStr ? 0 :Integer.parseInt(reportFormSortStr));
				
				//获得Reportforms ”reportFormType“节点的值
				Element reportFormTypeNode = reportFormNode.getChild(this.reportFormType);
				String reportFormTypeStr = reportFormTypeNode.getTextTrim();
				reportForm.setReportFormType(null == reportFormTypeStr ? 0 : Integer.parseInt(reportFormTypeStr));
				
				//获得Reportforms ”tables“节点的值
				Element tablesNode = reportFormNode.getChild(this.tables);
				String tablesStr = tablesNode.getTextTrim();
				reportForm.setTables(null == tablesStr ? "" : tablesStr);
				
				//获得Reportforms ”selTerm“节点的值
				Element selTermNode = reportFormNode.getChild(this.selTerm);
				String selTermStr = selTermNode.getTextTrim();
				reportForm.setSelTerm(null == selTermStr ? "" : selTermStr);
				
				//获得Reportforms ”reportFormSql“节点的值
				Element reportFormSqlNode = reportFormNode.getChild(this.reportFormSql);
				String reportFormSqlStr = reportFormSqlNode.getTextTrim();
				reportForm.setReportFormSql(null == reportFormSqlStr ? "" : reportFormSqlStr);
				
				//获得Reportforms ”coordx“节点的值

				Element coordxNode = reportFormNode.getChild(this.coordx);
				String coordxStr = coordxNode.getTextTrim();
				reportForm.setCoordx(null == coordxStr ? "" : coordxStr);
				
				//获得Reportforms ”coordy“节点的值

				Element coordyNode = reportFormNode.getChild(this.coordy);
				String coordyStr = coordyNode.getTextTrim();
				reportForm.setCoordy(null == coordyStr ? "" : coordyStr);
				
				//获得Reportforms ”groupby“节点的值

				Element groupbyNode = reportFormNode.getChild(this.groupby);
				String groupbyStr = groupbyNode.getTextTrim();
				reportForm.setGroupby(null == groupbyStr ? "" : groupbyStr);
				
				//获得Reportforms ”orderby“节点的值

				Element orderbyNode = reportFormNode.getChild(this.orderby);
				String orderbyStr = orderbyNode.getTextTrim();
				reportForm.setOrderby(null == orderbyStr ? "" : orderbyStr);
				
				List formInfoNodeList = reportFormNode.getChildren(this.formInfo);
				
				if(null != formInfoNodeList && formInfoNodeList.size() > 0){
					for(Iterator infoIt = formInfoNodeList.iterator(); infoIt.hasNext();){
						Reportformsinfo formInfo = new Reportformsinfo();
						Element formInfoNode = (Element)infoIt.next();
						//获得 Reportformsinfo "colName"节点的值
						Element colNameNode = formInfoNode.getChild(this.colName);
						String colNameStr = colNameNode.getTextTrim();
						formInfo.setColName(null == colNameStr ? "" : colNameStr);
						
						//获得 Reportformsinfo "colWidth"节点的值
						Element colWidthNode = formInfoNode.getChild(this.colWidth);
						String colWidthStr = colWidthNode.getTextTrim();
						formInfo.setColWidth(null == colWidthStr ? "" : colWidthStr);
						
						//获得 Reportformsinfo "alignType"节点的值
						Element alignTypeNode = formInfoNode.getChild(this.alignType);
						String calignTypeStr = alignTypeNode.getTextTrim();
						formInfo.setAlignType(null == calignTypeStr ? 0 : Integer.parseInt(calignTypeStr));
						
						//获得 Reportformsinfo "correspondingTable"节点的值
						Element correspondingTableNode = formInfoNode.getChild(this.correspondingTable);
						String correspondingTableStr = correspondingTableNode.getTextTrim();
						formInfo.setCorrespondingTable(null == correspondingTableStr ? 0 : Integer.parseInt(correspondingTableStr));
					
						//获得 Reportformsinfo "correspondingField"节点的值
						Element correspondingFieldNode = formInfoNode.getChild(this.correspondingField);
						String correspondingFieldStr = correspondingFieldNode.getTextTrim();
						formInfo.setCorrespondingField(null == correspondingFieldStr ? 0 : Integer.parseInt(correspondingFieldStr));
						
						//获得 Reportformsinfo "exportType"节点的值
						Element exportTypeNode = formInfoNode.getChild(this.exportType);
						String exportTypeStr = exportTypeNode.getTextTrim();
						formInfo.setExportType(null == exportTypeStr ? "" : exportTypeStr);
						
						//放入List中
						formInfoList.add(formInfo);
						
						//构造ReportFormAndFormInfoModel
						ReportFormAndFormInfoModel m = new ReportFormAndFormInfoModel();
						m.setFormInfoList(formInfoList);
						m.setReportform(reportForm);
						
						//放入结果返回
						modelList.add(m);
					}
				}
			}
		}
		return modelList;
	}
	
	/**
	 * 查看是否存在名字相同的组态报表模板
	 * @param reportForm 组态报表模板
	 * @return  存在返回true 不存在返回false;
	 */
	@SuppressWarnings("unchecked")
	public boolean isExistForm(Reportforms reportForm){
		if(null == reportForm){
			return true;
		}
		String formName = reportForm.getReportFormName();
		if(null != formName && !"".equals(formName)){
			//查找是否存在名字相同的模板
			String hql = "from Reportforms where reportFormName=:formName";
			Map params = new HashMap();
			params.put("formName", formName);
			List resultList = (List)dao.getNamedQuery(hql, params);
			//如果不存在返回false
			if(null == resultList || 0 == resultList.size()){
				return false;
			}
		}else{
			return true;
		}
		//存在返回true
		return true;
	}
	
	/**
	 * 插入模板信息
	 * @param reportFromModel
	 */
	@SuppressWarnings("unchecked")
	public void saveReportFormAndFormInfo(ReportFormAndFormInfoModel reportFromModel){
		//参数校验
		if(null == reportFromModel || null == reportFromModel.getFormInfoList() || null == reportFromModel.getReportform()){
			return;
		}
		List formInfoList = reportFromModel.getFormInfoList();
		Reportforms reportform = reportFromModel.getReportform();
		long dateTimeL = System.currentTimeMillis();
		
		//设置reportFormId
		reportform.setReportFormId(dateTimeL);
		//设置创建时间
		reportform.setCreateDate(dateTimeL);
		//保存
		dao.save(reportform);
		
		for(Iterator it = formInfoList.iterator(); it.hasNext();){
			Reportformsinfo formInfo = (Reportformsinfo)it.next();
			//formInfo.setReportFormId(""+dateTimeL);
			formInfo.setReportFormId(dateTimeL);
			//保存
			dao.save(formInfo);
		}
	}
	
	
}
