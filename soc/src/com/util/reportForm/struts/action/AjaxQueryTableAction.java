package com.util.reportForm.struts.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.util.reportForm.datadeal.BaseDao;
import com.util.reportForm.datadeal.model.Alltablename;
import com.util.reportForm.datadeal.model.Tableorder;

public class AjaxQueryTableAction extends DispatchAction {
	BaseDao dao=new BaseDao();
	public ActionForward getTablesQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		String orderNum=request.getParameter("orderNum");
		String paterOrder=request.getParameter("paterOrders");
		String twoids=request.getParameter("twoids");
		String checked=request.getParameter("checked");
		String threeids=request.getParameter("threeids");
//		String[] porders=paterOrder.split(";");
//		String porder="";
//		for(int i=0;i<porders.length;i++){
//			porder=porder+porders[i]+",";
//		}
//		porder=porder.substring(0,porder.length()-1);
		String hql="from Tableorder where orderNum=:orderNum and paterOrder in("+paterOrder+")";
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("orderNum", Integer.parseInt(orderNum));
		Collection<Tableorder> list1=new ArrayList<Tableorder>();
		Collection<Tableorder> list2=new ArrayList<Tableorder>();
		Collection<Alltablename> list3=new ArrayList<Alltablename>();
		
		if(orderNum!=null && orderNum.equals("2")){
			list1=dao.getNamedQuery(hql, map);
			if(twoids!=null && !twoids.equals("")){
				String ids="";
				String[] idss=twoids.split(",");
				for(Iterator<Tableorder> it=list1.iterator();it.hasNext();){
					Tableorder t=(Tableorder) it.next();
					long id=t.getId();
					if(checked.equals("false")){
						twoids="";
						for(int i=0;i<idss.length;i++){
							if(idss[i]!=null && idss[i].equals(t.getId().toString())){
								idss[i]=null;
								break;
							}
						}
						for(String s:idss){
							if(s!=null) twoids+=(s+",");
						}
					}else{
						ids+=id+",";
					}
				}
				ids+=twoids;
				String hqls="";
				if(!ids.equals("")){
					ids=ids.substring(0,ids.length()-1);
					hqls="from Tableorder where id in("+ids+")";
				}else{
					hqls="from Tableorder where id in('0')";
				}
				list1.clear();
				list1=(List) dao.getNamedQuery(hqls, null);
			}
			
		}else if(orderNum!=null && orderNum.equals("3")){
			list2=dao.getNamedQuery(hql, map);
			for(Iterator<Tableorder> it=list2.iterator();it.hasNext();){
				Tableorder t=it.next();
				String name=t.getName();
				threeids+=name+",";
			}
			String hqls="";
			if(!threeids.equals("")){
				threeids=threeids.substring(0,threeids.length()-1);
				hqls="from Alltablename where id in("+threeids+")";
			}else{
				hqls="from Alltablename where id in('0')";
			}
//			//System.out.println("hql2-->" + hqls);
			list3=dao.getNamedQuery(hqls, null);
			
		}

		request.setAttribute("leveltwolist", list1);
		request.setAttribute("levelthreelist", list3);
		return mapping.findForward("ajaxquerytablesres");
		
	}
	
	//删除按钮
	public ActionForward getDelTwoTablesQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String orderNum=request.getParameter("orderNum");
		String unckeckitmeid=request.getParameter("unckeckitmeid");
		List list1=new ArrayList();
		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("orderNum", Integer.parseInt(orderNum));
		String hql1="";
		if(orderNum!=null && orderNum.equals("2")){
			if(unckeckitmeid!=null &&!unckeckitmeid.equals("")){
				hql1="from Tableorder where orderNum=:orderNum and id in("+unckeckitmeid.substring(0,unckeckitmeid.length()-1)+")";
				
			}else{
				hql1="from Tableorder where orderNum=:orderNum and id in('0')";
			}
			//二级菜单
			list1=(List) dao.getNamedQuery(hql1, map);
			request.setAttribute("leveltwolist", list1);
			
			
		}
//		if(orderNum!=null && orderNum.equals("3")){
//			//二级菜单
//			String hql4="from Tableorder where orderNum=2 and id in("+twoids.substring(0,twoids.length()-1)+")";
//			List list5=(List) dao.getNamedQuery(hql4, null);
//			request.setAttribute("leveltwolist", list5);
//		}
		
		return mapping.findForward("ajaxquerytablesres");
		
	}
	public ActionForward getDelThreeTablesQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String orderNum=request.getParameter("orderNum");
		String unckeckitmeid=request.getParameter("unckeckitmeid");
		String threeids=request.getParameter("threeids");
		//三级菜单
		String hql2="";
		if(orderNum!=null && orderNum.equals("2")){
			if(unckeckitmeid!=null  &&!unckeckitmeid.equals("")){
				hql2="from Tableorder where orderNum=3 and paterOrder in("+unckeckitmeid.substring(0,unckeckitmeid.length()-1)+")";
			}else{
				hql2="from Tableorder where orderNum=3 and paterOrder in('0')";
			}
			List list2=(List) dao.getNamedQuery(hql2, null);
			String ids="";
			for(Iterator it=list2.iterator();it.hasNext();){
				Tableorder t=(Tableorder) it.next();
				String name=t.getName();
				
				//判断三级菜单中是否有所删除的表
				if(threeids!=null && !threeids.equals("") && threeids.indexOf(name)!=-1){
					ids=ids+name+",";
				}
				
			}
			String hqls="";

			if(!ids.equals("")){
				ids=ids.substring(0,ids.length()-1);
				hqls="from Alltablename where id in("+ids+")";
			}else{
				hqls="from Alltablename where id in('0')";
			}

			List list3=(List) dao.getNamedQuery(hqls, null);
			
			request.setAttribute("levelthreelist", list3);
			
		}if(orderNum!=null && orderNum.equals("3")){
			String hql3="";
			if(unckeckitmeid!=null &&!unckeckitmeid.equals("")){
				hql3="from Alltablename where id in("+unckeckitmeid.substring(0,unckeckitmeid.length()-1)+")";
			}else if(unckeckitmeid==null||unckeckitmeid.equals("")){
				hql3="from Alltablename where id in('0')";
			}

			//三级菜单
			List list4=(List) dao.getNamedQuery(hql3, null);
			request.setAttribute("levelthreelist", list4);
		}
		return mapping.findForward("ajaxquerytablesres");
		
	}
}
