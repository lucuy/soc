package com.compliance.webapp.action.psad;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.internal.matchers.SubstringMatcher;

import com.compliance.model.msad.MsaApp;
import com.compliance.model.msad.Msad;
import com.compliance.model.psad.Psa;
import com.compliance.model.psad.Psad;
import com.compliance.service.cpManage.demand.ArithmeticService;
import com.compliance.service.psad.PsaService;
import com.compliance.service.psad.PsadService;
import com.compliance.webapp.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class PsadAction extends BaseAction implements ModelDriven<Psad>{

	private PsadService psadService;
	private PsaService psaService;
	private ArithmeticService arithmeticService;
	private String psadFatherSort;
	private List<Psad> psadList;  //查询psadContent集合
	private List<Psad> cpzbList; //测评指标集合
	private Psad psad;
	private String resultAlgorithm;//评估算法
	private int countNum;// 统计已经评估的数量
	String lastTime;// 评估时间
	private String unitResult;//单元评估结果
	//private String msaDescription="";
	private int MsaId;//评估结果id
	public int getCountNum() {
		return countNum;
	}
	
	public String queryPsadByFatherSort()
	{
		HttpServletRequest request = super.getRequest();
		String psadFatherSort=this.getRequest().getParameter("psadFatherSort");
	    String PSAD_FatherSort=this.getRequest().getParameter("PSAD_FatherSort");
	    ////System.out.println("==========================="+psadFatherSort);
	    ////System.out.println("============================="+PSAD_FatherSort.lastIndexOf("."));
	    //System.out.println("=============================="+PSAD_FatherSort.substring(0,PSAD_FatherSort.lastIndexOf(".")));
	     String idsub=PSAD_FatherSort.substring(0,PSAD_FatherSort.lastIndexOf("."));
	    request.setAttribute("psadfatherid", idsub) ;
	     String psadqueryname=psadService.queryPsadnameById(idsub);
	     request.setAttribute("psadname", psadqueryname);
	    // 数据库中最后一次评估已经评估个数
	 		countNum = psaService.queryTheTimeNum();
	 		// 数据库中最后一次评估时间
	 		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 	// 查询评估算法
	 		if(psadFatherSort.contains("10")){
	 			// 查询评估算法
	 				resultAlgorithm = arithmeticService.queryJsAlgBySort(psadFatherSort).getJsAlg();
	 			}
	 		/**
			 * 根据得到的已经整改的个数与总个数进行对比，如果已经整改完成则当用户点击时从新开始评估，如果未完成则用户对已经评估过的进行修改。
			 */
			// 上次评估已经完成或数据库中无数据，将时间进行更改为现在时间，统计个数清空为0
	 		if (countNum == 6 || countNum == 0||countNum>6) {
	 			if(psaService.queryLastTime()!=null){
	 				countNum = 6;// 将以评估数清零
	 			}else{
	 				countNum = 0;// 将以评估数清零
	 			}
				lastTime = formatter.format(new Date());// 将评估时间设定为当前时间
				// 测评实施list集合
				    psadList=psadService.queryPsadByFatherSort(psadFatherSort);
				 // 测评指标list集合
				    cpzbList=psadService.queryPsadByFatherSort(PSAD_FatherSort);
	 		} else {
				// 本次评估未结束 ,得到测评实施的fathersprt与测评未结束的测评时间进行查询测评结果，将测评结果与测评项进行整合
				// 测评实施list集合
	 			 psadList=psadService.queryPsadByFatherSort(psadFatherSort);
				// 测评指标list集合
	 			 cpzbList=psadService.queryPsadByFatherSort(PSAD_FatherSort);
	 			lastTime = formatter.format(psaService.queryLastTime());
				// 根据时间与父类排序查询该单元的评估结果，并将该结果依次插入到msadList对应的实体类中
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("PSA_Date", formatter.format(psaService.queryLastTime()).toString());
				map.put("PSA_Sort", psadFatherSort);
				Psa psa = psaService.queryResultByTimeAndSort(map);
				request.setAttribute("psaDescription", psa);
				
				if(psa!=null){
					super.getRequest().setAttribute("psaMainProbDes",psa.getPsaMainProbDes().trim());
					MsaId=psa.getPsaId();
					unitResult = psa.getPsaAssessResult();
					for (Psad psad : psadList) {
						if (psad.getPsadSort().contains("a")) {
							if (psa.getPsaA() != null) {
								psad.setResult(psa.getPsaA());
							}
						}
						if (psad.getPsadSort().contains("b")) {
							if (psa.getPsaB() != null) {
								psad.setResult(psa.getPsaB());
							}
						}
						if (psad.getPsadSort().contains("c")) {
							if (psa.getPsaC() != null) {
								psad.setResult(psa.getPsaC());
							}

						}
						if (psad.getPsadSort().contains("d")) {
							if (psa.getPsaD() != null) {

							}
							psad.setResult(psa.getPsaD());
						}
						if (psad.getPsadSort().contains("e")) {
							if (psa.getPsaE() != null) {

							}
							psad.setResult(psa.getPsaE());
						}
						if (psad.getPsadSort().contains("f")) {
							if (psa.getPsaF() != null) {

							}
							psad.setResult(psa.getPsaF());
						}
						if (psad.getPsadSort().contains("g")) {
							if (psa.getPsaG() != null) {

							}
							psad.setResult(psa.getPsaG());
						}
						if (psad.getPsadSort().contains("h")) {
							if (psa.getPsaH() != null) {

							}
							psad.setResult(psa.getPsaH());
						}
						if (psad.getPsadSort().contains("i")) {
							if (psa.getPsaI() != null) {

							}
							psad.setResult(psa.getPsaI());
						}
						if (psad.getPsadSort().contains("j")) {
							if (psa.getPsaJ() != null) {

							}
							psad.setResult(psa.getPsaJ());
						}
					}
				}
			
			}
      
		return SUCCESS;
	}

	public PsadService getPsadService() {
		return psadService;
	}

	public void setPsadService(PsadService psadService) {
		this.psadService = psadService;
	}

	public ArithmeticService getArithmeticService() {
		return arithmeticService;
	}

	public void setArithmeticService(ArithmeticService arithmeticService) {
		this.arithmeticService = arithmeticService;
	}

	public String getPsadFatherSort() {
		return psadFatherSort;
	}

	public void setPsadFatherSort(String psadFatherSort) {
		this.psadFatherSort = psadFatherSort;
	}

	public List<Psad> getPsadList() {
		return psadList;
	}

	public void setPsadList(List<Psad> psadList) {
		this.psadList = psadList;
	}

	public List<Psad> getCpzbList() {
		return cpzbList;
	}

	public void setCpzbList(List<Psad> cpzbList) {
		this.cpzbList = cpzbList;
	}

	public Psad getPsad() {
		return psad;
	}

	public void setPsad(Psad psad) {
		this.psad = psad;
	}

	public String getResultAlgorithm() {
		return resultAlgorithm;
	}

	public void setResultAlgorithm(String resultAlgorithm) {
		this.resultAlgorithm = resultAlgorithm;
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
	public Psad getModel() {
		
		return psad;
	}

	public PsaService getPsaService() {
		return psaService;
	}

	public void setPsaService(PsaService psaService) {
		this.psaService = psaService;
	}

}
