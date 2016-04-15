package com.soc.model.alert;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.soc.model.asset.Asset;
import com.soc.model.user.User;

/**
 * 
 * 告警规则实体类
 * 告警规则的添加删除修改
 * 
 * @author  jiadongux
 * @version  V100R001C001
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AlertRule implements Serializable
{
    
    private static final long serialVersionUID = 1L;
    
    // 告警规则ID
    private long ruleId;
    
    // 告警规则名称
    private String ruleName;
    
    // 告警规则创建时间
    private Date ruleCreateTime;
    
    // 告警规则更新时间
    private Date ruleUpdateTime;
    
    // 告警创建人
    private String ruleCreateName;
    
    // 告警规则标记删除
    private long ruleIdIsDelete;
    
    //告警级别串
    private String ruleLevelValue;
    
    // 关联账户
    private List<User> ruleUserList;
    
    // 关联所有账户
    private List<User> allUserList;
    
    // 关联资产
    private List<Asset> ruleAssetList;
    
    // 关联所有资产
    private List<User> allAssetList;
    
    // 等级，有0和1组成（0：未选择 | 1：选择）
    private String rank;
    
    private String sourceAddress;
    
    private String startTime;
    private String endTime;
    
    private String userId;
    private String deviceCategoryId;//设备类型表tbl_device_category(DEVICE_CATEGORY_ID)
	private String assetId;//设备名称tbl_asset(ASSET_ID)
	private String definitionId;//事件类型tbl_event_definition(DEFINITION_ID)
    /*private long deviceCategoryId;
    private long property;
    private long definitionId;*/
    private Long thisUserId;
    
    //目的地址
    private String targetAddress;
    //告警动作是否开启，0：不开启，1：开启
    private int changeScript;
    private String scriptName;
    private String scriptFile;//安全策略文件名
    /**
     * 0：发送 1:不发送
     */
    private int sendMail ; 
    private int sendMessage ; 
    private int sendSyslog ; 
    private int sendPlatformAlert ; 
    
    public String getRank()
    {
        return rank;
    }
    
    public void setRank(String rank)
    {
        this.rank = rank;
    }
    
    public List<Asset> getRuleAssetList()
    {
        return ruleAssetList;
    }
    
    public void setRuleAssetList(List<Asset> ruleAssetList)
    {
        this.ruleAssetList = ruleAssetList;
    }
    
    public List<User> getRuleUserList()
    {
        return ruleUserList;
    }
    
    public void setRuleUserList(List<User> ruleUserList)
    {
        this.ruleUserList = ruleUserList;
    }
    
    public long getRuleId()
    {
        return ruleId;
    }
    
    public void setRuleId(long ruleId)
    {
        this.ruleId = ruleId;
    }
    
    public String getRuleName()
    {
        return ruleName;
    }
    
    public void setRuleName(String ruleName)
    {
        this.ruleName = ruleName;
    }
    
    public Date getRuleCreateTime()
    {
        return ruleCreateTime;
    }
    
    public void setRuleCreateTime(Date ruleCreateTime)
    {
        this.ruleCreateTime = ruleCreateTime;
    }
    
    public Date getRuleUpdateTime()
    {
        return ruleUpdateTime;
    }
    
    public void setRuleUpdateTime(Date ruleUpdateTime)
    {
        this.ruleUpdateTime = ruleUpdateTime;
    }
    
    public String getRuleCreateName()
    {
        return ruleCreateName;
    }
    
    public void setRuleCreateName(String ruleCreateName)
    {
        this.ruleCreateName = ruleCreateName;
    }
    
    public List<User> getAllUserList()
    {
        return allUserList;
    }
    
    public void setAllUserList(List<User> allUserList)
    {
        this.allUserList = allUserList;
    }
    
    public List<User> getAllAssetList()
    {
        return allAssetList;
    }
    
    public void setAllAssetList(List<User> allAssetList)
    {
        this.allAssetList = allAssetList;
    }
    
    public long getRuleIdIsDelete()
    {
        return ruleIdIsDelete;
    }
    
    public void setRuleIdIsDelete(long ruleIdIsDelete)
    {
        this.ruleIdIsDelete = ruleIdIsDelete;
    }
    
    public String getRuleLevelValue()
    {
        return ruleLevelValue;
    }
    
    public void setRuleLevelValue(String ruleLevelValue)
    {
        this.ruleLevelValue = ruleLevelValue;
    }

	public String getSourceAddress() {
		return sourceAddress;
	}

	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeviceCategoryId() {
		return deviceCategoryId;
	}

	public void setDeviceCategoryId(String deviceCategoryId) {
		this.deviceCategoryId = deviceCategoryId;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getDefinitionId() {
		return definitionId;
	}

	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}

	public Long getThisUserId() {
		return thisUserId;
	}

	public void setThisUserId(Long thisUserId) {
		this.thisUserId = thisUserId;
	}

    public String getTargetAddress()
    {
        return targetAddress;
    }

    public void setTargetAddress(String targetAddress)
    {
        this.targetAddress = targetAddress;
    }

	public int getSendMail() {
		return sendMail;
	}

	public void setSendMail(int sendMail) {
		this.sendMail = sendMail;
	}

	public int getSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(int sendMessage) {
		this.sendMessage = sendMessage;
	}

	public int getSendSyslog() {
		return sendSyslog;
	}

	public void setSendSyslog(int sendSyslog) {
		this.sendSyslog = sendSyslog;
	}

	public int getSendPlatformAlert() {
		return sendPlatformAlert;
	}

	public void setSendPlatformAlert(int sendPlatformAlert) {
		this.sendPlatformAlert = sendPlatformAlert;
	}

	public int getChangeScript() {
		return changeScript;
	}

	public void setChangeScript(int changeScript) {
		this.changeScript = changeScript;
	}

	public String getScriptName() {
		return scriptName;
	}

	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}

	public String getScriptFile() {
		return scriptFile;
	}

	public void setScriptFile(String scriptFile) {
		this.scriptFile = scriptFile;
	}
    
	
}
