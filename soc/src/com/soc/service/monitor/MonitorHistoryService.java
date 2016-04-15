package com.soc.service.monitor;

import java.util.List;
import java.util.Map;

import com.soc.model.progressmsg.LinuxProgressMsg;
import com.soc.model.progressmsg.MonitorAlarmHistory;
import com.soc.model.progressmsg.WinProgressMsg;
import com.soc.model.progressmsg.WinSoftMsg;
import com.soc.model.servicemsg.LinuxServiceMsg;
import com.soc.model.servicemsg.WinServiceMsg;
import com.soc.service.BaseService;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface MonitorHistoryService extends BaseService {
	/**
	 * <新增历史监控><功能详细描述>
	 * 
	 * @param MonitorAlarmHistory
	 *            mah
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public void insterMonitorAlarmHistory(MonitorAlarmHistory mah);

	/**
	 * <查询所有的历史监控记录> <功能详细描述>
	 * 
	 * @param Map
	 *            map
	 * @param startRow
	 * @param pageSize
	 * @return List<MonitorAlarmHistory>
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("rawtypes")
	public SearchResult queryMonitorAlarmHistories(Map map, Page page);

	/**
	 * <新增windows进程><功能详细描述>
	 * 
	 * @param WinProgressMsg
	 *            wpm
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public void insterWinProgressMsg(WinProgressMsg wpm);

	/**
	 * <查询所有windows进程><功能详细>
	 * 
	 * @param Map
	 *            map
	 * @param startRow
	 * @param pageSize
	 * @return List<WinProgressMsg>
	 * @see [类、类#方法、类#成员]
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public SearchResult queryWinProgressMsgs(Map map, Page page);

	/**
	 * <新增windows服务><功能详细描述>
	 * 
	 * @param WinServiceMsg
	 *            wsm
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public void insterWinServiceMsg(WinServiceMsg wsm);

	/**
	 * <查询所有windows进程><功能详细>
	 * 
	 * @param Map
	 *            map
	 * @param startRow
	 * @param pageSize
	 * @return List<WinServiceMsg>
	 * @see [类、类#方法、类#成员]
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public SearchResult queryWinServiceMsgs(Map map, Page page);

	/**
	 * <新增windows软件信息><功能详细>
	 * 
	 * @param WinSoftMsg
	 *            wfm
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public void insterWinSoftMsg(WinSoftMsg wfm);

	/**
	 * <查询所有windows软件信息><功能详细>
	 * 
	 * @param Map
	 *            map
	 * @param startRow
	 * @param pageSize
	 * @return List<WinSoftMsg>
	 * @see [类、类#方法、类#成员]
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public SearchResult queryWinSoftMsgs(Map map, Page page);

	/**
	 * <新增linux进程><功能详细描述>
	 * 
	 * @param LinuxProgressMsg
	 *            lpm
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public void insterLinuxProgressMsg(LinuxProgressMsg lpm);

	/**
	 * <查询所有linux进程><功能详细>
	 * 
	 * @param Map
	 *            map
	 * @param startRow
	 * @param pageSize
	 * @return List<LinuxProgressMsg>
	 * @see [类、类#方法、类#成员]
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public SearchResult queryLinuxProgressMsgs(Map map, Page page);

	/**
	 * <新增linux服务><功能详细描述>
	 * 
	 * @param LinuxServiceMsg
	 *            lsm
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public void insterLinuxServiceMsg(LinuxServiceMsg lsm);

	/**
	 * <查询所有linux进程><功能详细>
	 * 
	 * @param Map
	 *            map
	 * @param startRow
	 * @param pageSize
	 * @return List<LinuxServiceMsg>
	 * @see [类、类#方法、类#成员]
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public SearchResult queryLinuxServiceMsgs(Map map, Page page);

}
