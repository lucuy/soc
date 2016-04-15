package com.compliance.model.rank;

import java.util.Date;

/**
 * Description：定级
 * 
 * @author quyongkun
 * @version 1.0 2013-5-13
 */
public class Rank {
	/**
	 * 主键
	 */
	private int rankId;

	/**
	 * 信息系统名称
	 */
	private String sysInfoName;

	/**
	 * 信息系统编号
	 */
	private String sysInfoId;
	
	/**
	 * 信息系统业务描述
	 */
	private String sysInfoBusDescription;

	/**
	 * 测评单位名称
	 */
	private String rankEvalUnitName;

	/**
	 * 投入使用时间
	 */
	private Date rankUseDate;

	/**
	 * 是否是子系统：0：否，1：是
	 */
	private String rankFlag;

	/**
	 * 上级系统名称
	 */
	private String rankParentSysName;

	/**
	 * 上级所属单位名称
	 */
	private String rankParentUnitName;

	/**
	 * 测评相关附件
	 */
	private String rankEvalRelAccess;
	
	/**
	 * 测评相关附件重命名
	 */
	private String reRankEvalRelAccess;

	/**
	 * 覆盖范围 ：1：局域网，2：城域网，3：广域网，4：其它，
	 */
	private String rankCoveArea;

	/**
	 * 其他覆盖范围名称
	 */
	private String rankOthArea;

	/**
	 * 网络性质:1：业务专网，2：互联网 ，3:其它
	 */
	private String rankNetworkProp;

	/**
	 * 其他网络性质名称
	 */
	private String rankOthNetworkProp;

	/**
	 * 系统互联情况 ：1：与其他行业系统连接，2：与本行业其他单位系统连接，3：与本单位其他系统连接，4：其它，
	 */
	private String rankSysConn;

	/**
	 * 其他系统互联情况
	 */
	private String rankOtherSysConn;

	/**
	 * 安全专用产品数量
	 */
	private String rankSecCount;

	/**
	 * 安全专用产品使用国产品率: 全部使用：100，全部未使用：0，部分使用：3
	 */
	private String rankSecUse;

	/**
	 * 0-100
	 */
	private String partRankSecUse;

	/**
	 * 网络产品数量
	 */
	private String rankNetCount;

	/**
	 * 网络产品使用国产品率 : 全部使用：100，全部未使用：0，部分使用：3
	 */
	private String rankNetUse;
	
	/**
	 * 0-100
	 */
	private String partRankNetUse;
	
	

	/**
	 * 操作系统数量
	 */
	private String rankSysCount;

	/**
	 * 操作系统使用国产品率 : 全部使用：100，全部未使用：0，部分使用：3
	 */
	private String rankSysUse;

	/**
	 * 0-100
	 */
	private String partRankSysUse;
	

	/**
	 * 数据库数量
	 */
	private String rankSqlCount;

	/**
	 * 数据库使用情况: 全部使用：100，全部未使用：0，部分使用：3
	 */
	private String rankSqlUse;

	/**
	 * 0-100
	 */
	private String partRankSqlUse;
	

	/**
	 * 服务器数量
	 */
	private String rankSerCount;

	/**
	 * 服务器使用国产品率 : 全部使用：100，全部未使用：0，部分使用：3
	 */
	private String rankSerUse;

	/**
	 * 0-100
	 */
	private String partRankSerUse;
	

	/**
	 * 其他产品类型名称
	 */
	private String rankOthProd;

	/**
	 * 其他产品类型数量
	 */
	private String rankOthProdCount;

	/**
	 * 其他产品类型使用国产品率 : 全部使用：100，全部未使用：0，部分使用：3
	 */
	private String rankOthProdUse;

	/**
	 * 0-100
	 */
	private String partRankOthProdUse;
	

	/**
	 * 是否有等级测评 无：0：有：1
	 */
	private String rankIfGradeEval;

