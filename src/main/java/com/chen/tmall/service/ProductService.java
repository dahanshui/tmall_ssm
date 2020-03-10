package com.chen.tmall.service;

import java.util.List;

import com.chen.tmall.pojo.Category;
import com.chen.tmall.pojo.Product;

public interface ProductService {
    void add(Product p);

    void delete(int id);

    void update(Product p);

    Product get(int id);

    List list(int cid);

    void setFirstProductImage(Product p);

    void setFirstProductImage(List<Product> ps);

    void fill(List<Category> cs);

    void fill(Category c);

    void fillByRow(List<Category> cs);

    void setSaleAndReviewNumber(Product p);

    void setSaleAndReviewNumber(List<Product> ps);

    List<Product> search(String keyword);

    void deleteStock(List<Product> ps, int orderItemNumber);

    void deleteStock(Product p, int orderItemNumber);

    void orderByDate(Category c);

    void orderByImage(Category c);

    void orderByPrice(Category c);

    void orderByAll(Category c);

    void orderBySaleCount(Category c);

    void orderByReview(Category c);
}