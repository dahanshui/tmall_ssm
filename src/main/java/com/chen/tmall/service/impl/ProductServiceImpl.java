package com.chen.tmall.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.chen.tmall.pojo.Category;
import com.chen.tmall.pojo.Product;
import com.chen.tmall.pojo.ProductExample;
import com.chen.tmall.pojo.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chen.tmall.mapper.ProductMapper;
import com.chen.tmall.service.CategoryService;
import com.chen.tmall.service.OrderItemService;
import com.chen.tmall.service.ProductImageService;
import com.chen.tmall.service.ProductService;
import com.chen.tmall.service.ReviewService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ReviewService reviewService;

    @Override
    public void add(Product p) {
        productMapper.insert(p);
    }

    @Override
    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product p) {
        productMapper.updateByPrimaryKeySelective(p);
    }

    @Override
    public Product get(int id) {
        Product p = productMapper.selectByPrimaryKey(id);
        setFirstProductImage(p);
        setCategory(p);
        return p;
    }

    @Override
    public void setFirstProductImage(List<Product> ps) {
        for (Product p : ps) {
            setFirstProductImage(p);
        }
    }

    public void setCategory(List<Product> ps) {
        for (Product p : ps)
            setCategory(p);
    }

    public void setCategory(Product p) {
        int cid = p.getCid();
        Category c = categoryService.get(cid);
        p.setCategory(c);
    }

    @Override
    public List list(int cid) {
        ProductExample example = new ProductExample();
        example.createCriteria().andCidEqualTo(cid);
        example.setOrderByClause("id desc");
        List result = productMapper.selectByExample(example);
        setFirstProductImage(result);
        setCategory(result);
        return result;
    }

    @Override
    public void setFirstProductImage(Product p) {
        List<ProductImage> pis = productImageService.list(p.getId(), ProductImageService.type_single);
        if (!pis.isEmpty()) {
            ProductImage pi = pis.get(0);
            p.setFirstProductImage(pi);
        }
    }

    @Override
    public void fill(List<Category> cs) {
        for (Category c : cs) {
            fill(c);
        }
    }

    @Override
    public void fillByRow(List<Category> cs) {
        int productNumberEachRow = 8;
        for (Category c : cs) {
            List<Product> products = c.getProducts();
            List<List<Product>> productsByRow = new ArrayList<>();
            for (int i = 0; i < products.size(); i += productNumberEachRow) {
                int size = i + productNumberEachRow;
                size = size > products.size() ? products.size() : size;
                List<Product> productsOfEachRow = products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            c.setProductsByRow(productsByRow);
        }
    }

    @Override
    public void setSaleAndReviewNumber(Product p) {
        int saleCount = orderItemService.getSaleCount(p.getId());
        p.setSaleCount(saleCount);

        int reviewCount = reviewService.getCount(p.getId());
        p.setReviewCount(reviewCount);
    }

    @Override
    public void setSaleAndReviewNumber(List<Product> ps) {
        for (Product p : ps) {
            setSaleAndReviewNumber(p);
        }
    }

    @Override
    public List<Product> search(String keyword) {
        ProductExample example = new ProductExample();
        example.createCriteria().andNameLike("%" + keyword + "%");
        example.setOrderByClause("id desc");
        List<Product> ps = productMapper.selectByExample(example);
        setFirstProductImage(ps);
        setCategory(ps);
        return ps;
    }

    @Override
    public void deleteStock(List<Product> ps, int orderItemNumber) {
        for (Product p : ps) {
            deleteStock(p, orderItemNumber);
        }
    }

    @Override
    public void deleteStock(Product p, int orderItemNumber) {
        int stock = p.getStock();
        p.setStock(stock - orderItemNumber);
        productMapper.updateByPrimaryKey(p);
    }

    @Override
    public void orderByDate(Category c) {
        ProductExample example = new ProductExample();
        example.createCriteria().andCidEqualTo(c.getId());
        example.setOrderByClause("createDate desc");
        List<Product> ps = productMapper.selectByExample(example);
        c.setProducts(ps);
    }

    @Override
    public void orderByImage(Category c) {
        List<Product> ps = productMapper.listByProductImage(c.getId());
        c.setProducts(ps);
    }

    @Override
    public void orderByPrice(Category c) {
        ProductExample example = new ProductExample();
        example.createCriteria().andCidEqualTo(c.getId());
        example.setOrderByClause("promotePrice desc");
        List<Product> ps = productMapper.selectByExample(example);
        c.setProducts(ps);
    }

    @Override
    public void orderByAll(Category c) {
        List<Product> ps = productMapper.listByProductAll(c.getId());
        c.setProducts(ps);
    }

    @Override
    public void orderBySaleCount(Category c) {
        List<Product> ps = productMapper.listByProductSaleCount(c.getId());
        c.setProducts(ps);
    }

    @Override
    public void orderByReview(Category c) {
        List<Product> ps = productMapper.listByProductReview(c.getId());
        c.setProducts(ps);
    }

    @Override
    public void fill(Category c) {
        List<Product> ps = list(c.getId());
        c.setProducts(ps);
    }

}
