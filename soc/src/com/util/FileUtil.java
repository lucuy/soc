/**
 * FileUtil.java
 * 
 * Copyright(C)2008 Founder Corporation.
 * written by Founder Corp.
 */
package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * [类名]<br>
 * FileUtil<br>
 * [功能概要]<br>
 * <br>
 * <br>
 * [変更履歴]<br>
 * 2009-3-16 ver1.00 新建 zhaoxinsheng<br>
 * 2010-08-05 ver1.00 加入了extName函数 guoyuxi<br>
 * 
 * @author FOUNDER CORPORATION
 * @version 1.00
 */
public class FileUtil {

    /**
     * 日志打印对象
     */
    private static final Log logger = LogFactory.getLog(FileUtil.class);

    // 删除整个目录，包括子目录 方法
    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] filelist = file.listFiles();
                for (File subfile : filelist) {
                    deleteFile(subfile.getAbsolutePath());
                }
            }
            file.delete();
        }
    }

    /**
     * 自己写文件，一解决在linux中renameTo不能工作的问题
     * 
     * @param f
     * @param newFile
     * @throws IOException
     */
    public static void writeTo(File f, File newFile) throws IOException {
        FileInputStream fis = new FileInputStream(f);
        FileOutputStream fos = new FileOutputStream(newFile);
        try {
            byte[] buf = new byte[8192];
            do {
                int rc = fis.read(buf);
                if (rc == -1)
                    break;
                fos.write(buf, 0, rc);
            } while (true);
        } finally {
            fis.close();
            fos.close();
        }
    }

    // 复制文件
    public static boolean copyFile(File src, File dst) throws IOException {
        // if the parameters are same,then don't excute anything.or it make original file null.
        if (!src.getAbsolutePath().equalsIgnoreCase(dst.getAbsolutePath())) {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dst);
            // Transfer bytes from in to out
            byte[] buf = new byte[1024 * 5];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            // out.flush();
            // in.close();
            out.close();
        }
        return true;
    }

    // 上传文件
    public static boolean upFile(InputStream in, String TargetPath) throws FileNotFoundException, IOException {
        // if the parameters are same,then don't excute anything.or it make original file null.
        File targetfile = new File(TargetPath);

        if (!targetfile.exists())
            makeFile(TargetPath);
        OutputStream out = new FileOutputStream(TargetPath);
        try {
            //System.out.println("=upFile begin transfer file=");
            // Transfer bytes from in to out
            byte[] buf = new byte[1024 * 5];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        } catch (FileNotFoundException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
            throw e;
        } finally {
            in.close();
            out.close();
        }

        return true;
    }

    // 删除文件
    public static boolean delFileByName(String file) throws Exception {
        File f = new File(file);

        // File fB = new File(file1);
        if (f.exists()) {
            logger.info("begin delete the file==>" + file + "  from the file system!");
            f.delete();
        } else {
            logger.info(file + "not in the file system!");
        }
        
        return true;
    }

    /**
     * 创建目录 不存在时创建。已存在目录时，直接返回。
     * 
     * @param folderPath
     *            :目录路径
     * @return
     * @throws IOException
     */
    public static boolean createFolder(String folderPath) throws IOException {
        boolean result = false;
        File f = new File(folderPath);
        if (!f.isDirectory())
            result = f.mkdirs();
        return result;
    }

    /**
     * 删除目录下所有文件
     * 
     * @param directory
     *            (File 对象)
     */
    public static boolean emptyDirectory(File directory) {
        File[] entries = directory.listFiles();
        for (int i = 0; i < entries.length; i++) {
            entries[i].delete();
        }
        return true;
    }

    /**
     * 创建文件
     * 
     * @param filepath
     *            :文件所在目录路径,比如:c:/test/test.txt test目录必须存在。
     * @return
     */
    public static boolean makeFile(String filepath) throws IOException {
        boolean result = false;
        File file = new File(filepath);

        result = file.createNewFile();

        file = null;
        return result;
    }

    /**
     * 删除文件
     * 
     * @param filepath
     *            :文件所在物理路径
     * @return
     */
    public static boolean isDel(String filepath) {
        boolean result = false;
        File file = new File(filepath);
        result = file.delete();
        file = null;
        return result;
    }

    /**
     * 文件重命名
     * 
     * @param filepath
     *            :文件所在物理路径
     * @param destname
     *            :新文件名
     * @return
     */
    public static boolean renamefile(String filepath, String destname) {
        boolean result = false;
        File f = new File(filepath);
        String fileParent = f.getParent();
        String filename = f.getName();
        File rf = new File(fileParent + File.separator + destname);
        if (f.renameTo(rf)) {
            result = true;
        }
        f = null;
        rf = null;
        return result;
    }

    /**
     * 将文件内容写入文件中
     * 
     * @param filepath
     *            :文件所在物理路径
     * @param content
     *            :写入内容
     * @param isAppend
     *            :是否追加写入
     * @throws Exception
     */
    public static boolean WriteFile(String filepath, String content, boolean isAppend) throws Exception {
        FileWriter filewriter = new FileWriter(filepath, isAppend); // 写入多行 第2个参数=true append的方式写入
        PrintWriter printwriter = new PrintWriter(filewriter);
        printwriter.println(content);
        printwriter.flush();
        printwriter.close();
        filewriter.close();
        return true;
    }

    /**
     * 日志备份
     * 
     * @param filePath
     *            :日志备份路径
     * @param baksize
     *            :日志备份大小参考值(字节大小)
     * @throws IOException
     */
    public static boolean logBak(String filePath, long baksize) throws IOException {
        File f = new File(filePath);
        long len = f.length();
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = simpledateformat.format(new Date());
        String fileName = f.getName();
        int dot = fileName.indexOf(".");
        String bakName = s + fileName.substring(dot);
        //System.out.println(bakName);
        if (len >= baksize) {
            renamefile(filePath, bakName);
            makeFile(filePath);
        }
        f = null;
        return true;
    }
    
    /**
     * 得到文件扩展名
     * 
     * @param f
     *            :文件对象
     */
    public static String extName(File f) {
        
        String fileName = f.getName();
        int dot = fileName.lastIndexOf(".") + 1;
        return fileName.substring(dot);
    }
    /**
     * 将文件重命名为当前系统时间
     * @param f 源文件
     */
    public static String renameFileName(String oldname){
    	String filename="";
    	String extension="";
    	int dot = oldname.lastIndexOf(".") + 1;
    	extension=oldname.substring(dot);
    	//获取当前系统时间
    	String currenttimes=DateUtil.curDateMselStr18();
    	filename=currenttimes+"."+extension;
    	System.out.println("重命名文件名是====================="+filename);
    	return filename;
     }
}
