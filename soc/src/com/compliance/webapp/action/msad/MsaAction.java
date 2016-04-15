package com.compliance.webapp.action.msad;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.compliance.model.msad.MsaApp;
import com.compliance.model.msad.Msad;
import com.compliance.service.cpManage.demand.ArithmeticService;
import com.compliance.service.msad.MsaAppService;
import com.compliance.service.msad.MsadService;
import com.compliance.webapp.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import com.util.DateUtil;

public class MsaAction extends BaseAction implements ModelDriven<MsaApp> {

	private MsaAppService msaAppService;
	private MsadService msadService;
	private MsaApp msa = new MsaApp();
	private String msadFatherSort;
	private String Msad_FatherSort;
	private String resultAlgorithm;// 评估算法
	private ArithmeticService arithmeticService;
//	private AuditService auditService;
	private int countNum;// 统计已经评估的数量
	private String masdTime;// 评估时间
	private String unitResult;// 单元评估结果
	private String MsaId;// 评估单元id

	/**
	 * 添加一条数据
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String insert() throws ParseException {
		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		response.setCharacterEncoding("UTF-8");
		String masdTime = super.getRequest().getParameter("masdTime");
		String countNum = super.getRequest().getParameter("countNum");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date datatime = formatter.parse(masdTime);
		msa.setMsaDate(masdTime);
		if (msa.getMsaSort() != null && msa.getMsaSort() != "") {
			/**
			 * 首先到数据库中去查找看该单元有没有已经评估，如果有返回值则进行修改，否则进行添加
			 */

			// 根据时间与父类排序查询该单元的评估结果，并将该结果依次插入到msadList对应的实体类中
			Map<String, String> map = new HashMap<String, String>();
			map.put("MSA_Date", formatter.format(datatime));
			map.put("MSA_Sort", msa.getMsaSort());
			
			/**
			 * 添加审计
			 * @author hanyang
			 */
		/*	Audit a = new Audit();
			a.setName(request.getSession().getAttribute("SSI_LOGIN_USER").toString());
			a.setObject("通用差距分析");
			a.setDetailed("通用管理");
			a.setTime(DateUtil.curDateTimeStr19());*/

			MsaApp app = msaAppService.queryResultByTimeAndSort(map);
			if (app == null) {
				msaAppService.insert(msa);
			/*	a.setType("添加");
				auditService.instert(a);*/
				
			} else {
				msaAppService.upData(msa);
				/*a.setType("修改");
				auditService.instert(a);*/
			}

			// 根据已经评估数
			List<Msad> msadList = msadService.queryByMsadSort(null);
			int msadIndex = 0;
			for (Msad msad : msadList) {
				if (msad.getMsadSort().equals(msa.getMsaSort() + ".2") || msad.getMsadSort().equals(msa.getMsaSort())) {
					msadIndex = msadList.indexOf(msad);
				}
			}
			if (msadIndex != 0) {
				if (msadIndex == msadList.size() - 1) {
					log.info("已到达最后一个……");
					msadFatherSort = "11.1.2";
					Msad_FatherSort = "11.1.1";
				} else {
					Msad msad = msadList.get(msadIndex + 1);
					log.info(msad + " - " + msadList.get(msadIndex + 1));
					msadFatherSort = msad.getMsadSort();
					Msad_FatherSort = msad.getMsadFatherSort() + ".1";
					resultAlgorithm = arithmeticService.queryJsAlgBySort(msadFatherSort).getJsAlg();
				}
			}
		}
		return SUCCESS;
	}

	public MsaAppService getMsaAppService() {
		return msaAppService;
	}

	public void setMsaAppService(MsaAppService msaAppService) {
		this.msaAppService = msaAppService;
	}

	public MsadService getMsadService() {
		return msadService;
	}

	public void setMsadService(MsadService msadService) {
		this.msadService = msadService;
	}

	public MsaApp getMsa() {
		return msa;
	}

	public void setMsa(MsaApp msa) {
		this.msa = msa;
	}

	public String getMsadFatherSort() {
		return msadFatherSort;
	}

	public void setMsadFatherSort(String msadFatherSort) {
		this.msadFatherSort = msadFatherSort;
	}

	public String getMsad_FatherSort() {
		return Msad_FatherSort;
	}

	public void setMsad_FatherSort(String msad_FatherSort) {
		Msad_FatherSort = msad_FatherSort;
	}

	public MsaApp getModel() {
		return msa;
	}

	public String getResultAlgorithm() {
		return resultAlgorithm;
	}

	public void setResultAlgorithm(String resultAlgorithm) {
		this.resultAlgorithm = resultAlgorithm;
	}

	public ArithmeticService getArithmeticService() {
		return arithmeticService;
	}

	public void setArithmeticService(ArithmeticService arithmeticService) {
		this.arithmeticService = arithmeticService;
	}

	public int getCountNum() {
		return countNum;
	}

	public void setCountNum(int countNum) {
		this.countNum = countNum;
	}

	public String getMasdTime() {
		return masdTime;
	}

	public void setMasdTime(String masdTime) {
		this.masdTime = masdTime;
	}

	public String getUnitResult() {
		return unitResult;
	}

	public void setUnitResult(String unitResult) {
		this.unitResult = unitResult;
	}

	public String getMsaId() {
		return MsaId;
	}

	public void setMsaId(String msaId) {
		MsaId = msaId;
	}

/*	public AuditService getAuditService() {
		return auditService;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}
	*/
	
}
