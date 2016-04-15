package com.util.reportForm.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import com.util.reportForm.datadeal.BaseDao;
import com.util.reportForm.datadeal.ExtendedActionServlet;
import com.util.reportForm.datadeal.model.Reportforms;
import com.util.reportForm.datadeal.model.Reportformsinfo;
import com.util.reportForm.model.ReportFormAndFormInfoModel;
import com.util.reportForm.util.export.ZipCompressUtil;

/**
 * 导入导出模板信息
 */
public class ExportService {
	// 生成的文件名
	public static final String fileName = "reportForm.xml";

	// 生成的压缩文件名
	public static final String zipFileName = "reportForm.zip";

	// 项目名称及路径
	public static final String programPath = ExtendedActionServlet.setupPath;
	//public static final String programPath = "";
	// private final String programPath =
	// "F:\\JD-ESMS\\Server\\Tomcat6\\webapps\\esms\\";

	// zip文件路径名及问价名
	public static final String zipFileNameAndPath = programPath + zipFileName;

	public static final String fileNameAndPath = programPath + "CommonFile"
			+ File.separator + "temp" + File.separator + fileName;

	// 操作数据的dao
	private BaseDao dao = new BaseDao();

	// 日期格式
	private SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");

	// 压缩工具
	ZipCompressUtil zipUtil = new ZipCompressUtil();

	public ExportService() {

	}

	/**
	 * 生成zip文件并导出 生成的路径是：CommonFile\temp
	 */
	public void export() {
		////System.out.println("导出组态报表模板。。。。。。");
		List modelList = new ArrayList();

		// 查找所有的组态报表
		List ReportformsList = (List) dao.getNamedQuery("from Reportforms",
				new HashMap());
		int i = 0;

		for (Iterator aaIt = ReportformsList.iterator(); aaIt.hasNext();) {
			Reportforms form = (Reportforms) aaIt.next();
			// 查找组态报表对应的详细信息
			List infoList = (List) dao.getNamedQuery(
					"from Reportformsinfo where reportFormId="
							+ form.getReportFormId(), null);
			ReportFormAndFormInfoModel model = new ReportFormAndFormInfoModel();
			model.setFormInfoList(infoList);
			model.setReportform(form);
			modelList.add(model);
		}
		// 生成xml文件
		this.generateXmlFile(modelList);
		this.mkZipFile();
		////System.out.println("导出组态报表模板成功。。。。。");
	}

