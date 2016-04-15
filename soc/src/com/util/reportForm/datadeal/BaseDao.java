package com.util.reportForm.datadeal;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.util.reportForm.util.hibernate.HibernateCallback;
import com.util.reportForm.util.hibernate.HibernateTemplate;

/**
 * @author sunke
 * @see
 * @version 1.0 2008-1-31
 */
public class BaseDao extends HibernateDaoSupport implements DAO {
	
	/*
	 * 清空表
	 */
	public void deleteTable(final String Tablename)throws HibernateException {
	new HibernateTemplate().run(new HibernateCallback(){

		public Object execute(Session session) throws HibernateException {
			Query query=session.createQuery("delete from "+Tablename);
			query.executeUpdate();
			return null;
		}
		
	});
}
	/*
	 * 保存 @param Object @return Object @exception HibernateException
	 */
	public Object save(final Object o) throws HibernateException {
		return new HibernateTemplate().run(new HibernateCallback() {
					public Object execute(Session session)
							throws HibernateException {
						return session.save(o);
					}
				});
	}
	
	/*
	 * 更新 @param Object @return Object @exception HibernateException
	 */
	public void updateAll(final Collection entities) throws HibernateException {
		 new HibernateTemplate().run(new HibernateCallback(){

			public Object execute(Session session) throws HibernateException {
				for (Iterator it = entities.iterator(); it.hasNext();) {
					session.update(it.next());
					
				}
				return null;
			}
			 
		 });
	}

	/*
	 * 保存全部 @param Collection @return Object @exception HibernateException
	 */
	public Object saveAll(final Collection entities) throws HibernateException {
		return new HibernateTemplate().run(new HibernateCallback() {
			public Object execute(Session session) throws HibernateException {
				int i = 0;
				for (Iterator it = entities.iterator(); it.hasNext();) {
					session.save(it.next());
					
					//清空缓存
					if( (i++) % 20 == 0){
						session.flush();
						session.clear();
					}
				}
				return entities;
			}
		});
	}

	/*
	 * 删除 @param Object @return void @exception HibernateException
	 */
	public void delete(final Object clazz) throws HibernateException {
		new HibernateTemplate().run(new HibernateCallback() {// ������ʱ��������
					public Object execute(Session session)
							throws HibernateException {
						session.delete(clazz);// �����
						return null;
					}
				});
	}

	/*
	 * 删除全部 @param Object @return void @exception HibernateException
	 */
	public void deleteAll(final Collection entities) throws HibernateException {
		new HibernateTemplate().run(new HibernateCallback() {
			public Object execute(Session session) throws HibernateException {
				for (Iterator it = entities.iterator(); it.hasNext();) {
					session.delete(it.next());
				}
				return null;
			}
		});
	}

	/*
	 * 根据ID获得该对象(用于已存在的对象,无此对象返回空) @param Class,Serializable @return Object
	 * @exception HibernateException
	 */
	public Object get(final Class name, final Serializable id)
			throws HibernateException {
		return new HibernateTemplate().run(new HibernateCallback() {// ������ʱ��������
					public Object execute(Session session)
							throws HibernateException {
						return session.get(name, id);// ��ʵҵ�����
					}
				});
	}

	/*
	 * 查询 @param String ,Object[] @return Collection @exception
	 * HibernateException
	 */
	public Collection getNamedQuery(final String name, final Map params)
			throws HibernateException {
		return (Collection) new HibernateTemplate()
				.run(new HibernateCallback() {
					public Object execute(Session session)
							throws HibernateException {
						Query q = session.createQuery(name);
						if (null != params && params.size() > 0) {
							for (Iterator i = params.entrySet().iterator(); i.hasNext();){
								Map.Entry entry = (Map.Entry) i.next();
								setParameterValue(q, (String) entry.getKey(),entry.getValue());
							}
						}
						Collection list = q.list();
						return q.list();
					}
				});
	}

