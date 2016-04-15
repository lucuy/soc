package com.compliance.webapp.action.cpManage.gpaShow;



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
import com.compliance.model.cpManage.gpaShow.GpaShow;
import com.compliance.model.rank.Record;
import com.compliance.service.cpManage.gpaShow.GpaService;
import com.compliance.service.cpManage.gpaShow.GpaShowService;
import com.compliance.webapp.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.PageModel;
import com.util.page.SearchResult;

/**
 * 通用物理测评整改需求汇总action
 * @author quyongkun
 *
 */
public class GpaShowAction extends BaseAction implements ModelDriven<GpaShow> {
	
	/**
	 * 通用物理测评业务接口
	 */
	private GpaService gpaService;
	
	/**
	 * 通用物理测评整改需求汇总业务接口
	 */
	private GpaShowService gpaShowService;
	
	/**
	 * 通用物理测评整改需求汇总集合
	 */
	private List<GpaShow> gpaShows=new ArrayList<GpaShow>();
	
	/**
	 * 通用物理测评整改需求汇总
	 */
	private GpaShow gpaShow=new GpaShow();
	
	/**
	 * 通用物理测评集合
	 */
	private List<Gpa> gpas;
	
	/**
	 * 测评时间 例 1996-12-02
	 */
	private String gpaDate;
	
	/**
	 * 测评类型 
	 */
	private String type;
	


	/**
	 * 查询不同通用管理测评
	 * @return
	 */
	public String querySoleDate(){
		gpas=gpaService.querySoleDate();

		return SUCCESS;
	}
	
