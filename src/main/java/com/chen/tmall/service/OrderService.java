package com.chen.tmall.service;

import java.util.List;

import com.chen.tmall.pojo.Order;
import com.chen.tmall.pojo.OrderItem;

public interface OrderService {

    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";

    void add(Order c);

    void delete(int id);

    void update(Order c);

    Order get(int id);

    List list();

    List<Order> list(int uid, String noIncludeStatus);

    float add(Order o, List<OrderItem> ois);

    void setUser(Order o);

    void setUser(List<Order> os);
}