	/**
	 * 等级测评服务责任方类型 ：1：本行业（单位），2：国内其他服务商，3：国外服务商
	 */
	private String rankSerGradeType;

	/**
	 * 是否有风险测评 无：0：有：1
	 */
	private String rankIfRiskEval;

	/**
	 * 风险评估服务责任方类型：1：本行业（单位），2：国内其他服务商，3：国外服务商
	 */
	private String rankSerRiskType;

	/**
	 * 是否有灾难恢复 无：0：有：1
	 */
	private String rankIfSuffReco;

	/**
	 * 灾难恢复服务责任方类型：1：本行业（单位），2：国内其他服务商，3：国外服务商
	 */
	private String rankIfSuffRecoType;

	/**
	 * 是否有应急响应 无：0：有：1
	 */
	private String rankIfResponse;

	/**
	 * 应急响应服务责任方类型：1：本行业（单位），2：国内其他服务商，3：国外服务商：
	 */
	private String rankResponseType;

	/**
	 * 是否有系统集成 无：0：有：1
	 */
	private String rankIfSysInte;

	/**
	 * 系统集成服务责任方类型：1：本行业（单位），2：国内其他服务商，3：国外服务商
	 */
	private String rankSysInteType;

	/**
	 * 是否有安全咨询 无：0：有：1
	 */
	private String rankIfSecCon;

	/**
	 * 安全咨询服务责任方类型：1：本行业（单位），2：国内其他服务商，3：国外服务商
	 */
	private String rankSecConypeType;

	/**
	 * 是否有安全培训 无：0：有：1
	 */
	private String rankIfSecTrain;

	/**
	 * 安全培训服务责任方类型：1：本行业（单位），2：国内其他服务商，3：国外服务商
	 */
	private String rankSecTrainType;

	/**
	 * 其他服务类型名称
	 */
	private String rankOthSerName;

	/**
	 * 是否有其他服务类型 无：0：有：1
	 */
	private String rankIfOthSer;

	/**
	 * 其他类型服务责任方类型：1：本行业（单位），2：国内其他服务商，3：国外服务商
	 */
	private String rankOthUseType;

	/**
	 * 机构类别
	 */
	private String rankOrganType;

	/**
	 * 机构区域
	 */
	private String rankOrganArea;

	/**
	 * 业务种类
	 */
	private String rankBusinessType;

	/**
	 * 信息系统说明
	 */
	private String rankInfoSysIntr;

	/**
	 * 等级
	 */
	private String rankGrade;

	/**
	 * 定级时间
	 */
	private Date rankTime;

	/**
	 * 专家评审 0:未审批，1：已审批
	 */
	private String rankJudge;

	/**
	 * 主管部门是否存在 0:不存在，1：存在
	 */
	private String rankIsDep;

	/**
	 * '主管部门名称
	 */
	private String rankDepName;

	/**
	 * 主管部门审批情况 0:未审批，1：已审批
	 */
	private String rankDepJudge;

	/**
	 * 定级报告是否存在，0：无，1：有
	 */
	private String rankDoc;

	/**
	 * 定级报告附件
	 */
	private String rankAccess;
	
	/**
	 * 重命名定级报告附件
	 */
	private String reRankAccess;

	/**
	 * '填报人
	 */
	private String rankInformant;

	/**
	 * 填报日期
	 */
	private Date rankDate;

	/**
	 * 拓扑结构图有、无，0：无，1：有
	 */
	private String rankTopStruct;

	/**
	 * 拓扑结构附件
	 */
	private String rankTopRelAcc;
	
	/**
	 * 重命名拓扑结构附件
	 */
	private String reRankTopRelAcc;

	/**
	 * 系统安全组织机构及管理是否存在，0：无，1：有
	 */
	private String rankSysManage;

	/**
	 * 系统安全组织机构管理附件
	 */
	private String rankSysManRel;
	
