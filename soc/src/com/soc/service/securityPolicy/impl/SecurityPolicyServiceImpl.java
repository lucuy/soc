/*
 * 文 件 名:  TimePolicyServiceImpl.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改安全:  2012-8-11
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.service.securityPolicy.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.alert.AlertRuleDao;
import com.soc.dao.securityPolicy.SecurityPolicyDao;
import com.soc.model.asset.Asset;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.securityPolicy.SecurityPolicy;
import com.soc.service.securityPolicy.SecurityPolicyService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.util.ReadAndWriteFileUtil;
import com.util.linkMethod.SSHMode;
import com.util.linkMethod.TelnetMode;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 安全策略Service实现类
 * 安全策略的查询，修改，添加，删除，安全策略的状态修改
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-11]
 * @see  com.soc.dao.policy.TimePolicyDao
 * @since  [用户管理/安全策略管理/V100R001C001]
 */
public class SecurityPolicyServiceImpl implements SecurityPolicyService
{
    
    //安全管理策略业务对象
    private SecurityPolicyDao securityPolicyDao;
    //告警规则业务对象
    private AlertRuleDao alertRuleDao;
    private String set = "1";
    
    /**
    * {@inheritDoc}
    */
    public int count(Map map)
    {
        
        return securityPolicyDao.count(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public SearchResult queryPolicy(Map map, Page page)
    {
        int rowsCount = securityPolicyDao.count(map);
        page.setTotalCount(rowsCount);
        List<SecurityPolicy> list = securityPolicyDao.queryPolicy(map, page.getStartIndex(), page.getPageSize());
        // 对查找的结果做分页处理
        SearchResult sr = new SearchResult();
        sr.setList(list);
        sr.setPage(page);
        return sr;
    }
    
 
    
   
    
    /**
    * {@inheritDoc}
    */
    public SecurityPolicy queryPolicyById(int policyId)
    {
        return securityPolicyDao.queryPolicyById(policyId);
    }
    
    /**
    * {@inheritDoc}
    */
    public void deletePolicy(int id)
    {
    	@SuppressWarnings("unused")
		SecurityPolicy policy = this.queryPolicyById(id);
    	alertRuleDao.updateRuleScpriptByScpriptName(policy.getPolicyName());
        Map<String, Object> map = new HashMap<String, Object>();
        //将传递过来的id放到map内
        map.put("timePolicyTd", id);
        //将当前安全存到map内       
        map.put("timePolicyUpdateDateTime", new Date());
        //将删除标记设置成1,存入map
        map.put("timePolicyIsDelete", 1);
        
        securityPolicyDao.deletePolicy(map);
    }
    
 
    
    /**
     * {@inheritDoc}
     */
    public void updatePolicy(SecurityPolicy tp)
    {
        
        //判断策略对象的Id是否为空，如果为空执行更新操作,不为空执行添加操作
        if (tp.getId() != 0)
        {
            securityPolicyDao.updatePolicy(tp);
        }
        else
        {
            securityPolicyDao.insertPolicy(tp);
          //新建文件夹
			File file = new File(GlobalConfig.securityPolicyFilePath+File.separator+tp.getId());
			file.mkdir();
        }
    }
    
    /** {@inheritDoc} */
    
    @Override
    public List<SecurityPolicy> queryByPolicyName(String policyName)
    {
        // TODO Auto-generated method stub
        return securityPolicyDao.queryByPolicyName(policyName);
    }

	public SecurityPolicyDao getSecurityPolicyDao() {
		return securityPolicyDao;
	}

	public void setSecurityPolicyDao(SecurityPolicyDao securityPolicyDao) {
		this.securityPolicyDao = securityPolicyDao;
	}
/**
 * 下发配置文件
 */

	@Override
	public String issuedPolicy(Asset asset, int policyId, String path) {
		SecurityPolicy policity= this.queryPolicyById(policyId);
		String policityFileName = policity.getRelPolicyName();
		SSHMode sshMode = new SSHMode(); 
		TelnetMode telnetMode = new TelnetMode();
		String script = ReadAndWriteFileUtil.readFileContent(path+"/"+policityFileName);
		script = script.replace("\n", "  &&  ");
		if (asset.getSecurityLinkeThod().equals("SSH")) {
			
			return sshMode.linkAsset(asset, script,path+"/"+policyId+"/");
		}else {
			return telnetMode.linkAsset(asset, script,path+"/"+policyId+"/");
		}
		
	}

@Override
public List<SecurityPolicy> queryAllSecurityPolicy() {
	List<SecurityPolicy> list =new ArrayList<SecurityPolicy>();
	try {
		list = securityPolicyDao.queryAllSecurityPolicy();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

@Override
public void actionPolicy(Asset asset, String relPolicyName, String path) {
	String script = ReadAndWriteFileUtil.readFileContent(path+"/"+relPolicyName.trim());
	SSHMode sshMode = new SSHMode(); 
	TelnetMode telnetMode = new TelnetMode();
	script = script.replace("\n", "  &&  ");
	if (asset.getSecurityLinkeThod().equals("SSH")) {
		 sshMode.linkAsset(asset, script,"");
	}else {
		 telnetMode.linkAsset(asset, script,"");
	}
}


public AlertRuleDao getAlertRuleDao() {
	return alertRuleDao;
}

public void setAlertRuleDao(AlertRuleDao alertRuleDao) {
	this.alertRuleDao = alertRuleDao;
}



	
@Override
public int countOfName(String name) {
	return this.securityPolicyDao.countOfName(name);
}

    
}
