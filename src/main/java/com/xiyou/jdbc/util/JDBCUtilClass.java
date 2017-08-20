package com.xiyou.jdbc.util;

import java.sql.*;

/**
 * jdbc工具类
 *
 * @author cc
 * @create 2017-08-17-21:15
 */

public class JDBCUtilClass {
    static {
        //注册驱动程序
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection openConnection(){
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/mybase";
        String user = "root";
        String passwd = "root";
        //获得连接
        try {
            conn = DriverManager.getConnection(url,user,passwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    //关闭连接
    public static void closeConnection(Connection conn) {
        if (conn != null ){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //关闭语句
    public static void closeStatement(Statement st){
        if (st != null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //关闭结果
    public static void closeResultSet(ResultSet rs){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
