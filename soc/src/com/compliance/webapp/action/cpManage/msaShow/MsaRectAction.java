package com.compliance.webapp.action.cpManage.msaShow;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsx3.net.Request;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.compliance.model.cpManage.gpaShow.GpaRect;
import com.compliance.model.cpManage.msaShow.Msa;
import com.compliance.model.cpManage.msaShow.MsaRect;
import com.compliance.service.cpManage.msaShow.MsaRectService;
import com.compliance.service.cpManage.msaShow.MsaService;
import com.compliance.webapp.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.load.MsaRectDocCreate;
import com.util.page.Page;
import com.util.page.PageModel;
import com.util.page.SearchResult;
/**
 * 通用管理整改建议action
 * @author quyongkun
 *
 */
public class MsaRectAction extends BaseAction implements ModelDriven<MsaRect>{
	
	/**
	 * 通用管理整改建议实体
	 */
	private MsaRect msaRect=new MsaRect();
	
	/**
	 * 通用管理整改建议业务接口
	 */
	private MsaRectService msaRectService;
	
	/**
	 * 通用管理整改建议集合
	 */
	private List<MsaRect> msaRects=new ArrayList<MsaRect>();
	
	/**
	 * 测评时间 例 1996-12-02
	 */
	private String msaDate;
	
	/**
	 * 测评类型 
	 */
	private String type;
	
	/**
	 * 通用管理测评业务接口
	 */
	private MsaService msaService;
	
	/**
	 * 通用管理测评集合
	 */
	private List<Msa> msas;
	
	/**
	 * 审计业务接口
	 */
	/*public AuditService auditService;*/
	

	
	/**
	 * 查询通用管理测评列表
	 */
	public void queryTree(){
		HttpServletRequest request = super.getRequest();
		Page page = null;
		SearchResult sr = new SearchResult();
		this.getResponse().setCharacterEncoding("UTF-8");
		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		// 接受查询条件
		this.msas=msaService.querySoleDate();
		List<Map> mapMsa = new ArrayList<Map>();
		for(Msa Msa: msas)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("MsaDate", DateUtil.putDateToTimeStr19(Msa.getMsaDate()));
			mapMsa.add(map);
		}

