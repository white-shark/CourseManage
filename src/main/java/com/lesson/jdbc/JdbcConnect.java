package com.lesson.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Wei Peng
 */

public class JdbcConnect {

    public Connection connect()throws Exception{
        // 查询所有的分类信息
        // 注意：使用JDBC规范，采用都是java.sql包下的内容
        //1 注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2 获得连接
        String url = "jdbc:mysql://localhost:3306/coursemanage?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false";
        Connection conn = DriverManager.getConnection(url,"root", "60978516");
        //3获得语句执行者

        return conn;
    }

    public static void test(String[] args) throws Exception {
        JdbcConnect jdbcUtil = new JdbcConnect();
        Connection conn = jdbcUtil.connect();

        Statement st = conn.createStatement();
        //4执行SQL语句
        ResultSet rs = st.executeQuery("select * from student_information;");
        //5处理结果集
        while(rs.next()){
            // 获得一行数据
            Integer cid = rs.getInt("student_id");
            String cname = rs.getString("student_name");
            System.out.println(cid + " , " + cname);
        }
        //6释放资源
        rs.close();
        st.close();
        conn.close();
    }
}
