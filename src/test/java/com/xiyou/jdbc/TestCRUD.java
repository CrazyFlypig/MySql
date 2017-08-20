package com.xiyou.jdbc;

import com.sun.deploy.security.ValidationState;
import jdk.nashorn.internal.codegen.CompilerConstants;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;


/**
 * @author cc
 * @create 2017-08-17-17:55
 */

public class TestCRUD {
    private Connection conn = null;
    @Before
    public void iniConn(){
        String url = "jdbc:mysql://localhost:3306/mybase";
        String user = "root";
        String password = "root";
        try {
            //注册驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            //获得连接
            conn = DriverManager.getConnection(url,user,password);
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void select(){
        try {
            Statement st = conn.createStatement();
            String sql = "SELECT id,name,age FROM test";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println("id:" + id + " name:" + name + "　age:" + age );
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void BatchInserts(){
        try {
            String sql = "insert into persons(name,password,age) values(?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            for (int i =0 ; i < 10000 ; i++ ){
                pst.setString(1,"tom" + i );
                pst.setString(2, i + "" );
                pst.setInt(3 , i % 30 );

                //添加批处理命令
                pst.addBatch();

                //处理1000
                if (i % 1000 == 0 ){
                    //执行批处理命令
                    pst.executeBatch();
                    //清除批处理命令
                    pst.clearBatch();
                }
            }
            pst.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 存储过程批量插入
     */
    @Test
    public void biginsert(){
        String sql = "{call sp_biginsert(?)}";
        try {
            CallableStatement cst = conn.prepareCall(sql);
            cst.setInt(1,100000);
            cst.execute();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 存储过程查找
     */
    @Test
    public void sp_count(){
        String sql = "{call sp_count(?)}";
        try {
            CallableStatement cst = conn.prepareCall(sql);
            cst.registerOutParameter(1, Types.INTEGER);
            cst.execute();
            int out = cst.getInt(1);
            System.out.println(out);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     *  自定义函数调用
     */
    @Test
    public void add(){
        String sql = "{ ? = call f_add(?,?)}";
        try {
            CallableStatement cst = conn.prepareCall(sql);
            cst.registerOutParameter(1,Types.INTEGER);
            cst.setInt(2,10);
            cst.setInt(3,20);
            cst.execute();
            int out = cst.getInt(1);
            System.out.println(out);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
