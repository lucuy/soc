package com.util.reportForm.util.export.reportCustom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import com.util.reportForm.datadeal.BaseDao;
import com.util.reportForm.datadeal.model.ReportCustom;
import com.util.reportForm.model.ReportCustomFormModel;

/**
 * 自定义报表导入
 * @author zsa
 *
 */
public class ImportCustom {
	private BaseDao dao = new BaseDao();

	private final String reportForm = "reportForm"; // 一个模板记录名称	private SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");

	private final String reportCustomName = "reportCustomName";
	private final String memo = "memo";
	private final String createDate = "createDate";
	private final String updateDate="updateDate";
	private final String customSql = "customSql";
   
	
	public ImportCustom(){
		
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
		File xmlFile = new File(filePath);
		
		//得到ReportFormAndFormInfoModel 组成的List
		List modelList = getModelListFromXml(xmlFile);
		if(null != modelList){
			for(Iterator it = modelList.iterator(); it.hasNext();){
				ReportCustomFormModel model=(ReportCustomFormModel)it.next();
				ReportCustom forms = model.getReportCustom();
				//如果模板不存在，插入到数据库中
				if(!isExistForm(forms)){
					saveReportForm(model);
				}
			}
		}
	}
	
	/**
	 * 从xml文件中获得ReportCustomFormModel链表
	 * @param xmlFile xml文件
	 * @return ReportCustomFormModel链表
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
				ReportCustom reportCustom = new ReportCustom();
				Element reportCustomNode = (Element)it.next();
				
				//获得”reportCustomName“节点的值
				Element reportCustomNameNode = reportCustomNode.getChild(this.reportCustomName);
				String reportCustomNameStr = reportCustomNameNode.getTextTrim();
				reportCustom.setName(null == reportCustomNameStr ? "" : reportCustomNameStr);
				
				//获得”memo“节点的值
				Element memoNode = reportCustomNode.getChild(this.memo);
				String memo = memoNode.getTextTrim();
				reportCustom.setMemo(null == memo ? "" :memo);
				
				//获得”customSql“节点的值
				Element customSqlNode = reportCustomNode.getChild(this.customSql);
				String customSqlStr = customSqlNode.getTextTrim();
				reportCustom.setCustomSql(null == customSqlStr ? "" : customSqlStr);
				
				try {
					
				//获得”createDate“节点的值

				Element createDateNode = reportCustomNode.getChild(this.createDate);
				String createDateStr = createDateNode.getTextTrim();
				reportCustom.setCreateDate(format.parse(null == createDateStr ? "" : createDateStr));
				
				//获得”createDate“节点的值

				Element updateDateNode = reportCustomNode.getChild(this.updateDate);
				String updateDateStr = updateDateNode.getTextTrim();
				reportCustom.setCreateDate(format.parse(null == updateDateStr ? "" : updateDateStr));
				
				} catch (ParseException e) {
					e.printStackTrace();
				}
				ReportCustomFormModel model=new ReportCustomFormModel();
				model.setReportCustom(reportCustom);
				modelList.add(model);
			}
		}
		return modelList;
	}
	
	/**
	 * 查看是否存在名字相同的自定义组态报表
	 * @param ReportCustom 
	 * @return  存在返回true 不存在返回false;
	 */
	@SuppressWarnings("unchecked")
	public boolean isExistForm(ReportCustom reportCustom){
		if(null == reportForm){
			return true;
		}
		String formName = reportCustom.getName();
		if(null != formName && !"".equals(formName)){
			//查找是否存在名字相同的模板
			String hql = "from ReportCustom where name=:formName";
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
	 * @param ReportCustomFormModel
	 */
	public void saveReportForm(ReportCustomFormModel model){
		//参数校验
		if(null == model || null == model.getReportCustom()){
			return;
		}
		
		ReportCustom reportCustom = model.getReportCustom();
		
		reportCustom.setUpdateDate(new Date());
		
		//保存
		dao.save(reportCustom);
		
	}
	
	
}