	/**
	 * 系统安全组织机构管理附件
	 */
	private String reRankSysManRel;

	/**
	 * 系统安全保护设施方案或改建实施方案有、无，0：无，1：有
	 */
	private String rankSysPlan;

	/**
	 * 系统安全保护设施方案或改建实施方案附件
	 */
	private String rankSysPlanRel;
	
	/**
	 * 重命名系统安全保护设施方案或改建实施方案附件
	 */
	private String reRankSysPlanRel;	 
	
	/**
	 * '系统使用的安全产品清单及认证、销售许可证明有、无，0：无、1：有
	 */
	private String rankSysLicense;

	/**
	 * 系统使用的安全产品清单及认证、销售许可证明附件
	 */
	private String rankSysLiceRel;
	
	/**
	 * 重命名系统使用的安全产品清单及认证、销售许可证明附件
	 */
	private String reRankSysLiceRel;

	/**
	 * 系统等级测评报告有、无，0：无、1：有
	 */
	private String rankSysReport;

	/**
	 * 系统等级测评报告附件
	 */
	private String rankSysReportRel;
	
	/**
	 * 重命名系统等级测评报告附件
	 */
	private String reRrankSysReportRel;

	/**
	 * 专家评审有、无，0：有、1：无
	 */
	private String rankPeerRev;

	/**
	 * 专家评审附件
	 */
	private String rankPeerRevRel;
	
	/**
	 * 重命名专家评审附件
	 */
	private String reRankPeerRevRel;

	/**
	 * 上级部门审批意见附件
	 */
	private String rankSuperOpinRel;
	
	/**
	 * 重命名上级部门审批意见附件
	 */
	private String reRankSuperOpinRel;

	/**
	 * 上级部门审批意见有、无，0：无，1：有
	 */
	private String rankSuperOpin;

	/**
	 * 定级次数
	 */
	private String rankCount;

	public Rank() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getRankId() {
		return rankId;
	}

	public void setRankId(int rankId) {
		this.rankId = rankId;
	}

	public String getSysInfoName() {
		return sysInfoName;
	}

	public void setSysInfoName(String sysInfoName) {
		this.sysInfoName = sysInfoName;
	}

	public String getSysInfoId() {
		return sysInfoId;
	}

	public void setSysInfoId(String sysInfoId) {
		this.sysInfoId = sysInfoId;
	}

	public String getRankEvalUnitName() {
		return rankEvalUnitName;
	}

	public void setRankEvalUnitName(String rankEvalUnitName) {
		this.rankEvalUnitName = rankEvalUnitName;
	}

	public Date getRankUseDate() {
		return rankUseDate;
	}

	public void setRankUseDate(Date rankUseDate) {
		this.rankUseDate = rankUseDate;
	}

	public String getRankFlag() {
		return rankFlag;
	}

	public void setRankFlag(String rankFlag) {
		this.rankFlag = rankFlag;
	}

	public String getRankParentSysName() {
		return rankParentSysName;
	}

	public void setRankParentSysName(String rankParentSysName) {
		this.rankParentSysName = rankParentSysName;
	}

	public String getRankParentUnitName() {
		return rankParentUnitName;
	}

	public void setRankParentUnitName(String rankParentUnitName) {
		this.rankParentUnitName = rankParentUnitName;
	}

	public String getRankEvalRelAccess() {
		return rankEvalRelAccess;
	}

	public void setRankEvalRelAccess(String rankEvalRelAccess) {
		this.rankEvalRelAccess = rankEvalRelAccess;
	}

	public String getRankCoveArea() {
		return rankCoveArea;
	}

	public void setRankCoveArea(String rankCoveArea) {
		this.rankCoveArea = rankCoveArea;
	}

	public String getRankOthArea() {
		return rankOthArea;
	}

	public void setRankOthArea(String rankOthArea) {
		this.rankOthArea = rankOthArea;
	}

	public String getRankNetworkProp() {
		return rankNetworkProp;
	}

