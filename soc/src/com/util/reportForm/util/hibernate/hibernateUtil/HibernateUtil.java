package com.util.reportForm.util.hibernate.hibernateUtil;


import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.util.reportForm.framework.exceptions.DatastoreException;
import com.util.reportForm.util.hibernate.sessionFactory.HibernateSessionFactory;

/**
 * 整个工程统一处理Hibernate Sission 业务 
 **/
public class HibernateUtil {
	private static Log log = LogFactory.getLog(HibernateUtil.class);

	private static Configuration configuration;

	private static SessionFactory sessionFactory;

	private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";

	private static final String INTERCEPTOR_CLASS = "com.cat.hibernate.hibernateUtil.interceptor_class";

	private static boolean useThreadLocal = true;

	// 保存Session对象实例的线程局部变量

	private static ThreadLocal threadSession = new ThreadLocal();

	// 保存Transaction对象的线程局部变量

	private static ThreadLocal threadTransaction = new ThreadLocal();
	// 基于配置文件初始化 sessionFactory
	static {
		try {
			log.info("===打开数据库=====");
			// 创建CONFIGURACTION实例
			configuration = new Configuration();
			// 读取HIBERNATE.PROPERTIES或者HIBERNATE.CFG.XML文件
			configuration.configure(CONFIG_FILE_LOCATION);

			// 指定一个全局的用户自定义的拦截器
			String interceptorName = configuration
					.getProperty(INTERCEPTOR_CLASS);
			if (interceptorName != null) {

				Class interceptorClass = HibernateUtil.class.getClassLoader()
						.loadClass(interceptorName);
				Interceptor interceptor = (Interceptor) interceptorClass
						.newInstance();
				configuration.setInterceptor(interceptor);

			}

			// 如果使用CMT，那么就不使用线程安全的Session和Transaction
			if (org.hibernate.transaction.CMTTransactionFactory.class
					.getName()
					.equals(
							configuration
									.getProperty(Environment.TRANSACTION_STRATEGY))) {
				useThreadLocal = false;
			}

			if (configuration.getProperty(Environment.SESSION_FACTORY_NAME) != null) {
				// 绑定HIBERNATE到JNDI
				configuration.buildSessionFactory();
			} else {
				// 使用静态的变量
				sessionFactory = configuration.buildSessionFactory();
			}

		} catch (Throwable ex) {
			// 必须要捕获THROWABLE，否则就不能捕获NoClassDefFoundError异常以及其子类错误

			log.error("Building SessionFactory failed.", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Returns the SessionFactory used for this static class.
	 *
	 * @return SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		SessionFactory sf = null;
		String sfName = configuration
				.getProperty(Environment.SESSION_FACTORY_NAME);
		if (sfName != null) {
			log.debug("Looking up SessionFactory in JNDI");
			try {
				sf = (SessionFactory) new InitialContext().lookup(sfName);
			} catch (NamingException ex) {
				throw new RuntimeException(ex);
			}
		} else {
			sf = sessionFactory;
		}

		if (sf == null)
			throw new IllegalStateException("SessionFactory not available.");

		return sf;
	}

	/**
	 * Returns the original Hibernate configuration.
	 *
	 * @return Configuration
	 */
	public static Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * 重建 SessionFactory with the static Configuration.
	 *
	 */
	public static void rebuildSessionFactory() throws DatastoreException {
		synchronized (sessionFactory) {
			try {
				sessionFactory = getConfiguration().buildSessionFactory();
			} catch (Exception ex) {
				log.error(ex.getMessage());
				throw DatastoreException.datastoreError(ex);
			}
		}
	}

	/**
	 * 重建给定配置文件的 sessionFactory
	 *
	 * @param cfg
	 */
	public static void rebuildSessionFactory(Configuration cfg)
			throws DatastoreException {
		synchronized (sessionFactory) {
			try {
				sessionFactory = cfg.buildSessionFactory();
				configuration = cfg;
			} catch (Exception ex) {
				log.error(ex.getMessage());
				throw DatastoreException.datastoreError(ex);

			}
		}
	}

	public static Session getSession() throws DatastoreException {
		try {
			return sessionFactory.openSession();

		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw DatastoreException.datastoreError(ex);
		}
	}

	public static void close() {
		try {
			log.info("===关闭数据库=====");
			sessionFactory.close();
		} catch (Exception ex) {
			log.error(ex.getMessage());

		}
	}

	/**
	 * 关闭当前SessionFactory并且释放资源
	 * 
	 */
	public static void shutdown() {
		log.debug("Shutting down Hibernate.");
		// 关闭缓冲的连接池
		getSessionFactory().close();

		// 清除静态变量

		configuration = null;
		sessionFactory = null;

		// 清除本地进程变量
		threadSession.set(null);
		threadTransaction.set(null);
	}

	/**
	 * 获得当前的Session对象的实例

	 */
	public static Session getCurrentSession() {
		if (useThreadLocal) {
			Session s = (Session) threadSession.get();
			if (s == null) {
				log.debug("Opening new Session for this thread.");
				s = getSessionFactory().openSession();
				threadSession.set(s);
			}
			return s;
		} else {
			return getSessionFactory().getCurrentSession();
		}
	}

	/**
	 * 重新连接当前的SESSION
	 */

	public static void reconnect(Session session) {
		if (useThreadLocal) {
			log.debug("Reconnecting Session to this thread.");
			session.reconnect();
			threadSession.set(session);
		} else {
			log.error("Using CMT/JTA,intercepted not supported reconnect call");
		}
	}

	/**
	 * 断开当前的SESSION
	 */

	public static Session disconnectSession() {
		if (useThreadLocal) {
			Transaction tx = (Transaction) threadTransaction.get();
			if (tx != null && (!tx.wasCommitted() || !tx.wasRolledBack()))
				throw new IllegalStateException(
						"Disconnecting Session but Transaction still open!");

			Session session = getCurrentSession();
			threadSession.set(null);
			if (session.isConnected() && session.isOpen()) {
				log.debug("Disconnecting Session from this thread.");
				session.disconnect();
			}
			return session;

		} else {
			log
					.error("Using CMT/JTA,intercepted not supported disconnect call.");
			return null;
		}
	}

	/**
	 * 关闭SESSION对象
	 */
	public static void closeSession() {
		if (useThreadLocal) {
			Session s = (Session) threadSession.get();
			threadSession.set(null);
			Transaction tx = (Transaction) threadTransaction.get();
			if (tx != null && (!tx.wasCommitted() || !tx.wasRolledBack()))
				throw new IllegalStateException(
						"Closing Session but Transaction still open!");
			if (s != null && s.isOpen()) {
				log.debug("Closing Session of this thread.");
			}
		} else {
			log.warn("Using CMT/JTA,intercepted superfluous close call.");
		}
	}

	/**
	 * 开始一个新的事务

	 */

	public static void beginTransaction() {
		if (useThreadLocal) {
			Transaction tx = (Transaction) threadTransaction.get();
			if (tx == null) {
				log.debug("Starting new database transaction in this thread");
				tx = getCurrentSession().beginTransaction();
				threadTransaction.set(tx);
			} else {
				log
						.warn("Using CMT/JTA,intercepted superfluous tx beging call.");
			}
		}
	}

	/**
	 * 提交事务
	 */
	public static void commitTransaction() {
		if (useThreadLocal) {
			Transaction tx = (Transaction) threadTransaction.get();
			try {
				if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
					log
							.debug("Committing database transaction of this thread.");
					tx.commit();
				}
				threadTransaction.set(null);
			} catch (RuntimeException ex) {
				log.error(ex);
				rollbackTransaction();
				throw ex;
			}

		} else {
			log.warn("Using CMT/JTA,intercepted superfluous tx commit call.");
		}
	}

	/**
	 * 回滚数据库事务

	 */

	public static void rollbackTransaction() {
		if (useThreadLocal) {
			Transaction tx = (Transaction) threadTransaction.get();
			try {
				threadTransaction.set(null);
				if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
					log
							.debug("Tyring to rollback database transaction of this thread.");
					tx.rollback();
					log.debug("Database transaction rolled back.");
				}
			} catch (RuntimeException ex) {
				throw new RuntimeException(
						"Might swallow original cause,check ERROR log!", ex);
			} finally {
				closeSession();
			}
		} else {
			log.warn("Using CMT/JTA,intercepted superfluous tx rollback call.");
		}
	}

