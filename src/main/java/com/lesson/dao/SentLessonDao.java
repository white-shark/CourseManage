package com.lesson.dao;

import com.lesson.entity.Lesson;
import com.lesson.jdbc.JdbcConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SentLessonDao {

    public String sentLesson(String id, String time, String classroom) throws Exception {
        String sql = "select * from feedback where feedback_id='"+ id +"';";
        String lessonId = null;
        String teacherId = null;
        JdbcConnect jdbcUtil = new JdbcConnect();
        Connection conn = jdbcUtil.connect();
        Statement st = conn.createStatement();
        //4执行SQL语句
        ResultSet rs = st.executeQuery(sql);
        Lesson lesson = new Lesson();
        while(rs.next()){
            // 获得一行数据
            lesson.setAdministratorId("101010");
            lesson.setCapacity(rs.getInt("capacity"));
            lesson.setLessonname(rs.getString("lesson_name"));
            lesson.setCredit(rs.getInt("credit"));
            lesson.setHours(rs.getInt("hours"));
            lesson.setOpentime(rs.getString("suggested_opentime"));
            lesson.setClassroom(classroom);
            lesson.setTeachername(rs.getString("teacher_name"));
            teacherId = rs.getString("teacher_id");
        }
        String sql1 = "insert into lessons(lesson_name,administrator_id,credit,hours,opentime,capacity,classroom) values" +
                "('"+ lesson.getLessonname() +"','"+ lesson.getAdministratorId() +"','"+ lesson.getCredit() +"','"+ lesson.getHours() +"','"+ lesson.getOpentime() +"','"+
                lesson.getCapacity() +"','"+ lesson.getClassroom() +"');";
        try {
            st.execute(sql1);
            String sql2 = "select lesson_id from lessons where lesson_name='"+ lesson.getLessonname() + "';";
            rs = st.executeQuery(sql2);
            while(rs.next()){
                lessonId = rs.getString("lesson_id");
                System.out.println("lessonId:" + lessonId);
            }
            String sql3 = "insert into teacher_lessons(teacher_id,lesson_id) value('"+ teacherId +"','"+ lessonId +"');";
            System.out.println(sql3);
            boolean res = st.execute(sql3);
            System.out.println(res);
            String sql4 = "delete from feedback_list where feedback_id='"+ id +"';";
            boolean ress = st.execute(sql4);
            System.out.println(ress);
            //6释放资源
            rs.close();
            st.close();
            conn.close();
            return "发布成功";
        }catch (Exception e){
            return "error";
        }

    }
}
