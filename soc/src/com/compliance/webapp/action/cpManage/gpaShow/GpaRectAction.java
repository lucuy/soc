package com.compliance.webapp.action.cpManage.gpaShow;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.struts2.ServletActionContext;

import com.compliance.model.cpManage.gpaShow.Gpa;
import com.compliance.model.cpManage.gpaShow.GpaRect;
import com.compliance.service.cpManage.gpaShow.GpaRectService;
import com.compliance.service.cpManage.gpaShow.GpaService;
import com.compliance.webapp.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.load.GpaRectDocCreate;
import com.util.page.Page;
import com.util.page.PageModel;
import com.util.page.SearchResult;
/**
 * 通用物理整改建议action
 * @author quyongkun
 *
 */
public class GpaRectAction extends BaseAction implements ModelDriven<GpaRect>{
	
	/**
	 * 通用物理整改建议实体
	 */
	private GpaRect gpaRect=new GpaRect();
	
	/**
	 * 通用物理整改建议业务接口
	 */
	private GpaRectService gpaRectService;
	
	/**
	 * 通用物理整改建议集合
	 */
	private List<GpaRect> gpaRects=new ArrayList<GpaRect>();
	
	/**
	 * 测评时间 例 1996-12-02
	 */
	private String gpaDate;
	
	/**
	 * 测评类型 
	 */
	private String type;
	
	
	/**
	 * 测评时间 例 1996-12-02 12:22:03
	 */
	private String gpaDateinsert;
	
	/**
	 * 审计业务接口
	 *//*
	public AuditService auditService;
	
	public AuditService getAuditService() {
		return auditService;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}*/
	
	public String getGpaDateinsert() {
		return gpaDateinsert;
	}


	public void setGpaDateinsert(String gpaDateinsert) {
		this.gpaDateinsert = gpaDateinsert;
	}


	public String getTypeinsert() {
		return typeinsert;
	}


	public void setTypeinsert(String typeinsert) {
		this.typeinsert = typeinsert;
	}


	/**
	 * 测评类型 
	 */
	private String typeinsert;
	
	/**
	 * 通用物理测评业务接口
	 */
	private GpaService gpaService;
	
