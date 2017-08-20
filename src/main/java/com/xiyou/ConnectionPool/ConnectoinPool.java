package com.xiyou.ConnectionPool;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

/**
 * 连接池
 *
 * @author cc
 * @create 2017-08-19-21:05
 */

public class ConnectoinPool {
    /**
     * 连接池对象
     */
    private List<Connection> pool = new LinkedList<Connection>();
    //从池中获取一个连接
    public synchronized Connection getConnection(){
        Connection conn = null;
        try {
            while (pool.isEmpty()){
                this.wait();
            }
            conn = pool.remove(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            return conn;
        }
    }
    //放置连接
    public synchronized void addConnection(Connection conn){
        pool.add(conn);
        this.notifyAll();
    }
    public int getSize (){
        return pool.size();
    }

}
