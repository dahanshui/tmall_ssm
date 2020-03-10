package com.chen.tmall.service;

import java.util.List;

import com.chen.tmall.pojo.User;

public interface UserService {
    void add(User c);

    void delete(int id);

    void update(User c);

    User get(int id);

    List list();

    boolean isExist(String name);

    User get(String name, String password);
}