	public void setRankNetworkProp(String rankNetworkProp) {
		this.rankNetworkProp = rankNetworkProp;
	}

	public String getRankOthNetworkProp() {
		return rankOthNetworkProp;
	}

	public void setRankOthNetworkProp(String rankOthNetworkProp) {
		this.rankOthNetworkProp = rankOthNetworkProp;
	}

	public String getRankSysConn() {
		return rankSysConn;
	}

	public void setRankSysConn(String rankSysConn) {
		this.rankSysConn = rankSysConn;
	}

	public String getRankOtherSysConn() {
		return rankOtherSysConn;
	}

	public void setRankOtherSysConn(String rankOtherSysConn) {
		this.rankOtherSysConn = rankOtherSysConn;
	}

	public String getRankSecCount() {
		return rankSecCount;
	}

	public void setRankSecCount(String rankSecCount) {
		this.rankSecCount = rankSecCount;
	}

	public String getRankSecUse() {
		return rankSecUse;
	}

	public void setRankSecUse(String rankSecUse) {
		this.rankSecUse = rankSecUse;
	}

	public String getRankNetCount() {
		return rankNetCount;
	}

	public void setRankNetCount(String rankNetCount) {
		this.rankNetCount = rankNetCount;
	}

	public String getRankNetUse() {
		return rankNetUse;
	}

	public void setRankNetUse(String rankNetUse) {
		this.rankNetUse = rankNetUse;
	}

	public String getRankSysCount() {
		return rankSysCount;
	}

	public void setRankSysCount(String rankSysCount) {
		this.rankSysCount = rankSysCount;
	}

	public String getRankSysUse() {
		return rankSysUse;
	}

	public void setRankSysUse(String rankSysUse) {
		this.rankSysUse = rankSysUse;
	}

	public String getRankSqlCount() {
		return rankSqlCount;
	}

	public void setRankSqlCount(String rankSqlCount) {
		this.rankSqlCount = rankSqlCount;
	}

	public String getRankSqlUse() {
		return rankSqlUse;
	}

	public void setRankSqlUse(String rankSqlUse) {
		this.rankSqlUse = rankSqlUse;
	}

	public String getRankSerCount() {
		return rankSerCount;
	}

	public void setRankSerCount(String rankSerCount) {
		this.rankSerCount = rankSerCount;
	}

	public String getRankSerUse() {
		return rankSerUse;
	}

	public void setRankSerUse(String rankSerUse) {
		this.rankSerUse = rankSerUse;
	}

	public String getRankOthProd() {
		return rankOthProd;
	}

	public void setRankOthProd(String rankOthProd) {
		this.rankOthProd = rankOthProd;
	}

	public String getRankOthProdCount() {
		return rankOthProdCount;
	}

	public void setRankOthProdCount(String rankOthProdCount) {
		this.rankOthProdCount = rankOthProdCount;
	}

	public String getRankOthProdUse() {
		return rankOthProdUse;
	}

	public void setRankOthProdUse(String rankOthProdUse) {
		this.rankOthProdUse = rankOthProdUse;
	}

	public String getRankIfGradeEval() {
		return rankIfGradeEval;
	}

	public void setRankIfGradeEval(String rankIfGradeEval) {
		this.rankIfGradeEval = rankIfGradeEval;
	}

	public String getRankSerGradeType() {
		return rankSerGradeType;
	}

	public void setRankSerGradeType(String rankSerGradeType) {
		this.rankSerGradeType = rankSerGradeType;
	}

	public String getRankIfRiskEval() {
		return rankIfRiskEval;
	}

	public void setRankIfRiskEval(String rankIfRiskEval) {
		this.rankIfRiskEval = rankIfRiskEval;
	}

	public String getRankSerRiskType() {
		return rankSerRiskType;
	}

	public void setRankSerRiskType(String rankSerRiskType) {
		this.rankSerRiskType = rankSerRiskType;
	}

