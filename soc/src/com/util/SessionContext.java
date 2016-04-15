/*
 * 文 件 名:  SessionContext.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-9-26
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.util;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

/**
 * Session对象管理类
 * <功能详细描述>
 * 
 * @author  liuyanxu
 * @version  [版本号, 2012-9-26]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SessionContext 
{
    private static SessionContext instance;
    private HashMap mymap;
    private SessionContext() {
        mymap = new HashMap();
    }
    public static SessionContext getInstance() {
        if (instance == null) {
            instance = new SessionContext();
        }
        return instance;
    }
    public synchronized void AddSession(HttpSession session) {
        if (session != null) {
            mymap.put(session.getId(), session);
        }
    }
    public synchronized void DelSession(HttpSession session) {
        if (session != null) {
            mymap.remove(session.getId());
        }
    }
    public synchronized HttpSession getSession(String session_id) {
        if (session_id == null) return null;
        return (HttpSession) mymap.get(session_id);
    }
}
