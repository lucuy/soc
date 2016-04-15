package com.util.reportForm.util.dataBase;

import java.io.File;
import java.io.StringBufferInputStream;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import com.sun.syndication.io.SAXBuilder;
import com.util.reportForm.datadeal.ExtendedActionServlet;

public class DataBase {
	
	private static Log log = LogFactory.getLog(DataBase.class);
	
	private String driver;	//数据库驱动
	private String url;     //数据库URL
	private String userName;  //数据库用户名
	private String passWord;  //数据库密码
	
	//配置文件路径
	//private final String filePath="";
	private final String filePath = ExtendedActionServlet.setupPath+"WEB-INF"+File.separator+"classes"+File.separator+"hibernate.cfg.xml";;
	
	//sessionFactory 节点名称
	private final String sessionFac = "session-factory";
	
	//property节点名称
	private final String property = "property";
	
	//数据库配置项的属性名称
	private final String dialect = "dialect";
	
	//配置文件表示url的相关字符串
	private final String urlStr = "connection.url";

	//配置文件中表示用户名相关的字符串
	private final String userNameStr = "connection.username";
		
	//配置文件中表示密码的相关字符串
	private final String passWordStr = "connection.password";
	
	/**
	 * 获得数据库的类型
	 * @return 数据库类型
	 */
 	public String InitDateBaseInfo(){
		
 		//数据库类型
 		String dbType = "";
 		
		SAXBuilder saxBuilder = new SAXBuilder(false);
		
		//该方法使得jdom不去读取dtd文件
		saxBuilder.setEntityResolver(new NoOpEntityResolver());

		Document doc = null;
		Element root = null;
		File file = new File(filePath);
		
		//判断文件是否存在
		if(!file.exists()){
			log.debug("数据库配置文件不存在！！");
			return null;
		}
		
		//开始解析配置文件
		try {
			doc = saxBuilder.build(filePath);
			//获得根节点

			root = doc.getRootElement();
			
			//获得session-factory节点
			Element sessionFactoryElement = root.getChild(this.sessionFac);
			
			//获得所有的名为property的节点

			List<Element> proElements = (List<Element>)sessionFactoryElement.getChildren(this.property);
			
			for(Element elmt : proElements){
				//如果是数据库类型的配置，则获取数据库类型
				if(this.dialect.equalsIgnoreCase(elmt.getAttributeValue("name"))){
					dbType = elmt.getTextTrim();
					continue;
				}
				//如果是数据库URl
				if(this.urlStr.equalsIgnoreCase(elmt.getAttributeValue("name"))){
					this.url = elmt.getTextTrim();
					continue;
				}
				//如果是数据库用户名
				if(this.userNameStr.equalsIgnoreCase(elmt.getAttributeValue("name"))){
					this.userName = elmt.getTextTrim();
					continue;
				}
				//如果是数据库密码
				if(this.passWordStr.equalsIgnoreCase(elmt.getAttributeValue("name"))){
					this.passWord = elmt.getTextTrim();
					continue;
				}
			}
			
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//如果是oracle数据库
		if(null!=dbType && (dbType.toLowerCase()).indexOf("oracle")!=-1){
			this.driver = "oracle.jdbc.driver.OracleDriver";
		}
		
		return dbType;
	}
 	
 	//内部类，使saxBuider不去读取dtd文件
 	class NoOpEntityResolver implements EntityResolver {
 		  public InputSource resolveEntity(String publicId, String systemId) {
 		             return new InputSource(new StringBufferInputStream(""));
 		  }
 	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
 	
 	

}