	public String getRankIfSuffReco() {
		return rankIfSuffReco;
	}

	public void setRankIfSuffReco(String rankIfSuffReco) {
		this.rankIfSuffReco = rankIfSuffReco;
	}

	public String getRankIfSuffRecoType() {
		return rankIfSuffRecoType;
	}

	public void setRankIfSuffRecoType(String rankIfSuffRecoType) {
		this.rankIfSuffRecoType = rankIfSuffRecoType;
	}

	public String getRankIfResponse() {
		return rankIfResponse;
	}

	public void setRankIfResponse(String rankIfResponse) {
		this.rankIfResponse = rankIfResponse;
	}

	public String getRankResponseType() {
		return rankResponseType;
	}

	public void setRankResponseType(String rankResponseType) {
		this.rankResponseType = rankResponseType;
	}

	public String getRankIfSysInte() {
		return rankIfSysInte;
	}

	public void setRankIfSysInte(String rankIfSysInte) {
		this.rankIfSysInte = rankIfSysInte;
	}

	public String getRankSysInteType() {
		return rankSysInteType;
	}

	public void setRankSysInteType(String rankSysInteType) {
		this.rankSysInteType = rankSysInteType;
	}

	public String getRankIfSecCon() {
		return rankIfSecCon;
	}

	public void setRankIfSecCon(String rankIfSecCon) {
		this.rankIfSecCon = rankIfSecCon;
	}

	public String getRankSecConypeType() {
		return rankSecConypeType;
	}

	public void setRankSecConypeType(String rankSecConypeType) {
		this.rankSecConypeType = rankSecConypeType;
	}

	public String getRankIfSecTrain() {
		return rankIfSecTrain;
	}

	public void setRankIfSecTrain(String rankIfSecTrain) {
		this.rankIfSecTrain = rankIfSecTrain;
	}

	public String getRankSecTrainType() {
		return rankSecTrainType;
	}

	public void setRankSecTrainType(String rankSecTrainType) {
		this.rankSecTrainType = rankSecTrainType;
	}

	public String getRankOthSerName() {
		return rankOthSerName;
	}

	public void setRankOthSerName(String rankOthSerName) {
		this.rankOthSerName = rankOthSerName;
	}

	public String getRankIfOthSer() {
		return rankIfOthSer;
	}

	public void setRankIfOthSer(String rankIfOthSer) {
		this.rankIfOthSer = rankIfOthSer;
	}

	public String getRankOthUseType() {
		return rankOthUseType;
	}

	public void setRankOthUseType(String rankOthUseType) {
		this.rankOthUseType = rankOthUseType;
	}

	public String getRankOrganType() {
		return rankOrganType;
	}

	public void setRankOrganType(String rankOrganType) {
		this.rankOrganType = rankOrganType;
	}

	public String getRankOrganArea() {
		return rankOrganArea;
	}

	public void setRankOrganArea(String rankOrganArea) {
		this.rankOrganArea = rankOrganArea;
	}

	public String getRankBusinessType() {
		return rankBusinessType;
	}

	public void setRankBusinessType(String rankBusinessType) {
		this.rankBusinessType = rankBusinessType;
	}

	public String getRankInfoSysIntr() {
		return rankInfoSysIntr;
	}

	public void setRankInfoSysIntr(String rankInfoSysIntr) {
		this.rankInfoSysIntr = rankInfoSysIntr;
	}

	public String getRankGrade() {
		return rankGrade;
	}

	public void setRankGrade(String rankGrade) {
		this.rankGrade = rankGrade;
	}

	public Date getRankTime() {
		return rankTime;
	}

	public void setRankTime(Date rankTime) {
		this.rankTime = rankTime;
	}

	public String getRankJudge() {
		return rankJudge;
	}

	public void setRankJudge(String rankJudge) {
		this.rankJudge = rankJudge;
	}

