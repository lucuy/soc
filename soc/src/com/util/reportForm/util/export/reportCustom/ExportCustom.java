package com.util.reportForm.util.export.reportCustom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.soc.model.conf.GlobalConfig;
import com.util.reportForm.datadeal.BaseDao;
import com.util.reportForm.datadeal.ExtendedActionServlet;
import com.util.reportForm.datadeal.model.ReportCustom;
import com.util.reportForm.model.ReportCustomFormModel;
import com.util.reportForm.util.export.ZipCompressUtil;

/**
 * 	导出模板信息
 * @author zsa
 *
 */
public class ExportCustom {
	public static final String programPath = GlobalConfig.ctx;
	
	// 操作数据的dao
	private BaseDao dao = new BaseDao();

	// 日期格式
	private SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");

	// 压缩工具
	ZipCompressUtil zipUtil = new ZipCompressUtil();

	public ExportCustom() {

	}

	/**
	 * 生成zip文件并导出 生成的路径是：CommonFile\temp
	 */
	@SuppressWarnings("unchecked")
	public void export(String fileName,String zipFileName) {
		////System.out.println("导出组态报表模板。。。。。。");
		List modelList = new ArrayList();

		// 查找所有的组态报表

		List ReportformsList = (List) dao.getNamedQuery("from ReportCustom",
				new HashMap());

		for (Iterator aaIt = ReportformsList.iterator(); aaIt.hasNext();) {
			ReportCustom form = (ReportCustom) aaIt.next();
			// 查找组态报表对应的详细信息
			List infoList = (List) dao.getNamedQuery(
					"from ReportCustom where id="
							+ form.getId(), null);
			ReportCustomFormModel model = new ReportCustomFormModel();
			//model.setFormInfoList(infoList);
			model.setReportCustom(form);
			modelList.add(model);
		}
		// 生成xml文件
		this.generateXmlFile(modelList,fileName);
		this.mkZipFile(zipFileName,fileName);
		////System.out.println("导出组态报表模板成功。。。。。");
	}

	/**
	 * 生成xml文件
	 */
	@SuppressWarnings("unchecked")
	public void generateXmlFile(List modelList,String fileName) {
		if (null == modelList) {
			return;
		}
		String rootName = "records"; // 根节点名称
		File file = this.generateFileAndDirs(fileName);

		// 创建根节点

		Element rootNode = new Element(rootName);
		Document doc = new Document(rootNode);

		this.mkContent(rootNode, modelList);

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
	@SuppressWarnings("unchecked")
	public Element mkContent(Element rootNode, List modelList) {

		// 传入的参数如果为空

		if (null == rootNode || null == modelList) {
			return rootNode;
		}

		String reportFormNodeName = "reportForm"; // 一个模板记录名称

		String formInfoNodeName = "formInfo"; // 一条模板内容信息


		for (Iterator it = modelList.iterator(); it.hasNext();) {
			ReportCustomFormModel infoModel = (ReportCustomFormModel) it
					.next();
			ReportCustom reportCustom = infoModel.getReportCustom();
			List formInfoList = infoModel.getFormInfoList();

			// 生成新节点，放到rootElement下面
			Element reportFormNode = new Element(reportFormNodeName);
			rootNode.addContent(reportFormNode);

			// 写入Reportforms的内容

			mkReportFormContent(reportFormNode, reportCustom);
			/*for (Iterator iterator = formInfoList.iterator(); iterator
					.hasNext();) {
				// 生成新节点，放到reportFormNode下

				Element formInfoNode = new Element(formInfoNodeName);
				reportFormNode.addContent(formInfoNode);

				Reportformsinfo formInfo = (Reportformsinfo) iterator.next();
				mkReportformsinfoContent(formInfoNode, formInfo);
			}*/
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
			ReportCustom reportCustom) {
		// 参数校验
		if (null == parentNode || null == reportCustom) {
			return parentNode;
		}

		// 从Reportforms中导出信息的节点名称
		String reportCustomName = "reportCustomName";
		String memo = "memo";
		String createDate = "createDate";
		String customSql = "customSql";
		String updateDate = "updateDate";

		// 添加reportFormName节点
		Element reportFormNameNode = new Element(reportCustomName);
		reportFormNameNode
				.addContent(null == reportCustom.getName() ? "": reportCustom.getName());
		parentNode.addContent(reportFormNameNode);

		// 添加reportFormDescription节点
		Element reportFormDescriptionNode = new Element(memo);
		reportFormDescriptionNode.addContent(null == reportCustom.getMemo() ? "" : reportCustom.getMemo());
		parentNode.addContent(reportFormDescriptionNode);

		// 添加reportFormSql节点
		Element reportFormSqlNode = new Element(customSql);
		reportFormSqlNode.addContent(null == reportCustom.getCustomSql()? "" : reportCustom.getCustomSql());
		parentNode.addContent(reportFormSqlNode);
		
		// 添加createDate节点
		Element createDateNode = new Element(createDate);
		String dateString = format.format(null == reportCustom.getCreateDate() ? 0 : reportCustom.getCreateDate());
		createDateNode.addContent(dateString);
		parentNode.addContent(createDateNode);
		
		// 添加updateDate节点
		Element updateDateNode = new Element(updateDate);
		String udateString = format.format(null == reportCustom.getUpdateDate() ? 0 : reportCustom.getUpdateDate());
		updateDateNode.addContent(udateString);
		parentNode.addContent(updateDateNode);
		
		return parentNode;
	}

	/**
	 * 生成临时目录和xml文件
	 * 
	 * @return 生成的文件

	 */
	public File generateFileAndDirs(String fileName) {
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
	public void mkZipFile(String zipFileName,String fileName) {
		ZipCompressUtil util = new ZipCompressUtil();
		String zipFileNameAndPath = programPath + zipFileName;
		String fileNameAndPath = programPath + "CommonFile"+ File.separator + "temp" + File.separator + fileName;
		util.compress(fileNameAndPath,zipFileNameAndPath);
	}

}