		JSONArray jsonArray = JSONArray.fromObject(mapMsa);
		JSONObject jsonObject = JSONObject.fromObject(sr.getPage());
		// Ajax返回
		try {
			getResponse().getWriter().write(
					"{\"processLog\":" + jsonArray.toString() + ",\"page\":"+ jsonObject.toString() + "}");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;

	}
	
	
	/**
	 * 查询整改建议历史树
	 */
	public void queryHistoryTree(){
		
		this.msaRects=msaRectService.queryTree();
		List<Map> mapMsaRect = new ArrayList<Map>();
		for(MsaRect msaRectShort: msaRects)
		{
			if(msaRectShort.getMsaDate()!=null){
				Map<String, String> map = new HashMap<String, String>();
				map.put("msaDate", msaRectShort.getMsaDate());
				mapMsaRect.add(map);			
			}

		}

		JSONArray jsonArray = JSONArray.fromObject(mapMsaRect);
		JSONObject jsonObject = JSONObject.fromObject(null);
		// Ajax返回
		try {
			getResponse().getWriter().write(
					"{\"processLog\":" + jsonArray.toString() + ",\"page\":"+ jsonObject.toString() + "}");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;

	}
	
	/**
	 * 查询整改建议历史
	 */
	public void queryMsaReHistory(){
		HttpServletRequest request = super.getRequest();
		this.getResponse().setCharacterEncoding("UTF-8");
		String msaDate=request.getParameter("msaDate");
		String type=request.getParameter("type");
		if(msaDate==null || "".equals(msaDate)){
			this.msaRects=msaRectService.queryTree();
			if(msaRects.size()!=0){
				msaDate=msaRects.get(0).getMsaDate();
				type="1";
			}else{
				msaDate="";
				type="1";
			}	
		}
		Map<String,String> masp1=new HashMap();
		masp1.put("msaDate", msaDate);
		masp1.put("msaAssessResult", type);
		this.msaRects=msaRectService.queryByMap(masp1);
		List<Map> mapMsaRect = new ArrayList<Map>();
		for(MsaRect MsaRectShort: msaRects)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("gFatherSort", MsaRectShort.getgFatherSort());
			map.put("gFatherName", MsaRectShort.getgFatherName());
			map.put("fatherSort", MsaRectShort.getFatherSort());
			map.put("fatherName", MsaRectShort.getFatherName());
			map.put("sonContent", MsaRectShort.getSonContent());
			map.put("msaRectAdvise", MsaRectShort.getMsaRectAdvise());
			map.put("msaRectDate", MsaRectShort.getMsaRectDate());
			mapMsaRect.add(map);
		}

		JSONArray jsonArray = JSONArray.fromObject(mapMsaRect);
		JSONObject jsonObject = JSONObject.fromObject(null);
		// Ajax返回
		try {
			getResponse().getWriter().write(
					"{\"processLog\":" + jsonArray.toString() + ",\"page\":"+ jsonObject.toString() + "}");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;

	}
	
	public List<MsaRect> getMsaRectList(){
		HttpServletRequest request = super.getRequest();
		if("1".equals(request.getParameter("flag"))){
			 List<Msa> msass=msaService.querySoleDate();
			if(msass.size()!=0){
				msaDate=DateUtil.putDateToTimeStr19(msass.get(0).getMsaDate());
				type="1";
			}else{
				msaDate="";
				type="1";
			}
			
		}

		List<MsaRect> msaRects2=msaRectService.query(msaDate);//通过测评日期查询的数据
		Map map=new HashMap();
		map.put("msaDate", msaDate);
		map.put("msaAssessResult", type);
		String oneMsaType="";
		if("1".equals(type) || "2".equals(type)){//如果评估结果是部分符合或者不符合就比较单个评估是“否”的，否则比较单个是“不适用的”
			oneMsaType="0";
		}else{
			oneMsaType="2";
			map.remove("msaAssessResult");
		}
		List<Msa> msas=msaService.needConnect(map);
		List<MsaRect> list=msaRectService.queryAllData();//通用管理源数据 
		for(Msa msa:msas){
			if(!("0".equals(msa.getMsaAssessResult()))){
				boolean flag1=false;
				String str=msa.getMsaSort();
                 //取a
				if(msa.getMsaA()!=null && oneMsaType.equals(msa.getMsaA())){
					//遍历整改需求汇总集合
					for(MsaRect msaRectShort:list){
						if(msaRectShort.getSonSort().equals(str+".a")){
							msaRectShort.setSonContent("a)"+msaRectShort.getSonContent());
							
	                           for(MsaRect msaRect : msaRects2){
	                        	   if(msaRectShort.getSonSort().equals(msaRect.getSonSort())){
	                        		   msaRectShort.setMsaRectId(msaRect.getMsaRectId());//主键
	                        		   msaRectShort.setMsaDate(msaRect.getMsaDate());//通用整改时间
	                        		   msaRectShort.setMsaRectAdvise(msaRect.getMsaRectAdvise());//整改建议
	                        		   msaRectShort.setMsaRectDate(msaRect.getMsaRectDate());//整改时间
	                        		   this.msaRects.add(msaRectShort);
	                        		   flag1=true;
	                        		   break;
	                        	   }
	                           }
	                           if(flag1==false){
	                        	   this.msaRects.add(msaRectShort);
	                           }
	                           flag1=false;
							break;
						}
					}
				}
				if(msa.getMsaB()!=null && oneMsaType.equals(msa.getMsaB())){
					for(MsaRect msaRectShort:list){
						if(msaRectShort.getSonSort().equals(str+".b")){
							msaRectShort.setSonContent("b)"+msaRectShort.getSonContent());
	                           for(MsaRect msaRect : msaRects2){
	                        	   if(msaRectShort.getSonSort().equals(msaRect.getSonSort())){
	                        		   msaRectShort.setMsaRectId(msaRect.getMsaRectId());//主键
	                        		   msaRectShort.setMsaDate(msaRect.getMsaDate());//通用整改时间
	                        		   msaRectShort.setMsaRectAdvise(msaRect.getMsaRectAdvise());//整改建议
	                        		   msaRectShort.setMsaRectDate(msaRect.getMsaRectDate());//整改时间
	                        		   this.msaRects.add(msaRectShort);
	                        		   flag1=true;
	                        	   }
	                           }
	                           if(flag1==false){
	                        	   this.msaRects.add(msaRectShort);
	                           }
	                           flag1=false;
							break;
						}
					}
					
				}
				
				if(msa.getMsaC()!=null && oneMsaType.equals(msa.getMsaC())){
					for(MsaRect msaRectShort:list){
						if(msaRectShort.getSonSort().equals(str+".c")){
							msaRectShort.setSonContent("c)"+msaRectShort.getSonContent());
	                           for(MsaRect msaRect : msaRects2){
	                        	   if(msaRectShort.getSonSort().equals(msaRect.getSonSort())){
	                        		   msaRectShort.setMsaRectId(msaRect.getMsaRectId());//主键
	                        		   msaRectShort.setMsaDate(msaRect.getMsaDate());//通用整改时间
	                        		   msaRectShort.setMsaRectAdvise(msaRect.getMsaRectAdvise());//整改建议
	                        		   msaRectShort.setMsaRectDate(msaRect.getMsaRectDate());//整改时间
	                        		   this.msaRects.add(msaRectShort);
	                        		   flag1=true;
	                        	   }
	                           }
	                           if(flag1==false){
	                        	   this.msaRects.add(msaRectShort);
	                           }
	                           flag1=false;
							break;
						}
					}
				}
				
				if(msa.getMsaD()!=null && oneMsaType.equals(msa.getMsaD())){
					for(MsaRect msaRectShort:list){
						if(msaRectShort.getSonSort().equals(str+".d")){
							msaRectShort.setSonContent("d)"+msaRectShort.getSonContent());
	                           for(MsaRect msaRect : msaRects2){
	                        	   if(msaRectShort.getSonSort().equals(msaRect.getSonSort())){
	                        		   msaRectShort.setMsaRectId(msaRect.getMsaRectId());//主键
	                        		   msaRectShort.setMsaDate(msaRect.getMsaDate());//通用整改时间
	                        		   msaRectShort.setMsaRectAdvise(msaRect.getMsaRectAdvise());//整改建议
	                        		   msaRectShort.setMsaRectDate(msaRect.getMsaRectDate());//整改时间
	                        		   this.msaRects.add(msaRectShort);
	                        		   flag1=true;
	                        	   }
	                           }
	                           if(flag1==false){
	                        	   this.msaRects.add(msaRectShort);
	                           }
	                           flag1=false;
							break;
						}
					}
					
				}
				if(msa.getMsaE()!=null && oneMsaType.equals(msa.getMsaE())){
					for(MsaRect msaRectShort:list){
						if(msaRectShort.getSonSort().equals(str+".e")){
							msaRectShort.setSonContent("e)"+msaRectShort.getSonContent());
	                           for(MsaRect msaRect : msaRects2){
	                        	   if(msaRectShort.getSonSort().equals(msaRect.getSonSort())){
	                        		   msaRectShort.setMsaRectId(msaRect.getMsaRectId());//主键
	                        		   msaRectShort.setMsaDate(msaRect.getMsaDate());//通用整改时间
	                        		   msaRectShort.setMsaRectAdvise(msaRect.getMsaRectAdvise());//整改建议
	                        		   msaRectShort.setMsaRectDate(msaRect.getMsaRectDate());//整改时间
	                        		   this.msaRects.add(msaRectShort);
	                        		   flag1=true;
	                        	   }
	                           }
	                           if(flag1==false){
	                        	   this.msaRects.add(msaRectShort);
	                           }
	                           flag1=false;							
							break;
						}
					}
					
				}
				
				if(msa.getMsaF()!=null && oneMsaType.equals(msa.getMsaF())){
					for(MsaRect msaRectShort:list){
						if(msaRectShort.getSonSort().equals(str+".f")){
							msaRectShort.setSonContent("f)"+msaRectShort.getSonContent());
	                           for(MsaRect msaRect : msaRects2){
	                        	   if(msaRectShort.getSonSort().equals(msaRect.getSonSort())){
	                        		   msaRectShort.setMsaRectId(msaRect.getMsaRectId());//主键
	                        		   msaRectShort.setMsaDate(msaRect.getMsaDate());//通用整改时间
	                        		   msaRectShort.setMsaRectAdvise(msaRect.getMsaRectAdvise());//整改建议
	                        		   msaRectShort.setMsaRectDate(msaRect.getMsaRectDate());//整改时间
	                        		   this.msaRects.add(msaRectShort);
	                        		   flag1=true;
	                        	   }
	                           }
	                           if(flag1==false){
	                        	   this.msaRects.add(msaRectShort);
	                           }
	                           flag1=false;							
							break;
						}
					}
				}
				if(msa.getMsaG()!=null && oneMsaType.equals(msa.getMsaG())){
					for(MsaRect msaRectShort:list){
						if(msaRectShort.getSonSort().equals(str+".g")){
							msaRectShort.setSonContent("g)"+msaRectShort.getSonContent());
	                           for(MsaRect msaRect : msaRects2){
	                        	   if(msaRectShort.getSonSort().equals(msaRect.getSonSort())){
	                        		   msaRectShort.setMsaRectId(msaRect.getMsaRectId());//主键
	                        		   msaRectShort.setMsaDate(msaRect.getMsaDate());//通用整改时间
	                        		   msaRectShort.setMsaRectAdvise(msaRect.getMsaRectAdvise());//整改建议
	                        		   msaRectShort.setMsaRectDate(msaRect.getMsaRectDate());//整改时间
	                        		   this.msaRects.add(msaRectShort);
	                        		   flag1=true;
	                        	   }
	                           }
	                           if(flag1==false){
	                        	   this.msaRects.add(msaRectShort);
	                           }
	                           flag1=false;	
							break;
						}
					}
					
				}
				if(msa.getMsaH()!=null && oneMsaType.equals(msa.getMsaH())){
					for(MsaRect msaRectShort:list){
						if(msaRectShort.getSonSort().equals(str+".h")){
							msaRectShort.setSonContent("h)"+msaRectShort.getSonContent());
	                           for(MsaRect msaRect : msaRects2){
	                        	   if(msaRectShort.getSonSort().equals(msaRect.getSonSort())){
	                        		   msaRectShort.setMsaRectId(msaRect.getMsaRectId());//主键
	                        		   msaRectShort.setMsaDate(msaRect.getMsaDate());//通用整改时间
	                        		   msaRectShort.setMsaRectAdvise(msaRect.getMsaRectAdvise());//整改建议
	                        		   msaRectShort.setMsaRectDate(msaRect.getMsaRectDate());//整改时间
	                        		   this.msaRects.add(msaRectShort);
	                        		   flag1=true;
	                        	   }
	                           }
	                           if(flag1==false){
	                        	   this.msaRects.add(msaRectShort);
	                           }
	                           flag1=false;	
							break;
						}
					}
				}
				if(msa.getMsaI()!=null && oneMsaType.equals(msa.getMsaI())){
					for(MsaRect msaRectShort:list){
						if(msaRectShort.getSonSort().equals(str+".i")){
							msaRectShort.setSonContent("i)"+msaRectShort.getSonContent());
	                           for(MsaRect msaRect : msaRects2){
	                        	   if(msaRectShort.getSonSort().equals(msaRect.getSonSort())){
	                        		   msaRectShort.setMsaRectId(msaRect.getMsaRectId());//主键
	                        		   msaRectShort.setMsaDate(msaRect.getMsaDate());//通用整改时间
	                        		   msaRectShort.setMsaRectAdvise(msaRect.getMsaRectAdvise());//整改建议
	                        		   msaRectShort.setMsaRectDate(msaRect.getMsaRectDate());//整改时间
	                        		   this.msaRects.add(msaRectShort);
	                        		   flag1=true;
	                        	   }
	                           }
	                           if(flag1==false){
	                        	   this.msaRects.add(msaRectShort);
	                           }
	                           flag1=false;	
							break;
						}
					}
				}
				if(msa.getMsaJ()!=null && oneMsaType.equals(msa.getMsaJ())){
					for(MsaRect msaRectShort:list){
						if(msaRectShort.getSonSort().equals(str+".j")){
							msaRectShort.setSonContent("j)"+msaRectShort.getSonContent());
	                           for(MsaRect msaRect : msaRects2){
	                        	   if(msaRectShort.getSonSort().equals(msaRect.getSonSort())){
	                        		   msaRectShort.setMsaRectId(msaRect.getMsaRectId());//主键
	                        		   msaRectShort.setMsaDate(msaRect.getMsaDate());//通用整改时间
	                        		   msaRectShort.setMsaRectAdvise(msaRect.getMsaRectAdvise());//整改建议
	                        		   msaRectShort.setMsaRectDate(msaRect.getMsaRectDate());//整改时间
	                        		   this.msaRects.add(msaRectShort);
	                        		   flag1=true;
	                        	   }
	                           }
	                           if(flag1==false){
	                        	   this.msaRects.add(msaRectShort);
	                           }
	                           flag1=false;	
							break;
						}
					}
				}
		
			}

		}
		return this.msaRects;
	}
	
	/**
	 * 显示通用管理整改建议列表
	 */
	@SuppressWarnings("unchecked")
	public String msaRectList(){
		HttpServletRequest request = super.getRequest();
		msaDate=request.getParameter("msaDate");
		type=request.getParameter("type");
		request.setAttribute("msaDate", msaDate);
		request.setAttribute("type", type);
		getMsaRectList();
		this.getResponse().setCharacterEncoding("UTF-8");
		List<Map> mapMsaRect = new ArrayList<Map>();
		if(msaRects.size()!=0){
			for(MsaRect msaRect1: msaRects)
			{
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("msaRectId", msaRect1.getMsaRectId());//整改建议
				map1.put("msaRectAdvise", msaRect1.getMsaRectAdvise());//整改建议
				map1.put("msaRectDate", msaRect1.getMsaRectDate());//整改时间
				map1.put("gFatherSort", msaRect1.getgFatherSort());//祖父排序
				map1.put("gFatherName",msaRect1.getgFatherName());//祖父名称
				map1.put("fatherSort", msaRect1.getFatherSort());//父亲排序+父亲名称
				map1.put("fatherName",msaRect1.getFatherName());//父亲排序+父亲名称
				map1.put("sonSort", msaRect1.getSonSort());//儿子排序
				map1.put("sonContent", msaRect1.getSonContent());//儿子内容
				
				mapMsaRect.add(map1);
			}
		
	    }
		PageModel pm = new PageModel(mapMsaRect, 15);
		// 处理数据分页的起始条数
				String startIndex = request.getParameter("page");
				if (StringUtil.isNotBlank(startIndex)) {
					mapMsaRect =pm.getObjectLists(Integer.valueOf(startIndex)); 
				} else {
					mapMsaRect =pm.getObjectLists(1); 
				}
				request.setAttribute("Page", pm);
				request.setAttribute("mapMsaRect", mapMsaRect);
				
	/*	JSONArray jsonArray = JSONArray.fromObject(mapMsaRect);
		JSONObject jsonObject = JSONObject.fromObject(null);
		// Ajax返回
		try {
			getResponse().getWriter().write(
					"{\"processLog\":" + jsonArray.toString() + ",\"page\":"+ jsonObject.toString() + "}");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		return SUCCESS;
	}
	
	/**
	 * 整改建议修改
	 * @return
	 */
	
	String	mpaDateUpdate;
	String typeUpdate;
	public String msaRectUpdate(){
		HttpServletRequest request = super.getRequest();
		String str1=request.getParameter("msaRectId");
		int MsaRectId=Integer.parseInt(str1);
		mpaDateUpdate=request.getParameter("mpaDateUpdate");
		typeUpdate=request.getParameter("typeUpdate");
		String MsaRectAdvise=request.getParameter("msaRectAdvise");
		try {
			MsaRectAdvise=java.net.URLDecoder.decode(MsaRectAdvise, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.msaRect.setMsaRectId(MsaRectId);
		this.msaRect.setMsaRectAdvise(MsaRectAdvise);
		this.msaRect.setMsaRectDate(DateUtil.curDateTimeStr19());
		msaRectService.update(msaRect);
		/*Audit audit=new Audit();
		audit.setDetailed(mpaDateUpdate);
		audit.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
		audit.setObject("通用管理安全整改建议");
		audit.setTime(DateUtil.curDateTimeStr19());
		audit.setType("修改");
		auditService.instert(audit);*/
		return SUCCESS;
	}
	
	public String getMpaDateUpdate() {
		return mpaDateUpdate;
	}


	public void setMpaDateUpdate(String mpaDateUpdate) {
		this.mpaDateUpdate = mpaDateUpdate;
	}


	public String getTypeUpdate() {
		return typeUpdate;
	}


	public void setTypeUpdate(String typeUpdate) {
		this.typeUpdate = typeUpdate;
	}


	/**
	 * 整改建议插入
	 * @return
	 */
	String mpaDateinsert;
	String typeinsert;
	

	public String msaRectInsert(){
		HttpServletRequest request = super.getRequest();
		mpaDateinsert=request.getParameter("maDateInsert");
		typeinsert=request.getParameter("typeInsert");
        this.msaRect.setMsaRectDate(DateUtil.curDateTimeStr19());
        super.getRequest().setAttribute("test", "test");
		msaRectService.insert(msaRect);
	/*	Audit audit=new Audit();
		audit.setDetailed(mpaDateinsert);
		audit.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
		audit.setObject("通用管理安全整改建议");
		audit.setTime(DateUtil.curDateTimeStr19());
		audit.setType("添加");
		auditService.instert(audit);*/
		return SUCCESS;
	}
	
	/**
	 * 整改建议删除
	 * @return
	 */
	String mpaDateDelete;
	String typeDelete;
	


	public String msaRectDelete(){
		HttpServletRequest request = super.getRequest();
		String str=request.getParameter("msaRectId");
		int msaRectId=Integer.parseInt(str);
		
		mpaDateDelete=request.getParameter("mpaDateDelete");
		typeDelete=request.getParameter("typeDelete");
		msaRectService.delete(msaRectId);
	/*	Audit audit=new Audit();
		audit.setDetailed(mpaDateDelete);
		audit.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
		audit.setObject("通用管理安全整改建议");
		audit.setTime(DateUtil.curDateTimeStr19());
		audit.setType("删除");
		auditService.instert(audit);*/
		return SUCCESS;
	}
	
	public void msaRectDocCreate(){
		HttpServletRequest request = super.getRequest();
		HttpServletResponse  response=super.getResponse();
		msaDate=request.getParameter("msaDate");
		
		type="1";
		Map map1=new HashMap();
		map1.put("msaDate", msaDate);
		map1.put("msaAssessResult", type);
		List<MsaRect> msaRectList1=getMsaRectList();
		
		msaRects=new ArrayList<MsaRect>();
		type="2";
		Map map2=new HashMap();
		map2.put("msaDate", msaDate);
		map2.put("msaAssessResult", type);
		List<MsaRect> msaRectList2=getMsaRectList();
		MsaRectDocCreate.createMsaRectDoc(response,msaRectList1.size(),msaRectList2.size(),msaRectList1,msaRectList2);
	/*	Audit audit=new Audit();
		audit.setDetailed(msaDate);
		audit.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
		audit.setObject("通用管理安全整改建议");
		audit.setTime(DateUtil.curDateTimeStr19());
		audit.setType("导出报表");
		auditService.instert(audit);*/
		return ;
	}
	//导出excel表格
	public void msaexportexcel(){
		HttpServletRequest request = super.getRequest();
		this.getResponse().setCharacterEncoding("UTF-8");
		String msaDate=request.getParameter("msaDate");
		String type=request.getParameter("type");
		Map<String,String> masp1=new HashMap();
		masp1.put("msaDate", msaDate);
		masp1.put("msaAssessResult", type);
		this.msaRects=msaRectService.queryByMap(masp1);
		//System.out.println(msaDate);
		//System.out.println(type);
		//System.out.println(msaRects.size());
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
			HSSFSheet sheet = wb.createSheet(msaDate+"整改建议");
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
			cellheader.setCellValue(this.msaRect.getMsaDate()+"整改建议对比");

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
			
			for (int i = 0; i < this.msaRects.size(); i++)
			{
				msaRect = (MsaRect) this.msaRects.get(i);
				
				HSSFRow row=sheet.createRow((short) (i+2));
					HSSFCell cell2 = row.createCell((short) 0);
					cell2.setCellValue(msaRect.getgFatherSort()+msaRect.getgFatherName());
					cell2.setCellStyle(style);
					cell2=row.createCell((short) 1);
					cell2.setCellStyle(style);
					cell2.setCellValue(msaRect.getFatherSort()+msaRect.getFatherName());
					cell2=row.createCell((short) 2);
					cell2.setCellStyle(stylewrap);
					cell2.setCellValue(msaRect.getSonContent());
					cell2=row.createCell((short) 3);
					cell2.setCellStyle(stylewrap);
					cell2.setCellValue(msaRect.getMsaRectAdvise());
					cell2=row.createCell((short) 4);
					cell2.setCellStyle(style);
					cell2.setCellValue(msaRect.getMsaRectDate());
				
							
			}
		
			// 第六步，将文件存到指定位置
			HttpServletResponse response = null;
		
			OutputStream out = null;
		
			try {
				response = ServletActionContext.getResponse();
				
				out = response.getOutputStream();
				String doc = DateUtil.curDateTimeStr14()+"通用管理整改建议";
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
				/*	Audit audit=new Audit();
					audit.setDetailed(msaDate);
					audit.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
					audit.setObject("通用管理历史整改需求");
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

	public MsaRect getModel() {
		return this.msaRect;
	}


	public MsaRect getMsaRect() {
		return msaRect;
	}


	public void setMsaRect(MsaRect msaRect) {
		this.msaRect = msaRect;
	}


	public MsaRectService getMsaRectService() {
		return msaRectService;
	}


	public void setMsaRectService(MsaRectService msaRectService) {
		this.msaRectService = msaRectService;
	}


	public List<MsaRect> getMsaRects() {
		return msaRects;
	}


	public void setMsaRects(List<MsaRect> msaRects) {
		this.msaRects = msaRects;
	}


	public String getMsaDate() {
		return msaDate;
	}


	public void setMsaDate(String msaDate) {
		this.msaDate = msaDate;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public MsaService getMsaService() {
		return msaService;
	}


	public void setMsaService(MsaService msaService) {
		this.msaService = msaService;
	}


	public List<Msa> getMsas() {
		return msas;
	}


	public void setMsas(List<Msa> msas) {
		this.msas = msas;
	}
	public String getMpaDateDelete() {
		return mpaDateDelete;
	}


	public void setMpaDateDelete(String mpaDateDelete) {
		this.mpaDateDelete = mpaDateDelete;
	}


	public String getTypeDelete() {
		return typeDelete;
	}


	public void setTypeDelete(String typeDelete) {
		this.typeDelete = typeDelete;
	}
	public String getMpaDateinsert() {
		return mpaDateinsert;
	}


	public void setMpaDateinsert(String mpaDateinsert) {
		this.mpaDateinsert = mpaDateinsert;
	}


	public String getTypeinsert() {
		return typeinsert;
	}


	public void setTypeinsert(String typeinsert) {
		this.typeinsert = typeinsert;
	}


	/*public AuditService getAuditService() {
		return auditService;
	}


	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}
*/
	
}
