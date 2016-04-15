package com.soc.service.audit.impl;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

import com.soc.dao.audit.AuditDao;
import com.soc.dao.user.UserDao;
import com.soc.model.BaseObject;
import com.soc.model.audit.Audit;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.impl.BaseServiceImpl;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * 审计Service 
 * <对审计的相关操作：查询审计,添加审计,导出excel>
 * @author 申海广
 * @version
 * @see [相关类/方法]
 * @since soc v3.6.0.1
 */
public class AuditServiceImpl extends BaseServiceImpl implements AuditService
{
    
    //审计管理业务类
    private AuditDao auditDao;
    
    //用户管理业务类
    private UserDao userDao;
    
    /**
     * {@inheritDoc}
     */
    public int count(Map map)
    {
        // TODO Auto-generated method stub
        return auditDao.count(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public SearchResult query(Map map, Page page)
    {
        
        int rowsCount = auditDao.count(map);
        page.setTotalCount(rowsCount);
        List<Audit> list = auditDao.queryAudit(map, page.getStartIndex(), page.getPageSize());
        
        // 解析JSON
        for (Audit audit : list)
        {
            StringBuffer buf = new StringBuffer();
            JSONArray jsonArray = (JSONArray)JSONSerializer.toJSON(audit.getAuditInformation());
            List output = (List)JSONSerializer.toJava(jsonArray);
            for (Object obj : output)
            {
                
                StringBuffer bufobj = new StringBuffer((String)obj);
                bufobj.append("<br />");
                buf.append(bufobj);
            }
            audit.setAuditInformation(buf.toString());
            
        }
        // 对查找的审计列表做分页处理
        SearchResult sr = new SearchResult();
        sr.setList(list);
        sr.setPage(page);
        
        return sr;
        
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Map> auditExport(Map map)
    {
        return auditDao.auditExport(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public int insert(Audit audit)
    {
        
        return auditDao.insert(audit);
    }
    
    /**
     * {@inheritDoc}
     */
    public int insertByLoginOperator(long userId, String moduleDesc, String sourceIP, List<String> fieldList)
    {
        Audit audit = new Audit();
        
        // 行为描述
        audit.setAuditOperationProcedure("系统登录"+moduleDesc);
        // 操作者
        audit.setAuditUserId(userId);
        
        // 操作者名称
        User user = userDao.queryByUserId(userId);
        audit.setAuditUserLoginName(user.getUserLoginName());
        
        // 行为时间
        Date date = new Date();
        audit.setAuditOperationTime(date);
        // 行为IP地址
        audit.setAuditOperationIp(sourceIP);
        // 行为内容
        
        JSONArray jsonArray = (JSONArray)JSONSerializer.toJSON(fieldList.toArray());
        audit.setAuditInformation(jsonArray.toString());
        
        if (fieldList.size() > 0)
        {
            return auditDao.insert(audit);
        }
        else
        {
            return 0;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public int insertByInsertOperator(long userId, String moduleDesc, String sourceIP, BaseObject object)
    {
        
        Audit audit = new Audit();
        
        // 行为描述
        audit.setAuditOperationProcedure("新增" + moduleDesc);
        // 操作者
        audit.setAuditUserId(userId);
        
        // 操作者名称
        User user = userDao.queryByUserId(userId);
        audit.setAuditUserLoginName(user.getUserLoginName());
        
        // 行为时间
        audit.setAuditOperationTime(new Date());
        // 行为IP地址
        audit.setAuditOperationIp(sourceIP);
        // 行为内容
        List<String> fieldList = object.getFieldsList();
        
        JSONArray jsonArray = (JSONArray)JSONSerializer.toJSON(fieldList.toArray());
        audit.setAuditInformation(jsonArray.toString());
        
        return auditDao.insert(audit);
    }
    
    /**
     * {@inheritDoc}
     */
    public int insertByUpdateOperator(long userId, String moduleDesc, String sourceIP, BaseObject objectSrc,
        BaseObject objectNew)
    {
        Audit audit = new Audit();
        // 行为描述
        audit.setAuditOperationProcedure("更新" + moduleDesc);
        // 操作者
        audit.setAuditUserId(userId);
        
        // 操作者名称
        User user = userDao.queryByUserId(userId);
        audit.setAuditUserLoginName(user.getUserLoginName());
        
        // 行为时间
        audit.setAuditOperationTime(new Date());
        // 行为IP地址
        audit.setAuditOperationIp(sourceIP);
        // 行为内容
        List<String> fieldList = objectSrc.getDifferencesList(objectNew);
        
        JSONArray jsonArray = (JSONArray)JSONSerializer.toJSON(fieldList.toArray());
        audit.setAuditInformation(jsonArray.toString());
        
        if (fieldList.size() > 0)
        {
            return auditDao.insert(audit);
        }
        else
        {
            return 0;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public int insertByInsertOperator(long userId, String moduleDesc, String sourceIP, List<String> fieldList)
    {
        
        Audit audit = new Audit();
        
        // 行为描述
        audit.setAuditOperationProcedure("新增" + moduleDesc);
        // 操作者
        audit.setAuditUserId(userId);
        
        // 操作者名称
        User user = userDao.queryByUserId(userId);
        audit.setAuditUserLoginName(user.getUserLoginName());
        
        // 行为时间
        audit.setAuditOperationTime(new Date());
        // 行为IP地址
        audit.setAuditOperationIp(sourceIP);
        // 行为内容
        JSONArray jsonArray = (JSONArray)JSONSerializer.toJSON(fieldList.toArray());
        audit.setAuditInformation(jsonArray.toString());
        
        return auditDao.insert(audit);
    }
    
    /**
     * {@inheritDoc}
     */
    public int insertByUpdateOperator(long userId, String moduleDesc, String sourceIP, List<String> fieldList)
    {
        
        Audit audit = new Audit();
        
        // 行为描述
        audit.setAuditOperationProcedure("更新" + moduleDesc);
        // 操作者
        audit.setAuditUserId(userId);
        
        // 操作者名称
        User user = userDao.queryByUserId(userId);
        audit.setAuditUserLoginName(user.getUserLoginName());
        
        // 行为时间
        audit.setAuditOperationTime(new Date());
        // 行为IP地址
        audit.setAuditOperationIp(sourceIP);
        
        JSONArray jsonArray = (JSONArray)JSONSerializer.toJSON(fieldList.toArray());
        audit.setAuditInformation(jsonArray.toString());
        
        if (fieldList.size() > 0)
        {
            return auditDao.insert(audit);
        }
        else
        {
            return 0;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public int insertBySystemOperator(long userId, String moduleDesc, String sourceIP, List<String> fieldList)
    {
        
        Audit audit = new Audit();
        
        // 行为描述
        audit.setAuditOperationProcedure( moduleDesc);
        // 操作者
        audit.setAuditUserId(userId);
        
        // 操作者名称
        User user = userDao.queryByUserId(userId);
        audit.setAuditUserLoginName(user.getUserLoginName());
        
        // 行为时间
        audit.setAuditOperationTime(new Date());
        // 行为IP地址
        audit.setAuditOperationIp(sourceIP);
        
        JSONArray jsonArray = (JSONArray)JSONSerializer.toJSON(fieldList.toArray());
        audit.setAuditInformation(jsonArray.toString());
        
        if (fieldList.size() > 0)
        {
            return auditDao.insert(audit);
        }
        else
        {
            return 0;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public int insertByDeleteOperator(long userId, String moduleDesc, String sourceIP, List<String> fieldList)
    {
        
        Audit audit = new Audit();
        
        // 行为描述
        audit.setAuditOperationProcedure("删除" + moduleDesc);
        // 操作者
        audit.setAuditUserId(userId);
        
        // 操作者名称
        User user = userDao.queryByUserId(userId);
        audit.setAuditUserLoginName(user.getUserLoginName());
        
        // 行为时间
        audit.setAuditOperationTime(new Date());
        // 行为IP地址
        audit.setAuditOperationIp(sourceIP);
        
        JSONArray jsonArray = (JSONArray)JSONSerializer.toJSON(fieldList.toArray());
        audit.setAuditInformation(jsonArray.toString());
        
        if (fieldList.size() > 0)
        {
            return auditDao.insert(audit);
        }
        else
        {
            return 0;
        }
    }
    
    public AuditDao getAuditDao()
    {
        return auditDao;
    }
    
    public void setAuditDao(AuditDao auditDao)
    {
        this.auditDao = auditDao;
    }
    
    public UserDao getUserDao()
    {
        return userDao;
    }
    
    public void setUserDao(UserDao userDao)
    {
        this.userDao = userDao;
    }
    
    public SearchResult sortAudit(Map map, Page page) {
		int rowsCount = auditDao.count(map);
		page.setTotalCount(rowsCount);
		String field=(String)map.get("field");
		String sortType=(String)map.get("sortType");
		
		String str=" \""+field+"\""+" "+sortType;
		List<Audit> list = auditDao.queryAuditBySort(str, page.getStartIndex(),
				page.getPageSize());

		// 解析JSON
		for (Audit audit : list) {
			StringBuffer buf = new StringBuffer();
			JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(audit
					.getAuditInformation());
			List output = (List) JSONSerializer.toJava(jsonArray);
			for (Object obj : output) {

				StringBuffer bufobj = new StringBuffer((String) obj);
				bufobj.append("<br />");
				buf.append(bufobj);
			}
			audit.setAuditInformation(buf.toString());

		}
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);

		return sr;
	}
    
}
