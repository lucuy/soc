package com.util.reportForm.util.hibernate;


import org.hibernate.HibernateException;
import org.hibernate.Session;
/**
 * @author sunke
 * @see Session
 * @version 1.0 2008-1-31
 * 
 */
public interface HibernateCallback {
	/*
	 * @param Session 
	 * @return Object 
	 * @exception HibernateException
	 */
	Object execute(Session session)throws HibernateException;
}
