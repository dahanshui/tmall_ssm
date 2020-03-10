package com.chen.tmall.service;

import com.chen.tmall.pojo.Administrator;

import java.util.List;

public interface AdminstratorService {

    void add(Administrator administrator);

    void delete(int id);

    void update(Administrator administrator);

    Administrator get(int id);

    List<Administrator> list();

    Administrator listByNameAndPassword(String aname, String apassword);

    boolean isExistName(String aname);

}
