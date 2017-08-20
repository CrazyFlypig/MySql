package com.xiyou.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


/**
 * jdbc
 *
 * @author cc
 * @create 2017-08-17-17:19
 */

public class JDBCDemo {
    public static void main(String[] args) {
        /**
         * jdbc:mysql 协议
         */
        String url = "jdbc:mysql://localhost:3306/mybase";
        String user = "root";
        String passwd = "root";
        try {
            //注册驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            //获得连接
            Connection conn = DriverManager.getConnection(url,user,passwd);
            //创建语句对象（解耦和）
            Statement st = conn.createStatement();
            //编写执行的sql语句
            String sql = "insert into test(id,name,age) values(20,'cc',29)";
            //执行sql语句
            st.execute(sql);
            //释放资源
            st.close();
            conn.close();
            System.out.println(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
