package com.lesson.dao;

import com.lesson.entity.Login;
import com.lesson.jdbc.JdbcConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Wei Peng
 */
public class StudentInfoDao {

    public ArrayList<String> getInfo(String studentId) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        String sql = "select * from stu_info where student_id="+studentId+";";
        Login login = new Login();
        JdbcConnect jdbcUtil = new JdbcConnect();
        Connection conn = jdbcUtil.connect();
        Statement st = conn.createStatement();
        //4执行SQL语句
        ResultSet rs = st.executeQuery(sql);
        //5处理结果集
        while(rs.next()){
            // 获得一行数据
            list.add(rs.getString("student_name"));
            list.add(rs.getString("password"));
        }
        //6释放资源
        rs.close();
        st.close();
        conn.close();
        return list;
    }

    public void changeStuInfo(String studentId,String name,String password) throws Exception {
        String sql = "update student_information set student_name='"+ name +"' where student_id='"+ studentId +"' ;";
        String sql2 = "update login set password='"+ password +"' where username='"+ studentId +"' ;";
        JdbcConnect jdbcUtil = new JdbcConnect();
        Connection conn = jdbcUtil.connect();
        Statement st = conn.createStatement();
        //4执行SQL语句
        boolean rs = st.execute(sql);
        boolean rs2=st.execute(sql2);
    }

}
