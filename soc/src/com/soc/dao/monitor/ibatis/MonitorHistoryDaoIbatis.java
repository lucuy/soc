package com.soc.dao.monitor.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mvel2.util.ThisLiteral;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.monitor.MonitorHistoryDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.progressmsg.LinuxProgressMsg;
import com.soc.model.progressmsg.MonitorAlarmHistory;
import com.soc.model.progressmsg.WinProgressMsg;
import com.soc.model.progressmsg.WinSoftMsg;
import com.soc.model.servicemsg.LinuxServiceMsg;
import com.soc.model.servicemsg.WinServiceMsg;

public class MonitorHistoryDaoIbatis extends BaseDaoIbatis implements MonitorHistoryDao {

	@Override
	public void insterMonitorAlarmHistory(MonitorAlarmHistory mah) {
		super.getSqlMapClientTemplate().insert("mah.instert", mah);

	}

	@SuppressWarnings("rawtypes")
	@Override
	public int countMonitorAlarmHistory(Map map) {
		   Object ob = null;
	        
	        // 根据map中存储的条件查询符合条件的记录数
	        try
	        {
	            ob = super.queryForObject("mah.count", map);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        
	        // 总条数
	        int totalRows = 0;
	        
	        if (ob != null)
	        {
	            totalRows = ((Integer)ob).intValue();
	        }
	        
	        return totalRows;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<MonitorAlarmHistory> queryMonitorAlarmHistories(Map map,
			int startRow, int pageSize) {
		List<MonitorAlarmHistory> list = null;
		try {
			list = this.getSqlMapClientTemplate().queryForList("mah.query", map,startRow,pageSize);
		} catch (Exception e) {
			list =new ArrayList<MonitorAlarmHistory>();
			e.printStackTrace();
		}
		
		
		
		return list;
	}

	@Override
	public void insterWinProgressMsg(WinProgressMsg wpm) {
		super.getSqlMapClientTemplate().insert("wpm.inster",wpm);

	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public int countWinProgressMsg(Map map) {
		Object object = null;
		try {
			object = this.getSqlMapClientTemplate().queryForObject("wpm.count", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (object!=null) {
			return ((Integer)object).intValue();
		}else {
			return 0;
		}
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<WinProgressMsg> queryWinProgressMsgs(Map map, int startRow,
			int pageSize) {
		List<WinProgressMsg> list =null;
		try {
			list = this.getSqlMapClientTemplate().queryForList("wpm.query", map, startRow, pageSize);
		} catch (Exception e) {
			list =new ArrayList<WinProgressMsg>();
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void insterWinServiceMsg(WinServiceMsg wsm) {
		super.getSqlMapClientTemplate().insert("wsm.inster",wsm);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<WinServiceMsg> queryWinServiceMsgs(Map map, int startRow,
			int pageSize) {
		List<WinServiceMsg> list =null;
		try {
			list = this.getSqlMapClientTemplate().queryForList("wsm.query", map, startRow, pageSize);
		} catch (Exception e) {
			list =new ArrayList<WinServiceMsg>();
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int countWinServiceMsgg(Map map) {
		Object object = null;
		try {
			object = this.getSqlMapClientTemplate().queryForObject("wsm.count", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (object!=null) {
			return ((Integer)object).intValue();
		}else {
			return 0;
		}
	}

	@Override
	public void insterWinSoftMsg(WinSoftMsg wfm) {
		super.getSqlMapClientTemplate().insert("wfm.inster",wfm);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<WinSoftMsg> queryWinSoftMsgs(Map map, int startRow, int pageSize) {
		List<WinSoftMsg> list =null;
		try {
			list = this.getSqlMapClientTemplate().queryForList("wfm.query", map, startRow, pageSize);
		} catch (Exception e) {
			list =new ArrayList<WinSoftMsg>();
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int countWinSoftMsg(Map map) {
		Object object = null;
		try {
			object = this.getSqlMapClientTemplate().queryForObject("wfm.count", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (object!=null) {
			return ((Integer)object).intValue();
		}else {
			return 0;
		}
	}

	@Override
	public void insterLinuxProgressMsg(LinuxProgressMsg lpm) {
		super.getSqlMapClientTemplate().insert("lpm.inster", lpm);

	}

	@SuppressWarnings("rawtypes")
	@Override
	public int countLinuxProgressMsg(Map map) {
		Object object = null;
		try {
			object = this.getSqlMapClientTemplate().queryForObject("lpm.count", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (object!=null) {
			return ((Integer)object).intValue();
		}else {
			return 0;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LinuxProgressMsg> queryLinuxProgressMsgs(Map map, int startRow,
			int pageSize) {
		List<LinuxProgressMsg> list =null;
		try {
			list = this.getSqlMapClientTemplate().queryForList("lpm.query", map, startRow, pageSize);
		} catch (Exception e) {
			list =new ArrayList<LinuxProgressMsg>();
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void insterLinuxServiceMsg(LinuxServiceMsg lsm) {
		super.getSqlMapClientTemplate().insert("lsm.inster", lsm);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LinuxServiceMsg> queryLinuxServiceMsgs(Map map, int startRow,
			int pageSize) {
		List<LinuxServiceMsg> list =null;
		try {
			list = this.getSqlMapClientTemplate().queryForList("lsm.query", map, startRow, pageSize);
		} catch (Exception e) {
			list =new ArrayList<LinuxServiceMsg>();
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int countLinuxServiceMsg(Map map) {
		Object object = null;
		try {
			object = this.getSqlMapClientTemplate().queryForObject("lsm.count", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (object!=null) {
			return ((Integer)object).intValue();
		}else {
			return 0;
		}
	}

}
