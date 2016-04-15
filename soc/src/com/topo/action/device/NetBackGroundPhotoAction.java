package com.topo.action.device;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.topo.action.BaseAction;
import com.topo.model.device.NetBackGroundPhoto;
import com.topo.service.device.NetBackGroundPhotoService;
import com.topo.util.LinuxSystem;
import com.util.StringUtil;

public class NetBackGroundPhotoAction extends BaseAction {

	private NetBackGroundPhotoService netBackGroundPhotoService;
	private NetBackGroundPhoto netBackGroundPhoto=new NetBackGroundPhoto();
	private List<NetBackGroundPhoto> nbgpList;
	
	
	public String queryAll(){
		nbgpList=netBackGroundPhotoService.queryAllNBGP();
		return SUCCESS;
	}
	
	public void queryPhotoName() throws IOException{
		HttpServletRequest request=super.getRequest();
		HttpServletResponse response=super.getResponse();
		StringBuffer s = new StringBuffer();
		PrintWriter out = response.getWriter();
		String strStatus=request.getParameter("status");
		//看是否存在内外网使用同一张图片的情况
		String name=netBackGroundPhotoService.queryCurrentPhotoName(2);
		if(StringUtil.isNotBlank(name)){
			s.append(name);
		}else{
			String photoName=netBackGroundPhotoService.queryCurrentPhotoName(Integer.valueOf(strStatus));
			s.append(photoName);
		}
		out.print(s.toString());
		out.flush();
	}
	
	public String updatePhotoName(){
		HttpServletRequest request=super.getRequest();
		String photoStatus=request.getParameter("photoStatus");
		String photoStatuss=request.getParameter("photoStatuss");
		String photoName=request.getParameter("netBackGroundPhotophotoName");
		if(StringUtil.isNotBlank(photoStatuss)){
			netBackGroundPhoto.setPhotoStatus(Integer.parseInt(photoStatuss));
		}else{
			if(StringUtil.isNotBlank(photoStatus)){
				netBackGroundPhoto.setPhotoStatus(Integer.parseInt(photoStatus));
			}
		}
		netBackGroundPhoto.setPhotoChecked(1);
		netBackGroundPhoto.setPhotoName(photoName);
		//先查询是否存在内外拓扑用同一张图片
		Map map0=new HashMap();
		map0.put("status", 2);
		NetBackGroundPhoto bean0=netBackGroundPhotoService.queryCurrentNBGP(map0);
		if(bean0!=null){
			if(netBackGroundPhoto.getPhotoStatus()==0){
				bean0.setPhotoStatus(1);
				//bean0.setPhotoChecked(0);
			}else{
				bean0.setPhotoStatus(0);
				//bean0.setPhotoChecked(0);
			}
			netBackGroundPhotoService.update(bean0);
		}
		//数据库中是否存在当前拓扑所选择的图片
		Map map=new HashMap();
		//map.put("status", netBackGroundPhoto.getPhotoStatus());
		if(netBackGroundPhoto.getPhotoStatus()==2){//这样写，是因为当外网选1，内网选2，修改时，让内外网都用3，此时需要把1,2图片状态都该为0
			for(int i=0;i<2;i++){
				map.put("status", i);
				NetBackGroundPhoto bean=netBackGroundPhotoService.queryCurrentNBGP(map);
				if(bean!=null ){
					bean.setPhotoChecked(0);
					bean.setPhotoStatus(netBackGroundPhoto.getPhotoStatus());
					netBackGroundPhotoService.update(bean);
				}
			}
		}else{
			map.put("status", Integer.parseInt(photoStatus));
			NetBackGroundPhoto bean=netBackGroundPhotoService.queryCurrentNBGP(map);
			if(bean!=null ){
				bean.setPhotoChecked(0);
				bean.setPhotoStatus(netBackGroundPhoto.getPhotoStatus());
				netBackGroundPhotoService.update(bean);
			}
		}
		
		//内外网选择的图片相同时，就把status置为2
		Map map1=new HashMap();
		map1.put("name", photoName);
		NetBackGroundPhoto bean1=netBackGroundPhotoService.queryCurrentNBGP(map1);
		if(bean1!=null&&bean1.getPhotoChecked()==1&&bean1.getPhotoStatus()!=netBackGroundPhoto.getPhotoStatus()){
			netBackGroundPhoto.setPhotoStatus(2);
		}
		netBackGroundPhotoService.update(netBackGroundPhoto);
		request.setAttribute("setSuccess", "保存成功");
		return SUCCESS;
	}
	public NetBackGroundPhotoService getNetBackGroundPhotoService() {
		return netBackGroundPhotoService;
	}
	public void setNetBackGroundPhotoService(
			NetBackGroundPhotoService netBackGroundPhotoService) {
		this.netBackGroundPhotoService = netBackGroundPhotoService;
	}
	public NetBackGroundPhoto getNetBackGroundPhoto() {
		return netBackGroundPhoto;
	}
	public void setNetBackGroundPhoto(NetBackGroundPhoto netBackGroundPhoto) {
		this.netBackGroundPhoto = netBackGroundPhoto;
	}
	public List<NetBackGroundPhoto> getNbgpList() {
		return nbgpList;
	}
	public void setNbgpList(List<NetBackGroundPhoto> nbgpList) {
		this.nbgpList = nbgpList;
	}
}
