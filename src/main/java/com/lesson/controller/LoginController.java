package com.lesson.controller;

import com.lesson.dao.LoginDao;
import com.lesson.entity.Login;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Wei Peng
 */
@RestController
public class LoginController {
    @PostMapping(value = "/user/login")
    public String userlogin(@RequestParam String username, @RequestParam String password,
                            HttpSession session) throws Exception {

        LoginDao loginDao = new LoginDao();
        Login login = loginDao.loginInfo(username);
        System.out.println(username);
        System.out.println(password);
        if (login.getUsername() == null){
            return "error";
        }
        if (password.equals(login.getPassword())){
            session.setAttribute("username",username);
            session.setAttribute("power",login.getPower());
            if ("1".equals(login.getPower())){
                return "index-stu";
            }
            else if("2".equals(login.getPower())){
                return "index-tea";
            }
            else if("3".equals(login.getPower())){
                return "index-admin";
            }
            return "error";
        }
        else {
            return "error";
        }
    }
}
