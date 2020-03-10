package com.chen.tmall.service;

import java.util.List;

import com.chen.tmall.pojo.Order;
import com.chen.tmall.pojo.OrderItem;

public interface OrderItemService {


    void add(OrderItem c);

    void delete(int id);

    void update(OrderItem c);

    OrderItem get(int id);

    List list();

    List<OrderItem> list(int oid);

    void fill(List<Order> os);

    void fill(Order o);

    int getSaleCount(int pid);

    List<OrderItem> listByUser(int uid);

    void deleteProductStock(List<OrderItem> ois);

    void deleteProductStock(OrderItem oi);
}