package com.compliance.webapp.action.msad;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.compliance.model.msad.MsaApp;
import com.compliance.model.msad.Msad;
import com.compliance.service.cpManage.demand.ArithmeticService;
import com.compliance.service.msad.MsaAppService;
import com.compliance.service.msad.MsadService;
import com.compliance.webapp.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class MsadAction extends BaseAction implements ModelDriven<Msad> {

	private MsadService msadService;
	private MsaAppService msaAppService;
	private String msadFatherSort;
	private ArithmeticService arithmeticService;
	private List<Msad> msadList;
	private List<Msad> cpzb;// 测评指标list集合
	private Msad msad;
	private String resultAlgorithm;// 评估算法
	private int countNum;// 统计已经评估的数量
	String lastTime;// 评估时间
	private String unitResult;//单元评估结果
 
	private int MsaId;//评估结果id
	public Msad getModel() {
		return msad;
	}

	public String queryFatherSort() {
		// 查询测评实施父节点编号
		String msadFatherSort = this.getRequest().getParameter("msadFatherSort");
		// 查询测评指标父节点编号
		String MSAD_FatherSort = this.getRequest().getParameter("MSAD_FatherSort");
		
		String fatherid="";
		if(msadFatherSort.length()==6){
			fatherid=msadFatherSort.substring(0, 4);
		}else{
			fatherid=msadFatherSort.substring(0, 6);
		}
		String fathername=msadService.queryMsadName(fatherid);
		super.getRequest().setAttribute("fatherid",fatherid);
		super.getRequest().setAttribute("fathername",fathername);
		// 数据库中最后一次评估已经评估个数
		countNum = msaAppService.queryTheTimeNum();
		// 数据库中最后一次评估时间
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 
		// 查询评估算法
		if(msadFatherSort.contains("11")){
		resultAlgorithm = arithmeticService.queryJsAlgBySort(msadFatherSort).getJsAlg();
		}
		/**
		 * 根据得到的已经整改的个数与总个数进行对比，如果已经整改完成则当用户点击时从新开始评估，如果未完成则用户对已经评估过的进行修改。
		 */
		// 上次评估已经完成或数据库中无数据，将时间进行更改为现在时间，统计个数清空为0
		if (countNum == 33 || countNum == 0||countNum>33) {
			countNum = 0;// 将以评估数清零
			if(msaAppService.queryLastTime()!=null){
 				countNum = 33;// 将以评估数清零
 			}else{
 				countNum = 0;// 将以评估数清零
 			}
			lastTime = formatter.format(new Date());// 将评估时间设定为当前时间
			// 测评实施list集合
			msadList = msadService.queryMsadByFatherSort(msadFatherSort);
			// 测评指标list集合
			cpzb = msadService.queryMsadByFatherSort(MSAD_FatherSort);
		} else {
			// 本次评估未结束 ,得到测评实施的fathersprt与测评未结束的测评时间进行查询测评结果，将测评结果与测评项进行整合
			// 测评实施list集合
			msadList = msadService.queryMsadByFatherSort(msadFatherSort);
			// 测评指标list集合
			cpzb = msadService.queryMsadByFatherSort(MSAD_FatherSort);
			lastTime = formatter.format(msaAppService.queryLastTime());
			// 根据时间与父类排序查询该单元的评估结果，并将该结果依次插入到msadList对应的实体类中
			Map<String, String> map = new HashMap<String, String>();
			map.put("MSA_Date", formatter.format(msaAppService.queryLastTime()).toString());
			map.put("MSA_Sort", msadFatherSort);
			MsaApp app = msaAppService.queryResultByTimeAndSort(map);
			HttpServletRequest request = super.getRequest();
			super.getRequest().setAttribute("appDescription",app);
			if(app!=null){
				MsaId=app.getMsaId();
				
				request.setAttribute("msaMainProbDes",  app.getMsaMainProbDes().trim());
				unitResult = app.getMsaAssessResult();
				for (Msad msad : msadList) {
					if (msad.getMsadSort().contains("a")) {
						if (app.getMsaA() != null) {
							msad.setResult(app.getMsaA());
						}
					}
					if (msad.getMsadSort().contains("b")) {
						if (app.getMsaB() != null) {
							msad.setResult(app.getMsaB());
						}
					}
					if (msad.getMsadSort().contains("c")) {
						if (app.getMsaC() != null) {
							msad.setResult(app.getMsaC());
						}

					}
					if (msad.getMsadSort().contains("d")) {
						if (app.getMsaD() != null) {

						}
						msad.setResult(app.getMsaD());
					}
					if (msad.getMsadSort().contains("e")) {
						if (app.getMsaE() != null) {

						}
						msad.setResult(app.getMsaE());
					}
					if (msad.getMsadSort().contains("f")) {
						if (app.getMsaF() != null) {

						}
						msad.setResult(app.getMsaF());
					}
					if (msad.getMsadSort().contains("g")) {
						if (app.getMsaG() != null) {

						}
						msad.setResult(app.getMsaG());
					}
					if (msad.getMsadSort().contains("h")) {
						if (app.getMsaH() != null) {

						}
						msad.setResult(app.getMsaH());
					}
					if (msad.getMsadSort().contains("i")) {
						if (app.getMsaI() != null) {

						}
						msad.setResult(app.getMsaI());
					}
					if (msad.getMsadSort().contains("j")) {
						if (app.getMsaJ() != null) {

						}
						msad.setResult(app.getMsaJ());
					}
				}
			}
		
		}

	 

		return SUCCESS;
	}

	public MsadService getMsadService() {
		return msadService;
	}

	public void setMsadService(MsadService msadService) {
		this.msadService = msadService;
	}

	public String getMsadFatherSort() {
		return msadFatherSort;
	}

	public void setMsadFatherSort(String msadFatherSort) {
		this.msadFatherSort = msadFatherSort;
	}

	public List<Msad> getMsadList() {
		return msadList;
	}

	public void setMsadList(List<Msad> msadList) {
		this.msadList = msadList;
	}

	public Msad getMsad() {
		return msad;
	}

	public void setMsad(Msad msad) {
		this.msad = msad;
	}

	public List<Msad> getCpzb() {
		return cpzb;
	}

	public void setCpzb(List<Msad> cpzb) {
		this.cpzb = cpzb;
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

	public MsaAppService getMsaAppService() {
		return msaAppService;
	}

	public void setMsaAppService(MsaAppService msaAppService) {
		this.msaAppService = msaAppService;
	}

	public int getCountNum() {
		return countNum;
	}

	public void setCountNum(int countNum) {
		this.countNum = countNum;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public String getUnitResult() {
		return unitResult;
	}

	public void setUnitResult(String unitResult) {
		this.unitResult = unitResult;
	}

	public int getMsaId() {
		return MsaId;
	}

	public void setMsaId(int msaId) {
		MsaId = msaId;
	}
 
}
