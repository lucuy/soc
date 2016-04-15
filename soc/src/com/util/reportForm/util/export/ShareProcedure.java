package com.util.reportForm.util.export;

/* ShareProcedure - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;



//import org.apache.log4j.Logger;

public class ShareProcedure {
//    private static Logger log = Logger.getLogger(ShareProcedure.class.getName());
    public int service_flag;
    public static String MM_itop_DRIVER = "org.gjt.mm.mysql.Driver";
    public static String MM_itop_STRING =
            "jdbc:mysql://127.0.0.1/itop?useUnicode=true&characterEncoding=GBK";
    public static String MM_itop_USERNAME = "root";
    public static String MM_itop_PASSWORD = "1qw23e";

    public static void setMM_itop_DRIVER(String mm_itop_DRIVER) {
        MM_itop_DRIVER = mm_itop_DRIVER;
    }

    public static void setMM_itop_STRING(String mm_itop_STRING) {
        MM_itop_STRING = mm_itop_STRING;
    }

    public static void setMM_itop_USERNAME(String mm_itop_USERNAME) {
        MM_itop_USERNAME = mm_itop_USERNAME;
    }

    public static void setMM_itop_PASSWORD(String mm_itop_PASSWORD) {
        MM_itop_PASSWORD = mm_itop_PASSWORD;
    }

    public static Connection getConnection
            (String MM_itop_DRIVER, String MM_itop_STRING,
             String MM_itop_USERNAME,
             String MM_itop_PASSWORD) throws Exception, SQLException {
//        try {
//            Driver Driver_account_save
//                = (Driver) Class.forName(MM_itop_DRIVER).newInstance();
//            Connection Conn_account_save
//                = DriverManager.getConnection(MM_itop_STRING, MM_itop_USERNAME,
//                                              MM_itop_PASSWORD);
//            Connection connection = Conn_account_save;
//            return connection;
//        } catch (SQLException e) {
//            //System.out.print(e.getMessage());
//            Connection connection = null;
//            return connection;
//        }
      //  Connection connection = DBConnection.getDBConnection();
       // return connection;
    	return null;
    }

    public static String[][] getResultSet(String sqlstr, String str_r) throws
            Exception, SQLException {
        String errmsg
                =
                "\u53d6sql\u67e5\u8be2\u7ed3\u679c,\u8fd4\u56de\u6570\u7ec4----";
        Connection sqlConn = null;
        Connection rowConn = null;
        Statement sqlStmt = null;
        Statement rowStmt = null;
        ResultSet rs = null;
        ResultSet rsRow = null;
        String rowSql = "";
        String[][] array = null;
        try {
            int rowcount = 0;
            int cols = 0;
            int i = 0;
            int j = 0;
            int m = 0;
            errmsg = "\u5efa\u7acb\u8fde\u63a5";
            sqlConn = getConnection(MM_itop_DRIVER, MM_itop_STRING,
                                    MM_itop_USERNAME, MM_itop_PASSWORD);
            sqlStmt = sqlConn.createStatement();
            errmsg = "\u53d6\u603b\u884c\u6570";
            rowConn = getConnection(MM_itop_DRIVER, MM_itop_STRING,
                                    MM_itop_USERNAME, MM_itop_PASSWORD);
            rowStmt = sqlConn.createStatement();
            rsRow = sqlStmt.executeQuery(sqlstr);
            while (rsRow.next()) {
                rowcount++;
            }
            if (rsRow != null) {
                rsRow.close();
            }
            if (rowStmt != null) {
                rowStmt.close();
            }
            if (rowConn != null) {
                rowConn.close();
            }
            errmsg = "\u67e5\u8be2";
            rs = sqlStmt.executeQuery(sqlstr);
            errmsg = "\u53d6\u603b\u5217\u6570";
            cols = rs.getMetaData().getColumnCount();
            errmsg = "\u751f\u6210\u7ed3\u679c\u6570\u7ec4";
            array = new String[rowcount][cols];
            while (rs.next()) {
                for (j = 0; j < cols; j++) {
                    errmsg
                            = (String.valueOf
                               (new StringBuffer
                                ("\u751f\u6210\u7ed3\u679c\u6570\u7ec4  cols:")
                                .append
                                (cols).append
                                (" i:").append
                                (i).append
                                (" j:").append(j)));
                    //��Ҫ�Ƿ�ֹɨ�����ʱ�����治ʶ�����һ��ţ��ɴ�ȥ��
//                    String temp=Gfun.tochinese(rs.getString(j + 1));
//                    if(temp.lastIndexOf(",")==temp.length()-1)temp=temp.substring(0,temp.length()-1);
//                    array[i][j] =temp;
                    array[i][j]=Gfun.tochinese(rs.getString(j + 1));
                    if (array[i][j] == null || array[i][j].equals("")) {
                        array[i][j] = str_r;
                    }
                }
                i++;
            }
            m = 40;
            errmsg = "\u5173\u95ed\u8fde\u63a5";
            if (rs != null) {
                rs.close();
            }
            if (sqlStmt != null) {
                sqlStmt.close();
            }
            if (sqlConn != null) {
                sqlConn.close();
            }
            return array;
        } catch (SQLException e) {
            throw new Exception
                    (String.valueOf
                     (new StringBuffer(String.valueOf(e)).append
                      (
                    "<br>\u53d6sql\u67e5\u8be2\u7ed3\u679c,Sql\u9519\u8bef----")
                      .append(errmsg)));
        } catch (Exception e) {
            throw new Exception
                    (String.valueOf
                     (new StringBuffer(String.valueOf(e)).append
                      ("<br>\u53d6sql\u67e5\u8be2\u7ed3\u679c----")
                      .append(errmsg)));
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (sqlStmt != null) {
                sqlStmt.close();
            }
            if (sqlConn != null) {
                sqlConn.close();
            }
        }
    }

    public static String[][] getResultSet(String sqlstr) throws Exception,
            SQLException {
        return getResultSet(sqlstr, "");
    }

    public String gettemp(String sqlstr) throws Exception, SQLException {
        String errmsg
                =
                "\u53d6sql\u67e5\u8be2\u7ed3\u679c,\u8fd4\u56de\u6570\u7ec4----";
        Connection sqlConn = null;
        Connection rowConn = null;
        Statement sqlStmt = null;
        Statement rowStmt = null;
        ResultSet rs = null;
        ResultSet rsRow = null;
        String[][] array = null;
        String result_return = "222";
        int rowcount = 0;
        int cols = 0;
        int i = 0;
        int j = 0;
        int m = 0;
        Vector[][] rowStr = new Vector[0][];
        String rowSql = "";
        try {
            errmsg = "\u5efa\u7acb\u8fde\u63a5";
            sqlConn = getConnection(MM_itop_DRIVER, MM_itop_STRING,
                                    MM_itop_USERNAME, MM_itop_PASSWORD);
            m = 10;
            sqlStmt = sqlConn.createStatement();
            errmsg = "\u67e5\u8be2";
            m = 20;
            rs = sqlStmt.executeQuery(sqlstr);
            m = 30;
            errmsg = "\u53d6\u603b\u5217\u6570";
            cols = rs.getMetaData().getColumnCount();
            m = 40;
            errmsg = "\u53d6\u603b\u884c\u6570";
            rs.last();
            rowcount = rs.getRow();
            array = new String[rowcount][cols];
            while (rs.next()) {
                for (j = 0; j < cols; j++) {
                    errmsg
                            = (String.valueOf
                               (new StringBuffer
                                ("\u751f\u6210\u7ed3\u679c\u6570\u7ec4  cols:")
                                .append
                                (cols).append
                                (" i:").append
                                (i).append
                                (" j:").append(j)));
                    array[i][j] = rs.getString(j + 1);
                    if (array[i][j] == null || array[i][j].equals("")) {
                        array[i][j] = "";
                    }
                }
                i++;
            }
            errmsg = "\u751f\u6210\u7ed3\u679c\u6570\u7ec4";
            m = 80;
            result_return
                    = (String.valueOf
                       (new StringBuffer
                        ("\u751f\u6210\u7ed3\u679c\u6570\u7ec4  cols:").append
                        (cols).append
                        (" i:").append
                        (i).append
                        (" j:").append
                        (j).append
                        ("<br>get_temp_m:").append
                        (m).append
                        ("<br>array:").append(array[0][0])));
            m = 90;
            errmsg = "\u5173\u95ed\u8fde\u63a5";
            if (rs != null) {
                rs.close();
            }
            if (sqlStmt != null) {
                sqlStmt.close();
            }
            if (sqlConn != null) {
                sqlConn.close();
            }
            String string = result_return;
            return string;
        } catch (SQLException e) {
            throw new Exception
                    (String.valueOf
                     (new StringBuffer(e.getMessage()).append
                      (
                    "<br>\u53d6sql\u67e5\u8be2\u7ed3\u679c,Sql\u9519\u8bef----")
                      .append(errmsg)));
        } catch (Exception e) {
            throw new Exception
                    (String.valueOf
                     (new StringBuffer(e.getMessage()).append
                      ("<br>\u53d6sql\u67e5\u8be2\u7ed3\u679c----")
                      .append(errmsg)));
        }
    }

    public void sql_str
            (String sqlstr, Statement sqlStmt, Connection sqlConn) throws
            Exception, SQLException {
        String lowersql = "";
        String newsql = "";
        int newsqlindex = 1;
        String errmsg = "\u53d6sql\u6267\u884c\u7ed3\u679c----";
        try {
            sqlConn.setAutoCommit(false);
            errmsg = "\u6267\u884c";
            sqlstr = sqlstr.concat("        ");
            lowersql = sqlstr.toLowerCase();
            for (newsqlindex = lowersql.indexOf(";"); newsqlindex >= 0;
                               newsqlindex = lowersql.indexOf(";")) {
                if (lowersql.substring(newsqlindex, newsqlindex + 8)
                    .equals(";delete ")
                    || lowersql.substring(newsqlindex, newsqlindex + 8)
                    .equals(";insert ")
                    || lowersql.substring(newsqlindex, newsqlindex + 8)
                    .equals(";update ")) {
                    newsql = sqlstr.substring(0, newsqlindex);
                    lowersql = lowersql.substring(newsqlindex + 1);
                    sqlstr = sqlstr.substring(newsqlindex + 1);
                    if (newsql.substring(0, 7).toLowerCase().equals("delete ")) {
                        sqlStmt.execute(newsql);
                    } else {
                        sqlStmt.executeUpdate(newsql);
                    }
                }
            }
            if (lowersql.substring(0, 7).equals("delete ")) {
                sqlStmt.execute(sqlstr);
            } else if (lowersql.substring(0, 7).equals("insert ")
                       || lowersql.substring(0, 7).equals("update ")) {
                sqlStmt.executeUpdate(sqlstr);
            }
            sqlConn.commit();
        } catch (Exception e) {
            throw new Exception(String.valueOf(new StringBuffer(errmsg).append
                                               ("<br>newsqlindex:").append
                                               (newsqlindex).append
                                               ("<br>newsql:").append
                                               (newsql).append
                                               ("<br>sql:").append
                                               (sqlstr).append
                                               ("<br>\u9519\u8bef\uff1a")
                                               .append(e.getMessage())));
        } finally {
            if (sqlStmt != null) {
                sqlStmt.close();
            }
            if (sqlConn != null) {
                sqlConn.close();
            }
        }
    }

    public static void dosql(String sqlstr) throws Exception, SQLException {
        String errmsg = "\u53d6sql\u6267\u884c\u7ed3\u679c----";
        Connection sqlConn = null;
        Statement sqlStmt = null;
        CallableStatement callStmt = null;
        String lowersql = "";
        String newsql = "";
        int sqlindex = 1;
        int sqlnext = 0;
        try {
            errmsg = "\u5efa\u7acb\u8fde\u63a5";
//            sqlConn = getConnection(MM_itop_DRIVER, MM_itop_STRING,
//                                    MM_itop_USERNAME, MM_itop_PASSWORD);
            sqlConn = null;    // DBConnection.getDBConnection();
            sqlStmt = sqlConn.createStatement();
            errmsg = "\u6267\u884c";
            sqlstr = sqlstr.concat("         ");
            lowersql = sqlstr.toLowerCase();
            while (sqlstr.trim().length() > 0) {
                newsql = sqlstr;
                sqlstr = "";
                sqlindex = lowersql.indexOf(";");
                while (sqlindex > 0) {
                    errmsg = "\u6279\u91cfsql\u8bed\u53e5\u5904\u7406";
                    if (lowersql.substring(sqlindex, sqlindex + 8)
                        .equals(";delete ")
                        || lowersql.substring(sqlindex, sqlindex + 8)
                        .equals(";insert ")
                        || lowersql.substring(sqlindex, sqlindex + 8)
                        .equals(";update ")
                        || lowersql.substring(sqlindex, sqlindex + 6)
                        .equals(";call ")) {
                        sqlstr = newsql.substring(sqlindex + 1);
                        newsql = newsql.substring(0, sqlindex);
                        lowersql = lowersql.substring(sqlindex + 1);
                        sqlindex = 0;
                    } else {
                        sqlindex = lowersql.indexOf(";", sqlindex + 1);
                    }
                }
                errmsg = "\u6267\u884csql\u8bed\u53e5";
                newsql = newsql.concat("       ");
                if (newsql.substring(0, 7).toLowerCase().equals("delete ")) {
                    sqlStmt.execute(newsql);
                } else if (newsql.substring(0, 5).toLowerCase()
                           .equals("call ")) {
                    errmsg = "\u5b9a\u4e49\u8fc7\u7a0b\u3001\u53c2\u6570";
                    callStmt = (sqlConn.prepareCall
                                (String.valueOf(new StringBuffer("{").append
                                                (newsql).append("}"))));
                    callStmt.registerOutParameter(1, 12);
                    errmsg = "\u6267\u884c\u8fc7\u7a0b";
                    callStmt.execute();
                    errmsg = "\u53d6\u4f20\u51fa\u53c2\u6570";
                    errmsg = callStmt.getString(1);
                    if (errmsg != null && !errmsg.equals("")) {
                        throw new Exception
                                ("\u8fc7\u7a0b\u6267\u884c\u9519\u8bef");
                    }
                } else if (newsql.substring(0, 7).toLowerCase()
                           .equals("insert ")
                           || newsql.substring(0, 7).toLowerCase()
                           .equals("update ")) {
//                    log.error("newsql="+newsql);
                    sqlStmt.executeUpdate(newsql);
                }
            }
        } catch (SQLException e) {
            throw new Exception(String.valueOf(new StringBuffer(errmsg).append
                                               ("<br>sqlindex:").append
                                               (sqlindex).append
                                               ("<br>newsql:").append
                                               (newsql).append
                                               ("|<br>sql:").append
                                               (sqlstr).append
                                               ("|<br>\u9519\u8bef\uff1a")
                                               .append(e.getMessage())));
        } finally {
            if (sqlStmt != null) {
                sqlStmt.close();
            }
            if (sqlConn != null) {
                sqlConn.close();
            }
        }
    }
}
