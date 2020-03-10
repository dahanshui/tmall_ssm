package com.chen.tmall.mapper;

import com.chen.tmall.pojo.Administrator;
import com.chen.tmall.pojo.AdministratorExample;

import java.util.List;

public interface AdministratorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Administrator record);

    int insertSelective(Administrator record);

    List<Administrator> selectByExample(AdministratorExample example);

    Administrator selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Administrator record);

    int updateByPrimaryKey(Administrator record);
}