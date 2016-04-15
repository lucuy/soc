package com.util.topoSearch;


import java.io.File;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * ʹ�õݹ����һ��XML�ĵ����ҽ��������������������
 * 
 */
public class ReadXML
{
    public static void main(String[] args) throws Exception
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("C:\\Users\\liuyanxu\\Desktop\\a.xml"));

        // ��ȡ��Ԫ�ؽڵ�
        Element root = doc.getDocumentElement();

        parseElement(root);
    }

    private static void parseElement(Element element)
    {
        String tagName = element.getNodeName();

       // System.out.print("<" + tagName);

        // elementԪ�ص��������Թ��ɵ�NamedNodeMap������Ҫ��������ж�
        NamedNodeMap map = element.getAttributes();

        // ���������ԣ����ӡ����
        if (null != map && map.getLength()<3)
        {   
            for (int i = 0; i < map.getLength(); i++)
            {
                // ��ø�Ԫ�ص�ÿһ������
                Attr attr = (Attr) map.item(i);

                // �����������ֵ
                String attrName = attr.getName();
                String attrValue = attr.getValue();
               if(Pattern.matches("\\d+\\.\\d+\\.\\d+\\.\\d+", attrValue))
               {  
                // ע������ֵ��Ҫ������ţ�������Ҫ\ת��
                 System.out.print("ip��ַΪ��" + attrValue+"\n");
               }
            }
        }

        // �رձ�ǩ��
       // System.out.print(">");

        // �����Ѿ���ӡ����Ԫ�����������
        // ���濪ʼ���������Ԫ��
        NodeList children = element.getChildNodes();

        for (int i = 0; i < children.getLength(); i++)
        {

            // ��ȡÿһ��child
            Node node = children.item(i);
            // ��ȡ�ڵ�����
            short nodeType = node.getNodeType();

            if (nodeType == Node.ELEMENT_NODE)
            {
                // �����Ԫ�����ͣ���ݹ����
            	if(node.getNodeName().equals("host")||node.getNodeName().equals("address"))
            	{
                    parseElement((Element) node);
            	}
            }
            else if (nodeType == Node.TEXT_NODE)
            {
                // ������ı����ͣ�������ڵ�ֵ�����ı�����
                //System.out.print(node.getNodeValue());
            }
            else if (nodeType == Node.COMMENT_NODE)
            {
                // �����ע�ͣ������ע��
               // System.out.print("<!--");

                Comment comment = (Comment) node;

                // ע������
                String data = comment.getData();

               // System.out.print(data);

              //  System.out.print("-->");
            }
        }

        // �������ݴ�����֮��������رո�ڵ�
       // System.out.print("</" + tagName + ">");
    }
}