	/**
	 * 查询通用物理测评列表
	 */
	public void queryTree(){

		HttpServletRequest request = super.getRequest();
		this.getResponse().setCharacterEncoding("UTF-8");
		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		// 接受查询条件
		this.gpas=gpaService.querySoleDate();
		List<Map> mapgpa = new ArrayList<Map>();
		for(Gpa gpa: gpas)
		{
			if(gpa.getGpaDate()!=null){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("gpaDate", DateUtil.putDateToTimeStr19(gpa.getGpaDate()));
				mapgpa.add(map);	
			}

		}

		JSONArray jsonArray = JSONArray.fromObject(mapgpa);
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
	 * 部分或不符合或不适用整改需求汇总
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String  gpaShowNoFit(){
		HttpServletRequest request = super.getRequest();
		gpaDate=request.getParameter("gpaDate");
		type=request.getParameter("type");
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
		List<GpaShow> list=gpaShowService.queryAllData();
		for(Gpa gpa:gpas){
			if(!("0".equals(gpa.getGpaAssessResult()))){
				boolean flag=false;
				GpaShow gpaShow1=null;
				String str=gpa.getGpaSort();
                 //取a
				if(gpa.getGpaA()!=null && onegpaType.equals(gpa.getGpaA())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".a")){
							gpaShowShort.setSonContent("a)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}
				
				//取b
				if(gpa.getGpaB()!=null && onegpaType.equals(gpa.getGpaB())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".b")){
							gpaShowShort.setSonContent("b)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"<br>"+gpaShowShort.getSonContent());
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}
				
				//取c
				if(gpa.getGpaC()!=null && onegpaType.equals(gpa.getGpaC())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".c")){
							gpaShowShort.setSonContent("c)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"<br>"+gpaShowShort.getSonContent());
							}							
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}				
				
				//取d
				if(gpa.getGpaD()!=null && onegpaType.equals(gpa.getGpaD())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".d")){
							gpaShowShort.setSonContent("d)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"<br>"+gpaShowShort.getSonContent());
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}
				
				//取e
				if(gpa.getGpaE()!=null && onegpaType.equals(gpa.getGpaE())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".e")){
							gpaShowShort.setSonContent("e)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"<br>"+gpaShowShort.getSonContent());
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}
				
				//取f
				if(gpa.getGpaF()!=null && onegpaType.equals(gpa.getGpaF())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".f")){
							gpaShowShort.setSonContent("f)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"<br>"+gpaShowShort.getSonContent());
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}
				
				//取g
				if(gpa.getGpaG()!=null && onegpaType.equals(gpa.getGpaG())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".g")){
							gpaShowShort.setSonContent("g)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"<br>"+gpaShowShort.getSonContent());
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}				
				
				//取h
				if(gpa.getGpaH()!=null && onegpaType.equals(gpa.getGpaH())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".h")){
							gpaShowShort.setSonContent("h)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"\n"+gpaShowShort.getSonContent());
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}
				
				//取i
				if(gpa.getGpaI()!=null && onegpaType.equals(gpa.getGpaI())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".i")){
							gpaShowShort.setSonContent("i)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"\n"+gpaShowShort.getSonContent());
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}
				//取j
				if(gpa.getGpaJ()!=null && onegpaType.equals(gpa.getGpaJ())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".j")){
							gpaShowShort.setSonContent("j)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"<br>"+gpaShowShort.getSonContent());
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}	
				if(gpaShow1!=null){
					this.gpaShows.add(gpaShow1);
				}
			}
		}
		Page page = null;
		this.getResponse().setCharacterEncoding("UTF-8");
		List<Map> mapgpaShow = new ArrayList<Map>();
		if(gpaShows.size()!=0){
			for(GpaShow gpaShow1: gpaShows)
			{
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("g", gpaShow1.getgFatherSort()+" "+gpaShow1.getgFatherName());
				map1.put("f", gpaShow1.getFatherSort() +" "+gpaShow1.getFatherName());
				map1.put("s", gpaShow1.getSonContent());
				mapgpaShow.add(map1);
			}
		}
		
		PageModel pm = new PageModel(mapgpaShow, 15);
		// 处理数据分页的起始条数
				String startIndex = request.getParameter("page");
				if (StringUtil.isNotBlank(startIndex)) {
					mapgpaShow =pm.getObjectLists(Integer.valueOf(startIndex)); 
				} else {
					mapgpaShow =pm.getObjectLists(1); 
				}
				request.setAttribute("mapgpaShow", mapgpaShow);
				request.setAttribute("Page", pm);
		return SUCCESS;
	}
	
	
	public String gpaShowNoFitFirst(){
		HttpServletRequest request = super.getRequest();
		List<Gpa>gpass=gpaService.querySoleDate();
		if(gpass.size()!=0){
			request.setAttribute("gpaDateFirst",DateUtil.putDateToTimeStr19(gpass.get(0).getGpaDate()));
			request.setAttribute("typeFirst", "1");
		}
		
		return SUCCESS;
	}
	
	
	/**
	 * 部分或不符合或不适用整改需求汇总
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public void  queryNotKeyAjaxGpaShowNoFit() throws UnsupportedEncodingException{
		HttpServletRequest request = super.getRequest();
		
		Page page = null;
		// 处理数据分页的起始条数
				String startIndex = request.getParameter("startIndex");
				if (StringUtil.isNotBlank(startIndex)) {
					page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
				} else {
					page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
				}
		gpaDate=request.getParameter("gpaDate");
		type=request.getParameter("type");
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
		List<GpaShow> list=gpaShowService.queryAllData();
		for(Gpa gpa:gpas){
			if(!("0".equals(gpa.getGpaAssessResult()))){
				boolean flag=false;
				GpaShow gpaShow1=null;
				String str=gpa.getGpaSort();
                 //取a
				if(gpa.getGpaA()!=null && onegpaType.equals(gpa.getGpaA())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".a")){
							gpaShowShort.setSonContent("a)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}
				
				//取b
				if(gpa.getGpaB()!=null && onegpaType.equals(gpa.getGpaB())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".b")){
							gpaShowShort.setSonContent("b)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"<br>"+gpaShowShort.getSonContent());
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}
				
				//取c
				if(gpa.getGpaC()!=null && onegpaType.equals(gpa.getGpaC())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".c")){
							gpaShowShort.setSonContent("c)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"<br>"+gpaShowShort.getSonContent());
							}							
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}				
				
				//取d
				if(gpa.getGpaD()!=null && onegpaType.equals(gpa.getGpaD())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".d")){
							gpaShowShort.setSonContent("d)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"<br>"+gpaShowShort.getSonContent());
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}
				
				//取e
				if(gpa.getGpaE()!=null && onegpaType.equals(gpa.getGpaE())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".e")){
							gpaShowShort.setSonContent("e)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"<br>"+gpaShowShort.getSonContent());
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}
				
				//取f
				if(gpa.getGpaF()!=null && onegpaType.equals(gpa.getGpaF())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".f")){
							gpaShowShort.setSonContent("f)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"<br>"+gpaShowShort.getSonContent());
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}
				
				//取g
				if(gpa.getGpaG()!=null && onegpaType.equals(gpa.getGpaG())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".g")){
							gpaShowShort.setSonContent("g)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"<br>"+gpaShowShort.getSonContent());
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}				
				
				//取h
				if(gpa.getGpaH()!=null && onegpaType.equals(gpa.getGpaH())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".h")){
							gpaShowShort.setSonContent("h)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"\n"+gpaShowShort.getSonContent());
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}
				
				//取i
				if(gpa.getGpaI()!=null && onegpaType.equals(gpa.getGpaI())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".i")){
							gpaShowShort.setSonContent("i)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"\n"+gpaShowShort.getSonContent());
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}
				//取j
				if(gpa.getGpaJ()!=null && onegpaType.equals(gpa.getGpaJ())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".j")){
							gpaShowShort.setSonContent("j)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"<br>"+gpaShowShort.getSonContent());
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}	
				if(gpaShow1!=null){
					this.gpaShows.add(gpaShow1);
				}
			}
		}
		
		
		this.getResponse().setCharacterEncoding("UTF-8");
		List<Map> mapgpaShow = new ArrayList<Map>();
		List<Map> mapgpaShowNotKey = new ArrayList<Map>();
//		if(gpaShows.size()!=0){
//			for(GpaShow gpaShow1: gpaShows)
//			{
//				Map<String, Object> map1 = new HashMap<String, Object>();
//				map1.put("g", gpaShow1.getgFatherSort()+" "+gpaShow1.getgFatherName());
//				map1.put("f", gpaShow1.getFatherSort() +" "+gpaShow1.getFatherName());
//				map1.put("s", gpaShow1.getSonContent());
//				mapgpaShow.add(map1);
//			}
//		}
		
		
		if(gpaShows.size()!=0){
			for(GpaShow gpaShow1: gpaShows)
			{
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("g", gpaShow1.getgFatherSort()+" "+gpaShow1.getgFatherName());
				map1.put("f", gpaShow1.getFatherSort() +" "+gpaShow1.getFatherName());
				map1.put("s", gpaShow1.getSonContent());
				mapgpaShow.add(map1);
			}
		}
		
		
		
		
		
		// 处理分页
				SearchResult sr = new SearchResult();
				
				for (int i = 0; i < mapgpaShow.size(); i++) {
					
					if((page.getStartIndex()<=i)&&(mapgpaShow.get(i)!=null)&&(mapgpaShowNotKey.size()<page.getPageSize())){
						mapgpaShowNotKey.add(mapgpaShow.get(i));
					}
				}
				sr.setList(mapgpaShowNotKey);
				page.setTotalCount(mapgpaShow.size());
				sr.setPage(page);
				
				JSONArray jsonArray = JSONArray.fromObject(sr.getList());
				JSONObject jsonObject = JSONObject.fromObject(sr.getPage());
		// Ajax返回
				try {
					//System.out.println("{\"gpaShows\":" + jsonArray.toString() + ",\"page\":" + jsonObject.toString() + "}");
					getResponse().getWriter().write("{\"gpaShows\":" + jsonArray.toString() + ",\"page\":" + jsonObject.toString() + "}");
				} catch (IOException e) {
					e.printStackTrace();
				}
//		// 处理数据分页的起始条数
//				String startIndex = request.getParameter("page");
//				if (StringUtil.isNotBlank(startIndex)) {
//					mapgpaShow =pm.getObjectLists(Integer.valueOf(startIndex)); 
//				} else {
//					mapgpaShow =pm.getObjectLists(1); 
//				}
				request.setAttribute("mapgpaShow", mapgpaShow);
//				request.setAttribute("Page", pm);
		return ;
	}

	
	
	
	/**
	 * 部分或不符合或不适用整改需求汇总
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public void  queryAjaxGpaShowNoFit() throws UnsupportedEncodingException{
		HttpServletRequest request = super.getRequest();
		Page page = null;
		// 处理数据分页的起始条数
				String startIndex = request.getParameter("startIndex");
				if (StringUtil.isNotBlank(startIndex)) {
					page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
				} else {
					page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
				}
		gpaDate=request.getParameter("gpaDate");
		type=request.getParameter("type");
		String key=java.net.URLDecoder.decode(request.getParameter("keyword"), "UTF-8").trim();
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
		List<GpaShow> list=gpaShowService.queryAllData();
		for(Gpa gpa:gpas){
			if(!("0".equals(gpa.getGpaAssessResult()))){
				boolean flag=false;
				GpaShow gpaShow1=null;
				String str=gpa.getGpaSort();
                 //取a
				if(gpa.getGpaA()!=null && onegpaType.equals(gpa.getGpaA())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".a")){
							gpaShowShort.setSonContent("a)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}
				
				//取b
				if(gpa.getGpaB()!=null && onegpaType.equals(gpa.getGpaB())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".b")){
							gpaShowShort.setSonContent("b)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"<br>"+gpaShowShort.getSonContent());
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}
				
				//取c
				if(gpa.getGpaC()!=null && onegpaType.equals(gpa.getGpaC())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".c")){
							gpaShowShort.setSonContent("c)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"<br>"+gpaShowShort.getSonContent());
							}							
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}				
				
				//取d
				if(gpa.getGpaD()!=null && onegpaType.equals(gpa.getGpaD())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".d")){
							gpaShowShort.setSonContent("d)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"<br>"+gpaShowShort.getSonContent());
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}
				
				//取e
				if(gpa.getGpaE()!=null && onegpaType.equals(gpa.getGpaE())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".e")){
							gpaShowShort.setSonContent("e)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"<br>"+gpaShowShort.getSonContent());
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}
				
				//取f
				if(gpa.getGpaF()!=null && onegpaType.equals(gpa.getGpaF())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".f")){
							gpaShowShort.setSonContent("f)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"<br>"+gpaShowShort.getSonContent());
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}
				
				//取g
				if(gpa.getGpaG()!=null && onegpaType.equals(gpa.getGpaG())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".g")){
							gpaShowShort.setSonContent("g)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"<br>"+gpaShowShort.getSonContent());
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}				
				
				//取h
				if(gpa.getGpaH()!=null && onegpaType.equals(gpa.getGpaH())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".h")){
							gpaShowShort.setSonContent("h)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"\n"+gpaShowShort.getSonContent());
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}
				
				//取i
				if(gpa.getGpaI()!=null && onegpaType.equals(gpa.getGpaI())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".i")){
							gpaShowShort.setSonContent("i)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"\n"+gpaShowShort.getSonContent());
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}
				//取j
				if(gpa.getGpaJ()!=null && onegpaType.equals(gpa.getGpaJ())){
					//遍历整改需求汇总集合
					for(GpaShow gpaShowShort:list){
						if(gpaShowShort.getSonSort().equals(str+".j")){
							gpaShowShort.setSonContent("j)"+gpaShowShort.getSonContent());
							if(gpaShow1==null){
								gpaShow1=gpaShowShort;
							}else{
								gpaShow1.setSonContent(gpaShow1.getSonContent()+"<br>"+gpaShowShort.getSonContent());
							}
							//this.gpaShows.add(gpaShowShort);
							break;
						}
					}
				}	
				if(gpaShow1!=null){
					this.gpaShows.add(gpaShow1);
				}
			}
		}
		this.getResponse().setCharacterEncoding("UTF-8");
		List<Map> mapgpaShow = new ArrayList<Map>();
		List<Map> mapgpaShowNotKey = new ArrayList<Map>();
 
		if(gpaShows.size()!=0){
			for(GpaShow gpaShow1: gpaShows)
			{
				String a=gpaShow1.getgFatherSort();
				String b=gpaShow1.getgFatherName();
				String c=gpaShow1.getFatherSort();
				String d=gpaShow1.getFatherName();
				String f=gpaShow1.getSonContent();
				if((a+" "+b).contains(key)){
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("g", a+" "+gpaShow1.getgFatherName());
					map1.put("f", gpaShow1.getFatherSort() +" "+gpaShow1.getFatherName());
					map1.put("s", gpaShow1.getSonContent());
					mapgpaShow.add(map1);
				}else
				if((c+" "+d).contains(key)){
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("g", a+" "+gpaShow1.getgFatherName());
					map1.put("f", gpaShow1.getFatherSort() +" "+gpaShow1.getFatherName());
					map1.put("s", gpaShow1.getSonContent());
					mapgpaShow.add(map1);
				}else
				if(a.contains(key)){
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("g", a+" "+gpaShow1.getgFatherName());
					map1.put("f", gpaShow1.getFatherSort() +" "+gpaShow1.getFatherName());
					map1.put("s", gpaShow1.getSonContent());
					mapgpaShow.add(map1);
				}else
				if(b.contains(key)){
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("g", gpaShow1.getgFatherSort()+" "+b);
					map1.put("f", gpaShow1.getFatherSort() +" "+gpaShow1.getFatherName());
					map1.put("s", gpaShow1.getSonContent());
					mapgpaShow.add(map1);
				}else
				if(c.contains(key)){
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("g", gpaShow1.getgFatherSort()+" "+gpaShow1.getgFatherName());
					map1.put("f", c +" "+gpaShow1.getFatherName());
					map1.put("s", gpaShow1.getSonContent());
					mapgpaShow.add(map1);
				}else
				if(d.contains(key)){
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("g", gpaShow1.getgFatherSort()+" "+gpaShow1.getgFatherName());
					map1.put("f", gpaShow1.getFatherSort() +" "+d);
					map1.put("s", gpaShow1.getSonContent());
					mapgpaShow.add(map1);
				}else
				if(f.contains(key)){
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("g", gpaShow1.getgFatherSort()+" "+gpaShow1.getgFatherName());
					map1.put("f", gpaShow1.getFatherSort() +" "+gpaShow1.getFatherName());
					map1.put("s", f);
					mapgpaShow.add(map1);
				}
			}
		}
		
		
					// 处理分页
					SearchResult sr = new SearchResult();
					
					for (int i = 0; i < mapgpaShow.size(); i++) {
						
						if((page.getStartIndex()<=i)&&(mapgpaShow.get(i)!=null)&&(mapgpaShowNotKey.size()<page.getPageSize())){
							mapgpaShowNotKey.add(mapgpaShow.get(i));
						}
					}


					sr.setList(mapgpaShowNotKey);
					page.setTotalCount(mapgpaShow.size());
					sr.setPage(page);
					
					JSONArray jsonArray = JSONArray.fromObject(sr.getList());
					JSONObject jsonObject = JSONObject.fromObject(sr.getPage());
					
						// Ajax返回
						try {
							//System.out.println("{\"gpaShows\":" + jsonArray.toString() + ",\"page\":" + jsonObject.toString() + "}");
							getResponse().getWriter().write("{\"gpaShows\":" + jsonArray.toString() + ",\"page\":" + jsonObject.toString() + "}");
						} catch (IOException e) {
							e.printStackTrace();
						}
//		// 处理数据分页的起始条数
				request.setAttribute("mapgpaShow", mapgpaShow);
		return ;
	}
	/**
	 * 显示部分符合整改需求汇总
	 * @return
	 */
	public String gpaShowPartFit(){
	     return SUCCESS;
	}
	
	public GpaShow getModel() {
		return null;
	}

	public GpaService getGpaService() {
		return gpaService;
	}

	public void setGpaService(GpaService gpaService) {
		this.gpaService = gpaService;
	}

	public GpaShowService getGpaShowService() {
		return gpaShowService;
	}

	public void setGpaShowService(GpaShowService gpaShowService) {
		this.gpaShowService = gpaShowService;
	}

	public List<GpaShow> getGpaShows() {
		return gpaShows;
	}

	public void setGpaShows(List<GpaShow> gpaShows) {
		this.gpaShows = gpaShows;
	}

	public GpaShow getGpaShow() {
		return gpaShow;
	}

	public void setGpaShow(GpaShow gpaShow) {
		this.gpaShow = gpaShow;
	}

	public List<Gpa> getGpas() {
		return gpas;
	}

	public void setGpas(List<Gpa> gpas) {
		this.gpas = gpas;
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
	
}
