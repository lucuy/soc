package com.util.reportForm.datadeal;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;

/**
 * Data Access Object (DAO) interface. 
 * This is an interface used to tag our DAO
 * classes and to provide common methods to all DAOs.
 * 
 * 
 * @author caoguangxin www.relationinfo.com
 */
public interface DAO {
	/**
	 * 
	 * @param name
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public Object get(Class name,Serializable id)throws HibernateException;

	/**
	 * 
	 * @param o
	 * @return
	 * @throws DataAccessException
	 */
	public Object save(Object o)throws HibernateException;

	/**
	 * Generic method used to get all objects of a particular type. This is the
	 * same as lookup up all rows in a table.
	 * 
	 * @param clazz
	 *            the type of objects (a.k.a. while table) to get data from
	 * @return List of populated objects
	 */
//	public Collection getAll(Class clazz) throws HibernateException;
		/**
	 * Generic method to get an object based on class and identifier. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param clazz
	 *            model class to lookup
	 * @param id
	 *            the identifier (primary key) of the class
	 * @return a populated object
	 * @see org.springframework.orm.ObjectRetrievalFailureException
	 */
	public Object load(Class clazz, Serializable id) throws HibernateException;


	/**
	 * Generic method to delete an object based on class and id
	 * 
	 * @param clazz
	 *            model class to lookup
	 * @param id
	 *            the identifier (primary key) of the class
	 */
	public void delete(Object clazz) throws HibernateException;
	/**
	 * 
	 * @param name
	 * @param params
	 * @return
	 * @throws DataAccessException
	 */
	public Collection getNamedQuery(final String name, final Map params) 
	throws HibernateException;

}
