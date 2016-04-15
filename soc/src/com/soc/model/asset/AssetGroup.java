package com.soc.model.asset;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 资产组实体类
 * 资产组信息的查看，编辑，删除，添加同级资产组，下级资产组
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-30]
 * @see  
 * @since  [任务管理/资产组管理/V100R001C001]
 */
public class AssetGroup implements Serializable
{
    //资产组id
    private long assetGroupId;
    //资产组名称
    private String assetGroupName;
    //父资产组id
    private long assetGroupParentId;
    //资产组创建时间
    private Date assetGroupCreateDateTime;
    //资产组更新时间
    private Date assetGroupUpdateDateTime;
    //资产组删除标识
    private int assetGroupIsDelete;
    //资产组描述
    private String assetGroupMemo;  
    //资产组的父资产组
    private AssetGroup parentGroup;
    //资产组的父级结构
    private String assetParentsFeature;
    
    private double vAVulnerabilityValue;   //脆弱性值
    
    private double riskThreatValue;        //威胁值
    
    private double assetValue;				//资产风险
    
    public long getAssetGroupId()
    {
        return assetGroupId;
    }
    
    public void setAssetGroupId(long assetGroupId)
    {
        this.assetGroupId = assetGroupId;
    }
    
    public String getAssetGroupName()
    {
        return assetGroupName;
    }
    
    public void setAssetGroupName(String assetGroupName)
    {
        this.assetGroupName = assetGroupName;
    }
    
    public long getAssetGroupParentId()
    {
        return assetGroupParentId;
    }
    
    public void setAssetGroupParentId(long assetGroupParentId)
    {
        this.assetGroupParentId = assetGroupParentId;
    }
    
    public Date getAssetGroupCreateDateTime()
    {
        return assetGroupCreateDateTime;
    }
    
    public void setAssetGroupCreateDateTime(Date assetGroupCreateDateTime)
    {
        this.assetGroupCreateDateTime = assetGroupCreateDateTime;
    }
    
    public Date getAssetGroupUpdateDateTime()
    {
        return assetGroupUpdateDateTime;
    }
    
    public void setAssetGroupUpdateDateTime(Date assetGroupUpdateDateTime)
    {
        this.assetGroupUpdateDateTime = assetGroupUpdateDateTime;
    }
    
    public int getAssetGroupIsDelete()
    {
        return assetGroupIsDelete;
    }
    
    public void setAssetGroupIsDelete(int assetGroupIsDelete)
    {
        this.assetGroupIsDelete = assetGroupIsDelete;
    }
    
    public String getAssetGroupMemo()
    {
        return assetGroupMemo;
    }
    
    public void setAssetGroupMemo(String assetGroupMemo)
    {
        this.assetGroupMemo = assetGroupMemo;
    }

    public AssetGroup getParentGroup()
    {
        return parentGroup;
    }

    public void setParentGroup(AssetGroup parentGroup)
    {
        this.parentGroup = parentGroup;
    }

    public String getAssetParentsFeature()
    {
        return assetParentsFeature;
    }

    public void setAssetParentsFeature(String assetParentsFeature)
    {
        this.assetParentsFeature = assetParentsFeature;
    }

	public double getvAVulnerabilityValue() {
		return vAVulnerabilityValue;
	}

	public void setvAVulnerabilityValue(double vAVulnerabilityValue) {
		this.vAVulnerabilityValue = vAVulnerabilityValue;
	}

	public double getRiskThreatValue() {
		return riskThreatValue;
	}

	public void setRiskThreatValue(double riskThreatValue) {
		this.riskThreatValue = riskThreatValue;
	}

	public double getAssetValue() {
		return assetValue;
	}

	public void setAssetValue(double assetValue) {
		this.assetValue = assetValue;
	}

	
    
}
