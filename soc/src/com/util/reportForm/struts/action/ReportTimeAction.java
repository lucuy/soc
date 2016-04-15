package com.util.reportForm.struts.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.util.reportForm.datadeal.BaseDao;
import com.util.reportForm.datadeal.model.ReportCustom;
import com.util.reportForm.datadeal.model.ReportTimer;
import com.util.reportForm.datadeal.model.Reportforms;
import com.util.reportForm.struts.form.ReportTimerForm;

/**
 * 定时报表action
 * @author zsa
 *
 */
public class ReportTimeAction extends DispatchAction {
	
	private BaseDao dao = new BaseDao();

	
	public ActionForward initPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ReportTimerForm reportTimerForm=(ReportTimerForm)form;
		String hql="from ReportTimer";
		//得到所有的数据——显示选定的报表
		List<ReportTimer> allReportTimer=(List<ReportTimer>)dao.getNamedQuery(hql,null);
		//得到一条数据——显示时间的设置
		List<Integer>weekList=new ArrayList<Integer>();
		List<Integer>dayList=new ArrayList<Integer>();
		ReportTimer rt=allReportTimer.get(0);
		for(int i=0;i<rt.getWeekSet().length();i++){
			weekList.add(i,Integer.parseInt(String.valueOf(rt.getWeekSet().charAt(i))));
		}
		if(rt.getDaySet()!=null){
			for(int j=0;j<rt.getDaySet().length();j++){
				dayList.add(j, Integer.parseInt(String.valueOf(rt.getDaySet().charAt(j))));
			}
		}
		reportTimerForm.setReportTimer(rt);
		reportTimerForm.setWeekList(weekList);
		reportTimerForm.setDayList(dayList);
	
		//系统报表-已设置
		List<Reportforms>selSys=new ArrayList<Reportforms>();
		//自定义报表-已设置
		List<ReportCustom>selCustom=new ArrayList<ReportCustom>();
		//等到已经设置的报表的id串
		String sys="";
		String custom="";
		for(ReportTimer t:allReportTimer){
			//确保已设置
			if(t.getReportType()!=null){
				if(t.getReportType()==1){
					if(custom==""){
						custom=t.getReportId();
					}else{
						custom=custom+","+t.getReportId();
					}
				}
				if(t.getReportType()==0){
					if(sys==""){
						sys=t.getReportId().substring(0,t.getReportId().indexOf(","));
					}else{
						sys=sys+","+t.getReportId().substring(0,t.getReportId().indexOf(","));
					}
				}
			}
		}
		//查询所有的已设置的系统报表
		if(!sys.equals("")){
			String hqlsys="from Reportforms where id in ("+sys+")";
			selSys=(List<Reportforms>)dao.getNamedQuery(hqlsys, null);
		}
		request.setAttribute("selSys", selSys);
		//查询已设置的自定义报表
		if(!custom.equals("")){
			String hqlCustom="from ReportCustom where id in ("+custom+")";
			selCustom=(List<ReportCustom>)dao.getNamedQuery(hqlCustom,null);
		}
		request.setAttribute("selCustom", selCustom);
		//获得系统报表列表
		String hqlTemp="from Reportforms";
		List<Reportforms>formsList=(List<Reportforms>)dao.getNamedQuery(hqlTemp, null);
		request.setAttribute("formsList", formsList);
		//获得自定义报表列表
		String hqlCustom="from ReportCustom";
		List<ReportCustom>customList=(List<ReportCustom>)dao.getNamedQuery(hqlCustom, null);
		request.setAttribute("customList", customList);
		
		return mapping.findForward("initPage");
	}
	
	/**
	 * 设置定时报表参数
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ReportTimerForm reportTimerForm=(ReportTimerForm)form;
		dao.deleteTable("ReportTimer");
		String week="0000000";
		String day="000000000000000000000000000000";
		ReportTimer rt=reportTimerForm.getReportTimer();
		rt.setUseDay(reportTimerForm.getUseday());
		rt.setUseWeek(reportTimerForm.getUseweek());
		if(reportTimerForm.getUseday()==1){//按日
			if(!reportTimerForm.getDaySet().equals("")){
				String[]daySet=reportTimerForm.getDaySet().split(":");
				for(int i=0;i<daySet.length;i++){
					day=day.substring(0, Integer.parseInt(String.valueOf(daySet[i]))-1)+"1"+day.substring(Integer.parseInt(String.valueOf(daySet[i])));
				}
				rt.setUseWeek(0);
			}
		}else{//按周
			if(!reportTimerForm.getWeekSet().equals("")){
				String[]weekSet=reportTimerForm.getWeekSet().split(":");
				for(int i=0;i<weekSet.length;i++){
					week=week.substring(0,Integer.parseInt(String.valueOf(weekSet[i]))-1)+"1"+week.substring(Integer.parseInt(String.valueOf(weekSet[i])));
				}
				rt.setUseDay(0);
			}
			rt.setUseWeek(1);
		}
		rt.setDaySet(day);
		rt.setWeekSet(week);
		//选定报表
		String reportStr=reportTimerForm.getReportStr();
		if(reportStr==""){
			dao.save(rt);
		}else{
			String[]s=reportStr.split(";");
			for(int i=0;i<s.length;i++){
				if(s[i].indexOf(",")==-1){
					rt.setReportType(1);
				}else{
					rt.setReportType(0);
				}
				rt.setReportId(s[i]);
				dao.save(rt);
			}	
		}
		return mapping.findForward("toShow");
	}
	
}