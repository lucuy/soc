package com.soc.model.asset;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 
 * 资产实体类
 * 资产的显示，添加，修改，删除
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-29]
 * @see  
 * @since  [任务管理/历史资产管理/V100R001C001]
 */
public class Asset implements Serializable
{
	
	public static void main(String[] args) {
		
		String s = "2014-10-16 14:18:16" ; 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date =  sdf.parse(s);
			System.out.println(date.getTime());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
    //资产id
    private long assetId;
    
    
    //资产名称
    private String assetName;
    
    //资产IP
    private long assetIp;
    
    private String assetIps;
    
    //资产网关
    private long assetGateWay;
    
    //
    private String assetGateWays;
    
    //资产IP段
    private long assetSegMent;
    
    //资产重要性
    private int assetImportance;
    
    //资产主机名称
    private String assetHostName;
    
    //资产mac地址
    private String assetMac;
    
    //创建时间
    private Date assetCreateDateTime;
    
    //更新时间
    private Date assetUpdateDateTime;
    
    //创建者登录名
    private String assetUserLoginName;
    
    //资产节点id
    private long assetNodeId;
    
    //资产别名
    private String assetAliasName;
    
    //资产待定
    private int assetUnName;
    
    //团体名
    private String organizationName;
    
    //版本
    private String version;
    
    //监视目录
    private String directorise;
    
    //监视文件
    private String file;
    
    //资产标志
    private String assetWorkIdent;
    
    //标记删除标识
    private int assetIsDelete;
    
    //资产描述
    private String assetMemo;
    
    //资产所属组
    //private AssetGroup assetGroup;
    //所属资产组名称
    private String assetGroupName;
    
    //所属组的架构
    private String assetGroupFeature;
    
    //采集器id
    private long assetCollectorId;
    
    //采集器名称
    private String assetCollectorName;
    
    //采集器mac
    private String assetCollectorMac;
    
    //采集器类型
    private String assetCollectType;
    
    //设备类型名称
    private String assetDeviceType;
    
    //支持设备名称
    private String assetSupportDevice;
    
    //设备类型id
    private long assetDeviceTypeId;
    
    //支持设备id
    private long assetSupportDeviceId;
    
    //特权命令
    private String assetPrivilegeCommand;
    
    //资产状态|启用。停用
    private int assetStatus;
    
    //支持设备代码
    private String assetDeviceTypeCode;
    
    private String assetSupportDeviceTypeCode;
    
    //资产关联的规则
    private String assetDeviceRules;
    
    //资产的端口
    private String assetPort;
    
    //轮循日志
    private String assetLog;
    
    //APM端口
    private String assetAPM;
    
    //实时监控
    private String assetAPMMonitor;
    
    //保密性价值
    private int secretValue;
    
    //完整性价值 
    private int integrityValue;
    
    //可用性价值
    private int usabilityValue;
    
    //资产风险值
    private double assetValue;
    
    private double vAVulnerabilityValue;   //脆弱性值
    
    private double riskThreatValue;        //威胁值

    //资产下发状态
    private int assetIssued ; 

    private int asset_answer_user_id; 	//响应用户id
    
    private String asset_answer_userName;
    
    private int asset_pessponsibility_user_id; 	//责任人id
    
    private String asset_pessonsibility_userName;
    
    private int asset_system_id;		 	//资源系统id
    
    private String asset_system_name;
    
    private int asset_system_version;	//资源系统版本
    
    private String asset_system_version_name;
    
    private int asset_system_versionno;	//资源系统版本号
    
    private String asset_system_versionno_name;
    
    private int asset_system_brand;		//资源系统品牌
    
    private String asset_system_brand_name;
    
    private String assetProperty1;
    
    private String assetPropertyValue1;
    
    private String assetProperty2;
    
    private String assetPropertyValue2;
    
    private String assetProperty3;
    
    private long assetPropertyValue3;
    
    private String assetProperty4;
    
    private Date assetPropertyValue4;
    
    private String assetProperty5;
    
    private Date assetPropertyValue5;
   
    private List<AssetGroup> assetGrouplistt;
    private int securityPort;//端口号
    private String securityUserName; //用户名
    private String securityPWD; //密码
    private String securityLinkeThod; //连接方式
    
  //是否在线  ：   true 在线 false 不在线 
  	private boolean isOnLine;
    
    
    public boolean getIsOnLine() {
		return isOnLine;
	}

	public void setIsOnLine(boolean isOnLine) {
		this.isOnLine = isOnLine;
	}
    
    
    public int getSecurityPort() {
		return securityPort;
	}

	public void setSecurityPort(int securityPort) {
		this.securityPort = securityPort;
	}

	public String getSecurityUserName() {
		return securityUserName;
	}

	public void setSecurityUserName(String securityUserName) {
		this.securityUserName = securityUserName;
	}

	public String getSecurityPWD() {
		return securityPWD;
	}

	public void setSecurityPWD(String securityPWD) {
		this.securityPWD = securityPWD;
	}

	public String getSecurityLinkeThod() {
		return securityLinkeThod;
	}

	public void setSecurityLinkeThod(String securityLinkeThod) {
		this.securityLinkeThod = securityLinkeThod;
	}

	public int getSecretValue()
    {
        return secretValue;
    }
    
    public void setSecretValue(int secretValue)
    {
        this.secretValue = secretValue;
    }
    
    public int getIntegrityValue()
    {
        return integrityValue;
    }
    
    public void setIntegrityValue(int integrityValue)
    {
        this.integrityValue = integrityValue;
    }
    
    public int getUsabilityValue()
    {
        return usabilityValue;
    }
    
    public void setUsabilityValue(int usabilityValue)
    {
        this.usabilityValue = usabilityValue;
    }
    
    public long getAssetId()
    {
        return assetId;
    }
    
    public void setAssetId(long assetId)
    {
        this.assetId = assetId;
    }
    
    public String getAssetName()
    {
        return assetName;
    }
    
    public void setAssetName(String assetName)
    {
        this.assetName = assetName;
    }
    
    public int getAssetImportance()
    {
        return assetImportance;
    }
    
    public void setAssetImportance(int assetImportance)
    {
        this.assetImportance = assetImportance;
    }
    
    public String getAssetHostName()
    {
        return assetHostName;
    }
    
    public void setAssetHostName(String assetHostName)
    {
        this.assetHostName = assetHostName;
    }
    
    public String getAssetMac()
    {
        return assetMac;
    }
    
    public void setAssetMac(String assetMac)
    {
        this.assetMac = assetMac;
    }
    
    public Date getAssetCreateDateTime()
    {
        return assetCreateDateTime;
    }
    
    public void setAssetCreateDateTime(Date assetCreateDateTime)
    {
        this.assetCreateDateTime = assetCreateDateTime;
    }
    
    public Date getAssetUpdateDateTime()
    {
        return assetUpdateDateTime;
    }
    
    public void setAssetUpdateDateTime(Date assetUpdateDateTime)
    {
        this.assetUpdateDateTime = assetUpdateDateTime;
    }
    
    public String getAssetUserLoginName()
    {
        return assetUserLoginName;
    }
    
    public void setAssetUserLoginName(String assetUserLoginName)
    {
        this.assetUserLoginName = assetUserLoginName;
    }
    
    public long getAssetNodeId()
    {
        return assetNodeId;
    }
    
    public void setAssetNodeId(long assetNodeId)
    {
        this.assetNodeId = assetNodeId;
    }
    
    public int getAssetIsDelete()
    {
        return assetIsDelete;
    }
    
    public void setAssetIsDelete(int assetIsDelete)
    {
        this.assetIsDelete = assetIsDelete;
    }
    
    public String getAssetMemo()
    {
        return assetMemo;
    }
    
    public void setAssetMemo(String assetMemo)
    {
        this.assetMemo = assetMemo;
    }
    
    public String getAssetGroupName()
    {
        return assetGroupName;
    }
    
    public void setAssetGroupName(String assetGroupName)
    {
        this.assetGroupName = assetGroupName;
    }
    
    public String getAssetGroupFeature()
    {
        return assetGroupFeature;
    }
    
    public void setAssetGroupFeature(String assetGroupFeature)
    {
        this.assetGroupFeature = assetGroupFeature;
    }
    
    public String getAssetAliasName()
    {
        return assetAliasName;
    }
    
    public void setAssetAliasName(String assetAliasName)
    {
        this.assetAliasName = assetAliasName;
    }
    
    public String getAssetWorkIdent()
    {
        return assetWorkIdent;
    }
    
    public void setAssetWorkIdent(String assetWorkIdent)
    {
        this.assetWorkIdent = assetWorkIdent;
    }
    
    public String getAssetIps()
    {
        return assetIps;
    }
    
    public void setAssetIps(String assetIps)
    {
        this.assetIps = assetIps;
    }
    
    public String getAssetGateWays()
    {
        return assetGateWays;
    }
    
    public void setAssetGateWays(String assetGateWays)
    {
        this.assetGateWays = assetGateWays;
    }
    
    public long getAssetIp()
    {
        return assetIp;
    }
    
    public void setAssetIp(long assetIp)
    {
        this.assetIp = assetIp;
    }
    
    public long getAssetGateWay()
    {
        return assetGateWay;
    }
    
    public void setAssetGateWay(long assetGateWay)
    {
        this.assetGateWay = assetGateWay;
    }
    
    public long getAssetSegMent()
    {
        return assetSegMent;
    }
    
    public void setAssetSegMent(long assetSegMent)
    {
        this.assetSegMent = assetSegMent;
    }
    
    public long getAssetCollectorId()
    {
        return assetCollectorId;
    }
    
    public void setAssetCollectorId(long assetCollectorId)
    {
        this.assetCollectorId = assetCollectorId;
    }
    
    public String getAssetCollectorName()
    {
        return assetCollectorName;
    }
    
    public void setAssetCollectorName(String assetCollectorName)
    {
        this.assetCollectorName = assetCollectorName;
    }
    
    public String getAssetCollectorMac()
    {
        return assetCollectorMac;
    }
    
    public void setAssetCollectorMac(String assetCollectorMac)
    {
        this.assetCollectorMac = assetCollectorMac;
    }
    
    public String getAssetCollectType()
    {
        return assetCollectType;
    }
    
    public void setAssetCollectType(String assetCollectType)
    {
        this.assetCollectType = assetCollectType;
    }
    
    public String getAssetDeviceType()
    {
        return assetDeviceType;
    }
    
    public void setAssetDeviceType(String assetDeviceType)
    {
        this.assetDeviceType = assetDeviceType;
    }
    
    public String getDirectorise()
    {
        return directorise;
    }
    
    public void setDirectorise(String directorise)
    {
        this.directorise = directorise;
    }
    
    public String getFile()
    {
        return file;
    }
    
    public void setFile(String file)
    {
        this.file = file;
    }
    
    public int getAssetUnName()
    {
        return assetUnName;
    }
    
    public void setAssetUnName(int assetUnName)
    {
        this.assetUnName = assetUnName;
    }
    
    public String getVersion()
    {
        return version;
    }
    
    public void setVersion(String version)
    {
        this.version = version;
    }
    
    public String getOrganizationName()
    {
        return organizationName;
    }
    
    public void setOrganizationName(String organizationName)
    {
        this.organizationName = organizationName;
    }
    
    public long getAssetDeviceTypeId()
    {
        return assetDeviceTypeId;
    }
    
    public void setAssetDeviceTypeId(long assetDeviceTypeId)
    {
        this.assetDeviceTypeId = assetDeviceTypeId;
    }
    
    public String getAssetSupportDevice()
    {
        return assetSupportDevice;
    }
    
    public void setAssetSupportDevice(String assetSupportDevice)
    {
        this.assetSupportDevice = assetSupportDevice;
    }
    
    public long getAssetSupportDeviceId()
    {
        return assetSupportDeviceId;
    }
    
    public void setAssetSupportDeviceId(long assetSupportDeviceId)
    {
        this.assetSupportDeviceId = assetSupportDeviceId;
    }
    
    public String getAssetPrivilegeCommand()
    {
        return assetPrivilegeCommand;
    }
    
    public void setAssetPrivilegeCommand(String assetPrivilegeCommand)
    {
        this.assetPrivilegeCommand = assetPrivilegeCommand;
    }
    
    public int getAssetStatus()
    {
        return assetStatus;
    }
    
    public void setAssetStatus(int assetStatus)
    {
        this.assetStatus = assetStatus;
    }
    
    public String getAssetDeviceTypeCode()
    {
        return assetDeviceTypeCode;
    }
    
    public void setAssetDeviceTypeCode(String assetDeviceTypeCode)
    {
        this.assetDeviceTypeCode = assetDeviceTypeCode;
    }
    
    public String getAssetSupportDeviceTypeCode()
    {
        return assetSupportDeviceTypeCode;
    }
    
    public void setAssetSupportDeviceTypeCode(String assetSupportDeviceTypeCode)
    {
        this.assetSupportDeviceTypeCode = assetSupportDeviceTypeCode;
    }
    
    public String getAssetDeviceRules()
    {
        return assetDeviceRules;
    }
    
    public void setAssetDeviceRules(String assetDeviceRules)
    {
        this.assetDeviceRules = assetDeviceRules;
    }
    
    public String getAssetPort()
    {
        return assetPort;
    }
    
    public void setAssetPort(String assetPort)
    {
        this.assetPort = assetPort;
    }
    
    public String getAssetLog()
    {
        return assetLog;
    }
    
    public void setAssetLog(String assetLog)
    {
        this.assetLog = assetLog;
    }
    
    public String getAssetAPM()
    {
        return assetAPM;
    }
    
    public void setAssetAPM(String assetAPM)
    {
        this.assetAPM = assetAPM;
    }
    
    public String getAssetAPMMonitor()
    {
        return assetAPMMonitor;
    }
    
    public void setAssetAPMMonitor(String assetAPMMonitor)
    {
        this.assetAPMMonitor = assetAPMMonitor;
    }

   

	public double getAssetValue() {
		return assetValue;
	}

	public void setAssetValue(double assetValue) {
		this.assetValue = assetValue;
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


	public int getAssetIssued() {
		return assetIssued;
	}

	public void setAssetIssued(int assetIssued) {
		this.assetIssued = assetIssued;
	}


	public int getAsset_answer_user_id() {
		return asset_answer_user_id;
	}

	public void setAsset_answer_user_id(int asset_answer_user_id) {
		this.asset_answer_user_id = asset_answer_user_id;
	}

	public int getAsset_pessponsibility_user_id() {
		return asset_pessponsibility_user_id;
	}

	public void setAsset_pessponsibility_user_id(int asset_pessponsibility_user_id) {
		this.asset_pessponsibility_user_id = asset_pessponsibility_user_id;
	}

	public int getAsset_system_id() {
		return asset_system_id;
	}

	public void setAsset_system_id(int asset_system_id) {
		this.asset_system_id = asset_system_id;
	}

	public int getAsset_system_version() {
		return asset_system_version;
	}

	public void setAsset_system_version(int asset_system_version) {
		this.asset_system_version = asset_system_version;
	}

	public int getAsset_system_versionno() {
		return asset_system_versionno;
	}

	public void setAsset_system_versionno(int asset_system_versionno) {
		this.asset_system_versionno = asset_system_versionno;
	}

	public int getAsset_system_brand() {
		return asset_system_brand;
	}

	public void setAsset_system_brand(int asset_system_brand) {
		this.asset_system_brand = asset_system_brand;
	}

	public String getAssetProperty1() {
		return assetProperty1;
	}

	public void setAssetProperty1(String assetProperty1) {
		this.assetProperty1 = assetProperty1;
	}

	public String getAssetPropertyValue1() {
		return assetPropertyValue1;
	}

	public void setAssetPropertyValue1(String assetPropertyValue1) {
		this.assetPropertyValue1 = assetPropertyValue1;
	}

	public String getAssetProperty2() {
		return assetProperty2;
	}

	public void setAssetProperty2(String assetProperty2) {
		this.assetProperty2 = assetProperty2;
	}

	public String getAssetPropertyValue2() {
		return assetPropertyValue2;
	}

	public void setAssetPropertyValue2(String assetPropertyValue2) {
		this.assetPropertyValue2 = assetPropertyValue2;
	}

	public String getAssetProperty3() {
		return assetProperty3;
	}

	public void setAssetProperty3(String assetProperty3) {
		this.assetProperty3 = assetProperty3;
	}

	public long getAssetPropertyValue3() {
		return assetPropertyValue3;
	}

	public void setAssetPropertyValue3(long assetPropertyValue3) {
		this.assetPropertyValue3 = assetPropertyValue3;
	}

	public String getAssetProperty4() {
		return assetProperty4;
	}

	public void setAssetProperty4(String assetProperty4) {
		this.assetProperty4 = assetProperty4;
	}

	public Date getAssetPropertyValue4() {
		return assetPropertyValue4;
	}

	public void setAssetPropertyValue4(Date assetPropertyValue4) {
		this.assetPropertyValue4 = assetPropertyValue4;
	}

	public String getAssetProperty5() {
		return assetProperty5;
	}

	public void setAssetProperty5(String assetProperty5) {
		this.assetProperty5 = assetProperty5;
	}

	public Date getAssetPropertyValue5() {
		return assetPropertyValue5;
	}

	public void setAssetPropertyValue5(Date assetPropertyValue5) {
		this.assetPropertyValue5 = assetPropertyValue5;
	}

	public List<AssetGroup> getAssetGrouplistt() {
		return assetGrouplistt;
	}

	public void setAssetGrouplistt(List<AssetGroup> assetGrouplistt) {
		this.assetGrouplistt = assetGrouplistt;
	}

	public String getAsset_answer_userName() {
		return asset_answer_userName;
	}

	public void setAsset_answer_userName(String asset_answer_userName) {
		this.asset_answer_userName = asset_answer_userName;
	}

	public String getAsset_pessonsibility_userName() {
		return asset_pessonsibility_userName;
	}

	public void setAsset_pessonsibility_userName(
			String asset_pessonsibility_userName) {
		this.asset_pessonsibility_userName = asset_pessonsibility_userName;
	}

	public String getAsset_system_name() {
		return asset_system_name;
	}

	public void setAsset_system_name(String asset_system_name) {
		this.asset_system_name = asset_system_name;
	}

	public String getAsset_system_version_name() {
		return asset_system_version_name;
	}

	public void setAsset_system_version_name(String asset_system_version_name) {
		this.asset_system_version_name = asset_system_version_name;
	}

	public String getAsset_system_versionno_name() {
		return asset_system_versionno_name;
	}

	public void setAsset_system_versionno_name(String asset_system_versionno_name) {
		this.asset_system_versionno_name = asset_system_versionno_name;
	}

	public String getAsset_system_brand_name() {
		return asset_system_brand_name;
	}

	public void setAsset_system_brand_name(String asset_system_brand_name) {
		this.asset_system_brand_name = asset_system_brand_name;
	}

    
}
