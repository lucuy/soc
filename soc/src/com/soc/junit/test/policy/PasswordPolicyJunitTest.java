package com.soc.junit.test.policy;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.soc.junit.test.base.BaseTest;
import com.soc.model.policy.PasswordPolicy;
import com.soc.model.user.User;
import com.soc.service.policy.PasswordPolicyService;
import com.soc.service.user.UserService;


public class PasswordPolicyJunitTest extends BaseTest{

	public PasswordPolicyJunitTest()  {
    }
	private UserService uservice;
	private PasswordPolicyService ppService;
	
	@Before
	public void init(){
		super.init();
		uservice = (UserService) super.getBean("userManager");
		ppService = (PasswordPolicyService) super.getBean("passwordPolicyManager");
	}

	
	
    @Ignore
    public void getAllUserTest() {
       List<User> userlist = uservice.query(null);
       for (User user : userlist) {
    	   //System.out.println(user.getUserId()+"\t"+user.getUserLoginName()+"\t"+user.getUserPassword());
       }
    }
    
    @Ignore
  	public void getAllPasswordPolicyCountTest(){
      	//System.out.println(ppService.count(null));
      }
    
    @Ignore
    public void getAllPasswordPolicyTest(){
    	List<PasswordPolicy> list = ppService.query(null);
    	for (PasswordPolicy obj : list) {
			//System.out.println(obj.getPasswordPolicyId()+"\t\t"+obj.getPasswordPolicyName());
		}
    }
    
  
    @Ignore
    public void getAllPasswordPolicyByMapTest(){
    		
			Map<String, Object> map = new HashMap<String, Object>();
			//设置模糊查询条件
			map.put("selpasswordPolicyName", "123");
			// 设置密码策略显示
			map.put("display", 0);
			List<PasswordPolicy> list = ppService.query(map);
	    	for (PasswordPolicy obj : list) {
				//System.out.println(obj.getPasswordPolicyId()+"\t\t"+obj.getPasswordPolicyName());
			}
		
    }
    
    @Test
    public void deletePasswordPolicyTest(){
    	  ppService.deletePasswordPolicy(11);
    	  Map<String, Object> map = new HashMap<String, Object>();
          map.put("display", 0);
          List<PasswordPolicy> list = ppService.query(map);
          for (PasswordPolicy obj : list) {
 			//System.out.println(obj.getPasswordPolicyId()+"\t\t"+obj.getPasswordPolicyName());
          }
    }
    
    @Ignore
    public void updatePasswordPolicyStatusTest(){
         
         ppService.updatePasswordPolicyStatus(11, 1);
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("display", 0);
         List<PasswordPolicy> list = ppService.query(map);
         for (PasswordPolicy obj : list) {
			//System.out.println(obj.getPasswordPolicyId()+"\t\t"+obj.getPasswordPolicyName());
         }
         
    }
    
    @Ignore
    public void updatePasswordPolicyTest(){
    	
    		PasswordPolicy passwordPolicy = new PasswordPolicy();
    		//设置密码策略的Id
    		passwordPolicy.setPasswordPolicyId(10);
    		//设置密码策略的名称
    		passwordPolicy.setPasswordPolicyName("test10");
    		//设置密码策略的修改时间
    		passwordPolicy.setPasswordPolicyUpdateDateTime(new Date());
            // 设置密码策略可见
            passwordPolicy.setPasswordPolicyIsDelete(0);
            // 设置密码策略激活状态
            passwordPolicy.setPasswordPolicyStatus(1);
            // 执行更新操作
            ppService.update(passwordPolicy);
    }
  
    @Test
    public void savePasswordPoicyTest(){
    	PasswordPolicy passwordPolicy = new PasswordPolicy();
		//设置密码策略的名称
		passwordPolicy.setPasswordPolicyName("test10");
		//设置密码策略的修改时间
		passwordPolicy.setPasswordPolicyUpdateDateTime(new Date());
        // 设置密码策略可见
        passwordPolicy.setPasswordPolicyIsDelete(0);
        // 设置密码策略激活状态
        passwordPolicy.setPasswordPolicyStatus(1);
        // 执行更新操作
        ppService.update(passwordPolicy);
        
        
        getAllPasswordPolicyCountTest();
    }
    
}