	/**
	 * 生成xml文件
	 */
	public void generateXmlFile(List formAndInfoModelList) {
		if (null == formAndInfoModelList) {
			return;
		}
		String rootName = "records"; // 根节点名称
		String reportForm = "reportForm"; // 一个模板记录名称
		String formInfo = "formInfo"; // 一条模板内容信息

		// 获得输出文件及构建目录路径
		File file = this.generateFileAndDirs();

		SAXBuilder saxBuilder = new SAXBuilder();

		// 创建根节点
		Element rootNode = new Element(rootName);
		Document doc = new Document(rootNode);

		this.mkContent(rootNode, formAndInfoModelList);

		// 生成XMl文件路径
		String filePath = programPath + "CommonFile" + File.separator + "temp";
		// 写出xml文件
		XMLOutputter xmlOut = new XMLOutputter("  ", true);
		FileOutputStream writer = null;
		try {
			writer = new FileOutputStream(filePath + File.separator + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (null != writer) {
			try {
				xmlOut.output(doc, writer);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 根据文件生成
	 * 
	 * @param doc
	 * @return
	 */
	public Element mkContent(Element rootNode, List formAndInfoModelList) {

		// 传入的参数如果为空
		if (null == rootNode || null == formAndInfoModelList) {
			return rootNode;
		}

		String reportFormNodeName = "reportForm"; // 一个模板记录名称
		String formInfoNodeName = "formInfo"; // 一条模板内容信息

		for (Iterator it = formAndInfoModelList.iterator(); it.hasNext();) {
			ReportFormAndFormInfoModel infoModel = (ReportFormAndFormInfoModel) it
					.next();
			Reportforms reportform = infoModel.getReportform();
			List formInfoList = infoModel.getFormInfoList();

			// //System.out.println("formInfoList size -- " +
			// formInfoList.size());

			// 生成新节点，放到rootElement下面
			Element reportFormNode = new Element(reportFormNodeName);
			rootNode.addContent(reportFormNode);

			// 写入Reportforms的内容
			mkReportFormContent(reportFormNode, reportform);
			for (Iterator iterator = formInfoList.iterator(); iterator
					.hasNext();) {
				// 生成新节点，放到reportFormNode下
				Element formInfoNode = new Element(formInfoNodeName);
				reportFormNode.addContent(formInfoNode);

				Reportformsinfo formInfo = (Reportformsinfo) iterator.next();
				mkReportformsinfoContent(formInfoNode, formInfo);
			}
		}

		return rootNode;
	}

	/**
	 * 生成 Reportforms 的节点信息
	 * 
	 * @param parentNode
	 *            父节点
	 * @param reportForm
	 * @return 父节点
	 */
	public Element mkReportFormContent(Element parentNode,
			Reportforms reportForm) {
		// 参数校验
		if (null == parentNode || null == reportForm) {
			return parentNode;
		}

		// 从Reportforms中导出信息的节点名称
		String reportFormName = "reportFormName";
		String reportFormDescription = "reportFormDescription";
		String createDate = "createDate";
		String reportFormSort = "reportFormSort";
		String reportFormType = "reportFormType";
		String tables = "tables";
		String selTerm = "selTerm";
		String reportFormSql = "reportFormSql";
		String coordx = "coordx";
	    String coordy = "coordy";
	    String groupby = "groupby";
	    String orderby = "orderby";

		// 添加reportFormName节点
		Element reportFormNameNode = new Element(reportFormName);
		reportFormNameNode
				.addContent(null == reportForm.getReportFormName() ? ""
						: reportForm.getReportFormName());
		parentNode.addContent(reportFormNameNode);

		// 添加reportFormDescription节点
		Element reportFormDescriptionNode = new Element(reportFormDescription);
		reportFormDescriptionNode.addContent(null == reportForm
				.getReportFormDescription() ? "" : reportForm
				.getReportFormDescription());
		parentNode.addContent(reportFormDescriptionNode);

		// 添加createDate节点
		Element createDateNode = new Element(createDate);
		String dateString = format.format(new Date(null == reportForm
				.getCreateDate() ? 0 : reportForm.getCreateDate()));
		createDateNode.addContent(dateString);
		parentNode.addContent(createDateNode);

		// 添加reportFormSort节点
		Element reportFormSortNode = new Element(reportFormSort);
		String sortStr = (null == reportForm.getReportFormSort() ? 0
				: reportForm.getReportFormSort())
				+ "";
		reportFormSortNode.addContent(sortStr);
		parentNode.addContent(reportFormSortNode);

		// 添加reportFormType节点
		Element reportFormTypeNode = new Element(reportFormType);
		reportFormTypeNode
				.addContent(null == reportForm.getReportFormType() ? ""
						: reportForm.getReportFormType() + "");
		parentNode.addContent(reportFormTypeNode);

		// 添加tables节点
		Element tablesNode = new Element(tables);
		tablesNode.addContent(null == reportForm.getTables() ? "" : reportForm
				.getTables());
		parentNode.addContent(tablesNode);

		// 添加selTerm节点
		Element selTermNode = new Element(selTerm);
		selTermNode.addContent(null == reportForm.getSelTerm() ? ""
				: reportForm.getSelTerm());
		parentNode.addContent(selTermNode);

		// 添加reportFormSql节点
		Element reportFormSqlNode = new Element(reportFormSql);
		reportFormSqlNode.addContent(null == reportForm.getReportFormSql() ? ""
				: reportForm.getReportFormSql());
		parentNode.addContent(reportFormSqlNode);
		
		// 添加coordx节点
		Element coordxNode = new Element(coordx);
		coordxNode.addContent(null == reportForm.getCoordx() ? ""
				: reportForm.getCoordx());
		parentNode.addContent(coordxNode);
		
		// 添加 coordy 节点
		Element coordyNode = new Element(coordy);
		coordyNode.addContent(null == reportForm.getCoordy() ? ""
				: reportForm.getCoordy());
		parentNode.addContent(coordyNode);
		
		// 添加 groupby 节点
		Element groupbyNode = new Element(groupby);
		groupbyNode.addContent(null == reportForm.getGroupby() ? ""
				: reportForm.getGroupby());
		parentNode.addContent(groupbyNode);
		
		// 添加orderby节点
		Element orderbyNode = new Element(orderby);
		orderbyNode.addContent(null == reportForm.getOrderby() ? ""
				: reportForm.getOrderby());
		parentNode.addContent(orderbyNode);

		return parentNode;
	}

	/**
	 * 生成 Reportformsinfo 的节点信息
	 * 
	 * @param parentNode
	 *            父节点
	 * @param formInfo
	 * @return 父节点
	 */
	public Element mkReportformsinfoContent(Element parentNode,
			Reportformsinfo formInfo) {
		if (null == parentNode || null == formInfo) {
			return parentNode;
		}

		String colName = "colName";
		String colWidth = "colWidth";
		String alignType = "alignType";
		String correspondingTable = "correspondingTable";
		String correspondingField = "correspondingField";
		String exportType = "exportType";

		// 生成colName节点
		Element colNameNode = new Element(colName);
		colNameNode.addContent(null == formInfo.getColName() ? "" : formInfo
				.getColName());
		parentNode.addContent(colNameNode);

		// 生成colWidth节点
		Element colWidthNode = new Element(colWidth);
		colWidthNode.addContent(null == formInfo.getColWidth() ? "" : formInfo
				.getColWidth());
		parentNode.addContent(colWidthNode);

		// 生成alignType节点
		Element alignTypeNode = new Element(alignType);
		alignTypeNode.addContent(null == formInfo.getAlignType() ? ""
				: formInfo.getAlignType() + "");
		parentNode.addContent(alignTypeNode);

		// 生成correspondingTable节点
		Element correspondingTableNode = new Element(correspondingTable);
		correspondingTableNode.addContent(null == formInfo
				.getCorrespondingTable() ? "" : formInfo
				.getCorrespondingTable()
				+ "");
		parentNode.addContent(correspondingTableNode);

		// 生成correspondingField节点
		Element correspondingFieldNode = new Element(correspondingField);
		correspondingFieldNode.addContent(null == formInfo
				.getCorrespondingField() ? "" : formInfo
				.getCorrespondingField()
				+ "");
		parentNode.addContent(correspondingFieldNode);

		// 生成exportType节点
		Element exportTypeNode = new Element(exportType);
		exportTypeNode.addContent(null == formInfo.getExportType() ? ""
				: formInfo.getExportType());
		parentNode.addContent(exportTypeNode);

		return parentNode;
	}

	/**
	 * 生成临时目录和xml文件
	 * 
	 * @return 生成的文件
	 */
	public File generateFileAndDirs() {
		// 文件目录
		String filePath = programPath + "CommonFile" + File.separator + "temp";
		File file = new File(filePath);

		// 生成目录CommonFile\temp
		if (!file.exists() || !file.isDirectory()) {
			file.mkdirs();
		}
		file = new File(filePath + File.separator + fileName);

		// 生成文件CommonFile\temp\reportForm.xml
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				//System.out.println("生成文件出错：" + e.getMessage());
			}
		}
		return file;
	}

	/**
	 * 生成zip格式的压缩文件
	 */
	public void mkZipFile() {
		ZipCompressUtil util = new ZipCompressUtil();
		util.compress(this.fileNameAndPath, this.zipFileNameAndPath);
	}

	public static void main(String[] args) {
		BaseDao dao = new BaseDao();
		List modelList = new ArrayList();
		List aa = (List) dao.getNamedQuery("from Reportforms", null);

		for (Iterator aaIt = aa.iterator(); aaIt.hasNext();) {
			Reportforms form = (Reportforms) aaIt.next();
			List infoList = (List) dao.getNamedQuery(
					"from Reportformsinfo where reportFormId="
							+ form.getReportFormId(), null);
//			//System.out.println(form.getReportFormId() + "--" + infoList.size());
//			//System.out.println("------------"
//					+ ((Reportformsinfo) infoList.get(0)).getColName()
//					+ "--"
//					+ ((Reportformsinfo) infoList.get(0)).getColWidth()
//					+ "--"
//					+ ((Reportformsinfo) infoList.get(0)).getExportType()
//					+ "--"
//					+ ((Reportformsinfo) infoList.get(0)).getAlignType()
//					+ "--"
//					+ ((Reportformsinfo) infoList.get(0))
//							.getCorrespondingField()
//					+ "--"
//					+ ((Reportformsinfo) infoList.get(0))
//							.getCorrespondingTable() + "--");
			ReportFormAndFormInfoModel model = new ReportFormAndFormInfoModel();
			model.setFormInfoList(infoList);
			model.setReportform(form);
			modelList.add(model);
		}
		ExportService service = new ExportService();
		service.generateXmlFile(modelList);
	}
}
