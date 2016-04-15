package com.compliance.webapp.action.basicinfo.division;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.compliance.model.basicinfo.division.Division;
import com.compliance.service.basicinfo.division.DivisionService;
import com.compliance.webapp.action.BaseAction;
import com.util.StringUtil;

public class DivisionAction extends BaseAction {

	private DivisionService divisionService;
	private List<Division> divisitionList;
	
	//查询组织部门信息树
	public void query(){
		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Pragma","No-cache"); 
		response.setHeader("Cache-Control","no-cache"); 
		response.setDateHeader("Expires", 0);
		try {
			List<Division> divisionList = divisionService.query();
			List<Division> newList = new ArrayList<Division>();
			if (divisionList.size() != 0) {
				for (Division division : divisionList) {
					if (division.getParentId() == 0) {
						newList.add(division);
						XNodeList(divisionList,newList,division.getId());
						
					}
				}
			}

			try {
				if(newList.size()!=0){
					JSONArray jsonArray = JSONArray.fromObject(newList);
					response.getWriter().write(jsonArray.toString());		
				}else {
					response.getWriter().write("0");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return;
	}
	
	//查询组织部门信息
	public void queryInfo(){
		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Pragma","No-cache"); 
		response.setHeader("Cache-Control","no-cache"); 
		response.setDateHeader("Expires", 0);
		String id = request.getParameter("id");
		if (StringUtil.isNotBlank(id)) {
			Division division = divisionService.queryById(Integer.parseInt(id));
			JSONObject jsonObject = JSONObject.fromObject(division);
			try {
				response.getWriter().write(jsonObject.toString());		
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return;
	}
	
	//修改组织部门信息
	public void updateInfo(){
		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Pragma","No-cache"); 
		response.setHeader("Cache-Control","no-cache"); 
		response.setDateHeader("Expires", 0);
		
		
		String id = request.getParameter("id");
		String depName = request.getParameter("depName");
		String depEmp = request.getParameter("depEmp");
		String depHead = request.getParameter("depHead");
		String depDescription = request.getParameter("depDescription");
		String parentId = request.getParameter("parentId");
		String parentDepEmp = request.getParameter("parentDepEmp");
		String parentDepHead = request.getParameter("parentDepHead");
		
		
		if (StringUtil.isNotBlank(id) && StringUtil.isNotBlank(depName)) {
			Division division = new Division();
			division.setId(Integer.parseInt(id));
			division.setDepName(depName);
			division.setDepEmp(depEmp);
			division.setDepHead(depHead);
			division.setDepDescription(depDescription);
			division.setParentId(Integer.parseInt(parentId));
			division.setParentDepEmp(parentDepEmp);
			division.setParentDepHead(parentDepHead);
			divisionService.updata(division);
			try {
				response.getWriter().write("SUCCESS");		
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				response.getWriter().write("ERROR");		
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return;
	}
	//添加组织部门信息
		public void addInfo(){
			HttpServletRequest request = super.getRequest();
			HttpServletResponse response = super.getResponse();
			
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Pragma","No-cache"); 
			response.setHeader("Cache-Control","no-cache"); 
			response.setDateHeader("Expires", 0);
			
			
			String depName = request.getParameter("depName");
			String depEmp = request.getParameter("depEmp");
			String depHead = request.getParameter("depHead");
			String depDescription = request.getParameter("depDescription");
			String parentId = request.getParameter("parentId");
			String parentDepEmp = request.getParameter("parentDepEmp");
			String parentDepHead = request.getParameter("parentDepHead");
			
			
			if (StringUtil.isNotBlank(depName)) {
				Division division = new Division();
				division.setDepName(depName);
				division.setDepEmp(depEmp);
				division.setDepHead(depHead);
				division.setDepDescription(depDescription);
				division.setParentId(Integer.parseInt(parentId));
				division.setParentDepEmp(parentDepEmp);
				division.setParentDepHead(parentDepHead);
				int id = divisionService.insert(division);
				try {
					//System.out.println("----" + id);
					response.getWriter().write(String.valueOf(id));		
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				try {
					response.getWriter().write("ERROR");		
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return;
		}
		public void depNameCheck(){
			HttpServletRequest request = super.getRequest();
			HttpServletResponse response = super.getResponse();

			List<Division> divs=divisionService.queryByDepName(request.getParameter("depName"));
			try {
				if(divs.size()==0){
					response.getWriter().write("0");
				}else{
					response.getWriter().write("1");
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		public void deleteCheck(){
			HttpServletRequest request = super.getRequest();
			HttpServletResponse response = super.getResponse();
			String depName = request.getParameter("depName");
			//List<Employee> employees=employeeService.queryByDepName(depName);
			List<Division> divs=divisionService.queryByParentId(Integer.parseInt(request.getParameter("id")));
			try {
				if(divs.size()==0){
				/*	if(employees.size()==0){
						response.getWriter().write("1");
					}else{
						response.getWriter().write("2");
					}*/
				}else{
					response.getWriter().write("3");
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	
	//添加组织部门信息
	public void deleteInfo(){
		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Pragma","No-cache"); 
		response.setHeader("Cache-Control","no-cache"); 
		response.setDateHeader("Expires", 0);
		
		String id = request.getParameter("id");
		
		if (StringUtil.isNotBlank(id)) {
			List<Division> divisionList = divisionService.query();
			////System.out.println(id);
			////System.out.println();
			XNodeDelete(divisionList,Integer.parseInt(id));
			divisionService.delete(Integer.parseInt(id));
			
			try {
				response.getWriter().write("SUCCESS");		
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				response.getWriter().write("ERROR");		
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return;
	}
	
	
	
	public  void XNodeList(List<Division> oldList, List<Division> newList,int parentId){
		if(oldList.size()!=0){
			for (Division division : oldList) {
				if(parentId == division.getParentId()){
					newList.add(division);
					XNodeList(oldList, newList, division.getId());
				}
			}
		}	
	}
	
	public void XNodeDelete(List<Division> list,int id){
		if(list.size()!=0){
			for (Division division : list) {
				if(id == division.getParentId()){
					XNodeDelete(list,division.getId());
					divisionService.delete(division.getId());
				}
			}
		}	
	}
	
	public DivisionService getDivisionService() {
		return divisionService;
	}

	public void setDivisionService(DivisionService divisionService) {
		this.divisionService = divisionService;
	}


	public List<Division> getDivisitionList() {
		return divisitionList;
	}


	public void setDivisitionList(List<Division> divisitionList) {
		this.divisitionList = divisitionList;
	}

}