	/*
	 * 根据ID获得该对象(用于已存在的对象,无此对象抛异常) @param Class,Serializable @return Object
	 * @exception HibernateException
	 */

	public Object load(final Class clazz, final Serializable id)
			throws HibernateException {
		Object o = new HibernateTemplate().run(new HibernateCallback() {// ������ʱ��������
					public Object execute(Session session)
							throws HibernateException {
						return session.load(clazz, id);// ��ʵҵ�����
					}
				});
		if (o == null) {
			throw new HibernateException("dd");
		}
		return o;
	}

	/*
	 * 保存或更新 @param Object @return void @exception HibernateException
	 */

	public void saveOrUpdate(final Object entity) throws HibernateException {
		new HibernateTemplate().run(new HibernateCallback() {
			public Object execute(Session session) throws HibernateException {
				session.saveOrUpdate(entity);
				return null;
			}
		});
	}

	/*
	 * 匹配类型 @param Query,Object[] @return void @exception
	 */

	protected void setParameterValue(Query query, String key, Object value) {
		if (null == key || null == value) {
			return;
		} else if (value instanceof Boolean) {
			query.setBoolean(key, ((Boolean) value).booleanValue());
		} else if (value instanceof String) {
			query.setString(key, (String) value);
		} else if (value instanceof Integer) {
			query.setInteger(key, ((Integer) value).intValue());
		} else if (value instanceof Long) {
			query.setLong(key, ((Long) value).longValue());
		} else if (value instanceof Float) {
			query.setFloat(key, ((Float) value).floatValue());
		} else if (value instanceof Double) {
			query.setDouble(key, ((Double) value).doubleValue());
		} else if (value instanceof BigDecimal) {
			query.setBigDecimal(key, (BigDecimal) value);
		} else if (value instanceof Byte) {
			query.setByte(key, ((Byte) value).byteValue());
		} else if (value instanceof Calendar) {
			query.setCalendar(key, (Calendar) value);
		} else if (value instanceof Character) {
			query.setCharacter(key, ((Character) value).charValue());
		} else if (value instanceof Timestamp) {
			query.setTimestamp(key, (Timestamp) value);
		} else if (value instanceof Date) {
			query.setDate(key, (Date) value);
		} else if (value instanceof Short) {
			query.setShort(key, ((Short) value).shortValue());
		}
	}

	// 记录数
	public Integer getQueryCount(final String hql, final Map values)
			throws HibernateException {
		return (Integer) new HibernateTemplate().run(new HibernateCallback() {
			public Object execute(Session session) throws HibernateException {
				int fromIndex = hql.indexOf("from");
				String q = "select count(*) " + hql.substring(fromIndex);
				Query query = session.createQuery(q);
				// 设置参数
				if (values != null && values.size() > 0) {
					for (Iterator i = values.entrySet().iterator(); i.hasNext();) {
						Map.Entry entry = (Map.Entry) i.next();
						setParameterValue(query, (String) entry.getKey(), entry
								.getValue());
					}
				}
				Integer queryCount = (Integer) query.uniqueResult();
				return queryCount;
			}
		});
	}

	// 分页
	public Collection getQueryResult(final String hql, final Map values,
			final Integer startRow, final Integer pageSize)
			throws HibernateException {
		return (Collection) new HibernateTemplate()
				.run(new HibernateCallback() {
					public Object execute(Session session)
							throws HibernateException {
						Query query = session.createQuery(hql);
						
						// 设置参数
						if (values != null && values.size() > 0) {
							for (Iterator i = values.entrySet().iterator(); i
									.hasNext();) {
								Map.Entry entry = (Map.Entry) i.next();
								setParameterValue(query, (String) entry
										.getKey(), entry.getValue());
							}
						}
						query.setFirstResult(startRow);
						query.setMaxResults(pageSize);
						return query.list(); 
					}
				});
	}
}
