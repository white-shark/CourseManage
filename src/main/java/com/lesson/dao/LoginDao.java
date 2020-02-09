package com.lesson.dao;

import com.lesson.entity.Login;
import com.lesson.jdbc.JdbcConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Wei Peng
 */
public class LoginDao {

    public Login loginInfo(String username) throws Exception {

        String sql = "select * from login where username="+username+";";
        Login login = new Login();
        JdbcConnect jdbcUtil = new JdbcConnect();
        Connection conn = jdbcUtil.connect();
        Statement st = conn.createStatement();
        //4执行SQL语句
        ResultSet rs = st.executeQuery(sql);
        //5处理结果集
        while(rs.next()){
            // 获得一行数据
            login.setUsername(username);
            login.setPassword(rs.getString("password"));
            login.setPower(rs.getString("power"));
        }
        //6释放资源
        rs.close();
        st.close();
        conn.close();
        return login;
    }
}
