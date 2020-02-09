package com.lesson.controller;

import com.lesson.dao.FeedbackDao;
import com.lesson.dao.LessonDao;
import com.lesson.dao.SentLessonDao;
import com.lesson.entity.CreatLesson;
import com.lesson.entity.Feedback;
import com.lesson.entity.Lesson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * @author Wei Peng
 */
@RestController
public class LessonController {

    @PostMapping(value = "/getLesson")
    public ArrayList<Lesson> getLesson(HttpSession session) throws Exception {
        String power = (String) session.getAttribute("power");
        if ("1".equals(power)){
            ArrayList<Lesson> lessons;
            LessonDao lessonDao = new LessonDao();
            lessons = lessonDao.getLesson();
            return lessons;
        }
        else {
            return null;
        }

    }

    @PostMapping(value = "/getStuLesson")
    public ArrayList<Lesson> getStuLesson(HttpSession session) throws Exception {
        String power = (String) session.getAttribute("power");
        String studentId = (String) session.getAttribute("username");
        if ("1".equals(power)){
            ArrayList<Lesson> lessons;
            LessonDao lessonDao = new LessonDao();
            lessons = lessonDao.getStuLesson(studentId);
            return lessons;
        }
        else {
            return null;
        }
    }

    @PostMapping(value = "/exitLesson")
    public String exitLesson(HttpSession session, @RequestParam String lessonid) throws Exception {
        System.out.println(lessonid);
        String power = (String) session.getAttribute("power");
        String studentId = (String) session.getAttribute("username");
        System.out.println(studentId);
        if ("1".equals(power)){

            try {
                LessonDao lessonDao = new LessonDao();
                lessonDao.exitLesson(lessonid,studentId);
                return "退选成功";
            } catch (Exception e) {
                return "退选失败";
            }
        }
        else {
            return "权限不足";
        }
    }

    @PostMapping(value = "/selectLesson")
    public String selectLesson(HttpSession session, @RequestParam String lessonid) {
        System.out.println(lessonid);
        String power = (String) session.getAttribute("power");
        String studentId = (String) session.getAttribute("username");
        System.out.println(studentId);
        if ("1".equals(power)){

            try {
                LessonDao lessonDao = new LessonDao();
                lessonDao.selectLesson(lessonid,studentId);
                return "选课成功";
            } catch (Exception e) {
                return "选课失败";
            }
        }
        else {
            return "权限不足";
        }
    }

    @PostMapping(value = "/getTeaLesson")
    public ArrayList<Lesson> getTeaLesson(HttpSession session) throws Exception {
        String power = (String) session.getAttribute("power");
        String teacherId = (String) session.getAttribute("username");
        if ("2".equals(power)){
            ArrayList<Lesson> lessons;
            LessonDao lessonDao = new LessonDao();
            lessons = lessonDao.getTeaLesson(teacherId);
            return lessons;
        }
        else {
            return null;
        }
    }

    @PostMapping(value = "/creatLesson")
    public String creatLesson(HttpSession session,@RequestParam String name,@RequestParam String credit,
                              @RequestParam String hours,@RequestParam String capacity,@RequestParam String time) throws Exception {
        String teacherId = String.valueOf(session.getAttribute("username"));
        CreatLesson lesson = new CreatLesson();
        lesson.setName(name);
        lesson.setHours(Integer.valueOf(hours));
        lesson.setCredit(Integer.valueOf(credit));
        lesson.setCapacity(Integer.valueOf(capacity));
        lesson.setTime(time);
        LessonDao lessonDao = new LessonDao();
        String res = lessonDao.creatLesson(lesson,teacherId);
        return res;

    }

    @PostMapping(value = "/delete/lesson")
    public String deleteLesson(HttpSession session, String lessonid) throws Exception {
        String teacherId = String.valueOf(session.getAttribute("username"));
        LessonDao lessonDao = new LessonDao();
        lessonDao.deleteLesson(lessonid,teacherId);
        return "删除成功";

    }
    @PostMapping(value = "/getFddeback")
    public ArrayList<Feedback> getFeedback(HttpSession session) throws Exception {
        String power = String.valueOf(session.getAttribute("power"));
        if ("3".equals(power)){
            System.out.println(power);
            FeedbackDao feedbackDao = new FeedbackDao();
            ArrayList<Feedback> feedbacks =feedbackDao.getFeedback();
            return feedbacks;
        }
        else {
            return null;
        }

    }

    @PostMapping(value = "/sentLesson")
    public String sentLesson(HttpSession session, @RequestParam String id, @RequestParam String time, @RequestParam String classroom) throws Exception {
        SentLessonDao sentLessonDao = new SentLessonDao();
        String res = sentLessonDao.sentLesson(id,time,classroom);
        return res;

    }

}
