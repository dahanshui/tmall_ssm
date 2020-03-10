package com.chen.tmall.controller;

import com.chen.tmall.pojo.Administrator;
import com.chen.tmall.service.AdminstratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class AdminLoginController {

    @Autowired
    AdminstratorService adminstratorService;

    @RequestMapping("loginAdminPage")
    public String loginPage() {
        return "admin/loginAdminPage";
    }

    @RequestMapping("doAdminLogin")
    public String doadminLogin(String aname, String apassword, HttpSession session, Model model) {
        aname = HtmlUtils.htmlEscape(aname);
        Administrator administrator = adminstratorService.listByNameAndPassword(aname, apassword);
        if (null == administrator) {
            String msg = "账号或密码错误";
            model.addAttribute("msg", msg);
            return "admin/loginAdminPage";
        }
        session.setAttribute("Administrator", administrator);
        return "redirect:/admin";
    }

    @RequestMapping("goAdminRegister")
    public String goAdminRegister() {
        return "admin/registerAdminPage";
    }

    @RequestMapping("doAdminRegister")
    public String doAdminRegister(String aname, String apassword, String code, Model model) {
        aname = HtmlUtils.htmlEscape(aname);
        if (!code.equals("大汗水")) {
            String msg = "注册验证码错误，请联系管理员获取-------(请输入：大汗水)";
            model.addAttribute("msg", msg);
            return "admin/registerAdminPage";
        }
        boolean temp = adminstratorService.isExistName(aname);
        if (temp) {
            String msg = "该账号已存在";
            model.addAttribute("msg", msg);
            return "admin/registerAdminPage";
        }
        Administrator administrator = new Administrator();
        administrator.setAname(aname);
        administrator.setApassword(apassword);
        adminstratorService.add(administrator);
        return "admin/registerAdminSuccess";
    }

    @RequestMapping("admin_user_drop")
    public String userDrop(HttpSession session) {
        session.removeAttribute("Administrator");
        return "redirect:loginAdminPage";
    }


}