	public String getRankIsDep() {
		return rankIsDep;
	}

	public void setRankIsDep(String rankIsDep) {
		this.rankIsDep = rankIsDep;
	}

	public String getRankDepName() {
		return rankDepName;
	}

	public void setRankDepName(String rankDepName) {
		this.rankDepName = rankDepName;
	}

	public String getRankDepJudge() {
		return rankDepJudge;
	}

	public void setRankDepJudge(String rankDepJudge) {
		this.rankDepJudge = rankDepJudge;
	}

	public String getRankDoc() {
		return rankDoc;
	}

	public void setRankDoc(String rankDoc) {
		this.rankDoc = rankDoc;
	}

	public String getRankAccess() {
		return rankAccess;
	}

	public void setRankAccess(String rankAccess) {
		this.rankAccess = rankAccess;
	}

	public String getRankInformant() {
		return rankInformant;
	}

	public void setRankInformant(String rankInformant) {
		this.rankInformant = rankInformant;
	}

	public Date getRankDate() {
		return rankDate;
	}

	public void setRankDate(Date rankDate) {
		this.rankDate = rankDate;
	}

	public String getRankTopStruct() {
		return rankTopStruct;
	}

	public void setRankTopStruct(String rankTopStruct) {
		this.rankTopStruct = rankTopStruct;
	}

	public String getRankTopRelAcc() {
		return rankTopRelAcc;
	}

	public void setRankTopRelAcc(String rankTopRelAcc) {
		this.rankTopRelAcc = rankTopRelAcc;
	}

	public String getRankSysManage() {
		return rankSysManage;
	}

	public void setRankSysManage(String rankSysManage) {
		this.rankSysManage = rankSysManage;
	}

	public String getRankSysManRel() {
		return rankSysManRel;
	}

	public void setRankSysManRel(String rankSysManRel) {
		this.rankSysManRel = rankSysManRel;
	}

	public String getRankSysPlan() {
		return rankSysPlan;
	}

	public void setRankSysPlan(String rankSysPlan) {
		this.rankSysPlan = rankSysPlan;
	}

	public String getRankSysPlanRel() {
		return rankSysPlanRel;
	}

	public void setRankSysPlanRel(String rankSysPlanRel) {
		this.rankSysPlanRel = rankSysPlanRel;
	}

	public String getRankSysLicense() {
		return rankSysLicense;
	}

	public void setRankSysLicense(String rankSysLicense) {
		this.rankSysLicense = rankSysLicense;
	}

	public String getRankSysLiceRel() {
		return rankSysLiceRel;
	}

	public void setRankSysLiceRel(String rankSysLiceRel) {
		this.rankSysLiceRel = rankSysLiceRel;
	}

	public String getRankSysReport() {
		return rankSysReport;
	}

	public void setRankSysReport(String rankSysReport) {
		this.rankSysReport = rankSysReport;
	}

	public String getRankSysReportRel() {
		return rankSysReportRel;
	}

	public void setRankSysReportRel(String rankSysReportRel) {
		this.rankSysReportRel = rankSysReportRel;
	}

	public String getRankPeerRev() {
		return rankPeerRev;
	}

	public void setRankPeerRev(String rankPeerRev) {
		this.rankPeerRev = rankPeerRev;
	}

	public String getRankPeerRevRel() {
		return rankPeerRevRel;
	}

	public void setRankPeerRevRel(String rankPeerRevRel) {
		this.rankPeerRevRel = rankPeerRevRel;
	}

	public String getRankSuperOpinRel() {
		return rankSuperOpinRel;
	}

	public void setRankSuperOpinRel(String rankSuperOpinRel) {
		this.rankSuperOpinRel = rankSuperOpinRel;
	}

	public String getRankSuperOpin() {
		return rankSuperOpin;
	}

	public void setRankSuperOpin(String rankSuperOpin) {
		this.rankSuperOpin = rankSuperOpin;
	}

