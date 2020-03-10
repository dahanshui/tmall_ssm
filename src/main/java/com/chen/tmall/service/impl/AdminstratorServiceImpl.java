package com.chen.tmall.service.impl;

import com.chen.tmall.mapper.AdministratorMapper;
import com.chen.tmall.pojo.Administrator;
import com.chen.tmall.pojo.AdministratorExample;
import com.chen.tmall.service.AdminstratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminstratorServiceImpl implements AdminstratorService {

    @Autowired
    AdministratorMapper administratorMapper;

    @Override
    public void add(Administrator administrator) {
        administratorMapper.insert(administrator);
    }

    @Override
    public void delete(int id) {
        administratorMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Administrator administrator) {
        administratorMapper.updateByPrimaryKey(administrator);
    }

    @Override
    public Administrator get(int id) {
        return administratorMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Administrator> list() {
        AdministratorExample example = new AdministratorExample();
        example.setOrderByClause("id desc");
        return administratorMapper.selectByExample(example);
    }

    @Override
    public Administrator listByNameAndPassword(String aname, String apassword) {
        AdministratorExample example = new AdministratorExample();
        example.createCriteria().andAnameEqualTo(aname).andApasswordEqualTo(apassword);
        List<Administrator> as = administratorMapper.selectByExample(example);
        if (as.isEmpty()) {
            return null;
        }
        return as.get(0);
    }

    @Override
    public boolean isExistName(String aname) {
        AdministratorExample example = new AdministratorExample();
        example.createCriteria().andAnameEqualTo(aname);
        List<Administrator> result = administratorMapper.selectByExample(example);
        if (result.isEmpty()) {
            return false;
        }
        return true;
    }
}
