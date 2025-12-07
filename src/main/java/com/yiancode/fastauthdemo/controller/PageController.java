package com.yiancode.fastauthdemo.controller;

import com.yiancode.fastauth.model.AuthUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * 页面控制器
 */
@Controller
public class PageController {

    /**
     * 首页
     */
    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        AuthUser user = (AuthUser) session.getAttribute("user");
        if (user != null) {
            return "redirect:/user";
        }
        return "index";
    }

    /**
     * 用户信息页
     */
    @GetMapping("/user")
    public String user(HttpSession session, Model model) {
        AuthUser user = (AuthUser) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);
        return "user";
    }
}