	public String getRankCount() {
		return rankCount;
	}

	public void setRankCount(String rankCount) {
		this.rankCount = rankCount;
	}
	

	public String getPartRankSecUse() {
		return partRankSecUse;
	}

	public void setPartRankSecUse(String partRankSecUse) {
		this.partRankSecUse = partRankSecUse;
	}

	public String getPartRankNetUse() {
		return partRankNetUse;
	}

	public void setPartRankNetUse(String partRankNetUse) {
		this.partRankNetUse = partRankNetUse;
	}

	public String getPartRankSysUse() {
		return partRankSysUse;
	}

	public void setPartRankSysUse(String partRankSysUse) {
		this.partRankSysUse = partRankSysUse;
	}

	public String getPartRankSqlUse() {
		return partRankSqlUse;
	}

	public void setPartRankSqlUse(String partRankSqlUse) {
		this.partRankSqlUse = partRankSqlUse;
	}

	public String getPartRankSerUse() {
		return partRankSerUse;
	}

	public void setPartRankSerUse(String partRankSerUse) {
		this.partRankSerUse = partRankSerUse;
	}

	public String getPartRankOthProdUse() {
		return partRankOthProdUse;
	}

	public void setPartRankOthProdUse(String partRankOthProdUse) {
		this.partRankOthProdUse = partRankOthProdUse;
	}

	
	public String getSysInfoBusDescription() {
		return sysInfoBusDescription;
	}

	public void setSysInfoBusDescription(String sysInfoBusDescription) {
		this.sysInfoBusDescription = sysInfoBusDescription;
	}
	
	public String getReRankEvalRelAccess() {
		return reRankEvalRelAccess;
	}

	public void setReRankEvalRelAccess(String reRankEvalRelAccess) {
		this.reRankEvalRelAccess = reRankEvalRelAccess;
	}

	public String getReRankAccess() {
		return reRankAccess;
	}

	public void setReRankAccess(String reRankAccess) {
		this.reRankAccess = reRankAccess;
	}

	public String getReRankTopRelAcc() {
		return reRankTopRelAcc;
	}

	public void setReRankTopRelAcc(String reRankTopRelAcc) {
		this.reRankTopRelAcc = reRankTopRelAcc;
	}

	public String getReRankSysManRel() {
		return reRankSysManRel;
	}

	public void setReRankSysManRel(String reRankSysManRel) {
		this.reRankSysManRel = reRankSysManRel;
	}

	public String getReRankSysPlanRel() {
		return reRankSysPlanRel;
	}

	public void setReRankSysPlanRel(String reRankSysPlanRel) {
		this.reRankSysPlanRel = reRankSysPlanRel;
	}

	public String getReRankSysLiceRel() {
		return reRankSysLiceRel;
	}

	public void setReRankSysLiceRel(String reRankSysLiceRel) {
		this.reRankSysLiceRel = reRankSysLiceRel;
	}

	public String getReRrankSysReportRel() {
		return reRrankSysReportRel;
	}

	public void setReRrankSysReportRel(String reRrankSysReportRel) {
		this.reRrankSysReportRel = reRrankSysReportRel;
	}

	public String getReRankPeerRevRel() {
		return reRankPeerRevRel;
	}

	public void setReRankPeerRevRel(String reRankPeerRevRel) {
		this.reRankPeerRevRel = reRankPeerRevRel;
	}

	public String getReRankSuperOpinRel() {
		return reRankSuperOpinRel;
	}

	public void setReRankSuperOpinRel(String reRankSuperOpinRel) {
		this.reRankSuperOpinRel = reRankSuperOpinRel;
	}

