package com.xiyou.ConnectionPool;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * @author cc
 * @create 2017-08-20-15:01
 *
 * 实现数据源,自定义数据源
 */

public class MyDataSource implements DataSource {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static final int MAX = 10;
    //引进池
    private ConnectoinPool pool = new ConnectoinPool();
    public MyDataSource(){
        initPool();
    }
    String url = "jdbc:mysql://localhost:3306/mybase";
    String user = "root";
    private String password = "root";
    private void initPool(){
        try {
            for (int i = 0 ; i < MAX ; i++){
                Connection conn = DriverManager.getConnection(url,user,password);
                pool.addConnection(new MyConnectionWrapper(conn,pool));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int Size (){
        return pool.getSize();
    }
    //重点实现的方法
    public Connection getConnection() throws SQLException {
        return pool.getConnection();
    }

    public Connection getConnection(String username, String password) throws SQLException {
        return pool.getConnection();
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    public void setLoginTimeout(int seconds) throws SQLException {

    }

    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    public Logger getParentLogger(){
        return null;
    }
}
