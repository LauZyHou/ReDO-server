package com.ecnu.redo.controller;

import com.ecnu.redo.dao.User;

import com.ecnu.redo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.IconUIResource;
import java.util.*;

//import java.io.File;

@Controller
//@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "",methods = {})
public class LoginController {

@Autowired
private UserService userService;

    @PostMapping("/session")
    @ResponseBody
    public Map<String,String> springSession(HttpServletRequest request, HttpSession session) {
        System.out.println("/session执行了");
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().contains("JSESSION")) {
                    System.out.println(cookie.getName() + "=" + cookie.getValue());
                }
            }
        }
        Object value = session.getAttribute("username");
        Map<String,String> m=new HashMap<>();
        if (value == null) {
            System.out.println("User doesn't exist");
            m.put("username","not-exist");
        } else {
            System.out.println("User exists");
            m.put("username",value.toString());
        }

        return m;
    }


    @PostMapping("/login")
    @ResponseBody
    public Map<String,String> login(@RequestParam(value="username") String username,@RequestParam(value="password") String password,HttpSession session) {
        System.out.println("/login执行了");
        // validate, assume is true (front-end check)
        Map<String,String> m=new HashMap<>();

        List<String> passwords= userService.selectByUsername(username);
        // can find and password correct
        if(passwords.size()>0 && passwords.get(0).equals(password) ){
//        if(username.equals("123")){
            session.setAttribute("username", "" + username); // return a session, next time to get this attribute is not null (with data)
            m.put("success","true");
        }
        else{
            m.put("success","false");
        }
        return m;
    }

    @PostMapping("/sessionOut")
    @ResponseBody
    public Map<String,String> sessionOut(HttpServletRequest request, HttpSession session) {
        System.out.println("/sessionOut执行了");
        Object value = session.getAttribute("username");
        session.removeAttribute("username");
        Map<String,String> m=new HashMap<>();
        m.put("success","true");
        return m;
    }

    @PostMapping("/register")
    @ResponseBody
    public Map<String,String> register(@RequestParam(value="username") String username,
                                       @RequestParam(value="password") String password,
                                       @RequestParam(value="email") String email,
                                       @RequestParam(value="company") String company) {
        System.out.println("/register执行了");
        // validate, assume is true
        System.out.println(username+password+email+company);
        userService.insert(new User(username,password,email,company));
        return null;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> test() {
        System.out.println("test执行了");
        Map<String, String> ans = new HashMap<>();
        ans.put("ok", "woqu");
        return ans;
    }
}