	@Override
	public String toString() {
		return "Rank [rankId=" + rankId + ", sysInfoName=" + sysInfoName
				+ ", sysInfoId=" + sysInfoId + ", rankEvalUnitName="
				+ rankEvalUnitName + ", rankUseDate=" + rankUseDate
				+ ", rankFlag=" + rankFlag + ", rankParentSysName="
				+ rankParentSysName + ", rankParentUnitName="
				+ rankParentUnitName + ", rankEvalRelAccess="
				+ rankEvalRelAccess + ", rankCoveArea=" + rankCoveArea
				+ ", rankOthArea=" + rankOthArea + ", rankNetworkProp="
				+ rankNetworkProp + ", rankOthNetworkProp="
				+ rankOthNetworkProp + ", rankSysConn=" + rankSysConn
				+ ", rankOtherSysConn=" + rankOtherSysConn + ", rankSecCount="
				+ rankSecCount + ", rankSecUse=" + rankSecUse
				+ ", rankNetCount=" + rankNetCount + ", rankNetUse="
				+ rankNetUse + ", rankSysCount=" + rankSysCount
				+ ", rankSysUse=" + rankSysUse + ", rankSqlCount="
				+ rankSqlCount + ", rankSqlUse=" + rankSqlUse
				+ ", rankSerCount=" + rankSerCount + ", rankSerUse="
				+ rankSerUse + ", rankOthProd=" + rankOthProd
				+ ", rankOthProdCount=" + rankOthProdCount
				+ ", rankOthProdUse=" + rankOthProdUse + ", rankIfGradeEval="
				+ rankIfGradeEval + ", rankSerGradeType=" + rankSerGradeType
				+ ", rankIfRiskEval=" + rankIfRiskEval + ", rankSerRiskType="
				+ rankSerRiskType + ", rankIfSuffReco=" + rankIfSuffReco
				+ ", rankIfSuffRecoType=" + rankIfSuffRecoType
				+ ", rankIfResponse=" + rankIfResponse + ", rankResponseType="
				+ rankResponseType + ", rankIfSysInte=" + rankIfSysInte
				+ ", rankSysInteType=" + rankSysInteType + ", rankIfSecCon="
				+ rankIfSecCon + ", rankSecConypeType=" + rankSecConypeType
				+ ", rankIfSecTrain=" + rankIfSecTrain + ", rankSecTrainType="
				+ rankSecTrainType + ", rankOthSerName=" + rankOthSerName
				+ ", rankIfOthSer=" + rankIfOthSer + ", rankOthUseType="
				+ rankOthUseType + ", rankOrganType=" + rankOrganType
				+ ", rankOrganArea=" + rankOrganArea + ", rankBusinessType="
				+ rankBusinessType + ", rankInfoSysIntr=" + rankInfoSysIntr
				+ ", rankGrade=" + rankGrade + ", rankTime=" + rankTime
				+ ", rankJudge=" + rankJudge + ", rankIsDep=" + rankIsDep
				+ ", rankDepName=" + rankDepName + ", rankDepJudge="
				+ rankDepJudge + ", rankDoc=" + rankDoc + ", rankAccess="
				+ rankAccess + ", rankInformant=" + rankInformant
				+ ", rankDate=" + rankDate + ", rankTopStruct=" + rankTopStruct
				+ ", rankTopRelAcc=" + rankTopRelAcc + ", rankSysManage="
				+ rankSysManage + ", rankSysManRel=" + rankSysManRel
				+ ", rankSysPlan=" + rankSysPlan + ", rankSysPlanRel="
				+ rankSysPlanRel + ", rankSysLicense=" + rankSysLicense
				+ ", rankSysLiceRel=" + rankSysLiceRel + ", rankSysReport="
				+ rankSysReport + ", rankSysReportRel=" + rankSysReportRel
				+ ", rankPeerRev=" + rankPeerRev + ", rankPeerRevRel="
				+ rankPeerRevRel + ", rankSuperOpinRel=" + rankSuperOpinRel
				+ ", rankSuperOpin=" + rankSuperOpin + ", rankCount="
				+ rankCount + "]";
	}
	
	
	

}
