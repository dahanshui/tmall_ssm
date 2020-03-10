package com.chen.tmall.service.impl;

import java.util.List;

import com.chen.tmall.pojo.OrderItem;
import com.chen.tmall.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chen.tmall.mapper.OrderMapper;
import com.chen.tmall.pojo.Order;
import com.chen.tmall.pojo.OrderExample;
import com.chen.tmall.pojo.User;
import com.chen.tmall.service.OrderService;
import com.chen.tmall.service.UserService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserService userService;
    @Autowired
    OrderItemService orderItemService;

    @Override
    public void add(Order c) {
        orderMapper.insert(c);
    }

    @Override
    public void delete(int id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Order c) {
        orderMapper.updateByPrimaryKeySelective(c);
    }

    @Override
    public Order get(int id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        setUser(order);
        return order;
    }

    public List<Order> list() {
        OrderExample example = new OrderExample();
        example.setOrderByClause("id desc");
        List<Order> result = orderMapper.selectByExample(example);
        setUser(result);
        return result;

    }

    @Override
    public List<Order> list(int uid, String noIncludeStatus) {
        OrderExample example = new OrderExample();
        example.createCriteria().andUidEqualTo(uid).andStatusNotEqualTo(noIncludeStatus);
        example.setOrderByClause("id desc");
        List<Order> result = orderMapper.selectByExample(example);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public float add(Order o, List<OrderItem> ois) {
        float total = 0;
        add(o);
        for (OrderItem oi : ois) {
            oi.setOid(o.getId());
            orderItemService.update(oi);
            total += oi.getProduct().getPromotePrice() * oi.getNumber();
        }
        return total;
    }

    @Override
    public void setUser(Order o) {
        User user = userService.get(o.getUid());
        o.setUser(user);
    }

    @Override
    public void setUser(List<Order> os) {
        for (Order o : os) {
            setUser(o);
        }
    }


}