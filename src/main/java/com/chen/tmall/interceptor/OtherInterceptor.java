package com.chen.tmall.interceptor;


import com.chen.tmall.pojo.Category;
import com.chen.tmall.pojo.OrderItem;
import com.chen.tmall.pojo.User;
import com.chen.tmall.service.CategoryService;
import com.chen.tmall.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OtherInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderItemService orderItemService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        List<Category> cs = categoryService.list();
        request.getSession().setAttribute("cs", cs);

        String contextPath = request.getSession().getServletContext().getContextPath();
        contextPath = contextPath + "/forehome";
        request.getSession().setAttribute("contextPath", contextPath);

        User user = (User) request.getSession().getAttribute("user");
        int cartTotalItemNumber = 0;
        if (null != user) {
            List<OrderItem> ois = orderItemService.listByUser(user.getId());
            for (OrderItem oi : ois) {
                cartTotalItemNumber += oi.getNumber();
            }
        }
        request.getSession().setAttribute("cartTotalItemNumber", cartTotalItemNumber);
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
