package com.compliance.webapp.action.cpManage.msaShow;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.compliance.model.cpManage.gpaShow.Gpa;
import com.compliance.model.cpManage.msaShow.Msa;
import com.compliance.model.cpManage.msaShow.MsaShow;
import com.compliance.service.cpManage.msaShow.MsaService;
import com.compliance.service.cpManage.msaShow.MsaShowService;
import com.compliance.webapp.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.PageModel;
import com.util.page.SearchResult;

/**
 * 通用管理安全测评整改需求汇总action
 * @author quyongkun
 *
 */
public class MsaShowAction extends BaseAction implements ModelDriven<MsaShow> {
	
	/**
	 * 通用管理安全测评业务接口
	 */
	private MsaService msaService;
	
	/**
	 * 通用管理安全测评整改需求汇总业务接口
	 */
	private MsaShowService msaShowService;
	
	/**
	 * 通用管理安全测评整改需求汇总集合
	 */
	private List<MsaShow> msaShows=new ArrayList<MsaShow>();
	
	/**
	 * 通用管理安全测评整改需求汇总
	 */
	private MsaShow msaShow=new MsaShow();
	
	/**
	 * 通用管理安全测评集合
	 */
	private List<Msa> msas;
	
	/**
	 * 测评时间 例 1996-12-02
	 */
	private String msaDate;
	
	/**
	 * 测评类型 
	 */
	private String type;
	


	public MsaShow getModel() {
		return msaShow;
	}
	
	/**
	 * 查询不同通用管理测评
	 * @return
	 */
	public String querySoleDate(){
		msas=msaService.querySoleDate();

		return SUCCESS;
	}
	
