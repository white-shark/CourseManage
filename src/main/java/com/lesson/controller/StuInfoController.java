package com.lesson.controller;

import com.lesson.dao.StudentInfoDao;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * @author Wei Peng
 */
@RestController
public class StuInfoController {
    @PostMapping(value = "/getStuInfo")
    public ArrayList<String> getStuInfo(HttpSession session) throws Exception {
        String studentId = (String) session.getAttribute("username");
        StudentInfoDao studentInfoDao = new StudentInfoDao();
        ArrayList<String> list = studentInfoDao.getInfo(studentId);
        return list;
    }

    @PostMapping(value = "/changeStuInfo")
    public String changeStuInfo(HttpSession session, @RequestParam String name,@RequestParam String password) throws Exception {
        String studentId = (String) session.getAttribute("username");
        StudentInfoDao studentInfoDao = new StudentInfoDao();
        studentInfoDao.changeStuInfo(studentId,name,password);
        return "修改成功";
    }
}
