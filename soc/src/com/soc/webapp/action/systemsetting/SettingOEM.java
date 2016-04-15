package com.soc.webapp.action.systemsetting;



import java.io.File;
import java.io.IOException;

import com.soc.model.systemsetting.Setting;
import com.soc.service.systemsetting.SettingFunctionControlSerive;
import com.soc.service.systemsetting.SettingService;
import com.soc.webapp.action.BaseAction;
import com.util.FileUtil;



public class SettingOEM extends BaseAction {

	private File oemTar;

	private String oemTarFileName;

	private static String path="";

	private boolean upStatr = false; //上传是否成功
	//功能控制过来的值  用他来判断用什么sql
	private String functionControl; // 0:JSOC-EM       1:JSOC-RM      2:JSOC-NM        3: JSOC-CM
	//
	private SettingFunctionControlSerive settingFunctionControlManager;
	private SettingService settingManager;//系统设置service
	public String toUpdate(){

		System.out.println("--------------------command----------------");

		System.out.println(super.getRealyPath("/images"));

		path=super.getRealyPath("/reportFile");

		return SUCCESS;

	}

	public String upOEMTar(){

		File saveFile= new File(path,"/"+oemTarFileName);

		try {

			upStatr=FileUtil.copyFile(oemTar, saveFile);

		} catch (IOException e) {

			e.printStackTrace();

		}

		

		return SUCCESS;

	}

	public void updateOEM(){

		

		 Runtime run = Runtime.getRuntime();

		 try {

			// System.out.println("长传完成");

			 //System.out.println("开始赋权限");

			 

			 run.exec("chmod 777 "+path+"/"+oemTarFileName);

//			 System.out.println("结束赋权限");

//			 System.out.println("开始解压");

			 run.exec("tar -zxvf "+path+"/"+oemTarFileName +" -C "+ path);

			// System.out.println("解压结束");

			 String [] fileNames = oemTarFileName.split("\\.");

			 String fileName=fileNames[0];

//			 System.out.println("文件夹名称："+fileName);

//			 System.out.println("开始复制图片");
			 Thread.sleep(3000);
			 run.exec("chmod 777 "+path+"/"+fileName+"/*");

//			 System.out.println(super.getRealyPath("/images"));

			 File f =new File(path+"/"+fileName);
			 
				if(f.isDirectory()){

					File [] files = f.listFiles();
					System.out.println("fileSize----"+fileNames.length);
					for (File file : files) {
						System.out.println("fileName-----" +file.getName());
						if(!file.getName().endsWith(".jsp")){

							//复制图片

							if(!file.getName().endsWith(".db")){
								
								run.exec("cp "+path+"/"+fileName+"/"+file.getName()+" " +super.getRealyPath("/images"));
								System.out.println(file.getName());
								Thread.sleep(3000);
							}

						}

					}

				}

//			 System.out.println("备份taglibs.jsp");

			 run.exec("cp "+super.getRealyPath("/pages/commons/taglibs.jsp") +" " +super.getRealyPath("/pages/commons")+"/taglibs.jsp.bak");

//			 System.out.println("备份taglibs.jsp结束");

//			 System.out.println("复制taglibs.jsp结束");

			 run.exec("cp "+path+"/"+fileName+"/taglibs.jsp" +" " +super.getRealyPath("/pages/commons"));

//			 System.out.println("复制taglibs.jsp结束");
			 Thread.sleep(5000);
			 //删除oem包

			 run.exec("rm -rf "+path+"/"+fileName);

			 run.exec("rm -rf "+path+"/"+oemTarFileName);

			 try

             {

                 getResponse().getWriter().write("tomcat6");

             }

             catch (IOException e)

             {

                 e.printStackTrace();

             }

			 /*  // 重启服务器升级系统

             try

             {

            	 run.exec("reboot");

                 ////System.out.println("123");

             }

             catch (Exception e)

             {

                 e.printStackTrace();

             }*/

			// run.exec(" reboot");

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	public String functionColtrolDeail(){
	    functionControl =settingManager.queryByKey("functionControl");
	    return SUCCESS;
	}
	/**
	 * 
	 * 功能控制的action 根据传过来的id 调用不同的sql 对权限表操作 达到屏蔽模块的目的
	 */
	public String functionColtrolAction(){
		String sqName  = "";//定义sql文件名字的变量
	if (this.functionControl.equals("0")) {
		sqName = "JSOC-EM";
	} else if (this.functionControl.equals("1")) {
		sqName = "JSOC-RM";
	}else if (this.functionControl.equals("2")) {
		sqName = "JSOC-NM";
	}else if (this.functionControl.equals("3")) {
		sqName = "JSOC-CM";
	}
	else if (this.functionControl.equals("4")) {
        sqName = "JSOC-ALL";
    }
	this.settingFunctionControlManager.SettingFunctionControl(sqName);
	Setting setting = new Setting();
	setting.setKey("functionControl");
	setting.setValue(functionControl);
	settingManager.updateByKey("functionControl", setting);
	/* Runtime run = Runtime.getRuntime();
	 try {
		run.exec("init 6");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
		return null;
	}

	public File getOemTar() {

		return oemTar;

	}

	public void setOemTar(File oemTar) {

		this.oemTar = oemTar;

	}

	public String getOemTarFileName() {

		return oemTarFileName;

	}

	public void setOemTarFileName(String oemTarFileName) {

		this.oemTarFileName = oemTarFileName;

	}

	public boolean isUpStatr() {

		return upStatr;

	}

	public void setUpStatr(boolean upStatr) {

		this.upStatr = upStatr;

	}

	public String getFunctionControl() {
		return functionControl;
	}

	public void setFunctionControl(String functionControl) {
		this.functionControl = functionControl;
	}

	public SettingFunctionControlSerive getSettingFunctionControlManager() {
		return settingFunctionControlManager;
	}

	public void setSettingFunctionControlManager(
			SettingFunctionControlSerive settingFunctionControlManager) {
		this.settingFunctionControlManager = settingFunctionControlManager;
	}

    public SettingService getSettingManager()
    {
        return settingManager;
    }

    public void setSettingManager(SettingService settingManager)
    {
        this.settingManager = settingManager;
    }

	
	

}

