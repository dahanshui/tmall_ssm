package com.chen.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.chen.tmall.pojo.Administrator;
import com.chen.tmall.service.AdminstratorService;
import com.chen.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class AdministratorController {

    @Autowired
    AdminstratorService adminstratorService;

    @RequestMapping("admin_administrator_list")
    public String administratorList(Model model, Page page) {
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Administrator> ats = adminstratorService.list();
        int total = (int) new PageInfo<>(ats).getTotal();
        page.setTotal(total);
        model.addAttribute("ats", ats);
        model.addAttribute("page", page);
        return "admin/listAdministrator";

    }


}

