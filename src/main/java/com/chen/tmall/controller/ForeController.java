package com.chen.tmall.controller;

import com.chen.tmall.pojo.*;
import com.chen.tmall.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.chen.tmall.util.Page;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class ForeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ReviewService reviewService;

    @RequestMapping("forehome")
    public String home(Model model) {
        List<Category> cs = categoryService.list();
        productService.fill(cs);
        productService.fillByRow(cs);
        model.addAttribute("cs", cs);
        return "fore/home";
    }

    @RequestMapping("foreregister")
    public String register(Model model, User user) {
        String name = user.getName();
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        boolean exist = userService.isExist(name);

        if (exist) {
            String m = "用户名已经被使用,不能使用";
            model.addAttribute("msg", m);


            return "fore/register";
        }
        userService.add(user);

        return "redirect:registerSuccessPage";
    }

    @RequestMapping("forelogin")
    public String login(@RequestParam("name") String name, @RequestParam("password") String password, Model model, HttpSession session) {
        name = HtmlUtils.htmlEscape(name);
        User user = userService.get(name, password);

        if (null == user) {
            model.addAttribute("msg", "账号密码错误");
            return "fore/login";
        }
        session.setAttribute("user", user);
        return "redirect:forehome";
    }

    @RequestMapping("forelogout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:forehome";
    }

    @RequestMapping("foreproduct")
    public String product(int pid, Model model) {
        Product p = productService.get(pid);

        List<ProductImage> productSingleImages = productImageService.list(p.getId(), ProductImageService.type_single);
        List<ProductImage> productDetailImages = productImageService.list(p.getId(), ProductImageService.type_detail);
        p.setProductSingleImages(productSingleImages);
        p.setProductDetailImages(productDetailImages);

        List<PropertyValue> pvs = propertyValueService.list(p.getId());
        List<Review> reviews = reviewService.list(p.getId());
        productService.setSaleAndReviewNumber(p);

        model.addAttribute("reviews", reviews);
        model.addAttribute("p", p);
        model.addAttribute("pvs", pvs);
        return "fore/product";
    }

    @RequestMapping("forecheckLogin")
    @ResponseBody
    public String checkLogin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (null != user) {
            return "success";
        }
        return "fail";
    }

    @RequestMapping("foreloginAjax")
    @ResponseBody
    public String loginAjax(@RequestParam("name") String name, @RequestParam("password") String password, HttpSession session) {
        name = HtmlUtils.htmlEscape(name);
        User user = userService.get(name, password);

        if (null == user) {
            return "fail";
        }
        session.setAttribute("user", user);
        return "success";
    }

    @RequestMapping("forecategory")
    public String category(int cid, String sort, Model model, Page page) {
        Category c = categoryService.get(cid);
        PageHelper.offsetPage(page.getStart(), 12);
        /*productService.fill(c);*/
        if (null != sort) {
            switch (sort) {
                case "review":
                    /*Collections.sort(c.getProducts(), new ProductReviewComparator());*/
                    productService.orderByReview(c);
                    break;
                case "date":
                    /* Collections.sort(c.getProducts(), new ProductDateComparator());*/
                    productService.orderByDate(c);
                    break;
                case "saleCount":
                    /*Collections.sort(c.getProducts(), new ProductSaleCountComparator());*/
                    productService.orderBySaleCount(c);
                    break;
                case "price":
                    /*Collections.sort(c.getProducts(), new ProductPriceComparator());*/
                    productService.orderByPrice(c);
                    break;
                case "all":
                    /*Collections.sort(c.getProducts(), new ProductAllComparator());*/
                    productService.orderByAll(c);
                    break;
                case "image":
                    /* Collections.sort(c.getProducts(), new ProductImageComparator());*/
                    productService.orderByImage(c);
                    break;
            }
        } else {
            productService.orderByAll(c);
        }
        productService.setSaleAndReviewNumber(c.getProducts());
        productService.setFirstProductImage(c.getProducts());
        int total = (int) new PageInfo<>(c.getProducts()).getTotal();
        page.setTotal(total);
        if (null != sort) {
            page.setParam("&cid=" + c.getId() + "&sort=" + sort);
        } else {
            page.setParam("&cid=" + c.getId());
        }
        model.addAttribute("page", page);
        model.addAttribute("c", c);
        return "fore/category";
    }

    @RequestMapping("foresearch")
    public String search(String keyword, Model model) {
        PageHelper.offsetPage(0, 20);
        List<Product> ps = productService.search(keyword);
        productService.setSaleAndReviewNumber(ps);
        model.addAttribute("ps", ps);
        return "fore/searchResult";
    }

    @RequestMapping("forebuyone")
    public String buyone(int pid, int num, HttpSession session) {
        Product p = productService.get(pid);
        int oiid = 0;
        boolean temp = false;
        User user = (User) session.getAttribute("user");
        List<OrderItem> ois = orderItemService.listByUser(user.getId());
        for (OrderItem oi : ois) {
            if (oi.getProduct().getId().intValue() == p.getId().intValue()) {
                oi.setNumber(oi.getNumber() + num);
                temp = true;
                oiid = oi.getId();
                break;
            }
        }
        if (!temp) {
            OrderItem oi = new OrderItem();
            oi.setNumber(num);
            oi.setPid(pid);
            orderItemService.add(oi);
            oiid = oi.getId();
        }
        return "redirect:/forebuy?oiid=" + oiid;
    }

    @RequestMapping("forebuy")
    public String buy(Model model, HttpSession session, String[] oiid) {
        List<OrderItem> ois = new ArrayList<>();
        int total = 0;
        for (String stroi : oiid) {
            int id = Integer.parseInt(stroi);
            OrderItem oi = orderItemService.get(id);
            total += oi.getProduct().getPromotePrice() * oi.getNumber();
            ois.add(oi);
        }
        session.setAttribute("ois", ois);
        model.addAttribute("total", total);
        return "fore/buy";
    }

    @RequestMapping("foreaddCart")
    @ResponseBody
    public String addCart(int pid, int num, Model model, HttpSession session) {
        Product p = productService.get(pid);
        User user = (User) session.getAttribute("user");
        List<OrderItem> ois = orderItemService.listByUser(user.getId());
        boolean temp = false;
        for (OrderItem oi : ois) {
            if (oi.getProduct().getId().intValue() == p.getId().intValue()) {
                oi.setNumber(oi.getNumber() + num);
                orderItemService.update(oi);
                temp = true;
                break;
            }
        }
        if (!temp) {
            OrderItem oi = new OrderItem();
            oi.setNumber(num);
            oi.setUid(user.getId());
            oi.setPid(p.getId());
            orderItemService.add(oi);
        }
        return "success";
    }

    @RequestMapping("forecart")
    public String cart(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<OrderItem> ois = orderItemService.listByUser(user.getId());
        model.addAttribute("ois", ois);
        return "fore/cart";
    }

    @RequestMapping("deleteOrderItemPage")
    @ResponseBody
    public String deleteOrderItem(@RequestParam("oiid") int oiid, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (null == user) {
            return "fail";
        }
        orderItemService.delete(oiid);
        return "success";
    }

    @RequestMapping("forechangeOrderItem")
    @ResponseBody
    public String changeOrderItem(@RequestParam("pid") int pid, @RequestParam("num") int num, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (null == user) {
            return "fail";
        }
        List<OrderItem> ois = orderItemService.listByUser(user.getId());
        for (OrderItem oi : ois) {
            if (oi.getPid().intValue() == pid) {
                oi.setNumber(num);
                orderItemService.update(oi);
                break;
            }
        }
        return "success";
    }

    @RequestMapping("forecreateOrder")
    public String createOrder(Order o, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderCode = sdf.format(new Date()) + RandomUtils.nextInt(10000);
        List<OrderItem> ois = (List<OrderItem>) session.getAttribute("ois");
        o.setOrderCode(orderCode);
        o.setStatus(OrderService.waitPay);
        o.setCreateDate(new Date());
        o.setUid(user.getId());
        for (OrderItem oi : ois) {
            oi.setUid(user.getId());
        }
        float total = orderService.add(o, ois);
        return "redirect:/forealipay?oid=" + o.getId() + "&total=" + total;
    }

    @RequestMapping("forepayed")
    public String payed(int oid, float total, Model model) {
        Order order = orderService.get(oid);
        order.setStatus(OrderService.waitDelivery);
        order.setPayDate(new Date());
        orderService.update(order);
        List<OrderItem> ois = orderItemService.list(order.getId());
        orderItemService.deleteProductStock(ois);
        String arriveTime = "";
        Date createDate = order.getCreateDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(createDate);
        String[] strs = StringUtils.split(str, "-");
        int m = Integer.parseInt(strs[1]);
        int n = Integer.parseInt(strs[2]);
        int k = Integer.parseInt(strs[0]);
        if (n + 7 > 30) {
            m++;
            if (m > 12) {
                m = m - 12;
                k++;
            }
            n = (n + 7) - 30;
        }
        arriveTime = k + "年" + m + "月" + n + "日";
        model.addAttribute("arriveTime", arriveTime);
        model.addAttribute("o", order);
        return "fore/payed";
    }

    @RequestMapping("forebought")
    public String bought(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Order> os = orderService.list(user.getId(), OrderService.delete);
        orderItemService.fill(os);
        model.addAttribute("os", os);
        return "fore/bought";
    }

    @RequestMapping("foredeleteOrder")
    @ResponseBody
    public String deleteOrder(int oid, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (null != user) {
            Order order = orderService.get(oid);
            order.setStatus(OrderService.delete);
            orderService.update(order);
            return "success";
        }
        return "fail";
    }

    @RequestMapping("foreconfirmPay")
    public String confirmPay(int oid, Model model) {
        Order o = orderService.get(oid);
        orderItemService.fill(o);
        model.addAttribute("o", o);
        return "fore/confirmPay";
    }

    @RequestMapping("foreorderConfirmed")
    public String orderConfirmed(Model model, int oid) {
        Order order = orderService.get(oid);
        order.setStatus(OrderService.waitReview);
        order.setConfirmDate(new Date());
        orderService.update(order);
        return "fore/confirmed";
    }

    @RequestMapping("forereview")
    public String review(Model model, int oiid) {
        OrderItem oi = orderItemService.get(oiid);
        Product p = oi.getProduct();
        List<Review> reviews = reviewService.list(p.getId());
        productService.setSaleAndReviewNumber(p);
        Order o = orderService.get(oi.getOid());
        model.addAttribute("o", o);
        model.addAttribute("p", p);
        model.addAttribute("oi", oi);
        model.addAttribute("reviews", reviews);
        return "fore/review";
    }

    @RequestMapping("foredoreview")
    public String doreview(String content, int oid, int pid, int oiid, HttpSession session) {
        Order order = orderService.get(oid);
        List<OrderItem> ois = orderItemService.list(oid);
        order.setStatus(OrderService.finish);
        orderService.update(order);
        Product p = productService.get(pid);
        content = HtmlUtils.htmlEscape(content);
        User user = (User) session.getAttribute("user");
        Review r = new Review();
        r.setUid(user.getId());
        r.setPid(p.getId());
        r.setContent(content);
        r.setCreateDate(new Date());
        reviewService.add(r);
        return "redirect:/forereview?oiid=" + oiid + "&showonly=" + "true";
    }
}

