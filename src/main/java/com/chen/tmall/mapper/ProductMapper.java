package com.chen.tmall.mapper;

import com.chen.tmall.pojo.Product;
import com.chen.tmall.pojo.ProductExample;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    List<Product> listByProductImage(int cid);

    List<Product> listByProductSaleCount(int cid);

    List<Product> listByProductReview(int cid);

    List<Product> listByProductAll(int cid);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}