package com.util.reportForm.util.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.util.reportForm.framework.exceptions.DatastoreException;
import com.util.reportForm.util.hibernate.hibernateUtil.HibernateUtil;

/**
 * @author sunke
 * @see HibernateCallback
 * @version 1.0 2008-1-31
 * 
 */

public class HibernateTemplate {

	/*
	 * @param HibernateCallback 
	 * @return Object 
	 * @exception HibernateException
	 */
	public Object run(HibernateCallback callback) throws HibernateException {

		Transaction tx = null;
		Object rs = null;
		Session session = null;
		try {
			// 获得session
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();// 加入Transaction的处理
			rs = callback.execute(session);// 回调 ���ò����
			tx.commit();// 事务提交
		} catch (HibernateException e) {
			tx.rollback();// 回滚
			throw new RuntimeException(e);
		} catch (DatastoreException e1) {
			e1.printStackTrace();
		} finally {
			if (session != null) {
				try {
					HibernateUtil.closeSession(session);// 关闭session
				} catch (DatastoreException e) {
					e.printStackTrace();
				}
			}
		}
		return rs;
	}

}
