package com.util.reportForm.util.page;


import java.util.ArrayList;
import java.util.List;

import com.util.reportForm.util.hibernate.hibernateUtil.HibernateUtil;




/**
 * <p>Title: 分页及初始化分页程序</p>
 * <p>Description: 所有的初始化分页必须继承此类，如果是预查询调用，同时会初始化Page实体，否则Page实体会由FormBean提交生成</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: 四方人才网</p>
 * @author Yeno.hhr
 * @version 1.0
 */
abstract public class AbstractList {
    private Page page;
    /**查询结果的主键ID集*/
    private Object[] idList;
    
    public AbstractList(){}
    /**
     * 预查询初始化分页构造（初次查询）
     * @param hql 查询语句
     * @param search 预查询类
     */
    public AbstractList(AbstractSearch search){
        this.idList=search.getList();
    }
    /**
     * 查询分页构造（分页查询）
     * @param page 分页状态实体
     * @param idList
     */
    public AbstractList(Page page, Object[] idList){
        this.idList=idList;
        this.page=page;
    }
    /**子类方法：设置分页查询的查询语句*/
    abstract protected String getHql();
    /**
     * 返回查询结果
     * @return Result
     */
    public Result getResult(){
        if(page==null){
            if(idList==null)
                this.page = PageUtil.createPage(SysConstant.PAGE_SIZE,1,0);
            else
                this.page = PageUtil.createPage(SysConstant.PAGE_SIZE,1,idList.length);
        }
        return new Result(page, getListByPage());
    }
    /**
     * 分页查询，返回当前页的查询结果集
     * @param hql
     * @return list 结果集
     */
    public List getListByPage(){
        List list = null;
        if (page.getTotalPage() < 1)
            return list;
        try{
            String hql=getHql();
            if(hql==null || hql.equals(""))
                return list;
            Object[] bean=getCurrentIds();
            if(bean!=null)
                list=HibernateUtil.query(hql,bean);
            else
                list=HibernateUtil.query(hql);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return list;
    }
    /**
     * 从查询结果的ID集中找出当前页的ID集
     * @param arr 所有查询结果的主键ID集
     * @return Object[]
     */
    private Object[] getCurrentIds(){
        if(idList==null)    return null;
        ArrayList<Object> list = new ArrayList<Object>();
        int begin = page.getBeginIndex();
        int ends = page.getTotalRecords();
        int end = begin+page.getEveryPage();
        if (end >= ends)
            end = ends;
        for (int l=begin;l<end;l++){
            list.add(idList[l]);
        }        
        return list.toArray();
    }
    /**
     * 返回查询结果主键ID集的字符串组合形式
     * @return String
     */
    public String getIdList(){
        String ids="";
        if(idList == null)
            return ids;
        for(int x=0; x<idList.length; x++){
            ids+=idList[x].toString();
            if(x<idList.length-1)
                ids+=",";
        }
        return ids;
    }
}