	/**
	 * 通用物理测评集合
	 */
	private List<Gpa> gpas;
	/**
	 * 查询通用物理测评列表
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
		this.gpas=gpaService.querySoleDate();
		List<Map> mapgpa = new ArrayList<Map>();
		for(Gpa gpa: gpas)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("gpaDate", DateUtil.putDateToTimeStr10(gpa.getGpaDate()));
			mapgpa.add(map);
		}

		JSONArray jsonArray = JSONArray.fromObject(mapgpa);
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
		this.getResponse().setCharacterEncoding("UTF-8");

		this.gpaRects=gpaRectService.queryTree();
		List<Map> mapgpaRect = new ArrayList<Map>();
		for(GpaRect gpaRectShort: gpaRects)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("gpaDate", gpaRectShort.getGpaDate());
			mapgpaRect.add(map);
		}

		JSONArray jsonArray = JSONArray.fromObject(mapgpaRect);
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
	public void queryGpaReHistory(){
		HttpServletRequest request = super.getRequest();
		this.getResponse().setCharacterEncoding("UTF-8");
		String gpaDate=request.getParameter("gpaDate");
		String type=request.getParameter("type");
		if(gpaDate==null || "".equals(gpaDate)){
			this.gpaRects=gpaRectService.queryTree();//gpaDate
			if(gpaRects.size()!=0){
				gpaDate=gpaRects.get(0).getGpaDate();
				type="1";
			}else{
				gpaDate="";
				type="1";
			}	
		}
		Map<String,String> masp1=new HashMap();
		masp1.put("gpaDate", gpaDate);
		masp1.put("gpaAssessResult", type);
		this.gpaRects=gpaRectService.queryByMap(masp1);
		List<Map> mapgpaRect = new ArrayList<Map>();
		for(GpaRect gpaRectShort: gpaRects)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("gFatherSort", gpaRectShort.getgFatherSort());
			map.put("gFatherName", gpaRectShort.getgFatherName());
			map.put("fatherSort", gpaRectShort.getFatherSort());
			map.put("fatherName", gpaRectShort.getFatherName());
			map.put("sonContent", gpaRectShort.getSonContent());
			map.put("gpaRectAdvise", gpaRectShort.getGpaRectAdvise());
			map.put("gpaRectDate", gpaRectShort.getGpaRectDate());
			mapgpaRect.add(map);
		}

		JSONArray jsonArray = JSONArray.fromObject(mapgpaRect);
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
	
	
	public List<GpaRect> getGpaRectList(){
		HttpServletRequest request = super.getRequest();
		if("1".equals(request.getParameter("flag"))){
			 List<Gpa> gpass=gpaService.querySoleDate();
			if(gpass.size()!=0){
				gpaDate=DateUtil.putDateToTimeStr19(gpass.get(0).getGpaDate());
				type="1";
			}else{
				gpaDate="";
				type="1";
			}
		}
		List<GpaRect> gpaRects2=gpaRectService.query(gpaDate.trim());//通过测评日期查询的数据
		Map map=new HashMap();
		map.put("gpaDate", gpaDate);
		map.put("gpaAssessResult", type);
		String onegpaType="";
		if("1".equals(type) || "2".equals(type)){//如果评估结果是部分符合或者不符合就比较单个评估是“否”的，否则比较单个是“不适用的”
			onegpaType="0";
		}else{
			onegpaType="2";
			map.remove("gpaAssessResult");
		}
		List<Gpa> gpas=gpaService.needConnect(map);
		List<GpaRect> list=gpaRectService.queryAllData();//通用物理源数据 
		for(Gpa gpa:gpas){
			if(!("0".equals(gpa.getGpaAssessResult()))){
				boolean flag1=false;
				String str=gpa.getGpaSort();
                 //取a
				if(gpa.getGpaA()!=null && onegpaType.equals(gpa.getGpaA())){
					//遍历整改需求汇总集合
					for(GpaRect gpaRectShort:list){
						if(gpaRectShort.getSonSort().equals(str+".a")){
							gpaRectShort.setSonContent("a)"+gpaRectShort.getSonContent());
							
	                           for(GpaRect gpaRect : gpaRects2){
	                        	   if(gpaRectShort.getSonSort().equals(gpaRect.getSonSort())){
	                        		   gpaRectShort.setGpaRectId(gpaRect.getGpaRectId());//主键
	                        		   gpaRectShort.setGpaDate(gpaRect.getGpaDate());//通用整改时间
	                        		   gpaRectShort.setGpaRectAdvise(gpaRect.getGpaRectAdvise());//整改建议
	                        		   gpaRectShort.setGpaRectDate(gpaRect.getGpaRectDate());//整改时间
	                        		   this.gpaRects.add(gpaRectShort);
	                        		   flag1=true;
	                        		   break;
	                        	   }
	                           }
	                           if(flag1==false){
	                        	   this.gpaRects.add(gpaRectShort);
	                           }
	                           flag1=false;
							break;
						}
					}
				}
				if(gpa.getGpaB()!=null && onegpaType.equals(gpa.getGpaB())){
					for(GpaRect gpaRectShort:list){
						if(gpaRectShort.getSonSort().equals(str+".b")){
							gpaRectShort.setSonContent("b)"+gpaRectShort.getSonContent());
	                           for(GpaRect gpaRect : gpaRects2){
	                        	   if(gpaRectShort.getSonSort().equals(gpaRect.getSonSort())){
	                        		   gpaRectShort.setGpaRectId(gpaRect.getGpaRectId());//主键
	                        		   gpaRectShort.setGpaDate(gpaRect.getGpaDate());//通用整改时间
	                        		   gpaRectShort.setGpaRectAdvise(gpaRect.getGpaRectAdvise());//整改建议
	                        		   gpaRectShort.setGpaRectDate(gpaRect.getGpaRectDate());//整改时间
	                        		   this.gpaRects.add(gpaRectShort);
	                        		   flag1=true;
	                        	   }
	                           }
	                           if(flag1==false){
	                        	   this.gpaRects.add(gpaRectShort);
	                           }
	                           flag1=false;
							break;
						}
					}
					
				}
				
				if(gpa.getGpaC()!=null && onegpaType.equals(gpa.getGpaC())){
					for(GpaRect gpaRectShort:list){
						if(gpaRectShort.getSonSort().equals(str+".c")){
							gpaRectShort.setSonContent("c)"+gpaRectShort.getSonContent());
	                           for(GpaRect gpaRect : gpaRects2){
	                        	   if(gpaRectShort.getSonSort().equals(gpaRect.getSonSort())){
	                        		   gpaRectShort.setGpaRectId(gpaRect.getGpaRectId());//主键
	                        		   gpaRectShort.setGpaDate(gpaRect.getGpaDate());//通用整改时间
	                        		   gpaRectShort.setGpaRectAdvise(gpaRect.getGpaRectAdvise());//整改建议
	                        		   gpaRectShort.setGpaRectDate(gpaRect.getGpaRectDate());//整改时间
	                        		   this.gpaRects.add(gpaRectShort);
	                        		   flag1=true;
	                        	   }
	                           }
	                           if(flag1==false){
	                        	   this.gpaRects.add(gpaRectShort);
	                           }
	                           flag1=false;
							break;
						}
					}
				}
				
				if(gpa.getGpaD()!=null && onegpaType.equals(gpa.getGpaD())){
					for(GpaRect gpaRectShort:list){
						if(gpaRectShort.getSonSort().equals(str+".d")){
							gpaRectShort.setSonContent("d)"+gpaRectShort.getSonContent());
	                           for(GpaRect gpaRect : gpaRects2){
	                        	   if(gpaRectShort.getSonSort().equals(gpaRect.getSonSort())){
	                        		   gpaRectShort.setGpaRectId(gpaRect.getGpaRectId());//主键
	                        		   gpaRectShort.setGpaDate(gpaRect.getGpaDate());//通用整改时间
	                        		   gpaRectShort.setGpaRectAdvise(gpaRect.getGpaRectAdvise());//整改建议
	                        		   gpaRectShort.setGpaRectDate(gpaRect.getGpaRectDate());//整改时间
	                        		   this.gpaRects.add(gpaRectShort);
	                        		   flag1=true;
	                        	   }
	                           }
	                           if(flag1==false){
	                        	   this.gpaRects.add(gpaRectShort);
	                           }
	                           flag1=false;
							break;
						}
					}
					
				}
				if(gpa.getGpaE()!=null && onegpaType.equals(gpa.getGpaE())){
					for(GpaRect gpaRectShort:list){
						if(gpaRectShort.getSonSort().equals(str+".e")){
							gpaRectShort.setSonContent("e)"+gpaRectShort.getSonContent());
	                           for(GpaRect gpaRect : gpaRects2){
	                        	   if(gpaRectShort.getSonSort().equals(gpaRect.getSonSort())){
	                        		   gpaRectShort.setGpaRectId(gpaRect.getGpaRectId());//主键
	                        		   gpaRectShort.setGpaDate(gpaRect.getGpaDate());//通用整改时间
	                        		   gpaRectShort.setGpaRectAdvise(gpaRect.getGpaRectAdvise());//整改建议
	                        		   gpaRectShort.setGpaRectDate(gpaRect.getGpaRectDate());//整改时间
	                        		   this.gpaRects.add(gpaRectShort);
	                        		   flag1=true;
	                        	   }
	                           }
	                           if(flag1==false){
	                        	   this.gpaRects.add(gpaRectShort);
	                           }
	                           flag1=false;							
							break;
						}
					}
					
				}
				
				if(gpa.getGpaF()!=null && onegpaType.equals(gpa.getGpaF())){
					for(GpaRect gpaRectShort:list){
						if(gpaRectShort.getSonSort().equals(str+".f")){
							gpaRectShort.setSonContent("f)"+gpaRectShort.getSonContent());
	                           for(GpaRect gpaRect : gpaRects2){
	                        	   if(gpaRectShort.getSonSort().equals(gpaRect.getSonSort())){
	                        		   gpaRectShort.setGpaRectId(gpaRect.getGpaRectId());//主键
	                        		   gpaRectShort.setGpaDate(gpaRect.getGpaDate());//通用整改时间
	                        		   gpaRectShort.setGpaRectAdvise(gpaRect.getGpaRectAdvise());//整改建议
	                        		   gpaRectShort.setGpaRectDate(gpaRect.getGpaRectDate());//整改时间
	                        		   this.gpaRects.add(gpaRectShort);
	                        		   flag1=true;
	                        	   }
	                           }
	                           if(flag1==false){
	                        	   this.gpaRects.add(gpaRectShort);
	                           }
	                           flag1=false;							
							break;
						}
					}
				}
				if(gpa.getGpaG()!=null && onegpaType.equals(gpa.getGpaG())){
					for(GpaRect gpaRectShort:list){
						if(gpaRectShort.getSonSort().equals(str+".g")){
							gpaRectShort.setSonContent("g)"+gpaRectShort.getSonContent());
	                           for(GpaRect gpaRect : gpaRects2){
	                        	   if(gpaRectShort.getSonSort().equals(gpaRect.getSonSort())){
	                        		   gpaRectShort.setGpaRectId(gpaRect.getGpaRectId());//主键
	                        		   gpaRectShort.setGpaDate(gpaRect.getGpaDate());//通用整改时间
	                        		   gpaRectShort.setGpaRectAdvise(gpaRect.getGpaRectAdvise());//整改建议
	                        		   gpaRectShort.setGpaRectDate(gpaRect.getGpaRectDate());//整改时间
	                        		   this.gpaRects.add(gpaRectShort);
	                        		   flag1=true;
	                        	   }
	                           }
	                           if(flag1==false){
	                        	   this.gpaRects.add(gpaRectShort);
	                           }
	                           flag1=false;	
							break;
						}
					}
				}
				if(gpa.getGpaH()!=null && onegpaType.equals(gpa.getGpaH())){
					for(GpaRect gpaRectShort:list){
						if(gpaRectShort.getSonSort().equals(str+".h")){
							gpaRectShort.setSonContent("h)"+gpaRectShort.getSonContent());
	                           for(GpaRect gpaRect : gpaRects2){
	                        	   if(gpaRectShort.getSonSort().equals(gpaRect.getSonSort())){
	                        		   gpaRectShort.setGpaRectId(gpaRect.getGpaRectId());//主键
	                        		   gpaRectShort.setGpaDate(gpaRect.getGpaDate());//通用整改时间
	                        		   gpaRectShort.setGpaRectAdvise(gpaRect.getGpaRectAdvise());//整改建议
	                        		   gpaRectShort.setGpaRectDate(gpaRect.getGpaRectDate());//整改时间
	                        		   this.gpaRects.add(gpaRectShort);
	                        		   flag1=true;
	                        	   }
	                           }
	                           if(flag1==false){
	                        	   this.gpaRects.add(gpaRectShort);
	                           }
	                           flag1=false;	
							break;
						}
					}
				}
				if(gpa.getGpaI()!=null && onegpaType.equals(gpa.getGpaI())){
					for(GpaRect gpaRectShort:list){
						if(gpaRectShort.getSonSort().equals(str+".i")){
							gpaRectShort.setSonContent("i)"+gpaRectShort.getSonContent());
	                           for(GpaRect gpaRect : gpaRects2){
	                        	   if(gpaRectShort.getSonSort().equals(gpaRect.getSonSort())){
	                        		   gpaRectShort.setGpaRectId(gpaRect.getGpaRectId());//主键
	                        		   gpaRectShort.setGpaDate(gpaRect.getGpaDate());//通用整改时间
	                        		   gpaRectShort.setGpaRectAdvise(gpaRect.getGpaRectAdvise());//整改建议
	                        		   gpaRectShort.setGpaRectDate(gpaRect.getGpaRectDate());//整改时间
	                        		   this.gpaRects.add(gpaRectShort);
	                        		   flag1=true;
	                        	   }
	                           }
	                           if(flag1==false){
	                        	   this.gpaRects.add(gpaRectShort);
	                           }
	                           flag1=false;	
							break;
						}
					}
				}
				if(gpa.getGpaJ()!=null && onegpaType.equals(gpa.getGpaJ())){
					for(GpaRect gpaRectShort:list){
						if(gpaRectShort.getSonSort().equals(str+".j")){
							gpaRectShort.setSonContent("j)"+gpaRectShort.getSonContent());
	                           for(GpaRect gpaRect : gpaRects2){
	                        	   if(gpaRectShort.getSonSort().equals(gpaRect.getSonSort())){
	                        		   gpaRectShort.setGpaRectId(gpaRect.getGpaRectId());//主键
	                        		   gpaRectShort.setGpaDate(gpaRect.getGpaDate());//通用整改时间
	                        		   gpaRectShort.setGpaRectAdvise(gpaRect.getGpaRectAdvise());//整改建议
	                        		   gpaRectShort.setGpaRectDate(gpaRect.getGpaRectDate());//整改时间
	                        		   this.gpaRects.add(gpaRectShort);
	                        		   flag1=true;
	                        	   }
	                           }
	                           if(flag1==false){
	                        	   this.gpaRects.add(gpaRectShort);
	                           }
	                           flag1=false;	
							break;
						}
					}
				}
		
			}
		}
		return this.gpaRects;
	}
	
	/**
	 * 显示通用物理整改建议列表
	 */
	@SuppressWarnings("unchecked")
	public String gpaRectList(){
		HttpServletRequest request = super.getRequest();
		gpaDate=request.getParameter("gpaDate");
		if(StringUtil.isNotBlank(gpaDate)){
			type=request.getParameter("type");
			request.setAttribute("gpaDate", gpaDate);
			request.setAttribute("type", type);
			gpaRects =getGpaRectList();
			this.getResponse().setCharacterEncoding("UTF-8");
			List<Map> mapgpaRect = new ArrayList<Map>();
			if(gpaRects.size()!=0){
				for(GpaRect gpaRect1: gpaRects)
				{
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("gpaRectId", gpaRect1.getGpaRectId());//整改建议
					map1.put("gpaRectAdvise", gpaRect1.getGpaRectAdvise());//整改建议
					map1.put("gpaRectDate", gpaRect1.getGpaRectDate());//整改时间
					map1.put("gFatherSort", gpaRect1.getgFatherSort());//祖父排序
					map1.put("gFatherName",gpaRect1.getgFatherName());//祖父名称
					map1.put("fatherSort", gpaRect1.getFatherSort());//父亲排序+父亲名称
					map1.put("fatherName",gpaRect1.getFatherName());//父亲排序+父亲名称
					map1.put("sonSort", gpaRect1.getSonSort());//儿子排序
					map1.put("sonContent", gpaRect1.getSonContent());//儿子内容
					mapgpaRect.add(map1);
				}
		    }
			PageModel pm = new PageModel(mapgpaRect, 15);
			// 处理数据分页的起始条数
					String startIndex = request.getParameter("page");
					if (StringUtil.isNotBlank(startIndex)) {
						mapgpaRect =pm.getObjectLists(Integer.valueOf(startIndex)); 
					} else {
						mapgpaRect =pm.getObjectLists(1); 
					}
					request.setAttribute("Page", pm);
					request.setAttribute("mapgpaRect", mapgpaRect);
		}
		
	 
		
				
		/*JSONArray jsonArray = JSONArray.fromObject(mapgpaRect);
		JSONObject jsonObject = JSONObject.fromObject(pm);*/
		// Ajax返回
	/*	try {
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
	
	String	gpaDateUpdate;
	String typeUpdate;
	

	public String gpaRectUpdate(){
		HttpServletRequest request = super.getRequest();
		
		String str1=request.getParameter("gpaRectId");
		gpaDateUpdate=request.getParameter("gpaDateUpdate");
		typeUpdate=request.getParameter("typeUpdate");
		int gpaRectId=Integer.parseInt(str1);
		String gpaRectAdvise=request.getParameter("gpaRectAdvise");
		try {
			gpaRectAdvise=java.net.URLDecoder.decode(gpaRectAdvise, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.gpaRect.setGpaRectId(gpaRectId);
		this.gpaRect.setGpaRectAdvise(gpaRectAdvise);
		this.gpaRect.setGpaRectDate(DateUtil.curDateTimeStr19());
		gpaRectService.update(gpaRect);
	 
		return SUCCESS;
	}
	
	/**
	 * 整改建议插入
	 * @return
	 */
	public String gpaRectInsert(){
		HttpServletRequest request = super.getRequest();
		gpaDateinsert=request.getParameter("gpaDate");
		typeinsert=request.getParameter("type");
        this.gpaRect.setGpaRectDate(DateUtil.curDateTimeStr19());
		gpaRectService.insert(gpaRect);
	/*	Audit audit=new Audit();
		audit.setDetailed(gpaDateinsert);
		audit.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
		audit.setObject("通用物理安全整改建议");
		audit.setTime(DateUtil.curDateTimeStr19());
		audit.setType("添加");
		auditService.instert(audit);*/
		return SUCCESS;
	}
	
	/**
	 * 整改建议删除
	 * @return
	 */
	String gpaDateDelete;
	String typeDelete;
	
	public String gpaRectDelete(){
		HttpServletRequest request = super.getRequest();
		String str=request.getParameter("gpaRectId");
		
		gpaDateDelete=request.getParameter("gpaDateDelete");
		typeDelete=request.getParameter("typeDelete");
		int gpaRectId=Integer.parseInt(str);
		gpaRectService.delete(gpaRectId);
		/*Audit audit=new Audit();
		audit.setDetailed(gpaDateDelete);
		audit.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
		audit.setObject("通用物理安全整改建议");
		audit.setTime(DateUtil.curDateTimeStr19());
		audit.setType("删除");
		auditService.instert(audit);*/
		return SUCCESS;
	}
	
	public void gpaRectDocCreate(){
		HttpServletRequest request = super.getRequest();
		HttpServletResponse  response=super.getResponse();
		gpaDate=request.getParameter("gpaDate");
		
		type="1";
		Map map1=new HashMap();
		map1.put("gpaDate", gpaDate);
		map1.put("gpaAssessResult", type);
		List<GpaRect> gpaRectList1=getGpaRectList();
		
		gpaRects=new ArrayList<GpaRect>();
		type="2";
		Map map2=new HashMap();
		map2.put("gpaDate", gpaDate);
		map2.put("gpaAssessResult", type);
		List<GpaRect> gpaRectList2=getGpaRectList();
		GpaRectDocCreate.createGpaRectDoc(response,gpaRectList1.size(),gpaRectList2.size(),gpaRectList1,gpaRectList2);
	/*	Audit audit=new Audit();
		audit.setDetailed(gpaDate);
		audit.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
		audit.setObject("通用物理安全整改建议");
		audit.setTime(DateUtil.curDateTimeStr19());
		audit.setType("导出报表");
		auditService.instert(audit);*/
		return ;
	}
	
	/*导出excel表格*/
	public void gapexportexcel(){
		HttpServletRequest request = super.getRequest();
		String gpaDate=request.getParameter("gpaDate");
		String type=request.getParameter("type");
		Map<String,String> masp1=new HashMap();
		masp1.put("gpaDate", gpaDate);
		masp1.put("gpaAssessResult", type);
		this.gpaRects=gpaRectService.queryByMap(masp1);
		//System.out.println(gpaRects.size());
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
			HSSFSheet sheet = wb.createSheet("sheet1");
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
			cellheader.setCellValue(this.gpaRect.getGpaDate()+"整改建议对比");

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
			
			for (int i = 0; i < this.gpaRects.size(); i++)
			{
				gpaRect = (GpaRect) this.gpaRects.get(i);
				
				HSSFRow row=sheet.createRow((short) (i+2));
					HSSFCell cell2 = row.createCell((short) 0);
					cell2.setCellValue(gpaRect.getgFatherSort()+gpaRect.getgFatherName());
					cell2.setCellStyle(style);
					cell2=row.createCell((short) 1);
					cell2.setCellStyle(style);
					cell2.setCellValue(gpaRect.getFatherSort()+gpaRect.getFatherName());
					cell2=row.createCell((short) 2);
					cell2.setCellStyle(stylewrap);
					cell2.setCellValue(gpaRect.getSonContent());
					cell2=row.createCell((short) 3);
					cell2.setCellStyle(stylewrap);
					cell2.setCellValue(gpaRect.getGpaRectAdvise());
					cell2=row.createCell((short) 4);
					cell2.setCellStyle(style);
					cell2.setCellValue(gpaRect.getGpaRectDate());
				
							
			}
		
			// 第六步，将文件存到指定位置
			HttpServletResponse response = null;
		
			OutputStream out = null;
		
			try {
				response = ServletActionContext.getResponse();
				
				out = response.getOutputStream();
				//生成文档名称
				String doc = DateUtil.curDateTimeStr14()+"通用物理整改建议";
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
					audit.setDetailed(gpaDate);
					audit.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
					audit.setObject("通用物理历史整改需求");
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
	

	public GpaRect getModel() {
		return gpaRect;
	}

	public GpaRect getGpaRect() {
		return gpaRect;
	}

	public void setGpaRect(GpaRect gpaRect) {
		this.gpaRect = gpaRect;
	}

	public GpaRectService getGpaRectService() {
		return gpaRectService;
	}

	public void setGpaRectService(GpaRectService gpaRectService) {
		this.gpaRectService = gpaRectService;
	}


	public List<GpaRect> getGpaRects() {
		return gpaRects;
	}


	public void setGpaRects(List<GpaRect> gpaRects) {
		this.gpaRects = gpaRects;
	}


	public String getGpaDate() {
		return gpaDate;
	}


	public void setGpaDate(String gpaDate) {
		this.gpaDate = gpaDate;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public GpaService getGpaService() {
		return gpaService;
	}


	public void setGpaService(GpaService gpaService) {
		this.gpaService = gpaService;
	}


	public List<Gpa> getGpas() {
		return gpas;
	}


	public void setGpas(List<Gpa> gpas) {
		this.gpas = gpas;
	}
	public String getGpaDateUpdate() {
		return gpaDateUpdate;
	}


	public void setGpaDateUpdate(String gpaDateUpdate) {
		this.gpaDateUpdate = gpaDateUpdate;
	}


	public String getTypeUpdate() {
		return typeUpdate;
	}


	public void setTypeUpdate(String typeUpdate) {
		this.typeUpdate = typeUpdate;
	}

	

	public String getGpaDateDelete() {
		return gpaDateDelete;
	}


	public void setGpaDateDelete(String gpaDateDelete) {
		this.gpaDateDelete = gpaDateDelete;
	}


	public String getTypeDelete() {
		return typeDelete;
	}


	public void setTypeDelete(String typeDelete) {
		this.typeDelete = typeDelete;
	}

	

}
