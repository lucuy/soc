package com.soc.junit.test.monitor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.soc.junit.test.base.BaseTest;
import com.soc.model.monitor.MonitorDatabaseTask;
import com.soc.model.monitor.database.mysql.MySqlLastWarning;
import com.soc.model.monitor.database.mysql.MysqlDB;
import com.soc.model.monitor.database.mysql.MysqlDetail;
import com.soc.model.monitor.database.orcale.OracleCustom;
import com.soc.model.monitor.database.orcale.OracleDetail;
import com.soc.service.monitor.MonitorDatabaseTaskService;
import com.util.monitorDatabase.JdbcConnectionWrapper;
import com.util.page.Page;
import com.util.page.SearchResult;

public class TestMonitorDatabaseTask extends BaseTest {
	private MonitorDatabaseTaskService mdtManager;
	private static String mySqlUrl="jdbc:mysql://$ip$:$port$/?useUnicode=true&amp;characterEncoding=UTF-8";
	private static final String mySqlDRIVER="com.mysql.jdbc.Driver";//mysql驱动
	@SuppressWarnings("unused")
	private static final String oracleDRIVER="oracle.jdbc.driver.OracleDriver";//oracle驱动
	@SuppressWarnings("unused")
	private static final String sqlServerDRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";//sqlserver驱动
	@Before
	public void init(){
		super.init();
		mdtManager = (MonitorDatabaseTaskService) super.getBean("mdtManager");
	}
	@Test
	public void add(){
		MonitorDatabaseTask mdt = new MonitorDatabaseTask();
		mdt.setTaskName("测试Mysql");
		mdt.setDbIp("198.9.9.9");
		mdt.setDbCategory("MySql");
		mdt.setMonType("Jdbc");
		mdt.setDbOnline(1);
		mdt.setDbPort(3306);
		mdt.setUpdateTime(new Date());
		mdt.setDbUserName("root");
		mdt.setDbPWD("root");
		mdt.setDbUrl((mySqlUrl.replace("$ip$", mdt.getDbIp())).replace("$port$", mdt.getDbPort()+""));
		mdt.setDbType(1);
		mdt.setDbAddr("捷成");
		mdt.setDbContasts("010-33333333");
		mdt.setTaskDESC("hehehehhehehe");
		System.out.println(mdtManager.insertMonitorDatabaseTask(mdt));
	}
	@Test
	public void checkName(){
		String name="测试Mysql";
		System.out.println(mdtManager.checkTaskName(name));
	} 
	@Test
	public void id(){
		long id=1;
		System.out.println(mdtManager.detailMonitorDatabaseTask(id).getDbIp());
	}
	@Test
	public void list(){
		Map map = new HashMap();
		map.put("dbType", 1);
		Page page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		SearchResult<MonitorDatabaseTask> sr = mdtManager.queryMonitorDatabaseTaskS(map, page);
		for (MonitorDatabaseTask mdt : sr.getList()) {
			if(mdt.getDbType()==1)
			System.out.println(checkOnline(mdt));
		}
	}
	
	public static boolean checkOnline (MonitorDatabaseTask mdt){
		JdbcConnectionWrapper wrapper = null;
		switch (mdt.getDbType()) {
		case 1:
			wrapper = JdbcConnectionWrapper.init(mySqlDRIVER, mdt.getDbUrl(), mdt.getDbUserName(), mdt.getDbPWD());
			break;
		case 2:
			wrapper = JdbcConnectionWrapper.init(oracleDRIVER, mdt.getDbUrl(), mdt.getDbUserName(), mdt.getDbPWD());
			break;
		case 3:
			wrapper = JdbcConnectionWrapper.init(sqlServerDRIVER, mdt.getDbUrl(), mdt.getDbUserName(), mdt.getDbPWD());
			break;
		}
		MysqlDetail md = new MysqlDetail(wrapper.getConn());
		System.out.println(md.getAbortedClients());
		System.out.println(md.getHostName());
		System.out.println(md.getOs());
		System.out.println(md.getAbortedConnects());
		System.out.println(md.getDataDIR());
		System.out.println(md.getDir());
		System.out.println(md.getFalg());
		System.out.println(md.getInnodbBPH());
		System.out.println(md.getInnodbBPS());
		System.out.println(md.getKbs());
		System.out.println(md.getKey_blocks_used());
		System.out.println(md.getKey_Hit());
		System.out.println(md.getOpenTables());
		System.out.println(md.getqCacheHit());
		System.err.println(md.getQueryTimes());
		System.out.println(md.getQcs());
		System.out.println(md.getQctb());
		System.out.println(md.getRunningThread());
		System.out.println(md.getVersion());
		System.out.println(md.getThread_stack());
		System.out.println(md.getThreadsConnected());
		for (MySqlLastWarning ml : md.getLastWarning()) {
			System.out.println(ml.getLevel());
		}
		for (MysqlDB db : md.getMysqlDB()) {
			System.out.println(db.getNum()+"\t"+db.getDbName());
		}
		/*OracleDetail od = new OracleDetail(wrapper.getConn());
		System.out.println(od.getHostName());
		System.out.println(od.getCreatTime());
		System.out.println(od.getStartTime());
		System.out.println(od.getVersion());
		System.out.println(od.getLatchCount());
		System.out.println(od.getLatchTime());
		System.out.println(od.getOpenCursoors());
		System.out.println(od.getOracleConnect());
		System.out.println(od.getLch());
		System.out.println(od.getBch());
		System.out.println(od.getFalg());
		System.out.println(od.getOracleObtainLockCourse());
		System.out.println(od.getOracleDBMemUsed());
		System.out.println(od.getOracleInMemorySort());
		System.out.println(od.getOracleWaitLockCourse());
		for (OracleCustom oc : od.getOracleConfigInfo()) {
			System.out.println(oc.getCostom1()+"\t"+oc.getCostom2());
		}
		for (OracleCustom oc : od.getOracleFreeSpace()) {
			System.out.println(oc.getCostom1()+"\t"+oc.getCostom2());
		}
		for (OracleCustom oc : od.getOracleTotalSpace()) {
			System.out.println(oc.getCostom1()+"\t"+oc.getCostom2());
		}*/
		
		return wrapper.isFalg();
	}
}
