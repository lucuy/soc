package com.compliance.webapp.action.cpManage.contrastRectification;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;

import com.compliance.model.cpManage.rectificationProposal.RectificationProposal;
import com.compliance.service.cpManage.contrastRectification.ContrastService;
import com.compliance.service.cpManage.technology.TechnologyService;
import com.compliance.webapp.action.BaseAction;
import com.compliance.webapp.action.cpManage.demand.DemandColletAction;
import com.opensymphony.xwork2.ModelDriven;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.PageModel;
import com.util.page.SearchResult;

@SuppressWarnings("serial")
public class ContrastAction extends BaseAction implements
		ModelDriven<RectificationProposal> {

	public ContrastService contrastSrivice;
	public RectificationProposal proposal = new RectificationProposal();
	public List<RectificationProposal> rectificationProposalsList;
	private TechnologyService technologyService;
	/**
	 * 审计业务接口
	 */
	/*public AuditService auditService;*/

	@SuppressWarnings("unused")
	public void queryTree() {
		log.info("queryTree Contrast info...");
		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		response.setCharacterEncoding("UTF-8");
		try {
			rectificationProposalsList = contrastSrivice.auerySysList();
			if (null != rectificationProposalsList) {
				JSONArray jsonArray = JSONArray
						.fromObject(rectificationProposalsList);
				getResponse().getWriter().write(jsonArray.toString());
			}
		} catch (Exception e) {
			log.error(DemandColletAction.class, e);
		}
		return;
	}

	/**
	 * 查询某定级系统评估列表
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public String queryList() throws UnsupportedEncodingException {
		log.info("queryList Contrast info...");
		HttpServletRequest request = super.getRequest();
		Page page = null;
		request.setCharacterEncoding("UTF-8");
		 
		// 接受查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		String CORRRECOM_SysName = request.getParameter("CORRRECOM_SysName");
	   boolean sdf = StringUtil.isNotBlank(CORRRECOM_SysName);
		if(StringUtil.isNotBlank(CORRRECOM_SysName)){
			if(System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS")==0){
				CORRRECOM_SysName = new String(CORRRECOM_SysName.getBytes("iso-8859-1"),"utf-8");
			}
			String CORRRECOM_AssessResult = request.getParameter("CORRRECOM_AssessResult");
			if (CORRRECOM_SysName != null) {
				map.put("CORRRECOM_SysName", CORRRECOM_SysName);
			}
			if (CORRRECOM_AssessResult != null) {
				map.put("CORRRECOM_AssessResult", CORRRECOM_AssessResult);
			}
			SearchResult sr = contrastSrivice.query(map, page);
			rectificationProposalsList = sr.getList();
			request.setAttribute("Page", sr.getPage());
			request.setAttribute("CORRRECOM_SysName", CORRRECOM_SysName);
			request.setAttribute("CORRRECOM_AssessResult", CORRRECOM_AssessResult);
			PageModel pm = new PageModel(rectificationProposalsList, 15);
			// 处理数据分页的起始条数
					String startIndex = request.getParameter("page");
					if (StringUtil.isNotBlank(startIndex)) {
						rectificationProposalsList =pm.getObjectLists(Integer.valueOf(startIndex)); 
					} else {
						rectificationProposalsList =pm.getObjectLists(1); 
					}
			request.setAttribute("Page", pm);
		}
		return SUCCESS;

	}

	// 将页面数据导出为excel表格
	public void exportToExcel() throws UnsupportedEncodingException{
		String sysName = java.net.URLDecoder.decode(this.proposal.getCORRRECOM_SysName(), "UTF-8");
		String CORRRECOM_SysId=super.getRequest().getParameter("CORRRECOM_SysId");
		String sysname = null;
		if(sysName != null && !"".equals(sysName)){
			sysname=(technologyService.queryById(Integer.parseInt(CORRRECOM_SysId))).getName();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if(sysname!=null){
			map.put("CORRRECOM_SysName", sysname);
		}
//		if (this.proposal.getCORRRECOM_SysName() != null) {
//			map.put("CORRRECOM_SysName", this.proposal.getCORRRECOM_SysName().trim());
//		}
		if (this.proposal.getCORRRECOM_AssessResult() != null) {
			map.put("CORRRECOM_AssessResult", this.proposal.getCORRRECOM_AssessResult().trim());
		}
		List contrastList=contrastSrivice.query(map);
		//System.out.println("查询的数据条数是"+contrastList.size());
		try{
			//创建一个webbook对应一个excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			//创建内容样式
			HSSFCellStyle style = wb.createCellStyle(); // 样式对象
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
			style.setWrapText(true);  
			 // 设置标题字体
			HSSFFont font2 = wb.createFont(); // 创建字体格式 
			font2.setColor(HSSFFont.SS_NONE); // 设置单元格字体的颜色. 
			font2.setFontHeight((short) 250); // 设置字体大小 
			font2.setFontName("仿宋_GB2312");
			// 设置表格头字体
			HSSFFont fonth = wb.createFont(); // 创建字体格式 
			fonth.setColor(HSSFFont.SS_NONE); // 设置单元格字体的颜色. 
			fonth.setFontHeight((short) 300); // 设置字体大小 
			fonth.setFontName("黑体");
			//创建标题栏样式
			HSSFCellStyle styletitle = wb.createCellStyle(); // 样式对象
			styletitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
			styletitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
			styletitle.setFont(font2);
		    styletitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); // 填充单元格
		    styletitle.setFillForegroundColor(HSSFColor.GOLD.index);
		    //创建表头样式
		    HSSFCellStyle styleheader = wb.createCellStyle(); // 样式对象
		    styleheader.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
		    styleheader.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
		    styleheader.setFont(fonth);
		    //创建第2,3列的样式
		    HSSFCellStyle stylewrap = wb.createCellStyle(); // 样式对象
		    stylewrap.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
		    stylewrap.setWrapText(true);   
			//创建一个sheet
			HSSFSheet sheet = wb.createSheet(this.proposal.getCORRRECOM_SysName()+"整改建议");
			//设置列宽
			sheet.setColumnWidth((short) 0, 
					(short) ((70 * 8) / ((double) 1 / 10))); 
			sheet.setColumnWidth((short) 1, 
					(short) ((70 * 8) / ((double) 1 / 10))); 
			sheet.setColumnWidth((short) 2, 
					(short) ((250 * 8) / ((double) 1 / 10))); 
					sheet.setColumnWidth((short) 3, 
					(short) ((150 * 8) / ((double) 1 / 10))); 
					sheet.setColumnWidth((short) 4, 
					(short) ((30 * 8) / ((double) 1 / 20))); 
			//创建表格第0行
			sheet.addMergedRegion(new Region((short)(0), (short) 0, (short)(0), (short) 4));
			HSSFRow rowheader=sheet.createRow((short) 0);
			HSSFCell cellheader  = rowheader.createCell((short) 0);
			cellheader.setCellStyle(styleheader);
			cellheader.setCellValue(this.proposal.getCORRRECOM_SysName()+"整改建议对比");

			//创建表格第1行
			HSSFRow row0=sheet.createRow((short) 1);
			//创建第0行单元格
			HSSFCell cell= row0.createCell((short) 0);
			//样式居中
			cell.setCellStyle(styletitle);
			//添加第0行第0个单元格的标题内容
			cell.setCellValue("控制域");
			//添加第0行第一个单元格
			cell=row0.createCell((short) 1);
			//样式居中
			cell.setCellStyle(styletitle);
			//添加内容
			cell.setCellValue("控制单元");
			//添加第0行第二个单元格
			cell=row0.createCell((short) 2);
			//样式居中
			cell.setCellStyle(styletitle);
			//添加内容
			cell.setCellValue("不符合项");
			//添加第0行第三个单元格
			cell=row0.createCell((short)3);
			//样式居中
			cell.setCellStyle(styletitle);
			//添加内容
			cell.setCellValue("整改建议");
			//添加第0行第四个单元格
			cell=row0.createCell((short)4);
			//样式居中
			cell.setCellStyle(styletitle);
			//添加内容
			cell.setCellValue("整改时间");
			//写入实体数据
			int num=1;
			for (int i = 0; i < contrastList.size(); i++)
			{
				RectificationProposal con = (RectificationProposal) contrastList.get(i);
				List conlist=con.getList();
				int conlistnow=conlist.size();
				//System.out.println("该行数据中list长度是："+conlistnow);
				HSSFRow row =null;
				for(int k=0;k<conlistnow;k++){
					RectificationProposal innerlistcon=(RectificationProposal)conlist.get(k);
					//System.out.println("第"+i+"条数据"+"创建第"+(num+1)+"行");
					row = sheet.createRow((int) ( num+1));
					HSSFCell cell2 = row.createCell((short) 0);
					cell2.setCellValue(con.getCORRRECOM_ControlDomainName());
					cell2.setCellStyle(style);
					cell2=row.createCell((short) 1);
					cell2.setCellStyle(style);
					cell2.setCellValue(con.getCORRRECOM_ControlUnitName());
					cell2=row.createCell((short) 2);
					cell2.setCellStyle(stylewrap);
					cell2.setCellValue(innerlistcon.getCORRRECOM_ItemNumber()+innerlistcon.getCORRRECOM_Content());
					cell2=row.createCell((short) 3);
					cell2.setCellStyle(stylewrap);
					cell2.setCellValue(innerlistcon.getCORRRECOM_Advise());
					cell2=row.createCell((short) 4);
					cell2.setCellStyle(style);
					cell2.setCellValue(innerlistcon.getCORRRECOM_Date());
					num++;
					//System.out.println(num);
				}
				 //System.out.println("合并第"+(num-conlistnow+1)+"行和第"+(num)+"行");
				 sheet.addMergedRegion(new Region((short)(num-conlistnow+1), (short) 0, (short)(num), (short) 0));
			   	 sheet.addMergedRegion(new Region((short)(num-conlistnow+1), (short) 1, (short)(num), (short) 1));
							
			}
		
			// 第六步，将文件存到指定位置
			HttpServletResponse response = null;
		
			OutputStream out = null;
		
			try {
				response = ServletActionContext.getResponse();
				
				out = response.getOutputStream();
				String doc = "["+this.proposal.getCORRRECOM_SysName()+"]"+"整改建议对比";
				response.setHeader("Content-disposition", "attachment; filename="
						+ java.net.URLEncoder.encode(doc, "UTF-8") + ".xls");
				response.setContentType("application/msexcel;charset=UTF-8");
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				wb.write(out);
				out.flush();
				wb.write(out);
			} finally {
				if (out != null) {
					out.close();
					/*Audit audit=new Audit();
					audit.setDetailed(this.proposal.getCORRRECOM_SysName());
					audit.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
					audit.setObject("技术历史整改需求");
					audit.setTime(DateUtil.curDateTimeStr19());
					audit.setType("导出报表");
					auditService.instert(audit);*/
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	
		return;
		

	}

	public ContrastService getContrastSrivice() {
		return contrastSrivice;
	}

	public void setContrastSrivice(ContrastService contrastSrivice) {
		this.contrastSrivice = contrastSrivice;
	}

	public RectificationProposal getProposal() {
		return proposal;
	}

	public void setProposal(RectificationProposal proposal) {
		this.proposal = proposal;
	}

	public List<RectificationProposal> getRectificationProposalsList() {
		return rectificationProposalsList;
	}

	public void setRectificationProposalsList(
			List<RectificationProposal> rectificationProposalsList) {
		this.rectificationProposalsList = rectificationProposalsList;
	}

	public RectificationProposal getModel() {
		// TODO Auto-generated method stub
		return proposal;
	}

	public TechnologyService getTechnologyService() {
		return technologyService;
	}

	public void setTechnologyService(TechnologyService technologyService) {
		this.technologyService = technologyService;
	}

/*	public AuditService getAuditService() {
		return auditService;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}
	*/
	


}