	/**
	 * 查询通用管理安全测评列表
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
		for(Msa msa: msas)
		{
			
			if(msa.getMsaDate()!=null){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("msaDate", DateUtil.putDateToTimeStr19(msa.getMsaDate()));
				mapMsa.add(map);
			}

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
	 * 部分或不符合或不适用整改需求汇总
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String msaShowNoFit(){
		HttpServletRequest request = super.getRequest();
		msaDate=request.getParameter("msaDate");
		type=request.getParameter("type");
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
		Map map=new HashMap();
		map.put("msaDate", msaDate);
		map.put("msaAssessResult", type);
		String oneMSaType="";
		if("1".equals(type) || "2".equals(type)){//如果评估结果是部分符合或者不符合就比较单个评估是“否”的，否则比较单个是“不适用的”
			oneMSaType="0";
		}else{
			oneMSaType="2";
			map.remove("msaAssessResult");
		}

		List<Msa> msas=msaService.needConnect(map);
		List<MsaShow> list=msaShowService.queryAllData();
		String msaAssessResult=""; 
		for(Msa msa:msas){
			if("1".equals(type)||"2".equals(type)){
				msaAssessResult=msa.getMsaAssessResult();
			}else{
				msaAssessResult="3";
			}
			if(type.equals(msaAssessResult)){
				boolean flag=false;
				MsaShow msaShow1=null;
				String str=msa.getMsaSort();
                 //取a
				if(msa.getMsaA()!=null && oneMSaType.equals(msa.getMsaA())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".a")){
							msaShowShort.setSonContent("a)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}
				
				//取b
				if(msa.getMsaB()!=null && oneMSaType.equals(msa.getMsaB())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".b")){
							msaShowShort.setSonContent("b)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"<br>"+msaShowShort.getSonContent());
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}
				
				//取c
				if(msa.getMsaC()!=null && oneMSaType.equals(msa.getMsaC())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".c")){
							msaShowShort.setSonContent("c)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"<br>"+msaShowShort.getSonContent());
							}							
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}				
				
				//取d
				if(msa.getMsaD()!=null && oneMSaType.equals(msa.getMsaD())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".d")){
							msaShowShort.setSonContent("d)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"<br>"+msaShowShort.getSonContent());
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}
				
				//取e
				if(msa.getMsaE()!=null && oneMSaType.equals(msa.getMsaE())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".e")){
							msaShowShort.setSonContent("e)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"<br>"+msaShowShort.getSonContent());
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}
				
				//取f
				if(msa.getMsaF()!=null && oneMSaType.equals(msa.getMsaF())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".f")){
							msaShowShort.setSonContent("f)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"<br>"+msaShowShort.getSonContent());
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}
				
				//取g
				if(msa.getMsaG()!=null && oneMSaType.equals(msa.getMsaG())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".g")){
							msaShowShort.setSonContent("g)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"<br>"+msaShowShort.getSonContent());
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}				
				
				//取h
				if(msa.getMsaH()!=null && oneMSaType.equals(msa.getMsaH())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".h")){
							msaShowShort.setSonContent("h)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"\n"+msaShowShort.getSonContent());
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}
				
				//取i
				if(msa.getMsaI()!=null && oneMSaType.equals(msa.getMsaI())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".i")){
							msaShowShort.setSonContent("i)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"\n"+msaShowShort.getSonContent());
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}
				
				//取j
				if(msa.getMsaJ()!=null && oneMSaType.equals(msa.getMsaJ())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".j")){
							msaShowShort.setSonContent("j)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"<br>"+msaShowShort.getSonContent());
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}	
				if(msaShow1!=null){
					this.msaShows.add(msaShow1);
				}
			}
		}
		Page page = null;
		SearchResult sr = new SearchResult();
		this.getResponse().setCharacterEncoding("UTF-8");
		List<Map> mapMsaShow = new ArrayList<Map>();
		if(msaShows.size()!=0){
			for(MsaShow msaShow1: msaShows)
			{
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("g", msaShow1.getgFatherSort()+" "+msaShow1.getgFatherName());
				map1.put("f", msaShow1.getFatherSort() +" "+msaShow1.getFatherName());
				map1.put("s", msaShow1.getSonContent());
				mapMsaShow.add(map1);
			}
		}
		PageModel pm = new PageModel(mapMsaShow, 15);
		// 处理数据分页的起始条数
				String startIndex = request.getParameter("page");
				if (StringUtil.isNotBlank(startIndex)) {
					mapMsaShow =pm.getObjectLists(Integer.valueOf(startIndex)); 
				} else {
					mapMsaShow =pm.getObjectLists(1); 
				}
				request.setAttribute("mapMsaShow", mapMsaShow);
				request.setAttribute("Page", pm);
/*
		JSONArray jsonArray = JSONArray.fromObject(mapMsaShow);
		JSONObject jsonObject = JSONObject.fromObject(sr.getPage());
		// Ajax返回
		try {
			getResponse().getWriter().write(
					"{\"processLog\":" + jsonArray.toString() + ",\"page\":"+ jsonObject.toString() + "}");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		return SUCCESS;
	}

	public String msaShowNoFitFirst(){
		HttpServletRequest request = super.getRequest();
		List<Msa>msass=msaService.querySoleDate();
		if(msass.size()!=0){
			request.setAttribute("msaDateFirst",DateUtil.putDateToTimeStr19(msass.get(0).getMsaDate()));
			request.setAttribute("typeFirst", "1");
		}
		
		return SUCCESS;
	}
	
	
	/**
	 * 模糊查询
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public void   queryNotKeyAjaxMsaShowNoFit() throws UnsupportedEncodingException{
		HttpServletRequest request = super.getRequest();
		msaDate=request.getParameter("msaDate");
		type=request.getParameter("type");
		
		
		Page page = null;
		// 处理数据分页的起始条数
				String startIndex = request.getParameter("startIndex");
				if (StringUtil.isNotBlank(startIndex)) {
					page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
				} else {
					page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
				}
				
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
		Map map=new HashMap();
		map.put("msaDate", msaDate);
		map.put("msaAssessResult", type);
		String oneMSaType="";
		if("1".equals(type) || "2".equals(type)){//如果评估结果是部分符合或者不符合就比较单个评估是“否”的，否则比较单个是“不适用的”
			oneMSaType="0";
		}else{
			oneMSaType="2";
			map.remove("msaAssessResult");
		}

		List<Msa> msas=msaService.needConnect(map);
		List<MsaShow> list=msaShowService.queryAllData();
		String msaAssessResult=""; 
		for(Msa msa:msas){
			if("1".equals(type)||"2".equals(type)){
				msaAssessResult=msa.getMsaAssessResult();
			}else{
				msaAssessResult="3";
			}
			if(type.equals(msaAssessResult)){
				boolean flag=false;
				MsaShow msaShow1=null;
				String str=msa.getMsaSort();
                 //取a
				if(msa.getMsaA()!=null && oneMSaType.equals(msa.getMsaA())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".a")){
							msaShowShort.setSonContent("a)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}
				
				//取b
				if(msa.getMsaB()!=null && oneMSaType.equals(msa.getMsaB())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".b")){
							msaShowShort.setSonContent("b)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"<br>"+msaShowShort.getSonContent());
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}
				
				//取c
				if(msa.getMsaC()!=null && oneMSaType.equals(msa.getMsaC())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".c")){
							msaShowShort.setSonContent("c)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"<br>"+msaShowShort.getSonContent());
							}							
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}				
				
				//取d
				if(msa.getMsaD()!=null && oneMSaType.equals(msa.getMsaD())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".d")){
							msaShowShort.setSonContent("d)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"<br>"+msaShowShort.getSonContent());
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}
				
				//取e
				if(msa.getMsaE()!=null && oneMSaType.equals(msa.getMsaE())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".e")){
							msaShowShort.setSonContent("e)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"<br>"+msaShowShort.getSonContent());
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}
				
				//取f
				if(msa.getMsaF()!=null && oneMSaType.equals(msa.getMsaF())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".f")){
							msaShowShort.setSonContent("f)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"<br>"+msaShowShort.getSonContent());
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}
				
				//取g
				if(msa.getMsaG()!=null && oneMSaType.equals(msa.getMsaG())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".g")){
							msaShowShort.setSonContent("g)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"<br>"+msaShowShort.getSonContent());
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}				
				
				//取h
				if(msa.getMsaH()!=null && oneMSaType.equals(msa.getMsaH())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".h")){
							msaShowShort.setSonContent("h)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"\n"+msaShowShort.getSonContent());
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}
				
				//取i
				if(msa.getMsaI()!=null && oneMSaType.equals(msa.getMsaI())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".i")){
							msaShowShort.setSonContent("i)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"\n"+msaShowShort.getSonContent());
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}
				
				//取j
				if(msa.getMsaJ()!=null && oneMSaType.equals(msa.getMsaJ())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".j")){
							msaShowShort.setSonContent("j)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"<br>"+msaShowShort.getSonContent());
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}	
				if(msaShow1!=null){
					this.msaShows.add(msaShow1);
				}
			}
		}
		this.getResponse().setCharacterEncoding("UTF-8");
		List<Map> mapMsaShow = new ArrayList<Map>();
		List<Map> mapMsaShowNotKey = new ArrayList<Map>();
		if(msaShows.size()!=0){
			for(MsaShow msaShow1: msaShows)
			{
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("g", msaShow1.getgFatherSort()+" "+msaShow1.getgFatherName());
					map1.put("f", msaShow1.getFatherSort() +" "+msaShow1.getFatherName());
					map1.put("s", msaShow1.getSonContent());
					mapMsaShow.add(map1);
			}
		}
				request.setAttribute("mapMsaShow", mapMsaShow);
				
				// 处理分页
				SearchResult sr = new SearchResult();
				
				for (int i = 0; i < mapMsaShow.size(); i++) {
					
					if((page.getStartIndex()<=i)&&(mapMsaShow.get(i)!=null)&&(mapMsaShowNotKey.size()<page.getPageSize())){
						mapMsaShowNotKey.add(mapMsaShow.get(i));
					}
				}
				sr.setList(mapMsaShowNotKey);
				page.setTotalCount(mapMsaShow.size());
				sr.setPage(page);
				
				JSONArray jsonArray = JSONArray.fromObject(sr.getList());
				JSONObject jsonObject = JSONObject.fromObject(sr.getPage());
				// Ajax返回
				try {
					//System.out.println("{\"mapMsaShows\":" + jsonArray.toString() + ",\"page\":" + jsonObject.toString() + "}");
					getResponse().getWriter().write("{\"mapMsaShows\":" + jsonArray.toString() + ",\"page\":" + jsonObject.toString() + "}");
				} catch (IOException e) {
					e.printStackTrace();
				}
/*
		JSONArray jsonArray = JSONArray.fromObject(mapMsaShow);
		JSONObject jsonObject = JSONObject.fromObject(sr.getPage());
		// Ajax返回
		try {
			getResponse().getWriter().write(
					"{\"processLog\":" + jsonArray.toString() + ",\"page\":"+ jsonObject.toString() + "}");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		return ;
	}

	
	/**
	 * 模糊查询
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public void queryAjaxMsaShowNoFit() throws UnsupportedEncodingException{
		HttpServletRequest request = super.getRequest();
		msaDate=request.getParameter("msaDate");
		type=request.getParameter("type");
		
		Page page = null;
		// 处理数据分页的起始条数
				String startIndex = request.getParameter("startIndex");
				if (StringUtil.isNotBlank(startIndex)) {
					page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
				} else {
					page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
				}
		String key=java.net.URLDecoder.decode(request.getParameter("keyword"), "UTF-8");
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
		Map map=new HashMap();
		map.put("msaDate", msaDate);
		map.put("msaAssessResult", type);
		String oneMSaType="";
		if("1".equals(type) || "2".equals(type)){//如果评估结果是部分符合或者不符合就比较单个评估是“否”的，否则比较单个是“不适用的”
			oneMSaType="0";
		}else{
			oneMSaType="2";
			map.remove("msaAssessResult");
		}

		List<Msa> msas=msaService.needConnect(map);
		List<MsaShow> list=msaShowService.queryAllData();
		String msaAssessResult=""; 
		for(Msa msa:msas){
			if("1".equals(type)||"2".equals(type)){
				msaAssessResult=msa.getMsaAssessResult();
			}else{
				msaAssessResult="3";
			}
			if(type.equals(msaAssessResult)){
				boolean flag=false;
				MsaShow msaShow1=null;
				String str=msa.getMsaSort();
                 //取a
				if(msa.getMsaA()!=null && oneMSaType.equals(msa.getMsaA())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".a")){
							msaShowShort.setSonContent("a)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}
				
				//取b
				if(msa.getMsaB()!=null && oneMSaType.equals(msa.getMsaB())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".b")){
							msaShowShort.setSonContent("b)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"<br>"+msaShowShort.getSonContent());
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}
				
				//取c
				if(msa.getMsaC()!=null && oneMSaType.equals(msa.getMsaC())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".c")){
							msaShowShort.setSonContent("c)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"<br>"+msaShowShort.getSonContent());
							}							
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}				
				
				//取d
				if(msa.getMsaD()!=null && oneMSaType.equals(msa.getMsaD())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".d")){
							msaShowShort.setSonContent("d)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"<br>"+msaShowShort.getSonContent());
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}
				
				//取e
				if(msa.getMsaE()!=null && oneMSaType.equals(msa.getMsaE())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".e")){
							msaShowShort.setSonContent("e)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"<br>"+msaShowShort.getSonContent());
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}
				
				//取f
				if(msa.getMsaF()!=null && oneMSaType.equals(msa.getMsaF())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".f")){
							msaShowShort.setSonContent("f)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"<br>"+msaShowShort.getSonContent());
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}
				
				//取g
				if(msa.getMsaG()!=null && oneMSaType.equals(msa.getMsaG())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".g")){
							msaShowShort.setSonContent("g)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"<br>"+msaShowShort.getSonContent());
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}				
				
				//取h
				if(msa.getMsaH()!=null && oneMSaType.equals(msa.getMsaH())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".h")){
							msaShowShort.setSonContent("h)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"\n"+msaShowShort.getSonContent());
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}
				
				//取i
				if(msa.getMsaI()!=null && oneMSaType.equals(msa.getMsaI())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".i")){
							msaShowShort.setSonContent("i)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"\n"+msaShowShort.getSonContent());
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}
				
				//取j
				if(msa.getMsaJ()!=null && oneMSaType.equals(msa.getMsaJ())){
					//遍历整改需求汇总集合
					for(MsaShow msaShowShort:list){
						if(msaShowShort.getSonSort().equals(str+".j")){
							msaShowShort.setSonContent("j)"+msaShowShort.getSonContent());
							if(msaShow1==null){
								msaShow1=msaShowShort;
							}else{
								msaShow1.setSonContent(msaShow1.getSonContent()+"<br>"+msaShowShort.getSonContent());
							}
							//this.msaShows.add(msaShowShort);
							break;
						}
					}
				}	
				if(msaShow1!=null){
					this.msaShows.add(msaShow1);
				}
			}
		}
		this.getResponse().setCharacterEncoding("UTF-8");
		List<Map> mapMsaShow = new ArrayList<Map>();
		List<Map> mapMsaShowNotKey = new ArrayList<Map>();
		
		if(msaShows.size()!=0){
			for(MsaShow msaShow1: msaShows)
			{
 
				
				String a=msaShow1.getgFatherSort();
				String b=msaShow1.getgFatherName();
				String c=msaShow1.getFatherSort();
				String d=msaShow1.getFatherName();
				String f=msaShow1.getSonContent();
				if((a+" "+b).contains(key)){
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("g", a+" "+msaShow1.getgFatherName());
					map1.put("f", msaShow1.getFatherSort() +" "+msaShow1.getFatherName());
					map1.put("s", msaShow1.getSonContent());
					mapMsaShow.add(map1);
				}else
				if((b+" "+c).contains(key)){
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("g", a+" "+msaShow1.getgFatherName());
					map1.put("f", msaShow1.getFatherSort() +" "+msaShow1.getFatherName());
					map1.put("s", msaShow1.getSonContent());
					mapMsaShow.add(map1);
				}else
				if(a.contains(key)){
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("g", a+" "+msaShow1.getgFatherName());
					map1.put("f", msaShow1.getFatherSort() +" "+msaShow1.getFatherName());
					map1.put("s", msaShow1.getSonContent());
					mapMsaShow.add(map1);
				}else
				if(b.contains(key)){
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("g", msaShow1.getgFatherSort()+" "+b);
					map1.put("f", msaShow1.getFatherSort() +" "+msaShow1.getFatherName());
					map1.put("s", msaShow1.getSonContent());
					mapMsaShow.add(map1);
				}else
				if(c.contains(key)){
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("g", msaShow1.getgFatherSort()+" "+msaShow1.getgFatherName());
					map1.put("f", c +" "+msaShow1.getFatherName());
					map1.put("s", msaShow1.getSonContent());
					mapMsaShow.add(map1);
				}else
				if(d.contains(key)){
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("g", msaShow1.getgFatherSort()+" "+msaShow1.getgFatherName());
					map1.put("f", msaShow1.getFatherSort() +" "+d);
					map1.put("s", msaShow1.getSonContent());
					mapMsaShow.add(map1);
				}else
				if(f.contains(key)){
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("g", msaShow1.getgFatherSort()+" "+msaShow1.getgFatherName());
					map1.put("f", msaShow1.getFatherSort() +" "+msaShow1.getFatherName());
					map1.put("s", f);
					mapMsaShow.add(map1);
				}
			}
		}
				request.setAttribute("mapMsaShow", mapMsaShow);
				

				// 处理分页
				SearchResult sr = new SearchResult();
				
				for (int i = 0; i < mapMsaShow.size(); i++) {
					
					if((page.getStartIndex()<=i)&&(mapMsaShow.get(i)!=null)&&(mapMsaShowNotKey.size()<page.getPageSize())){
						mapMsaShowNotKey.add(mapMsaShow.get(i));
					}
				}
				sr.setList(mapMsaShowNotKey);
				page.setTotalCount(mapMsaShow.size());
				sr.setPage(page);
				
				JSONArray jsonArray = JSONArray.fromObject(sr.getList());
				JSONObject jsonObject = JSONObject.fromObject(sr.getPage());
				// Ajax返回
				try {
					//System.out.println("{\"mapMsaShows\":" + jsonArray.toString() + ",\"page\":" + jsonObject.toString() + "}");
					getResponse().getWriter().write("{\"mapMsaShows\":" + jsonArray.toString() + ",\"page\":" + jsonObject.toString() + "}");
				} catch (IOException e) {
					e.printStackTrace();
				}
/*
		JSONArray jsonArray = JSONArray.fromObject(mapMsaShow);
		JSONObject jsonObject = JSONObject.fromObject(sr.getPage());
		// Ajax返回
		try {
			getResponse().getWriter().write(
					"{\"processLog\":" + jsonArray.toString() + ",\"page\":"+ jsonObject.toString() + "}");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		return ;
	}

	
	/**
	 * 显示部分符合整改需求汇总
	 * @return
	 */
	public String msaShowPartFit(){
	     return SUCCESS;
	}
	
	
	public List<MsaShow> getMsaShows() {
		return msaShows;
	}


	public void setMsaShows(List<MsaShow> msaShows) {
		this.msaShows = msaShows;
	}


	public MsaService getMsaService() {
		return msaService;
	}

	public void setMsaService(MsaService msaService) {
		this.msaService = msaService;
	}

	public MsaShowService getMsaShowService() {
		return msaShowService;
	}

	public void setMsaShowService(MsaShowService msaShowService) {
		this.msaShowService = msaShowService;
	}

	public MsaShow getMsaShow() {
		return msaShow;
	}

	public void setMsaShow(MsaShow msaShow) {
		this.msaShow = msaShow;
	}

	public List<Msa> getMsas() {
		return msas;
	}

	public void setMsas(List<Msa> msas) {
		this.msas = msas;
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

	


	
	
}
