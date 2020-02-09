package com.lesson.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectJDBC {

    public static void main(String[] args) throws SQLException{
        String driver = "com.mysql.jdbc.Driver";
        String URL = "jdbc:mysql://122.51.231.110:3306/courseManage?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false";
        Connection con = null;
        try
        {
            Class.forName(driver);
        }
        catch(java.lang.ClassNotFoundException e)
        {
            System.out.println("无法加载驱动.");
        }
        try
        {
            con=DriverManager.getConnection(URL,"root","60978516");
            //这里输入你自己安装MySQL时候设置的用户名和密码，用户名默认为root
            System.out.println("连接成功.");
        }
        catch(Exception e)
        {
            System.out.println("连接失败:" + e.getMessage());
        }
    }

}
