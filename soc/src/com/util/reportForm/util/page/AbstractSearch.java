package com.util.reportForm.util.page;


import java.util.List;

import com.util.reportForm.util.hibernate.hibernateUtil.HibernateUtil;


 
/**
 * <p>Title: 预查询初始化程序</p>
 * <p>Description: 所有的初始化查询必须继承此类，本类只负责预查询ID集和Page对象的初始化，不实现显示逻辑</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: 四方人才网</p>
 * @author Yeno.hhr
 * @version 1.0
 */
public abstract class AbstractSearch {

    public AbstractSearch()
    {
        super();
    }
    /**
     * 根据HQL查询出记录的主键ID（主索引）集合
     * 注：此hql必须是只检索主键及复合主键的查询语句，具体见应用实例
     * @param hql 不带查询的查询语句
     * @return idArray 主索引集合（可以主键ID，也可以是复合ID）
     */
    public Object[] getIDList(String hql)
    {
        List list = HibernateUtil.query(hql);
        if (list==null || list.size()==0)
            return null;
        return list.toArray();
    }
    /**
     * 根据HQL查询出记录的主键ID（主索引）集合
     * 注：此hql必须是只检索主键及复合主键的查询语句，具体见应用实例
     * @param hql 带参数的查询语句
     * @param bean 参数设置实体类
     * @return Object[] 主索引集合（可以主键ID，也可以是复合ID）
     */
    public Object[] getIDList(String hql, Object bean)
    {
        List list = HibernateUtil.query(hql,bean);
        if (list==null || list.size()==0)
            return null;
        return list.toArray();
    }
    /**子类方法：根据子类的需要选择调用AbstractSearch的“带参”和“不带参”两种查询中的一种返回主键ID的数组集*/
    abstract public Object[] getList();
    /**子类方法：设定查询条件*/
    abstract protected void condition();
}

