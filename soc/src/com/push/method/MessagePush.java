package com.push.method;
import javax.servlet.ServletException;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContextFactory;

import com.push.util.DwrScriptSessionManagerUtil;

public class MessagePush {

    public void pageOnLoad(String userId) {
        ScriptSession scriptSession = WebContextFactory.get().getScriptSession();
        scriptSession.setAttribute(userId, userId);
        DwrScriptSessionManagerUtil dwrScriptSessionManagerUtil = new DwrScriptSessionManagerUtil();
        try {
               dwrScriptSessionManagerUtil.init();
        } catch (ServletException e) {
               e.printStackTrace();
        }
    }

}
