package com.xiaoxin.community.controller;

import com.xiaoxin.community.util.CommunityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AlphaController {

    @RequestMapping(path = "/alpha/cookie/set", method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpServletResponse response){
        Cookie cookie = new Cookie("code", CommunityUtil.generateUUID());
        cookie.setMaxAge(10*60);
        cookie.setPath("/community/alpha");
        response.addCookie(cookie);
        return "set Cookie";
    }

    @RequestMapping(path = "/alpha/cookie/get",method = RequestMethod.GET)
    @ResponseBody
    public String getCookie( @RequestHeader("cookie") String cookie){
        System.out.println(cookie);
        return "get cookie";
    }

    @GetMapping("/alpha/session/set")
    @ResponseBody
    public String setSession(HttpSession session) {
        session.setAttribute("id",1);
        session.setAttribute("name","Tom");
        return "set session";
    }

    @GetMapping("/alpha/session/get")
    @ResponseBody
    public String getSession(HttpSession session){
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "get session";
    }

    //ajax示例
    @RequestMapping(path = "/alpha/ajax",method = RequestMethod.POST)
    @ResponseBody
    public String testAjax(String name,int age){
        System.out.println(name);
        System.out.println(age);
        return CommunityUtil.getJSONString(0,"操作成功！");
    }
}