	public static void closeSession(Session session) throws DatastoreException {
		try {
			session.close();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw DatastoreException.datastoreError(ex);
		}
	}

	public static void rollbackTransaction(Transaction transaction)
			throws DatastoreException {
		try {
			if (transaction != null)
				transaction.rollback();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw DatastoreException.datastoreError(ex);
		}
	}
    /**
     * 执行数据库查询,返回结果集List
     * @param hsql HSQL查询语句
     * @return list 结果集

     */
    public static List query(String hql) {
        List list = null;
        Query query = null;      
        Session sess = HibernateSessionFactory.currentSession();
        try{
            //创建一条HQL查询语句
            if (hql.toLowerCase().contains("from "))
                query = sess.createQuery(hql);
            else
                 query = sess.getNamedQuery(hql);
            
            list = query.list();
            
        }catch(HibernateException e)  {
            log.error("Hibernate Exception:@"+e.getMessage());
            throw new RuntimeException(e);
        }finally {
            HibernateSessionFactory.closeSession();
        }
        return list;
    }
    public static List query(String hql, Object bean){
        List list = null;
        Query query = null;
        
        Session sess = HibernateSessionFactory.currentSession();
        try {
            //创建一条HQL查询语句
            if (hql.toLowerCase().contains("from "))
                query = sess.createQuery(hql);
            else
                 query = sess.getNamedQuery(hql);
            
            query.setProperties(bean);
            list = query.list();
        }catch(HibernateException e) {
        	log.error("Hibernate Exception:@"+e.getMessage());
            throw new RuntimeException(e);
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return list;
    }
}