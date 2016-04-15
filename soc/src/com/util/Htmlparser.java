package com.util;





import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
 
public class Htmlparser {
    
 
    /**
     * 返回符合要求的第一个标签的文本内容
     * @param content  要解析的文本
     * @param tagName  要解析的标签
     * @param attributesMap  标签的参数和数值，如果没有参数需要解析的话，就为null
     * @return
     */
    public static NodeList getTagContent(String content, String tagName,
            Map<String, String> attributesMap) {
        Parser myParser = Parser.createParser(content, "UTF-8"); // 换成你自己的编码，这里用的是UTF-8
        NodeFilter tagFilter = new TagNameFilter(tagName);
        List<NodeFilter> attributeFiltersList = new ArrayList<NodeFilter>();
        if (attributesMap != null) {
            for (Map.Entry<String, String> entry : attributesMap.entrySet()) {
                attributeFiltersList.add(new HasAttributeFilter(entry.getKey(),
                        entry.getValue()));
            }
        }
        attributeFiltersList.add(0, tagFilter);
        NodeFilter filter = new AndFilter(attributeFiltersList.toArray(new NodeFilter[attributeFiltersList.size()]));
        try {
             
            	return myParser.parse(filter);
           
      
        } catch (ParserException e) {
           e.printStackTrace();
            return null;
        }
 
    }
}
  

