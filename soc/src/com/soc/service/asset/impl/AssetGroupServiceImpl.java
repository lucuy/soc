package com.soc.service.asset.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.soc.dao.asset.AssetDao;
import com.soc.dao.asset.AssetGroupDao;
import com.soc.model.asset.Asset;
import com.soc.model.asset.AssetGroup;
import com.soc.service.asset.AssetGroupService;
import com.soc.service.impl.BaseServiceImpl;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class AssetGroupServiceImpl extends BaseServiceImpl implements
		AssetGroupService {

	private AssetGroupDao assetGroupDao;

	private AssetDao assetDao;

	private static final String PICTURE_NAME_SEED = "arrow_03.gif"; // 表示页子节点的图片
	private static final String PICTURE_NAME_CLOSE = "u321_normal.gif"; // 表示关闭的图片
	List listAG = new ArrayList();
	// 存放遍历之后的父节点信息
	private StringBuffer strBuf ;

	//查询出来的资产的Id
	private StringBuffer bufAsId ; 
	
	@Override
	public SearchResult query(Map map, Page page) {
		// 按Map中存储的条件查找用户列表
		int rowsCount = assetGroupDao.count(map);
		page.setTotalCount(rowsCount);
		List<AssetGroup> list = assetGroupDao.query(map, page.getStartIndex(),
				page.getPageSize());

		// 对查找的用户列表做分页处理
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);

		return sr;
	}

	/** {@inheritDoc} */

	@Override
	public List<AssetGroup> queryByParentId(Map map) {
		// TODO Auto-generated method stub
		return assetGroupDao.queryByParentId(map);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Map> querySon(String parentsFeature) {
		return assetGroupDao.querySon(parentsFeature);
	}

	/** {@inheritDoc} */

	@Override
	public AssetGroup queryById(long id) {
		// TODO Auto-generated method stub
		return assetGroupDao.queryById(id);
	}

	/** {@inheritDoc} */

	@Override
	public AssetGroup insertAssetGroup(String type, int groupId) {
		// TODO Auto-generated method stub
		AssetGroup thisGroup;
		AssetGroup assetGroup = new AssetGroup();
		assetGroup.setAssetGroupId(0);
		if (type.equals("b")) {
			// 增加同级相同的上级
			thisGroup = this.queryById(groupId);
			assetGroup.setAssetGroupParentId(thisGroup.getAssetGroupParentId());
			assetGroup.setParentGroup(thisGroup.getParentGroup());
			assetGroup.setAssetParentsFeature(thisGroup
					.getAssetParentsFeature());
		} else if (type.equals("s")) {
			// 增加下级
			thisGroup = this.queryById(groupId);
			assetGroup.setAssetGroupParentId(thisGroup.getAssetGroupId());
			assetGroup.setParentGroup(thisGroup);
			assetGroup.setAssetParentsFeature(thisGroup
					.getAssetParentsFeature()
					+ thisGroup.getAssetGroupId()
					+ ",");

		} else if (type.equals("r")) {
			assetGroup.setAssetGroupParentId(0);
			assetGroup.setAssetParentsFeature(",");
		}
		return assetGroup;
	}

	/** {@inheritDoc} */

	@Override
	public void deleteAssetGroup(long id) {
		// TODO Auto-generated method stub
		// 删除组
		assetGroupDao.deleteAssetGroup(id);
		/*
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("groupId", 0); map.put("groupName", "不进行分组");
		 * map.put("assetGroupId", id); //删除资产所关联的组 assetDao.deleteGroup(map);
		 */
		assetGroupDao.delLinedAssetGroup(id);
		
	}

	StringBuffer treeBuff;

	@Override
	public int count(Map map) {
		return assetGroupDao.count(map);
	}

	/** {@inheritDoc} */

	@Override
	public int updateAssetGroup(AssetGroup assetGroup) {
		// TODO Auto-generated method stub
		// 执行更新操作
		if (assetGroup.getAssetGroupId() != 0) {
			assetGroup.setAssetGroupUpdateDateTime(new Date());
			assetGroupDao.updateAssetGroup(assetGroup);
			return Integer.parseInt(String.valueOf(assetGroup.getAssetGroupId()))  ; 
		}
		// 执行插入操作	
		else {
			assetGroup.setAssetGroupCreateDateTime(new Date());
			assetGroup.setAssetGroupUpdateDateTime(new Date());
			assetGroup.setAssetGroupIsDelete(0);
			int groupId =  assetGroupDao.insertAssetGroup(assetGroup);
			return groupId ; 
		}
		
	}

	public AssetGroupDao getAssetGroupDao() {
		return assetGroupDao;
	}

	public void setAssetGroupDao(AssetGroupDao assetGroupDao) {
		this.assetGroupDao = assetGroupDao;
	}

	public AssetDao getAssetDao() {
		return assetDao;
	}

	public void setAssetDao(AssetDao assetDao) {
		this.assetDao = assetDao;
	}

	@Override
	public String getTreeStyle(String path) {
		// TODO Auto-generated method stub
		treeBuff = new StringBuffer();
		Map<String, Long> map = new HashMap<String, Long>();

		map.put("assetGroupParentId", Long.valueOf(0));

		List<AssetGroup> assetGroupList = assetGroupDao.queryByParentId(map);

		for (AssetGroup assetGroup : assetGroupList) {
			Map<String, Long> map1 = new HashMap<String, Long>();

			map1.put("assetGroupParentId", assetGroup.getAssetGroupId());

			List<AssetGroup> assetGroupList1 = assetGroupDao
					.queryByParentId(map1);

			if (assetGroupList1.size() <= 0) {
				treeBuff.append("<ul>");
				treeBuff.append("<li><a href=\"javascript:showAssetGroup('"
						+ assetGroup.getAssetGroupId() + "');\"><img src=\""
						+ path + "/images/" + PICTURE_NAME_SEED
						+ "\"></a>&nbsp;<a href=\"javascript:showAssetGroup('"
						+ assetGroup.getAssetGroupId()
						+ "');\"><span class=\"eventext\">&nbsp;"
						+ assetGroup.getAssetGroupName() + "</span></a>");
				treeBuff.append("</li>");
				treeBuff.append("</ul>");
			} else {
				// 画出下级组
				treeBuff.append("<ul>");
				treeBuff.append("<li id=\"query_img_query_assetGroup"
						+ assetGroup.getAssetGroupId()
						+ "\"><a href=\"javascript:action('"
						+ assetGroup.getAssetGroupId()
						+ "','img_query_assetGroup');\"><img src=\""
						+ path
						+ "/images/"
						+ PICTURE_NAME_CLOSE
						+ "\" id=\"img_query_assetGroup"
						+ assetGroup.getAssetGroupId()
						+ "\"></a>&nbsp;<a href=\"javascript:showAssetGroup('"
						+ assetGroup.getAssetGroupId()
						+ "');\" ondblclick=\"action('"
						+ assetGroup.getAssetGroupId()
						+ "','img_query_assetGroup')\"><span class=\"eventext\">&nbsp;"
						+ assetGroup.getAssetGroupName() + "</span></a>");
				drawSon(assetGroupList1, path);
				treeBuff.append("</li>");
				treeBuff.append("</ul>");
			}

		}

		return treeBuff.toString();
	}
	
	
	
	
	//当用户有全部资产的权限时
	private void  allAssetGroup(StringBuffer str,String path){
		     List<AssetGroup> assetGroupList = new ArrayList<AssetGroup>();
		     List<AssetGroup> childrenList = new ArrayList<AssetGroup>();
		     Map map = new HashMap() ; 
		     assetGroupList = assetGroupDao.queryAllFatherGroup() ; 
		     for(AssetGroup group:assetGroupList){
		    		// 画出下级组
					treeBuff.append("<ul>");
					treeBuff.append("<li id=\"query_img_query_assetGroup"
							+ group.getAssetGroupId()
							+ "\"><a href=\"javascript:action('"
							+ group.getAssetGroupId()
							+ "','img_query_assetGroup');\"><img src=\""
							+ path
							+ "/images/"
							+ PICTURE_NAME_CLOSE
							+ "\" id=\"img_query_assetGroup"
							+ group.getAssetGroupId()
							+ "\"></a>&nbsp;<a href=\"javascript:showAssetGroup('"
							+ group.getAssetGroupId()
							+ "');\" ondblclick=\"action('"
							+ group.getAssetGroupId()
							+ "','img_query_assetGroup')\"><span class=\"eventext\">&nbsp;"
							+ group.getAssetGroupName() + "</span></a>");
					map.put("assetGroupParentId",group.getAssetGroupId());
					childrenList =  queryByParentId(map);
					if(!childrenList.isEmpty()){
					     drawSon(childrenList, path);
					}
					treeBuff.append("</li>");
					treeBuff.append("</ul>");
		     }
	}
	//画出未分组和全部资产
	private void drawAllAssetAndNoGroup(StringBuffer treeBuff,String groupName,long groupId,String path){
			 treeBuff.append("<ul>");
				treeBuff.append("<li><a href=\"javascript:showAssetGroup('"
						+ groupId
						+ "');\"><img src=\""
						+ path
						+ "/images/"
						+ PICTURE_NAME_SEED
						+ "\"></a>&nbsp;<a href=\"javascript:showAssetGroup('"
						+ groupId
						+ "');\"><span class=\"eventext\">&nbsp;"
						+ groupName + "</span></a>");
				treeBuff.append("</li>");
				treeBuff.append("</ul>");
	}
	
	/*//左侧属性结构
	public String getTreeStyle(String path, long groupId) {
		treeBuff = new StringBuffer() ; 
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("groupId", groupId);
		//用户对应的是全部资产时：
        if(groupId == 1){
        	drawAllAssetAndNoGroup(treeBuff,"全部资产",1,path);
        	drawAllAssetAndNoGroup(treeBuff,"未分组",2,path);
        	allAssetGroup(treeBuff,path) ; 
        }else{
        	AssetGroup parentGroup = assetGroupDao.queryById(groupId);
            List<AssetGroup> childrenList = assetGroupDao.queryByParentId(map);
            
            treeBuff.append("<ul>");
			treeBuff.append("<li id=\"query_img_query_assetGroup"
					+ parentGroup.getAssetGroupId()
					+ "\"><a href=\"javascript:action('"
					+ parentGroup.getAssetGroupId()
					+ "','img_query_assetGroup');\"><img src=\""
					+ path
					+ "/images/"
					+ PICTURE_NAME_CLOSE
					+ "\" id=\"img_query_assetGroup"
					+ parentGroup.getAssetGroupId()
					+ "\"></a>&nbsp;<a href=\"javascript:showAssetGroup('"
					+ parentGroup.getAssetGroupId()
					+ "');\" ondblclick=\"action('"
					+ parentGroup.getAssetGroupId()
					+ "','img_query_assetGroup')\"><span class=\"eventext\">"
					+ parentGroup.getAssetGroupName() + "</span></a>");
	
			     drawSon(childrenList, path);

			treeBuff.append("</li>");
			treeBuff.append("</ul>");
          
        }
		return treeBuff.toString()  ; 
	}*/

	
	
	@Override
	public String getTreeStyle(String path , long groupId) {
		// TODO Auto-generated method stub
		treeBuff = new StringBuffer();
		Map map = new HashMap<String, Long>();
        if(groupId == 1 ){
		     map.put("assetGroupParentId",0);
        }else{
        	map.put("assetGroupParentId", groupId) ; 
        }
        
		List<AssetGroup> assetGroupList = assetGroupDao.queryByParentId(map);
        
		AssetGroup group = assetGroupDao.queryById(groupId);
		
		if(assetGroupList.isEmpty()){
			if (group!=null) {
				
			
			treeBuff.append("<ul>");
			treeBuff.append("<li><a href=\"javascript:showAssetGroup('"
					+ group.getAssetGroupId() + "');\"><img src=\""
					+ path + "/images/" + PICTURE_NAME_SEED
					+ "\"></a>&nbsp;<a href=\"javascript:showAssetGroup('"
					+ group.getAssetGroupId()
					+ "');\"><span class=\"eventext\">&nbsp;"
					+ group.getAssetGroupName() + "</span></a>");
			treeBuff.append("</li>");
			treeBuff.append("</ul>");
			
			}
			
		}else if(group.getAssetGroupParentId() == 0 && group.getAssetGroupId() != 1 && group.getAssetGroupId() !=2  ){
			
			
			Map<String,Long> map1 = new HashMap<String, Long>();
			map1.put("assetGroupParentId", group.getAssetGroupId());
			List<AssetGroup> assetGroupList1 = assetGroupDao
					.queryByParentId(map1);
			
			if (assetGroupList1.size() <= 0) {
				treeBuff.append("<ul>");
				treeBuff.append("<li><a href=\"javascript:showAssetGroup('"
						+ group.getAssetGroupId() + "');\"><img src=\""
						+ path + "/images/" + PICTURE_NAME_SEED
						+ "\"></a>&nbsp;<a href=\"javascript:showAssetGroup('"
						+ group.getAssetGroupId()
						+ "');\"><span class=\"eventext\">&nbsp;"
						+ group.getAssetGroupName() + "</span></a>");
				treeBuff.append("</li>");
				treeBuff.append("</ul>");
			} else {
				// 画出下级组
				treeBuff.append("<ul>");
				treeBuff.append("<li id=\"query_img_query_assetGroup"
						+ group.getAssetGroupId()
						+ "\"><a href=\"javascript:action('"
						+ group.getAssetGroupId()
						+ "','img_query_assetGroup');\"><img src=\"" + path + "/images/"
						+ PICTURE_NAME_CLOSE + "\" id=\"img_query_assetGroup"
						+ group.getAssetGroupId()
						+ "\"></a>&nbsp;<a href=\"javascript:showAssetGroup('"
						+ group.getAssetGroupId()
						+ "');\" ondblclick=\"action('"
						+ group.getAssetGroupId()
						+ "','img_query_assetGroup')\"><span class=\"eventext\">&nbsp;"
						+ group.getAssetGroupName() + "</span></a>");
				drawSon(assetGroupList1, path);
				treeBuff.append("</li>");
				treeBuff.append("</ul>");
			}
			
			

		}else{	
			if (groupId!=1) {
				
				treeBuff.append("<ul>");
				treeBuff.append("<li id=\"query_img_query_assetGroup"
						+ group.getAssetGroupId()
						+ "\"><a href=\"javascript:action('"
						+ group.getAssetGroupId()
						+ "','img_query_assetGroup');\"><img src=\"" + path + "/images/"
						+ PICTURE_NAME_CLOSE + "\" id=\"img_query_assetGroup"
						+ group.getAssetGroupId()
						+ "\"></a>&nbsp;<a href=\"javascript:showAssetGroup('"
						+ group.getAssetGroupId()
						+ "');\" ondblclick=\"action('"
						+ group.getAssetGroupId()
						+ "','img_query_assetGroup')\"><span class=\"eventext\">&nbsp;"
						+ group.getAssetGroupName() + "</span></a>");
				drawSon(assetGroupList, path);
				treeBuff.append("</li>");
				treeBuff.append("</ul>");
			}else{
		 for (AssetGroup assetGroup : assetGroupList) {
			
			Map<String, Long> map1 = new HashMap<String, Long>();

			map1.put("assetGroupParentId", assetGroup.getAssetGroupId());

			List<AssetGroup> assetGroupList1 = assetGroupDao
					.queryByParentId(map1);

			if (assetGroupList1.size() <= 0) {
				treeBuff.append("<ul>");
				treeBuff.append("<li><a href=\"javascript:showAssetGroup('"
						+ group.getAssetGroupId() + "');\"><img src=\""
						+ path + "/images/" + PICTURE_NAME_SEED
						+ "\"></a>&nbsp;<a href=\"javascript:showAssetGroup('"
						+ group.getAssetGroupId()
						+ "');\"><span class=\"eventext\">&nbsp;"
						+ assetGroup.getAssetGroupName() + "</span></a>");
				
				treeBuff.append("</li>");
				treeBuff.append("</ul>");
			} else {
				// 画出下级组
				treeBuff.append("<ul>");
				treeBuff.append("<li id=\"query_img_query_assetGroup"
						+ assetGroup.getAssetGroupId()
						+ "\"><a href=\"javascript:action('"
						+ assetGroup.getAssetGroupId()
						+ "','img_query_assetGroup');\"><img src=\"" + path + "/images/"
						+ PICTURE_NAME_CLOSE + "\" id=\"img_query_assetGroup"
						+ assetGroup.getAssetGroupId()
						+ "\"></a>&nbsp;<a href=\"javascript:showAssetGroup('"
						+ assetGroup.getAssetGroupId()
						+ "');\" ondblclick=\"action('"
						+ assetGroup.getAssetGroupId()
						+ "','img_query_assetGroup')\"><span class=\"eventext\">&nbsp;"
						+ assetGroup.getAssetGroupName() + "</span></a>");
				drawSon(assetGroupList1, path);
				treeBuff.append("</li>");
				treeBuff.append("</ul>");
			}

		}
			}
		}
		return treeBuff.toString();
	}
	
	//返回登陆用户所有的资产Id字符串
	@Override
	public List<Asset> getAllAsset(long groupId,String groupIdStr,Page page){
		List<Asset> assetList = new ArrayList<Asset>(); 
		if(groupId == 1){
			assetList=assetDao.query(null) ; 
		}else{
			Map map = new HashMap() ; 
			if(StringUtil.isNotEmpty(groupIdStr)){
				map.put("groupId", groupIdStr) ; 
				assetList =  assetDao.queryAllAsset(map,page.getStartIndex(),page.getPageSize());
			}
		}
		return assetList ; 
	}
    
	//根据组id查询组下的所有资产组的id,返回字符串
	public String getAllGroupIdByID(long groupId){
        List<AssetGroup> listGroup = new ArrayList<AssetGroup>() ;  
        strBuf = new StringBuffer() ; 
        Map map = new HashMap() ;  
        map.put("groupId", groupId);
        listGroup = assetGroupDao.queryAllGroupById(map);
        if(groupId == 1){
        	
        	listGroup = assetGroupDao.queryAllFatherGroup(); 
        	//查询所有资产的ID
        	for(AssetGroup group:listGroup){
        		if(strBuf.length() <= 0 ){
        		   strBuf.append(group.getAssetGroupId()) ; 
        		}else{
        			strBuf.append(","+group.getAssetGroupId());
        		}
        	}
        }else{
        	strBuf.append(groupId);
        	diguiMethod(listGroup,groupId);
        }
        return strBuf.toString() ; 
	}
	//便利显示所有资产组时 ，递归的方法
	public void diguiMethod(List<AssetGroup> listGroup,long id){
		for(AssetGroup assetGroup:listGroup){
			Map map = new HashMap() ;  
			map.put("groupId", assetGroup.getAssetGroupId());
			listGroup = assetGroupDao.queryAllGroupById(map);
			
		    if(!listGroup.isEmpty()){
    	    for(AssetGroup group : listGroup){
    	    	String strId = String.valueOf(group.getAssetGroupId());
    	        if(strBuf.indexOf(strId) == -1 ){
    	    	if(strBuf.length() <= 0 ){
         		   strBuf.append(group.getAssetGroupId()) ; 
         		}else{
         			strBuf.append(","+group.getAssetGroupId());
         		}
    	    	listGroup = assetGroupDao.queryAllFatherGroup(); 	
    	    	diguiMethod(listGroup,group.getAssetGroupId());
    	    }
    	    }
    	}else{
    		 String strId = String.valueOf(assetGroup.getAssetGroupId()) ; 
    		 if(strBuf.indexOf(strId) == -1){
    			 if(strBuf.length() <= 0 ){
           		   strBuf.append(strId) ; 
           		}else{
           			strBuf.append(","+strId);
           		}
    		 }
    	}
		}
	}
	
	
	
	
	

	

	public void drawSon(List<AssetGroup> assetGroupList, String path) {
		for (AssetGroup assetGroup : assetGroupList) {
			Map<String, Long> map = new HashMap<String, Long>();

			map.put("assetGroupParentId", assetGroup.getAssetGroupId());

			List<AssetGroup> groupList = assetGroupDao.queryByParentId(map);

			if (groupList.size() <= 0) {
				// 写出页子节点
				treeBuff.append("<ul  class=\"disply\">");
				treeBuff.append("<li class=\"eventleft\"><a href=\"javascript:showAssetGroup('"
						+ assetGroup.getAssetGroupId()
						+ "');\"><img src=\""
						+ path
						+ "/images/"
						+ PICTURE_NAME_SEED
						+ "\"></a>&nbsp;<a href=\"javascript:showAssetGroup('"
						+ assetGroup.getAssetGroupId()
						+ "');\"><span class=\"eventext\">&nbsp;"
						+ assetGroup.getAssetGroupName() + "</span></a>");
				treeBuff.append("</li>");
				treeBuff.append("</ul>");
			} else {
				// 画出下级组
				treeBuff.append("<ul  class=\"disply\">");
				treeBuff.append("<li id=\"query_img_query_assetGroup"
						+ assetGroup.getAssetGroupId()
						+ "\" class=\"eventleft\"><a href=\"javascript:action('"
						+ assetGroup.getAssetGroupId()
						+ "','img_query_assetGroup');\"><img src=\""
						+ path
						+ "/images/"
						+ PICTURE_NAME_CLOSE
						+ "\" id=\"img_query_assetGroup"
						+ assetGroup.getAssetGroupId()
						+ "\"></a>&nbsp;<a href=\"javascript:showAssetGroup('"
						+ assetGroup.getAssetGroupId()
						+ "')\" ondblclick=\"action('"
						+ assetGroup.getAssetGroupId()
						+ "','img_query_assetGroup');\"><span class=\"eventext\">&nbsp;"
						+ assetGroup.getAssetGroupName() + "</span></a>");
				drawSon(groupList, path);
				treeBuff.append("</li>");
				treeBuff.append("</ul>");

			}
		}
	}
	
	private List<AssetGroup> assetGroupFather = new ArrayList<AssetGroup>() ; 
	
	public void setAssetGroupFather(){
		 assetGroupFather = new ArrayList<AssetGroup>() ; 
	 }
	
	//递归查询资产组的所有父级组
	public List<AssetGroup> queryAllFatherGroupId(long groupId){
		
		
              AssetGroup group = assetGroupDao.queryById(groupId);
              long fatherId = group.getAssetGroupParentId() ; 
		      if(fatherId == 0){
		    	   assetGroupFather.add(group);
		      }else{
		    	  assetGroupFather.add(group);
		    	  queryAllFatherGroupId(fatherId);
		      }
		      
		
		return assetGroupFather ; 
	}

	
	
	
	@Override
	public List<AssetGroup> query() {

		return assetGroupDao.query();
	}

	@Override
	public List<AssetGroup> queryByuserid(Map map) {

		return assetGroupDao.queryByuserid(map);
	}

	@Override
	public void linkedAssetAssetGroup(Map map) {
		
		assetGroupDao.linkedAssetAssetGroup(map);
		
	}

	@Override
	public void delLikedAssetGroup(long id) {
		
		assetGroupDao.delLinedAssetGroup(id);
		
	}

	@Override
	public List<AssetGroup> queryGroupByAssetId(Map map) {
		// TODO Auto-generated method stub
		return assetGroupDao.queryGroupByAssetId(map);
	}

	@Override
	public void delAssetLinked(long id) {
		// TODO Auto-generated method stub
		assetGroupDao.delAssetLinked(id);	
	}

	@Override
	public void delAssetLinkedPro(Map map) {
		// TODO Auto-generated method stub
		assetGroupDao.delAssetLinkedPro(map);
	}

	@Override
	public AssetGroup queryByAssetGroupName(String assetGroupName) {
		return assetGroupDao.queryByAssetGroupName(assetGroupName);
	}
	
	

}
