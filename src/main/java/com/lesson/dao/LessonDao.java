package com.lesson.dao;

import com.lesson.entity.CreatLesson;
import com.lesson.entity.Lesson;
import com.lesson.jdbc.JdbcConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Wei Peng
 */
public class LessonDao {
    public ArrayList<Lesson> getLesson() throws Exception {
        ArrayList<Lesson> lessons = new ArrayList<>();
        String sql = "select * from lesson ;";

        JdbcConnect jdbcUtil = new JdbcConnect();
        Connection conn = jdbcUtil.connect();
        Statement st = conn.createStatement();
        //4执行SQL语句
        ResultSet rs = st.executeQuery(sql);
        //5处理结果集
        while(rs.next()){
            // 获得一行数据
            Lesson lesson = new Lesson();
            lesson.setLessonid(rs.getString("lesson_id"));
            lesson.setLessonname(rs.getString("lesson_name"));
            lesson.setCredit(rs.getInt("credit"));
            lesson.setHours(rs.getInt("hours"));
            lesson.setOpentime(rs.getString("opentime"));
            lesson.setClassroom(rs.getString("classroom"));
            lesson.setTeachername(rs.getString("teacher_name"));
            lessons.add(lesson);
        }
        //6释放资源
        rs.close();
        st.close();
        conn.close();
        return lessons;
    }
    public ArrayList<Lesson> getStuLesson(String studentId) throws Exception {
        ArrayList<Lesson> lessons = new ArrayList<>();
        String sql = "select * from lesson_stu where student_id='"+ studentId +"';";

        JdbcConnect jdbcUtil = new JdbcConnect();
        Connection conn = jdbcUtil.connect();
        Statement st = conn.createStatement();
        //4执行SQL语句
        ResultSet rs = st.executeQuery(sql);
        //5处理结果集
        while(rs.next()){
            // 获得一行数据
            Lesson lesson = new Lesson();
            lesson.setLessonid(rs.getString("lesson_id"));
            lesson.setLessonname(rs.getString("lesson_name"));
            lesson.setCredit(rs.getInt("credit"));
            lesson.setHours(rs.getInt("hours"));
            lesson.setOpentime(rs.getString("opentime"));
            lesson.setClassroom(rs.getString("classroom"));
            lesson.setTeachername(rs.getString("teachername"));
            lessons.add(lesson);
        }
        //6释放资源
        rs.close();
        st.close();
        conn.close();
        return lessons;
    }
    public void exitLesson(String lessonid,String studentid) throws Exception {
        String sql = "delete from lessons_student where student_id='"+ studentid +"'and lesson_id='"+ lessonid +"';";

        JdbcConnect jdbcUtil = new JdbcConnect();
        Connection conn = jdbcUtil.connect();
        Statement st = conn.createStatement();
        //4执行SQL语句
        boolean rs = st.execute(sql);
    }
    public void selectLesson(String lessonid,String studentid) throws Exception {
        String sql = "insert into lessons_student(student_id,lesson_id)values ('"+ studentid +"','"+ lessonid +"');";

        JdbcConnect jdbcUtil = new JdbcConnect();
        Connection conn = jdbcUtil.connect();
        Statement st = conn.createStatement();
        //4执行SQL语句
        boolean rs = st.execute(sql);
    }

    public ArrayList<Lesson> getTeaLesson(String teacherId) throws Exception {
        ArrayList<Lesson> lessons = new ArrayList<>();
        String sql = "select * from tea_lesson where teacher_id='"+ teacherId +"';";

        JdbcConnect jdbcUtil = new JdbcConnect();
        Connection conn = jdbcUtil.connect();
        Statement st = conn.createStatement();
        //4执行SQL语句
        ResultSet rs = st.executeQuery(sql);
        //5处理结果集
        while(rs.next()){
            // 获得一行数据
            Lesson lesson = new Lesson();
            lesson.setLessonid(rs.getString("lesson_id"));
            lesson.setLessonname(rs.getString("lesson_name"));
            lesson.setCredit(rs.getInt("credit"));
            lesson.setHours(rs.getInt("hours"));
            lessons.add(lesson);
        }
        //6释放资源
        rs.close();
        st.close();
        conn.close();
        return lessons;
    }

    public String creatLesson(CreatLesson lesson,String teacherId) throws Exception {
        String sql = "insert into lessons(lesson_name,administrator_id,credit,hours,capacity) values" +
                "('"+ lesson.getName() +"','"+ lesson.getAdministratorid() +"','"+ lesson.getCredit() +"','"+ lesson.getHours() +"','"+ lesson.getCapacity() +"');";

        String sql1= "insert into feedback_list(lesson_name,credit,hours,suggested_opentime,capacity,teacher_id,administrator_id) " +
                "values('"+ lesson.getName() +"','"+ lesson.getCredit() +"','"+ lesson.getHours() +"','"+ lesson.getTime() +"','"+
                lesson.getCapacity() +"','"+ teacherId +"','"+ lesson.getAdministratorid() +"')";

        String sql2 = "insert into teacher_lessons(teacher_id,lesson_id) values('"+ teacherId +"','"+ lesson.getId() +"')";

        JdbcConnect jdbcUtil = new JdbcConnect();
        Connection conn = jdbcUtil.connect();
        Statement st = conn.createStatement();
        //4执行SQL语句
        System.out.println(sql1);
        boolean rs1 = st.execute(sql1);
        boolean rs2 = st.execute(sql2);
        if (rs2==true){
            return "创建成功";
        }else {
            return "创建";
        }
    }

    public void deleteLesson(String lessonid,String teacherId) throws Exception {
        String sql = "delete from teacher_lessons where teacher_id='"+ teacherId +"'and lesson_id='"+ lessonid +"';";
        String sql2="delete from lessons where lesson_id='"+ lessonid +"';";
        JdbcConnect jdbcUtil = new JdbcConnect();
        Connection conn = jdbcUtil.connect();
        Statement st = conn.createStatement();
        //4执行SQL语句
        boolean rs = st.execute(sql);
        rs = st.execute(sql2);
    }

}
