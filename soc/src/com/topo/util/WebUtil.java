package com.topo.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * User: ChenZQ YuJF
 * Date: 2011-08-11
 * Time: 14:54:55
 */
public class WebUtil {
	private static Logger logger = LoggerFactory.getLogger(WebUtil.class.getName());
//    public static void output(String content, HttpServletResponse response, String contentType) {
//        if (contentType.equals(WebConstants.ContentType_JSON)) {
//            outputJson(content, response);
//        } else if (contentType.equals(WebConstants.ContentType_XML)) {
//            outputXml(content, response);
//        } else {
//            response.setContentType(contentType);
//            output(content, response);
//        }
//    }

    public static void outputPlainText(String content, HttpServletResponse response) {
        response.setContentType(WebConstants.ContentType_PLAINTEXT);
        output(content, response);
    }

    public static void outputXml(String content, HttpServletResponse response) {
        response.setContentType(WebConstants.ContentType_XML);
        output(content, response);
    }

    public static void outputJson(String content, HttpServletResponse response) {
        response.setContentType(WebConstants.ContentType_JSON);
        output(content, response);
    }
    
    public static <K> void outputJson(ProcessResult<K> pr, HttpServletResponse response) {
        response.setContentType(WebConstants.ContentType_JSON);
        JSONObject jsonObject=JSONObject.fromObject(pr);
        logger.debug(jsonObject.toString());
        output(jsonObject.toString(), response);
    }

    public static void outputHTML(String content, HttpServletResponse response) {
        response.setContentType(WebConstants.ContentType_HTML);
        output(content, response);
    }
    
    private static void output(String content, HttpServletResponse response) {
        OutputStream out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            out = response.getOutputStream();
            response.setContentLength(content.getBytes("UTF-8").length);
            out.write(content.getBytes("UTF-8"));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException ignore) {
            }
        }
    }

    @SuppressWarnings("unchecked")
	public static <K> List<K> getObjectListFromJsonString(String jsonString, Class<K> objectClass) {
		List<K> list=new ArrayList<K>();
		JSONArray jsonArray=JSONArray.fromObject(jsonString);
		Object o;
		JSONObject jsonObject;
		K k;
		for(int i=0;i<jsonArray.size();i++){
			o=jsonArray.get(i);
			jsonObject=JSONObject.fromObject(o);
			k= (K) JSONObject.toBean(jsonObject,objectClass);
			list.add(k);
		}
		return list;
	}

	public static ProcessResult<Map<String, Object>> getParameter(
			HttpServletRequest request, String[] stringParemeters,
			String[] intParemeters) {
		ProcessResult<Map<String,Object>> pr=new ProcessResult<Map<String,Object>>();
		Map<String,Object> map=new HashMap<String, Object>();
		int intValue;
		String strValue;
		try {
			for(String intParameter:intParemeters){
				intValue=Integer.parseInt(request.getParameter(intParameter));
				map.put(intParameter, intValue);
			}
			for(String strParameter:stringParemeters){
				strValue=request.getParameter(strParameter);
				map.put(strParameter, strValue);
			}
			pr.setSuccess(true);
			pr.setData(map);
		} catch (ClassCastException e) {
			pr.setSuccess(false);
			pr.setMessage("整数类型转化错误");
		} catch (Exception e) {
			pr.setSuccess(false);
			pr.setMessage("参数获取失败");
		}
		return pr;
	}
    
	//处理中文参数
	public static String disposeChineseParam(String param) throws UnsupportedEncodingException {
		param = new String(param.getBytes("iso-8859-1"),"GBK");
		return param;
	}
	 
}
