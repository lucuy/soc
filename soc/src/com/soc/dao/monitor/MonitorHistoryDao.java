package com.soc.dao.monitor;

import java.util.List;
import java.util.Map;

import com.soc.dao.BaseDao;
import com.soc.model.progressmsg.LinuxProgressMsg;
import com.soc.model.progressmsg.MonitorAlarmHistory;
import com.soc.model.progressmsg.WinProgressMsg;
import com.soc.model.progressmsg.WinSoftMsg;
import com.soc.model.servicemsg.LinuxServiceMsg;
import com.soc.model.servicemsg.WinServiceMsg;

/**
 * <AMP信息历史Dao类> <功能详细描述>
 * 
 * @author lichanghong
 * @version [版本号, 2014-06-06]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface MonitorHistoryDao  extends BaseDao{
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
	 * <查询历史监控总数><功能详细描述>
	 * 
	 * @param map
	 * @return int
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("rawtypes")
	public int countMonitorAlarmHistory(Map map);

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
	public List<MonitorAlarmHistory> queryMonitorAlarmHistories(Map map,
			int startRow, int pageSize);

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
	 * <查询windows进程总数><功能详细>
	 * 
	 * @param Map
	 *            map
	 * @return int
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("rawtypes")
	public int countWinProgressMsg(Map map);

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
	public List<WinProgressMsg> queryWinProgressMsgs(Map map, int startRow,
			int pageSize);

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
	public List<WinServiceMsg> queryWinServiceMsgs(Map map, int startRow,
			int pageSize);

	/**
	 * <查询windows服务总数><功能详细>
	 * 
	 * @param Map
	 *            map
	 * @return int
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("rawtypes")
	public int countWinServiceMsgg(Map map);

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
	public List<WinSoftMsg> queryWinSoftMsgs(Map map, int startRow, int pageSize);

	/**
	 * <查询windows软件信息><功能详细>
	 * 
	 * @param Map
	 *            map
	 * @return int
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("rawtypes")
	public int countWinSoftMsg(Map map);

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
	 * <查询linux进程总数><功能详细>
	 * 
	 * @param Map
	 *            map
	 * @return int
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("rawtypes")
	public int countLinuxProgressMsg(Map map);

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
	public List<LinuxProgressMsg> queryLinuxProgressMsgs(Map map, int startRow,
			int pageSize);

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
	public List<LinuxServiceMsg> queryLinuxServiceMsgs(Map map, int startRow,
			int pageSize);

	/**
	 * <查询linux服务总数><功能详细>
	 * 
	 * @param Map
	 *            map
	 * @return int
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("rawtypes")
	public int countLinuxServiceMsg(Map map);

}
