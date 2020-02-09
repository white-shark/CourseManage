package com.lesson.dao;

import com.lesson.entity.Feedback;
import com.lesson.jdbc.JdbcConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Wei Peng
 */
public class FeedbackDao {

    public ArrayList<Feedback> getFeedback() throws Exception {
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        String sql = "select * from feedback";
        JdbcConnect jdbcUtil = new JdbcConnect();
        Connection conn = jdbcUtil.connect();
        Statement st = conn.createStatement();
        //4执行SQL语句
        ResultSet rs = st.executeQuery(sql);
        //5处理结果集
        while(rs.next()){
            // 获得一行数据
            Feedback feedback = new Feedback();
            feedback.setFeedbackId(rs.getString("feedback_id"));
            feedback.setLessonName(rs.getString("lesson_name"));
            feedback.setTime(rs.getString("suggested_opentime"));
            feedback.setTeacherName(rs.getString("teacher_name"));
            feedback.setHours(rs.getInt("hours"));
            feedback.setCredit(rs.getInt("credit"));
            feedback.setCapacity(rs.getInt("capacity"));
            feedbacks.add(feedback);

        }
        //6释放资源
        rs.close();
        st.close();
        conn.close();
        return feedbacks;

    }
}
