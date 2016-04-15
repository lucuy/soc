package com.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadXML
{
   
    public static Map TestJava(String str) throws DocumentException
    {
        Map<String, Object> map = new HashMap<String, Object>();
        
        SAXReader reader = new SAXReader(); 
        Document  document = reader.read(new File(str)); 
        Element rootElm = document.getRootElement(); 
        
        List nodes = rootElm.elements("Item"); 
            for (Iterator it = nodes.iterator(); it.hasNext();) { 
              Element elm = (Element) it.next(); 
              String a=elm.getStringValue();
              String b=elm.attributeValue("name"); 
              map.put(b, a);
           }
            return map;
            
           
        }
    
}
