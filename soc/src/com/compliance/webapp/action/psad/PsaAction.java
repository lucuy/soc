package com.compliance.webapp.action.psad;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.compliance.model.psad.Psa;
import com.compliance.model.psad.Psad;
import com.compliance.service.cpManage.demand.ArithmeticService;
import com.compliance.service.psad.PsaService;
import com.compliance.service.psad.PsadService;
import com.compliance.webapp.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import com.util.DateUtil;

public class PsaAction extends BaseAction implements ModelDriven<Psa> {

	private PsaService psaService;
	private PsadService psadService;
	private ArithmeticService arithmeticService;
	//private AuditService auditService;
	private Psa psa = new Psa();
	private List<Psad> psaList;
	private String psadFatherSort;
	private String PSAD_FatherSort;
	private String resultAlgorithm;// 评估算法
	private int count;

	public Psa getModel() {
		return psa;
	}

	// 添加数据
	public String insert() throws ParseException {

		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		response.setCharacterEncoding("UTF-8");
		String pasdTime = super.getRequest().getParameter("pasdTime");
		String countNum = super.getRequest().getParameter("countNum");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date datatime = formatter.parse(pasdTime);
		 psa.setPsaDate(pasdTime);

		if (psa.getPsaSort() != null && psa.getPsaSort() != "") {
			/**
			 * 首先到数据库中去查找看该单元有没有已经评估，如果有返回值则进行修改，否则进行添加
			 */

			// 根据时间与父类排序查询该单元的评估结果，并将该结果依次插入到msadList对应的实体类中
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("PSA_Date", formatter.format(datatime));
			map1.put("PSA_Sort", psa.getPsaSort());
			Psa psad1 = psaService.queryResultByTimeAndSort(map1);
			psa.setPsaMainProbDes(psa.getPsaMainProbDes().trim());
			
			/**
			 * 添加审计
			 * @author hanyang
			 */
		/*	Audit a = new Audit();
			a.setName(request.getSession().getAttribute("SSI_LOGIN_USER").toString());
			a.setObject("通用差距分析");
			a.setDetailed("通用物理");
			a.setTime(DateUtil.curDateTimeStr19());*/
			if (psad1 == null) {
				
				psaService.insert(psa);
				/*a.setType("添加");
				auditService.instert(a);*/
			} else {
				psaService.upData(psa);
				/*a.setType("修改");
				auditService.instert(a);*/
			}
			Map<String, Object> map = new HashMap<String, Object>();
			psaList = psadService.queryByPsadSortInfo(null);
			count = psadService.queryNextPsadSortInfo(map);
			int psadIndex = 0;
			for (Psad ps : psaList) {
				if (ps.getPsadSort().equals(psa.getPsaSort())) {
					psadIndex = psaList.indexOf(ps);
				}
			}
			if (psadIndex != 0) {
				if (psadIndex == (psaList.size() - 1)) {
					log.info("已到达最后一个");
					psadFatherSort = "10.1.2";
					PSAD_FatherSort = "10.2.1";
				} else {
					Psad psad = psaList.get(psadIndex + 1);
					psadFatherSort = psad.getPsadFatherSort() + ".2";
					PSAD_FatherSort = psad.getPsadSort();
					resultAlgorithm = arithmeticService.queryJsAlgBySort(psadFatherSort).getJsAlg();
				}
			}

		}

		return SUCCESS;
	}

	public PsaService getPsaService() {
		return psaService;
	}

	public void setPsaService(PsaService psaService) {
		this.psaService = psaService;
	}

	public PsadService getPsadService() {
		return psadService;
	}

	public void setPsadService(PsadService psadService) {
		this.psadService = psadService;
	}

	public Psa getPsa() {
		return psa;
	}

	public void setPsa(Psa psa) {
		this.psa = psa;
	}

	public String getPsadFatherSort() {
		return psadFatherSort;
	}

	public void setPsadFatherSort(String psadFatherSort) {
		this.psadFatherSort = psadFatherSort;
	}

	public String getPSAD_FatherSort() {
		return PSAD_FatherSort;
	}

	public void setPSAD_FatherSort(String pSAD_FatherSort) {
		PSAD_FatherSort = pSAD_FatherSort;
	}

	public List<Psad> getPsaList() {
		return psaList;
	}

	public void setPsaList(List<Psad> psaList) {
		this.psaList = psaList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ArithmeticService getArithmeticService() {
		return arithmeticService;
	}

	public void setArithmeticService(ArithmeticService arithmeticService) {
		this.arithmeticService = arithmeticService;
	}

	public String getResultAlgorithm() {
		return resultAlgorithm;
	}

	public void setResultAlgorithm(String resultAlgorithm) {
		this.resultAlgorithm = resultAlgorithm;
	}

	/*public AuditService getAuditService() {
		return auditService;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}*/

}
