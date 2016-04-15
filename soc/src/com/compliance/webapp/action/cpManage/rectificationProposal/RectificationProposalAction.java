package com.compliance.webapp.action.cpManage.rectificationProposal;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.util.HSSFColor.TEAL;

import com.compliance.model.cpManage.rectificationProposal.RectificationProposal;
import com.compliance.service.cpManage.rectificationProposal.RectificationProposalService;
import com.compliance.service.cpManage.technology.TechnologyService;
import com.compliance.webapp.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.load.ProposalRectDocCreate;
import com.util.page.Page;
import com.util.page.PageModel;
import com.util.page.SearchResult;

@SuppressWarnings("serial")
public class RectificationProposalAction extends BaseAction implements ModelDriven<RectificationProposal>{

	private RectificationProposalService rectificationProposalService;
	private String keyword;
	private List<RectificationProposal>proposals;
	private RectificationProposal rectificationProposal;
	private TechnologyService technologyService;
	/**
	 * 审计业务接口
	 */
	/*public AuditService auditService;
	
	public AuditService getAuditService() {
		return auditService;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}*/

	/**
	 * 整改建议列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String query() {
		log.info("queryList technology info...");
		  String FK_CA = super.getRequest().getParameter("FK_CA").trim();
		  
		    super.getRequest().setAttribute("FK_CA", FK_CA);
		    String CIA_AssessResult = super.getRequest().getParameter("CIA_AssessResult").trim();
		    super.getRequest().setAttribute("CIA_AssessResult", CIA_AssessResult);
		    String sysname = super.getRequest().getParameter("sysname");
		    String sysName=(technologyService.queryById(Integer.parseInt(FK_CA))).getName();
		    try {
				sysname = new String(sysname.getBytes("iso-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    super.getRequest().setAttribute("sysname", sysName);
		HttpServletRequest request = super.getRequest();
		Page page = null;
		 
		// 接受查询条件
		Map<String, Object> map = new HashMap<String, Object>();
	 
		
		if (FK_CA != null) {
			map.put("FK_CA", Integer.parseInt(FK_CA));
		}
		if (CIA_AssessResult != null) {
			map.put("CIA_AssessResult", CIA_AssessResult);
		}
		SearchResult sr = rectificationProposalService.query(map, page);
	 	proposals = sr.getList();
	 	request.setAttribute("Page", sr.getPage());
	 	PageModel pm = new PageModel(proposals, 15);
		// 处理数据分页的起始条数
				String startIndex = request.getParameter("page");
				if (StringUtil.isNotBlank(startIndex)) {
					proposals =pm.getObjectLists(Integer.valueOf(startIndex)); 
				} else {
					proposals =pm.getObjectLists(1); 
				}
		request.setAttribute("Page", pm);
		return SUCCESS;
	}
	
	
	/**
	 * 技术整改建议报表生成
	 */
	public void proposalRectDocCreate(){

		 String FK_CA = super.getRequest().getParameter("FK_CA").trim();
		 String sysname = super.getRequest().getParameter("sysname");
		 String sysName=(technologyService.queryById(Integer.parseInt(FK_CA))).getName();
		 try {
			 sysname = new String(sysname.getBytes("iso-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		 Page page =new Page(Page.DEFAULT_PAGE_SIZE, 0);
		// 接受查询条件
			Map<String, Object> map1 = new HashMap<String, Object>();
			if (FK_CA != null) {
				map1.put("FK_CA", Integer.parseInt(FK_CA));
			}
			map1.put("CIA_AssessResult", "1");
			SearchResult sr = rectificationProposalService.query(map1, page);//
			List<RectificationProposal> proposals1 = sr.getList();//部分符合数据集合
			
			Map<String, Object> map2 = new HashMap<String, Object>();
			if (FK_CA != null) {
				map2.put("FK_CA", Integer.parseInt(FK_CA));
			}
			map2.put("CIA_AssessResult", "2");
			 sr = rectificationProposalService.query(map2, page);//
			List<RectificationProposal> proposals2 = sr.getList();//不符合数据集合

			map1 = new HashMap<String, Object>();
			map1.put("FK_CA", Integer.parseInt(FK_CA));
			map1.put("CIA_AssessResult", "1");

		    map2 = new HashMap<String, Object>();
			map2.put("FK_CA", Integer.parseInt(FK_CA));
			map2.put("CIA_AssessResult", "2");
			ProposalRectDocCreate.createProposalRectDoc( super.getResponse(),sysName,  proposals1.size(), proposals2.size(), proposals1, proposals2);
		/*	Audit audit=new Audit();
			audit.setDetailed(sysnameparam);
			audit.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
			audit.setObject("技术差距分析整改建议");
			audit.setTime(DateUtil.curDateTimeStr19());
			audit.setType("导出报表");
			auditService.instert(audit);*/
		return ;
	}

	
	/**
	 * 添加整改建议
	 * @throws UnsupportedEncodingException 
	 */
	String cia_assessResultparam;
	private String sysname;
	String sysnameparam;
	String fkcaparam;
	public String insterProposal() throws UnsupportedEncodingException{
		HttpServletRequest request = super.getRequest();
		cia_assessResultparam=new String(request.getParameter("cia_assessResultparam").getBytes("iso8859-1"),"gb2312");
		sysnameparam=new String(request.getParameter("sysnameparam").getBytes("iso8859-1"),"utf-8");
		fkcaparam=request.getParameter("fkcaparam");
		String ControlDomainFromPage = super.getRequest().getParameter("ControlDomain") ;
		String ControlUnitFromPage = super.getRequest().getParameter("ControlUnit") ;
		String ContentFromPage =  super.getRequest().getParameter("Content") ;
		String sysIdFromPage =  super.getRequest().getParameter("sysId") ;
		String sysnameFromPage = super.getRequest().getParameter("sysname") ;
		String proposalFromPage =  super.getRequest().getParameter("proposal") ;
		String assessResultFromPage = super.getRequest().getParameter("AssessResult");
		//处理界面参数
		String ControlDomainSort ="";
		String ControlDomainName ="";
		String ControlUnitSort ="";
		String ControlUnitName ="";
		String ItemNumber ="";
		String Content =ContentFromPage.trim().substring(2).trim();
		String Advise =proposalFromPage;
		String SysName =sysnameFromPage;
		
		String SysId =sysIdFromPage;
		String AssessResult = assessResultFromPage;
		
		String [] domains = ControlDomainFromPage.split(":");
		ControlDomainSort=domains[0];
		ControlDomainName=domains[domains.length-1];
		String [] Units = ControlUnitFromPage.split(":");
		ControlUnitSort = Units[0];
		ControlUnitName = Units[Units.length-1];
		ItemNumber = ControlUnitSort.replaceAll(" ", "")+".2."+ContentFromPage.trim().substring(0, 1).replaceAll(" ", "");
		String s1 = ControlUnitSort.replaceAll(" ", "")+".2.";
		
		RectificationProposal rectificationProposal = new RectificationProposal();
		rectificationProposal.setCORRRECOM_ControlDomainSort(ControlDomainSort.trim());
		rectificationProposal.setCORRRECOM_ControlDomainName(ControlDomainName);
		rectificationProposal.setCORRRECOM_ControlUnitSort(ControlUnitSort.trim());
		rectificationProposal.setCORRRECOM_ControlUnitName(ControlUnitName.trim());
		rectificationProposal.setCORRRECOM_ItemNumber(ItemNumber.trim().replaceAll(" ", ""));
		rectificationProposal.setCORRRECOM_Content(Content.trim());
		rectificationProposal.setCORRRECOM_Advise(Advise.trim());
		rectificationProposal.setCORRRECOM_SysName(sysname.trim());
		rectificationProposal.setCORRRECOM_SysId(SysId.trim());
		Date dateDate = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		rectificationProposal.setCORRRECOM_Date(dateString);
		rectificationProposal.setCORRRECOM_AssessType("2");
		rectificationProposal.setCORRRECOM_AssessResult(AssessResult.trim());
		rectificationProposalService.insterProposal(rectificationProposal);
	/*	Audit audit=new Audit();
		audit.setDetailed(sysnameparam);
		audit.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
		audit.setObject("技术差距分析整改建议");
		audit.setTime(DateUtil.curDateTimeStr19());
		audit.setType("添加");
		auditService.instert(audit);*/
		return SUCCESS;
	}
	
	/**
	 * 修改整改建议
	 */
	String cia_assessResultUpdata;
	String sysnameUpdata;
	String fkcaUpdata;
	public String updataProposal() throws UnsupportedEncodingException{
		HttpServletRequest request = super.getRequest();
		cia_assessResultUpdata=request.getParameter("cia_assessResultUpdata");
		sysnameUpdata=new String(request.getParameter("sysnameUpdata").getBytes("iso8859-1"),"utf-8");
		fkcaUpdata=request.getParameter("fkcaUpdata");
		String ControlDomainFromPage = super.getRequest().getParameter("ControlDomain") ;
		String ControlUnitFromPage = super.getRequest().getParameter("ControlUnit") ;
		String ContentFromPage =  super.getRequest().getParameter("Content") ;
		String sysIdFromPage =  super.getRequest().getParameter("sysId") ;
		String sysnameFromPage =  super.getRequest().getParameter("sysname") ;
		String proposalFromPage =  super.getRequest().getParameter("proposal") ;
		String assessResultFromPage = super.getRequest().getParameter("AssessResult");
		String sysName=(technologyService.queryById(Integer.parseInt(fkcaUpdata))).getName();
		//处理界面参数
		String ControlDomainSort ="";
		String ControlDomainName ="";
		String ControlUnitSort ="";
		String ControlUnitName ="";
		String ItemNumber ="";
		String Content =ContentFromPage.trim().substring(2).trim();
		String Advise =proposalFromPage;
		String SysName =sysnameFromPage;
		String SysId =sysIdFromPage;
		String AssessResult = assessResultFromPage;
		
		
		String [] domains = ControlDomainFromPage.split(":");
		ControlDomainSort=domains[0];
		ControlDomainName=domains[domains.length-1];
		String [] Units = ControlUnitFromPage.split(":");
		ControlUnitSort = Units[0];
		ControlUnitName = Units[Units.length-1];
		ItemNumber = ControlUnitSort.replaceAll(" ", "")+".2."+ContentFromPage.trim().substring(0, 1).replaceAll(" ", "");
		String s1 = ControlUnitSort.replaceAll(" ", "")+".2.";
		
		RectificationProposal rectificationProposal = new RectificationProposal();
		rectificationProposal.setCORRRECOM_ControlDomainSort(ControlDomainSort.trim());
		rectificationProposal.setCORRRECOM_ControlDomainName(ControlDomainName);
		rectificationProposal.setCORRRECOM_ControlUnitSort(ControlUnitSort.trim());
		rectificationProposal.setCORRRECOM_ControlUnitName(ControlUnitName.trim());
		rectificationProposal.setCORRRECOM_ItemNumber(ItemNumber.trim().replaceAll(" ", ""));
		rectificationProposal.setCORRRECOM_Content(Content.trim());
		rectificationProposal.setCORRRECOM_Advise(Advise.trim());
		rectificationProposal.setCORRRECOM_SysName(sysName.trim());
		rectificationProposal.setCORRRECOM_SysId(SysId.trim());
		Date dateDate = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		rectificationProposal.setCORRRECOM_Date(dateString);
		rectificationProposal.setCORRRECOM_AssessType("2");
		rectificationProposal.setCORRRECOM_AssessResult(AssessResult.trim());
		rectificationProposalService.updataProposal(rectificationProposal);
	/*	Audit audit=new Audit();
		audit.setDetailed(sysnameUpdata);
		audit.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
		audit.setObject("技术差距分析整改建议");
		audit.setTime(DateUtil.curDateTimeStr19());
		audit.setType("修改");
		auditService.instert(audit);*/
		return SUCCESS;
	}
	
	/**
	 * 删除整改建议
	 * @throws UnsupportedEncodingException 
	 *
	 */
	String cia_assessResultDelete;
	String sysnameDelete;
	String fkcaDelete;
	public String deleteProposal() throws UnsupportedEncodingException{
		HttpServletRequest request = super.getRequest();
		cia_assessResultDelete=request.getParameter("cia_assessResultDelete");
		sysnameDelete=java.net.URLDecoder.decode(super.getRequest().getParameter("sysnameDelete") , "UTF-8");
		fkcaDelete=request.getParameter("fkcaDelete");
		String sysIdFromPage =  super.getRequest().getParameter("sysId") ;
		String ContentFromPage =  java.net.URLDecoder.decode(super.getRequest().getParameter("Content") , "UTF-8");
		String ControlUnitFromPage = java.net.URLDecoder.decode(super.getRequest().getParameter("ControlUnit"), "UTF-8") ;
		String [] Units = ControlUnitFromPage.split(":");
		String ItemNumber =Units[0].replaceAll(" ", "")+".2."+ContentFromPage.trim().substring(0, 1).replaceAll(" ", "");;
		Map<String, String>map = new HashMap<String, String>();
		map.put("CORRRECOM_SysId", sysIdFromPage);
		map.put("CORRRECOM_ItemNumber", ItemNumber);
		rectificationProposalService.deleteProposal(map);
	/*	Audit audit=new Audit();
		audit.setDetailed(sysnameDelete);
		audit.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
		audit.setObject("技术差距分析整改建议");
		audit.setTime(DateUtil.curDateTimeStr19());
		audit.setType("删除");
		auditService.instert(audit);*/
		return SUCCESS;
	}
	
	public String getFkcaDelete() {
		return fkcaDelete;
	}


	public void setFkcaDelete(String fkcaDelete) {
		this.fkcaDelete = fkcaDelete;
	}


	public RectificationProposal getModel() {
		return rectificationProposal;
	}
	public RectificationProposalService getRectificationProposalService() {
		return rectificationProposalService;
	}
	public void setRectificationProposalService(
			RectificationProposalService rectificationProposalService) {
		this.rectificationProposalService = rectificationProposalService;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public List<RectificationProposal> getProposals() {
		return proposals;
	}
	public void setProposals(List<RectificationProposal> proposals) {
		this.proposals = proposals;
	}
	public RectificationProposal getRectificationProposal() {
		return rectificationProposal;
	}
	public void setRectificationProposal(RectificationProposal rectificationProposal) {
		this.rectificationProposal = rectificationProposal;
	}


	public String getCia_assessResultparam() {
		return cia_assessResultparam;
	}


	public void setCia_assessResultparam(String cia_assessResultparam) {
		this.cia_assessResultparam = cia_assessResultparam;
	}


	public String getSysnameparam() {
		return sysnameparam;
	}


	public void setSysnameparam(String sysnameparam) {
		this.sysnameparam = sysnameparam;
	}
	public String getCia_assessResultUpdata() {
		return cia_assessResultUpdata;
	}


	public void setCia_assessResultUpdata(String cia_assessResultUpdata) {
		this.cia_assessResultUpdata = cia_assessResultUpdata;
	}


	public String getSysnameUpdata() {
		return sysnameUpdata;
	}


	public void setSysnameUpdata(String sysnameUpdata) {
		this.sysnameUpdata = sysnameUpdata;
	}
	
	public String getCia_assessResultDelete() {
		return cia_assessResultDelete;
	}


	public void setCia_assessResultDelete(String cia_assessResultDelete) {
		this.cia_assessResultDelete = cia_assessResultDelete;
	}


	public String getSysnameDelete() {
		return sysnameDelete;
	}


	public void setSysnameDelete(String sysnameDelete) {
		this.sysnameDelete = sysnameDelete;
	}
	public String getFkcaparam() {
		return fkcaparam;
	}


	public void setFkcaparam(String fkcaparam) {
		this.fkcaparam = fkcaparam;
	}
	public String getFkcaUpdata() {
		return fkcaUpdata;
	}


	public void setFkcaUpdata(String fkcaUpdata) {
		this.fkcaUpdata = fkcaUpdata;
	}


	public TechnologyService getTechnologyService() {
		return technologyService;
	}


	public void setTechnologyService(TechnologyService technologyService) {
		this.technologyService = technologyService;
	}


	public String getSysname() {
		return sysname;
	}


	public void setSysname(String sysname) {
		this.sysname = sysname;
	}

}
