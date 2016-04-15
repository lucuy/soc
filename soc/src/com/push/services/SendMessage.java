package com.push.services;
import java.util.Collection;
import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;

public class SendMessage {

	
    public void sendMessageAuto(String userid,String message) {
    	final String userId = userid ;
        final String autoMessage = message; 
        Browser.withAllSessionsFiltered(new ScriptSessionFilter(){
	        public boolean match(ScriptSession session) {
	                  if (session.getAttribute("userId") == null)
	                         return false;
	                  else
	                         return (session.getAttribute("userId")).equals(userId);
	        }
        },new Runnable(){
               private ScriptBuffer script = new ScriptBuffer();
               public void run() {
                      script.appendCall("showMessage", autoMessage);
                      Collection<ScriptSession> sessions = Browser.getTargetSessions();
                      for (ScriptSession scriptSession : sessions) {
                             scriptSession.addScript(script);
                      }
               		}
        		});
    		}
    public void sendMessageAuto1(String userid,String message) {
    	final String userId = userid ;
        final String autoMessage = message; 
        Browser.withAllSessionsFiltered(new ScriptSessionFilter(){
	        public boolean match(ScriptSession session) {
	        	//System.out.println(session.getAttribute("userId1"));
	                  if (session.getAttribute("userId1") == null)
	                         return false;
	                  else
	                         return (session.getAttribute("userId1")).equals(userId);
	        }
        },new Runnable(){
               private ScriptBuffer script = new ScriptBuffer();
               public void run() {
                      script.appendCall("showmessage", autoMessage);
                      Collection<ScriptSession> sessions = Browser.getTargetSessions();
                      for (ScriptSession scriptSession : sessions) {
                             scriptSession.addScript(script);
                      }
               		}
        		});
    		}
}
